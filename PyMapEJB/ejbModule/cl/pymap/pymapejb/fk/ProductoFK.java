package cl.pymap.pymapejb.fk;

import cl.pymap.pymapejb.bean.CategoriaDAO;
import cl.pymap.pymapejb.bean.CategoriaDAOLocal;
import cl.pymap.pymapejb.bean.MarcaDAO;
import cl.pymap.pymapejb.bean.MarcaDAOLocal;
import cl.pymap.pymapejb.bean.TipoProductoDAO;
import cl.pymap.pymapejb.bean.TipoProductoDAOLocal;
import cl.pymap.pymapejb.model.Categoria;
import cl.pymap.pymapejb.model.Marca;
import cl.pymap.pymapejb.model.Producto;
import cl.pymap.pymapejb.model.TipoProducto;

public class ProductoFK {
	private MarcaDAOLocal daoMarca = new MarcaDAO();
	private CategoriaDAOLocal daoCategoria = new CategoriaDAO();
	private TipoProductoDAOLocal daoTipoProducto = new TipoProductoDAO();
	private Marca m = new Marca();
	private Categoria c = new Categoria();
	private TipoProducto t = new TipoProducto();
	
	public int getMarcaId(Producto p) {
		this.m = daoMarca.find(p.getMarcaFK());
		return this.m.getId();
	}
	
	public String getMarcaNombre(Producto p) {
		this.m = daoMarca.find(p.getMarcaFK());
		return this.m.getNombre();
	}
	
	public String getMarcaLogo(Producto p) {
		this.m = daoMarca.find(p.getMarcaFK());
		return this.m.getLogo();
	}
	
	public int getCategoriaId(Producto p) {
		this.c = daoCategoria.find(p.getCategoriaFK());
		return this.c.getId();
	}
	
	public String getCategoriaNombre(Producto p) {
		this.c = daoCategoria.find(p.getCategoriaFK());
		return this.c.getNombre();
	}
	
	public int getTipoProductoId(Producto p) {
		this.t = daoTipoProducto.find(p.getTipoProductoFK());
		return this.t.getId();
	}
	
	public String getTipoProductoNombre(Producto p) {
		this.t = daoTipoProducto.find(p.getTipoProductoFK());
		return this.t.getNombre();
	}
	
	public int getTipoProductoCategoriaFK(Producto p) {
		this.t = daoTipoProducto.find(p.getTipoProductoFK());
		return this.t.getCategoriaFK();
	}
}
