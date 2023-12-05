package cl.pymap.pymapejb.fk;

import cl.pymap.pymapejb.bean.CategoriaDAO;
import cl.pymap.pymapejb.bean.CategoriaDAOLocal;
import cl.pymap.pymapejb.model.Categoria;
import cl.pymap.pymapejb.model.TipoProducto;

public class TipoProductoFK {
	private CategoriaDAOLocal daoCategoria = new CategoriaDAO();
	private Categoria c = new Categoria();
	
	public int getCategoriaId(TipoProducto tp) {
		this.c = daoCategoria.find(tp.getCategoriaFK());
		return this.c.getId();
	}
	
	public String getCategoriaNombre(TipoProducto tp) {
		this.c = daoCategoria.find(tp.getCategoriaFK());
		return this.c.getNombre();
	}
}
