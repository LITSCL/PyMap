package cl.pymap.pymapwar.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.pymap.pymapejb.bean.NegocioDAOLocal;
import cl.pymap.pymapejb.bean.ProductoDAOLocal;
import cl.pymap.pymapejb.bean.ProductoNegocioDAOLocal;
import cl.pymap.pymapejb.model.Producto;
import cl.pymap.pymapejb.model.ProductoNegocio;


/**
 * Servlet implementation class CarritoControlador
 */
@WebServlet("/Carrito")
public class CarritoControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	private ProductoDAOLocal daoProducto;
	@Inject
	private NegocioDAOLocal daoNegocio;
	@Inject
	private ProductoNegocioDAOLocal daoProductoNegocio;
	
	private String jpql;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarritoControlador() {
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
		case "mostrar": {
			String rutNegocio = request.getParameter("negocio");
			sesion.setAttribute("renderizarVista", "mostrar");
			response.sendRedirect(request.getContextPath() + "/carrito/mostrar" + "?negocio=" + rutNegocio);
			break;
		}
		case "mis_carritos": {
			sesion.setAttribute("renderizarVista", "misCarritos");
			response.sendRedirect(request.getContextPath() + "/carrito/mis-carritos");
			break;
		}
		default:
			break;
		}
		
		switch (opcion) { //Acceso a datos GET.
		case "1": { //Añadir.
			try {
				String rutNegocio = request.getParameter("negocio");
				String nombreNegocio = daoNegocio.find(rutNegocio).getNombre();
				int idProducto = Integer.parseInt(request.getParameter("id"));
				int contador = 0;
				
				boolean carritoRecienCreado = false;
				
				List<ProductoNegocio> productosNegocio = daoProductoNegocio.findAll("negocioFK", rutNegocio);
				
				double precioProductoNegocio = 0;
				for (ProductoNegocio pn : productosNegocio) {
					if (pn.getProductoFK() == idProducto) {
						precioProductoNegocio = pn.getPrecio();
					}
				}
				
				String nombreCarrito = "carrito" + "->" + rutNegocio;
				
				List<String[]> carritos = (List<String[]>)sesion.getAttribute("carritos");
				
				if (carritos == null || carritos.isEmpty() == true) {
					carritos = new ArrayList<String[]>();
				}
				
				if (sesion.getAttribute(nombreCarrito) != null) {
					List<Object[]> carrito = (List<Object[]>)sesion.getAttribute(nombreCarrito);
					
					for (int i = 0; i < carrito.size(); i++) {
						if ((int)carrito.get(i)[0] == idProducto) {
							int unidadesActuales = (int)carrito.get(i)[2];			
							carrito.get(i)[2] = unidadesActuales + 1;
							sesion.setAttribute("carrito" + "->" + rutNegocio, carrito);
							contador++;	
						}
					}
				}
				
				if (sesion.getAttribute(nombreCarrito) == null) {
					List<Object[]> carrito = new ArrayList<Object[]>();
					Producto p = daoProducto.find(idProducto);
					
					Object[] productoEnCarrito = new Object[4];
					productoEnCarrito[0] = p.getId();
					productoEnCarrito[1] = precioProductoNegocio;
					productoEnCarrito[2] = 1;
					productoEnCarrito[3] = p;
					
					carrito.add(productoEnCarrito);
					sesion.setAttribute(nombreCarrito, carrito);
					carritoRecienCreado = true;
					
					String[] informacionCarrito = {rutNegocio, nombreNegocio, nombreCarrito};
					carritos.add(informacionCarrito);
					
					sesion.setAttribute("carritos", carritos);
				}
				
				if (sesion.getAttribute(nombreCarrito) != null && contador == 0) {
					if (carritoRecienCreado == false) {
						List<Object[]> carrito = (List<Object[]>)sesion.getAttribute(nombreCarrito);
						Producto p = daoProducto.find(idProducto);
						
						Object[] productoEnCarrito = new Object[4];
						productoEnCarrito[0] = p.getId();
						productoEnCarrito[1] = precioProductoNegocio;
						productoEnCarrito[2] = 1;
						productoEnCarrito[3] = p;
						
						carrito.add(productoEnCarrito);
					}
				}
				response.sendRedirect(request.getContextPath() + "/carrito/mostrar" + "?negocio=" + rutNegocio);
			} catch (Exception ex) {
				response.sendRedirect(request.getContextPath());
			}
			break;
		}
		case "2": { //Remover.
			try {
				String rutNegocio = request.getParameter("negocio");
				int indice = Integer.parseInt(request.getParameter("indice"));
				
				String nombreCarrito = "carrito" + "->" + rutNegocio;
				
				List<Object[]> carrito = (List<Object[]>)sesion.getAttribute(nombreCarrito);
				
				carrito.remove(indice);
				sesion.setAttribute(nombreCarrito, carrito);
				
				response.sendRedirect(request.getContextPath() + "/carrito/mostrar" + "?negocio=" + rutNegocio);
			} catch (Exception ex) {
				response.sendRedirect(request.getContextPath());
			}
			break;
		}
		case "3": { //Remover todos.
			String rutNegocio = request.getParameter("negocio");
			
			String nombreCarrito = "carrito" + "->" + rutNegocio;
			
			sesion.removeAttribute(nombreCarrito);
			
			List<String[]> carritos = (List<String[]>)sesion.getAttribute("carritos");
			
			for (String[] carrito : carritos) {
				if (carrito[2].equals(nombreCarrito)) {
					carritos.remove(carrito);
					break;
				}
			}
			
			if (carritos.isEmpty() == true) {
				sesion.setAttribute("carritos", null);
			}
			else {
				sesion.setAttribute("carritos", carritos);
			}
			
			response.sendRedirect(request.getContextPath() + "/carrito/mis-carritos");
			break;
		}
		case "4": { //Aumentar unidad.
			try {
				String rutNegocio = request.getParameter("negocio");
				
				String nombreCarrito = "carrito" + "->" + rutNegocio;
				
				int indice = Integer.parseInt(request.getParameter("indice"));
				List<Object[]> carrito = (List<Object[]>)sesion.getAttribute(nombreCarrito);
				
				int unidadesActuales = (int)carrito.get(indice)[2];
				
				carrito.get(indice)[2] = unidadesActuales + 1;
				sesion.setAttribute(nombreCarrito, carrito);
				
				response.sendRedirect(request.getContextPath() + "/carrito/mostrar" + "?negocio=" + rutNegocio);
			} catch (Exception ex) {
				response.sendRedirect(request.getContextPath());
			}
			break;
		}
		case "5": { //Disminuir unidad.
			try {
				String rutNegocio = request.getParameter("negocio");
				
				String nombreCarrito = "carrito" + "->" + rutNegocio;
				
				int indice = Integer.parseInt(request.getParameter("indice"));
				List<Object[]> carrito = (List<Object[]>)sesion.getAttribute(nombreCarrito);
				
				carrito.get(indice)[2] = (int)carrito.get(indice)[2] - 1;
				if ((int)carrito.get(indice)[2] == 0) {
					carrito.remove(indice);
				}
				sesion.setAttribute(nombreCarrito, carrito);
				
				response.sendRedirect(request.getContextPath() + "/carrito/mostrar" + "?negocio=" + rutNegocio);
			} catch (Exception ex) {
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
