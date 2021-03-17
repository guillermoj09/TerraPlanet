package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the proyecto_grupo database table.
 * 
 */
@Entity
@Table(name="proyecto_grupo", schema = "cliente")
@NamedQuery(name="ProyectoGrupo.findAll", query="SELECT p FROM ProyectoGrupo p")
public class ProyectoGrupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pygru_id")
	private Integer pygruId;

	@Column(name="pygru_fecha_fin")
	private Timestamp pygruFechaFin;

	@Column(name="pygru_fecha_ini")
	private Timestamp pygruFechaIni;

	//bi-directional many-to-one association to Grupo
	@ManyToOne
	@JoinColumn(name="gru_id_grupo")
	private Grupo grupo;

	//bi-directional many-to-one association to Proyecto
	@ManyToOne
	@JoinColumn(name="py_id_proyecto")
	private Proyecto proyecto;

	public ProyectoGrupo() {
	}

	public Integer getPygruId() {
		return this.pygruId;
	}

	public void setPygruId(Integer pygruId) {
		this.pygruId = pygruId;
	}

	public Timestamp getPygruFechaFin() {
		return this.pygruFechaFin;
	}

	public void setPygruFechaFin(Timestamp pygruFechaFin) {
		this.pygruFechaFin = pygruFechaFin;
	}

	public Timestamp getPygruFechaIni() {
		return this.pygruFechaIni;
	}

	public void setPygruFechaIni(Timestamp pygruFechaIni) {
		this.pygruFechaIni = pygruFechaIni;
	}

	public Grupo getGrupo() {
		return this.grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Proyecto getProyecto() {
		return this.proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

}