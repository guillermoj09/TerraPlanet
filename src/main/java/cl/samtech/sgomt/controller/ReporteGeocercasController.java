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

import cl.samtech.sgomt.form.HistoricoForm;
import cl.samtech.sgomt.form.ReporteGeocercasForm;
import cl.samtech.sgomt.form.ReportePatenteFechaForm;
import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.FaenaActive;
import cl.samtech.sgomt.object.Fechas;
import cl.samtech.sgomt.object.ListadoGeocercasActive;
import cl.samtech.sgomt.object.PatenteGPSActive;
import cl.samtech.sgomt.object.ReporteGeocercasActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.AdministracionService;
import cl.samtech.sgomt.service.ReporteService;
import cl.samtech.sgomt.service.UsuarioService;
import cl.samtech.sgomt.service.UtilServicio;

@Controller
public class ReporteGeocercasController {
	
	@RequestMapping(value = "/reportegeocercas", method = RequestMethod.GET)
	public ModelAndView showForm(Map model,HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
			return new ModelAndView("home");
		}
		
		//Relaciono el form
		ReporteGeocercasForm reporteGeocercasForm = new ReporteGeocercasForm();
		model.put("reporteGeocercasForm", reporteGeocercasForm);
		
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
		//validad menu
		if(!UsuarioService.validarMenuUsuario(usuario, request.getServletPath())){		
			return new ModelAndView("home");	
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuario, request.getServletPath());
		request.setAttribute("b", b);		
		
		Fechas fechas = UtilServicio.getFechasConfDiaParam("0", "dd/MM/yyyy");
				
		Date fecha= new Date();
		List<PatenteGPSActive> listpatentes = ReporteService.findPatentesXClienteFecha(usuLogin, clave, fecha, usuario.getClienterut());
				
		List<FaenaActive> listfaena = ReporteService.findFaenaXCliente(usuLogincliente, clavecliente);		
		
		reporteGeocercasForm.setFechaDesde(fechas.getFechain());
		reporteGeocercasForm.setFechaHasta(fechas.getFechafin());
		
		DateFormat formatteri2 = new SimpleDateFormat("HH:mm");
		String horaDesde = formatteri2.format(fechas.getCalendarin().getTime());
		String horaHasta = formatteri2.format(fechas.getCalendarfin().getTime());
		
		reporteGeocercasForm.setHoraDesde(horaDesde);
		reporteGeocercasForm.setHoraHasta(horaHasta);		
		reporteGeocercasForm.setVelocidad("1");
		reporteGeocercasForm.setCollapseshow("NO");
		
		List<ListadoGeocercasActive>  geocercas = new ArrayList<ListadoGeocercasActive>();
		geocercas = AdministracionService.findListadoGeocercos( usuLogin, clave);
		
		
	    request.setAttribute("geocercas", geocercas);		
		request.setAttribute("rform", reporteGeocercasForm);
		request.setAttribute("listfaena", listfaena);
		request.setAttribute("listpatentes", listpatentes);
				
		//request.setAttribute("rlist", rlist);
		
		return new ModelAndView("reportegeocercas");
	}
	
	
	//procesar 
	@RequestMapping(value = "/reportegeocercas" , method = RequestMethod.POST)
	public ModelAndView processForm(@Valid ReporteGeocercasForm reporteGeocercasForm, BindingResult result,Map model,HttpServletRequest request) {
		
		if (result.hasErrors()) {
			return new ModelAndView("home");
		}
		//reporteGeocercasForm = (ReporteGeocercasForm) model.get("reporteGeocercasForm");
		
		reporteGeocercasForm.setCollapseshow("SI");
		
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
		//validad menu
		if(!UsuarioService.validarMenuUsuario(usuario, request.getServletPath())){		
			return new ModelAndView("home");	
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuario, request.getServletPath());
		request.setAttribute("b", b);
		
		Date fecha= new Date();
		List<PatenteGPSActive> listpatentes = ReporteService.findPatentesXClienteFecha(usuLogin, clave, fecha, usuario.getClienterut());
				
		List<FaenaActive> listfaena = ReporteService.findFaenaXCliente(usuLogincliente, clavecliente);	
		
		List<ListadoGeocercasActive>  geocercas = new ArrayList<ListadoGeocercasActive>();
		geocercas = AdministracionService.findListadoGeocercos( usuLogin, clave);
		
		/*
		Si @sw = 0 es por patente.
		Si @sw = 1 es por faena.
		Si @geocerca = 0 es todas las geocercas.
		Si @geocerca > 0 es por geocerca.
		@solo_entradaysalida=0  todos
		@solo_entradaysalida=1  solo entrada y salidas
		*/
		String sw = "0";
		String faena = "0";
		String patentes = "0";
		
		if(reporteGeocercasForm.getFaenas().equals("")==false){
			faena = reporteGeocercasForm.getFaenas();
			sw  = "1";
		}
		if(reporteGeocercasForm.getPatentes().equals("")==false){
			patentes = reporteGeocercasForm.getPatentes();
		}
		
		List<ReporteGeocercasActive> listReporGeo = ReporteService.findReporteGeocercas(reporteGeocercasForm.getFechaDesde()+" "+reporteGeocercasForm.getHoraDesde()
				, reporteGeocercasForm.getFechaHasta()+" "+reporteGeocercasForm.getHoraHasta() , sw, faena
				, reporteGeocercasForm.getGeocercas(),reporteGeocercasForm.getPuntos() ,patentes , usuLogin, reporteGeocercasForm.getVelocidad());
		
		request.setAttribute("listReporGeo", listReporGeo);
		request.setAttribute("geocercas", geocercas);		
		request.setAttribute("listfaena", listfaena);
		request.setAttribute("listpatentes", listpatentes);
		request.setAttribute("rform", reporteGeocercasForm);	
		return new ModelAndView("reportegeocercas");
		
	}

}
