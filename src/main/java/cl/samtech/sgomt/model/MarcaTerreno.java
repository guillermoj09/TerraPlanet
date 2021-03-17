package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the marca_terreno database table.
 * 
 */
@Entity
@Table(name="marca_terreno", schema = "cliente")
@NamedQuery(name="MarcaTerreno.findAll", query="SELECT m FROM MarcaTerreno m")
public class MarcaTerreno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="mt_id")
	private Integer mtId;

	@Column(name="mt_color")
	private Integer mtColor;

	@Column(name="mt_descripcion")
	private String mtDescripcion;

	@Column(name="mt_este")
	private String mtEste;

	@Column(name="mt_fecha_crea")
	private Timestamp mtFechaCrea;

	/*@Column(name="mt_geom")
	private Object mtGeom; */

	@Column(name="mt_icono")
	private Integer mtIcono;

	@Column(name="mt_lat")
	private String mtLat;

	@Column(name="mt_lon")
	private String mtLon;

	@Column(name="mt_muestra_mapa")
	private Integer mtMuestraMapa;

	@Column(name="mt_nombre")
	private String mtNombre;

	@Column(name="mt_norte")
	private String mtNorte;

	@Column(name="mt_paradero")
	private Integer mtParadero;

	@Column(name="mt_vigencia")
	private Integer mtVigencia;

	@Column(name="usu_rut_usuario")
	private String usuRutUsuario;

	public MarcaTerreno() {
	}

	public Integer getMtId() {
		return this.mtId;
	}

	public void setMtId(Integer mtId) {
		this.mtId = mtId;
	}

	public Integer getMtColor() {
		return this.mtColor;
	}

	public void setMtColor(Integer mtColor) {
		this.mtColor = mtColor;
	}

	public String getMtDescripcion() {
		return this.mtDescripcion;
	}

	public void setMtDescripcion(String mtDescripcion) {
		this.mtDescripcion = mtDescripcion;
	}

	public String getMtEste() {
		return this.mtEste;
	}

	public void setMtEste(String mtEste) {
		this.mtEste = mtEste;
	}

	public Timestamp getMtFechaCrea() {
		return this.mtFechaCrea;
	}

	public void setMtFechaCrea(Timestamp mtFechaCrea) {
		this.mtFechaCrea = mtFechaCrea;
	}

	/*public Object getMtGeom() {
		return this.mtGeom;
	}

	public void setMtGeom(Object mtGeom) {
		this.mtGeom = mtGeom;
	}*/

	public Integer getMtIcono() {
		return this.mtIcono;
	}

	public void setMtIcono(Integer mtIcono) {
		this.mtIcono = mtIcono;
	}

	public String getMtLat() {
		return this.mtLat;
	}

	public void setMtLat(String mtLat) {
		this.mtLat = mtLat;
	}

	public String getMtLon() {
		return this.mtLon;
	}

	public void setMtLon(String mtLon) {
		this.mtLon = mtLon;
	}

	public Integer getMtMuestraMapa() {
		return this.mtMuestraMapa;
	}

	public void setMtMuestraMapa(Integer mtMuestraMapa) {
		this.mtMuestraMapa = mtMuestraMapa;
	}

	public String getMtNombre() {
		return this.mtNombre;
	}

	public void setMtNombre(String mtNombre) {
		this.mtNombre = mtNombre;
	}

	public String getMtNorte() {
		return this.mtNorte;
	}

	public void setMtNorte(String mtNorte) {
		this.mtNorte = mtNorte;
	}

	public Integer getMtParadero() {
		return this.mtParadero;
	}

	public void setMtParadero(Integer mtParadero) {
		this.mtParadero = mtParadero;
	}

	public Integer getMtVigencia() {
		return this.mtVigencia;
	}

	public void setMtVigencia(Integer mtVigencia) {
		this.mtVigencia = mtVigencia;
	}

	public String getUsuRutUsuario() {
		return this.usuRutUsuario;
	}

	public void setUsuRutUsuario(String usuRutUsuario) {
		this.usuRutUsuario = usuRutUsuario;
	}

}