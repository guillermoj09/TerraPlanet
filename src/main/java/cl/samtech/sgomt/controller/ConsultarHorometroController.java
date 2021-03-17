package cl.samtech.sgomt.controller;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.UsuarioService;


@Controller
public class ConsultarHorometroController {
	@RequestMapping(value = "/consultarhorometro", method = RequestMethod.GET)
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
		
		UsuarioLogin usuariologin = (UsuarioLogin) request.getSession().getAttribute("usuario");		
		//validad menu
		if(!UsuarioService.validarMenuUsuario(usuariologin, request.getServletPath())){		
			return "home";			
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuariologin, request.getServletPath());
		request.setAttribute("b", b);	
				
		return "consultarhorometro";	
	}		
	
}