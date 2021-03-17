package cl.samtech.sgomt.object;

import java.sql.Timestamp;

public class IbuttomActive {
	
	private Integer id;
	private String codigo;
	private Integer estado;
	private Timestamp fecha_crea;
	private String rut_usuario;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public Timestamp getFecha_crea() {
		return fecha_crea;
	}
	public void setFecha_crea(Timestamp fecha_crea) {
		this.fecha_crea = fecha_crea;
	}
	public String getRut_usuario() {
		return rut_usuario;
	}
	public void setRut_usuario(String rut_usuario) {
		this.rut_usuario = rut_usuario;
	}
	
	

}
