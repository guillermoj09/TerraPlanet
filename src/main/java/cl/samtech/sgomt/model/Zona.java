package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;

import com.vividsolutions.jts.geom.Geometry;

import java.sql.Timestamp;


/**
 * The persistent class for the zona database table.
 * 
 */
@Entity
@Table(name = "ZONA", schema = "cliente")
@NamedQuery(name="Zona.findAll", query="SELECT z FROM Zona z")
public class Zona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="zon_id")
	private Integer zonId;

	@Column(name="con_fecha_crea")
	private Timestamp conFechaCrea;

	@Column(name="zon_color")
	private String zonColor;

	/*@Column(name="zon_geom")
	private Geometry  zonGeom;*/

	@Column(name="zon_nombre")
	private String zonNombre;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usu_rut_usuario")
	private Usuario usuario;

	public Zona() {
	}
	

	/*public Geometry getZonGeom() {
		return zonGeom;
	}

	public void setZonGeom(Geometry zonGeom) {
		this.zonGeom = zonGeom;
	}*/

	public Integer getZonId() {
		return this.zonId;
	}

	public void setZonId(Integer zonId) {
		this.zonId = zonId;
	}

	public Timestamp getConFechaCrea() {
		return this.conFechaCrea;
	}

	public void setConFechaCrea(Timestamp conFechaCrea) {
		this.conFechaCrea = conFechaCrea;
	}

	public String getZonColor() {
		return this.zonColor;
	}

	public void setZonColor(String zonColor) {
		this.zonColor = zonColor;
	}


	public String getZonNombre() {
		return this.zonNombre;
	}

	public void setZonNombre(String zonNombre) {
		this.zonNombre = zonNombre;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}