package cl.pymap.pymapejb.fk;

import cl.pymap.pymapejb.bean.MarcaDAO;
import cl.pymap.pymapejb.bean.MarcaDAOLocal;
import cl.pymap.pymapejb.bean.TipoProductoDAO;
import cl.pymap.pymapejb.bean.TipoProductoDAOLocal;
import cl.pymap.pymapejb.model.Marca;
import cl.pymap.pymapejb.model.MarcaTipoProducto;
import cl.pymap.pymapejb.model.TipoProducto;

public class MarcaTipoProductoFK {
	private MarcaDAOLocal daoMarca = new MarcaDAO();
	private TipoProductoDAOLocal daoTipoProducto = new TipoProductoDAO();
	private Marca m = new Marca();
	private TipoProducto tp = new TipoProducto();
	
	public int getMarcaId(MarcaTipoProducto mtp) {
		this.m = daoMarca.find(mtp.getMarcaFK());
		return this.m.getId();
	}
	
	public String getMarcaNombre(MarcaTipoProducto mtp) {
		this.m = daoMarca.find(mtp.getMarcaFK());
		return this.m.getNombre();
	}
	
	public String getMarcaLogo(MarcaTipoProducto mtp) {
		this.m = daoMarca.find(mtp.getMarcaFK());
		return this.m.getLogo();
	}
	
	public int getTipoProductoId(MarcaTipoProducto mtp) {
		this.tp = daoTipoProducto.find(mtp.getTipoProductoFK());
		return this.m.getId();
	}
	
	public String getTipoProductoNombre(MarcaTipoProducto mtp) {
		this.tp = daoTipoProducto.find(mtp.getTipoProductoFK());
		return this.tp.getNombre();
	}
	
	public int getTipoProductoCategoriaFK(MarcaTipoProducto mtp) {
		this.tp = daoTipoProducto.find(mtp.getTipoProductoFK());
		return this.tp.getCategoriaFK();
	}
}
