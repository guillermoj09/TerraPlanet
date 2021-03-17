package cl.samtech.sgomt.controller;

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
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.ReporteService;
import cl.samtech.sgomt.service.UsuarioService;
import cl.samtech.sgomt.service.UtilServicio;

@Controller
//@RequestMapping("editarusuario.html")
public class ReporteTiempoTransporteController {
	@RequestMapping(value = "/reportetiempotransporte", method = RequestMethod.GET)
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
		try {
			cliente  = usuario.getUsername();
			clave  = usuario.getPassword();		
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
		List<FaenaActive> listfaena = ReporteService.findFaenaXCliente(cliente, clave);		

		Fechas fechas = UtilServicio.getFechasConfDiaParam("-1", "dd/MM/yyyy");
		
		reportePatenteFechaForm.setFechaDesde(fechas.getFechain());
		reportePatenteFechaForm.setFechaHasta(fechas.getFechafin());
		
		reportePatenteFechaForm.setCollapseshow("NO");
		
		request.setAttribute("rform", reportePatenteFechaForm);		
		request.setAttribute("listpatentes", listpatentes);
		request.setAttribute("listfaena", listfaena);
		
		return new ModelAndView("reportetiempotransporte");
	}
	
	//procesar 
	@RequestMapping(value = "/reportetiempotransporte" , method = RequestMethod.POST)
	public ModelAndView processForm(@Valid ReportePatenteFechaForm reportePatenteFechaForm, BindingResult result,
			Map model,HttpServletRequest request) {


		if (result.hasErrors()) {
			return new ModelAndView("home");
		}
		
		UsuarioLogin usuario=(UsuarioLogin)request.getSession().getAttribute("usuario");				
		
		String cliente = "";
		String clave = "";
		try {
			cliente  = usuario.getUsername();
			clave  = usuario.getPassword();			
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
		List<FaenaActive> listfaena = ReporteService.findFaenaXCliente(cliente, clave);	

		reportePatenteFechaForm.setCollapseshow("SI");
		
		String faena = reportePatenteFechaForm.getFaena();
		String patente = reportePatenteFechaForm.getPatente();
		String desde = reportePatenteFechaForm.getFechaDesde();
		String hasta = reportePatenteFechaForm.getFechaHasta();

		List<ReporteTiempoTransporteActive> rlist = ReporteService.findTiempoTransporte(faena,patente, desde+" 00:00:00", hasta+" 00:00:00");
		

		request.setAttribute("rlist", rlist);								
		request.setAttribute("rform", reportePatenteFechaForm);
		request.setAttribute("listpatentes", listpatentes);
		request.setAttribute("listfaena", listfaena);
	
					
		return new ModelAndView("reportetiempotransporte");
		
	}
	
}