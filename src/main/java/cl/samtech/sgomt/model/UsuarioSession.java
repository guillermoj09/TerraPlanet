package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the usuario_session database table.
 * 
 */
@Entity
@Table(name="usuario_session", schema="cliente")
@NamedQuery(name="UsuarioSession.findAll", query="SELECT u FROM UsuarioSession u")
public class UsuarioSession implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "usuario_session_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "usuario_session_id_seq", sequenceName = "cliente.usuario_session_id_seq",allocationSize=1)
	private Integer id;

	@Column(name="cli_rut_cliente")
	private String cliRutCliente;

	private Timestamp ingreso;

	private Timestamp salida;

	@Column(name="usu_login")
	private String usuLogin;

	@Column(name="usu_rut")
	private String usuRut;
	
	@Column(name="ip")
	private String ip;
	
	@Column(name="sistema_operativo")
	private String sistemaOperativo;
	
	@Column(name="navegador")
	private String navegador;
	
	@Column(name="dispositivo")
	private String dispositivo;
	
	//bi-directional many-to-one association to UsuarioHistorial
	@OneToMany(mappedBy="usuarioSession", cascade = 
	        CascadeType.PERSIST, 
	        fetch = FetchType.EAGER )
	private List<UsuarioHistorial> usuarioHistorials;

	public UsuarioSession() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCliRutCliente() {
		return this.cliRutCliente;
	}

	public void setCliRutCliente(String cliRutCliente) {
		this.cliRutCliente = cliRutCliente;
	}

	public Timestamp getIngreso() {
		return this.ingreso;
	}

	public void setIngreso(Timestamp ingreso) {
		this.ingreso = ingreso;
	}

	public Timestamp getSalida() {
		return this.salida;
	}

	public void setSalida(Timestamp salida) {
		this.salida = salida;
	}

	public String getUsuLogin() {
		return this.usuLogin;
	}

	public void setUsuLogin(String usuLogin) {
		this.usuLogin = usuLogin;
	}

	public String getUsuRut() {
		return this.usuRut;
	}

	public void setUsuRut(String usuRut) {
		this.usuRut = usuRut;
	}

	public List<UsuarioHistorial> getUsuarioHistorials() {
		return this.usuarioHistorials;
	}

	public void setUsuarioHistorials(List<UsuarioHistorial> usuarioHistorials) {
		this.usuarioHistorials = usuarioHistorials;
	}

	public UsuarioHistorial addUsuarioHistorial(UsuarioHistorial usuarioHistorial) {
		getUsuarioHistorials().add(usuarioHistorial);
		usuarioHistorial.setUsuarioSession(this);

		return usuarioHistorial;
	}

	public UsuarioHistorial removeUsuarioHistorial(UsuarioHistorial usuarioHistorial) {
		getUsuarioHistorials().remove(usuarioHistorial);
		usuarioHistorial.setUsuarioSession(null);

		return usuarioHistorial;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSistemaOperativo() {
		return sistemaOperativo;
	}

	public void setSistemaOperativo(String sistemaOperativo) {
		this.sistemaOperativo = sistemaOperativo;
	}

	public String getNavegador() {
		return navegador;
	}

	public void setNavegador(String navegador) {
		this.navegador = navegador;
	}

	public String getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
	}
		

}