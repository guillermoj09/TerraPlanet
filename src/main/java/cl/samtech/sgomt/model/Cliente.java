package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@Table(name = "CLIENTE", schema = "cliente")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cli_rut")
	private String cliRut;

	@Column(name="cli_apellidorl")
	private String cliApellidorl;

	@Column(name="cli_direccion")
	private String cliDireccion;

	@Column(name="cli_fecha_ingresa")
	private Timestamp cliFechaIngresa;

	@Column(name="cli_fono1")
	private String cliFono1;

	@Column(name="cli_fono2")
	private String cliFono2;

	@Column(name="cli_mail")
	private String cliMail;

	@Column(name="cli_nombrerl")
	private String cliNombrerl;

	@Column(name="cli_razon_social")
	private String cliRazonSocial;
	
	@Column(name="cli_usu_samtech")
	private String cliUsuSamtech;

	@Column(name="cli_pass_samtech")
	private String cliPassSamtech;
	
	//bi-directional many-to-one association to Vehiculo
	@OneToMany(mappedBy="cliente")
	private List<Vehiculo> vehiculos;
	
	//bi-directional many-to-one association to Perfil
	@OneToMany(mappedBy="cliente")
	private List<Perfil> perfils;

	//bi-directional many-to-one association to Rol
	@OneToMany(mappedBy="cliente")
	private List<Rol> rols;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="cliente")
	private List<Usuario> usuarios;
	
	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="cliente")
	private List<Tag> tags;
	
	public String getCliUsuSamtech() {
		return cliUsuSamtech;
	}

	public void setCliUsuSamtech(String cliUsuSamtech) {
		this.cliUsuSamtech = cliUsuSamtech;
	}

	public String getCliPassSamtech() {
		return cliPassSamtech;
	}

	public void setCliPassSamtech(String cliPassSamtech) {
		this.cliPassSamtech = cliPassSamtech;
	}


	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Cliente() {
	}

	public String getCliRut() {
		return this.cliRut;
	}

	public void setCliRut(String cliRut) {
		this.cliRut = cliRut;
	}

	public String getCliApellidorl() {
		return this.cliApellidorl;
	}

	public void setCliApellidorl(String cliApellidorl) {
		this.cliApellidorl = cliApellidorl;
	}

	public String getCliDireccion() {
		return this.cliDireccion;
	}

	public void setCliDireccion(String cliDireccion) {
		this.cliDireccion = cliDireccion;
	}

	public Timestamp getCliFechaIngresa() {
		return this.cliFechaIngresa;
	}

	public void setCliFechaIngresa(Timestamp cliFechaIngresa) {
		this.cliFechaIngresa = cliFechaIngresa;
	}

	public String getCliFono1() {
		return this.cliFono1;
	}

	public void setCliFono1(String cliFono1) {
		this.cliFono1 = cliFono1;
	}

	public String getCliFono2() {
		return this.cliFono2;
	}

	public void setCliFono2(String cliFono2) {
		this.cliFono2 = cliFono2;
	}

	public String getCliMail() {
		return this.cliMail;
	}

	public void setCliMail(String cliMail) {
		this.cliMail = cliMail;
	}

	public String getCliNombrerl() {
		return this.cliNombrerl;
	}

	public void setCliNombrerl(String cliNombrerl) {
		this.cliNombrerl = cliNombrerl;
	}

	public String getCliRazonSocial() {
		return this.cliRazonSocial;
	}

	public void setCliRazonSocial(String cliRazonSocial) {
		this.cliRazonSocial = cliRazonSocial;
	}

	public List<Perfil> getPerfils() {
		return this.perfils;
	}

	public void setPerfils(List<Perfil> perfils) {
		this.perfils = perfils;
	}

	public Perfil addPerfil(Perfil perfil) {
		getPerfils().add(perfil);
		perfil.setCliente(this);

		return perfil;
	}

	public Perfil removePerfil(Perfil perfil) {
		getPerfils().remove(perfil);
		perfil.setCliente(null);

		return perfil;
	}

	public List<Rol> getRols() {
		return this.rols;
	}

	public void setRols(List<Rol> rols) {
		this.rols = rols;
	}

	public Rol addRol(Rol rol) {
		getRols().add(rol);
		rol.setCliente(this);

		return rol;
	}

	public Rol removeRol(Rol rol) {
		getRols().remove(rol);
		rol.setCliente(null);

		return rol;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setCliente(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setCliente(null);

		return usuario;
	}

}