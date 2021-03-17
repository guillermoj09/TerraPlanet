package cl.samtech.sgomt.object;

import java.util.List;

public class ListadoGeocercasActive {
	
	private Integer id_geo;
	private String nombre;
	private String nombre_color;
	private String codigo_color;
	private Double area;
	private String geomText;
	private List<CoordenadasActive> coordenadas;
	private Integer velocidadAlarma;
	private Integer estadoAlarma;
	private String  correo;
	
	private String uso;
	private Integer vigencia;
	private Integer visible;
	private Integer georeferencia;
	private Integer aplicacion;
	private Integer fuera;
	
	
	
	
	public Integer getVelocidadAlarma() {
		return velocidadAlarma;
	}
	public void setVelocidadAlarma(Integer velocidadAlarma) {
		this.velocidadAlarma = velocidadAlarma;
	}
	public Integer getEstadoAlarma() {
		return estadoAlarma;
	}
	public void setEstadoAlarma(Integer estadoAlarma) {
		this.estadoAlarma = estadoAlarma;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public Integer getId_geo() {
		return id_geo;
	}
	public void setId_geo(Integer id_geo) {
		this.id_geo = id_geo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre_color() {
		return nombre_color;
	}
	public void setNombre_color(String nombre_color) {
		this.nombre_color = nombre_color;
	}
	public String getCodigo_color() {
		return codigo_color;
	}
	public void setCodigo_color(String codigo_color) {
		this.codigo_color = codigo_color;
	}
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	public String getGeomText() {
		return geomText;
	}
	public void setGeomText(String geomText) {
		this.geomText = geomText;
	}
	public List<CoordenadasActive> getCoordenadas() {
		return coordenadas;
	}
	public void setCoordenadas(List<CoordenadasActive> coordenadas) {
		this.coordenadas = coordenadas;
	}
	public String getUso() {
		return uso;
	}
	public void setUso(String uso) {
		this.uso = uso;
	}
	public Integer getVigencia() {
		return vigencia;
	}
	public void setVigencia(Integer vigencia) {
		this.vigencia = vigencia;
	}
	public Integer getVisible() {
		return visible;
	}
	public void setVisible(Integer visible) {
		this.visible = visible;
	}
	public Integer getGeoreferencia() {
		return georeferencia;
	}
	public void setGeoreferencia(Integer georeferencia) {
		this.georeferencia = georeferencia;
	}
	public Integer getAplicacion() {
		return aplicacion;
	}
	public void setAplicacion(Integer aplicacion) {
		this.aplicacion = aplicacion;
	}
	public Integer getFuera() {
		return fuera;
	}
	public void setFuera(Integer fuera) {
		this.fuera = fuera;
	}
	
	
	

}
