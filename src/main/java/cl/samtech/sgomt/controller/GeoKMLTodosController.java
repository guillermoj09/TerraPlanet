package cl.samtech.sgomt.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cl.samtech.sgomt.object.ListadoGeocercasActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.object.kml.Document;
import cl.samtech.sgomt.service.AdministracionService;
import cl.samtech.sgomt.service.GestionService;
import cl.samtech.sgomt.service.ReporteService;

@Controller
public class GeoKMLTodosController {

@RequestMapping(value = "/downloadgeotodoKML")
public void downloadXML(HttpServletRequest request,
        HttpServletResponse response) throws IOException, JAXBException {
	
	String usuLogincliente = "";
	if(request.getSession().getAttribute("usuario")!=null)
	{
		
		UsuarioLogin usuario=(UsuarioLogin)request.getSession().getAttribute("usuario");	
		
		String cliente = "";
		String clave = "";
	
		String clavecliente = "";
		
		cliente  = usuario.getUsername();
		clave  = usuario.getPassword();
		usuLogincliente  = usuario.getCliUsuSamtech();
		clavecliente  = usuario.getCliPassSamtech();
		
		
		 List<ListadoGeocercasActive>  mlistGeo = new ArrayList<ListadoGeocercasActive>();
		    
	     mlistGeo = ReporteService.findGeocercaByIdPg( usuLogincliente, clavecliente, "0");
	     
	     
		  Document document = AdministracionService.findExportKMLgeoTodos(mlistGeo, usuLogincliente);
	
	
    	
    try {
    	
    	String fechaname = ""; 
    	DateFormat formatteri2 = new SimpleDateFormat("dd_MM_yyyy");
    	Calendar today = Calendar.getInstance();
		 fechaname = formatteri2.format(today.getTime());
    	
    	String filename = "GeoKML_"+usuLogincliente+" "+fechaname+".kml";

        response.setContentType("application/kml");
        response.setHeader("Content-Disposition",
              
        		"attachment; filename="+filename+"");

        JAXBContext jaxbContext = JAXBContext.newInstance(Document.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        jaxbMarshaller.marshal(document, response.getOutputStream());
        
        response.flushBuffer();
    } catch (Exception e) {
        e.printStackTrace();
    }
    
	}//fin if usuario null

   }

  }