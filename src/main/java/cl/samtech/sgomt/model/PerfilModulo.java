package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the perfil_modulo database table.
 * 
 */
@Entity
@Table(name="perfil_modulo", schema = "cliente")
@NamedQuery(name="PerfilModulo.findAll", query="SELECT p FROM PerfilModulo p")
public class PerfilModulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="usumod_id")
	private Integer usumodId;

	//bi-directional many-to-one association to Modulo
	@ManyToOne
	@JoinColumn(name="mod_id_modulo")
	private Modulo modulo;

	//bi-directional many-to-one association to Perfil
	@ManyToOne
	@JoinColumn(name="per_id_perfil")
	private Perfil perfil;

	public PerfilModulo() {
	}

	public Integer getUsumodId() {
		return this.usumodId;
	}

	public void setUsumodId(Integer usumodId) {
		this.usumodId = usumodId;
	}

	public Modulo getModulo() {
		return this.modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}