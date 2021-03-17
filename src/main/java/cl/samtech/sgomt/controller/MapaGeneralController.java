package cl.samtech.sgomt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.ListadoGeocercasActive;
import cl.samtech.sgomt.object.MapaUltimoGPS;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.ReporteService;
import cl.samtech.sgomt.service.UsuarioService;

@Controller
public class MapaGeneralController {
	@RequestMapping(value = "/mapageneral", method = RequestMethod.GET)
	public String showForm(Map model,HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
			return "home";
		}
												
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
			usuario.setUrlservlet(request.getServletPath());
		} catch (Exception e) {
			return "home";
		}
		//validad menu
		if(!UsuarioService.validarMenuUsuario(usuario, request.getServletPath())){		
			return "home";			
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuario, request.getServletPath());
		request.setAttribute("b", b);		
		
		//mapa ultimo
		List<MapaUltimoGPS>  mulist = new ArrayList<MapaUltimoGPS>();
		
		mulist = ReporteService.findVehiculoUltimaPosicion(rutcliente);
		
		MapaUltimoGPS mc = new MapaUltimoGPS();
		
		 try {

	    	mc = mulist.get(0);

	       } catch (Exception e) {

			mc.setLat("-24.2692571");
			mc.setLon("-69.0809277");

	       }
	       		
		 List<ListadoGeocercasActive>  mlistGeo = new ArrayList<ListadoGeocercasActive>();
	     mlistGeo = ReporteService.findGeocercaByIdPg( usuLogincliente, clavecliente, "0");
	     
	     //Ubico geocerca de proyecto central para hacer zoom y center dinamico
	     List<ListadoGeocercasActive>  mlistGeoId = new ArrayList<ListadoGeocercasActive>(); 
	     mlistGeoId = ReporteService.findGeocercaByIdPg( usuLogincliente, clavecliente, "107");
	     ListadoGeocercasActive mlistGeoId2 = new ListadoGeocercasActive(); 
	     try {
	    	  mlistGeoId2 = mlistGeoId.get(0);			
		} catch (Exception e) {			
			 mlistGeoId2 = null;		
		}
    	     	 	     
	     mc.setCollapseshow("SI");
	     //fin mapa ultimo
	     
	     //Test zona 	     
	    // Zona zona = ReporteService.findZona(66);
	     
	     request.setAttribute("mc", mc);
		 request.setAttribute("mulist", mulist);
	     request.setAttribute("mlistGeo", mlistGeo);
	     request.setAttribute("mlistGeoId2", mlistGeoId2);
	     				
		return "mapageneral";
	}		
	
}