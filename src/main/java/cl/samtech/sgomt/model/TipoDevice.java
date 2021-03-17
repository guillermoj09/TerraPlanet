package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tipo_device database table.
 * 
 */
@Entity
@Table(name="tipo_device", schema="dispositivo")
@NamedQuery(name="TipoDevice.findAll", query="SELECT t FROM TipoDevice t")
public class TipoDevice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="tdev_id")
	private Integer tdevId;

	@Column(name="cli_rut_cliente")
	private String cliRutCliente;

	@Column(name="tdev_descripcion")
	private String tdevDescripcion;

	@Column(name="tdev_fabricante")
	private String tdevFabricante;

	@Column(name="tdev_fecha_ingresa")
	private Timestamp tdevFechaIngresa;

	@Column(name="tdev_marca")
	private String tdevMarca;

	@Column(name="tdev_modelo")
	private String tdevModelo;

	@Column(name="tdev_tipo")
	private String tdevTipo;

	//bi-directional many-to-one association to Device
	@OneToMany(mappedBy="tipoDevice")
	private List<Device> devices;

	public TipoDevice() {
	}

	public Integer getTdevId() {
		return this.tdevId;
	}

	public void setTdevId(Integer tdevId) {
		this.tdevId = tdevId;
	}

	public String getCliRutCliente() {
		return this.cliRutCliente;
	}

	public void setCliRutCliente(String cliRutCliente) {
		this.cliRutCliente = cliRutCliente;
	}

	public String getTdevDescripcion() {
		return this.tdevDescripcion;
	}

	public void setTdevDescripcion(String tdevDescripcion) {
		this.tdevDescripcion = tdevDescripcion;
	}

	public String getTdevFabricante() {
		return this.tdevFabricante;
	}

	public void setTdevFabricante(String tdevFabricante) {
		this.tdevFabricante = tdevFabricante;
	}

	public Timestamp getTdevFechaIngresa() {
		return this.tdevFechaIngresa;
	}

	public void setTdevFechaIngresa(Timestamp tdevFechaIngresa) {
		this.tdevFechaIngresa = tdevFechaIngresa;
	}

	public String getTdevMarca() {
		return this.tdevMarca;
	}

	public void setTdevMarca(String tdevMarca) {
		this.tdevMarca = tdevMarca;
	}

	public String getTdevModelo() {
		return this.tdevModelo;
	}

	public void setTdevModelo(String tdevModelo) {
		this.tdevModelo = tdevModelo;
	}

	public String getTdevTipo() {
		return this.tdevTipo;
	}

	public void setTdevTipo(String tdevTipo) {
		this.tdevTipo = tdevTipo;
	}

	public List<Device> getDevices() {
		return this.devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public Device addDevice(Device device) {
		getDevices().add(device);
		device.setTipoDevice(this);

		return device;
	}

	public Device removeDevice(Device device) {
		getDevices().remove(device);
		device.setTipoDevice(null);

		return device;
	}

}