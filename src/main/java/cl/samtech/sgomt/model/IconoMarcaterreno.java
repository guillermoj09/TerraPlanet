package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the icono_marcaterreno database table.
 * 
 */
@Entity
@Table(name="icono_marcaterreno", schema = "cliente")
@NamedQuery(name="IconoMarcaterreno.findAll", query="SELECT i FROM IconoMarcaterreno i")
public class IconoMarcaterreno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ico_id")
	private Integer icoId;

	@Column(name="ico_nombre")
	private String icoNombre;

	@Column(name="ico_ruta")
	private String icoRuta;

	public IconoMarcaterreno() {
	}

	public Integer getIcoId() {
		return this.icoId;
	}

	public void setIcoId(Integer icoId) {
		this.icoId = icoId;
	}

	public String getIcoNombre() {
		return this.icoNombre;
	}

	public void setIcoNombre(String icoNombre) {
		this.icoNombre = icoNombre;
	}

	public String getIcoRuta() {
		return this.icoRuta;
	}

	public void setIcoRuta(String icoRuta) {
		this.icoRuta = icoRuta;
	}

}