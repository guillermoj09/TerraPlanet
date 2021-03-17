package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the datos_can_inicio database table.
 * 
 */
@Embeddable
public class DatosCanInicioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ini_gps")
	private String iniGps;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ini_fecha_hora")
	private java.util.Date iniFechaHora;

	@Column(name="ini_energizacion")
	private Integer iniEnergizacion;

	@Column(name="ini_partida")
	private Integer iniPartida;

	public DatosCanInicioPK() {
	}
	public String getIniGps() {
		return this.iniGps;
	}
	public void setIniGps(String iniGps) {
		this.iniGps = iniGps;
	}
	public java.util.Date getIniFechaHora() {
		return this.iniFechaHora;
	}
	public void setIniFechaHora(java.util.Date iniFechaHora) {
		this.iniFechaHora = iniFechaHora;
	}
	public Integer getIniEnergizacion() {
		return this.iniEnergizacion;
	}
	public void setIniEnergizacion(Integer iniEnergizacion) {
		this.iniEnergizacion = iniEnergizacion;
	}
	public Integer getIniPartida() {
		return this.iniPartida;
	}
	public void setIniPartida(Integer iniPartida) {
		this.iniPartida = iniPartida;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DatosCanInicioPK)) {
			return false;
		}
		DatosCanInicioPK castOther = (DatosCanInicioPK)other;
		return 
			this.iniGps.equals(castOther.iniGps)
			&& this.iniFechaHora.equals(castOther.iniFechaHora)
			&& this.iniEnergizacion.equals(castOther.iniEnergizacion)
			&& this.iniPartida.equals(castOther.iniPartida);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.iniGps.hashCode();
		hash = hash * prime + this.iniFechaHora.hashCode();
		hash = hash * prime + this.iniEnergizacion.hashCode();
		hash = hash * prime + this.iniPartida.hashCode();
		
		return hash;
	}
}