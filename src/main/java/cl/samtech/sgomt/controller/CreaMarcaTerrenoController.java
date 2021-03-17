package cl.samtech.sgomt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cl.samtech.sgomt.form.GeocercasForm;
import cl.samtech.sgomt.form.MarcaterrenoForm;
import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.ListadoGeocercasActive;
import cl.samtech.sgomt.object.ListadoMarcaTerrenoActive;
import cl.samtech.sgomt.object.MapaUltimoGPS;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.AdministracionService;
import cl.samtech.sgomt.service.ReporteService;
import cl.samtech.sgomt.service.UsuarioService;

@Controller
public class CreaMarcaTerrenoController {	
	@RequestMapping(value = "/creamarcaterreno", method = RequestMethod.GET)
	public ModelAndView showForm(Map model,HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
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
			usuario.setUrlservlet(request.getServletPath());
		} catch (Exception e) {			
			return new ModelAndView("home");			
		}
		
		//validad menu
		if(!UsuarioService.validarMenuUsuario(usuario, "listadomarcaterreno.html")){		
			return new ModelAndView("home");		
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuario, "listadomarcaterreno.html");
		request.setAttribute("b", b);		
		
		MapaUltimoGPS mc = new MapaUltimoGPS();
		mc.setLat("-22.7368623");
		mc.setLon("-69.3352093");
		
		List<ListadoGeocercasActive>  mlistGeo = new ArrayList<ListadoGeocercasActive>();
	    mlistGeo = ReporteService.findGeocercaByIdPg( usuLogincliente, clavecliente, "0");	

	    		
		List<ListadoMarcaTerrenoActive>  marcas = new ArrayList<ListadoMarcaTerrenoActive>();
		marcas = AdministracionService.findListadoMarcasTerreno( usuLogincliente, clavecliente,0);
		
	    String origen = "crear";
	    
	    List<ListadoIconos>  lIconos = new ArrayList<ListadoIconos>();
		 lIconos = AdministracionService.findListadoIconos( usuLogincliente, clavecliente);
		
	    request.setAttribute("lIconos", lIconos);	     
		request.setAttribute("origen", origen);		
	    request.setAttribute("marcas", marcas);
	    request.setAttribute("mlistGeo", mlistGeo);
	    request.setAttribute("mc", mc);
		
		return new ModelAndView("creamarcaterreno");
	}
	
	
	@RequestMapping(value = "/editamarcaterreno", method = RequestMethod.GET)
	public ModelAndView showForm1(Map model,HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
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
		if(!UsuarioService.validarMenuUsuario(usuario, "listadomarcaterreno.html")){		
			return new ModelAndView("home");				
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuario, "listadomarcaterreno.html");
		request.setAttribute("b", b);		
		
		MapaUltimoGPS mc = new MapaUltimoGPS();
		mc.setLat("-22.7368623");
		mc.setLon("-69.3352093");
		
		List<ListadoGeocercasActive>  mlistGeo = new ArrayList<ListadoGeocercasActive>();
	    mlistGeo = ReporteService.findGeocercaByIdPg( usuLogincliente, clavecliente, "0");	
	    
	    Integer id= Integer.parseInt(request.getParameter("id"));
	     
	     if(id != null){
	    	 
	    	 List<ListadoMarcaTerrenoActive>  mlistMarcasId = new ArrayList<ListadoMarcaTerrenoActive>();
	    	 mlistMarcasId = AdministracionService.findListadoMarcasTerreno( usuLogincliente, clavecliente,(Integer)id);
	    	 ListadoMarcaTerrenoActive mlistMarcasId2 = mlistMarcasId.get(0);
	    	 request.setAttribute("marcasxid", mlistMarcasId2);
	     }

	    
		
	     List<ListadoMarcaTerrenoActive>  marcas = new ArrayList<ListadoMarcaTerrenoActive>();
		 marcas = AdministracionService.findListadoMarcasTerreno( usuLogincliente, clavecliente,0);
		 
		 List<ListadoIconos>  lIconos = new ArrayList<ListadoIconos>();
		 lIconos = AdministracionService.findListadoIconos( usuLogincliente, clavecliente);
		
	    String origen = "editar";
	     
	    request.setAttribute("lIconos", lIconos);		
		request.setAttribute("origen", origen);		
	    request.setAttribute("marcas", marcas);
	    request.setAttribute("mlistGeo", mlistGeo);
	    request.setAttribute("mc", mc);
		
		return new ModelAndView("creamarcaterreno");
	}
	
	
	@RequestMapping(value = "/creamarcaterreno" , method = RequestMethod.POST)
	public String processForm(@Valid MarcaterrenoForm GForm, BindingResult result,Map model,HttpServletRequest request) {
		
		if (result.hasErrors()) {
			return "home";
		}
				
		UsuarioLogin usuario=(UsuarioLogin)request.getSession().getAttribute("usuario");		
		AdministracionService.saveMarcaterreno(usuario.getUsername(),GForm);		

		return "redirect:/listadomarcaterreno.html";
		
	}

}
