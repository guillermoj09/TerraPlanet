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

import cl.samtech.sgomt.form.CrearUsuarioForm;
import cl.samtech.sgomt.form.ReportePatenteFechaForm;
import cl.samtech.sgomt.object.ReporteIndicadorOptimoCargaActive;
import cl.samtech.sgomt.object.ReporteProduccionTurnoActive;
import cl.samtech.sgomt.object.ReporteTiempoCarguioActive;
import cl.samtech.sgomt.object.ReporteTiempoTransporteActive;
import cl.samtech.sgomt.service.ReporteService;
import cl.samtech.sgomt.service.UsuarioService;

@Controller
//@RequestMapping("editarusuario.html")
public class ReporteProduccionTurnoController {
	@RequestMapping(value = "/reporteproduccionturno", method = RequestMethod.GET)
	public ModelAndView showForm(Map model,HttpServletRequest request) {
		if(request.getSession().getAttribute("usuario")==null)
		{
			return new ModelAndView("home");
		}
		
		//Relaciono el form
		ReportePatenteFechaForm reportePatenteFechaForm = new ReportePatenteFechaForm();
		model.put("reportePatenteFechaForm", reportePatenteFechaForm);
			
		//Busco valores que se vayan a carga a la vista inicial
		//List<Object[]> clasificaciones = Reporteservice.AllClasificacion(); 
		//request.setAttribute("rlist", rlist);		
		
		
		return new ModelAndView("reporteproduccionturno");
	}
	
	//procesar 
	@RequestMapping(value = "/reporteproduccionturno" , method = RequestMethod.POST)
	public ModelAndView processForm(@Valid ReportePatenteFechaForm reportePatenteFechaForm, BindingResult result,
			Map model,HttpServletRequest request) {


		if (result.hasErrors()) {
			return new ModelAndView("home");
		}
		
		//me traigo los valores del formulario
		reportePatenteFechaForm = (ReportePatenteFechaForm) model.get("reportePatenteFechaForm");
		
		String patente = reportePatenteFechaForm.getPatente();
		String desde = reportePatenteFechaForm.getFechaDesde();
		String hasta = reportePatenteFechaForm.getFechaHasta();

		List<ReporteProduccionTurnoActive> rlist = ReporteService.findProduccionTurno(patente, desde, hasta);
		
		

		//if (rlist != null && !rlist.isEmpty()) { }
		

		request.setAttribute("rlist", rlist);								
		request.setAttribute("rform", reportePatenteFechaForm);
	
					
		return new ModelAndView("reportetiempotransporte");
		
	}
	
}