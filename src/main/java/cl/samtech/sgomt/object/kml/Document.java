package cl.samtech.sgomt.object.kml;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Document")
public class Document  implements Serializable{
	
	String name;
		
	List<Folder> folders;
	
	List<Style> styles;
	
	List<Placemark> placemarkers;
	
	StyleMap styleMap;
	
	

	public StyleMap getStyleMap() {
		return styleMap;
	}

	@XmlElement(name = "StyleMap")
	public void setStyleMap(StyleMap styleMap) {
		this.styleMap = styleMap;
	}

	public List<Placemark> getPlacemarkers() {
		return placemarkers;
	}

	@XmlElement(name = "Placemark")
	public void setPlacemarkers(List<Placemark> placemarkers) {
		this.placemarkers = placemarkers;
	}

	public List<Style> getStyles() {
		return styles;
	}

	@XmlElement(name = "Style")
	public void setStyles(List<Style> styles) {
		this.styles = styles;
	}

	public String getName() {
		return name;
	}
	
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Folder> getFolders() {
		return folders;
	}

	@XmlElement(name ="Folder")
	public void setFolders(List<Folder> folders) {
		this.folders = folders;
	}
	

}
