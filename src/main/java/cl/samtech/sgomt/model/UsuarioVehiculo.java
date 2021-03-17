package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuario_vehiculo database table.
 * 
 */
@Entity
@Table(name="usuario_vehiculo", schema="dispositivo")
@NamedQuery(name="UsuarioVehiculo.findAll", query="SELECT u FROM UsuarioVehiculo u")
public class UsuarioVehiculo implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(generator = "usuario_vehiculo_usuveh_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "usuario_vehiculo_usuveh_id_seq", sequenceName = "dispositivo.usuario_vehiculo_usuveh_id_seq",allocationSize=1)
	@Column(name="usuveh_id", unique=true, nullable=false, precision=10, scale=0)
	private Integer usuvehId;
	
	@Column(name="usu_rut_usuario")
	private String usuRutUsuario;
	
	
	public String getVehPatenteVehiculo() {
		return vehPatenteVehiculo;
	}

	public void setVehPatenteVehiculo(String vehPatenteVehiculo) {
		this.vehPatenteVehiculo = vehPatenteVehiculo;
	}

	@Column(name="veh_patente_vehiculo")
	private String vehPatenteVehiculo;

	//bi-directional many-to-one association to Vehiculo
	/*@ManyToOne
	@JoinColumn(name="veh_patente_vehiculo")
	private Vehiculo vehiculo; */

	public UsuarioVehiculo() {
	}

	public String getUsuRutUsuario() {
		return this.usuRutUsuario;
	}

	public void setUsuRutUsuario(String usuRutUsuario) {
		this.usuRutUsuario = usuRutUsuario;
	}

	public Integer getUsuvehId() {
		return this.usuvehId;
	}

	public void setUsuvehId(Integer usuvehId) {
		this.usuvehId = usuvehId;
	}

	/*public Vehiculo getVehiculo() {
		return this.vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}*/

}