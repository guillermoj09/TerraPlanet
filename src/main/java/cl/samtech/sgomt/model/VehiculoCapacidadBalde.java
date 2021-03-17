package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the vehiculo_capacidad_balde database table.
 * 
 */
@Entity
@Table(name="vehiculo_capacidad_balde", schema = "dispositivo")
@NamedQuery(name="VehiculoCapacidadBalde.findAll", query="SELECT v FROM VehiculoCapacidadBalde v")
public class VehiculoCapacidadBalde implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="vehbal_id")
	private Integer vehbalId;

	@Column(name="veh_patente_vehiculo")
	private String vehPatenteVehiculo;

	@Column(name="vehbal_fecha_fin")
	private Timestamp vehbalFechaFin;

	@Column(name="vehbal_fecha_ini")
	private Timestamp vehbalFechaIni;

	public VehiculoCapacidadBalde() {
	}

	public Integer getVehbalId() {
		return this.vehbalId;
	}

	public void setVehbalId(Integer vehbalId) {
		this.vehbalId = vehbalId;
	}

	public String getVehPatenteVehiculo() {
		return this.vehPatenteVehiculo;
	}

	public void setVehPatenteVehiculo(String vehPatenteVehiculo) {
		this.vehPatenteVehiculo = vehPatenteVehiculo;
	}

	public Timestamp getVehbalFechaFin() {
		return this.vehbalFechaFin;
	}

	public void setVehbalFechaFin(Timestamp vehbalFechaFin) {
		this.vehbalFechaFin = vehbalFechaFin;
	}

	public Timestamp getVehbalFechaIni() {
		return this.vehbalFechaIni;
	}

	public void setVehbalFechaIni(Timestamp vehbalFechaIni) {
		this.vehbalFechaIni = vehbalFechaIni;
	}

}