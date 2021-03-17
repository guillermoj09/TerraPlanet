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
import cl.samtech.sgomt.form.ReportePatenteFechaForm;
import cl.samtech.sgomt.model.Grupo;
import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.FaenaActive;
import cl.samtech.sgomt.object.Fechas;
import cl.samtech.sgomt.object.PatenteGPSActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.object.VehiculoActive;
import cl.samtech.sgomt.service.ReporteService;
import cl.samtech.sgomt.service.UsuarioService;
import cl.samtech.sgomt.service.UtilServicio;

@Controller
public class ReporteResumenDescargaController {
	@RequestMapping(value = "/reporteresumendescarga", method = RequestMethod.GET)
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
		try {
			usuLogin  = usuario.getUsername();			
			clave  = usuario.getPassword();
			usuLogincliente  = usuario.getCliUsuSamtech();
			clavecliente  = usuario.getCliPassSamtech();	
			usuario.setUrlservlet(request.getServletPath());
		} catch (Exception e) {			
			return "home";			
		}
		//validad menu
		if(!UsuarioService.validarMenuUsuario(usuario, request.getServletPath())){		
			return "home";			
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuario, request.getServletPath());
		request.setAttribute("b", b);		
		
		Fechas fechas = UtilServicio.getFechasConfDiaParam("0", "dd/MM/yyyy");
		
		
		Date fecha= new Date();
		//camiones		
		List<PatenteGPSActive> listpatentes = ReporteService.findPatentesXClienteFechaTipo(usuLogin, clave, fecha, "c", usuario.getClienterut());					
				
		List<FaenaActive> listfaena = ReporteService.findFaenaXCliente(usuLogincliente, clavecliente);		
		
		reportePatenteFechaForm.setFechaDesde(fechas.getFechain());
		reportePatenteFechaForm.setFechaHasta(fechas.getFechafin());
		
		DateFormat formatteri2 = new SimpleDateFormat("HH:mm");
		String horaDesde = formatteri2.format(fechas.getCalendarin().getTime());
		String horaHasta = formatteri2.format(fechas.getCalendarfin().getTime());
		
		reportePatenteFechaForm.setHoraDesde(horaDesde);
		reportePatenteFechaForm.setHoraHasta(horaHasta);
		
		reportePatenteFechaForm.setPatente("todos");
		
		reportePatenteFechaForm.setCollapseshow("NO");
		
		request.setAttribute("rform", reportePatenteFechaForm);
		request.setAttribute("listfaena", listfaena);
		request.setAttribute("listpatentes", listpatentes);				
		
		return "reporteresumendescarga";
	}
	
	//procesar 
	@RequestMapping(value = "/reporteresumendescarga" , method = RequestMethod.POST)
	public String processForm(@Valid ReportePatenteFechaForm reportePatenteFechaForm, BindingResult result,
			Map model,HttpServletRequest request) {
		
		if(request.getSession().getAttribute("usuario")==null)
		{
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
		
		String patente = reportePatenteFechaForm.getPatente();
		String desde = reportePatenteFechaForm.getFechaDesde()+" "+reportePatenteFechaForm.getHoraDesde();
		String hasta = reportePatenteFechaForm.getFechaHasta()+" "+reportePatenteFechaForm.getHoraHasta();
		String faena = reportePatenteFechaForm.getFaena();		
				
		String	valor = ""; //patente
		String valor02 = ""; // faena 
		String sw = "";
		
						
		if(patente.equals("")){
			
			valor02 = faena;
			sw = "1";
					
		}
		if(!patente.equals("")){
			
			valor = patente;
			valor02 = "0";
			sw = "0";
						
		}
	
		List<VehiculoActive> rlist = new ArrayList<VehiculoActive>();
		
		rlist = ReporteService.findResumenDescarga(rutcliente, desde, hasta, patente);
		String callapse = "";
		if(rlist.size()==0){			
			String mensaje = "No hay resultado";
			request.setAttribute("mensaje", mensaje);
			callapse = "NO";
		}		
							
		Date fecha= new Date();
		//camiones
		// tipo c camion, m, maquina
		List<PatenteGPSActive> listpatentes = ReporteService.findPatentesXClienteFechaTipo(usuLogin, clave, fecha, "c", usuario.getClienterut());
		//List<PatenteGPSActive> listpatentes = ReporteService.findPatentesXClienteFecha(usuLogin, clave, fecha);
		
		
		List<FaenaActive> listfaena = ReporteService.findFaenaXCliente(usuLogincliente, clavecliente);
		
		Grupo grupo =  ReporteService.findGrupoById(faena);						
				
		if(callapse.equals("NO")){
			reportePatenteFechaForm.setCollapseshow("NO");
		}else{
			reportePatenteFechaForm.setCollapseshow("SI");
		}
		
		request.setAttribute("listfaena", listfaena);		
		request.setAttribute("listpatentes", listpatentes);
		request.setAttribute("rlist", rlist);		
		request.setAttribute("rform", reportePatenteFechaForm);
		request.setAttribute("grupo", grupo);		
		//request.setAttribute("faenaTodas", faenaTodas);
	
		return "reporteresumendescarga";
		
	}
	
}