package cl.samtech.sgomt.object.kml;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Folder  implements Serializable {
	
	
	String id;
	
	String open;
	
	String name;
	
	List<Style> styles;
			
	StyleMap styleMap;	
	
	List<Placemark> placemarkers;
			
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

	public String getId() {
		return id;
	}

	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}

	public List<Placemark> getPlacemarkers() {
		return placemarkers;
	}

	@XmlElement(name = "Placemark")
	public void setPlacemarkers(List<Placemark> placemarkers) {
		this.placemarkers = placemarkers;
	}

	public String getOpen() {
		return open;
	}

	@XmlElement
	public void setOpen(String open) {
		this.open = open;
	}

	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	
	

}
