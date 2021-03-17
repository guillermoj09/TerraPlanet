package cl.samtech.sgomt.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cl.samtech.sgomt.form.ConductorForm;
import cl.samtech.sgomt.form.CrearTurnosForm;
import cl.samtech.sgomt.form.HistoricoForm;
import cl.samtech.sgomt.model.Cliente;
import cl.samtech.sgomt.model.Ibuttom;
import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.Fechas;
import cl.samtech.sgomt.object.TurnoActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.DispositivoService;
import cl.samtech.sgomt.service.ReporteService;
import cl.samtech.sgomt.service.UsuarioService;
import cl.samtech.sgomt.service.UtilServicio;

@Controller
@RequestMapping("crearturnos")
public class CrearTurnosController {
	@RequestMapping(method = RequestMethod.GET)
	public String showForm(Map model,HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
			return "home";
		}
		
		//Relaciono el form
		CrearTurnosForm crearTurnosForm = new CrearTurnosForm();
		model.put("crearTurnosForm", crearTurnosForm);
		
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
				b.setMenu("Turnos");
				b.setSubmenu("Crear Turno");
				request.setAttribute("b", b);	
				
				
		Fechas fechas = UtilServicio.getFechasConfDiaParam("0", "dd/MM/yyyy");
				
				
		crearTurnosForm.setFechaDesde(fechas.getFechain());
		crearTurnosForm.setFechaHasta(fechas.getFechafin());
				
		DateFormat formatteri2 = new SimpleDateFormat("HH:mm");
		String horaDesde = formatteri2.format(fechas.getCalendarin().getTime());
		String horaHasta = formatteri2.format(fechas.getCalendarfin().getTime());
				
		crearTurnosForm.setHoraDesde(horaDesde);
		crearTurnosForm.setHoraHasta(horaHasta);		
					
		ArrayList<Cliente> clientes =  UsuarioService.findClientes(rutCliente);
		
		List<TurnoActive> listturno = ReporteService.findTurnoXCliente(usuLogincliente, clavecliente , rutCliente);
		
		
		request.setAttribute("clientes", clientes);
		request.setAttribute("listturno", listturno);
		request.setAttribute("rform", crearTurnosForm);
				
		return "crearturnos";
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
		b.setMenu("Turnos");
		b.setSubmenu("Crear Turno");
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
			mensaje = "Turno guardado con exito";
			estilo = "alert alert-success";
			
			request.setAttribute("mensaje", mensaje);
			request.setAttribute("estilo", estilo);
			return "crearturnos";
			
		}else {
			
			//hubo un error
			mensaje = "Error al guardar Turno, consultar con el Administrador";
			estilo = "alert alert-danger";
			request.setAttribute("mensaje", mensaje);
			request.setAttribute("estilo", estilo);
			return "crearturnos";
		}

		
		
	}
	
}