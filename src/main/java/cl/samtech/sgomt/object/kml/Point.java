package cl.samtech.sgomt.object.kml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class Point implements Serializable{
	
	 String coordinates;

	public String getCoordinates() {
		return coordinates;
	}

	@XmlElement
	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}


}
