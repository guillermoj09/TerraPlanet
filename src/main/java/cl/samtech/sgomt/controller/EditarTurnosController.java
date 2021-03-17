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

import cl.samtech.sgomt.form.ConductorForm;
import cl.samtech.sgomt.form.CrearUsuarioForm;
import cl.samtech.sgomt.form.VehiculoForm;
import cl.samtech.sgomt.model.Cliente;
import cl.samtech.sgomt.model.Conductor;
import cl.samtech.sgomt.model.Ibuttom;
import cl.samtech.sgomt.model.Perfil;
import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.ConductorActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.object.VehiculoActive;
import cl.samtech.sgomt.service.DispositivoService;
import cl.samtech.sgomt.service.ReporteService;
import cl.samtech.sgomt.service.UsuarioService;

@Controller
public class EditarTurnosController {
	@RequestMapping(value = "/editarturnos", method = RequestMethod.GET)
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
				if(!UsuarioService.validarMenuUsuario(usuario, "consultarconductor")){		
					return "home";				
				}
				//mando barra menu a la vista
				BarraMenuActive b = UsuarioService.barraMenu(usuario, "consultarconductor");
				b.setMenu("Operadores");
				b.setSubmenu("Editar Operador");	
				request.setAttribute("b", b);	
		
		ConductorForm conductorForm = new ConductorForm();
		model.put("conductorForm", conductorForm);
		
		String id=request.getParameter("id");
		
		//UsuarioLogin userlogin=UsuarioService.findUsuarioLogin(id);
		ArrayList<Cliente> clientes = UsuarioService.findClientes(clienteRut);
		ArrayList<Perfil> perfiles = UsuarioService.findPerfilByCliente(clienteRut);
		
		Conductor c = DispositivoService.findConductor(id);		
		
		ArrayList<Ibuttom> ibuttoms = DispositivoService.findIbuttomByConductor(c);
		ArrayList<Ibuttom> ibuttoms02 = DispositivoService.findNotIbuttomAllByConductor(clienteRut);
		
		//ArrayList<Ibuttom> ibuttoms02 = DispositivoService.findNotIbuttomByConductor(c);
				
		request.setAttribute("clientes", clientes);
		request.setAttribute("c", c);
		request.setAttribute("ibuttoms", ibuttoms);
		request.setAttribute("ibuttoms02", ibuttoms02);
		
		return "editarturnos";
	}
	
	@RequestMapping(value = "/editarturnos" , method = RequestMethod.POST)
	public String processForm(@Valid ConductorForm conductorForm, BindingResult result,
			Map model,HttpServletRequest request) {
		
		String mensaje = "";
		String estilo = "";
		
		ModelAndView modelAndView = new ModelAndView("editarconductor");

		if (result.hasErrors()) {
			return "home";
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
			return "home";			
		}
		//validad menu
		if(!UsuarioService.validarMenuUsuario(usuario, "consultarconductor")){		
			return "home";				
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuario, "consultarconductor");
		b.setMenu("Operadores");
		b.setSubmenu("Editar Operador");
		
		request.setAttribute("b", b);		
		
		conductorForm = (ConductorForm) model.get("conductorForm");
		
		if(DispositivoService.modificarConductor(conductorForm, usuLogincliente, clavecliente, usuario.getPerfilid())){
			
			//exito
			mensaje = "Conductor editado con exito";
			estilo = "alert alert-success";
			request.setAttribute("mensaje", mensaje);
			request.setAttribute("estilo", estilo);
			
						
			UsuarioLogin usuariologin = (UsuarioLogin) request.getSession().getAttribute("usuario");	
			
			List<ConductorActive> conductores=  ReporteService.findConductoresXCliente(usuario.getClienterut());
			
			request.setAttribute("conductores", conductores);			
			return "consultarconductor";
			
		}else {
			
			//hubo un error
			mensaje = "Error al editar Conductor";
			estilo = "alert alert-danger ";
			request.setAttribute("mensaje", mensaje);
			request.setAttribute("estilo", estilo);
			
			
			ArrayList<VehiculoActive> vehiculos = new ArrayList<VehiculoActive>();			
			UsuarioLogin usuariologin = (UsuarioLogin) request.getSession().getAttribute("usuario");			
			
			List<ConductorActive> conductores=  ReporteService.findConductoresXCliente(usuario.getClienterut());
			
			request.setAttribute("conductores", conductores);
			return "editarturnos";
		}
		
	}

}