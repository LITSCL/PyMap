package cl.pymap.pymapejb.fk;

import cl.pymap.pymapejb.bean.NegocioDAO;
import cl.pymap.pymapejb.bean.NegocioDAOLocal;
import cl.pymap.pymapejb.bean.TipoProductoDAO;
import cl.pymap.pymapejb.bean.TipoProductoDAOLocal;
import cl.pymap.pymapejb.model.Negocio;
import cl.pymap.pymapejb.model.TipoProductoNegocio;
import cl.pymap.pymapejb.model.TipoProducto;

public class NegocioTipoProductoFK {
	private NegocioDAOLocal daoNegocio = new NegocioDAO();
	private TipoProductoDAOLocal daoTipoProducto = new TipoProductoDAO();
	private Negocio n = new Negocio();
	private TipoProducto tp = new TipoProducto();
	
	public int getTipoProductoId(TipoProductoNegocio ntp) {
		this.tp = daoTipoProducto.find(ntp.getTipoProductoFK());
		return this.tp.getId();
	}
	
	public String getTipoProductoNombre(TipoProductoNegocio ntp) {
		this.tp = daoTipoProducto.find(ntp.getTipoProductoFK());
		return this.tp.getNombre();
	}
	
	public int getTipoProductoCategoriaFK(TipoProductoNegocio ntp) {
		this.tp = daoTipoProducto.find(ntp.getTipoProductoFK());
		return this.tp.getCategoriaFK();
	}
	
	public String getNegocioRut(TipoProductoNegocio ntp) {
		this.n = daoNegocio.find(ntp.getNegocioFK());
		return this.n.getRut();
	}
	
	public String getNegocioNombre(TipoProductoNegocio ntp) {
		this.n = daoNegocio.find(ntp.getNegocioFK());
		return this.n.getNombre();
	}
	
	public String getNegocioCiudad(TipoProductoNegocio ntp) {
		this.n = daoNegocio.find(ntp.getNegocioFK());
		return this.n.getCiudad();
	}
	
	public String getNegocioComuna(TipoProductoNegocio ntp) {
		this.n = daoNegocio.find(ntp.getNegocioFK());
		return this.n.getComuna();
	}
	
	public String getNegocioCalle(TipoProductoNegocio ntp) {
		this.n = daoNegocio.find(ntp.getNegocioFK());
		return this.n.getCalle();
	}
	
	public String getNegocioDiasAtencion(TipoProductoNegocio ntp) {
		this.n = daoNegocio.find(ntp.getNegocioFK());
		return this.n.getDiasAtencion();
	}
	
	public String getNegocioMetodosPago(TipoProductoNegocio ntp) {
		this.n = daoNegocio.find(ntp.getNegocioFK());
		return this.n.getMetodosPago();
	}
	
	public String getNegocioEstado(TipoProductoNegocio ntp) {
		this.n = daoNegocio.find(ntp.getNegocioFK());
		return this.n.getEstado();
	}
	
	public String getNegocioLogo(TipoProductoNegocio ntp) {
		this.n = daoNegocio.find(ntp.getNegocioFK());
		return this.n.getLogo();
	}
	
	public int getNegocioTipoNegocioFK(TipoProductoNegocio ntp) {
		this.n = daoNegocio.find(ntp.getNegocioFK());
		return this.n.getTipoNegocioFK();
	}
	
	public String getNegocioUsuarioFK(TipoProductoNegocio ntp) {
		this.n = daoNegocio.find(ntp.getNegocioFK());
		return this.n.getUsuarioFK();
	}
}
