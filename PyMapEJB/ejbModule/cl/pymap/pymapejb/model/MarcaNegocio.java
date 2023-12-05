package cl.pymap.pymapejb.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: MarcaNegocio
 *
 */
@Entity
@Table(name = "marca_negocio")
@NamedQueries({
	@NamedQuery(name = "MarcaNegocio.getAll", query = "SELECT mn FROM MarcaNegocio mn")
})
public class MarcaNegocio implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public MarcaNegocio() {
		super();
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "marca_id")
    private int marcaFK;
    @Column(name = "negocio_rut")
    private String negocioFK;

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
	public String getNegocioFK() {
		return negocioFK;
	}
	public void setNegocioFK(String negocioFK) {
		this.negocioFK = negocioFK;
	}
   
}
