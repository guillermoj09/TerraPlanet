package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the perfil database table.
 * 
 */
@Entity
@Table(name = "PERFIL", schema = "cliente")
@NamedQuery(name="Perfil.findAll", query="SELECT p FROM Perfil p")
public class Perfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="per_id")
	private Integer perId;

	@Column(name="per_nombre")
	private String perNombre;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="cli_rut_cliente")
	private Cliente cliente;

	//bi-directional many-to-one association to PerfilModulo
	@OneToMany(mappedBy="perfil")
	private List<PerfilModulo> perfilModulos;

	//bi-directional many-to-one association to UsurioPerfil
	/*@OneToMany(mappedBy="perfil")
	private List<UsurioPerfil> usurioPerfils;*/
	
	@JoinTable(schema="cliente",
			name="usurio_perfil"
			, joinColumns={
				@JoinColumn(name="per_id_perfil", referencedColumnName="per_id") 
				}
			, inverseJoinColumns={
				@JoinColumn(name="usu_rut_usuario", referencedColumnName="usu_rut")
				}
			)
	private List<Usuario> usuarios;	

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Perfil() {
	}

	public Integer getPerId() {
		return this.perId;
	}

	public void setPerId(Integer perId) {
		this.perId = perId;
	}

	public String getPerNombre() {
		return this.perNombre;
	}

	public void setPerNombre(String perNombre) {
		this.perNombre = perNombre;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<PerfilModulo> getPerfilModulos() {
		return this.perfilModulos;
	}

	public void setPerfilModulos(List<PerfilModulo> perfilModulos) {
		this.perfilModulos = perfilModulos;
	}

	public PerfilModulo addPerfilModulo(PerfilModulo perfilModulo) {
		getPerfilModulos().add(perfilModulo);
		perfilModulo.setPerfil(this);

		return perfilModulo;
	}

	public PerfilModulo removePerfilModulo(PerfilModulo perfilModulo) {
		getPerfilModulos().remove(perfilModulo);
		perfilModulo.setPerfil(null);

		return perfilModulo;
	}

	/*public List<UsurioPerfil> getUsurioPerfils() {
		return this.usurioPerfils;
	}

	public void setUsurioPerfils(List<UsurioPerfil> usurioPerfils) {
		this.usurioPerfils = usurioPerfils;
	}*/

	/*public UsurioPerfil addUsurioPerfil(UsurioPerfil usurioPerfil) {
		getUsurioPerfils().add(usurioPerfil);
		usurioPerfil.setPerfil(this);

		return usurioPerfil;
	}

	public UsurioPerfil removeUsurioPerfil(UsurioPerfil usurioPerfil) {
		getUsurioPerfils().remove(usurioPerfil);
		usurioPerfil.setPerfil(null);

		return usurioPerfil;
	}*/

}