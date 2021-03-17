package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the rol database table.
 * 
 */
@Entity
@Table(name = "ROL", schema = "cliente")
@NamedQuery(name="Rol.findAll", query="SELECT r FROM Rol r")
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="rol_id")
	private Integer rolId;

	@Column(name="rol_nombre")
	private String rolNombre;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="cli_rut_cliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Modulo
	@ManyToOne
	@JoinColumn(name="mod_id_modulo")
	private Modulo modulo;

	//bi-directional many-to-one association to UruarioRol
	@OneToMany(mappedBy="rol")
	private List<UruarioRol> uruarioRols;

	public Rol() {
	}

	public Integer getRolId() {
		return this.rolId;
	}

	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}

	public String getRolNombre() {
		return this.rolNombre;
	}

	public void setRolNombre(String rolNombre) {
		this.rolNombre = rolNombre;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Modulo getModulo() {
		return this.modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public List<UruarioRol> getUruarioRols() {
		return this.uruarioRols;
	}

	public void setUruarioRols(List<UruarioRol> uruarioRols) {
		this.uruarioRols = uruarioRols;
	}

	public UruarioRol addUruarioRol(UruarioRol uruarioRol) {
		getUruarioRols().add(uruarioRol);
		uruarioRol.setRol(this);

		return uruarioRol;
	}

	public UruarioRol removeUruarioRol(UruarioRol uruarioRol) {
		getUruarioRols().remove(uruarioRol);
		uruarioRol.setRol(null);

		return uruarioRol;
	}

}