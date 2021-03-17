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


@Controller
//@RequestMapping("editarusuario.html")
public class PageFrameController {
	@RequestMapping(value = "/pageframe", method = RequestMethod.GET)
	public ModelAndView showForm(Map model,HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
			 return new ModelAndView("home");
		}
		String url = "ggffg";		
		String id = request.getParameter("id");
		String parametros = "";
		String imgdescarga = "NO";
		
		// MAPAS
		if(id.equals("FaListadoFlota"))         
        {url = "maps/";parametros = "&faena=todas";}
		else if(id.equals("kml_generico_new"))         
        {
			url = "reportes/real_time/";
			imgdescarga = "SI";
			
			
        }
		else if(id.equals("maps/index"))         
        {url = "";parametros = "&id=Spence&numero=3&N=1060&alto=200";}	
         
		// GESTION FLOTA
		else if(id.equals("paginacion77_DM"))         
        {url = "reportes/historico/";}
		else if(id.equals("geo_cercas_todos_new"))         
        {url = "reportes/geo_cercas/clientes_parametrizados/";}
		
		else if(id.equals("alar_condicion"))         
        {url = "reportes/geo_cercas/alarmas_de_ruta/";}
		else if(id.equals("alar_rut"))         
        {url = "reportes/geo_cercas/alarmas_de_ruta/";}
		else if(id.equals("detenciones_sostenidas_Ver2"))         
        {url = "reportes/historico/";}
		else if(id.equals("sobre_extendidas_new2"))         
        {url = "reportes/historico/";}
		
		// ADMINISTRACION
		else if(id.equals("geoPoli2/index"))         
        {url = "mantenedores/";}
		else if(id.equals("listadoconductores"))         
        {url = "mantenedores/conductores/";}
		else if(id.equals("asigna-fae"))         
        {url = "ext-2.1/doc/layout_2/";}
		else if(id.equals("acciones_ibutton"))         
        {url = "mantenedores/geo_cercas/acciones/";}
		else if(id.equals("listado_faenas"))         
        {url = "mantenedores/faenas_3/";}
		else if(id.equals("marcas_de_terreno"))         
        {url = "mantenedores/marcas_de_terreno/";}
		else if(id.equals("geo_cercas_dm_rutas"))         
        {url = "mantenedores/geo_cercas/clientes_parametrizados/";}
		else if(id.equals("vehiculos_new"))         
        {url = "mantenedores/vehiculos/";}
		
		// PRODUCTIVIDAD		
		else if(id.equals("PRODUCTIVIDAD"))         
        {url = "mantenedores/vehiculos/";}
		else if(id.equals("listado4"))         
        {url = "dashboard/talabres/";}
		else if(id.equals("informe_ibutton"))         
        {url = "reportes/geo_cercas/clientes_parametrizados/";}
		
		// SALUD
		else if(id.equals("Ralenti_CAN"))         
        {url = "reportes/Rendimiento_CAN/";}
		else if(id.equals("Rendimiento_CAN_new"))         
        {url = "reportes/Rendimiento_CAN/";}
		else if(id.equals("Informe_Turno_Itinerario_CAN"))         
        {url = "reportes/CAN_Belectronics/";}
		else if(id.equals("Ralenti_CAN33"))         
        {url = "reportes/Rendimiento_CAN/";}
		
		
         
         
         
         
         request.setAttribute("id", id);
         request.setAttribute("url", url+id);     
         request.setAttribute("parametros", parametros);
         request.setAttribute("imgdescarga", imgdescarga);
         
		 return new ModelAndView("pageframe");
	}
	

}