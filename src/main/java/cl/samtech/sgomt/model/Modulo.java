package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the modulo database table.
 * 
 */
@Entity
@Table(name = "MODULO", schema = "cliente")
@NamedQuery(name="Modulo.findAll", query="SELECT m FROM Modulo m")
public class Modulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="mod_id")
	private Integer modId;

	@Column(name="mod_fecha_crea")
	private Timestamp modFechaCrea;

	@Column(name="mod_nombre")
	private String modNombre;

	//bi-directional many-to-one association to Menu
	@OneToMany(mappedBy="modulo")
	private List<Menu> menus;

	//bi-directional many-to-one association to PerfilModulo
	@OneToMany(mappedBy="modulo")
	private List<PerfilModulo> perfilModulos;

	//bi-directional many-to-one association to Rol
	@OneToMany(mappedBy="modulo")
	private List<Rol> rols;

	public Modulo() {
	}

	public Integer getModId() {
		return this.modId;
	}

	public void setModId(Integer modId) {
		this.modId = modId;
	}

	public Timestamp getModFechaCrea() {
		return this.modFechaCrea;
	}

	public void setModFechaCrea(Timestamp modFechaCrea) {
		this.modFechaCrea = modFechaCrea;
	}

	public String getModNombre() {
		return this.modNombre;
	}

	public void setModNombre(String modNombre) {
		this.modNombre = modNombre;
	}

	public List<Menu> getMenus() {
		return this.menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public Menu addMenus(Menu menus) {
		getMenus().add(menus);
		menus.setModulo(this);

		return menus;
	}

	public Menu removeMenus(Menu menus) {
		getMenus().remove(menus);
		menus.setModulo(null);

		return menus;
	}

	public List<PerfilModulo> getPerfilModulos() {
		return this.perfilModulos;
	}

	public void setPerfilModulos(List<PerfilModulo> perfilModulos) {
		this.perfilModulos = perfilModulos;
	}

	public PerfilModulo addPerfilModulo(PerfilModulo perfilModulo) {
		getPerfilModulos().add(perfilModulo);
		perfilModulo.setModulo(this);

		return perfilModulo;
	}

	public PerfilModulo removePerfilModulo(PerfilModulo perfilModulo) {
		getPerfilModulos().remove(perfilModulo);
		perfilModulo.setModulo(null);

		return perfilModulo;
	}

	public List<Rol> getRols() {
		return this.rols;
	}

	public void setRols(List<Rol> rols) {
		this.rols = rols;
	}

	public Rol addRol(Rol rol) {
		getRols().add(rol);
		rol.setModulo(this);

		return rol;
	}

	public Rol removeRol(Rol rol) {
		getRols().remove(rol);
		rol.setModulo(null);

		return rol;
	}

}