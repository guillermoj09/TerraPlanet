package cl.samtech.sgomt.object.kml;

import javax.xml.bind.annotation.XmlElement;

public class OuterBoundaryIs {
	
	LinearRing linearRing;

	public LinearRing getLinearRing() {
		return linearRing;
	}

	@XmlElement(name = "LinearRing")
	public void setLinearRing(LinearRing linearRing) {
		this.linearRing = linearRing;
	}

	
}
