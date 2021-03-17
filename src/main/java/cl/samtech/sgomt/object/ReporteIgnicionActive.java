package cl.samtech.sgomt.object;

import java.sql.Timestamp;

public class ReporteIgnicionActive {

	private Timestamp fecha_ini;
	private String patente;
	private String conductor;
	private int tiempo;
	private String evento;
	private String ubicacion;
	private String lat;
	private String lon;
	private String numinterno;
	private Double distancia;
	private String distancia_str;
	private String tiempo_str;
	
	public Timestamp getFecha_ini() {
		return fecha_ini;
	}
	public void setFecha_ini(Timestamp fecha_ini) {
		this.fecha_ini = fecha_ini;
	}
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public String getConductor() {
		return conductor;
	}
	public void setConductor(String conductor) {
		this.conductor = conductor;
	}
	public int getTiempo() {
		return tiempo;
	}
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
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
	public String getNuminterno() {
		return numinterno;
	}
	public void setNuminterno(String numinterno) {
		this.numinterno = numinterno;
	}
	public Double getDistancia() {
		return distancia;
	}
	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}
	public String getDistancia_str() {
		return distancia_str;
	}
	public void setDistancia_str(String distancia_str) {
		this.distancia_str = distancia_str;
	}
	public String getTiempo_str() {
		return tiempo_str;
	}
	public void setTiempo_str(String tiempo_str) {
		this.tiempo_str = tiempo_str;
	}

}
