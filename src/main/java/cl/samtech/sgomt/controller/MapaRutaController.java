package cl.samtech.sgomt.controller;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cl.samtech.sgomt.model.Vehiculo;
import cl.samtech.sgomt.object.HistoricoActive;
import cl.samtech.sgomt.object.ListadoGeocercasActive;
import cl.samtech.sgomt.object.MapaUltimoGPS;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.DispositivoService;
import cl.samtech.sgomt.service.GestionService;
import cl.samtech.sgomt.service.ReporteService;

@Controller
public class MapaRutaController {
	@RequestMapping(value = "/maparuta", method = RequestMethod.GET)
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
		} catch (Exception e) {
			return "home";
		}
		
		String patente = request.getParameter("patente");
		String fechain = request.getParameter("fechain");
		String fechafin =  request.getParameter("fechafin");
		String velocidad =  request.getParameter("velocidad");
		
		String lat =  request.getParameter("lat");
		String lon=  request.getParameter("lon");
		
		List<HistoricoActive> listHistorico =  new ArrayList<HistoricoActive>();
		
		 Vehiculo vehiculo = DispositivoService.findVehiculoByPatente(patente);
									
			listHistorico = GestionService.findHistoricoPG(fechain, fechafin, patente, velocidad, vehiculo);
			
			MapaUltimoGPS mc = new MapaUltimoGPS(); 
			
			
			try {
				lat = listHistorico.get(0).getLat();
				lon = listHistorico.get(0).getLon();
			} catch (Exception e) {
				 lat =  request.getParameter("lat");
				 lon = request.getParameter("lon");
				
			}
			
			 //lat = lat.replaceAll("-0", "-");
			 //lon = lon.replaceAll("-0", "-");
			
			mc.setLat(lat);
			mc.setLon(lon);
			
			if(listHistorico.size()==0){
				
				String mensaje = "No hay resultado";
				request.setAttribute("mensaje", mensaje);	
			}
				 	 	 
		 List<ListadoGeocercasActive>  mlistGeo = new ArrayList<ListadoGeocercasActive>();
		 mlistGeo = ReporteService.findGeocercaByIdPg( usuLogincliente, clavecliente, "0");
	     
	     //Quito el primero y ultimo, para dejarselo a las bandera inicio y fin del mapa
	     List<HistoricoActive> listHistorico2 =  new ArrayList<HistoricoActive>();	     
	     listHistorico2 = listHistorico;
	     /*listHistorico2.remove(0);
	     listHistorico2.remove(listHistorico.size()-1);*/
	     
	     request.setAttribute("mc", mc);
	     request.setAttribute("listHistorico", listHistorico);
	     request.setAttribute("listHistorico2", listHistorico2);		 
	     request.setAttribute("mlistGeo", mlistGeo);
				
		return "maparuta";
	}		
	
}