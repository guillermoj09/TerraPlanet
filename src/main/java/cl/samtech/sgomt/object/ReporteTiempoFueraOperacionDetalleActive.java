package cl.samtech.sgomt.object;

import java.sql.Timestamp;

public class ReporteTiempoFueraOperacionDetalleActive {
	
    //   in                     patente          ubicacion                  interno         chofer               faena  vel e  hdg   lat         lon
	//   0                       1              2                              3             4                   5      6   7   8      9          19
	//"2019-03-22 12:08:26-03"	"HKPC22"	"Plataforma Tuberia Noroeste"	"CT.316"	"No Registra Operador"	faena	5	0	85	"-22.72928"	"-069.31876"
	
	private Timestamp fechain;
	private String patente;
	private String ubicacion;
	private String nrointerno;
	private String chofer;
	private String faena;
	private String vel;
	private String evento;
	private int hdg;
	private String lat;
	private String lon;
	private String iconoeve;
	private String nombreve;
	private String classEvent;
	private String nomflecha;
	private String rutaflecha;
	private String idEvent;
		
	public String getIdEvent() {
		return idEvent;
	}
	public void setIdEvent(String idEvent) {
		this.idEvent = idEvent;
	}
	public Timestamp getFechain() {
		return fechain;
	}
	public void setFechain(Timestamp fechain) {
		this.fechain = fechain;
	}
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getNrointerno() {
		return nrointerno;
	}
	public void setNrointerno(String nrointerno) {
		this.nrointerno = nrointerno;
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
	public String getVel() {
		return vel;
	}
	public void setVel(String vel) {
		this.vel = vel;
	}
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}
	public int getHdg() {
		return hdg;
	}
	public void setHdg(int hdg) {
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
	
	
	
	

}
