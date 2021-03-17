package cl.samtech.sgomt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import cl.samtech.sgomt.model.Vehiculo;
import cl.samtech.sgomt.object.ListadoGeocercasActive;
import cl.samtech.sgomt.object.MapaUltimoGPS;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.DispositivoService;
import cl.samtech.sgomt.service.ReporteService;

@Controller
public class MapaXPAtenteReporte {
	@RequestMapping(value = "/mapaxpatentereporte", method = RequestMethod.GET)
	public String showForm(Map model,HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
			return "home";
		}
		
		ModelAndView modelAndView = new ModelAndView("mapaxpatentereporte");
												
		UsuarioLogin usuario=(UsuarioLogin)request.getSession().getAttribute("usuario");			
		
		String cliente = "";
		String clave = "";
		String usuLogincliente = "";
		String clavecliente = "";	
		String rutcliente = "";
		try {
			cliente  = usuario.getUsername();
			clave  = usuario.getPassword();
			usuLogincliente  = usuario.getCliUsuSamtech();
			clavecliente  = usuario.getCliPassSamtech();
			rutcliente = usuario.getClienterut();
		} catch (Exception e) {
			return "home";
		}
		
		String patente = request.getParameter("patente");
		String lon = request.getParameter("lon");
		String lat = request.getParameter("lat");
		//String fecha = request.getParameter("fecha");
		//String vel =  request.getParameter("vel");
		
		
		//mapa ultimo
	 MapaUltimoGPS  mc = new MapaUltimoGPS();
	 
	 //mc.setFecha(fecha);
	 mc.setLon(lon);
	 mc.setLat(lat);
	 //mc.setVelocidad(vel);
	
	 Vehiculo v = DispositivoService.findVehiculo(patente);
	 
	 mc.setVehiculo(v);
	 
		//mc = ReporteService.findVehiculoUltimaPosicionxPatente(patente);
		   				 
	     List<ListadoGeocercasActive>  mlistGeo = new ArrayList<ListadoGeocercasActive>();
	     mlistGeo = ReporteService.findGeocercaByIdPg( usuLogincliente, clavecliente, "0");
	     
	     //mc.setCollapseshow("SI");
	     //fin mapa ultimo
	     
	     //agregar siguientes componentes
	     
	     request.setAttribute("mc", mc);		 
	     request.setAttribute("mlistGeo", mlistGeo);
				
		return "mapaxpatentereporte";
	}		
	
}