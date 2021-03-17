package cl.samtech.sgomt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cl.samtech.sgomt.form.LoginForm;
import cl.samtech.sgomt.object.StringResponse;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.UsuarioService;

import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;



@Controller

public class RestFulLoginController {
	
	  
	
	@RequestMapping(value = "/loginservice" , method = RequestMethod.GET ) // produces = "application/json"
	@ResponseBody //List<StringResponse> en caso que sea una lista como respuesta
	public StringResponse loginservice(@Valid LoginForm loginForm, BindingResult result,
			Map model,HttpServletRequest request, HttpServletResponse response) {
		
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With, Accept,Origin ");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT");
	    
		
		//Para cuando retorne una lista de json
		//List<StringResponse> stringlist = new ArrayList<StringResponse>();
		//ModelAndView modelAndView = new ModelAndView("listaruta");
		String mensaje = null;
		//Para Json simple
		StringResponse jsonmensaje = new  StringResponse(mensaje); 
				
		
		//loginForm = (LoginForm) model.get("loginForm");
		
		String username = null;
		String password = null;
		
		try {
			
			 username = loginForm.getUsername();
			 password = loginForm.getPassword();
			 
			 if (username == null ||password == null ){
				 
					mensaje = "1";
					jsonmensaje.setResponse(mensaje);
			  		
					 return jsonmensaje;
				 
			 }
			
		} catch (Exception e) {
			
			mensaje = "1";
			jsonmensaje.setResponse(mensaje);
	  			 
			return jsonmensaje;
			// TODO: handle exception
		}
		
		//String username = loginForm.getUsername();
		//String password = loginForm.getPassword();
		
		
		UsuarioLogin usuario=UsuarioService.findUsuarioLogin(username);
		
		if (result.hasErrors()) {
			//return "home";
			//mensaje = "Error";
			mensaje = "1";
			jsonmensaje.setResponse(mensaje);
			//modelAndView.addObject("mensaje", mensaje);
			//modelAndView.setViewName("home");
			
	  		
			 return jsonmensaje;
			
			//return stringlist; 
		}
		
		if (usuario.getUsername()==null ||  usuario.equals(null) ) {
			//return "home";
			//mensaje = "Usuario no existe!";
			mensaje = "2";
			jsonmensaje.setResponse(mensaje);
			//modelAndView.addObject("mensaje", mensaje);
			//modelAndView.setViewName("home");
			 //stringlist.add(jsonmensaje);
			  		
			 return jsonmensaje;
			//return jsonmensaje; 
		}
		
		loginForm = (LoginForm) model.get("loginForm");
		if (!loginForm.getUsername().equals(usuario.getUsername())
				|| !loginForm.getPassword().equals(usuario.getPassword())) {
			//return "home";
			//mensaje = "usuario o password incorrecto!";
			mensaje = "3";
			jsonmensaje.setResponse(mensaje);
			//modelAndView.addObject("mensaje", mensaje);
			//modelAndView.setViewName("home");
			return jsonmensaje; 
		}
		
		mensaje = "4"; //Exito
		jsonmensaje.setResponse(mensaje);
		
		 return jsonmensaje;
		//return modelAndView;
	}

	@RequestMapping(value = "/loginserviceNo" , method = RequestMethod.POST)// el normal
	public ModelAndView processForm(@Valid LoginForm loginForm, BindingResult result,
			Map model,HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView("monitoreo");
		
		String mensaje = null;
		
		
		String username = loginForm.getUsername();
		String password = loginForm.getPassword();
		
		UsuarioLogin usuario=UsuarioService.findUsuarioLogin(username);

		if (result.hasErrors()) {
			//return "home";
			mensaje = "Error";
			modelAndView.addObject("mensaje", mensaje);
			modelAndView.setViewName("home");
			return modelAndView; 
		}
		
		if (usuario.getUsername()==null ||  usuario.equals(null) ) {
			//return "home";
			mensaje = "Usuario no existe!";
			modelAndView.addObject("mensaje", mensaje);
			modelAndView.setViewName("home");
			return modelAndView; 
		}
		
		/*loginForm = (LoginForm) model.get("loginForm");
		if (!loginForm.getUserName().equals(username)
				|| !loginForm.getPassword().equals(password)) {
			return "home";
		}*/
		
		loginForm = (LoginForm) model.get("loginForm");
		if (!loginForm.getUsername().equals(usuario.getUsername())
				|| !loginForm.getPassword().equals(usuario.getPassword())) {
			//return "home";
			mensaje = "usuario o password incorrecto!";
			modelAndView.addObject("mensaje", mensaje);
			modelAndView.setViewName("home");
			return modelAndView; 
		}
		
		//Test
		 //modelAndView.addObject("message", mensaje);
		 //return modelAndView;
		 			
		request.getSession().setAttribute("usuario", usuario);			
		modelAndView.setViewName("redirect:/monitoreo");
		//request.getSession().setAttribute("message", message);	
		return modelAndView; 
		//return "redirect:/monitoreo";
	}

}
