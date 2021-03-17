package cl.samtech.sgomt.controller;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cl.samtech.sgomt.object.MapaUltimoGPSJson;
import cl.samtech.sgomt.object.StringResponse;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.DispositivoService;
import cl.samtech.sgomt.service.ReporteService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class ModificarHorometroController {
		
	final static Logger logger = Logger.getLogger(ModificarHorometroController.class);
							  
	@RequestMapping(value = "/modificarhorometro" , method = RequestMethod.POST) // produces = "application/json"	 //
	public @ResponseBody StringResponse ultimaposicionajax( HttpServletRequest request, HttpServletResponse response) {
		 
		StringResponse repuesta = new StringResponse(null);
	
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With, Accept,Origin ");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT");
			    
		String horometro =  request.getParameter("horometro");
		String gps =  request.getParameter("gps");
		String tipo =  request.getParameter("tipo");
		String patente =  request.getParameter("patente");
		
		
		boolean resp = true;
		boolean resp2 = true;
		
		UsuarioLogin usuariologin = (UsuarioLogin) request.getSession().getAttribute("usuario");	
			
		resp = DispositivoService.modificarHorometroSQLSERVER(Double.valueOf(horometro), usuariologin.getRut(), gps, tipo);
		
		if(resp){
			
			resp2 = DispositivoService.modificarHorometroPG(Double.valueOf(horometro), usuariologin.getRut(), gps, tipo, patente);
			
			if(resp2){
			
				repuesta.setResponse("Exito Modificacion");
				
			}else {
				
				repuesta.setResponse("Error en Modificacion PG");
				
			}			
			
		}else{
			
			repuesta.setResponse("Error en Modificacion SQL");
		}
		
		 return repuesta;
	
	}


}
