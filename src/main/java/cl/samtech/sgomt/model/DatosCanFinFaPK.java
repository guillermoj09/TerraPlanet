package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the datos_can_fin_fa database table.
 * 
 */
@Embeddable
public class DatosCanFinFaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="fin_gps")
	private String finGps;

	@Column(name="fin_energizacion")
	private Integer finEnergizacion;

	@Column(name="fin_partida")
	private Integer finPartida;

	@Column(name="fin_identificador")
	private String finIdentificador;

	public DatosCanFinFaPK() {
	}
	public String getFinGps() {
		return this.finGps;
	}
	public void setFinGps(String finGps) {
		this.finGps = finGps;
	}
	public Integer getFinEnergizacion() {
		return this.finEnergizacion;
	}
	public void setFinEnergizacion(Integer finEnergizacion) {
		this.finEnergizacion = finEnergizacion;
	}
	public Integer getFinPartida() {
		return this.finPartida;
	}
	public void setFinPartida(Integer finPartida) {
		this.finPartida = finPartida;
	}
	public String getFinIdentificador() {
		return this.finIdentificador;
	}
	public void setFinIdentificador(String finIdentificador) {
		this.finIdentificador = finIdentificador;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DatosCanFinFaPK)) {
			return false;
		}
		DatosCanFinFaPK castOther = (DatosCanFinFaPK)other;
		return 
			this.finGps.equals(castOther.finGps)
			&& this.finEnergizacion.equals(castOther.finEnergizacion)
			&& this.finPartida.equals(castOther.finPartida)
			&& this.finIdentificador.equals(castOther.finIdentificador);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.finGps.hashCode();
		hash = hash * prime + this.finEnergizacion.hashCode();
		hash = hash * prime + this.finPartida.hashCode();
		hash = hash * prime + this.finIdentificador.hashCode();
		
		return hash;
	}
}