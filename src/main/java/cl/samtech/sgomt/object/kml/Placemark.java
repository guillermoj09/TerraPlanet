package cl.samtech.sgomt.object.kml;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Placemark  implements Serializable{
	
	 String name;

	String styleUrl;
	
	String description;
	
	
	List<LineString> lineStrings;
	
	
	List<Point> points;
	
	
	Style style;
	
	Polygon polygon;
	
	List<Style> styles;
	
	StyleMap styleMap;
	

	public List<Style> getStyles() {
		return styles;
	}

	@XmlElement(name = "Style")
	public void setStyles(List<Style> styles) {
		this.styles = styles;
	}

	public StyleMap getStyleMap() {
		return styleMap;
	}

	@XmlElement(name = "StyleMap")
	public void setStyleMap(StyleMap styleMap) {
		this.styleMap = styleMap;
	}

	public Polygon getPolygon() {
		return polygon;
	}

	@XmlElement(name = "Polygon")
	public void setPolygon(Polygon polygon) {
		this.polygon = polygon;
	}

	public Style getStyle() {
		return style;
	}

	@XmlElement(name = "Style")
	public void setStyle(Style style) {
		this.style = style;
	}

	public List<Point> getPoints() {
		return points;
	}

	@XmlElement(name = "Point")
	public void setPoints(List<Point> points) {
		this.points = points;
	}

	public String getDescription() {
		return description;
	}

	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public String getStyleUrl() {
		return styleUrl;
	}

	@XmlElement
	public void setStyleUrl(String styleUrl) {
		this.styleUrl = styleUrl;
	}

	public List<LineString> getLineStrings() {
		return lineStrings;
	}

	@XmlElement(name = "LineString")
	public void setLineStrings(List<LineString> lineStrings) {
		this.lineStrings = lineStrings;
	}
	
	
	
	

}
