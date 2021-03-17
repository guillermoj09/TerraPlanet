package cl.samtech.sgomt.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
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
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.annotation.JacksonInject.Value;

import cl.samtech.sgomt.form.CrearUsuarioForm;
import cl.samtech.sgomt.form.ModuloForm;
import cl.samtech.sgomt.model.Cliente;
import cl.samtech.sgomt.model.Menu;
import cl.samtech.sgomt.model.Usuario;
import cl.samtech.sgomt.model.UsuarioHistorial;
import cl.samtech.sgomt.model.UsuarioSession;
import cl.samtech.sgomt.model.UsurioPerfil;
import cl.samtech.sgomt.model.Vehiculo;
import cl.samtech.sgomt.object.BarraMenuActive;
import cl.samtech.sgomt.object.DetalleXViajeActive;
import cl.samtech.sgomt.object.MenuActive;
import cl.samtech.sgomt.object.MenuActive3;
import cl.samtech.sgomt.object.MenuForm;
import cl.samtech.sgomt.object.ModuloActive;
import cl.samtech.sgomt.object.SubMenuActive;
import cl.samtech.sgomt.object.TurnoActive;
import cl.samtech.sgomt.object.UsuarioLogin;
import cl.samtech.sgomt.object.VehiculoActive;
import cl.samtech.sgomt.service.UtilServicio.OS;
import cl.samtech.sgomt.util.RandomString;
import cl.samtech.sgomt.util.UAgentInfo;
import cl.samtech.sgomt.util.sendMail;
import cl.samtech.sgomt.model.Modulo;
import cl.samtech.sgomt.model.Perfil;
import cl.samtech.sgomt.model.Turno2;

/**
 * Esta Clase cumple con las fuciones que tiene que ver con el modulo usuario, login, menu, perfiles,  log de usuarios, validacion controlador menus, entre otros
 * @author: Christopher rickblanc02@gmail.com
 * @version: 25/06/2019/A
 */
public class UsuarioService {
	
	private static EntityManager em;
	private static EntityManager em1;
	private static EntityManager em2;
	private static EntityManager em3;
	private static EntityManager em4;
	private static EntityManager em5;
	private static EntityManager eml;
	private static EntityManager emm;
	private static EntityManager emm2;
	private static EntityManager emmo;
	private static EntityManager emme;
	private static EntityManager emmp;
	private static EntityManager emb;
	
	
	
	public static Boolean existeUsuario(String username){
			
		Boolean existe=false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
	
		em = emf.createEntityManager();				
		
		Query query = em.createQuery("select u from Usuario u where u.usuLogin = :username ");
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		query.setParameter("username", username);
			
		List resultList = query.getResultList();
       if (resultList != null && !resultList.isEmpty()) {          
    	   existe=true;
       }
       em.clear();
		em.close();				
		return existe;
	}
	
	public static Boolean existeRutUsuario(String rut){
		
		Boolean existe=false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
	
		em = emf.createEntityManager();				
		
		Query query = em.createQuery("select u from Usuario u where u.usuRut = :rut ");
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		query.setParameter("rut", rut);
			
		List resultList = query.getResultList();
       if (resultList != null && !resultList.isEmpty()) {          
    	   existe=true;
       }
       em.clear();
		em.close();				
		return existe;
	}
	
	
	public static Boolean guardarUsuario(CrearUsuarioForm crearUsuarioForm){

		Boolean grabado=true;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		em = emf.createEntityManager();			
		Usuario usuario = new Usuario();		
		RandomString rs= new RandomString();
		usuario.setUsuLogin(crearUsuarioForm.getUsuLogin().toLowerCase());
		//usuario.setClave(rs.randomString(8));
		usuario.setUsuClave("12345");		
		
		usuario.setUsuRut(crearUsuarioForm.getUsuRut());//llave pk
		usuario.setUsuNombre(crearUsuarioForm.getUsuNombre());
		usuario.setUsuApellido(crearUsuarioForm.getUsuApellido());
		usuario.setUsuMail(crearUsuarioForm.getUsuMail());		
		usuario.setUsuDireccion(crearUsuarioForm.getUsuDireccion());
		usuario.setUsuEstado(crearUsuarioForm.getUsuEstado());
		
		if(crearUsuarioForm.getPerfil()==null){
			crearUsuarioForm.setPerfil("8");
		}
		
		
		try {
			Cliente cliente  = findCliente(crearUsuarioForm.getCliente());		
			usuario.setCliente(cliente);
			
		} catch (Exception e) {
		   
		}
		
		try {
			Perfil perfil  = findPerfilById(crearUsuarioForm.getPerfil());	
			ArrayList<Perfil> perfiles = new ArrayList<Perfil>();
			perfiles.add(perfil);
			usuario.setPerfiles(perfiles);
			
		} catch (Exception e) {
		   
		}
		
		
		//Menu por defecto // 
		String menus = "1,2,10,12,17,30";
			List<Menu> menulist = new ArrayList<Menu>();
			
			if(menus!=null){
				String[] parts02=menus.split(",");
					for(int i = 0; i < parts02.length; i++){		
					
					Menu m = em.find(Menu.class, Integer.parseInt(parts02[i]));
									
					menulist.add(m);										
								
					}
				}
			usuario.setMenus(menulist);
					
				
			try{
			em.getTransaction().begin();
			em.merge(usuario);
			em.getTransaction().commit();
									
									
		}catch(Exception e){
			//System.out.println(e);
			grabado=false;
		}		
		
		String mensaje = "";
		
		//if(usuario.getRolusuario().getCodigo()==1){
	if(grabado){
		mensaje="  Usuario: "+ usuario.getUsuLogin()+
		               "\n Contraseña: "+usuario.getUsuClave()+			               			               
		               "\n Ingresar por https://sgomt.samtech.cl/ ";
							 		
		try {
			sendMail.enviarCorreo("Usuario nuevo Sgomt",mensaje,usuario.getUsuMail());
			
		} catch (Exception e) {
			//System.out.println(e);
		
		}
	 }
				
		em.clear();
		em.close();
		return grabado;
	}
	
				
	public static Boolean modificarUsuario(CrearUsuarioForm crearUsuarioForm, Integer perfilid){

		Boolean grabado=true;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		em = emf.createEntityManager();					
		em3 = emf.createEntityManager();
		Usuario usuario = findUsuarioXUsuLogin(crearUsuarioForm.getUsuLogin02());
		
		usuario.setUsuNombre(crearUsuarioForm.getUsuNombre());
		usuario.setUsuApellido(crearUsuarioForm.getUsuApellido());
		usuario.setUsuMail(crearUsuarioForm.getUsuMail());		
		usuario.setUsuDireccion(crearUsuarioForm.getUsuDireccion());
		usuario.setUsuEstado(crearUsuarioForm.getUsuEstado());
		
		try {
			
			Cliente cliente  = findCliente(crearUsuarioForm.getCliente());		
			usuario.setCliente(cliente);
			
		} catch (Exception e) {
		
				
		}
		
		try {
			Perfil perfil  = findPerfilById(crearUsuarioForm.getPerfil());	
			ArrayList<Perfil> perfiles = new ArrayList<Perfil>();
			perfiles.add(perfil);
			usuario.setPerfiles(perfiles);
			
		} catch (Exception e) {
		   
		}
		
		
		//solo administrador puede quitar o agregar menu	
	 	if(perfilid==6 || perfilid==7){
			List<Menu> menulist = new ArrayList<Menu>();
			
			if(crearUsuarioForm.getMenus()!=null){
				String[] parts02=crearUsuarioForm.getMenus().split(",");
					for(int i = 0; i < parts02.length; i++){		
					
					Menu m = em3.find(Menu.class, Integer.parseInt(parts02[i]));
									
					menulist.add(m);										
								
					}
				}
			usuario.setMenus(menulist);
			
			}
	 	
	 	//solo administrador puede quitar o agregar menu	
	 	if(perfilid==6 || perfilid==7){
			List<Vehiculo> vehiculolist = new ArrayList<Vehiculo>();
			
			if(crearUsuarioForm.getVehiculos()!=null){
				String[] parts03=crearUsuarioForm.getVehiculos().split(",");
					for(int i = 0; i < parts03.length; i++){		
					
					Vehiculo v = em3.find(Vehiculo.class, parts03[i]);
									
					vehiculolist.add(v);										
								
					}
				}
			usuario.setVehiculos(vehiculolist);
			
			}
		
		
		try {
			
			em.getTransaction().begin();		
			em.merge(usuario);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			grabado = false;
		}					
				
		em.clear();
		em.close();
		em3.clear();
		em3.close();
		
		return grabado;
	}
	
	
	public static Boolean cambiarClave(String userrut,String newpassword){

		Boolean grabado=true;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		em = emf.createEntityManager();			
		
		Usuario usuario = em.find(Usuario.class, userrut);

		usuario.setUsuClave(newpassword);
		
		em.getTransaction().begin();
		em.merge(usuario);
		em.getTransaction().commit();
						
		return grabado;
	}
	
			
	public static UsuarioLogin findUsuarioRut(String usutrut){

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		em = emf.createEntityManager();		
		
		Usuario usuarioobj = em.find(Usuario.class, usutrut);

		UsuarioLogin usuariol = new UsuarioLogin();
						
						
			usuariol.setCorreo(usuarioobj.getUsuMail());
			usuariol.setNombre(usuarioobj.getUsuNombre());
			usuariol.setUsername(usuarioobj.getUsuLogin());
			
			usuariol.setIdRol((long) 1);
			usuariol.setCliente(usuarioobj.getCliente().getCliRazonSocial());
			usuariol.setClienterut(usuarioobj.getCliente().getCliRut());
			usuariol.setPassword(usuarioobj.getUsuClave());
			
		
		return usuariol;
	}
	
	public static UsuarioLogin findUsuarioLogin(String usulogin){
	
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		eml = emf.createEntityManager();		
				
	
		UsuarioLogin usuariol = new UsuarioLogin();
						
					
			Query query = eml.createQuery("select u from Usuario u where u.usuLogin = :usulogin ");
			query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
			query.setParameter("usulogin", usulogin);	
			
			Usuario usuarioobj = new Usuario();
			
			try {
				
				usuarioobj = (Usuario) query.getSingleResult();
				
				try {
					
					Cliente cliente = findCliente(usuarioobj.getCliente().getCliRut());
					usuariol.setCliente(cliente.getCliRazonSocial());
					usuariol.setClienterut(cliente.getCliRut());
					usuariol.setCliUsuSamtech(usuarioobj.getCliente().getCliUsuSamtech());
					usuariol.setCliPassSamtech(usuarioobj.getCliente().getCliPassSamtech());
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
				Perfil perfil = new Perfil();
				
				perfil = UsuarioService.getPerfilbyUsuRut(usuarioobj.getUsuRut());
				
				usuariol.setPerfilid(perfil.getPerId());
				usuariol.setPerfilnombre(perfil.getPerNombre());
				
				//este se usara para la edicion
				List<Menu> menus = new ArrayList<Menu>();	
				menus = UsuarioService.findMenuByUsuario(usuarioobj, perfil.getPerId());
				
				ArrayList<MenuActive> menuActivesi  = new ArrayList<MenuActive>();	
				for (Menu m1 : menus) {
					MenuActive ma1 = new  MenuActive();	
					ma1.setMenLink(m1.getMenLink());
					ma1.setMenNombre(m1.getMenNombre());					
					
					menuActivesi.add(ma1);
				}
				usuariol.setMenus(menuActivesi);
				
				List<Menu> menus03 = new ArrayList<Menu>();	
				menus03 = UsuarioService.findNoMenuByUsuario(usuarioobj,  perfil.getPerId());	
				ArrayList<MenuActive> menuActiveno  = new ArrayList<MenuActive>();
				for (Menu m2 : menus03) {
					MenuActive ma2 = new  MenuActive();	
					ma2.setMenLink(m2.getMenLink());
					ma2.setMenNombre(m2.getMenNombre());					
					
					menuActiveno.add(ma2);
				}
				usuariol.setMenusno(menuActiveno);
				//fin edicion menus
				
				//este para imprimir el menu respectivo al usuario
				ArrayList<Modulo> modulos  = new ArrayList<Modulo>();								
				modulos=UsuarioService.findAllModulos(usuarioobj);
				
				ArrayList<ModuloActive> listmodulos  = new ArrayList<ModuloActive>();
				
				//Recorro modulos para llenar el menu que le corresponda
				for (Modulo m : modulos) {					
					
					ModuloActive modulo = new ModuloActive();
					
					modulo.setModNombre(m.getModNombre());
					if(m.getModId()==1){
						modulo.setClassdiv("fa fa-road");						
					}
					if(m.getModId()==2){
						modulo.setClassdiv("fa fa-group");						
					}
					if(m.getModId()==3){
						modulo.setClassdiv("fa fa-truck");						
					}
					if(m.getModId()==4){
						modulo.setClassdiv("fa fa-line-chart");						
					}
					if(m.getModId()==5){
						modulo.setClassdiv("fa fa-plus-square");						
					}
					
					ArrayList<MenuActive> menuActiveModulo  = new ArrayList<MenuActive>();
					List<Menu> menus02 = new ArrayList<Menu>();	
					menus02 = UsuarioService.findMenuByUsuarioModulo(usuarioobj, m, "SI");
					for (Menu m3 : menus02) {
						MenuActive ma3 = new  MenuActive();	
						
						
						
						ma3.setMenId(m3.getMenId());
						ma3.setMenLink(m3.getMenLink());
						ma3.setMenNombre(m3.getMenNombre());	
						ma3.setMenIdMen(String.valueOf(m3.getMenIdMen()));
						ma3.setValsubme("NO");
						
						String submenuS = "";
						if(m3.getMenIdMen()==-1){
							
							List<Menu> sublist = new ArrayList<Menu>();		
							sublist = UsuarioService.findSubMenuByMenu(ma3.getMenId(), usuarioobj);
							if(sublist.size()>0){
								
								submenuS = "SI";
								
							}
						}
																		
						if(m3.getMenId()==1 ||m3.getMenId()==2 ){
							
							ma3.setTarget("target='_blank'"); 
						}
						
						//menu anidado
						List<Menu> listsubmenu = new ArrayList<Menu>();	
						List<SubMenuActive> listsubmenu2 = new ArrayList<SubMenuActive>();	
						if(ma3.getMenIdMen().equals("-1")){
								
							listsubmenu = UsuarioService.findSubMenuByMenu(ma3.getMenId(), usuarioobj);
							for (Menu submenu : listsubmenu) {	
								SubMenuActive submenuAtcive = new SubMenuActive();
								submenuAtcive.setMenLink(submenu.getMenLink());
								submenuAtcive.setMenNombre(submenu.getMenNombre());
								
								//submenu.getMenId()
								
								listsubmenu2.add(submenuAtcive);
							}
							
							ma3.setSubmenus(listsubmenu2);
							
							ma3.setValsubme("SI");
							
						}//fin menu anidado
						
						if(ma3.getMenIdMen().equals("0") || ma3.getMenIdMen().equals("-1")){
							
							if(ma3.getMenIdMen().equals("-1") && submenuS.equals("SI")){
							
								menuActiveModulo.add(ma3);
							}
							if(ma3.getMenIdMen().equals("0")) {
								
								menuActiveModulo.add(ma3);
								
							}
							
						}
					}//fin recorrido por menu
																
					modulo.setMenus(menuActiveModulo);
					
					listmodulos.add(modulo);
					
					
				}
				
				usuariol.setModulos(listmodulos);
				
				
				usuariol.setCorreo(usuarioobj.getUsuMail());
				usuariol.setNombre(usuarioobj.getUsuNombre());
				usuariol.setUsername(usuarioobj.getUsuLogin());
				usuariol.setApellido(usuarioobj.getUsuApellido());
				usuariol.setIdRol((long) 1);
				usuariol.setPassword(usuarioobj.getUsuClave());
				usuariol.setEstado(usuarioobj.getUsuEstado());
				usuariol.setDireccion(usuarioobj.getUsuDireccion());
				usuariol.setRut(usuarioobj.getUsuRut());
				
				
			} catch (Exception e) {
				//System.out.println(e);
			}
			
			eml.clear();
			eml.close();
		
		return usuariol;
	}

	public static Boolean addClienteAcceso(String login,String ip) {
		

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		em1 = emf.createEntityManager();
		InetAddress IP;
		
		
		String ipAddres = null;
	    try {

            IP = InetAddress.getLocalHost();

            System.out.println("Mi ip local es = "+IP.getHostAddress());
            ipAddres = IP.getHostAddress();

       } catch (UnknownHostException e) {
           e.printStackTrace();
       }
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		try {
			em1.getTransaction().begin();
			em1.createNativeQuery("insert into cliente.acceso (login, ip,servicio, fechahora) values('"+login+"','"+ip+"','dashboard.html','"+timestamp+"')").executeUpdate();
			em1.getTransaction().commit();
		}catch(Exception e) {
			System.out.println(e);
		}
		//insert into cliente.acceso(login, ip,servicio, fechahora)
		//values('prueba','192.168.1.5','historico.html','12/01/2019 00:00:00')
		return true;
	}
	
	public static Usuario findUsuarioXUsuLogin(String usulogin){

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		em1 = emf.createEntityManager();		
		
				
			Query query = em1.createQuery("select u from Usuario u where u.usuLogin = :usulogin ");
			query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
			query.setParameter("usulogin", usulogin);	
			
			Usuario usuarioobj = new Usuario();
			
			try {
				
				usuarioobj = (Usuario) query.getSingleResult();
				
				
			} catch (Exception e) {
				//System.out.println(e);
			}
						
		
		return usuarioobj;
	}
	
		
	public static Usuario findUsuario(String usurut){

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		em = emf.createEntityManager();		
		Usuario usuario = em.find(Usuario.class, usurut);
				
		return usuario;
	}
	
	
public static ArrayList<UsuarioLogin> allUsuarios(){
		
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
	
		em = emf.createEntityManager();				
							
		TypedQuery<Usuario> alluser= em.createNamedQuery("Usuario.findAll", Usuario.class);		
		alluser.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		
		List<Usuario> usuarios = alluser.getResultList();
		 		
		ArrayList<UsuarioLogin> UsuarioList = new ArrayList<UsuarioLogin>();
			
		for (Usuario u : usuarios) {
			
			UsuarioLogin ul = new UsuarioLogin();					
			ul.setUsername(u.getUsuLogin());
			ul.setCorreo(u.getUsuMail());					
			ul.setNombre(u.getUsuNombre());
			try {
				ul.setCliente(u.getCliente().getCliRazonSocial());
				ul.setClienterut(u.getCliente().getCliRut());
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			ul.setApellido(u.getUsuApellido());
			ul.setRut(u.getUsuRut());
			UsuarioList.add(ul);
						
		}
			
		return UsuarioList;
	}

public static ArrayList<UsuarioLogin> allUsuariosbyCliente(String rutcliente){
	
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");

	em4 = emf.createEntityManager();	
	
	Query query = em4.createQuery("select u from Usuario u where u.cliente.cliRut = :clienterut"
			+ ""
			+ " ");
	query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
	query.setParameter("clienterut", rutcliente);	
	
	List<Usuario> usuarios = query.getResultList();
	 		
	ArrayList<UsuarioLogin> UsuarioList = new ArrayList<UsuarioLogin>();
		
	for (Usuario u : usuarios) {
		
		UsuarioLogin ul = new UsuarioLogin();					
		ul.setUsername(u.getUsuLogin());
		ul.setCorreo(u.getUsuMail());					
		ul.setNombre(u.getUsuNombre());
		try {
			ul.setCliente(u.getCliente().getCliRazonSocial());
			ul.setClienterut(u.getCliente().getCliRut());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		Perfil perfil = new Perfil();		
		perfil = UsuarioService.getPerfilbyUsuRut(u.getUsuRut());		
		
		try {
		
		ul.setPerfilid(perfil.getPerId());
		ul.setPerfilnombre(perfil.getPerNombre());
		
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		ul.setApellido(u.getUsuApellido());
		ul.setRut(u.getUsuRut());
		UsuarioList.add(ul);
					
	}
	
	//em4.clear();
	//em4.close();
	
	return UsuarioList;
}

//Solo para consultar operador admin
public static ArrayList<UsuarioLogin> allUsuariosbyPerfil(String rutcliente){
	
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");

	em4 = emf.createEntityManager();	
	
	Query query = em4.createQuery("select u from Usuario u where u.cliente.cliRut = :clienterut"
			+ ""
			+ " ");
	query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
	query.setParameter("clienterut", rutcliente);	
	
	List<Usuario> usuarios = query.getResultList();
	 		
	ArrayList<UsuarioLogin> UsuarioList = new ArrayList<UsuarioLogin>();
		
	for (Usuario u : usuarios) {
		
		UsuarioLogin ul = new UsuarioLogin();					
		ul.setUsername(u.getUsuLogin());
		ul.setCorreo(u.getUsuMail());					
		ul.setNombre(u.getUsuNombre());
		try {
			ul.setCliente(u.getCliente().getCliRazonSocial());
			ul.setClienterut(u.getCliente().getCliRut());
		} catch (Exception e) {
			// TODO: handle exception
		}
				
		ul.setApellido(u.getUsuApellido());
		ul.setRut(u.getUsuRut());
		
		
		Perfil perfil = new Perfil();
		
		perfil = UsuarioService.getPerfilbyUsuRut(u.getUsuRut());
		
		int idperfil = 0;
		try {
			
			idperfil = perfil.getPerId();
			ul.setPerfilid(perfil.getPerId());
			ul.setPerfilnombre(perfil.getPerNombre());
			
		} catch (Exception e) {
			System.out.print(e);
		}
	
		
		if(idperfil ==7 || idperfil ==8){
		
			UsuarioList.add(ul);
		
		}
					
	}//fin for
	
	//em4.clear();
	//em4.close();
	
	return UsuarioList;
}


	public static ArrayList<Usuario> allUsuariosbyClienteRut(String clienterut){
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");

	em4 = emf.createEntityManager();				
	
	Query query = em4.createQuery("select u from Usuario u where u.cliente.cliRut = :clienterut"
			+ ""
			+ " ");
	query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
	query.setParameter("clienterut", clienterut);	
	
	ArrayList<Usuario> usuarios  = new ArrayList<Usuario>(query.getResultList());
			
	em4.clear();
	em4.close();
	return usuarios;
}
	
	public static ArrayList<Usuario> allSiUsuariosbyClienteRutPatente(String clienterut, String patente){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");

		em4 = emf.createEntityManager();				
		
		Query query = em4.createQuery("select u from Usuario u where u.cliente.cliRut = :clienterut and u.usuRut "
				+ "in "
				+ "(select uv.usuRutUsuario from UsuarioVehiculo uv where uv.vehPatenteVehiculo = :patente ) "
				);
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		query.setParameter("clienterut", clienterut);	
		query.setParameter("patente", patente);	
		
		ArrayList<Usuario> usuarios  = new ArrayList<Usuario>(query.getResultList());
		
			
		em4.clear();
		em4.close();
		return usuarios;
	}
	
	public static ArrayList<Usuario> allNoUsuariosbyClienteRutPatente(String clienterut, String patente){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");

		em4 = emf.createEntityManager();				
		
		Query query = em4.createQuery("select u from Usuario u where u.cliente.cliRut = :clienterut and u.usuRut "
				+ "not in "
				+ "(select uv.usuRutUsuario from UsuarioVehiculo uv where uv.vehPatenteVehiculo = :patente ) "
				
				);
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		query.setParameter("clienterut", clienterut);
		query.setParameter("patente", patente);
		
		ArrayList<Usuario> usuarios  = new ArrayList<Usuario>(query.getResultList());
		
			
		em4.clear();
		em4.close();
		return usuarios;
	}
	
	

	public static ArrayList<Cliente> allCliente(){
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");

	em4 = emf.createEntityManager();				
	
	TypedQuery<Cliente> query= em4.createNamedQuery("Cliente.findAll", Cliente.class);		
	
	query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
	
	ArrayList<Cliente> clientes  = new ArrayList<Cliente>(query.getResultList());
		
	em4.clear();
	em4.close();
	return clientes;
}
	
	public static Cliente findCliente(String rutcliente){

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		em5 = emf.createEntityManager();		
		Cliente cliente = em5.find(Cliente.class, rutcliente);
				
		return cliente;
	}
	
	public static ArrayList<Cliente> findClientes(String rutcliente){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");

		em4 = emf.createEntityManager();				
		
		Query query= em4.createQuery("Select c from Cliente c where c.cliRut = :rutcliente ");				
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		query.setParameter("rutcliente", rutcliente);
				
		ArrayList<Cliente> clientes  = new ArrayList<Cliente>(query.getResultList());
			
		em4.clear();
		em4.close();
		return clientes;
	}
	
	public static ArrayList<Modulo> findAllModulos(Usuario usuario){

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		em1 = emf.createEntityManager();		
				
			Query query = em1.createQuery("select m from Modulo m	  "
					+ ""
					+ "where m.modId in "
					+ " (select me.modulo.modId from Menu me where me.menId in "
					+ " (select um.menIdMenu from UsuarioMenu um where um.usuRutUsuario = :rutsuario ) "
					+ ") "
					+ " order by m.modId  ");
			query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
			query.setParameter("rutsuario", usuario.getUsuRut());
						
			ArrayList<Modulo> modulos  = new ArrayList<Modulo>(query.getResultList());
		
		return modulos;
	}
	
	public static ArrayList<Menu> findMenuByUsuarioModulo(Usuario usuario, Modulo modulo, String sub){

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		em1 = emf.createEntityManager();		
		String sql = "  ";
		//para filtrar Sub menu, submenus mayor a 0
		if(sub.equals("SI")){
			
			sql = " and m.menIdMen <= 0 "; // menu normal 0, submenu -1 
		
			//sql = sql + " and  m.menId in (select um.menIdMenu from UsuarioMenu um where um.usuRutUsuario = :rutsuario ) ";
			//sql = sql + "  and um.usuRutUsuario = :rutsuario and m.menId = um.menIdMenu  "; //and um.menIdMenu in (select um2.menIdMenu from UsuarioMenu um2 where m.menId = um2.menIdMenu)
			
			//sql = sql + " and  m.menId in ( select m2.menIdMen from Menu m2 where m.menId = m2.menIdMen )  ";			
			sql = sql + " and  m.menId in ( select um.menIdMenu from UsuarioMenu um where um.usuRutUsuario = :rutsuario  )  ";
			
			
		}else{// no es submenu, verificar que no tenga submenu para no imprimir el parent
			
			sql = "";
			
		}
		
		
					
			Query query = em1.createQuery("select m from Menu m  "
					+ "where m.modulo = :modulo  "
					+ sql
					//+ "and m.menId in "
					//+ "(select um.menIdMenu from UsuarioMenu um where um.usuRutUsuario = :rutsuario ) "
					+ " order by m.menId  ");
			query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
			query.setParameter("modulo", modulo);
			query.setParameter("rutsuario", usuario.getUsuRut());
						
			ArrayList<Menu> menus  = new ArrayList<Menu>(query.getResultList());
		
		return menus;
	}
	
	public static ArrayList<Menu> findMenuByUsuario(Usuario usuario, Integer idperfil){

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		em1 = emf.createEntityManager();	
		
		int estado = 1;
		String sql = "";
		
		//admin samtech
		if(idperfil == 6){
			
			
			 sql = "and :estado = :estado ";
			
		}else if(idperfil == 7 || idperfil == 8  ){
			//admin operador

			 estado = 1;
			 sql = "and m.menEstado = :estado ";
		}else {
			 estado = 1;
			 sql = "and m.menEstado = :estado ";
			
		}
		
		Query query = em1.createQuery("select m from Menu m "
				+ "where  "
				+ " m.menId in "
				+ "(select um.menIdMenu from UsuarioMenu um where um.usuRutUsuario = :rutsuario ) "
				+ sql
				//+ "and m.menEstado = :estado "
				+ " order by m.menId  ");
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");		
		query.setParameter("rutsuario", usuario.getUsuRut());
		query.setParameter("estado", estado);
					
		ArrayList<Menu> menus  = new ArrayList<Menu>(query.getResultList());
	
	return menus;
	}
	
	public static ArrayList<MenuActive> findMenuByModulo( Modulo modulo, String sub){

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		em1 = emf.createEntityManager();		
		String sql = "  ";
		//para filtrar Sub menu, submenus mayor a 0
		if(sub.equals("SI")){
			
			sql = " and m.menIdMen <= 0 "; // menu normal 0, submenu padre -1 
				
		}else{
			
			sql = "";
			
		}				
			Query query = em1.createQuery("select m from Menu m  "
					+ "where m.modulo = :modulo  "
					+ sql
					+ " order by m.menId  ");
			query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
			query.setParameter("modulo", modulo);
		
						
			ArrayList<Menu> menus  = new ArrayList<Menu>(query.getResultList());
			
			ArrayList<MenuActive> meList = new ArrayList<MenuActive>();
				
			for (Menu m : menus) {
				
				MenuActive ma = new MenuActive(); 
				
				ma.setMenId(m.getMenId());
				ma.setMenLink(m.getMenLink());
				ma.setMenNombre(m.getMenNombre());
				
				ModuloActive moduloactive = new ModuloActive(); 
				
				moduloactive.setModId(modulo.getModId());
				moduloactive.setModNombre(modulo.getModNombre());
								
				ma.setModulo(moduloactive);
				
				//llenamos submenu
				Query query2 = em1.createQuery("select m from Menu m  "
						+ "where m.modulo = :modulo  "
						+ "and m.menIdMen = :menuid "
						//+ sql
						+ " order by m.menId  ");
				query2.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
				query2.setParameter("modulo", modulo);
				query2.setParameter("menuid", m.getMenId());
				
				ArrayList<Menu> menus02  = new ArrayList<Menu>(query2.getResultList());
				
				ArrayList<SubMenuActive> submeList = new ArrayList<SubMenuActive>();
				
				
				for (Menu sm : menus02) {
					
					SubMenuActive subm = new SubMenuActive();
					
					subm.setMenId(sm.getMenId());
					subm.setMenLink(sm.getMenLink());
					subm.setMenNombre(sm.getMenNombre());
					
					submeList.add(subm);
					
					
				}//fin for submenu
				
				ma.setMenIdMen(String.valueOf(m.getMenIdMen()));
				
				ma.setSubmenus(submeList);
								
				meList.add(ma);
							
			}
			
			em1.clear();
			em1.close();
		
		
		return meList;
	}
	
	public static ArrayList<MenuActive> findMenuByPadre( Modulo modulo, String menuid){

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		em1 = emf.createEntityManager();		
		String sql = "  ";
		
		
			Query query = em1.createQuery("select m from Menu m  "
					+ "where m.modulo = :modulo  "
					+ "and m.menIdMen = :menuid "
					+ sql
					+ " order by m.menId  ");
			query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
			query.setParameter("modulo", modulo);
			query.setParameter("menuid", menuid);
		
						
			ArrayList<Menu> menus  = new ArrayList<Menu>(query.getResultList());
			
			ArrayList<MenuActive> meList = new ArrayList<MenuActive>();
				
			for (Menu m : menus) {
				
				MenuActive ma = new MenuActive(); 
				
				ma.setMenId(m.getMenId());
				ma.setMenLink(m.getMenLink());
				ma.setMenNombre(m.getMenNombre());
				
				ModuloActive moduloactive = new ModuloActive(); 
				
				moduloactive.setModId(modulo.getModId());
				moduloactive.setModNombre(modulo.getModNombre());
								
				ma.setModulo(moduloactive);
				
				meList.add(ma);
							
			}
			
			em1.clear();
			em1.close();
		
		
		return meList;
	}
	
	public static ArrayList<Menu> findNoMenuByUsuario(Usuario usuario, Integer idperfil){

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		em1 = emf.createEntityManager();		
					
		int estado = 1;
		String sql = "";
		
		//admin samtech
		if(idperfil == 6){
			
			
			 sql = "and :estado = :estado ";
			
		}//admin operador
		else if(idperfil == 7 || idperfil == 8  ){
			//admin operador

			 estado = 1;
			 sql = "and m.menEstado = :estado ";
		}else {
			 estado = 1;
			 sql = "and m.menEstado = :estado ";
			
		}
		
		Query query = em1.createQuery("select m from Menu m "
				+ "where  "
				+ " m.menId not in "
				+ "(select um.menIdMenu from UsuarioMenu um where um.usuRutUsuario = :rutsuario ) "
				+ sql
				//+ "and m.menEstado = :estado "
				+ " order by m.menId  ");
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");		
		query.setParameter("rutsuario", usuario.getUsuRut());
		query.setParameter("estado", estado);
					
		ArrayList<Menu> menus  = new ArrayList<Menu>(query.getResultList());
	
	return menus;
	}
	
	public static ArrayList<Menu> findSubMenuByMenu(Integer menId, Usuario usuario){

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		emm = emf.createEntityManager();		
		
		Query query = emm.createQuery("select m from Menu m "
				+ "where m.menIdMen = :menId "				
				+ "and m.menId in "
				+ "(select um.menIdMenu from UsuarioMenu um where um.usuRutUsuario = :rutsuario ) "
				//+ "m.menEstado = 1 "
				+ " order by m.menId  ");
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");		
		query.setParameter("menId", menId);
		query.setParameter("rutsuario", usuario.getUsuRut());
					
		ArrayList<Menu> menus  = new ArrayList<Menu>(query.getResultList());
		
		emm.clear();
		emm.close();
				
	
	return menus;
	}
	
	public static ArrayList<Menu> findNotSubMenuByMenu(Integer menId, Usuario usuario){

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		emm2 = emf.createEntityManager();		
		
		Query query = emm2.createQuery("select m from Menu m "
				+ "where m.menIdMen = :menId "				
				+ "and m.menId not in "
				+ "(select um.menIdMenu from UsuarioMenu um where um.usuRutUsuario = :rutsuario ) "
				//+ "m.menEstado = 1 "
				+ " order by m.menId  ");
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");		
		query.setParameter("menId", menId);
		query.setParameter("rutsuario", usuario.getUsuRut());
					
		ArrayList<Menu> menus  = new ArrayList<Menu>(query.getResultList());
		
		emm2.clear();
		emm2.close();
					
		return menus;
		
	}
	
	public static Perfil getPerfilbyUsuRut(String usurut){

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		em4 = emf.createEntityManager();		
		
		//select * from cliente.perfil where per_id in (select per_id_perfil from cliente.usurio_perfil where usu_rut_usuario = '768857474')
		//--select * from cliente.perfil inner join cliente.usurio_perfil  on perfil.per_id = usurio_perfil.per_id_perfil 
		//--and   usurio_perfil.usu_rut_usuario = '768857474'
				
			Query query = em4.createQuery("select p from Perfil p where p.perId "
					+ " in (select up.perfil.perId from UsurioPerfil up where up.usuario.usuRut = :usurut )");
			query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
			query.setParameter("usurut", usurut);	
			
			Perfil perfil = new Perfil();
			
			try {
				
				perfil = (Perfil) query.getSingleResult();
				
				
			} catch (Exception e) {
				//System.out.println(e);
			}
			
		em4.clear();
		em4.close();
						
		
		return perfil;
	}
	
	public static void removeIdle(){
		
		String bd = "SGOMT";
		String appname = "PostgreSQL JDBC Driver";
		String minutos = "15";

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		em4 = emf.createEntityManager();		
		
		Query query02 = em4.createNativeQuery("SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname = '"+bd+"' AND pid <> pg_backend_pid()  "
				+ "AND state in (  'idle') AND APPLICATION_NAME = '"+appname+"' "
				+ "AND state_change < current_timestamp - INTERVAL '"+minutos+"' MINUTE; "
				+ " "
				+ " ");
						
		List<Object[]> patentelist = query02.getResultList();	
			
		em4.clear();
		em4.close();
						
		
	}
	
	public static Modulo findModulo(Integer id){

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		emmo = emf.createEntityManager();		
		Modulo modulo= emmo.find(Modulo.class, id);
		
		emmo.clear();
		emmo.close();
		
		return modulo;
	}
	
	public static Menu findMenu(Integer id){

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		emme = emf.createEntityManager();		
		Menu menu= emme.find(Menu.class, id);
		
		emme.clear();
		emme.close();
				
		return menu;
	}
	
	public static ArrayList<MenuActive> findMenuActivebyModulo(Usuario usuarioobj, Modulo modulo, String sub){

		
		ArrayList<MenuActive> menuActiveModulo  = new ArrayList<MenuActive>();
		List<Menu> menus02 = new ArrayList<Menu>();	
		menus02 = UsuarioService.findMenuByUsuarioModulo(usuarioobj, modulo, "SI");
		for (Menu m3 : menus02) {
			MenuActive ma3 = new  MenuActive();	
			
			
			
			ma3.setMenId(m3.getMenId());
			ma3.setMenLink(m3.getMenLink());
			ma3.setMenNombre(m3.getMenNombre());	
			ma3.setMenIdMen(String.valueOf(m3.getMenIdMen()));
			ma3.setValsubme("NO");
			
			String submenuS = "";
			if(m3.getMenIdMen()==-1){
				
				List<Menu> sublist = new ArrayList<Menu>();		
				sublist = UsuarioService.findSubMenuByMenu(ma3.getMenId(), usuarioobj);
				if(sublist.size()>0){
					
					submenuS = "SI";
					
				}
			}
															
			if(m3.getMenId()==1 ||m3.getMenId()==2 ){
				
				ma3.setTarget("target='_blank'"); 
			}
			
			//menu anidado
			List<Menu> listsubmenu = new ArrayList<Menu>();	
			List<SubMenuActive> listsubmenu2 = new ArrayList<SubMenuActive>();	
			if(ma3.getMenIdMen().equals("-1")){
					
				listsubmenu = UsuarioService.findSubMenuByMenu(ma3.getMenId(), usuarioobj);
				for (Menu submenu : listsubmenu) {	
					SubMenuActive submenuAtcive = new SubMenuActive();
					submenuAtcive.setMenLink(submenu.getMenLink());
					submenuAtcive.setMenNombre(submenu.getMenNombre());
					
					//submenu.getMenId()
					
					listsubmenu2.add(submenuAtcive);
				}
				
				ma3.setSubmenus(listsubmenu2);
				
				ma3.setValsubme("SI");
				
			}//fin menu anidado
			
			if(ma3.getMenIdMen().equals("0") || ma3.getMenIdMen().equals("-1")){
				
				if(ma3.getMenIdMen().equals("-1") && submenuS.equals("SI")){
				
					menuActiveModulo.add(ma3);
				}
				if(ma3.getMenIdMen().equals("0")) {
					
					menuActiveModulo.add(ma3);
					
				}
				
			}
		}//fin recorrido por menu
		
	
	return menuActiveModulo;
	}
	
	public static Boolean modificarMenu(MenuForm menuForm){

		Boolean grabado=true;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		em = emf.createEntityManager();					

		Menu menu = findMenu(Integer.parseInt(menuForm.getMenId()));
		
		menu.setMenLink(menuForm.getMenLink());
		menu.setMenNombre(menuForm.getMenNombre());
		menu.setMenEstado(Integer.parseInt(menuForm.getEstado()));
			
		Modulo modulo = new Modulo();
		try {
			 modulo = findModulo(Integer.parseInt(menuForm.getModulo()));	
		} catch (Exception e) {
			
		}
				
		menu.setModulo(modulo);
		
		/*if(menuForm.getMenupadre().equals("0")){
			menu.setMenIdMen(0); // es menu normal
			}else if (!menuForm.getMenupadre().equals("0")){
			menu.setMenIdMen(-1); // es menu padre				
	    }*/
				
		//	//submenu falta debe hacerse manual, native query
	 	
			/*List<Menu> menulist = new ArrayList<Menu>();
			
			if(crearUsuarioForm.getMenus()!=null){
				String[] parts02=crearUsuarioForm.getMenus().split(",");
					for(int i = 0; i < parts02.length; i++){		
					
					Menu m = em3.find(Menu.class, Integer.parseInt(parts02[i]));
									
					menulist.add(m);										
								
					}
				}
			
			
			
		*/
		
		
		try {
			
			em.getTransaction().begin();		
			em.merge(menu);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			grabado = false;
		}					
		
		
				
		em.clear();
		em.close();
		
		return grabado;
	}
	
	public static Boolean guardarMenu(MenuForm menuForm){

		Boolean grabado=true;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		em = emf.createEntityManager();					
		
		Menu menu = new Menu();
		menu.setMenLink(menuForm.getMenLink());
		menu.setMenNombre(menuForm.getMenNombre());
		
		if(menuForm.getMenupadre().equals("0")){
		menu.setMenIdMen(0); // es menu normal
		}else if (!menuForm.getMenupadre().equals("0")){
		menu.setMenIdMen(-1); // es menu padre				
		}
				
		menu.setMenEstado(Integer.parseInt(menuForm.getEstado()));
		 Calendar c = Calendar.getInstance();
		Timestamp t = new Timestamp(c.getTimeInMillis());
		menu.setMenFechaCrea(t);
			
		Modulo modulo = new Modulo();
		try {
			 modulo = findModulo(Integer.parseInt(menuForm.getModulo()));	
		} catch (Exception e) {
			
		}
				
		menu.setModulo(modulo);
				
		//	//submenu falta debe hacerse manual, native query
	 	
			/*List<Menu> menulist = new ArrayList<Menu>();
			
			if(crearUsuarioForm.getMenus()!=null){
				String[] parts02=crearUsuarioForm.getMenus().split(",");
					for(int i = 0; i < parts02.length; i++){		
					
					Menu m = em3.find(Menu.class, Integer.parseInt(parts02[i]));
									
					menulist.add(m);										
								
					}
				}
			
			
			
		*/
				
		try {
			
			em.getTransaction().begin();		
			em.persist(menu);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			grabado = false;
		}					
		
		
				
		em.clear();
		em.close();
		
		return grabado;
	}
	
public static ArrayList<Perfil> findPerfilByCliente(String rutcliente){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");

		em4 = emf.createEntityManager();				
		
		Query query= em4.createQuery("Select p from Perfil p where p.cliente.cliRut = :rutcliente ");				
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		query.setParameter("rutcliente", rutcliente);
				
		ArrayList<Perfil> perfiles  = new ArrayList<Perfil>(query.getResultList());
			
		em4.clear();
		em4.close();
		return perfiles;
	}

public static ArrayList<Perfil> findPerfilByClienteAdminCliente(String rutcliente){
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");

	em4 = emf.createEntityManager();				
	
	Query query= em4.createQuery("Select p from Perfil p where p.cliente.cliRut = :rutcliente "
			+ "and p.perId in (7,8) ");				
	query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
	query.setParameter("rutcliente", rutcliente);
			
	ArrayList<Perfil> perfiles  = new ArrayList<Perfil>(query.getResultList());
		
	em4.clear();
	em4.close();
	return perfiles;
}

public static Perfil findPerfilById(String id){
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");

	em4 = emf.createEntityManager();				
	
	Query query= em4.createQuery("Select p from Perfil p where p.perId = :id ");				
	query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
	query.setParameter("id", Integer.parseInt(id));
			
	Perfil perfil  = (Perfil) query.getSingleResult();
		
	em4.clear();
	em4.close();
	return perfil;
}

public static Boolean modificarModulo(ModuloForm moduloForm){

	Boolean grabado=true;
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
	em = emf.createEntityManager();					

	Modulo modulo = findModulo(Integer.valueOf(moduloForm.getModId()));
		
	modulo.setModNombre(moduloForm.getModNombre());
	
	try {
		
		em.getTransaction().begin();		
		em.merge(modulo);
		em.getTransaction().commit();
		
	} catch (Exception e) {
		grabado = false;
	}					
	
	em.clear();
	em.close();
	
	return grabado;
}

public static ArrayList<Menu> findSubMenuByMenuPadre(Integer menId, String todos, Integer modId){

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
	emmp = emf.createEntityManager();	
	
	String sql = "";
	
	if(todos.equals("S")){
		
		sql = " m.menIdMen = :menId	";
		
	}
	
	if(todos.equals("N")){
		
		sql = "((m.menIdMen != 10 and  m.menIdMen > 0) or  m.menIdMen = 0  ) and :menId = :menId ";
		
	}
	
	
	Query query = emmp.createQuery("select m from Menu m "
			+ "where "
			+ sql	
			+ " and m.modulo.modId = :modId "
			//+ "m.menEstado = 1 "
			+ " order by m.menId  ");
	query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");		
	query.setParameter("menId", menId);
	query.setParameter("modId", modId);
				
	ArrayList<Menu> menus  = new ArrayList<Menu>(query.getResultList());
	
	emmp.clear();
	emmp.close();

	return menus;
}

public static Boolean modificarMenuPadre(MenuForm menuForm){

	Boolean grabado=true;
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
	em = emf.createEntityManager();	
	em2 = emf.createEntityManager();	
	em3 = emf.createEntityManager();	

	Menu menu = findMenu(Integer.parseInt(menuForm.getMenId()));
	
	menu.setMenNombre(menuForm.getMenNombre());
	menu.setMenEstado(Integer.parseInt(menuForm.getEstado()));
		
			
	//deshabilitar hijos viejos -> ponerlos en 0	
	ArrayList<Menu> menus = UsuarioService.findSubMenuByMenuPadre(menu.getMenId(), "S", menu.getModulo().getModId());
	
	for (Menu m2 : menus) {
		em.getTransaction().begin();	
		m2.setMenIdMen(0);
		em.merge(m2);
		em.getTransaction().commit();
		
	}
	
 	//nuevos hijos
	List<Menu> menulist = new ArrayList<Menu>();
		
		if(menuForm.getMenus()!=null){
			String[] parts02=menuForm.getMenus().split(",");
				for(int i = 0; i < parts02.length; i++){		
				
				Menu m = em2.find(Menu.class, Integer.parseInt(parts02[i]));
				
				m.setMenIdMen(menu.getMenId());
				
				em2.getTransaction().begin();	
				em2.merge(m);
				em2.getTransaction().commit();
																			
				}
			}
					
	try {
		
		em3.getTransaction().begin();
		em3.merge(menu);
		em3.getTransaction().commit();
		
		
	} catch (Exception e) {
		grabado = false;
	}
	
	em.clear();
	em.close();			
	em2.clear();
	em2.close();
	em3.clear();
	em3.close();
	
	return grabado;
}

/**
 * Esta funcion recibe el url que se ingresa en el controlador y menu, debe ser igual
 * @author: Christopher rickblanc02@gmail.com
 * @version: 25/06/2019/A
 */
public static boolean validarMenuUsuario(UsuarioLogin usuario, String url){

	url =  url.replace("/", "");
	
	//agregamos funcion para guardar menu en el log usuariolog
	
		if(actualizarLogUsuarioHistorial(usuario, url)){
			////System.out.println("Se guardho historial usuario");			
		}//else //System.out.println("No se guardho historial usuario");
    //fin 		
				
	boolean repuesta = false;
	
	for (ModuloActive modulo : usuario.getModulos()) {
		
		for (MenuActive menu : modulo.getMenus()) {
			
			String menulink = menu.getMenLink();
			if(menulink =="" || menulink  == null ){
				
				menulink = "";
				
				//si es nulo es padre , verificamos si se valida en el hijo
				for (SubMenuActive submenu : menu.getSubmenus()) {
					
					if(submenu.getMenLink().equals(url)){						
						repuesta = true;
						break;
					}//fin if submenu
					
					
				}//fin for submenu
				
				
			}//fin if si es nulo
			
			if(menulink.equals(url)){
				
				repuesta = true;
				break;
				
			/*	for (SubMenuActive submenu : menu.getSubmenus()) {
										
					if(submenu.getMenLink().equals(url)){
						
						repuesta = true;
						
					}//fin if submenu
					
					
				}//fin for submenu */
					
			}//fin if menu
				
				
			}//fin for menu
			
			
		}//fin for modulo
	
	 //repuesta = false;
		
	  return repuesta ;
		
	}//fin funcion


/**
 * Esta funcion crea barra menu, donde se va mostrar dinamicamente en el menu
 * @author: Christopher rickblanc02@gmail.com
 * @version: 25/06/2019/A
 */
public static BarraMenuActive barraMenu(UsuarioLogin usuario, String url){

	url =  url.replace("/", "");
	
	BarraMenuActive b = new BarraMenuActive();
				
	boolean repuesta = false;
	
	for (ModuloActive modulo : usuario.getModulos()) {
		
	
		
		for (MenuActive menu : modulo.getMenus()) {
			
			String menulink = menu.getMenLink();
			if(menulink =="" || menulink  == null ){
				
				menulink = "";
				
				//si es nulo es padre , verificamos si se valida en el hijo
				for (SubMenuActive submenu : menu.getSubmenus()) {
					
					if(submenu.getMenLink().equals(url)){
						
						b.setModulo(modulo.getModNombre());
						b.setMenu(menu.getMenNombre());
						b.setSubmenu(submenu.getMenNombre());
						
						repuesta = true;
						
					}//fin if submenu
					
					
				}//fin for submenu
				
				
			}//fin if si es nulo
			
			if(menulink.equals(url)){
				
				repuesta = true;
				b.setModulo(modulo.getModNombre());
				b.setMenu(menu.getMenNombre());
				
			/*	for (SubMenuActive submenu : menu.getSubmenus()) {
										
					if(submenu.getMenLink().equals(url)){
						
						repuesta = true;
						
					}//fin if submenu
					
					
				}//fin for submenu */
					
			}//fin if menu
				
				
			}//fin for menu
			
			
		}//fin for modulo
	
	 //repuesta = false;
		
	  return b;
		
	}//fin funcion


	public static List<MenuActive3> findMenuByUsuario3(Usuario usuario, Integer idperfil){
	
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		em2 = emf.createEntityManager();		
					
		String sql = "";
		
		if(idperfil == 7 || idperfil == 8 ){
			 sql = "and t0.men_estado = 1 ";			
		}
		
		Query query = em2.createNativeQuery("  SELECT t0.men_id , case when (select men_nombre from cliente.menu t2 where t0.men_id_men = t2.men_id) is null "
				+ "then t0.men_nombre else   concat( (select men_nombre from cliente.menu t2 where t0.men_id_men = t2.men_id),' -- ',t0.men_nombre)  end "
				+ "nombre_compuesto ,  t1.mod_id, t1.mod_nombre ,  COALESCE((SELECT  'selected'::varchar(10) "
				+ "FROM cliente.usuario_menu t1 WHERE t1.usu_rut_usuario = '"+usuario.getUsuRut()+"' and t1.men_id_menu=t0.men_id  ) ,'') existe   "
				+ "FROM cliente.MENU t0, cliente.MODULO t1   WHERE   t0.mod_id_modulo = t1.mod_id "	+ sql + "  order by t1.mod_id , nombre_compuesto");
		
		List <MenuActive3> mlist = new ArrayList();
		List<Object[]> faenalist = query.getResultList();

		for (Object[] fa : faenalist) {
			MenuActive3 fa2= new MenuActive3();
				fa2.setMen_id((Integer) fa[0]);
				fa2.setMen_nombre((String) fa[1]);
				fa2.setMod_id((Integer) fa[2]);
				fa2.setMod_nombre((String) fa[3]);
				fa2.setSelected((String) fa[4]);			
			mlist.add(fa2);
		}

		em2.clear();
		em2.close();
	
	return mlist;

	}
	
	public static String guardarLogUsuario(HttpServletRequest request, UsuarioLogin usuario){

		int usuarioIdSession = 0;
		Boolean grabado=true;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		em = emf.createEntityManager();	
		
		UsuarioSession obj = new UsuarioSession();
		
		//ip remoto
		//String ip = request.getRemoteAddr();
		String ip = request.getHeader("X-FORWARDED-FOR");
		
		//navegador
		String userAgent = request.getHeader("User-Agent");
		
		String httpAccept = request.getHeader("Accept");
		UAgentInfo agent = new UAgentInfo(userAgent,httpAccept); 
		
		OS os = UtilServicio.OS.valueOf(request); 
				
		obj.setIp(ip);
		obj.setNavegador(userAgent);
		obj.setSistemaOperativo(String.valueOf(os));
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		obj.setIngreso(timestamp);
				
		obj.setUsuRut(usuario.getRut());
		obj.setUsuLogin(usuario.getUsername());
		obj.setCliRutCliente(usuario.getClienterut());
		
				
		try {
			
			em.getTransaction().begin();		
			em.persist(obj);
			em.flush();
		
			em.getTransaction().commit();
			
			usuarioIdSession = obj.getId();
			
		} catch (Exception e) {
			usuarioIdSession = 0;
		}					
		
		 				
		em.clear();
		em.close();
		
		return String.valueOf(usuarioIdSession);
	}
	
	
	public static Boolean actualizarLogUsuario(String iduser){
		
		Boolean grabado=true;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		em = emf.createEntityManager();	
		
		//UsuarioSession obj = new UsuarioSession();
		UsuarioSession obj= em.find(UsuarioSession.class, Integer.valueOf(iduser));
				
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		obj.setSalida(timestamp);
		
				
		try {
			
			em.getTransaction().begin();		
			em.merge(obj);
		
			em.getTransaction().commit();
			
			
		} catch (Exception e) {
			grabado = false;
		}					
		
		 				
		em.clear();
		em.close();
		
		return grabado;
	}
	
public static Boolean actualizarLogUsuarioHistorial(UsuarioLogin usuario, String url){
		
		Boolean grabado=true;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
		em5 = emf.createEntityManager();	
		
		//UsuarioSession obj = new UsuarioSession();
		UsuarioSession obj= em5.find(UsuarioSession.class, Integer.valueOf(usuario.getIduser()));
		
		UsuarioHistorial userhist = new UsuarioHistorial();
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		//actualizamos salida del padre
		obj.setSalida(timestamp); //deberia sumarse 30 min, en caso que no cierre session por logout
		
		//creamos hijo
		userhist.setHoraIngreso(timestamp);
		
		Menu menu = findMenuByLink(url);
		if(menu.getMenId() !=  null){
		userhist.setMenu(menu);
		}
		
		userhist.setUsuarioSession(obj);
		userhist.setUrl(usuario.getUrlservlet());
		
				
		try {
			
			em5.getTransaction().begin();
			em5.merge(obj);
			em5.persist(userhist);
		
			em5.getTransaction().commit();
			
			
		} catch (Exception e) {
			grabado = false;
		}					
		
		 				
		em5.clear();
		em5.close();
		
		return grabado;
	}

public static Menu findMenuByLink(String link){

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");	
	emb = emf.createEntityManager();		
	
			
		Query query = emb.createQuery("select u from Menu u where u.menLink = :link ");
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		query.setParameter("link", link);	
		
		Menu menu = new Menu();
		
		try {
			
			menu = (Menu) query.getSingleResult();
			
			
		} catch (Exception e) {
			//System.out.println(e);
		}
			
		emb.clear();
		emb.close();
	
	return menu;
}


public static List<UsuarioSession> findUsuarioSession(String desde, String hasta){

	String fechanulas = "";

	//dd-MM-yyyy
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
	DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); //dd/MM/yyyy
	em = emf.createEntityManager();


	Calendar calendarin = Calendar.getInstance();
	Calendar calendarfin = Calendar.getInstance();


	Date fecdesde= new Date();
	Date fechasta= new Date();

	//si las fechas vienen nulo se calcula el mes completo
	if(desde.equals("") && hasta.equals("")){

		fechanulas = "S";

	}

	try {
		fecdesde = formatteri.parse(desde);
		fechasta=  formatteri.parse(hasta);
	} catch (ParseException e) {

		e.printStackTrace();
	}

	//configuro las hora inicio y fin
	calendarin.setTime(fecdesde);
		//si las fechas vienen nul se resta el mes a la fecha inicio y fecha fin queda como dia hoy
	if(fechanulas.equals("S")){

		calendarfin.add(Calendar.DAY_OF_MONTH, 0);

	}

	calendarfin.setTime(fechasta);
	
	//las convierto timestamp para insertarlas en el query
	Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
	Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

		Query query = em.createQuery("select d from UsuarioSession d "
									+ "where 1 = 1 "
									+ "and d.ingreso >= :timein and d.ingreso <= :timefin "
									+ " order by d.ingreso ");
		
		//+ " and d.id.tolFechaHora  >= :timein and d.id.tolFechaHora <= :timefin and d.eventosGp.eveIdId = 7 ");
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		 
	 query.setParameter("timein", timein);
	 query.setParameter("timefin", timefin);
	 

	    //ArrayList<UsuarioSession> mlist = new ArrayList<UsuarioSession>();

	    ArrayList<UsuarioSession> mlist =  new ArrayList<UsuarioSession>(query.getResultList());
		Integer i = 0;

		em.clear();
		em.close();

			return mlist;
	}

public static List<UsuarioHistorial> findUsuarioHistorialDetalle(String id){

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");

	em = emf.createEntityManager();

		Query query = em.createQuery("select d from UsuarioHistorial d "
									+ "where 1 = 1 "
									+ "and d.usuarioSession.id = :id "
									+ " order by d.id desc ");
		
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");		
		query.setParameter("id", Integer.valueOf(id));

	    ArrayList<UsuarioHistorial> mlist =  new ArrayList<UsuarioHistorial>(query.getResultList());
		Integer i = 0;

		em.clear();
		em.close();

		return mlist;
	}

public static int findTurnobyNameAndFechaIni(String name, String fechaini){ // falta agregarle validad por cliente 

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");

	em = emf.createEntityManager();
	
	DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy"); //dd/MM/yyyy
	
	Calendar calendarin = Calendar.getInstance();
	
	Date fecdesde= new Date();
	
	try {
		fecdesde = formatteri.parse(fechaini);
	} catch (ParseException e) {
		
		e.printStackTrace();
	}
	
	calendarin.setTime(fecdesde);	
	//configuro las hora inicio y fin
		calendarin.set(Calendar.HOUR_OF_DAY, 00);
		calendarin.set(Calendar.MINUTE, 00);
		calendarin.set(Calendar.SECOND, 00);
		calendarin.set(Calendar.MILLISECOND, 00);
	
	//calendarin.add(Calendar.DAY_OF_MONTH, -2);
	
	Timestamp timein = new Timestamp(calendarin.getTimeInMillis());

	Query query = em.createQuery("select t.turId from Turno2 t "
									+ "where 1 = 1 "
									+ "and t.turNombre = :name "
									+ "and :timein >= t.turDesde and :timein <= t.turHasta  "
									//+ "between t.turDesde >= :fechaini and t.turHasta <= :fechaini  "
									+ "");
		
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");			
		query.setParameter("name", name);
		query.setParameter("timein", timein);
		query.setMaxResults(1);
		
		int turnoid =  1;
		
		try {
			
			turnoid = (Integer) query.getSingleResult();
			
		} catch (Exception e) {
			
			
		}

	    em.clear();
		em.close();

		return turnoid;
	}

public static int verificarSolapaTurno2(String name, String fechaini, String fechafin, String cliente){

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");

	em = emf.createEntityManager();
	
	DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy"); //dd/MM/yyyy
	
	Calendar calendarin = Calendar.getInstance();
	
	Date fecdesde= new Date();
	
	try {
		fecdesde = formatteri.parse(fechaini);
	} catch (ParseException e) {
		
		e.printStackTrace();
	}
	
	calendarin.setTime(fecdesde);	
	//configuro las hora inicio y fin
		calendarin.set(Calendar.HOUR_OF_DAY, 00);
		calendarin.set(Calendar.MINUTE, 00);
		calendarin.set(Calendar.SECOND, 00);
		calendarin.set(Calendar.MILLISECOND, 00);
	
	//calendarin.add(Calendar.DAY_OF_MONTH, -2);
	
	Timestamp timein = new Timestamp(calendarin.getTimeInMillis());

	Query query = em.createQuery("select t.turId from Turno2 t "
									+ "where 1 = 1 "
									+ "and t.turNombre = :name "
									+ "and :timein >= t.turDesde and :timein <= t.turHasta  "
									//+ "between t.turDesde >= :fechaini and t.turHasta <= :fechaini  "
									+ "");
		
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");			
		query.setParameter("name", name);
		query.setParameter("timein", timein);
		query.setMaxResults(1);
		
		int turnoid =  1;
		
		try {
			
			turnoid = (Integer) query.getSingleResult();
			
		} catch (Exception e) {
			
			
		}

	    em.clear();
		em.close();

		return turnoid;
	}

public static ArrayList<Turno2> findAllTurnos2(String rutcliente){
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");

	em4 = emf.createEntityManager();				
	
	Query query= em4.createQuery("Select t from Turno2 t where t.usuRutUsuario = :rutcliente "
			+ "order by t.turId ");				
	query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
	query.setParameter("rutcliente", rutcliente);
			
	ArrayList<Turno2> turnos  = new ArrayList<Turno2>(query.getResultList());
		
	em4.clear();
	em4.close();
	return turnos;
}

}
