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

import cl.pymap.pymapejb.bean.UsuarioDAOLocal;
import cl.pymap.pymapejb.model.Usuario;
import cl.pymap.pymapwar.util.ArchivoUtil;
import cl.pymap.pymapwar.util.EncriptacionUtil;

/**
 * Servlet implementation class UsuarioControlador
 */
@MultipartConfig
@WebServlet("/Usuario")
public class UsuarioControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Inject
    private UsuarioDAOLocal daoUsuario;
    
    private Usuario u = new Usuario();
    
	private String correo;
	private String clave;
	private String tipo;
	private String primerNombre;
	private String segundoNombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String imagen = "";
	
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
    public UsuarioControlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession(false);
		
		this.rutaArchivos = getServletContext().getRealPath("/uploads/models/usuario/images/");
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
		case "registrarse": {
			if (sesion.getAttribute("usuario") == null) {
				sesion.setAttribute("renderizarVista", "registrarse");
				response.sendRedirect(request.getContextPath() + "/usuario/registrarse");
			}
			else {
				response.sendRedirect(request.getContextPath());
			}
			break;
		}
		case "iniciar_sesion": {
			if (sesion.getAttribute("usuario") == null) {
				sesion.setAttribute("renderizarVista", "iniciarSesion");
				response.sendRedirect(request.getContextPath() + "/usuario/iniciar-sesion");
			}
			else {
				response.sendRedirect(request.getContextPath());
			}
			break;
		}
		case "editar_perfil": {
			if (sesion.getAttribute("usuario") != null) {
				sesion.setAttribute("renderizarVista", "editarPerfil");
				response.sendRedirect(request.getContextPath() + "/usuario/editar-perfil");
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
		case "1": { //Cerrar sesión.
			sesion.removeAttribute("usuario");
			response.sendRedirect(request.getContextPath());
			break;
		}
		default:
			break;
		}
		
		switch (asincrono) { //Acceso a datos GET.
		case "1": {
			//
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
		
		this.rutaArchivos = getServletContext().getRealPath("/uploads/models/usuario/images/");
		this.rutaArchivoDestino = new File(rutaArchivos);
		
		String opcion = request.getParameter("opcion");
		
		switch (opcion) { //Acceso a datos POST.
		case "1": { //Registro.
			this.correo = request.getParameter("correo");
			this.clave = request.getParameter("clave");
			this.primerNombre = request.getParameter("primerNombre");
			this.segundoNombre = request.getParameter("segundoNombre");
			this.apellidoPaterno = request.getParameter("apellidoPaterno");
			this.apellidoMaterno = request.getParameter("apellidoMaterno");
			
			if (this.correo != null && this.clave != null && this.primerNombre != null && this.segundoNombre != null && this.apellidoPaterno != null && this.apellidoMaterno != null) {
				this.u.setCorreo(correo);
				this.u.setClave(encriptacionUtil.encriptarClave(clave));
				this.u.setTipo("Cliente");
				this.u.setPrimerNombre(primerNombre);
				this.u.setSegundoNombre(segundoNombre);
				this.u.setApellidoPaterno(apellidoPaterno);
				this.u.setApellidoMaterno(apellidoMaterno);
				this.u.setImagen("Default.png");
				
				if (daoUsuario.save(this.u)) {
					sesion.setAttribute("registro", "Exitoso");
				}
				else {
					sesion.setAttribute("registro", "Fallido");
				}			
				response.sendRedirect(request.getContextPath() + "/usuario/registrarse");
			}
			else {
				response.sendRedirect(request.getContextPath());
			}	
			break;
		}
		case "2": { //Iniciar sesión.
			this.correo = request.getParameter("correo");
			this.clave = request.getParameter("clave");
			
			if (this.correo != null && this.clave != null) {
				this.u = daoUsuario.find(this.correo);
				
				if (this.u != null) {
					if (encriptacionUtil.validarClave(this.clave, this.u.getClave()) == true) {
						sesion.setAttribute("usuario", this.u);
						response.sendRedirect(request.getContextPath());
					}
					else {
						sesion.setAttribute("errorLogin", "Credenciales incorrectas");
						response.sendRedirect(request.getContextPath() + "/usuario/iniciar-sesion");
					}
				}
				else {
					sesion.setAttribute("errorLogin", "Credenciales incorrectas");
					response.sendRedirect(request.getContextPath() + "/usuario/iniciar-sesion");
				}		
			}
			else {
				response.sendRedirect(request.getContextPath());
			}
			break;
		}
		case "3": { //Editar perfil.
			this.correo = request.getParameter("correo");
			
			String clave = request.getParameter("clave");
			if (clave.equals("Clave no cambiada")) {
				this.clave = (((Usuario)(sesion.getAttribute("usuario"))).getClave());
			}
			else {
				this.clave = encriptacionUtil.encriptarClave(clave);
			}
			
			this.primerNombre = request.getParameter("primerNombre");
			this.segundoNombre = request.getParameter("segundoNombre");
			this.apellidoPaterno = request.getParameter("apellidoPaterno");
			this.apellidoMaterno = request.getParameter("apellidoMaterno");
			
			archivo = request.getPart("imagen");
			
			String correoActual = (((Usuario)(sesion.getAttribute("usuario"))).getCorreo());
			boolean correoRepetido = false;
			if (correoActual.equalsIgnoreCase(this.correo) == false) {
				Usuario usuario = daoUsuario.find(this.correo);
				if (usuario != null) {
					correoRepetido = true;
				}
			}
			
			if (correoRepetido == false) {
				if (archivo.getSubmittedFileName().equals("") == false && archivoUtil.validarFormato(archivo.getSubmittedFileName(), formatosSoportados) == true) {			
					if (((Usuario)(sesion.getAttribute("usuario"))).getImagen().equals("Default.png")) {
						//
					}
					else {
						File archivoAntiguo = new File(rutaArchivos + (((Usuario)(sesion.getAttribute("usuario"))).getImagen()));
						archivoAntiguo.delete();
					}
					
					this.imagen = archivoUtil.guardarArchivo(archivo, rutaArchivoDestino);
					
					this.u.setCorreo(correo);
					this.u.setClave(this.clave);
					this.u.setTipo((((Usuario)(sesion.getAttribute("usuario"))).getTipo()));
					this.u.setPrimerNombre(primerNombre);
					this.u.setSegundoNombre(segundoNombre);
					this.u.setApellidoPaterno(apellidoPaterno);
					this.u.setApellidoMaterno(apellidoMaterno);
					this.u.setImagen(imagen);
					
					if (daoUsuario.update(this.u)) {
						sesion.setAttribute("editarPerfil", "Exitoso");
					}
					else {
						sesion.setAttribute("editarPerfil", "Fallido");
					}
				}
				else if (archivo.getSubmittedFileName().equals("") == true) {
					this.imagen = (((Usuario)(sesion.getAttribute("usuario"))).getImagen());
					
					this.u.setCorreo(correo);
					this.u.setClave(this.clave);
					this.u.setTipo((((Usuario)(sesion.getAttribute("usuario"))).getTipo()));
					this.u.setPrimerNombre(primerNombre);
					this.u.setSegundoNombre(segundoNombre);
					this.u.setApellidoPaterno(apellidoPaterno);
					this.u.setApellidoMaterno(apellidoMaterno);
					this.u.setImagen(imagen);
					
					if (daoUsuario.update(this.u)) {
						sesion.setAttribute("editarPerfil", "Exitoso");
						
						Usuario usuarioConectado = (Usuario)sesion.getAttribute("usuario");
						usuarioConectado.setImagen(imagen);
						
						sesion.setAttribute("usuario", usuarioConectado);
					}
					else {
						sesion.setAttribute("editarPerfil", "Fallido");
					}
				}
				else {
					sesion.setAttribute("editarPerfil", "Fallido");
				}
			}
			else {
				sesion.setAttribute("editarPerfil", "Fallido");
			}
			response.sendRedirect(request.getContextPath() + "/usuario/editar-perfil");
			break;
		}
		default:
			break;
		}
	}

}
