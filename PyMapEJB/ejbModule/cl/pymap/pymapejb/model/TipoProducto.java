package cl.pymap.pymapejb.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: TipoProducto
 *
 */
@Entity
@Table(name = "`tipo-producto`")
@NamedQueries({
	@NamedQuery(name = "TipoProducto.getAll", query = "SELECT tp FROM TipoProducto tp")
})
public class TipoProducto implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "categoria_id")
    private int categoriaFK;
    
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

	public int getCategoriaFK() {
		return categoriaFK;
	}
	
	public void setCategoriaFK(int categoriaFK) {
		this.categoriaFK = categoriaFK;
	}
	
}
