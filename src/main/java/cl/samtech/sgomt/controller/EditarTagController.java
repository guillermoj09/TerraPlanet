package cl.samtech.sgomt.controller;

import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cl.samtech.sgomt.form.TagForm;
import cl.samtech.sgomt.model.Cliente;
import cl.samtech.sgomt.model.Tag;
import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.DispositivoService;
import cl.samtech.sgomt.service.UsuarioService;

@Controller
public class EditarTagController {
	@RequestMapping(value = "/editartag", method = RequestMethod.GET)
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
		if(!UsuarioService.validarMenuUsuario(usuario, "consultartag.html")){		
			return "home";			
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuario, "consultartag.html");
		b.setMenu("Editar Tags");
		request.setAttribute("b", b);			
		
		String id=request.getParameter("id");//la llave primaria de vehiculo es patente
		
		Tag tag = DispositivoService.findTag(id);
				
				
		TagForm tagForm = new TagForm();
		model.put("tagForm", tagForm);
					
		ArrayList<Cliente> clientes =  UsuarioService.findClientes(rutCliente);
		
		request.setAttribute("clientes", clientes);
		request.setAttribute("tag", tag);
						
		return "editartag";
	}
	
	@RequestMapping(value = "/editartag" , method = RequestMethod.POST)
	public String processForm(@Valid TagForm tagForm, BindingResult result,
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
				if(!UsuarioService.validarMenuUsuario(usuario, "consultartag.html")){		
					return "home";			
				}
				//mando barra menu a la vista
				BarraMenuActive b = UsuarioService.barraMenu(usuario, "consultartag.html");
				b.setMenu("Editar Tags");
				request.setAttribute("b", b);		
		
		tagForm = (TagForm) model.get("tagForm");
		
		
		if(DispositivoService.editarTag(tagForm)){
			
			//exito
			mensaje = "Tag editado con exito";
			estilo = "alert alert-success";
			request.setAttribute("mensaje", mensaje);
			request.setAttribute("estilo", estilo);
			
			ArrayList<Tag> tags = new ArrayList<Tag>();			
						
			tags=  DispositivoService.FindAllTagByRutCliente(rutCliente);						
			request.setAttribute("tags", tags);
			
			return "consultartag";
			
		}else {
			
			//hubo un error
			mensaje = "Error al editar tag, consultar con el Administrador";
			estilo = "alert alert-danger";
			request.setAttribute("mensaje", mensaje);
			request.setAttribute("estilo", estilo);
			request.setAttribute("tagForm", tagForm);
			
			return"editartag";
		}

	
	}
	
}