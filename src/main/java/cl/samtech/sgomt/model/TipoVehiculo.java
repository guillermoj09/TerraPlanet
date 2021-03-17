package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;


/**
 * The persistent class for the tipo_vehiculo database table.
 * 
 */
@Entity
@Table(name="tipo_vehiculo", schema="dispositivo")
@NamedQuery(name="TipoVehiculo.findAll", query="SELECT t FROM TipoVehiculo t")
public class TipoVehiculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="tipv_id")
	private Integer tipvId;

	@Column(name="cli_rut_cliente")
	private String cliRutCliente;

	@Column(name="tipv_descripcion")
	private String tipvDescripcion;
	
	@Column(name="tiv_ruta_icono")
	private String tivRutaIcono;
	
	@Column(name="tipo")
	private String tipo;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTivRutaIcono() {
		return tivRutaIcono;
	}

	public void setTivRutaIcono(String tivRutaIcono) {
		this.tivRutaIcono = tivRutaIcono;
	}

	//bi-directional many-to-one association to Vehiculo
	@JsonIgnore
	@OneToMany(mappedBy="tipoVehiculo")
	//@JsonManagedReference	
	private List<Vehiculo> vehiculos;

	public TipoVehiculo() {
	}

	public Integer getTipvId() {
		return this.tipvId;
	}

	public void setTipvId(Integer tipvId) {
		this.tipvId = tipvId;
	}

	public String getCliRutCliente() {
		return this.cliRutCliente;
	}

	public void setCliRutCliente(String cliRutCliente) {
		this.cliRutCliente = cliRutCliente;
	}

	public String getTipvDescripcion() {
		return this.tipvDescripcion;
	}

	public void setTipvDescripcion(String tipvDescripcion) {
		this.tipvDescripcion = tipvDescripcion;
	}

	public List<Vehiculo> getVehiculos() {
		return this.vehiculos;
	}

	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public Vehiculo addVehiculo(Vehiculo vehiculo) {
		getVehiculos().add(vehiculo);
		vehiculo.setTipoVehiculo(this);

		return vehiculo;
	}

	public Vehiculo removeVehiculo(Vehiculo vehiculo) {
		getVehiculos().remove(vehiculo);
		vehiculo.setTipoVehiculo(null);

		return vehiculo;
	}

}