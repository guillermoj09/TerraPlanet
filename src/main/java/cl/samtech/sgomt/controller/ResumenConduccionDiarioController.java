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

import cl.samtech.sgomt.form.BasicForm;
import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.FaenaActive;
import cl.samtech.sgomt.object.Fechas;
import cl.samtech.sgomt.object.PatenteGPSActive;
import cl.samtech.sgomt.object.ResumenConduccionDiarioActive;
import cl.samtech.sgomt.object.TurnoActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.ReporteService;
import cl.samtech.sgomt.service.UsuarioService;
import cl.samtech.sgomt.service.UtilServicio;

@Controller
public class ResumenConduccionDiarioController {
	
	@RequestMapping(value = "/resumenconducciondiario", method = RequestMethod.GET)	
	public ModelAndView showForm(Map model,HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
			return new ModelAndView("home");
		}
		
		//Relaciono el form
		BasicForm reportBasicForm = new BasicForm();
		model.put("reportBasicForm", reportBasicForm);

		UsuarioLogin usuario=(UsuarioLogin)request.getSession().getAttribute("usuario");				
		
		String cliente = "";
		String clave = "";
		String usuLogincliente = "";
		String clavecliente = "";
		String rutCliente = "";
		try {
			cliente  = usuario.getUsername();
			clave  = usuario.getPassword();
			usuLogincliente  = usuario.getCliUsuSamtech();
			clavecliente  = usuario.getCliPassSamtech();
			rutCliente = usuario.getClienterut();
			usuario.setUrlservlet(request.getServletPath());
		} catch (Exception e) {			
			return new ModelAndView("home");			
		}
		//validad menu
		if(!UsuarioService.validarMenuUsuario(usuario, request.getServletPath())){		
			return new ModelAndView("home");	
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuario, request.getServletPath());
		request.setAttribute("b", b);		
		
		Date fecha= new Date();
		List<PatenteGPSActive> listpatentes = ReporteService.findPatentesXClienteFechaTipo(cliente, clave, fecha, "t", usuario.getClienterut());
		List<FaenaActive> listfaena = ReporteService.findFaenaXCliente(usuLogincliente, clavecliente);	
		List<TurnoActive> listturno = ReporteService.findTurnoXCliente(usuLogincliente, clavecliente , rutCliente);
		
		
		Fechas fechas = UtilServicio.getFechasConfDiaParam("-1", "dd/MM/yyyy");
		
		reportBasicForm.setFechaDesde(fechas.getFechain());
		
		reportBasicForm.setCollapseshow("NO");
		
		request.setAttribute("rform", reportBasicForm);		
		request.setAttribute("listpatentes", listpatentes);
		request.setAttribute("listfaena", listfaena);
		request.setAttribute("listturno", listturno);
		
		return new ModelAndView("resumenconducciondiario");
	}
	
	
	//procesar 
		@RequestMapping(value = "/resumenconducciondiario" , method = RequestMethod.POST)
		public ModelAndView processForm(@Valid BasicForm reportBasicForm, BindingResult result,
				Map model,HttpServletRequest request) {


			if (result.hasErrors()) {
				return new ModelAndView("home");
			}
			
			UsuarioLogin usuario=(UsuarioLogin)request.getSession().getAttribute("usuario");				
			
			String cliente = "";
			String clave = "";
			String usuLogincliente = "";
			String clavecliente = "";
			String rutCliente = "";
			try {
				cliente  = usuario.getUsername();
				clave  = usuario.getPassword();	
				usuLogincliente  = usuario.getCliUsuSamtech();
				clavecliente  = usuario.getCliPassSamtech();
				rutCliente = usuario.getClienterut();
				usuario.setUrlservlet(request.getServletPath());
			} catch (Exception e) {			
				return new ModelAndView("home");			
			}
			//validad menu
			if(!UsuarioService.validarMenuUsuario(usuario, request.getServletPath())){		
				return new ModelAndView("home");		
			}
			//mando barra menu a la vista
			BarraMenuActive b = UsuarioService.barraMenu(usuario, request.getServletPath());
			request.setAttribute("b", b);		
			
			Date fecha= new Date();
			List<PatenteGPSActive> listpatentes = ReporteService.findPatentesXClienteFechaTipo(cliente, clave, fecha, "t", usuario.getClienterut());
			List<FaenaActive> listfaena = ReporteService.findFaenaXCliente(usuLogincliente, clavecliente);
			List<TurnoActive> listturno = ReporteService.findTurnoXCliente(usuLogincliente, clavecliente , rutCliente);
			
			
			reportBasicForm.setCollapseshow("off");

			String desde = reportBasicForm.getFechaDesde();
			String patente = reportBasicForm.getPatentes();
			String faenas = reportBasicForm.getFaenas();
			String opt_ver = reportBasicForm.getOpt_ver();
			Integer turnos = reportBasicForm.getTurnos();
			String turnoname = reportBasicForm.getTurnoname();
			
			//Buscar Turno por nombre dentro rango fecha //no terminado
			int turnoid = 0;
			if(!turnoname.equals("0")){
			turnoid = UsuarioService.findTurnobyNameAndFechaIni(turnoname, desde);
			}else{
				turnoid = 0;
			}

			//List<ResumenConduccionDiarioActive> rlist = ReporteService.findConduccionDiario(patente,faenas, desde, desde,turnos);
			List<ResumenConduccionDiarioActive> rlist = ReporteService.findConduccionDiario(patente,faenas, desde, desde,turnoid);
			
			
			if(opt_ver.equals("1")){
				reportBasicForm.setOpt_ver_text("Hrs");
			}
			else if(opt_ver.equals("2")){
				reportBasicForm.setOpt_ver_text("Min");
			}
			else if(opt_ver.equals("3")){
				reportBasicForm.setOpt_ver_text("Seg");
			}
			else if(opt_ver.equals("4")){
				reportBasicForm.setOpt_ver_text("HMS");
			}
			
			
			request.setAttribute("rlist", rlist);								
			request.setAttribute("rform", reportBasicForm);
			request.setAttribute("listpatentes", listpatentes);	
			request.setAttribute("listfaena", listfaena);
			request.setAttribute("listturno", listturno);
			
						
			return new ModelAndView("resumenconducciondiario");
			
		}



}
