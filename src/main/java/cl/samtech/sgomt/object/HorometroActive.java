package cl.samtech.sgomt.object;

import java.sql.Timestamp;

public class HorometroActive {
	
	private String gps;
	private String patente;
	private Double horometro;
	private String tipo;
	private String tipoD;
	private Timestamp fechaCambia;
	private Double horoActual;
	private Double horoCambia;
	private String usuarioCambia;
	private Integer estado;
	private String fechaCambiaS;
	private String estadoD;
	private Timestamp fechaUltima;
	private String fechaUltimaS;
	
	public Timestamp getFechaUltima() {
		return fechaUltima;
	}
	public void setFechaUltima(Timestamp fechaUltima) {
		this.fechaUltima = fechaUltima;
	}
	public String getFechaUltimaS() {
		return fechaUltimaS;
	}
	public void setFechaUltimaS(String fechaUltimaS) {
		this.fechaUltimaS = fechaUltimaS;
	}
	public String getTipoD() {
		return tipoD;
	}
	public void setTipoD(String tipoD) {
		this.tipoD = tipoD;
	}
	public String getEstadoD() {
		return estadoD;
	}
	public void setEstadoD(String estadoD) {
		this.estadoD = estadoD;
	}
	public String getFechaCambiaS() {
		return fechaCambiaS;
	}
	public void setFechaCambiaS(String fechaCambiaS) {
		this.fechaCambiaS = fechaCambiaS;
	}
	public Timestamp getFechaCambia() {
		return fechaCambia;
	}
	public void setFechaCambia(Timestamp fechaCambia) {
		this.fechaCambia = fechaCambia;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public String getGps() {
		return gps;
	}
	public void setGps(String gps) {
		this.gps = gps;
	}
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public Double getHorometro() {
		return horometro;
	}
	public void setHorometro(Double horometro) {
		this.horometro = horometro;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Double getHoroActual() {
		return horoActual;
	}
	public void setHoroActual(Double horoActual) {
		this.horoActual = horoActual;
	}
	public Double getHoroCambia() {
		return horoCambia;
	}
	public void setHoroCambia(Double horoCambia) {
		this.horoCambia = horoCambia;
	}
	public String getUsuarioCambia() {
		return usuarioCambia;
	}
	public void setUsuarioCambia(String usuarioCambia) {
		this.usuarioCambia = usuarioCambia;
	}
	
	

}
