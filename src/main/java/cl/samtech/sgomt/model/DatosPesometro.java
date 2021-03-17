package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;

import com.vividsolutions.jts.geom.Geometry;

import java.sql.Timestamp;


/**
 * The persistent class for the datos_pesometro database table.
 * 
 */
@Entity
@Table(name="datos_pesometro")
@NamedQuery(name="DatosPesometro.findAll", query="SELECT d FROM DatosPesometro d")
public class DatosPesometro implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DatosPesometroPK id;

	@Column(name="dev_id_device")
	private String devIdDevice;

	@Column(name="per_goecoding")
	private String perGoecoding;

	@Column(name="pes_fecha_inserta")
	private Timestamp pesFechaInserta;

	@Column(name="pes_geom")
	private Geometry pesGeom;

	@Column(name="pes_gps")
	private String pesGps;

	@Column(name="pes_id")
	private Integer pesId;

	@Column(name="pes_latitud")
	private String pesLatitud;

	@Column(name="pes_logitud")
	private String pesLogitud;

	@Column(name="pes_orientacion")
	private Integer pesOrientacion;

	@Column(name="pes_peso")
	private double pesPeso;

	@Column(name="pes_tipo_peso")
	private String pesTipoPeso;

	@Column(name="pes_velocidad")
	private Integer pesVelocidad;

	public DatosPesometro() {
	}

	public DatosPesometroPK getId() {
		return this.id;
	}

	public void setId(DatosPesometroPK id) {
		this.id = id;
	}

	public String getDevIdDevice() {
		return this.devIdDevice;
	}

	public void setDevIdDevice(String devIdDevice) {
		this.devIdDevice = devIdDevice;
	}

	public String getPerGoecoding() {
		return this.perGoecoding;
	}

	public void setPerGoecoding(String perGoecoding) {
		this.perGoecoding = perGoecoding;
	}

	public Timestamp getPesFechaInserta() {
		return this.pesFechaInserta;
	}

	public void setPesFechaInserta(Timestamp pesFechaInserta) {
		this.pesFechaInserta = pesFechaInserta;
	}

	/*public Object getPesGeom() {
		return this.pesGeom;
	}

	public void setPesGeom(Object pesGeom) {
		this.pesGeom = pesGeom;
	}*/

	public String getPesGps() {
		return this.pesGps;
	}

	public void setPesGps(String pesGps) {
		this.pesGps = pesGps;
	}

	public Integer getPesId() {
		return this.pesId;
	}

	public void setPesId(Integer pesId) {
		this.pesId = pesId;
	}

	public String getPesLatitud() {
		return this.pesLatitud;
	}

	public void setPesLatitud(String pesLatitud) {
		this.pesLatitud = pesLatitud;
	}

	public String getPesLogitud() {
		return this.pesLogitud;
	}

	public void setPesLogitud(String pesLogitud) {
		this.pesLogitud = pesLogitud;
	}

	public Integer getPesOrientacion() {
		return this.pesOrientacion;
	}

	public void setPesOrientacion(Integer pesOrientacion) {
		this.pesOrientacion = pesOrientacion;
	}

	public double getPesPeso() {
		return this.pesPeso;
	}

	public void setPesPeso(double pesPeso) {
		this.pesPeso = pesPeso;
	}

	public String getPesTipoPeso() {
		return this.pesTipoPeso;
	}

	public void setPesTipoPeso(String pesTipoPeso) {
		this.pesTipoPeso = pesTipoPeso;
	}

	public Integer getPesVelocidad() {
		return this.pesVelocidad;
	}

	public void setPesVelocidad(Integer pesVelocidad) {
		this.pesVelocidad = pesVelocidad;
	}

}