package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the datos_pesometro database table.
 * 
 */
@Embeddable
public class DatosPesometroPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="pes_id_maquina")
	private String pesIdMaquina;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="pes_fecha_hora")
	private java.util.Date pesFechaHora;

	public DatosPesometroPK() {
	}
	public String getPesIdMaquina() {
		return this.pesIdMaquina;
	}
	public void setPesIdMaquina(String pesIdMaquina) {
		this.pesIdMaquina = pesIdMaquina;
	}
	public java.util.Date getPesFechaHora() {
		return this.pesFechaHora;
	}
	public void setPesFechaHora(java.util.Date pesFechaHora) {
		this.pesFechaHora = pesFechaHora;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DatosPesometroPK)) {
			return false;
		}
		DatosPesometroPK castOther = (DatosPesometroPK)other;
		return 
			this.pesIdMaquina.equals(castOther.pesIdMaquina)
			&& this.pesFechaHora.equals(castOther.pesFechaHora);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.pesIdMaquina.hashCode();
		hash = hash * prime + this.pesFechaHora.hashCode();
		
		return hash;
	}
}