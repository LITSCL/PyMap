package cl.pymap.pymapejb.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ProductoCompra
 *
 */
@Entity
@Table(name = "producto_compra")
@NamedQueries({
	@NamedQuery(name = "ProductoCompra.getAll", query = "SELECT pc FROM ProductoCompra pc")
})
public class ProductoCompra implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "compra_id")
    private int compraFK;
    @Column(name = "producto_id")
    private int productoFK;
    @Column(name = "unidades")
    private int unidades;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCompraFK() {
		return compraFK;
	}
	public void setCompraFK(int compraFK) {
		this.compraFK = compraFK;
	}
	public int getProductoFK() {
		return productoFK;
	}
	public void setProductoFK(int productoFK) {
		this.productoFK = productoFK;
	}
	public int getUnidades() {
		return unidades;
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
    
}
