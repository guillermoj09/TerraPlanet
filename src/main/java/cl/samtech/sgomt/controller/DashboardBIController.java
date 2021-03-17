package cl.samtech.sgomt.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cl.samtech.sgomt.form.HistoricoForm;
import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.UsuarioService;

@Controller
public class DashboardBIController {
		
	@RequestMapping(value = "/dashboardbi", method = RequestMethod.GET)
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
		//validad menu
		if(!UsuarioService.validarMenuUsuario(usuario, request.getServletPath())){		
			return "home";			
		}
		//mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuario, request.getServletPath());		
		
		//String iframe = "https://app.powerbi.com/reportEmbed?reportId=5da38fce-0425-4adb-a81a-748b1d87efe5&autoAuth=true&ctid=800848b6-54e6-473a-9e41-5f170af0e0dd";
		String iframe = "https://app.powerbi.com/view?r=eyJrIjoiMDQ2NGE5MGQtZDhlOC00MDM0LWFhMGItMmNjOWM3ZWI0MTU4IiwidCI6IjgwMDg0OGI2LTU0ZTYtNDczYS05ZTQxLTVmMTcwYWYwZTBkZCIsImMiOjR9";
									
		request.setAttribute("b", b);	
		request.setAttribute("iframe", iframe);		
		
		return "dashboardbi";
	}

}

//tokken
//ejemplo como print en javascriop teniendo ya el tokken
//https://community.powerbi.com/t5/Developer/How-To-Get-embed-token-using-Get-Post-only/td-p/294475

//obtener tokken con jdk java
//https://github.com/m-moris/PowerBI-SDK-Java
