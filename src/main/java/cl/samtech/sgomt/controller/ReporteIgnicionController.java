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

import cl.samtech.sgomt.form.BasicForm;
import cl.samtech.sgomt.form.ReportePatenteFechaForm;
import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.FaenaActive;
import cl.samtech.sgomt.object.Fechas;
import cl.samtech.sgomt.object.PatenteGPSActive;
import cl.samtech.sgomt.object.ReporteIgnicionActive;
import cl.samtech.sgomt.object.ReporteTiempoTransporteActive;
import cl.samtech.sgomt.object.TurnoActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.ReporteService;
import cl.samtech.sgomt.service.UsuarioService;
import cl.samtech.sgomt.service.UtilServicio;

@Controller
public class ReporteIgnicionController {
	@RequestMapping(value = "/reporteignicion", method = RequestMethod.GET)	
	public ModelAndView showForm(Map model,HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
			return new ModelAndView("home");
		}
		
		//Relaciono el form
		BasicForm basicForm = new BasicForm();
		model.put("basicForm", basicForm);

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

		Fechas fechas = UtilServicio.getFechasConfDiaParam("0", "dd/MM/yyyy");
		
		basicForm.setFechaDesde(fechas.getFechain());
		basicForm.setFechaHasta(fechas.getFechafin());
		
		DateFormat formatteri2 = new SimpleDateFormat("HH:mm");
		String horaDesde = formatteri2.format(fechas.getCalendarin().getTime());
		String horaHasta = formatteri2.format(fechas.getCalendarfin().getTime());
		
		basicForm.setHoraDesde(horaDesde);
		basicForm.setHoraHasta(horaHasta);
		
		basicForm.setCollapseshow("NO");
		
		request.setAttribute("rform", basicForm);		
		request.setAttribute("listpatentes", listpatentes);
		request.setAttribute("listfaena", listfaena);
		
		return new ModelAndView("reporteignicion");
	}
	
	//procesar 
		@RequestMapping(value = "/reporteignicion" , method = RequestMethod.POST)
		public ModelAndView processForm(@Valid BasicForm basicForm, BindingResult result,
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
			
			basicForm.setCollapseshow("off");
			
			String faena = basicForm.getFaenas();
			String patente = basicForm.getPatentes();
			String desde = basicForm.getFechaDesde();
			String hasta = basicForm.getFechaHasta();
			String horaDesde = basicForm.getHoraDesde();
			String horaHasta = basicForm.getHoraHasta();
			Integer turnos = basicForm.getTurnos();
			String opt_ver = basicForm.getOpt_ver();
			Integer sw=0;

			List<ReporteIgnicionActive> rlist = ReporteService.findIgnicion(desde+" "+horaDesde, hasta+" "+horaHasta, faena, patente, usuLogincliente);					

			request.setAttribute("rlist", rlist);								
			request.setAttribute("rform", basicForm);
			request.setAttribute("listpatentes", listpatentes);
			request.setAttribute("listfaena", listfaena);
		
						
			return new ModelAndView("reporteignicion");
		}
	

}
