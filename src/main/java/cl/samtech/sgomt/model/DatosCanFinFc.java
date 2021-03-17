package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the datos_can_fin_fc database table.
 * 
 */
@Entity
@Table(name="datos_can_fin_fc")
@NamedQuery(name="DatosCanFinFc.findAll", query="SELECT d FROM DatosCanFinFc d")
public class DatosCanFinFc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="fincf_id")
	private Integer fincfId;

	@Column(name="finfc_activacion_cc")
	private double finfcActivacionCc;

	@Column(name="finfc_activacion_retarder")
	private Integer finfcActivacionRetarder;

	@Column(name="finfc_fecha_hora_fc")
	private Timestamp finfcFechaHoraFc;

	@Column(name="finfc_pisadas_embrague")
	private Integer finfcPisadasEmbrague;

	@Column(name="finfc_pisadas_freno")
	private Integer finfcPisadasFreno;

	//bi-directional many-to-one association to DatosCanFinFa
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="fin_energizacion_datos_can_fin_fa", referencedColumnName="fin_energizacion"),
		@JoinColumn(name="fin_gps_datos_can_fin_fa", referencedColumnName="fin_gps"),
		@JoinColumn(name="fin_identificador_datos_can_fin_fa", referencedColumnName="fin_identificador"),
		@JoinColumn(name="fin_partida_datos_can_fin_fa", referencedColumnName="fin_partida")
		})
	private DatosCanFinFa datosCanFinFa;

	public DatosCanFinFc() {
	}

	public Integer getFincfId() {
		return this.fincfId;
	}

	public void setFincfId(Integer fincfId) {
		this.fincfId = fincfId;
	}

	public double getFinfcActivacionCc() {
		return this.finfcActivacionCc;
	}

	public void setFinfcActivacionCc(double finfcActivacionCc) {
		this.finfcActivacionCc = finfcActivacionCc;
	}

	public Integer getFinfcActivacionRetarder() {
		return this.finfcActivacionRetarder;
	}

	public void setFinfcActivacionRetarder(Integer finfcActivacionRetarder) {
		this.finfcActivacionRetarder = finfcActivacionRetarder;
	}

	public Timestamp getFinfcFechaHoraFc() {
		return this.finfcFechaHoraFc;
	}

	public void setFinfcFechaHoraFc(Timestamp finfcFechaHoraFc) {
		this.finfcFechaHoraFc = finfcFechaHoraFc;
	}

	public Integer getFinfcPisadasEmbrague() {
		return this.finfcPisadasEmbrague;
	}

	public void setFinfcPisadasEmbrague(Integer finfcPisadasEmbrague) {
		this.finfcPisadasEmbrague = finfcPisadasEmbrague;
	}

	public Integer getFinfcPisadasFreno() {
		return this.finfcPisadasFreno;
	}

	public void setFinfcPisadasFreno(Integer finfcPisadasFreno) {
		this.finfcPisadasFreno = finfcPisadasFreno;
	}

	public DatosCanFinFa getDatosCanFinFa() {
		return this.datosCanFinFa;
	}

	public void setDatosCanFinFa(DatosCanFinFa datosCanFinFa) {
		this.datosCanFinFa = datosCanFinFa;
	}

}