package cl.samtech.sgomt.object.kml;

import javax.xml.bind.annotation.XmlAttribute;

public class hotSpot {
	
	String x;
	String y;
	String xunits;
	String yunits;
	public String getX() {
		return x;
	}
	@XmlAttribute
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	@XmlAttribute
	public void setY(String y) {
		this.y = y;
	}
	public String getXunits() {
		return xunits;
	}
	@XmlAttribute
	public void setXunits(String xunits) {
		this.xunits = xunits;
	}
	public String getYunits() {
		return yunits;
	}
	@XmlAttribute
	public void setYunits(String yunits) {
		this.yunits = yunits;
	}
	
	

}
