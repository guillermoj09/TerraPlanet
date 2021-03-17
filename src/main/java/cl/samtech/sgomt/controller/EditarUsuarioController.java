package cl.samtech.sgomt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import cl.samtech.sgomt.form.CambiarClaveForm;
import cl.samtech.sgomt.form.CrearUsuarioForm;
import cl.samtech.sgomt.model.Cliente;
import cl.samtech.sgomt.model.Menu;
import cl.samtech.sgomt.model.Perfil;
import cl.samtech.sgomt.model.Usuario;
import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.MenuActive3;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.object.VehiculoActive;
import cl.samtech.sgomt.service.DispositivoService;
import cl.samtech.sgomt.service.UsuarioService;

@Controller
public class EditarUsuarioController {
	@RequestMapping(value = "/editarusuario", method = RequestMethod.GET)
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
				if(!UsuarioService.validarMenuUsuario(usuario, "consultarusuarios.html")){		
					return "home";			
				}
				//mando barra menu a la vista
				BarraMenuActive b = UsuarioService.barraMenu(usuario, "consultarusuarios.html");
				b.setMenu("Usuarios");
				b.setSubmenu("Crear Usuario");
				request.setAttribute("b", b);		
		
		CrearUsuarioForm crearUsuarioForm = new CrearUsuarioForm();
		model.put("crearUsuarioForm", crearUsuarioForm);
		
		String id=request.getParameter("id").toLowerCase();
		
		UsuarioLogin userlogin=UsuarioService.findUsuarioLogin(id);
		ArrayList<Cliente> clientes = UsuarioService.findClientes(clienteRut);
		ArrayList<Perfil> perfiles = UsuarioService.findPerfilByCliente(clienteRut);
		ArrayList<Perfil> perfiles02 = UsuarioService.findPerfilByClienteAdminCliente(clienteRut);
		
		Usuario u = UsuarioService.findUsuario(userlogin.getRut());
		
		ArrayList<Menu> menus = UsuarioService.findMenuByUsuario(u, usuario.getPerfilid());
		
		ArrayList<Menu> menus02 = UsuarioService.findNoMenuByUsuario(u, usuario.getPerfilid());
		
		List<MenuActive3> menus03 = UsuarioService.findMenuByUsuario3(u, usuario.getPerfilid());
		
		//VEHICULOS
		ArrayList<VehiculoActive> vehiculos = new ArrayList<VehiculoActive>();
		ArrayList<VehiculoActive> vehiculos02 = new ArrayList<VehiculoActive>();
		
		vehiculos=  DispositivoService.allVehiculesByUsuario(userlogin.getRut(), userlogin.getClienterut());
		vehiculos02 =  DispositivoService.allNotVehiculesByUsuario(userlogin.getRut(), userlogin.getClienterut());
		
		
		request.setAttribute("userlogin", userlogin);
		request.setAttribute("clientes", clientes);
		request.setAttribute("perfiles", perfiles);
		request.setAttribute("perfiles02", perfiles02);
		request.setAttribute("menus", menus);
		request.setAttribute("menus02", menus02);
		request.setAttribute("menus03", menus03);
		request.setAttribute("vehiculos", vehiculos);
		request.setAttribute("vehiculos02", vehiculos02);
		
		return "editarusuario";
	}
	
	@RequestMapping(value = "/editarusuario" , method = RequestMethod.POST)
	public ModelAndView processForm(@Valid CrearUsuarioForm crearusuarioForm, BindingResult result,
			Map model,HttpServletRequest request) {
		
		String mensaje = null;
		String mensaje02 = null;
		
		ModelAndView modelAndView = new ModelAndView("usuariomensaje");

		if (result.hasErrors()) {
			return new ModelAndView("home");
		}
		
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
		} catch (Exception e) {			
			return new ModelAndView("home");			
		}
		//validad menu
		//validad menu
		if(!UsuarioService.validarMenuUsuario(usuario, "consultarusuarios.html")){		
			return new ModelAndView("home");	
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuario, "consultarusuarios.html");
		b.setMenu("Usuarios");
		b.setSubmenu("Crear Usuario");
		request.setAttribute("b", b);	
				
		crearusuarioForm = (CrearUsuarioForm) model.get("crearUsuarioForm");

		
		String username=crearusuarioForm.getUsuLogin();

						
			if(UsuarioService.modificarUsuario( crearusuarioForm , usuario.getPerfilid())){
				
				mensaje = "Usuario Modificado Exitosamente!";
				mensaje02 = "Por favor ingresar con su nuevo Usuario al sistema.";
				modelAndView.addObject("mensaje", mensaje);
				modelAndView.addObject("mensaje02", mensaje02);
				modelAndView.setViewName("usuariomensaje");
				return modelAndView;				
			}
	
			mensaje = "Usuario No ha sido Grabado!";
			mensaje02 = "Asegurese que el Usuario no exista con ese rut, Si continua el error comuniquese con el Administrador del Sistema.";
			modelAndView.addObject("mensaje", mensaje);
			modelAndView.addObject("mensaje02", mensaje02);			
		return modelAndView;
		
	}
	
	
	@RequestMapping(value = "/cambiarclave", method = RequestMethod.GET)
	public ModelAndView showForm02(Map model,HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
			return new ModelAndView("home");
		}
		UsuarioLogin usuario=(UsuarioLogin)request.getSession().getAttribute("usuario");
		usuario.setUrlservlet(request.getServletPath());
		String usuLogin = "";		
		try {
			usuLogin  = usuario.getUsername();						
		} catch (Exception e) {			
			return new ModelAndView("home");			
		}
		//validad menu
		if(!UsuarioService.validarMenuUsuario(usuario, request.getServletPath())){		
			return new ModelAndView("home");		
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuario, request.getServletPath());
		
		CambiarClaveForm cambiarClaveForm = new CambiarClaveForm();
		model.put("cambiarClaveForm", cambiarClaveForm);
		
		UsuarioLogin userlogin = new UsuarioLogin();
		
		//es user
		String id=request.getParameter("id");
		
		if(!(id == null)){
		
		userlogin=UsuarioService.findUsuarioLogin(id);
		
		}else{
			
		userlogin=UsuarioService.findUsuarioLogin(usuLogin);
			
		}
		request.setAttribute("b", b);		
		request.setAttribute("userlogin", userlogin);
					
		return new ModelAndView("cambiarclave");
	}
		
	@RequestMapping(value = "/cambiarclave" , method = RequestMethod.POST)
	public ModelAndView processForm02(@Valid CambiarClaveForm cambiarClaveForm, BindingResult result,
			Map model,HttpServletRequest request) {
		
		UsuarioLogin usuarios=(UsuarioLogin)request.getSession().getAttribute("usuario");	

		ModelAndView modelAndView = new ModelAndView("cambiarclave");

		cambiarClaveForm = (CambiarClaveForm) model.get("cambiarClaveForm");
		
		String username = cambiarClaveForm.getUsername();
		String passwordviejo = cambiarClaveForm.getPasswordviejo();
		String passwordnuevo = cambiarClaveForm.getPasswordnuevo();
		String passwordnuevo2 = cambiarClaveForm.getPasswordnuevo2();
		
		UsuarioLogin usuario=UsuarioService.findUsuarioLogin(username);
		
		if(!UsuarioService.validarMenuUsuario(usuarios, request.getServletPath())){		
			return new ModelAndView("home");	
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuarios, request.getServletPath());
		request.setAttribute("b", b);	
				
		request.setAttribute("userlogin", usuario);
		
		String mensaje = "";
		if (result.hasErrors()) {
			return new ModelAndView("cambiarclave");
		}
		
		if (!cambiarClaveForm.getUsername().equals(usuario.getUsername())
				|| !cambiarClaveForm.getPasswordviejo().equals(usuario.getPassword())) {
			//return "home";
			mensaje = "password incorrecto, coloque su password actual !";
			modelAndView.addObject("mensaje", mensaje);
			modelAndView.setViewName("cambiarclave");
			return modelAndView; 
		}
		
		if (!passwordnuevo.equals(passwordnuevo2) ){
			
			mensaje = "password no es igual, por favor volver verificar ";
			modelAndView.addObject("mensaje", mensaje);
			modelAndView.setViewName("cambiarclave");
			return modelAndView;
			
		}
		// id es usuario.getRut(); //username
		if(UsuarioService.cambiarClave(usuario.getRut(), passwordnuevo2)){
			
			mensaje = "Exito, password ha sido cambiado! ";
			modelAndView.addObject("mensaje", mensaje);
			modelAndView.setViewName("cambiarclave");
			return modelAndView;
						
		}
				
		mensaje = "Error ! ";
		modelAndView.addObject("mensaje", mensaje);
	
					
		return new ModelAndView("usuarioexiste");
		
	}
	
}