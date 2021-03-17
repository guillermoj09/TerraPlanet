package cl.samtech.sgomt.object;

import java.sql.Timestamp;

public class ReporteInformeAlarmaCondActive {
	
	
	private String patente;
	private String alarma; 
	private String ubicacion;
	private String conductor;
	private Timestamp fecha;
	private String vel;
	private String mapa;
	private String lat;
	private String lon;
	
	private String idVehicle;
	private String hdg;
	
	public String getIdVehicle() {
		return idVehicle;
	}
	public void setIdVehicle(String idVehicle) {
		this.idVehicle = idVehicle;
	}
	public String getHdg() {
		return hdg;
	}
	public void setHdg(String hdg) {
		this.hdg = hdg;
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
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public String getAlarma() {
		return alarma;
	}
	public void setAlarma(String alarma) {
		this.alarma = alarma;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getConductor() {
		return conductor;
	}
	public void setConductor(String conductor) {
		this.conductor = conductor;
	}
	
	public Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	public String getVel() {
		return vel;
	}
	public void setVel(String vel) {
		this.vel = vel;
	}
	public String getMapa() {
		return mapa;
	}
	public void setMapa(String mapa) {
		this.mapa = mapa;
	}
	
	
}
