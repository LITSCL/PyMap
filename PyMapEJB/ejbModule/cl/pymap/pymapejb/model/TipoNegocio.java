package cl.pymap.pymapejb.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: TipoNegocio
 *
 */
@Entity
@Table(name = "`tipo-negocio`")
@NamedQueries({
	@NamedQuery(name = "TipoNegocio.getAll", query = "SELECT tn FROM TipoNegocio tn")
})
public class TipoNegocio implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public TipoNegocio() {
		super();
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "nombre")
    private String nombre;
    
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

}
