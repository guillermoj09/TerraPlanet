package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the conductor database table.
 * 
 */
@Entity
@Table(name = "CONDUCTOR", schema = "cliente")
@NamedQuery(name="Conductor.findAll", query="SELECT c FROM Conductor c")
public class Conductor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cond_rut")
	private String condRut;

	@Column(name="cond_apellido")
	private String condApellido;

	@Column(name="cond_direccion")
	private String condDireccion;

	@Column(name="cond_fecha_crea")
	private Timestamp condFechaCrea;

	@Column(name="cond_fono")
	private String condFono;

	@Column(name="cond_nombre")
	private String condNombre;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usu_rut_usuario")
	private Usuario usuario;
	
	//bi-directional many-to-many association to Tag
			/*@ManyToMany(cascade = 
			        CascadeType.PERSIST, 
			        fetch = FetchType.EAGER
			        )*/
			@JoinTable(schema="cliente",
					name="ibuttom_conductor"
					, joinColumns={
						@JoinColumn(name="cond_rut_conductor", referencedColumnName="cond_rut") 
						}
					, inverseJoinColumns={
						@JoinColumn(name="ibu_id_ibuttom", referencedColumnName="ibu_codigo") 
						}
					)
	private List<Ibuttom> ibuttoms;
		

	public Conductor() {
	}
	
	
	public List<Ibuttom> getIbuttoms() {
		return ibuttoms;
	}



	public void setIbuttoms(List<Ibuttom> ibuttoms) {
		this.ibuttoms = ibuttoms;
	}



	public String getCondRut() {
		return this.condRut;
	}

	public void setCondRut(String condRut) {
		this.condRut = condRut;
	}

	public String getCondApellido() {
		return this.condApellido;
	}

	public void setCondApellido(String condApellido) {
		this.condApellido = condApellido;
	}

	public String getCondDireccion() {
		return this.condDireccion;
	}

	public void setCondDireccion(String condDireccion) {
		this.condDireccion = condDireccion;
	}

	public Timestamp getCondFechaCrea() {
		return this.condFechaCrea;
	}

	public void setCondFechaCrea(Timestamp condFechaCrea) {
		this.condFechaCrea = condFechaCrea;
	}

	public String getCondFono() {
		return this.condFono;
	}

	public void setCondFono(String condFono) {
		this.condFono = condFono;
	}

	public String getCondNombre() {
		return this.condNombre;
	}

	public void setCondNombre(String condNombre) {
		this.condNombre = condNombre;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}