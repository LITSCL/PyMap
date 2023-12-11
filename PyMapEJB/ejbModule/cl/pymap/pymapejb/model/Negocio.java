package cl.pymap.pymapejb.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Negocio
 *
 */
@Entity
@Table(name = "negocio")
@NamedQueries({
	@NamedQuery(name = "Negocio.getAll", query = "SELECT n FROM Negocio n")
})
public class Negocio implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "rut")
	private String rut;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "ciudad")
	private String ciudad;
	@Column(name = "comuna")
	private String comuna;
	@Column(name = "calle")
	private String calle;
	@Column(name = "dias_atencion")
	private String diasAtencion;
	@Column(name = "metodos_pago")
	private String metodosPago;
	@Column(name = "estado")
	private String estado;
	@Column(name = "logo")
	private String logo;
	@Column(name = "`tipo-negocio_id`")
	private int tipoNegocioFK;
	@Column(name = "usuario_correo")
	private String usuarioFK;
	
	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getComuna() {
		return comuna;
	}

	public void setComuna(String comuna) {
		this.comuna = comuna;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getDiasAtencion() {
		return diasAtencion;
	}

	public void setDiasAtencion(String diasAtencion) {
		this.diasAtencion = diasAtencion;
	}

	public String getMetodosPago() {
		return metodosPago;
	}

	public void setMetodosPago(String metodosPago) {
		this.metodosPago = metodosPago;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public int getTipoNegocioFK() {
		return tipoNegocioFK;
	}

	public void setTipoNegocioFK(int tipoNegocioFK) {
		this.tipoNegocioFK = tipoNegocioFK;
	}

	public String getUsuarioFK() {
		return usuarioFK;
	}
	
	public void setUsuarioFK(String usuarioFK) {
		this.usuarioFK = usuarioFK;
	}
	
}
