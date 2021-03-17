package cl.samtech.sgomt.object;

import java.sql.Timestamp;

public class ReporteHorometroActive {
	
	private String patente;
	private String nrointerno;
	private String marca;
	private Timestamp fecha;
	private double horometro;
	private double horas;
	private Integer estado;
	
	private Timestamp fecha_fin;
	private String modelo;
	private double horometro_fin;
	private Integer tipo_equipo;
	private String tipo_equipo_str;
	
	private String tipo_horometro;
	
	private String horometro_str;
	private String horas_str;
	private String horometro_fin_str;
		
	public String getHorometro_str() {
		return horometro_str;
	}
	public void setHorometro_str(String horometro_str) {
		this.horometro_str = horometro_str;
	}
	public String getHoras_str() {
		return horas_str;
	}
	public void setHoras_str(String horas_str) {
		this.horas_str = horas_str;
	}
	public String getHorometro_fin_str() {
		return horometro_fin_str;
	}
	public void setHorometro_fin_str(String horometro_fin_str) {
		this.horometro_fin_str = horometro_fin_str;
	}
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public String getNrointerno() {
		return nrointerno;
	}
	public void setNrointerno(String nrointerno) {
		this.nrointerno = nrointerno;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	public double getHorometro() {
		return horometro;
	}
	public void setHorometro(double horometro) {
		this.horometro = horometro;
	}
	public double getHoras() {
		return horas;
	}
	public void setHoras(double horas) {
		this.horas = horas;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
	
	
	public Timestamp getFecha_fin() {
		return fecha_fin;
	}
	public void setFecha_fin(Timestamp fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public double getHorometro_fin() {
		return horometro_fin;
	}
	public void setHorometro_fin(double horometro_fin) {
		this.horometro_fin = horometro_fin;
	}
	public Integer getTipo_equipo() {
		return tipo_equipo;
	}
	public void setTipo_equipo(Integer tipo_equipo) {
		this.tipo_equipo = tipo_equipo;
	}
	public String getTipo_equipo_str() {
		return tipo_equipo_str;
	}
	public void setTipo_equipo_str(String tipo_equipo_str) {
		this.tipo_equipo_str = tipo_equipo_str;
	}
	
	
	public String getTipo_horometro() {
		return tipo_horometro;
	}
	public void setTipo_horometro(String tipo_horometro) {
		this.tipo_horometro = tipo_horometro;
	}
	
	

}
