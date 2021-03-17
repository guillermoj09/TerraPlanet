package cl.samtech.sgomt.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cl.samtech.sgomt.form.ReportePatenteFechaForm;
import cl.samtech.sgomt.model.UsuarioHistorial;
import cl.samtech.sgomt.model.UsuarioSession;
import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.Fechas;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.UsuarioService;
import cl.samtech.sgomt.service.UtilServicio;

@Controller
public class ReporteLoginUsuarioDetalleController {
	@RequestMapping(value = "/reporteloginusuariodetalle", method = RequestMethod.GET)
	public String showForm(Map model,HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
			 return "home";
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
			
			return "home";
			
		}		
		//validad menu, guarda historial de menu de la session
		if(!UsuarioService.validarMenuUsuario(usuario, "/reporteloginusuario")){		
			return "home";			
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuario, "/reporteloginusuario");	
		
		String id=request.getParameter("id");
		
		List<UsuarioHistorial> rlist =  new ArrayList<UsuarioHistorial>();	
		
		rlist = UsuarioService.findUsuarioHistorialDetalle(id);
		
		request.setAttribute("b", b);
		request.setAttribute("rlist", rlist);
		
		return "reporteloginusuariodetalle";
	}
	
}