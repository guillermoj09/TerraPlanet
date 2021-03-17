package cl.samtech.sgomt.object;

import java.util.List;

public class CarguioGraficoActive {
	
	private String patente;
	private String nrointerno;
	private String backgroundColor;	
	private String borderColor;
	private List<OperacionesActive> operacionesactive;
	
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
	public String getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}
	public String getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
	public List<OperacionesActive> getOperacionesactive() {
		return operacionesactive;
	}
	public void setOperacionesactive(List<OperacionesActive> operacionesactive) {
		this.operacionesactive = operacionesactive;
	}
		

}
