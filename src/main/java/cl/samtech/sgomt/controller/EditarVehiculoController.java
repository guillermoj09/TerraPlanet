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
import cl.samtech.sgomt.form.VehiculoForm;
import cl.samtech.sgomt.model.Tag;
import cl.samtech.sgomt.model.TipoVehiculo;
import cl.samtech.sgomt.model.Usuario;
import cl.samtech.sgomt.model.Vehiculo;
import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.object.VehiculoActive;
import cl.samtech.sgomt.service.DispositivoService;
import cl.samtech.sgomt.service.UsuarioService;

@Controller
public class EditarVehiculoController {
	
	@RequestMapping(value = "/crearvehiculo", method = RequestMethod.GET)
	public ModelAndView newForm(Map model,HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
		//	return new ModelAndView("home");
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
			usuario.setUrlservlet(request.getServletPath());
		} catch (Exception e) {			
		//	return new ModelAndView("home");			
		}
		
		//validad menu
				if(!UsuarioService.validarMenuUsuario(usuario, "consultarvehiculo.html")){		
				//	return new ModelAndView("home");			
				}
				//mando barra menu a la vista
				BarraMenuActive b = UsuarioService.barraMenu(usuario, "consultarvehiculo.html");
				b.setMenu("Equipo");
				b.setSubmenu("Crear Equipo");
				request.setAttribute("b", b);			
		
				VehiculoForm vehiculoForm = new VehiculoForm();
				model.put("vehiculoForm", vehiculoForm);
				
				UsuarioLogin usuariologin = (UsuarioLogin) request.getSession().getAttribute("usuario");
				String rutCliente = DispositivoService.FindRutClienteByUser(usuariologin.getUsername());
						
				ArrayList<TipoVehiculo> tipovehiculos = DispositivoService.FindAllTipoVehiculoByRutCliente(rutCliente);
								
				//ArrayList<Tag> tags = DispositivoService.FindAllTagByRutCliente(rutCliente);
				
				//ArrayList<Usuario> usuarios = UsuarioService.allUsuariosbyClienteRut(rutCliente); // terminar query
						
				request.setAttribute("tipovehiculos", tipovehiculos);
				//request.setAttribute("tags", tags);
				//request.setAttribute("usuarios", usuarios);
						
		return new ModelAndView("editarvehiculo");
	}
	
	
	@RequestMapping(value = "/editarvehiculo", method = RequestMethod.GET)
	public ModelAndView showForm(Map model,HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
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
			usuario.setUrlservlet(request.getServletPath());
		} catch (Exception e) {			
			return new ModelAndView("home");			
		}
		
		//validad menu
				if(!UsuarioService.validarMenuUsuario(usuario, "consultarvehiculo.html")){		
					return new ModelAndView("home");			
				}
				//mando barra menu a la vista
				BarraMenuActive b = UsuarioService.barraMenu(usuario, "consultarvehiculo.html");
				b.setMenu("Equipo");
				b.setSubmenu("Editar Equipo");
				request.setAttribute("b", b);			
		
		VehiculoForm vehiculoForm = new VehiculoForm();
		model.put("vehiculoForm", vehiculoForm);
		
		UsuarioLogin usuariologin = (UsuarioLogin) request.getSession().getAttribute("usuario");
		String rutCliente = DispositivoService.FindRutClienteByUser(usuariologin.getUsername());
		
		//buscar vehiculo
		
		String id=request.getParameter("id");//la llave primaria de vehiculo es patente
		
		Vehiculo vehiculo = DispositivoService.findVehiculo(id);
				
		ArrayList<TipoVehiculo> tipovehiculos = DispositivoService.FindAllTipoVehiculoByRutCliente(rutCliente);
						
		ArrayList<Tag> tags = DispositivoService.FindAllSiTagByPAtenteClienteRut(vehiculo.getVehPatente(), rutCliente);		
		ArrayList<Tag> tags02 = DispositivoService.FindAllNoTagByPAtenteClienteRut( vehiculo.getVehPatente(), rutCliente);
		
		ArrayList<Usuario> usuarios = UsuarioService.allSiUsuariosbyClienteRutPatente(rutCliente, vehiculo.getVehPatente()); 
		ArrayList<Usuario> usuarios02 = UsuarioService.allNoUsuariosbyClienteRutPatente(rutCliente, vehiculo.getVehPatente());
				
		request.setAttribute("tipovehiculos", tipovehiculos);
		request.setAttribute("tags", tags);
		request.setAttribute("tags02", tags02);
		request.setAttribute("usuarios", usuarios);
		request.setAttribute("usuarios02", usuarios02);		
		request.setAttribute("vehiculo", vehiculo);
						
		return new ModelAndView("editarvehiculo");
	}
	
	@RequestMapping(value = "/editarvehiculo" , method = RequestMethod.POST)
	public ModelAndView processForm(@Valid VehiculoForm vehiculoForm, BindingResult result,
			Map model,HttpServletRequest request) {
		
		String mensaje = "";
		String estilo = "";
		
		ModelAndView modelAndView = new ModelAndView("editarvehiculo");

	
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
		if(!UsuarioService.validarMenuUsuario(usuario, "consultarvehiculo.html")){		
			return new ModelAndView("home");			
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuario, "consultarvehiculo.html");
		b.setMenu("Equipo");
		b.setSubmenu("Editar Equipo");
		request.setAttribute("b", b);		
		
		vehiculoForm = (VehiculoForm) model.get("vehiculoForm");
		String origen = vehiculoForm.getOrigen();
		
		if(origen.equals("crear"))
		{
			if(DispositivoService.crearVehiculo(vehiculoForm, usuLogincliente, clavecliente, usuario.getClienterut())){				
				//exito
				mensaje = "Vehiculo creado con exito";
				estilo = "alert alert-success";				
			}else {				
				//hubo un error
				mensaje = "Error al crear Vehiculo";
				estilo = "alert alert-danger ";

			}
		}
		else
		{
			if(DispositivoService.modificarVehiculo(vehiculoForm, usuLogincliente, clavecliente, usuario.getPerfilid())){				
				//exito
				mensaje = "Vehiculo editado con exito";
				estilo = "alert alert-success";				
			}else {				
				//hubo un error
				mensaje = "Error al editar Vehiculo";
				estilo = "alert alert-danger ";

			}
		}
		
		request.setAttribute("mensaje", mensaje);
		request.setAttribute("estilo", estilo);

		
		ArrayList<VehiculoActive> vehiculos = new ArrayList<VehiculoActive>();			
		UsuarioLogin usuariologin = (UsuarioLogin) request.getSession().getAttribute("usuario");			
		vehiculos=  DispositivoService.allVehiculesByUsuario(usuariologin.getRut(), usuariologin.getClienterut());						
		request.setAttribute("vehiculos", vehiculos);
		
		return new ModelAndView("consultarvehiculo");
		
	}

		
		
	}
	
