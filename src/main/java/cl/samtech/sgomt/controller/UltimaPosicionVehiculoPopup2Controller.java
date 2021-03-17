package cl.samtech.sgomt.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cl.samtech.sgomt.object.MapaUltimoGPSJson;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.ReporteService;


@Controller
public class UltimaPosicionVehiculoPopup2Controller {
	@RequestMapping(value = "/ultimaposicionvehiculopopup2", method = RequestMethod.GET)
	public String showForm(Map model,HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
			return "home";
		}
				
		UsuarioLogin usuario=(UsuarioLogin)request.getSession().getAttribute("usuario");
		String rut = "";
		String clave = "";
		String usuLogincliente = "";
		String clavecliente = "";	
		String rutcliente = "";
		try {
			rut  = usuario.getRut();
			clave  = usuario.getPassword();
			usuLogincliente  = usuario.getCliUsuSamtech();
			clavecliente  = usuario.getCliPassSamtech();
			rutcliente = usuario.getClienterut();
		} catch (Exception e) {
			return "home";
		}
		
		List<MapaUltimoGPSJson>  mulist = new ArrayList<MapaUltimoGPSJson>();
				
		mulist = ReporteService.findVehiculoUltimaPosicionAjax(rutcliente, rut);

	    request.setAttribute("mulist", mulist);
					
		return "ultimaposicionvehiculopopup2";	
	}		
	
}