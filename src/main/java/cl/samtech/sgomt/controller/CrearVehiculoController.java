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
import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.DispositivoService;
import cl.samtech.sgomt.service.UsuarioService;

@Controller
@RequestMapping("crearvehiculo2.html")
public class CrearVehiculoController {
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showForm(Map model,HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
			return new ModelAndView("home");
		}
		UsuarioLogin usuario=(UsuarioLogin)request.getSession().getAttribute("usuario");
		//validad menu
				if(!UsuarioService.validarMenuUsuario(usuario,"consultarvehiculo")){		
					return new ModelAndView("home");			
				}
				//mando barra menu a la vista
				BarraMenuActive b = UsuarioService.barraMenu(usuario, "consultarvehiculo");
				b.setMenu("Equipo");
				b.setSubmenu("Crear Equipo");
				request.setAttribute("b", b);			
			
		VehiculoForm vehiculoForm = new VehiculoForm();
		model.put("vehiculoForm", vehiculoForm);
		
		UsuarioLogin usuariologin = (UsuarioLogin) request.getSession().getAttribute("usuario");
		String rutCliente = DispositivoService.FindRutClienteByUser(usuariologin.getUsername());
				
		ArrayList<TipoVehiculo> tipovehiculos = DispositivoService.FindAllTipoVehiculoByRutCliente(rutCliente);
						
		ArrayList<Tag> tags = DispositivoService.FindAllTagByRutCliente(rutCliente);
		
		ArrayList<Usuario> usuarios = UsuarioService.allUsuariosbyClienteRut(rutCliente); // terminar query
				
		request.setAttribute("tipovehiculos", tipovehiculos);
		request.setAttribute("tags", tags);
		request.setAttribute("usuarios", usuarios);
				
		return new ModelAndView("crearvehiculo");
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView processForm(@Valid VehiculoForm vehiculoForm, BindingResult result,
			Map model,HttpServletRequest request) {

		//alert alert-danger rojo
		//alert alert-success verde
		//alert-info azul
		//alert-warning amarillo
		//class = "alert alert-light alert-dismissible fade show"
		String mensaje = "";
		String estilo = "";

		if (result.hasErrors()) {
			return new ModelAndView("home");
		}
		
		UsuarioLogin usuario=(UsuarioLogin)request.getSession().getAttribute("usuario");
		//validad menu
		if(!UsuarioService.validarMenuUsuario(usuario,"consultarvehiculo")){		
			return new ModelAndView("home");			
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuario, "consultarvehiculo");
		b.setMenu("Equipo");
		b.setSubmenu("Crear Equipo");
		request.setAttribute("b", b);		
		
		vehiculoForm = (VehiculoForm) model.get("vehiculoForm");
		
		if(DispositivoService.guardarVehiculo(vehiculoForm)){
			
			//exito
			mensaje = "Vehiculo guardado con exito";
			estilo = "alert alert-success alert-dismissible fade show";
			request.setAttribute("mensaje", mensaje);
			request.setAttribute("estilo", estilo);
			return new ModelAndView("consultarvehiculo");
			
		}else {
			
			//hubo un error
			mensaje = "Error al guardar Vehiculo, consultar con el Administrador";
			estilo = "alert alert-danger alert-dismissible fade show";
			request.setAttribute("mensaje", mensaje);
			request.setAttribute("estilo", estilo);
			return new ModelAndView("crearvehiculo");
		}

		
		
	}
	
}