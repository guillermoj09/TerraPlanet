package cl.samtech.sgomt.object;

import java.sql.Timestamp;

public class ReporteTiempoCarguioActive {
	
	private String idcamion;
	private String patente;
	private String nrointerno;
	private String tiempocarguio;
	private String ubicacioncarguio;
	private double cargareal;
	private int pases;
	private String tiempoespera;
	private String patentecamion;
	private String nrointernocamion;
    private Timestamp fechain;
	private Timestamp fechafin;
	private String lat;
	private String lon;
	private int segundos;
	
	public int getSegundos() {
		return segundos;
	}
	public void setSegundos(int segundos) {
		this.segundos = segundos;
	}
	public String getNrointernocamion() {
		return nrointernocamion;
	}
	public void setNrointernocamion(String nrointernocamion) {
		this.nrointernocamion = nrointernocamion;
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
	public String getPatentecamion() {
		return patentecamion;
	}
	public void setPatentecamion(String patentecamion) {
		this.patentecamion = patentecamion;
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
	public Timestamp getFechain() {
		return fechain;
	}
	public void setFechain(Timestamp fechain) {
		this.fechain = fechain;
	}
	public Timestamp getFechafin() {
		return fechafin;
	}
	public void setFechafin(Timestamp fechafin) {
		this.fechafin = fechafin;
	}
	public String getIdcamion() {
		return idcamion;
	}
	public void setIdcamion(String idcamion) {
		this.idcamion = idcamion;
	}
	public String getTiempocarguio() {
		return tiempocarguio;
	}
	public void setTiempocarguio(String tiempocarguio) {
		this.tiempocarguio = tiempocarguio;
	}
	public String getUbicacioncarguio() {
		return ubicacioncarguio;
	}
	public void setUbicacioncarguio(String ubicacioncarguio) {
		this.ubicacioncarguio = ubicacioncarguio;
	}
	public double getCargareal() {
		return cargareal;
	}
	public void setCargareal(double cargareal) {
		this.cargareal = cargareal;
	}
	public int getPases() {
		return pases;
	}
	public void setPases(int pases) {
		this.pases = pases;
	}
	public String getTiempoespera() {
		return tiempoespera;
	}
	public void setTiempoespera(String tiempoespera) {
		this.tiempoespera = tiempoespera;
	}
			
	
		
	
}
