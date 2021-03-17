package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;

import com.vividsolutions.jts.geom.Geometry;

import java.sql.Timestamp;


/**
 * The persistent class for the datos_rfid database table.
 * 
 */
@Entity
@Table(name="datos_rfid")
@NamedQuery(name="DatosRfid.findAll", query="SELECT d FROM DatosRfid d")
public class DatosRfid implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DatosRfidPK id;

	@Column(name="dev_id_device")
	private String devIdDevice;

	@Column(name="rf_fecha_inserta")
	private Timestamp rfFechaInserta;

	@Column(name="rf_geocoding")
	private String rfGeocoding;

	@Column(name="rf_geom")
	private Geometry rfGeom;

	@Column(name="rf_id")
	private Integer rfId;

	@Column(name="rf_id_maquina")
	private String rfIdMaquina;

	@Column(name="rf_id_rfid_camion")
	private String rfIdRfidCamion;

	@Column(name="rf_latitud")
	private String rfLatitud;

	@Column(name="rf_longitud")
	private String rfLongitud;

	public DatosRfid() {
	}

	public DatosRfidPK getId() {
		return this.id;
	}

	public void setId(DatosRfidPK id) {
		this.id = id;
	}

	public String getDevIdDevice() {
		return this.devIdDevice;
	}

	public void setDevIdDevice(String devIdDevice) {
		this.devIdDevice = devIdDevice;
	}

	public Timestamp getRfFechaInserta() {
		return this.rfFechaInserta;
	}

	public void setRfFechaInserta(Timestamp rfFechaInserta) {
		this.rfFechaInserta = rfFechaInserta;
	}

	public String getRfGeocoding() {
		return this.rfGeocoding;
	}

	public void setRfGeocoding(String rfGeocoding) {
		this.rfGeocoding = rfGeocoding;
	}

	/*public Object getRfGeom() {
		return this.rfGeom;
	}

	public void setRfGeom(Object rfGeom) {
		this.rfGeom = rfGeom;
	}*/

	public Integer getRfId() {
		return this.rfId;
	}

	public void setRfId(Integer rfId) {
		this.rfId = rfId;
	}

	public String getRfIdMaquina() {
		return this.rfIdMaquina;
	}

	public void setRfIdMaquina(String rfIdMaquina) {
		this.rfIdMaquina = rfIdMaquina;
	}

	public String getRfIdRfidCamion() {
		return this.rfIdRfidCamion;
	}

	public void setRfIdRfidCamion(String rfIdRfidCamion) {
		this.rfIdRfidCamion = rfIdRfidCamion;
	}

	public String getRfLatitud() {
		return this.rfLatitud;
	}

	public void setRfLatitud(String rfLatitud) {
		this.rfLatitud = rfLatitud;
	}

	public String getRfLongitud() {
		return this.rfLongitud;
	}

	public void setRfLongitud(String rfLongitud) {
		this.rfLongitud = rfLongitud;
	}

}