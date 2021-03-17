package cl.samtech.sgomt.object;

public class SinopticoTableActive {
	
	private int pesoTransp; //Pesos transportados a diferentes destinos (por destino)
	private int tiempoMedio; // Tiempos medio de transporte a diferentes destinos
	private int pesoTotalDesp; //Peso total despachado en la vista (diaria)
	private int distribucionCarga; //Distribución de cargas realizadas respecto de óptimos por cada camión.
	private int tiempoOperacionCar; // Tiempo de Operación de carga del equipo de carguío
	private int tiempoOperacionLimp; //Tiempos de Operación de limpieza del equipo de carguío
	private int tiempoMuerto; //Tiempos muertos del equipo de carguío
	private int frecuenciaDesp; // Frecuencia de despacho media
	private int tiempoCargaMedia; //Tiempo de carga medio
	public int getPesoTransp() {
		return pesoTransp;
	}
	public void setPesoTransp(int pesoTransp) {
		this.pesoTransp = pesoTransp;
	}
	public int getTiempoMedio() {
		return tiempoMedio;
	}
	public void setTiempoMedio(int tiempoMedio) {
		this.tiempoMedio = tiempoMedio;
	}
	public int getPesoTotalDesp() {
		return pesoTotalDesp;
	}
	public void setPesoTotalDesp(int pesoTotalDesp) {
		this.pesoTotalDesp = pesoTotalDesp;
	}
	public int getDistribucionCarga() {
		return distribucionCarga;
	}
	public void setDistribucionCarga(int distribucionCarga) {
		this.distribucionCarga = distribucionCarga;
	}
	public int getTiempoOperacionCar() {
		return tiempoOperacionCar;
	}
	public void setTiempoOperacionCar(int tiempoOperacionCar) {
		this.tiempoOperacionCar = tiempoOperacionCar;
	}
	public int getTiempoOperacionLimp() {
		return tiempoOperacionLimp;
	}
	public void setTiempoOperacionLimp(int tiempoOperacionLimp) {
		this.tiempoOperacionLimp = tiempoOperacionLimp;
	}
	public int getTiempoMuerto() {
		return tiempoMuerto;
	}
	public void setTiempoMuerto(int tiempoMuerto) {
		this.tiempoMuerto = tiempoMuerto;
	}
	public int getFrecuenciaDesp() {
		return frecuenciaDesp;
	}
	public void setFrecuenciaDesp(int frecuenciaDesp) {
		this.frecuenciaDesp = frecuenciaDesp;
	}
	public int getTiempoCargaMedia() {
		return tiempoCargaMedia;
	}
	public void setTiempoCargaMedia(int tiempoCargaMedia) {
		this.tiempoCargaMedia = tiempoCargaMedia;
	}
	

	


}
