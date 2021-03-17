package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the grupo_vehiculo database table.
 * 
 */
@Entity
@Table(name="grupo_vehiculo",schema = "cliente")
@NamedQuery(name="GrupoVehiculo.findAll", query="SELECT g FROM GrupoVehiculo g")
public class GrupoVehiculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="gruveh_id")
	private Integer gruvehId;

	@Column(name="gruveh_fecha_fin")
	private Timestamp gruvehFechaFin;

	@Column(name="gruveh_fecha_ini")
	private Timestamp gruvehFechaIni;

	@Column(name="veh_patente_vehiculo")
	private String vehPatenteVehiculo;

	//bi-directional many-to-one association to Grupo
	@ManyToOne
	@JoinColumn(name="gru_id_grupo")
	private Grupo grupo;

	public GrupoVehiculo() {
	}

	public Integer getGruvehId() {
		return this.gruvehId;
	}

	public void setGruvehId(Integer gruvehId) {
		this.gruvehId = gruvehId;
	}

	public Timestamp getGruvehFechaFin() {
		return this.gruvehFechaFin;
	}

	public void setGruvehFechaFin(Timestamp gruvehFechaFin) {
		this.gruvehFechaFin = gruvehFechaFin;
	}

	public Timestamp getGruvehFechaIni() {
		return this.gruvehFechaIni;
	}

	public void setGruvehFechaIni(Timestamp gruvehFechaIni) {
		this.gruvehFechaIni = gruvehFechaIni;
	}

	public String getVehPatenteVehiculo() {
		return this.vehPatenteVehiculo;
	}

	public void setVehPatenteVehiculo(String vehPatenteVehiculo) {
		this.vehPatenteVehiculo = vehPatenteVehiculo;
	}

	public Grupo getGrupo() {
		return this.grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

}