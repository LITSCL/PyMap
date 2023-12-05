package cl.pymap.pymapwar.controller;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import cl.pymap.pymapejb.model.Usuario;
import cl.pymap.pymapejb.bean.MarcaDAOLocal;
import cl.pymap.pymapejb.model.Marca;
import cl.pymap.pymapwar.util.ArchivoUtil;
import cl.pymap.pymapwar.util.EncriptacionUtil;

/**
 * Servlet implementation class MarcaControlador
 */
@MultipartConfig
@WebServlet("/Marca")
public class MarcaControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	MarcaDAOLocal daoMarca;
	
	Marca m = new Marca();
	
	private int id;
	private String nombre;
	private String logo;
	
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
    public MarcaControlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession(false);
		
		this.rutaArchivos = getServletContext().getRealPath("/uploads/models/negocio/images/");
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
					sesion.setAttribute("renderizarVista", "agregarMarca");
					response.sendRedirect(request.getContextPath() + "/marca/agregar");
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
		
		switch (opcion) { //Peticiones REST.
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
		
		this.rutaArchivos = getServletContext().getRealPath("/uploads/models/negocio/images/");
		this.rutaArchivoDestino = new File(rutaArchivos);
		
		String opcion = request.getParameter("opcion");
		
		switch (opcion) { //Acceso a datos POST.
		case "1": { //Agregar.
			if (sesion.getAttribute("usuario") != null) {
				if ((((Usuario)(sesion.getAttribute("usuario"))).getTipo()).equals("Administrador")) {
					this.nombre = request.getParameter("nombre");
					
					archivo = request.getPart("logo");
					
					if (this.nombre != null) {
						if (archivo.getSubmittedFileName().equals("") == false && archivoUtil.validarFormato(archivo.getSubmittedFileName(), formatosSoportados) == true) {			
							this.logo = archivoUtil.guardarArchivo(archivo, rutaArchivoDestino);
							
							this.m.setNombre(nombre);
							this.m.setLogo(logo);
							
							if (daoMarca.save(this.m)) {
								sesion.setAttribute("agregarMarca", "Exitoso");
							}
							else {
								sesion.setAttribute("agregarMarca", "Fallido");
							}	
						}
						else {
							sesion.setAttribute("agregarMarca", "Fallido");
						}	
						response.sendRedirect(request.getContextPath() + "/marca/agregar");
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
