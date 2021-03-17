package cl.samtech.sgomt.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import cl.samtech.sgomt.model.Modulo;
import cl.samtech.sgomt.model.Usuario;
import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.UsuarioService;

@Controller
public class ConsultarModuloController {
	@RequestMapping("/consultarmodulo")
	public String monitoreo(HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
			return "home";
		}
				
		ArrayList<Modulo> modulos = new ArrayList<Modulo>();
					
		UsuarioLogin usuariologin = (UsuarioLogin) request.getSession().getAttribute("usuario");
		usuariologin.setUrlservlet(request.getServletPath());
		//validad menu
		if(!UsuarioService.validarMenuUsuario(usuariologin, request.getServletPath())){		
			return "home";			
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuariologin, request.getServletPath());
		request.setAttribute("b", b);		
		
		Usuario usuario = UsuarioService.findUsuario(usuariologin.getRut());
		
		modulos = UsuarioService.findAllModulos(usuario);		
					
		request.setAttribute("modulos", modulos);
		
		return "consultarmodulo";
	}
}