package cl.samtech.sgomt.service;

import java.awt.Color;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import cl.samtech.sgomt.object.Fechas;
import cl.samtech.sgomt.object.FlechaActive;

public class UtilServicio {
	
	public static long getDiferenciaday(Date fechaInicial, Date fechaFinal){

	    long diferencia = fechaFinal.getTime() - fechaInicial.getTime();
	   
	    long segsMilli = 1000;
	    long minsMilli = segsMilli * 60;
	    long horasMilli = minsMilli * 60;
	    long diasMilli = horasMilli * 24;

	    long diasTranscurridos = diferencia / diasMilli;
	    diferencia = diferencia % diasMilli;

	    long horasTranscurridos = diferencia / horasMilli;
	    diferencia = diferencia % horasMilli;

	    long minutosTranscurridos = diferencia / minsMilli;
	    diferencia = diferencia % minsMilli;

	    long segsTranscurridos = diferencia / segsMilli;
	  	    
	    return diasTranscurridos;


	}
	
	public static long getDiferenciadayHoras(Date fechaInicial, Date fechaFinal){

	    long diferencia = fechaFinal.getTime() - fechaInicial.getTime();
	   
	    long segsMilli = 1000;
	    long minsMilli = segsMilli * 60;
	    long horasMilli = minsMilli * 60;
	    long diasMilli = horasMilli * 24;
	   
	    long horasTranscurridos = diferencia / horasMilli;
	    diferencia = diferencia % horasMilli;
	  	    
	    return horasTranscurridos;


	}
	
  //funcion para configurar fechas para los filtros de inicio, el parametro que recibe son los dias que se va restar o inicia la busqueda	
  public static Fechas getFechasConfDiaParam(String dias, String formatostring){
	
	  Fechas fechas = new Fechas();
	  
	  Calendar calendarin = Calendar.getInstance();
		Calendar calendarfin = Calendar.getInstance();
						
		//Date fecdesde= new Date();
		//Date fechasta= new Date();
		
		calendarin.add(Calendar.DAY_OF_MONTH, Integer.valueOf(dias));	// <- FECHA INICIO CONFIGURACION			
		calendarin.set(Calendar.HOUR_OF_DAY, 00);
		calendarin.set(Calendar.SECOND, 0);
		calendarin.set(Calendar.MINUTE, 0);
		calendarin.set(Calendar.MILLISECOND, 0);
		
		calendarfin.set(Calendar.HOUR_OF_DAY, 23);
		calendarfin.set(Calendar.MINUTE, 59);
		calendarfin.set(Calendar.SECOND, 59);
				
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy"); 
		DateFormat formatteri2 = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
		
		
		String fechain = formatteri.format(calendarin.getTime());
		String fechafin = formatteri.format(calendarfin.getTime());
		
		String fechatimein = formatteri2.format(calendarin.getTime());
		String fechatimefin = formatteri2.format(calendarfin.getTime());
		
		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
		Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());
		
		fechas.setFechain(fechain);
		fechas.setFechafin(fechafin);
		
		fechas.setTimein(timein);
		fechas.setTimefin(timefin);
		
		fechas.setCalendarin(calendarin);
		fechas.setCalendarfin(calendarfin);
		
		fechas.setFechatimein(fechatimein);
		fechas.setFechatimefin(fechatimefin);
		
		
	  return fechas;
	  
  }
  
  public static String getClassEvent(Integer idEvent, String nombreve){
	  	  
	  String classevent = "";
	  
	  if(idEvent == null){
		  
		  idEvent = 0;
	  }
	  
	   switch ( idEvent ) {
	      case 45:
	    	  classevent = "<i class='fa fa-share' title='"+nombreve+"'></i>";
	           break;
	      case 61:
	    	  classevent = "<i class='fa fa-minus-circle' title='"+nombreve+"'></i>";
	           break;
	      case 46:
	    	  classevent = "<i class='fa fa-map-marker' title='"+nombreve+"'></i>";
	           break;
	      case 64:
	    	  classevent = "<i class='fa fa-truck' title='"+nombreve+"'></i>";
	           break;
	      case 65://salidad
	    	  classevent = "<i class='fa fa-arrow-circle-left'></i>";
	           break;      
	      
	      case 18:
	    	  classevent = "<i class='fa fa-unlock' title='"+nombreve+"'></i>";
	           break;
	      case 19:
	    	  classevent = "<i class='fa fa-unlock-alt' title='"+nombreve+"'></i>";
	           break;
	      case 47:
	    	  classevent = "<i class='fa fa-thumb-tack' title='"+nombreve+"'></i>";
	           break;
	      case 51:
	    	  classevent = "<i class='fa fa-plug' title='"+nombreve+"'></i>";
	           break;	
	      case 7:
	    	  classevent = "<i class='fa fa-level-up' title='"+nombreve+"'></i>";
	           break;	
	      case 8:
	    	  classevent = "<i class='fa fa-level-down' title='"+nombreve+"'></i>";
	           break;		           
	      case 0:
	    	  classevent = "";
	           break;           
	      default:
	    	  classevent = nombreve;
	           break;
	      }
	  	  
	  return classevent;
	  
  }
  
  public static String getClassEventJS(Integer idEvent, String nombreve){
  	  
	  String classevent = "";
	  
	  if(idEvent == null){
		  
		  idEvent = 0;
	  }
	  
	   switch ( idEvent ) {
	      case 45:
	    	  classevent = "fa fa-share";
	           break;
	      case 61:
	    	  classevent = "fa fa-minus-circle";
	           break;
	      case 46:
	    	  classevent = "fa fa-map-marker";
	           break;
	      case 64:
	    	  classevent = "fa fa-truck";
	           break;
	      case 65://salidad
	    	  classevent = "fa fa-arrow-circle-left";
	           break;      
	      case 18:
	    	  classevent = "fa fa-unlock";
	           break;
	      case 19:
	    	  classevent = "fa fa-unlock-alt";
	           break;
	      case 47:
	    	  classevent = "fa fa-thumb-tack";
	           break;
	      case 51:
	    	  classevent = "fa fa-plug";
	           break;	
	      case 7:
	    	  classevent = "fa fa-level-up";
	           break;	
	      case 8:
	    	  classevent = "fa fa-level-down";
	           break;
	      case 0:
	    	  classevent = "";
	           break;      
	      default:
	    	  classevent = nombreve;
	           break;
	      }
	  	  
	  return classevent;
	  
  }
  
  public static FlechaActive getFlecha(Integer rutOrientacion){
  	  
	    String nomflecha = "";
		String rutaflecha = "";
		
		FlechaActive flecha = new FlechaActive(); 
		
		if(rutOrientacion >= 0  &&  rutOrientacion < 26){

			nomflecha = "Norte ("+rutOrientacion+"°)";
			rutaflecha = "arriba.gif";

		}
		else if (rutOrientacion >= 26  &&  rutOrientacion < 68){
			
			nomflecha = "Noreste ("+rutOrientacion+"°)";			
			rutaflecha = "arriba-der.gif";
			
		}
		else if (rutOrientacion >= 68  &&  rutOrientacion < 112){
			
			nomflecha = "Este ("+rutOrientacion+"°)";
			rutaflecha = "der.gif";
			
		}
		else if (rutOrientacion >= 112  &&  rutOrientacion < 153){
	
			nomflecha = "Sureste ("+rutOrientacion+"°)";
			rutaflecha = "abajo-der.gif";
	
		}
		else if (rutOrientacion >= 153  &&  rutOrientacion < 186){
	
			nomflecha = "Sur ("+rutOrientacion+"°)";
			rutaflecha = "abajo.gif";
	
		}
		else if (rutOrientacion >= 186  &&  rutOrientacion < 224){
	
			nomflecha = "Suroeste ("+rutOrientacion+"°)";
			rutaflecha = "abajo-izq.gif";
	
		}
		else if (rutOrientacion >= 224  &&  rutOrientacion < 255){
	
			nomflecha = "Oeste ("+rutOrientacion+"°)";
			rutaflecha = "izq.gif";
	
		}
		else if(rutOrientacion >= 255  &&  rutOrientacion <= 360){
	
			nomflecha = "Noroeste ("+rutOrientacion+"°)";
			rutaflecha = "arriba-izq.gif";
	
		}
		
		flecha.setNomflecha(nomflecha);
		flecha.setRutaflecha(rutaflecha);
	
	
	  return flecha;
	  
  }
  
  public static String getClassEventEntradaSalidad(Integer idEvent, String nombreve){
  	  
	  String classevent = "";
	  
	   switch ( idEvent ) {
	     
	      case 64://entrada
	    	  classevent = "<i class='fa fa-truck'></i>";
	           break;
	      case 65://salidad
	    	  classevent = "<i class='fa fa-arrow-circle-left'></i>";
	           break;
	     
	   }
	  return classevent;
	    	
  }
  
 public static String FormatoTimer2(long segundos){//deprecated
  	
		/*Calendar ctiempoactual2 = Calendar.getInstance();
		
		ctiempoactual2.set(Calendar.YEAR, 0);
		ctiempoactual2.set(Calendar.DAY_OF_MONTH, 1);
		ctiempoactual2.set(Calendar.MONTH, 0);
		ctiempoactual2.set(Calendar.HOUR_OF_DAY, 0);
		ctiempoactual2.set(Calendar.MINUTE, 0);
		ctiempoactual2.set(Calendar.MILLISECOND, 0);
		ctiempoactual2.set(Calendar.SECOND, Integer.valueOf(String.valueOf(segundos)));
		
		String tiempoactuals = "";
		DateFormat formatteri2 = new SimpleDateFormat("HH:mm:ss"); 
		
		if(segundos >= 86400){
				
			ctiempoactual2.add(Calendar.DAY_OF_MONTH, -1);
			formatteri2 = new SimpleDateFormat("HH:mm:ss");
			tiempoactuals = String.valueOf(ctiempoactual2.get(Calendar.DAY_OF_MONTH))+"d "+formatteri2.format(ctiempoactual2.getTime());
		}else{
		
		 tiempoactuals = formatteri2.format(ctiempoactual2.getTime());
		
		}*/
	 
	 //int segundos = 5000;
	    TimeZone tz = TimeZone.getTimeZone("UTC");
	    SimpleDateFormat df = new SimpleDateFormat("dd HH:mm:ss");
	    df.format("00 00:00:00");
	    df.setTimeZone(tz);
	    String tiempoactuals = df.format(new Date(segundos));
	    System.out.println(tiempoactuals);
	
		return tiempoactuals;
	
  }
 
 public static String FormatoTimer(long seconds) {
     String tiempoactuals = "";  String dayS=""; String hoursS="";  String minuteS="";  String secondS="";       
     int day = (int)TimeUnit.SECONDS.toDays(seconds);        
     long hours = TimeUnit.SECONDS.toHours(seconds) - (day *24);
     long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds)* 60);
     long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) *60);
     
     //if (day <10) dayS  = "0"+day; else dayS = String.valueOf(day);
     dayS = String.valueOf(day);
     if (hours <10) hoursS  = "0"+hours; else hoursS = String.valueOf(hours);
     if (minute <10) minuteS  = "0"+minute; else minuteS = String.valueOf(minute);
     if (second <10) secondS  = "0"+second; else secondS = String.valueOf(second);

     System.out.println("Day " + day + " Hour " + hours + " Minute " + minute + " Seconds " + second);
     if(seconds >= 86400){
     tiempoactuals = dayS + "d " + hoursS + ":" + minuteS + ":" + secondS;
     }else{
    	
     tiempoactuals = hoursS + ":" + minuteS + ":" + secondS;
    	 
     }
     
     return tiempoactuals;

 }
 
  
  public static Color rondonColor(){
	  
			Random randomGenerator = new Random();
			int r = randomGenerator.nextInt(256);
			int g = randomGenerator.nextInt(256);
			int b = randomGenerator.nextInt(256);
			
			Color color = new Color(r,g,b);
	  
	  return color;
  }
  
  public static long getDiferenciaSeg(Date fechaInicial, Date fechaFinal){

	    long diferencia = fechaFinal.getTime() - fechaInicial.getTime();
	   
	    long segsMilli = 1000;

	    long segsTranscurridos = diferencia / segsMilli;
	  	    
	    return segsTranscurridos;


	}
  
  public static boolean esPar(int numero){
	  
	    if (numero%2==0) return true; else return false;
	}
  
  public static String getRgbColorToKmlColor(String colorrpg, String aa){
	  //#FFFF00
	  //0123456
	  //#RRGGBB
	  //https://www.december.com/html/spec/dechex.html  escala ocacidad
	  if(aa.equals("")){
		  
		  aa = "7f";
	  }
	  String colorkml = "";	  
	  String rr = "";
	  String gg = "";
	  String bb = "";
	  rr = colorrpg.substring(1,3);
	  gg = colorrpg.substring(3,5);
	  bb = colorrpg.substring(5,7);
	  //aa = "7f";
	  // ff 0 ocacity, 00, full trasnarente , 7f intermedio
	  
	  colorkml = aa+bb+gg+rr;	  
	  
	  return colorkml;
	  
  }
  
  public enum OS {

	    WINDOWS,
	    MAC,
	    LINUX,
	    ANDROID,
	    IPHONE,
	    UNKNOWN;

	    public static OS valueOf(HttpServletRequest request) {

	        final String userAgent = request.getHeader("User-Agent");
	        final OS toReturn;

	        if (userAgent == null || userAgent.isEmpty()) {
	            toReturn = UNKNOWN;
	        } else if (userAgent.toLowerCase().contains("windows")) {
	            toReturn = WINDOWS;
	        } else if (userAgent.toLowerCase().contains("mac")) {
	            toReturn = MAC;
	        } else if (userAgent.toLowerCase().contains("x11")) {
	            toReturn = LINUX;
	        } else if (userAgent.toLowerCase().contains("android")) {
	            toReturn = ANDROID;
	        } else if (userAgent.toLowerCase().contains("iphone")) {
	            toReturn = IPHONE;
	        } else {
	            toReturn = UNKNOWN;
	        }

	        return toReturn;
	    }

	}
  
}
