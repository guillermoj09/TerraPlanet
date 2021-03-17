package cl.samtech.sgomt.object.kml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class LineStyle implements Serializable{
	
	
	String color;
	
	String width;

	public String getColor() {
		return color;
	}

	@XmlElement()
	public void setColor(String color) {
		this.color = color;
	}

	public String getWidth() {
		return width;
	}

	@XmlElement()
	public void setWidth(String width) {
		this.width = width;
	}

	
	
}
