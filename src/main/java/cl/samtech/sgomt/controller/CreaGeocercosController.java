package cl.samtech.sgomt.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import cl.samtech.sgomt.form.GeocercasForm;
import cl.samtech.sgomt.form.HistoricoForm;
import cl.samtech.sgomt.model.ColorGeo;
import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.CoordenadasActive;
import cl.samtech.sgomt.object.GeocercasActive;
import cl.samtech.sgomt.object.HistoricoActive;
import cl.samtech.sgomt.object.ListadoColorActive;
import cl.samtech.sgomt.object.ListadoGeocercasActive;
import cl.samtech.sgomt.object.ListadoMarcaTerrenoActive;
import cl.samtech.sgomt.object.MapaCalorActive;
import cl.samtech.sgomt.object.MapaUltimoGPS;
import cl.samtech.sgomt.object.PatenteGPSActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.service.AdministracionService;
import cl.samtech.sgomt.service.GestionService;
import cl.samtech.sgomt.service.ReporteService;
import cl.samtech.sgomt.service.UsuarioService;
import cl.samtech.sgomt.service.UtilServicio;

@Controller
public class CreaGeocercosController {
	@RequestMapping(value = "/creageocercos", method = RequestMethod.GET)
	public ModelAndView showForm(Map model, HttpServletRequest request) {
		if (request.getSession().getAttribute("usuario") == null) {
			// System.out.println("crea error getAttribute(usuario) null");
			return new ModelAndView("home");
		}

		UsuarioLogin usuario = (UsuarioLogin) request.getSession().getAttribute("usuario");
		String cliente = "";
		String clave = "";
		String usuLogincliente = "";
		String clavecliente = "";
		try {
			cliente = usuario.getUsername();
			clave = usuario.getPassword();
			usuLogincliente = usuario.getCliUsuSamtech();
			clavecliente = usuario.getCliPassSamtech();
			usuario.setUrlservlet(request.getServletPath());

		} catch (Exception e) {
			// System.out.println("crea error
			// (UsuarioLogin)request.getSession().getAttribute");
			return new ModelAndView("home");

		}
		// validad menu
		if (!UsuarioService.validarMenuUsuario(usuario, "listadogeocercas.html")) {
			// System.out.println("crea error
			// UsuarioService.validarMenuUsuario(usuario,
			// request.getServletPath())");
			return new ModelAndView("home");
		}
		// mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuario, "listadogeocercas.html");
		request.setAttribute("b", b);

		MapaUltimoGPS mc = new MapaUltimoGPS();
		mc.setLat("-22.7368623");
		mc.setLon("-69.3352093");

		List<ListadoGeocercasActive> mlistGeo = new ArrayList<ListadoGeocercasActive>();
		mlistGeo = ReporteService.findGeocercaByIdPg(usuLogincliente, clavecliente, "0");

		List<ListadoColorActive> mcolor = new ArrayList<ListadoColorActive>();
		mcolor = ReporteService.listadoColoresGeocercas();
		
		 List<ListadoMarcaTerrenoActive>  marcas_terreno = new ArrayList<ListadoMarcaTerrenoActive>();
	     marcas_terreno = AdministracionService.findListadoMarcasTerreno( usuLogincliente, clavecliente,0);

		String origen = "crear";
		request.setAttribute("origen", origen);

		request.setAttribute("color", mcolor);
		request.setAttribute("mc", mc);
		request.setAttribute("mlistGeo", mlistGeo);
	    request.setAttribute("marcas_terreno", marcas_terreno);

		return new ModelAndView("creageocercos");
	}

	@RequestMapping(value = "/editageocercos", method = RequestMethod.GET)
	public ModelAndView showForm1(Map model, HttpServletRequest request) {
		if (request.getSession().getAttribute("usuario") == null) {
			// System.out.println("edita error getAttribute(usuario) null");
			return new ModelAndView("home");
		}

		UsuarioLogin usuario = (UsuarioLogin) request.getSession().getAttribute("usuario");
		String cliente = "";
		String clave = "";
		String usuLogincliente = "";
		String clavecliente = "";
		try {
			cliente = usuario.getUsername();
			clave = usuario.getPassword();
			usuLogincliente = usuario.getCliUsuSamtech();
			clavecliente = usuario.getCliPassSamtech();

		} catch (Exception e) {
			// System.out.println("edita error
			// (UsuarioLogin)request.getSession().getAttribute");
			return new ModelAndView("home");

		}

		// validad menu
		if (!UsuarioService.validarMenuUsuario(usuario, "listadogeocercas.html")) {
			// System.out.println("edita error
			// UsuarioService.validarMenuUsuario(usuario,
			// request.getServletPath())");
			return new ModelAndView("home");
		}
		// mando barra menu a la vista
		BarraMenuActive b = UsuarioService.barraMenu(usuario, "listadogeocercas.html");
		request.setAttribute("b", b);

		MapaUltimoGPS mc = new MapaUltimoGPS();
		mc.setLat("-22.7368623");
		mc.setLon("-69.3352093");

		List<ListadoGeocercasActive> mlistGeo = new ArrayList<ListadoGeocercasActive>();
		mlistGeo = ReporteService.findGeocercaByIdPg(usuLogincliente, clavecliente, "0");

		String id = request.getParameter("id").toLowerCase();

		if (id != null) {
			List<ListadoGeocercasActive> mlistGeoId = new ArrayList<ListadoGeocercasActive>();
			// ListadoGeocercasActive mlistGeoId = new ListadoGeocercasActive();
			// mlistGeoId = (ListadoGeocercasActive)
			// ReporteService.findGeocercaByIdPgOne( usuLogincliente,
			// clavecliente, id);
			// ListadoGeocercasActive mlistGeoId2 = mlistGeoId.get(0);
			// mlistGeo = ReporteService.findGeocercaByIdPg3( usuLogincliente,
			// clavecliente,mlistCoor,
			// 2,GForm.getVisible(),GForm.getGeoreferencia(),GForm.getVigencia(),GForm.getAplicacion(),GForm.getFuera()
			// ,
			// GForm.getVelocidadAlarma(),GForm.getEstadoAlarma(),GForm.getCorreo());

			mlistGeoId = ReporteService.findGeocercaByIdPg3("nombre2",0,usuLogincliente, clavecliente, null, 4, id, 0, 0, 0, 0, 0,
					0, 0, "");
			ListadoGeocercasActive mlistGeoId2 = mlistGeoId.get(0);
			request.setAttribute("mlistGeoId", mlistGeoId2);
		}

		List<ListadoColorActive> mcolor = new ArrayList<ListadoColorActive>();
		mcolor = ReporteService.listadoColoresGeocercas();
		
		 List<ListadoMarcaTerrenoActive>  marcas_terreno = new ArrayList<ListadoMarcaTerrenoActive>();
	     marcas_terreno = AdministracionService.findListadoMarcasTerreno( usuLogincliente, clavecliente,0);

		String origen = "editar";
		request.setAttribute("origen", origen);

		request.setAttribute("color", mcolor);
		request.setAttribute("mc", mc);
		request.setAttribute("mlistGeo", mlistGeo);
	    request.setAttribute("marcas_terreno", marcas_terreno);

		return new ModelAndView("creageocercos");
	}

	@RequestMapping(value = "/creageocercos", method = RequestMethod.POST)
	public String processForm(@RequestParam("file") MultipartFile file, @Valid GeocercasForm GForm,
			BindingResult result, Map model, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "home";
		}

		UsuarioLogin usuario = (UsuarioLogin) request.getSession().getAttribute("usuario");
		String cliente = "";
		String clave = "";
		String usuLogincliente = "";
		String clavecliente = "";
		try {
			cliente = usuario.getUsername();
			clave = usuario.getPassword();
			usuLogincliente = usuario.getCliUsuSamtech();
			clavecliente = usuario.getCliPassSamtech();

		} catch (Exception e) {
			// System.out.println("edita error
			// (UsuarioLogin)request.getSession().getAttribute");
			return "home";

		}

		ListadoGeocercasActive mlistGeoId2 = new ListadoGeocercasActive();

		ArrayList<CoordenadasActive> mlistCoor = new ArrayList<CoordenadasActive>();

		if (!file.isEmpty()) {
			// por si queremos guardar el archivo usamos bytes
			// para leerlo inputStream
			byte[] bytes;
			try {
				bytes = file.getBytes();
				// tamano archivo
				// double bytesd = file.getSize();
				// double kilobytes = (bytesd / 1024);
				// double megabytes = (kilobytes / 1024);
				// System.out.println(megabytes);
				/*
				 * if(megabytes > 30){ request.setAttribute("mensaje",
				 * "Tamaño Archivo demasiado grande"); return "mensaje"; }
				 */

				InputStream inputStream = new BufferedInputStream(file.getInputStream());
				String salida = "POLYGON ((";
				String primero = "";
				try {
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					Document doc = dBuilder.parse(inputStream);

					doc.getDocumentElement().normalize();

					NodeList nList = doc.getElementsByTagName("coordinates");
					//System.out.println("Número de coodernadas: " + nList.getLength());
					
					for (int temp = 0; temp < nList.getLength(); temp++) {
						Node nNode = nList.item(temp);

						if (nNode.getNodeType() == Node.ELEMENT_NODE) {
							Element eElement = (Element) nNode;
							
							String coordenadasxml = eElement.getTextContent();
							
							String caracterBuscar= ",0";
							boolean aux = coordenadasxml.contains(caracterBuscar);
							String cadenaNew;
							if(aux){
								 cadenaNew = coordenadasxml.replace(",0",",").replace(" ","").trim();
								 //System.out.println("encontrado");
							}else{
								 cadenaNew = coordenadasxml.replace(" ",",");
							}
							
							
							String lat;
							String lon;
							String[] parts = cadenaNew.split(",");
							
							//System.out.println(parts.length);
							
							StringBuilder sbuilder = new StringBuilder();
							for(int i = 0 ; i < parts.length-1; i=i+2 ){	
									
								lon = parts[i];   
								lat = parts[i+1]; 
								sbuilder.append(lon).append(" ").append(lat).append(",");
								//salida += lon + " " + lat + ",";								
							}
							salida += sbuilder.toString()+ ",))";
							salida = salida.replace(",,", "");
							//System.out.println(salida);	
						} 

					}
					/*
					if (nList.getLength() == 1) {
						// caso que sea una

					} else {
						// para multiples geocercas

					}*/
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("mensaje", "Formato Archivo incorrecto");
					return "mensaje";
				}

				// mando barra menu a la vista
				BarraMenuActive b = UsuarioService.barraMenu(usuario, "listadogeocercas.html");
				request.setAttribute("b", b);

				MapaUltimoGPS mc = new MapaUltimoGPS();
				mc.setLat("-22.7368623");
				mc.setLon("-69.3352093");
				
				List<ListadoGeocercasActive> mlistGeo = new ArrayList<ListadoGeocercasActive>();

				List<ListadoColorActive> mcolor = new ArrayList<ListadoColorActive>();
				mcolor = ReporteService.listadoColoresGeocercas();

				mlistGeoId2.setCoordenadas(mlistCoor);

				int origen;

				if (GForm.getId_geo() != null) {
					mlistGeoId2.setId_geo(GForm.getId_geo());
					mlistGeo = ReporteService.findGeocercaByIdPg3(GForm.getNombre(),Integer.valueOf(GForm.getColor()), usuLogincliente, clavecliente, salida, 2,
							GForm.getId_geo().toString(), GForm.getVisible(), GForm.getGeoreferencia(), GForm.getVigencia(),
							GForm.getAplicacion(), GForm.getFuera(), GForm.getVelocidadAlarma(), GForm.getEstadoAlarma(),
							GForm.getCorreo());
					origen = 2;
					return "redirect:/editageocercos.html?id="+GForm.getId_geo();

				}else{
					mlistGeo = ReporteService.findGeocercaByIdPg3(GForm.getNombre(),Integer.valueOf(GForm.getColor()), usuLogincliente, clavecliente, salida, 1,"0", GForm.getVisible(), GForm.getGeoreferencia(), GForm.getVigencia(),
							GForm.getAplicacion(), GForm.getFuera(), GForm.getVelocidadAlarma(), GForm.getEstadoAlarma(),
							GForm.getCorreo());
					 origen = 1;	
				}
				/*mlistGeoId2.setNombre(GForm.getNombre());
				ColorGeo color = ReporteService.findColor(Integer.parseInt(GForm.getColor()));
				mlistGeoId2.setCodigo_color(color.getColCodigo());
				// mlistGeoId2.setNombre_color(GForm.getColor());
				mlistGeoId2.setUso(GForm.getUso());
				mlistGeoId2.setVigencia(GForm.getVigencia());
				mlistGeoId2.setVisible(GForm.getVisible());
				mlistGeoId2.setAplicacion(GForm.getAplicacion());
				mlistGeoId2.setFuera(GForm.getFuera());
				mlistGeoId2.setGeoreferencia(GForm.getGeoreferencia());
				mlistGeoId2.setEstadoAlarma(GForm.getEstadoAlarma());
				mlistGeoId2.setVelocidadAlarma(GForm.getVelocidadAlarma());
				mlistGeoId2.setCorreo(GForm.getCorreo());
				*/

				//mlistGeoId2.setGeomText("S");

				request.setAttribute("mlistGeoId", mlistGeoId2);

				request.setAttribute("origen", origen);

				request.setAttribute("color", mcolor);
				request.setAttribute("mc", mc);
				request.setAttribute("mlistGeo", mlistGeo);

				request.setAttribute("mensaje", "KML ha sido cargado");

				return "redirect:/listadogeocercas.html";

			} catch (IOException e) {

				e.printStackTrace();
			}

		} else {

			// AdministracionService.saveGeocerca(usuario.getUsername(),
			// GForm.getNombre(), GForm.getColor(),
			// GForm.getOrigen(),GForm.getGeomText(),GForm.getId_geo(),GForm);
			// comentar para testear
			AdministracionService.saveGeocerca(usuario.getUsername(), GForm);

			return "redirect:/listadogeocercas.html";

		} // fin archivo vacio

		/*
		 * String coordenadas = GForm.getGeomText();
		 * 
		 * Integer i = 0; String primero = "";
		 * 
		 * String salida = "POLYGON ((";
		 * 
		 * if(GForm.getGeomText().substring(0, 5).equals("MULTI")){
		 * 
		 * String geomtext= GForm.getGeomText().replace("MULTIPOLYGON(((",
		 * "").replace(")))", "");
		 * 
		 * String[] parts = geomtext.split(",",0); for (String p : parts) { i++;
		 * String[] parts2 = p.split(" ",0); String lat = parts2[1]; String lon
		 * = parts2[0]; salida+= lon+" "+lat+","; if(i==1){ primero =
		 * lon+" "+lat+","; } }
		 * 
		 * salida+=primero+",))"; salida = salida.replace(",,", ""); } else{
		 * 
		 * String[] parts = GForm.getGeomText().split("\\),\\(",0); for (String
		 * p : parts) { i++; p = p.replace("(", "").replace(" ",
		 * "").replace(")", ""); String[] parts2 = p.split(",",0); String lat =
		 * parts2[0]; String lon = parts2[1]; salida+= lon+" "+lat+",";
		 * if(i==1){ primero = lon+" "+lat+","; } }
		 * 
		 * salida+=primero+",))"; salida = salida.replace(",,", ""); }
		 * 
		 * System.out.println(coordenadas); System.out.println(salida);
		 */

		return "redirect:/listadogeocercas.html";

	}

}
