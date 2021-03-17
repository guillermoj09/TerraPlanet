package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name = "USUARIO", schema = "cliente")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="usu_rut")
	private String usuRut;

	@Column(name="usu_apellido")
	private String usuApellido;

	@Column(name="usu_clave")
	private String usuClave;

	@Column(name="usu_direccion")
	private String usuDireccion;

	@Column(name="usu_fecha_ingresa")
	private Timestamp usuFechaIngresa;

	@Column(name="usu_login")
	private String usuLogin;

	@Column(name="usu_mail")
	private String usuMail;

	@Column(name="usu_nombre")
	private String usuNombre;
	
	@Column(name="usu_estado")
	private Integer usuEstado;

	//bi-directional many-to-one association to Conductor
	@OneToMany(mappedBy="usuario")
	private List<Conductor> conductors;

	//bi-directional many-to-one association to Proyecto
	@OneToMany(mappedBy="usuario")
	private List<Proyecto> proyectos;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="cli_rut_cliente")
	private Cliente cliente;

	//bi-directional many-to-one association to UsurioPerfil NO
	/*@OneToMany(mappedBy="usuario", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<UsurioPerfil> usurioPerfils;*/

	//bi-directional many-to-one association to Zona
	@OneToMany(mappedBy="usuario")
	private List<Zona> zonas;
	
	//bi-directional many-to-many association to Usuario
	//@ManyToMany(mappedBy="usuarios", cascade = CascadeType.MERGE)
	//private List<Menu> menus;
	
	//bi-directional many-to-many association to Tag
		@ManyToMany(cascade = 
		        CascadeType.PERSIST, 
		        fetch = FetchType.EAGER
		        )
		@JoinTable(schema="cliente",
				name="usuario_menu"
				, joinColumns={
					@JoinColumn(name="usu_rut_usuario", referencedColumnName="usu_rut") 
					}
				, inverseJoinColumns={
					@JoinColumn(name="men_id_menu", referencedColumnName="men_id") 
					}
				)
		private List<Menu> menus;
	
	//bi-directional many-to-many association to Usuario
	//@ManyToMany(mappedBy="usuarios", cascade = CascadeType.MERGE)
		@ManyToMany(cascade = 
		        CascadeType.PERSIST, 
		        fetch = FetchType.EAGER
		        )
		@JoinTable(schema="dispositivo",
				name="usuario_vehiculo"
				, joinColumns={
					@JoinColumn(name="usu_rut_usuario", referencedColumnName="usu_rut") 
					}
				, inverseJoinColumns={
					@JoinColumn(name="veh_patente_vehiculo", referencedColumnName="veh_patente") 
					}
				)	
	private List<Vehiculo> vehiculos;
	

	//bi-directional many-to-many association to Tag
	@ManyToMany(cascade = 
			        CascadeType.PERSIST, 
			        fetch = FetchType.EAGER
			        )
	@JoinTable(schema="cliente",
					name="usurio_perfil"
					, joinColumns={
						@JoinColumn(name="usu_rut_usuario", referencedColumnName="usu_rut") 
						}
					, inverseJoinColumns={
						@JoinColumn(name="per_id_perfil", referencedColumnName="per_id") 
						}
					)
	private List<Perfil> perfiles;

	public List<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public Usuario() {
	}

	public String getUsuRut() {
		return this.usuRut;
	}

	public void setUsuRut(String usuRut) {
		this.usuRut = usuRut;
	}

	public String getUsuApellido() {
		return this.usuApellido;
	}

	public void setUsuApellido(String usuApellido) {
		this.usuApellido = usuApellido;
	}

	public String getUsuClave() {
		return this.usuClave;
	}

	public void setUsuClave(String usuClave) {
		this.usuClave = usuClave;
	}

	public String getUsuDireccion() {
		return this.usuDireccion;
	}

	public void setUsuDireccion(String usuDireccion) {
		this.usuDireccion = usuDireccion;
	}

	public Timestamp getUsuFechaIngresa() {
		return this.usuFechaIngresa;
	}

	public void setUsuFechaIngresa(Timestamp usuFechaIngresa) {
		this.usuFechaIngresa = usuFechaIngresa;
	}

	public String getUsuLogin() {
		return this.usuLogin;
	}

	public void setUsuLogin(String usuLogin) {
		this.usuLogin = usuLogin;
	}

	public String getUsuMail() {
		return this.usuMail;
	}

	public void setUsuMail(String usuMail) {
		this.usuMail = usuMail;
	}

	public String getUsuNombre() {
		return this.usuNombre;
	}

	public void setUsuNombre(String usuNombre) {
		this.usuNombre = usuNombre;
	}

	public List<Conductor> getConductors() {
		return this.conductors;
	}

	public void setConductors(List<Conductor> conductors) {
		this.conductors = conductors;
	}

	public Conductor addConductor(Conductor conductor) {
		getConductors().add(conductor);
		conductor.setUsuario(this);

		return conductor;
	}

	public Conductor removeConductor(Conductor conductor) {
		getConductors().remove(conductor);
		conductor.setUsuario(null);

		return conductor;
	}

	public List<Proyecto> getProyectos() {
		return this.proyectos;
	}

	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	public Proyecto addProyecto(Proyecto proyecto) {
		getProyectos().add(proyecto);
		proyecto.setUsuario(this);

		return proyecto;
	}

	public Proyecto removeProyecto(Proyecto proyecto) {
		getProyectos().remove(proyecto);
		proyecto.setUsuario(null);

		return proyecto;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/*public List<UsurioPerfil> getUsurioPerfils() {
		return this.usurioPerfils;
	}

	public void setUsurioPerfils(List<UsurioPerfil> usurioPerfils) {
		this.usurioPerfils = usurioPerfils;
	}*/

	/*public UsurioPerfil addUsurioPerfil(UsurioPerfil usurioPerfil) {
		getUsurioPerfils().add(usurioPerfil);
		usurioPerfil.setUsuario(this);

		return usurioPerfil;
	}

	public UsurioPerfil removeUsurioPerfil(UsurioPerfil usurioPerfil) {
		getUsurioPerfils().remove(usurioPerfil);
		usurioPerfil.setUsuario(null);

		return usurioPerfil;
	}*/

	public List<Zona> getZonas() {
		return this.zonas;
	}

	public void setZonas(List<Zona> zonas) {
		this.zonas = zonas;
	}

	public Zona addZona(Zona zona) {
		getZonas().add(zona);
		zona.setUsuario(this);

		return zona;
	}

	public Zona removeZona(Zona zona) {
		getZonas().remove(zona);
		zona.setUsuario(null);

		return zona;
	}
	
	public Integer getUsuEstado() {
		return usuEstado;
	}

	public void setUsuEstado(Integer usuEstado) {
		this.usuEstado = usuEstado;
	}

}