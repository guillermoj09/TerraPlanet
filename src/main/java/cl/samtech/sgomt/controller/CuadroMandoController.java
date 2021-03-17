package cl.samtech.sgomt.controller;

import java.text.DecimalFormat;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cl.samtech.sgomt.form.CuadroMandoForm;
import cl.samtech.sgomt.object.CuadroMandoActive;
import cl.samtech.sgomt.object.ListadoGeocercasActive;
import cl.samtech.sgomt.service.ReporteService;

@Controller
public class CuadroMandoController {

	@RequestMapping(value = "/cuadromando", method = RequestMethod.GET)
	public ModelAndView cuadromandomostrar(HttpServletRequest request) {
		List<ListadoGeocercasActive> listadogeocercas = new ArrayList<ListadoGeocercasActive>();
		listadogeocercas = ReporteService.geoCercasCDM();
		request.setAttribute("listadogeo", listadogeocercas);
		return new ModelAndView("cuadromando");
	}

	@RequestMapping(value = "/cuadromando", method = RequestMethod.POST)
	public String cuadromandoForm(@Valid CuadroMandoForm CForm, BindingResult result, Map model,
			HttpServletRequest request) {
		
		String mensaje = "";
		Integer idOrigen = CForm.getOrigen();
		String fecha = CForm.getFechaBusqueda();
		List<ListadoGeocercasActive> listadogeocercas = new ArrayList<ListadoGeocercasActive>();
		System.out.println("origen" +idOrigen);
		listadogeocercas = ReporteService.geoCercasCDM();
		request.setAttribute("listadogeo", listadogeocercas);
		//select * from  public.InformeDashboard('10/16/2019',0,1,0,233,'emexcosa','')
		List<CuadroMandoActive> mlist = ReporteService.controlMandoListar(fecha, 0, 1, 0,idOrigen);		
		if(mlist.size() == 0){
			mensaje = "No se encontraron datos";
		}
		request.setAttribute("mensaje", mensaje);
		request.setAttribute("lista1", mlist);
		request.setAttribute("cform", CForm);
		return "cuadromando";
	}

	@RequestMapping(value = "/ajaxlistar2", method = RequestMethod.POST)
	@ResponseBody()
	public String ajaxlistar2(HttpServletRequest request){
		
		Integer idOrigen = Integer.parseInt(request.getParameter("origen"));
		System.out.println("id origen"+idOrigen);
		String fecha = request.getParameter("fecha");
		Integer turno = Integer.parseInt(request.getParameter("turno"));
		//select * from  public.InformeDashboard(@fecha,@turno,@sw,@destino,@origen,@uaurio,@clave)
		//-- select * from  public.InformeDashboard('10/16/2019',3,2,0,233,'emexcosa','')
		List<CuadroMandoActive> mlist = ReporteService.controlMandoListar(fecha, turno, 2, 0, idOrigen);
		StringBuilder sb = new StringBuilder();
		sb.append("<div class='table-responsive'>");
		sb.append("<table class='table footable' id='tabla_2'>");
		sb.append("<thead><tr><th>Destino</th><th>Vueltas totales</th><th>Ciclos promedio (Hr/vtas)</th><th>Produccion media x hora  (Ton/hrs) </th><th>Tiempo ciclos (Hr/vtas)</th>");
		sb.append("<th>Velocidad promedio (Km/hr)</th><th>Distancia Promedio x camión</th><th>Vueltas x camion</th><th>Ralenti x camion  (Hrs/camion)</th><th>Horas x camion</th><th>Camiones totales</th></tr></thead><tbody>");	
		String resultado;
		DecimalFormat df = new DecimalFormat("#0.00");

		for(CuadroMandoActive c : mlist){
			sb.append("<tr onclick='ajaxlista3("+c.getId_destino()+","+turno+","+c.getId_geo()+")' data-target='#myModal'>").
			append("<td class='destino'>").append(c.getDestino_str()).append("</td>").
			append("<td>").append(c.getTotal_vueltas()).append("</td>").
			append("<td>").append(df.format(c.getCiclo_promedio())).append("</td>").
			append("<td>").append(df.format(c.getProduccion_mxh())).append("</td>").
			append("<td>").append(df.format(c.getTransp_kmxh())).append("</td>").
			append("<td>").append(df.format(c.getVel_prom())).append("</td>").
			append("<td>").append(df.format(c.getDistancia_promedio())).append("</td>").
			append("<td>").append(df.format(c.getVueltaxcamion())).append("</td>").
			append("<td>").append(df.format(c.getRalentixcamion())).append("</td>").
			append("<td>").append(df.format(c.getHorasxcamion())).append("</td>").
			append("<td>").append(c.getNum_camiones()).append("</td>").
			append("</tr>");
			sb.append("<input type='hidden' value='"+c.getDestino_str()+"' id='destino"+c.getId_destino()+"'>");
		}
		sb.append("</tbody>").append("</table></div>");
		resultado = sb.toString();	
		//request.setAttribute("lista2", resultado);

		System.out.println(resultado);
		return resultado;
	}
	
	
	@RequestMapping(value = "/ajaxlistar3", method = RequestMethod.POST)
	@ResponseBody
	public String ajaxlistar3(HttpServletRequest request){
		
		Integer idOrigen = Integer.parseInt(request.getParameter("origen"));
		String fecha = request.getParameter("fecha");
		Integer turno = Integer.parseInt(request.getParameter("turno"));
		Integer destino = Integer.parseInt(request.getParameter("destino"));
		//select * from  public.InformeDashboard(@fecha,@turno,@sw,@destino,@origen,@uaurio,@clave)
		//-- select * from  public.InformeDashboard('10/16/2019',3,3,241,233,'emexcosa','')   
		List<CuadroMandoActive> mlist = ReporteService.controlMandoListar(fecha, turno, 3, destino, idOrigen);
		StringBuilder sb = new StringBuilder();
		String resultado;
		DecimalFormat df = new DecimalFormat("#0.00");
		for(CuadroMandoActive c : mlist){
			sb.append("<tr>").
			append("<td>").append(c.getPatente()).append("</td>").
			append("<td>").append(c.getTotal_vueltas()).append("</td>").
			append("<td>").append(df.format(c.getProduccion_mxh())).append("</td>").
			append("<td>").append(df.format(c.getTransp_kmxh())).append("</td>").
			append("<td>").append(df.format(c.getVel_prom())).append("</td>").
			append("<td>").append(df.format(c.getDistancia_promedio())).append("</td>").
			append("</tr>");
			
		}
		
		resultado = sb.toString();
		System.out.println(resultado);
		return resultado;
	}
}
