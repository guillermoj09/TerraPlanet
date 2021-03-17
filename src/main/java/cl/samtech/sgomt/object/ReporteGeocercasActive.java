package cl.samtech.sgomt.object;

import java.sql.Timestamp;

public class ReporteGeocercasActive {
	
	private Timestamp fecha_ini;
	private String patente;
	private String nom_zona;
	private String num_interno;
	private String chofer;
	private String faena;
	private Integer velocidad;
	private Integer evento;
	private String nombre_evento;
	private Integer hdg;
	private String lat;
	private String lon;
	private String nom_flecha;
	private String ruta_flecha;
	
	
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
	public String getNom_zona() {
		return nom_zona;
	}
	public void setNom_zona(String nom_zona) {
		this.nom_zona = nom_zona;
	}
	public String getNum_interno() {
		return num_interno;
	}
	public void setNum_interno(String num_interno) {
		this.num_interno = num_interno;
	}
	public String getChofer() {
		return chofer;
	}
	public void setChofer(String chofer) {
		this.chofer = chofer;
	}
	public String getFaena() {
		return faena;
	}
	public void setFaena(String faena) {
		this.faena = faena;
	}
	public Integer getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(Integer velocidad) {
		this.velocidad = velocidad;
	}
	public Integer getEvento() {
		return evento;
	}
	public void setEvento(Integer evento) {
		this.evento = evento;
	}
	public Integer getHdg() {
		return hdg;
	}
	public void setHdg(Integer hdg) {
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
	public String getNombre_evento() {
		return nombre_evento;
	}
	public void setNombre_evento(String nombre_evento) {
		this.nombre_evento = nombre_evento;
	}
	public String getNom_flecha() {
		return nom_flecha;
	}
	public void setNom_flecha(String nom_flecha) {
		this.nom_flecha = nom_flecha;
	}
	public String getRuta_flecha() {
		return ruta_flecha;
	}
	public void setRuta_flecha(String ruta_flecha) {
		this.ruta_flecha = ruta_flecha;
	}
		

}
