package cl.pymap.pymapejb.fk;

import cl.pymap.pymapejb.bean.MarcaDAO;
import cl.pymap.pymapejb.bean.MarcaDAOLocal;
import cl.pymap.pymapejb.bean.NegocioDAO;
import cl.pymap.pymapejb.bean.NegocioDAOLocal;
import cl.pymap.pymapejb.model.Marca;
import cl.pymap.pymapejb.model.MarcaNegocio;
import cl.pymap.pymapejb.model.Negocio;

public class MarcaNegocioFK {
	private MarcaDAOLocal daoMarca = new MarcaDAO();
	private NegocioDAOLocal daoNegocio = new NegocioDAO();
	private Marca m = new Marca();
	private Negocio n = new Negocio();
	
	public int getMarcaId(MarcaNegocio mn) {
		this.m = daoMarca.find(mn.getMarcaFK());
		return this.m.getId();
	}
	
	public String getMarcaNombre(MarcaNegocio mn) {
		this.m = daoMarca.find(mn.getMarcaFK());
		return this.m.getNombre();
	}
	
	public String getMarcaLogo(MarcaNegocio mn) {
		this.m = daoMarca.find(mn.getMarcaFK());
		return this.m.getLogo();
	}
	
	public String getNegocioRut(MarcaNegocio mn) {
		this.n = daoNegocio.find(mn.getNegocioFK());
		return this.n.getRut();
	}
	
	public String getNegocioNombre(MarcaNegocio mn) {
		this.n = daoNegocio.find(mn.getNegocioFK());
		return this.n.getNombre();
	}
	
	public String getNegocioCiudad(MarcaNegocio mn) {
		this.n = daoNegocio.find(mn.getNegocioFK());
		return this.n.getCiudad();
	}
	
	public String getNegocioComuna(MarcaNegocio mn) {
		this.n = daoNegocio.find(mn.getNegocioFK());
		return this.n.getComuna();
	}
	
	public String getNegocioCalle(MarcaNegocio mn) {
		this.n = daoNegocio.find(mn.getNegocioFK());
		return this.n.getCalle();
	}
	
	public String getNegocioDiasAtencion(MarcaNegocio mn) {
		this.n = daoNegocio.find(mn.getNegocioFK());
		return this.n.getDiasAtencion();
	}
	
	public String getNegocioMetodosPago(MarcaNegocio mn) {
		this.n = daoNegocio.find(mn.getNegocioFK());
		return this.n.getMetodosPago();
	}
	
	public String getNegocioEstado(MarcaNegocio mn) {
		this.n = daoNegocio.find(mn.getNegocioFK());
		return this.n.getEstado();
	}
	
	public String getNegocioLogo(MarcaNegocio mn) {
		this.n = daoNegocio.find(mn.getNegocioFK());
		return this.n.getLogo();
	}
	
	public int getNegocioTipoNegocioFK(MarcaNegocio mn) {
		this.n = daoNegocio.find(mn.getNegocioFK());
		return this.n.getTipoNegocioFK();
	}
	
	public String getNegocioUsuarioFK(MarcaNegocio mn) {
		this.n = daoNegocio.find(mn.getNegocioFK());
		return this.n.getUsuarioFK();
	}
}
