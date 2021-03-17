package cl.samtech.sgomt.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import cl.samtech.sgomt.form.ReporteHorometroForm;
import cl.samtech.sgomt.object.FaenaActive;
import cl.samtech.sgomt.object.Fechas;
import cl.samtech.sgomt.object.ProduccionTurnoActive;
import cl.samtech.sgomt.object.ReporteHorometroActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.ReporteService;
import cl.samtech.sgomt.service.UtilServicio;

@Controller
public class ProduccionTurnoController {
	
	@RequestMapping(value = "/produccionturno", method = RequestMethod.GET)
	public String showForm(Map model,HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
			 return "home";
		}
		
		//Relaciono el form
		BasicForm produccionTurnoForm = new BasicForm();
		model.put("produccionTurnoForm", produccionTurnoForm);
				
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
		
		Fechas fechas = UtilServicio.getFechasConfDiaParam("0", "dd/MM/yyyy");
				
		produccionTurnoForm.setFechaDesde(fechas.getFechain());

		request.setAttribute("rform", produccionTurnoForm);
		
		return "produccionturno";
	}
	
	
	//procesar 
	@RequestMapping(value = "/produccionturno" , method = RequestMethod.POST)
	public ModelAndView processForm(@Valid BasicForm reportForm, BindingResult result,
			Map model,HttpServletRequest request) {


		if (result.hasErrors()) {
			return new ModelAndView("home");
		}
		
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
			return new ModelAndView("home");			
		}
		
	
		reportForm.setCollapseshow("off");
		
		String desde = reportForm.getFechaDesde();

		List<ProduccionTurnoActive> rlist = ReporteService.findProduccionTurno(usuLogincliente,clavecliente,desde+" 00:00:00-4", desde+" 23:59:00-04");
		
		
		
		request.setAttribute("rlist", rlist);								
		request.setAttribute("rform", reportForm);		
	
					
		return new ModelAndView("produccionturno");
		
	}

}
