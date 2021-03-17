package cl.samtech.sgomt.controller;

import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cl.samtech.sgomt.model.Modulo;
import cl.samtech.sgomt.model.Usuario;
import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.MenuActive;
import cl.samtech.sgomt.object.MenuForm;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.UsuarioService;

@Controller
public class CrearMenuController {
	@RequestMapping(value = "/crearmenu", method = RequestMethod.GET)
	public String showForm(Map model,HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
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
			usuario.setUrlservlet(request.getServletPath());
		} catch (Exception e) {			
			return "home";			
		}
		
		BarraMenuActive b = new BarraMenuActive();
		b.setModulo("Administracion");
		b.setMenu("Menu");
		b.setSubmenu("Crear Menu");
		request.setAttribute("b", b);		
		
		String id=request.getParameter("id");//la llave primaria de modulo
		
		Usuario u = UsuarioService.findUsuario(usuario.getRut());
				
		ArrayList<Modulo> modulos =  UsuarioService.findAllModulos(u);		
		Modulo modulo = UsuarioService.findModulo(Integer.parseInt(id));
				
		MenuForm menuForm = new MenuForm();
		model.put("menuForm", menuForm);
		
		request.setAttribute("modulos", modulos);
		request.setAttribute("modulo", modulo);
						
		return "crearmenu";
	}
	
	@RequestMapping(value = "/crearmenu" , method = RequestMethod.POST)
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
		BarraMenuActive b = new BarraMenuActive();
		b.setModulo("Administracion");
		b.setMenu("Menu");
		b.setSubmenu("Crear Menu");
		Usuario u = UsuarioService.findUsuario(usuario.getRut());
		
		menuForm = (MenuForm) model.get("menuForm");
				
		if(UsuarioService.guardarMenu(menuForm)){
			
			//exito
			mensaje = "Menu guardado con exito";
			estilo = "alert alert-success";
			request.setAttribute("mensaje", mensaje);
			request.setAttribute("estilo", estilo);
	
			Modulo modulo = UsuarioService.findModulo(Integer.parseInt(menuForm.getModulo()));
			
			ArrayList<MenuActive> menus = new ArrayList<MenuActive>();
									
			menus = UsuarioService.findMenuByModulo(modulo, "SI");
						
			request.setAttribute("menus", menus);
			request.setAttribute("modulo", modulo);
					
			 // return "redirect:/consultarmenuxmodulo";
			return "consultarmenuxmodulo";
			
		}else {
			
									
			ArrayList<Modulo> modulos =  UsuarioService.findAllModulos(u);		
			Modulo modulo = UsuarioService.findModulo(Integer.parseInt(menuForm.getModulo()));
								
			mensaje = "Error al guardar menu, consultar con el Administrador";
			estilo = "alert alert-danger";
			request.setAttribute("mensaje", mensaje);
			request.setAttribute("estilo", estilo);
			
			request.setAttribute("modulo", modulo);
			request.setAttribute("modulos", modulos);
						
			  
			return "crearmenu";
		}

	
	}
	
}