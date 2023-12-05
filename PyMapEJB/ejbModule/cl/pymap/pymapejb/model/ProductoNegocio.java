package cl.pymap.pymapejb.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ProductoNegocio
 *
 */
@Entity
@Table(name = "producto_negocio")
@NamedQueries({
	@NamedQuery(name = "ProductoNegocio.getAll", query = "SELECT pn FROM ProductoNegocio pn")
})
public class ProductoNegocio implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "precio")
    private double precio;
    @Column(name = "producto_id")
    private int productoFK;
    @Column(name = "negocio_rut")
    private String negocioFK;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getProductoFK() {
		return productoFK;
	}
	public void setProductoFK(int productoFK) {
		this.productoFK = productoFK;
	}
	public String getNegocioFK() {
		return negocioFK;
	}
	public void setNegocioFK(String negocioFK) {
		this.negocioFK = negocioFK;
	}
    
}
