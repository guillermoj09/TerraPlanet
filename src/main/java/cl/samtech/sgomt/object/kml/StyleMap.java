package cl.samtech.sgomt.object.kml;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class StyleMap {
	
	String id;
	List<Pair> pairs;
	
	public String getId() {
		return id;
	}
    
	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}

	public List<Pair> getPairs() {
		return pairs;
	}
	
	@XmlElement(name = "Pair")
	public void setPairs(List<Pair> pairs) {
		this.pairs = pairs;
	}

}
