package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the datos_geocercas database table.
 * 
 */
@Entity
@Table(name="datos_geocercas")
@NamedQuery(name="DatosGeocerca.findAll", query="SELECT d FROM DatosGeocerca d")
public class DatosGeocerca implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DatosGeocercaPK id;

	private String chofer;

	@Column(name="dev_id_device")
	private String devIdDevice;

	@Column(name="geo_fecha_inserta")
	private Timestamp geoFechaInserta;

	@Column(name="geo_id")
	private Integer geoId;

	private Integer hdg;

	private Integer hdop;

	private String latitud;

	private String longitud;

	@Column(name="nom_zona")
	private String nomZona;

	private String patente;

	private Integer spd;

	private String tipo;

	public DatosGeocerca() {
	}

	public DatosGeocercaPK getId() {
		return this.id;
	}

	public void setId(DatosGeocercaPK id) {
		this.id = id;
	}

	public String getChofer() {
		return this.chofer;
	}

	public void setChofer(String chofer) {
		this.chofer = chofer;
	}

	public String getDevIdDevice() {
		return this.devIdDevice;
	}

	public void setDevIdDevice(String devIdDevice) {
		this.devIdDevice = devIdDevice;
	}

	public Timestamp getGeoFechaInserta() {
		return this.geoFechaInserta;
	}

	public void setGeoFechaInserta(Timestamp geoFechaInserta) {
		this.geoFechaInserta = geoFechaInserta;
	}

	public Integer getGeoId() {
		return this.geoId;
	}

	public void setGeoId(Integer geoId) {
		this.geoId = geoId;
	}

	public Integer getHdg() {
		return this.hdg;
	}

	public void setHdg(Integer hdg) {
		this.hdg = hdg;
	}

	public Integer getHdop() {
		return this.hdop;
	}

	public void setHdop(Integer hdop) {
		this.hdop = hdop;
	}

	public String getLatitud() {
		return this.latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return this.longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getNomZona() {
		return this.nomZona;
	}

	public void setNomZona(String nomZona) {
		this.nomZona = nomZona;
	}

	public String getPatente() {
		return this.patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public Integer getSpd() {
		return this.spd;
	}

	public void setSpd(Integer spd) {
		this.spd = spd;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}