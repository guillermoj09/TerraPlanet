package cl.samtech.sgomt.object;

import java.util.List;
import cl.samtech.sgomt.model.Ibuttom;

public class ConductorActive {
	
	String nombre;
	String apellido;
	String rut;
	String cliRazonSocial;
	String direccion;
	String telefono;
	List<Ibuttom> ibuttoms;
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public List<Ibuttom> getIbuttoms() {
		return ibuttoms;
	}
	public void setIbuttoms(List<Ibuttom> ibuttoms) {
		this.ibuttoms = ibuttoms;
	}
	public String getCliRazonSocial() {
		return cliRazonSocial;
	}
	public void setCliRazonSocial(String cliRazonSocial) {
		this.cliRazonSocial = cliRazonSocial;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	
	

}
