package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;

import com.vividsolutions.jts.geom.Geometry;

import java.sql.Timestamp;


/**
 * The persistent class for the datos_can_reporte database table.
 * 
 */
@Entity
@Table(name="datos_can_reporte")
@NamedQuery(name="DatosCanReporte.findAll", query="SELECT d FROM DatosCanReporte d")
public class DatosCanReporte implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DatosCanReportePK id;

	@Column(name="da_acelerador")
	private Integer daAcelerador;

	@Column(name="da_consumo_acumulado")
	private double daConsumoAcumulado;

	@Column(name="da_crucero")
	private Integer daCrucero;

	@Column(name="da_direccion")
	private String daDireccion;

	@Column(name="da_dist_acumulada")
	private double daDistAcumulada;

	@Column(name="da_embrague")
	private Integer daEmbrague;

	@Column(name="da_fecha_hora_db")
	private Timestamp daFechaHoraDb;

	@Column(name="da_fecha_inserta")
	private Timestamp daFechaInserta;

	@Column(name="da_geocoding")
	private String daGeocoding;

	@Column(name="da_geom")
	private Geometry daGeom;

	@Column(name="da_horo_caiquen")
	private double daHoroCaiquen;

	@Column(name="da_horometro")
	private double daHorometro;

	@Column(name="da_id")
	private Integer daId;

	@Column(name="da_id_conductor")
	private Integer daIdConductor;

	@Column(name="da_id_faena")
	private Integer daIdFaena;

	@Column(name="da_identificador")
	private String daIdentificador;

	@Column(name="da_latitud")
	private String daLatitud;

	@Column(name="da_libre1")
	private Integer daLibre1;

	@Column(name="da_libre2")
	private Integer daLibre2;

	@Column(name="da_longitud")
	private String daLongitud;

	@Column(name="da_nivel_aceite")
	private Integer daNivelAceite;

	@Column(name="da_nivel_estanque")
	private Integer daNivelEstanque;

	@Column(name="da_nivel_estanque_adblue")
	private double daNivelEstanqueAdblue;

	@Column(name="da_nivel_refrigerante")
	private Integer daNivelRefrigerante;

	@Column(name="da_odo")
	private double daOdo;

	@Column(name="da_olt")
	private double daOlt;

	@Column(name="da_pedal_freno")
	private Integer daPedalFreno;

	@Column(name="da_presion_aceite")
	private Integer daPresionAceite;

	@Column(name="da_presion_hidraulica")
	private double daPresionHidraulica;

	@Column(name="da_presion_turbo")
	private double daPresionTurbo;

	@Column(name="da_pto")
	private Integer daPto;

	@Column(name="da_ralenti_acumulado")
	private double daRalentiAcumulado;

	@Column(name="da_retarde")
	private Integer daRetarde;

	@Column(name="da_rpm")
	private double daRpm;

	@Column(name="da_temp_aceite")
	private Integer daTempAceite;

	@Column(name="da_temp_hidraulico")
	private Integer daTempHidraulico;

	@Column(name="da_temp_refrigerante")
	private Integer daTempRefrigerante;

	@Column(name="da_temperatura_motor")
	private Integer daTemperaturaMotor;

	@Column(name="da_tipo")
	private String daTipo;

	@Column(name="da_tipo_reporte")
	private String daTipoReporte;

	@Column(name="da_tmpo_acumulado_ralenti")
	private Integer daTmpoAcumuladoRalenti;

	@Column(name="da_torque_motor")
	private Integer daTorqueMotor;

	@Column(name="da_torque_retarder")
	private double daTorqueRetarder;

	@Column(name="da_tpo_acumulado")
	private double daTpoAcumulado;

	@Column(name="da_velocidad_can")
	private Integer daVelocidadCan;

	@Column(name="da_volt_bateria")
	private double daVoltBateria;

	@Column(name="dev_id_device")
	private String devIdDevice;

	@Column(name="zon_id_zona")
	private Integer zonIdZona;

	public DatosCanReporte() {
	}

	public DatosCanReportePK getId() {
		return this.id;
	}

	public void setId(DatosCanReportePK id) {
		this.id = id;
	}

	public Integer getDaAcelerador() {
		return this.daAcelerador;
	}

	public void setDaAcelerador(Integer daAcelerador) {
		this.daAcelerador = daAcelerador;
	}

	public double getDaConsumoAcumulado() {
		return this.daConsumoAcumulado;
	}

	public void setDaConsumoAcumulado(double daConsumoAcumulado) {
		this.daConsumoAcumulado = daConsumoAcumulado;
	}

	public Integer getDaCrucero() {
		return this.daCrucero;
	}

	public void setDaCrucero(Integer daCrucero) {
		this.daCrucero = daCrucero;
	}

	public String getDaDireccion() {
		return this.daDireccion;
	}

	public void setDaDireccion(String daDireccion) {
		this.daDireccion = daDireccion;
	}

	public double getDaDistAcumulada() {
		return this.daDistAcumulada;
	}

	public void setDaDistAcumulada(double daDistAcumulada) {
		this.daDistAcumulada = daDistAcumulada;
	}

	public Integer getDaEmbrague() {
		return this.daEmbrague;
	}

	public void setDaEmbrague(Integer daEmbrague) {
		this.daEmbrague = daEmbrague;
	}

	public Timestamp getDaFechaHoraDb() {
		return this.daFechaHoraDb;
	}

	public void setDaFechaHoraDb(Timestamp daFechaHoraDb) {
		this.daFechaHoraDb = daFechaHoraDb;
	}

	public Timestamp getDaFechaInserta() {
		return this.daFechaInserta;
	}

	public void setDaFechaInserta(Timestamp daFechaInserta) {
		this.daFechaInserta = daFechaInserta;
	}

	public String getDaGeocoding() {
		return this.daGeocoding;
	}

	public void setDaGeocoding(String daGeocoding) {
		this.daGeocoding = daGeocoding;
	}

	/*public Object getDaGeom() {
		return this.daGeom;
	}

	public void setDaGeom(Object daGeom) {
		this.daGeom = daGeom;
	}*/

	public double getDaHoroCaiquen() {
		return this.daHoroCaiquen;
	}

	public void setDaHoroCaiquen(double daHoroCaiquen) {
		this.daHoroCaiquen = daHoroCaiquen;
	}

	public double getDaHorometro() {
		return this.daHorometro;
	}

	public void setDaHorometro(double daHorometro) {
		this.daHorometro = daHorometro;
	}

	public Integer getDaId() {
		return this.daId;
	}

	public void setDaId(Integer daId) {
		this.daId = daId;
	}

	public Integer getDaIdConductor() {
		return this.daIdConductor;
	}

	public void setDaIdConductor(Integer daIdConductor) {
		this.daIdConductor = daIdConductor;
	}

	public Integer getDaIdFaena() {
		return this.daIdFaena;
	}

	public void setDaIdFaena(Integer daIdFaena) {
		this.daIdFaena = daIdFaena;
	}

	public String getDaIdentificador() {
		return this.daIdentificador;
	}

	public void setDaIdentificador(String daIdentificador) {
		this.daIdentificador = daIdentificador;
	}

	public String getDaLatitud() {
		return this.daLatitud;
	}

	public void setDaLatitud(String daLatitud) {
		this.daLatitud = daLatitud;
	}

	public Integer getDaLibre1() {
		return this.daLibre1;
	}

	public void setDaLibre1(Integer daLibre1) {
		this.daLibre1 = daLibre1;
	}

	public Integer getDaLibre2() {
		return this.daLibre2;
	}

	public void setDaLibre2(Integer daLibre2) {
		this.daLibre2 = daLibre2;
	}

	public String getDaLongitud() {
		return this.daLongitud;
	}

	public void setDaLongitud(String daLongitud) {
		this.daLongitud = daLongitud;
	}

	public Integer getDaNivelAceite() {
		return this.daNivelAceite;
	}

	public void setDaNivelAceite(Integer daNivelAceite) {
		this.daNivelAceite = daNivelAceite;
	}

	public Integer getDaNivelEstanque() {
		return this.daNivelEstanque;
	}

	public void setDaNivelEstanque(Integer daNivelEstanque) {
		this.daNivelEstanque = daNivelEstanque;
	}

	public double getDaNivelEstanqueAdblue() {
		return this.daNivelEstanqueAdblue;
	}

	public void setDaNivelEstanqueAdblue(double daNivelEstanqueAdblue) {
		this.daNivelEstanqueAdblue = daNivelEstanqueAdblue;
	}

	public Integer getDaNivelRefrigerante() {
		return this.daNivelRefrigerante;
	}

	public void setDaNivelRefrigerante(Integer daNivelRefrigerante) {
		this.daNivelRefrigerante = daNivelRefrigerante;
	}

	public double getDaOdo() {
		return this.daOdo;
	}

	public void setDaOdo(double daOdo) {
		this.daOdo = daOdo;
	}

	public double getDaOlt() {
		return this.daOlt;
	}

	public void setDaOlt(double daOlt) {
		this.daOlt = daOlt;
	}

	public Integer getDaPedalFreno() {
		return this.daPedalFreno;
	}

	public void setDaPedalFreno(Integer daPedalFreno) {
		this.daPedalFreno = daPedalFreno;
	}

	public Integer getDaPresionAceite() {
		return this.daPresionAceite;
	}

	public void setDaPresionAceite(Integer daPresionAceite) {
		this.daPresionAceite = daPresionAceite;
	}

	public double getDaPresionHidraulica() {
		return this.daPresionHidraulica;
	}

	public void setDaPresionHidraulica(double daPresionHidraulica) {
		this.daPresionHidraulica = daPresionHidraulica;
	}

	public double getDaPresionTurbo() {
		return this.daPresionTurbo;
	}

	public void setDaPresionTurbo(double daPresionTurbo) {
		this.daPresionTurbo = daPresionTurbo;
	}

	public Integer getDaPto() {
		return this.daPto;
	}

	public void setDaPto(Integer daPto) {
		this.daPto = daPto;
	}

	public double getDaRalentiAcumulado() {
		return this.daRalentiAcumulado;
	}

	public void setDaRalentiAcumulado(double daRalentiAcumulado) {
		this.daRalentiAcumulado = daRalentiAcumulado;
	}

	public Integer getDaRetarde() {
		return this.daRetarde;
	}

	public void setDaRetarde(Integer daRetarde) {
		this.daRetarde = daRetarde;
	}

	public double getDaRpm() {
		return this.daRpm;
	}

	public void setDaRpm(double daRpm) {
		this.daRpm = daRpm;
	}

	public Integer getDaTempAceite() {
		return this.daTempAceite;
	}

	public void setDaTempAceite(Integer daTempAceite) {
		this.daTempAceite = daTempAceite;
	}

	public Integer getDaTempHidraulico() {
		return this.daTempHidraulico;
	}

	public void setDaTempHidraulico(Integer daTempHidraulico) {
		this.daTempHidraulico = daTempHidraulico;
	}

	public Integer getDaTempRefrigerante() {
		return this.daTempRefrigerante;
	}

	public void setDaTempRefrigerante(Integer daTempRefrigerante) {
		this.daTempRefrigerante = daTempRefrigerante;
	}

	public Integer getDaTemperaturaMotor() {
		return this.daTemperaturaMotor;
	}

	public void setDaTemperaturaMotor(Integer daTemperaturaMotor) {
		this.daTemperaturaMotor = daTemperaturaMotor;
	}

	public String getDaTipo() {
		return this.daTipo;
	}

	public void setDaTipo(String daTipo) {
		this.daTipo = daTipo;
	}

	public String getDaTipoReporte() {
		return this.daTipoReporte;
	}

	public void setDaTipoReporte(String daTipoReporte) {
		this.daTipoReporte = daTipoReporte;
	}

	public Integer getDaTmpoAcumuladoRalenti() {
		return this.daTmpoAcumuladoRalenti;
	}

	public void setDaTmpoAcumuladoRalenti(Integer daTmpoAcumuladoRalenti) {
		this.daTmpoAcumuladoRalenti = daTmpoAcumuladoRalenti;
	}

	public Integer getDaTorqueMotor() {
		return this.daTorqueMotor;
	}

	public void setDaTorqueMotor(Integer daTorqueMotor) {
		this.daTorqueMotor = daTorqueMotor;
	}

	public double getDaTorqueRetarder() {
		return this.daTorqueRetarder;
	}

	public void setDaTorqueRetarder(double daTorqueRetarder) {
		this.daTorqueRetarder = daTorqueRetarder;
	}

	public double getDaTpoAcumulado() {
		return this.daTpoAcumulado;
	}

	public void setDaTpoAcumulado(double daTpoAcumulado) {
		this.daTpoAcumulado = daTpoAcumulado;
	}

	public Integer getDaVelocidadCan() {
		return this.daVelocidadCan;
	}

	public void setDaVelocidadCan(Integer daVelocidadCan) {
		this.daVelocidadCan = daVelocidadCan;
	}

	public double getDaVoltBateria() {
		return this.daVoltBateria;
	}

	public void setDaVoltBateria(double daVoltBateria) {
		this.daVoltBateria = daVoltBateria;
	}

	public String getDevIdDevice() {
		return this.devIdDevice;
	}

	public void setDevIdDevice(String devIdDevice) {
		this.devIdDevice = devIdDevice;
	}

	public Integer getZonIdZona() {
		return this.zonIdZona;
	}

	public void setZonIdZona(Integer zonIdZona) {
		this.zonIdZona = zonIdZona;
	}

}