package cl.samtech.sgomt.object.kml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class IconStyle implements Serializable{
		
	Icon icon;
	String scala;
	hotSpot hotSpot;
	

	public hotSpot getHotSpot() {
		return hotSpot;
	}

	@XmlElement(name = "hotSpot")
	public void setHotSpot(hotSpot hotSpot) {
		this.hotSpot = hotSpot;
	}

	public String getScala() {
		return scala;
	}

	@XmlElement
	public void setScala(String scala) {
		this.scala = scala;
	}

	public Icon getIcon() {
		return icon;
	}

	@XmlElement(name = "Icon")
	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	
}
