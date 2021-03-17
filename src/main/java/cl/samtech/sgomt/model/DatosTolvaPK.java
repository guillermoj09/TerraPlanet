package cl.samtech.sgomt.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * The primary key class for the datos_tolva database table.
 * 
 */
@Embeddable
public class DatosTolvaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="tol_gps")
	private String tolGps;

	@Column(name="tol_patente")
	private String tolPatente;

	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name="tol_fecha_hora")
	//private java.util.Date tolFechaHora;
	private Timestamp tolFechaHora;
	
	public Timestamp getTolFechaHora() {
		return tolFechaHora;
	}
	public void setTolFechaHora(Timestamp tolFechaHora) {
		this.tolFechaHora = tolFechaHora;
	}
	public DatosTolvaPK() {
	}
	public String getTolGps() {
		return this.tolGps;
	}
	public void setTolGps(String tolGps) {
		this.tolGps = tolGps;
	}
	public String getTolPatente() {
		return this.tolPatente;
	}
	public void setTolPatente(String tolPatente) {
		this.tolPatente = tolPatente;
	}
	/*public java.util.Date getTolFechaHora() {
		return this.tolFechaHora;
	}*/
	/*public void setTolFechaHora(java.util.Date tolFechaHora) {
		this.tolFechaHora = tolFechaHora;
	}*/

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DatosTolvaPK)) {
			return false;
		}
		DatosTolvaPK castOther = (DatosTolvaPK)other;
		return 
			this.tolGps.equals(castOther.tolGps)
			&& this.tolPatente.equals(castOther.tolPatente)
			&& this.tolFechaHora.equals(castOther.tolFechaHora);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.tolGps.hashCode();
		hash = hash * prime + this.tolPatente.hashCode();
		hash = hash * prime + this.tolFechaHora.hashCode();
		
		return hash;
	}
}