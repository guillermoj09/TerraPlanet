package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;

import com.vividsolutions.jts.geom.Geometry;

import java.sql.Timestamp;


/**
 * The persistent class for the datos_can_alarma database table.
 * 
 */
@Entity
@Table(name="datos_can_alarma")
@NamedQuery(name="DatosCanAlarma.findAll", query="SELECT d FROM DatosCanAlarma d")
public class DatosCanAlarma implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DatosCanAlarmaPK id;

	@Column(name="ala_duracion_evento")
	private Integer alaDuracionEvento;

	@Column(name="ala_fecha_inserta")
	private Timestamp alaFechaInserta;

	@Column(name="ala_geocoding")
	private String alaGeocoding;

	@Column(name="ala_geom")
	private Geometry alaGeom;

	@Column(name="ala_id")
	private Integer alaId;

	@Column(name="ala_identificador")
	private String alaIdentificador;

	@Column(name="ala_latitud")
	private String alaLatitud;

	@Column(name="ala_longitud")
	private String alaLongitud;

	@Column(name="ala_rmp_max")
	private Integer alaRmpMax;

	@Column(name="ala_tipo_reporte")
	private String alaTipoReporte;

	@Column(name="ala_tmpo_inicia_evento")
	private Integer alaTmpoIniciaEvento;

	@Column(name="ala_velocidad_activacion")
	private Integer alaVelocidadActivacion;

	@Column(name="dev_id_device")
	private String devIdDevice;

	public DatosCanAlarma() {
	}

	public DatosCanAlarmaPK getId() {
		return this.id;
	}

	public void setId(DatosCanAlarmaPK id) {
		this.id = id;
	}

	public Integer getAlaDuracionEvento() {
		return this.alaDuracionEvento;
	}

	public void setAlaDuracionEvento(Integer alaDuracionEvento) {
		this.alaDuracionEvento = alaDuracionEvento;
	}

	public Timestamp getAlaFechaInserta() {
		return this.alaFechaInserta;
	}

	public void setAlaFechaInserta(Timestamp alaFechaInserta) {
		this.alaFechaInserta = alaFechaInserta;
	}

	public String getAlaGeocoding() {
		return this.alaGeocoding;
	}

	public void setAlaGeocoding(String alaGeocoding) {
		this.alaGeocoding = alaGeocoding;
	}

	public Integer getAlaId() {
		return this.alaId;
	}

	public void setAlaId(Integer alaId) {
		this.alaId = alaId;
	}

	public String getAlaIdentificador() {
		return this.alaIdentificador;
	}

	public void setAlaIdentificador(String alaIdentificador) {
		this.alaIdentificador = alaIdentificador;
	}

	public String getAlaLatitud() {
		return this.alaLatitud;
	}

	public void setAlaLatitud(String alaLatitud) {
		this.alaLatitud = alaLatitud;
	}

	public String getAlaLongitud() {
		return this.alaLongitud;
	}

	public void setAlaLongitud(String alaLongitud) {
		this.alaLongitud = alaLongitud;
	}

	public Integer getAlaRmpMax() {
		return this.alaRmpMax;
	}

	public void setAlaRmpMax(Integer alaRmpMax) {
		this.alaRmpMax = alaRmpMax;
	}

	public String getAlaTipoReporte() {
		return this.alaTipoReporte;
	}

	public void setAlaTipoReporte(String alaTipoReporte) {
		this.alaTipoReporte = alaTipoReporte;
	}

	public Integer getAlaTmpoIniciaEvento() {
		return this.alaTmpoIniciaEvento;
	}

	public void setAlaTmpoIniciaEvento(Integer alaTmpoIniciaEvento) {
		this.alaTmpoIniciaEvento = alaTmpoIniciaEvento;
	}

	public Integer getAlaVelocidadActivacion() {
		return this.alaVelocidadActivacion;
	}

	public void setAlaVelocidadActivacion(Integer alaVelocidadActivacion) {
		this.alaVelocidadActivacion = alaVelocidadActivacion;
	}

	public String getDevIdDevice() {
		return this.devIdDevice;
	}

	public void setDevIdDevice(String devIdDevice) {
		this.devIdDevice = devIdDevice;
	}

}