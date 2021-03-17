package cl.samtech.sgomt.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cl.samtech.sgomt.form.ComandoGpsForm;
import cl.samtech.sgomt.form.HistoricoForm;
import cl.samtech.sgomt.model.Vehiculo;
import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.EventActive;
import cl.samtech.sgomt.object.Fechas;
import cl.samtech.sgomt.object.HistoricoActive;
import cl.samtech.sgomt.object.MapaCalorActive;
import cl.samtech.sgomt.object.PatenteGPSActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.DispositivoService;
import cl.samtech.sgomt.service.GestionService;
import cl.samtech.sgomt.service.ReporteService;
import cl.samtech.sgomt.service.UsuarioService;
import cl.samtech.sgomt.service.UtilServicio;;

@Controller
public class ComandoGpsController {
	@RequestMapping(value = "/comandogps", method = RequestMethod.GET)
	public String showForm(Map model,HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
			 return "home";
		}
		
		//Relaciono el form
		ComandoGpsForm comandoGpsForm = new ComandoGpsForm();
		model.put("comandoGpsForm", comandoGpsForm);
				
		UsuarioLogin usuario=(UsuarioLogin)request.getSession().getAttribute("usuario");				
		String cliente = "";
		String clave = "";
		String usuLogincliente = "";
		String clavecliente = "";
		try {
			cliente  = usuario.getUsername();
			clave  = usuario.getPassword();
			usuLogincliente  = usuario.getCliUsuSamtech();
			clavecliente  = usuario.getCliPassSamtech();
			
		} catch (Exception e) {
			
			return "home";
			
		}		
		//validad menu
		if(!UsuarioService.validarMenuUsuario(usuario, request.getServletPath())){		
			return "home";			
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuario, request.getServletPath());		
								
		Date fecha= new Date();
						
		List<PatenteGPSActive> listPatentes = ReporteService.findPatentesXClienteFecha(cliente, clave, fecha, usuario.getClienterut());
						
		request.setAttribute("b", b);		
		request.setAttribute("listPatentes", listPatentes);		
		request.setAttribute("rform", comandoGpsForm);
		
		return "comandogps";
	}
	
			
	//procesar 
		@RequestMapping(value = "/comandogps" , method = RequestMethod.POST)
		public String processForm(@Valid ComandoGpsForm comandoGpsForm, BindingResult result,Map model,HttpServletRequest request) {
			
			if (result.hasErrors()) {
				return "home";
			}
			
			//me traigo los valores del formulario
			comandoGpsForm = (ComandoGpsForm) model.get("comandoGpsForm");
						
			UsuarioLogin usuario=(UsuarioLogin)request.getSession().getAttribute("usuario");
			String cliente = "";
			String clave = "";
			String usuLogincliente = "";
			String clavecliente = "";
			try {
				cliente  = usuario.getUsername();
				clave  = usuario.getPassword();
				usuLogincliente  = usuario.getCliUsuSamtech();
				clavecliente  = usuario.getCliPassSamtech();
				usuario.setUrlservlet(request.getServletPath());
			} catch (Exception e) {				
				return "home";				
			};
			//validad menu
			if(!UsuarioService.validarMenuUsuario(usuario, request.getServletPath())){		
				return "home";			
			}
			//mando barra menu a la vista
			BarraMenuActive b = UsuarioService.barraMenu(usuario, request.getServletPath());		
			
			
						
			return "historico";
			
		}

}