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

import cl.samtech.sgomt.form.ReporteHorometroForm;
import cl.samtech.sgomt.model.TipoVehiculo;
import cl.samtech.sgomt.object.FaenaActive;
import cl.samtech.sgomt.object.Fechas;
import cl.samtech.sgomt.object.PatenteGPSActive;
import cl.samtech.sgomt.object.ReporteHorometroActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.DispositivoService;
import cl.samtech.sgomt.service.ReporteService;
import cl.samtech.sgomt.service.UtilServicio;

@Controller
public class ReporteHorometroTiempoController {
	
	@RequestMapping(value = "/reportehorometrotiempo", method = RequestMethod.GET)
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
		
		Fechas fechas = UtilServicio.getFechasConfDiaParam("0", "dd/MM/yyyy");
		DateFormat formatteri2 = new SimpleDateFormat("HH:mm");
		String horaDesde = formatteri2.format(fechas.getCalendarin().getTime());
		String horaHasta = formatteri2.format(fechas.getCalendarfin().getTime());
		
		reporteHorometroForm.setFechaDesde(fechas.getFechain());
		reporteHorometroForm.setFechaHasta(fechas.getFechafin());
		reporteHorometroForm.setHoraDesde(horaDesde);
		reporteHorometroForm.setHoraHasta(horaHasta);
		
		//UsuarioLogin usuariologin = (UsuarioLogin) request.getSession().getAttribute("usuario");
		//String rutCliente = DispositivoService.FindRutClienteByUser(usuariologin.getUsername());		
		//Date fecha= new Date();
		//List<PatenteGPSActive> listpatentes = ReporteService.findPatentesXClienteFechaTipo(cliente, clave, fecha, "t");
		//ArrayList<TipoVehiculo> tipovehiculos = DispositivoService.FindAllTipoVehiculoByRutCliente(rutCliente);
		List<FaenaActive> listfaena = ReporteService.findFaenaXCliente(usuLogincliente, clavecliente);
				
		request.setAttribute("listfaena", listfaena);
		request.setAttribute("rform", reporteHorometroForm);
		//request.setAttribute("listpatentes", listpatentes);
		//request.setAttribute("tipovehiculos", tipovehiculos);
		
		return "reportehorometrotiempo";
	}
	
	
	
	//procesar 
	@RequestMapping(value = "/reportehorometrotiempo" , method = RequestMethod.POST)
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
		String desde = reportForm.getFechaDesde();
		String hasta = reportForm.getFechaHasta();	
		String horaDesde = reportForm.getHoraDesde();
		String horaHasta = reportForm.getHoraHasta();	
		String opt_ver = reportForm.getOpt_ver();
		

		List<ReporteHorometroActive> rlist = ReporteService.findHorometro(usuLogincliente,clavecliente,Integer.parseInt(faenas),desde+" "+horaDesde, hasta+" "+horaHasta,opt_ver);
		
		
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
	
					
		return new ModelAndView("reportehorometrotiempo");
		
	}

}
