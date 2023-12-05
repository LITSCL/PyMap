package cl.pymap.pymapwar.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import cl.pymap.pymapejb.bean.MarcaDAOLocal;
import cl.pymap.pymapejb.bean.MarcaNegocioDAOLocal;
import cl.pymap.pymapejb.bean.MarcaTipoNegocioDAOLocal;
import cl.pymap.pymapejb.bean.MarcaTipoProductoDAOLocal;
import cl.pymap.pymapejb.bean.NegocioDAOLocal;
import cl.pymap.pymapejb.bean.ProductoDAOLocal;
import cl.pymap.pymapejb.bean.ProductoNegocioDAOLocal;
import cl.pymap.pymapejb.bean.TipoProductoNegocioDAOLocal;
import cl.pymap.pymapejb.bean.TipoNegocioDAOLocal;
import cl.pymap.pymapejb.bean.TipoProductoDAOLocal;
import cl.pymap.pymapejb.model.Marca;
import cl.pymap.pymapejb.model.MarcaNegocio;
import cl.pymap.pymapejb.model.MarcaTipoNegocio;
import cl.pymap.pymapejb.model.MarcaTipoProducto;
import cl.pymap.pymapejb.model.Negocio;
import cl.pymap.pymapejb.model.Producto;
import cl.pymap.pymapejb.model.ProductoNegocio;
import cl.pymap.pymapejb.model.TipoProductoNegocio;
import cl.pymap.pymapejb.model.TipoProducto;
import cl.pymap.pymapejb.model.Usuario;
import cl.pymap.pymapwar.pagination.MarcaTipoNegocioPaginador;
import cl.pymap.pymapwar.pagination.NegocioPaginador;
import cl.pymap.pymapwar.pagination.ProductoPaginador;
import cl.pymap.pymapwar.pagination.TipoProductoPaginador;
import cl.pymap.pymapwar.util.ArchivoUtil;
import cl.pymap.pymapwar.util.EncriptacionUtil;
import cl.pymap.pymapwar.util.RemoverDuplicadosUtil;

/**
 * Servlet implementation class NegocioControlador
 */
@MultipartConfig
@WebServlet("/Negocio")
public class NegocioControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Inject
    private NegocioDAOLocal daoNegocio;
    @Inject
    private TipoNegocioDAOLocal daoTipoNegocio;
    @Inject
    private TipoProductoDAOLocal daoTipoProducto;
    @Inject
    private MarcaTipoNegocioDAOLocal daoMarcaTipoNegocio;
    @Inject
    private MarcaTipoProductoDAOLocal daoMarcaTipoProducto;
    @Inject
    private MarcaDAOLocal daoMarca;
    @Inject
    private MarcaNegocioDAOLocal daoMarcaNegocio;
    @Inject
    private TipoProductoNegocioDAOLocal daoNegocioTipoProducto;
    @Inject
    private ProductoDAOLocal daoProducto;
    @Inject
    private ProductoNegocioDAOLocal daoProductoNegocio;
    
    private Negocio n = new Negocio();
    
	private String rut;
	private String nombre;
	private String ciudad;
	private String comuna;
	private String calle;
	private String diasAtencion = "";
	private String metodosPago = "";
	private String estado;
	private String logo;
	private int tipoNegocioFK;
	private String usuarioFK;
	
	private Part archivo;
	private String rutaArchivos;
    private File rutaArchivoDestino;
    private String[] formatosSoportados = {".ico", ".png", ".jpg", ".jpeg"};
	
	private RemoverDuplicadosUtil removerDuplicadosUtil = new RemoverDuplicadosUtil();
	private ArchivoUtil archivoUtil = new ArchivoUtil();
	
	private MarcaTipoNegocioPaginador marcaTipoNegocioPaginador;
	private TipoProductoPaginador tipoProductoPaginador;
	private NegocioPaginador negocioPaginador;
	private ProductoPaginador productoPaginador;
	private String registros;
	private String numeros;
	
	private String jpql;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NegocioControlador() {
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
		case "publicar_paso_1": {
			if (sesion.getAttribute("usuario") != null) {
				if (sesion.getAttribute("pasoActual") == null) {
					sesion.setAttribute("pasoActual", "1");
				}
				
				if (sesion.getAttribute("pasoActual") == "1") {
					sesion.setAttribute("tiposNegocio", daoTipoNegocio.getAll());
					sesion.setAttribute("renderizarVista", "publicarPasoUno");
					response.sendRedirect(request.getContextPath() + "/negocio/publicar-paso-1");
				}
				
				if (sesion.getAttribute("pasoActual") == "2") {
					response.sendRedirect(request.getContextPath() + "/negocio/publicar-paso-2");
				}
				
				if (sesion.getAttribute("pasoActual") == "3") {
					response.sendRedirect(request.getContextPath() + "/negocio/publicar-paso-3");
				}
			}
			else {
				response.sendRedirect(request.getContextPath());
			}
			break;
		}
		case "publicar_paso_2": {
			if (sesion.getAttribute("usuario") != null) { 
				if (sesion.getAttribute("pasoActual") == null) {
					sesion.setAttribute("pasoActual", "1");
				}
				
				if (sesion.getAttribute("pasoActual") == "1") {
					response.sendRedirect(request.getContextPath() + "/negocio/publicar-paso-1");
				}
				
				if (sesion.getAttribute("pasoActual") == "2") {
					List<MarcaTipoNegocio> marcasTipoNegocio = (List<MarcaTipoNegocio>)daoMarcaTipoNegocio.findAll("tipoNegocioFK", Integer.toString(((Negocio)sesion.getAttribute("negocioListo")).getTipoNegocioFK()));
					
					boolean raiz = false;
					int paginaActual;
					try {
						paginaActual = Integer.parseInt(request.getParameter("pagina"));
					} catch (Exception ex) {
						paginaActual = 1;
						raiz = true;
					}
					
					marcaTipoNegocioPaginador = new MarcaTipoNegocioPaginador(paginaActual, 4, marcasTipoNegocio);
					
					registros = marcaTipoNegocioPaginador.generarRegistros("tabla", request.getContextPath(), "Negocio", "publicar_paso_2");
					numeros = marcaTipoNegocioPaginador.generarNumeros(request.getContextPath(), "Negocio", "publicar_paso_2");
					
					sesion.setAttribute("registros", registros);
					sesion.setAttribute("numeros", numeros);
					sesion.setAttribute("renderizarVista", "publicarPasoDos");
					
					if (raiz == true) {
						response.sendRedirect(request.getContextPath() + "/negocio/publicar-paso-2");
					}
					else {
						response.sendRedirect(request.getContextPath() + "/negocio/publicar-paso-2" + "?pagina=" + paginaActual);
					}
				}
				
				if (sesion.getAttribute("pasoActual") == "3") {
					response.sendRedirect(request.getContextPath() + "/negocio/publicar-paso-3");
				}
			}
			else {
				response.sendRedirect(request.getContextPath());
			}
			break;
		}
		case "publicar_paso_3": {
			if (sesion.getAttribute("usuario") != null) { 
				if (sesion.getAttribute("pasoActual") == null) {
					sesion.setAttribute("pasoActual", "1");
				}
				
				if (sesion.getAttribute("pasoActual") == "1") {
					response.sendRedirect(request.getContextPath() + "/negocio/publicar-paso-1");
				}
				
				if (sesion.getAttribute("pasoActual") == "2") {
					response.sendRedirect(request.getContextPath() + "/negocio/publicar-paso-2");
				}
				
				if (sesion.getAttribute("pasoActual") == "3") {
					List<Marca> marcas = (List<Marca>)sesion.getAttribute("marcasListas");
					
					List<MarcaTipoProducto> marcasTipoProducto = new ArrayList<MarcaTipoProducto>();
					for (Marca m : marcas) {
						List<MarcaTipoProducto> marcasTipoProductoAuxiliar = daoMarcaTipoProducto.findAll("marcaFK", Integer.toString(m.getId()));
						
						for (MarcaTipoProducto mtp : marcasTipoProductoAuxiliar) {
							marcasTipoProducto.add(mtp);
						}
					}
					
					ArrayList<Integer> idTiposProductoDuplicados = new ArrayList<Integer>();
					for (MarcaTipoProducto mtp : marcasTipoProducto) {
						idTiposProductoDuplicados.add(mtp.getTipoProductoFK());
					}
					
					List<Integer> idTiposProductoNoDuplicados = removerDuplicadosUtil.removerDuplicadosEnListas(idTiposProductoDuplicados);
					
					List<TipoProducto> tiposProducto = new ArrayList<TipoProducto>();
					for (int idTipoProducto : idTiposProductoNoDuplicados) {
						tiposProducto.add(daoTipoProducto.find(idTipoProducto));
					}
					
					boolean raiz = false;
					int paginaActual;
					try {
						paginaActual = Integer.parseInt(request.getParameter("pagina"));
					} catch (Exception ex) {
						paginaActual = 1;
						raiz = true;
					}
					
					tipoProductoPaginador = new TipoProductoPaginador(paginaActual, 4, tiposProducto);
					
					registros = tipoProductoPaginador.generarRegistros("tabla", request.getContextPath(), "Negocio", "publicar_paso_3");
					numeros = tipoProductoPaginador.generarNumeros(request.getContextPath(), "Negocio", "publicar_paso_3");
					
					sesion.setAttribute("registros", registros);
					sesion.setAttribute("numeros", numeros);
					sesion.setAttribute("renderizarVista", "publicarPasoTres");
					
					if (raiz == true) {
						response.sendRedirect(request.getContextPath() + "/negocio/publicar-paso-3");
					}
					else {
						response.sendRedirect(request.getContextPath() + "/negocio/publicar-paso-3" + "?pagina=" + paginaActual);
					}
				}
			}
			else {
				response.sendRedirect(request.getContextPath());
			}
			break;
		}
		case "mis_negocios": {
			if (sesion.getAttribute("usuario") != null) {
				List<Negocio> negocios = daoNegocio.findAll("usuarioFK", ((Usuario)sesion.getAttribute("usuario")).getCorreo());
				
				boolean raiz = false;
				int paginaActual;
				try {
					paginaActual = Integer.parseInt(request.getParameter("pagina"));
				} catch (Exception ex) {
					paginaActual = 1;
					raiz = true;
				}
				
				negocioPaginador = new NegocioPaginador(paginaActual, 15, negocios);
				
				registros = negocioPaginador.generarRegistros("tarjeta", request.getContextPath(), "Negocio", "mis_negocios");
				numeros = negocioPaginador.generarNumeros(request.getContextPath(), "Negocio", "mis_negocios");
				
				sesion.setAttribute("registros", registros);
				sesion.setAttribute("numeros", numeros);
				sesion.setAttribute("renderizarVista", "misNegocios");
				
				if (raiz == true) {
					response.sendRedirect(request.getContextPath() + "/negocio/mis-negocios");
				}
				else {
					response.sendRedirect(request.getContextPath() + "/negocio/mis-negocios" + "?pagina=" + paginaActual);
				}
			}
			else {
				response.sendRedirect(request.getContextPath());
			}
			break;
		}
		case "ver_negocios": {
			List<Negocio> negocios = daoNegocio.getAll();
			
			boolean raiz = false;
			int paginaActual;
			try {
				paginaActual = Integer.parseInt(request.getParameter("pagina"));
			} catch (Exception ex) {
				paginaActual = 1;
				raiz = true;
			}
			
			negocioPaginador = new NegocioPaginador(paginaActual, 15, negocios);
			
			registros = negocioPaginador.generarRegistros("tarjeta", request.getContextPath(), "ver_negocios", "Negocio");
			numeros = negocioPaginador.generarNumeros(request.getContextPath(), "Negocio", "ver_negocios");
			
			sesion.setAttribute("registros", registros);
			sesion.setAttribute("numeros", numeros);
			sesion.setAttribute("renderizarVista", "verNegocios");
			
			if (raiz == true) {
				response.sendRedirect(request.getContextPath() + "/negocio/ver-negocios");
			}
			else {
				response.sendRedirect(request.getContextPath() + "/negocio/ver-negocios" + "?pagina=" + paginaActual);
			}
			break;
		}
		case "administrar": {
			if (sesion.getAttribute("usuario") != null) {
				if ((((Usuario)(sesion.getAttribute("usuario"))).getTipo()).equals("Administrador")) {
					response.sendRedirect(request.getContextPath());
				}
				else if ((((Usuario)(sesion.getAttribute("usuario"))).getTipo()).equals("Cliente")) {
					String seleccion = request.getParameter("seleccion");
					if (seleccion == null) {
						seleccion = "1";
					}
					if (seleccion.equals("null")) {
						seleccion = "1";
					}
					
					Negocio negocioRecibido = daoNegocio.find(request.getParameter("negocio"));
					
					if (negocioRecibido != null && (seleccion.equals("1") || seleccion.equals("2"))) {
						if (seleccion.equals("1")) {
							//Obtención de datos en base a selección.
							sesion.setAttribute("negocio", negocioRecibido);
							sesion.setAttribute("renderizarVista", "administrar");
							response.sendRedirect(request.getContextPath() + "/negocio/administrar" + "?negocio=" + negocioRecibido.getRut() + "&seleccion=" + seleccion);
						}
						if (seleccion.equals("2")) {
							//Obtención de datos en base a selección.
							List<MarcaNegocio> marcasNegocio = daoMarcaNegocio.findAll("negocioFK", negocioRecibido.getRut());
							
							List<Producto> productos = new ArrayList<Producto>();
							for (MarcaNegocio mn : marcasNegocio) {
								List<Producto> productosDeLaMarca = daoProducto.findAll("marcaFK", Integer.toString(mn.getMarcaFK()));
								
								for (Producto p : productosDeLaMarca) {
									productos.add(p);
								}
							}
							
							List<ProductoNegocio> productosNegocio = daoProductoNegocio.findAll("negocioFK", negocioRecibido.getRut());
							
							boolean raiz = false;
							int paginaActual;
							try {
								paginaActual = Integer.parseInt(request.getParameter("pagina"));
							} catch (Exception ex) {
								paginaActual = 1;
								raiz = true;
							}
							
							productoPaginador = new ProductoPaginador(paginaActual, 4, productos);
							
							registros = productoPaginador.generarRegistros("tabla", request.getContextPath(), "Negocio", "administrar", productosNegocio);
							numeros = productoPaginador.generarNumeros(request.getContextPath(), "1", "Negocio", "administrar", negocioRecibido.getRut(), seleccion);
							
							sesion.setAttribute("registros", registros);
							sesion.setAttribute("numeros", numeros);
							sesion.setAttribute("negocio", negocioRecibido);
							sesion.setAttribute("renderizarVista", "administrar");
							
							if (raiz == true) {
								response.sendRedirect(request.getContextPath() + "/negocio/administrar" + "?negocio=" + negocioRecibido.getRut() + "&seleccion=" + seleccion);
							}
							else {
								response.sendRedirect(request.getContextPath() + "/negocio/administrar" + "?negocio=" + negocioRecibido.getRut() + "&seleccion=" + seleccion + "&pagina=" + paginaActual);
							}	
						}	
					}
					else {
						response.sendRedirect(request.getContextPath());
					}	
				}
			}
			else {
				response.sendRedirect(request.getContextPath());
			}
			break;
		}
		case "detalle": {
			String rutNegocio = request.getParameter("negocio");
			
			if (rutNegocio != null) {
				Negocio negocio = daoNegocio.find(rutNegocio);
				
				if (negocio != null) {
					List<ProductoNegocio> productosNegocio = daoProductoNegocio.findAll("negocioFK", rutNegocio);
					
					boolean raiz = false;
					int paginaActual;
					try {
						paginaActual = Integer.parseInt(request.getParameter("pagina"));
					} catch (Exception ex) {
						paginaActual = 1;
						raiz = true;
					}
					
					List<Producto> productos = new ArrayList<Producto>();
					for (ProductoNegocio pn : productosNegocio) {
						productos.add(daoProducto.find(pn.getProductoFK()));
					}
					
					productoPaginador = new ProductoPaginador(paginaActual, 6, productos);
					
					registros = productoPaginador.generarRegistros("tarjeta", request.getContextPath(), "Negocio", "detalle", productosNegocio);
					numeros = productoPaginador.generarNumeros(request.getContextPath(), "2", "Negocio", "detalle", rutNegocio, null);
					
					sesion.setAttribute("registros", registros);
					sesion.setAttribute("numeros", numeros);
					
					sesion.setAttribute("renderizarVista", "detalle");
					
					if (raiz == true) {
						response.sendRedirect(request.getContextPath() + "/negocio/detalle" + "?negocio=" + rutNegocio);
					}
					else {
						response.sendRedirect(request.getContextPath() + "/negocio/detalle" + "?negocio=" + rutNegocio + "&pagina=" + paginaActual);
					}	
				}
				else {
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
		case "1": { //Anular publicación.
			sesion.removeAttribute("pasoActual");
			response.sendRedirect(request.getContextPath());
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
		case "1": { //Publicar negocio (Paso 1).
			if (sesion.getAttribute("usuario") != null) {
				if ((((Usuario)(sesion.getAttribute("usuario"))).getTipo()).equals("Administrador")) {
					response.sendRedirect(request.getContextPath());
				}
				else if ((((Usuario)(sesion.getAttribute("usuario"))).getTipo()).equals("Cliente")) {
					this.rut = request.getParameter("rut");
					this.nombre = request.getParameter("nombre");
					this.ciudad = request.getParameter("ciudad");
					this.comuna = request.getParameter("comuna");
					this.calle = request.getParameter("calle");
					String[] diasAtencionSeleccionados = request.getParameterValues("diasAtencion");
					String[] metodosPagoSeleccionados = request.getParameterValues("metodosPago");
					this.estado = "Aprobado";
					this.logo = "Default.png";
					
					try {
						this.tipoNegocioFK = Integer.parseInt(request.getParameter("tipoNegocio"));
					} catch (Exception ex) {
						this.tipoNegocioFK = -1;
					}
					
					this.usuarioFK = ((Usuario)(sesion.getAttribute("usuario"))).getCorreo();

					if (this.rut != null && this.nombre != null && this.ciudad != null && this.comuna != null && this.calle != null && diasAtencionSeleccionados != null && metodosPagoSeleccionados != null && tipoNegocioFK != -1) {
						this.n.setRut(rut);
						this.n.setNombre(nombre);
						this.n.setCiudad(ciudad);
						this.n.setComuna(comuna);
						this.n.setCalle(calle);
						
						for (int i = 0; i < diasAtencionSeleccionados.length; i++) {
							if (i == (diasAtencionSeleccionados.length - 1)) {
								this.diasAtencion+=diasAtencionSeleccionados[i];
							}
							else {
								this.diasAtencion+=diasAtencionSeleccionados[i] + ";";
							}		
						}
						this.n.setDiasAtencion(diasAtencion);
						
						for (int i = 0; i < metodosPagoSeleccionados.length; i++) {
							if (i == (metodosPagoSeleccionados.length - 1)) {
								this.metodosPago+=metodosPagoSeleccionados[i];
							}
							else {
								this.metodosPago+=metodosPagoSeleccionados[i] + ";";
							}		
						}
						this.n.setMetodosPago(metodosPago);
						
						this.n.setEstado(estado);
						this.n.setLogo(logo);
						this.n.setTipoNegocioFK(tipoNegocioFK);
						this.n.setUsuarioFK(usuarioFK);
						
						if (daoNegocio.find(this.rut) == null) {
							sesion.setAttribute("negocioListo", this.n);
							sesion.setAttribute("pasoActual", "2");
							response.sendRedirect(request.getContextPath() + "/negocio/publicar-paso-2");
						}
						else {
							sesion.setAttribute("publicarNegocio", "Fallido");
							response.sendRedirect(request.getContextPath() + "/negocio/publicar-paso-1");
						}
					}
					else {
						response.sendRedirect(request.getContextPath());
					}
				}
			}
			else {
				response.sendRedirect(request.getContextPath());
			}
			break;
		}
		case "2": { //Publicar negocio (Paso 2).
			if (sesion.getAttribute("usuario") != null) {
				if ((((Usuario)(sesion.getAttribute("usuario"))).getTipo()).equals("Administrador")) {
					response.sendRedirect(request.getContextPath());
				}
				else if ((((Usuario)(sesion.getAttribute("usuario"))).getTipo()).equals("Cliente")) {
					String[] idMarcasSeleccionadas = request.getParameterValues("marcas");
					
					if (idMarcasSeleccionadas != null) {
						List<Marca> marcas = new ArrayList<Marca>();
						for (int i = 0; i < idMarcasSeleccionadas.length; i++) {
							marcas.add(daoMarca.find(Integer.parseInt(idMarcasSeleccionadas[i])));
						}
						
						sesion.setAttribute("marcasListas", marcas);
						sesion.setAttribute("pasoActual", "3");
						response.sendRedirect(request.getContextPath() + "/negocio/publicar-paso-3");
					}
					else {
						sesion.setAttribute("publicarNegocio", "Fallido");
						response.sendRedirect(request.getContextPath() + "/negocio/publicar-paso-2");
					}
				}
			}
			else {
				response.sendRedirect(request.getContextPath());
			}
			break;
		}
		case "3": { //Publicar negocio (Paso 3).
			if (sesion.getAttribute("usuario") != null) {
				if ((((Usuario)(sesion.getAttribute("usuario"))).getTipo()).equals("Administrador")) {
					response.sendRedirect(request.getContextPath());
				}
				else if ((((Usuario)(sesion.getAttribute("usuario"))).getTipo()).equals("Cliente")) {
					String[] idTiposProductoSeleccionados = request.getParameterValues("tiposProducto");
					
					Negocio negocioListo = (Negocio)sesion.getAttribute("negocioListo");
					List<Marca> marcasListas = (List<Marca>)sesion.getAttribute("marcasListas");
					
					if (idTiposProductoSeleccionados != null) {
						daoNegocio.save(negocioListo);

						for (Marca m : marcasListas) {
							MarcaNegocio mn = new MarcaNegocio();
							mn.setMarcaFK(m.getId());
							mn.setNegocioFK(negocioListo.getRut());
							
							daoMarcaNegocio.save(mn);
						}
						
						for (int i = 0; i < idTiposProductoSeleccionados.length; i++) {
							TipoProductoNegocio ntp = new TipoProductoNegocio();
							ntp.setNegocioFK(negocioListo.getRut());
							ntp.setTipoProductoFK(Integer.parseInt(idTiposProductoSeleccionados[i]));
							
							daoNegocioTipoProducto.save(ntp);
						}
						
						sesion.removeAttribute("negocioListo");
						sesion.removeAttribute("marcasListas");
						sesion.removeAttribute("pasoActual");
						
						response.sendRedirect(request.getContextPath() + "/negocio/mis-negocios");
					}
					else {
						sesion.setAttribute("publicarNegocio", "Fallido");
						response.sendRedirect(request.getContextPath() + "/negocio/publicar-paso-3");
					}
				}
			}
			else {
				response.sendRedirect(request.getContextPath());
			}
			break;
		}
		case "4": { //Modificar negocio.
			String[] diasAtencionSeleccionados = {};
			String[] metodosPagoSeleccionados = {};
			if (sesion.getAttribute("usuario") != null) {
				if ((((Usuario)(sesion.getAttribute("usuario"))).getTipo()).equals("Administrador")) {
					response.sendRedirect(request.getContextPath());
				}
				else if ((((Usuario)(sesion.getAttribute("usuario"))).getTipo()).equals("Cliente")) {
					this.rut = request.getParameter("rut");
					this.nombre = request.getParameter("nombre");
					this.ciudad = request.getParameter("ciudad");
					this.comuna = request.getParameter("comuna");
					this.calle = request.getParameter("calle");
					diasAtencionSeleccionados = request.getParameterValues("diasAtencion");
					metodosPagoSeleccionados = request.getParameterValues("metodosPago");
					archivo = request.getPart("logo");
					
					if (archivo.getSubmittedFileName().equals("") == false && archivoUtil.validarFormato(archivo.getSubmittedFileName(), formatosSoportados) == true) {			
						Negocio negocio = daoNegocio.find(this.rut);
						if (negocio != null && negocio.getLogo().equals("Default.png")) {
							//
						}
						else {
							File archivoAntiguo = new File(rutaArchivos + negocio.getLogo());
							archivoAntiguo.delete();
						}
						
						this.logo = archivoUtil.guardarArchivo(archivo, rutaArchivoDestino);
						
						this.n.setNombre(nombre);
						this.n.setCiudad(ciudad);
						this.n.setComuna(comuna);
						this.n.setCalle(calle);
						
						for (int i = 0; i < diasAtencionSeleccionados.length; i++) {
							if (i == (diasAtencionSeleccionados.length - 1)) {
								this.diasAtencion+=diasAtencionSeleccionados[i];
							}
							else {
								this.diasAtencion+=diasAtencionSeleccionados[i] + ";";
							}		
						}
						this.n.setDiasAtencion(diasAtencion);
						
						for (int i = 0; i < metodosPagoSeleccionados.length; i++) {
							if (i == (metodosPagoSeleccionados.length - 1)) {
								this.metodosPago+=metodosPagoSeleccionados[i];
							}
							else {
								this.metodosPago+=metodosPagoSeleccionados[i] + ";";
							}		
						}
						this.n.setMetodosPago(metodosPago);
						this.n.setLogo(logo);
						
						if (daoNegocio.updateOne(this.rut, "nombre", this.nombre) 
								&& daoNegocio.updateOne(this.rut, "ciudad", this.ciudad) 
									&& daoNegocio.updateOne(this.rut, "comuna", this.comuna) 
										&& daoNegocio.updateOne(this.rut, "calle", this.calle)
											&& daoNegocio.updateOne(this.rut, "diasAtencion", this.diasAtencion)
												&& daoNegocio.updateOne(this.rut, "metodosPago", this.metodosPago)
													&& daoNegocio.updateOne(this.rut, "logo", this.logo)) {
							sesion.setAttribute("modificarNegocio", "Exitoso");
						}
						else {
							sesion.setAttribute("modificarNegocio", "Fallido");
						}
					}
					else if (archivo.getSubmittedFileName().equals("") == true) {
						this.rut = request.getParameter("rut");
						this.nombre = request.getParameter("nombre");
						this.ciudad = request.getParameter("ciudad");
						this.comuna = request.getParameter("comuna");
						this.calle = request.getParameter("calle");
						diasAtencionSeleccionados = request.getParameterValues("diasAtencion");
						metodosPagoSeleccionados = request.getParameterValues("metodosPago");

						this.n.setNombre(nombre);
						this.n.setCiudad(ciudad);
						this.n.setComuna(comuna);
						this.n.setCalle(calle);
							
						for (int i = 0; i < diasAtencionSeleccionados.length; i++) {
							if (i == (diasAtencionSeleccionados.length - 1)) {
								this.diasAtencion+=diasAtencionSeleccionados[i];
							}
							else {
								this.diasAtencion+=diasAtencionSeleccionados[i] + ";";
							}		
						}
						this.n.setDiasAtencion(diasAtencion);
						
						for (int i = 0; i < metodosPagoSeleccionados.length; i++) {
							if (i == (metodosPagoSeleccionados.length - 1)) {
								this.metodosPago+=metodosPagoSeleccionados[i];
							}
							else {
								this.metodosPago+=metodosPagoSeleccionados[i] + ";";
							}		
						}
						this.n.setMetodosPago(metodosPago);
						this.n.setLogo(logo);
						
						if (daoNegocio.updateOne(this.rut, "nombre", this.nombre) 
								&& daoNegocio.updateOne(this.rut, "ciudad", this.ciudad) 
									&& daoNegocio.updateOne(this.rut, "comuna", this.comuna) 
										&& daoNegocio.updateOne(this.rut, "calle", this.calle)
											&& daoNegocio.updateOne(this.rut, "diasAtencion", this.diasAtencion)
												&& daoNegocio.updateOne(this.rut, "metodosPago", this.metodosPago)) {
							sesion.setAttribute("modificarNegocio", "Exitoso");
						}
						else {
							sesion.setAttribute("modificarNegocio", "Fallido");
						}
					}
					else {
						sesion.setAttribute("modificarNegocio", "Fallido");
					}
				}
			}
			else {
				response.sendRedirect(request.getContextPath());
			}
			response.sendRedirect(request.getContextPath() + "/negocio/administrar" + "?negocio=" + this.rut + "&seleccion=1");
			break;
		}
		default: 
			break;
		}
	}

}
