package cl.samtech.sgomt.object;

import java.sql.Timestamp;

public class ReporteCicloTransporteActive {
	
	private String idcamion;
	private String ubicacioncarguio1;
	private Timestamp horacarguio1;
	private double cargareal;
	private String ubicacioncarguio2;
	private Timestamp horacarguio2;
	private String tiempodeciclo;
	private String intervalopromediotransporte;
	
	public String getIdcamion() {
		return idcamion;
	}
	public void setIdcamion(String idcamion) {
		this.idcamion = idcamion;
	}
	public String getUbicacioncarguio1() {
		return ubicacioncarguio1;
	}
	public void setUbicacioncarguio1(String ubicacioncarguio1) {
		this.ubicacioncarguio1 = ubicacioncarguio1;
	}
	public Timestamp getHoracarguio1() {
		return horacarguio1;
	}
	public void setHoracarguio1(Timestamp horacarguio1) {
		this.horacarguio1 = horacarguio1;
	}
	public double getCargareal() {
		return cargareal;
	}
	public void setCargareal(double cargareal) {
		this.cargareal = cargareal;
	}
	public String getUbicacioncarguio2() {
		return ubicacioncarguio2;
	}
	public void setUbicacioncarguio2(String ubicacioncarguio2) {
		this.ubicacioncarguio2 = ubicacioncarguio2;
	}
	public Timestamp getHoracarguio2() {
		return horacarguio2;
	}
	public void setHoracarguio2(Timestamp horacarguio2) {
		this.horacarguio2 = horacarguio2;
	}
	public String getTiempodeciclo() {
		return tiempodeciclo;
	}
	public void setTiempodeciclo(String tiempodeciclo) {
		this.tiempodeciclo = tiempodeciclo;
	}
	public String getIntervalopromediotransporte() {
		return intervalopromediotransporte;
	}
	public void setIntervalopromediotransporte(String intervalopromediotransporte) {
		this.intervalopromediotransporte = intervalopromediotransporte;
	}
			
		
	
}
