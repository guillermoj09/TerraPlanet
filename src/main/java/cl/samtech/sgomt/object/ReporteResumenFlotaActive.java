package cl.samtech.sgomt.object;

import java.sql.Timestamp;



public class ReporteResumenFlotaActive { 
	
	private String patente;
	private String idVehicle;
	private String chofer;
	private String faena;
	private Timestamp dateDate;
	private int odometro;
	private String nivelComb;
	private String lat;
	private String lon;
	private String marca;
	private String modelo;
	private String numinterno;
	private Double horometro;
	private String nombreTercero;
	private int viajes;
	private Double dt;
	private String dtString;
	private Double tpomm;
	private String tpommString;
	private Double dsttot;
	private Double combtot;
	private Double velmax;
	private Double rpmmax;
	private Double tporal;
	private Double tpocc;
	private Double tpovel;
	private Double combral;
	private Double combcc;
	private Double dstcc;
	private Double combmarcha;
	private int alarpm;
	private int alavel;
	private int alaral;
	private int alacodf;
	private Double combpto;
	private Double tpoto;
	private Double adblue;
	private int alaacelb;
	private int alafrenb;
	private double rendimkmlt;
	private double rendimlthr;
	
	private String rendimkmltString;
	private String rendimlthrString;
	
	private String dsttotString;
	private String combtotString;
	
	private String totaltiempo;
	
	
	
	public String getDtString() {
		return dtString;
	}
	public void setDtString(String dtString) {
		this.dtString = dtString;
	}
	public String getTpommString() {
		return tpommString;
	}
	public void setTpommString(String tpommString) {
		this.tpommString = tpommString;
	}
	public String getTotaltiempo() {
		return totaltiempo;
	}
	public void setTotaltiempo(String totaltiempo) {
		this.totaltiempo = totaltiempo;
	}
	public String getDsttotString() {
		return dsttotString;
	}
	public void setDsttotString(String dsttotString) {
		this.dsttotString = dsttotString;
	}
	public String getCombtotString() {
		return combtotString;
	}
	public void setCombtotString(String combtotString) {
		this.combtotString = combtotString;
	}
	public String getRendimkmltString() {
		return rendimkmltString;
	}
	public void setRendimkmltString(String rendimkmltString) {
		this.rendimkmltString = rendimkmltString;
	}
	public String getRendimlthrString() {
		return rendimlthrString;
	}
	public void setRendimlthrString(String rendimlthrString) {
		this.rendimlthrString = rendimlthrString;
	}
	public double getRendimkmlt() {
		return rendimkmlt;
	}
	public void setRendimkmlt(double rendimkmlt) {
		this.rendimkmlt = rendimkmlt;
	}
	public double getRendimlthr() {
		return rendimlthr;
	}
	public void setRendimlthr(double rendimlthr) {
		this.rendimlthr = rendimlthr;
	}
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public String getIdVehicle() {
		return idVehicle;
	}
	public void setIdVehicle(String idVehicle) {
		this.idVehicle = idVehicle;
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
	public Timestamp getDateDate() {
		return dateDate;
	}
	public void setDateDate(Timestamp dateDate) {
		this.dateDate = dateDate;
	}
	public int getOdometro() {
		return odometro;
	}
	public void setOdometro(int odometro) {
		this.odometro = odometro;
	}
	public String getNivelComb() {
		return nivelComb;
	}
	public void setNivelComb(String nivelComb) {
		this.nivelComb = nivelComb;
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
	public String getNuminterno() {
		return numinterno;
	}
	public void setNuminterno(String numinterno) {
		this.numinterno = numinterno;
	}
	public Double getHorometro() {
		return horometro;
	}
	public void setHorometro(Double horometro) {
		this.horometro = horometro;
	}
	public String getNombreTercero() {
		return nombreTercero;
	}
	public void setNombreTercero(String nombreTercero) {
		this.nombreTercero = nombreTercero;
	}
	public int getViajes() {
		return viajes;
	}
	public void setViajes(int viajes) {
		this.viajes = viajes;
	}
	public Double getDt() {
		return dt;
	}
	public void setDt(Double dt) {
		this.dt = dt;
	}
	public Double getTpomm() {
		return tpomm;
	}
	public void setTpomm(Double tpomm) {
		this.tpomm = tpomm;
	}
	public Double getDsttot() {
		return dsttot;
	}
	public void setDsttot(Double dsttot) {
		this.dsttot = dsttot;
	}
	public Double getCombtot() {
		return combtot;
	}
	public void setCombtot(Double combtot) {
		this.combtot = combtot;
	}
	public Double getVelmax() {
		return velmax;
	}
	public void setVelmax(Double velmax) {
		this.velmax = velmax;
	}
	public Double getRpmmax() {
		return rpmmax;
	}
	public void setRpmmax(Double rpmmax) {
		this.rpmmax = rpmmax;
	}
	public Double getTporal() {
		return tporal;
	}
	public void setTporal(Double tporal) {
		this.tporal = tporal;
	}
	public Double getTpocc() {
		return tpocc;
	}
	public void setTpocc(Double tpocc) {
		this.tpocc = tpocc;
	}
	public Double getTpovel() {
		return tpovel;
	}
	public void setTpovel(Double tpovel) {
		this.tpovel = tpovel;
	}
	public Double getCombral() {
		return combral;
	}
	public void setCombral(Double combral) {
		this.combral = combral;
	}
	public Double getCombcc() {
		return combcc;
	}
	public void setCombcc(Double combcc) {
		this.combcc = combcc;
	}
	public Double getDstcc() {
		return dstcc;
	}
	public void setDstcc(Double dstcc) {
		this.dstcc = dstcc;
	}
	public Double getCombmarcha() {
		return combmarcha;
	}
	public void setCombmarcha(Double combmarcha) {
		this.combmarcha = combmarcha;
	}
	public int getAlarpm() {
		return alarpm;
	}
	public void setAlarpm(int alarpm) {
		this.alarpm = alarpm;
	}
	public int getAlavel() {
		return alavel;
	}
	public void setAlavel(int alavel) {
		this.alavel = alavel;
	}
	public int getAlaral() {
		return alaral;
	}
	public void setAlaral(int alaral) {
		this.alaral = alaral;
	}
	public int getAlacodf() {
		return alacodf;
	}
	public void setAlacodf(int alacodf) {
		this.alacodf = alacodf;
	}
	public Double getCombpto() {
		return combpto;
	}
	public void setCombpto(Double combpto) {
		this.combpto = combpto;
	}
	public Double getTpoto() {
		return tpoto;
	}
	public void setTpoto(Double tpoto) {
		this.tpoto = tpoto;
	}
	public Double getAdblue() {
		return adblue;
	}
	public void setAdblue(Double adblue) {
		this.adblue = adblue;
	}
	public int getAlaacelb() {
		return alaacelb;
	}
	public void setAlaacelb(int alaacelb) {
		this.alaacelb = alaacelb;
	}
	public int getAlafrenb() {
		return alafrenb;
	}
	public void setAlafrenb(int alafrenb) {
		this.alafrenb = alafrenb;
	}
	
	
	
					
		
}
