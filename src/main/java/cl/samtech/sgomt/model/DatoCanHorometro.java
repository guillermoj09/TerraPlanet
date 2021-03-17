package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;

import com.vividsolutions.jts.geom.Geometry;

import java.sql.Timestamp;


/**
 * The persistent class for the dato_can_horometro database table.
 * 
 */
@Entity
@Table(name="dato_can_horometro")
@NamedQuery(name="DatoCanHorometro.findAll", query="SELECT d FROM DatoCanHorometro d")
public class DatoCanHorometro implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DatoCanHorometroPK id;

	@Column(name="dev_id_device")
	private String devIdDevice;

	@Column(name="hor_contador_base")
	private Integer horContadorBase;

	@Column(name="hor_contador1")
	private Integer horContador1;

	@Column(name="hor_contador2")
	private Integer horContador2;

	@Column(name="hor_contador3")
	private Integer horContador3;

	@Column(name="hor_contador4")
	private Integer horContador4;

	@Column(name="hor_estado_entrada1")
	private Integer horEstadoEntrada1;

	@Column(name="hor_estado_entrada2")
	private Integer horEstadoEntrada2;

	@Column(name="hor_estado_entrada3")
	private Integer horEstadoEntrada3;

	@Column(name="hor_estado_entrada4")
	private Integer horEstadoEntrada4;

	@Column(name="hor_estado_sal1")
	private Integer horEstadoSal1;

	@Column(name="hor_estado_sal2")
	private Integer horEstadoSal2;

	@Column(name="hor_fecha_inserta")
	private Timestamp horFechaInserta;

	@Column(name="hor_geocoding")
	private String horGeocoding;

	@Column(name="hor_geom")
	private Geometry horGeom;

	@Column(name="hor_id")
	private Integer horId;

	@Column(name="hor_identificador")
	private String horIdentificador;

	@Column(name="hor_latitud")
	private String horLatitud;

	@Column(name="hor_longitud")
	private String horLongitud;

	@Column(name="hor_num_secuencia")
	private Integer horNumSecuencia;

	@Column(name="hor_velocidad")
	private Integer horVelocidad;

	public DatoCanHorometro() {
	}

	public DatoCanHorometroPK getId() {
		return this.id;
	}

	public void setId(DatoCanHorometroPK id) {
		this.id = id;
	}

	public String getDevIdDevice() {
		return this.devIdDevice;
	}

	public void setDevIdDevice(String devIdDevice) {
		this.devIdDevice = devIdDevice;
	}

	public Integer getHorContadorBase() {
		return this.horContadorBase;
	}

	public void setHorContadorBase(Integer horContadorBase) {
		this.horContadorBase = horContadorBase;
	}

	public Integer getHorContador1() {
		return this.horContador1;
	}

	public void setHorContador1(Integer horContador1) {
		this.horContador1 = horContador1;
	}

	public Integer getHorContador2() {
		return this.horContador2;
	}

	public void setHorContador2(Integer horContador2) {
		this.horContador2 = horContador2;
	}

	public Integer getHorContador3() {
		return this.horContador3;
	}

	public void setHorContador3(Integer horContador3) {
		this.horContador3 = horContador3;
	}

	public Integer getHorContador4() {
		return this.horContador4;
	}

	public void setHorContador4(Integer horContador4) {
		this.horContador4 = horContador4;
	}

	public Integer getHorEstadoEntrada1() {
		return this.horEstadoEntrada1;
	}

	public void setHorEstadoEntrada1(Integer horEstadoEntrada1) {
		this.horEstadoEntrada1 = horEstadoEntrada1;
	}

	public Integer getHorEstadoEntrada2() {
		return this.horEstadoEntrada2;
	}

	public void setHorEstadoEntrada2(Integer horEstadoEntrada2) {
		this.horEstadoEntrada2 = horEstadoEntrada2;
	}

	public Integer getHorEstadoEntrada3() {
		return this.horEstadoEntrada3;
	}

	public void setHorEstadoEntrada3(Integer horEstadoEntrada3) {
		this.horEstadoEntrada3 = horEstadoEntrada3;
	}

	public Integer getHorEstadoEntrada4() {
		return this.horEstadoEntrada4;
	}

	public void setHorEstadoEntrada4(Integer horEstadoEntrada4) {
		this.horEstadoEntrada4 = horEstadoEntrada4;
	}

	public Integer getHorEstadoSal1() {
		return this.horEstadoSal1;
	}

	public void setHorEstadoSal1(Integer horEstadoSal1) {
		this.horEstadoSal1 = horEstadoSal1;
	}

	public Integer getHorEstadoSal2() {
		return this.horEstadoSal2;
	}

	public void setHorEstadoSal2(Integer horEstadoSal2) {
		this.horEstadoSal2 = horEstadoSal2;
	}

	public Timestamp getHorFechaInserta() {
		return this.horFechaInserta;
	}

	public void setHorFechaInserta(Timestamp horFechaInserta) {
		this.horFechaInserta = horFechaInserta;
	}

	public String getHorGeocoding() {
		return this.horGeocoding;
	}

	public void setHorGeocoding(String horGeocoding) {
		this.horGeocoding = horGeocoding;
	}

	/*public Object getHorGeom() {
		return this.horGeom;
	}

	public void setHorGeom(Object horGeom) {
		this.horGeom = horGeom;
	}*/

	public Integer getHorId() {
		return this.horId;
	}

	public void setHorId(Integer horId) {
		this.horId = horId;
	}

	public String getHorIdentificador() {
		return this.horIdentificador;
	}

	public void setHorIdentificador(String horIdentificador) {
		this.horIdentificador = horIdentificador;
	}

	public String getHorLatitud() {
		return this.horLatitud;
	}

	public void setHorLatitud(String horLatitud) {
		this.horLatitud = horLatitud;
	}

	public String getHorLongitud() {
		return this.horLongitud;
	}

	public void setHorLongitud(String horLongitud) {
		this.horLongitud = horLongitud;
	}

	public Integer getHorNumSecuencia() {
		return this.horNumSecuencia;
	}

	public void setHorNumSecuencia(Integer horNumSecuencia) {
		this.horNumSecuencia = horNumSecuencia;
	}

	public Integer getHorVelocidad() {
		return this.horVelocidad;
	}

	public void setHorVelocidad(Integer horVelocidad) {
		this.horVelocidad = horVelocidad;
	}

}