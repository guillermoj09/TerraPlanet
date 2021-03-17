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
import cl.samtech.sgomt.model.VehiculoDevice;
import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.DetalleXViajeActive;
import cl.samtech.sgomt.object.DiagramaCicloGraficoActive;
import cl.samtech.sgomt.object.DiagramaCicloTablaActive;
import cl.samtech.sgomt.object.Fechas;
import cl.samtech.sgomt.object.LinkDataArrayActive;
import cl.samtech.sgomt.object.ListadoGeocercasActive;
import cl.samtech.sgomt.object.NodeDataArrayActive;
import cl.samtech.sgomt.object.PatenteGPSActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.AdministracionService;
import cl.samtech.sgomt.service.ReporteService;
import cl.samtech.sgomt.service.UsuarioService;
import cl.samtech.sgomt.service.UtilServicio;

@Controller
public class DiagramaCicloController {

	@RequestMapping(value = "/diagramaciclo", method = RequestMethod.GET)
	public String showForm(Map model,HttpServletRequest request) {
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
		
		//Relaciono el form
		ReportePatenteFechaForm reportePatenteFechaForm = new ReportePatenteFechaForm();
		model.put("reportePatenteFechaForm", reportePatenteFechaForm);
		Fechas fechas = UtilServicio.getFechasConfDiaParam("0", "dd/MM/yyyy");
		
		reportePatenteFechaForm.setFechaDesde(fechas.getFechain());
		reportePatenteFechaForm.setFechaHasta(fechas.getFechafin());
		
		DateFormat formatteri2 = new SimpleDateFormat("HH:mm");
		String horaDesde = formatteri2.format(fechas.getCalendarin().getTime());
		String horaHasta = formatteri2.format(fechas.getCalendarfin().getTime());
		
		reportePatenteFechaForm.setHoraDesde(horaDesde);
		reportePatenteFechaForm.setHoraHasta(horaHasta);
		
		reportePatenteFechaForm.setCollapseshow("NO");
				
		List<ListadoGeocercasActive>  geocercas = new ArrayList<ListadoGeocercasActive>();
		geocercas = AdministracionService.findListadoGeocercos( usuLogincliente, clavecliente);
		
		Date fecha= new Date();
		List<PatenteGPSActive> listpatentes = ReporteService.findPatentesXClienteFecha(usuLogin, clave, fecha, usuario.getClienterut());
		
		request.setAttribute("rform", reportePatenteFechaForm);
		request.setAttribute("listpatentes", listpatentes);
	    request.setAttribute("geocercas", geocercas);
	    request.setAttribute("geocercas2", geocercas);
		
		return"diagramaciclo";
	}
	
	//procesar 
		@RequestMapping(value = "/diagramaciclo" , method = RequestMethod.POST)
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
			
			Date fecha= new Date();
			String todo ="";										
			String desde = reportePatenteFechaForm.getFechaDesde()+" "+reportePatenteFechaForm.getHoraDesde();
			String hasta = reportePatenteFechaForm.getFechaHasta()+" "+reportePatenteFechaForm.getHoraHasta();
			String geo = reportePatenteFechaForm.getGeo();
			String geo2 = reportePatenteFechaForm.getGeo2();
			String patente = reportePatenteFechaForm.getPatente();
			
			if(geo.equals("") && geo2.equals("") && patente.equals("")){
				
				todo = "S";
				
			}	
							
			DiagramaCicloGraficoActive obj = ReporteService.findDiagramaCiclo(desde, hasta, geo, geo2, patente, todo);
			//List<DiagramaCicloGraficoActive> rlistall = ReporteService.findDiagramaCiclo(desde, hasta, geo);
			String callapse = "";
			
			try {
				obj.getDiagramaCicloTablaActive().size();	
			} catch (Exception e) {
				String mensaje = "No hay resultado";
				request.setAttribute("mensaje", mensaje);
				callapse = "NO";
				
			}
			
			
			/*if(obj.getDiagramaCicloTablaActive().size()==0){			
				String mensaje = "No hay resultado";
				request.setAttribute("mensaje", mensaje);
				callapse = "NO";
			}*/
			
			
			List<DiagramaCicloTablaActive> rlist = obj.getDiagramaCicloTablaActive();
			List<NodeDataArrayActive> nlist = obj.getNodeDataArray();
			List<LinkDataArrayActive> llist = obj.getLinkDataArray();
			
			
			if(callapse.equals("NO")){
				reportePatenteFechaForm.setCollapseshow("NO");
			}else{
				reportePatenteFechaForm.setCollapseshow("SI");
			}
						
			List<ListadoGeocercasActive>  geocercas = new ArrayList<ListadoGeocercasActive>();
			geocercas = AdministracionService.findListadoGeocercos( usuLogincliente, clavecliente);
						
			List<PatenteGPSActive> listpatentes = ReporteService.findPatentesXClienteFecha(usuLogin, clave, fecha, usuario.getClienterut());

			request.setAttribute("rlist", rlist);	
			request.setAttribute("nlist", nlist);	
			request.setAttribute("llist", llist);	
			request.setAttribute("rform", reportePatenteFechaForm);
			request.setAttribute("geocercas", geocercas);
			request.setAttribute("geocercas2", geocercas);
			request.setAttribute("listpatentes", listpatentes);
					
			return "diagramaciclo";
			
		}
	
	
}