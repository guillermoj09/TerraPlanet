package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the datos_geocercas database table.
 * 
 */
@Embeddable
public class DatosGeocercaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_zona")
	private Integer idZona;

	@Column(name="id_gps")
	private String idGps;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_date")
	private java.util.Date dataDate;

	public DatosGeocercaPK() {
	}
	public Integer getIdZona() {
		return this.idZona;
	}
	public void setIdZona(Integer idZona) {
		this.idZona = idZona;
	}
	public String getIdGps() {
		return this.idGps;
	}
	public void setIdGps(String idGps) {
		this.idGps = idGps;
	}
	public java.util.Date getDataDate() {
		return this.dataDate;
	}
	public void setDataDate(java.util.Date dataDate) {
		this.dataDate = dataDate;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DatosGeocercaPK)) {
			return false;
		}
		DatosGeocercaPK castOther = (DatosGeocercaPK)other;
		return 
			this.idZona.equals(castOther.idZona)
			&& this.idGps.equals(castOther.idGps)
			&& this.dataDate.equals(castOther.dataDate);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idZona.hashCode();
		hash = hash * prime + this.idGps.hashCode();
		hash = hash * prime + this.dataDate.hashCode();
		
		return hash;
	}
}