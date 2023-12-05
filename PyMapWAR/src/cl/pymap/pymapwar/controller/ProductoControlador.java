package cl.pymap.pymapwar.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.ws.rs.core.MediaType;

import cl.pymap.pymapejb.bean.CategoriaDAOLocal;
import cl.pymap.pymapejb.bean.MarcaDAOLocal;
import cl.pymap.pymapejb.bean.MarcaTipoProductoDAOLocal;
import cl.pymap.pymapejb.bean.ProductoDAOLocal;
import cl.pymap.pymapejb.bean.TipoProductoDAOLocal;
import cl.pymap.pymapejb.model.Marca;
import cl.pymap.pymapejb.model.MarcaTipoProducto;
import cl.pymap.pymapejb.model.Producto;
import cl.pymap.pymapejb.model.TipoProducto;
import cl.pymap.pymapejb.model.Usuario;
import cl.pymap.pymapwar.util.ArchivoUtil;
import cl.pymap.pymapwar.util.EncriptacionUtil;

/**
 * Servlet implementation class ProductoControlador
 */
@MultipartConfig
@WebServlet("/Producto")
public class ProductoControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Inject
    private ProductoDAOLocal daoProducto;
    @Inject
    private MarcaDAOLocal daoMarca;
    @Inject
    private MarcaTipoProductoDAOLocal daoMarcaTipoProducto;
    @Inject
    private CategoriaDAOLocal daoCategoria;
    @Inject
    private TipoProductoDAOLocal daoTipoProducto;
    
    private Producto p = new Producto();
    
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private String imagen;
    private int marcaFK;
    private int categoriaFK;
    private int tipoProductoFK;
    
	private Part archivo;
	private String rutaArchivos;
    private File rutaArchivoDestino;
    private String[] formatosSoportados = {".ico", ".png", ".jpg", ".jpeg"};
	
	private ArchivoUtil archivoUtil = new ArchivoUtil();
	private EncriptacionUtil encriptacionUtil = new EncriptacionUtil();
	
	private String jpql;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductoControlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession(false);
		
		this.rutaArchivos = getServletContext().getRealPath("/uploads/models/producto/images/");
		this.rutaArchivoDestino = new File(rutaArchivos);
		
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
		case "agregar": {
			if (sesion.getAttribute("usuario") != null) {
				if ((((Usuario)(sesion.getAttribute("usuario"))).getTipo()).equals("Administrador")) {
					List<Marca> marcas = daoMarca.getAll();
					
					List<MarcaTipoProducto> marcasTipoProducto = daoMarcaTipoProducto.findAll("marcaFK", "1");
					
					List<TipoProducto> tiposProducto = new ArrayList<TipoProducto>();
					for (MarcaTipoProducto mtp : marcasTipoProducto) {
						tiposProducto.add(daoTipoProducto.find(mtp.getTipoProductoFK()));
					}
					
					sesion.setAttribute("marcas", marcas);
					sesion.setAttribute("tiposProducto", tiposProducto);
					sesion.setAttribute("renderizarVista", "agregarProducto");
					response.sendRedirect(request.getContextPath() + "/producto/agregar");
				}
				else if ((((Usuario)(sesion.getAttribute("usuario"))).getTipo()).equals("Cliente")) {
					response.sendRedirect(request.getContextPath());
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
			//
			break;
		}
		default:
			break;
		}
		
		switch (asincrono) { //Peticiones REST.
		case "1": {
			int idMarca = -1;
			try {
				idMarca = Integer.parseInt(request.getParameter("marca"));
			} catch (Exception ex) {
				idMarca = -1;
			}
			
			if (idMarca != -1 && daoMarca.find(idMarca) != null) {
				List<MarcaTipoProducto> marcasTipoProducto = daoMarcaTipoProducto.findAll("marcaFK", Integer.toString(idMarca));
				
				List<TipoProducto> tiposProducto = new ArrayList<TipoProducto>();
				for (MarcaTipoProducto mtp : marcasTipoProducto) {
					tiposProducto.add(daoTipoProducto.find(mtp.getTipoProductoFK()));
				}
				
				JsonArrayBuilder builder = Json.createArrayBuilder();
				
				for (TipoProducto tp : tiposProducto) {
				     builder.add(Json.createObjectBuilder()
					        .add("id", tp.getId())
					        .add("nombre", tp.getNombre())
				     		.add("categoriaFK", tp.getCategoriaFK()));
				}
				
				JsonArray arrayJSON = builder.build();
				
				response.setContentType(MediaType.APPLICATION_JSON);
				
				try (PrintWriter pw = new PrintWriter(response.getOutputStream())) {
					pw.println(arrayJSON.toString());
				}
			}
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
		
		this.rutaArchivos = getServletContext().getRealPath("/uploads/models/producto/images/");
		this.rutaArchivoDestino = new File(rutaArchivos);
		
		String opcion = request.getParameter("opcion");
		
		switch (opcion) { //Acceso a datos POST.
		case "1": { //Agregar.
			if (sesion.getAttribute("usuario") != null) {
				if ((((Usuario)(sesion.getAttribute("usuario"))).getTipo()).equals("Administrador")) {
					this.nombre = request.getParameter("nombre");
					this.descripcion = request.getParameter("descripcion");
					
					try {
						this.precio = Integer.parseInt(request.getParameter("precio"));
					} catch (Exception ex) {
						this.precio = -1;
					}
					
					archivo = request.getPart("imagen");
					
					try {
						this.marcaFK = Integer.parseInt(request.getParameter("marca"));
					} catch (Exception ex) {
						this.marcaFK = -1;
					}
					
					try {
						this.tipoProductoFK = Integer.parseInt(request.getParameter("tipoProducto"));
					} catch (Exception ex) {
						this.tipoProductoFK = -1;
					}
					
					try {
						this.categoriaFK = daoTipoProducto.find(this.tipoProductoFK).getCategoriaFK();
					} catch (Exception ex) {
						this.categoriaFK = -1;
					}
					
					if (this.nombre != null && this.descripcion != null && (this.precio != -1 || this.precio == -1) && this.marcaFK != -1 && this.categoriaFK != -1 && this.tipoProductoFK != -1) {
						if (archivo.getSubmittedFileName().equals("") == false && archivoUtil.validarFormato(archivo.getSubmittedFileName(), formatosSoportados) == true) {			
							this.imagen = archivoUtil.guardarArchivo(archivo, rutaArchivoDestino);
							
							this.p.setNombre(nombre);
							this.p.setDescripcion(descripcion);
							
							if (this.precio == -1) {
								this.precio = 0;
							}
							this.p.setPrecio(precio);
							
							this.p.setImagen(imagen);
							this.p.setMarcaFK(marcaFK);
							this.p.setCategoriaFK(categoriaFK);
							this.p.setTipoProductoFK(tipoProductoFK);
							
							if (daoProducto.save(this.p)) {
								sesion.setAttribute("agregarProducto", "Exitoso");
							}
							else {
								sesion.setAttribute("agregarProducto", "Fallido");
							}	
						}
						else {
							sesion.setAttribute("agregarProducto", "Fallido");
						}	
						response.sendRedirect(request.getContextPath() + "/producto/agregar");
					}
					else {
						response.sendRedirect(request.getContextPath());
					}		
				}
				else if ((((Usuario)(sesion.getAttribute("usuario"))).getTipo()).equals("Cliente")) {
					response.sendRedirect(request.getContextPath());
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
	}

}
