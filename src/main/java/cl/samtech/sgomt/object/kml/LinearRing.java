package cl.samtech.sgomt.object.kml;

import javax.xml.bind.annotation.XmlElement;

public class LinearRing {
	
	String coordinates;

	public String getCoordinates() {
		return coordinates;
	}

	@XmlElement
	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
	
	

}
