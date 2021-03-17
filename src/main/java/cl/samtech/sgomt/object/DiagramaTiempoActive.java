package cl.samtech.sgomt.object;

import java.sql.Timestamp;

public class DiagramaTiempoActive {
	
	private String idcamion;
	private String patente;
	private String nrointerno;
	private String tiemporuta;
	private	 String tiempofueraproyecto;
	private	 String tiempotaller;	
	private	 String tiempogeo;	
	private String tiempolatencia;
	private String ubicacioncarguio;
	private Timestamp fechain;
	private Timestamp fechafin;
	private int segundos;
	private double horasfueraproyecto;
	private double horastaller;
	private double horasgeo;
	private double horasruta;
	private double horaslatencia;
	
	public double getHoraslatencia() {
		return horaslatencia;
	}
	public void setHoraslatencia(double horaslatencia) {
		this.horaslatencia = horaslatencia;
	}
	public String getTiempolatencia() {
		return tiempolatencia;
	}
	public void setTiempolatencia(String tiempolatencia) {
		this.tiempolatencia = tiempolatencia;
	}
	public double getHorasfueraproyecto() {
		return horasfueraproyecto;
	}
	public void setHorasfueraproyecto(double horasfueraproyecto) {
		this.horasfueraproyecto = horasfueraproyecto;
	}
	public double getHorastaller() {
		return horastaller;
	}
	public void setHorastaller(double horastaller) {
		this.horastaller = horastaller;
	}
	public double getHorasgeo() {
		return horasgeo;
	}
	public void setHorasgeo(double horasgeo) {
		this.horasgeo = horasgeo;
	}
	public double getHorasruta() {
		return horasruta;
	}
	public void setHorasruta(double horasruta) {
		this.horasruta = horasruta;
	}
	public String getTiempogeo() {
		return tiempogeo;
	}
	public void setTiempogeo(String tiempogeo) {
		this.tiempogeo = tiempogeo;
	}
	public String getIdcamion() {
		return idcamion;
	}
	public void setIdcamion(String idcamion) {
		this.idcamion = idcamion;
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
	public String getTiemporuta() {
		return tiemporuta;
	}
	public void setTiemporuta(String tiemporuta) {
		this.tiemporuta = tiemporuta;
	}
	public String getTiempofueraproyecto() {
		return tiempofueraproyecto;
	}
	public void setTiempofueraproyecto(String tiempofueraproyecto) {
		this.tiempofueraproyecto = tiempofueraproyecto;
	}
	public String getTiempotaller() {
		return tiempotaller;
	}
	public void setTiempotaller(String tiempotaller) {
		this.tiempotaller = tiempotaller;
	}
	public String getUbicacioncarguio() {
		return ubicacioncarguio;
	}
	public void setUbicacioncarguio(String ubicacioncarguio) {
		this.ubicacioncarguio = ubicacioncarguio;
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
	public int getSegundos() {
		return segundos;
	}
	public void setSegundos(int segundos) {
		this.segundos = segundos;
	}
	
	
		
}
