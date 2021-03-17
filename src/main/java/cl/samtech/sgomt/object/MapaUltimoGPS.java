package cl.samtech.sgomt.object;

import java.sql.Timestamp;

import cl.samtech.sgomt.model.Vehiculo;

public class MapaUltimoGPS {
	
	private String lat;
	private String lon;
	private Vehiculo vehiculo;
	private String collapseshow;
	private String classEvent;
	private String velocidad;
	private Timestamp fecha;
	private String rutGeocerca;	
	private String evento;
	
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}
	public String getRutGeocerca() {
		return rutGeocerca;
	}
	public void setRutGeocerca(String rutGeocerca) {
		this.rutGeocerca = rutGeocerca;
	}
	public Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	public String getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(String velocidad) {
		this.velocidad = velocidad;
	}
	public String getClassEvent() {
		return classEvent;
	}
	public void setClassEvent(String classEvent) {
		this.classEvent = classEvent;
	}
	
	public String getCollapseshow() {
		return collapseshow;
	}
	public void setCollapseshow(String collapseshow) {
		this.collapseshow = collapseshow;
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
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	
	

}
