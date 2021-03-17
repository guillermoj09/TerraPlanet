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
import cl.samtech.sgomt.model.Cliente;
import cl.samtech.sgomt.model.Perfil;
import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.RolusuarioActivo;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.UsuarioService;

@Controller
@RequestMapping("crearusuario.html")
public class CrearUsuarioController {
	@RequestMapping(method = RequestMethod.GET)
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
		b.setMenu("Usuario");
		b.setSubmenu("Crear Usuario");
		request.setAttribute("b", b);		
			
		CrearUsuarioForm crearUsuarioForm = new CrearUsuarioForm();
		model.put("crearUsuarioForm", crearUsuarioForm);
				
		ArrayList<Cliente> clientes = UsuarioService.findClientes(clienteRut);
		ArrayList<Perfil> perfiles = UsuarioService.findPerfilByCliente(clienteRut);
		ArrayList<Perfil> perfiles02 = UsuarioService.findPerfilByClienteAdminCliente(clienteRut);
		
					
		request.setAttribute("clientes", clientes);
		request.setAttribute("perfiles", perfiles);
		request.setAttribute("perfiles02", perfiles02);
		request.setAttribute("userlogin", usuario);
				
		return "crearusuario";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView processForm(@Valid CrearUsuarioForm crearusuarioForm, BindingResult result,
			Map model,HttpServletRequest request) {
		
		String mensaje = null;
		String mensaje02 = null;

		ModelAndView modelAndView = new ModelAndView("usuariomensaje");

		if (result.hasErrors()) {
			return new ModelAndView("home");
		}
		crearusuarioForm = (CrearUsuarioForm) model.get("crearUsuarioForm");
		
		String username=crearusuarioForm.getUsuLogin().toLowerCase();
		String rut = crearusuarioForm.getUsuRut().toLowerCase();
		
		if(UsuarioService.existeUsuario(username)){
			
			mensaje = "Usuario No ha sido Grabado!";
			mensaje02 = "Asegurese que el Usuario no exista, Si continua el error comuniquese con el Administrador del Sistema.";
			modelAndView.addObject("mensaje", mensaje);
			modelAndView.addObject("mensaje02", mensaje02);			
			return modelAndView;
			
		}else if(UsuarioService.existeRutUsuario(rut)){
			
			
			mensaje = "Usuario No ha sido Grabado!";
			mensaje02 = "Asegurese que la Rut no exista, Si continua el error comuniquese con el Administrador del Sistema.";
			modelAndView.addObject("mensaje", mensaje);
			modelAndView.addObject("mensaje02", mensaje02);			
			return modelAndView;
			
			
			
		} else {
			
			/*if(UsuarioService.existeRutUsuario(rut)){
				
				mensaje = "Usuario No ha sido Grabado!";
				mensaje02 = "Asegurese que la Rut no exista, Si continua el error comuniquese con el Administrador del Sistema.";
				modelAndView.addObject("mensaje", mensaje);
				modelAndView.addObject("mensaje02", mensaje02);			
				return modelAndView;
				
				}*/
			
			
			if(UsuarioService.guardarUsuario( crearusuarioForm )){
				
				mensaje = "Usuario Guardado Exitosamente!";
				mensaje02 = "Ha sido enviado un correo al usuario con las instrucciones de uso (solo para usuarios creados por primera vez).!";
				modelAndView.addObject("mensaje", mensaje);
				modelAndView.addObject("mensaje02", mensaje02);
				modelAndView.setViewName("usuariomensaje");
				return modelAndView;
			}
	
		}
					
		mensaje = "Usuario No ha sido Grabado!";
		mensaje02 = "Sucedio algun error inesperado, vuelva intentar, Si continua el error comuniquese con el Administrador del Sistema.";
		modelAndView.addObject("mensaje", mensaje);
		modelAndView.addObject("mensaje02", mensaje02);			
		//return new ModelAndView("usuariomensaje");
		return modelAndView;
		
	}
	
}