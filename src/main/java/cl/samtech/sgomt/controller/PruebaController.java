package cl.samtech.sgomt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PruebaController {
	@RequestMapping(value = "/prueba", method = RequestMethod.GET)
	public String index(){
		return "prueba.html";	
	}
}
