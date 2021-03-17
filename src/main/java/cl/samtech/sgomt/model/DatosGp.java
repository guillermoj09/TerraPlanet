package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;

import com.vividsolutions.jts.geom.Geometry;

import java.sql.Timestamp;


/**
 * The persistent class for the datos_gps database table.
 * 
 */
@Entity
@Table(name="datos_gps")
@NamedQuery(name="DatosGp.findAll", query="SELECT d FROM DatosGp d")
public class DatosGp implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DatosGpPK id;

	@Column(name="dev_id_device")
	private String devIdDevice;

	@Column(name="rut_altitud")
	private Integer rutAltitud;

	@Column(name="rut_binario")
	private String rutBinario;

	@Column(name="rut_edad_gps")
	private Integer rutEdadGps;

	@Column(name="rut_estado_conexion")
	private Integer rutEstadoConexion;

	@Column(name="rut_estado_energia")
	private Integer rutEstadoEnergia;

	@Column(name="rut_estado_gsm")
	private Integer rutEstadoGsm;

	/*@Column(name="rut_evento")
	private Integer rutEvento;*/
	
	//bi-directional many-to-one association to Cliente
		@ManyToOne
		@JoinColumn(name="rut_evento", columnDefinition="eve_id_id")
		private EventosGp eventosGp;

	@Column(name="rut_fecha_inserta")
	private Timestamp rutFechaInserta;

	/*@Column(name="rut_geom")
	private Geometry rutGeom;*/

	@Column(name="rut_goecoding")
	private String rutGoecoding;

	@Column(name="rut_hdop")
	private Integer rutHdop;

	@Column(name="rut_id")
	private Integer rutId;

	@Column(name="rut_latitud")
	private String rutLatitud;

	@Column(name="rut_longitud")
	private String rutLongitud;

	@Column(name="rut_num_gsm")
	private Integer rutNumGsm;

	@Column(name="rut_num_satelite")
	private Integer rutNumSatelite;

	@Column(name="rut_odometro")
	private double rutOdometro;

	@Column(name="rut_orientacion")
	private Integer rutOrientacion;

	@Column(name="rut_velocidad")
	private Integer rutVelocidad;

	@Column(name="zon_id_zona")
	private Integer zonIdZona;
	
	@Column(name="rut_geocerca")
	private String rutGeocerca;

	public String getRutGeocerca() {
		return rutGeocerca;
	}

	public void setRutGeocerca(String rutGeocerca) {
		this.rutGeocerca = rutGeocerca;
	}

	public DatosGp() {
	}

	public DatosGpPK getId() {
		return this.id;
	}

	public void setId(DatosGpPK id) {
		this.id = id;
	}

	public String getDevIdDevice() {
		return this.devIdDevice;
	}

	public void setDevIdDevice(String devIdDevice) {
		this.devIdDevice = devIdDevice;
	}

	public Integer getRutAltitud() {
		return this.rutAltitud;
	}

	public void setRutAltitud(Integer rutAltitud) {
		this.rutAltitud = rutAltitud;
	}

	public String getRutBinario() {
		return this.rutBinario;
	}

	public void setRutBinario(String rutBinario) {
		this.rutBinario = rutBinario;
	}

	public Integer getRutEdadGps() {
		return this.rutEdadGps;
	}

	public void setRutEdadGps(Integer rutEdadGps) {
		this.rutEdadGps = rutEdadGps;
	}

	public Integer getRutEstadoConexion() {
		return this.rutEstadoConexion;
	}

	public void setRutEstadoConexion(Integer rutEstadoConexion) {
		this.rutEstadoConexion = rutEstadoConexion;
	}

	public Integer getRutEstadoEnergia() {
		return this.rutEstadoEnergia;
	}

	public void setRutEstadoEnergia(Integer rutEstadoEnergia) {
		this.rutEstadoEnergia = rutEstadoEnergia;
	}

	public Integer getRutEstadoGsm() {
		return this.rutEstadoGsm;
	}

	public void setRutEstadoGsm(Integer rutEstadoGsm) {
		this.rutEstadoGsm = rutEstadoGsm;
	}

	/*public Integer getRutEvento() {
		return this.rutEvento;
	}

	public void setRutEvento(Integer rutEvento) {
		this.rutEvento = rutEvento;
	}*/
	
	

	public Timestamp getRutFechaInserta() {
		return this.rutFechaInserta;
	}

	public EventosGp getEventosGp() {
		return eventosGp;
	}

	public void setEventosGp(EventosGp eventosGp) {
		this.eventosGp = eventosGp;
	}

	public void setRutFechaInserta(Timestamp rutFechaInserta) {
		this.rutFechaInserta = rutFechaInserta;
	}

	/*public Object getRutGeom() {
		return this.rutGeom;
	}

	public void setRutGeom(Object rutGeom) {
		this.rutGeom = rutGeom;
	}*/

	public String getRutGoecoding() {
		return this.rutGoecoding;
	}

	public void setRutGoecoding(String rutGoecoding) {
		this.rutGoecoding = rutGoecoding;
	}

	public Integer getRutHdop() {
		return this.rutHdop;
	}

	public void setRutHdop(Integer rutHdop) {
		this.rutHdop = rutHdop;
	}

	public Integer getRutId() {
		return this.rutId;
	}

	public void setRutId(Integer rutId) {
		this.rutId = rutId;
	}

	public String getRutLatitud() {
		return this.rutLatitud;
	}

	public void setRutLatitud(String rutLatitud) {
		this.rutLatitud = rutLatitud;
	}

	public String getRutLongitud() {
		return this.rutLongitud;
	}

	public void setRutLongitud(String rutLongitud) {
		this.rutLongitud = rutLongitud;
	}

	public Integer getRutNumGsm() {
		return this.rutNumGsm;
	}

	public void setRutNumGsm(Integer rutNumGsm) {
		this.rutNumGsm = rutNumGsm;
	}

	public Integer getRutNumSatelite() {
		return this.rutNumSatelite;
	}

	public void setRutNumSatelite(Integer rutNumSatelite) {
		this.rutNumSatelite = rutNumSatelite;
	}

	public double getRutOdometro() {
		return this.rutOdometro;
	}

	public void setRutOdometro(double rutOdometro) {
		this.rutOdometro = rutOdometro;
	}

	public Integer getRutOrientacion() {
		return this.rutOrientacion;
	}

	public void setRutOrientacion(Integer rutOrientacion) {
		this.rutOrientacion = rutOrientacion;
	}

	public Integer getRutVelocidad() {
		return this.rutVelocidad;
	}

	public void setRutVelocidad(Integer rutVelocidad) {
		this.rutVelocidad = rutVelocidad;
	}

	public Integer getZonIdZona() {
		return this.zonIdZona;
	}

	public void setZonIdZona(Integer zonIdZona) {
		this.zonIdZona = zonIdZona;
	}

}