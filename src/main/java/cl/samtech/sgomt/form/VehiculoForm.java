package cl.samtech.sgomt.form;

import javax.persistence.Column;

public class VehiculoForm {
	
	private String vehPatente;

	private String vehAnio;

	private double vehCargaSoportada;

	private Double vehCargaVolumen;
	
	private String vehFechaInserta;

	private String vehMarca;

	private String vehModelo;

	private String vehMotor;

	//serial del motor
	private String vehVin;
	
	private String tags; // administrar por sgomt
	
	private String usuarios; // administrar por sgomt
	
	private String diveces;// los edita el otro sistema sqlserver
	
	private String tipoVehiculo;
	
	private String vehNumInterno;
	
	private String vehColor;
	
	private Double vehPesoBruto;
	
	private Double vehCapacidadBalde;
	
	private Double vehPesoOperacional;
	
	private Double vehNivelEstanque;
	
	private Double vehCapacidadBaldeTon;
	
	private String origen;
	
	

	public Double getVehPesoBruto() {
		return vehPesoBruto;
	}

	public void setVehPesoBruto(Double vehPesoBruto) {
		this.vehPesoBruto = vehPesoBruto;
	}

	public Double getVehCapacidadBalde() {
		return vehCapacidadBalde;
	}

	public void setVehCapacidadBalde(Double vehCapacidadBalde) {
		this.vehCapacidadBalde = vehCapacidadBalde;
	}

	public Double getVehPesoOperacional() {
		return vehPesoOperacional;
	}

	public void setVehPesoOperacional(Double vehPesoOperacional) {
		this.vehPesoOperacional = vehPesoOperacional;
	}

	public Double getVehNivelEstanque() {
		return vehNivelEstanque;
	}

	public void setVehNivelEstanque(Double vehNivelEstanque) {
		this.vehNivelEstanque = vehNivelEstanque;
	}

	public Double getVehCapacidadBaldeTon() {
		return vehCapacidadBaldeTon;
	}

	public void setVehCapacidadBaldeTon(Double vehCapacidadBaldeTon) {
		this.vehCapacidadBaldeTon = vehCapacidadBaldeTon;
	}

	public String getVehNumInterno() {
		return vehNumInterno;
	}

	public void setVehNumInterno(String vehNumInterno) {
		this.vehNumInterno = vehNumInterno;
	}

	public String getVehColor() {
		return vehColor;
	}

	public void setVehColor(String vehColor) {
		this.vehColor = vehColor;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(String usuarios) {
		this.usuarios = usuarios;
	}

	public String getDiveces() {
		return diveces;
	}

	public void setDiveces(String diveces) {
		this.diveces = diveces;
	}

	public String getVehPatente() {
		return vehPatente;
	}

	public void setVehPatente(String vehPatente) {
		this.vehPatente = vehPatente;
	}

	public String getVehAnio() {
		return vehAnio;
	}

	public void setVehAnio(String vehAnio) {
		this.vehAnio = vehAnio;
	}

	public double getVehCargaSoportada() {
		return vehCargaSoportada;
	}

	public void setVehCargaSoportada(double vehCargaSoportada) {
		this.vehCargaSoportada = vehCargaSoportada;
	}

	

	public Double getVehCargaVolumen() {
		return vehCargaVolumen;
	}

	public void setVehCargaVolumen(Double vehCargaVolumen) {
		this.vehCargaVolumen = vehCargaVolumen;
	}

	public String getVehFechaInserta() {
		return vehFechaInserta;
	}

	public void setVehFechaInserta(String vehFechaInserta) {
		this.vehFechaInserta = vehFechaInserta;
	}

	public String getVehMarca() {
		return vehMarca;
	}

	public void setVehMarca(String vehMarca) {
		this.vehMarca = vehMarca;
	}

	public String getVehModelo() {
		return vehModelo;
	}

	public void setVehModelo(String vehModelo) {
		this.vehModelo = vehModelo;
	}

	public String getVehMotor() {
		return vehMotor;
	}

	public void setVehMotor(String vehMotor) {
		this.vehMotor = vehMotor;
	}

	public String getVehVin() {
		return vehVin;
	}

	public void setVehVin(String vehVin) {
		this.vehVin = vehVin;
	}


	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}
	
	


}
