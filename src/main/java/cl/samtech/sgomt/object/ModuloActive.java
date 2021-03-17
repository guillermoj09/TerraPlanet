package cl.samtech.sgomt.object;

import java.sql.Timestamp;
import java.util.List;


public class ModuloActive {
	
	
	private Integer modId;

	private Timestamp modFechaCrea;

	private String modNombre;
	
	private List<MenuActive> menus;
	
	private String classdiv;
	
	

	public List<MenuActive> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuActive> menus) {
		this.menus = menus;
	}

	public Integer getModId() {
		return modId;
	}

	public void setModId(Integer modId) {
		this.modId = modId;
	}

	public Timestamp getModFechaCrea() {
		return modFechaCrea;
	}

	public void setModFechaCrea(Timestamp modFechaCrea) {
		this.modFechaCrea = modFechaCrea;
	}

	public String getModNombre() {
		return modNombre;
	}

	public void setModNombre(String modNombre) {
		this.modNombre = modNombre;
	}
	
	public String getClassdiv() {
		return classdiv;
	}

	public void setClassdiv(String classdiv) {
		this.classdiv = classdiv;
	}
	
	


}
