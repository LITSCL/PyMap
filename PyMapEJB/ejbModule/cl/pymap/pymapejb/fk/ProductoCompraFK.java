package cl.pymap.pymapejb.fk;

import cl.pymap.pymapejb.bean.CompraDAO;
import cl.pymap.pymapejb.bean.CompraDAOLocal;
import cl.pymap.pymapejb.bean.ProductoDAO;
import cl.pymap.pymapejb.bean.ProductoDAOLocal;
import cl.pymap.pymapejb.model.Compra;
import cl.pymap.pymapejb.model.Producto;
import cl.pymap.pymapejb.model.ProductoCompra;

public class ProductoCompraFK {
	private CompraDAOLocal daoCompra = new CompraDAO();
	private ProductoDAOLocal daoProducto = new ProductoDAO();
	private Compra c = new Compra();
	private Producto p = new Producto();
	
	public int getCompraId(ProductoCompra pc) {
		this.c = daoCompra.find(pc.getCompraFK());
		return this.c.getId();
	}
	
	public double getCompraCoste(ProductoCompra pc) {
		this.c = daoCompra.find(pc.getCompraFK());
		return this.c.getCoste();
	}
	
	public String getCompraEstado(ProductoCompra pc) {
		this.c = daoCompra.find(pc.getCompraFK());
		return this.c.getEstado();
	}
	
	public String getCompraFecha(ProductoCompra pc) {
		this.c = daoCompra.find(pc.getCompraFK());
		return this.c.getFecha();
	}
	
	public String getCompraHora(ProductoCompra pc) {
		this.c = daoCompra.find(pc.getCompraFK());
		return this.c.getHora();
	}
	
	public String getCompraUsuarioFK(ProductoCompra pc) {
		this.c = daoCompra.find(pc.getCompraFK());
		return this.c.getUsuarioFK();
	}
	
	public String getCompraNegocioFK(ProductoCompra pc) {
		this.c = daoCompra.find(pc.getCompraFK());
		return this.c.getNegocioFK();
	}
	
	public int getProductoId(ProductoCompra pc) {
		this.p = daoProducto.find(pc.getProductoFK());
		return this.p.getId();
	}
	
	public String getProductoNombre(ProductoCompra pc) {
		this.p = daoProducto.find(pc.getProductoFK());
		return this.p.getNombre();
	}
	
	public String getProductoDescripcion(ProductoCompra pc) {
		this.p = daoProducto.find(pc.getProductoFK());
		return this.p.getDescripcion();
	}
	
	public double getProductoPrecio(ProductoCompra pc) {
		this.p = daoProducto.find(pc.getProductoFK());
		return this.p.getPrecio();
	}
	
	public String getProductoImagen(ProductoCompra pc) {
		this.p = daoProducto.find(pc.getProductoFK());
		return this.p.getImagen();
	}
	
	public int getProductoMarcaFK(ProductoCompra pc) {
		this.p = daoProducto.find(pc.getProductoFK());
		return this.p.getMarcaFK();
	}
	
	public int getProductoCategoriaFK(ProductoCompra pc) {
		this.p = daoProducto.find(pc.getProductoFK());
		return this.p.getCategoriaFK();
	}
	
	public int getProductoTipoProductoFK(ProductoCompra pc) {
		this.p = daoProducto.find(pc.getProductoFK());
		return this.p.getTipoProductoFK();
	}
}
