package cl.samtech.sgomt.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import cl.samtech.sgomt.form.BasicForm;
import cl.samtech.sgomt.form.ComandoGpsForm;
import cl.samtech.sgomt.form.ConductorForm;
import cl.samtech.sgomt.form.TagForm;
import cl.samtech.sgomt.form.VehiculoForm;
import cl.samtech.sgomt.model.Cliente;
import cl.samtech.sgomt.model.Conductor;
import cl.samtech.sgomt.model.Ibuttom;
import cl.samtech.sgomt.model.IbuttomConductor;
import cl.samtech.sgomt.model.Menu;
import cl.samtech.sgomt.model.Tag;
import cl.samtech.sgomt.model.TipoVehiculo;
import cl.samtech.sgomt.model.Usuario;
import cl.samtech.sgomt.model.Vehiculo;
import cl.samtech.sgomt.model.VehiculoDevice;
import cl.samtech.sgomt.object.ConsultarComandoActive;
import cl.samtech.sgomt.object.CoordenadasActive;
import cl.samtech.sgomt.object.GeocercasActive;
import cl.samtech.sgomt.object.HorometroActive;
import cl.samtech.sgomt.object.IbuttomActive;
import cl.samtech.sgomt.object.PatenteGPSActive;
import cl.samtech.sgomt.object.ReporteResumenFlotaActive;
import cl.samtech.sgomt.object.VehiculoActive;

public class DispositivoService {
	
	private static EntityManager em;
	private static EntityManager em1;
	private static EntityManager em2;
	private static EntityManager em3;
	private static EntityManager em4;
	private static EntityManager em5;
	private static EntityManager em6;
	private static EntityManager emc;
	private static EntityManager emh;
	private static EntityManager emh2;
	private static EntityManager emh3;
	
	private static String log4jConfPath = "/opt/tomcat9/webapps/web/log4j.properties";
	
	final static Logger logger = Logger.getLogger(DispositivoService.class);
	
	
	public static Vehiculo findVehiculo(String id){

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		em2 = emf.createEntityManager();		
		Vehiculo vehiculo = em2.find(Vehiculo.class, id);
		
		em2.clear();
		em2.close();
		return vehiculo;
	}
	
	
	public static Vehiculo findVehiculoByPatente(String patente){

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		em3 = emf.createEntityManager();
		Vehiculo vehiculo = em3.find(Vehiculo.class, patente);
				
		em3.clear();
		em3.close();
		return vehiculo;
	}
	
	public static TipoVehiculo findTipoVehiculo(String id){

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		em5 = emf.createEntityManager();
		
		TipoVehiculo tipoVehiculo  = new TipoVehiculo();
		try {
			
			 tipoVehiculo = em5.find(TipoVehiculo.class, Integer.valueOf(id));
			
		} catch (Exception e) {
			
		}
		
		
		
		em5.clear();
		em5.close();
		return tipoVehiculo;
	}
	
	public static Tag findTag(String id){

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		em2 = emf.createEntityManager();		
		Tag tag = em2.find(Tag.class, id);
		
		em2.clear();
		em2.close();
		return tag;
	}
	
	
	public static String FindRutClienteByUser(String usuLogin){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
	
		em1 = emf.createEntityManager();				
		
		Query query = em1.createQuery("select u.cliente.cliRut from Usuario u where u.usuLogin = :usuLogin ");
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		query.setParameter("usuLogin", usuLogin);
		
		String rutCliente = "";
		
		try {
			
			rutCliente = (String) query.getSingleResult();
			
		} catch (Exception e) {
		
		}
			
       em1.clear();
	   em1.close();				
	   return rutCliente;
	}
	
	 public static ArrayList<TipoVehiculo> FindAllTipoVehiculoByRutCliente(String rutCliente){
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");

			em2 = emf.createEntityManager();				
			
			TypedQuery<TipoVehiculo> alltipovehiculo= em2.createQuery(""
					+ "select e from TipoVehiculo e "
					//+ "where e.cliRutCliente = :rutCliente "								
					+ "", TipoVehiculo.class);//.setParameter("rutCliente", rutCliente);	
			
			alltipovehiculo.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
			
			ArrayList<TipoVehiculo> tipovehiculos  = new ArrayList<TipoVehiculo>(alltipovehiculo.getResultList());
			
			em2.clear();
			em2.close();	
			return tipovehiculos;
		}
	 
	 public static ArrayList<Tag> FindAllTagByRutCliente(String rutCliente){
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");

			em3 = emf.createEntityManager();				
			
			TypedQuery<Tag> alltag= em3.createQuery(""
					+ "select e from Tag e where e.cliente.cliRut = :rutCliente "								
					+ "", Tag.class).setParameter("rutCliente", rutCliente);	
			
			alltag.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
			
			ArrayList<Tag> tags  = new ArrayList<Tag>(alltag.getResultList());
					 				
			em3.clear();
			em3.close();
			return tags;
		}
	 
	 
	 public static ArrayList<Tag> FindAllNoTagByPAtenteClienteRut(String patente, String rutCliente){
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");

			em3 = emf.createEntityManager();				
			
			TypedQuery<Tag> alltag= em3.createQuery(""
					+ "select e from Tag e where e.cliente.cliRut = :rutCliente and e.tagId "
					+ "not in "
					//+ "(select u.tagIdTag from VehiculoTag u where u.vehPatenteVehiculo = :vehPatenteVehiculo ) "  el tag no debe repetirse por patente
					+ "(select u.tagIdTag from VehiculoTag u ) order by e.tagId "
					+ "", Tag.class).setParameter("rutCliente", rutCliente);//.setParameter("vehPatenteVehiculo", patente);	
			
			alltag.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
			
			ArrayList<Tag> tags  = new ArrayList<Tag>(alltag.getResultList());
					 				
			em3.clear();
			em3.close();
			return tags;
		}
	 
	 public static ArrayList<Tag> FindAllSiTagByPAtenteClienteRut(String patente, String rutCliente){
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");

			em3 = emf.createEntityManager();				
			
			TypedQuery<Tag> alltag= em3.createQuery(""
					+ "select e from Tag e where e.cliente.cliRut = :rutCliente and e.tagId "
					+ "in "
					+ "(select u.tagIdTag from VehiculoTag u where u.vehPatenteVehiculo = :vehPatenteVehiculo ) order by e.tagId "								
					+ "", Tag.class).setParameter("rutCliente", rutCliente).setParameter("vehPatenteVehiculo", patente);	
			
			alltag.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
			
			ArrayList<Tag> tags  = new ArrayList<Tag>(alltag.getResultList());
					 				
			em3.clear();
			em3.close();
			return tags;
		}
	 
	 //no habilitado aun
	 public static Boolean guardarVehiculo(VehiculoForm vehiculoForm){

			Boolean grabado=true;
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		
			em = emf.createEntityManager();
			em2 = emf.createEntityManager();
			
			Vehiculo vehiculo = new Vehiculo();
		
			List<Tag> tagslist = new ArrayList<Tag>();
			
			if(vehiculoForm.getTags()!=null){
			String[] parts=vehiculoForm.getTags().split(",");
				for(int i = 0; i < parts.length; i++){		
				
				Tag t = em2.find(Tag.class, parts[i]);	
			
				tagslist.add(t);
				
				//Estanque ec = em2.find(Estanque.class, Integer.parseInt(parts[i]) ) ;
							
				}
			}
			
			vehiculo.setTags(tagslist);
			
			try{
				em.getTransaction().begin();
				em.merge(vehiculo);
				em.getTransaction().commit();	
			}catch(Exception e){
				System.out.println(e);
				grabado=false;
			}
			
			em.clear();
			em.close();
			em2.clear();
			em2.close();
			
			return grabado;
		}
	 
	 
	 public  static Boolean  crearVehiculo(VehiculoForm vehiculoForm, String usuLogincliente, String clavecliente, String rutcliente){

		   Boolean respuesta = false;
		   String resultado = "";
			

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");		
			DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy"); //dd/MM/yyyy
			em4 = emf.createEntityManager();	
											

			//	public.mantenedordevice(patente, año, modelo, marca,motor,'',0.0,0.0,tipv_id_tipo_vehiculo,
            //                          rut_cliente,num_interno,color,0.0,0.0,0.0,0.0,0.0,0,1)
			
			//public.mantenedordevice(patente, año, modelo, marca,motor,vin,carga_soportada,carga_volumen,tipv_id_tipo_vehiculo,
            // rut_cliente,num_interno,color,peso_bruto,capacidad_balde,peso_operacional,nivel_estanque,capacidad_balde_ton
			//,con_gps, sw_accion)



			//	Para ingresar
            //      -- select * from public.mantenedordevice('DLKF85','2012','gris','lindo','dfddgf','sdsde',0.0,0.0, 10,'768857474', '', '', 0.0, 0.0, 0.0, 0.0, 0.0,0,1)

			
			StoredProcedureQuery query=em4.createStoredProcedureQuery("public.mantenedordevice");
			
			query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN); // patente
			query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN); // anio
		    query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN); // modelo
		    query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN); // marca
		    query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN); // motor
		    query.registerStoredProcedureParameter(6, String.class, ParameterMode.IN); // vin
		    query.registerStoredProcedureParameter(7, Double.class, ParameterMode.IN); // veh_carga_soportada
		    query.registerStoredProcedureParameter(8, Double.class, ParameterMode.IN); // carga_volumen
		    query.registerStoredProcedureParameter(9, int.class, ParameterMode.IN); // tipv_id_tipo_vehiculo
		    query.registerStoredProcedureParameter(10, String.class, ParameterMode.IN); // cli_rut_cliente
		    query.registerStoredProcedureParameter(11, String.class, ParameterMode.IN); // veh_num_interno
		    query.registerStoredProcedureParameter(12, String.class, ParameterMode.IN); // veh_color
		    query.registerStoredProcedureParameter(13, Double.class, ParameterMode.IN); // veh_peso_bruto
		    query.registerStoredProcedureParameter(14, Double.class, ParameterMode.IN); // veh_capacidad_balde
		    query.registerStoredProcedureParameter(15, Double.class, ParameterMode.IN); // veh_peso_operacional
		    query.registerStoredProcedureParameter(16, Double.class, ParameterMode.IN); // veh_nivel_estanque
		    query.registerStoredProcedureParameter(17, Double.class, ParameterMode.IN); // veh_capacidad_balde_ton
		    query.registerStoredProcedureParameter(18, int.class, ParameterMode.IN); //   veh_con_gps
		    query.registerStoredProcedureParameter(19, int.class, ParameterMode.IN); //   sw_accion 
		    
	    
		    query.setParameter(1, vehiculoForm.getVehPatente());
		    query.setParameter(2, vehiculoForm.getVehAnio());  
		    query.setParameter(3, vehiculoForm.getVehModelo());
		    query.setParameter(4, vehiculoForm.getVehMarca());  
		    query.setParameter(5, vehiculoForm.getVehMotor());		    
		    query.setParameter(6, "");
		    query.setParameter(7, vehiculoForm.getVehCargaSoportada());
		    query.setParameter(8, vehiculoForm.getVehCargaVolumen());
		    query.setParameter(9, Integer.valueOf(vehiculoForm.getTipoVehiculo()));  
		    query.setParameter(10, rutcliente);
		    query.setParameter(11, vehiculoForm.getVehNumInterno());
		    query.setParameter(12, vehiculoForm.getVehColor()); 		    
		    query.setParameter(13, vehiculoForm.getVehPesoBruto());
		    query.setParameter(14, vehiculoForm.getVehCapacidadBalde());
		    query.setParameter(15, vehiculoForm.getVehPesoOperacional());
		    query.setParameter(16, vehiculoForm.getVehNivelEstanque());
		    query.setParameter(17, vehiculoForm.getVehCapacidadBaldeTon());
		    query.setParameter(18, 0); //congps
		    query.setParameter(19, 1); //sw_accion
		    
      		query.execute();
      		
      		System.out.println(query);
			
			 //String resultaro = (String) query.getSingleResult();
			  
			  List<Object[]> obj = query.getResultList();
				try {					
					for (Object[] o : obj) {							
						resultado = (String) o[2]; 
					}					
				} catch (Exception e) {
					System.out.println(e);
					respuesta = true;
				}				
				if(resultado.equals("")){		 			 
		 			 respuesta = true;
		 		 }
				else{		 			 
		 			respuesta = false;		 			 
		 		 }
						 		
		 		em4.clear();
		 		em4.close();
			 			 
		    return  respuesta;
	}
	 
	 public static Boolean modificarVehiculo(VehiculoForm vehiculoForm, String usuLogincliente, String clavecliente, Integer perfilid){
		 
		 //PropertyConfigurator.configure(log4jConfPath);
		
		 if(perfilid == null){
			 
			 perfilid = 0;
		 }
		 

			Boolean grabado=true;
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");			
			em = emf.createEntityManager();			
			em2 = emf.createEntityManager();
			em3 = emf.createEntityManager();
			
			
			Vehiculo vehiculo = em.find(Vehiculo.class, vehiculoForm.getVehPatente());
			
			//los tags se borran si cambiaron el tipo de vehiculo y no corresponde a camion
			if(perfilid==6 || perfilid==7){//|| perfilid==7
			List<Tag> tagslist = new ArrayList<Tag>();
			
			if(vehiculoForm.getTags()!=null){
			String[] parts=vehiculoForm.getTags().split(",");
				for(int i = 0; i < parts.length; i++){		
				
				Tag t = em2.find(Tag.class, parts[i]);	
			
				tagslist.add(t);				
				//Estanque ec = em2.find(Estanque.class, Integer.parseInt(parts[i]) ) ;
							
				}
			}
			vehiculo.setTags(tagslist);
			}
			
			
		//solo administrador puede quitar o agregar usuario a vehiculos	
	 	if(perfilid==6){
			List<Usuario> usuariolist = new ArrayList<Usuario>();
			
			if(vehiculoForm.getUsuarios()!=null){
				String[] parts02=vehiculoForm.getUsuarios().split(",");
					for(int i = 0; i < parts02.length; i++){		
					
					Usuario u = em3.find(Usuario.class, parts02[i]);
									
					usuariolist.add(u);					
					//Estanque ec = em2.find(Estanque.class, Integer.parseInt(parts[i]) ) ;
								
					}
				}
			vehiculo.setUsuarios(usuariolist);
			}
			
			vehiculo.setVehAnio(vehiculoForm.getVehAnio());
			vehiculo.setVehMarca(vehiculoForm.getVehMarca());
			vehiculo.setVehModelo(vehiculoForm.getVehModelo());
			vehiculo.setVehMotor(vehiculoForm.getVehMotor());
			vehiculo.setVehNumInterno(vehiculoForm.getVehNumInterno());
			vehiculo.setVehColor(vehiculoForm.getVehColor());
			
			//camion
			//if(vehiculoForm.getVehCargaVolumen()!= null || vehiculoForm.getVehPesoBruto()!= null){
			vehiculo.setVehCargaSoportada(vehiculoForm.getVehCargaSoportada());
			vehiculo.setVehCargaVolumen(vehiculoForm.getVehCargaVolumen());
			vehiculo.setVehPesoBruto(vehiculoForm.getVehPesoBruto());
			//}
						
			//maquina
			//if(vehiculoForm.getVehCapacidadBalde()!= null || vehiculoForm.getVehPesoOperacional()!= null){ 				
			vehiculo.setVehCapacidadBalde(vehiculoForm.getVehCapacidadBalde());
			vehiculo.setVehPesoOperacional(vehiculoForm.getVehPesoOperacional());	
			vehiculo.setVehCapacidadBaldeTon(vehiculoForm.getVehCapacidadBaldeTon());
			//}
			
			//maquina y camion
			vehiculo.setVehNivelEstanque(vehiculoForm.getVehNivelEstanque());
			
			
			TipoVehiculo tipoVehiculo =  findTipoVehiculo(vehiculoForm.getTipoVehiculo());
			if(tipoVehiculo.getTipvId()!=null){
			vehiculo.setTipoVehiculo(tipoVehiculo);
			}
						
		try {
			
			em.getTransaction().begin();						
			em.merge(vehiculo);
			em.getTransaction().commit();
						
		} catch (Exception e) {
			
			logger.error("Modificar Vehiculo "+e);
			
			System.out.println(e);
			grabado=false;
			
			
		}			
			em.clear();
			em.close();
			em2.clear();
			em2.close();
			em3.clear();
			em3.close();
			return grabado;
		}
	 
	 public  static Boolean  modificarVehiculoSQLSERVER(VehiculoForm vehiculoForm, String usuLogincliente, String clavecliente){

		   Boolean respuesta = false;
		   String resultado = "";
						
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("gpsf");			
			DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy"); //dd/MM/yyyy
			em4 = emf.createEntityManager();	
											
			 //Basegpsf.dbo.MM_P_Actualiza_Vehiculo_SGOMT
			//@gps varchar(5), @anio int, @marca varchar(80), @modelo varchar(80), @motor varchar(80), @num_int  varchar(30), @tipo_vehi int, 
			//@color  varchar(30), @login_samtech varchar(80), @pw_samtech varchar(80)			 
			
			StoredProcedureQuery query=em4.createStoredProcedureQuery("MM_P_Actualiza_Vehiculo_SGOMT");      
			query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN); // gps varchar(5)
			query.registerStoredProcedureParameter(2, int.class, ParameterMode.IN); // anio
		    query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN); // marca
		    query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN); // modelo
		    query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN); // motor
		    query.registerStoredProcedureParameter(6, String.class, ParameterMode.IN); // num_int
		    query.registerStoredProcedureParameter(7, int.class, ParameterMode.IN); // tipo_vehi
		    query.registerStoredProcedureParameter(8, String.class, ParameterMode.IN); // color
		    query.registerStoredProcedureParameter(9, String.class, ParameterMode.IN); // login_samtech
		    query.registerStoredProcedureParameter(10, String.class, ParameterMode.IN); // pw_samtech
		    
		    VehiculoDevice v = ReporteService.findDeviceByPatente02(vehiculoForm.getVehPatente());
		    String idDevice = v.getDevIdDevice();
		    
		    query.setParameter(1, idDevice);
		    //query.setParameter(1, vehiculoForm.getVehPatente());
		    query.setParameter(2, Integer.valueOf(vehiculoForm.getVehAnio()));  
		    query.setParameter(3, vehiculoForm.getVehMarca());
		    query.setParameter(4, vehiculoForm.getVehModelo());  
		    query.setParameter(5, vehiculoForm.getVehMotor());  
		    query.setParameter(6, vehiculoForm.getVehNumInterno());
		    query.setParameter(7, Integer.valueOf(vehiculoForm.getTipoVehiculo()));  
		    query.setParameter(8, vehiculoForm.getVehColor());  
		    query.setParameter(9, usuLogincliente);
		    query.setParameter(10, clavecliente);//clavecliente // claveemexcosa
			
			query.execute();
			
			 //String resultaro = (String) query.getSingleResult();
			  
			  List<Object[]> obj = query.getResultList();
				try {
					
					for (Object[] o : obj) {	
						
						resultado = (String) o[0]; 
					}
					
					
				} catch (Exception e) {
					System.out.println(e);
					respuesta = true;
				}
				
				if(resultado.equals("OKA")){
		 			 
		 			 respuesta = true;
		 		 }
				if(resultado.equals("NOK")){
		 			 
		 			respuesta = false;
		 			 
		 		 }
						 		
		 		em4.clear();
		 		em4.close();
			 			 
		    return  respuesta;
	}
		
	 
	 
	 public static Boolean guardarTag(TagForm tagForm){

			Boolean grabado=true;
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		
			em = emf.createEntityManager();
						
			Tag tag = new Tag();
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			
			tag.setTagId(tagForm.getTagId().trim());
			tag.setTagSerie(tagForm.getTagSerie().trim());
			tag.setTagMarca(tagForm.getTagMarca().trim());
			tag.setTagModelo(tagForm.getTagModelo().trim());
			tag.setTagFechaIngresa(timestamp);
			
			Cliente cliente = UsuarioService.findCliente(tagForm.getTagCliente());
			
			tag.setCliente(cliente);
			
			try{
				em.getTransaction().begin();
				em.persist(tag);				
				em.getTransaction().commit();
			}catch(Exception e){
				
				logger.error("Guardar Tag "+e);
				System.out.println(e);
				grabado=false;
			}
			
			em.clear();
			em.close();
			
			return grabado;
		}
	 
	 public static Boolean editarTag(TagForm tagForm){

			Boolean grabado=true;
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		
			em = emf.createEntityManager();
			
			Tag tag = em.find(Tag.class, tagForm.getTagId());					
			
			tag.setTagSerie(tagForm.getTagSerie());
			tag.setTagMarca(tagForm.getTagMarca());
			tag.setTagModelo(tagForm.getTagModelo());
			
			Cliente cliente = UsuarioService.findCliente(tagForm.getTagCliente());
			
			tag.setCliente(cliente);
			
			try{
				em.getTransaction().begin();
				em.merge(tag);				
				em.getTransaction().commit();
			}catch(Exception e){
				
				logger.error("Modificar Tag "+e);
				System.out.println(e);
				grabado=false;
			}
			
			em.clear();
			em.close();
			
			return grabado;
		}
	 
	 public static ArrayList<VehiculoActive> allVehiculesByCliente(String rutUsuario, String  cliRut){
						
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		
			em = emf.createEntityManager();				
								
			TypedQuery<Vehiculo> allvehiculo= em.createQuery(""
					+ "select v from Vehiculo v where v.vehPatente "
					+ "in "
					+ "(select u.vehPatenteVehiculo from UsuarioVehiculo u where u.usuRutUsuario = :rutUsuario ) "
					+ "and v.cliente.cliRut = :cliRut "
					+ "order by v.vehPatente"
					+ ""
					, Vehiculo.class);		
			allvehiculo.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
			allvehiculo.setParameter("rutUsuario", rutUsuario);
			allvehiculo.setParameter("cliRut", cliRut);
			
			List<Vehiculo> vehiculos = allvehiculo.getResultList();
			 		
			ArrayList<VehiculoActive> vehiculoList = new ArrayList<VehiculoActive>();
				
			for (Vehiculo v : vehiculos) {
				
				VehiculoActive va = new VehiculoActive(); 
				
				va.setVehPatente(v.getVehPatente());
				va.setVehVin(v.getVehAnio());
				va.setVehModelo(v.getVehModelo());
				va.setVehMarca(v.getVehMarca());
				va.setVehAnio(v.getVehAnio());
				
				vehiculoList.add(va);
													
							
			}
			
			em.clear();
			em.close();
			return vehiculoList;
		}
	 
	 
	 public static ArrayList<VehiculoActive> allVehiculesByUsuario(String rutUsuario, String cliRut){
			
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		
			em = emf.createEntityManager();				
			
			Query query = em.createNativeQuery("select * from public.mantenedordevice('','','','','','',0.0,0.0, 0,'"+cliRut+"', '', '', 0.0, 0.0, 0.0, 0.0, 0.0,0,4)");
			
			ArrayList<VehiculoActive> vehiculoList = new ArrayList<VehiculoActive>();
			
			List<Object[]> glist = query.getResultList();
			
			for(Object[] g : glist ){
				
				VehiculoActive va = new VehiculoActive(); 
				
				va.setVehId((Integer)g[0]);
				va.setVehPatente((String)g[1]);
				va.setVehVin((String)g[6]);
				va.setVehModelo((String)g[3]);
				va.setVehMarca((String)g[4]);
				va.setVehAnio((String)g[2]);
				va.setTipoVehiculo(findTipoVehiculo(String.valueOf(g[10])));
				va.setNrointerno((String)g[12]);								
				//	va.setTags(v.getTags());	
				va.setListadoTags((String)g[20]);
				vehiculoList.add(va); 
				
			}
			
			em.clear();
	 		em.close();
								
			
			return vehiculoList;
		}
	 
	 public static ArrayList<VehiculoActive> allNotVehiculesByUsuario(String rutUsuario, String cliRut){
			
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		
			em = emf.createEntityManager();				
								
			TypedQuery<Vehiculo> allvehiculo= em.createQuery(""
					+ "select v from Vehiculo v where v.vehPatente "
					+ "not in "
					+ "(select u.vehPatenteVehiculo from UsuarioVehiculo u where u.usuRutUsuario = :rutUsuario ) "
					+ "and v.cliente.cliRut = :cliRut "
					+ "order by v.vehPatente"
					+ ""
					, Vehiculo.class);		
			allvehiculo.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
			allvehiculo.setParameter("rutUsuario", rutUsuario);
			allvehiculo.setParameter("cliRut", cliRut);
			
			List<Vehiculo> vehiculos = allvehiculo.getResultList();
			 		
			ArrayList<VehiculoActive> vehiculoList = new ArrayList<VehiculoActive>();
				
			for (Vehiculo v : vehiculos) {
				
				VehiculoActive va = new VehiculoActive(); 
				
				va.setVehId(v.getVehId());
				va.setVehPatente(v.getVehPatente());
				va.setVehVin(v.getVehVin());
				va.setVehModelo(v.getVehModelo());
				va.setVehMarca(v.getVehMarca());
				va.setVehAnio(v.getVehAnio());
				
				va.setTipoVehiculo(v.getTipoVehiculo());
				va.setNrointerno(v.getVehNumInterno());
								
				va.setTags(v.getTags());
				
				vehiculoList.add(va);
													
							
			}
			
			em.clear();
			em.close();
			return vehiculoList;
		}
	 
	 public static Boolean guardarConductor(ConductorForm conductorForm){

			Boolean grabado=true;
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		
			em = emf.createEntityManager();
						
			Conductor conductor= new Conductor();
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			
			conductor.setCondRut(conductorForm.getRut());
			conductor.setCondNombre(conductorForm.getNombre());
			conductor.setCondApellido(conductorForm.getApellido());
			conductor.setCondFono(conductorForm.getTelefono());
			conductor.setCondDireccion(conductorForm.getDireccion());
			conductor.setCondFechaCrea(timestamp);
						
			//Esta relacion no debe estar asi.
			Cliente cliente = UsuarioService.findCliente(conductorForm.getCliRazonSocial());			
			Usuario usuario =  UsuarioService.findUsuario(cliente.getCliRut());			
			conductor.setUsuario(usuario);
			
			String mensaje = "";
			
			try{
				em.getTransaction().begin();
				em.persist(conductor);				
				//em.getTransaction().commit();
				
				List<Ibuttom> ibuttomlist = new ArrayList<Ibuttom>();
				
				if(conductorForm.getIbuttoms()!=null){
				String[] parts=conductorForm.getIbuttoms().split(",");
					for(int i = 0; i < parts.length; i++){		
					
					Ibuttom ib = em.find(Ibuttom.class, parts[i]);
					
					//agregamos el nuevo ibuttoms al conductor					
					IbuttomConductor ic = new IbuttomConductor();
					
					ic.setCondRutConductor(conductorForm.getRut());
					ic.setIbucondEstado(1);
					ic.setIbucondFechaAsignacion(timestamp);
					ic.setIbuIdIbuttom(ib.getIbuCodigo());
					
					try{
					
					em.persist(ic);
					
					}catch(Exception e){					
						logger.error("Agregar IB "+e);
						System.out.println(e);
						grabado=false;
					}
					
											
					}//fin for
				}// fin if
				
				em.getTransaction().commit();
				
			}catch(Exception e){
				
				logger.error("Guardar Conduct "+e);
				System.out.println(e);
				grabado=false;
			}
			
			em.clear();
			em.close();
			
			return grabado;
		}
	 
 public static Boolean modificarConductor(ConductorForm conductorForm, String usuLogincliente, String clavecliente, Integer perfilid){
		 
		 //PropertyConfigurator.configure(log4jConfPath);
		
		 if(perfilid == null){
			 
			 perfilid = 0;
		 }
		 

			Boolean grabado=true;
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");			
			em = emf.createEntityManager();			
			//em2 = emf.createEntityManager();
			//em3 = emf.createEntityManager();
			
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
						
			Conductor conductor = em.find(Conductor.class, conductorForm.getRut());
						
			conductor.setCondNombre(conductorForm.getNombre());
			conductor.setCondApellido(conductorForm.getApellido());
			conductor.setCondFono(conductorForm.getTelefono());
			conductor.setCondDireccion(conductorForm.getDireccion());
						
			//Esta relacion no debe estar asi.
			Cliente cliente = UsuarioService.findCliente(conductorForm.getCliRazonSocial());			
			Usuario usuario =  UsuarioService.findUsuario(cliente.getCliRut());			
			conductor.setUsuario(usuario);
			
			List<Ibuttom> ibuttomlist = new ArrayList<Ibuttom>();
			String mensaje = "";
			
			em.getTransaction().begin();
			
			if(conductorForm.getIbuttoms()!=null){
			String[] parts=conductorForm.getIbuttoms().split(",");
				for(int i = 0; i < parts.length; i++){	
									
				Ibuttom ib = em.find(Ibuttom.class, parts[i]);
								
				Query query = em.createQuery("select i from IbuttomConductor i "
						+ "where  "
						+ "i.ibuIdIbuttom = :ibuIdIbuttom "
						+ "and i.condRutConductor = :rutconductor "
						+ "and i.ibucondEstado = 1  "						
						+ "  ");
				query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");		
				query.setParameter("ibuIdIbuttom", ib.getIbuCodigo());
				query.setParameter("rutconductor", conductor.getCondRut());
				
				List<IbuttomConductor> iclist = query.getResultList();
				
				
				if(iclist.size()>0){
					
							System.out.println("existe ibuttom para este conductor se deja el registro original");
						
				}else{
					
					//verificamos que no tenga ibuttom registrados y activo, lo desactivamos
					Query query2 = em.createQuery("select i from IbuttomConductor i "
							+ "where  "
							+ "i.condRutConductor = :rutconductor and i.ibucondEstado = 1  "						
							+ "  ");
					query2.setHint("javax.persistence.cache.retrieveMode", "BYPASS");		
					query2.setParameter("rutconductor", conductor.getCondRut());
					
					List<IbuttomConductor> iclist2 = query2.getResultList();
					

					for (IbuttomConductor obj : iclist2) {
											
						obj.setIbucondEstado(0);
											
						try{
						
						em.merge(obj);
						
						}catch(Exception e){					
							logger.error("Quitar IB "+e);
							System.out.println(e);
							grabado=false;
						}
						
					}	
					
					
				//agregamos el nuevo ibuttoms al conductor					
				IbuttomConductor ic = new IbuttomConductor();
				
				ic.setCondRutConductor(conductor.getCondRut());
				ic.setIbucondEstado(1);
				ic.setIbucondFechaAsignacion(timestamp);
				ic.setIbuIdIbuttom(ib.getIbuCodigo());
				
				try{
				
				em.persist(ic);
				
				}catch(Exception e){					
					logger.error("Agregar IB "+e);
					System.out.println(e);
					grabado=false;
				}
				
				//Activo ibutton NO FUNCIONA !!!...
				//mensaje = ActivarIbuttom(conductorForm.getNombre(), conductorForm.getApellido(), conductorForm.getRut(), conductorForm.getDireccion(), conductorForm.getTelefono(), cliente.getCliUsuSamtech(), "2", ib.getIbuCodigo());
				
			
				//ibuttomlist.add(ib);		
				
				}//fin else
										
				}//fin for
			}else {
				
				IbuttomConductor ic = new IbuttomConductor();
				List<IbuttomConductor> ibuttomConductorlist = new ArrayList<IbuttomConductor>();
				
				Query query = em.createQuery("select i from IbuttomConductor i "
						+ "where  "
						+ "i.condRutConductor = :rutconductor and i.ibucondEstado = 1  "						
						+ "  ");
				query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");		
				query.setParameter("rutconductor", conductor.getCondRut());
				
				List<IbuttomConductor> iclist = query.getResultList();
				Integer i = 0;

				for (IbuttomConductor obj : iclist) {
										
					obj.setIbucondEstado(0);
										
					try{
					
					em.merge(obj);
					
					}catch(Exception e){					
						logger.error("Quitar IB "+e);
						System.out.println(e);
						grabado=false;
					}
					
				}
				
				//desactivo Ibuttom
				//mensaje = ActivarIbuttom(conductorForm.getNombre(), conductorForm.getApellido(), conductorForm.getRut(), conductorForm.getDireccion(), conductorForm.getTelefono(), cliente.getCliUsuSamtech(), "2", "");
				
				
				
			}
			//conductor.setIbuttoms(ibuttomlist);
			
			try{
				//em.getTransaction().begin();
				em.merge(conductor);				
				em.getTransaction().commit();
			}catch(Exception e){
				
				logger.error("Editar Conduct "+e);
				System.out.println(e);
				grabado=false;
			}
			
			em.clear();
			em.close();
			
			return grabado;
		}
 
 public static Boolean eliminarConductor(String rut){

		Boolean grabado=true;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
	
		em = emf.createEntityManager();
					
		Conductor conductor = em.find(Conductor.class, rut);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		
		String mensaje = "";
		
		try{
			em.getTransaction().begin();
			em.remove(conductor);				
			//em.getTransaction().commit();
			
			
			// desactivo el ibuttom para este conductor, pero queda guardado el registro como historico
			Query query = em.createQuery("select i from IbuttomConductor i "
					+ "where  "
					+ "i.condRutConductor = :rutconductor and i.ibucondEstado = 1  "						
					+ "  ");
			query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");		
			query.setParameter("rutconductor", rut);
			
			List<IbuttomConductor> iclist = query.getResultList();
			Integer i = 0;

			for (IbuttomConductor obj : iclist) {
									
				obj.setIbucondEstado(0);
									
				try{
				
				em.merge(obj);
				
				}catch(Exception e){					
					logger.error("Quitar IB "+e);
					System.out.println(e);
					grabado=false;
				}
				
			}
			
			em.getTransaction().commit();
			
		}catch(Exception e){
			
			logger.error("Eliminar Conduct "+e);
			System.out.println(e);
			grabado=false;
		}
		
		em.clear();
		em.close();
		
		return grabado;
	}
 
 public static ArrayList<Ibuttom> findIbuttomByConductor(Conductor conductor){

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		em = emf.createEntityManager();		
		
		Query query = em.createQuery("select i from Ibuttom i "
				+ "where  "
				+ "i.ibuCodigo in "
				+ "(select ic.ibuIdIbuttom from IbuttomConductor ic where ic.condRutConductor = :rutconductor and ic.ibucondEstado = 1 ) "
				+ "and i.ibuEstado = 1 "
				+ "order by i.ibuCodigo  ");
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");		
		query.setParameter("rutconductor", conductor.getCondRut());
					
		ArrayList<Ibuttom> ibuttoms  = new ArrayList<Ibuttom>(query.getResultList());
		
		em.clear();
		em.close();
	
	return ibuttoms;
	}
 
 	public static ArrayList<Ibuttom> findNotIbuttomByConductor(Conductor conductor){

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		em1 = emf.createEntityManager();		
		
		Query query = em1.createQuery("select i from Ibuttom i "
				+ "where  "
				+ "i.ibuCodigo not in "
				+ "(select ic.ibuIdIbuttom from IbuttomConductor ic where ic.condRutConductor = :rutconductor and ic.ibucondEstado = 1 ) "
				+ "and i.ibuEstado = 1 "
				+ "order by i.ibuCodigo  ");
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");		
		query.setParameter("rutconductor", conductor.getCondRut());
					
		ArrayList<Ibuttom> ibuttoms  = new ArrayList<Ibuttom>(query.getResultList());
		
		em1.clear();
		em1.close();
	
	return ibuttoms;
	}
 	
 	public static ArrayList<Ibuttom> findNotIbuttomAllByConductor(String cliente){

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		em1 = emf.createEntityManager();	
		
		Integer estado = 1;
		
		Query query = em1.createQuery("select i from Ibuttom i "
				+ "where  "
				+ "i.ibuCodigo not in "
				+ "(select ic.ibuIdIbuttom from IbuttomConductor ic where ic.ibucondEstado = 1) "
				+ "and i.ibuEstado = :estado "
				+ "and i.usuario.cliente.cliRut = :cliente "
				+ " order by i.ibuCodigo ");
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		query.setParameter("cliente", cliente);
		query.setParameter("estado", estado);
							
		ArrayList<Ibuttom> ibuttoms  = new ArrayList<Ibuttom>(query.getResultList());
		
		em1.clear();
		em1.close();
	
	return ibuttoms;
	}
 
 
	public static Conductor findConductor(String condRut){

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		em2 = emf.createEntityManager();		
		Conductor conductor= em2.find(Conductor.class, condRut);
		
		em2.clear();
		em2.close();
		return conductor;
	}
	
	public static Conductor findConductorByRut(String condRut){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
	
		em2 = emf.createEntityManager();				
		
		//Query query = em2.createQuery("select c from Conductor c where c.condRut = :condRut ");
		Query query = em2.createNativeQuery("select cond_rut, cond_nombre, cond_apellido, cond_direccion, cond_fono from Conductor c where c.cond_rut = '"+condRut+"' ");
		//query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		//query.setParameter("condRut", condRut);
		
		Conductor conductor = new Conductor();
		ArrayList<Conductor> conductorlisy = new ArrayList<Conductor>();
		try {
			//conductorlisy = (ArrayList<Conductor>) query.getResultList();
			//conductor = conductorlisy.get(0);			
			//conductor = (Conductor) query.getSingleResult();
			List<Object[]> conductorlist = query.getResultList();
			Integer i = 0;

			for (Object[] obj : conductorlist) {
				
				   String rut = (String)obj[0];
				    String nombre= (String)obj[1];
				    String apellido = (String) obj[2];
				    String direccion= (String)obj[3];
				    String fono= (String)obj[4];
				    
				    conductor.setCondRut(rut);
				    conductor.setCondNombre(nombre);
				    conductor.setCondApellido(apellido);
				    conductor.setCondDireccion(direccion);
				    conductor.setCondFono(fono);
				    
				    conductorlisy.add(conductor);
				
			}
			
			conductor = conductorlisy.get(0);		
			
			
		} catch (Exception e) {
		
		}
			
       em2.clear();
	   em2.close();	
	   
	   return conductor;
	}
	
	
	public static String ActivarIbuttom(String nombre, String apellido, String rut, String direccion, String fono, String cliente, String sw, String ibuttom){

		String mensaje = "Nada";
		
		if(nombre.equals(null)){
			nombre = "";
		}
		if(apellido.equals(null)){
			apellido = "";
		}
		if(direccion.equals(null)){
			direccion = "";
		}
		if(fono.equals(null)){
			fono = "";
		}
		if(ibuttom.equals(null)){
			ibuttom = "";
		}
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		
		em2 = emf.createEntityManager();				
				
		Query query = em2.createNativeQuery("select * from public.mantenedorconductores( "
				+ "'"+nombre+"', "
				+ "'"+apellido+"', "
				+ "'"+rut+"', "
				+ "'"+direccion+"', "
				+ "'"+fono+"', "
				+ "'"+cliente+"', "
				+ "'"+sw+"', "
				+ "'"+ibuttom+"' "
				+ ")"
				+ "");
		
		List<Object[]> list = query.getResultList();
		Integer i = 0;

		for (Object[] obj : list) {
			
			    mensaje = (String)obj[0];
			   
		}
		
		em2.clear();
		em2.close();
		
		return mensaje;
		}
	
	public static List<HorometroActive>  findHorometroPG(String rutcliente, String sw){

	      //select * from public.mantenedorhorometro('768857474', '',0,2);
		
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB"); //gpsf
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); //dd/MM/yyyy
		//<td><fmt:formatDate value="${r.fechaIni}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
		em2 = emf.createEntityManager();


		StoredProcedureQuery query=em2.createStoredProcedureQuery("mantenedorhorometro");
		query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN); // id_geo
		query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN); // usuario
	    query.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN); // pw
	    query.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN); // pw

	    query.setParameter(1, rutcliente);
	    query.setParameter(2, "");
	    query.setParameter(3, 0);
	    query.setParameter(4, Integer.valueOf(sw));

		 ///////////////////////////////////////


		 ArrayList<HorometroActive> hlist = new ArrayList<HorometroActive>();

		  query.execute();

	 		List<Object[]> eventlist = query.getResultList();

	 		Integer i = 0;
	 		boolean resp = true;
	 		boolean resp2 = true;
	 		
	 	    
	 	   //DecimalFormat df = new DecimalFormat("#######.##");
	 		DecimalFormat df = new DecimalFormat("#######");

	 		for (Object[] event : eventlist) {
				i++;
				
				//verificamos si se actualizo el estado por cada patente 
				
				String tipoD = "";

				HorometroActive horometroActive = new HorometroActive();

				String gps = (String) event[0];
				String patente = (String) event[1];
				String tipo = (String) event[3];
				
				//Calcular horometro	
				//Para DB, DM, DA: dividir en 3600
				//Para HR: dividir en 60
				Double horometro = (Double) event[2];
				Double horoActual = (Double) event[5];
				
				if(tipo.equals("DB")||tipo.equals("DM")||tipo.equals("DA")){
					
					horometro = horometro/3600;
					try {
						horoActual = horoActual/3600;	
					} catch (Exception e) {
						horoActual = 0.0;
					}
					
				}else if (tipo.equals("HR")) {
					
					horometro = horometro/60;
					try {
						horoActual = horoActual/60;	
					} catch (Exception e) {
						horoActual = 0.0;
					}
					
					
				}else {
					
					horometro = horometro;
					horoActual = horoActual;
				}
				
				String horometroString = df.format(horometro);
				String horoActualString = df.format(horoActual);
				
				Timestamp fechaCambia = (Timestamp) event[4];
				//Double horoActual = (Double) event[5];
				Double horoCambia = (Double) event[6];
				String usuarioCambia = (String) event[7];
				Integer estado = (Integer) event[8];
				Timestamp fechaUltima = (Timestamp) event[9];
				
				
				//DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); //dd/MM/yyyy
				String fechaCambiaS = "";
				String fechaUltimaS = "";
				try {					
					Date fecdesde= new Date(fechaCambia.getTime());
					fechaCambiaS = formatteri.format(fecdesde);									
				} catch (Exception e) {					
				   fechaCambiaS = "";					
				}
				try {					
					Date fecdesdeu= new Date(fechaUltima.getTime());
					fechaUltimaS = formatteri.format(fecdesdeu);									
				} catch (Exception e) {					
					fechaUltimaS = "";					
				}
								
				try {
					
					if(estado == 0){
						resp = verificarHorometroSQLSERVER(gps, rutcliente, 0.0, tipo);
						//resp = false;
							
					}else{
							
						resp = false;
							
					}
					
				} catch (Exception e) {
					estado = 2;
					resp = false;
					System.out.println(e);
					
				}
				
				horometroActive.setGps(gps);
				horometroActive.setPatente(patente);				
				//horometroActive.setHorometro(horometro);
				try {
					//resumenflota.setRendkmlitroString(rendkmlitroString.replace(",", ".").trim());
				horometroActive.setHorometro(Double.valueOf(horometroString.replace(",", ".").trim()));
					
				} catch (Exception e) {
				
				}				
				horometroActive.setFechaCambia(fechaCambia);
				//horometroActive.setHoroActual(horoActual);
				try {
					horometroActive.setHoroActual(Double.valueOf(horoActualString.replace(",", ".").trim()));	
				} catch (Exception e) {

				}
				
				horometroActive.setHoroCambia(horoCambia);
				horometroActive.setUsuarioCambia(usuarioCambia);
				horometroActive.setFechaCambiaS(fechaCambiaS);
				
				horometroActive.setFechaUltima(fechaUltima);
				horometroActive.setFechaUltimaS(fechaUltimaS);
				
				if(resp){
					//actualizo estado en PG
					resp2 = modificarHorometroEstadoPG(horometro, rutcliente, gps, tipo, patente);
					//resp2 = false;
					if(resp2){
					estado = 1;
					
					}/*else{
					
						estado = estado;
						
					}*/
					
				}/*else{
					
					estado = estado;
					horometroActive.setEstadoD("Esperando Repuesta ....");
						
				}*/
				
				
				
				horometroActive.setEstado(estado);
				if(estado == 1){// respondido
					horometroActive.setEstadoD("<small style='color: green; font-size :12px;'>Respondido <i class='fa fa-warning'></i> </small>");
					
				}if(estado == 0){//esperando repuesta
					
					horometroActive.setEstadoD("<small style='color: red; font-size :12px;'>Enviando Datos <i class='fa fa-warning'></i> </small>");
					
				}
				
				if(estado == 2){ // no asignado
					
					horometroActive.setEstadoD("<small style='color: orange; font-size :12px;'>Sin envio de Datos<i class='fa fa-warning'></i> </small>");
					
				}
				
				/*HR: Digital
				DA: CAN
				DB: CAN Digital
				DM: No se que es!!*/
				
				
				if(tipo.equals("HR")){
					tipoD = "Digital";
					
				}
				else if(tipo.equals("DA")){
					tipoD = "CAN";
				}
				else if(tipo.equals("DB")){
					tipoD = "CAN Digital";
				}
				else if(tipo.equals("DM")){
					tipoD = tipo;
				}else {
					tipoD = tipo;
					
				}
				
				horometroActive.setTipo(tipo);
				horometroActive.setTipoD(tipoD);
				
				if(!tipo.equals("DA")){
												
				hlist.add(horometroActive);
				
				}

	 		}

	 		em2.clear();
	 		em2.close();


	    return  hlist;
}
	
	 public static Boolean  modificarHorometroSQLSERVER(Double horometro, String rutcliente, String gps, String tipo){

		 Boolean respuesta = false;
		   String resultado = "";
						
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("gpsf02");			
			DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy"); //dd/MM/yyyy
			em4 = emf.createEntityManager();	
											
			  /*Paso 1.- [MM_P_envia_msg_horometro] '0001',1.2,'HR',0
				Paso 2.-  Si la respuesta es Ok, debes actualizar SGOMT para registrar en envío.
				Paso 3.-  select * from public.mantenedorhorometro('768857474', 'DLKF74', 1.2 ,0); */			
			/*
			 
			 PA SQL

			[MM_P_envia_msg_horometro] @gps,@horometro,@tipo,@sw

           Donde:

				@gps: Corresponde al Id gps.
				@horometro: Corresponde al valor en horas del horómetro  que se desea actualizar.
				@tipo: Corresponde al tipo de horómetro que se va a actualizar.  Corresponde a la columna hor_tipo del listado de public.mantenedorhorometro.
				@sw: Cuando es 0, actualiza el horómetro según el valor enviado. Cuando es distinto de 0, consulta el estado del envío.
			 
			 */
			
			if(tipo.equals("DB") ||tipo.equals("DA") ){
				
				horometro = horometro * 3600;
				
			}else if (tipo.equals("HR")) {
				
				horometro = horometro * 60;
				
			}else if (tipo.equals("DM")) {
				
				horometro = horometro * 3600;
				
			}
			
			StoredProcedureQuery query=em4.createStoredProcedureQuery("MM_P_envia_msg_horometro");      
			query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN); // gps varchar(5)
			query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN); // hor
		    query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN); // tipo
		    query.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN); // estdo
		    
		   
		    DecimalFormat df = new DecimalFormat("#######");
		    
		    String horometroS = df.format(horometro);
		    
		    query.setParameter(1, gps);		    
		    query.setParameter(2, Integer.valueOf(horometroS));  
		    query.setParameter(3, tipo);
		    query.setParameter(4, 0);  
		    
			query.execute();
			
			 //String resultaro = (String) query.getSingleResult();
			  
			  List<Object[]> obj = query.getResultList();
				try {
					
					for (Object[] o : obj) {	
						
						resultado = (String) o[0]; 
					}
					
					
				} catch (Exception e) {
					System.out.println(e);
					respuesta = true;
				}
				
				if(resultado.equals("Ok")){
		 			 
		 			 respuesta = true;
		 		 }else{
		 			 
		 			respuesta = false; 
		 			 
		 		 }
				/*if(resultado.equals("NOK")){
		 			 
		 		 }*/
						 		
		 		em4.clear();
		 		em4.close();
			 			 
		    return  respuesta;
	}
	 
	 public static Boolean  verificarHorometroSQLSERVER(String gps, String rutcliente, Double horometro, String tipo){

		   Boolean respuesta = false;
		   String resultado = "";
						
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("gpsf02");			
			DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy"); //dd/MM/yyyy
			emh = emf.createEntityManager();	
											
			  /*Paso 1.- [MM_P_envia_msg_horometro] '0001',0,'HR',1
			  
	          Paso 2.-  Si la respuesta es ‘Ok’, debes actualizar el estado en SGOMT de la siguiente forma.

	          Paso 3.-  select * from public.mantenedorhorometro('768857474', 'DLKF74', 0,1); */
			
			/*
			 
			 PA SQL

			[MM_P_envia_msg_horometro] @gps,@horometro,@tipo,@sw

             Donde:

				@gps: Corresponde al Id gps.
				@horometro: Corresponde al valor en horas del horómetro  que se desea actualizar.
				@tipo: Corresponde al tipo de horómetro que se va a actualizar.  Corresponde a la columna hor_tipo del listado de public.mantenedorhorometro.
				@sw: Cuando es 0, actualiza el horómetro según el valor enviado. Cuando es distinto de 0, consulta el estado del envío.
			 
			 */
			
			StoredProcedureQuery query=emh.createStoredProcedureQuery("MM_P_envia_msg_horometro");      
			query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN); // gps varchar(5)
			query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN); // hor
		    query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN); // tipo
		    query.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN); // estdo
		    
		    query.setParameter(1, gps);		    
		    query.setParameter(2, 0);  
		    query.setParameter(3, tipo);
		    query.setParameter(4, 1);  
		    
			query.execute();
			
			 //String resultaro = (String) query.getSingleResult();
			  
			  List<Object[]> obj = query.getResultList();
				try {
					
					for (Object[] o : obj) {	
						
						resultado = (String) o[0]; 
					}
					
					
				} catch (Exception e) {
					System.out.println(e);
					respuesta = true;
				}
				
				if(resultado.equals("Ok")){
		 			 
		 			 respuesta = true;
		 		 }else{
		 			 
		 			respuesta = false; 
		 			 
		 		 }
				/*if(resultado.equals("NOK")){
		 			 
		 		 }*/
						 		
		 		emh.clear();
		 		emh.close();
			 			 
		    return  respuesta;
	}
	 
	 public static Boolean  modificarHorometroPG(Double horometro, String rutcliente, String gps, String tipo, String patente){

		 Boolean respuesta = false;
		   String resultado = "";
						
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");			
			DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy"); //dd/MM/yyyy
			emh2 = emf.createEntityManager();
			
			
			//calculamos nuevo horometro
			
			/*
			 * tipo_horo='Horometro CAN' where tipo_horo in ('DA','DM');
			tipo_horo='Horometro CAND' where tipo_horo in ('DB');
			tipo_horo='Horometro Digital' where tipo_horo in ('HR');
			 * 
			 */
			
			/*
			   if (tip_hor='HR') then

                                  hor_cambia = hor_cambia * 60;

                               elsif (tip_hor='DB' or tip_hor='DA') then

                                  hor_cambia = hor_cambia * 3600;

                               elsif (tip_hor='DM') then

                                  hor_cambia = hor_cambia * 3600;

                               end if;
			 */
			
			/*if(tipo.equals("DB") ||tipo.equals("DA") ){
				
				horometro = horometro * 3600;
				
			}else if (tipo.equals("HR")) {
				
				horometro = horometro * 60;
				
			}else if (tipo.equals("DM")) {
				
				horometro = horometro * 3600;
				
			}*/
											
			  /*Paso 1.- [MM_P_envia_msg_horometro] '0001',1.2,'HR',0
				Paso 2.-  Si la respuesta es Ok, debes actualizar SGOMT para registrar en envío.
				Paso 3.-  select * from public.mantenedorhorometro('768857474', 'DLKF74', 1.2 ,0); */			
			/*
			 
			 PA SQL

		Paso 3.-  select * from public.mantenedorhorometro('768857474', 'DLKF74', 1.2 ,0);

           Donde:

				@gps: Corresponde al Id gps.
				@horometro: Corresponde al valor en horas del horómetro  que se desea actualizar.
				@tipo: Corresponde al tipo de horómetro que se va a actualizar.  Corresponde a la columna hor_tipo del listado de public.mantenedorhorometro.
				@sw: Cuando es 0, actualiza el horómetro según el valor enviado. Cuando es distinto de 0, consulta el estado del envío.
			 
			 */
			
			StoredProcedureQuery query=emh2.createStoredProcedureQuery("mantenedorhorometro");
			query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN); // id_geo
			query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN); // usuario
		    query.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN); // pw
		    query.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN); // pw

		    query.setParameter(1, rutcliente);
		    query.setParameter(2, patente);
		    query.setParameter(3, horometro);
		    query.setParameter(4, 0);

		    
			query.execute();
			
			 //String resultaro = (String) query.getSingleResult();
			  
			  List<Object[]> obj = query.getResultList();
				try {
					
					for (Object[] o : obj) {	
						
						resultado = (String) o[0]; 
					}
					
					
				} catch (Exception e) {
					System.out.println(e);
					respuesta = true;
				}
				
				if(resultado.equals("")){
		 			 
		 			 respuesta = true;
		 		 }else{
		 			 
		 			respuesta = false; 
		 			 
		 		 }
				/*if(resultado.equals("NOK")){
		 			 
		 		 }*/
						 		
		 		emh2.clear();
		 		emh2.close();
		 				 
		    return  respuesta;
	}
	 
	 public static Boolean  modificarHorometroEstadoPG(Double horometro, String rutcliente, String gps, String tipo, String patente){ //verificar estado

		 Boolean respuesta = false;
		   String resultado = "";
						
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");			
			DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy"); //dd/MM/yyyy
			emh3 = emf.createEntityManager();	
											
			  /*Paso 1.- [MM_P_envia_msg_horometro] '0001',0,'HR',1
             Paso 2.-  Si la respuesta es ‘Ok’, debes actualizar el estado en SGOMT de la siguiente forma.
			 Paso 3.-  select * from public.mantenedorhorometro('768857474', 'DLKF74', 0,1);

           Donde:

				@gps: Corresponde al Id gps.
				@horometro: Corresponde al valor en horas del horómetro  que se desea actualizar.
				@tipo: Corresponde al tipo de horómetro que se va a actualizar.  Corresponde a la columna hor_tipo del listado de public.mantenedorhorometro.
				@sw: Cuando es 0, actualiza el horómetro según el valor enviado. Cuando es distinto de 0, consulta el estado del envío.
			 
			 */
			
			StoredProcedureQuery query=emh3.createStoredProcedureQuery("mantenedorhorometro");
			query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN); // id_geo
			query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN); // usuario
		    query.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN); // pw
		    query.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN); // pw

		    query.setParameter(1, rutcliente);
		    query.setParameter(2, patente);
		    query.setParameter(3, 0);
		    query.setParameter(4, 1);

		    
			query.execute();
			
			 //String resultaro = (String) query.getSingleResult();
			  
			  List<Object[]> obj = query.getResultList();
				try {
					
					for (Object[] o : obj) {	
						
						resultado = (String) o[0]; 
					}
					
					
				} catch (Exception e) {
					System.out.println(e);
					respuesta = true;
				}
				
				if(resultado.equals("Ok")){
		 			 
		 			 respuesta = true;
		 		 }else{
		 			 
		 			respuesta = false; 
		 			 
		 		 }
				/*if(resultado.equals("NOK")){
		 			 
		 		 }*/
						 		
				emh3.clear();
				emh3.close();
						 
		    return  respuesta;
	}
	 
 
	 public static Boolean guardarIbuttom(BasicForm basicForm, String rutCliente){

			//Boolean grabado=false;
			Integer contador = 0;
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
			DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); //dd/MM/yyyy
			emc = emf.createEntityManager();
			em2 = emf.createEntityManager();			
			
			 Query query02 = emc.createNativeQuery("select ibu_id from cliente.ibuttom where ibu_codigo ='"+basicForm.getIbuttom()+"' and ibu_estado = 1");
			    
			    System.out.println(query02);

				List<Object[]> mlistFor = query02.getResultList();	
					
				List <IbuttomActive> mlist = new ArrayList();
					for (Object[] pa : mlistFor) {
						IbuttomActive pa2= new IbuttomActive();
							pa2.setId((Integer) pa[0]);
							contador = 1;						
						mlist.add(pa2);
					}	
					
					emc.close();
					
			if(contador == 1){				
				return false;				
			}else if(contador == 0 ){	
				em2.getTransaction().begin();
				em2.createNativeQuery("insert into cliente.ibuttom(ibu_codigo, ibu_estado,ibu_fecha_crea,usu_rut_usuario) values(?,1,now(),?) ").setParameter(1, basicForm.getIbuttom()).setParameter(2, rutCliente).executeUpdate();
				em2.getTransaction().commit();
		        em2.close();
				return true;
			} else{
				return false;
			}
			
			

		}
	 
	 public static Boolean borrarIbuttom(String ibuttom, String rutCliente){

			//Boolean grabado=false;
			Integer contador = 0;
			String query = "delete from cliente.ibuttom where ibu_id='"+ibuttom+"' and usu_rut_usuario='"+rutCliente+"'";
			System.out.println(query);
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
					
			em2 = emf.createEntityManager();
			em2.getTransaction().begin();
			em2.createNativeQuery(query).executeUpdate();
			em2.getTransaction().commit();
	        em2.close();
			return true;
		}
		
	 public static Boolean  modificarComandoIDGPSSQLSERVER(ComandoGpsForm comandoGpsForm){

		 Boolean respuesta = false;
		   String resultado = "";
						
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("gpsf02");			
			DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy"); //dd/MM/yyyy
			em4 = emf.createEntityManager();	
							
					
			StoredProcedureQuery query=em4.createStoredProcedureQuery("MM_P_envia_msg_horometro");      
			query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN); // gps varchar(5)
			query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN); // hor
		    query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN); // tipo
		    query.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN); // estdo
		    
			query.execute();
			  
			List<Object[]> obj = query.getResultList();
				try {
					
					for (Object[] o : obj) {	
						
						resultado = (String) o[0]; 
					}
					
					
				} catch (Exception e) {
					System.out.println(e);
					respuesta = true;
				}
										 	
		 		em4.clear();
		 		em4.close();
			 			 
		    return  respuesta;
	}
	 
	 public static ConsultarComandoActive findComandoGPS(String cliente){
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("gpsf02");
		
			em2 = emf.createEntityManager();				
			
			//Query query = em2.createQuery("select c from Conductor c where c.condRut = :condRut ");
			Query query = em2.createNativeQuery("select cond_rut, cond_nombre, cond_apellido, cond_direccion, cond_fono from Conductor c where c.cond_rut = '"+cliente+"' ");
			//query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
			//query.setParameter("condRut", condRut);
			
			ConsultarComandoActive conductor = new ConsultarComandoActive();
			ArrayList<ConsultarComandoActive> conductorlisy = new ArrayList<ConsultarComandoActive>();
			try {
				//conductorlisy = (ArrayList<Conductor>) query.getResultList();
				//conductor = conductorlisy.get(0);			
				//conductor = (Conductor) query.getSingleResult();
				List<Object[]> conductorlist = query.getResultList();
				Integer i = 0;

				for (Object[] obj : conductorlist) {
					
					   String rut = (String)obj[0];
					    String nombre= (String)obj[1];
					    String apellido = (String) obj[2];
					    String direccion= (String)obj[3];
					    String fono= (String)obj[4];
					    
					  
					    
					    conductorlisy.add(conductor);
					
				}
				
				conductor = conductorlisy.get(0);		
				
				
			} catch (Exception e) {
			
			}
				
	       em2.clear();
		   em2.close();	
		   
		   return conductor;
		}
	 
	
}
