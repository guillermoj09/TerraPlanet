package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the datos_ibutton database table.
 * 
 */
@Embeddable
public class DatosIbuttonPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ibu_gps")
	private String ibuGps;

	@Column(name="ibu_estado")
	private String ibuEstado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ibu_fecha_inicio")
	private java.util.Date ibuFechaInicio;

	public DatosIbuttonPK() {
	}
	public String getIbuGps() {
		return this.ibuGps;
	}
	public void setIbuGps(String ibuGps) {
		this.ibuGps = ibuGps;
	}
	public String getIbuEstado() {
		return this.ibuEstado;
	}
	public void setIbuEstado(String ibuEstado) {
		this.ibuEstado = ibuEstado;
	}
	public java.util.Date getIbuFechaInicio() {
		return this.ibuFechaInicio;
	}
	public void setIbuFechaInicio(java.util.Date ibuFechaInicio) {
		this.ibuFechaInicio = ibuFechaInicio;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DatosIbuttonPK)) {
			return false;
		}
		DatosIbuttonPK castOther = (DatosIbuttonPK)other;
		return 
			this.ibuGps.equals(castOther.ibuGps)
			&& this.ibuEstado.equals(castOther.ibuEstado)
			&& this.ibuFechaInicio.equals(castOther.ibuFechaInicio);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.ibuGps.hashCode();
		hash = hash * prime + this.ibuEstado.hashCode();
		hash = hash * prime + this.ibuFechaInicio.hashCode();
		
		return hash;
	}
}