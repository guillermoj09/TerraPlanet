package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuario_menu database table.
 * 
 */
@Entity
@Table(name="usuario_menu", schema = "cliente")
@NamedQuery(name="UsuarioMenu.findAll", query="SELECT u FROM UsuarioMenu u")
public class UsuarioMenu implements Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(generator = "usuario_menu_usumen_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "usuario_menu_usumen_id_seq", sequenceName = "cliente.usuario_menu_usumen_id_seq",allocationSize=1)
	@Column(name="usumen_id")
	private Integer usumenId;
	
	/*@Id
	@Column(name="usumen_id")
	private Integer usumenId;*/

	@Column(name="men_id_menu")
	private Integer menIdMenu;

	@Column(name="usu_rut_usuario")
	private String usuRutUsuario;

	public UsuarioMenu() {
	}

	public Integer getUsumenId() {
		return this.usumenId;
	}

	public void setUsumenId(Integer usumenId) {
		this.usumenId = usumenId;
	}

	public Integer getMenIdMenu() {
		return this.menIdMenu;
	}

	public void setMenIdMenu(Integer menIdMenu) {
		this.menIdMenu = menIdMenu;
	}

	public String getUsuRutUsuario() {
		return this.usuRutUsuario;
	}

	public void setUsuRutUsuario(String usuRutUsuario) {
		this.usuRutUsuario = usuRutUsuario;
	}

}