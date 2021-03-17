package cl.samtech.sgomt.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cl.samtech.sgomt.form.ReporteHorometroForm;
import cl.samtech.sgomt.form.ReportePatenteFechaForm;
import cl.samtech.sgomt.object.FaenaActive;
import cl.samtech.sgomt.object.PatenteGPSActive;
import cl.samtech.sgomt.object.ReporteHorometroActive;
import cl.samtech.sgomt.object.ReporteTiempoTransporteActive;
import cl.samtech.sgomt.object.TurnoActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.ReporteService;


@Controller
public class ReporteHorometroController {
	@RequestMapping(value = "/reportehorometro", method = RequestMethod.GET)
	public String showForm(Map model,HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
			 return "home";
		}
		
		//Relaciono el form
		ReporteHorometroForm reporteHorometroForm = new ReporteHorometroForm();
		model.put("reporteHorometroForm", reporteHorometroForm);
				
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
			
		}
		

		List<FaenaActive> listfaena = ReporteService.findFaenaXCliente(usuLogincliente, clavecliente);
				
		request.setAttribute("listfaena", listfaena);
		request.setAttribute("rform", reporteHorometroForm);
		
		return "reportehorometro";
	}
	
	
	//procesar 
	@RequestMapping(value = "/reportehorometro" , method = RequestMethod.POST)
	public ModelAndView processForm(@Valid ReporteHorometroForm reportForm, BindingResult result,
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
		
		
		List<FaenaActive> listfaena = ReporteService.findFaenaXCliente(usuLogincliente, clavecliente);
		
		reportForm.setCollapseshow("off");
		
		String faenas = reportForm.getFaenas();
		String opt_ver = reportForm.getOpt_ver();

		List<ReporteHorometroActive> rlist = ReporteService.findHorometro(usuLogincliente,clavecliente,Integer.parseInt(faenas),"","",opt_ver);
				
		if(opt_ver.equals("1")){
			reportForm.setOpt_ver_text("Hrs");
		}
		else if(opt_ver.equals("2")){
			reportForm.setOpt_ver_text("Min");
		}
		else if(opt_ver.equals("3")){
			reportForm.setOpt_ver_text("Seg");
		}
		else if(opt_ver.equals("4")){
			reportForm.setOpt_ver_text("HMS");
		}

		request.setAttribute("rlist", rlist);								
		request.setAttribute("rform", reportForm);		
		request.setAttribute("listfaena", listfaena);
	
					
		return new ModelAndView("reportehorometro");
		
	}
}
