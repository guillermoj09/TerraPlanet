package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the uruario_rol database table.
 * 
 */
@Entity
@Table(name="uruario_rol", schema = "cliente")
@NamedQuery(name="UruarioRol.findAll", query="SELECT u FROM UruarioRol u")
public class UruarioRol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="usurol_id")
	private Integer usurolId;

	//bi-directional many-to-one association to Rol
	@ManyToOne
	@JoinColumn(name="rol_id_rol")
	private Rol rol;

	public UruarioRol() {
	}

	public Integer getUsurolId() {
		return this.usurolId;
	}

	public void setUsurolId(Integer usurolId) {
		this.usurolId = usurolId;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

}