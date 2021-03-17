package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the vehiculo_device database table.
 * 
 */
@Entity
@Table(name="vehiculo_device", schema="dispositivo")
@NamedQuery(name="VehiculoDevice.findAll", query="SELECT v FROM VehiculoDevice v")
public class VehiculoDevice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "vehiculo_device_vehdev_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "vehiculo_device_vehdev_id_seq", sequenceName = "dispositivo.vehiculo_device_vehdev_id_seq",allocationSize=1)
	@Column(name="vehdev_id", unique=true, nullable=false, precision=10, scale=0)	
	private Integer vehdevId;

	@Column(name="vehdev_fecha_fin")
	private Timestamp vehdevFechaFin;

	@Column(name="vehdev_fecha_ini")
	private Timestamp vehdevFechaIni;

	//bi-directional many-to-one association to Device
	/*@ManyToOne
	@JoinColumn(name="dev_id_device")
	private Device device; */

	//bi-directional many-to-one association to Vehiculo
	/*@ManyToOne
	@JoinColumn(name="veh_patente_vehiculo")
	private Vehiculo vehiculo; */
	
	@Column(name="veh_patente_vehiculo")
	private String vehPatenteVehiculo; 
	
	@Column(name="dev_id_device")
	private String devIdDevice;

	public String getVehPatenteVehiculo() {
		return vehPatenteVehiculo;
	}

	public void setVehPatenteVehiculo(String vehPatenteVehiculo) {
		this.vehPatenteVehiculo = vehPatenteVehiculo;
	}

	public String getDevIdDevice() {
		return devIdDevice;
	}

	public void setDevIdDevice(String devIdDevice) {
		this.devIdDevice = devIdDevice;
	}

	public VehiculoDevice() {
	}

	public Integer getVehdevId() {
		return this.vehdevId;
	}

	public void setVehdevId(Integer vehdevId) {
		this.vehdevId = vehdevId;
	}

	public Timestamp getVehdevFechaFin() {
		return this.vehdevFechaFin;
	}

	public void setVehdevFechaFin(Timestamp vehdevFechaFin) {
		this.vehdevFechaFin = vehdevFechaFin;
	}

	public Timestamp getVehdevFechaIni() {
		return this.vehdevFechaIni;
	}

	public void setVehdevFechaIni(Timestamp vehdevFechaIni) {
		this.vehdevFechaIni = vehdevFechaIni;
	}

	/*public Device getDevice() {
		return this.device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Vehiculo getVehiculo() {
		return this.vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}*/

}