package cl.pymap.pymapejb.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Producto
 *
 */
@Entity
@Table(name = "producto")
@NamedQueries({
	@NamedQuery(name = "Producto.getAll", query = "SELECT p FROM Producto p")
})
public class Producto implements Serializable {

	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "precio")
    private double precio;
    @Column(name = "imagen")
    private String imagen;
    @Column(name = "marca_id")
    private int marcaFK;
    @Column(name = "categoria_id")
    private int categoriaFK;
    @Column(name = "`tipo-producto_id`")
    private int tipoProductoFK;
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public int getMarcaFK() {
		return marcaFK;
	}

	public void setMarcaFK(int marcaFK) {
		this.marcaFK = marcaFK;
	}

	public int getCategoriaFK() {
		return categoriaFK;
	}

	public void setCategoriaFK(int categoriaFK) {
		this.categoriaFK = categoriaFK;
	}

	public int getTipoProductoFK() {
		return tipoProductoFK;
	}
	
	public void setTipoProductoFK(int tipoFK) {
		this.tipoProductoFK = tipoFK;
	} 
	
}
