package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the turno database table.
 * 
 */
@Entity
@Table(name = "TURNO", schema = "cliente")
@NamedQuery(name="Turno.findAll", query="SELECT t FROM Turno t")
public class Turno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="tur_id")
	private Integer turId;

	@Column(name="tur_desde")
	private Timestamp turDesde;

	@Column(name="tur_hasta")
	private Timestamp turHasta;

	@Column(name="tur_hora_fin")
	private String turHoraFin;

	@Column(name="tur_hora_fin2")
	private String turHoraFin2;

	@Column(name="tur_hora_ini")
	private String turHoraIni;

	@Column(name="tur_hora_ini2")
	private String turHoraIni2;

	@Column(name="tur_nombre")
	private String turNombre;

	@Column(name="tur_tipo")
	private Integer turTipo;

	@Column(name="usu_rut_usuario")
	private String usuRutUsuario;

	public Turno() {
	}

	public Integer getTurId() {
		return this.turId;
	}

	public void setTurId(Integer turId) {
		this.turId = turId;
	}

	public Timestamp getTurDesde() {
		return this.turDesde;
	}

	public void setTurDesde(Timestamp turDesde) {
		this.turDesde = turDesde;
	}

	public Timestamp getTurHasta() {
		return this.turHasta;
	}

	public void setTurHasta(Timestamp turHasta) {
		this.turHasta = turHasta;
	}

	public String getTurHoraFin() {
		return this.turHoraFin;
	}

	public void setTurHoraFin(String turHoraFin) {
		this.turHoraFin = turHoraFin;
	}

	public String getTurHoraFin2() {
		return this.turHoraFin2;
	}

	public void setTurHoraFin2(String turHoraFin2) {
		this.turHoraFin2 = turHoraFin2;
	}

	public String getTurHoraIni() {
		return this.turHoraIni;
	}

	public void setTurHoraIni(String turHoraIni) {
		this.turHoraIni = turHoraIni;
	}

	public String getTurHoraIni2() {
		return this.turHoraIni2;
	}

	public void setTurHoraIni2(String turHoraIni2) {
		this.turHoraIni2 = turHoraIni2;
	}

	public String getTurNombre() {
		return this.turNombre;
	}

	public void setTurNombre(String turNombre) {
		this.turNombre = turNombre;
	}

	public Integer getTurTipo() {
		return this.turTipo;
	}

	public void setTurTipo(Integer turTipo) {
		this.turTipo = turTipo;
	}

	public String getUsuRutUsuario() {
		return this.usuRutUsuario;
	}

	public void setUsuRutUsuario(String usuRutUsuario) {
		this.usuRutUsuario = usuRutUsuario;
	}

}