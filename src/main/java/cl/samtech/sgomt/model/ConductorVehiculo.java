package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the conductor_vehiculo database table.
 * 
 */
@Entity
@Table(name="conductor_vehiculo", schema="dispositivo")
@NamedQuery(name="ConductorVehiculo.findAll", query="SELECT c FROM ConductorVehiculo c")
public class ConductorVehiculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="condveh_id")
	private Integer condvehId;

	@Column(name="cond_rut_conductor")
	private String condRutConductor;

	@Column(name="condveh_fecha_fin")
	private Timestamp condvehFechaFin;

	@Column(name="conveh_fecha_inicio")
	private Timestamp convehFechaInicio;

	//bi-directional many-to-one association to Vehiculo
	@ManyToOne
	@JoinColumn(name="veh_patente_vehiculo")
	private Vehiculo vehiculo;

	public ConductorVehiculo() {
	}

	public Integer getCondvehId() {
		return this.condvehId;
	}

	public void setCondvehId(Integer condvehId) {
		this.condvehId = condvehId;
	}

	public String getCondRutConductor() {
		return this.condRutConductor;
	}

	public void setCondRutConductor(String condRutConductor) {
		this.condRutConductor = condRutConductor;
	}

	public Timestamp getCondvehFechaFin() {
		return this.condvehFechaFin;
	}

	public void setCondvehFechaFin(Timestamp condvehFechaFin) {
		this.condvehFechaFin = condvehFechaFin;
	}

	public Timestamp getConvehFechaInicio() {
		return this.convehFechaInicio;
	}

	public void setConvehFechaInicio(Timestamp convehFechaInicio) {
		this.convehFechaInicio = convehFechaInicio;
	}

	public Vehiculo getVehiculo() {
		return this.vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

}