package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the datos_can_hormetro_alarma database table.
 * 
 */
@Entity
@Table(name="datos_can_hormetro_alarma")
@NamedQuery(name="DatosCanHormetroAlarma.findAll", query="SELECT d FROM DatosCanHormetroAlarma d")
public class DatosCanHormetroAlarma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="dev_id_device")
	private String devIdDevice;

	@Column(name="horala_energizacion")
	private Integer horalaEnergizacion;

	@Column(name="horala_id")
	private Integer horalaId;

	@Column(name="horala_limite_alarma")
	private Integer horalaLimiteAlarma;

	@Column(name="horala_num_secuencia")
	private Integer horalaNumSecuencia;

	public DatosCanHormetroAlarma() {
	}

	public String getDevIdDevice() {
		return this.devIdDevice;
	}

	public void setDevIdDevice(String devIdDevice) {
		this.devIdDevice = devIdDevice;
	}

	public Integer getHoralaEnergizacion() {
		return this.horalaEnergizacion;
	}

	public void setHoralaEnergizacion(Integer horalaEnergizacion) {
		this.horalaEnergizacion = horalaEnergizacion;
	}

	public Integer getHoralaId() {
		return this.horalaId;
	}

	public void setHoralaId(Integer horalaId) {
		this.horalaId = horalaId;
	}

	public Integer getHoralaLimiteAlarma() {
		return this.horalaLimiteAlarma;
	}

	public void setHoralaLimiteAlarma(Integer horalaLimiteAlarma) {
		this.horalaLimiteAlarma = horalaLimiteAlarma;
	}

	public Integer getHoralaNumSecuencia() {
		return this.horalaNumSecuencia;
	}

	public void setHoralaNumSecuencia(Integer horalaNumSecuencia) {
		this.horalaNumSecuencia = horalaNumSecuencia;
	}

}