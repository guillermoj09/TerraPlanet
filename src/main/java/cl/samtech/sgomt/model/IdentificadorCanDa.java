package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the identificador_can_da database table.
 * 
 */
@Entity
@Table(name="identificador_can_da")
@NamedQuery(name="IdentificadorCanDa.findAll", query="SELECT i FROM IdentificadorCanDa i")
public class IdentificadorCanDa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ide_identificador")
	private String ideIdentificador;

	@Column(name="ide_energizacion")
	private Integer ideEnergizacion;

	@Column(name="ide_fecha_hora_db")
	private Timestamp ideFechaHoraDb;

	@Column(name="ide_gps")
	private String ideGps;

	@Column(name="ide_id")
	private Integer ideId;

	@Column(name="ide_partida")
	private Integer idePartida;

	public IdentificadorCanDa() {
	}

	public String getIdeIdentificador() {
		return this.ideIdentificador;
	}

	public void setIdeIdentificador(String ideIdentificador) {
		this.ideIdentificador = ideIdentificador;
	}

	public Integer getIdeEnergizacion() {
		return this.ideEnergizacion;
	}

	public void setIdeEnergizacion(Integer ideEnergizacion) {
		this.ideEnergizacion = ideEnergizacion;
	}

	public Timestamp getIdeFechaHoraDb() {
		return this.ideFechaHoraDb;
	}

	public void setIdeFechaHoraDb(Timestamp ideFechaHoraDb) {
		this.ideFechaHoraDb = ideFechaHoraDb;
	}

	public String getIdeGps() {
		return this.ideGps;
	}

	public void setIdeGps(String ideGps) {
		this.ideGps = ideGps;
	}

	public Integer getIdeId() {
		return this.ideId;
	}

	public void setIdeId(Integer ideId) {
		this.ideId = ideId;
	}

	public Integer getIdePartida() {
		return this.idePartida;
	}

	public void setIdePartida(Integer idePartida) {
		this.idePartida = idePartida;
	}

}