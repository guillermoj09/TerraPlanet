package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the datos_can_grafico database table.
 * 
 */
@Embeddable
public class DatosCanGraficoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="gra_gps")
	private String graGps;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="gra_fecha_hora")
	private java.util.Date graFechaHora;

	@Column(name="gra_energizacion")
	private Integer graEnergizacion;

	@Column(name="gra_partida")
	private Integer graPartida;

	public DatosCanGraficoPK() {
	}
	public String getGraGps() {
		return this.graGps;
	}
	public void setGraGps(String graGps) {
		this.graGps = graGps;
	}
	public java.util.Date getGraFechaHora() {
		return this.graFechaHora;
	}
	public void setGraFechaHora(java.util.Date graFechaHora) {
		this.graFechaHora = graFechaHora;
	}
	public Integer getGraEnergizacion() {
		return this.graEnergizacion;
	}
	public void setGraEnergizacion(Integer graEnergizacion) {
		this.graEnergizacion = graEnergizacion;
	}
	public Integer getGraPartida() {
		return this.graPartida;
	}
	public void setGraPartida(Integer graPartida) {
		this.graPartida = graPartida;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DatosCanGraficoPK)) {
			return false;
		}
		DatosCanGraficoPK castOther = (DatosCanGraficoPK)other;
		return 
			this.graGps.equals(castOther.graGps)
			&& this.graFechaHora.equals(castOther.graFechaHora)
			&& this.graEnergizacion.equals(castOther.graEnergizacion)
			&& this.graPartida.equals(castOther.graPartida);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.graGps.hashCode();
		hash = hash * prime + this.graFechaHora.hashCode();
		hash = hash * prime + this.graEnergizacion.hashCode();
		hash = hash * prime + this.graPartida.hashCode();
		
		return hash;
	}
}