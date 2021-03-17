package cl.samtech.sgomt.object.kml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class LineString  implements Serializable{
	
	 String tessellate;
	
	 String coordinates;
	 

	public String getTessellate() {
		return tessellate;
	}

	@XmlElement
	public void setTessellate(String tessellate) {
		this.tessellate = tessellate;
	}

	public String getCoordinates() {
		return coordinates;
	}

	@XmlElement
	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
	
	

}
