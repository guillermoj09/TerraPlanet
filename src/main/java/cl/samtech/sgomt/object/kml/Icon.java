package cl.samtech.sgomt.object.kml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class Icon implements Serializable{
	
	 String href;

	public String getHref() {
		return href;
	}

	@XmlElement
	public void setHref(String href) {
		this.href = href;
	} 

	
}
