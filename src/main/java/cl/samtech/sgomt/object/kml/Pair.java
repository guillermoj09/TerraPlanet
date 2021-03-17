package cl.samtech.sgomt.object.kml;

import javax.xml.bind.annotation.XmlElement;

public class Pair {
	
	
	String key;
	String styleUrl;
	
	public String getKey() {
		return key;
	}
	@XmlElement
	public void setKey(String key) {
		this.key = key;
	}
	public String getStyleUrl() {
		return styleUrl;
	}
	@XmlElement
	public void setStyleUrl(String styleUrl) {
		this.styleUrl = styleUrl;
	}
	
	
}
