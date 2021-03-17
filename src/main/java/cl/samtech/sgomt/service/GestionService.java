package cl.samtech.sgomt.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import cl.samtech.sgomt.model.Vehiculo;
import cl.samtech.sgomt.object.EventActive;
import cl.samtech.sgomt.object.FlechaActive;
import cl.samtech.sgomt.object.HistoricoActive;
import cl.samtech.sgomt.object.PatentesActive;
import cl.samtech.sgomt.object.ReporteAlarmaRutaActive;
import cl.samtech.sgomt.object.ReporteDetencionesSosActive;
import cl.samtech.sgomt.object.ReporteInformeAlarmaCondActive;
import cl.samtech.sgomt.object.ReporteInformeRendimientoActive;
import cl.samtech.sgomt.object.ReporteTiempoConduccionActive;
import cl.samtech.sgomt.object.ReportetInformeralentiCanActive;
import cl.samtech.sgomt.object.kml.Document;
import cl.samtech.sgomt.object.kml.Folder;
import cl.samtech.sgomt.object.kml.Icon;
import cl.samtech.sgomt.object.kml.IconStyle;
import cl.samtech.sgomt.object.kml.LineString;
import cl.samtech.sgomt.object.kml.LineStyle;
import cl.samtech.sgomt.object.kml.Placemark;
import cl.samtech.sgomt.object.kml.Point;
import cl.samtech.sgomt.object.kml.Style;


public class GestionService {

	private static EntityManager em;
	private static EntityManager em1;
	private static EntityManager em2;
	private static EntityManager em3;
	private static EntityManager em4;

	//private static String log4jConfPath = "/opt/tomcat9/webapps/web/log4j.properties";
	
	//private static String log4jConfPath = "C:\\log4j.properties";
	final static Logger logger = Logger.getLogger(ReporteService.class);


	public static List<EventActive> findEventos(String usuario, String clave) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gpsf");
		
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy"); //dd/MM/yyyy
		em = emf.createEntityManager();


		StoredProcedureQuery query=em.createStoredProcedureQuery("MM_Consultas_Historico_pass");
		query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN); // 0
	    query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN); // ''
	    query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN); // 23
	    query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN); // usuario
	    query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN); // clave

	     query.setParameter(1, "0");
	     query.setParameter(2, "");
		 query.setParameter(3, "23");
	     query.setParameter(4, usuario);
		 query.setParameter(5, clave);

		 ///////////////////////////////////////


		 ArrayList<EventActive> mlist = new ArrayList<EventActive>();

		  query.execute();

	 		List<Object[]> eventlist = query.getResultList();

	 		Integer i = 0;
	 		try {
				
			
	 		for (Object[] event : eventlist) {
				i++;

				EventActive eventactive = new EventActive();

					BigDecimal id = (BigDecimal) event[0];
				    String nombre = (String) event[1];

				    eventactive.setId_evento(String.valueOf(id));
				    eventactive.setNombre(nombre);

				    mlist.add(eventactive);

	 		}
	 		
	 		} catch (Exception e) {
				// TODO: handle exception
			}

	 		em.clear();
	 		em.close();


	    return  mlist;
	}


public static List<PatentesActive> findPatentes(String usuario, String clave) {

		// 	patentes
		//SQL="MM_Consultas_Historico_pass 0,'',11,'"&session("usuario")&"','"&session("clave_us")&"'"

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gpsf");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy"); //dd/MM/yyyy
		em1 = emf.createEntityManager();


		StoredProcedureQuery query=em1.createStoredProcedureQuery("MM_Consultas_Historico_pass");
		query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN); // 0
	    query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN); // ''
	    query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN); // 23
	    query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN); // usuario
	    query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN); // clave

	     query.setParameter(1, "0");
	     query.setParameter(2, "");
		 query.setParameter(3, "11");
	     query.setParameter(4, usuario);
		 query.setParameter(5, clave);

		 ///////////////////////////////////////


		 ArrayList<PatentesActive> mlist = new ArrayList<PatentesActive>();

		  query.execute();

	 		List<Object[]> eventlist = query.getResultList();

	 		Integer i = 0;

	 		for (Object[] event : eventlist) {
				i++;

				PatentesActive patenteactive = new PatentesActive();


				    String id = (String) event[0];
				    String nombre = (String) event[1];
				    String ninterno = (String) event[0];

				    patenteactive.setIdPatente(id);
				    patenteactive.setPatente(nombre);
				    patenteactive.setnInterno(ninterno);

				    mlist.add(patenteactive);

	 		}

	 		em1.clear();
	 		em1.close();


	    return  mlist;
	}

//usuLogin, fechas.getFechatimein(), fechas.getFechatimefin(),alar, busqueda, clave, sw
public static List<ReporteInformeAlarmaCondActive> findInformeAlarmaCond(String usuario, String desde, String hasta,String alar, String busqueda, String clave, String sw ){

	String fechanulas = "";

	//dd-MM-yyyy
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("gpsf");
	DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); //dd/MM/yyyy
	em = emf.createEntityManager();


	Calendar calendarin = Calendar.getInstance();
	Calendar calendarfin = Calendar.getInstance();


	Date fecdesde= new Date();
	Date fechasta= new Date();

	//si las fechas vienen nulo se calcula el mes completo
	if(desde.equals("") && hasta.equals("")){

		fechanulas = "S";

	}

	try {
		fecdesde = formatteri.parse(desde);
		fechasta=  formatteri.parse(hasta);
	} catch (ParseException e) {

		e.printStackTrace();
	}

	//configuro las hora inicio y fin
	calendarin.setTime(fecdesde);
	
	//si las fechas vienen nul se resta el mes a la fecha inicio y fecha fin queda como dia hoy
	if(fechanulas.equals("S")){

		calendarfin.add(Calendar.DAY_OF_MONTH, -2);

	}

	calendarfin.setTime(fechasta);

	//las convierto timestamp para insertarlas en el query
	Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
	Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

	//Configuracion de inicio, todas las faenas,
			//'usuario','fecha,'fecha2',100,'reg_pagina','alar','busqueda','clave_us','sw'
			//                                  0          5        0                   4

			//descripcion de los parametros que recibe el procedure en sqlserver
			//alar (evento)
			//if alar "" -> alar= 5
			//else alar = evento

			//faena seleccionada sw 2
			//if faena es 0 - > sw 4

			//patente seleccionada sw 1

			//conductor seleccioando sw 3

	//recibe la fecha string, se deja configurado la fecha timestamp para futura migracion
	 StoredProcedureQuery query=em.createStoredProcedureQuery("MM_P_NoConformidadesCondicionPorFaena_CPE2_pass");
	 query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN); // usuario
     query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN); //fecha in
     query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN); //fechan fin
     query.registerStoredProcedureParameter(4, int.class, ParameterMode.IN); // 100
     query.registerStoredProcedureParameter(5, int.class, ParameterMode.IN); // reg_pagina
     query.registerStoredProcedureParameter(6, int.class, ParameterMode.IN); // alar
     query.registerStoredProcedureParameter(7, String.class, ParameterMode.IN); // busqueda
     query.registerStoredProcedureParameter(8, String.class, ParameterMode.IN); // clave_us
     query.registerStoredProcedureParameter(9, int.class, ParameterMode.IN); // sw  Tipo

     //String usuario, String desde, String hasta,String alar, String busqueda, String clave, String sw
     query.setParameter(1, usuario);
     query.setParameter(2, desde);
	 query.setParameter(3, hasta);
	 query.setParameter(4, 100);
	 query.setParameter(5, 0);
	 query.setParameter(6, Integer.valueOf(alar));
	 query.setParameter(7, busqueda);
	 query.setParameter(8, clave);
	 query.setParameter(9, Integer.valueOf(sw));


	 query.execute();


	ArrayList<ReporteInformeAlarmaCondActive> rlist = new ArrayList<ReporteInformeAlarmaCondActive>();

	List<Object[]> mapalist = query.getResultList();
		Integer i = 0;

		for (Object[] ruta : mapalist) {
		i++;

		ReporteInformeAlarmaCondActive reporteInformeAlarmaCondActive = new ReporteInformeAlarmaCondActive();

		   
	// 0 		1			2			3		4			5			6		7		8		9						10		11						12							13																14			15			16					17								18						19			
	//temp  faena       id_vehiculo   patente ruta       lat         lon       HDG  id_event   date_date              spd    chofer					rx_date                    Alarma 															tercero		max_reg	  	num_interno 		ubicacion						ini_date				tiempo detenido
	//1	  Operaciones UL34			FGZS92	NULL	-33.40004	-070.74735	343	  47	    2018-12-18 21:14:03.000	 0	  Guillermo Gonzalez	2018-12-18 21:14:07.550	No cumple descanso de 8 Hrs entre las 07:00:01  y las 20:59:59	Guillermo Gonzalez	535			Hilux			A 4.3 Km. de Aeropuerto Amb - Stgo.	2018-12-18 18:02:59.000	NULL

		    		    
		    String patente = (String)ruta[3];
		    String alarma = (String)ruta[13];
		    String ubicacion = (String) ruta[17];
		    String conductor = (String) ruta[11];
		    Timestamp fecha = (Timestamp) ruta[9];
		    String spd = (String) ruta[10];
		    String lat = (String) ruta[5];
		    String lon = (String) ruta[6];
		    
		    String hdg = (String) ruta[7];
		    String idVehiculo =  (String) ruta[2];		
		    
		    
		    //Reporte
		    reporteInformeAlarmaCondActive.setPatente(patente);
		    reporteInformeAlarmaCondActive.setAlarma(alarma);
		    reporteInformeAlarmaCondActive.setUbicacion(ubicacion);
		    reporteInformeAlarmaCondActive.setConductor(conductor);
		    reporteInformeAlarmaCondActive.setFecha(fecha);
		    reporteInformeAlarmaCondActive.setVel(spd);
		    reporteInformeAlarmaCondActive.setLat(lat);
		    reporteInformeAlarmaCondActive.setLon(lon);
		    
		    reporteInformeAlarmaCondActive.setHdg(hdg);
		    reporteInformeAlarmaCondActive.setIdVehicle(idVehiculo);
		    
		    rlist.add(reporteInformeAlarmaCondActive);

		}

	em.clear();
	em.close();

	return rlist;
	}


	
public static List<HistoricoActive> findHistorico(String usuario, String clave, String fechaDesde ,String fechaHasta ,String Patente, String velocidad ) {
		
		// 	historico
		//SQL="basegpsf..RUT_Informe_Ruta_MM_pass_segmentado_eve 'emexcosa','UE71','04-12-2018 16:53:00','04-01-2019 16:53:00',100,'0','0',0,'emexcosa','emexco7688','KDRG31',0,'0'"
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gpsf");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy"); //dd/MM/yyyy
		em2 = emf.createEntityManager();	
				
		
		StoredProcedureQuery query=em2.createStoredProcedureQuery("RUT_Informe_Ruta_MM_pass_segmentado_eve");      
		query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN); // usuario
	    query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN); // id_gps
	    query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN); // fecha_inicio	    
	    query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN); // fecha_fin
	    query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN); // max_pagina
	    query.registerStoredProcedureParameter(6, String.class, ParameterMode.IN); // num_pagina
	    query.registerStoredProcedureParameter(7, String.class, ParameterMode.IN); // velocidad
	    query.registerStoredProcedureParameter(8, String.class, ParameterMode.IN); // sw	    
	    query.registerStoredProcedureParameter(9, String.class, ParameterMode.IN); // login2
	    query.registerStoredProcedureParameter(10, String.class, ParameterMode.IN); // pw
	    query.registerStoredProcedureParameter(11, String.class, ParameterMode.IN); // patente
	    query.registerStoredProcedureParameter(12, String.class, ParameterMode.IN); // tor_reg
	    query.registerStoredProcedureParameter(13, String.class, ParameterMode.IN); // eve	
	    
	     query.setParameter(1, usuario);  
	     query.setParameter(2, "");
		 query.setParameter(3, fechaDesde);
	     query.setParameter(4, fechaHasta);
		 query.setParameter(5, "100000");
		 query.setParameter(6, "0");  
	     query.setParameter(7, velocidad);
		 query.setParameter(8, "0");
	     query.setParameter(9, usuario);
		 query.setParameter(10, clave);
		 query.setParameter(11, Patente);  
	     query.setParameter(12, "0");
		 query.setParameter(13, "0");

		 ///////////////////////////////////////
		 
		 
		 ArrayList<HistoricoActive> mlist = new ArrayList<HistoricoActive>();
			
		  query.execute();
		    
	 		List<Object[]> eventlist = query.getResultList();
	 		
	 		Integer i = 0;
	 		
	 		for (Object[] event : eventlist) {
				i++;
				
				HistoricoActive historicoActive = new HistoricoActive();							
				    
					String idVehicle = (String) event[1];
					String lat = (String) event[2];
					String lon = (String) event[3];
					String spd = (String) event[4];
					Timestamp data_date = (Timestamp) event[6];
					Timestamp rx_date = (Timestamp) event[7];
					String hdg = (String) event[8];
					String fecha = (String) event[10];
					String hora = (String) event[11];
					Integer idEvent = (Integer) event[13];
					String satFix = (String) event[15];
					String chofer = (String) event[20];
					String patente = (String) event[21];
					String faena = (String) event[23];
					String numInterno = (String) event[24];
					Double odometro = (Double) event[26];
					String iconoeve = (String) event[28];
					String nombreve = (String) event[29];
					String nomflecha = (String) event[32];
					String rutaflecha = (String) event[31];
					String ubicacion = (String) event[33];
					String tposDesc = (String) event[34];
					String geoDir = (String) event[37];
					Integer sw = (Integer) event[38];
					String classEvent = UtilServicio.getClassEvent(idEvent, nombreve);
					    
					 historicoActive.setClassEvent(classEvent);
				   
					 historicoActive.setIdVehicle(idVehicle);
					 historicoActive.setLat(lat);
					 historicoActive.setLon(lon);
					 historicoActive.setSpd(spd);
					 historicoActive.setData_date(data_date);
					 historicoActive.setRx_date(rx_date);
					 historicoActive.setHdg(hdg);
					 historicoActive.setFecha(fecha);
					 historicoActive.setHora(hora);
					 historicoActive.setIdEvent(idEvent);
					 historicoActive.setSatFix(satFix);
					 historicoActive.setChofer(chofer);
					 historicoActive.setPatente(patente);
					 historicoActive.setFaena(faena);
					 historicoActive.setNumInterno(numInterno);
					 historicoActive.setOdometro(odometro);
					 historicoActive.setIconoeve(iconoeve);
					 historicoActive.setNombreve(nombreve);
					 historicoActive.setNomflecha(nomflecha);
					 historicoActive.setRutaflecha(rutaflecha);
					 historicoActive.setUbicacion(ubicacion);
					 historicoActive.setTposDesc(tposDesc);
					 historicoActive.setGeoDir(geoDir);
					 historicoActive.setSw(sw);
				   			   
				    mlist.add(historicoActive);
				    
	 		}
	 			
	 		em2.clear();
	 		em2.close();
		 
		 
	    return  mlist;
	}

//usuLogin, fechas.getFechatimein(), fechas.getFechatimefin(),alar, busqueda, clave, sw
public static List<ReporteAlarmaRutaActive> findAlarmaRuta(String usuario, String desde, String hasta,String alar, String busqueda, String clave, String sw ){

	String fechanulas = "";

	//dd-MM-yyyy
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("gpsf");
	DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); //dd/MM/yyyy
	em = emf.createEntityManager();


	Calendar calendarin = Calendar.getInstance();
	Calendar calendarfin = Calendar.getInstance();


	Date fecdesde= new Date();
	Date fechasta= new Date();

	//si las fechas vienen nulo se calcula el mes completo
	if(desde.equals("") && hasta.equals("")){

		fechanulas = "S";

	}

	try {
		fecdesde = formatteri.parse(desde);
		fechasta=  formatteri.parse(hasta);
	} catch (ParseException e) {

		e.printStackTrace();
	}

	//configuro las hora inicio y fin
	calendarin.setTime(fecdesde);
	
	//si las fechas vienen nul se resta el mes a la fecha inicio y fecha fin queda como dia hoy
	if(fechanulas.equals("S")){

		calendarfin.add(Calendar.DAY_OF_MONTH, -2);

	}

	calendarfin.setTime(fechasta);

	//las convierto timestamp para insertarlas en el query
	Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
	Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

			//descripcion de los parametros que recibe el procedure en sqlserver
			//alar (evento)
			//if alar "" -> alar= 5
			//else alar = evento

			//faena seleccionada sw 2
			//if faena es 0 - > sw 4

			//patente seleccionada sw 1

			//conductor seleccioando sw 3

	//recibe la fecha string, se deja configurado la fecha timestamp para futura migracion
	 StoredProcedureQuery query=em.createStoredProcedureQuery("MM_P_NoConformidadesEnRuta_CPE2_pass");
	 query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN); // usuario
	 query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN); //fecha in
	 query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN); //fechan fin
	 query.registerStoredProcedureParameter(4, int.class, ParameterMode.IN); // 100
	 query.registerStoredProcedureParameter(5, int.class, ParameterMode.IN); // reg_pagina
	 query.registerStoredProcedureParameter(6, int.class, ParameterMode.IN); // alar   
	 query.registerStoredProcedureParameter(7, String.class, ParameterMode.IN); // busqueda
	 query.registerStoredProcedureParameter(8, int.class, ParameterMode.IN); // sw  Tipo
	 query.registerStoredProcedureParameter(9, String.class, ParameterMode.IN); // clave_us
   

   
   	 query.setParameter(1, usuario);
   	 query.setParameter(2, desde);
	 query.setParameter(3, hasta);
	 query.setParameter(4, 100);
	 query.setParameter(5, 0);
	 query.setParameter(6, Integer.valueOf(alar));
	 query.setParameter(7, busqueda);	 
	 query.setParameter(8, Integer.valueOf(sw));
	 query.setParameter(9, clave);


	 query.execute();


	ArrayList<ReporteAlarmaRutaActive> rlist = new ArrayList<ReporteAlarmaRutaActive>();

	List<Object[]> mapalist = query.getResultList();
		Integer i = 0;

		for (Object[] ruta : mapalist) {
		i++;

		ReporteAlarmaRutaActive reporteAlarmaRutaActive = new ReporteAlarmaRutaActive();

		   
		//temp id faena  id_vehiculo patente posicion ruta lat  lon hdg  id_event date_date spd chofer rx_date alarma max_reg num_interno tercero
		//0     1          2           3         4      5   6    7   8      9      10		11  12		13     14     15      16         17
		    		    
		    String patente = (String)ruta[3];
		    String alarma = (String)ruta[14];		
		    String conductor = (String) ruta[12];
		    Timestamp fecha = (Timestamp) ruta[10];
		    String vel = (String) ruta[11];
		    String lat = (String) ruta[6];
		    String lon = (String) ruta[7];
		    
		    String hdg = (String) ruta[8];
		    String idVehiculo =  (String) ruta[2];		
		    
		    reporteAlarmaRutaActive.setPatente(patente);
		    reporteAlarmaRutaActive.setAlarma(alarma);
		    reporteAlarmaRutaActive.setConductor(conductor);
		    reporteAlarmaRutaActive.setFecha(fecha);
		    reporteAlarmaRutaActive.setVel(vel);
		    reporteAlarmaRutaActive.setLat(lat);
		    reporteAlarmaRutaActive.setLon(lon);		    
		    reporteAlarmaRutaActive.setHdg(hdg);
		    reporteAlarmaRutaActive.setIdVehicle(idVehiculo);
		    
		    
		    rlist.add(reporteAlarmaRutaActive);

		}


	em.clear();
	em.close();

	return rlist;
}

//usuLogin, desde, hasta,valor_pat, cod_pat, clave
public static List<ReporteDetencionesSosActive> findDetencionesSos(String usuario, String desde, String hasta,String valor_pat, String cod_pat, String clave, String vel ){

	String fechanulas = "";

	//dd-MM-yyyy
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("gpsf");
	DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); //dd/MM/yyyy
	em = emf.createEntityManager();


	Calendar calendarin = Calendar.getInstance();
	Calendar calendarfin = Calendar.getInstance();


	Date fecdesde= new Date();
	Date fechasta= new Date();

	//si las fechas vienen nulo se calcula el mes completo
	if(desde.equals("") && hasta.equals("")){

		fechanulas = "S";

	}

	try {
		fecdesde = formatteri.parse(desde);
		fechasta=  formatteri.parse(hasta);
	} catch (ParseException e) {

		e.printStackTrace();
	}

	//configuro las hora inicio y fin
	calendarin.setTime(fecdesde);
	
	//si las fechas vienen nul se resta el mes a la fecha inicio y fecha fin queda como dia hoy
	if(fechanulas.equals("S")){

		calendarfin.add(Calendar.DAY_OF_MONTH, -2);

	}

	calendarfin.setTime(fechasta);

	//las convierto timestamp para insertarlas en el query
	Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
	Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

/*MM_P_Detenciones_Sostenidas_pass2 'fast','01-01-2019 10:50:00','15-01-2019 23:59:59','100','0','TM38',      0,    0,   '1','','0','fast','juanitolindo'
									     user     in                     fin                  pag pag valor_pat   0  cod_pat  1  ''  0    user     clave
									
logica parametros

if patente es " "

valor_pat = faena 
cod_pat = 1 

if patente no es ""

valor_pat = patente
cod_pat =  0 */

	//recibe la fecha string, se deja configurado la fecha timestamp para futura migracion
	 StoredProcedureQuery query=em.createStoredProcedureQuery("MM_P_Detenciones_Sostenidas_pass2");
	 query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN); // usuario
	 query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN); //fecha in
	 query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN); //fechan fin
	 query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN); // 100
	 query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN); // reg_pagina
	 query.registerStoredProcedureParameter(6, String.class, ParameterMode.IN); // valor_pat   
	 query.registerStoredProcedureParameter(7, String.class, ParameterMode.IN); // 0 //velocidad
	 query.registerStoredProcedureParameter(8, int.class, ParameterMode.IN); // cod_pat
	 query.registerStoredProcedureParameter(9, int.class, ParameterMode.IN); // 1
	 query.registerStoredProcedureParameter(10, String.class, ParameterMode.IN); // ""
	 query.registerStoredProcedureParameter(11, String.class, ParameterMode.IN); // 0
	 query.registerStoredProcedureParameter(12, String.class, ParameterMode.IN); // user
	 query.registerStoredProcedureParameter(13, String.class, ParameterMode.IN); // clave
 
	 //MM_P_Detenciones_Sostenidas_pass2 'fast','15-01-2019 00:00:00','15-01-2019 23:59:59','100','0','TM38',   0,    0,   '1','','0','fast','juanitolindo'
 //String usuario, String desde, String hasta,String valor_pat, String cod_pat, String clave 
	 query.setParameter(1, usuario);
	 query.setParameter(2, desde);
	 query.setParameter(3, hasta);
	 query.setParameter(4, "100");
	 query.setParameter(5, "0");
	 query.setParameter(6, valor_pat); //valor_pat
	 query.setParameter(7, "0");	 
	 query.setParameter(8, Integer.valueOf(cod_pat)); //cod_pat
	 query.setParameter(9, Integer.valueOf(vel));
	 query.setParameter(10, "");
	 query.setParameter(11, "0");
	 query.setParameter(12, usuario);
	 query.setParameter(13, clave);

	 query.execute();

	 ArrayList<ReporteDetencionesSosActive> rlist = new ArrayList<ReporteDetencionesSosActive>();

	List<Object[]> mapalist = query.getResultList();
		Integer i = 0;

		for (Object[] ruta : mapalist) {
		i++;

		ReporteDetencionesSosActive reporteDetencionesSosActive = new ReporteDetencionesSosActive();

		   
	//tmp_id id_vehiculo spd lat lon flag date_date fecha hora horadif horadif2 rx_date hdg id_event hdop idgeo chofer patente max_reg faena num_interno2	iconoeve nombreeve rutaflecha nombreflecha ubicacion 								
	//0        1          2   3   4    5     6       7     8     9       10       11     12   13      14    15    16    17      18      19      20          21        22        23        24           25
		    		   
		    String patente = (String)ruta[17];
		    String conductor = (String)ruta[16];		
		    Timestamp fecha = (Timestamp) ruta[6];
		    String tiempodetenido = (String) ruta[10];
		    String velocidad = (String) ruta[2];
		    int idEvent = (Integer) ruta[13];		    
		    String nomflecha = (String) ruta[24];
			String rutaflecha = (String) ruta[23];
		    String lat = (String) ruta[3];
		    String lon = (String) ruta[4];
		    String nombreve = (String) ruta[22];
		    String ubicacion = (String) ruta[25];
		    String hdg = (String) ruta[12];
		    
		    String idVehicle = (String)ruta[1];
		    		    		    
		    reporteDetencionesSosActive.setPatente(patente);
		    reporteDetencionesSosActive.setConductor(conductor);
		    reporteDetencionesSosActive.setFecha(fecha);
		    reporteDetencionesSosActive.setVelocidad(velocidad);
		    reporteDetencionesSosActive.setEvento(String.valueOf(idEvent));
		    reporteDetencionesSosActive.setNomflecha(nomflecha);
		    reporteDetencionesSosActive.setRutaflecha(rutaflecha);
		    reporteDetencionesSosActive.setLat(lat);
		    reporteDetencionesSosActive.setLon(lon);
		    reporteDetencionesSosActive.setTiempoDetenido(tiempodetenido);
		    
		    
		    reporteDetencionesSosActive.setNombreve(nombreve);
		    reporteDetencionesSosActive.setUbicacion(ubicacion);
		    
		    reporteDetencionesSosActive.setIdVehicle(idVehicle);
		    reporteDetencionesSosActive.setIdEvent(String.valueOf(idEvent));
		    reporteDetencionesSosActive.setHdg(hdg);
		    
		    String classEvent = UtilServicio.getClassEvent(idEvent, nombreve);
		    
		    reporteDetencionesSosActive.setClassEvent(classEvent);
		    
		  		    
		    rlist.add(reporteDetencionesSosActive); 

		}


	em.clear();
	em.close();

	return rlist;
}

//usuLogin, desde, hasta,valor, tipo, clave, sw
public static List<ReporteInformeRendimientoActive> findInformeRendimiento(String usuario, String desde, String hasta,String valor, String tipo, String clave, Boolean sw ){

	String fechanulas = "";

	//dd-MM-yyyy
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("gpsf");
	DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); //dd/MM/yyyy
	em = emf.createEntityManager();


	Calendar calendarin = Calendar.getInstance();
	Calendar calendarfin = Calendar.getInstance();


	Date fecdesde= new Date();
	Date fechasta= new Date();

	//si las fechas vienen nulo se calcula el mes completo
	if(desde.equals("") && hasta.equals("")){

		fechanulas = "S";

	}

	try {
		fecdesde = formatteri.parse(desde);
		fechasta=  formatteri.parse(hasta);
	} catch (ParseException e) {

		e.printStackTrace();
	}

	//configuro las hora inicio y fin
	calendarin.setTime(fecdesde);
	
	//si las fechas vienen nul se resta el mes a la fecha inicio y fecha fin queda como dia hoy
	if(fechanulas.equals("S")){

		calendarfin.add(Calendar.DAY_OF_MONTH, -2);

	}

	calendarfin.setTime(fechasta);

	//las convierto timestamp para insertarlas en el query
	Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
	Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

	
	//recibe la fecha string, se deja configurado la fecha timestamp para futura migracion
	 StoredProcedureQuery query= null;
	 
	 if(sw){			 
			 query = em.createStoredProcedureQuery("MM_P_Informe_Rendimiento_hora_CAN_pass");
						 
	 }else{		 
		 	query = em.createStoredProcedureQuery("MM_P_Informe_Rendimiento_ver3_CAN_pass");		 		
	 }
	
	 
	 query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN); // usuario
	 query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN); //fecha in
	 query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN); //fechan fin
	 query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN); // 100
	 query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN); // reg_pagina
	 query.registerStoredProcedureParameter(6, String.class, ParameterMode.IN); // valor   
	 query.registerStoredProcedureParameter(7, String.class, ParameterMode.IN); // 0 //tipo
	 query.registerStoredProcedureParameter(8, String.class, ParameterMode.IN); //clave
	 	 
	 query.setParameter(1, usuario);
	 query.setParameter(2, desde);
	 query.setParameter(3, hasta);
	 query.setParameter(4, "100");
	 query.setParameter(5, "0");
	 query.setParameter(6, valor); 
	 query.setParameter(7, tipo);	 	 
	 query.setParameter(8, clave);
	 	 
	 query.execute();

	ArrayList<ReporteInformeRendimientoActive> rlist = new ArrayList<ReporteInformeRendimientoActive>();

	List<Object[]> mapalist = query.getResultList();
		Integer i = 0;

		for (Object[] ruta : mapalist) {
		i++;

		ReporteInformeRendimientoActive reporteInformeRendimientoActive = new ReporteInformeRendimientoActive();
		
		//MM_P_Informe_Rendimiento_ver3_CAN_pass 'fast','15-01-2019 00:00:00','15-01-2019 23:59:59','100','0','TM38',   1, 'juanitolindo'
		
		//tmpdivece     patente  faena  tmp_nom_geo_origen  fecha	origen              lat origen     lon origen   hdg  spd  odo           ltrs    nom geo destini  fecha destino          evento destino    lat         lon       hdg  spd   odo          lts     tiempo recorrido   distancia   lts consumido     rendimiento  tipo	num interno  marca   modelo
		//TM38			CLHJ86	Centro	Geo DyS		       2019-01-15 12:56:02.000	   -33.36562	  -070.69438	169	 2	  1353482750	487983	Oficina Lasama	2019-01-15 14:13:55.000	     64      	-34.20166	-070.87076	223	  20	1353591750	488020	01:17:53         	109	        37	              2,946      	1	114      	RENAULT	440	   
		// 0             1        2       3                     4                        5                6          7    8     9            10       11               12                        13           14          15         16   17     18           19       20                21         22                 23           24   25          26      27  
		
			String tmpdivece =(String)ruta[0];
		    String patente =(String)ruta[1];
		    String origen = (String)ruta[3]; 
		    Timestamp salida = (Timestamp)ruta[4];		
		    String destino = (String) ruta[11];		   
		    Timestamp llegada = (Timestamp) ruta[12];
		    String tporecorrido = (String) ruta[20];		   
		    Double kmrecorrido = (Double) ruta[21];
			Double lstconsumidos = (Double) ruta[22];
			Double rendimiento = (Double) ruta[23];		    
		    String latorigen = (String) ruta[5];
		    String lonorigen = (String) ruta[6];
		    String latdestino = (String) ruta[15];
		    String londestino = (String) ruta[15];
		    
		    Integer velO = (Integer) ruta[8];
		    Integer velD = (Integer) ruta[17];
		    
		    Integer hdgO = (Integer) ruta[7];
		    Integer hdgD = (Integer) ruta[16];
		    		   
		    reporteInformeRendimientoActive.setNroInt(tmpdivece);
		    reporteInformeRendimientoActive.setPatente(patente);
		    reporteInformeRendimientoActive.setOrigen(origen);
		    reporteInformeRendimientoActive.setSalidad(salida);
		    reporteInformeRendimientoActive.setDestino(destino);
		    reporteInformeRendimientoActive.setLlegada(llegada);
		    reporteInformeRendimientoActive.setTporecorrido(tporecorrido);
		    reporteInformeRendimientoActive.setKmrecorrido(String.valueOf(kmrecorrido));
		    reporteInformeRendimientoActive.setLstconsumidos(String.valueOf(lstconsumidos));
		    reporteInformeRendimientoActive.setRendimiento(String.valueOf(rendimiento));
		    reporteInformeRendimientoActive.setLatO(latorigen);
		    reporteInformeRendimientoActive.setLatD(latdestino);
		    reporteInformeRendimientoActive.setLonO(lonorigen);
		    reporteInformeRendimientoActive.setLonD(londestino);
		    
		    reporteInformeRendimientoActive.setVelO(String.valueOf(velO));
		    reporteInformeRendimientoActive.setVelD(String.valueOf(velD));
		    
		    reporteInformeRendimientoActive.setHdgO(String.valueOf(hdgO));
		    reporteInformeRendimientoActive.setHdgD(String.valueOf(hdgD));
		    
		    rlist.add(reporteInformeRendimientoActive); 

		}


	em.clear();
	em.close();

	return rlist;
}

//usuLogin, desde, hasta,sw, valor, clave, rpm
public static List<ReportetInformeralentiCanActive> findInformeRalenti(String usuario, String desde, String hasta,String sw, String valor, String clave, String rpm){

	String fechanulas = "";

	//dd-MM-yyyy
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("gpsf");
	DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); //dd/MM/yyyy 
	em = emf.createEntityManager();


	Calendar calendarin = Calendar.getInstance();
	Calendar calendarfin = Calendar.getInstance();


	Date fecdesde= new Date();
	Date fechasta= new Date();

	//si las fechas vienen nulo se calcula el mes completo
	if(desde.equals("") && hasta.equals("")){

		fechanulas = "S";

	}

	try {
		fecdesde = formatteri.parse(desde);
		fechasta=  formatteri.parse(hasta);
	} catch (ParseException e) {

		e.printStackTrace();
	}

	//configuro las hora inicio y fin
	calendarin.setTime(fecdesde);
	
	//si las fechas vienen nul se resta el mes a la fecha inicio y fecha fin queda como dia hoy
	if(fechanulas.equals("S")){

		calendarfin.add(Calendar.DAY_OF_MONTH, -2);

	}

	calendarfin.setTime(fechasta);

	//las convierto timestamp para insertarlas en el query
	Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
	Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());
	
	//recibe la fecha string, se deja configurado la fecha timestamp para futura migracion
	 
	StoredProcedureQuery query = em.createStoredProcedureQuery("dbo.FA_RelantiCan_pass_CPE");
				
	//																				tipo valor  rpm 
	//FA_RelantiCan_pass_CPE 'fast','juanitolindo','24-01-2019 00:00','24-01-2019 23:59','1','0','800
	
	 query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN); // usuario
	 query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN); //clave
	 query.registerStoredProcedureParameter(3, Timestamp.class, ParameterMode.IN); //fechan in
	 query.registerStoredProcedureParameter(4, Timestamp.class, ParameterMode.IN); // fecha fin
	 query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN); // tipo
	 query.registerStoredProcedureParameter(6, String.class, ParameterMode.IN); // valor   
	 query.registerStoredProcedureParameter(7, String.class, ParameterMode.IN); // rpm
	 	 
	 query.setParameter(1, usuario);
	 query.setParameter(2, clave);
	 query.setParameter(3, timein);
	 query.setParameter(4, timefin);
	 query.setParameter(5, sw);
	 query.setParameter(6, valor); 
	 query.setParameter(7, rpm); 	 
	 	 
	 query.execute();

	ArrayList<ReportetInformeralentiCanActive> rlist = new ArrayList<ReportetInformeralentiCanActive>();

	List<Object[]> mapalist = query.getResultList();
		Integer i = 0;

		for (Object[] ruta : mapalist) {
		i++;

		ReportetInformeralentiCanActive reportetInformeralentiCanActive = new ReportetInformeralentiCanActive();
		
		
		//id  iddivece patente   fini							ffin				 tiempo       lat     lon           chofer           numint   marca  modelo    faena    comb   ubicacion
		//1	6589	 GGPC78	  2019-01-24 00:02:45.000	2019-01-24 00:03:42.000	    1	        -34,433	-71,07541	GERARDO SANDAÑA CALVIN	152	      VOLVO	 460	  Centro 	0	    A 0.8 Km. de Faenadora San Vicente
		//0     1        2        3                            4                    5           6         7            8                    9         10    11        12      13         14 

		
			String patente =(String)ruta[2];
			String conductor =(String)ruta[8];
		    String nrInt =(String)ruta[9];
		    String ubicacion = (String)ruta[14]; 
		    Timestamp fechainicio = (Timestamp)ruta[3];		
		    Timestamp fechafin = (Timestamp) ruta[4];		   		    
		    Integer duracionralenti = (Integer) ruta[5];		   
		    Double combutil = (Double) ruta[13];
		    Double lat = (Double) ruta[6];
		    Double lon = (Double) ruta[7];		    
		    String modelo = (String) ruta[10];
		    String marca = (String) ruta[11];
		    
		    
		    reportetInformeralentiCanActive.setPatente(patente);
		    reportetInformeralentiCanActive.setConductor(conductor);
		    reportetInformeralentiCanActive.setNrInt(nrInt);
		    reportetInformeralentiCanActive.setUbicacion(ubicacion);
		    reportetInformeralentiCanActive.setFechainicio(fechainicio);
		    reportetInformeralentiCanActive.setFechafin(fechafin);
		    reportetInformeralentiCanActive.setDuracionralenti(String.valueOf(duracionralenti));
		    reportetInformeralentiCanActive.setCombutil(combutil);
		    reportetInformeralentiCanActive.setLat(String.valueOf(lat));
		    reportetInformeralentiCanActive.setLon(String.valueOf(lon));
		    reportetInformeralentiCanActive.setModelo(modelo);
		    reportetInformeralentiCanActive.setMarca(marca);
		    
		    rlist.add(reportetInformeralentiCanActive); 

		}


	em.clear();
	em.close();

	return rlist;
}

public static List<HistoricoActive> findHistoricoPG(String desde ,String hasta ,String patente, String velocidad, Vehiculo vehiculo) {
		
	String fechanulas = "";

	//dd-MM-yyyy
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
	DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); //dd/MM/yyyy
	em = emf.createEntityManager();


	Calendar calendarin = Calendar.getInstance();
	Calendar calendarfin = Calendar.getInstance();


	Date fecdesde= new Date();
	Date fechasta= new Date();

	//si las fechas vienen nulo se calcula el mes completo
	if(desde.equals("") && hasta.equals("")){

		fechanulas = "S";

	}

	try {
		fecdesde = formatteri.parse(desde);
		fechasta=  formatteri.parse(hasta);
	} catch (ParseException e) {

		e.printStackTrace();
	}

	//configuro las hora inicio y fin
	calendarin.setTime(fecdesde);
	
	//si las fechas vienen nul se resta el mes a la fecha inicio y fecha fin queda como dia hoy
	if(fechanulas.equals("S")){

		calendarfin.add(Calendar.DAY_OF_MONTH, -2);

	}

	calendarfin.setTime(fechasta);

	//las convierto timestamp para insertarlas en el query
	Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
	Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());
	
	
	int velocidad2 = 0;
	if(velocidad==null ||velocidad.isEmpty()){
		velocidad2 = 0;
	}else if (velocidad.equals("")){
		velocidad2 = 0;
	}else{
		velocidad2 = Integer.valueOf(velocidad);
		
	}
	
	/*Query query = em.createNativeQuery("select * from informehistorico("
			+ " '"+timein+"', '"+timefin+"', '"+patente+"', "+velocidad+" " 															
			+ " )");*/
	//select * from informehistorico('2019-03-03 00:00:00','2019-03-08 23:59:00','HKPC22',0)
	StoredProcedureQuery query = em.createStoredProcedureQuery("informehistorico");
	
	 query.registerStoredProcedureParameter(1, Timestamp.class, ParameterMode.IN); //fechan in
	 query.registerStoredProcedureParameter(2, Timestamp.class, ParameterMode.IN); // fecha fin
	 query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN); // tipo
	 query.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN); // valor   
	 
	 query.setParameter(1, timein);
	 query.setParameter(2, timefin);
	 query.setParameter(3, patente);
	 try {
			query.setParameter(4, Integer.valueOf(velocidad));
		} catch (Exception e) {
			query.setParameter(4, 0);
		}
	 
	
	 ArrayList<HistoricoActive> mlist = new ArrayList<HistoricoActive>();
		
 	
	 	List<Object[]> eventlist = query.getResultList();
 		Integer i = 0;
 		
 	
 		for (Object[] d : eventlist) {
			i++;
			
 //d.id.devIdDevice, d.id.rutPatente, d.eventosGp.eveIdId, d.eventosGp.eveNombre, d.rutGeocerca, d.rutGoecoding, d.rutLatitud, d.rutLongitud,d.rutOdometro, d.rutOrientacion, d.rutVelocidad, d.id.rutFechaHora as fecha, chofer
	// 0               1                      2                 3                  4               5               6              7            8               9                10                      11               	12
			
			HistoricoActive historicoActive = new HistoricoActive();
			
			String devIdDevice = (String) d[0];
			String rutPatente = (String) d[1];
			Integer eveIdId = (Integer) d[2];
			String eveNombre = (String) d[3];
			String rutGeocerca = (String) d[4];
			String rutGoecoding = (String) d[5];
			String rutLatitud = (String) d[6];
			String rutLongitud = (String) d[7];
			Double rutOdometro = (Double) d[8];
			Integer rutOrientacion = (Integer) d[9];
			Integer rutVelocidad = (Integer) d[10];
			
			//Calendar current = Calendar.getInstance(TimeZone.getTimeZone("GMT"));			
			//Timestamp	 rutFechaHora = new Timestamp(current.getTimeInMillis());						
			//rutFechaHora = (Timestamp) d[11];
			System.out.println(d[11].toString());
			//String rutFechaHora = (String) d[11];
			Timestamp rutFechaHora = (Timestamp) d[11];
			System.out.println(rutFechaHora);
			
			//PropertyConfigurator.configure(log4jConfPath);
			//logger.error("rutFechaHora objet "+d[11].toString());
			//logger.error("rutFechaHora Timestamp "+rutFechaHora);
						 
			String chofer = (String) d[12];
						    
			    historicoActive.setIdVehicle(devIdDevice);
			    
			     historicoActive.setLat(rutLatitud.replaceAll("-0", "-"));
				 historicoActive.setLon(rutLongitud.replaceAll("-0", "-"));
				 historicoActive.setSpd(String.valueOf(rutVelocidad));
				 
				 
				 historicoActive.setData_date(rutFechaHora);
				 
				 historicoActive.setChofer(chofer); 
				 historicoActive.setPatente(rutPatente);
				 if(!(rutGeocerca == null) ){
					 historicoActive.setUbicacion(rutGeocerca);
					 
				 }else{
					 historicoActive.setUbicacion(rutGoecoding);
					 
				 }
				 				
				
				 String classEvent = UtilServicio.getClassEvent(eveIdId, eveNombre);
				 historicoActive.setClassEvent(classEvent);
				 historicoActive.setIdEvent(eveIdId);
				 historicoActive.setNombreve(eveNombre);
				 String classEventJs = UtilServicio.getClassEventJS(eveIdId, eveNombre);
				 historicoActive.setClassEventJs(classEventJs);
				 
				 
				 historicoActive.setNumInterno(vehiculo.getVehNumInterno());
			   
				 FlechaActive flecha = UtilServicio.getFlecha(rutOrientacion);
				 
				 historicoActive.setNomflecha(flecha.getNomflecha());
				 historicoActive.setRutaflecha(flecha.getRutaflecha());
			   			   
			    mlist.add(historicoActive);
			    
 		}
 			
 		em.clear();
 		em.close();
	 
	 
    return  mlist;
}

//fechain, fechafin, patente, velocidad, vehiculo
public static Document findExportKMLHistorico(String fechain, String fechafin, String patente,String velocidad){

	Document document = new Document();  
	
	document.setName("reporte_kml_"+patente+"_"+fechain+".kml");
	    
  	ArrayList<Folder> folders = new ArrayList<Folder>(); 
  	Folder folder = new Folder();
  	
  	folder.setName("EVENTO "+ patente +"");
  	folder.setOpen("2");
  	
  	List<HistoricoActive> listHistorico =  new ArrayList<HistoricoActive>();
  	
  	 Vehiculo vehiculo = DispositivoService.findVehiculoByPatente(patente);			
		 listHistorico = GestionService.findHistoricoPG(fechain, fechafin, patente, velocidad, vehiculo);
		 
		 	ArrayList<Placemark> placemarkers = new ArrayList<Placemark>();	
		 Integer i = 0;
		 
	 		for (HistoricoActive h : listHistorico) {
				i++;
			
			if(!(h.getIdEvent()==7 ||h.getIdEvent()==8)){ 
		 
  		Placemark placemark = new Placemark();
  		
  		placemark.setName(""+i);
  		
  		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
  		
  		String fecha = formatteri.format(h.getData_date());
  		
  		String descripcion = "<table width='400' border='0'>"
  				+ "<tr>"
  				+ " <td colspan='2' bgcolor='#EBF3FD'>"
  				+ "<table width='100%' border='0'>"
  				+ "<tr>"
  				//+ " <td align='left' colspan='2'><h1>"+h.getPatente()+"&nbsp; </h1></td>"
  				+ " <td align='left' colspan='2'><h2>"+fecha+"&nbsp; </h2></td>"
  				+ "</tr>"
  				+ "</table>"
  				+ "</td>"
  				+ " </tr>"
  				+ " <tr>"
  				+ " <td bgcolor='#CDDCEE'>&nbsp;CONDUCTOR:</td>"
  				+ " <td>&nbsp;"+h.getChofer()+"</td>"
  				+ " </tr>"
  				+ "<tr>"
  				+ " <td bgcolor='#CDDCEE'>&nbsp;VELOCIDAD:</td>"
  				+ " <td>&nbsp;"+h.getSpd()+" Km/h</td>"
  				+ " </tr>"
  				+ "<tr>"
  				+ "  <td bgcolor='#CDDCEE'>&nbsp;PATENTE:</td>"
  				+ " <td>&nbsp;"+h.getPatente()+"</td>"
  				+ " </tr>"
  				+ " <tr>"
  				//+ "<tr>"
  				//+ "  <td bgcolor='#CDDCEE'>&nbsp;FECHA:</td>"
  				//+ " <td>&nbsp;"+fecha+"</td>"
  				//+ " </tr>"
  				+ " <tr>"
  				+ " <td bgcolor='#CDDCEE'>&nbsp;FAENA:</td>"
  				+ " <td>&nbsp;"+h.getUbicacion() +"                                            </td>"
  				+ " </tr>   "
  				+ " <tr>"
  				+ "  <td bgcolor='#CDDCEE'>&nbsp;GEO. UBICACION:</td>"
  				+ "  <td>&nbsp;<b>("+h.getLat()+", "+h.getLon()+")</b></td>"
  				+ " </tr>"
  				+ " <tr>"
  				+ " <td bgcolor='#CDDCEE'>&nbsp;EVENTO:</td>"
  				+ " <td>&nbsp;"+h.getNombreve()+"</td>"
  				+ " </tr>"
  				+ "</table>"
  				+ "";
  		    		
  		placemark.setDescription(descripcion);
  		
  			Style style = new Style();
  			
  				IconStyle iconStyle = new IconStyle();
  				
  					Icon icon = new Icon();
  					     					
  					// icon.setHref("http://sgomt.samtech.cl/sgomtweb/resources/img/historico/"+h.getRutaflecha());
  					icon.setHref("http://maps.gstatic.com/mapfiles/ridefinder-images/mm_20_blue.png");
  					
  					if(h.getIdEvent()==7){
  						icon.setHref("http://maps.google.com/mapfiles/ms/icons/green-dot.png");
  						
  						
  					}
  					if(h.getIdEvent()==8){
  						icon.setHref("http://maps.google.com/mapfiles/ms/icons/red-dot.png");
  						
  						
  					}
  					
  					iconStyle.setIcon(icon);	
  			
  			style.setIconStyle(iconStyle);
  			
  			ArrayList<Point> points = new ArrayList<Point>();
  			Point point = new Point();    			 
  			
  			point.setCoordinates(""+h.getLon()+","+h.getLat()+"");
  			
  			points.add(point);
  		
  		placemark.setPoints(points);
  		placemark.setStyle(style);
  		placemarkers.add(placemark);
  		
			}
  		
	 		}// fin for
  		
  	 
	 	//FIN EVENTOS
   		
  	
	
   		
   	
   	//CARPETA TOLVA  
   	Folder folder2 = new Folder();
      	
      folder2.setName("TOLVAS");
      folder2.setOpen("2");	
  	ArrayList<Placemark> placemarkers2 = new ArrayList<Placemark>();	
      for (HistoricoActive h : listHistorico) {
			i++;
			
		if(h.getIdEvent()==7 ||h.getIdEvent()==8){ 
			
		
		Placemark placemark3 = new Placemark();
		if(h.getIdEvent()==7){
		placemark3.setName("Arriba "+i);
		}
		if(h.getIdEvent()==8){
		placemark3.setName("Abajo "+i);
		}
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		String fecha = formatteri.format(h.getData_date());
		
		String descripcion = "<table width='400' border='0'>"
				+ "<tr>"
				+ " <td colspan='2' bgcolor='#EBF3FD'>"
				+ "<table width='100%' border='0'>"
				+ "<tr>"
				+ " <td align='left' colspan='2'><h1>"+h.getPatente()+"&nbsp; </h1></td>"
				+ "</tr>"
				+ "</table>"
				+ "</td>"
				+ " </tr>"
				+ " <tr>"
				+ " <td bgcolor='#CDDCEE'>&nbsp;CONDUCTOR:</td>"
				+ " <td>&nbsp;"+h.getChofer()+"</td>"
				+ " </tr>"
				+ "<tr>"
				+ " <td bgcolor='#CDDCEE'>&nbsp;VELOCIDAD:</td>"
				+ " <td>&nbsp;"+h.getSpd()+" Km/h</td>"
				+ " </tr>"
				+ "<tr>"
				+ "  <td bgcolor='#CDDCEE'>&nbsp;FECHA:</td>"
				+ " <td>&nbsp;"+fecha+"</td>"
				+ " </tr>"
				+ " <tr>"
				+ " <td bgcolor='#CDDCEE'>&nbsp;FAENA:</td>"
				+ " <td>&nbsp;"+h.getUbicacion() +"                                            </td>"
				+ " </tr>   "
				+ " <tr>"
				+ "  <td bgcolor='#CDDCEE'>&nbsp;GEO. UBICACION:</td>"
				+ "  <td>&nbsp;<b>("+h.getLat()+", "+h.getLon()+")</b></td>"
				+ " </tr>"
				+ " <tr>"
				+ " <td bgcolor='#CDDCEE'>&nbsp;EVENTO:</td>"
				+ " <td>&nbsp;"+h.getNombreve()+"</td>"
				+ " </tr>"
				+ "</table>"
				+ "";
		    		
		placemark3.setDescription(descripcion);
		
			Style style2 = new Style();
			
				IconStyle iconStyle2 = new IconStyle();
				
					Icon icon2 = new Icon();
					     					
					// icon.setHref("http://sgomt.samtech.cl/sgomtweb/resources/img/historico/"+h.getRutaflecha());
					//icon2.setHref("http://maps.gstatic.com/mapfiles/ridefinder-images/mm_20_blue.png");
					
					if(h.getIdEvent()==7){
						icon2.setHref("http://maps.google.com/mapfiles/ms/icons/green-dot.png");
						
						
					}
					if(h.getIdEvent()==8){
						icon2.setHref("http://maps.google.com/mapfiles/ms/icons/red-dot.png");
						
						
					}
					
					iconStyle2.setIcon(icon2);	
			
			style2.setIconStyle(iconStyle2);
			
			ArrayList<Point> points = new ArrayList<Point>();
			Point point = new Point();    			 
			
			point.setCoordinates(""+h.getLon()+","+h.getLat()+"");
			
			points.add(point);
		
		placemark3.setPoints(points);
		placemark3.setStyle(style2);
		placemarkers2.add(placemark3);
		
		}
		
		}// fin for
   		
      
      //FIN BLOQUE TOLVA
      
      //CARPETA LINEA
      
    //CARPETA TOLVA  
   	Folder folder3 = new Folder();
      	
      folder3.setName("LINEAS");
      folder3.setOpen("2");	
      
      //LINEAS BLOQUE
      ArrayList<Placemark> placemarkers3 = new ArrayList<Placemark>();	
  	Placemark placemark2 = new Placemark();
		
		placemark2.setName("Lineas");
		
			Style style3 = new Style();
			
				IconStyle iconStyle3 = new IconStyle();
				
					Icon icon3 = new Icon();
					
					icon3.setHref("http://maps.google.com/mapfiles/kml/pushpin/ylw-pushpin.png");
					
					iconStyle3.setIcon(icon3);	
			
			style3.setIconStyle(iconStyle3);
			
				LineStyle lineStyle = new LineStyle();
				
				lineStyle.setColor("ff0000ff");
				lineStyle.setWidth("2");
				
			style3.setLineStyle(lineStyle);	
			
			ArrayList<LineString> lineStrings = new ArrayList<LineString>();	
			LineString lineString = new LineString();
			
			
			
			String coordinates = "";
	
  	 for (HistoricoActive h : listHistorico) {
  		 
  		 coordinates = h.getLon()+","+h.getLat()+" "+coordinates;
  		 
  	 }
  	 lineString.setCoordinates(coordinates);
  	 lineString.setTessellate("1");
  	
  	 
  	 
  	 lineStrings.add(lineString);
  	 
  	 
  		placemark2.setStyle(style3);
  		placemark2.setLineStrings(lineStrings);
   	  placemarkers3.add(placemark2);
   	
   	  //FIN LINEA BLOQUE
   	  	
   	 folder.setPlacemarkers(placemarkers);
   	 folders.add(folder);
   	  
   	 folder2.setPlacemarkers(placemarkers2);	
       folders.add(folder2);
   	  
   	folder3.setPlacemarkers(placemarkers3);       		
   	folders.add(folder3);
   	//FIN CARPETA EVENTO Y LINEA	
      
      //FIN CARPETA LINEA
   		
   	
  	document.setFolders(folders);

	return document;
}

//usuLogincliente, desde, hasta,sw, patente, faena, rpm
public static List<ReportetInformeralentiCanActive> findInformeRalentiPG(String usuarioclient, String desde, String hasta,String sw, String patente, String faena, String rpm){

	String fechanulas = "";

	//dd-MM-yyyy
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
	DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); //dd/MM/yyyy 
	em = emf.createEntityManager();


	Calendar calendarin = Calendar.getInstance();
	Calendar calendarfin = Calendar.getInstance();


	Date fecdesde= new Date();
	Date fechasta= new Date();

	//si las fechas vienen nulo se calcula el mes completo
	if(desde.equals("") && hasta.equals("")){

		fechanulas = "S";

	}

	try {
		fecdesde = formatteri.parse(desde);
		fechasta=  formatteri.parse(hasta);
	} catch (ParseException e) {

		e.printStackTrace();
	}

	//configuro las hora inicio y fin
	calendarin.setTime(fecdesde);
	
	//si las fechas vienen nul se resta el mes a la fecha inicio y fecha fin queda como dia hoy
	if(fechanulas.equals("S")){

		calendarfin.add(Calendar.DAY_OF_MONTH, -2);

	}

	calendarfin.setTime(fechasta);

	//las convierto timestamp para insertarlas en el query
	Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
	Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());
	
	//recibe la fecha string, se deja configurado la fecha timestamp para futura migracion
	 
	StoredProcedureQuery query = em.createStoredProcedureQuery("informeralenti");
				
	//select * from informeralenti(@fecha_ini,@fecha_fin,@sw,@id_faena,@rpm,@patente,@cliente)
	//Si @sw=0 es patente.
	//Si @sw=1 es faena.
	//select * from informeralenti('03/12/2019 00:00:00','03/13/2019 23:59:00',0,0,800,'CYYD31','emexcosa')
	//select * from informeralenti('03/12/2019 00:00:00','03/13/2019 23:59:00',1,1,800,'','emexcosa')
	
	 query.registerStoredProcedureParameter(1, Timestamp.class, ParameterMode.IN); // in
	 query.registerStoredProcedureParameter(2, Timestamp.class, ParameterMode.IN); //fin
	 query.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN); //sw
	 query.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN); // faena
	 query.registerStoredProcedureParameter(5, Integer.class, ParameterMode.IN); // rpm
	 query.registerStoredProcedureParameter(6, String.class, ParameterMode.IN); // patente   
	 query.registerStoredProcedureParameter(7, String.class, ParameterMode.IN); // cliente
	 	 
	 query.setParameter(1, timein);
	 query.setParameter(2, timefin);
	 query.setParameter(3, Integer.valueOf(sw));
	 try {
		 query.setParameter(4, Integer.valueOf(faena));	
	} catch (Exception e) {
		query.setParameter(4, 0);
	}
	 
	 query.setParameter(5, Integer.valueOf(rpm));
	 try {
		 query.setParameter(6, patente);	
	} catch (Exception e) {
		query.setParameter(6, "");
	}
	  
	 query.setParameter(7, usuarioclient); 	 
	 	 
	query.execute();

	ArrayList<ReportetInformeralentiCanActive> rlist = new ArrayList<ReportetInformeralentiCanActive>();

	List<Object[]> mapalist = query.getResultList();
		Integer i = 0;
		
		DecimalFormat df = new DecimalFormat("#######.##");


		for (Object[] ruta : mapalist) {
		i++;

		ReportetInformeralentiCanActive reportetInformeralentiCanActive = new ReportetInformeralentiCanActive();
		//  0          1                            2                      3        4      5           6                7           8                     9          10      11           12        13            14
		//"516"	"2019-03-12 08:48:09-03"	"2019-03-12 08:53:08-03"	"HKPC22"	299	"-22.73141"	"-069.30832"	" 12345678"	"No Registra Operador"	"CT.316"	"MACK"	"GRANITE"	"40094.79"	"0"	" Región de Antofagasta Mina Spence (BHP Billiton)"		1
		
					String patente2 =(String)ruta[3];
			String conductor =(String)ruta[8];
		    String nrInt =(String)ruta[9];
		    String ubicacion = (String)ruta[14]; 
		    Timestamp fechainicio = (Timestamp)ruta[1];		
		    Timestamp fechafin = (Timestamp) ruta[2];		   		    
		    Integer duracionralenti = (Integer) ruta[4];		   
		    Double combutil = (Double) ruta[13];
		    String lat = (String) ruta[5];
		    String lon = (String) ruta[6];		    
		    String modelo = (String) ruta[11];
		    String marca = (String) ruta[10];
		    
		 
		    reportetInformeralentiCanActive.setPatente(patente2);
		    reportetInformeralentiCanActive.setConductor(conductor);
		    reportetInformeralentiCanActive.setNrInt(nrInt);
		    reportetInformeralentiCanActive.setUbicacion(ubicacion);
		    reportetInformeralentiCanActive.setFechainicio(fechainicio);
		    reportetInformeralentiCanActive.setFechafin(fechafin);
		    reportetInformeralentiCanActive.setDuracionralenti(String.valueOf(duracionralenti));
		    reportetInformeralentiCanActive.setCombutil(combutil);
		    reportetInformeralentiCanActive.setLat(lat);
		    reportetInformeralentiCanActive.setLon(lon);
		    reportetInformeralentiCanActive.setModelo(modelo);
		    reportetInformeralentiCanActive.setMarca(marca);
		    
			String combutilString =  df.format(combutil);
			reportetInformeralentiCanActive.setCombutilS(combutilString.replace(",", ".").trim());

		    
		    rlist.add(reportetInformeralentiCanActive); 

		}


	em.clear();
	em.close();

	return rlist;
}

//usuLogin, desde, hasta,valor, tipo, clave, sw
public static List<ReporteInformeRendimientoActive> findInformeRendimientoPG(String usuario, String desde, String hasta,String patente, String faena, String sw1 ){

	String fechanulas = "";

	//dd-MM-yyyy
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
	DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); //dd/MM/yyyy
	em = emf.createEntityManager();


	Calendar calendarin = Calendar.getInstance();
	Calendar calendarfin = Calendar.getInstance();


	Date fecdesde= new Date();
	Date fechasta= new Date();

	//si las fechas vienen nulo se calcula el mes completo
	if(desde.equals("") && hasta.equals("")){

		fechanulas = "S";

	}

	try {
		fecdesde = formatteri.parse(desde);
		fechasta=  formatteri.parse(hasta);
	} catch (ParseException e) {

		e.printStackTrace();
	}

	//configuro las hora inicio y fin
	calendarin.setTime(fecdesde);
	
	//si las fechas vienen nul se resta el mes a la fecha inicio y fecha fin queda como dia hoy
	if(fechanulas.equals("S")){

		calendarfin.add(Calendar.DAY_OF_MONTH, -2);

	}

	calendarfin.setTime(fechasta);

	//las convierto timestamp para insertarlas en el query
	Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
	Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());
	
	 StoredProcedureQuery query= null;
	
	 //select * from informerendimiento('03/01/2019 00:00:00','03/18/2019 23:59:59','HKPC22','xsdf')
	 query = em.createStoredProcedureQuery("informerendimiento");
					
	  
	 query.registerStoredProcedureParameter(1, Timestamp.class, ParameterMode.IN); // in
	 query.registerStoredProcedureParameter(2, Timestamp.class, ParameterMode.IN); //fin
	 query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN); //patente
	 query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN); // usuarip
	 
	 query.setParameter(1, timein);
	 query.setParameter(2, timefin);
	 query.setParameter(3, patente);
	 query.setParameter(4, usuario);
	 	 	 	 
	 query.execute();
	 

	ArrayList<ReporteInformeRendimientoActive> rlist = new ArrayList<ReporteInformeRendimientoActive>();

	List<Object[]> mapalist = query.getResultList();
		Integer i = 0;

		for (Object[] ruta : mapalist) {
		i++;

		ReporteInformeRendimientoActive reporteInformeRendimientoActive = new ReporteInformeRendimientoActive();
        //  0     1       2        3                 4      5       6       7          8        9    10    11         12        13          14                 15    16      17      18          19       20   21     22         23        24          25         26      27                      28           29     30      31     32         33             34    35               				
		// gps  patnete  f       fecha ori           geo  nomgeo  even    lat ori      lon ori  hdg  spd  odo         lts       horo        fecha dest         geo   nomgeo  eve     lat         lon      hdg  spd    odo         lts      horo     recorrido    dist     lts                   rendimiento     tipo  nr     marca  modelo    velpro     
		//UM33, HKPC22,  null, 2019-03-01 08:39:44.0, 0, null,     null, -22.75744, -069.30224, 0,   19, 5.220618E7, 39566.92, 3729.55, 2019-03-14 13:48:53.0, null, null,   null, -22.72928, -069.31357, 0,   0,    5.294404E7, 40312.37, 3792.1, 19269,        737.86, 745.4500000000044, 0.9898182305989612, 0,   CT.316, MACK, GRANITE, 28.32917847025496, null, null]		
		
		
			String tmpdivece =(String)ruta[0];
		    String patente2 =(String)ruta[1];
		    String faena2 =(String)ruta[2];
		    String nrointerno = (String)ruta[30];
		    String marca = (String)ruta[31];
		    String modelo = (String)ruta[32];
		    
		    String origen = (String)ruta[5]; 
		    Timestamp salida = (Timestamp)ruta[3];
		    
		    String destino = "";		   
		    Timestamp llegada = null;
		    try {
		    	 destino = (String) ruta[16];		   
			     llegada = (Timestamp) ruta[14];
			} catch (Exception e) {
				 destino = "";		   
			     llegada = null;
			}
		    		    
		    String tporecorrido = "";
		   
		    	 tporecorrido = (String) ruta[25];	
		   if 	(tporecorrido == null ){
				 tporecorrido = "0";
		   }
	   
		    Double kmrecorrido = (Double) ruta[26];
			Double lstconsumidos = (Double) ruta[27];
			Double rendimiento = (Double) ruta[28];
			
			Double velocidapromedio = (Double) ruta[33];
			if(velocidapromedio == null ) {
				
				velocidapromedio = 0.0;
			}
			
			Double horoo = (Double) ruta[13];
			Double horod = (Double) ruta[24];
			
			Double horo = 0.0;
			
			if(horoo == 0 || horod == 0){
				
				 horo = 0.0;
			}else{
				
				 horo = horod - horoo;
			}
			
			
			
			//se divide con hodometro
			Double rendimientolthr = lstconsumidos/(Double.valueOf(horo));
			
			if(rendimientolthr.isNaN()){
				
				rendimientolthr = 0.0;
			}
			if(rendimientolthr.isInfinite()){
				
				rendimientolthr = 0.0;
			}
			
			 
		    reporteInformeRendimientoActive.setNroInt(nrointerno);
		    reporteInformeRendimientoActive.setPatente(patente2);
		    reporteInformeRendimientoActive.setMarca(marca);
		    reporteInformeRendimientoActive.setModelo(modelo);
		    
		    reporteInformeRendimientoActive.setOrigen(origen);
		    reporteInformeRendimientoActive.setSalidad(salida);
		    reporteInformeRendimientoActive.setDestino(destino);
		    reporteInformeRendimientoActive.setLlegada(llegada);
		    reporteInformeRendimientoActive.setTporecorrido(tporecorrido);
		    reporteInformeRendimientoActive.setKmrecorrido(String.valueOf(kmrecorrido));
		    reporteInformeRendimientoActive.setLstconsumidos(String.valueOf(lstconsumidos));
		    reporteInformeRendimientoActive.setRendimiento(String.valueOf(rendimiento));
		    reporteInformeRendimientoActive.setVelocidapromedio(String.valueOf(velocidapromedio));
		    reporteInformeRendimientoActive.setRendimientolthr(String.valueOf(rendimientolthr));
		    reporteInformeRendimientoActive.setHorometro(String.valueOf(horo));
		    
		    
		    
		    
		    rlist.add(reporteInformeRendimientoActive); 

		}


	em.clear();
	em.close();

	return rlist;
}

//usuLogincliente, desde, hasta, patente, faena
public static List<ReporteTiempoConduccionActive> findTiempoConduccion(String usuario, String desde, String hasta,String patente, String faena, String cliente, String conductor){

	String fechanulas = "";
	String sql = "";

	//dd-MM-yyyy
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
	DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); //dd/MM/yyyy
	em = emf.createEntityManager();
	


	Calendar calendarin = Calendar.getInstance();
	Calendar calendarfin = Calendar.getInstance();


	Date fecdesde= new Date();
	Date fechasta= new Date();

	//si las fechas vienen nulo se calcula el mes completo
	if(desde.equals("") && hasta.equals("")){

		fechanulas = "S";

	}

	try {
		fecdesde = formatteri.parse(desde);
		fechasta=  formatteri.parse(hasta);
	} catch (ParseException e) {

		e.printStackTrace();
	}

	//configuro las hora inicio y fin
	calendarin.setTime(fecdesde);
	
	//si las fechas vienen nul se resta el mes a la fecha inicio y fecha fin queda como dia hoy
	if(fechanulas.equals("S")){

		calendarfin.add(Calendar.DAY_OF_MONTH, -2);

	}

	calendarfin.setTime(fechasta);

	//las convierto timestamp para insertarlas en el query
	Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
	Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

	int sw = 0;
	int sw2 = 0;
	String valor = "";
	if(!patente.equals("")){
		
		sw = 0;
		faena = "0";
		sw2 = 0;
		valor = patente;
		
	}else if(!faena.equals("")){
		
		sw = 1;
		patente = "";
		sw2 = 0; 
		
		
	}else if(!conductor.equals("")){
		
		sw = 2;
		faena = "0";
		sw2 = 0;
		valor = conductor;
		
		
	}
		 	
	//select * from informedtpoconduccion(@fecha_ini,@fecha_fin,@sw,@faena,@patente,0,@cliente)
	//select * from informedtpoconduccion('03/01/2019 00:00:00','03/21/2019 23:59:00',0,0,'HKPC22',50,'emexcosa')
	//select * from informedtpoconduccion('03/01/2019 00:00:00','03/22/2019 23:59:00',1,1,'',0,'emexcosa')
	
	 StoredProcedureQuery query= null;
	 query = em.createStoredProcedureQuery("informedtpoconduccion");
						  
	 query.registerStoredProcedureParameter(1, Timestamp.class, ParameterMode.IN); // in
	 query.registerStoredProcedureParameter(2, Timestamp.class, ParameterMode.IN); //fin
	 query.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN); //sw
	 query.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN); // faena
	 query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN); // patente o conductor (valor)
	 query.registerStoredProcedureParameter(6, Integer.class, ParameterMode.IN); // sw2
	 query.registerStoredProcedureParameter(7, String.class, ParameterMode.IN); // client
	 
	 query.setParameter(1, timein);
	 query.setParameter(2, timefin);
	 query.setParameter(3, sw);
	 query.setParameter(4, Integer.valueOf(faena));
	 query.setParameter(5, valor);
	 query.setParameter(6, Integer.valueOf(sw2));
	 query.setParameter(7, cliente);
	 	 	 	 
	 query.execute(); 
	//    0       1            2                                                 3          4                      5                        6                    7              8         9           10           11   12            
    //   patente  dist       ubucacion                                         identifi     fin                    ini                      chofer              nroI            total   detenido      mov         lat   lon
	//"HKPC22"	"0.696"	" Región de Antofagasta Mina Spence (BHP Billiton)"	" 12345678"	"2019-03-13 08:26:04"	"2019-03-13 08:21:08"	"No Registra Operador"	"CT.316"	"00:04:56"	"00:02:38"	"00:03:03"
	ArrayList<ReporteTiempoConduccionActive> rlist = new ArrayList<ReporteTiempoConduccionActive>();

	List<Object[]> mapalist = query.getResultList();
		Integer i = 0;

		for (Object[] ruta : mapalist) {
		i++;

		ReporteTiempoConduccionActive reporteTiempoConduccionActive = new ReporteTiempoConduccionActive();
					
		    //Timer
			String patente2 =(String)ruta[0];
			String nrointerno =(String)ruta[7];		    
		    String identificador = (String)ruta[3];
		    String conductor2 = (String)ruta[6];
		    Timestamp inicio = (Timestamp)ruta[4];
		    Timestamp fin = (Timestamp)ruta[5];
		    String tiempoconduccion = (String) ruta[8];
			String timpoignicion = (String) ruta[9];
			String tiempomovimiento = (String) ruta[10];
			Double distancia = (Double) ruta[1];
			String lat = (String) ruta[11];
			String lon= (String) ruta[12];
			
			Calendar ctiempoactual = Calendar.getInstance();
			
			Timestamp tiempoactual = new Timestamp(ctiempoactual.getTimeInMillis());
			
			long diferencia =  tiempoactual.getTime()- inicio.getTime();
				
			long segundos = diferencia / 1000;
			
			String tiempoactuals = "";
			
			
			tiempoactuals = UtilServicio.FormatoTimer(segundos);
			
			Double velpro = 0.0;
			
			em1 = emf.createEntityManager();
			
			Query query02 = em1.createNativeQuery("select avg(rut_velocidad) from datos_gps "
					+ "  where rut_patente =  '"+patente2+"' " 					
					+ " and rut_fecha_hora >= '"+inicio+"' and rut_fecha_hora <= '"+fin+"' "									
					+ " ");
			
			BigDecimal velpro2 = (BigDecimal) query02.getSingleResult();
			
			try {
				velpro = Double.valueOf(String.valueOf(velpro2));
			} catch (Exception e) {
				velpro = 0.0;
				e.printStackTrace();
				
			}finally{
				
				em1.clear();
				em1.close();
				
			}
				
		    String ubicacionfinal = (String)ruta[2];
		   		    
		    reporteTiempoConduccionActive.setPatente(patente2);
		    reporteTiempoConduccionActive.setNrointerno(nrointerno);		    
		    reporteTiempoConduccionActive.setIdentificador(identificador);
		    reporteTiempoConduccionActive.setConductor(conductor2);
		    reporteTiempoConduccionActive.setInicio(inicio);
		    reporteTiempoConduccionActive.setFin(fin);
		    reporteTiempoConduccionActive.setTiempoconduccionS(tiempoconduccion);
		    reporteTiempoConduccionActive.setTimpoignicionS(timpoignicion);
		    reporteTiempoConduccionActive.setTiempomovimientoS(tiempomovimiento);
		    reporteTiempoConduccionActive.setDistancia(distancia);
		    reporteTiempoConduccionActive.setVelpro(velpro);
		    reporteTiempoConduccionActive.setUbicacionfinal(ubicacionfinal);
		    reporteTiempoConduccionActive.setLat(lat);
		    reporteTiempoConduccionActive.setLon(lon);
		   
		     	reporteTiempoConduccionActive.setTiempoconduccion(0.0);
			    reporteTiempoConduccionActive.setTimpoignicion(0.0);
			    reporteTiempoConduccionActive.setTiempomovimiento(0.0);
			
		    
			//esta en conduccion acuaul
			if((timpoignicion.equals("") && tiempomovimiento.equals("")) ||(timpoignicion.equals(null) && tiempomovimiento.equals(null)) ){
				
				reporteTiempoConduccionActive.setEnconduccion("SI");
								
				//tiempoactuals
				reporteTiempoConduccionActive.setTiempoconduccionS("<spam style='color: red'> En conducción</spam>" );
				reporteTiempoConduccionActive.setTimpoignicionS("00:00:00");
			    reporteTiempoConduccionActive.setTiempomovimientoS("00:00:00");
				
			}
			
		    		    		    
		    rlist.add(reporteTiempoConduccionActive); 

		}//fin for


	em.clear();
	em.close();

	return rlist;
}


public static List<HistoricoActive> findHistoricoPG2(String desde ,String hasta ,String patente, String velocidad, Vehiculo vehiculo, String evento) {
	
	String fechanulas = "";

	//dd-MM-yyyy
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
	DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); //dd/MM/yyyy
	em = emf.createEntityManager();


	Calendar calendarin = Calendar.getInstance();
	Calendar calendarfin = Calendar.getInstance();


	Date fecdesde= new Date();
	Date fechasta= new Date();

	//si las fechas vienen nulo se calcula el mes completo
	if(desde.equals("") && hasta.equals("")){

		fechanulas = "S";

	}

	try {
		fecdesde = formatteri.parse(desde);
		fechasta=  formatteri.parse(hasta);
	} catch (ParseException e) {

		e.printStackTrace();
	}

	//configuro las hora inicio y fin
	calendarin.setTime(fecdesde);
	
	//si las fechas vienen nul se resta el mes a la fecha inicio y fecha fin queda como dia hoy
	if(fechanulas.equals("S")){

		calendarfin.add(Calendar.DAY_OF_MONTH, -2);

	}

	calendarfin.setTime(fechasta);

	//las convierto timestamp para insertarlas en el query
	Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
	Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());
	
	
	int velocidad2 = 0;
	if(velocidad==null ||velocidad.isEmpty()){
		velocidad2 = 0;
	}else if (velocidad.equals("")){
		velocidad2 = 0;
	}else{
		velocidad2 = Integer.valueOf(velocidad);
		
	}
	
	/*Query query = em.createNativeQuery("select * from informehistorico("
			+ " '"+timein+"', '"+timefin+"', '"+patente+"', "+velocidad+" " 															
			+ " )");*/
	//select * from informehistorico('2019-03-03 00:00:00','2019-03-08 23:59:00','HKPC22',0)
	StoredProcedureQuery query = em.createStoredProcedureQuery("informehistorico_por_evento");
	
	 query.registerStoredProcedureParameter(1, Timestamp.class, ParameterMode.IN); //fechan in
	 query.registerStoredProcedureParameter(2, Timestamp.class, ParameterMode.IN); // fecha fin
	 query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN); // tipo
	 query.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN); // valor   
	 query.registerStoredProcedureParameter(5, Integer.class, ParameterMode.IN); // evento
	 
	 query.setParameter(1, timein);
	 query.setParameter(2, timefin);
	 query.setParameter(3, patente);
	 try {
			query.setParameter(4, Integer.valueOf(velocidad));
		} catch (Exception e) {
			query.setParameter(4, 0);
		}
	 try {
			query.setParameter(5, Integer.valueOf(evento));
		} catch (Exception e) {
			query.setParameter(5, 0);
		}	 
	 
	 System.out.println(evento);
	
	 ArrayList<HistoricoActive> mlist = new ArrayList<HistoricoActive>();
		
 	
	 	List<Object[]> eventlist = query.getResultList();
 		Integer i = 0;
 		
 	
 		for (Object[] d : eventlist) {
			i++;
			
 //d.id.devIdDevice, d.id.rutPatente, d.eventosGp.eveIdId, d.eventosGp.eveNombre, d.rutGeocerca, d.rutGoecoding, d.rutLatitud, d.rutLongitud,d.rutOdometro, d.rutOrientacion, d.rutVelocidad, d.id.rutFechaHora as fecha, chofer
	// 0               1                      2                 3                  4               5               6              7            8               9                10                      11               	12
			
			HistoricoActive historicoActive = new HistoricoActive();
			
			String devIdDevice = (String) d[0];
			String rutPatente = (String) d[1];
			Integer eveIdId = (Integer) d[2];
			String eveNombre = (String) d[3];
			String rutGeocerca = (String) d[4];
			String rutGoecoding = (String) d[5];
			String rutLatitud = (String) d[6];
			String rutLongitud = (String) d[7];
			Double rutOdometro = (Double) d[8];
			Integer rutOrientacion = (Integer) d[9];
			Integer rutVelocidad = (Integer) d[10];
			
			//Calendar current = Calendar.getInstance(TimeZone.getTimeZone("GMT"));			
			//Timestamp	 rutFechaHora = new Timestamp(current.getTimeInMillis());						
			//rutFechaHora = (Timestamp) d[11];
			//System.out.println(d[11].toString());
			//String rutFechaHora = (String) d[11];
			Timestamp rutFechaHora = (Timestamp) d[11];
			//System.out.println(rutFechaHora);
			
			//PropertyConfigurator.configure(log4jConfPath);
			//logger.error("rutFechaHora objet "+d[11].toString());
			//logger.error("rutFechaHora Timestamp "+rutFechaHora);
						 
			String chofer = (String) d[12];
						    
			    historicoActive.setIdVehicle(devIdDevice);
			    
			     historicoActive.setLat(rutLatitud.replaceAll("-0", "-"));
				 historicoActive.setLon(rutLongitud.replaceAll("-0", "-"));
				 historicoActive.setSpd(String.valueOf(rutVelocidad));
				 
				 
				 historicoActive.setData_date(rutFechaHora);
				 
				 historicoActive.setChofer(chofer); 
				 historicoActive.setPatente(rutPatente);
				 if(!(rutGeocerca == null) ){
					 historicoActive.setUbicacion(rutGeocerca);
					 
				 }else{
					 historicoActive.setUbicacion(rutGoecoding);
					 
				 }
				 				
				
				 String classEvent = UtilServicio.getClassEvent(eveIdId, eveNombre);
				 historicoActive.setClassEvent(classEvent);
				 historicoActive.setIdEvent(eveIdId);
				 historicoActive.setNombreve(eveNombre);
				 String classEventJs = UtilServicio.getClassEventJS(eveIdId, eveNombre);
				 historicoActive.setClassEventJs(classEventJs);
				 
				 
				 historicoActive.setNumInterno(vehiculo.getVehNumInterno());
			   
				 FlechaActive flecha = UtilServicio.getFlecha(rutOrientacion);
				 
				 historicoActive.setNomflecha(flecha.getNomflecha());
				 historicoActive.setRutaflecha(flecha.getRutaflecha());
			   			   
			    mlist.add(historicoActive);
			    
 		}
 			
 		em.clear();
 		em.close();
	 
	 
    return  mlist;
}




}
