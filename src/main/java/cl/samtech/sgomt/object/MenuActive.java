package cl.samtech.sgomt.object;

import java.sql.Timestamp;
import java.util.List;

public class MenuActive {
		
	private Timestamp menFechaCrea;

	private Integer menId;
	
	private String menLink;
	
	private String menNombre;
	
	private String classdiv;
	
	private String target;
	
	private String divider;
	
	private List<SubMenuActive> submenus;
	
	private ModuloActive modulo;
	
	private String menIdMen;
	
	private String valsubme;
	
	
	
	

	public String getValsubme() {
		return valsubme;
	}

	public void setValsubme(String valsubme) {
		this.valsubme = valsubme;
	}

	public String getMenIdMen() {
		return menIdMen;
	}

	public void setMenIdMen(String menIdMen) {
		this.menIdMen = menIdMen;
	}

	public ModuloActive getModulo() {
		return modulo;
	}

	public void setModulo(ModuloActive modulo) {
		this.modulo = modulo;
	}

	public Timestamp getMenFechaCrea() {
		return menFechaCrea;
	}

	public void setMenFechaCrea(Timestamp menFechaCrea) {
		this.menFechaCrea = menFechaCrea;
	}

	public Integer getMenId() {
		return menId;
	}

	public void setMenId(Integer menId) {
		this.menId = menId;
	}

	public String getMenLink() {
		return menLink;
	}

	public void setMenLink(String menLink) {
		this.menLink = menLink;
	}

	public String getMenNombre() {
		return menNombre;
	}

	public void setMenNombre(String menNombre) {
		this.menNombre = menNombre;
	}

	public String getClassdiv() {
		return classdiv;
	}

	public void setClassdiv(String classdiv) {
		this.classdiv = classdiv;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getDivider() {
		return divider;
	}

	public void setDivider(String divider) {
		this.divider = divider;
	}

	public List<SubMenuActive> getSubmenus() {
		return submenus;
	}

	public void setSubmenus(List<SubMenuActive> submenus) {
		this.submenus = submenus;
	}
	
	

}
