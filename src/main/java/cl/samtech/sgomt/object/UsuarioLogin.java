package cl.samtech.sgomt.object;

import java.util.List;

import javax.persistence.Column;

import cl.samtech.sgomt.model.Menu;
import cl.samtech.sgomt.model.Modulo;
import cl.samtech.sgomt.model.Usuario;

public class UsuarioLogin {
	
	private String rut;
	
	private String username;

	private String apellidos;

	private String correo;

	private String direccion;

	private String nombre;

	private String password;

	private String telefono;
	private Long idRol;

	private String rolDescripcion;
	
	private String adminuser;
	
	private Integer estado;
	
	private String cliente;//razon social
	
	private String clienterut;
	
	private String cliUsuSamtech;

	private String cliPassSamtech;
	
	//perfil
	private String perfilnombre;	
	private Integer perfilid;
		
	private String iduser;
	
	private String urlservlet;
	
	public String getUrlservlet() {
		return urlservlet;
	}

	public void setUrlservlet(String urlservlet) {
		this.urlservlet = urlservlet;
	}

	public String getIduser() {
		return iduser;
	}

	public void setIduser(String iduser) {
		this.iduser = iduser;
	}


	//active
	private List<MenuActive> menus; //para la edicion	
	private List<MenuActive> menusno;// para la edicion
	
	private List<ModuloActive> modulos; // built menu
			
	
	public Integer getPerfilid() {
		return perfilid;
	}

	public void setPerfilid(Integer perfilid) {
		this.perfilid = perfilid;
	}

	public List<MenuActive> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuActive> menus) {
		this.menus = menus;
	}

	public List<MenuActive> getMenusno() {
		return menusno;
	}

	public void setMenusno(List<MenuActive> menusno) {
		this.menusno = menusno;
	}

	public List<ModuloActive> getModulos() {
		return modulos;
	}

	public void setModulos(List<ModuloActive> modulos) {
		this.modulos = modulos;
	}

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

	public String getPerfilnombre() {
		return perfilnombre;
	}

	public void setPerfilnombre(String perfilnombre) {
		this.perfilnombre = perfilnombre;
	}

	public String getClienterut() {
		return clienterut;
	}

	public void setClienterut(String clienterut) {
		this.clienterut = clienterut;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	private String apellido;
	
		
	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getAdminuser() {
		return adminuser;
	}

	public void setAdminuser(String adminuser) {
		this.adminuser = adminuser;
	}

		
	private List<RolusuarioActivo> rolColList;

	public List<RolusuarioActivo> getRolColList() {
		return rolColList;
	}

	public void setRolColList(List<RolusuarioActivo> rolColList) {
		this.rolColList = rolColList;
	} 
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	public String getRolDescripcion() {
		return rolDescripcion;
	}

	public void setRolDescripcion(String rolDescripcion) {
		this.rolDescripcion = rolDescripcion;
	}

}
