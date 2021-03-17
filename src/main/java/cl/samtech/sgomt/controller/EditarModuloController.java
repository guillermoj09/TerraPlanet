package cl.samtech.sgomt.controller;

import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cl.samtech.sgomt.form.ModuloForm;
import cl.samtech.sgomt.model.Modulo;
import cl.samtech.sgomt.model.Usuario;
import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.UsuarioService;

@Controller
public class EditarModuloController {
	@RequestMapping(value = "/editarmodulo", method = RequestMethod.GET)
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
		b.setMenu("Modulo");
		b.setSubmenu("Editar");
		request.setAttribute("b", b);		
		
		Usuario u = UsuarioService.findUsuario(usuario.getRut());
		
		String id=request.getParameter("id");//la llave primaria de menu
		
		Modulo modulo = UsuarioService.findModulo(Integer.parseInt(id));
								
		ModuloForm moduloForm = new ModuloForm();
		model.put("moduloForm", moduloForm);
							
		request.setAttribute("modulo", modulo);
							
		return "editarmodulo";
	}
	
	@RequestMapping(value = "/editarmodulo" , method = RequestMethod.POST)
	public String processForm(@Valid ModuloForm moduloForm, BindingResult result,
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
		b.setMenu("Modulo");
		b.setSubmenu("Editar Modulo");
		request.setAttribute("b", b);		
		
		Usuario u = UsuarioService.findUsuario(usuario.getRut());
		
		moduloForm = (ModuloForm) model.get("moduloForm");
		
		
		if(UsuarioService.modificarModulo(moduloForm)){
			
			//exito
			mensaje = "Modulo editado con exito";
			estilo = "alert alert-success";
			request.setAttribute("mensaje", mensaje);
			request.setAttribute("estilo", estilo);
			
			ArrayList<Modulo> modulos = new ArrayList<Modulo>();
			
			modulos = UsuarioService.findAllModulos(u);		
						
			request.setAttribute("modulos", modulos);
		
			return "consultarmodulo";
			
		}else {
						
			mensaje = "Error al editar menu, consultar con el Administrador";
			estilo = "alert alert-danger";
			request.setAttribute("mensaje", mensaje);
			request.setAttribute("estilo", estilo);
			
			Modulo modulo = UsuarioService.findModulo(Integer.valueOf(moduloForm.getModId()));
											
			request.setAttribute("modulo", modulo);
			
			return "editarmodulo";
		}
	
	}
	
}