package cl.pymap.pymapwar.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.pymap.pymapejb.bean.CompraDAOLocal;
import cl.pymap.pymapejb.bean.ProductoCompraDAOLocal;
import cl.pymap.pymapejb.bean.ProductoNegocioDAOLocal;
import cl.pymap.pymapejb.model.Compra;
import cl.pymap.pymapejb.model.Producto;
import cl.pymap.pymapejb.model.ProductoNegocio;
import cl.pymap.pymapejb.model.Usuario;
import cl.pymap.pymapwar.util.CarritoUtil;

/**
 * Servlet implementation class CompraControlador
 */
@WebServlet("/Compra")
public class CompraControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	CompraDAOLocal daoCompra;
	@Inject
	ProductoCompraDAOLocal daoProductoCompra;
	@Inject
	ProductoNegocioDAOLocal daoProductoNegocio;
	
	Compra c = new Compra();
	
	int id;
	double coste;
	String estado;
	String fecha;
	String hora;
	String usuarioFK;
	String negocioFK;
	
	private String jpql;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompraControlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession(false);
		
		String vista = request.getParameter("vista");
		String opcion = request.getParameter("opcion");
		String asincrono = request.getParameter("asincrono");
		
		if (vista == null) {
			vista = "";
		}
		if (opcion == null) {
			opcion = "";
		}
		if (asincrono == null) {
			asincrono = "";
		}
		
		switch (vista) { //Renderización de vistas.
		case "realizada": {
			if (sesion.getAttribute("usuario") != null) {
				if ((((Usuario)(sesion.getAttribute("usuario"))).getTipo()).equals("Administrador")) {
					response.sendRedirect(request.getContextPath());
				}
				else if ((((Usuario)(sesion.getAttribute("usuario"))).getTipo()).equals("Cliente")) {
					String jpql = "SELECT c.id, c.coste, c.estado FROM Compra c WHERE c.usuarioFK = " + "'" + ((Usuario)sesion.getAttribute("usuario")).getCorreo() + "'" + " ORDER BY c.id DESC";
					Object[] datosCompra = daoCompra.customQuery(jpql).get(0);
					
					this.id = (int)datosCompra[0];
					
					String rutNegocio = (String)sesion.getAttribute("rutNegocioUltimaCompra");
					
					jpql = "SELECT p, pc.unidades FROM Producto p INNER JOIN ProductoCompra pc ON p.id = pc.productoFK WHERE pc.compraFK = " + this.id;
					
					List<Object[]> productosCompra = daoCompra.customQuery(jpql);
					List<ProductoNegocio> productosNegocio = daoProductoNegocio.findAll("negocioFK", rutNegocio);
					
					List<Double> precios = new ArrayList<Double>();
					for (ProductoNegocio pn : productosNegocio) {
						for (Object[] o : productosCompra) {
							Producto p = (Producto)o[0];
							if (p.getId() == pn.getProductoFK()) {
								precios.add(pn.getPrecio());
							}
						}
					}
					
					sesion.removeAttribute("rutNegocioUltimaCompra");
					
					sesion.setAttribute("datosCompra", datosCompra);
					sesion.setAttribute("productosCompra", productosCompra);
					sesion.setAttribute("precios", precios);
					sesion.setAttribute("renderizarVista", "realizada");
					response.sendRedirect(request.getContextPath() + "/compra/realizada");
				}
			}
			else {
				response.sendRedirect(request.getContextPath());
			}
			break;
		}
		case "mis_compras": {
			if (sesion.getAttribute("usuario") != null) {
				if ((((Usuario)(sesion.getAttribute("usuario"))).getTipo()).equals("Administrador")) {
					response.sendRedirect(request.getContextPath());
				}
				else if ((((Usuario)(sesion.getAttribute("usuario"))).getTipo()).equals("Cliente")) {
					List<Compra> compras = daoCompra.findAll("usuarioFK", (((Usuario)(sesion.getAttribute("usuario"))).getCorreo()));
	
					sesion.setAttribute("compras", compras);
					sesion.setAttribute("renderizarVista", "misCompras");
					
					response.sendRedirect(request.getContextPath() + "/compra/mis-compras");
				}
			}
			else {
				response.sendRedirect(request.getContextPath());
			}
			break;
		}
		default:
			break;
		}
		
		switch (opcion) { //Acceso a datos GET.
		case "1": {
			if (sesion.getAttribute("usuario") != null) {
				if ((((Usuario)(sesion.getAttribute("usuario"))).getTipo()).equals("Administrador")) {
					response.sendRedirect(request.getContextPath());
				}
				else if ((((Usuario)(sesion.getAttribute("usuario"))).getTipo()).equals("Cliente")) {
					String rutNegocio =  request.getParameter("negocio");
					String nombreCarrito = request.getParameter("carrito");
					
					List<Object[]> carrito = (List<Object[]>)sesion.getAttribute(nombreCarrito);
					
					DateTimeFormatter fecha = DateTimeFormatter.ofPattern("yyyy/MM/dd");
				    DateTimeFormatter hora = DateTimeFormatter.ofPattern("HH:mm:ss");
					
					this.coste = new CarritoUtil().obtenerTotal(carrito);
					this.estado = "Confirmado";
					this.fecha = fecha.format(LocalDateTime.now());
				    this.hora = hora.format(LocalDateTime.now());
					this.usuarioFK = ((Usuario)(sesion.getAttribute("usuario"))).getCorreo();
					
					this.c.setCoste(coste);
					this.c.setEstado(estado);
					this.c.setFecha(this.fecha);
					this.c.setHora(this.hora);
					this.c.setUsuarioFK(usuarioFK);
					this.c.setNegocioFK(rutNegocio);
					
					if (daoCompra.save(c)) {
						String jpql = "SELECT c.id, c.coste, c.estado FROM Compra c WHERE c.usuarioFK = " + "'" + ((Usuario)sesion.getAttribute("usuario")).getCorreo() + "'" + " ORDER BY c.id DESC";
						
						int idCompra = (int)daoCompra.customQuery(jpql).get(0)[0];

						if (daoProductoCompra.save(carrito, idCompra)) {
							sesion.setAttribute("crearCompra", "Exitoso");	
							sesion.removeAttribute(nombreCarrito);
							
							List<String[]> carritos = (List<String[]>)sesion.getAttribute("carritos");
							
							for (String[] carritoEnLista : carritos) {
								if (carritoEnLista[2].equals(nombreCarrito)) {
									carritos.remove(carritoEnLista);
									break;
								}
							}
							
							if (carritos.isEmpty() == true) {
								sesion.setAttribute("carritos", null);
							}
							else {
								sesion.setAttribute("carritos", carritos);
							}
						}
						else {
							sesion.setAttribute("crearCompra", "Fallido");
						}
					}
					else {
						sesion.setAttribute("crearCompra", "Fallido");
					}
					
					sesion.setAttribute("rutNegocioUltimaCompra", rutNegocio);
					
					response.sendRedirect(request.getContextPath() + "/compra/realizada");
				}
			}
			else {
				response.sendRedirect(request.getContextPath());
			}
			break;
		}
		default:
			break;
		}
		
		switch (asincrono) { //Peticiones REST.
		case "1": {
			//
			break;
		}
		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession(false);
		
		String opcion = request.getParameter("opcion");
		
		switch (opcion) { //Acceso a datos POST.
		case "1": {
			//
			break;
		}
		default:
			break;
		}
	}

}
