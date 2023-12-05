package cl.pymap.pymapejb.fk;

import cl.pymap.pymapejb.bean.NegocioDAO;
import cl.pymap.pymapejb.bean.NegocioDAOLocal;
import cl.pymap.pymapejb.bean.UsuarioDAO;
import cl.pymap.pymapejb.bean.UsuarioDAOLocal;
import cl.pymap.pymapejb.model.Compra;
import cl.pymap.pymapejb.model.Negocio;
import cl.pymap.pymapejb.model.Usuario;

public class CompraFK {
	private UsuarioDAOLocal daoUsuario = new UsuarioDAO();
	private NegocioDAOLocal daoNegocio = new NegocioDAO();
	private Usuario u = new Usuario();
	private Negocio n = new Negocio();
	
	public String getUsuarioCorreo(Compra c) {
		this.u = daoUsuario.find(c.getUsuarioFK());
		return this.u.getCorreo();
	}
	
	public String getUsuarioClave(Compra c) {
		this.u = daoUsuario.find(c.getUsuarioFK());
		return this.u.getClave();
	}
	
	public String getUsuarioTipo(Compra c) {
		this.u = daoUsuario.find(c.getUsuarioFK());
		return this.u.getTipo();
	}
	
	public String getUsuarioPrimerNombre(Compra c) {
		this.u = daoUsuario.find(c.getUsuarioFK());
		return this.u.getPrimerNombre();
	}
	
	public String getUsuarioSegundoNombre(Compra c) {
		this.u = daoUsuario.find(c.getUsuarioFK());
		return this.u.getSegundoNombre();
	}
	
	public String getUsuarioApellidoPaterno(Compra c) {
		this.u = daoUsuario.find(c.getUsuarioFK());
		return this.u.getApellidoPaterno();
	}
	
	public String getUsuarioApellidoMaterno(Compra c) {
		this.u = daoUsuario.find(c.getUsuarioFK());
		return this.u.getApellidoMaterno();
	}
	
	public String getUsuarioImagen(Compra c) {
		this.u = daoUsuario.find(c.getUsuarioFK());
		return this.u.getImagen();
	}
	
	public String getNegocioRut(Compra c) {
		this.n = daoNegocio.find(c.getNegocioFK());
		return this.n.getRut();
	}
	
	public String getNegocioNombre(Compra c) {
		this.n = daoNegocio.find(c.getNegocioFK());
		return this.n.getNombre();
	}
	
	public String getNegocioCiudad(Compra c) {
		this.n = daoNegocio.find(c.getNegocioFK());
		return this.n.getCiudad();
	}
	
	public String getNegocioComuna(Compra c) {
		this.n = daoNegocio.find(c.getNegocioFK());
		return this.n.getComuna();
	}
	
	public String getNegocioCalle(Compra c) {
		this.n = daoNegocio.find(c.getNegocioFK());
		return this.n.getCalle();
	}
	
	public int getNegocioTipoNegocio(Compra c) {
		this.n = daoNegocio.find(c.getNegocioFK());
		return this.n.getTipoNegocioFK();
	}
	
	public String getNegocioDiasAtencion(Compra c) {
		this.n = daoNegocio.find(c.getNegocioFK());
		return this.n.getDiasAtencion();
	}
	
	public String getNegocioMetodosPago(Compra c) {
		this.n = daoNegocio.find(c.getNegocioFK());
		return this.n.getMetodosPago();
	}
	
	public String getNegocioEstado(Compra c) {
		this.n = daoNegocio.find(c.getNegocioFK());
		return this.n.getEstado();
	}
	
	public String getNegocioUsuarioFK(Compra c) {
		this.n = daoNegocio.find(c.getNegocioFK());
		return this.n.getUsuarioFK();
	}
}
