package cl.samtech.sgomt.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cl.samtech.sgomt.form.LoginForm;

@Controller
public class ServiciosController {
	
	
	@RequestMapping(value = "/servicios" , method = RequestMethod.POST )
	public ResponseEntity<?> servicios(LoginForm loginForm, BindingResult result,
			Map model, HttpServletRequest request,  HttpServletResponse response) {
		
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With, Accept,Origin ");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT");
	    		
		String log4jConfPath = "/opt/tomcat9/webapps/web/log4j.properties";

		
		
		PropertyConfigurator.configure(log4jConfPath);
		
		String username = null;
		String password = null;
			
		username = loginForm.getUsername();
		password = loginForm.getPassword();
			
		String rutcliente =  request.getParameter("rutcliente");
				
		System.out.println("Llamada servicio curl"+ rutcliente);
		
		 return new ResponseEntity("Successfully login: "+username+"-"+password+" ", new HttpHeaders(), HttpStatus.OK);
				
	}
}