package cl.samtech.sgomt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.ListadoMarcaTerrenoActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.AdministracionService;
import cl.samtech.sgomt.service.UsuarioService;

@Controller
public class ListadoMarcaTerrenoController {
	@RequestMapping(value = "/listadomarcaterreno", method = RequestMethod.GET)
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
		if(!UsuarioService.validarMenuUsuario(usuario, request.getServletPath())){		
			return new ModelAndView("home");			
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuario, request.getServletPath());
		request.setAttribute("b", b);		
		
		List<ListadoMarcaTerrenoActive>  marcas = new ArrayList<ListadoMarcaTerrenoActive>();
		marcas = AdministracionService.findListadoMarcasTerreno( usuLogincliente, clavecliente,0);
	    request.setAttribute("marcas", marcas);
		
		
		return new ModelAndView("listadomarcaterreno");
	}
	
	

	@RequestMapping(value = "/eliminamarcaterreno", method = RequestMethod.GET)
	public String showForm1(Map model,HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
			 return "home";
		}
		
		String id=request.getParameter("id_marca").toLowerCase();
		
		UsuarioLogin usuario=(UsuarioLogin)request.getSession().getAttribute("usuario");		
		AdministracionService.deleteMarcaTerreno(usuario.getUsername(), Integer.valueOf(id));		
	
		
		return  "redirect:/listadomarcaterreno.html";
	}

}
