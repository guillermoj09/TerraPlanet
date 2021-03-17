package cl.samtech.sgomt.object;

import java.sql.Timestamp;

import cl.samtech.sgomt.model.Vehiculo;

public class MapaUltimoGPSJson {
	
	private String lat;
	private String lon;	
	private String classEvent;
	private String velocidad;
	private Timestamp fecha;
	private String fechaS;
	private String rutGeocerca;	
	private String evento;
	
	//datos vehiculos
	private String patente;
	private String tipoVehiculo;
	private Integer idTipoVehiculo;
	private String nroInterno;
	private String marca;
	
	//gps
	private String estado;
	private String descestado;		
	
	//db
	private String estadodb;
	private String descestadodb;		
	
	private Timestamp fechadb;
	private String fechaSdb;
	
	private String icon;
	private String tipo;
	
	//fecha ultima actualizacion ajax
	private Timestamp fechaAjax;
	private String fechaAjaxS;
	
	
	public Timestamp getFechaAjax() {
		return fechaAjax;
	}
	public void setFechaAjax(Timestamp fechaAjax) {
		this.fechaAjax = fechaAjax;
	}
	public String getFechaAjaxS() {
		return fechaAjaxS;
	}
	public void setFechaAjaxS(String fechaAjaxS) {
		this.fechaAjaxS = fechaAjaxS;
	}
	public String getEstadodb() {
		return estadodb;
	}
	public void setEstadodb(String estadodb) {
		this.estadodb = estadodb;
	}
	public String getDescestadodb() {
		return descestadodb;
	}
	public void setDescestadodb(String descestadodb) {
		this.descestadodb = descestadodb;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Timestamp getFechadb() {
		return fechadb;
	}
	public void setFechadb(Timestamp fechadb) {
		this.fechadb = fechadb;
	}
	public String getFechaSdb() {
		return fechaSdb;
	}
	public void setFechaSdb(String fechaSdb) {
		this.fechaSdb = fechaSdb;
	}
	public String getDescestado() {
		return descestado;
	}
	public void setDescestado(String descestado) {
		this.descestado = descestado;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getFechaS() {
		return fechaS;
	}
	public void setFechaS(String fechaS) {
		this.fechaS = fechaS;
	}
	public Integer getIdTipoVehiculo() {
		return idTipoVehiculo;
	}
	public void setIdTipoVehiculo(Integer idTipoVehiculo) {
		this.idTipoVehiculo = idTipoVehiculo;
	}
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public String getTipoVehiculo() {
		return tipoVehiculo;
	}
	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	public String getNroInterno() {
		return nroInterno;
	}
	public void setNroInterno(String nroInterno) {
		this.nroInterno = nroInterno;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
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
