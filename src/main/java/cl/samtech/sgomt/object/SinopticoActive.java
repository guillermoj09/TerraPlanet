package cl.samtech.sgomt.object;

public class SinopticoActive {
	
	private String nrocamion;
	private int cargaactual;
	private int cargapermitida;
	private int parimpar;
	private String hora;
			
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public int getParimpar() {
		return parimpar;
	}
	public void setParimpar(int parimpar) {
		this.parimpar = parimpar;
	}
	public int getCargaactual() {
		return cargaactual;
	}
	public void setCargaactual(int cargaactual) {
		this.cargaactual = cargaactual;
	}
	public int getCargapermitida() {
		return cargapermitida;
	}
	public void setCargapermitida(int cargapermitida) {
		this.cargapermitida = cargapermitida;
	}
	public String getNrocamion() {
		return nrocamion;
	}
	public void setNrocamion(String nrocamion) {
		this.nrocamion = nrocamion;
	}
		

}
