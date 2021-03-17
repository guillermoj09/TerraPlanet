package cl.samtech.sgomt.service;

import java.io.Console;
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
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import cl.samtech.sgomt.controller.ListadoIconos;
import cl.samtech.sgomt.form.GeocercasForm;
import cl.samtech.sgomt.form.MarcaterrenoForm;
import cl.samtech.sgomt.model.Vehiculo;
import cl.samtech.sgomt.object.CercosVirtualesActive;
import cl.samtech.sgomt.object.CoordenadasActive;
import cl.samtech.sgomt.object.FaenaActive;
import cl.samtech.sgomt.object.HistoricoActive;
import cl.samtech.sgomt.object.ListadoGeocercasActive;
import cl.samtech.sgomt.object.ListadoMarcaTerrenoActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.object.kml.Document;
import cl.samtech.sgomt.object.kml.Folder;
import cl.samtech.sgomt.object.kml.Icon;
import cl.samtech.sgomt.object.kml.IconStyle;
import cl.samtech.sgomt.object.kml.LineString;
import cl.samtech.sgomt.object.kml.LineStyle;
import cl.samtech.sgomt.object.kml.LinearRing;
import cl.samtech.sgomt.object.kml.OuterBoundaryIs;
import cl.samtech.sgomt.object.kml.Pair;
import cl.samtech.sgomt.object.kml.Placemark;
import cl.samtech.sgomt.object.kml.Point;
import cl.samtech.sgomt.object.kml.PolyStyle;
import cl.samtech.sgomt.object.kml.Polygon;
import cl.samtech.sgomt.object.kml.Style;
import cl.samtech.sgomt.object.kml.StyleMap;
import cl.samtech.sgomt.object.kml.hotSpot;
import cl.samtech.sgomt.util.Deg2UTM;

public class AdministracionService {
	
	
	private static EntityManager em;
	private static EntityManager em1;
	private static EntityManager em2;
	private static EntityManager em3;
	private static EntityManager em4;
	
	//private static String log4jConfPath = "/opt/tomcat9/webapps/web/log4j.properties";
	
	final static Logger logger = Logger.getLogger(ReporteService.class);
	
	public static ArrayList<ListadoGeocercasActive> findListadoGeocercos(String cliente, String clave){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		em = emf.createEntityManager();	
		
		//Query query = em.createNativeQuery("select * from public.mantenedorzonas_2('nombre2',1,4,'i' ,'','', '','emexcosa','',0,0,0,0,0,0)  ");
		Query query = em.createNativeQuery("select * from public.mantenedorzonas_2('nombre2',1,4,'i' ,'','', '','"+cliente+"','',0,0,0,0,0,0)  ");
				
		ArrayList<ListadoGeocercasActive> GeoList = new ArrayList<ListadoGeocercasActive>();
		
		List<Object[]> glist = query.getResultList();
		
		for(Object[] g : glist ){
			
			ListadoGeocercasActive geo = new ListadoGeocercasActive();
			geo.setId_geo((Integer)g[0]);
			geo.setNombre((String)g[1]);
			geo.setNombre_color((String)g[8]);
			geo.setCodigo_color((String)g[9]);
			geo.setArea((double)Math.round((Double)g[7]/1000));	
			
			GeoList.add(geo); 
			
		}
		
		em.clear();
 		em.close();
 		
		return GeoList;
		 
	}
	
	
	
	
//	public static String saveGeocerca(String cliente,String nombre, String color, String origen, String geomtext, Integer id_geo,GeocercasForm GForm){
	public static String saveGeocerca(String cliente,GeocercasForm GForm){
		//, GForm.getNombre(), GForm.getColor(), GForm.getOrigen(),GForm.getGeomText(),GForm.getId_geo()
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		em = emf.createEntityManager();
		
		Integer i = 0; 
		String primero = "";
		Integer sw= 0;
		String salida = "POLYGON ((";
		
		if(GForm.getGeomText().substring(0, 5).equals("MULTI")){	
			
			String geomtext= GForm.getGeomText().replace("MULTIPOLYGON(((", "").replace(")))", "");
			
			String[] parts = geomtext.split(",",0);						
			for (String p : parts) {	
			 i++;
			 String[] parts2 = p.split(" ",0);		 
			 String lat = parts2[1];
			 String lon = parts2[0];		 
			 salida+= lon+" "+lat+",";
			 	if(i==1){
			 		primero = lon+" "+lat+",";
			 	}
			}
			
			salida+=primero+",))";
			salida =  salida.replace(",,", "");
		}
		else{
			
			String[] parts = GForm.getGeomText().split("\\),\\(",0);					
			for (String p : parts) {	
			 i++;
			 p = p.replace("(", "").replace(" ", "").replace(")", "");
			 String[] parts2 = p.split(",",0);		 
			 String lat = parts2[0];
			 String lon = parts2[1];		 
			 salida+= lon+" "+lat+",";
			 	if(i==1){
			 		primero = lon+" "+lat+",";
			 	}
			}
			
			salida+=primero+",))";
			salida =  salida.replace(",,", "");			
		}
		
		if(GForm.getOrigen().equals("crear")){
			sw= 1;
			GForm.setId_geo(0);
		}else{
			sw= 2;
		}
	
		StoredProcedureQuery query=em.createStoredProcedureQuery("public.mantenedorzonas_3");
		 
		 query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
	     query.registerStoredProcedureParameter(2, int.class, ParameterMode.IN);
	     query.registerStoredProcedureParameter(3, int.class, ParameterMode.IN);
	     query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
	     query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
	     query.registerStoredProcedureParameter(6, String.class, ParameterMode.IN);         
	     query.registerStoredProcedureParameter(7, String.class, ParameterMode.IN);    
	     query.registerStoredProcedureParameter(8, String.class, ParameterMode.IN);    
	     query.registerStoredProcedureParameter(9, String.class, ParameterMode.IN);    
	     query.registerStoredProcedureParameter(10, int.class, ParameterMode.IN); 
	     
	     query.registerStoredProcedureParameter(11, int.class, ParameterMode.IN); 
	     query.registerStoredProcedureParameter(12, int.class, ParameterMode.IN); 
	     query.registerStoredProcedureParameter(13, int.class, ParameterMode.IN); 
	     query.registerStoredProcedureParameter(14, int.class, ParameterMode.IN); 
	     query.registerStoredProcedureParameter(15, int.class, ParameterMode.IN); 
	     query.registerStoredProcedureParameter(16, int.class, ParameterMode.IN); 
	     query.registerStoredProcedureParameter(17, int.class, ParameterMode.IN); 
	     query.registerStoredProcedureParameter(18, String.class, ParameterMode.IN); 
	     
	     //select * from public.mantenedorzonas_2('nombre2',0,4,'i' ,'','', '','emexcosa','',0 ,0,0,0,0,0)
	     //	 zon_visible,zon_georeferencia,zon_vigencia,zon_apicacion,zon_fuera_operacion
	   
		 query.setParameter(1,GForm.getNombre());
		 query.setParameter(2,Integer.valueOf(GForm.getColor()));
		 query.setParameter(3,sw);
		 query.setParameter(4,GForm.getUso());
		 query.setParameter(5,"");
		 query.setParameter(6,"");
		 query.setParameter(7,salida);
		 query.setParameter(8,cliente);
		 query.setParameter(9,"");
		 query.setParameter(10,GForm.getId_geo());			 
		 query.setParameter(11,GForm.getVisible());	
		 query.setParameter(12,GForm.getGeoreferencia());	
		 query.setParameter(13,GForm.getVigencia());	
		 query.setParameter(14,GForm.getAplicacion());	
		 query.setParameter(15,GForm.getFuera());	
		 query.setParameter(16,GForm.getVelocidadAlarma());
		 query.setParameter(17,GForm.getEstadoAlarma());
		 query.setParameter(18,GForm.getCorreo());

		 query.execute();
		 em.clear();
		 em.close();
		
		return "";
	}
	
	
public static String deleteGeocerca(String cliente, Integer id_geo){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		em = emf.createEntityManager();	
		
		StoredProcedureQuery query=em.createStoredProcedureQuery("mantenedorzonas");
		 
		 query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
	     query.registerStoredProcedureParameter(2, int.class, ParameterMode.IN);
	     query.registerStoredProcedureParameter(3, int.class, ParameterMode.IN);
	     query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
	     query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
	     query.registerStoredProcedureParameter(6, String.class, ParameterMode.IN);         
	     query.registerStoredProcedureParameter(7, String.class, ParameterMode.IN);    
	     query.registerStoredProcedureParameter(8, String.class, ParameterMode.IN);    
	     query.registerStoredProcedureParameter(9, String.class, ParameterMode.IN);    
	     query.registerStoredProcedureParameter(10, int.class, ParameterMode.IN); 
	    
		 query.setParameter(1,"");
		 query.setParameter(2,0);
		 query.setParameter(3,3);
		 query.setParameter(4,"");
		 query.setParameter(5,"");
		 query.setParameter(6,"");
		 query.setParameter(7,"");
		 query.setParameter(8,cliente);
		 query.setParameter(9,"");
		 query.setParameter(10,id_geo);				 

		 query.execute();
	 
		 em.clear();
		 em.close();
		
		return "";
	}

public static Document findExportKMLgeo(ListadoGeocercasActive mlistGeoId2){

	Document document = new Document();  
	
	document.setName("geo_kml__"+mlistGeoId2.getNombre()+".kml");
	
		ArrayList<Style> styles = new ArrayList<Style>();
		Style style = new Style();
		style.setId("s_ylw-pushpin15");
	
			IconStyle iconStyle = new IconStyle();
	
				Icon icon = new Icon();
		     							
				icon.setHref("http://maps.google.com/mapfiles/kml/pushpin/ylw-pushpin.png");
				
				hotSpot hotSpot = new hotSpot();
				
				hotSpot.setX("20");
				hotSpot.setY("2");
				hotSpot.setXunits("pixels");
				hotSpot.setYunits("pixels");
				
		
			iconStyle.setIcon(icon);
			iconStyle.setScala("1.1");
			iconStyle.setHotSpot(hotSpot);
			
			LineStyle lineStyle = new LineStyle();
				lineStyle.setColor("ff000000");
				lineStyle.setWidth("2");
				
		    PolyStyle polyStyle = new PolyStyle();	
		    //polyStyle.setColor("8000aaff");
		    
		    String colorkml = UtilServicio.getRgbColorToKmlColor(mlistGeoId2.getCodigo_color(), "7f");// 50 % opacidad
		    
		    polyStyle.setColor(colorkml);
				
		style.setIconStyle(iconStyle);
		style.setLineStyle(lineStyle);
		style.setPolyStyle(polyStyle);
		
		Style style2 = new Style();
		style2.setId("s_ylw-pushpin_hl9");
		
			IconStyle iconStyle2 = new IconStyle();
		
				Icon icon2 = new Icon();
     							
				icon2.setHref("http://maps.google.com/mapfiles/kml/pushpin/ylw-pushpin.png");
		
					hotSpot hotSpot2 = new hotSpot();
		
					hotSpot2.setX("20");
					hotSpot2.setY("2");
					hotSpot2.setXunits("pixels");
					hotSpot2.setYunits("pixels");
		

			  iconStyle2.setIcon(icon2);
			  iconStyle2.setScala("1.3");
			  iconStyle2.setHotSpot(hotSpot2);
	
		  LineStyle lineStyle2 = new LineStyle();
		  	lineStyle2.setColor("ff000000");
		  	lineStyle2.setWidth("2");
		
		  PolyStyle polyStyle2 = new PolyStyle();	
		  String colorkml2 = UtilServicio.getRgbColorToKmlColor(mlistGeoId2.getCodigo_color(), "c6");// 80 % opacidad
		  	polyStyle2.setColor(colorkml2);
		
	 style2.setIconStyle(iconStyle2);
	 style2.setLineStyle(lineStyle2);
	 style2.setPolyStyle(polyStyle2);
	 
	 styles.add(style);
	 styles.add(style2);
		
		StyleMap styleMap = new StyleMap();
		styleMap.setId("m_ylw-pushpin21");
			ArrayList<Pair> pairs = new ArrayList<Pair>();
			Pair pair1 = new Pair();
			
				pair1.setKey("normal");
				pair1.setStyleUrl("#s_ylw-pushpin15");
				
			Pair pair2 = new Pair();	
			
				pair2.setKey("highlight");
				pair2.setStyleUrl("#s_ylw-pushpin_hl9");
				
			pairs.add(pair1);
			pairs.add(pair2);
		
		styleMap.setPairs(pairs);
				
	ArrayList<Placemark> placemarks = new ArrayList<Placemark>();  
	Placemark placemark = new Placemark();
	
		placemark.setName(mlistGeoId2.getNombre());
		placemark.setStyleUrl("#m_ylw-pushpin21");
		
			Polygon polygon = new Polygon(); 
			polygon.setTessellate("1");
			
				OuterBoundaryIs outerBoundaryIs = new OuterBoundaryIs();
					LinearRing linearRing = new LinearRing();
					
					mlistGeoId2.getCoordenadas();
					String coordinates = "";
					//formato ejemplo -70.04373147723042,-20.8854927871278,0 -70.0421029556836,-20.88452493443691,0 -70.04482595884932,-20.88292486066472,0 -70.04564981541751,-20.88335948401927,0 -70.04373147723042,-20.8854927871278,0 
					for (CoordenadasActive c : mlistGeoId2.getCoordenadas()) {
						
						//coordinates = coordinates+ c.getLon()+","+c.getLat()+",";
						 coordinates = c.getLon()+","+c.getLat()+" "+coordinates;
												
					}
										
					linearRing.setCoordinates(coordinates);
				outerBoundaryIs.setLinearRing(linearRing);
			polygon.setOuterBoundaryIs(outerBoundaryIs);
		placemark.setPolygon(polygon);
		placemarks.add(placemark);
	
	document.setStyles(styles);
	document.setStyleMap(styleMap);
	document.setPlacemarkers(placemarks);
	
	/* for (HistoricoActive h : listHistorico) {
		 
		 coordinates = h.getLon()+","+h.getLat()+" "+coordinates;
		 
	 }*/

	return document;
}

public static String unocero(Integer codigo){
	
	String retorna = new String(); 
	
	if (codigo == 1){
		retorna = "SI";
	}else{
		retorna = "NO";
	}
	
	return retorna;
}

public static List<ListadoMarcaTerrenoActive> findListadoMarcasTerreno(String usuLogincliente, String clavecliente, Integer id_marca) {
	// TODO Auto-generated method stub
	
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		em = emf.createEntityManager();	
		
		//Query query = em.createNativeQuery("select * from public.mantenedormarcasterreno('nombre',1,1,1,1,1,'emexcosa','20','30','-20','-30',"+id_marca+",4,''); ");
		Query query = em.createNativeQuery("select * from public.mantenedormarcasterreno('nombre',1,1,1,1,1,'"+usuLogincliente+"','20','30','-20','-30',"+id_marca+",4,''); ");
		
		ArrayList<ListadoMarcaTerrenoActive> marcas = new ArrayList<ListadoMarcaTerrenoActive>();
		
		List<Object[]> mlist = query.getResultList();
		
		for(Object[] m : mlist ){
			
			ListadoMarcaTerrenoActive mar = new ListadoMarcaTerrenoActive();			
				mar.setId_marca((Integer)m[0]);
				mar.setNombre((String)m[1]);
				mar.setColor((Integer)m[2]);
				mar.setVigencia((Integer)m[3]);
				mar.setTxt_vigencia((String) unocero((Integer)m[3]));
				mar.setMuestra((Integer)m[4]);
				mar.setTxt_muestra((String) unocero((Integer)m[4]));
				mar.setValido((Integer)m[5]);
				mar.setTxt_valido((String) unocero((Integer)m[5]));
				mar.setId_icono((Integer)m[6]);
				mar.setLat((String)m[7]);
				mar.setLon((String)m[8]);
				mar.setDescripcion((String)m[9]);	
				mar.setNombre_icono((String)m[10]);
			marcas.add(mar); 
			
			
		}
		
		em.clear();
		em.close();
			
		return marcas;
	
	
}

public static void saveMarcaterreno(String username, MarcaterrenoForm GForm) {
	// TODO Auto-generated method stub
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
	em = emf.createEntityManager();
	
	Integer i = 0; 
	String primero = "";
	Integer sw= 0;
	
	
	if(GForm.getOrigen().equals("crear")){
		sw= 1;
	}else{
		sw= 2;
	}
	/*
	-- select * from public.mantenedormarcasterreno(@nom_marca,@color,@vigencia,@muestra_mapa,@paradero,@icono,@usuario_login,@norte,@este,@lat,@lon,@id_mt,@sw)

	@sw=1, ingresa
	@sw=2, actualiza.
	@sw=3, elimina.

	-- select * from public.mantenedormarcasterreno('nombre',1,1,1,1,1,'emexcosa','20','30','-20','-30',0,1,'')

*/
	StoredProcedureQuery query=em.createStoredProcedureQuery("public.mantenedormarcasterreno");
	 
	 query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
     query.registerStoredProcedureParameter(2, int.class, ParameterMode.IN);
     query.registerStoredProcedureParameter(3, int.class, ParameterMode.IN);
     query.registerStoredProcedureParameter(4, int.class, ParameterMode.IN);
     query.registerStoredProcedureParameter(5, int.class, ParameterMode.IN);
     query.registerStoredProcedureParameter(6, int.class, ParameterMode.IN);         
     query.registerStoredProcedureParameter(7, String.class, ParameterMode.IN);    
     query.registerStoredProcedureParameter(8, String.class, ParameterMode.IN);    
     query.registerStoredProcedureParameter(9, String.class, ParameterMode.IN);    
     query.registerStoredProcedureParameter(10, String.class, ParameterMode.IN);      
     query.registerStoredProcedureParameter(11, String.class, ParameterMode.IN); 
     query.registerStoredProcedureParameter(12, int.class, ParameterMode.IN); 
     query.registerStoredProcedureParameter(13, int.class, ParameterMode.IN); 
     query.registerStoredProcedureParameter(14, String.class, ParameterMode.IN);
     	 
     Deg2UTM pos = new Deg2UTM(Double.parseDouble(GForm.getTxtLat0()),Double.parseDouble(GForm.getTxtLon0()));	 
     
     String north =  Double.toString(pos.Northing);
     String east = Double.toString(pos.Easting);
     String lat = GForm.getTxtLat0();
     String lon = GForm.getTxtLon0();
     
     if(north.length()>16){north= north.substring(0, 16);}
     if(east.length()>16){east= east.substring(0, 16);}
     if(lat.length()>12){lat= lat.substring(0, 12);}
     if(lon.length()>12){lon= lon.substring(0, 12);}
          
	 query.setParameter(1,GForm.getNombre());
	 query.setParameter(2,Integer.valueOf(1));
	 query.setParameter(3,GForm.getVigencia());
	 query.setParameter(4,GForm.getMuestra());
	 query.setParameter(5,GForm.getValido());
	 query.setParameter(6,GForm.getIcono());
	 query.setParameter(7,username);
	 query.setParameter(8,north);
	 query.setParameter(9,east);
	 query.setParameter(10,lat);		 
	 query.setParameter(11,lon);	
	 query.setParameter(12,GForm.getId_marca());	
	 query.setParameter(13,sw);	
	 query.setParameter(14,"");
	 

	 query.execute();
 
	 em.clear();
	 em.close();
	
	return;
	
}

public static List<ListadoIconos> findListadoIconos(String usuLogincliente, String clavecliente) {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
	em = emf.createEntityManager();	
	
	Query query = em.createNativeQuery("select * from cliente.icono_marcaterreno");
			
	ArrayList<ListadoIconos> iconos = new ArrayList<ListadoIconos>();
	
	List<Object[]> mlist = query.getResultList();
	
	for(Object[] m : mlist ){
		
		ListadoIconos ico = new ListadoIconos();			
			ico.setId_icono((Integer)m[0]);
			ico.setNombre((String)m[2]);
			ico.setRuta((String)m[1]);
			iconos.add(ico);		
	}
	
	em.clear();
	em.close();
		
	return iconos;
}

public static String deleteMarcaTerreno(String username, Integer id_marca) {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
	em = emf.createEntityManager();	
	
	 // select * from public.mantenedormarcasterreno('nombre',1,1,1,1,1,'emexcosa','20','30','-20','-80',1,3,'')

	StoredProcedureQuery query=em.createStoredProcedureQuery("public.mantenedormarcasterreno");
	 
	 query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
     query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
     query.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN);
     query.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN);
     query.registerStoredProcedureParameter(5, Integer.class, ParameterMode.IN);
     query.registerStoredProcedureParameter(6, Integer.class, ParameterMode.IN);         
     query.registerStoredProcedureParameter(7, String.class, ParameterMode.IN);    
     query.registerStoredProcedureParameter(8, String.class, ParameterMode.IN);    
     query.registerStoredProcedureParameter(9, String.class, ParameterMode.IN);    
     query.registerStoredProcedureParameter(10, String.class, ParameterMode.IN);
     query.registerStoredProcedureParameter(11, String.class, ParameterMode.IN);
     query.registerStoredProcedureParameter(12, int.class, ParameterMode.IN);
     query.registerStoredProcedureParameter(13, int.class, ParameterMode.IN);
     query.registerStoredProcedureParameter(14, String.class, ParameterMode.IN);
    
	 query.setParameter(1,"");
	 query.setParameter(2,1);
	 query.setParameter(3,1);
	 query.setParameter(4,1);
	 query.setParameter(5,1);
	 query.setParameter(6,1);
	 query.setParameter(7,username);
	 query.setParameter(8,"");
	 query.setParameter(9,"");
	 query.setParameter(10,"");		
	 query.setParameter(11,"");	
	 query.setParameter(12,id_marca);	
	 query.setParameter(13,3);	
	 query.setParameter(14,"");	

	 query.execute();
 
	 em.clear();
	 em.close();
	
	return "";
	
	
}
	
	public static Document findExportKMLgeoTodos(List<ListadoGeocercasActive>  mlistGeo, String cliente){
		
		DateFormat formatteri2 = new SimpleDateFormat("dd_MM_yyyy");
    	Calendar today = Calendar.getInstance();
		String fechaname = formatteri2.format(today.getTime());

		Document document = new Document();  
		
		String nombre = "Totas";
		
		document.setName("geo_kml_todas_"+ fechaname+"_"+cliente+".kml");
		
		ArrayList<Folder> folders = new ArrayList<Folder>();
		
		for (ListadoGeocercasActive m : mlistGeo) {
				 
	  	Folder folder = new Folder();
	  	
	  	folder.setName(""+ m.getNombre() +"");
	  	folder.setOpen("2");
		
			ArrayList<Style> styles = new ArrayList<Style>();
			Style style = new Style();
			style.setId("s_ylw-pushpin15");
		
				IconStyle iconStyle = new IconStyle();
		
					Icon icon = new Icon();
			     							
					icon.setHref("http://maps.google.com/mapfiles/kml/pushpin/ylw-pushpin.png");
					
					hotSpot hotSpot = new hotSpot();
					
					hotSpot.setX("20");
					hotSpot.setY("2");
					hotSpot.setXunits("pixels");
					hotSpot.setYunits("pixels");
					
			
				iconStyle.setIcon(icon);
				iconStyle.setScala("1.1");
				iconStyle.setHotSpot(hotSpot);
				
				LineStyle lineStyle = new LineStyle();
					lineStyle.setColor("ff000000");
					lineStyle.setWidth("2");
					
			    PolyStyle polyStyle = new PolyStyle();	
			    //polyStyle.setColor("8000aaff");
			    
			    String colorkml = UtilServicio.getRgbColorToKmlColor(m.getCodigo_color(), "7f");// 50 % opacidad
			    
			    polyStyle.setColor(colorkml);
					
			style.setIconStyle(iconStyle);
			style.setLineStyle(lineStyle);
			style.setPolyStyle(polyStyle);
			
			if(m.getId_geo()!=107){
			Style style2 = new Style();
			style2.setId("s_ylw-pushpin_hl9");
			
				IconStyle iconStyle2 = new IconStyle();
			
					Icon icon2 = new Icon();
	     							
					icon2.setHref("http://maps.google.com/mapfiles/kml/pushpin/ylw-pushpin.png");
			
						hotSpot hotSpot2 = new hotSpot();
			
						hotSpot2.setX("20");
						hotSpot2.setY("2");
						hotSpot2.setXunits("pixels");
						hotSpot2.setYunits("pixels");
			

				  iconStyle2.setIcon(icon2);
				  iconStyle2.setScala("1.3");
				  iconStyle2.setHotSpot(hotSpot2);
		
			  LineStyle lineStyle2 = new LineStyle();
			  	lineStyle2.setColor("ff000000");
			  	lineStyle2.setWidth("2");
			
			  PolyStyle polyStyle2 = new PolyStyle();	
			  String colorkml2 = UtilServicio.getRgbColorToKmlColor(m.getCodigo_color(), "c6");// 80 % opacidad
			  	polyStyle2.setColor(colorkml2);
			
		 style2.setIconStyle(iconStyle2);
		 style2.setLineStyle(lineStyle2);
		 style2.setPolyStyle(polyStyle2);
		 styles.add(style2);
			}
		 
		 styles.add(style);
		
			
			StyleMap styleMap = new StyleMap();
			styleMap.setId("m_ylw-pushpin21");
				ArrayList<Pair> pairs = new ArrayList<Pair>();
				Pair pair1 = new Pair();
				
					pair1.setKey("normal");
					pair1.setStyleUrl("#s_ylw-pushpin15");
					
				Pair pair2 = new Pair();	
				
					pair2.setKey("highlight");
					pair2.setStyleUrl("#s_ylw-pushpin_hl9");
					
				pairs.add(pair1);
				pairs.add(pair2);
			
			styleMap.setPairs(pairs);
					
		ArrayList<Placemark> placemarks = new ArrayList<Placemark>();  
		Placemark placemark = new Placemark();
		
			placemark.setName(m.getNombre());
			placemark.setStyleUrl("#m_ylw-pushpin21");
			
				Polygon polygon = new Polygon(); 
				polygon.setTessellate("1");
				
					OuterBoundaryIs outerBoundaryIs = new OuterBoundaryIs();
						LinearRing linearRing = new LinearRing();
						
						m.getCoordenadas();
						String coordinates = "";
						//formato ejemplo -70.04373147723042,-20.8854927871278,0 -70.0421029556836,-20.88452493443691,0 -70.04482595884932,-20.88292486066472,0 -70.04564981541751,-20.88335948401927,0 -70.04373147723042,-20.8854927871278,0 
						for (CoordenadasActive c : m.getCoordenadas()) {
							
							//coordinates = coordinates+ c.getLon()+","+c.getLat()+",";
							 coordinates = c.getLon()+","+c.getLat()+" "+coordinates;
													
						}
											
						linearRing.setCoordinates(coordinates);
					outerBoundaryIs.setLinearRing(linearRing);
				polygon.setOuterBoundaryIs(outerBoundaryIs);
				
				placemark.setStyles(styles);
				placemark.setStyleMap(styleMap);
				
			placemark.setPolygon(polygon);
			placemarks.add(placemark);
			
	     folder.setPlacemarkers(placemarks);
		 folders.add(folder);
		
		
		}//fin for
	   	 
	 	document.setFolders(folders);
		
		return document;
	}
	
	public static List<ListadoMarcaTerrenoActive> findListadoAntenas(String clavecliente) {
		// TODO Auto-generated method stub
		
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
			em = emf.createEntityManager();	
			
			Query query = em.createNativeQuery("select mt_id, mt_nombre, mt_color, mt_vigencia, mt_lat, mt_lon, mt_descripcion ,ico_ruta, ico_nombre "
					+ "from cliente.marca_terreno ma, cliente.icono_marcaterreno i "
					+ "where ma.mt_icono = 2 "
					+ "and usu_rut_usuario = '"+clavecliente+"' "
					+ "and ma.mt_icono = i.ico_id ");
					
			ArrayList<ListadoMarcaTerrenoActive> marcas = new ArrayList<ListadoMarcaTerrenoActive>();
			
			List<Object[]> mlist = query.getResultList();
			
			for(Object[] m : mlist ){
				
				ListadoMarcaTerrenoActive mar = new ListadoMarcaTerrenoActive();			
					mar.setId_marca((Integer)m[0]);
					mar.setNombre((String)m[1]);
					mar.setColor((Integer)m[2]);
					mar.setVigencia((Integer)m[3]);					
					mar.setLat((String)m[4]);
					mar.setLon((String)m[5]);
					mar.setDescripcion((String)m[6]);	
					//mar.setId_icono((Integer)m[7]);	
					mar.setNombre_icono((String)m[7]);
				marcas.add(mar); 
				
				
			}
			
			em.clear();
			em.close();
				
			return marcas;
		
		
	}

}
