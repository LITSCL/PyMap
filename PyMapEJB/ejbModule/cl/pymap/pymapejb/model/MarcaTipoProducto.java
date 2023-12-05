package cl.pymap.pymapejb.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: MarcaTipoProducto
 *
 */
@Entity
@Table(name = "`marca_tipo-producto`")
@NamedQueries({
	@NamedQuery(name = "MarcaTipoProducto.getAll", query = "SELECT mtp FROM MarcaTipoProducto mtp")
})
public class MarcaTipoProducto implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public MarcaTipoProducto() {
		super();
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "marca_id")
    private int marcaFK;
    @Column(name = "`tipo-producto_id`")
    private int tipoProductoFK;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMarcaFK() {
		return marcaFK;
	}
	public void setMarcaFK(int marcaFK) {
		this.marcaFK = marcaFK;
	}
	public int getTipoProductoFK() {
		return tipoProductoFK;
	}
	public void setTipoProductoFK(int tipoProductoFK) {
		this.tipoProductoFK = tipoProductoFK;
	}
   
}
