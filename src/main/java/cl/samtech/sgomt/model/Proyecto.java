package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the proyecto database table.
 * 
 */
@Entity
@Table(name = "PROYECTO", schema = "cliente")
@NamedQuery(name="Proyecto.findAll", query="SELECT p FROM Proyecto p")
public class Proyecto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="py_id")
	private Integer pyId;

	@Column(name="py_descripcion")
	private String pyDescripcion;

	@Column(name="py_fecha_crea")
	private Timestamp pyFechaCrea;

	@Column(name="py_nombre")
	private String pyNombre;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usu_rut_usuario")
	private Usuario usuario;

	//bi-directional many-to-one association to ProyectoGrupo
	@OneToMany(mappedBy="proyecto")
	private List<ProyectoGrupo> proyectoGrupos;

	public Proyecto() {
	}

	public Integer getPyId() {
		return this.pyId;
	}

	public void setPyId(Integer pyId) {
		this.pyId = pyId;
	}

	public String getPyDescripcion() {
		return this.pyDescripcion;
	}

	public void setPyDescripcion(String pyDescripcion) {
		this.pyDescripcion = pyDescripcion;
	}

	public Timestamp getPyFechaCrea() {
		return this.pyFechaCrea;
	}

	public void setPyFechaCrea(Timestamp pyFechaCrea) {
		this.pyFechaCrea = pyFechaCrea;
	}

	public String getPyNombre() {
		return this.pyNombre;
	}

	public void setPyNombre(String pyNombre) {
		this.pyNombre = pyNombre;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<ProyectoGrupo> getProyectoGrupos() {
		return this.proyectoGrupos;
	}

	public void setProyectoGrupos(List<ProyectoGrupo> proyectoGrupos) {
		this.proyectoGrupos = proyectoGrupos;
	}

	public ProyectoGrupo addProyectoGrupo(ProyectoGrupo proyectoGrupo) {
		getProyectoGrupos().add(proyectoGrupo);
		proyectoGrupo.setProyecto(this);

		return proyectoGrupo;
	}

	public ProyectoGrupo removeProyectoGrupo(ProyectoGrupo proyectoGrupo) {
		getProyectoGrupos().remove(proyectoGrupo);
		proyectoGrupo.setProyecto(null);

		return proyectoGrupo;
	}

}