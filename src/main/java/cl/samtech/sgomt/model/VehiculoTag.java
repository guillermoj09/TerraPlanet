package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the vehiculo_tag database table.
 * 
 */
@Entity
@Table(name="vehiculo_tag", schema="dispositivo")
@NamedQuery(name="VehiculoTag.findAll", query="SELECT v FROM VehiculoTag v")
public class VehiculoTag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "vehiculo_tag_vehtag_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "vehiculo_tag_vehtag_id_seq", sequenceName = "dispositivo.vehiculo_tag_vehtag_id_seq",allocationSize=1)
	@Column(name="vehtag_id")
	private Integer vehtagId;

	@Column(name="tag_id_tag")
	private String tagIdTag;

	@Column(name="veh_patente_vehiculo")
	private String vehPatenteVehiculo;

	public VehiculoTag() {
	}

	public Integer getVehtagId() {
		return this.vehtagId;
	}

	public void setVehtagId(Integer vehtagId) {
		this.vehtagId = vehtagId;
	}

	public String getTagIdTag() {
		return this.tagIdTag;
	}

	public void setTagIdTag(String tagIdTag) {
		this.tagIdTag = tagIdTag;
	}

	public String getVehPatenteVehiculo() {
		return this.vehPatenteVehiculo;
	}

	public void setVehPatenteVehiculo(String vehPatenteVehiculo) {
		this.vehPatenteVehiculo = vehPatenteVehiculo;
	}

}