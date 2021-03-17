package cl.samtech.sgomt.controller;

import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cl.samtech.sgomt.form.ConductorForm;
import cl.samtech.sgomt.model.Cliente;
import cl.samtech.sgomt.model.Ibuttom;
import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.DispositivoService;
import cl.samtech.sgomt.service.UsuarioService;

@Controller
@RequestMapping("crearconductor")
public class CrearConductorController {
	@RequestMapping(method = RequestMethod.GET)
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
				if(!UsuarioService.validarMenuUsuario(usuario, "consultarconductor")){		
					return "home";			
				}
				//mando barra menu a la vista
				BarraMenuActive b = UsuarioService.barraMenu(usuario, "consultarconductor");
				b.setMenu("Conductor");
				b.setSubmenu("Crear Conductor");
				request.setAttribute("b", b);	
			
		ConductorForm conductorForm = new ConductorForm();
		model.put("conductorForm", conductorForm);
		
		
		ArrayList<Cliente> clientes =  UsuarioService.findClientes(rutCliente);
		
		//trae todos los ibuttons que no estan asociados a conductores
		ArrayList<Ibuttom> ibuttoms = DispositivoService.findNotIbuttomAllByConductor(rutCliente);
		
		request.setAttribute("clientes", clientes);
		request.setAttribute("ibuttoms", ibuttoms);
				
		return "crearconductor";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processForm(@Valid ConductorForm conductorForm, BindingResult result,
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
		if(!UsuarioService.validarMenuUsuario(usuario, "consultarconductor")){		
			return "home";			
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuario, "consultarconductor");
		b.setMenu("Conductor");
		b.setSubmenu("Crear Conductor");
		request.setAttribute("b", b);		

		if (result.hasErrors()) {
			return "home";
		}
		conductorForm = (ConductorForm) model.get("conductorForm");
				
		ArrayList<Cliente> clientes =  UsuarioService.findClientes(rutCliente);
		//trae todos los ibuttons que no estan asociados a conductores
		ArrayList<Ibuttom> ibuttoms = DispositivoService.findNotIbuttomAllByConductor(rutCliente);
				
		request.setAttribute("ibuttoms", ibuttoms);
			
		request.setAttribute("clientes", clientes);
		
		//if(1==1){
		if(DispositivoService.guardarConductor(conductorForm)){
			
			//exito
			mensaje = "Conductor guardado con exito";
			estilo = "alert alert-success";
			
			request.setAttribute("mensaje", mensaje);
			request.setAttribute("estilo", estilo);
			return "crearconductor";
			
		}else {
			
			//hubo un error
			mensaje = "Error al guardar Conductor, consultar con el Administrador";
			estilo = "alert alert-danger";
			request.setAttribute("mensaje", mensaje);
			request.setAttribute("estilo", estilo);
			return "crearconductor";
		}

		
		
	}
	
}