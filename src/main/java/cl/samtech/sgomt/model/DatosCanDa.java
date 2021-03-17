package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the datos_can_da database table.
 * 
 */
@Entity
@Table(name="datos_can_da")
@NamedQuery(name="DatosCanDa.findAll", query="SELECT d FROM DatosCanDa d")
public class DatosCanDa implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DatosCanDaPK id;

	@Column(name="da_acelerador")
	private Integer daAcelerador;

	@Column(name="da_consumo_acumulado")
	private double daConsumoAcumulado;

	@Column(name="da_crucero")
	private Integer daCrucero;

	@Column(name="da_dist_acumulada")
	private double daDistAcumulada;

	@Column(name="da_embrague")
	private Integer daEmbrague;

	@Column(name="da_fecha_hora")
	private Timestamp daFechaHora;

	@Column(name="da_fecha_inserta")
	private Timestamp daFechaInserta;

	@Column(name="da_horometro")
	private double daHorometro;

	@Column(name="da_id")
	private Integer daId;

	@Column(name="da_latitud")
	private String daLatitud;

	@Column(name="da_longitud")
	private String daLongitud;

	@Column(name="da_nivel_estanque")
	private Integer daNivelEstanque;

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

	@Column(name="da_torque_motor")
	private Integer daTorqueMotor;

	@Column(name="da_tpo_acumulado")
	private double daTpoAcumulado;

	@Column(name="da_velocidad_can")
	private Integer daVelocidadCan;

	public DatosCanDa() {
	}

	public DatosCanDaPK getId() {
		return this.id;
	}

	public void setId(DatosCanDaPK id) {
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

	public Timestamp getDaFechaHora() {
		return this.daFechaHora;
	}

	public void setDaFechaHora(Timestamp daFechaHora) {
		this.daFechaHora = daFechaHora;
	}

	public Timestamp getDaFechaInserta() {
		return this.daFechaInserta;
	}

	public void setDaFechaInserta(Timestamp daFechaInserta) {
		this.daFechaInserta = daFechaInserta;
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

	public String getDaLatitud() {
		return this.daLatitud;
	}

	public void setDaLatitud(String daLatitud) {
		this.daLatitud = daLatitud;
	}

	public String getDaLongitud() {
		return this.daLongitud;
	}

	public void setDaLongitud(String daLongitud) {
		this.daLongitud = daLongitud;
	}

	public Integer getDaNivelEstanque() {
		return this.daNivelEstanque;
	}

	public void setDaNivelEstanque(Integer daNivelEstanque) {
		this.daNivelEstanque = daNivelEstanque;
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

	public Integer getDaTorqueMotor() {
		return this.daTorqueMotor;
	}

	public void setDaTorqueMotor(Integer daTorqueMotor) {
		this.daTorqueMotor = daTorqueMotor;
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

}