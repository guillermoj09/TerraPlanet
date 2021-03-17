package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;

import com.vividsolutions.jts.geom.Geometry;

import java.sql.Timestamp;


/**
 * The persistent class for the datos_ibutton_ultimo database table.
 * 
 */
@Entity
@Table(name="datos_ibutton_ultimo")
@NamedQuery(name="DatosIbuttonUltimo.findAll", query="SELECT d FROM DatosIbuttonUltimo d")
public class DatosIbuttonUltimo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DatosIbuttonUltimoPK id;

	@Column(name="ibu_detenido")
	private Integer ibuDetenido;

	@Column(name="ibu_dist_recorrida")
	private double ibuDistRecorrida;

	@Column(name="ibu_duracion")
	private Integer ibuDuracion;

	@Column(name="ibu_estado")
	private String ibuEstado;

	@Column(name="ibu_evento")
	private String ibuEvento;

	@Column(name="ibu_fecha_inserta")
	private Timestamp ibuFechaInserta;

	@Column(name="ibu_geocoding")
	private String ibuGeocoding;

	@Column(name="ibu_geom")
	private Geometry ibuGeom;

	@Column(name="ibu_id")
	private Integer ibuId;

	@Column(name="ibu_latitud")
	private String ibuLatitud;

	@Column(name="ibu_longitud")
	private String ibuLongitud;

	@Column(name="ibu_movimiento")
	private Integer ibuMovimiento;

	public DatosIbuttonUltimo() {
	}

	public DatosIbuttonUltimoPK getId() {
		return this.id;
	}

	public void setId(DatosIbuttonUltimoPK id) {
		this.id = id;
	}

	public Integer getIbuDetenido() {
		return this.ibuDetenido;
	}

	public void setIbuDetenido(Integer ibuDetenido) {
		this.ibuDetenido = ibuDetenido;
	}

	public double getIbuDistRecorrida() {
		return this.ibuDistRecorrida;
	}

	public void setIbuDistRecorrida(double ibuDistRecorrida) {
		this.ibuDistRecorrida = ibuDistRecorrida;
	}

	public Integer getIbuDuracion() {
		return this.ibuDuracion;
	}

	public void setIbuDuracion(Integer ibuDuracion) {
		this.ibuDuracion = ibuDuracion;
	}

	public String getIbuEstado() {
		return this.ibuEstado;
	}

	public void setIbuEstado(String ibuEstado) {
		this.ibuEstado = ibuEstado;
	}

	public String getIbuEvento() {
		return this.ibuEvento;
	}

	public void setIbuEvento(String ibuEvento) {
		this.ibuEvento = ibuEvento;
	}

	public Timestamp getIbuFechaInserta() {
		return this.ibuFechaInserta;
	}

	public void setIbuFechaInserta(Timestamp ibuFechaInserta) {
		this.ibuFechaInserta = ibuFechaInserta;
	}

	public String getIbuGeocoding() {
		return this.ibuGeocoding;
	}

	public void setIbuGeocoding(String ibuGeocoding) {
		this.ibuGeocoding = ibuGeocoding;
	}

	/*public Object getIbuGeom() {
		return this.ibuGeom;
	}

	public void setIbuGeom(Object ibuGeom) {
		this.ibuGeom = ibuGeom;
	}*/

	public Integer getIbuId() {
		return this.ibuId;
	}

	public void setIbuId(Integer ibuId) {
		this.ibuId = ibuId;
	}

	public String getIbuLatitud() {
		return this.ibuLatitud;
	}

	public void setIbuLatitud(String ibuLatitud) {
		this.ibuLatitud = ibuLatitud;
	}

	public String getIbuLongitud() {
		return this.ibuLongitud;
	}

	public void setIbuLongitud(String ibuLongitud) {
		this.ibuLongitud = ibuLongitud;
	}

	public Integer getIbuMovimiento() {
		return this.ibuMovimiento;
	}

	public void setIbuMovimiento(Integer ibuMovimiento) {
		this.ibuMovimiento = ibuMovimiento;
	}

}