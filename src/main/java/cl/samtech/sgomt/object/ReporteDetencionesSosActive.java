package cl.samtech.sgomt.object;

import java.sql.Timestamp;

public class ReporteDetencionesSosActive {
	
	private String patente;
	private String conductor;
	private Timestamp fecha;
	private String hora;
	private String ubicacion;
	private String tiempoDetenido;
	private String velocidad;
	private String evento;
	private String orientacion;
	private String mapa;
	private String lat;
	private String lon;
	private String nomflecha;
	private String rutaflecha; 
	private String classEvent;	
	private String idVehicle;
	private String nombreve;
	private String idEvent;
	private String hdg;
	
	
	
	
	public String getHdg() {
		return hdg;
	}
	public void setHdg(String hdg) {
		this.hdg = hdg;
	}
	public String getIdEvent() {
		return idEvent;
	}
	public void setIdEvent(String idEvent) {
		this.idEvent = idEvent;
	}
	public String getNombreve() {
		return nombreve;
	}
	public void setNombreve(String nombreve) {
		this.nombreve = nombreve;
	}
	public String getIdVehicle() {
		return idVehicle;
	}
	public void setIdVehicle(String idVehicle) {
		this.idVehicle = idVehicle;
	}
	public Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	public String getClassEvent() {
		return classEvent;
	}
	public void setClassEvent(String classEvent) {
		this.classEvent = classEvent;
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
	public String getNomflecha() {
		return nomflecha;
	}
	public void setNomflecha(String nomflecha) {
		this.nomflecha = nomflecha;
	}
	public String getRutaflecha() {
		return rutaflecha;
	}
	public void setRutaflecha(String rutaflecha) {
		this.rutaflecha = rutaflecha;
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
	
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getTiempoDetenido() {
		return tiempoDetenido;
	}
	public void setTiempoDetenido(String tiempoDetenido) {
		this.tiempoDetenido = tiempoDetenido;
	}
	public String getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(String velocidad) {
		this.velocidad = velocidad;
	}
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}
	public String getOrientacion() {
		return orientacion;
	}
	public void setOrientacion(String orientacion) {
		this.orientacion = orientacion;
	}
	public String getMapa() {
		return mapa;
	}
	public void setMapa(String mapa) {
		this.mapa = mapa;
	}
	

	

}
