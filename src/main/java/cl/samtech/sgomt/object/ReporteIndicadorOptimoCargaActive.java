package cl.samtech.sgomt.object;

public class ReporteIndicadorOptimoCargaActive {
	
	//Para reporte
	private int nrovuelta;
	private String horasdespacho;
	private String idcamion;
	private double cargareal;
	private int carganominal;
	private int porcentajedecarga;
	
	//para grafico
	private int ano;
	private int mes;
	private int dia;
	private int hora;
	private int min;
	private int seg;
	private double tonelada;
	
	
	
	
	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getSeg() {
		return seg;
	}

	public void setSeg(int seg) {
		this.seg = seg;
	}

	public double getTonelada() {
		return tonelada;
	}

	public void setTonelada(double tonelada) {
		this.tonelada = tonelada;
	}

	public double getCargareal() {
		return cargareal;
	}

	public void setCargareal(double cargareal) {
		this.cargareal = cargareal;
	}

	public String getIdcamion() {
		return idcamion;
	}

	public void setIdcamion(String idcamion) {
		this.idcamion = idcamion;
	}
	
	public int getNrovuelta() {
		return nrovuelta;
	}
	public void setNrovuelta(int nrovuelta) {
		this.nrovuelta = nrovuelta;
	}
	public String getHorasdespacho() {
		return horasdespacho;
	}
	public void setHorasdespacho(String horasdespacho) {
		this.horasdespacho = horasdespacho;
	}
	
	public int getCarganominal() {
		return carganominal;
	}
	public void setCarganominal(int carganominal) {
		this.carganominal = carganominal;
	}
	public int getPorcentajedecarga() {
		return porcentajedecarga;
	}
	public void setPorcentajedecarga(int porcentajedecarga) {
		this.porcentajedecarga = porcentajedecarga;
	}
			
		
	
}
