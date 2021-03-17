package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the datos_can_fin_fd database table.
 * 
 */
@Entity
@Table(name="datos_can_fin_fd")
@NamedQuery(name="DatosCanFinFd.findAll", query="SELECT d FROM DatosCanFinFd d")
public class DatosCanFinFd implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="finfd_id")
	private Integer finfdId;

	@Column(name="finfd_consumo_pto")
	private Integer finfdConsumoPto;

	@Column(name="finfd_fecha_hora_fd")
	private Timestamp finfdFechaHoraFd;

	@Column(name="finfd_horo_caiquen")
	private double finfdHoroCaiquen;

	@Column(name="finfd_nivel_estanque_adblue")
	private Integer finfdNivelEstanqueAdblue;

	@Column(name="finfd_tiempo_pto")
	private Integer finfdTiempoPto;

	//bi-directional many-to-one association to DatosCanFinFa
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="fin_energizacion_datos_can_fin_fa", referencedColumnName="fin_energizacion"),
		@JoinColumn(name="fin_gps_datos_can_fin_fa", referencedColumnName="fin_gps"),
		@JoinColumn(name="fin_identificador_datos_can_fin_fa", referencedColumnName="fin_identificador"),
		@JoinColumn(name="fin_partida_datos_can_fin_fa", referencedColumnName="fin_partida")
		})
	private DatosCanFinFa datosCanFinFa;

	public DatosCanFinFd() {
	}

	public Integer getFinfdId() {
		return this.finfdId;
	}

	public void setFinfdId(Integer finfdId) {
		this.finfdId = finfdId;
	}

	public Integer getFinfdConsumoPto() {
		return this.finfdConsumoPto;
	}

	public void setFinfdConsumoPto(Integer finfdConsumoPto) {
		this.finfdConsumoPto = finfdConsumoPto;
	}

	public Timestamp getFinfdFechaHoraFd() {
		return this.finfdFechaHoraFd;
	}

	public void setFinfdFechaHoraFd(Timestamp finfdFechaHoraFd) {
		this.finfdFechaHoraFd = finfdFechaHoraFd;
	}

	public double getFinfdHoroCaiquen() {
		return this.finfdHoroCaiquen;
	}

	public void setFinfdHoroCaiquen(double finfdHoroCaiquen) {
		this.finfdHoroCaiquen = finfdHoroCaiquen;
	}

	public Integer getFinfdNivelEstanqueAdblue() {
		return this.finfdNivelEstanqueAdblue;
	}

	public void setFinfdNivelEstanqueAdblue(Integer finfdNivelEstanqueAdblue) {
		this.finfdNivelEstanqueAdblue = finfdNivelEstanqueAdblue;
	}

	public Integer getFinfdTiempoPto() {
		return this.finfdTiempoPto;
	}

	public void setFinfdTiempoPto(Integer finfdTiempoPto) {
		this.finfdTiempoPto = finfdTiempoPto;
	}

	public DatosCanFinFa getDatosCanFinFa() {
		return this.datosCanFinFa;
	}

	public void setDatosCanFinFa(DatosCanFinFa datosCanFinFa) {
		this.datosCanFinFa = datosCanFinFa;
	}

}