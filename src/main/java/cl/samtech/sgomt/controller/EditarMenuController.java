package cl.samtech.sgomt.controller;

import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cl.samtech.sgomt.model.Cliente;
import cl.samtech.sgomt.model.Menu;
import cl.samtech.sgomt.model.Modulo;
import cl.samtech.sgomt.model.Usuario;
import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.MenuActive;
import cl.samtech.sgomt.object.MenuForm;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.UsuarioService;

@Controller
public class EditarMenuController {
	@RequestMapping(value = "/editarmenu", method = RequestMethod.GET)
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
		
		//validad menu
		BarraMenuActive b = new BarraMenuActive();
		b.setModulo("Administracion");
		b.setMenu("Menu");
		b.setSubmenu("Editar Menu");
		request.setAttribute("b", b);			
		
		Usuario u = UsuarioService.findUsuario(usuario.getRut());
		
		String id=request.getParameter("id");//la llave primaria de menu
		
		Menu menu = UsuarioService.findMenu(Integer.parseInt(id));
		ArrayList<Modulo> modulos =  UsuarioService.findAllModulos(u);		
				
		MenuForm menuForm = new MenuForm();
		model.put("menuForm", menuForm);
					
		ArrayList<Cliente> clientes =  UsuarioService.findClientes(rutCliente);
		
		request.setAttribute("modulos", modulos);
		request.setAttribute("menu", menu);
						
		return "editarmenu";
	}
	
	@RequestMapping(value = "/editarmenu" , method = RequestMethod.POST)
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
		
		BarraMenuActive b = new BarraMenuActive();
		b.setModulo("Administracion");
		b.setMenu("Menu");
		b.setSubmenu("Editar Menu");
		request.setAttribute("b", b);		
		
		Usuario u = UsuarioService.findUsuario(usuario.getRut());
		
		menuForm = (MenuForm) model.get("menuForm");
		
		
		if(UsuarioService.modificarMenu(menuForm)){
			
			//exito
			mensaje = "Menu editado con exito";
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
			
			Menu menu = UsuarioService.findMenu(Integer.parseInt(menuForm.getMenId()));
			ArrayList<Modulo> modulos =  UsuarioService.findAllModulos(u);		
			
			mensaje = "Error al editar menu, consultar con el Administrador";
			estilo = "alert alert-danger";
			request.setAttribute("mensaje", mensaje);
			request.setAttribute("estilo", estilo);
			
			request.setAttribute("modulos", modulos);
			request.setAttribute("menu", menu);
			
			return "editarmenu";
		}

	
	}
	
}