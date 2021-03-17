package cl.samtech.sgomt.controller;

import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import cl.samtech.sgomt.form.CrearUsuarioForm;
import cl.samtech.sgomt.form.ModuloForm;
import cl.samtech.sgomt.model.Menu;
import cl.samtech.sgomt.model.Modulo;
import cl.samtech.sgomt.model.Usuario;
import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.MenuActive;
import cl.samtech.sgomt.object.MenuForm;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.UsuarioService;

@Controller
public class ConsultarMenuXPadreController {
	@RequestMapping(value = "/consultarmenuxpadre", method = RequestMethod.GET)
	public String showForm(Map model,HttpServletRequest request) {
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
		try {
			usuLogin  = usuario.getUsername();			
			clave  = usuario.getPassword();
			usuLogincliente  = usuario.getCliUsuSamtech();
			clavecliente  = usuario.getCliPassSamtech();
			clienteRut = usuario.getClienterut();
			usuario.setUrlservlet(request.getServletPath());
		} catch (Exception e) {			
			return "home";			
		}
		//validad menu
				if(!UsuarioService.validarMenuUsuario(usuario, "consultarmodulo")){		
					return "home";			
				}
				//mando barra menu a la vista
				BarraMenuActive b = UsuarioService.barraMenu(usuario, "consultarmodulo");
				request.setAttribute("b", b);	
		
		MenuForm menuForm = new MenuForm();
		model.put("menuForm", menuForm);
		
		//id menu padre		
		String id=request.getParameter("id");
		
		Menu menu = UsuarioService.findMenu(Integer.parseInt(id));
		
		UsuarioLogin userlogin=UsuarioService.findUsuarioLogin(usuLogin);
		
		Usuario u = UsuarioService.findUsuario(userlogin.getRut());
		
		ArrayList<Menu> menus = UsuarioService.findSubMenuByMenuPadre(Integer.parseInt(id), "S", menu.getModulo().getModId());
		
		ArrayList<Menu> menus02 = UsuarioService.findSubMenuByMenuPadre(Integer.parseInt(id), "N", menu.getModulo().getModId());
				
		request.setAttribute("menu", menu);
		request.setAttribute("menus", menus);
		request.setAttribute("menus02", menus02);
		
		return "consultarmenuxpadre";
	}
	
	@RequestMapping(value = "/consultarmenuxpadre" , method = RequestMethod.POST)
	public String processForm(@Valid MenuForm menuForm, BindingResult result,
			Map model,HttpServletRequest request) {
		
		String mensaje = "";
		String estilo = "";
		

		if (result.hasErrors()) {
			return "home";
		}
		
		UsuarioLogin usuario=(UsuarioLogin)request.getSession().getAttribute("usuario");		
		String usuLogin = "";
		String clave = "";
		String usuLogincliente = "";
		String clavecliente = "";
		String rutCliente = "";
		try {
			usuLogin  = usuario.getUsername();			
			clave  = usuario.getPassword();
			usuLogincliente  = usuario.getCliUsuSamtech();
			clavecliente  = usuario.getCliPassSamtech();
			rutCliente = usuario.getClienterut();
		} catch (Exception e) {			
			return "home";			
		}
		//validad menu
		if(!UsuarioService.validarMenuUsuario(usuario, "consultarmodulo")){		
			return "home";			
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuario, "consultarmodulo");
		request.setAttribute("b", b);	
		
		Usuario u = UsuarioService.findUsuario(usuario.getRut());
		
		menuForm = (MenuForm) model.get("menuForm");
		
		
		if(UsuarioService.modificarMenuPadre(menuForm)){
			
			//exito
			mensaje = "Menu padre editado con exito";
			estilo = "alert alert-success";
			request.setAttribute("mensaje", mensaje);
			request.setAttribute("estilo", estilo);
			
			Modulo modulo = UsuarioService.findModulo(Integer.parseInt(menuForm.getModulo()));
			
			ArrayList<MenuActive> menus = new ArrayList<MenuActive>();
									
			menus = UsuarioService.findMenuByModulo(modulo, "SI");
						
			request.setAttribute("menus", menus);
			request.setAttribute("modulo", modulo);
		
			return "consultarmenuxmodulo";
			
		}else {
						
			mensaje = "Error al editar menu padre, consultar con el Administrador";
			estilo = "alert alert-danger";
			request.setAttribute("mensaje", mensaje);
			request.setAttribute("estilo", estilo);
			
			Menu menu = UsuarioService.findMenu(Integer.parseInt(menuForm.getMenId()));
			
			UsuarioLogin userlogin=UsuarioService.findUsuarioLogin(usuLogin);
			
			ArrayList<Menu> menus = UsuarioService.findSubMenuByMenuPadre(Integer.parseInt(menuForm.getMenId()), "S", menu.getModulo().getModId());
			
			ArrayList<Menu> menus02 = UsuarioService.findSubMenuByMenuPadre(Integer.parseInt(menuForm.getMenId()), "N", menu.getModulo().getModId());
					
			request.setAttribute("menu", menu);
			request.setAttribute("menus", menus);
			request.setAttribute("menus02", menus02);
			
			return "consultarmenuxpadre";
		}
		
	}
	
}