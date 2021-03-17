package cl.samtech.sgomt.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the menu database table.
 * 
 */
@Entity
@Table(name = "MENU", schema = "cliente")
@NamedQuery(name="Menu.findAll", query="SELECT m FROM Menu m")
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="men_fecha_crea")
	private Timestamp menFechaCrea;

	@Id 
	@GeneratedValue(generator = "menu_men_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "menu_men_id_seq", sequenceName = "cliente.menu_men_id_seq",allocationSize=1)
	@Column(name="men_id")
	private Integer menId;

	@Column(name="men_link")
	private String menLink;

	@Column(name="men_nombre")
	private String menNombre;
	
	@Column(name="men_estado")
	private Integer menEstado;

	

	public Integer getMenEstado() {
		return menEstado;
	}

	public void setMenEstado(Integer menEstado) {
		this.menEstado = menEstado;
	}

	//bi-directional many-to-one association to Modulo
	@ManyToOne
	@JoinColumn(name="mod_id_modulo")
	private Modulo modulo;
	
	/*
	//relacion asi misma
		//bi-directional many-to-one association to Modulo
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="men_id_men")
		private Menu parentmenu;
		
		//bi-directional many-to-one association to Menu
		@OneToMany(mappedBy="parentmenu",fetch = FetchType.LAZY )
		private List<Menu> menus;
	//fin relacion asi misma	
	*/	
		
	@Column(name="men_id_men")
	private Integer menIdMen;

	@JoinTable(schema="cliente",
				name="usuario_menu"
				, joinColumns={
					@JoinColumn(name="men_id_menu", referencedColumnName="men_id")
					}
				, inverseJoinColumns={
					@JoinColumn(name="usu_rut_usuario", referencedColumnName="usu_rut")
					}
				)
	private List<Usuario> usuarios;	
		
		

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Menu() {
	}

	public Timestamp getMenFechaCrea() {
		return this.menFechaCrea;
	}

	public void setMenFechaCrea(Timestamp menFechaCrea) {
		this.menFechaCrea = menFechaCrea;
	}

	public Integer getMenId() {
		return this.menId;
	}

	public void setMenId(Integer menId) {
		this.menId = menId;
	}

	public String getMenLink() {
		return this.menLink;
	}

	public void setMenLink(String menLink) {
		this.menLink = menLink;
	}

	public String getMenNombre() {
		return this.menNombre;
	}

	public void setMenNombre(String menNombre) {
		this.menNombre = menNombre;
	}

	public Modulo getModulo() {
		return this.modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public Integer getMenIdMen() {
		return menIdMen;
	}

	public void setMenIdMen(Integer menIdMen) {
		this.menIdMen = menIdMen;
	}

	
}