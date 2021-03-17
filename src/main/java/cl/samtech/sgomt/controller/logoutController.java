package cl.samtech.sgomt.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cl.samtech.sgomt.form.LoginForm;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.UsuarioService;

import org.springframework.validation.BindingResult;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("logout.html")
public class logoutController {
	

	@RequestMapping(method = RequestMethod.GET)
	public String processForm(@Valid LoginForm loginForm, BindingResult result,
			Map model,HttpServletRequest request) {
		
		UsuarioLogin usuario=(UsuarioLogin)request.getSession().getAttribute("usuario");
		
			  //actualizar usuario log para salida
		try {
			
			boolean grabado = UsuarioService.actualizarLogUsuario(usuario.getIduser());
			  if(grabado ){
				  System.out.println("Se guardo la edicion usuario session");
				  
			  }
			  
			
		} catch (Exception e) {
			
			 System.out.println("expiro");

		}
	
		       request.getSession().invalidate();
	
		return "redirect:/";
	}

}
