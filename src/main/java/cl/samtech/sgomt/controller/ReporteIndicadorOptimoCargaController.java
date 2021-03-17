package cl.samtech.sgomt.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import cl.samtech.sgomt.object.Fechas;
import cl.samtech.sgomt.object.PatenteGPSActive;
import cl.samtech.sgomt.object.ReporteIndicadorOptimoCargaActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.ReporteService;
import cl.samtech.sgomt.service.UtilServicio;

@Controller
public class ReporteIndicadorOptimoCargaController {
	@RequestMapping(value = "/reporteindicadoroptimocarga", method = RequestMethod.GET)
	public String showForm(Map model,HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
			return "home";
		}
		
		//Relaciono el form
		ReportePatenteFechaForm reportePatenteFechaForm = new ReportePatenteFechaForm();
		model.put("reportePatenteFechaForm", reportePatenteFechaForm);
			
		UsuarioLogin usuario=(UsuarioLogin)request.getSession().getAttribute("usuario");					
		String usuLogin = "";
		String clave = "";
		String usuLogincliente = "";
		String clavecliente = "";
		String rutcliente = "";
		try {
			usuLogin  = usuario.getUsername();			
			clave  = usuario.getPassword();
			usuLogincliente  = usuario.getCliUsuSamtech();
			clavecliente  = usuario.getCliPassSamtech();
			rutcliente = usuario.getClienterut();
			usuario.setUrlservlet(request.getServletPath());
		} catch (Exception e) {			
			return "home";			
		}
		
		Date fecha= new Date();
		//camiones c, maquinas m
		List<PatenteGPSActive> listpatentes = ReporteService.findPatentesXClienteFechaTipo(usuLogin, clave, fecha, "m", usuario.getClienterut());		
				
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
		
		
		return "reporteindicadoroptimocarga";
	}
	
	//procesar Grafico scatter
	@RequestMapping(value = "/reporteindicadoroptimocarga" , method = RequestMethod.POST)
	public String processForm(@Valid ReportePatenteFechaForm reportePatenteFechaForm, BindingResult result,
			Map model,HttpServletRequest request) {

		if (result.hasErrors()) {
			return "home";
		}		
		UsuarioLogin usuario=(UsuarioLogin)request.getSession().getAttribute("usuario");					
		String usuLogin = "";
		String clave = "";
		String usuLogincliente = "";
		String clavecliente = "";
		String rutcliente = "";
		try {
			usuLogin  = usuario.getUsername();			
			clave  = usuario.getPassword();
			usuLogincliente  = usuario.getCliUsuSamtech();
			clavecliente  = usuario.getCliPassSamtech();
			rutcliente = usuario.getClienterut();
		} catch (Exception e) {			
			return "home";			
		}
		
		String patentes = reportePatenteFechaForm.getPatente();
		String desde = reportePatenteFechaForm.getFechaDesde()+" "+reportePatenteFechaForm.getHoraDesde();
		String hasta = reportePatenteFechaForm.getFechaHasta()+" "+reportePatenteFechaForm.getHoraHasta();
		
		Date fecha= new Date();
		//camiones c, maquinas m
		List<PatenteGPSActive> listpatentes = ReporteService.findPatentesXClienteFechaTipoNotIn(usuLogin, clave, fecha, "m", patentes, "N", usuario.getClienterut());
		List<PatenteGPSActive> listpatentessi = ReporteService.findPatentesXClienteFechaTipoNotIn(usuLogin, clave, fecha, "m", patentes, "S", usuario.getClienterut());
		
		//me traigo los valores del formulario
		reportePatenteFechaForm = (ReportePatenteFechaForm) model.get("reportePatenteFechaForm");
				
		String callapse = "";
		List<ReporteIndicadorOptimoCargaActive> rlist = ReporteService.findIndicadorOptimoCarga(patentes, desde, hasta);

		if(callapse.equals("NO")){
			reportePatenteFechaForm.setCollapseshow("NO");
		}else{
			reportePatenteFechaForm.setCollapseshow("SI");
		}

		request.setAttribute("listpatentes", listpatentes);
		request.setAttribute("listpatentessi", listpatentessi);
		request.setAttribute("rlist", rlist);								
		request.setAttribute("rform", reportePatenteFechaForm);
	
					
		return "reporteindicadoroptimocarga";
		
	}
	
}