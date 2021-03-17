package cl.samtech.sgomt.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * The primary key class for the datos_gps database table.
 * 
 */
@Embeddable
public class DatosGpPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="rut_gps")
	private String rutGps;
	
	@Column(name="rut_patente")
	private String rutPatente;

	/*@Temporal(TemporalType.TIMESTAMP)
	@Column(name="rut_fecha_hora")
	private java.util.Date rutFechaHora;*/
	
	@Column(name="rut_fecha_hora")
	private Timestamp rutFechaHora;

	public DatosGpPK() {
	}
	public String getRutGps() {
		return this.rutGps;
	}
	public void setRutGps(String rutGps) {
		this.rutGps = rutGps;
	}
				
	/*public java.util.Date getRutFechaHora() {
		return this.rutFechaHora;
	}*/
	/*public void setRutFechaHora(java.util.Date rutFechaHora) {
		this.rutFechaHora = rutFechaHora;
	}*/

	public String getRutPatente() {
		return rutPatente;
	}
	public void setRutPatente(String rutPatente) {
		this.rutPatente = rutPatente;
	}
	public Timestamp getRutFechaHora() {
		return rutFechaHora;
	}
	public void setRutFechaHora(Timestamp rutFechaHora) {
		this.rutFechaHora = rutFechaHora;
	}
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DatosGpPK)) {
			return false;
		}
		DatosGpPK castOther = (DatosGpPK)other;
		return 
			this.rutGps.equals(castOther.rutGps)
			&& this.rutFechaHora.equals(castOther.rutFechaHora);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.rutGps.hashCode();
		hash = hash * prime + this.rutFechaHora.hashCode();
		
		return hash;
	}
}