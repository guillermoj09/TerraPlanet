package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the usuario_historial database table.
 * 
 */
@Entity
@Table(name="usuario_historial", schema="cliente")
@NamedQuery(name="UsuarioHistorial.findAll", query="SELECT u FROM UsuarioHistorial u")
public class UsuarioHistorial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "usuario_historial_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "usuario_historial_id_seq", sequenceName = "cliente.usuario_historial_id_seq",allocationSize=1)
	private Integer id;

	@Column(name="hora_ingreso")
	private Timestamp horaIngreso;

	//@Column(name="menu_id")
	//private Integer menuId;
	
	@ManyToOne
	@JoinColumn(name="menu_id")
	private Menu menu;

	//bi-directional many-to-one association to UsuarioSession
	@ManyToOne
	@JoinColumn(name="id_usuario_session")
	private UsuarioSession usuarioSession;
	
	@Column(name="url")
	private String url;

	public UsuarioHistorial() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getHoraIngreso() {
		return this.horaIngreso;
	}

	public void setHoraIngreso(Timestamp horaIngreso) {
		this.horaIngreso = horaIngreso;
	}

	/*public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}*/

	public UsuarioSession getUsuarioSession() {
		return this.usuarioSession;
	}

	public void setUsuarioSession(UsuarioSession usuarioSession) {
		this.usuarioSession = usuarioSession;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	

}