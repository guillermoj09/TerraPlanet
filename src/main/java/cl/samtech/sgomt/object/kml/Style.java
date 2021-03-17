package cl.samtech.sgomt.object.kml;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Style implements Serializable{

	String id;
	
	IconStyle iconStyle;
	
	LineStyle lineStyle;
	
	PolyStyle polyStyle;

	public String getId() {
		return id;
	}

	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}

	public PolyStyle getPolyStyle() {
		return polyStyle;
	}

	@XmlElement(name = "PolyStyle")
	public void setPolyStyle(PolyStyle polyStyle) {
		this.polyStyle = polyStyle;
	}

	public IconStyle getIconStyle() {
		return iconStyle;
	}

	@XmlElement(name = "IconStyle")
	public void setIconStyle(IconStyle iconStyle) {
		this.iconStyle = iconStyle;
	}

	public LineStyle getLineStyle() {
		return lineStyle;
	}

	@XmlElement(name = "LineStyle")
	public void setLineStyle(LineStyle lineStyle) {
		this.lineStyle = lineStyle;
	}
	
	
	
	
}
