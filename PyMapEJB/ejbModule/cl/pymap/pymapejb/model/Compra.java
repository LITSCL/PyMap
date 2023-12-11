package cl.pymap.pymapejb.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Compra
 *
 */
@Entity
@Table(name = "compra")
@NamedQueries({
	@NamedQuery(name = "Compra.getAll", query = "SELECT c FROM Compra c")
})
public class Compra implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "coste")
	private double coste;
	@Column(name = "estado")
	private String estado;
	@Column(name = "fecha")
	private String fecha;
	@Column(name = "hora")
	private String hora;
	@Column(name = "usuario_correo")
	private String usuarioFK;
	@Column(name = "negocio_rut")
	private String negocioFK;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCoste() {
		return coste;
	}

	public void setCoste(double coste) {
		this.coste = coste;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getUsuarioFK() {
		return usuarioFK;
	}

	public void setUsuarioFK(String usuarioFK) {
		this.usuarioFK = usuarioFK;
	}

	public String getNegocioFK() {
		return negocioFK;
	}
	
	public void setNegocioFK(String negocioFK) {
		this.negocioFK = negocioFK;
	}
   
}
