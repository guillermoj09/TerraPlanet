package cl.samtech.sgomt.object.kml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Polygon {
	
	String tessellate;
	
	String extrude;
	
	String altitudeMode;
	
	OuterBoundaryIs outerBoundaryIs;
	
	InnerBoundaryIs innerBoundaryIs;

	public String getExtrude() {
		return extrude;
	}
	

	public String getTessellate() {
		return tessellate;
	}

	@XmlAttribute
	public void setTessellate(String tessellate) {
		this.tessellate = tessellate;
	}

	@XmlElement
	public void setExtrude(String extrude) {
		this.extrude = extrude;
	}

	public String getAltitudeMode() {
		return altitudeMode;
	}

	@XmlElement
	public void setAltitudeMode(String altitudeMode) {
		this.altitudeMode = altitudeMode;
	}

	public OuterBoundaryIs getOuterBoundaryIs() {
		return outerBoundaryIs;
	}

	@XmlElement(name = "outerBoundaryIs")
	public void setOuterBoundaryIs(OuterBoundaryIs outerBoundaryIs) {
		this.outerBoundaryIs = outerBoundaryIs;
	}

	public InnerBoundaryIs getInnerBoundaryIs() {
		return innerBoundaryIs;
	}

	@XmlElement(name = "innerBoundaryIs")
	public void setInnerBoundaryIs(InnerBoundaryIs innerBoundaryIs) {
		this.innerBoundaryIs = innerBoundaryIs;
	}
	
	

}
