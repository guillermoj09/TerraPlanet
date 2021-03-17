package cl.samtech.sgomt.object;

import java.sql.Timestamp;

import cl.samtech.sgomt.model.Vehiculo;

public class ReporteResumenDescargaActive {
	
	private String patente;
	private Timestamp fecha;
	private Integer vueltas;
	private Integer produccion;
	private String dia;
	private String mes;
	private String ano;
	
	//private Vehiculo vehiculo;
	
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	
	/*public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}*/
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	public Integer getVueltas() {
		return vueltas;
	}
	public void setVueltas(Integer vueltas) {
		this.vueltas = vueltas;
	}
	public Integer getProduccion() {
		return produccion;
	}
	public void setProduccion(Integer produccion) {
		this.produccion = produccion;
	}
	
	

}
