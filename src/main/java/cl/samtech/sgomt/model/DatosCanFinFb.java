package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the datos_can_fin_fb database table.
 * 
 */
@Entity
@Table(name="datos_can_fin_fb")
@NamedQuery(name="DatosCanFinFb.findAll", query="SELECT d FROM DatosCanFinFb d")
public class DatosCanFinFb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="finfb_id")
	private Integer finfbId;

	@Column(name="finfb_cons_comb_marcha")
	private double finfbConsCombMarcha;

	@Column(name="finfb_consm_cc_activado")
	private double finfbConsmCcActivado;

	@Column(name="finfb_consu_comb_ralenti")
	private double finfbConsuCombRalenti;

	@Column(name="finfb_dist_crucero")
	private double finfbDistCrucero;

	@Column(name="finfb_fecha_hora_fb")
	private Timestamp finfbFechaHoraFb;

	@Column(name="finfb_tmpo_control_crucero")
	private Integer finfbTmpoControlCrucero;

	@Column(name="finfb_tmpo_ralenti")
	private Integer finfbTmpoRalenti;

	@Column(name="finfb_tmpo_veleo")
	private Integer finfbTmpoVeleo;

	//bi-directional many-to-one association to DatosCanFinFa
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="fin_energizacion_datos_can_fin_fa", referencedColumnName="fin_energizacion"),
		@JoinColumn(name="fin_gps_datos_can_fin_fa", referencedColumnName="fin_gps"),
		@JoinColumn(name="fin_identificador_datos_can_fin_fa", referencedColumnName="fin_identificador"),
		@JoinColumn(name="fin_partida_datos_can_fin_fa", referencedColumnName="fin_partida")
		})
	private DatosCanFinFa datosCanFinFa;

	public DatosCanFinFb() {
	}

	public Integer getFinfbId() {
		return this.finfbId;
	}

	public void setFinfbId(Integer finfbId) {
		this.finfbId = finfbId;
	}

	public double getFinfbConsCombMarcha() {
		return this.finfbConsCombMarcha;
	}

	public void setFinfbConsCombMarcha(double finfbConsCombMarcha) {
		this.finfbConsCombMarcha = finfbConsCombMarcha;
	}

	public double getFinfbConsmCcActivado() {
		return this.finfbConsmCcActivado;
	}

	public void setFinfbConsmCcActivado(double finfbConsmCcActivado) {
		this.finfbConsmCcActivado = finfbConsmCcActivado;
	}

	public double getFinfbConsuCombRalenti() {
		return this.finfbConsuCombRalenti;
	}

	public void setFinfbConsuCombRalenti(double finfbConsuCombRalenti) {
		this.finfbConsuCombRalenti = finfbConsuCombRalenti;
	}

	public double getFinfbDistCrucero() {
		return this.finfbDistCrucero;
	}

	public void setFinfbDistCrucero(double finfbDistCrucero) {
		this.finfbDistCrucero = finfbDistCrucero;
	}

	public Timestamp getFinfbFechaHoraFb() {
		return this.finfbFechaHoraFb;
	}

	public void setFinfbFechaHoraFb(Timestamp finfbFechaHoraFb) {
		this.finfbFechaHoraFb = finfbFechaHoraFb;
	}

	public Integer getFinfbTmpoControlCrucero() {
		return this.finfbTmpoControlCrucero;
	}

	public void setFinfbTmpoControlCrucero(Integer finfbTmpoControlCrucero) {
		this.finfbTmpoControlCrucero = finfbTmpoControlCrucero;
	}

	public Integer getFinfbTmpoRalenti() {
		return this.finfbTmpoRalenti;
	}

	public void setFinfbTmpoRalenti(Integer finfbTmpoRalenti) {
		this.finfbTmpoRalenti = finfbTmpoRalenti;
	}

	public Integer getFinfbTmpoVeleo() {
		return this.finfbTmpoVeleo;
	}

	public void setFinfbTmpoVeleo(Integer finfbTmpoVeleo) {
		this.finfbTmpoVeleo = finfbTmpoVeleo;
	}

	public DatosCanFinFa getDatosCanFinFa() {
		return this.datosCanFinFa;
	}

	public void setDatosCanFinFa(DatosCanFinFa datosCanFinFa) {
		this.datosCanFinFa = datosCanFinFa;
	}

}