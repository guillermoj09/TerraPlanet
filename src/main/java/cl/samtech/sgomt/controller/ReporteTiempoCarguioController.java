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
import cl.samtech.sgomt.model.Tag;
import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.CarguioGraficoActive;
import cl.samtech.sgomt.object.FaenaActive;
import cl.samtech.sgomt.object.Fechas;
import cl.samtech.sgomt.object.OperacionesActive;
import cl.samtech.sgomt.object.PatenteGPSActive;
import cl.samtech.sgomt.object.ReporteDescargaActive;
import cl.samtech.sgomt.object.ReporteTiempoCarguioActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.ReporteService;
import cl.samtech.sgomt.service.UsuarioService;
import cl.samtech.sgomt.service.UtilServicio;

@Controller
//@RequestMapping("editarusuario.html")
public class ReporteTiempoCarguioController {
	@RequestMapping(value = "/reportetiempocarguio", method = RequestMethod.GET)
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
		//validad menu
		if(!UsuarioService.validarMenuUsuario(usuario, request.getServletPath())){		
			return "home";			
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuario, request.getServletPath());
		request.setAttribute("b", b);		
		
		Date fecha= new Date();
		//camiones c, maquinas m
		List<PatenteGPSActive> listpatentes = ReporteService.findPatentesXClienteFechaTipo(usuLogin, clave, fecha, "m", usuario.getClienterut());				
				
		Fechas fechas = UtilServicio.getFechasConfDiaParam("0", "dd/MM/yyyy");
		
		List<FaenaActive> listfaena = ReporteService.findFaenaXCliente(usuLogincliente, clavecliente);		
		
		reportePatenteFechaForm.setFechaDesde(fechas.getFechain());
		reportePatenteFechaForm.setFechaHasta(fechas.getFechafin());
		
		DateFormat formatteri2 = new SimpleDateFormat("HH:mm");
		String horaDesde = formatteri2.format(fechas.getCalendarin().getTime());
		String horaHasta = formatteri2.format(fechas.getCalendarfin().getTime());
		
		reportePatenteFechaForm.setHoraDesde(horaDesde);
		reportePatenteFechaForm.setHoraHasta(horaHasta);
		
		reportePatenteFechaForm.setCollapseshow("NO");
		
		request.setAttribute("rform", reportePatenteFechaForm);
		request.setAttribute("listfaena", listfaena);
		request.setAttribute("listpatentes", listpatentes);			
	
		return "reportetiempocarguio";
	}
	
	//procesar 
	@RequestMapping(value = "/reportetiempocarguio" , method = RequestMethod.POST)
	public String processForm(@Valid ReportePatenteFechaForm reportePatenteFechaForm, BindingResult result,
			Map model,HttpServletRequest request) {
		
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
		
		Date fecha= new Date();
		
				
		//me traigo los valores del formulario
		reportePatenteFechaForm = (ReportePatenteFechaForm) model.get("reportePatenteFechaForm");
		
		String patentes = reportePatenteFechaForm.getPatente();
		String desde = reportePatenteFechaForm.getFechaDesde()+" "+reportePatenteFechaForm.getHoraDesde();
		String hasta = reportePatenteFechaForm.getFechaHasta()+" "+reportePatenteFechaForm.getHoraHasta();
		String faena = reportePatenteFechaForm.getFaena();	
		
		//List<Tag> tagslist = new ArrayList<Tag>();
		
		/*List<PatenteGPSActive> listpatentessi = new ArrayList<PatenteGPSActive>(); 
		if(reportePatenteFechaForm.getPatente()!=null){
		String[] parts=reportePatenteFechaForm.getPatente().split(",");
			for(int i = 0; i < parts.length; i++){	
				PatenteGPSActive p = new PatenteGPSActive();
				
				p.setVehPatenteVehiculo(parts[i]);
				
				listpatentessi.add(p);
						
			}
		}*/
		
		List<PatenteGPSActive> listpatentes = ReporteService.findPatentesXClienteFechaTipoNotIn(usuLogin, clave, fecha, "m", patentes, "N", usuario.getClienterut());
		List<PatenteGPSActive> listpatentessi = ReporteService.findPatentesXClienteFechaTipoNotIn(usuLogin, clave, fecha, "m", patentes, "S", usuario.getClienterut());
		//List<PatenteGPSActive> listpatentes = ReporteService.findPatentesXClienteFechaTipo(usuLogin, clave, fecha, "c");
		
				
		List<ReporteTiempoCarguioActive> rlist = new ArrayList<ReporteTiempoCarguioActive>();
		List<CarguioGraficoActive> clist = new ArrayList<CarguioGraficoActive>();
		List<OperacionesActive> olist = new ArrayList<OperacionesActive>();
		
		
		rlist = ReporteService.findTiempoCarguio(patentes,faena, desde, hasta, rutcliente);
		
		//GRAFICO RADAR
		olist = ReporteService.findOperaciones();
		clist = ReporteService.findCarguioGrafico2(patentes, faena, desde, hasta, rutcliente, olist, rlist);
		
		String callapse = "";
		if(rlist.size()==0){
			
			String mensaje = "No hay resultado";
			request.setAttribute("mensaje", mensaje);	
			callapse = "NO";
		}
				
		if(callapse.equals("NO")){
			reportePatenteFechaForm.setCollapseshow("NO");
		}else{
			reportePatenteFechaForm.setCollapseshow("SI");
		}

		//List<FaenaActive> listfaena = ReporteService.findFaenaXCliente(usuLogincliente, clavecliente);	
		//Grupo grupo =  ReporteService.findGrupoById(faena);						
		
		//request.setAttribute("listfaena", listfaena);		
		request.setAttribute("listpatentes", listpatentes);
		request.setAttribute("listpatentessi", listpatentessi);
		request.setAttribute("rlist", rlist);								
		request.setAttribute("rform", reportePatenteFechaForm);
		//request.setAttribute("grupo", grupo);		
		
		//GRAFICO RADAR CARGUIO		
		request.setAttribute("clist", clist);
		request.setAttribute("olist", olist);
		
						
		return "reportetiempocarguio";
		
	}
	
}