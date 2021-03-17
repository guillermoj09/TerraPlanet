package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the datos_can_da database table.
 * 
 */
@Embeddable
public class DatosCanDaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="da_gps")
	private String daGps;

	@Column(name="da_energizacion")
	private Integer daEnergizacion;

	@Column(name="da_partida")
	private Integer daPartida;

	public DatosCanDaPK() {
	}
	public String getDaGps() {
		return this.daGps;
	}
	public void setDaGps(String daGps) {
		this.daGps = daGps;
	}
	public Integer getDaEnergizacion() {
		return this.daEnergizacion;
	}
	public void setDaEnergizacion(Integer daEnergizacion) {
		this.daEnergizacion = daEnergizacion;
	}
	public Integer getDaPartida() {
		return this.daPartida;
	}
	public void setDaPartida(Integer daPartida) {
		this.daPartida = daPartida;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DatosCanDaPK)) {
			return false;
		}
		DatosCanDaPK castOther = (DatosCanDaPK)other;
		return 
			this.daGps.equals(castOther.daGps)
			&& this.daEnergizacion.equals(castOther.daEnergizacion)
			&& this.daPartida.equals(castOther.daPartida);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.daGps.hashCode();
		hash = hash * prime + this.daEnergizacion.hashCode();
		hash = hash * prime + this.daPartida.hashCode();
		
		return hash;
	}
}