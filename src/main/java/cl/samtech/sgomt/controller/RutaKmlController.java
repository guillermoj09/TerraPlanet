package cl.samtech.sgomt.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import cl.samtech.sgomt.object.kml.Document;
import cl.samtech.sgomt.service.GestionService;

@Controller
public class RutaKmlController {

@RequestMapping(value = "/downloadKML")
public void downloadXML(HttpServletRequest request,
        HttpServletResponse response) throws IOException, JAXBException {
	
	if(request.getSession().getAttribute("usuario")!=null)
	{
    
	String patente = request.getParameter("patente");
	String fechain = request.getParameter("fechain");
	String fechafin =  request.getParameter("fechafin");
	String velocidad =  request.getParameter("velocidad");
	
	Document document = GestionService.findExportKMLHistorico(fechain, fechafin, patente, velocidad);
    	
    try {
    	
    	String fechaname = ""; 
    	DateFormat formatteri2 = new SimpleDateFormat("dd_MM_yyyy");
    	Calendar today = Calendar.getInstance();
		 fechaname = formatteri2.format(today.getTime());
    	
    	String filename = "RutaSgomt_"+fechaname+".kml";

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