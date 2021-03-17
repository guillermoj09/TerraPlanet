package cl.samtech.sgomt.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * The primary key class for the datos_gps_ultimo database table.
 * 
 */
@Embeddable
public class DatosGpsUltimoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="rut_gps")
	private String rutGps;

	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name="rut_fecha_hora")
	private Timestamp rutFechaHora;

	@Column(name="rut_patente")
	private String rutPatente;

	public DatosGpsUltimoPK() {
	}
	public String getRutGps() {
		return this.rutGps;
	}
	public void setRutGps(String rutGps) {
		this.rutGps = rutGps;
	}
	/*public java.util.Date getRutFechaHora() {
		return this.rutFechaHora;
	}
	public void setRutFechaHora(java.util.Date rutFechaHora) {
		this.rutFechaHora = rutFechaHora;
	}*/
	
	
	
	public String getRutPatente() {
		return this.rutPatente;
	}
	public Timestamp getRutFechaHora() {
		return rutFechaHora;
	}
	public void setRutFechaHora(Timestamp rutFechaHora) {
		this.rutFechaHora = rutFechaHora;
	}
	public void setRutPatente(String rutPatente) {
		this.rutPatente = rutPatente;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DatosGpsUltimoPK)) {
			return false;
		}
		DatosGpsUltimoPK castOther = (DatosGpsUltimoPK)other;
		return 
			this.rutGps.equals(castOther.rutGps)
			&& this.rutFechaHora.equals(castOther.rutFechaHora)
			&& this.rutPatente.equals(castOther.rutPatente);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.rutGps.hashCode();
		hash = hash * prime + this.rutFechaHora.hashCode();
		hash = hash * prime + this.rutPatente.hashCode();
		
		return hash;
	}
}