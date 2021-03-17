package cl.samtech.sgomt.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import cl.samtech.sgomt.model.VehiculoDevice;
import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.DetalleXViajeActive;
import cl.samtech.sgomt.object.Fechas;
import cl.samtech.sgomt.object.PatenteGPSActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.ReporteService;
import cl.samtech.sgomt.service.UsuarioService;
import cl.samtech.sgomt.service.UtilServicio;

@Controller
public class DetalleXViajeController {
	@RequestMapping(value = "/detallexviaje", method = RequestMethod.GET)
	public String showForm(Map model,HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
			return "home";
		}
		
		UsuarioLogin usuario=(UsuarioLogin)request.getSession().getAttribute("usuario");
		usuario.setUrlservlet(request.getServletPath());
		//validad menu
		if(!UsuarioService.validarMenuUsuario(usuario, request.getServletPath())){		
			return "home";			
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuario, request.getServletPath());
		request.setAttribute("b", b);		
		
		//Relaciono el form
		ReportePatenteFechaForm reportePatenteFechaForm = new ReportePatenteFechaForm();
		model.put("reportePatenteFechaForm", reportePatenteFechaForm);
		
		String cliente  = usuario.getUsername();
		String clave  = usuario.getPassword();
				
		Date fecha= new Date();
		List<PatenteGPSActive> listpatentes = ReporteService.findPatentesXClienteFecha(cliente, clave, fecha, usuario.getClienterut());
		
		Fechas fechas = UtilServicio.getFechasConfDiaParam("0", "dd/MM/yyyy");
		
		reportePatenteFechaForm.setFechaDesde(fechas.getFechain());
		reportePatenteFechaForm.setFechaHasta(fechas.getFechafin());
		
		DateFormat formatteri2 = new SimpleDateFormat("HH:mm");
		String horaDesde = formatteri2.format(fechas.getCalendarin().getTime());
		String horaHasta = formatteri2.format(fechas.getCalendarfin().getTime());
		
		reportePatenteFechaForm.setHoraDesde(horaDesde);
		reportePatenteFechaForm.setHoraHasta(horaHasta);
		
		//todos (1) // combustible > 0 (0)  sw1 
		reportePatenteFechaForm.setSw1("1"); 
				
		reportePatenteFechaForm.setCollapseshow("NO");
				
		request.setAttribute("rform", reportePatenteFechaForm);
		request.setAttribute("listpatentes", listpatentes);
		
		return "detallexviaje";
	}
	
	//procesar 
	@RequestMapping(value = "/detallexviaje" , method = RequestMethod.POST)
	public String processForm(@Valid ReportePatenteFechaForm reportePatenteFechaForm, BindingResult result,
			Map model,HttpServletRequest request) {
		
		if(request.getSession().getAttribute("usuario")==null)
		{
			return "home";
		}
		
		UsuarioLogin usuario=(UsuarioLogin)request.getSession().getAttribute("usuario");
		
		
		String cliente = "";
		String clave = "";
		try {
			cliente  = usuario.getUsername();
			clave  = usuario.getPassword();			
		} catch (Exception e) {			
			return "home";			
		}

		if (result.hasErrors()) {
			return "home";
		}
		
		//validad menu
		if(!UsuarioService.validarMenuUsuario(usuario, request.getServletPath())){		
			return "home";			
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuario, request.getServletPath());
		request.setAttribute("b", b);		
		
		//me traigo los valores del formulario
		reportePatenteFechaForm = (ReportePatenteFechaForm) model.get("reportePatenteFechaForm");
		
		Date fecha= new Date();
		List<PatenteGPSActive> listpatentes = ReporteService.findPatentesXClienteFecha(cliente, clave, fecha, usuario.getClienterut());		
									
		String patente = reportePatenteFechaForm.getPatente(); //patente fnGetdevice
		String desde = reportePatenteFechaForm.getFechaDesde()+" "+reportePatenteFechaForm.getHoraDesde();
		String hasta = reportePatenteFechaForm.getFechaHasta()+" "+reportePatenteFechaForm.getHoraHasta();
		String sw1 = reportePatenteFechaForm.getSw1();
						
		List<DetalleXViajeActive> rlist = ReporteService.findDetallexViaje(patente, desde, hasta, sw1);
		String callapse = "";
		if(rlist.size()==0){			
			String mensaje = "No hay resultado";
			request.setAttribute("mensaje", mensaje);
			callapse = "NO";
		}
		
		VehiculoDevice vehiculoDevice = new VehiculoDevice();
		//se necesita es el nombre de la patente
		vehiculoDevice = ReporteService.findDeviceByPatente(patente); // sin fecha en la busqueda
		
		if(callapse.equals("NO")){
			reportePatenteFechaForm.setCollapseshow("NO");
		}else{
			reportePatenteFechaForm.setCollapseshow("SI");
		}

		request.setAttribute("rlist", rlist);								
		request.setAttribute("rform", reportePatenteFechaForm);
		request.setAttribute("listpatentes", listpatentes);
		request.setAttribute("vehiculoDevice", vehiculoDevice);
	
					
		return "detallexviaje";
		
	}
	
}