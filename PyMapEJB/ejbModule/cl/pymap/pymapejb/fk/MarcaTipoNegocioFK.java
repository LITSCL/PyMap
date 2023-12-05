package cl.pymap.pymapejb.fk;

import cl.pymap.pymapejb.bean.MarcaDAO;
import cl.pymap.pymapejb.bean.MarcaDAOLocal;
import cl.pymap.pymapejb.bean.TipoNegocioDAO;
import cl.pymap.pymapejb.bean.TipoNegocioDAOLocal;
import cl.pymap.pymapejb.model.Marca;
import cl.pymap.pymapejb.model.MarcaTipoNegocio;
import cl.pymap.pymapejb.model.TipoNegocio;

public class MarcaTipoNegocioFK {
	private MarcaDAOLocal daoMarca = new MarcaDAO();
	private TipoNegocioDAOLocal daoTipoNegocio = new TipoNegocioDAO();
	private Marca m = new Marca();
	private TipoNegocio tn = new TipoNegocio();
	
	public int getMarcaId(MarcaTipoNegocio mtn) {
		this.m = daoMarca.find(mtn.getMarcaFK());
		return this.m.getId();
	}
	
	public String getMarcaNombre(MarcaTipoNegocio mtn) {
		this.m = daoMarca.find(mtn.getMarcaFK());
		return this.m.getNombre();
	}
	
	public String getMarcaLogo(MarcaTipoNegocio mtn) {
		this.m = daoMarca.find(mtn.getMarcaFK());
		return this.m.getLogo();
	}
	
	public int getTipoNegocioId(MarcaTipoNegocio mtn) {
		this.tn = daoTipoNegocio.find(mtn.getTipoNegocioFK());
		return this.m.getId();
	}
	
	public String getTipoNegocioNombre(MarcaTipoNegocio mtn) {
		this.tn = daoTipoNegocio.find(mtn.getTipoNegocioFK());
		return this.tn.getNombre();
	}
}
