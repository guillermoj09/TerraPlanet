package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;

import com.vividsolutions.jts.geom.Geometry;

import java.util.List;


/**
 * The persistent class for the grupo database table.
 * 
 */
@Entity
@Table(name = "GRUPO", schema = "cliente")
@NamedQuery(name="Grupo.findAll", query="SELECT g FROM Grupo g")
public class Grupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="gru_id")
	private Integer gruId;

	@Column(name="gru_color")
	private String gruColor;

	@Column(name="gru_geom")
	private Geometry gruGeom;

	@Column(name="gru_nombre")
	private String gruNombre;

	//bi-directional many-to-one association to GrupoVehiculo
	@OneToMany(mappedBy="grupo")
	private List<GrupoVehiculo> grupoVehiculos;

	//bi-directional many-to-one association to ProyectoGrupo
	@OneToMany(mappedBy="grupo")
	private List<ProyectoGrupo> proyectoGrupos;

	public Grupo() {
	}

	public Integer getGruId() {
		return this.gruId;
	}

	public void setGruId(Integer gruId) {
		this.gruId = gruId;
	}

	public String getGruColor() {
		return this.gruColor;
	}

	public void setGruColor(String gruColor) {
		this.gruColor = gruColor;
	}

	/*public Object getGruGeom() {
		return this.gruGeom;
	}

	public void setGruGeom(Object gruGeom) {
		this.gruGeom = gruGeom;
	}*/

	public String getGruNombre() {
		return this.gruNombre;
	}

	public void setGruNombre(String gruNombre) {
		this.gruNombre = gruNombre;
	}

	public List<GrupoVehiculo> getGrupoVehiculos() {
		return this.grupoVehiculos;
	}

	public void setGrupoVehiculos(List<GrupoVehiculo> grupoVehiculos) {
		this.grupoVehiculos = grupoVehiculos;
	}

	public GrupoVehiculo addGrupoVehiculo(GrupoVehiculo grupoVehiculo) {
		getGrupoVehiculos().add(grupoVehiculo);
		grupoVehiculo.setGrupo(this);

		return grupoVehiculo;
	}

	public GrupoVehiculo removeGrupoVehiculo(GrupoVehiculo grupoVehiculo) {
		getGrupoVehiculos().remove(grupoVehiculo);
		grupoVehiculo.setGrupo(null);

		return grupoVehiculo;
	}

	public List<ProyectoGrupo> getProyectoGrupos() {
		return this.proyectoGrupos;
	}

	public void setProyectoGrupos(List<ProyectoGrupo> proyectoGrupos) {
		this.proyectoGrupos = proyectoGrupos;
	}

	public ProyectoGrupo addProyectoGrupo(ProyectoGrupo proyectoGrupo) {
		getProyectoGrupos().add(proyectoGrupo);
		proyectoGrupo.setGrupo(this);

		return proyectoGrupo;
	}

	public ProyectoGrupo removeProyectoGrupo(ProyectoGrupo proyectoGrupo) {
		getProyectoGrupos().remove(proyectoGrupo);
		proyectoGrupo.setGrupo(null);

		return proyectoGrupo;
	}

}