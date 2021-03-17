package cl.samtech.sgomt.object;

import java.sql.Timestamp;

public class ReporteTiempoFueraOperacionActive {
	
	private String idcamion;
	private String ubicacion;
	private int tiempopermanencia;
	private String alarma;
	private int zonaid;
	private String zonanombre;
	private String iconoeve;
	private String nombreve;
	private String classEvent;
	private String idEvent;
	private Timestamp tiempo;
	private String tiempoS;

	public String getTiempoS() {
		return tiempoS;
	}
	public void setTiempoS(String tiempoS) {
		this.tiempoS = tiempoS;
	}
	public Timestamp getTiempo() {
		return tiempo;
	}
	public void setTiempo(Timestamp tiempo) {
		this.tiempo = tiempo;
	}
	public String getIdEvent() {
		return idEvent;
	}
	public void setIdEvent(String idEvent) {
		this.idEvent = idEvent;
	}
	public String getIconoeve() {
		return iconoeve;
	}
	public void setIconoeve(String iconoeve) {
		this.iconoeve = iconoeve;
	}
	public String getNombreve() {
		return nombreve;
	}
	public void setNombreve(String nombreve) {
		this.nombreve = nombreve;
	}
	public String getClassEvent() {
		return classEvent;
	}
	public void setClassEvent(String classEvent) {
		this.classEvent = classEvent;
	}
	public String getZonanombre() {
		return zonanombre;
	}
	public void setZonanombre(String zonanombre) {
		this.zonanombre = zonanombre;
	}
	public int getZonaid() {
		return zonaid;
	}
	public void setZonaid(int zonaid) {
		this.zonaid = zonaid;
	}
	
	public int getTiempopermanencia() {
		return tiempopermanencia;
	}
	public void setTiempopermanencia(int tiempopermanencia) {
		this.tiempopermanencia = tiempopermanencia;
	}
	public String getIdcamion() {
		return idcamion;
	}
	public void setIdcamion(String idcamion) {
		this.idcamion = idcamion;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	public String getAlarma() {
		return alarma;
	}
	public void setAlarma(String alarma) {
		this.alarma = alarma;
	}
	
				
		
}
