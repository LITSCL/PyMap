package cl.pymap.pymapejb.fk;

import cl.pymap.pymapejb.bean.NegocioDAO;
import cl.pymap.pymapejb.bean.NegocioDAOLocal;
import cl.pymap.pymapejb.bean.ProductoDAO;
import cl.pymap.pymapejb.bean.ProductoDAOLocal;
import cl.pymap.pymapejb.model.MarcaNegocio;
import cl.pymap.pymapejb.model.Negocio;
import cl.pymap.pymapejb.model.Producto;
import cl.pymap.pymapejb.model.ProductoNegocio;

public class ProductoNegocioFK {
	private ProductoDAOLocal daoProducto = new ProductoDAO();
	private NegocioDAOLocal daoNegocio = new NegocioDAO();
	private Producto p = new Producto();
	private Negocio n = new Negocio();
	
	public int getProductoId(ProductoNegocio pn) {
		this.p = daoProducto.find(pn.getProductoFK());
		return this.p.getId();
	}
	
	public String getProductoNombre(ProductoNegocio pn) {
		this.p = daoProducto.find(pn.getProductoFK());
		return this.p.getNombre();
	}
	
	public String getProductoDescripcion(ProductoNegocio pn) {
		this.p = daoProducto.find(pn.getProductoFK());
		return this.p.getDescripcion();
	}
	
	public double getProductoPrecio(ProductoNegocio pn) {
		this.p = daoProducto.find(pn.getProductoFK());
		return this.p.getPrecio();
	}
	
	public String getProductoImagen(ProductoNegocio pn) {
		this.p = daoProducto.find(pn.getProductoFK());
		return this.p.getImagen();
	}
	
	public int getProductoMarcaFK(ProductoNegocio pn) {
		this.p = daoProducto.find(pn.getProductoFK());
		return this.p.getMarcaFK();
	}
	
	public int getProductoCategoriaFK(ProductoNegocio pn) {
		this.p = daoProducto.find(pn.getProductoFK());
		return this.p.getCategoriaFK();
	}
	
	public int getProductoTipoProductoFK(ProductoNegocio pn) {
		this.p = daoProducto.find(pn.getProductoFK());
		return this.p.getTipoProductoFK();
	}
	
	public String getNegocioRut(ProductoNegocio pn) {
		this.n = daoNegocio.find(pn.getNegocioFK());
		return this.n.getRut();
	}
	
	public String getNegocioNombre(ProductoNegocio pn) {
		this.n = daoNegocio.find(pn.getNegocioFK());
		return this.n.getNombre();
	}
	
	public String getNegocioCiudad(ProductoNegocio pn) {
		this.n = daoNegocio.find(pn.getNegocioFK());
		return this.n.getCiudad();
	}
	
	public String getNegocioComuna(ProductoNegocio pn) {
		this.n = daoNegocio.find(pn.getNegocioFK());
		return this.n.getComuna();
	}
	
	public String getNegocioCalle(ProductoNegocio pn) {
		this.n = daoNegocio.find(pn.getNegocioFK());
		return this.n.getCalle();
	}
	
	public String getNegocioDiasAtencion(ProductoNegocio pn) {
		this.n = daoNegocio.find(pn.getNegocioFK());
		return this.n.getDiasAtencion();
	}
	
	public String getNegocioMetodosPago(ProductoNegocio pn) {
		this.n = daoNegocio.find(pn.getNegocioFK());
		return this.n.getMetodosPago();
	}
	
	public String getNegocioEstado(ProductoNegocio pn) {
		this.n = daoNegocio.find(pn.getNegocioFK());
		return this.n.getEstado();
	}
	
	public String getNegocioLogo(MarcaNegocio mn) {
		this.n = daoNegocio.find(mn.getNegocioFK());
		return this.n.getLogo();
	}
	
	public int getNegocioTipoNegocioFK(ProductoNegocio pn) {
		this.n = daoNegocio.find(pn.getNegocioFK());
		return this.n.getTipoNegocioFK();
	}
	
	public String getNegocioUsuarioFK(ProductoNegocio pn) {
		this.n = daoNegocio.find(pn.getNegocioFK());
		return this.n.getUsuarioFK();
	}
}
