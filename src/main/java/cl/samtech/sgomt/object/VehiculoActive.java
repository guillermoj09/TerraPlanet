package cl.samtech.sgomt.object;

import java.util.List;

import javax.persistence.Column;

import cl.samtech.sgomt.model.Tag;
import cl.samtech.sgomt.model.TipoVehiculo;

public class VehiculoActive {
	
	private Integer vehId;
	
	private String vehPatente;

	private String vehAnio;

	private double vehCargaSoportada;

	private Integer vehCargaVolumen;
	
	private String vehFechaInserta;

	private String vehMarca;

	private String vehModelo;

	private String vehMotor;

	//serial del motor
	private String vehVin;
		
	private TipoVehiculo tipoVehiculo;
	
	private List<Tag> tags;
	
	private String nrointerno;
	
	private Integer vehNivelEstanque;
	
	private Integer vehCapacidadBaldeTon;
	
	private String listadoTags; 
	
	//para reporte resumen descarga
	private List<ReporteResumenDescargaActive> listreporteResumenDescargaActive;
	
	public List<ReporteResumenDescargaActive> getListreporteResumenDescargaActive() {
		return listreporteResumenDescargaActive;
	}

	public void setListreporteResumenDescargaActive(List<ReporteResumenDescargaActive> listreporteResumenDescargaActive) {
		this.listreporteResumenDescargaActive = listreporteResumenDescargaActive;
	}

	public Integer getVehNivelEstanque() {
		return vehNivelEstanque;
	}

	public void setVehNivelEstanque(Integer vehNivelEstanque) {
		this.vehNivelEstanque = vehNivelEstanque;
	}

	public Integer getVehCapacidadBaldeTon() {
		return vehCapacidadBaldeTon;
	}

	public void setVehCapacidadBaldeTon(Integer vehCapacidadBaldeTon) {
		this.vehCapacidadBaldeTon = vehCapacidadBaldeTon;
	}

	public String getNrointerno() {
		return nrointerno;
	}

	public void setNrointerno(String nrointerno) {
		this.nrointerno = nrointerno;
	}

	public Integer getVehId() {
		return vehId;
	}

	public void setVehId(Integer vehId) {
		this.vehId = vehId;
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

	public Integer getVehCargaVolumen() {
		return vehCargaVolumen;
	}

	public void setVehCargaVolumen(Integer vehCargaVolumen) {
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

	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public String getListadoTags() {
		return listadoTags;
	}

	public void setListadoTags(String listadoTags) {
		this.listadoTags = listadoTags;
	}
	
	

	
	
}
