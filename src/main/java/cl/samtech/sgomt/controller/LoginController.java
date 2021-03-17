package cl.samtech.sgomt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cl.samtech.sgomt.form.LoginForm;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.UsuarioService;
import cl.samtech.sgomt.util.HttpReqRespUtils;

import org.springframework.validation.BindingResult;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class LoginController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showForm(Map model) {
		LoginForm loginForm = new LoginForm();
		model.put("loginForm", loginForm);
		
		return "home";
		
	}

	@RequestMapping(value = "/home", method = RequestMethod.POST)	
	public String processForm(@Valid LoginForm loginForm, BindingResult result,
			Map model,HttpServletRequest request) {
		
		
		String mensaje = null;
		
		String username = loginForm.getUsername().toLowerCase();
		String password = loginForm.getPassword();
		
		
		UsuarioLogin usuario=UsuarioService.findUsuarioLogin(username);
		//registra usuario session log
		
		String iduser = UsuarioService.guardarLogUsuario(request, usuario);
		
		if(iduser.equals("0")){
			
			 System.out.println("No se guardo registro");
			
		}else {
			//para ir actualizando los menu que visita en los otros controladores
			System.out.println("se guardo registro " +iduser);
			usuario.setIduser(iduser);
			
		}

		if (result.hasErrors()) {
		
			mensaje = "Error";
		
			request.setAttribute("mensaje", mensaje);
		
			return "home";
			 
		}
		
		if (usuario.getUsername()==null ||  usuario.equals(null) ) {
		
			mensaje = "Usuario no existe!";
		
			request.setAttribute("mensaje", mensaje);
		
			return "home";
			 
		}
		
		
		loginForm = (LoginForm) model.get("loginForm");
		if (!username.equals(usuario.getUsername())
				|| !password.equals(usuario.getPassword())) {
		
			mensaje = "usuario o password incorrecto!";
			request.setAttribute("mensaje", mensaje);
			return "home";
			 
		}
						 		
		request.getSession().setAttribute("usuario", usuario);
		
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
		} catch (Exception e) {
		
			mensaje = "expiro la sesion";
			request.setAttribute("mensaje", mensaje);
			return "home";
		}
		
		//eliminar sessiones idd
		UsuarioService.removeIdle();
		String ip = null; // IP del cliente
		
		ip = HttpReqRespUtils.getClientIpAddressIfServletRequestExist(request);
		System.out.println(ip);
		UsuarioService.addClienteAcceso(username,ip);		
	     return "redirect:/dashboard";
		
				
		
	}

}
