package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the ibuttom_conductor database table.
 * 
 */
@Entity
@Table(name="ibuttom_conductor", schema = "cliente")
@NamedQuery(name="IbuttomConductor.findAll", query="SELECT i FROM IbuttomConductor i")
public class IbuttomConductor implements Serializable {
	private static final long serialVersionUID = 1L;

	//ibuttom_conductor_ibucond_id_seq
	@Id
	@GeneratedValue(generator = "ibuttom_conductor_ibucond_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "ibuttom_conductor_ibucond_id_seq", sequenceName = "cliente.ibuttom_conductor_ibucond_id_seq",allocationSize=1)
	@Column(name="ibucond_id")
	private Integer ibucondId;

	@Column(name="cond_rut_conductor")
	private String condRutConductor;

	@Column(name="ibucond_estado")
	private Integer ibucondEstado;

	@Column(name="ibucond_fecha_asignacion")
	private Timestamp ibucondFechaAsignacion;

	@Column(name="ibu_id_ibuttom")
	private String ibuIdIbuttom;

	public String getIbuIdIbuttom() {
		return ibuIdIbuttom;
	}

	public void setIbuIdIbuttom(String ibuIdIbuttom) {
		this.ibuIdIbuttom = ibuIdIbuttom;
	}

	public IbuttomConductor() {
	}

	public Integer getIbucondId() {
		return this.ibucondId;
	}

	public void setIbucondId(Integer ibucondId) {
		this.ibucondId = ibucondId;
	}

	public String getCondRutConductor() {
		return this.condRutConductor;
	}

	public void setCondRutConductor(String condRutConductor) {
		this.condRutConductor = condRutConductor;
	}

	public Integer getIbucondEstado() {
		return this.ibucondEstado;
	}

	public void setIbucondEstado(Integer ibucondEstado) {
		//this.ibucondEstado = 1;
		this.ibucondEstado = ibucondEstado;
	}

	public Timestamp getIbucondFechaAsignacion() {
		return this.ibucondFechaAsignacion;
	}

	public void setIbucondFechaAsignacion(Timestamp ibucondFechaAsignacion) {
		//Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		//timestamp = ibucondFechaAsignacion;
		this.ibucondFechaAsignacion = ibucondFechaAsignacion;
	}

	

}