package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the datos_can_fin_fa database table.
 * 
 */
@Entity
@Table(name="datos_can_fin_fa")
@NamedQuery(name="DatosCanFinFa.findAll", query="SELECT d FROM DatosCanFinFa d")
public class DatosCanFinFa implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DatosCanFinFaPK id;

	@Column(name="dev_id_device")
	private String devIdDevice;

	@Column(name="fin_cosumo_acumulado")
	private double finCosumoAcumulado;

	@Column(name="fin_distancia_trayecto")
	private double finDistanciaTrayecto;

	@Column(name="fin_fecha_hora")
	private Timestamp finFechaHora;

	@Column(name="fin_geocoding")
	private String finGeocoding;

   //	@Column(name="fin_geom")
	//	private Object finGeom;

	@Column(name="fin_horo_caiquen")
	private double finHoroCaiquen;

	@Column(name="fin_horometro_fin")
	private double finHorometroFin;

	@Column(name="fin_id")
	private Integer finId;

	@Column(name="fin_latitud")
	private String finLatitud;

	@Column(name="fin_longitud")
	private String finLongitud;

	@Column(name="fin_nivel_aceite")
	private Integer finNivelAceite;

	@Column(name="fin_nivel_estanque_fin")
	private Integer finNivelEstanqueFin;

	@Column(name="fin_nivel_refrigerante")
	private Integer finNivelRefrigerante;

	@Column(name="fin_odolitro_fin")
	private Integer finOdolitroFin;

	@Column(name="fin_odolitro_ini")
	private Integer finOdolitroIni;

	@Column(name="fin_odometro_fin")
	private double finOdometroFin;

	@Column(name="fin_odometro_inicio")
	private double finOdometroInicio;

	@Column(name="fin_orientacion")
	private Integer finOrientacion;

	@Column(name="fin_presion_aceite_motor")
	private Integer finPresionAceiteMotor;

	@Column(name="fin_presion_hidraulico")
	private Integer finPresionHidraulico;

	@Column(name="fin_presion_turbo")
	private Integer finPresionTurbo;

	@Column(name="fin_prom_carga_motor")
	private Integer finPromCargaMotor;

	@Column(name="fin_tipo_trama")
	private String finTipoTrama;

	@Column(name="fin_tmpo_acum_operacion")
	private Integer finTmpoAcumOperacion;

	@Column(name="fin_tmpo_marcha")
	private Integer finTmpoMarcha;

	@Column(name="fin_velocidad")
	private Integer finVelocidad;

	@Column(name="fin_velocidad_max_motor")
	private Integer finVelocidadMaxMotor;

	@Column(name="fin_velocidad_maxima")
	private Integer finVelocidadMaxima;

	//bi-directional many-to-one association to DatosCanFinFb
	@OneToMany(mappedBy="datosCanFinFa")
	private List<DatosCanFinFb> datosCanFinFbs;

	//bi-directional many-to-one association to DatosCanFinFc
	@OneToMany(mappedBy="datosCanFinFa")
	private List<DatosCanFinFc> datosCanFinFcs;

	//bi-directional many-to-one association to DatosCanFinFd
	@OneToMany(mappedBy="datosCanFinFa")
	private List<DatosCanFinFd> datosCanFinFds;

	public DatosCanFinFa() {
	}

	public DatosCanFinFaPK getId() {
		return this.id;
	}

	public void setId(DatosCanFinFaPK id) {
		this.id = id;
	}

	public String getDevIdDevice() {
		return this.devIdDevice;
	}

	public void setDevIdDevice(String devIdDevice) {
		this.devIdDevice = devIdDevice;
	}

	public double getFinCosumoAcumulado() {
		return this.finCosumoAcumulado;
	}

	public void setFinCosumoAcumulado(double finCosumoAcumulado) {
		this.finCosumoAcumulado = finCosumoAcumulado;
	}

	public double getFinDistanciaTrayecto() {
		return this.finDistanciaTrayecto;
	}

	public void setFinDistanciaTrayecto(double finDistanciaTrayecto) {
		this.finDistanciaTrayecto = finDistanciaTrayecto;
	}

	public Timestamp getFinFechaHora() {
		return this.finFechaHora;
	}

	public void setFinFechaHora(Timestamp finFechaHora) {
		this.finFechaHora = finFechaHora;
	}

	public String getFinGeocoding() {
		return this.finGeocoding;
	}

	public void setFinGeocoding(String finGeocoding) {
		this.finGeocoding = finGeocoding;
	}

	/*public Object getFinGeom() {
		return this.finGeom;
	}

	public void setFinGeom(Object finGeom) {
		this.finGeom = finGeom;
	}*/

	public double getFinHoroCaiquen() {
		return this.finHoroCaiquen;
	}

	public void setFinHoroCaiquen(double finHoroCaiquen) {
		this.finHoroCaiquen = finHoroCaiquen;
	}

	public double getFinHorometroFin() {
		return this.finHorometroFin;
	}

	public void setFinHorometroFin(double finHorometroFin) {
		this.finHorometroFin = finHorometroFin;
	}

	public Integer getFinId() {
		return this.finId;
	}

	public void setFinId(Integer finId) {
		this.finId = finId;
	}

	public String getFinLatitud() {
		return this.finLatitud;
	}

	public void setFinLatitud(String finLatitud) {
		this.finLatitud = finLatitud;
	}

	public String getFinLongitud() {
		return this.finLongitud;
	}

	public void setFinLongitud(String finLongitud) {
		this.finLongitud = finLongitud;
	}

	public Integer getFinNivelAceite() {
		return this.finNivelAceite;
	}

	public void setFinNivelAceite(Integer finNivelAceite) {
		this.finNivelAceite = finNivelAceite;
	}

	public Integer getFinNivelEstanqueFin() {
		return this.finNivelEstanqueFin;
	}

	public void setFinNivelEstanqueFin(Integer finNivelEstanqueFin) {
		this.finNivelEstanqueFin = finNivelEstanqueFin;
	}

	public Integer getFinNivelRefrigerante() {
		return this.finNivelRefrigerante;
	}

	public void setFinNivelRefrigerante(Integer finNivelRefrigerante) {
		this.finNivelRefrigerante = finNivelRefrigerante;
	}

	public Integer getFinOdolitroFin() {
		return this.finOdolitroFin;
	}

	public void setFinOdolitroFin(Integer finOdolitroFin) {
		this.finOdolitroFin = finOdolitroFin;
	}

	public Integer getFinOdolitroIni() {
		return this.finOdolitroIni;
	}

	public void setFinOdolitroIni(Integer finOdolitroIni) {
		this.finOdolitroIni = finOdolitroIni;
	}

	public double getFinOdometroFin() {
		return this.finOdometroFin;
	}

	public void setFinOdometroFin(double finOdometroFin) {
		this.finOdometroFin = finOdometroFin;
	}

	public double getFinOdometroInicio() {
		return this.finOdometroInicio;
	}

	public void setFinOdometroInicio(double finOdometroInicio) {
		this.finOdometroInicio = finOdometroInicio;
	}

	public Integer getFinOrientacion() {
		return this.finOrientacion;
	}

	public void setFinOrientacion(Integer finOrientacion) {
		this.finOrientacion = finOrientacion;
	}

	public Integer getFinPresionAceiteMotor() {
		return this.finPresionAceiteMotor;
	}

	public void setFinPresionAceiteMotor(Integer finPresionAceiteMotor) {
		this.finPresionAceiteMotor = finPresionAceiteMotor;
	}

	public Integer getFinPresionHidraulico() {
		return this.finPresionHidraulico;
	}

	public void setFinPresionHidraulico(Integer finPresionHidraulico) {
		this.finPresionHidraulico = finPresionHidraulico;
	}

	public Integer getFinPresionTurbo() {
		return this.finPresionTurbo;
	}

	public void setFinPresionTurbo(Integer finPresionTurbo) {
		this.finPresionTurbo = finPresionTurbo;
	}

	public Integer getFinPromCargaMotor() {
		return this.finPromCargaMotor;
	}

	public void setFinPromCargaMotor(Integer finPromCargaMotor) {
		this.finPromCargaMotor = finPromCargaMotor;
	}

	public String getFinTipoTrama() {
		return this.finTipoTrama;
	}

	public void setFinTipoTrama(String finTipoTrama) {
		this.finTipoTrama = finTipoTrama;
	}

	public Integer getFinTmpoAcumOperacion() {
		return this.finTmpoAcumOperacion;
	}

	public void setFinTmpoAcumOperacion(Integer finTmpoAcumOperacion) {
		this.finTmpoAcumOperacion = finTmpoAcumOperacion;
	}

	public Integer getFinTmpoMarcha() {
		return this.finTmpoMarcha;
	}

	public void setFinTmpoMarcha(Integer finTmpoMarcha) {
		this.finTmpoMarcha = finTmpoMarcha;
	}

	public Integer getFinVelocidad() {
		return this.finVelocidad;
	}

	public void setFinVelocidad(Integer finVelocidad) {
		this.finVelocidad = finVelocidad;
	}

	public Integer getFinVelocidadMaxMotor() {
		return this.finVelocidadMaxMotor;
	}

	public void setFinVelocidadMaxMotor(Integer finVelocidadMaxMotor) {
		this.finVelocidadMaxMotor = finVelocidadMaxMotor;
	}

	public Integer getFinVelocidadMaxima() {
		return this.finVelocidadMaxima;
	}

	public void setFinVelocidadMaxima(Integer finVelocidadMaxima) {
		this.finVelocidadMaxima = finVelocidadMaxima;
	}

	public List<DatosCanFinFb> getDatosCanFinFbs() {
		return this.datosCanFinFbs;
	}

	public void setDatosCanFinFbs(List<DatosCanFinFb> datosCanFinFbs) {
		this.datosCanFinFbs = datosCanFinFbs;
	}

	public DatosCanFinFb addDatosCanFinFb(DatosCanFinFb datosCanFinFb) {
		getDatosCanFinFbs().add(datosCanFinFb);
		datosCanFinFb.setDatosCanFinFa(this);

		return datosCanFinFb;
	}

	public DatosCanFinFb removeDatosCanFinFb(DatosCanFinFb datosCanFinFb) {
		getDatosCanFinFbs().remove(datosCanFinFb);
		datosCanFinFb.setDatosCanFinFa(null);

		return datosCanFinFb;
	}

	public List<DatosCanFinFc> getDatosCanFinFcs() {
		return this.datosCanFinFcs;
	}

	public void setDatosCanFinFcs(List<DatosCanFinFc> datosCanFinFcs) {
		this.datosCanFinFcs = datosCanFinFcs;
	}

	public DatosCanFinFc addDatosCanFinFc(DatosCanFinFc datosCanFinFc) {
		getDatosCanFinFcs().add(datosCanFinFc);
		datosCanFinFc.setDatosCanFinFa(this);

		return datosCanFinFc;
	}

	public DatosCanFinFc removeDatosCanFinFc(DatosCanFinFc datosCanFinFc) {
		getDatosCanFinFcs().remove(datosCanFinFc);
		datosCanFinFc.setDatosCanFinFa(null);

		return datosCanFinFc;
	}

	public List<DatosCanFinFd> getDatosCanFinFds() {
		return this.datosCanFinFds;
	}

	public void setDatosCanFinFds(List<DatosCanFinFd> datosCanFinFds) {
		this.datosCanFinFds = datosCanFinFds;
	}

	public DatosCanFinFd addDatosCanFinFd(DatosCanFinFd datosCanFinFd) {
		getDatosCanFinFds().add(datosCanFinFd);
		datosCanFinFd.setDatosCanFinFa(this);

		return datosCanFinFd;
	}

	public DatosCanFinFd removeDatosCanFinFd(DatosCanFinFd datosCanFinFd) {
		getDatosCanFinFds().remove(datosCanFinFd);
		datosCanFinFd.setDatosCanFinFa(null);

		return datosCanFinFd;
	}

}