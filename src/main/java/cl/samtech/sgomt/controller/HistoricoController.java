package cl.samtech.sgomt.controller;

import java.text.DateFormat;
import java.text.ParseException;
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
import cl.samtech.sgomt.form.HistoricoForm;
import cl.samtech.sgomt.model.Vehiculo;
import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.EventActive;
import cl.samtech.sgomt.object.Fechas;
import cl.samtech.sgomt.object.HistoricoActive;
import cl.samtech.sgomt.object.MapaCalorActive;
import cl.samtech.sgomt.object.PatenteGPSActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.DispositivoService;
import cl.samtech.sgomt.service.GestionService;
import cl.samtech.sgomt.service.ReporteService;
import cl.samtech.sgomt.service.UsuarioService;
import cl.samtech.sgomt.service.UtilServicio;;

@Controller
public class HistoricoController {
	@RequestMapping(value = "/historico", method = RequestMethod.GET)
	public String showForm(Map model,HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
			 return "home";
		}
		
		//Relaciono el form
		HistoricoForm historicoForm = new HistoricoForm();
		model.put("historicoForm", historicoForm);
				
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
		//validad menu, guarda historial de menu de la session
		if(!UsuarioService.validarMenuUsuario(usuario, request.getServletPath())){		
			return "home";			
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuario, request.getServletPath());		
		
		
				
		Date fecha= new Date();
		
		Fechas fechas = UtilServicio.getFechasConfDiaParam("0", "dd/MM/yyyy");
		
		
		historicoForm.setFechaDesde(fechas.getFechain());
		historicoForm.setFechaHasta(fechas.getFechafin());
		
		DateFormat formatteri2 = new SimpleDateFormat("HH:mm");
		String horaDesde = formatteri2.format(fechas.getCalendarin().getTime());
		String horaHasta = formatteri2.format(fechas.getCalendarfin().getTime());
		
		historicoForm.setHoraDesde(horaDesde);
		historicoForm.setHoraHasta(horaHasta);
				
				
		List<PatenteGPSActive> listPatentes = ReporteService.findPatentesXClienteFecha(cliente, clave, fecha, usuario.getClienterut());
		List<EventActive> listEventos = ReporteService.listaEventos();
				
		request.setAttribute("b", b);		
		request.setAttribute("listPatentes", listPatentes);
		request.setAttribute("listEventos", listEventos);
		request.setAttribute("rform", historicoForm);
		
		return "historico";
	}
	
	
		
	//procesar 
		@RequestMapping(value = "/historico" , method = RequestMethod.POST)
		public String processForm(@Valid HistoricoForm historicoForm, BindingResult result,Map model,HttpServletRequest request) {
			
			if (result.hasErrors()) {
				return "home";
			}
			
			//me traigo los valores del formulario
			historicoForm = (HistoricoForm) model.get("historicoForm");
			
			String patente = historicoForm.getPatente();			
			String desde = historicoForm.getFechaDesde();
			String hasta = historicoForm.getFechaHasta();			
			String velocidad = historicoForm.getVelocidad();
			String horaDesde = historicoForm.getHoraDesde();
			String horaHasta = historicoForm.getHoraHasta();
			String evento = historicoForm.getEvento();
			Date fecha= new Date();

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
				
				return "home";
				
			};
			//validad menu
			if(!UsuarioService.validarMenuUsuario(usuario, request.getServletPath())){		
				return "home";			
			}
			//mando barra menu a la vista
			BarraMenuActive b = UsuarioService.barraMenu(usuario, request.getServletPath());		
			
			List<MapaCalorActive>  mlist = new ArrayList<MapaCalorActive>();
			List<PatenteGPSActive> listPatentes =  new ArrayList<PatenteGPSActive>();
			List<HistoricoActive> listHistorico =  new ArrayList<HistoricoActive>();
			
			//hacer calculo diferencia de dia para validar
			DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy"); //dd/MM/yyyy
			Date fecdesde= new Date();
			Date fechasta= new Date();
			try {
				fecdesde = formatteri.parse(desde);
				fechasta=  formatteri.parse(hasta);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String callapse = "";
			long dias =UtilServicio.getDiferenciaday(fecdesde, fechasta) ;
			if(dias <= 31){
							
							
			 Vehiculo vehiculo = DispositivoService.findVehiculoByPatente(patente);						
			listHistorico = GestionService.findHistoricoPG2(desde+" "+horaDesde, hasta+" "+horaHasta, patente, velocidad, vehiculo, evento);
			
			if(listHistorico.size()==0){
				
				String mensaje = "No hay resultado";
				request.setAttribute("mensaje", mensaje);
				callapse = "NO";
			}
			
			}else{

			String mensaje = "Rango fecha excede el permitido, maximo 31 dias";
			request.setAttribute("mensaje", mensaje);
			callapse = "NO";

			}
			
			if(callapse.equals("NO")){				
			}else{
				historicoForm.setCollapseshow("off");
			}
			
			listPatentes = ReporteService.findPatentesXClienteFecha(cliente, clave, fecha, usuario.getClienterut());
			List<EventActive> listEventos = ReporteService.listaEventos();
			
			request.setAttribute("b", b);		
			request.setAttribute("listHistorico", listHistorico);
			request.setAttribute("listPatentes", listPatentes);
			request.setAttribute("listEventos", listEventos);
			request.setAttribute("rform", historicoForm);		
						
			return "historico";
			
		}

}