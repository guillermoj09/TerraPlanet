package cl.samtech.sgomt.object;

import java.util.List;


public class GeocercasActive {
	
	private Integer id_geo;
	private String nombre;
	private String descripcion;
	private String tipo;
	private String lat0;
	private String lon0;
	private String lat1;
	private String lon1;
	private String lat2;
	private String lon2;
	private String lat3;
	private String lon3;
	private String lat4;
	private String lon4;
	private String geomText;
	private String color;
	private String area;	
	private List<CoordenadasActive> coordenadas;
	
	
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getLat0() {
		return lat0;
	}
	public void setLat0(String lat0) {
		this.lat0 = lat0;
	}
	public String getLon0() {
		return lon0;
	}
	public void setLon0(String lon0) {
		this.lon0 = lon0;
	}
	public String getLat1() {
		return lat1;
	}
	public void setLat1(String lat1) {
		this.lat1 = lat1;
	}
	public String getLon1() {
		return lon1;
	}
	public void setLon1(String lon1) {
		this.lon1 = lon1;
	}
	public String getLat2() {
		return lat2;
	}
	public void setLat2(String lat2) {
		this.lat2 = lat2;
	}
	public String getLon2() {
		return lon2;
	}
	public void setLon2(String lon2) {
		this.lon2 = lon2;
	}
	public String getLat3() {
		return lat3;
	}
	public void setLat3(String lat3) {
		this.lat3 = lat3;
	}
	public String getLon3() {
		return lon3;
	}
	public void setLon3(String lon3) {
		this.lon3 = lon3;
	}
	public String getLat4() {
		return lat4;
	}
	public void setLat4(String lat4) {
		this.lat4 = lat4;
	}
	public String getLon4() {
		return lon4;
	}
	public void setLon4(String lon4) {
		this.lon4 = lon4;
	}
	public String getGeomText() {
		return geomText;
	}
	public void setGeomText(String geomText) {
		this.geomText = geomText;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public List<CoordenadasActive> getCoordenadas() {
		return coordenadas;
	}
	public void setCoordenadas(List<CoordenadasActive> coordenadas) {
		this.coordenadas = coordenadas;
	}

}
