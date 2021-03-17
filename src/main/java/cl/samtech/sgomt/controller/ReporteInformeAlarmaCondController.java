package cl.samtech.sgomt.controller;


import java.text.DateFormat;
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
import org.springframework.web.servlet.ModelAndView;


import cl.samtech.sgomt.form.ReportePatenteFechaForm;
import cl.samtech.sgomt.model.Grupo;
import cl.samtech.sgomt.model.VehiculoDevice;
import cl.samtech.sgomt.object.EventActive;
import cl.samtech.sgomt.object.FaenaActive;
import cl.samtech.sgomt.object.Fechas;
import cl.samtech.sgomt.object.PatenteGPSActive;
import cl.samtech.sgomt.object.ReporteInformeAlarmaCondActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.GestionService;
import cl.samtech.sgomt.service.ReporteService;
import cl.samtech.sgomt.service.UtilServicio;

@Controller
//@RequestMapping("editarusuario.html")
public class ReporteInformeAlarmaCondController {
	@RequestMapping(value = "/reporteinformealarmacond", method = RequestMethod.GET)
	public ModelAndView showForm(Map model,HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
			return new ModelAndView("home");
		}
		
		//Relaciono el form
		ReportePatenteFechaForm reportePatenteFechaForm = new ReportePatenteFechaForm();
		model.put("reportePatenteFechaForm", reportePatenteFechaForm);
		
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
		
		Fechas fechas = UtilServicio.getFechasConfDiaParam("0", "dd/MM/yyyy");
		
		
		Date fecha= new Date();
		
		List<PatenteGPSActive> listpatentes = ReporteService.findPatentesXClienteFecha(usuLogin, clave, fecha, usuario.getClienterut());						
				
		List<FaenaActive> listfaena = ReporteService.findFaenaXCliente(usuLogin, clave);		
		
		//eventos
		List<EventActive> listEventos = GestionService.findEventos(usuLogincliente, clavecliente);
		
		//Luego
		//conductores
		
		reportePatenteFechaForm.setFechaDesde(fechas.getFechain());
		reportePatenteFechaForm.setFechaHasta(fechas.getFechafin());
		
		DateFormat formatteri2 = new SimpleDateFormat("HH:mm");
		String horaDesde = formatteri2.format(fechas.getCalendarin().getTime());
		String horaHasta = formatteri2.format(fechas.getCalendarfin().getTime());
		
		reportePatenteFechaForm.setHoraDesde(horaDesde);
		reportePatenteFechaForm.setHoraHasta(horaHasta);
				
		
		//Configuracion de inicio, todas las faenas, 	
		//'usuario','fecha,'fecha2',100,'reg_pagina','alar','busqueda','clave_us','sw'
		//                                  0          5        0                   4
		
		//descripcion de los parametros que recibe el procedure en sqlserver
		//alar (evento)
		//if alar "" -> alar= 5
		//else alar = evento 

		//faena seleccionada sw 2
		//if faena es 0 - > sw 4

		//patente seleccionada sw 1

		//conductor seleccioando sw 3
		
		String	sw = "4";
		String busqueda = "0";
		String alar = "5";
			
		//List<ReporteInformeAlarmaCondActive> rlist = GestionService.findInformeAlarmaCond(usuLogin, fechas.getFechatimein(), fechas.getFechatimefin(),alar, busqueda, clave, sw);
				
		reportePatenteFechaForm.setCollapseshow("NO");
		String faenaTodas = "NO";
		String eventoTodas = "SI";
		
		request.setAttribute("rform", reportePatenteFechaForm);
		request.setAttribute("listfaena", listfaena);
		request.setAttribute("listpatentes", listpatentes);
		request.setAttribute("listEventos", listEventos);		
		//request.setAttribute("rlist", rlist);
		request.setAttribute("faenaTodas", faenaTodas);
		request.setAttribute("eventoTodas", eventoTodas);
			
		
		return new ModelAndView("reporteinformealarmacond");
	}
	
	//procesar 
	@RequestMapping(value = "/reporteinformealarmacond" , method = RequestMethod.POST)
	public ModelAndView processForm(@Valid ReportePatenteFechaForm reportePatenteFechaForm, BindingResult result,
			Map model,HttpServletRequest request) {

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
		

		if (result.hasErrors()) {
			return new ModelAndView("home");
		}
		
		//me traigo los valores del formulario
		reportePatenteFechaForm = (ReportePatenteFechaForm) model.get("reportePatenteFechaForm");
		
		String patente = reportePatenteFechaForm.getPatente();
		String desde = reportePatenteFechaForm.getFechaDesde()+" "+reportePatenteFechaForm.getHoraDesde();
		String hasta = reportePatenteFechaForm.getFechaHasta()+" "+reportePatenteFechaForm.getHoraHasta();
		String faena = reportePatenteFechaForm.getFaena();
		String evento  = reportePatenteFechaForm.getEvento();		
		
		String alar = "";
		String sw = "";
		String busqueda = "";
		
		String eventoTodas = "";
		
		if(evento.equals(" ")) {
			
			alar = "5";
			eventoTodas = "SI";
				
		}else{
			
			alar = evento;
		}

		String faenaTodas = "";
		
		if(!faena.equals("")){
			
			sw = "2";
			//busqueda = faena;
			Grupo grupo =  ReporteService.findGrupoById(faena);	
			busqueda = grupo.getGruNombre();
			
			
			
		}
		
		if(faena.equals("0")){
			
			
			faenaTodas = "SI";
			sw = "4";
			busqueda = "0";
			
		}
		
		
		
		if(!patente.equals("")){
			
			sw = "1";
			//busqueda = patente;
			VehiculoDevice v = ReporteService.findDeviceByPatente02(patente);
			busqueda = v.getDevIdDevice();
			
			
		}
		
		/*if(!conductor.equals("")) {
			
			sw = "3";
			busqueda = conductor;
			
		}*/
		
		List<ReporteInformeAlarmaCondActive> rlist = new ArrayList<ReporteInformeAlarmaCondActive>();
		
		
		if(faena.equals("") && patente.equals("")){
			
			String mensaje = "Debe selecionar Patente o Faena";
			request.setAttribute("mensaje", mensaje);
			
			
		}else {
		
	
		//List<ReporteInformeAlarmaCondActive> rlist = GestionService.findInformeAlarmaCond(usuLogin, desde, hasta,alar, busqueda, clave, sw); samtech , 4510sam
		 rlist = GestionService.findInformeAlarmaCond(usuLogincliente, desde, hasta,alar, busqueda, clavecliente, sw);
		
		

		if(rlist.size()==0){
			
			String mensaje = "No hay resultado";
			request.setAttribute("mensaje", mensaje);	
		}
		
		}//fin validacion faena patente
							
		Date fecha= new Date();
		List<PatenteGPSActive> listpatentes = ReporteService.findPatentesXClienteFecha(usuLogin, clave, fecha, usuario.getClienterut());
		
		
		List<FaenaActive> listfaena = ReporteService.findFaenaXCliente(usuLogin, clave);
		
		Grupo grupo =  ReporteService.findGrupoById(faena);						
		
		//eventos
		List<EventActive> listEventos = GestionService.findEventos(usuLogin, clave);
		
		
		reportePatenteFechaForm.setCollapseshow("SI");
		
		request.setAttribute("listfaena", listfaena);		
		request.setAttribute("listpatentes", listpatentes);
		request.setAttribute("rlist", rlist);								
		request.setAttribute("rform", reportePatenteFechaForm);
		request.setAttribute("grupo", grupo);
		request.setAttribute("listEventos", listEventos);
		request.setAttribute("faenaTodas", faenaTodas);
		request.setAttribute("eventoTodas", eventoTodas);
		
	
		return new ModelAndView("reporteinformealarmacond");
		
	}
	
}