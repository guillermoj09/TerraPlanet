package cl.samtech.sgomt.object.kml;

import javax.xml.bind.annotation.XmlElement;

public class PolyStyle {
	
	String color;
	
	String outline;

	public String getColor() {
		return color;
	}

	@XmlElement
	public void setColor(String color) {
		this.color = color;
	}

	public String getOutline() {
		return outline;
	}

	@XmlElement
	public void setOutline(String outline) {
		this.outline = outline;
	}
	
	

}
