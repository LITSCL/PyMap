package cl.pymap.pymapejb.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: NegocioTipoProducto
 *
 */
@Entity
@Table(name = "`tipo-producto_negocio`")
@NamedQueries({
	@NamedQuery(name = "TipoProductoNegocio.getAll", query = "SELECT tpn FROM TipoProductoNegocio tpn")
})
public class TipoProductoNegocio implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public TipoProductoNegocio() {
		super();
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "`tipo-producto_id`")
    private int tipoProductoFK;
    @Column(name = "negocio_rut")
    private String negocioFK;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTipoProductoFK() {
		return tipoProductoFK;
	}
	public void setTipoProductoFK(int tipoProductoFK) {
		this.tipoProductoFK = tipoProductoFK;
	}
	public String getNegocioFK() {
		return negocioFK;
	}
	public void setNegocioFK(String negocioFK) {
		this.negocioFK = negocioFK;
	}
   
}
