package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the vehiculo database table.
 * 
 */
@Entity
@Table(name="vehiculo", schema="dispositivo")
@NamedQuery(name="Vehiculo.findAll", query="SELECT v FROM Vehiculo v")
public class Vehiculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id	
	@Column(name="veh_patente")
	private String vehPatente;

	@Column(name="veh_anio")
	private String vehAnio;

	@Column(name="veh_carga_soportada")
	private double vehCargaSoportada;

	@Column(name="veh_carga_volumen")
	private Double vehCargaVolumen;

	@Column(name="veh_fecha_inserta")
	private Timestamp vehFechaInserta;

	@Column(name="veh_id")
	private Integer vehId;

	@Column(name="veh_marca")
	private String vehMarca;

	@Column(name="veh_modelo")
	private String vehModelo;

	@Column(name="veh_motor")
	private String vehMotor;

	@Column(name="veh_vin")
	private String vehVin;
	
	@Column(name="veh_num_interno")
	private String vehNumInterno;
	
	@Column(name="veh_color")
	private String vehColor;
	
	@Column(name="veh_peso_bruto")
	private Double vehPesoBruto;
	
	@Column(name="veh_capacidad_balde")
	private Double vehCapacidadBalde;
	
	@Column(name="veh_peso_operacional")
	private Double vehPesoOperacional;
	
	@Column(name="veh_nivel_estanque")
	private Double vehNivelEstanque;
	
	@Column(name="veh_capacidad_balde_ton")
	private Double vehCapacidadBaldeTon;

	//bi-directional many-to-one association to ConductorVehiculo
	@OneToMany(mappedBy="vehiculo")
	private List<ConductorVehiculo> conductorVehiculos;
	
	//bi-directional many-to-one association to TipoVehiculo
	@JsonIgnore
	@ManyToOne	
	//@JsonManagedReference	
	@JoinColumn(name="tipv_id_tipo_vehiculo")
	private TipoVehiculo tipoVehiculo;
	
	
	@ManyToOne
	@JoinColumn(name="cli_rut_cliente")
	private Cliente cliente;
	

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	//bi-directional many-to-many association to Tag
	@ManyToMany(cascade = 
	        CascadeType.PERSIST, 
	        fetch = FetchType.EAGER
	        )
	@JoinTable(schema="dispositivo",
			name="vehiculo_tag"
			, joinColumns={
				@JoinColumn(name="veh_patente_vehiculo", referencedColumnName="veh_patente") // veh_patente_vehiculo
				}
			, inverseJoinColumns={
				@JoinColumn(name="tag_id_tag", referencedColumnName="tag_id")  // tag_id_tag
				}
			)
	private List<Tag> tags;
	
	//bi-directional many-to-many association to Tag
		@ManyToMany(cascade = 
		        CascadeType.PERSIST, 
		        fetch = FetchType.EAGER
		        )
		@JoinTable(schema="dispositivo",
				name="usuario_vehiculo"
				, joinColumns={
					@JoinColumn(name="veh_patente_vehiculo", referencedColumnName="veh_patente") //veh_patente  //   veh_patente_vehiculo
					}
				, inverseJoinColumns={
					@JoinColumn(name="usu_rut_usuario", referencedColumnName="usu_rut") // usu_rut  // usu_rut_usuario
					}
				)
	private List<Usuario> usuarios;
		
		//bi-directional many-to-many association to Tag
		@ManyToMany(cascade = 
		        CascadeType.PERSIST, 
		        fetch = FetchType.EAGER
		        )
		@JoinTable( schema="dispositivo",
				name="vehiculo_device"
				, joinColumns={
					@JoinColumn(name="veh_patente_vehiculo", referencedColumnName="veh_patente") //veh_patente_vehiculo veh_patente
					}
				, inverseJoinColumns={
					@JoinColumn(name="dev_id_device", referencedColumnName="dev_id")
					}
				)
	private List<Device> devices;
		
	

	public Double getVehCargaVolumen() {
			return vehCargaVolumen;
		}

		public void setVehCargaVolumen(Double vehCargaVolumen) {
			this.vehCargaVolumen = vehCargaVolumen;
		}

	public Double getVehPesoBruto() {
			return vehPesoBruto;
		}

		public void setVehPesoBruto(Double vehPesoBruto) {
			this.vehPesoBruto = vehPesoBruto;
		}

		public Double getVehCapacidadBalde() {
			return vehCapacidadBalde;
		}

		public void setVehCapacidadBalde(Double vehCapacidadBalde) {
			this.vehCapacidadBalde = vehCapacidadBalde;
		}

		public Double getVehPesoOperacional() {
			return vehPesoOperacional;
		}

		public void setVehPesoOperacional(Double vehPesoOperacional) {
			this.vehPesoOperacional = vehPesoOperacional;
		}

		public Double getVehNivelEstanque() {
			return vehNivelEstanque;
		}

		public void setVehNivelEstanque(Double vehNivelEstanque) {
			this.vehNivelEstanque = vehNivelEstanque;
		}

		public Double getVehCapacidadBaldeTon() {
			return vehCapacidadBaldeTon;
		}

		public void setVehCapacidadBaldeTon(Double vehCapacidadBaldeTon) {
			this.vehCapacidadBaldeTon = vehCapacidadBaldeTon;
		}

	public String getVehNumInterno() {
		return vehNumInterno;
	}

	public void setVehNumInterno(String vehNumInterno) {
		this.vehNumInterno = vehNumInterno;
	}

	public String getVehColor() {
		return vehColor;
	}

	public void setVehColor(String vehColor) {
		this.vehColor = vehColor;
	}
	
	public List<Tag> getTags() {
		return tags;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Vehiculo() {
	}

	public String getVehPatente() {
		return this.vehPatente;
	}

	public void setVehPatente(String vehPatente) {
		this.vehPatente = vehPatente;
	}

	public String getVehAnio() {
		return this.vehAnio;
	}

	public void setVehAnio(String vehAnio) {
		this.vehAnio = vehAnio;
	}

	public double getVehCargaSoportada() {
		return this.vehCargaSoportada;
	}

	public void setVehCargaSoportada(double vehCargaSoportada) {
		this.vehCargaSoportada = vehCargaSoportada;
	}

	

	public Timestamp getVehFechaInserta() {
		return this.vehFechaInserta;
	}

	public void setVehFechaInserta(Timestamp vehFechaInserta) {
		this.vehFechaInserta = vehFechaInserta;
	}

	public Integer getVehId() {
		return this.vehId;
	}

	public void setVehId(Integer vehId) {
		this.vehId = vehId;
	}

	public String getVehMarca() {
		return this.vehMarca;
	}

	public void setVehMarca(String vehMarca) {
		this.vehMarca = vehMarca;
	}

	public String getVehModelo() {
		return this.vehModelo;
	}

	public void setVehModelo(String vehModelo) {
		this.vehModelo = vehModelo;
	}

	public String getVehMotor() {
		return this.vehMotor;
	}

	public void setVehMotor(String vehMotor) {
		this.vehMotor = vehMotor;
	}

	public String getVehVin() {
		return this.vehVin;
	}

	public void setVehVin(String vehVin) {
		this.vehVin = vehVin;
	}

	public List<ConductorVehiculo> getConductorVehiculos() {
		return this.conductorVehiculos;
	}

	public void setConductorVehiculos(List<ConductorVehiculo> conductorVehiculos) {
		this.conductorVehiculos = conductorVehiculos;
	}

	public ConductorVehiculo addConductorVehiculo(ConductorVehiculo conductorVehiculo) {
		getConductorVehiculos().add(conductorVehiculo);
		conductorVehiculo.setVehiculo(this);

		return conductorVehiculo;
	}

	public ConductorVehiculo removeConductorVehiculo(ConductorVehiculo conductorVehiculo) {
		getConductorVehiculos().remove(conductorVehiculo);
		conductorVehiculo.setVehiculo(null);

		return conductorVehiculo;
	}

	
	public TipoVehiculo getTipoVehiculo() {
		return this.tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	}