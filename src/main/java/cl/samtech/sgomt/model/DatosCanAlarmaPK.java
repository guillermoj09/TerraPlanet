package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the datos_can_alarma database table.
 * 
 */
@Embeddable
public class DatosCanAlarmaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ala_gps")
	private String alaGps;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ala_fecha_hora")
	private java.util.Date alaFechaHora;

	@Column(name="ala_energizacion")
	private Integer alaEnergizacion;

	@Column(name="ala_partida")
	private Integer alaPartida;

	public DatosCanAlarmaPK() {
	}
	public String getAlaGps() {
		return this.alaGps;
	}
	public void setAlaGps(String alaGps) {
		this.alaGps = alaGps;
	}
	public java.util.Date getAlaFechaHora() {
		return this.alaFechaHora;
	}
	public void setAlaFechaHora(java.util.Date alaFechaHora) {
		this.alaFechaHora = alaFechaHora;
	}
	public Integer getAlaEnergizacion() {
		return this.alaEnergizacion;
	}
	public void setAlaEnergizacion(Integer alaEnergizacion) {
		this.alaEnergizacion = alaEnergizacion;
	}
	public Integer getAlaPartida() {
		return this.alaPartida;
	}
	public void setAlaPartida(Integer alaPartida) {
		this.alaPartida = alaPartida;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DatosCanAlarmaPK)) {
			return false;
		}
		DatosCanAlarmaPK castOther = (DatosCanAlarmaPK)other;
		return 
			this.alaGps.equals(castOther.alaGps)
			&& this.alaFechaHora.equals(castOther.alaFechaHora)
			&& this.alaEnergizacion.equals(castOther.alaEnergizacion)
			&& this.alaPartida.equals(castOther.alaPartida);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.alaGps.hashCode();
		hash = hash * prime + this.alaFechaHora.hashCode();
		hash = hash * prime + this.alaEnergizacion.hashCode();
		hash = hash * prime + this.alaPartida.hashCode();
		
		return hash;
	}
}