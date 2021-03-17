package cl.samtech.sgomt.object;

import java.sql.Timestamp;

public class ReporteTiempoConduccionActive {
	
	private String patente;
	private String nrointerno;
	private String faena;
	private String identificador;
	private String conductor;
	private Timestamp inicio;
	private Timestamp fin;
	private Double tiempoconduccion;
	private Double timpoignicion;
	private Double tiempomovimiento;
	private Double distancia;
	private String tiempoconduccionS;
	private String timpoignicionS;
	private String tiempomovimientoS;
	private String distanciaS;
	private Double velpro;
	private String ubicacionfinal;
	private String lat;
	private String lon;
	private String enconduccion;
	
	public String getEnconduccion() {
		return enconduccion;
	}
	public void setEnconduccion(String enconduccion) {
		this.enconduccion = enconduccion;
	}
	public String getTiempoconduccionS() {
		return tiempoconduccionS;
	}
	public void setTiempoconduccionS(String tiempoconduccionS) {
		this.tiempoconduccionS = tiempoconduccionS;
	}
	public String getTimpoignicionS() {
		return timpoignicionS;
	}
	public void setTimpoignicionS(String timpoignicionS) {
		this.timpoignicionS = timpoignicionS;
	}
	public String getTiempomovimientoS() {
		return tiempomovimientoS;
	}
	public void setTiempomovimientoS(String tiempomovimientoS) {
		this.tiempomovimientoS = tiempomovimientoS;
	}
	public String getDistanciaS() {
		return distanciaS;
	}
	public void setDistanciaS(String distanciaS) {
		this.distanciaS = distanciaS;
	}
	public String getNrointerno() {
		return nrointerno;
	}
	public void setNrointerno(String nrointerno) {
		this.nrointerno = nrointerno;
	}
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public String getFaena() {
		return faena;
	}
	public void setFaena(String faena) {
		this.faena = faena;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public String getConductor() {
		return conductor;
	}
	public void setConductor(String conductor) {
		this.conductor = conductor;
	}
	public Timestamp getInicio() {
		return inicio;
	}
	public void setInicio(Timestamp inicio) {
		this.inicio = inicio;
	}
	public Timestamp getFin() {
		return fin;
	}
	public void setFin(Timestamp fin) {
		this.fin = fin;
	}
	public Double getTiempoconduccion() {
		return tiempoconduccion;
	}
	public void setTiempoconduccion(Double tiempoconduccion) {
		this.tiempoconduccion = tiempoconduccion;
	}
	public Double getTimpoignicion() {
		return timpoignicion;
	}
	public void setTimpoignicion(Double timpoignicion) {
		this.timpoignicion = timpoignicion;
	}
	public Double getTiempomovimiento() {
		return tiempomovimiento;
	}
	public void setTiempomovimiento(Double tiempomovimiento) {
		this.tiempomovimiento = tiempomovimiento;
	}
	public Double getDistancia() {
		return distancia;
	}
	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}
	public Double getVelpro() {
		return velpro;
	}
	public void setVelpro(Double velpro) {
		this.velpro = velpro;
	}
	public String getUbicacionfinal() {
		return ubicacionfinal;
	}
	public void setUbicacionfinal(String ubicacionfinal) {
		this.ubicacionfinal = ubicacionfinal;
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
