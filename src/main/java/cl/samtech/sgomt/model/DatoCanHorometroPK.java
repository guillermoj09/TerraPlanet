package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the dato_can_horometro database table.
 * 
 */
@Embeddable
public class DatoCanHorometroPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="hor_gps")
	private String horGps;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="hor_fecha_hora")
	private java.util.Date horFechaHora;

	@Column(name="hor_energizacion")
	private Integer horEnergizacion;

	@Column(name="hor_partida")
	private Integer horPartida;

	public DatoCanHorometroPK() {
	}
	public String getHorGps() {
		return this.horGps;
	}
	public void setHorGps(String horGps) {
		this.horGps = horGps;
	}
	public java.util.Date getHorFechaHora() {
		return this.horFechaHora;
	}
	public void setHorFechaHora(java.util.Date horFechaHora) {
		this.horFechaHora = horFechaHora;
	}
	public Integer getHorEnergizacion() {
		return this.horEnergizacion;
	}
	public void setHorEnergizacion(Integer horEnergizacion) {
		this.horEnergizacion = horEnergizacion;
	}
	public Integer getHorPartida() {
		return this.horPartida;
	}
	public void setHorPartida(Integer horPartida) {
		this.horPartida = horPartida;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DatoCanHorometroPK)) {
			return false;
		}
		DatoCanHorometroPK castOther = (DatoCanHorometroPK)other;
		return 
			this.horGps.equals(castOther.horGps)
			&& this.horFechaHora.equals(castOther.horFechaHora)
			&& this.horEnergizacion.equals(castOther.horEnergizacion)
			&& this.horPartida.equals(castOther.horPartida);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.horGps.hashCode();
		hash = hash * prime + this.horFechaHora.hashCode();
		hash = hash * prime + this.horEnergizacion.hashCode();
		hash = hash * prime + this.horPartida.hashCode();
		
		return hash;
	}
}