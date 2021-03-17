package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the device database table.
 * 
 */
@Entity
@Table(name="DEVICE", schema="dispositivo")
@NamedQuery(name="Device.findAll", query="SELECT d FROM Device d")
public class Device implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="dev_id")
	private String devId;

	@Column(name="dev_fecha_crea")
	private Timestamp devFechaCrea;

	@Column(name="dev_fono")
	private String devFono;

	@Column(name="dev_imei")
	private String devImei;

	@Column(name="dev_num_serie")
	private String devNumSerie;

	//bi-directional many-to-one association to TipoDevice
	@ManyToOne
	@JoinColumn(name="tdev_id_tipo_device")
	private TipoDevice tipoDevice;

	//bi-directional many-to-one association to VehiculoDevice
	/*@OneToMany(mappedBy="device")
	private List<VehiculoDevice> vehiculoDevices;*/
	
	//bi-directional many-to-many association to devices
	@ManyToMany(mappedBy="devices", cascade = CascadeType.MERGE)
	private List<Vehiculo> vehiculos;
	
	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public Device() {
	}

	public String getDevId() {
		return this.devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}

	public Timestamp getDevFechaCrea() {
		return this.devFechaCrea;
	}

	public void setDevFechaCrea(Timestamp devFechaCrea) {
		this.devFechaCrea = devFechaCrea;
	}

	public String getDevFono() {
		return this.devFono;
	}

	public void setDevFono(String devFono) {
		this.devFono = devFono;
	}

	public String getDevImei() {
		return this.devImei;
	}

	public void setDevImei(String devImei) {
		this.devImei = devImei;
	}

	public String getDevNumSerie() {
		return this.devNumSerie;
	}

	public void setDevNumSerie(String devNumSerie) {
		this.devNumSerie = devNumSerie;
	}

	public TipoDevice getTipoDevice() {
		return this.tipoDevice;
	}

	public void setTipoDevice(TipoDevice tipoDevice) {
		this.tipoDevice = tipoDevice;
	}

	/*public List<VehiculoDevice> getVehiculoDevices() {
		return this.vehiculoDevices;
	}

	public void setVehiculoDevices(List<VehiculoDevice> vehiculoDevices) {
		this.vehiculoDevices = vehiculoDevices;
	}

	public VehiculoDevice addVehiculoDevice(VehiculoDevice vehiculoDevice) {
		getVehiculoDevices().add(vehiculoDevice);
		vehiculoDevice.setDevice(this);

		return vehiculoDevice;
	}

	public VehiculoDevice removeVehiculoDevice(VehiculoDevice vehiculoDevice) {
		getVehiculoDevices().remove(vehiculoDevice);
		vehiculoDevice.setDevice(null);

		return vehiculoDevice;
	}*/

}