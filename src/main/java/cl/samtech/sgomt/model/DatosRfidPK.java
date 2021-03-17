package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the datos_rfid database table.
 * 
 */
@Embeddable
public class DatosRfidPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="rf_gps")
	private String rfGps;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="rf_fecha_hora")
	private java.util.Date rfFechaHora;

	public DatosRfidPK() {
	}
	public String getRfGps() {
		return this.rfGps;
	}
	public void setRfGps(String rfGps) {
		this.rfGps = rfGps;
	}
	public java.util.Date getRfFechaHora() {
		return this.rfFechaHora;
	}
	public void setRfFechaHora(java.util.Date rfFechaHora) {
		this.rfFechaHora = rfFechaHora;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DatosRfidPK)) {
			return false;
		}
		DatosRfidPK castOther = (DatosRfidPK)other;
		return 
			this.rfGps.equals(castOther.rfGps)
			&& this.rfFechaHora.equals(castOther.rfFechaHora);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.rfGps.hashCode();
		hash = hash * prime + this.rfFechaHora.hashCode();
		
		return hash;
	}
}