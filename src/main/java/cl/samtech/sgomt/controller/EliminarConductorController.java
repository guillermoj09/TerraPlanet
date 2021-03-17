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
import cl.samtech.sgomt.object.ListadoGeocercasActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.AdministracionService;
import cl.samtech.sgomt.service.DispositivoService;
import cl.samtech.sgomt.service.UsuarioService;


@Controller
public class EliminarConductorController {
	
			
	@RequestMapping(value = "/eliminarconductor", method = RequestMethod.GET)
	public String showForm1(Map model,HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
			 return "home";
		}
		UsuarioLogin usuario=(UsuarioLogin)request.getSession().getAttribute("usuario");
		usuario.setUrlservlet(request.getServletPath());
		//validad menu
		if(!UsuarioService.validarMenuUsuario(usuario, request.getServletPath())){		
			return "home";			
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuario, request.getServletPath());
		request.setAttribute("b", b);		
		
		String rut=request.getParameter("rut");
		
		DispositivoService.eliminarConductor(rut);
					
		return  "redirect:/consultarconductor";
		
	}
		
	

}
