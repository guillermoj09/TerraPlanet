package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the color_geo database table.
 * 
 */
@Entity
@Table(name="color_geo", schema = "cliente")
@NamedQuery(name="ColorGeo.findAll", query="SELECT c FROM ColorGeo c")
public class ColorGeo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="col_id")
	private Integer colId;

	@Column(name="col_codigo")
	private String colCodigo;

	@Column(name="col_descripcion")
	private String colDescripcion;

	public ColorGeo() {
	}

	public Integer getColId() {
		return this.colId;
	}

	public void setColId(Integer colId) {
		this.colId = colId;
	}

	public String getColCodigo() {
		return this.colCodigo;
	}

	public void setColCodigo(String colCodigo) {
		this.colCodigo = colCodigo;
	}

	public String getColDescripcion() {
		return this.colDescripcion;
	}

	public void setColDescripcion(String colDescripcion) {
		this.colDescripcion = colDescripcion;
	}

}