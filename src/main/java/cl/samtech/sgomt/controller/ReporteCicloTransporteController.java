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

import cl.samtech.sgomt.form.CrearUsuarioForm;
import cl.samtech.sgomt.form.ReportePatenteFechaForm;
import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.FaenaActive;
import cl.samtech.sgomt.object.Fechas;
import cl.samtech.sgomt.object.PatenteGPSActive;
import cl.samtech.sgomt.object.ReporteIndicadorOptimoCargaActive;
import cl.samtech.sgomt.object.ReporteTiempoCarguioActive;
import cl.samtech.sgomt.object.ReporteTiempoTransporteActive;
import cl.samtech.sgomt.object.TurnoActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.ReporteService;
import cl.samtech.sgomt.service.UsuarioService;
import cl.samtech.sgomt.service.UtilServicio;

@Controller
//@RequestMapping("editarusuario.html")
public class ReporteCicloTransporteController {
	@RequestMapping(value = "/reporteciclotransporte", method = RequestMethod.GET)
	public ModelAndView showForm(Map model,HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
			return new ModelAndView("home");
		}
		
		//Relaciono el form
		ReportePatenteFechaForm reportePatenteFechaForm = new ReportePatenteFechaForm();
		model.put("reportePatenteFechaForm", reportePatenteFechaForm);

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

		List<PatenteGPSActive> listpatentes = ReporteService.findPatentesXClienteFechaTipo(cliente, clave, fecha, "c", usuario.getClienterut());

		//List<FaenaActive> listfaena = ReporteService.findFaenaXCliente(cliente, clave);
		List<FaenaActive> listfaena = ReporteService.findFaenaXCliente(usuLogincliente, clavecliente);
		List<TurnoActive> listturno = ReporteService.findTurnoXCliente(usuLogincliente, clavecliente , rutCliente);
		

		Fechas fechas = UtilServicio.getFechasConfDiaParam("0", "dd/MM/yyyy");
		
		reportePatenteFechaForm.setFechaDesde(fechas.getFechain());
		reportePatenteFechaForm.setFechaHasta(fechas.getFechafin());
		
		DateFormat formatteri2 = new SimpleDateFormat("HH:mm");
		String horaDesde = formatteri2.format(fechas.getCalendarin().getTime());
		String horaHasta = formatteri2.format(fechas.getCalendarfin().getTime());
		
		reportePatenteFechaForm.setHoraDesde(horaDesde);
		reportePatenteFechaForm.setHoraHasta(horaHasta);
		
		reportePatenteFechaForm.setCollapseshow("NO");
		
		request.setAttribute("rform", reportePatenteFechaForm);		
		request.setAttribute("listpatentes", listpatentes);
		request.setAttribute("listfaena", listfaena);
		request.setAttribute("listturno", listturno);
		
		return new ModelAndView("reporteciclotransporte");
	}
	
	//procesar  			  reporteciclotransporte
	@RequestMapping(value = "/reporteciclotransporte" , method = RequestMethod.POST)
	public ModelAndView processForm(@Valid ReportePatenteFechaForm reportePatenteFechaForm, BindingResult result,
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
		List<PatenteGPSActive> listpatentes = ReporteService.findPatentesXClienteFechaTipo(cliente, clave, fecha, "c", usuario.getClienterut());
		List<FaenaActive> listfaena = ReporteService.findFaenaXCliente(usuLogincliente, clavecliente);
		List<TurnoActive> listturno = ReporteService.findTurnoXCliente(usuLogincliente, clavecliente , rutCliente);
		//List<FaenaActive> listfaena = ReporteService.findFaenaXCliente(cliente, clave);	

		reportePatenteFechaForm.setCollapseshow("off");
		
		String faena = reportePatenteFechaForm.getFaena();
		String patente = reportePatenteFechaForm.getPatente();
		String desde = reportePatenteFechaForm.getFechaDesde();
		String hasta = reportePatenteFechaForm.getFechaHasta();
		String horaDesde = reportePatenteFechaForm.getHoraDesde();
		String horaHasta = reportePatenteFechaForm.getHoraHasta();
		Integer turnos = reportePatenteFechaForm.getTurnos();
		String opt_ver = reportePatenteFechaForm.getOpt_ver();
		String turnoname = reportePatenteFechaForm.getTurnoname();
		
		//Buscar Turno por nombre dentro rango fecha //
		int turnoid = 0;
		if(!turnoname.equals("0")){
		turnoid = UsuarioService.findTurnobyNameAndFechaIni(turnoname, desde);
		}else{
			turnoid = 0;
		}

		//List<ReporteTiempoTransporteActive> rlist = ReporteService.findCicloTransporte(faena,patente, desde+" "+horaDesde, hasta+" "+horaHasta,turnos,opt_ver);
		List<ReporteTiempoTransporteActive> rlist = ReporteService.findCicloTransporte(faena,patente, desde+" "+horaDesde, hasta+" "+horaHasta,turnoid,opt_ver);
		
		if(opt_ver.equals("1")){
			reportePatenteFechaForm.setOpt_ver_text("Hrs");
		}
		else if(opt_ver.equals("2")){
			reportePatenteFechaForm.setOpt_ver_text("Min");
		}
		else if(opt_ver.equals("3")){
			reportePatenteFechaForm.setOpt_ver_text("Seg");
		}
		else if(opt_ver.equals("4")){
			reportePatenteFechaForm.setOpt_ver_text("HMS");
		}
		
		//System.out.println(reportePatenteFechaForm.getOpt_ver_text());
				

		request.setAttribute("rlist", rlist);								
		request.setAttribute("rform", reportePatenteFechaForm);
		request.setAttribute("listpatentes", listpatentes);
		request.setAttribute("listfaena", listfaena);
		request.setAttribute("listturno", listturno);
	
					
		return new ModelAndView("reporteciclotransporte");
		
	}
	
}