package cl.samtech.sgomt.object;

import java.sql.Timestamp;

public class ResumenConduccionDiarioActive {
	
	private String patente;
	private String numero_int;
	private String marca;
	private String modelo;
	private Timestamp fecha;
	private Timestamp fecha_on;
	private Timestamp fecha_off;
	private double odo_ini;
	private double odo_fin;
	private double odo;	
	private double horo_ini;
	private double horo_fin;
	private double horo;
	private Integer ralenti;
	private Integer tot_ciclo;
	
	private String turno;
	private Timestamp fecha_ini;
	private Timestamp fecha_fin;
	
	
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public String getNumero_int() {
		return numero_int;
	}
	public void setNumero_int(String numero_int) {
		this.numero_int = numero_int;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	public Timestamp getFecha_on() {
		return fecha_on;
	}
	public void setFecha_on(Timestamp fecha_on) {
		this.fecha_on = fecha_on;
	}
	public Timestamp getFecha_off() {
		return fecha_off;
	}
	public void setFecha_off(Timestamp fecha_off) {
		this.fecha_off = fecha_off;
	}
	public double getOdo_ini() {
		return odo_ini;
	}
	public void setOdo_ini(double odo_ini) {
		this.odo_ini = odo_ini;
	}
	public double getOdo_fin() {
		return odo_fin;
	}
	public void setOdo_fin(double odo_fin) {
		this.odo_fin = odo_fin;
	}
	public double getOdo() {
		return odo;
	}
	public void setOdo(double odo) {
		this.odo = odo;
	}
	public double getHoro_ini() {
		return horo_ini;
	}
	public void setHoro_ini(double horo_ini) {
		this.horo_ini = horo_ini;
	}
	public double getHoro_fin() {
		return horo_fin;
	}
	public void setHoro_fin(double horo_fin) {
		this.horo_fin = horo_fin;
	}
	public double getHoro() {
		return horo;
	}
	public void setHoro(double horo) {
		this.horo = horo;
	}
	public Integer getRalenti() {
		return ralenti;
	}
	public void setRalenti(Integer ralenti) {
		this.ralenti = ralenti;
	}
	public Integer getTot_ciclo() {
		return tot_ciclo;
	}
	public void setTot_ciclo(Integer tot_ciclo) {
		this.tot_ciclo = tot_ciclo;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public Timestamp getFecha_ini() {
		return fecha_ini;
	}
	public void setFecha_ini(Timestamp fecha_ini) {
		this.fecha_ini = fecha_ini;
	}
	public Timestamp getFecha_fin() {
		return fecha_fin;
	}
	public void setFecha_fin(Timestamp fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

}
