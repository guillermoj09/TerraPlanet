package cl.samtech.sgomt.object;

import java.sql.Timestamp;

public class ReportetInformeralentiCanActive {
	
	private String patente;
	private String conductor;
	private String nrInt;
	private String ubicacion;
	private Timestamp fechainicio;
	private Timestamp fechafin;
	private String duracionralenti;
	private Double combutil;
	private String combutilS;
	private String lat;
	private String lon;
		
	public String getCombutilS() {
		return combutilS;
	}
	public void setCombutilS(String combutilS) {
		this.combutilS = combutilS;
	}
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public Double getCombutil() {
		return combutil;
	}
	public void setCombutil(Double combutil) {
		this.combutil = combutil;
	}
	private String modelo;
	private String marca;
	
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getConductor() {
		return conductor;
	}
	public void setConductor(String conductor) {
		this.conductor = conductor;
	}
	public String getNrInt() {
		return nrInt;
	}
	public void setNrInt(String nrInt) {
		this.nrInt = nrInt;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Timestamp getFechainicio() {
		return fechainicio;
	}
	public void setFechainicio(Timestamp fechainicio) {
		this.fechainicio = fechainicio;
	}
	public Timestamp getFechafin() {
		return fechafin;
	}
	public void setFechafin(Timestamp fechafin) {
		this.fechafin = fechafin;
	}
	public String getDuracionralenti() {
		return duracionralenti;
	}
	public void setDuracionralenti(String duracionralenti) {
		this.duracionralenti = duracionralenti;
	}
	
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	
	

}
