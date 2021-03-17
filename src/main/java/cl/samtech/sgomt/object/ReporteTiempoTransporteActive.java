package cl.samtech.sgomt.object;

import java.sql.Timestamp;
import java.util.Date;

public class ReporteTiempoTransporteActive {
	
	private String idcamion;
	private String ubicacioncarguio;
	private Date fechasalidacargio;
	private Date fechaentradadescarga;
	private Double cargareal;
	private String ubicaciondescarga;
	private String tiempototaldescarga;
	private String intervalopromediotransporte;
	private Integer tiempotransporte;
	private Integer tiempoespera;
	private Integer tiempodescarga;
	private Date fechadescarga;
	private Date fechasalidadescarga;
	private Date fechaentradacargio;
	private Integer tiempociclo;
	private String nrinterno;
	
	private Integer turno;
	private String maquina;
	private Double odo_ini;
	private Double odo_fin;
	private Integer vel_max;
	private Integer vel_prom;
	private String operador;
	
	private Date fecha_carga;
	private Integer tmpo_carga;
	
	private String tmpo_carga_str;
	private String tiempociclo_str;
	private String tiempodescarga_str;
	private String tiempoespera_str;
	private String tiempotransporte_str;
	
	private Integer data_id;
	
	
	public String getTmpo_carga_str() {
		return tmpo_carga_str;
	}
	public void setTmpo_carga_str(String tmpo_carga_str) {
		this.tmpo_carga_str = tmpo_carga_str;
	}
	public String getTiempociclo_str() {
		return tiempociclo_str;
	}
	public void setTiempociclo_str(String tiempociclo_str) {
		this.tiempociclo_str = tiempociclo_str;
	}
	public String getTiempodescarga_str() {
		return tiempodescarga_str;
	}
	public void setTiempodescarga_str(String tiempodescarga_str) {
		this.tiempodescarga_str = tiempodescarga_str;
	}
	public String getTiempoespera_str() {
		return tiempoespera_str;
	}
	public void setTiempoespera_str(String tiempoespera_str) {
		this.tiempoespera_str = tiempoespera_str;
	}
	public String getTiempotransporte_str() {
		return tiempotransporte_str;
	}
	public void setTiempotransporte_str(String tiempotransporte_str) {
		this.tiempotransporte_str = tiempotransporte_str;
	}
	
	
	
	public String getNrinterno() {
		return nrinterno;
	}
	public void setNrinterno(String nrinterno) {
		this.nrinterno = nrinterno;
	}
	public String getIdcamion() {
		return idcamion;
	}
	public void setIdcamion(String idcamion) {
		this.idcamion = idcamion;
	}
	public String getUbicacioncarguio() {
		return ubicacioncarguio;
	}
	public void setUbicacioncarguio(String ubicacioncarguio) {
		this.ubicacioncarguio = ubicacioncarguio;
	}
	public Double getCargareal() {
		return cargareal;
	}
	public void setCargareal(Double cargareal) {
		this.cargareal = cargareal;
	}
	public String getUbicaciondescarga() {
		return ubicaciondescarga;
	}
	public void setUbicaciondescarga(String ubicaciondescarga) {
		this.ubicaciondescarga = ubicaciondescarga;
	}
	public String getTiempototaldescarga() {
		return tiempototaldescarga;
	}
	public void setTiempototaldescarga(String tiempototaldescarga) {
		this.tiempototaldescarga = tiempototaldescarga;
	}
	public String getIntervalopromediotransporte() {
		return intervalopromediotransporte;
	}
	public void setIntervalopromediotransporte(String intervalopromediotransporte) {
		this.intervalopromediotransporte = intervalopromediotransporte;
	}
	public Integer getTiempotransporte() {
		return tiempotransporte;
	}
	public void setTiempotransporte(Integer pa) {
		this.tiempotransporte = pa;
	}
	public Integer getTiempoespera() {
		return tiempoespera;
	}
	public void setTiempoespera(Integer pa) {
		this.tiempoespera = pa;
	}
	public Integer getTiempodescarga() {
		return tiempodescarga;
	}
	public void setTiempodescarga(Integer pa) {
		this.tiempodescarga = pa;
	}
	public Date getFechasalidacargio() {
		return fechasalidacargio;
	}
	public void setFechasalidacargio(Date fechasalidacargio) {
		this.fechasalidacargio = fechasalidacargio;
	}
	public Date getFechaentradadescarga() {
		return fechaentradadescarga;
	}
	public void setFechaentradadescarga(Date fechaentradadescarga) {
		this.fechaentradadescarga = fechaentradadescarga;
	}
	public Date getFechadescarga() {
		return fechadescarga;
	}
	public void setFechadescarga(Date fechadescarga) {
		this.fechadescarga = fechadescarga;
	}
	public Date getFechasalidadescarga() {
		return fechasalidadescarga;
	}
	public void setFechasalidadescarga(Date fechasalidadescarga) {
		this.fechasalidadescarga = fechasalidadescarga;
	}
	public Date getFechaentradacargio() {
		return fechaentradacargio;
	}
	public void setFechaentradacargio(Date fechaentradacargio) {
		this.fechaentradacargio = fechaentradacargio;
	}
	public Integer getTiempociclo() {
		return tiempociclo;
	}
	public void setTiempociclo(Integer tiempociclo) {
		this.tiempociclo = tiempociclo;
	}
	
	
	
	public Integer getTurno() {
		return turno;
	}
	public void setTurno(Integer turno) {
		this.turno = turno;
	}
	public String getMaquina() {
		return maquina;
	}
	public void setMaquina(String maquina) {
		this.maquina = maquina;
	}
	public Double getOdo_ini() {
		return odo_ini;
	}
	public void setOdo_ini(Double odo_ini) {
		this.odo_ini = odo_ini;
	}
	public Double getOdo_fin() {
		return odo_fin;
	}
	public void setOdo_fin(Double odo_fin) {
		this.odo_fin = odo_fin;
	}
	public Integer getVel_max() {
		return vel_max;
	}
	public void setVel_max(Integer vel_max) {
		this.vel_max = vel_max;
	}
	public Integer getVel_prom() {
		return vel_prom;
	}
	public void setVel_prom(Integer vel_prom) {
		this.vel_prom = vel_prom;
	}
	public String getOperador() {
		return operador;
	}
	public void setOperador(String operador) {
		this.operador = operador;
	}
	public Date getFecha_carga() {
		return fecha_carga;
	}
	public void setFecha_carga(Date fecha_carga) {
		this.fecha_carga = fecha_carga;
	}
	public Integer getTmpo_carga() {
		return tmpo_carga;
	}
	public void setTmpo_carga(Integer tmpo_carga) {
		this.tmpo_carga = tmpo_carga;
	}
	public Integer getData_id() {
		return data_id;
	}
	public void setData_id(Integer dat_id) {
		this.data_id = dat_id;
	}
	
	
			
		
}
