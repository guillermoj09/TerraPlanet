package cl.samtech.sgomt.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import cl.samtech.sgomt.model.Modulo;
import cl.samtech.sgomt.model.Usuario;
import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.MenuActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.UsuarioService;

@Controller
public class ConsultarMenuXModuloController {
	@RequestMapping("/consultarmenuxmodulo")
	public String monitoreo(HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
			return "home";
		}
		String id =  request.getParameter("id");
				
		Modulo modulo = UsuarioService.findModulo(Integer.parseInt(id));
				
		ArrayList<MenuActive> menus = new ArrayList<MenuActive>();
					
		UsuarioLogin usuariologin = (UsuarioLogin) request.getSession().getAttribute("usuario");
		usuariologin.setUrlservlet(request.getServletPath());
		//validad menu
		if(!UsuarioService.validarMenuUsuario(usuariologin, "consultarmodulo")){		
			return "home";			
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuariologin, "consultarmodulo");
		request.setAttribute("b", b);		
		
		Usuario usuario = UsuarioService.findUsuario(usuariologin.getRut());
		
		menus = UsuarioService.findMenuByModulo(modulo, "SI");
		//menus = UsuarioService.findMenuActivebyModulo(usuario, modulo, "SI");
					
		request.setAttribute("menus", menus);
		request.setAttribute("modulo", modulo);
		
		return "consultarmenuxmodulo";
	}
}