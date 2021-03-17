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

import cl.samtech.sgomt.form.BasicForm;
import cl.samtech.sgomt.model.Cliente;
import cl.samtech.sgomt.model.Ibuttom;
import cl.samtech.sgomt.model.Usuario;
import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.IbuttomActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.DispositivoService;
import cl.samtech.sgomt.service.ReporteService;
import cl.samtech.sgomt.service.UsuarioService;

@Controller
public class ConsultaIbuttomController {
	@RequestMapping("/consultaibutton")
	public String monitoreo(HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
			return "home";
		}
		UsuarioLogin usuariologin = (UsuarioLogin) request.getSession().getAttribute("usuario");		
		//validad menu
		if(!UsuarioService.validarMenuUsuario(usuariologin, request.getServletPath())){		
			return "home";			
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuariologin, request.getServletPath());
		request.setAttribute("b", b);		
		
		Usuario usuario = UsuarioService.findUsuario(usuariologin.getRut());
		
		List<IbuttomActive> ibutton=  ReporteService.findIbuttomXCliente(usuario.getCliente().getCliRut());
					
		request.setAttribute("ibutton", ibutton);
		
		return "consultaibutton";
	}
	
	
	@RequestMapping("/creaributton")
	public String creacionibutton(HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
			return "home";
		}
		UsuarioLogin usuariologin = (UsuarioLogin) request.getSession().getAttribute("usuario");		
		//validad menu
		if(!UsuarioService.validarMenuUsuario(usuariologin, "consultaibutton")){		
			// return "home";			
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuariologin, "consultaibutton");
		b.setMenu("iButton");
		b.setSubmenu("Crear iButton");
		request.setAttribute("b", b);			
		
		Usuario usuario = UsuarioService.findUsuario(usuariologin.getRut());
		
		List<IbuttomActive> ibutton=  ReporteService.findIbuttomXCliente(usuario.getCliente().getCliRut());
					
		request.setAttribute("ibutton", ibutton);
		
		return "creaributton";
	}
	
	@RequestMapping(value = "/creaributton" ,method = RequestMethod.POST)
	public String processForm(@Valid BasicForm basicForm, BindingResult result,
			Map model,HttpServletRequest request) {

		//alert alert-danger rojo
		//alert alert-success verde
		//alert-info azul
		//alert-warning amarillo
		//class = "alert alert-light alert-dismissible fade show"
		String mensaje = "";
		String estilo = "";
		
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
		if(!UsuarioService.validarMenuUsuario(usuario, "consultaibutton")){		
		//	return "home";			
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuario, "consultaibutton");
		b.setMenu("iButton");
		b.setSubmenu("Crear iButton");
		request.setAttribute("b", b);		

		if (result.hasErrors()) {
			return "home";
		}
		basicForm = (BasicForm) model.get("basicForm");

		
		//if(DispositivoService.guardarConductor(conductorForm)){		
		if(DispositivoService.guardarIbuttom(basicForm,rutCliente)){	
			//exito
			mensaje = "iButton guardado con exito";
			estilo = "alert alert-success";			
			request.setAttribute("mensaje", mensaje);
			request.setAttribute("estilo", estilo);
			return "creaributton";			
		}else {			
			//hubo un error
			mensaje = "iButton ya existente";
			estilo = "alert alert-danger";
			request.setAttribute("mensaje", mensaje);
			request.setAttribute("estilo", estilo);
			return "creaributton";
		}
		 
		
	}
	
	@RequestMapping(value = "/eliminaibutton")
		public String eliminaibutton(HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
			return "redirect:/home.html";
		}
		UsuarioLogin usuariologin = (UsuarioLogin) request.getSession().getAttribute("usuario");		
		//validad menu
		if(!UsuarioService.validarMenuUsuario(usuariologin, "consultaibutton")){		
			// return "home";			
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuariologin, "consultaibutton");
		request.setAttribute("b", b);			
		
		Usuario usuario = UsuarioService.findUsuario(usuariologin.getRut());
		String id_ibu=request.getParameter("id_ibu");
		
		if(DispositivoService.borrarIbuttom(id_ibu,usuario.getCliente().getCliRut())){
			 return "redirect:/consultaibutton.html";
		}else{
			return "redirect:/consultaibutton.html";
		}
							
		
	}
	

}
