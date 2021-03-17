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
import cl.samtech.sgomt.object.ReporteInformeRendimientoActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.GestionService;
import cl.samtech.sgomt.service.ReporteService;
import cl.samtech.sgomt.service.UsuarioService;
import cl.samtech.sgomt.service.UtilServicio;

@Controller
public class ReporteInformeRendimientoController {
	@RequestMapping(value = "/reporteinformerendimiento", method = RequestMethod.GET)
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
		
		List<PatenteGPSActive> listpatentes = ReporteService.findPatentesXClienteFecha(usuLogin, clave, fecha, usuario.getClienterut());						
				
		List<FaenaActive> listfaena = ReporteService.findFaenaXCliente(usuLogincliente, clavecliente);		
				
		reportePatenteFechaForm.setFechaDesde(fechas.getFechain());
		reportePatenteFechaForm.setFechaHasta(fechas.getFechafin());
		
		DateFormat formatteri2 = new SimpleDateFormat("HH:mm");
		String horaDesde = formatteri2.format(fechas.getCalendarin().getTime());
		String horaHasta = formatteri2.format(fechas.getCalendarfin().getTime());
		
		reportePatenteFechaForm.setHoraDesde(horaDesde);
		reportePatenteFechaForm.setHoraHasta(horaHasta);
			
		reportePatenteFechaForm.setCollapseshow("NO");
		reportePatenteFechaForm.setSw(true);
		
		ReporteInformeRendimientoActive ri = new ReporteInformeRendimientoActive();	
		ri.setTporecorrido("0");
		ri.setHorometro("0");
		ri.setKmrecorrido("0");
		ri.setLstconsumidos("0");
		ri.setRendimiento("0");
		ri.setRendimientolthr("0");
		ri.setVelocidapromedio("0");
		
		request.setAttribute("r", ri);	
		request.setAttribute("rform", reportePatenteFechaForm);
		request.setAttribute("listfaena", listfaena);
		request.setAttribute("listpatentes", listpatentes);
					
		return "reporteinformerendimiento";
	}
	
	//procesar 
	@RequestMapping(value = "/reporteinformerendimiento" , method = RequestMethod.POST)
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
		try {
			usuLogin  = usuario.getUsername();			
			clave  = usuario.getPassword();
			usuLogincliente  = usuario.getCliUsuSamtech();
			clavecliente  = usuario.getCliPassSamtech();			
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
		Boolean sw  = reportePatenteFechaForm.getSw();
		String sw1 = "0";
		
		if(sw == null){
			
			sw = false;
		}
		
		String tipo = "";
		String valor = "";
				
		if(!patente.equals("")){
			
			faena = "";			
			sw1 = "1";
			
		}

		List<ReporteInformeRendimientoActive> rlist = new ArrayList<ReporteInformeRendimientoActive>();
	
		rlist = GestionService.findInformeRendimientoPG(usuLogincliente, desde, hasta,patente, faena , sw1);
		String callapse = "";
		if(rlist.size()==0){			
			String mensaje = "No hay resultado";
			request.setAttribute("mensaje", mensaje);
			callapse = "NO";
		}
			
		ReporteInformeRendimientoActive ri = new ReporteInformeRendimientoActive();
		
		try {
			 ri = rlist.get(0);
		} catch (Exception e) {
			ri.setTporecorrido("0");
			ri.setHorometro("0");
			ri.setKmrecorrido("0");
			ri.setLstconsumidos("0");
			ri.setRendimiento("0");
			ri.setRendimientolthr("0");
			ri.setVelocidapromedio("0");
		}
								
		Date fecha= new Date();
		List<PatenteGPSActive> listpatentes = ReporteService.findPatentesXClienteFecha(usuLogin, clave, fecha, usuario.getClienterut());
				
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
		request.setAttribute("r", ri);	
		request.setAttribute("rform", reportePatenteFechaForm);
		request.setAttribute("grupo", grupo);
		
		return "reporteinformerendimiento";
		
	}
	
}