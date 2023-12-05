package cl.pymap.pymapejb.fk;

import cl.pymap.pymapejb.bean.TipoNegocioDAO;
import cl.pymap.pymapejb.bean.TipoNegocioDAOLocal;
import cl.pymap.pymapejb.bean.UsuarioDAO;
import cl.pymap.pymapejb.bean.UsuarioDAOLocal;
import cl.pymap.pymapejb.model.Negocio;
import cl.pymap.pymapejb.model.TipoNegocio;
import cl.pymap.pymapejb.model.Usuario;

public class NegocioFK {
	private UsuarioDAOLocal daoUsuario = new UsuarioDAO();
	private TipoNegocioDAOLocal daoTipoNegocio = new TipoNegocioDAO();
	private Usuario u = new Usuario();
	private TipoNegocio tp = new TipoNegocio();
	
	public int getTipoNegocioId(Negocio n) {
		this.tp = daoTipoNegocio.find(n.getTipoNegocioFK());
		return this.tp.getId();
	}
	
	public String getTipoNegocioNombre(Negocio n) {
		this.tp = daoTipoNegocio.find(n.getTipoNegocioFK());
		return this.tp.getNombre();
	}
	
	public String getUsuarioCorreo(Negocio n) {
		this.u = daoUsuario.find(n.getUsuarioFK());
		return this.u.getCorreo();
	}
	
	public String getUsuarioClave(Negocio n) {
		this.u = daoUsuario.find(n.getUsuarioFK());
		return this.u.getClave();
	}
	
	public String getUsuarioTipo(Negocio n) {
		this.u = daoUsuario.find(n.getUsuarioFK());
		return this.u.getTipo();
	}
	
	public String getUsuarioPrimerNombre(Negocio n) {
		this.u = daoUsuario.find(n.getUsuarioFK());
		return this.u.getPrimerNombre();
	}
	
	public String getUsuarioSegundoNombre(Negocio n) {
		this.u = daoUsuario.find(n.getUsuarioFK());
		return this.u.getSegundoNombre();
	}
	
	public String getUsuarioApellidoPaterno(Negocio n) {
		this.u = daoUsuario.find(n.getUsuarioFK());
		return this.u.getApellidoPaterno();
	}
	
	public String getUsuarioApellidoMaterno(Negocio n) {
		this.u = daoUsuario.find(n.getUsuarioFK());
		return this.u.getApellidoMaterno();
	}
	
	public String getUsuarioImagen(Negocio n) {
		this.u = daoUsuario.find(n.getUsuarioFK());
		return this.u.getImagen();
	}
}
