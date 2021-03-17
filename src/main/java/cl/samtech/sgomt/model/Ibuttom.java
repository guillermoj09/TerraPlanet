package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the ibuttom database table.
 * 
 */
@Entity
@Table(name = "ibuttom", schema = "cliente")
@NamedQuery(name="Ibuttom.findAll", query="SELECT i FROM Ibuttom i")
public class Ibuttom implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ibu_codigo")
	private String ibuCodigo;

	@Column(name="ibu_estado")
	private Integer ibuEstado;

	@Column(name="ibu_fecha_crea")
	private Timestamp ibuFechaCrea;

	@Column(name="ibu_id")
	private Integer ibuId;

	/*@Column(name="usu_rut_usuario")
	private String usuRutUsuario;*/
	
	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usu_rut_usuario")
	private Usuario usuario;

	//bi-directional many-to-one association to IbuttomConductor
	/*@OneToMany(mappedBy="ibuttom")
	private List<IbuttomConductor> ibuttomConductors;*/
	
	@JoinTable(schema="cliente",
			name="ibuttom_conductor"
			, joinColumns={
					@JoinColumn(name="ibu_id_ibuttom", referencedColumnName="ibu_codigo")
				}
			, inverseJoinColumns={
				@JoinColumn(name="cond_rut_conductor", referencedColumnName="cond_rut")
				}
			)
	private List<Conductor> conductores;	

	public Ibuttom() {
	}
		
	public List<Conductor> getConductores() {
		return conductores;
	}

	public void setConductores(List<Conductor> conductores) {
		this.conductores = conductores;
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public String getIbuCodigo() {
		return this.ibuCodigo;
	}

	public void setIbuCodigo(String ibuCodigo) {
		this.ibuCodigo = ibuCodigo;
	}

	public Integer getIbuEstado() {
		return this.ibuEstado;
	}

	public void setIbuEstado(Integer ibuEstado) {
		this.ibuEstado = ibuEstado;
	}

	public Timestamp getIbuFechaCrea() {
		return this.ibuFechaCrea;
	}

	public void setIbuFechaCrea(Timestamp ibuFechaCrea) {
		this.ibuFechaCrea = ibuFechaCrea;
	}

	public Integer getIbuId() {
		return this.ibuId;
	}

	public void setIbuId(Integer ibuId) {
		this.ibuId = ibuId;
	}

	/*public String getUsuRutUsuario() {
		return this.usuRutUsuario;
	}

	public void setUsuRutUsuario(String usuRutUsuario) {
		this.usuRutUsuario = usuRutUsuario;
	}*/

	/*public List<IbuttomConductor> getIbuttomConductors() {
		return this.ibuttomConductors;
	}

	public void setIbuttomConductors(List<IbuttomConductor> ibuttomConductors) {
		this.ibuttomConductors = ibuttomConductors;
	}

	public IbuttomConductor addIbuttomConductor(IbuttomConductor ibuttomConductor) {
		getIbuttomConductors().add(ibuttomConductor);
		ibuttomConductor.setIbuttom(this);

		return ibuttomConductor;
	}

	public IbuttomConductor removeIbuttomConductor(IbuttomConductor ibuttomConductor) {
		getIbuttomConductors().remove(ibuttomConductor);
		ibuttomConductor.setIbuttom(null);

		return ibuttomConductor;
	}*/

}