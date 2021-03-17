package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usurio_perfil database table.
 * 
 */ 
@Entity
@Table(name="usurio_perfil", schema = "cliente")
@NamedQuery(name="UsurioPerfil.findAll", query="SELECT u FROM UsurioPerfil u")
public class UsurioPerfil implements Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(generator = "usurio_perfil_usuper_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "usurio_perfil_usuper_id_seq", sequenceName = "cliente.usurio_perfil_usuper_id_seq",allocationSize=1)
	@Column(name="usuper_id")
	private Integer usuperId;
	
	/*@Id
	@Column(name="usuper_id")
	private Integer usuperId; */

	//bi-directional many-to-one association to Perfil	
	@JoinColumn(name="per_id_perfil")
	private Perfil perfil;

	//bi-directional many-to-one association to Usuario	
	@JoinColumn(name="usu_rut_usuario")
	private Usuario usuario;

	public UsurioPerfil() {
	}

	public Integer getUsuperId() {
		return this.usuperId;
	}

	public void setUsuperId(Integer usuperId) {
		this.usuperId = usuperId;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}