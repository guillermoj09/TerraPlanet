package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;

import com.vividsolutions.jts.geom.Geometry;

import java.sql.Timestamp;


/**
 * The persistent class for the datos_tolva database table.
 * 
 */
@Entity
@Table(name="datos_tolva")
@NamedQuery(name="DatosTolva.findAll", query="SELECT d FROM DatosTolva d")
public class DatosTolva implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DatosTolvaPK id;

	@Column(name="dev_goecoding")
	private String devGoecoding;

	@Column(name="dev_id_device")
	private String devIdDevice;

	@Column(name="tol_altitud")
	private Integer tolAltitud;

	@Column(name="tol_binario")
	private String tolBinario;

	@Column(name="tol_edad_gps")
	private Integer tolEdadGps;

	@Column(name="tol_estado_conexion")
	private Integer tolEstadoConexion;

	@Column(name="tol_estado_energia")
	private Integer tolEstadoEnergia;

	@Column(name="tol_estado_gsm")
	private Integer tolEstadoGsm;

	/*@Column(name="tol_evento")
	private Integer tolEvento; */
	
	//bi-directional many-to-one association to Cliente
			@ManyToOne
			@JoinColumn(name="tol_evento", columnDefinition="eve_id_id")
			private EventosGp eventosGp;

	@Column(name="tol_fecha_inserta")
	private Timestamp tolFechaInserta;

	@Column(name="tol_geom")
	private Geometry tolGeom;

	@Column(name="tol_hdop")
	private Integer tolHdop;

	@Column(name="tol_id")
	private Integer tolId;

	@Column(name="tol_latitud")
	private String tolLatitud;

	@Column(name="tol_longitud")
	private String tolLongitud;

	@Column(name="tol_num_gsm")
	private Integer tolNumGsm;

	@Column(name="tol_num_satelite")
	private Integer tolNumSatelite;

	@Column(name="tol_odometro")
	private double tolOdometro;

	@Column(name="tol_orientacion")
	private Integer tolOrientacion;

	@Column(name="tol_velocidad")
	private Integer tolVelocidad;
	
	public EventosGp getEventosGp() {
		return eventosGp;
	}

	public void setEventosGp(EventosGp eventosGp) {
		this.eventosGp = eventosGp;
	}

	public DatosTolva() {
	}

	public DatosTolvaPK getId() {
		return this.id;
	}

	public void setId(DatosTolvaPK id) {
		this.id = id;
	}

	public String getDevGoecoding() {
		return this.devGoecoding;
	}

	public void setDevGoecoding(String devGoecoding) {
		this.devGoecoding = devGoecoding;
	}

	public String getDevIdDevice() {
		return this.devIdDevice;
	}

	public void setDevIdDevice(String devIdDevice) {
		this.devIdDevice = devIdDevice;
	}

	public Integer getTolAltitud() {
		return this.tolAltitud;
	}

	public void setTolAltitud(Integer tolAltitud) {
		this.tolAltitud = tolAltitud;
	}

	public String getTolBinario() {
		return this.tolBinario;
	}

	public void setTolBinario(String tolBinario) {
		this.tolBinario = tolBinario;
	}

	public Integer getTolEdadGps() {
		return this.tolEdadGps;
	}

	public void setTolEdadGps(Integer tolEdadGps) {
		this.tolEdadGps = tolEdadGps;
	}

	public Integer getTolEstadoConexion() {
		return this.tolEstadoConexion;
	}

	public void setTolEstadoConexion(Integer tolEstadoConexion) {
		this.tolEstadoConexion = tolEstadoConexion;
	}

	public Integer getTolEstadoEnergia() {
		return this.tolEstadoEnergia;
	}

	public void setTolEstadoEnergia(Integer tolEstadoEnergia) {
		this.tolEstadoEnergia = tolEstadoEnergia;
	}

	public Integer getTolEstadoGsm() {
		return this.tolEstadoGsm;
	}

	public void setTolEstadoGsm(Integer tolEstadoGsm) {
		this.tolEstadoGsm = tolEstadoGsm;
	}

	/*public Integer getTolEvento() {
		return this.tolEvento;
	}

	public void setTolEvento(Integer tolEvento) {
		this.tolEvento = tolEvento;
	}*/

	public Timestamp getTolFechaInserta() {
		return this.tolFechaInserta;
	}

	public void setTolFechaInserta(Timestamp tolFechaInserta) {
		this.tolFechaInserta = tolFechaInserta;
	}

	
	
	public Geometry getTolGeom() {
		return tolGeom;
	}

	public void setTolGeom(Geometry tolGeom) {
		this.tolGeom = tolGeom;
	}

	public Integer getTolHdop() {
		return this.tolHdop;
	}

	public void setTolHdop(Integer tolHdop) {
		this.tolHdop = tolHdop;
	}

	public Integer getTolId() {
		return this.tolId;
	}

	public void setTolId(Integer tolId) {
		this.tolId = tolId;
	}

	public String getTolLatitud() {
		return this.tolLatitud;
	}

	public void setTolLatitud(String tolLatitud) {
		this.tolLatitud = tolLatitud;
	}

	public String getTolLongitud() {
		return this.tolLongitud;
	}

	public void setTolLongitud(String tolLongitud) {
		this.tolLongitud = tolLongitud;
	}

	public Integer getTolNumGsm() {
		return this.tolNumGsm;
	}

	public void setTolNumGsm(Integer tolNumGsm) {
		this.tolNumGsm = tolNumGsm;
	}

	public Integer getTolNumSatelite() {
		return this.tolNumSatelite;
	}

	public void setTolNumSatelite(Integer tolNumSatelite) {
		this.tolNumSatelite = tolNumSatelite;
	}

	public double getTolOdometro() {
		return this.tolOdometro;
	}

	public void setTolOdometro(double tolOdometro) {
		this.tolOdometro = tolOdometro;
	}

	public Integer getTolOrientacion() {
		return this.tolOrientacion;
	}

	public void setTolOrientacion(Integer tolOrientacion) {
		this.tolOrientacion = tolOrientacion;
	}

	public Integer getTolVelocidad() {
		return this.tolVelocidad;
	}

	public void setTolVelocidad(Integer tolVelocidad) {
		this.tolVelocidad = tolVelocidad;
	}

}