package cl.samtech.sgomt.object;

import java.sql.Timestamp;
import java.util.Calendar;

public class Fechas {
	
	private String fechain;
	private String fechafin;	
	private Timestamp timein;
	private Timestamp timefin;
	private Calendar calendarin;
	private Calendar calendarfin;
	
	private String fechatimein;
	private String fechatimefin;
	
	
	
	
	public String getFechatimein() {
		return fechatimein;
	}
	public void setFechatimein(String fechatimein) {
		this.fechatimein = fechatimein;
	}
	public String getFechatimefin() {
		return fechatimefin;
	}
	public void setFechatimefin(String fechatimefin) {
		this.fechatimefin = fechatimefin;
	}
	public Calendar getCalendarin() {
		return calendarin;
	}
	public void setCalendarin(Calendar calendarin) {
		this.calendarin = calendarin;
	}
	public Calendar getCalendarfin() {
		return calendarfin;
	}
	public void setCalendarfin(Calendar calendarfin) {
		this.calendarfin = calendarfin;
	}
	public String getFechain() {
		return fechain;
	}
	public void setFechain(String fechain) {
		this.fechain = fechain;
	}
	public String getFechafin() {
		return fechafin;
	}
	public void setFechafin(String fechafin) {
		this.fechafin = fechafin;
	}
	public Timestamp getTimein() {
		return timein;
	}
	public void setTimein(Timestamp timein) {
		this.timein = timein;
	}
	public Timestamp getTimefin() {
		return timefin;
	}
	public void setTimefin(Timestamp timefin) {
		this.timefin = timefin;
	}
	
	

}
