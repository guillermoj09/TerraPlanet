package cl.samtech.sgomt.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.ReporteTiempoFueraOperacionDetalleActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.ReporteService;
import cl.samtech.sgomt.service.UsuarioService;

@Controller
public class ReporteTiempoFueraOperacionDetalleController {
			
	@RequestMapping(value = "/reportetiempofueraoperaciondetalle" , method = RequestMethod.GET)
	public String process(HttpServletRequest request) {

		UsuarioLogin usuario=(UsuarioLogin)request.getSession().getAttribute("usuario");						
		String usuLogin = "";
		String clave = "";
		String usuLogincliente = "";
		String clavecliente = "";
		try {
			usuLogin  = usuario.getUsername();			
			clave  = usuario.getPassword();
			usuLogincliente  = usuario.getCliUsuSamtech();
			clavecliente  = usuario.getCliPassSamtech();	
			usuario.setUrlservlet(request.getServletPath());
		} catch (Exception e) {			
			return "home";			
		}
		//validad menu
		if(!UsuarioService.validarMenuUsuario(usuario, "reportetiemfueraoperacion.html")){		
			return "home";			
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuario, "reportetiemfueraoperacion.html");
		request.setAttribute("b", b);		
				
		 //reportetiempofueraoperaciondetalle.html?patente=${r.idcamion}&fechain=${rform.fechaDesde} ${rform.horaDesde}&fechafin=${rform.fechaHasta} ${rform.horaHasta}&zonaid=${r.zonaid}'"
		
		String patente = request.getParameter("patente");
		String fechain = request.getParameter("fechain");
		String fechafin =  request.getParameter("fechafin");
		String zonaid =  request.getParameter("zonaid");
		
		List<ReporteTiempoFueraOperacionDetalleActive> rlist = new ArrayList<ReporteTiempoFueraOperacionDetalleActive>();	
	
		if(!zonaid.equals("0")){
		rlist = ReporteService.findInformeGeocercas(fechain, fechafin, zonaid, patente, usuLogincliente);
		}else{		
		rlist = ReporteService.findInformeDetenciones(fechain, fechafin, zonaid, patente, usuLogincliente);
		}
									
		request.setAttribute("collapseshow", "SI");						
		request.setAttribute("rlist", rlist);								
				
		return "reportetiempofueraoperaciondetalle";
		
	}
	
}