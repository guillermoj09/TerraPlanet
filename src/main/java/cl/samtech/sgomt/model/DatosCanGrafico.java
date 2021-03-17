package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the datos_can_grafico database table.
 * 
 */
@Entity
@Table(name="datos_can_grafico")
@NamedQuery(name="DatosCanGrafico.findAll", query="SELECT d FROM DatosCanGrafico d")
public class DatosCanGrafico implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DatosCanGraficoPK id;

	@Column(name="dev_id_device")
	private String devIdDevice;

	@Column(name="gra_fecha_inserta")
	private Timestamp graFechaInserta;

	@Column(name="gra_id")
	private Integer graId;

	@Column(name="gra_identificador")
	private String graIdentificador;

	@Column(name="gra_rango1")
	private double graRango1;

	@Column(name="gra_rango10")
	private double graRango10;

	@Column(name="gra_rango11")
	private double graRango11;

	@Column(name="gra_rango12")
	private double graRango12;

	@Column(name="gra_rango13")
	private double graRango13;

	@Column(name="gra_rango14")
	private double graRango14;

	@Column(name="gra_rango15")
	private double graRango15;

	@Column(name="gra_rango2")
	private double graRango2;

	@Column(name="gra_rango3")
	private double graRango3;

	@Column(name="gra_rango4")
	private double graRango4;

	@Column(name="gra_rango5")
	private double graRango5;

	@Column(name="gra_rango6")
	private double graRango6;

	@Column(name="gra_rango7")
	private double graRango7;

	@Column(name="gra_rango8")
	private double graRango8;

	@Column(name="gra_rango9")
	private double graRango9;

	@Column(name="gra_tipo_reporte")
	private String graTipoReporte;

	public DatosCanGrafico() {
	}

	public DatosCanGraficoPK getId() {
		return this.id;
	}

	public void setId(DatosCanGraficoPK id) {
		this.id = id;
	}

	public String getDevIdDevice() {
		return this.devIdDevice;
	}

	public void setDevIdDevice(String devIdDevice) {
		this.devIdDevice = devIdDevice;
	}

	public Timestamp getGraFechaInserta() {
		return this.graFechaInserta;
	}

	public void setGraFechaInserta(Timestamp graFechaInserta) {
		this.graFechaInserta = graFechaInserta;
	}

	public Integer getGraId() {
		return this.graId;
	}

	public void setGraId(Integer graId) {
		this.graId = graId;
	}

	public String getGraIdentificador() {
		return this.graIdentificador;
	}

	public void setGraIdentificador(String graIdentificador) {
		this.graIdentificador = graIdentificador;
	}

	public double getGraRango1() {
		return this.graRango1;
	}

	public void setGraRango1(double graRango1) {
		this.graRango1 = graRango1;
	}

	public double getGraRango10() {
		return this.graRango10;
	}

	public void setGraRango10(double graRango10) {
		this.graRango10 = graRango10;
	}

	public double getGraRango11() {
		return this.graRango11;
	}

	public void setGraRango11(double graRango11) {
		this.graRango11 = graRango11;
	}

	public double getGraRango12() {
		return this.graRango12;
	}

	public void setGraRango12(double graRango12) {
		this.graRango12 = graRango12;
	}

	public double getGraRango13() {
		return this.graRango13;
	}

	public void setGraRango13(double graRango13) {
		this.graRango13 = graRango13;
	}

	public double getGraRango14() {
		return this.graRango14;
	}

	public void setGraRango14(double graRango14) {
		this.graRango14 = graRango14;
	}

	public double getGraRango15() {
		return this.graRango15;
	}

	public void setGraRango15(double graRango15) {
		this.graRango15 = graRango15;
	}

	public double getGraRango2() {
		return this.graRango2;
	}

	public void setGraRango2(double graRango2) {
		this.graRango2 = graRango2;
	}

	public double getGraRango3() {
		return this.graRango3;
	}

	public void setGraRango3(double graRango3) {
		this.graRango3 = graRango3;
	}

	public double getGraRango4() {
		return this.graRango4;
	}

	public void setGraRango4(double graRango4) {
		this.graRango4 = graRango4;
	}

	public double getGraRango5() {
		return this.graRango5;
	}

	public void setGraRango5(double graRango5) {
		this.graRango5 = graRango5;
	}

	public double getGraRango6() {
		return this.graRango6;
	}

	public void setGraRango6(double graRango6) {
		this.graRango6 = graRango6;
	}

	public double getGraRango7() {
		return this.graRango7;
	}

	public void setGraRango7(double graRango7) {
		this.graRango7 = graRango7;
	}

	public double getGraRango8() {
		return this.graRango8;
	}

	public void setGraRango8(double graRango8) {
		this.graRango8 = graRango8;
	}

	public double getGraRango9() {
		return this.graRango9;
	}

	public void setGraRango9(double graRango9) {
		this.graRango9 = graRango9;
	}

	public String getGraTipoReporte() {
		return this.graTipoReporte;
	}

	public void setGraTipoReporte(String graTipoReporte) {
		this.graTipoReporte = graTipoReporte;
	}

}