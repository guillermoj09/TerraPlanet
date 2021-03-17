package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;

import com.vividsolutions.jts.geom.Geometry;

import java.sql.Timestamp;


/**
 * The persistent class for the datos_can_inicio database table.
 * 
 */
@Entity
@Table(name="datos_can_inicio")
@NamedQuery(name="DatosCanInicio.findAll", query="SELECT d FROM DatosCanInicio d")
public class DatosCanInicio implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DatosCanInicioPK id;

	@Column(name="dev_id_device")
	private String devIdDevice;

	@Column(name="ini_fecha_inserta")
	private Timestamp iniFechaInserta;

	@Column(name="ini_geocoding")
	private String iniGeocoding;

	@Column(name="ini_geom")
	private Geometry iniGeom;

	@Column(name="ini_horo_caiquen")
	private double iniHoroCaiquen;

	@Column(name="ini_horometro")
	private double iniHorometro;

	@Column(name="ini_id")
	private Integer iniId;

	@Column(name="ini_id_camion")
	private String iniIdCamion;

	@Column(name="ini_latitud")
	private String iniLatitud;

	@Column(name="ini_longitud")
	private String iniLongitud;

	@Column(name="ini_odolitro")
	private double iniOdolitro;

	@Column(name="ini_odometro")
	private double iniOdometro;

	@Column(name="ini_tipo_trama")
	private String iniTipoTrama;

	@Column(name="ini_version_fw")
	private String iniVersionFw;

	public DatosCanInicio() {
	}

	public DatosCanInicioPK getId() {
		return this.id;
	}

	public void setId(DatosCanInicioPK id) {
		this.id = id;
	}

	public String getDevIdDevice() {
		return this.devIdDevice;
	}

	public void setDevIdDevice(String devIdDevice) {
		this.devIdDevice = devIdDevice;
	}

	public Timestamp getIniFechaInserta() {
		return this.iniFechaInserta;
	}

	public void setIniFechaInserta(Timestamp iniFechaInserta) {
		this.iniFechaInserta = iniFechaInserta;
	}

	public String getIniGeocoding() {
		return this.iniGeocoding;
	}

	public void setIniGeocoding(String iniGeocoding) {
		this.iniGeocoding = iniGeocoding;
	}

	/*public Object getIniGeom() {
		return this.iniGeom;
	}

	public void setIniGeom(Object iniGeom) {
		this.iniGeom = iniGeom;
	}*/

	public double getIniHoroCaiquen() {
		return this.iniHoroCaiquen;
	}

	public void setIniHoroCaiquen(double iniHoroCaiquen) {
		this.iniHoroCaiquen = iniHoroCaiquen;
	}

	public double getIniHorometro() {
		return this.iniHorometro;
	}

	public void setIniHorometro(double iniHorometro) {
		this.iniHorometro = iniHorometro;
	}

	public Integer getIniId() {
		return this.iniId;
	}

	public void setIniId(Integer iniId) {
		this.iniId = iniId;
	}

	public String getIniIdCamion() {
		return this.iniIdCamion;
	}

	public void setIniIdCamion(String iniIdCamion) {
		this.iniIdCamion = iniIdCamion;
	}

	public String getIniLatitud() {
		return this.iniLatitud;
	}

	public void setIniLatitud(String iniLatitud) {
		this.iniLatitud = iniLatitud;
	}

	public String getIniLongitud() {
		return this.iniLongitud;
	}

	public void setIniLongitud(String iniLongitud) {
		this.iniLongitud = iniLongitud;
	}

	public double getIniOdolitro() {
		return this.iniOdolitro;
	}

	public void setIniOdolitro(double iniOdolitro) {
		this.iniOdolitro = iniOdolitro;
	}

	public double getIniOdometro() {
		return this.iniOdometro;
	}

	public void setIniOdometro(double iniOdometro) {
		this.iniOdometro = iniOdometro;
	}

	public String getIniTipoTrama() {
		return this.iniTipoTrama;
	}

	public void setIniTipoTrama(String iniTipoTrama) {
		this.iniTipoTrama = iniTipoTrama;
	}

	public String getIniVersionFw() {
		return this.iniVersionFw;
	}

	public void setIniVersionFw(String iniVersionFw) {
		this.iniVersionFw = iniVersionFw;
	}

}