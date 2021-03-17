package cl.samtech.sgomt.form;

public class GeocercasForm {
	
	private Integer id_geo;
	private String  nombre;
	private String  color;
	private String  geomText;
	private String  origen;
	private Integer velocidadAlarma;
	private Integer estadoAlarma;
	private String  correo;
	
	private String  uso;
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getGeomText() {
		return geomText;
	}
	public void setGeomText(String geomText) {
		this.geomText = geomText;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
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
