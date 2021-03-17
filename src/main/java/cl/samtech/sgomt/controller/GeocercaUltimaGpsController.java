package cl.samtech.sgomt.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cl.samtech.sgomt.object.GeocercaUltimaGpsActive;
import cl.samtech.sgomt.service.ReporteService;

import java.util.ArrayList;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class GeocercaUltimaGpsController {
	
	
	final static Logger logger = Logger.getLogger(GeocercaUltimaGpsController.class);
							  
	@RequestMapping(value = "/geocercaultimagps" , method = RequestMethod.POST) // produces = "application/json"
	 //List<StringResponse> en caso que sea una lista como respuesta
	public @ResponseBody List<GeocercaUltimaGpsActive> ultimaposicionajax( HttpServletRequest request, HttpServletResponse response) {
		 
		String log4jConfPath = "/opt/tomcat/apache-tomcat-9.0.2/webapps/web/log4j.properties";
		//String log4jConfPath = "C:/workspace/log4j.properties";
		//PropertyConfigurator.configure(log4jConfPath);
		
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With, Accept,Origin ");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT");		
	    	
		String rutcliente =  request.getParameter("rutcliente");

				
		List<GeocercaUltimaGpsActive>  glist = new ArrayList<GeocercaUltimaGpsActive>();
				
		glist = ReporteService.findGeocercaUltimaGpsAjax(rutcliente);
		
		 return glist;
	
	}


}
