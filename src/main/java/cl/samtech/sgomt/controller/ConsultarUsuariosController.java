package cl.samtech.sgomt.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.UsuarioService;

@Controller
public class ConsultarUsuariosController {
	@RequestMapping("/consultarusuarios")
	public String monitoreo(HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
			return "home";
		}
		UsuarioLogin usuario=(UsuarioLogin)request.getSession().getAttribute("usuario");						
		String clave = "";
		String usuLogin = "";
		String usuLogincliente = "";
		String clavecliente = "";
		String clienteRut = "";
		Integer perfilid = 0; 
		try {
			usuLogin  = usuario.getUsername();			
			clave  = usuario.getPassword();
			usuLogincliente  = usuario.getCliUsuSamtech();
			clavecliente  = usuario.getCliPassSamtech();
			clienteRut = usuario.getClienterut();
			perfilid = usuario.getPerfilid();
			usuario.setUrlservlet(request.getServletPath());
		} catch (Exception e) {			
			return "home";			
		}
		
		ArrayList<UsuarioLogin> usuarios = new ArrayList<UsuarioLogin>();
		
		//validad menu
		if(!UsuarioService.validarMenuUsuario(usuario, request.getServletPath())){		
			return "home";			
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuario, request.getServletPath());
		request.setAttribute("b", b);		
		
		//operado admin cliente
		if(perfilid == 7 || perfilid == 8){
			
			usuarios=UsuarioService.allUsuariosbyPerfil(clienteRut);
			
		}
		//admin samtech
		else if(perfilid == 6){
			
			usuarios=UsuarioService.allUsuariosbyCliente(clienteRut);
			
		}else { // en caso que se cree un nuevo perfil
			
			usuarios=UsuarioService.allUsuariosbyPerfil(clienteRut);
			
		}
						
		//usuarios=UsuarioService.allUsuariosbyCliente(clienteRut);
		
		request.setAttribute("usuarios", usuarios);
		
		return "consultarusuarios";
	}
}