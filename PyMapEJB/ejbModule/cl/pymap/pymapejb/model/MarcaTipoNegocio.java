package cl.pymap.pymapejb.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: MarcaTipoNegocio
 *
 */
@Entity
@Table(name = "`marca_tipo-negocio`")
@NamedQueries({
	@NamedQuery(name = "MarcaTipoNegocio.getAll", query = "SELECT mtn FROM MarcaTipoNegocio mtn")
})
public class MarcaTipoNegocio implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public MarcaTipoNegocio() {
		super();
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "marca_id")
    private int marcaFK;
    @Column(name = "`tipo-negocio_id`")
    private int tipoNegocioFK;

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

	public int getTipoNegocioFK() {
		return tipoNegocioFK;
	}
	
	public void setTipoNegocioFK(int tipoNegocioFK) {
		this.tipoNegocioFK = tipoNegocioFK;
	}
   
}
