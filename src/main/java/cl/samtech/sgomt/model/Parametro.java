package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the parametros database table.
 * 
 */
@Entity
@Table(name="parametros", schema = "cliente")
@NamedQuery(name="Parametro.findAll", query="SELECT p FROM Parametro p")
public class Parametro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="par_id")
	private Integer parId;

	@Column(name="par_descripcion")
	private String parDescripcion;

	@Column(name="par_valor")
	private Integer parValor;

	public Parametro() {
	}

	public Integer getParId() {
		return this.parId;
	}

	public void setParId(Integer parId) {
		this.parId = parId;
	}

	public String getParDescripcion() {
		return this.parDescripcion;
	}

	public void setParDescripcion(String parDescripcion) {
		this.parDescripcion = parDescripcion;
	}

	public Integer getParValor() {
		return this.parValor;
	}

	public void setParValor(Integer parValor) {
		this.parValor = parValor;
	}

}