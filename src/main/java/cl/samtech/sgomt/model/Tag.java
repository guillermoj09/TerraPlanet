package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tag database table.
 * 
 */
@Entity
@Table(name="tag", schema="dispositivo")
@NamedQuery(name="Tag.findAll", query="SELECT t FROM Tag t")
public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(columnDefinition = "serial", name="tag_id")
	@Column(name="tag_id")
	private String tagId;

	@Column(name="tag_fecha_ingresa")
	private Timestamp tagFechaIngresa;

	@Column(name="tag_marca")
	private String tagMarca;

	@Column(name="tag_modelo")
	private String tagModelo;

	@Column(name="tag_serie")
	private String tagSerie;
	
	//bi-directional many-to-many association to Usuario
	@ManyToMany(mappedBy="tags", cascade = CascadeType.MERGE)
	private List<Vehiculo> vehiculos;
	
	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="cli_rut_cliente")
	private Cliente cliente;

	//bi-directional many-to-one association to VehiculoTag
	//@OneToMany(mappedBy="tag")
	//private List<VehiculoTag> vehiculoTags;
	
	/*public List<VehiculoTag> getVehiculoTags() {
		return vehiculoTags;
	}

	public void setVehiculoTags(List<VehiculoTag> vehiculoTags) {
		this.vehiculoTags = vehiculoTags;
	}*/

	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Tag() {
	}

	public String getTagId() {
		return this.tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public Timestamp getTagFechaIngresa() {
		return this.tagFechaIngresa;
	}

	public void setTagFechaIngresa(Timestamp tagFechaIngresa) {
		this.tagFechaIngresa = tagFechaIngresa;
	}

	public String getTagMarca() {
		return this.tagMarca;
	}

	public void setTagMarca(String tagMarca) {
		this.tagMarca = tagMarca;
	}

	public String getTagModelo() {
		return this.tagModelo;
	}

	public void setTagModelo(String tagModelo) {
		this.tagModelo = tagModelo;
	}

	public String getTagSerie() {
		return this.tagSerie;
	}

	public void setTagSerie(String tagSerie) {
		this.tagSerie = tagSerie;
	}
	

}