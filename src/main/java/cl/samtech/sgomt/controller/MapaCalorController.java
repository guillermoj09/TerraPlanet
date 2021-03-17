package cl.samtech.sgomt.controller;

import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import cl.samtech.sgomt.form.MapaCalorForm;
import cl.samtech.sgomt.form.ReportePatenteFechaForm;
import cl.samtech.sgomt.model.Cliente;
import cl.samtech.sgomt.model.Grupo;
import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.CarguioActive;
import cl.samtech.sgomt.object.FaenaActive;
import cl.samtech.sgomt.object.Fechas;
import cl.samtech.sgomt.object.GeocercasActive;
import cl.samtech.sgomt.object.ListadoGeocercasActive;
import cl.samtech.sgomt.object.MapaCalorActive;
import cl.samtech.sgomt.object.ReporteIndicadorOptimoCargaActive;
import cl.samtech.sgomt.object.ReporteTiempoCarguioActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.ReporteService;
import cl.samtech.sgomt.service.UsuarioService;
import cl.samtech.sgomt.service.UtilServicio;

@Controller
public class MapaCalorController {
	@RequestMapping(value = "/mapacalor", method = RequestMethod.GET)
	public String showForm(Map model,HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
			return "home";
		}

		//Relaciono el form
		MapaCalorForm mapaCalorForm = new MapaCalorForm();
		model.put("mapaCalorForm", mapaCalorForm);

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
		//validad menu
		if(!UsuarioService.validarMenuUsuario(usuario, request.getServletPath())){		
			return "home";			
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuario, request.getServletPath());
		request.setAttribute("b", b);		

		Fechas fechas = UtilServicio.getFechasConfDiaParam("0", "dd/MM/yyyy");// "-1"

		List<FaenaActive> listfaena = ReporteService.findFaenaXCliente(usuLogincliente, clavecliente);


		//List<MapaCalorActive>  mlist = ReporteService.findMapaCalor("0", "", "", cliente, "0", "0");
		//FAENA EN 0, LA FECHAS SE CALCULA TAMBIEN EN EL SERVICIO FINDMAPACALOR, SI SE ENVIAN VACIAS "", "" PERO RESTA UN SOLO DIA
		//List<MapaCalorActive>  mlist = ReporteService.findMapaCalor("0", fechas.getFechatimein(), fechas.getFechatimefin(), cliente, "0", "0");

		MapaCalorActive mc = new MapaCalorActive();

		try {

    	   // mc = mlist.get(0);

    	    mc.setLat("-24.2692571");
    		mc.setLon("-69.0809277");

       } catch (Exception e) {

		mc.setLat("-24.2692571");
		mc.setLon("-69.0809277");

       }

		Grupo grupo =  ReporteService.findGrupoById("0");

		String faenaTodas = "NO";


		mapaCalorForm.setFechaDesde(fechas.getFechain());
		mapaCalorForm.setFechaHasta(fechas.getFechafin());


		DateFormat formatteri2 = new SimpleDateFormat("HH:mm");
		String horaDesde = formatteri2.format(fechas.getCalendarin().getTime());
		String horaHasta = formatteri2.format(fechas.getCalendarfin().getTime());

		mapaCalorForm.setSw1("0");

		mapaCalorForm.setHoraDesde(horaDesde);
		mapaCalorForm.setHoraHasta(horaHasta);

		mapaCalorForm.setCollapseshow("NO");

		request.setAttribute("listfaena", listfaena);
		request.setAttribute("mc", mc);
		//request.setAttribute("mlist", mlist);
		request.setAttribute("grupo", grupo);
		request.setAttribute("faenaTodas", faenaTodas);
		request.setAttribute("rform", mapaCalorForm);


		return "mapacalor";
	}

	//procesar
	@RequestMapping(value = "/mapacalor" , method = RequestMethod.POST)
	public String processForm(@Valid MapaCalorForm mapaCalorForm, BindingResult result,
			Map model,HttpServletRequest request) {


		if (result.hasErrors()) {
			return "home";
		}

		//me traigo los valores del formulario
		mapaCalorForm = (MapaCalorForm) model.get("mapaCalorForm");

		String faena = mapaCalorForm.getFaena();
		//String desde = mapaCalorForm.getFechaDesde()+" "+mapaCalorForm.getHoraDesde();
		String desde = mapaCalorForm.getFechaDesde()+" "+"00:00";
		String hasta = mapaCalorForm.getFechaHasta()+" "+mapaCalorForm.getHoraHasta();

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

		}
		//validad menu
		if(!UsuarioService.validarMenuUsuario(usuario, request.getServletPath())){		
			return "home";			
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuario, request.getServletPath());
		request.setAttribute("b", b);		

		List<FaenaActive> listfaena = ReporteService.findFaenaXCliente(usuLogincliente, clavecliente);
		

		String sw2 = "0";

		if(faena.equals("0")){

			sw2 = "0";

		}
		if(faena.equals("1")){

			sw2 = "1";

		}

		String faenaTodas = "";
		if(faena.equals("0")){


			faenaTodas = "SI";
		}


		String sw1 = mapaCalorForm.getSw1();
		//String  // mapaCalorForm.getSw2();

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

		long dias =UtilServicio.getDiferenciaday(fecdesde, fechasta) ;

		List<MapaCalorActive>  mlist = new ArrayList<MapaCalorActive>();

		if(dias < 9999){//cambiar a 8

		mlist = ReporteService.findMapaCalor(faena, desde, hasta, usuLogincliente, sw1, sw2);

		if(mlist.size()==0){

			String mensaje = "No hay resultado";
			request.setAttribute("mensaje", mensaje);
		}

		}else{

			String mensaje = "Rango fecha excede el permitido, maximo 7 dias";
			request.setAttribute("mensaje", mensaje);

		}

		//if (rlist != null && !rlist.isEmpty()) { }

		MapaCalorActive mc = new MapaCalorActive();

       try {

    	    mc = mlist.get(0);

       } catch (Exception e) {

		mc.setLat("-24.2692571");
		mc.setLon("-69.0809277");

       }

       Grupo grupo =  ReporteService.findGrupoById(faena);

       List<ListadoGeocercasActive>  mlistGeo = new ArrayList<ListadoGeocercasActive>();
	   mlistGeo = ReporteService.findGeocercaByIdPg( usuLogincliente, clavecliente, "0");
	   
	    //Ubico geocerca de proyecto central para hacer zoom y center dinamico
	     List<ListadoGeocercasActive>  mlistGeoId = new ArrayList<ListadoGeocercasActive>(); 
	     mlistGeoId = ReporteService.findGeocercaByIdPg( usuLogincliente, clavecliente, "107");
	     ListadoGeocercasActive mlistGeoId2 = new ListadoGeocercasActive(); 
	     try {
	    	  mlistGeoId2 = mlistGeoId.get(0);			
		} catch (Exception e) {			
			 mlistGeoId2 = null;		
		}

        mapaCalorForm.setCollapseshow("SI");

        request.setAttribute("listfaena", listfaena);
		request.setAttribute("mc", mc);
		request.setAttribute("mlist", mlist);
		request.setAttribute("mlistGeo", mlistGeo);
		request.setAttribute("rform", mapaCalorForm);
		request.setAttribute("grupo", grupo);
		request.setAttribute("faenaTodas", faenaTodas);
		request.setAttribute("mlistGeoId2", mlistGeoId2);

		return "mapacalor";

	}

}
