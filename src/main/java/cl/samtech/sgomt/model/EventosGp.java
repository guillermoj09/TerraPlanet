package cl.samtech.sgomt.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the eventos_gps database table.
 * 
 */
@Entity
@Table(name="eventos_gps", schema = "cliente")
@NamedQuery(name="EventosGp.findAll", query="SELECT e FROM EventosGp e")
public class EventosGp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="eve_id_id")
	private Integer eveIdId;

	@Column(name="eve_icono")
	private String eveIcono;

	@Column(name="eve_nombre")
	private String eveNombre;

	@Column(name="eve_rut_cliente")
	private String eveRutCliente;

	@Column(name="eve_ver_historico")
	private Integer eveVerHistorico;
	
	//bi-directional many-to-one association to Usuario
		@OneToMany(mappedBy="eventosGp")
		private List<DatosGp> datosGp;
		
	//bi-directional many-to-one association to Usuario
		@OneToMany(mappedBy="eventosGp")
		private List<DatosTolva> datosTolva;
		
	

	public List<DatosGp> getDatosGp() {
			return datosGp;
		}

		public void setDatosGp(List<DatosGp> datosGp) {
			this.datosGp = datosGp;
		}

		public List<DatosTolva> getDatosTolva() {
			return datosTolva;
		}

		public void setDatosTolva(List<DatosTolva> datosTolva) {
			this.datosTolva = datosTolva;
		}

	public EventosGp() {
	}

	public Integer getEveIdId() {
		return this.eveIdId;
	}

	public void setEveIdId(Integer eveIdId) {
		this.eveIdId = eveIdId;
	}

	public String getEveIcono() {
		return this.eveIcono;
	}

	public void setEveIcono(String eveIcono) {
		this.eveIcono = eveIcono;
	}

	public String getEveNombre() {
		return this.eveNombre;
	}

	public void setEveNombre(String eveNombre) {
		this.eveNombre = eveNombre;
	}

	public String getEveRutCliente() {
		return this.eveRutCliente;
	}

	public void setEveRutCliente(String eveRutCliente) {
		this.eveRutCliente = eveRutCliente;
	}

	public Integer getEveVerHistorico() {
		return this.eveVerHistorico;
	}

	public void setEveVerHistorico(Integer eveVerHistorico) {
		this.eveVerHistorico = eveVerHistorico;
	}

}