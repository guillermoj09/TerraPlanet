package cl.samtech.sgomt.object;

import java.sql.Timestamp;

public class ReporteDescargaActive {
	
	//salida del reporte
	// fecha ini, fecha fin, patente, tiempo de descarga, evento y dirección, latitud, longitud.
		
	private Timestamp fechaini;
	private Timestamp fechafin;
	private String patente;
	private String tiempodescarga;
	private String classEvent;
	private String evento;
	private String direccion;
	private String mapa;
	private String lat;
	private String lon;
	private String nrointerno;
	
	public String getNrointerno() {
		return nrointerno;
	}
	public void setNrointerno(String nrointerno) {
		this.nrointerno = nrointerno;
	}
	public Timestamp getFechaini() {
		return fechaini;
	}
	public void setFechaini(Timestamp fechaini) {
		this.fechaini = fechaini;
	}
	
	public Timestamp getFechafin() {
		return fechafin;
	}
	public void setFechafin(Timestamp fechafin) {
		this.fechafin = fechafin;
	}
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public String getTiempodescarga() {
		return tiempodescarga;
	}
	public void setTiempodescarga(String tiempodescarga) {
		this.tiempodescarga = tiempodescarga;
	}
	public String getClassEvent() {
		return classEvent;
	}
	public void setClassEvent(String classEvent) {
		this.classEvent = classEvent;
	}
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getMapa() {
		return mapa;
	}
	public void setMapa(String mapa) {
		this.mapa = mapa;
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
