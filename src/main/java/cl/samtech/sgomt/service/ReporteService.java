package cl.samtech.sgomt.service;

import java.awt.Color;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import cl.samtech.sgomt.object.ReporteHorometroActive;
import cl.samtech.sgomt.object.ReporteIgnicionActive;
import cl.samtech.sgomt.model.ColorGeo;
import cl.samtech.sgomt.model.Conductor;
import cl.samtech.sgomt.model.DatosGpsUltimo;
import cl.samtech.sgomt.model.EventosGp;
import cl.samtech.sgomt.model.Grupo;
import cl.samtech.sgomt.model.Ibuttom;
import cl.samtech.sgomt.model.Vehiculo;
import cl.samtech.sgomt.model.VehiculoDevice;
import cl.samtech.sgomt.model.Zona;
import cl.samtech.sgomt.object.CarguioActive;
import cl.samtech.sgomt.object.CarguioGraficoActive;
import cl.samtech.sgomt.object.ConductorActive;
import cl.samtech.sgomt.object.CoordenadasActive;
import cl.samtech.sgomt.object.CuadroMandoActive;
import cl.samtech.sgomt.object.DetalleXViajeActive;
import cl.samtech.sgomt.object.DiagramaCicloGraficoActive;
import cl.samtech.sgomt.object.DiagramaCicloTablaActive;
import cl.samtech.sgomt.object.DiagramaTiempoActive;
import cl.samtech.sgomt.object.DiagramaTiempoGraficoActive;
import cl.samtech.sgomt.object.DiagramaTiempoGraficoDetalleActive;
import cl.samtech.sgomt.object.EventActive;
import cl.samtech.sgomt.object.FaenaActive;
import cl.samtech.sgomt.object.FlechaActive;
import cl.samtech.sgomt.object.GeocercaUltimaGpsActive;
import cl.samtech.sgomt.object.GeocercasActive;
import cl.samtech.sgomt.object.HistoricoCanActive;
import cl.samtech.sgomt.object.IbuttomActive;
import cl.samtech.sgomt.object.LinkDataArrayActive;
import cl.samtech.sgomt.object.ListadoGeocercasActive;
import cl.samtech.sgomt.object.MapaCalorActive;
import cl.samtech.sgomt.object.MapaUltimoGPS;
import cl.samtech.sgomt.object.MapaUltimoGPSJson;
import cl.samtech.sgomt.object.NodeDataArrayActive;
import cl.samtech.sgomt.object.OperacionesActive;
import cl.samtech.sgomt.object.PatenteGPSActive;
import cl.samtech.sgomt.object.ProduccionTurnoActive;
import cl.samtech.sgomt.object.ReporteCicloTransporteActive;
import cl.samtech.sgomt.object.ReporteDescargaActive;
import cl.samtech.sgomt.object.ReporteGeocercasActive;
import cl.samtech.sgomt.object.ReporteIndicadorOptimoCargaActive;
import cl.samtech.sgomt.object.ReporteProduccionTurnoActive;
import cl.samtech.sgomt.object.ReporteResumenDescargaActive;
import cl.samtech.sgomt.object.ReporteResumenFlotaActive;
import cl.samtech.sgomt.object.ReporteTiempoCarguioActive;
import cl.samtech.sgomt.object.ReporteTiempoFueraOperacionActive;
import cl.samtech.sgomt.object.ReporteTiempoFueraOperacionDetalleActive;
import cl.samtech.sgomt.object.ReporteTiempoTransporteActive;
import cl.samtech.sgomt.object.ResumenConduccionDiarioActive;
import cl.samtech.sgomt.object.SinopticoActive;
import cl.samtech.sgomt.object.TurnoActive;
import cl.samtech.sgomt.object.VehiculoActive;
import cl.samtech.sgomt.object.ListadoColorActive;

public class ReporteService {

	private static EntityManager em;
	private static EntityManager em2;
	private static EntityManager em3;
	private static EntityManager em4;
	private static EntityManager emS;
	private static EntityManager emS2;
	private static EntityManager emSC;
	private static EntityManager emSD;
	private static EntityManager emSM;
	private static EntityManager emSG;
	private static EntityManager emc;
	private static EntityManager emD;
	private static EntityManager emD2;
	private static EntityManager eml;


	// private static String log4jConfPath =
	// "/opt/tomcat9/webapps/web/log4j.properties";
	// private static String log4jConfPath = "C:\\log4j.properties";

	final static Logger logger = Logger.getLogger(ReporteService.class);

	public static List<ListadoGeocercasActive> geoCercasCDM(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		eml = emf.createEntityManager();
		Query query = eml.createNativeQuery("select zon_id, zon_nombre from cliente.zona where  zon_tipo_uso ='c' and usu_rut_usuario='768857474' order by zon_nombre");
	
		List<Object[]> zlist = query.getResultList();
		ArrayList<ListadoGeocercasActive> mlist = new ArrayList<ListadoGeocercasActive>();
		for (Object[] v : zlist) {
			ListadoGeocercasActive listadoGeos = new ListadoGeocercasActive();
			listadoGeos.setId_geo((Integer) v[0]);
			listadoGeos.setNombre((String) v[1]);
			mlist.add(listadoGeos);
		}
		return mlist;
	}
	
	public static Date ParseFecha(String fecha)
    {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        } 
        catch (ParseException ex) 
        {
            System.out.println(ex);
        }
        return fechaDate;
    }
	
	public static List<CuadroMandoActive> controlMandoListar(String fecha, Integer turno,Integer sw,Integer destino,Integer origen) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		em = emf.createEntityManager();

		// select * from  public.InformeDashboard(@fecha,@turno,@sw,@destino,@origen,@uaurio,@clave)

		// public.InformeDashboard('10/16/2019',0,1,0,233,'emexcosa','')
		SimpleDateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy");
		
		StoredProcedureQuery query = em.createStoredProcedureQuery("public.InformeDashboard");	

		query.registerStoredProcedureParameter(1, Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(5, Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(6, String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(7, String.class, ParameterMode.IN);
		Date fechaDate = ParseFecha(fecha);

		
		query.setParameter(1, fechaDate);
		query.setParameter(2, turno);
		query.setParameter(3, sw);
		query.setParameter(4, destino);
		query.setParameter(5, origen);
		query.setParameter(6, "emexcosa");
		query.setParameter(7, "");

		ArrayList<CuadroMandoActive> mlist = new ArrayList<CuadroMandoActive>();

		query.execute();

		List<Object[]> datoslist = query.getResultList();
		
		try {
			for (Object[] dato : datoslist) {

				CuadroMandoActive cuadroactive = new CuadroMandoActive();
				
				try{
					cuadroactive.setTurno(dato[0].toString());
					cuadroactive.setOrigen(dato[1].toString());	
					cuadroactive.setId_turno((Integer)dato[3]);
					cuadroactive.setTotal_vueltas((Integer)dato[4]);
					cuadroactive.setDistancia_promedio(new Double(dato[5].toString()));
					cuadroactive.setTiempo_seg(new Double(dato[6].toString()));
					cuadroactive.setRalentixcamion(new Double(dato[20].toString()));
					cuadroactive.setCiclo_promedio(new Double(dato[9].toString()));
					cuadroactive.setProduccion_mxh(new Double(dato[10].toString()));
					cuadroactive.setTransp_kmxh(new Double(dato[11].toString()));
					cuadroactive.setVel_prom(new Double(dato[12].toString()));
					cuadroactive.setId_destino((Integer)dato[13]);
					cuadroactive.setDestino_str(dato[14].toString());
					cuadroactive.setTiempo_ciclo_promedio(new Double(dato[15].toString()));
					cuadroactive.setNum_camiones((new Double(dato[16].toString())).intValue()    );
					cuadroactive.setVueltaxcamion(new Double(dato[18].toString()));
					cuadroactive.setHorasxcamion(new Double(dato[21].toString()));
					cuadroactive.setId_geo((Integer)dato[23]);
					if(sw == 3){
						cuadroactive.setPatente(dato[22].toString());
					}
				}catch(NullPointerException e){
					e.getMessage();
					System.out.println(e);
				}
				mlist.add(cuadroactive);
			}
			
		}catch(NullPointerException e) {
			System.out.println(e);

		}


		return mlist;

	}
	
	

	public static List<ReporteIndicadorOptimoCargaActive> findIndicadorOptimoCarga(String patente, String desde,
			String hasta) {

		String fechanulas = "";

		// dd-MM-yyyy
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy"); // dd/MM/yyyy
		em = emf.createEntityManager();

		Calendar calendarin = Calendar.getInstance();
		Calendar calendarfin = Calendar.getInstance();

		Date fecdesde = new Date();
		Date fechasta = new Date();

		// si las fechas vienen nulo se calcula el mes completo
		if (desde.equals("") && hasta.equals("")) {

			fechanulas = "S";

		}

		try {
			fecdesde = formatteri.parse(desde);
			fechasta = formatteri.parse(hasta);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		// configuro las hora inicio y fin
		calendarin.setTime(fecdesde);
		calendarin.add(Calendar.HOUR_OF_DAY, 00);
		calendarin.add(Calendar.MINUTE, 00);
		calendarin.add(Calendar.SECOND, 00);

		// si las fechas vienen nul se resta el mes a la fecha inicio y fecha
		// fin queda como dia hoy
		if (fechanulas.equals("S")) {

			calendarfin.add(Calendar.DAY_OF_MONTH, -2);

		}

		calendarfin.setTime(fechasta);
		calendarfin.add(Calendar.HOUR_OF_DAY, 23);
		calendarfin.add(Calendar.MINUTE, 59);
		calendarfin.add(Calendar.SECOND, 59);

		// las convierto timestamp para insertarlas en el query
		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
		Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

		/*
		 * Query query = em.createQuery("select m from Listadoot m " //+
		 * "where m.fechacreacion>=:fc and  m.fechacreacion<=:ff " +
		 * "where m.fechainicio>=:fc and  m.fechatermino<=:ff " +
		 * "and m.flotatransporte.patente = :patente " +
		 * "order by m.fechainicio desc ");
		 * query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		 * 
		 * query.setParameter("fechain", timein); query.setParameter("fechafin",
		 * timefin); query.setParameter("patente", patente);
		 */

		// List resultList = query.getResultList();
		ArrayList<ReporteIndicadorOptimoCargaActive> rlist = new ArrayList<ReporteIndicadorOptimoCargaActive>();

		// Se construye Luego
		/*
		 * if (resultList != null && !resultList.isEmpty()) {
		 * 
		 * for (Listadoot m : resultList) {
		 * 
		 * 
		 * ReporteIndicadorOptimoCargaActive r=new
		 * ReporteIndicadorOptimoCargaActive();
		 * 
		 * 
		 * l.setGlosa(m.getGlosa()); l.setTipoMantencion(m.getTipomantencion());
		 * l.setGlosa(m.getGlosa()); l.setNumeroOT(m.getNumeroot());
		 * l.setStatus(m.getStatus());
		 * 
		 * l.setFechaCreacion(formatter.format(m.getFechacreacion()));
		 * l.setFechaInicioExtrema(formatter.format(m.getFechainicioextrema()));
		 * l.setFechaFinExtrema(formatter.format(m.getFechafinextrema()));
		 * l.setKilometrajeProximaMantencion(m.getKilometrajeproximamantencion()
		 * .intValue());
		 * 
		 * 
		 * lot.add(l); }
		 * 
		 * 
		 * }
		 */

		// llenamos manual por ahora
		ReporteIndicadorOptimoCargaActive r01 = new ReporteIndicadorOptimoCargaActive();
		ReporteIndicadorOptimoCargaActive r02 = new ReporteIndicadorOptimoCargaActive();
		ReporteIndicadorOptimoCargaActive r03 = new ReporteIndicadorOptimoCargaActive();
		ReporteIndicadorOptimoCargaActive r04 = new ReporteIndicadorOptimoCargaActive();
		ReporteIndicadorOptimoCargaActive r05 = new ReporteIndicadorOptimoCargaActive();
		ReporteIndicadorOptimoCargaActive r06 = new ReporteIndicadorOptimoCargaActive();

		// { x: new Date(2018, 10, 24, 9, 33, 30, 0), y: 74.2},
		// { x: new Date(2018, 10, 24, 10, 33, 30, 0), y: 23.4},

		// Reporte
		r01.setNrovuelta(2);
		r01.setHorasdespacho("09:33");
		r01.setIdcamion("HHFD98");
		r01.setCargareal(36.4833);
		r01.setCarganominal(30);
		r01.setPorcentajedecarga(122);

		// Grafico
		// model { x: new Date(2018, 10, 24, 09, 33, 30, 0), y: 74.2}
		r01.setAno(2018);
		r01.setMes(10);
		r01.setDia(24);
		r01.setHora(9);// revisar si funciona sin cero
		r01.setMin(33);
		r01.setSeg(30);
		r01.setTonelada(36.4833);

		r02.setNrovuelta(2);
		r02.setHorasdespacho("10:33");
		r02.setIdcamion("HKPC22");
		r02.setCargareal(23.4833);
		r02.setCarganominal(30);
		r02.setPorcentajedecarga(122);

		r02.setAno(2018);
		r02.setMes(10);
		r02.setDia(24);
		r02.setHora(10);// revisar si funciona sin cero
		r02.setMin(33);
		r02.setSeg(30);
		r02.setTonelada(23.4833);

		rlist.add(r01);

		// em.clear();
		// em.close();
		return rlist;
	}

	public static List<ReporteTiempoCarguioActive> findTiempoCarguio(String patentes, String faena, String desde,
			String hasta, String cliente) {

		String fechanulas = "";

		// dd-MM-yyyy
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy"); // dd/MM/yyyy
		em3 = emf.createEntityManager();

		Calendar calendarin = Calendar.getInstance();
		Calendar calendarfin = Calendar.getInstance();

		Date fecdesde = new Date();
		Date fechasta = new Date();

		// si las fechas vienen nulo se calcula el mes completo
		if (desde.equals("") && hasta.equals("")) {

			fechanulas = "S";

		}

		try {
			fecdesde = formatteri.parse(desde);
			fechasta = formatteri.parse(hasta);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		// configuro las hora inicio y fin
		calendarin.setTime(fecdesde);
		calendarin.add(Calendar.HOUR_OF_DAY, 00);
		calendarin.add(Calendar.MINUTE, 00);
		calendarin.add(Calendar.SECOND, 00);

		// si las fechas vienen nul se resta el mes a la fecha inicio y fecha
		// fin queda como dia hoy
		if (fechanulas.equals("S")) {

			calendarfin.add(Calendar.DAY_OF_MONTH, -2);

		}

		calendarfin.setTime(fechasta);
		calendarfin.add(Calendar.HOUR_OF_DAY, 23);
		calendarfin.add(Calendar.MINUTE, 59);
		calendarfin.add(Calendar.SECOND, 59);

		// las convierto timestamp para insertarlas en el query
		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
		Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

		// para jpa se para un array para el in, y no lleva parentesis
		String[] parts = patentes.split(",");
		List<String> plista = Arrays.asList(parts);

		String sql = "";

		if (!patentes.equals(null)) {

			sql = " and v.vehPatente in :patentes ";

		} else {

			sql = "";
		}

		Query query = em3
				.createQuery("select v from Vehiculo v where 1 = 1 " + sql + " and v.cliente.cliRut = :cliente "
		// + "and v.tipoVehiculo.tipvId in (11, 19, 22, 25, 26, 27, 28) ");
						+ "and v.tipoVehiculo.tipo in ('m') " + " ");
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		query.setParameter("cliente", cliente);
		if (!patentes.equals(null)) {

			query.setParameter("patentes", plista);

		}

		List<Vehiculo> vlist = query.getResultList();

		ArrayList<ReporteTiempoCarguioActive> rlist = new ArrayList<ReporteTiempoCarguioActive>();
		ArrayList<VehiculoActive> vclist = new ArrayList<VehiculoActive>();

		for (Vehiculo v : vlist) {

			// ReporteTiempoCarguioActive r=new ReporteTiempoCarguioActive();

			// aqui procedimiento que llena reportecarguio x patente

			em2 = emf.createEntityManager();

			Query query02 = em2.createNativeQuery(
					"SELECT rf_fecha_hora, rf_patente_camion, rf_latitud, rf_longitud, rf_geocoding, veh_num_interno "
							+ " FROM datos_rfid , dispositivo.vehiculo" + " where rf_patente_maquina = '"
							+ v.getVehPatente() + "' " + " and rf_patente_camion notnull "
							+ " and rf_patente_camion = veh_patente " + " and rf_fecha_hora >= '" + timein
							+ "' and rf_fecha_hora <= '" + timefin + "' " + " ORDER BY rf_fecha_hora " + " " + " ");

			List<Object[]> rfidlist = query02.getResultList();
			Integer i = 0;
			Integer i2 = 0;

			// reglas
			// si la patente cambia, se para el proceso
			// si el tiempo entre registro mas de 60 seg se para el proceso

			String patentecamion = "";
			String numerointerno = "";
			int duracionseg = 0;
			int duracionseg2 = 0;
			Date fechaentrada = new Date();
			Date fechasalida = new Date();
			Timestamp fecha = new Timestamp(fechaentrada.getTime());
			Timestamp fechainicio = new Timestamp(fechaentrada.getTime());
			Timestamp fechainicio2 = new Timestamp(fechaentrada.getTime());
			Timestamp fechafin = new Timestamp(fechasalida.getTime());

			String e_repetida = "NO";
			int pases = 0;
			Double cargareal = 0.0;
			String tiempocarguio = "";
			String lat = "";
			String lon = "";
			String ubicacion = "";
			String ciclomayoruno = "N";

			for (Object[] rf : rfidlist) {
				i++;
				i2++;

				Timestamp fecha2 = (Timestamp) rf[0];
				String patentecamion2 = (String) rf[1];
				String lat2 = (String) rf[2];
				String lon2 = (String) rf[3];
				String ubicacion2 = (String) rf[4];
				String numerointerno2 = (String) rf[5];

				Date date = new Date();
				Date datef = new Date(fecha2.getTime());
				if (i == 1) {

					date = new Date(fecha2.getTime());
					if (i2 > 1) {
						date = new Date(fecha.getTime());
					}
				} else {

					date = new Date(fecha.getTime());
				}

				if (!e_repetida.equals("SI")) {

					long seg = UtilServicio.getDiferenciaSeg(date, datef);

					duracionseg = Integer.valueOf(String.valueOf(Long.valueOf(seg)));

					if (i == 1) {
						if (!patentecamion2.equals(patentecamion)) {

							e_repetida = "NO";
							// break ;

						}
					} else {

						if (!patentecamion2.equals(patentecamion)) {

							e_repetida = "SI";
							fecha = fecha2;

						}
					}

					if (duracionseg >= 60) {

						e_repetida = "SI";
						// break; fecha2
						fechafin = fecha;
						fecha = fecha2;

					} else {

						if (i == 1) {

							if (i2 > 1) {
								fechainicio2 = fecha;
							}
						}

						fecha = fecha2;
						patentecamion = patentecamion2;
						numerointerno = numerointerno2;
						pases = pases + 1;
						duracionseg2 = duracionseg2 + duracionseg;
						tiempocarguio = UtilServicio.FormatoTimer(duracionseg2);

						if (i == 1) {
							fechainicio = fecha2;

							if (i2 > 1) {
								// fechainicio = fecha;
							}
						}

						lat = lat2;
						lon = lon2;
						ubicacion = ubicacion2;

					}

				} // fin e_repetida SI

				if (e_repetida.equals("SI")) {
					ReporteTiempoCarguioActive r = new ReporteTiempoCarguioActive();
					cargareal = pases * v.getVehCapacidadBalde();
					r.setPatente(v.getVehPatente());
					r.setNrointerno(v.getVehNumInterno());
					if (ciclomayoruno.equals("S")) {
						r.setFechain(fechainicio2);
						r.setFechafin(fechafin);
					} else {
						r.setFechain(fechainicio);
						r.setFechafin(fechafin);
					}

					r.setCargareal(cargareal);
					r.setTiempocarguio(tiempocarguio);
					r.setPases(pases);
					r.setUbicacioncarguio(ubicacion);
					r.setPatentecamion(patentecamion);
					r.setNrointernocamion(numerointerno);
					r.setSegundos(duracionseg2);

					e_repetida = "NO";
					i = 0;
					duracionseg2 = 0;
					pases = 1;
					ciclomayoruno = "S";
					if (pases != 0) {
						rlist.add(r);
					}

				} // fin if si

			} // fin for

			// fin carguio
			ReporteTiempoCarguioActive r2 = new ReporteTiempoCarguioActive();
			cargareal = pases * v.getVehCapacidadBalde();
			r2.setPatente(v.getVehPatente());
			r2.setNrointerno(v.getVehNumInterno());
			r2.setFechain(fechainicio2);
			r2.setFechafin(fecha);
			r2.setCargareal(cargareal);
			r2.setTiempocarguio(tiempocarguio);
			r2.setPases(pases);
			r2.setUbicacioncarguio(ubicacion);
			r2.setPatentecamion(patentecamion);
			r2.setNrointernocamion(numerointerno);
			r2.setSegundos(duracionseg2);

			e_repetida = "NO";
			i = 0; // duracionseg2 = 0; pases = 0;
			if (pases != 0) {
				rlist.add(r2);
			}
		} // fin for vehiculo

		em3.clear();
		em3.close();
		return rlist;
	}

	public static List<ReporteTiempoTransporteActive> findTiempoTransporte(String faena, String patente,
			String fecha_ini, String fecha_fin) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // dd/MM/yyyy
		em2 = emf.createEntityManager();

		Date fecdesde = new Date();
		Date fechasta = new Date();
		Calendar calendarin = Calendar.getInstance();
		Calendar calendarfin = Calendar.getInstance();
		try {
			fecdesde = formatteri.parse(fecha_ini);
			fechasta = formatteri.parse(fecha_fin);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendarin.setTime(fecdesde);
		calendarfin.setTime(fechasta);
		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
		Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

		// //System.out.println("select * from
		// ('"+timein+"','"+timefin+"','"+patente+"','"+faena+"','"+fecha_ini+"','"+fecha_fin+"')");

		Integer sw;
		if (patente == "") {
			sw = 1;
		} else {
			sw = 0;
		}

		StoredProcedureQuery query = em2.createStoredProcedureQuery("public.informevueltas");
		query.registerStoredProcedureParameter(1, Timestamp.class, ParameterMode.IN); // id_geo
		query.registerStoredProcedureParameter(2, Timestamp.class, ParameterMode.IN); // usuario
		query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(5, Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(6, Integer.class, ParameterMode.IN);

		query.setParameter(1, timein);
		query.setParameter(2, timefin);
		query.setParameter(3, patente);
		query.setParameter(4, Integer.parseInt(faena));
		query.setParameter(5, sw);
		query.setParameter(6, 0);

		query.execute();

		List<Object[]> mlistFor = query.getResultList();

		List<ReporteTiempoTransporteActive> mlist = new ArrayList();

		for (Object[] pa : mlistFor) {

			ReporteTiempoTransporteActive pa2 = new ReporteTiempoTransporteActive();

			pa2.setIdcamion((String) pa[2]);
			pa2.setUbicacioncarguio((String) pa[9]);
			pa2.setFechasalidacargio((Timestamp) pa[4]);
			pa2.setFechaentradadescarga((Timestamp) pa[11]);
			pa2.setCargareal((Double) pa[17]);
			pa2.setUbicaciondescarga((String) pa[15]);
			pa2.setTiempotransporte((Integer) pa[20]);
			pa2.setTiempoespera((Integer) pa[18]);
			pa2.setTiempodescarga((Integer) pa[19]);
			pa2.setFechadescarga((Timestamp) pa[13]);
			pa2.setFechasalidadescarga((Timestamp) pa[12]);
			// pa2.getFechaentradacargio((Timestamp) pa[]);
			// pa2.setTiempociclo((Integer) pa[]);

			mlist.add(pa2);

		}

		em2.clear();
		em2.close();

		return mlist;
	}

	public static List<ReporteTiempoTransporteActive> findCicloTransporte(String faena, String patente,
			String fecha_ini, String fecha_fin, Integer turnos, String opt_ver) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // dd/MM/yyyy
		emc = emf.createEntityManager();

		Date fecdesde = new Date();
		Date fechasta = new Date();
		Calendar calendarin = Calendar.getInstance();
		Calendar calendarfin = Calendar.getInstance();
		try {
			fecdesde = formatteri.parse(fecha_ini);
			fechasta = formatteri.parse(fecha_fin);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendarin.setTime(fecdesde);
		calendarfin.setTime(fechasta);
		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
		Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

		//// System.out.println("select * from
		//// ('"+timein+"','"+timefin+"','"+patente+"','"+faena+"','"+fecha_ini+"','"+fecha_fin+"')");

		Integer sw;
		Integer sw_faena;
		if (patente == "") {
			sw = 1;
			sw_faena = Integer.parseInt(faena);
		} else {
			sw = 0;
			sw_faena = 0;
		}
		// SELECT * FROM public.informevueltas('2019-05-13 00:00:00-04',
		// '2019-05-13 23:59:00-04', 'CYYS82', 0, 0, 1)
		StoredProcedureQuery query = emc.createStoredProcedureQuery("public.informevueltas_ver2");
		query.setHint("javax.persistence.query.timeout", 60000);
		query.registerStoredProcedureParameter(1, Timestamp.class, ParameterMode.IN); // id_geo
		query.registerStoredProcedureParameter(2, Timestamp.class, ParameterMode.IN); // usuario
		query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(5, Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(6, Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(7, Integer.class, ParameterMode.IN);

		query.setParameter(1, timein);
		query.setParameter(2, timefin);
		query.setParameter(3, patente);
		query.setParameter(4, sw_faena);
		query.setParameter(5, sw);
		query.setParameter(6, 1);
		query.setParameter(7, turnos);

		// System.out.println("select * from
		// ('"+timein+"','"+timefin+"','"+patente+"','"+faena+"','"+sw_faena+"','"+sw+"','"+turnos+"')");

		query.execute();

		List<Object[]> mlistFor = query.getResultList();

		// System.out.println("luego de getresuel " + mlistFor.size());

		List<ReporteTiempoTransporteActive> mlist = new ArrayList();

		for (Object[] pa : mlistFor) {

			//// System.out.println("dentro for " + pa[2]);

			ReporteTiempoTransporteActive pa2 = new ReporteTiempoTransporteActive();

			try {

				pa2.setData_id((Integer) pa[0]);

				pa2.setIdcamion((String) pa[2]);
				pa2.setUbicacioncarguio((String) pa[9]);
				pa2.setFechasalidacargio((Timestamp) pa[4]);
				pa2.setFechaentradadescarga((Timestamp) pa[11]);
				pa2.setCargareal((Double) pa[17]);
				pa2.setUbicaciondescarga((String) pa[15]);
				pa2.setTiempotransporte((Integer) pa[20]);
				pa2.setTiempoespera((Integer) pa[18]);
				pa2.setTiempodescarga((Integer) pa[19]);
				pa2.setFechadescarga((Timestamp) pa[13]);
				pa2.setFechasalidadescarga((Timestamp) pa[12]);
				pa2.setFechaentradacargio((Timestamp) pa[3]);
				pa2.setTiempociclo((Integer) pa[22]);
				pa2.setNrinterno((String) pa[23]);

				pa2.setTurno((Integer) pa[24]);
				pa2.setMaquina((String) pa[25]);
				pa2.setOdo_ini((Double) pa[26]);
				pa2.setOdo_fin((Double) pa[27]);
				pa2.setVel_max((Integer) pa[28]);
				pa2.setVel_prom((Integer) pa[29]);
				pa2.setOperador((String) pa[30]);

				pa2.setFecha_carga((Timestamp) pa[5]);
				pa2.setTmpo_carga((Integer) pa[6]);

				DecimalFormat df = new DecimalFormat("0.00");

				if (pa2.getTiempotransporte() < 0 || Integer.toString(pa2.getTiempotransporte()) == null) {
					pa2.setTiempotransporte(0);
				}
				if (pa2.getTiempociclo() < 0 || Integer.toString(pa2.getTiempociclo()) == null) {
					pa2.setTiempociclo(0);
				}
				if (pa2.getTiempoespera() < 0 || Integer.toString(pa2.getTiempoespera()) == null) {
					pa2.setTiempoespera(0);
				}
				if (pa2.getTiempodescarga() < 0 || Integer.toString(pa2.getTiempodescarga()) == null) {
					pa2.setTiempodescarga(0);
				}
				if (pa2.getTmpo_carga() < 0 || Integer.toString(pa2.getTmpo_carga()) == null) {
					pa2.setTmpo_carga(0);
				}

				if (opt_ver.equals("1")) {
					pa2.setTiempociclo_str(df.format(((Integer) pa2.getTiempociclo()) / (Double) 3600.0));
					pa2.setTiempoespera_str(df.format((Integer) pa2.getTiempoespera() / (Double) 3600.0));
					pa2.setTiempotransporte_str(df.format((Integer) pa2.getTiempotransporte() / (Double) 3600.0));
					pa2.setTiempodescarga_str(df.format((Integer) pa2.getTiempodescarga() / (Double) 3600.0));
					pa2.setTmpo_carga_str(df.format((Integer) pa2.getTmpo_carga() / (Double) 3600.0));
				} else if (opt_ver.equals("2")) {
					pa2.setTiempociclo_str(df.format((Integer) pa2.getTiempociclo() / 60.0));
					pa2.setTiempoespera_str(df.format(Double.valueOf(((Integer) pa2.getTiempoespera()) / 60.0)));
					pa2.setTiempotransporte_str(df.format((Integer) pa2.getTiempotransporte() / (Double) 60.0));
					pa2.setTiempodescarga_str(df.format((Integer) pa2.getTiempodescarga() / 60.0));
					pa2.setTmpo_carga_str(df.format((Integer) pa2.getTmpo_carga() / 60.0));
				} else if (opt_ver.equals("3")) {
					pa2.setTiempociclo_str(String.valueOf((Integer) pa2.getTiempociclo()));
					pa2.setTiempoespera_str(String.valueOf((Integer) pa2.getTiempoespera()));
					pa2.setTiempotransporte_str(String.valueOf((Integer) pa2.getTiempotransporte()));
					pa2.setTiempodescarga_str(String.valueOf((Integer) pa2.getTiempodescarga()));
					pa2.setTmpo_carga_str(String.valueOf((Integer) pa2.getTmpo_carga()));
				} else if (opt_ver.equals("4")) {
					pa2.setTiempociclo_str(UtilServicio.FormatoTimer((Integer) pa2.getTiempociclo()));
					pa2.setTiempoespera_str(UtilServicio.FormatoTimer((Integer) pa2.getTiempoespera()));
					pa2.setTiempotransporte_str(UtilServicio.FormatoTimer((Integer) pa2.getTiempotransporte()));
					pa2.setTiempodescarga_str(UtilServicio.FormatoTimer((Integer) pa2.getTiempodescarga()));
					pa2.setTmpo_carga_str(UtilServicio.FormatoTimer((Integer) pa2.getTmpo_carga()));
				}

				pa2.setTiempociclo_str(pa2.getTiempociclo_str().replace(".", ",").trim());
				pa2.setTiempoespera_str(pa2.getTiempoespera_str().replace(".", ",").trim());
				pa2.setTiempotransporte_str(pa2.getTiempotransporte_str().replace(".", ",").trim());
				pa2.setTiempodescarga_str(pa2.getTiempodescarga_str().replace(".", ",").trim());
				pa2.setTmpo_carga_str(pa2.getTmpo_carga_str().replace(".", ",").trim());

			} catch (Exception e) {
				// System.out.println(e);
			}

			mlist.add(pa2);

		}

		// emc.clear();
		// emc.close();

		return mlist;
	}

	public static List<ReporteCicloTransporteActive> findCicloTransporte_old(String patente, String fecha_ini,
			String fecha_fin, String faena) {

		// select * from informeciclotransporte(@fecha_ini, @fecha_fin,
		// @patente, SIN_USO)

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy"); // dd/MM/yyyy
		em2 = emf.createEntityManager();

		Date fecdesde = new Date();
		Date fechasta = new Date();
		Calendar calendarin = Calendar.getInstance();
		Calendar calendarfin = Calendar.getInstance();
		try {
			fecdesde = formatteri.parse(fecha_ini);
			fechasta = formatteri.parse(fecha_fin);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendarin.setTime(fecdesde);
		calendarfin.setTime(fechasta);
		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
		Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

		Integer fae_sw = 0;

		if (faena.equals("") == false) {
			fae_sw = 1;
		}

		// System.out.println("select * from
		// informeciclotransporte('"+timein+"','"+timefin+"','"+patente+"',"+fae_sw+")");

		StoredProcedureQuery query = em2.createStoredProcedureQuery("informeciclotransporte");
		query.registerStoredProcedureParameter(1, Timestamp.class, ParameterMode.IN); // id_geo
		query.registerStoredProcedureParameter(2, Timestamp.class, ParameterMode.IN); // usuario
		query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN);

		query.setParameter(1, timein);
		query.setParameter(2, timefin);
		query.setParameter(3, patente);
		query.setParameter(4, fae_sw);
		// query.setParameter(9, Integer.valueOf(velocidad));

		query.execute();

		List<Object[]> mlistFor = query.getResultList();

		List<ReporteCicloTransporteActive> mlist = new ArrayList();

		for (Object[] pa : mlistFor) {

			ReporteCicloTransporteActive pa2 = new ReporteCicloTransporteActive();

			// pa2.setFecha_ini((Timestamp) pa[0]);
			// pa2.setPatente((String) pa[1]);
			// pa2.setNom_zona((String) pa[2]);

			pa2.setHoracarguio1((Timestamp) pa[2]);
			pa2.setHoracarguio2((Timestamp) pa[3]);
			pa2.setIdcamion((String) pa[5]);
			pa2.setUbicacioncarguio1((String) pa[8]);
			pa2.setCargareal((Integer) pa[12]);
			pa2.setTiempodeciclo((String) formatSeconds((Integer) pa[13]));
			pa2.setUbicacioncarguio2((String) String.valueOf(pa[11]));

			mlist.add(pa2);

		}

		em2.clear();
		em2.close();

		// em.clear();
		// em.close();
		return mlist;
	}

	public static String formatSeconds(int timeInSeconds) {
		int hours = timeInSeconds / 3600;
		int secondsLeft = timeInSeconds - hours * 3600;
		int minutes = secondsLeft / 60;
		int seconds = secondsLeft - minutes * 60;

		String formattedTime = "";
		if (hours < 10)
			formattedTime += "0";
		formattedTime += hours + ":";

		if (minutes < 10)
			formattedTime += "0";
		formattedTime += minutes + ":";

		if (seconds < 10)
			formattedTime += "0";
		formattedTime += seconds;

		return formattedTime;
	}

	public static List<CarguioActive> findSinoptico(String patente, String desde, String hasta) {

		String fechanulas = "";

		// dd-MM-yyyy
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy"); // dd/MM/yyyy
		em = emf.createEntityManager();

		Calendar calendarin = Calendar.getInstance();
		Calendar calendarfin = Calendar.getInstance();

		Date fecdesde = new Date();
		Date fechasta = new Date();

		// si las fechas vienen nulo se calcula el mes completo
		if (desde.equals("") && hasta.equals("")) {

			fechanulas = "S";

		}

		try {
			fecdesde = formatteri.parse(desde);
			fechasta = formatteri.parse(hasta);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		// configuro las hora inicio y fin
		calendarin.setTime(fecdesde);
		calendarin.add(Calendar.HOUR_OF_DAY, 00);
		calendarin.add(Calendar.MINUTE, 00);
		calendarin.add(Calendar.SECOND, 00);

		// si las fechas vienen nul se resta el mes a la fecha inicio y fecha
		// fin queda como dia hoy
		if (fechanulas.equals("S")) {

			calendarfin.add(Calendar.DAY_OF_MONTH, -2);

		}

		calendarfin.setTime(fechasta);
		calendarfin.add(Calendar.HOUR_OF_DAY, 23);
		calendarfin.add(Calendar.MINUTE, 59);
		calendarfin.add(Calendar.SECOND, 59);

		// las convierto timestamp para insertarlas en el query
		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
		Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

		ArrayList<CarguioActive> clist = new ArrayList<CarguioActive>();

		ArrayList<SinopticoActive> s01list = new ArrayList<SinopticoActive>();
		ArrayList<SinopticoActive> s02list = new ArrayList<SinopticoActive>();

		CarguioActive c01 = new CarguioActive();
		CarguioActive c02 = new CarguioActive();

		// llenamos manual por ahora
		SinopticoActive s01 = new SinopticoActive();
		SinopticoActive s02 = new SinopticoActive();
		SinopticoActive s03 = new SinopticoActive();
		SinopticoActive s04 = new SinopticoActive();
		SinopticoActive s05 = new SinopticoActive();
		SinopticoActive s06 = new SinopticoActive();
		SinopticoActive s07 = new SinopticoActive();

		s01.setNrocamion("HKPC22 - CT.316");
		s01.setCargapermitida(20);
		s01.setCargaactual(10);
		s01.setParimpar(1);
		s01.setHora("10:11");

		s02.setNrocamion("HHFD98");
		s02.setCargapermitida(30);
		s02.setCargaactual(15);
		s02.setParimpar(2);
		s02.setHora("10:25");

		s01list.add(s01);
		s01list.add(s02);

		s05.setNrocamion("KWLJ61");
		s05.setCargapermitida(20);
		s05.setCargaactual(22);
		s05.setParimpar(1);
		s05.setHora("10:12");

		s02list.add(s05);
		c01.setNrocarg("KHRG34");
		c01.setNombre("KHRG34 - RH68");
		c01.setSinopticoactive(s01list);

		c02.setNrocarg("JXVD38");
		c02.setNombre("JXVD38 - EX84");
		c02.setSinopticoactive(s02list);

		clist.add(c01);
		clist.add(c02);

		return clist;
	}

	public static List<CarguioGraficoActive> findCarguioGrafico(String patentes, String faena, String desde,
			String hasta, String cliente, List<OperacionesActive> olist, List<ReporteTiempoCarguioActive> rlist) {

		String fechanulas = "";

		// dd-MM-yyyy
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy"); // dd/MM/yyyy
		em = emf.createEntityManager();

		Calendar calendarin = Calendar.getInstance();
		Calendar calendarfin = Calendar.getInstance();

		Date fecdesde = new Date();
		Date fechasta = new Date();

		// si las fechas vienen nulo se calcula el mes completo
		if (desde.equals("") && hasta.equals("")) {

			fechanulas = "S";

		}

		try {
			fecdesde = formatteri.parse(desde);
			fechasta = formatteri.parse(hasta);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		// configuro las hora inicio y fin
		calendarin.setTime(fecdesde);
		calendarin.add(Calendar.HOUR_OF_DAY, 00);
		calendarin.add(Calendar.MINUTE, 00);
		calendarin.add(Calendar.SECOND, 00);

		// si las fechas vienen nul se resta el mes a la fecha inicio y fecha
		// fin queda como dia hoy
		if (fechanulas.equals("S")) {

			calendarfin.add(Calendar.DAY_OF_MONTH, -2);

		}

		calendarfin.setTime(fechasta);
		calendarfin.add(Calendar.HOUR_OF_DAY, 23);
		calendarfin.add(Calendar.MINUTE, 59);
		calendarfin.add(Calendar.SECOND, 59);

		// las convierto timestamp para insertarlas en el query
		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
		Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

		String sql = "";

		if (!patentes.equals(null)) {

			sql = " and v.vehPatente in (:patentes) ";

		} else {

			sql = "";
		}

		Query query = em.createQuery("select v from Vehiculo v where 1 = 1 " + sql + " and v.cliente.cliRut = :cliente "
		// + "and v.tipoVehiculo.tipvId in (11, 19, 22, 25, 26, 27, 28) ");
				+ "and v.tipoVehiculo.tipo in ('m') " + " ");
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		query.setParameter("cliente", cliente);
		if (!patentes.equals(null)) {
			query.setParameter("patentes", patentes);
		}

		List<Vehiculo> vlist = query.getResultList();

		ArrayList<CarguioGraficoActive> clist = new ArrayList<CarguioGraficoActive>();

		for (Vehiculo v : vlist) {

			CarguioGraficoActive c = new CarguioGraficoActive();

			Color c1 = UtilServicio.rondonColor();
			Color c2 = UtilServicio.rondonColor();

			c.setPatente(v.getVehPatente());
			c.setBackgroundColor("rgba(" + c1.getRed() + ", " + c1.getGreen() + ", " + c1.getBlue() + ", 0)");
			c.setBorderColor("rgba(" + c1.getRed() + ", " + c1.getGreen() + ", " + c1.getBlue() + " 0)");

			ArrayList<OperacionesActive> o01list = new ArrayList<OperacionesActive>();

			for (OperacionesActive o : olist) {

				OperacionesActive o01 = new OperacionesActive();
				o01.setNombreoperacion(o.getNombreoperacion());
				// aqui realizamos calculos
				int data = 5;
				/*
				 * if(o.getIdnombreoperacion()==1){//Tiempo de Carga data =
				 * findTiempoCarga(desde, hasta, v.getVehPatente(), cliente); }
				 * if(o.getIdnombreoperacion()==2){// Carga Real data =
				 * findCargaReal(desde, hasta, v.getVehPatente(), cliente);
				 * 
				 * } if(o.getIdnombreoperacion()==3){// Pases data =
				 * findPases(desde, hasta, v.getVehPatente(), cliente); }
				 * if(o.getIdnombreoperacion()==4){// Espera data =
				 * findEspera(desde, hasta, v.getVehPatente(), cliente); }
				 * if(o.getIdnombreoperacion()==5){// Ralenti data =
				 * findRalenti(desde, hasta, v.getVehPatente(), cliente);
				 * 
				 * }
				 */
				o01.setData(35);

				o01list.add(o01);

			} // fin for operaciones

			c.setOperacionesactive(o01list);

			clist.add(c);

		} // fin for vehiculo

		em.clear();
		em.close();
		return clist;
	}

	public static List<CarguioGraficoActive> findCarguioGrafico2(String patentes, String faena, String desde,
			String hasta, String cliente, List<OperacionesActive> olist, List<ReporteTiempoCarguioActive> rlist) {

		String fechanulas = "";

		// dd-MM-yyyy
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy"); // dd/MM/yyyy
		em = emf.createEntityManager();

		Calendar calendarin = Calendar.getInstance();
		Calendar calendarfin = Calendar.getInstance();

		Date fecdesde = new Date();
		Date fechasta = new Date();

		// si las fechas vienen nulo se calcula el mes completo
		if (desde.equals("") && hasta.equals("")) {

			fechanulas = "S";

		}

		try {
			fecdesde = formatteri.parse(desde);
			fechasta = formatteri.parse(hasta);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		// configuro las hora inicio y fin
		calendarin.setTime(fecdesde);
		calendarin.add(Calendar.HOUR_OF_DAY, 00);
		calendarin.add(Calendar.MINUTE, 00);
		calendarin.add(Calendar.SECOND, 00);

		// si las fechas vienen nul se resta el mes a la fecha inicio y fecha
		// fin queda como dia hoy
		if (fechanulas.equals("S")) {

			calendarfin.add(Calendar.DAY_OF_MONTH, -2);

		}

		calendarfin.setTime(fechasta);
		calendarfin.add(Calendar.HOUR_OF_DAY, 23);
		calendarfin.add(Calendar.MINUTE, 59);
		calendarfin.add(Calendar.SECOND, 59);

		// las convierto timestamp para insertarlas en el query
		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
		Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

		// para jpa se para un array para el in, y no lleva parentesis
		String[] parts = patentes.split(",");
		List<String> plista = Arrays.asList(parts);

		String sql = "";

		if (!patentes.equals(null)) {

			sql = " and v.vehPatente in :patentes ";

		} else {

			sql = "";
		}

		Query query = em.createQuery("select v from Vehiculo v where 1 = 1 " + sql + " and v.cliente.cliRut = :cliente "
		// + "and v.tipoVehiculo.tipvId in (11, 19, 22, 25, 26, 27, 28) "
				+ "and v.tipoVehiculo.tipo in ('m') " + " ");

		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		query.setParameter("cliente", cliente);
		if (!patentes.equals(null)) {
			query.setParameter("patentes", plista);
		}

		List<Vehiculo> vlist = query.getResultList();

		ArrayList<CarguioGraficoActive> clist = new ArrayList<CarguioGraficoActive>();

		for (Vehiculo v : vlist) {

			CarguioGraficoActive c = new CarguioGraficoActive();

			Color c1 = UtilServicio.rondonColor();
			Color c2 = UtilServicio.rondonColor();

			c.setPatente(v.getVehPatente());
			c.setBackgroundColor("rgba(" + c1.getRed() + ", " + c1.getGreen() + ", " + c1.getBlue() + ", 0)");
			c.setBorderColor("rgba(" + c1.getRed() + ", " + c1.getGreen() + ", " + c1.getBlue() + " 0)");

			// Calculamos tiempo carga, carga real, espera

			/*
			 * em2 = emf.createEntityManager();
			 * 
			 * Query query02 = em2.
			 * createNativeQuery("SELECT rf_fecha_hora, rf_patente_camion, rf_latitud, rf_longitud, rf_geocoding, veh_num_interno "
			 * + " FROM datos_rfid , dispositivo.vehiculo" +
			 * " where rf_patente_maquina = '"+v.getVehPatente()+"' " +
			 * " and rf_patente_camion notnull " +
			 * " and rf_patente_camion = veh_patente " +
			 * " and rf_fecha_hora >= '"+timein+"' and rf_fecha_hora <= '"
			 * +timefin+"' " + " ORDER BY rf_fecha_hora " + " " + " ");
			 * 
			 * List<Object[]> rfidlist = query02.getResultList();
			 */
			Integer i = 0;

			int segundostotal = 0;
			int pasestotal = 0;
			double cargareal = 0.0;
			int tiempoesperatotal = 0;
			int segundoespera = 0;

			for (ReporteTiempoCarguioActive rf : rlist) {
				i++;

				if (rf.getPatente().equals(v.getVehPatente())) {

					segundostotal = segundostotal + rf.getSegundos();
					pasestotal = pasestotal + rf.getPases();
					cargareal = cargareal + rf.getCargareal();

					Date datef = new Date(rf.getFechafin().getTime());
					Date datefa = new Date(rf.getFechafin().getTime());

					ReporteTiempoCarguioActive rfa = new ReporteTiempoCarguioActive();
					if (i > 1) {
						rfa = rlist.get(i - 1);
						datefa = new Date(rfa.getFechafin().getTime());
					} else {

						datefa = new Date(rf.getFechafin().getTime());

					}

					long seg = UtilServicio.getDiferenciaSeg(datefa, datef);

					segundoespera = Integer.valueOf(String.valueOf(Long.valueOf(seg)));

					tiempoesperatotal = tiempoesperatotal + segundoespera;

				}

			}

			// fin Calculamos tiempo carga, carga real, espera

			ArrayList<OperacionesActive> o01list = new ArrayList<OperacionesActive>();

			for (OperacionesActive o : olist) {

				OperacionesActive o01 = new OperacionesActive();
				o01.setNombreoperacion(o.getNombreoperacion());
				// aqui realizamos calculos
				double data = 0.0;

				if (o.getIdnombreoperacion() == 1) {// Tiempo de Carga
					data = segundostotal;
					// data = findTiempoCarga(desde, hasta, v.getVehPatente(),
					// cliente);
				}
				if (o.getIdnombreoperacion() == 2) {// Carga Real
					data = cargareal;
					// data = findCargaReal(desde, hasta, v.getVehPatente(),
					// cliente);

				}
				if (o.getIdnombreoperacion() == 3) {// Pases
					data = pasestotal;
					// data = findPases(desde, hasta, v.getVehPatente(),
					// cliente);
				}
				if (o.getIdnombreoperacion() == 4) {// Espera
					data = tiempoesperatotal;
					// data = findEspera(desde, hasta, v.getVehPatente(),
					// cliente);
				}
				if (o.getIdnombreoperacion() == 5) {// Ralenti
					data = 0.0;
					// data = findRalenti(desde, hasta, v.getVehPatente(),
					// cliente);

				}
				o01.setData(data);

				o01list.add(o01);

			} // fin for operaciones

			if (pasestotal != 0) {
				c.setOperacionesactive(o01list);
				clist.add(c);
			}

		} // fin for vehiculo

		em.clear();
		em.close();
		return clist;
	}

	public static List<OperacionesActive> findOperaciones() {

		ArrayList<OperacionesActive> o01list = new ArrayList<OperacionesActive>();

		OperacionesActive o01 = new OperacionesActive();
		OperacionesActive o02 = new OperacionesActive();
		OperacionesActive o03 = new OperacionesActive();
		OperacionesActive o04 = new OperacionesActive();
		OperacionesActive o05 = new OperacionesActive();

		o01.setIdnombreoperacion(1);
		o01.setNombreoperacion("Tiempo de Carga (seg)");

		o02.setIdnombreoperacion(2);
		o02.setNombreoperacion("Carga Real");

		o03.setIdnombreoperacion(3);
		o03.setNombreoperacion("Pases");

		o04.setIdnombreoperacion(4);
		o04.setNombreoperacion("Espera (seg)");

		o05.setIdnombreoperacion(5);
		o05.setNombreoperacion("Ralenti");

		o01list.add(o01);
		o01list.add(o02);
		o01list.add(o03);
		o01list.add(o04);
		o01list.add(o05);

		return o01list;
	}

	public static List<MapaCalorActive> findMapaCalor(String faena, String desde, String hasta, String cliente,
			String sw1, String sw2) {

		// PropertyConfigurator.configure(log4jConfPath);

		logger.error("MapaCalor");

		String fechanulas = "";

		// dd-MM-yyyy
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // dd/MM/yyyy
																			// HH:mm
		em = emf.createEntityManager();

		Calendar calendarin = Calendar.getInstance();
		Calendar calendarfin = Calendar.getInstance();

		Date fecdesde = new Date();
		Date fechasta = new Date();

		// si las fechas vienen nulo se calcula el mes completo
		if (desde.equals("") && hasta.equals("")) {

			fechanulas = "S";

		} else {

			try {
				fecdesde = formatteri.parse(desde);
				fechasta = formatteri.parse(hasta);
			} catch (ParseException e) {
				// logger.error("mapa calor: "+ e);
				// e.printStackTrace();
			}

		}

		// configuro las hora inicio y fin
		if (fechanulas.equals("S")) {

			calendarin.add(Calendar.DAY_OF_MONTH, -1);

		} else {

			calendarin.setTime(fecdesde);

		}

		// si las fechas vienen nul se resta el mes a la fecha inicio y fecha
		// fin queda como dia hoy
		if (fechanulas.equals("S")) {

			// calendarfin.add(Calendar.DAY_OF_MONTH, -1);

		} else {
			calendarfin.setTime(fechasta);
		}

		// las convierto timestamp para insertarlas en el query
		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
		Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

		// DESCRIPCION DE LA FUNCION PG MAPA CALOR
		// select * from mapaCalor(@fecha_ini,@fecha_fin,@sw1,
		// @sw2,@cod_faena,@usuario)
		// select * from mapaCalor('11/27/18 00:00:00','11/27/18
		// 23:00:00',0,0,0,'emexcosa') � consulta datos gps flota completa
		// select * from mapaCalor('11/27/18 00:00:00','11/27/18
		// 23:00:00',1,0,0,'emexcosa') � consulta datos can de flota copmpleta
		// select * from mapaCalor('11/27/18 00:00:00','11/27/18
		// 23:00:00',0,1,451,'emexcosa') -- datos gps por faena, la faena 451
		// select * from mapaCalor('11/27/18 00:00:00','11/27/18
		// 23:00:00',1,1,452,'emexcosa') -- datos can por faena, la faena 452

		StoredProcedureQuery query = em.createStoredProcedureQuery("mapaCalor");
		query.registerStoredProcedureParameter(1, Timestamp.class, ParameterMode.IN); // fecha
																						// in
		query.registerStoredProcedureParameter(2, Timestamp.class, ParameterMode.IN); // fechan
																						// fin
		query.registerStoredProcedureParameter(3, int.class, ParameterMode.IN); //
		query.registerStoredProcedureParameter(4, int.class, ParameterMode.IN); //
		query.registerStoredProcedureParameter(5, int.class, ParameterMode.IN); // faena
		query.registerStoredProcedureParameter(6, String.class, ParameterMode.IN); // cliente

		query.setParameter(1, timein);
		query.setParameter(2, timefin);
		query.setParameter(3, Integer.valueOf(sw1));
		query.setParameter(4, Integer.valueOf(sw2));
		query.setParameter(5, Integer.valueOf(faena));
		query.setParameter(6, cliente);

		ArrayList<MapaCalorActive> mlist = new ArrayList<MapaCalorActive>();

		query.execute();

		List<Object[]> mapalist = query.getResultList();
		Integer i = 0;

		for (Object[] ruta : mapalist) {
			i++;

			MapaCalorActive mapacaloractive = new MapaCalorActive();

			String lat = (String) ruta[1];
			String lon = (String) ruta[2];

			mapacaloractive.setLat(lat);
			mapacaloractive.setLon(lon);

			mlist.add(mapacaloractive);

		}

		em.clear();
		em.close();

		return mlist;
	}

	// buscar faenas por clientes
	public static List<FaenaActive> findFaenaXCliente(String cliente, String clave) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		em2 = emf.createEntityManager();

		Query query02 = em2.createNativeQuery("select * from cliente.grupo where gru_id in ( "
				+ " select gru_id_grupo from cliente.proyecto_grupo where py_id_proyecto in ( "
				+ " select py_id from cliente.proyecto where usu_rut_usuario= "
				// + " (select usu_rut from cliente.usuario where usu_login=
				// '"+cliente+"' and usu_clave= '"+clave+"'))) "
				+ " (select usu_rut from cliente.usuario where usu_login= '" + cliente + "'))) " + " ");

		List<FaenaActive> listafaena = new ArrayList();

		List<Object[]> faenalist = query02.getResultList();

		for (Object[] fa : faenalist) {

			FaenaActive fa2 = new FaenaActive();

			Integer idGru = (Integer) fa[0];
			String nombreGru = (String) fa[1];

			fa2.setIdGru(idGru);
			fa2.setNombreGru(nombreGru);

			listafaena.add(fa2);

		}

		em2.clear();
		em2.close();

		return listafaena;
	}

	public static List<ReporteTiempoFueraOperacionActive> findTiempoFueraOperacion(String desde, String hasta,
			String sw1, String faena, String patente, String usuario) {

		String fechanulas = "";

		// dd-MM-yyyy
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // dd/MM/yyyy
		em = emf.createEntityManager();

		Calendar calendarin = Calendar.getInstance();
		Calendar calendarfin = Calendar.getInstance();

		Date fecdesde = new Date();
		Date fechasta = new Date();

		// si las fechas vienen nulo se calcula el mes completo
		if (desde.equals("") && hasta.equals("")) {

			fechanulas = "S";

		}

		try {
			fecdesde = formatteri.parse(desde);
			fechasta = formatteri.parse(hasta);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		// configuro las hora inicio y fin
		calendarin.setTime(fecdesde);

		// si las fechas vienen nul se resta el mes a la fecha inicio y fecha
		// fin queda como dia hoy
		if (fechanulas.equals("S")) {

			calendarfin.add(Calendar.DAY_OF_MONTH, -2);

		}

		calendarfin.setTime(fechasta);

		// las convierto timestamp para insertarlas en el query
		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
		Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

		// Descripcion Funcion PG
		// select * from
		// informeTpoFueraOperacion(@fecha_ini,@fecha_fin,@sw,@id_faena,@patente,@usuario)
		// select * from informeTpoFueraOperacion('11/28/2018
		// 00:00:00','11/28/2018 23:59:00',0,0,'GYYD17','xsdf')

		/*
		 * Parmetros de entrada:
		 * 
		 * Fecha inicio Fecha fin Switch: Consultar por patente o faena.
		 * Si @sw=0, resultado por patente. Si @sw=1, resultado por faena. Id
		 * Faena: Si la consulta es por faena, se debe enviar en id de la faena
		 * consultada. Patente: Si la consulta es por patente, se debe enviar la
		 * patente consultada. Usuario.
		 */

		/*
		 * Parmetros de salida (nombre de identificacion en el SP):
		 * 
		 * Fecha de ingreso (fecha_ini) Fecha de salida (fecha_fin) Patente
		 * (patente) Tiempo Permanencia (tiempo_fuera) Ubicacion (ubicacion)
		 * Alarma (alarma) Por ahora va en 1 por defecto hasta definir
		 * significado que no aparece en el informe de especificaciones.
		 */

		StoredProcedureQuery query = em.createStoredProcedureQuery("informeTpoFueraOperacion");
		query.registerStoredProcedureParameter(1, Timestamp.class, ParameterMode.IN); // fecha
																						// in
		query.registerStoredProcedureParameter(2, Timestamp.class, ParameterMode.IN); // fechan
																						// fin
		query.registerStoredProcedureParameter(3, int.class, ParameterMode.IN); // sw1
		query.registerStoredProcedureParameter(4, int.class, ParameterMode.IN); // faena
		query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN); // patente
		query.registerStoredProcedureParameter(6, String.class, ParameterMode.IN); // usuario

		query.setParameter(1, timein);
		query.setParameter(2, timefin);
		query.setParameter(3, Integer.valueOf(sw1));
		query.setParameter(4, Integer.valueOf(faena));
		query.setParameter(5, patente);
		query.setParameter(6, usuario);

		query.execute();

		// 0 1 2 3 4
		// "HKPC22" 135 "Muro Noroeste" 65 64
		ArrayList<ReporteTiempoFueraOperacionActive> rlist = new ArrayList<ReporteTiempoFueraOperacionActive>();

		List<Object[]> mapalist = query.getResultList();
		Integer i = 0;

		for (Object[] ruta : mapalist) {
			i++;

			ReporteTiempoFueraOperacionActive reporteTiempoFueraOperacionActive = new ReporteTiempoFueraOperacionActive();

			String patente2 = (String) ruta[0];
			long tiempoPermanencia = (Long) ruta[1];
			String ubicacion = (String) ruta[2];
			int evento = (Integer) ruta[3]; // evento
			int zonaid = (Integer) ruta[4];

			ListadoGeocercasActive geo = new ListadoGeocercasActive();

			if (zonaid != 0) {

				geo = findGeocercaByIdPgOne(usuario, String.valueOf(zonaid));

			} else {

				geo.setNombre("Todas");
			}

			// Reporte
			reporteTiempoFueraOperacionActive.setIdcamion(patente2);
			reporteTiempoFueraOperacionActive.setUbicacion(ubicacion);
			reporteTiempoFueraOperacionActive.setTiempopermanencia(Integer.valueOf(String.valueOf(tiempoPermanencia)));
			reporteTiempoFueraOperacionActive.setZonaid(zonaid);
			reporteTiempoFueraOperacionActive.setZonanombre(geo.getNombre());

			// no work
			Calendar calendart = Calendar.getInstance();
			calendart.set(Calendar.DAY_OF_MONTH, 0);
			calendart.set(Calendar.YEAR, 0);
			calendart.set(Calendar.MONTH, 0);
			calendart.set(Calendar.HOUR_OF_DAY, 0);
			calendart.set(Calendar.MINUTE, 0);
			calendart.set(Calendar.SECOND, 0);
			calendart.set(Calendar.MILLISECOND, 0);
			calendart.add(Calendar.SECOND, Integer.valueOf(String.valueOf(tiempoPermanencia)));
			Timestamp tiempo = new Timestamp(calendart.getTimeInMillis());
			// no worl

			String tiempoS = UtilServicio.FormatoTimer(tiempoPermanencia);

			reporteTiempoFueraOperacionActive.setTiempo(tiempo);
			reporteTiempoFueraOperacionActive.setTiempoS(tiempoS);

			/*
			 * String classEvent = UtilServicio.getClassEvent(evento, "");
			 * reporteTiempoFueraOperacionActive.setClassEvent(classEvent);
			 * EventosGp e = findEvento(evento);
			 * reporteTiempoFueraOperacionActive.setNombreve(e.getEveNombre());
			 * reporteTiempoFueraOperacionActive.setIdEvent(String.valueOf(
			 * evento));
			 */
			/*
			 * if(alarma==1){
			 * 
			 * reporteTiempoFueraOperacionActive.setAlarma("badge badge-primary"
			 * ); //class div
			 * 
			 * } if(alarma==2){
			 * 
			 * reporteTiempoFueraOperacionActive.setAlarma("badge badge-warning"
			 * ); //class div
			 * 
			 * } if(alarma==3){
			 * 
			 * reporteTiempoFueraOperacionActive.setAlarma("badge badge-danger"
			 * ); //class div
			 * 
			 * }
			 */

			rlist.add(reporteTiempoFueraOperacionActive);

		}

		em.clear();
		em.close();

		return rlist;
	}

	public static List<ReporteProduccionTurnoActive> findProduccionTurno(String patente, String desde, String hasta) {

		String fechanulas = "";

		// dd-MM-yyyy
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy"); // dd/MM/yyyy
		em = emf.createEntityManager();

		Calendar calendarin = Calendar.getInstance();
		Calendar calendarfin = Calendar.getInstance();

		Date fecdesde = new Date();
		Date fechasta = new Date();

		// si las fechas vienen nulo se calcula el mes completo
		if (desde.equals("") && hasta.equals("")) {

			fechanulas = "S";

		}

		try {
			fecdesde = formatteri.parse(desde);
			fechasta = formatteri.parse(hasta);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		// configuro las hora inicio y fin
		calendarin.setTime(fecdesde);
		calendarin.add(Calendar.HOUR_OF_DAY, 00);
		calendarin.add(Calendar.MINUTE, 00);
		calendarin.add(Calendar.SECOND, 00);

		// si las fechas vienen nul se resta el mes a la fecha inicio y fecha
		// fin queda como dia hoy
		if (fechanulas.equals("S")) {

			calendarfin.add(Calendar.DAY_OF_MONTH, -2);

		}

		calendarfin.setTime(fechasta);
		calendarfin.add(Calendar.HOUR_OF_DAY, 23);
		calendarfin.add(Calendar.MINUTE, 59);
		calendarfin.add(Calendar.SECOND, 59);

		// las convierto timestamp para insertarlas en el query
		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
		Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

		// List resultList = query.getResultList();
		ArrayList<ReporteProduccionTurnoActive> rlist = new ArrayList<ReporteProduccionTurnoActive>();

		// llenamos manual por ahora
		ReporteProduccionTurnoActive r01 = new ReporteProduccionTurnoActive();
		ReporteProduccionTurnoActive r02 = new ReporteProduccionTurnoActive();
		ReporteProduccionTurnoActive r03 = new ReporteProduccionTurnoActive();
		ReporteProduccionTurnoActive r04 = new ReporteProduccionTurnoActive();
		ReporteProduccionTurnoActive r05 = new ReporteProduccionTurnoActive();
		ReporteProduccionTurnoActive r06 = new ReporteProduccionTurnoActive();

		rlist.add(r01);
		rlist.add(r02);
		rlist.add(r03);
		rlist.add(r04);
		rlist.add(r05);
		rlist.add(r06);

		// em.clear();
		// em.close();
		return rlist;
	}

	// sw1, sw2, desde, hasta, faena
	public static List<ReporteResumenFlotaActive> findResumenFlota(String sw1, String sw2, String desde, String hasta,
			String usuLogin, String clave) {

		String fechanulas = "";

		// dd-MM-yyyy
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // dd/MM/yyyy
		em = emf.createEntityManager();

		Calendar calendarin = Calendar.getInstance();
		Calendar calendarfin = Calendar.getInstance();

		Date fecdesde = new Date();
		Date fechasta = new Date();

		// si las fechas vienen nulo se calcula el mes completo
		if (desde.equals("") && hasta.equals("")) {

			fechanulas = "S";

		}

		try {
			fecdesde = formatteri.parse(desde);
			fechasta = formatteri.parse(hasta);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		// configuro las hora inicio y fin
		calendarin.setTime(fecdesde);

		// si las fechas vienen nul se resta el mes a la fecha inicio y fecha
		// fin queda como dia hoy
		if (fechanulas.equals("S")) {

			calendarfin.add(Calendar.DAY_OF_MONTH, -2);

		}

		calendarfin.setTime(fechasta);

		// las convierto timestamp para insertarlas en el query
		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
		Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

		// select * from
		// ultima_posicion_CAN_BCA_pass_new(@sw1,@sw2,@fecha_ini,@fecha_fin,
		// @usuario,@password)
		// SELECT * FROM ultima_posicion_CAN_BCA_pass_new(0, 1, '2019-05-05
		// 00:00:00-03', '2019-05-06 23:59:00-03', 'emexcosa', 'emexco7688')

		StoredProcedureQuery query = em.createStoredProcedureQuery("ultima_posicion_CAN_BCA_pass_new");

		query.registerStoredProcedureParameter(1, int.class, ParameterMode.IN); //
		query.registerStoredProcedureParameter(2, int.class, ParameterMode.IN); //
		query.registerStoredProcedureParameter(3, Timestamp.class, ParameterMode.IN); // fecha
																						// in
		query.registerStoredProcedureParameter(4, Timestamp.class, ParameterMode.IN); // fechan
																						// fin
		query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN); // login
		query.registerStoredProcedureParameter(6, String.class, ParameterMode.IN); // clave

		query.setParameter(1, Integer.valueOf(sw1));
		query.setParameter(2, Integer.valueOf(sw2));
		query.setParameter(3, timein);
		query.setParameter(4, timefin);
		query.setParameter(5, usuLogin);
		query.setParameter(6, clave);

		ArrayList<ReporteResumenFlotaActive> mlist = new ArrayList<ReporteResumenFlotaActive>();

		query.execute();

		List<Object[]> mapalist = query.getResultList();
		Integer i = 0;

		for (Object[] ruta : mapalist) {
			i++;

			ReporteResumenFlotaActive mapacaloractive = new ReporteResumenFlotaActive();

			mapacaloractive.setPatente((String) ruta[0]);
			mapacaloractive.setIdVehicle((String) ruta[1]);
			mapacaloractive.setChofer((String) ruta[2]);
			mapacaloractive.setFaena((String) ruta[3]);
			mapacaloractive.setDateDate((Timestamp) ruta[4]);
			try {
				mapacaloractive.setOdometro((Integer) ruta[5]);

			} catch (Exception e) {

			}

			mapacaloractive.setNivelComb((String) ruta[6]);
			mapacaloractive.setLat((String) ruta[7]);
			mapacaloractive.setLon((String) ruta[8]);
			mapacaloractive.setMarca((String) ruta[9]);
			mapacaloractive.setModelo((String) ruta[10]);
			mapacaloractive.setNuminterno((String) ruta[11]);

			if ((Double) ruta[12] == null) {
				mapacaloractive.setHorometro(0.0);
			} else {

				mapacaloractive.setHorometro((Double) ruta[12]);
			}
			// mapacaloractive.setHorometro((Double) ruta[12]);

			mapacaloractive.setNombreTercero((String) ruta[13]);
			mapacaloractive.setViajes((Integer) ruta[14]);
			mapacaloractive.setDt((Double) ruta[15]);
			mapacaloractive.setTpomm((Double) ruta[16]);
			mapacaloractive.setDsttot((Double) ruta[17]);
			mapacaloractive.setCombtot((Double) ruta[18]);
			mapacaloractive.setVelmax((Double) ruta[19]);
			mapacaloractive.setRpmmax((Double) ruta[20]);
			mapacaloractive.setTporal((Double) ruta[21]);
			mapacaloractive.setTpocc((Double) ruta[22]);
			mapacaloractive.setTpovel((Double) ruta[23]);
			mapacaloractive.setCombral((Double) ruta[24]);
			mapacaloractive.setCombcc((Double) ruta[25]);
			mapacaloractive.setDstcc((Double) ruta[26]);
			mapacaloractive.setCombmarcha((Double) ruta[27]);

			try {
				mapacaloractive.setAlarpm((Integer) ruta[28]);
			} catch (Exception e) {

			}

			try {
				mapacaloractive.setAlavel((Integer) ruta[29]);
			} catch (Exception e) {

			}
			try {
				mapacaloractive.setAlaral((Integer) ruta[30]);
			} catch (Exception e) {

			}

			try {
				mapacaloractive.setAlacodf((Integer) ruta[31]);
			} catch (Exception e) {

			}

			try {
				mapacaloractive.setCombpto((Double) ruta[32]);
			} catch (Exception e) {

			}

			try {
				mapacaloractive.setTpoto((Double) ruta[33]);
			} catch (Exception e) {

			}

			try {
				mapacaloractive.setAdblue((Double) ruta[34]);
			} catch (Exception e) {

			}

			try {

				mapacaloractive.setAlaacelb((Integer) ruta[35]);

			} catch (Exception e) {

			}
			try {
				mapacaloractive.setAlafrenb((Integer) ruta[36]);

			} catch (Exception e) {

			}

			DecimalFormat df = new DecimalFormat("#######.##");

			String tpommString = df.format(mapacaloractive.getTpomm());
			mapacaloractive.setTpommString(tpommString.replace(",", ".").trim());

			String dtString = df.format(mapacaloractive.getDt());
			mapacaloractive.setDtString(dtString.replace(",", ".").trim());

			Double rendkmlitro = mapacaloractive.getTpomm() / 3600;
			Double rendlithora = mapacaloractive.getCombtot() / ((mapacaloractive.getDt() / 3600));
			Double totaltiempo = mapacaloractive.getDt() / 3600;
			Double dt = mapacaloractive.getDt();
			mapacaloractive.setDt(dt);

			if (rendlithora.isNaN()) {

				rendlithora = 0.0;
			}

			String rendkmlitroString = df.format(rendkmlitro);
			String rendlithoraString = df.format(rendlithora);
			String tiempototalString = df.format(totaltiempo);

			mapacaloractive.setRendimlthrString(rendlithoraString.replace(",", ".").trim());
			mapacaloractive.setRendimkmltString(rendkmlitroString.replace(",", ".").trim());

			String combtotstring = df.format(mapacaloractive.getCombtot());
			String dsttotstring = df.format(mapacaloractive.getDsttot());

			mapacaloractive.setCombtotString(combtotstring.replace(",", ".").trim());
			mapacaloractive.setDsttotString(dsttotstring.replace(",", ".").trim());
			mapacaloractive.setTotaltiempo(tiempototalString.replace(",", ".").trim());

			mlist.add(mapacaloractive);

		}

		em.clear();
		em.close();
		return mlist;
	}

	public static List<PatenteGPSActive> findPatentesXClienteFecha(String cliente, String clave, Date fecha,
			String clirut) {

		Calendar calendarin = Calendar.getInstance();

		calendarin.setTime(fecha);
		calendarin.add(Calendar.HOUR_OF_DAY, 00);
		calendarin.add(Calendar.MINUTE, 00);
		calendarin.add(Calendar.SECOND, 00);

		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		em3 = emf.createEntityManager();

		Query query02 = em3.createNativeQuery("select usuveh_id, fn_getdevice('idgps',veh_patente_vehiculo,'" + timein
				+ "'), veh_patente_vehiculo, veh_num_interno "
				+ "from  dispositivo.usuario_vehiculo, dispositivo.vehiculo " + "where usu_rut_usuario= "
				+ "(select usu_rut from cliente.usuario where usu_login= '" + cliente + "' and usu_clave= '" + clave
				+ "') " + "and veh_patente_vehiculo=veh_patente " + "and cli_rut_cliente = '" + clirut + "' "
				+ "order by veh_patente_vehiculo, veh_num_interno" + " ");

		List<PatenteGPSActive> listapatente = new ArrayList();

		List<Object[]> patentelist = query02.getResultList();

		for (Object[] pa : patentelist) {

			PatenteGPSActive pa2 = new PatenteGPSActive();

			Integer usuvehId = (Integer) pa[0];
			String fnGetdevice = (String) pa[1];
			String vehPatenteVehiculo = (String) pa[2];
			String nroInterno = (String) pa[3];

			pa2.setUsuvehId(usuvehId);
			pa2.setFnGetdevice(fnGetdevice);
			pa2.setVehPatenteVehiculo(vehPatenteVehiculo);
			pa2.setNroInterno(nroInterno);

			listapatente.add(pa2);

		}

		em3.clear();
		em3.close();

		return listapatente;
	}

	// patente, desde, hasta, sw1
	public static List<DetalleXViajeActive> findDetallexViaje(String patente, String desde, String hasta, String sw1) {

		String fechanulas = "";

		// dd-MM-yyyy
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // dd/MM/yyyy
		em = emf.createEntityManager();

		Calendar calendarin = Calendar.getInstance();
		Calendar calendarfin = Calendar.getInstance();

		Date fecdesde = new Date();
		Date fechasta = new Date();

		// si las fechas vienen nulo se calcula el mes completo
		if (desde.equals("") && hasta.equals("")) {

			fechanulas = "S";

		}

		try {
			fecdesde = formatteri.parse(desde);
			fechasta = formatteri.parse(hasta);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		// configuro las hora inicio y fin
		calendarin.setTime(fecdesde);
		// si las fechas vienen nul se resta el mes a la fecha inicio y fecha
		// fin queda como dia hoy
		if (fechanulas.equals("S")) {

			calendarfin.add(Calendar.DAY_OF_MONTH, 0);

		}

		calendarfin.setTime(fechasta);

		// las convierto timestamp para insertarlas en el query
		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
		Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

		/*
		 * Cuando @sw=1; debe mostrar todos los reportes generados.
		 * Cuando @sw=0; solo trae los reportes donde el combustible sea mayor a
		 * 0. Ejemplo de un SP con datos: select * from
		 * resumenCan('TP30','11/25/18 15:00:00','11/27/18 19:00:00',1)
		 */

		StoredProcedureQuery query = em.createStoredProcedureQuery("resumenCan");

		query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN); //
		query.registerStoredProcedureParameter(2, Timestamp.class, ParameterMode.IN); // fecha
																						// in
		query.registerStoredProcedureParameter(3, Timestamp.class, ParameterMode.IN); // fechan
																						// fin
		query.registerStoredProcedureParameter(4, int.class, ParameterMode.IN); // faena

		query.setParameter(1, patente); // gps alias
		query.setParameter(2, timein);
		query.setParameter(3, timefin);
		query.setParameter(4, Integer.valueOf(sw1));

		ArrayList<DetalleXViajeActive> mlist = new ArrayList<DetalleXViajeActive>();

		query.execute();

		List<Object[]> mapalist = query.getResultList();
		Integer i = 0;

		for (Object[] ruta : mapalist) {
			i++;

			DetalleXViajeActive resumenflota = new DetalleXViajeActive();

			resumenflota.setFechaIni((Timestamp) ruta[0]);
			resumenflota.setFechaFin((Timestamp) ruta[1]);
			resumenflota.setIdGps2((String) ruta[2]);
			resumenflota.setDt2((Double) ruta[3]);
			resumenflota.setTpomm2((Double) ruta[4]);
			resumenflota.setOdoini2((Double) ruta[5]);
			resumenflota.setOdofin2((Double) ruta[6]);
			resumenflota.setDsttot2((Double) ruta[7]); // distanci total
			resumenflota.setOltini2((Double) ruta[8]);
			resumenflota.setOltifin2((Double) ruta[9]);
			resumenflota.setCombtot2((Double) ruta[10]);
			resumenflota.setHorotot2((Double) ruta[11]);
			resumenflota.setVelmax2((Double) ruta[12]);
			resumenflota.setRpmmax2((Double) ruta[13]);
			resumenflota.setCargamoto2((Double) ruta[14]);
			resumenflota.setNivelestamque2((Double) ruta[15]);
			resumenflota.setTporal2((Double) ruta[16]);
			resumenflota.setTpocc2((Double) ruta[17]);
			resumenflota.setTpovel2((Double) ruta[18]);
			resumenflota.setTpopto2((Double) ruta[19]);
			resumenflota.setCombpto2((Double) ruta[20]);
			resumenflota.setAdblue2((Double) ruta[21]);
			resumenflota.setCombmarchar2((Double) ruta[22]);
			resumenflota.setCombral2((Double) ruta[23]);
			resumenflota.setCombcc2((Double) ruta[24]);
			resumenflota.setDstcc2((Double) ruta[25]);
			resumenflota.setUsoembrague2((Double) ruta[26]);
			resumenflota.setUsofreno2((Double) ruta[27]);
			resumenflota.setUsoretarde2((Double) ruta[28]);
			resumenflota.setUsocc2((Double) ruta[29]);
			resumenflota.setNumenergizacion2((Integer) ruta[30]);
			resumenflota.setNumpartida2((Integer) ruta[31]);
			resumenflota.setTipo2((String) ruta[32]);
			resumenflota.setNumInterno((String) ruta[33]);
			resumenflota.setPatente((String) ruta[34]);
			resumenflota.setConductor((String) ruta[35]);

			DecimalFormat df = new DecimalFormat("#######.##");

			Double rendkmlitro = resumenflota.getDsttot2() / resumenflota.getCombtot2();
			Double rendlithora = resumenflota.getCombtot2() / ((resumenflota.getDt2() / 3600));
			Double tiempomarcha = resumenflota.getTpomm2() / 3600;// tpomm2
			Double tiemporelamti = resumenflota.getTporal2() / 3600; // tporal2

			if (rendlithora.isNaN()) {
				rendlithora = 0.0;
			}
			if (rendkmlitro.isNaN()) {
				rendkmlitro = 0.0;
			}

			// DecimalFormat df = new DecimalFormat("#######.##");
			String rendkmlitroString = df.format(rendkmlitro);
			String rendlithoraString = df.format(rendlithora);

			String tiempomarchaString = df.format(tiempomarcha);
			String tiemporelamtiString = df.format(tiemporelamti);

			// System.out.println(rendkmlitroString);
			resumenflota.setRendkmlitroString(rendkmlitroString.replace(",", ".").trim());
			resumenflota.setRendlithoraString(rendlithoraString.replace(",", ".").trim());

			resumenflota.setTiempomarchaString(tiempomarchaString.replace(",", ".").trim());
			resumenflota.setTiemporelamtiString(tiemporelamtiString.replace(",", ".").trim());

			resumenflota.setRendkmlitro(rendkmlitro);
			resumenflota.setRendlithora(rendlithora);

			mlist.add(resumenflota);

		}
		em.clear();
		em.close();

		return mlist;
	}

	public static Grupo findGrupoById(String faenaid) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		em3 = emf.createEntityManager();

		Grupo grupo = new Grupo();

		try {

			Query query = em3.createQuery("select u from Grupo u where u.gruId = :faenaid ");
			query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
			query.setParameter("faenaid", Integer.valueOf(faenaid));

			grupo = (Grupo) query.getSingleResult();

		} catch (Exception e) {
			// System.out.println(e);
			logger.error("findGrupoById " + e);
		}

		em3.clear();
		em3.close();

		return grupo;
	}

	public static List<GeocercasActive> findGeocercaById(String usuario, String clave, String faenaid) {

		// Geocercas
		// SQL="BaseGPSF..MM_MantGeo_Pass '','emexcosa','emexco7688'"

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gpsf");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy"); // dd/MM/yyyy
		em2 = emf.createEntityManager();

		StoredProcedureQuery query = em2.createStoredProcedureQuery("MM_MantGeo_Pass");
		query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN); // id_geo
		query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN); // usuario
		query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN); // pw

		query.setParameter(1, faenaid);
		query.setParameter(2, usuario);
		query.setParameter(3, clave);

		///////////////////////////////////////

		ArrayList<GeocercasActive> mlist = new ArrayList<GeocercasActive>();

		query.execute();

		List<Object[]> eventlist = query.getResultList();

		Integer i = 0;

		for (Object[] event : eventlist) {
			i++;

			GeocercasActive geocercasActive = new GeocercasActive();

			Integer id_geo = (Integer) event[0];
			String nombre = (String) event[1];
			String descripcion = (String) event[2];
			String tipo = (String) event[3];
			String lat0 = (String) event[4];
			String lon0 = (String) event[5];
			String lat1 = (String) event[6];
			String lon1 = (String) event[7];
			String lat2 = (String) event[8];
			String lon2 = (String) event[9];
			String lat3 = (String) event[10];
			String lon3 = (String) event[11];
			String lat4 = (String) event[12];
			String lon4 = (String) event[13];
			String area = (String) event[14];
			String geomText = (String) event[26];
			String color = (String) event[27];

			if (tipo.equals("3")) {

				String geomTextTransform = geomText.replace("))", "").replace("POLYGON ((", "").replace(", ", ",");
				String[] parts = geomTextTransform.split(",");

				ArrayList<CoordenadasActive> mlistCoor = new ArrayList<CoordenadasActive>();

				for (String mapaLatLonObj : parts) {

					CoordenadasActive coordenadasActive = new CoordenadasActive();
					String[] LatLon = mapaLatLonObj.split(" ");
					String lat = (String) LatLon[0];
					String lon = (String) LatLon[1];
					coordenadasActive.setLat(lat);
					coordenadasActive.setLon(lon);
					mlistCoor.add(coordenadasActive);
				}

				geocercasActive.setCoordenadas(mlistCoor);

			}

			geocercasActive.setId_geo(id_geo);
			geocercasActive.setNombre(nombre);
			geocercasActive.setDescripcion(descripcion);
			geocercasActive.setTipo(tipo);
			geocercasActive.setLat0(lat0);
			geocercasActive.setLon0(lon0);
			geocercasActive.setLat1(lat1);
			geocercasActive.setLon1(lon1);
			geocercasActive.setLat2(lat2);
			geocercasActive.setLon2(lon2);
			geocercasActive.setLat3(lat3);
			geocercasActive.setLon3(lon3);
			geocercasActive.setLat4(lat4);
			geocercasActive.setLon4(lon4);
			geocercasActive.setGeomText(geomText);
			geocercasActive.setColor(color);
			geocercasActive.setArea(area);

			mlist.add(geocercasActive);

		}

		em2.clear();
		em2.close();

		return mlist;
	}

	public static List<ListadoGeocercasActive> findGeocercaByIdPg(String usuario, String clave, String id) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		em = emf.createEntityManager();

		// Query query = em.createNativeQuery("select * from
		// public.mantenedorzonas('nombre2',1,4,'i' ,'','',
		// '','emexcosa','',"+id+") ");
		Query query = em.createNativeQuery("select * from public.mantenedorzonas_2('nombre2',1,4,'i' ,'','', '','"
				+ usuario + "',''," + id + ",0,0,0,0,0)  ");
		// System.out.println("select * from
		// public.mantenedorzonas('nombre2',1,4,'i' ,'','',
		// '','"+usuario+"','',"+id+") ");
		ArrayList<ListadoGeocercasActive> GeoList = new ArrayList<ListadoGeocercasActive>();

		List<Object[]> glist = query.getResultList();

		// cuando traes un solo resultado usar getresullist
		// Object[] g = (Object[]) query.getSingleResult();

		Integer i = 0;

		for (Object[] g : glist) {
			i++;
			ListadoGeocercasActive geo = new ListadoGeocercasActive();
			geo.setId_geo((Integer) g[0]);
			geo.setNombre((String) g[1]);
			geo.setNombre_color((String) g[8]);
			geo.setCodigo_color((String) g[9]);
			geo.setArea((double) Math.round((Double) g[7] / 1000));

			String geomText = (String) g[3];

			String geomTextTransform = geomText.replace(")))", "").replace("MULTIPOLYGON(((", "").replace(", ", ",");
			String[] parts = geomTextTransform.split(",");

			ArrayList<CoordenadasActive> mlistCoor = new ArrayList<CoordenadasActive>();

			for (String mapaLatLonObj : parts) {
				CoordenadasActive coordenadasActive = new CoordenadasActive();
				String[] LatLon = mapaLatLonObj.split(" ");
				String lat = (String) LatLon[1];
				String lon = (String) LatLon[0];
				coordenadasActive.setLat(lat);
				coordenadasActive.setLon(lon);
				mlistCoor.add(coordenadasActive);
			}
			geo.setCoordenadas(mlistCoor);
			geo.setGeomText(geomText);

			geo.setUso((String) g[6]);
			geo.setVigencia((Integer) g[12]);
			geo.setVisible((Integer) g[10]);
			geo.setAplicacion((Integer) g[13]);
			geo.setFuera((Integer) g[14]);
			geo.setGeoreferencia((Integer) g[11]);

			GeoList.add(geo);
		}

		em.clear();
		em.close();

		return GeoList;
	}

	public static List<ListadoGeocercasActive> findGeocercaByIdPg3(String nombre, Integer Gcolor, String usuario,
			String clave, String coordenadas, Integer op, String id, Integer zon_visible, Integer zon_georeferencia,
			Integer zon_vigencia, Integer z_aplicacion, Integer f_prog, Integer VelAlarma, Integer est, String correo) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		em = emf.createEntityManager();
		// Query query = em.createNativeQuery("select * from
		// public.mantenedorzonas('nombre2',1,4,'i' ,'','',
		// '','emexcosa','',"+id+") ");
		Query query = em.createNativeQuery("select * from public.mantenedorzonas_3('" + nombre + "'," + Gcolor + ","
				+ op + ",'i' ,'','', '" + coordenadas + "','" + usuario + "',''," + id + "," + zon_visible + ","
				+ zon_georeferencia + "," + zon_vigencia + "," + z_aplicacion + "," + f_prog + "," + VelAlarma + ","
				+ est + ",'" + correo + "') ");
		//// System.out.println("select * from
		//// public.mantenedorzonas3('nombre2',1,2,'i','','',
		//// '','"+usuario+"','',0,0,0,0,0,"+VelAlarma+","+est+",'"+correo+"')");
		ArrayList<ListadoGeocercasActive> GeoList = new ArrayList<ListadoGeocercasActive>();

		List<Object[]> glist = query.getResultList();

		// cuando traes un solo resultado usar getresullist
		// Object[] g = (Object[]) query.getSingleResult();

		Integer i = 0;

		for (Object[] g : glist) {
			i++;
			ListadoGeocercasActive geo = new ListadoGeocercasActive();
			geo.setId_geo((Integer) g[0]);
			geo.setNombre((String) g[1]);

			geo.setVelocidadAlarma((Integer) g[15]);
			geo.setEstadoAlarma((Integer) g[16]);
			geo.setCorreo((String) g[17]);

			geo.setNombre_color((String) g[8]);
			geo.setCodigo_color((String) g[9]);
			geo.setArea((double) Math.round((Double) g[7] / 1000));

			String geomText = (String) g[3];

			String geomTextTransform = geomText.replace(")))", "").replace("MULTIPOLYGON(((", "").replace(", ", ",");
			String[] parts = geomTextTransform.split(",");

			ArrayList<CoordenadasActive> mlistCoor = new ArrayList<CoordenadasActive>();

			for (String mapaLatLonObj : parts) {
				CoordenadasActive coordenadasActive = new CoordenadasActive();
				String[] LatLon = mapaLatLonObj.split(" ");
				String lat = (String) LatLon[1];
				String lon = (String) LatLon[0];
				coordenadasActive.setLat(lat);
				coordenadasActive.setLon(lon);
				mlistCoor.add(coordenadasActive);
			}
			geo.setCoordenadas(mlistCoor);
			geo.setGeomText(geomText);

			geo.setUso((String) g[6]);
			geo.setVigencia((Integer) g[12]);
			geo.setVisible((Integer) g[10]);
			geo.setAplicacion((Integer) g[13]);
			geo.setFuera((Integer) g[14]);
			geo.setGeoreferencia((Integer) g[11]);

			GeoList.add(geo);
		}

		em.clear();
		em.close();

		return GeoList;
	}

	public static ListadoGeocercasActive findGeocercaByIdPgOne(String usuario, String id) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		em2 = emf.createEntityManager();

		Query query = em2.createNativeQuery(
				"select * from public.mantenedorzonas('nombre2',1,4,'i' ,'','', '','" + usuario + "',''," + id + ")  ");
		// System.out.println("select * from
		// public.mantenedorzonas('nombre2',1,4,'i' ,'','',
		// '','"+usuario+"','',"+id+") ");

		Object[] g = (Object[]) query.getSingleResult();

		Integer i = 0;

		i++;
		ListadoGeocercasActive geo = new ListadoGeocercasActive();
		geo.setId_geo((Integer) g[0]);
		geo.setNombre((String) g[1]);
		geo.setNombre_color((String) g[8]);
		geo.setCodigo_color((String) g[9]);
		geo.setArea((double) Math.round((Double) g[7] / 1000));

		String geomText = (String) g[3];

		String geomTextTransform = geomText.replace(")))", "").replace("MULTIPOLYGON(((", "").replace(", ", ",");
		String[] parts = geomTextTransform.split(",");

		ArrayList<CoordenadasActive> mlistCoor = new ArrayList<CoordenadasActive>();

		for (String mapaLatLonObj : parts) {

			CoordenadasActive coordenadasActive = new CoordenadasActive();
			String[] LatLon = mapaLatLonObj.split(" ");
			String lat = (String) LatLon[1];
			String lon = (String) LatLon[0];
			coordenadasActive.setLat(lat);
			coordenadasActive.setLon(lon);
			mlistCoor.add(coordenadasActive);
		}

		geo.setCoordenadas(mlistCoor);
		geo.setGeomText(geomText);

		em2.clear();
		em2.close();

		return geo;
	}

	public static List<Grupo> findGrupoAll() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		em3 = emf.createEntityManager();

		Grupo grupo = new Grupo();
		ArrayList<Grupo> grupos = new ArrayList<Grupo>();

		try {

			Query query = em3.createQuery("select u from Grupo u ");
			query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");

			grupos = (ArrayList<Grupo>) query.getResultList();

		} catch (Exception e) {
			// System.out.println(e);
			logger.error("findGrupoById " + e);
		}

		em3.clear();
		em3.close();

		return grupos;
	}

	public static Grupo findGrupoByNombre(String faenanombre) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		em3 = emf.createEntityManager();

		Query query = em3.createQuery("select u from Grupo u where u.gruNombre = :faenanombre ");
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		query.setParameter("faenanombre", faenanombre);

		Grupo grupo = new Grupo();

		try {

			grupo = (Grupo) query.getSingleResult();

		} catch (Exception e) {
			// System.out.println(e);
			logger.error("findGrupoByNombre " + e);
		}

		em3.clear();
		em3.close();

		return grupo;
	}

	public static Grupo findGrupoById(Integer gruId) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		em3 = emf.createEntityManager();

		Query query = em3.createQuery("select u from Grupo u where u.gruId = :gruId ");
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		query.setParameter("gruId", gruId);

		Grupo grupo = new Grupo();

		try {

			grupo = (Grupo) query.getSingleResult();

		} catch (Exception e) {
			// System.out.println(e);
			logger.error("findGrupoBygruId " + e);
		}

		em3.clear();
		em3.close();

		return grupo;
	}

	// obtiene se busca por iddevice, deberia buscar por patente
	public static VehiculoDevice findDeviceByPatente(String devIdDevice) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		em3 = emf.createEntityManager();

		Query query = em3.createQuery("select v from VehiculoDevice v where v.devIdDevice = :devIdDevice ");
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		query.setParameter("devIdDevice", devIdDevice);

		VehiculoDevice vehiculodevice = new VehiculoDevice();

		try {

			vehiculodevice = (VehiculoDevice) query.getSingleResult();

		} catch (Exception e) {
			// System.out.println(e);
			logger.error("Error findDeviceByPatente" + e);
		}

		em3.clear();
		em3.close();

		return vehiculodevice;
	}

	public static VehiculoDevice findDeviceByPatente02(String patente) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		em3 = emf.createEntityManager();

		Query query = em3.createQuery("select v from VehiculoDevice v where v.vehPatenteVehiculo = :patente ");
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		query.setParameter("patente", patente);

		VehiculoDevice vehiculodevice = new VehiculoDevice();

		try {

			vehiculodevice = (VehiculoDevice) query.getSingleResult();

		} catch (Exception e) {
			// System.out.println(e);
			logger.error("Error findDeviceByPatente" + e);
		}

		em3.clear();
		em3.close();

		return vehiculodevice;
	}

	public static String findGetDiveceByPatenteFecha(String patente, String desde) {

		// dd-MM-yyyy
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy"); // dd/MM/yyyy
		em4 = emf.createEntityManager();

		Calendar calendarin = Calendar.getInstance();

		Date fecdesde = new Date();

		try {
			fecdesde = formatteri.parse(desde);
		} catch (ParseException e1) {

			e1.printStackTrace();
		}

		// configuro las hora inicio y fin
		calendarin.setTime(fecdesde);
		calendarin.set(Calendar.HOUR_OF_DAY, 00);
		calendarin.set(Calendar.MINUTE, 00);
		calendarin.set(Calendar.SECOND, 00);

		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());

		StoredProcedureQuery query = em4.createStoredProcedureQuery("fn_getdevice");

		query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN); //
		query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN); //
		query.registerStoredProcedureParameter(3, Timestamp.class, ParameterMode.IN); // fecha
																						// in

		query.setParameter(1, "idgps");
		query.setParameter(2, patente);
		query.setParameter(3, timein);

		query.execute();

		String iddevice = "";

		try {
			iddevice = (String) query.getSingleResult();
		} catch (Exception e) {

		}

		em4.clear();
		em4.close();

		return iddevice;
	}

	// usuario, desde, hasta, valor, sw, vel
	public static List<ReporteDescargaActive> findInformeDescarga(String usuario, String desde, String hasta,
			String valor, String sw, String vel, String valor02) {

		String fechanulas = "";

		// dd-MM-yyyy
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // dd/MM/yyyy
		em = emf.createEntityManager();

		Calendar calendarin = Calendar.getInstance();
		Calendar calendarfin = Calendar.getInstance();

		Date fecdesde = new Date();
		Date fechasta = new Date();

		// si las fechas vienen nulo se calcula el mes completo
		if (desde.equals("") && hasta.equals("")) {

			fechanulas = "S";

		}

		try {
			fecdesde = formatteri.parse(desde);
			fechasta = formatteri.parse(hasta);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		// configuro las hora inicio y fin
		calendarin.setTime(fecdesde);

		// si las fechas vienen nul se resta el mes a la fecha inicio y fecha
		// fin queda como dia hoy
		if (fechanulas.equals("S")) {

			calendarfin.add(Calendar.DAY_OF_MONTH, -2);

		}

		calendarfin.setTime(fechasta);

		// las convierto timestamp para insertarlas en el query
		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
		Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

		// usuario, desde, hasta, valor, sw, vel
		// Los parametros de entrada en orden son fecha ini, fecha fin, sw,
		// faena, patente, tiempo y usuario.
		// select * from informeDescarga('01/21/2019 00:00:00','01/21/2019
		// 23:59:00',0,0,50,'HKPC22','emexcosa') (por patente y las descargas
		// superiores a 50 segundos)
		// select * from informeDescarga('01/21/2019 00:00:00','01/21/2019
		// 23:59:00',1,1,40,'','emexcosa') (por faena y las descargas superiores
		// a 40 segundos)

		// correcto
		// select * from informeDescarga('01/21/2019 00:00:00','01/21/2019
		// 23:59:00',0,0,'HKPC22',50,'emexcosa')
		// select * from informeDescarga('01/21/2019 00:00:00','01/21/2019
		// 23:59:00',1,1,'',40,'emexcosa')

		StoredProcedureQuery query = em.createStoredProcedureQuery("informeDescarga");
		query.registerStoredProcedureParameter(1, Timestamp.class, ParameterMode.IN); // fecha
																						// in
		query.registerStoredProcedureParameter(2, Timestamp.class, ParameterMode.IN); // fecha
																						// fin
		query.registerStoredProcedureParameter(3, int.class, ParameterMode.IN); // sw
		query.registerStoredProcedureParameter(4, int.class, ParameterMode.IN); // valor2
																				// faena
		query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN); // valor
		query.registerStoredProcedureParameter(6, int.class, ParameterMode.IN); // vel
																				// //
																				// tiempo
		query.registerStoredProcedureParameter(7, String.class, ParameterMode.IN); // usuario

		query.setParameter(1, timein);
		query.setParameter(2, timefin);
		query.setParameter(3, Integer.valueOf(sw));
		query.setParameter(4, Integer.valueOf(valor02));
		query.setParameter(5, valor);
		query.setParameter(6, Integer.valueOf(vel)); // tiempo
		query.setParameter(7, usuario);

		// //System.out.println("informeDescarga" + "query: "+ query);

		query.execute();

		ArrayList<ReporteDescargaActive> rlist = new ArrayList<ReporteDescargaActive>();

		List<Object[]> mapalist = query.getResultList();
		Integer i = 0;

		for (Object[] ruta : mapalist) {
			i++;

			ReporteDescargaActive reporteDescargaActive = new ReporteDescargaActive();

			// Los parametros de salida en orden son fecha ini, fecha fin,
			// patente, tiempo de descarga, evento y direcci�n, latitud,
			// longitud.
			// 0 1 2 3 4 5 6 7
			Timestamp fechaini = (Timestamp) ruta[0];
			Timestamp fechafin = (Timestamp) ruta[1];
			String patente = (String) ruta[2];
			int tiempodescarga = (Integer) ruta[3];
			int idEvent = (Integer) ruta[4];
			String direccion = (String) ruta[5];
			String lat = (String) ruta[6];
			String lon = (String) ruta[7];
			String nrinterno = (String) ruta[8];

			reporteDescargaActive.setFechaini(fechaini);
			reporteDescargaActive.setFechafin(fechafin);
			reporteDescargaActive.setPatente(patente);
			reporteDescargaActive.setTiempodescarga(String.valueOf(tiempodescarga));
			reporteDescargaActive.setDireccion(direccion);

			reporteDescargaActive.setLat(lat);
			reporteDescargaActive.setLon(lon);

			reporteDescargaActive.setNrointerno(nrinterno);

			rlist.add(reporteDescargaActive);

		}

		em.clear();
		em.close();

		return rlist;
	}

	public static List<HistoricoCanActive> findHistoricoCan(String valor, String desde, String hasta, String sw) {

		String fechanulas = "";

		// dd-MM-yyyy
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // dd/MM/yyyy
		em = emf.createEntityManager();

		Calendar calendarin = Calendar.getInstance();
		Calendar calendarfin = Calendar.getInstance();

		Date fecdesde = new Date();
		Date fechasta = new Date();

		// si las fechas vienen nulo se calcula el mes completo
		if (desde.equals("") && hasta.equals("")) {

			fechanulas = "S";

		}

		try {
			fecdesde = formatteri.parse(desde);
			fechasta = formatteri.parse(hasta);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		// configuro las hora inicio y fin
		calendarin.setTime(fecdesde);

		// si las fechas vienen nul se resta el mes a la fecha inicio y fecha
		// fin queda como dia hoy
		if (fechanulas.equals("S")) {

			calendarfin.add(Calendar.DAY_OF_MONTH, -2);

		}

		calendarfin.setTime(fechasta);

		// las convierto timestamp para insertarlas en el query
		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
		Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

		// SELECT * FROM historicocan('HKPC22', '2019-05-06 00:00:00-03',
		// '2019-05-06 23:59:00-03', 0)
		StoredProcedureQuery query = em.createStoredProcedureQuery("historicocan");
		query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN); // patente
		query.registerStoredProcedureParameter(2, Timestamp.class, ParameterMode.IN); // fecha
																						// in
		query.registerStoredProcedureParameter(3, Timestamp.class, ParameterMode.IN); // fecha
																						// fin
		query.registerStoredProcedureParameter(4, int.class, ParameterMode.IN); // sw

		query.setParameter(1, valor); // patente
		query.setParameter(2, timein);
		query.setParameter(3, timefin);
		query.setParameter(4, Integer.valueOf(sw));

		query.execute();

		ArrayList<HistoricoCanActive> rlist = new ArrayList<HistoricoCanActive>();

		List<Object[]> mapalist = query.getResultList();
		Integer i = 0;

		for (Object[] ruta : mapalist) {
			i++;

			HistoricoCanActive historicoCanActive = new HistoricoCanActive();
			// odo horastot rpm acel
			// 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24
			// 25 25 26 27
			// "UM33" "DB" "51855.04" "39108.58" "40" "1049" "40" "64" "100"
			// null "0" "0" "81" "1998-12-31 21:00:00-03" "-22.75139"
			// "-069.29798" 40 "HKPC22" "3679.9" "0" "1" "410" "0" "0" "0"
			// "26.0244444444444" 208 2 "CT.316"

			// patente , numerointerno
			// fecha, ubicacion, odkm, combutilizadolt, rpm , horomhrs, acel%,
			// nivelcomb%(LT.), T Motor%, Torque Motor % ,
			// Presion Aceite KPa, Nivel AdBlue, Uso Freno(1/10), Uso
			// Embrague(1/10),
			// Uso Crucero, Uso PTO, Uso Retarder, Vel.Can km/h

			String patente = (String) ruta[17];
			String numerointerno = (String) ruta[29];
			Timestamp fecha = (Timestamp) ruta[13];
			Double odkm = (Double) ruta[2];
			Double combutilizadolt = (Double) ruta[8];
			Double rpm = (Double) ruta[5];
			Double horomhrs = 0.0;

			horomhrs = (Double) ruta[18];

			if (horomhrs == null) {

				horomhrs = 0.0;
			}

			Double acel = (Double) ruta[6];
			Double nivelcomb = 0.0;
			try {
				nivelcomb = (Double) ruta[9];
			} catch (Exception e) {
				nivelcomb = 0.0;
			}
			if (nivelcomb == null) {
				nivelcomb = 0.0;
			}
			Double tmotor = (Double) ruta[3];
			Double torquemotor = (Double) ruta[24];
			Double crucero = (Double) ruta[11];
			Double retarder = (Double) ruta[20];
			int vel = (Integer) ruta[16];
			String lat = (String) ruta[14];
			String lon = (String) ruta[15];

			historicoCanActive.setPatente(patente);
			historicoCanActive.setNumerointerno(numerointerno);
			historicoCanActive.setFechaHoraGps(fecha);
			historicoCanActive.setOdometro(String.valueOf(odkm));
			historicoCanActive.setNivelCombP(String.valueOf(combutilizadolt));
			historicoCanActive.setRpm(String.valueOf(rpm));
			historicoCanActive.setHorometro(String.valueOf(horomhrs));
			historicoCanActive.setNivelCombLt(String.valueOf(nivelcomb));
			historicoCanActive.setHorasTotMotor(String.valueOf(tmotor));
			historicoCanActive.setTorqueretarder(String.valueOf(torquemotor));
			historicoCanActive.setCrucero(String.valueOf(crucero));
			historicoCanActive.setPosAcel(String.valueOf(acel));
			historicoCanActive.setSpd(String.valueOf(vel));
			historicoCanActive.setRetarder(String.valueOf(retarder));
			historicoCanActive.setLat(lat);
			historicoCanActive.setLon(lon);

			rlist.add(historicoCanActive);

		}

		em.clear();
		em.close();

		return rlist;
	}

	public static List<MapaUltimoGPS> findVehiculoUltimaPosicion(String cliente) {

		// PropertyConfigurator.configure(log4jConfPath);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		em3 = emf.createEntityManager();

		Query query = em3.createQuery("select v from Vehiculo v where v.cliente.cliRut = :cliente ");
		// query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		query.setParameter("cliente", cliente);

		ArrayList<MapaUltimoGPS> mlist = new ArrayList<MapaUltimoGPS>();

		List<Vehiculo> vlist = query.getResultList();

		for (Vehiculo v : vlist) {

			MapaUltimoGPS mapaUltimoGPS = new MapaUltimoGPS();

			DatosGpsUltimo dgu = new DatosGpsUltimo();

			dgu = ReporteService.findUiltimaPosicionByVehiculo(v.getVehPatente());

			String geocerca = "";

			if (dgu != null) {

				mapaUltimoGPS.setLat(dgu.getRutLatitud().replaceAll("-0", "-"));
				mapaUltimoGPS.setLon(dgu.getRutLongitud().replaceAll("-0", "-"));
				mapaUltimoGPS.setVelocidad(String.valueOf(dgu.getRutVelocidad()));
				mapaUltimoGPS.setFecha(dgu.getId().getRutFechaHora());

				if (dgu.getRutGeocerca() != null) {

					mapaUltimoGPS.setRutGeocerca(dgu.getRutGeocerca());

				} else {
					mapaUltimoGPS.setRutGeocerca(dgu.getRutGeocoding());

				}

				mapaUltimoGPS.setVehiculo(v);

				//
				String classEvent = UtilServicio.getClassEventJS(dgu.getRutEvento(), "");

				EventosGp evento = new EventosGp();
				evento = ReporteService.findEvento(dgu.getRutEvento());

				mapaUltimoGPS.setClassEvent(classEvent);
				try {
					mapaUltimoGPS.setEvento(evento.getEveNombre());
				} catch (Exception e) {
					mapaUltimoGPS.setEvento("N/A");
				}

				mlist.add(mapaUltimoGPS);

			}

		}

		em3.clear();
		em3.close();

		return mlist;
	}

	public static List<MapaUltimoGPSJson> findVehiculoUltimaPosicionAjax(String cliente, String rut) {

		// PropertyConfigurator.configure(log4jConfPath);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		emS = emf.createEntityManager();

		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Query query = emS
				.createQuery("select v from Vehiculo v " + "where v.cliente.cliRut = :cliente " + "and v.vehPatente  "
						+ "in " + "(select u.vehPatenteVehiculo from UsuarioVehiculo u where u.usuRutUsuario = :rut ) "
						+ "order by v.vehNumInterno asc ");
		// query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		query.setParameter("cliente", cliente);
		query.setParameter("rut", rut);

		ArrayList<MapaUltimoGPSJson> mlist = new ArrayList<MapaUltimoGPSJson>();

		List<Vehiculo> vlist = query.getResultList();

		for (Vehiculo v : vlist) {

			MapaUltimoGPSJson mapaUltimoGPSJson = new MapaUltimoGPSJson();

			DatosGpsUltimo dgu = new DatosGpsUltimo();

			// usando el mismo entity manager inicio
			Query query02 = emS.createQuery("select d from DatosGpsUltimo d where d.id.rutPatente = :patente ");
			query02.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
			query02.setHint("javax.persistence.query.timeout", 1000);
			// query.setHint(QueryHints.QUERY_TIMEOUT, "100");
			// query.setHint(QueryHints.JDBC_TIMEOUT, "100");

			query02.setParameter("patente", v.getVehPatente());

			// DatosGpsUltimo d = new DatosGpsUltimo();

			try {
				dgu = (DatosGpsUltimo) query02.getSingleResult();
			} catch (Exception e) {
				dgu = null;
			}
			// fin

			// dgu =
			// ReporteService.findUiltimaPosicionByVehiculo(v.getVehPatente());

			String geocerca = "";

			if (dgu != null) {

				mapaUltimoGPSJson.setLat(dgu.getRutLatitud());
				mapaUltimoGPSJson.setLon(dgu.getRutLongitud());
				mapaUltimoGPSJson.setVelocidad(String.valueOf(dgu.getRutVelocidad()));
				mapaUltimoGPSJson.setFecha(dgu.getId().getRutFechaHora());
				mapaUltimoGPSJson.setFechaS(formatteri.format(dgu.getId().getRutFechaHora()));
				mapaUltimoGPSJson.setFechaSdb(formatteri.format(dgu.getRutFechaInserta()));

				// fecha actual
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				mapaUltimoGPSJson.setFechaAjaxS(formatteri.format(timestamp));

				// Estado GPS
				// 1 optimo, 2 advertencia, 3 alerta
				Date datedesde = new Date(dgu.getId().getRutFechaHora().getTime());
				Date datehasta = new Date();
				long dias = UtilServicio.getDiferenciaday(datedesde, datehasta);
				long horas = UtilServicio.getDiferenciadayHoras(datedesde, datehasta);

				if (dias == 0) {

					if (horas >= 2) {

						mapaUltimoGPSJson.setEstado("2");
						mapaUltimoGPSJson.setDescestado(
								"<small style='color: orange; font-size :12px;'>Retraso de Datos <i class='fa fa-warning'></i> </small>");

					} else {

						mapaUltimoGPSJson.setEstado("1");
						mapaUltimoGPSJson.setDescestado(
								"<small style='color: green; font-size :12px;'>Activo <i class='fa fa-check'></i> </small>");

					}

				} else {

					mapaUltimoGPSJson.setEstado("3");
					mapaUltimoGPSJson.setDescestado(
							"<small style='color: red; font-size :12px;'>Sin Transmision <i class='fa fa-minus-circle'></i> </small>");

				}
				// fin estado

				// Estado DB
				// 1 optimo, 2 advertencia, 3 alerta
				// dgu.getId().getRutFechaHora().getTime()
				Date datedesdedb = new Date(dgu.getRutFechaInserta().getTime());
				Date datehastadb = new Date();
				long diasdb = UtilServicio.getDiferenciaday(datedesdedb, datehastadb);
				long horasdb = UtilServicio.getDiferenciadayHoras(datedesdedb, datehastadb);

				if (diasdb == 0) {

					if (horasdb >= 2) {

						mapaUltimoGPSJson.setEstadodb("2");
						mapaUltimoGPSJson.setDescestadodb(
								"<small style='color: orange; font-size :12px;'>Retraso de Datos<i class='fa fa-warning'></i> </small>");

					} else {

						mapaUltimoGPSJson.setEstadodb("1");
						mapaUltimoGPSJson.setDescestadodb(
								"<small style='color: green; font-size :12px;'>Activo<i class='fa fa-check'></i> </small>");

					}

				} else {

					mapaUltimoGPSJson.setEstadodb("3");
					mapaUltimoGPSJson.setDescestadodb(
							"<small style='color: red; font-size :12px;'>Sin Transmision<i class='fa fa-minus-circle'></i> </small>");

				}
				// fin estado

				if (dgu.getRutGeocerca() != null) {

					mapaUltimoGPSJson.setRutGeocerca(dgu.getRutGeocerca());

				} else {
					mapaUltimoGPSJson.setRutGeocerca(dgu.getRutGeocoding());

				}

				// vehiculo
				mapaUltimoGPSJson.setPatente(v.getVehPatente());
				mapaUltimoGPSJson.setTipoVehiculo(v.getTipoVehiculo().getTipvDescripcion());
				mapaUltimoGPSJson.setIdTipoVehiculo(v.getTipoVehiculo().getTipvId());
				mapaUltimoGPSJson.setMarca(v.getVehMarca());
				mapaUltimoGPSJson.setNroInterno(v.getVehNumInterno());

				mapaUltimoGPSJson.setTipo(v.getTipoVehiculo().getTipo());
				mapaUltimoGPSJson.setIcon(v.getTipoVehiculo().getTivRutaIcono());

				//
				String classEvent = UtilServicio.getClassEventJS(dgu.getRutEvento(), "");

				EventosGp evento = new EventosGp();

				evento = emS.find(EventosGp.class, dgu.getRutEvento());

				// evento = ReporteService.findEvento(dgu.getRutEvento());

				mapaUltimoGPSJson.setClassEvent(classEvent);
				try {
					mapaUltimoGPSJson.setEvento(evento.getEveNombre());
				} catch (Exception e) {
					mapaUltimoGPSJson.setEvento("N/A");
				}

				mlist.add(mapaUltimoGPSJson);

			}

		}

		emS.clear();
		emS.close();

		return mlist;
	}

	// para el dashboard3, separarlo del mapa
	public static List<MapaUltimoGPSJson> findVehiculoUltimaPosicionAjaxD(String cliente, String rut) {

		// PropertyConfigurator.configure(log4jConfPath);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		emD = emf.createEntityManager();

		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Query query = emD
				.createQuery("select v from Vehiculo v " + "where v.cliente.cliRut = :cliente " + "and v.vehPatente  "
						+ "in " + "(select u.vehPatenteVehiculo from UsuarioVehiculo u where u.usuRutUsuario = :rut ) "
						+ "order by v.vehNumInterno asc ");
		// query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		query.setParameter("cliente", cliente);
		query.setParameter("rut", rut);

		ArrayList<MapaUltimoGPSJson> mlist = new ArrayList<MapaUltimoGPSJson>();

		List<Vehiculo> vlist = query.getResultList();

		for (Vehiculo v : vlist) {

			MapaUltimoGPSJson mapaUltimoGPSJson = new MapaUltimoGPSJson();

			DatosGpsUltimo dgu = new DatosGpsUltimo();

			// usando el mismo entity manager inicio
			Query query02 = emD.createQuery("select d from DatosGpsUltimo d where d.id.rutPatente = :patente ");
			query02.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
			query02.setHint("javax.persistence.query.timeout", 1000);
			// query.setHint(QueryHints.QUERY_TIMEOUT, "100");
			// query.setHint(QueryHints.JDBC_TIMEOUT, "100");

			query02.setParameter("patente", v.getVehPatente());

			// DatosGpsUltimo d = new DatosGpsUltimo();

			try {
				dgu = (DatosGpsUltimo) query02.getSingleResult();
			} catch (Exception e) {
				dgu = null;
			}
			// fin

			// dgu =
			// ReporteService.findUiltimaPosicionByVehiculo(v.getVehPatente());

			String geocerca = "";

			if (dgu != null) {

				mapaUltimoGPSJson.setLat(dgu.getRutLatitud());
				mapaUltimoGPSJson.setLon(dgu.getRutLongitud());
				mapaUltimoGPSJson.setVelocidad(String.valueOf(dgu.getRutVelocidad()));
				mapaUltimoGPSJson.setFecha(dgu.getId().getRutFechaHora());
				mapaUltimoGPSJson.setFechaS(formatteri.format(dgu.getId().getRutFechaHora()));
				mapaUltimoGPSJson.setFechaSdb(formatteri.format(dgu.getRutFechaInserta()));

				// fecha actual
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				mapaUltimoGPSJson.setFechaAjaxS(formatteri.format(timestamp));

				// Estado GPS
				// 1 optimo, 2 advertencia, 3 alerta
				Date datedesde = new Date(dgu.getId().getRutFechaHora().getTime());
				Date datehasta = new Date();
				long dias = UtilServicio.getDiferenciaday(datedesde, datehasta);
				long horas = UtilServicio.getDiferenciadayHoras(datedesde, datehasta);

				if (dias == 0) {

					if (horas >= 2) {

						mapaUltimoGPSJson.setEstado("2");
						mapaUltimoGPSJson.setDescestado(
								"<small style='color: orange; font-size :12px;'>Retraso de Datos <i class='fa fa-warning'></i> </small>");

					} else {

						mapaUltimoGPSJson.setEstado("1");
						mapaUltimoGPSJson.setDescestado(
								"<small style='color: green; font-size :12px;'>Activo <i class='fa fa-check'></i> </small>");

					}

				} else {

					mapaUltimoGPSJson.setEstado("3");
					mapaUltimoGPSJson.setDescestado(
							"<small style='color: red; font-size :12px;'>Sin Transmision <i class='fa fa-minus-circle'></i> </small>");

				}
				// fin estado

				// Estado DB
				// 1 optimo, 2 advertencia, 3 alerta
				// dgu.getId().getRutFechaHora().getTime()
				Date datedesdedb = new Date(dgu.getRutFechaInserta().getTime());
				Date datehastadb = new Date();
				long diasdb = UtilServicio.getDiferenciaday(datedesdedb, datehastadb);
				long horasdb = UtilServicio.getDiferenciadayHoras(datedesdedb, datehastadb);

				if (diasdb == 0) {

					if (horasdb >= 2) {

						mapaUltimoGPSJson.setEstadodb("2");
						mapaUltimoGPSJson.setDescestadodb(
								"<small style='color: orange; font-size :12px;'>Retraso de Datos<i class='fa fa-warning'></i> </small>");

					} else {

						mapaUltimoGPSJson.setEstadodb("1");
						mapaUltimoGPSJson.setDescestadodb(
								"<small style='color: green; font-size :12px;'>Activo<i class='fa fa-check'></i> </small>");

					}

				} else {

					mapaUltimoGPSJson.setEstadodb("3");
					mapaUltimoGPSJson.setDescestadodb(
							"<small style='color: red; font-size :12px;'>Sin Transmision<i class='fa fa-minus-circle'></i> </small>");

				}
				// fin estado

				if (dgu.getRutGeocerca() != null) {

					mapaUltimoGPSJson.setRutGeocerca(dgu.getRutGeocerca());

				} else {
					mapaUltimoGPSJson.setRutGeocerca(dgu.getRutGeocoding());

				}

				// vehiculo
				mapaUltimoGPSJson.setPatente(v.getVehPatente());
				mapaUltimoGPSJson.setTipoVehiculo(v.getTipoVehiculo().getTipvDescripcion());
				mapaUltimoGPSJson.setIdTipoVehiculo(v.getTipoVehiculo().getTipvId());
				mapaUltimoGPSJson.setMarca(v.getVehMarca());
				mapaUltimoGPSJson.setNroInterno(v.getVehNumInterno());

				mapaUltimoGPSJson.setTipo(v.getTipoVehiculo().getTipo());
				mapaUltimoGPSJson.setIcon(v.getTipoVehiculo().getTivRutaIcono());

				//
				String classEvent = UtilServicio.getClassEventJS(dgu.getRutEvento(), "");

				EventosGp evento = new EventosGp();

				evento = emD.find(EventosGp.class, dgu.getRutEvento());

				// evento = ReporteService.findEvento(dgu.getRutEvento());

				mapaUltimoGPSJson.setClassEvent(classEvent);
				try {
					mapaUltimoGPSJson.setEvento(evento.getEveNombre());
				} catch (Exception e) {
					mapaUltimoGPSJson.setEvento("N/A");
				}

				mlist.add(mapaUltimoGPSJson);

			}

		}

		// emD.clear();
		// emD.close();

		return mlist;
	}

	// para el dashboard3, separarlo del mapa
	public static List<MapaUltimoGPSJson> findVehiculoUltimaPosicionAjaxD2(String cliente, String rut) {

		// PropertyConfigurator.configure(log4jConfPath);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		emD2 = emf.createEntityManager();

		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Query query = emD2
				.createQuery("select v from Vehiculo v " + "where v.cliente.cliRut = :cliente " + "and v.vehPatente  "
						+ "in " + "(select u.vehPatenteVehiculo from UsuarioVehiculo u where u.usuRutUsuario = :rut ) "
						+ "order by v.vehNumInterno asc ");
		// query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		query.setParameter("cliente", cliente);
		query.setParameter("rut", rut);

		ArrayList<MapaUltimoGPSJson> mlist = new ArrayList<MapaUltimoGPSJson>();

		List<Vehiculo> vlist = query.getResultList();

		for (Vehiculo v : vlist) {

			MapaUltimoGPSJson mapaUltimoGPSJson = new MapaUltimoGPSJson();

			DatosGpsUltimo dgu = new DatosGpsUltimo();

			// usando el mismo entity manager inicio
			query = emD2.createQuery("select d from DatosGpsUltimo d where d.id.rutPatente = :patente ");
			query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
			query.setHint("javax.persistence.query.timeout", 1000);
			// query.setHint(QueryHints.QUERY_TIMEOUT, "100");
			// query.setHint(QueryHints.JDBC_TIMEOUT, "100");

			query.setParameter("patente", v.getVehPatente());

			// DatosGpsUltimo d = new DatosGpsUltimo();

			try {
				dgu = (DatosGpsUltimo) query.getSingleResult();
			} catch (Exception e) {
				dgu = null;
			}
			// fin

			// dgu =
			// ReporteService.findUiltimaPosicionByVehiculo(v.getVehPatente());

			String geocerca = "";

			if (dgu != null) {

				mapaUltimoGPSJson.setLat(dgu.getRutLatitud());
				mapaUltimoGPSJson.setLon(dgu.getRutLongitud());
				mapaUltimoGPSJson.setVelocidad(String.valueOf(dgu.getRutVelocidad()));
				mapaUltimoGPSJson.setFecha(dgu.getId().getRutFechaHora());
				mapaUltimoGPSJson.setFechaS(formatteri.format(dgu.getId().getRutFechaHora()));
				mapaUltimoGPSJson.setFechaSdb(formatteri.format(dgu.getRutFechaInserta()));

				// fecha actual
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				mapaUltimoGPSJson.setFechaAjaxS(formatteri.format(timestamp));

				// Estado GPS
				// 1 optimo, 2 advertencia, 3 alerta
				Date datedesde = new Date(dgu.getId().getRutFechaHora().getTime());
				Date datehasta = new Date();
				long dias = UtilServicio.getDiferenciaday(datedesde, datehasta);
				long horas = UtilServicio.getDiferenciadayHoras(datedesde, datehasta);

				if (dias == 0) {

					if (horas >= 2) {

						mapaUltimoGPSJson.setEstado("2");
						mapaUltimoGPSJson.setDescestado(
								"<small style='color: orange; font-size :12px;'>Retraso de Datos <i class='fa fa-warning'></i> </small>");

					} else {

						mapaUltimoGPSJson.setEstado("1");
						mapaUltimoGPSJson.setDescestado(
								"<small style='color: green; font-size :12px;'>Activo <i class='fa fa-check'></i> </small>");

					}

				} else {

					mapaUltimoGPSJson.setEstado("3");
					mapaUltimoGPSJson.setDescestado(
							"<small style='color: red; font-size :12px;'>Sin Transmision <i class='fa fa-minus-circle'></i> </small>");

				}
				// fin estado

				// Estado DB
				// 1 optimo, 2 advertencia, 3 alerta
				// dgu.getId().getRutFechaHora().getTime()
				Date datedesdedb = new Date(dgu.getRutFechaInserta().getTime());
				Date datehastadb = new Date();
				long diasdb = UtilServicio.getDiferenciaday(datedesdedb, datehastadb);
				long horasdb = UtilServicio.getDiferenciadayHoras(datedesdedb, datehastadb);

				if (diasdb == 0) {

					if (horasdb >= 2) {

						mapaUltimoGPSJson.setEstadodb("2");
						mapaUltimoGPSJson.setDescestadodb(
								"<small style='color: orange; font-size :12px;'>Retraso de Datos<i class='fa fa-warning'></i> </small>");

					} else {

						mapaUltimoGPSJson.setEstadodb("1");
						mapaUltimoGPSJson.setDescestadodb(
								"<small style='color: green; font-size :12px;'>Activo<i class='fa fa-check'></i> </small>");

					}

				} else {

					mapaUltimoGPSJson.setEstadodb("3");
					mapaUltimoGPSJson.setDescestadodb(
							"<small style='color: red; font-size :12px;'>Sin Transmision<i class='fa fa-minus-circle'></i> </small>");

				}
				// fin estado

				if (dgu.getRutGeocerca() != null) {

					mapaUltimoGPSJson.setRutGeocerca(dgu.getRutGeocerca());

				} else {
					mapaUltimoGPSJson.setRutGeocerca(dgu.getRutGeocoding());

				}

				// vehiculo
				mapaUltimoGPSJson.setPatente(v.getVehPatente());
				mapaUltimoGPSJson.setTipoVehiculo(v.getTipoVehiculo().getTipvDescripcion());
				mapaUltimoGPSJson.setIdTipoVehiculo(v.getTipoVehiculo().getTipvId());
				mapaUltimoGPSJson.setMarca(v.getVehMarca());
				mapaUltimoGPSJson.setNroInterno(v.getVehNumInterno());

				mapaUltimoGPSJson.setTipo(v.getTipoVehiculo().getTipo());
				mapaUltimoGPSJson.setIcon(v.getTipoVehiculo().getTivRutaIcono());

				//
				String classEvent = UtilServicio.getClassEventJS(dgu.getRutEvento(), "");

				EventosGp evento = new EventosGp();

				evento = emD2.find(EventosGp.class, dgu.getRutEvento());

				// evento = ReporteService.findEvento(dgu.getRutEvento());

				mapaUltimoGPSJson.setClassEvent(classEvent);
				try {
					mapaUltimoGPSJson.setEvento(evento.getEveNombre());
				} catch (Exception e) {
					mapaUltimoGPSJson.setEvento("N/A");
				}

				mlist.add(mapaUltimoGPSJson);

			}

		}

		emD2.clear();
		emD2.close();

		return mlist;
	}

	public static DatosGpsUltimo findUiltimaPosicionByVehiculo(String patente) {

		// PropertyConfigurator.configure(log4jConfPath);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		emS2 = emf.createEntityManager();
		// em4.setProperty("idle_in_transaction_session_timeout", "10000");

		Query query = emS2.createQuery("select d from DatosGpsUltimo d where d.id.rutPatente = :patente ");
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		query.setHint("javax.persistence.query.timeout", 1000);
		// query.setHint(QueryHints.QUERY_TIMEOUT, "100");
		// query.setHint(QueryHints.JDBC_TIMEOUT, "100");

		query.setParameter("patente", patente);

		DatosGpsUltimo d = new DatosGpsUltimo();

		try {
			d = (DatosGpsUltimo) query.getSingleResult();
		} catch (Exception e) {
			d = null;
		}

		emS2.clear();
		emS2.close();

		return d;
	}

	public static DatosGpsUltimo findUiltimaPosicionByVehiculoC(String patente, EntityManager em) {

		// PropertyConfigurator.configure(log4jConfPath);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		em = emf.createEntityManager();
		// em4.setProperty("idle_in_transaction_session_timeout", "10000");

		Query query = em.createQuery("select d from DatosGpsUltimo d where d.id.rutPatente = :patente ");
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		query.setHint("javax.persistence.query.timeout", 1000);
		// query.setHint(QueryHints.QUERY_TIMEOUT, "100");
		// query.setHint(QueryHints.JDBC_TIMEOUT, "100");

		query.setParameter("patente", patente);

		DatosGpsUltimo d = new DatosGpsUltimo();

		try {
			d = (DatosGpsUltimo) query.getSingleResult();
		} catch (Exception e) {
			d = null;
		}

		// se cierra en el padre
		// em.clear();
		// em.close();

		return d;
	}

	public static MapaUltimoGPS findVehiculoUltimaPosicionxPatente(String patente) {

		// PropertyConfigurator.configure(log4jConfPath);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		em3 = emf.createEntityManager();

		em4 = emf.createEntityManager();

		Query query = em3.createQuery("select d from DatosGpsUltimo d where d.id.rutPatente = :patente ");
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		query.setParameter("patente", patente);

		DatosGpsUltimo d = new DatosGpsUltimo();

		try {
			d = (DatosGpsUltimo) query.getSingleResult();
		} catch (Exception e) {
			d = null;
		}

		MapaUltimoGPS mapaUltimoGPS = new MapaUltimoGPS();

		if (d != null) {

			mapaUltimoGPS.setLat(d.getRutLatitud());
			mapaUltimoGPS.setLon(d.getRutLongitud());
			mapaUltimoGPS.setVelocidad(String.valueOf(d.getRutVelocidad()));
			mapaUltimoGPS.setFecha(d.getRutFechaInserta());

			Vehiculo vehiculo = em4.find(Vehiculo.class, patente);

			mapaUltimoGPS.setVehiculo(vehiculo);

			// falta descripcion eventos
			String classEvent = UtilServicio.getClassEventJS(d.getRutEvento(), "");

			mapaUltimoGPS.setClassEvent(classEvent);

		}

		em3.clear();
		em3.close();
		em4.clear();
		em4.close();

		return mapaUltimoGPS;
	}

	public static List<VehiculoActive> findResumenDescarga(String cliente, String desde, String hasta, String patente) {

		String fechanulas = "";
		// dd-MM-yyyy
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // dd/MM/yyyy

		Calendar calendarin = Calendar.getInstance();
		Calendar calendarfin = Calendar.getInstance();

		Date fecdesde = new Date();
		Date fechasta = new Date();

		// si las fechas vienen nulo se calcula el mes completo
		if (desde.equals("") && hasta.equals("")) {

			fechanulas = "S";

		}

		try {
			fecdesde = formatteri.parse(desde);
			fechasta = formatteri.parse(hasta);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		// configuro las hora inicio y fin
		calendarin.setTime(fecdesde);

		// si las fechas vienen nul se resta el mes a la fecha inicio y fecha
		// fin queda como dia hoy
		if (fechanulas.equals("S")) {

			calendarfin.add(Calendar.DAY_OF_MONTH, -7);

		}

		calendarfin.setTime(fechasta);

		// las convierto timestamp para insertarlas en el query
		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
		Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

		// PropertyConfigurator.configure(log4jConfPath);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		em3 = emf.createEntityManager();

		String sql = " ";

		if (patente.equals("todos")) {

			sql = " ";
		} else {

			sql = " and v.vehPatente = :patente ";

		}
		// solo camiones , tipo 6 , 30
		Query query = em3.createQuery("select v from Vehiculo v where 1 = 1 " + sql
				+ " and v.cliente.cliRut = :cliente " + "and v.tipoVehiculo.tipvId in (6,30) ");
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		query.setParameter("cliente", cliente);
		if (!patente.equals("todos")) {
			query.setParameter("patente", patente);
		}

		List<Vehiculo> vlist = query.getResultList();

		ArrayList<VehiculoActive> vclist = new ArrayList<VehiculoActive>();

		for (Vehiculo v : vlist) {

			long vueltaslong = 0;
			Integer vueltas = 0;// (int) (long) vueltaslong;

			Calendar start = Calendar.getInstance();
			start.setTimeInMillis(timein.getTime());

			Calendar end = Calendar.getInstance();
			end.setTimeInMillis(timefin.getTime());

			ArrayList<ReporteResumenDescargaActive> mlist = new ArrayList<ReporteResumenDescargaActive>();

			for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {

				ReporteResumenDescargaActive reporteResumenDescargaActive = new ReporteResumenDescargaActive();

				Timestamp fecha = new Timestamp(date.getTime());

				Calendar c = Calendar.getInstance();

				c.setTime(fecha);

				vueltaslong = ReporteService.findResumenDescargabyPatente(v.getVehPatente(), fecha);

				vueltas = (int) (long) vueltaslong;

				if (vueltas != 0) {

					reporteResumenDescargaActive.setFecha(fecha);
					reporteResumenDescargaActive.setPatente(v.getVehPatente());
					// reporteResumenDescargaActive.setProduccion(vueltas*20);
					reporteResumenDescargaActive
							.setProduccion(Integer.valueOf((int) (vueltas * v.getVehCargaVolumen())));
					reporteResumenDescargaActive.setVueltas(vueltas);
					reporteResumenDescargaActive.setDia(String.valueOf(c.get(Calendar.DAY_OF_MONTH)));
					reporteResumenDescargaActive.setMes(String.valueOf(c.get(Calendar.MONTH)));
					reporteResumenDescargaActive.setAno(String.valueOf(c.get(Calendar.YEAR)));

					mlist.add(reporteResumenDescargaActive);

				}
				if (vueltas == 0) {

					reporteResumenDescargaActive.setFecha(fecha);
					reporteResumenDescargaActive.setPatente(v.getVehPatente());
					reporteResumenDescargaActive.setVueltas(vueltas);
					reporteResumenDescargaActive.setDia(String.valueOf(c.get(Calendar.DAY_OF_MONTH)));
					reporteResumenDescargaActive.setMes(String.valueOf(c.get(Calendar.MONTH)));
					reporteResumenDescargaActive.setAno(String.valueOf(c.get(Calendar.YEAR)));

					mlist.add(reporteResumenDescargaActive);

				}
				if (vueltas == 0) {

					reporteResumenDescargaActive.setFecha(fecha);
					reporteResumenDescargaActive.setPatente(v.getVehPatente());
					reporteResumenDescargaActive.setVueltas(vueltas);
					reporteResumenDescargaActive.setDia(String.valueOf(c.get(Calendar.DAY_OF_MONTH)));
					reporteResumenDescargaActive.setMes(String.valueOf(c.get(Calendar.MONTH)));
					reporteResumenDescargaActive.setAno(String.valueOf(c.get(Calendar.YEAR)));

					mlist.add(reporteResumenDescargaActive);

				}

			} // fin for fecha
			VehiculoActive vc = new VehiculoActive();
			if (mlist.size() > 0) {
				vc.setVehPatente(v.getVehPatente());
				vc.setListreporteResumenDescargaActive(mlist);

				vclist.add(vc);
			}
		} // for vehiculo

		em3.clear();
		em3.close();

		return vclist;
	}

	public static long findResumenDescargabyPatente(String patente, Timestamp fecha) {

		Calendar calendarin = Calendar.getInstance();
		Calendar calendarfin = Calendar.getInstance();

		// configuro las hora inicio y fin
		calendarin.setTime(fecha);
		calendarin.add(Calendar.HOUR_OF_DAY, 00);
		calendarin.add(Calendar.MINUTE, 00);
		calendarin.add(Calendar.SECOND, 00);

		calendarfin.setTime(fecha);
		calendarfin.add(Calendar.HOUR_OF_DAY, 23);
		calendarfin.add(Calendar.MINUTE, 59);
		calendarfin.add(Calendar.SECOND, 59);

		// las convierto timestamp para insertarlas en el query
		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
		Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

		Date datein = new Date(timein.getTime());
		Date datefin = new Date(timefin.getTime());

		// PropertyConfigurator.configure(log4jConfPath);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		em4 = emf.createEntityManager();

		Query query = em4.createQuery("select Count(d) from DatosTolva d where d.id.tolPatente = :patente "
				+ " and d.id.tolFechaHora  >= :timein and d.id.tolFechaHora <= :timefin and d.eventosGp.eveIdId = 7 ");
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		query.setParameter("patente", patente);
		query.setParameter("timein", timein);
		query.setParameter("timefin", timefin);

		long vueltas = 0;

		try {
			vueltas = (Long) query.getSingleResult();
		} catch (Exception e) {
			// System.out.println(e);
			vueltas = 0;
		}

		em4.clear();
		em4.close();

		return vueltas;
	}

	public static EventosGp findEvento(Integer id) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		em2 = emf.createEntityManager();
		EventosGp evento = em2.find(EventosGp.class, id);

		em2.clear();
		em2.close();
		return evento;
	}

	public static Zona findZona(Integer id) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		em2 = emf.createEntityManager();
		Zona zona = em2.find(Zona.class, id);

		em2.clear();
		em2.close();
		return zona;

	}

	public static List<ListadoColorActive> listadoColoresGeocercas() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		em = emf.createEntityManager();

		Query query = em.createNativeQuery(" select * from cliente.color_geo ");

		ArrayList<ListadoColorActive> colorList = new ArrayList<ListadoColorActive>();

		List<Object[]> clist = query.getResultList();

		Integer i = 0;

		for (Object[] c : clist) {
			i++;
			ListadoColorActive col = new ListadoColorActive();
			col.setId_color((Integer) c[0]);
			col.setNombre((String) c[1]);
			col.setCodigo((String) c[2]);

			colorList.add(col);
		}

		em.clear();
		em.close();

		return colorList;

	}

	public static List<PatenteGPSActive> findPatentesXClienteFechaTipo(String cliente, String clave, Date fecha,
			String tipos, String clirut) {
		String sql = "";
		// camion y dumper
		if (tipos.equals("c")) {

			// tipos = "6, 30";
			// tipos = "'c'";
			// sql = "and tipo in ("+tipos+") ";
			sql = " and tipo='c' ";

		} // maquina
		else if (tipos.equals("m")) {

			// tipos = "11, 19, 22, 25, 26, 27, 28";
			// tipos = "'m'";
			// sql = "and tipo in ("+tipos+") ";
			sql = " and tipo='m' ";

		} // todos
		else if (tipos.equals("t")) {

			// tipos = "11, 19, 22, 25, 26, 27, 6, 28, 30";
			sql = " ";
		}

		Calendar calendarin = Calendar.getInstance();

		calendarin.setTime(fecha);
		calendarin.add(Calendar.HOUR_OF_DAY, 00);
		calendarin.add(Calendar.MINUTE, 00);
		calendarin.add(Calendar.SECOND, 00);

		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		em3 = emf.createEntityManager();

		Query query02 = em3.createNativeQuery("select usuveh_id, fn_getdevice('idgps',veh_patente_vehiculo,'" + timein
				+ "'), veh_patente_vehiculo, veh_num_interno "
				+ "from  dispositivo.usuario_vehiculo, dispositivo.vehiculo " + ", dispositivo.tipo_vehiculo "
				+ "where usu_rut_usuario= " + "(select usu_rut from cliente.usuario where usu_login= '" + cliente
				+ "' and usu_clave= '" + clave + "') " + "and veh_patente_vehiculo=veh_patente "
				// + "and tipv_id_tipo_vehiculo in ("+tipos+") "
				+ "and tipv_id_tipo_vehiculo = tipv_id "
				// + "and tipo in ("+tipos+") "
				+ sql + "and dispositivo.vehiculo.cli_rut_cliente = '" + clirut + "' " + " ");

		List<PatenteGPSActive> listapatente = new ArrayList();

		List<Object[]> patentelist = query02.getResultList();

		for (Object[] pa : patentelist) {

			PatenteGPSActive pa2 = new PatenteGPSActive();

			Integer usuvehId = (Integer) pa[0];
			String fnGetdevice = (String) pa[1];
			String vehPatenteVehiculo = (String) pa[2];
			String nroInterno = (String) pa[3];

			pa2.setUsuvehId(usuvehId);
			pa2.setFnGetdevice(fnGetdevice);
			pa2.setVehPatenteVehiculo(vehPatenteVehiculo);
			pa2.setNroInterno(nroInterno);

			listapatente.add(pa2);

		}

		em3.clear();
		em3.close();

		return listapatente;
	}

	public static List<PatenteGPSActive> findPatentesXClienteFechaTipoNotIn(String cliente, String clave, Date fecha,
			String tipos, String patentes, String notin, String clirut) {

		String sql = "";
		if (tipos.equals("c")) {

			// tipos = "6, 30";
			tipos = "'c'";
			sql = "and tipo in (" + tipos + ") ";

		}
		if (tipos.equals("m")) {

			// tipos = "11, 19, 22, 25, 26, 27, 28";
			tipos = "'m'";
			sql = "and tipo in (" + tipos + ") ";

		}
		if (tipos.equals("t")) {

			// tipos = "11, 19, 22, 25, 26, 27, 6, 28, 30";

		}

		Calendar calendarin = Calendar.getInstance();

		calendarin.setTime(fecha);
		calendarin.add(Calendar.HOUR_OF_DAY, 00);
		calendarin.add(Calendar.MINUTE, 00);
		calendarin.add(Calendar.SECOND, 00);

		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		em3 = emf.createEntityManager();

		// String sql = "";

		String[] parts = patentes.split(",");
		String patentesplit = "";
		for (int i = 0; i < parts.length; i++) {

			patentesplit = "'" + parts[i] + "'," + patentesplit;

		}
		patentesplit = patentesplit.substring(0, patentesplit.length() - 1);

		if (notin.equals("N")) {

			sql = sql + " and veh_patente not in  (" + patentesplit + ") ";

		}
		if (notin.equals("S")) {

			sql = sql + " and veh_patente in  (" + patentesplit + ") ";

		}

		Query query02 = em3.createNativeQuery("select usuveh_id, fn_getdevice('idgps',veh_patente_vehiculo,'" + timein
				+ "'), veh_patente_vehiculo, veh_num_interno "
				+ "from  dispositivo.usuario_vehiculo, dispositivo.vehiculo " + ", dispositivo.tipo_vehiculo "
				+ "where usu_rut_usuario= " + "(select usu_rut from cliente.usuario where usu_login= '" + cliente
				+ "' and usu_clave= '" + clave + "') " + "and veh_patente_vehiculo=veh_patente "
				// + sql
				// + "and tipv_id_tipo_vehiculo in ("+tipos+") "
				+ "and tipv_id_tipo_vehiculo = tipv_id " + sql + "and dispositivo.vehiculo.cli_rut_cliente = '" + clirut
				+ "' " + " ");

		List<PatenteGPSActive> listapatente = new ArrayList();

		List<Object[]> patentelist = query02.getResultList();

		for (Object[] pa : patentelist) {

			PatenteGPSActive pa2 = new PatenteGPSActive();

			Integer usuvehId = (Integer) pa[0];
			String fnGetdevice = (String) pa[1];
			String vehPatenteVehiculo = (String) pa[2];
			String nroInterno = (String) pa[3];

			pa2.setUsuvehId(usuvehId);
			pa2.setFnGetdevice(fnGetdevice);
			pa2.setVehPatenteVehiculo(vehPatenteVehiculo);
			pa2.setNroInterno(nroInterno);

			listapatente.add(pa2);

		}

		em3.clear();
		em3.close();

		return listapatente;
	}

	public static List<ReporteTiempoFueraOperacionDetalleActive> findInformeGeocercas(String desde, String hasta,
			String zona, String patente, String usuario) {

		String fechanulas = "";

		// dd-MM-yyyy
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // dd/MM/yyyy
		em = emf.createEntityManager();

		Calendar calendarin = Calendar.getInstance();
		Calendar calendarfin = Calendar.getInstance();

		Date fecdesde = new Date();
		Date fechasta = new Date();

		// si las fechas vienen nulo se calcula el mes completo
		if (desde.equals("") && hasta.equals("")) {

			fechanulas = "S";

		}

		try {
			fecdesde = formatteri.parse(desde);
			fechasta = formatteri.parse(hasta);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		// configuro las hora inicio y fin
		calendarin.setTime(fecdesde);

		// si las fechas vienen nul se resta el mes a la fecha inicio y fecha
		// fin queda como dia hoy
		if (fechanulas.equals("S")) {

			calendarfin.add(Calendar.DAY_OF_MONTH, -2);

		}

		calendarfin.setTime(fechasta);

		// las convierto timestamp para insertarlas en el query
		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
		Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

		// informegeocercas(@fecha_ini,@fecha_fin,0, 0,@id_geocerca,0 ,@patente,
		// @cliente)
		// select * from informegeocercas('01/01/2019 00:00:00','03/25/2019
		// 23:59:00',0, 0,69,0 ,'HKPC22','emexcosa')
		StoredProcedureQuery query = em.createStoredProcedureQuery("informegeocercas");
		query.registerStoredProcedureParameter(1, Timestamp.class, ParameterMode.IN); // fecha
																						// in
		query.registerStoredProcedureParameter(2, Timestamp.class, ParameterMode.IN); // fechan
																						// fin
		query.registerStoredProcedureParameter(3, int.class, ParameterMode.IN); // 0
		query.registerStoredProcedureParameter(4, int.class, ParameterMode.IN); // 0
		query.registerStoredProcedureParameter(5, int.class, ParameterMode.IN); // zona
		query.registerStoredProcedureParameter(6, int.class, ParameterMode.IN); // 0
		query.registerStoredProcedureParameter(7, String.class, ParameterMode.IN); // patente
		query.registerStoredProcedureParameter(8, String.class, ParameterMode.IN); // usuario
		query.registerStoredProcedureParameter(9, int.class, ParameterMode.IN); // velocidad

		query.setParameter(1, timein);
		query.setParameter(2, timefin);
		query.setParameter(3, 0);
		query.setParameter(4, 0);
		query.setParameter(5, Integer.valueOf(zona));
		query.setParameter(6, 1);
		query.setParameter(7, patente);
		query.setParameter(8, usuario);
		query.setParameter(9, 0);

		query.execute();

		// in patente ubicacion interno chofer faena vel e hdg lat lon
		// 0 1 2 3 4 5 6 7 8 9 19
		// "2019-03-22 12:08:26-03" "HKPC22" "Plataforma Tuberia Noroeste"
		// "CT.316" "No Registra Operador" faena 5 0 85 "-22.72928" "-069.31876"
		ArrayList<ReporteTiempoFueraOperacionDetalleActive> rlist = new ArrayList<ReporteTiempoFueraOperacionDetalleActive>();

		List<Object[]> mapalist = query.getResultList();
		Integer i = 0;

		for (Object[] ruta : mapalist) {
			i++;

			ReporteTiempoFueraOperacionDetalleActive reporteTiempoFueraOperacionDetalleActive = new ReporteTiempoFueraOperacionDetalleActive();

			Timestamp fechain = (Timestamp) ruta[0];
			String patente2 = (String) ruta[1];
			String ubicacion = (String) ruta[2];
			String nrointerno = (String) ruta[3];
			String chofer = (String) ruta[4];
			// int faena = (Integer) ruta[5];
			int vel = (Integer) ruta[6];
			int evento = (Integer) ruta[7];
			int hdg = (Integer) ruta[8];
			String lat = (String) ruta[9];
			String lon = (String) ruta[10];

			reporteTiempoFueraOperacionDetalleActive.setFechain(fechain);
			reporteTiempoFueraOperacionDetalleActive.setPatente(patente2);
			reporteTiempoFueraOperacionDetalleActive.setUbicacion(ubicacion);
			reporteTiempoFueraOperacionDetalleActive.setNrointerno(nrointerno);
			reporteTiempoFueraOperacionDetalleActive.setChofer(chofer);
			// reporteTiempoFueraOperacionDetalleActive.setFaena(String.valueOf(faena));
			reporteTiempoFueraOperacionDetalleActive.setVel(String.valueOf(vel));
			reporteTiempoFueraOperacionDetalleActive.setEvento(String.valueOf(evento));
			reporteTiempoFueraOperacionDetalleActive.setHdg(hdg);
			reporteTiempoFueraOperacionDetalleActive.setLat(lat);
			reporteTiempoFueraOperacionDetalleActive.setLon(lon);

			String classEvent = UtilServicio.getClassEventEntradaSalidad(evento, "");
			reporteTiempoFueraOperacionDetalleActive.setClassEvent(classEvent);

			if (evento == 64) {

				reporteTiempoFueraOperacionDetalleActive.setNombreve("Entrada");
			} else {
				reporteTiempoFueraOperacionDetalleActive.setNombreve("Salida");
			}

			reporteTiempoFueraOperacionDetalleActive.setIdEvent(String.valueOf(evento));

			FlechaActive flecha = UtilServicio.getFlecha(hdg);

			reporteTiempoFueraOperacionDetalleActive.setNomflecha(flecha.getNomflecha());
			reporteTiempoFueraOperacionDetalleActive.setRutaflecha(flecha.getRutaflecha());

			rlist.add(reporteTiempoFueraOperacionDetalleActive);

		}

		em.clear();
		em.close();

		return rlist;

	}

	public static List<ReporteTiempoFueraOperacionDetalleActive> findInformeDetenciones(String desde, String hasta,
			String zona, String patente, String usuario) {

		String fechanulas = "";

		// dd-MM-yyyy
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // dd/MM/yyyy
		em = emf.createEntityManager();

		Calendar calendarin = Calendar.getInstance();
		Calendar calendarfin = Calendar.getInstance();

		Date fecdesde = new Date();
		Date fechasta = new Date();

		// si las fechas vienen nulo se calcula el mes completo
		if (desde.equals("") && hasta.equals("")) {

			fechanulas = "S";

		}

		try {
			fecdesde = formatteri.parse(desde);
			fechasta = formatteri.parse(hasta);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		// configuro las hora inicio y fin
		calendarin.setTime(fecdesde);

		// si las fechas vienen nul se resta el mes a la fecha inicio y fecha
		// fin queda como dia hoy
		if (fechanulas.equals("S")) {

			calendarfin.add(Calendar.DAY_OF_MONTH, -2);

		}

		calendarfin.setTime(fechasta);

		// las convierto timestamp para insertarlas en el query
		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
		Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

		// informegeocercas(@fecha_ini,@fecha_fin,0, 0,@id_geocerca,0 ,@patente,
		// @cliente)
		// select * from informegeocercas('01/01/2019 00:00:00','03/25/2019
		// 23:59:00',0, 0,69,0 ,'HKPC22','emexcosa')

		// informedetenciones(@fecha_ini,@fecha_fin,0,0,@patente,@usuario,0)
		// select * from public.informedetenciones('11/28/2018
		// 00:00:00','11/28/2019 23:59:00',0,0,'HHFD98','emexcosa',0)

		StoredProcedureQuery query = em.createStoredProcedureQuery("informedetenciones");
		query.registerStoredProcedureParameter(1, Timestamp.class, ParameterMode.IN); // fecha
																						// in
		query.registerStoredProcedureParameter(2, Timestamp.class, ParameterMode.IN); // fechan
																						// fin
		query.registerStoredProcedureParameter(3, int.class, ParameterMode.IN); // 0
		query.registerStoredProcedureParameter(4, int.class, ParameterMode.IN); // 0
		query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN); // patente
		query.registerStoredProcedureParameter(6, String.class, ParameterMode.IN); // usuario
		query.registerStoredProcedureParameter(7, int.class, ParameterMode.IN); // 0

		query.setParameter(1, timein);
		query.setParameter(2, timefin);
		query.setParameter(3, 0);
		query.setParameter(4, 0);
		query.setParameter(5, patente);
		query.setParameter(6, usuario);
		query.setParameter(7, 0);

		query.execute();

		// in patete ubicacion interno chofer faena vel e hdg lat lon evento
		// "2019-04-02 08:56:27-03" "HHFD98" "Proyecto Emexco" null "No
		// asignado" 25 64 97 "-22.74986" "-069.29478" "Puesta en Marcha"

		ArrayList<ReporteTiempoFueraOperacionDetalleActive> rlist = new ArrayList<ReporteTiempoFueraOperacionDetalleActive>();

		List<Object[]> mapalist = query.getResultList();
		Integer i = 0;

		for (Object[] ruta : mapalist) {
			i++;

			ReporteTiempoFueraOperacionDetalleActive reporteTiempoFueraOperacionDetalleActive = new ReporteTiempoFueraOperacionDetalleActive();

			Timestamp fechain = (Timestamp) ruta[0];
			String patente2 = (String) ruta[1];
			String ubicacion = (String) ruta[2];
			String nrointerno = (String) ruta[3];
			String chofer = (String) ruta[4];
			// int faena = (Integer) ruta[5];
			int vel = (Integer) ruta[6];
			int evento = (Integer) ruta[7];
			int hdg = (Integer) ruta[8];
			String lat = (String) ruta[9];
			String lon = (String) ruta[10];
			String nombreve = (String) ruta[11];

			reporteTiempoFueraOperacionDetalleActive.setFechain(fechain);
			reporteTiempoFueraOperacionDetalleActive.setPatente(patente2);
			reporteTiempoFueraOperacionDetalleActive.setUbicacion(ubicacion);
			reporteTiempoFueraOperacionDetalleActive.setNrointerno(nrointerno);
			reporteTiempoFueraOperacionDetalleActive.setChofer(chofer);
			// reporteTiempoFueraOperacionDetalleActive.setFaena(String.valueOf(faena));
			reporteTiempoFueraOperacionDetalleActive.setVel(String.valueOf(vel));
			reporteTiempoFueraOperacionDetalleActive.setEvento(String.valueOf(evento));
			reporteTiempoFueraOperacionDetalleActive.setHdg(hdg);
			reporteTiempoFueraOperacionDetalleActive.setLat(lat);
			reporteTiempoFueraOperacionDetalleActive.setLon(lon);

			String classEvent = UtilServicio.getClassEventEntradaSalidad(evento, "");
			reporteTiempoFueraOperacionDetalleActive.setClassEvent(classEvent);

			reporteTiempoFueraOperacionDetalleActive.setNombreve(nombreve);

			/*
			 * if(evento == 64){
			 * 
			 * reporteTiempoFueraOperacionDetalleActive.setNombreve("Entrada");
			 * }else {
			 * reporteTiempoFueraOperacionDetalleActive.setNombreve("Salida"); }
			 */

			reporteTiempoFueraOperacionDetalleActive.setIdEvent(String.valueOf(evento));

			FlechaActive flecha = UtilServicio.getFlecha(hdg);

			reporteTiempoFueraOperacionDetalleActive.setNomflecha(flecha.getNomflecha());
			reporteTiempoFueraOperacionDetalleActive.setRutaflecha(flecha.getRutaflecha());

			// if()

			rlist.add(reporteTiempoFueraOperacionDetalleActive);

		}
		int i2 = 0;
		for (ReporteTiempoFueraOperacionDetalleActive r : rlist) {

			Date fechain = new Date();

			fechain.setTime(r.getFechain().getTime());

			r.getFechain().getTime();

		}

		em.clear();
		em.close();

		return rlist;

	}

	public static List<ReporteGeocercasActive> findReporteGeocercas(String fecha_ini, String fecha_fin, String sw,
			String faena, String geocerca, String solo_entradaysalida, String patente, String cliente,
			String velocidad) {

		// select * from
		// informegeocercas(@fecha_ini,@fecha_fin,@sw,@faena,@geocerca,@solo_entradaysalida,@patente,@cliente)
		// Timestamp,Timestamp,Integer,Integer,Integer,Integer,String,String)
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // dd/MM/yyyy
		em2 = emf.createEntityManager();

		Date fecdesde = new Date();
		Date fechasta = new Date();
		Calendar calendarin = Calendar.getInstance();
		Calendar calendarfin = Calendar.getInstance();
		try {
			fecdesde = formatteri.parse(fecha_ini);
			fechasta = formatteri.parse(fecha_fin);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendarin.setTime(fecdesde);
		calendarfin.setTime(fechasta);
		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
		Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

		// System.out.println("select * from
		// informegeocercas('"+timein+"','"+timefin+"',"+sw+","+faena+","+geocerca+","+solo_entradaysalida+",'"+patente+"','"+cliente+"',"+velocidad+")");

		StoredProcedureQuery query = em2.createStoredProcedureQuery("informegeocercas");
		query.registerStoredProcedureParameter(1, Timestamp.class, ParameterMode.IN); // id_geo
		query.registerStoredProcedureParameter(2, Timestamp.class, ParameterMode.IN); // usuario
		query.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN); // pw
		query.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(5, Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(6, Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(7, String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(8, String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(9, Integer.class, ParameterMode.IN);

		query.setParameter(1, timein);
		query.setParameter(2, timefin);
		query.setParameter(3, Integer.valueOf(sw));
		query.setParameter(4, Integer.valueOf(faena));
		query.setParameter(5, Integer.valueOf(geocerca));
		query.setParameter(6, Integer.valueOf(solo_entradaysalida));
		query.setParameter(7, patente);
		query.setParameter(8, cliente);
		query.setParameter(9, Integer.valueOf(velocidad));

		query.execute();

		///////////////////////////////////////

		/*
		 * Query query = em2.
		 * createNativeQuery("select * from informegeocercas('03/11/2018 00:00:00','03/19/2019 23:59:00',0,0,0,0,'HKPC22','emexcosa')"
		 * );
		 */
		List<Object[]> mlistFor = query.getResultList();

		List<ReporteGeocercasActive> mlist = new ArrayList();

		for (Object[] pa : mlistFor) {

			ReporteGeocercasActive pa2 = new ReporteGeocercasActive();

			pa2.setFecha_ini((Timestamp) pa[0]);
			pa2.setPatente((String) pa[1]);
			pa2.setNom_zona((String) pa[2]);
			pa2.setNum_interno((String) pa[3]);
			pa2.setChofer((String) pa[4]);
			pa2.setFaena((String) pa[5]);
			pa2.setVelocidad((Integer) pa[6]);
			pa2.setEvento((Integer) pa[7]);
			pa2.setHdg((Integer) pa[8]);
			pa2.setLat((String) pa[9]);
			pa2.setLon((String) pa[10]);
			pa2.setNombre_evento((String) UtilServicio.getClassEvent((Integer) pa[7], (String) pa[11]));
			FlechaActive flecha = UtilServicio.getFlecha((Integer) pa[8]);
			pa2.setNom_flecha(flecha.getNomflecha());
			pa2.setRuta_flecha(flecha.getRutaflecha());

			mlist.add(pa2);

		}

		em2.clear();
		em2.close();

		return mlist;
	}

	public static int findTiempoCarga(String desde, String hasta, String patente, String usuario) {

		String fechanulas = "";

		// dd-MM-yyyy
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // dd/MM/yyyy
		em3 = emf.createEntityManager();

		Calendar calendarin = Calendar.getInstance();
		Calendar calendarfin = Calendar.getInstance();

		Date fecdesde = new Date();
		Date fechasta = new Date();

		// si las fechas vienen nulo se calcula el mes completo
		if (desde.equals("") && hasta.equals("")) {

			fechanulas = "S";

		}

		try {
			fecdesde = formatteri.parse(desde);
			fechasta = formatteri.parse(hasta);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		// configuro las hora inicio y fin
		calendarin.setTime(fecdesde);

		// si las fechas vienen nul se resta el mes a la fecha inicio y fecha
		// fin queda como dia hoy
		if (fechanulas.equals("S")) {

			calendarfin.add(Calendar.DAY_OF_MONTH, -2);

		}

		calendarfin.setTime(fechasta);

		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
		Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

		StoredProcedureQuery query = em.createStoredProcedureQuery("findTiempoCarga");
		query.registerStoredProcedureParameter(1, Timestamp.class, ParameterMode.IN); // fecha
																						// in
		query.registerStoredProcedureParameter(2, Timestamp.class, ParameterMode.IN); // fechan
																						// fin
		query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN); // patente
		query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN); // usuario

		query.setParameter(1, timein);
		query.setParameter(2, timefin);
		query.setParameter(3, patente);
		query.setParameter(4, usuario);

		query.execute();

		int data = (Integer) query.getSingleResult();

		em3.clear();
		em3.close();

		return data;

	}

	public static int findCargaReal(String desde, String hasta, String patente, String usuario) {

		String fechanulas = "";

		// dd-MM-yyyy
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // dd/MM/yyyy
		em3 = emf.createEntityManager();

		Calendar calendarin = Calendar.getInstance();
		Calendar calendarfin = Calendar.getInstance();

		Date fecdesde = new Date();
		Date fechasta = new Date();

		// si las fechas vienen nulo se calcula el mes completo
		if (desde.equals("") && hasta.equals("")) {

			fechanulas = "S";

		}

		try {
			fecdesde = formatteri.parse(desde);
			fechasta = formatteri.parse(hasta);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		// configuro las hora inicio y fin
		calendarin.setTime(fecdesde);

		// si las fechas vienen nul se resta el mes a la fecha inicio y fecha
		// fin queda como dia hoy
		if (fechanulas.equals("S")) {

			calendarfin.add(Calendar.DAY_OF_MONTH, -2);

		}

		calendarfin.setTime(fechasta);

		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
		Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

		StoredProcedureQuery query = em.createStoredProcedureQuery("findTiempoCarga");
		query.registerStoredProcedureParameter(1, Timestamp.class, ParameterMode.IN); // fecha
																						// in
		query.registerStoredProcedureParameter(2, Timestamp.class, ParameterMode.IN); // fechan
																						// fin
		query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN); // patente
		query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN); // usuario

		query.setParameter(1, timein);
		query.setParameter(2, timefin);
		query.setParameter(3, patente);
		query.setParameter(4, usuario);

		query.execute();

		int data = (Integer) query.getSingleResult();

		em3.clear();
		em3.close();

		return data;

	}

	public static int findPases(String desde, String hasta, String patente, String usuario) {

		String fechanulas = "";

		// dd-MM-yyyy
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // dd/MM/yyyy
		em3 = emf.createEntityManager();

		Calendar calendarin = Calendar.getInstance();
		Calendar calendarfin = Calendar.getInstance();

		Date fecdesde = new Date();
		Date fechasta = new Date();

		// si las fechas vienen nulo se calcula el mes completo
		if (desde.equals("") && hasta.equals("")) {

			fechanulas = "S";

		}

		try {
			fecdesde = formatteri.parse(desde);
			fechasta = formatteri.parse(hasta);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		// configuro las hora inicio y fin
		calendarin.setTime(fecdesde);

		// si las fechas vienen nul se resta el mes a la fecha inicio y fecha
		// fin queda como dia hoy
		if (fechanulas.equals("S")) {

			calendarfin.add(Calendar.DAY_OF_MONTH, -2);

		}

		calendarfin.setTime(fechasta);

		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
		Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

		StoredProcedureQuery query = em.createStoredProcedureQuery("findTiempoCarga");
		query.registerStoredProcedureParameter(1, Timestamp.class, ParameterMode.IN); // fecha
																						// in
		query.registerStoredProcedureParameter(2, Timestamp.class, ParameterMode.IN); // fechan
																						// fin
		query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN); // patente
		query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN); // usuario

		query.setParameter(1, timein);
		query.setParameter(2, timefin);
		query.setParameter(3, patente);
		query.setParameter(4, usuario);

		query.execute();

		int data = (Integer) query.getSingleResult();

		em3.clear();
		em3.close();

		return data;

	}

	public static int findEspera(String desde, String hasta, String patente, String usuario) {

		String fechanulas = "";

		// dd-MM-yyyy
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // dd/MM/yyyy
		em3 = emf.createEntityManager();

		Calendar calendarin = Calendar.getInstance();
		Calendar calendarfin = Calendar.getInstance();

		Date fecdesde = new Date();
		Date fechasta = new Date();

		// si las fechas vienen nulo se calcula el mes completo
		if (desde.equals("") && hasta.equals("")) {

			fechanulas = "S";

		}

		try {
			fecdesde = formatteri.parse(desde);
			fechasta = formatteri.parse(hasta);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		// configuro las hora inicio y fin
		calendarin.setTime(fecdesde);

		// si las fechas vienen nul se resta el mes a la fecha inicio y fecha
		// fin queda como dia hoy
		if (fechanulas.equals("S")) {

			calendarfin.add(Calendar.DAY_OF_MONTH, -2);

		}

		calendarfin.setTime(fechasta);

		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
		Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

		StoredProcedureQuery query = em.createStoredProcedureQuery("findTiempoCarga");
		query.registerStoredProcedureParameter(1, Timestamp.class, ParameterMode.IN); // fecha
																						// in
		query.registerStoredProcedureParameter(2, Timestamp.class, ParameterMode.IN); // fechan
																						// fin
		query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN); // patente
		query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN); // usuario

		query.setParameter(1, timein);
		query.setParameter(2, timefin);
		query.setParameter(3, patente);
		query.setParameter(4, usuario);

		query.execute();

		int data = (Integer) query.getSingleResult();

		em3.clear();
		em3.close();

		return data;

	}

	public static int findRalenti(String desde, String hasta, String patente, String usuario) {

		String fechanulas = "";

		// dd-MM-yyyy
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // dd/MM/yyyy
		em3 = emf.createEntityManager();

		Calendar calendarin = Calendar.getInstance();
		Calendar calendarfin = Calendar.getInstance();

		Date fecdesde = new Date();
		Date fechasta = new Date();

		// si las fechas vienen nulo se calcula el mes completo
		if (desde.equals("") && hasta.equals("")) {

			fechanulas = "S";

		}

		try {
			fecdesde = formatteri.parse(desde);
			fechasta = formatteri.parse(hasta);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		// configuro las hora inicio y fin
		calendarin.setTime(fecdesde);

		// si las fechas vienen nul se resta el mes a la fecha inicio y fecha
		// fin queda como dia hoy
		if (fechanulas.equals("S")) {

			calendarfin.add(Calendar.DAY_OF_MONTH, -2);

		}

		calendarfin.setTime(fechasta);

		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
		Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

		StoredProcedureQuery query = em.createStoredProcedureQuery("findTiempoCarga");
		query.registerStoredProcedureParameter(1, Timestamp.class, ParameterMode.IN); // fecha
																						// in
		query.registerStoredProcedureParameter(2, Timestamp.class, ParameterMode.IN); // fechan
																						// fin
		query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN); // patente
		query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN); // usuario

		query.setParameter(1, timein);
		query.setParameter(2, timefin);
		query.setParameter(3, patente);
		query.setParameter(4, usuario);

		query.execute();

		int data = (Integer) query.getSingleResult();

		em3.clear();
		em3.close();

		return data;

	}

	public static List<ConductorActive> findConductoresXCliente(String clienterut) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		em3 = emf.createEntityManager();

		Query query = em3.createQuery(
				"select c from Conductor c where c.usuario.cliente.cliRut = :clienterut " + " order by c.condNombre ");
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		query.setParameter("clienterut", clienterut);

		List<Conductor> clist = query.getResultList();

		List<ConductorActive> calist = new ArrayList();

		for (Conductor c : clist) {

			ConductorActive ca = new ConductorActive();

			ca.setNombre(c.getCondNombre());
			ca.setApellido(c.getCondApellido());
			ca.setRut(c.getCondRut());
			ca.setCliRazonSocial(c.getUsuario().getCliente().getCliRazonSocial());

			// ArrayList<Ibuttom> ibuttoms =
			// DispositivoService.findIbuttomByConductor(c);

			query = em3.createQuery("select i from Ibuttom i " + "where  " + "i.ibuCodigo in "
					+ "(select ic.ibuIdIbuttom from IbuttomConductor ic where ic.condRutConductor = :rutconductor and ic.ibucondEstado = 1 ) "
					+ "and i.ibuEstado = 1 " + "order by i.ibuCodigo  ");
			query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
			query.setParameter("rutconductor", c.getCondRut());

			ArrayList<Ibuttom> ibuttoms = new ArrayList<Ibuttom>(query.getResultList());

			ca.setIbuttoms(ibuttoms);

			calist.add(ca);

		}

		// ConductorActive ca2 = new ConductorActive();
		// ca2.setNombre("No Asignado");
		// ca2.setRut("1AF1CA8E");

		// calist.add(ca2);

		em3.clear();
		em3.close();

		return calist;
	}

	public static List<MapaUltimoGPSJson> findVehiculoUltimaPosicionCategoriaAjax(String cliente, String categoria,
			String rut) {

		// PropertyConfigurator.configure(log4jConfPath);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		emSC = emf.createEntityManager();

		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		String sql = "";
		String tipos = "";
		// camion
		if (categoria.equals("c")) {

			// tipos = "6";
			sql = "and v.tipoVehiculo.tipvId in (6) ";

			// camion dumper
		}
		if (categoria.equals("d")) {

			// tipos = tipos = "30";
			sql = "and v.tipoVehiculo.tipvId in (30) ";
		}
		// maquina
		if (categoria.equals("m")) {

			// tipos = "11, 19, 22, 25, 26, 27, 28";
			// sql = "and v.tipoVehiculo.tipvId in (11, 19, 22, 25, 26, 27, 28)
			// ";
			sql = "and v.tipoVehiculo.tipo in ('m') ";

		}

		List<String> tlista = Arrays.asList(tipos);

		Query query = emSC.createQuery("select v from Vehiculo v where v.cliente.cliRut = :cliente "
				// + "and v.tipoVehiculo.tipvId in :tlista "
				+ sql + "and v.vehPatente " + "in "
				+ "(select u.vehPatenteVehiculo from UsuarioVehiculo u where u.usuRutUsuario = :rut ) "
				+ "order by v.vehNumInterno asc ");
		// query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		query.setParameter("cliente", cliente);
		query.setParameter("rut", rut);
		// query.setParameter("tlista", tlista);

		ArrayList<MapaUltimoGPSJson> mlist = new ArrayList<MapaUltimoGPSJson>();

		List<Vehiculo> vlist = query.getResultList();

		for (Vehiculo v : vlist) {

			MapaUltimoGPSJson mapaUltimoGPSJson = new MapaUltimoGPSJson();

			DatosGpsUltimo dgu = new DatosGpsUltimo();

			query = emSC.createQuery("select d from DatosGpsUltimo d where d.id.rutPatente = :patente ");
			query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
			query.setParameter("patente", v.getVehPatente());

			try {
				dgu = (DatosGpsUltimo) query.getSingleResult();
			} catch (Exception e) {
				dgu = null;
			}

			// dgu =
			// ReporteService.findUiltimaPosicionByVehiculoC(v.getVehPatente(),
			// emSC);

			String geocerca = "";

			if (dgu != null) {

				mapaUltimoGPSJson.setLat(dgu.getRutLatitud());
				mapaUltimoGPSJson.setLon(dgu.getRutLongitud());
				mapaUltimoGPSJson.setVelocidad(String.valueOf(dgu.getRutVelocidad()));
				mapaUltimoGPSJson.setFecha(dgu.getId().getRutFechaHora());
				mapaUltimoGPSJson.setFechaS(formatteri.format(dgu.getId().getRutFechaHora()));

				mapaUltimoGPSJson.setFechadb(dgu.getRutFechaInserta());
				mapaUltimoGPSJson.setFechaSdb(formatteri.format(dgu.getRutFechaInserta()));

				// fecha actual
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				mapaUltimoGPSJson.setFechaAjaxS(formatteri.format(timestamp));

				// Estado
				// 1 optimo, 2 advertencia, 3 alerta
				Date datedesde = new Date(dgu.getId().getRutFechaHora().getTime());
				Date datehasta = new Date();
				long dias = UtilServicio.getDiferenciaday(datedesde, datehasta);
				long horas = UtilServicio.getDiferenciadayHoras(datedesde, datehasta);

				if (dias == 0) {

					if (horas >= 2) {

						mapaUltimoGPSJson.setEstado("2");
						mapaUltimoGPSJson.setDescestado(
								"<small style='color: orange; font-size :12px;'>Retraso de Datos <i class='fa fa-warning'></i> </small>");

					} else {

						mapaUltimoGPSJson.setEstado("1");
						mapaUltimoGPSJson.setDescestado(
								"<small style='color: green; font-size :12px;'>Activo <i class='fa fa-check'></i> </small>");

					}

				} else {

					mapaUltimoGPSJson.setEstado("3");
					mapaUltimoGPSJson.setDescestado(
							"<small style='color: red; font-size :12px;'>Sin Transmision <i class='fa fa-minus-circle'></i> </small>");

				}
				// fin estado

				if (dgu.getRutGeocerca() != null) {

					mapaUltimoGPSJson.setRutGeocerca(dgu.getRutGeocerca());

				} else {
					mapaUltimoGPSJson.setRutGeocerca(dgu.getRutGeocoding());

				}

				// vehiculo
				mapaUltimoGPSJson.setPatente(v.getVehPatente());
				mapaUltimoGPSJson.setTipoVehiculo(v.getTipoVehiculo().getTipvDescripcion());
				mapaUltimoGPSJson.setIdTipoVehiculo(v.getTipoVehiculo().getTipvId());
				mapaUltimoGPSJson.setMarca(v.getVehMarca());
				mapaUltimoGPSJson.setNroInterno(v.getVehNumInterno());

				mapaUltimoGPSJson.setTipo(v.getTipoVehiculo().getTipo());
				mapaUltimoGPSJson.setIcon(v.getTipoVehiculo().getTivRutaIcono());

				//
				String classEvent = UtilServicio.getClassEventJS(dgu.getRutEvento(), "");

				EventosGp evento = new EventosGp();// sacarlo
				evento = emSC.find(EventosGp.class, dgu.getRutEvento());
				// evento = ReporteService.findEvento(dgu.getRutEvento());

				mapaUltimoGPSJson.setClassEvent(classEvent);
				try {
					mapaUltimoGPSJson.setEvento(evento.getEveNombre());
				} catch (Exception e) {
					mapaUltimoGPSJson.setEvento("N/A");
				}

				mlist.add(mapaUltimoGPSJson);

			}

		}

		emSC.clear();
		emSC.close();

		return mlist;
	}

	public static List<MapaUltimoGPSJson> findVehiculoUltimaPosicionCategoriaAjax2(String cliente, String categoria,
			String rut) {

		// PropertyConfigurator.configure(log4jConfPath);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		emSD = emf.createEntityManager();

		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		String sql = "";
		String tipos = "";
		// camion
		if (categoria.equals("c")) {

			// tipos = "6";
			sql = "and v.tipoVehiculo.tipvId in (6) ";

			// camion dumper
		}
		if (categoria.equals("d")) {

			// tipos = tipos = "30";
			sql = "and v.tipoVehiculo.tipvId in (30) ";
		}
		// maquina
		if (categoria.equals("m")) {

			// tipos = "11, 19, 22, 25, 26, 27, 28";
			// sql = "and v.tipoVehiculo.tipvId in (11, 19, 22, 25, 26, 27, 28)
			// ";
			sql = "and v.tipoVehiculo.tipo in ('m') ";

		}

		List<String> tlista = Arrays.asList(tipos);

		Query query = emSD.createQuery("select v from Vehiculo v where v.cliente.cliRut = :cliente "
				// + "and v.tipoVehiculo.tipvId in :tlista "
				+ sql + "and v.vehPatente " + "in "
				+ "(select u.vehPatenteVehiculo from UsuarioVehiculo u where u.usuRutUsuario = :rut ) "
				+ "order by v.vehNumInterno asc ");
		// query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		query.setParameter("cliente", cliente);
		query.setParameter("rut", rut);
		// query.setParameter("tlista", tlista);

		ArrayList<MapaUltimoGPSJson> mlist = new ArrayList<MapaUltimoGPSJson>();

		List<Vehiculo> vlist = query.getResultList();

		for (Vehiculo v : vlist) {

			MapaUltimoGPSJson mapaUltimoGPSJson = new MapaUltimoGPSJson();

			DatosGpsUltimo dgu = new DatosGpsUltimo();

			query = emSD.createQuery("select d from DatosGpsUltimo d where d.id.rutPatente = :patente ");
			query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
			query.setParameter("patente", v.getVehPatente());

			try {
				dgu = (DatosGpsUltimo) query.getSingleResult();
			} catch (Exception e) {
				dgu = null;
			}

			// dgu =
			// ReporteService.findUiltimaPosicionByVehiculoC(v.getVehPatente(),
			// emSC);

			String geocerca = "";

			if (dgu != null) {

				mapaUltimoGPSJson.setLat(dgu.getRutLatitud());
				mapaUltimoGPSJson.setLon(dgu.getRutLongitud());
				mapaUltimoGPSJson.setVelocidad(String.valueOf(dgu.getRutVelocidad()));
				mapaUltimoGPSJson.setFecha(dgu.getId().getRutFechaHora());
				mapaUltimoGPSJson.setFechaS(formatteri.format(dgu.getId().getRutFechaHora()));

				mapaUltimoGPSJson.setFechadb(dgu.getRutFechaInserta());
				mapaUltimoGPSJson.setFechaSdb(formatteri.format(dgu.getRutFechaInserta()));

				// fecha actual
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				mapaUltimoGPSJson.setFechaAjaxS(formatteri.format(timestamp));

				// Estado
				// 1 optimo, 2 advertencia, 3 alerta
				Date datedesde = new Date(dgu.getId().getRutFechaHora().getTime());
				Date datehasta = new Date();
				long dias = UtilServicio.getDiferenciaday(datedesde, datehasta);
				long horas = UtilServicio.getDiferenciadayHoras(datedesde, datehasta);

				if (dias == 0) {

					if (horas >= 2) {

						mapaUltimoGPSJson.setEstado("2");
						mapaUltimoGPSJson.setDescestado(
								"<small style='color: orange; font-size :12px;'>Retraso de Datos <i class='fa fa-warning'></i> </small>");

					} else {

						mapaUltimoGPSJson.setEstado("1");
						mapaUltimoGPSJson.setDescestado(
								"<small style='color: green; font-size :12px;'>Activo <i class='fa fa-check'></i> </small>");

					}

				} else {

					mapaUltimoGPSJson.setEstado("3");
					mapaUltimoGPSJson.setDescestado(
							"<small style='color: red; font-size :12px;'>Sin Transmision <i class='fa fa-minus-circle'></i> </small>");

				}
				// fin estado

				if (dgu.getRutGeocerca() != null) {

					mapaUltimoGPSJson.setRutGeocerca(dgu.getRutGeocerca());

				} else {
					mapaUltimoGPSJson.setRutGeocerca(dgu.getRutGeocoding());

				}

				// vehiculo
				mapaUltimoGPSJson.setPatente(v.getVehPatente());
				mapaUltimoGPSJson.setTipoVehiculo(v.getTipoVehiculo().getTipvDescripcion());
				mapaUltimoGPSJson.setIdTipoVehiculo(v.getTipoVehiculo().getTipvId());
				mapaUltimoGPSJson.setMarca(v.getVehMarca());
				mapaUltimoGPSJson.setNroInterno(v.getVehNumInterno());

				mapaUltimoGPSJson.setTipo(v.getTipoVehiculo().getTipo());
				mapaUltimoGPSJson.setIcon(v.getTipoVehiculo().getTivRutaIcono());

				//
				String classEvent = UtilServicio.getClassEventJS(dgu.getRutEvento(), "");

				EventosGp evento = new EventosGp();// sacarlo
				evento = emSD.find(EventosGp.class, dgu.getRutEvento());
				// evento = ReporteService.findEvento(dgu.getRutEvento());

				mapaUltimoGPSJson.setClassEvent(classEvent);
				try {
					mapaUltimoGPSJson.setEvento(evento.getEveNombre());
				} catch (Exception e) {
					mapaUltimoGPSJson.setEvento("N/A");
				}

				mlist.add(mapaUltimoGPSJson);

			}

		}

		emSD.clear();
		emSD.close();

		return mlist;
	}

	public static List<MapaUltimoGPSJson> findVehiculoUltimaPosicionCategoriaAjax3(String cliente, String categoria,
			String rut) {

		// PropertyConfigurator.configure(log4jConfPath);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		emSM = emf.createEntityManager();

		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		String sql = "";
		String tipos = "";
		// camion
		if (categoria.equals("c")) {

			// tipos = "6";
			sql = "and v.tipoVehiculo.tipvId in (6) ";

			// camion dumper
		}
		if (categoria.equals("d")) {

			// tipos = tipos = "30";
			sql = "and v.tipoVehiculo.tipvId in (30) ";
		}
		// maquina
		if (categoria.equals("m")) {

			// tipos = "11, 19, 22, 25, 26, 27, 28";
			// sql = "and v.tipoVehiculo.tipvId in (11, 19, 22, 25, 26, 27, 28)
			// ";
			sql = "and v.tipoVehiculo.tipo in ('m') ";

		}

		List<String> tlista = Arrays.asList(tipos);

		Query query = emSM.createQuery("select v from Vehiculo v where v.cliente.cliRut = :cliente "
				// + "and v.tipoVehiculo.tipvId in :tlista "
				+ sql + "and v.vehPatente " + "in "
				+ "(select u.vehPatenteVehiculo from UsuarioVehiculo u where u.usuRutUsuario = :rut ) "
				+ "order by v.vehNumInterno asc ");
		// query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		query.setParameter("cliente", cliente);
		query.setParameter("rut", rut);
		// query.setParameter("tlista", tlista);

		ArrayList<MapaUltimoGPSJson> mlist = new ArrayList<MapaUltimoGPSJson>();

		List<Vehiculo> vlist = query.getResultList();

		for (Vehiculo v : vlist) {

			MapaUltimoGPSJson mapaUltimoGPSJson = new MapaUltimoGPSJson();

			DatosGpsUltimo dgu = new DatosGpsUltimo();

			query = emSM.createQuery("select d from DatosGpsUltimo d where d.id.rutPatente = :patente ");
			query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
			query.setParameter("patente", v.getVehPatente());

			try {
				dgu = (DatosGpsUltimo) query.getSingleResult();
			} catch (Exception e) {
				dgu = null;
			}

			// dgu =
			// ReporteService.findUiltimaPosicionByVehiculoC(v.getVehPatente(),
			// emSC);

			String geocerca = "";

			if (dgu != null) {

				mapaUltimoGPSJson.setLat(dgu.getRutLatitud());
				mapaUltimoGPSJson.setLon(dgu.getRutLongitud());
				mapaUltimoGPSJson.setVelocidad(String.valueOf(dgu.getRutVelocidad()));
				mapaUltimoGPSJson.setFecha(dgu.getId().getRutFechaHora());
				mapaUltimoGPSJson.setFechaS(formatteri.format(dgu.getId().getRutFechaHora()));

				mapaUltimoGPSJson.setFechadb(dgu.getRutFechaInserta());
				mapaUltimoGPSJson.setFechaSdb(formatteri.format(dgu.getRutFechaInserta()));

				// fecha actual
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				mapaUltimoGPSJson.setFechaAjaxS(formatteri.format(timestamp));

				// Estado
				// 1 optimo, 2 advertencia, 3 alerta
				Date datedesde = new Date(dgu.getId().getRutFechaHora().getTime());
				Date datehasta = new Date();
				long dias = UtilServicio.getDiferenciaday(datedesde, datehasta);
				long horas = UtilServicio.getDiferenciadayHoras(datedesde, datehasta);

				if (dias == 0) {

					if (horas >= 2) {

						mapaUltimoGPSJson.setEstado("2");
						mapaUltimoGPSJson.setDescestado(
								"<small style='color: orange; font-size :12px;'>Retraso de Datos <i class='fa fa-warning'></i> </small>");

					} else {

						mapaUltimoGPSJson.setEstado("1");
						mapaUltimoGPSJson.setDescestado(
								"<small style='color: green; font-size :12px;'>Activo <i class='fa fa-check'></i> </small>");

					}

				} else {

					mapaUltimoGPSJson.setEstado("3");
					mapaUltimoGPSJson.setDescestado(
							"<small style='color: red; font-size :12px;'>Sin Transmision <i class='fa fa-minus-circle'></i> </small>");

				}
				// fin estado

				if (dgu.getRutGeocerca() != null) {

					mapaUltimoGPSJson.setRutGeocerca(dgu.getRutGeocerca());

				} else {
					mapaUltimoGPSJson.setRutGeocerca(dgu.getRutGeocoding());

				}

				// vehiculo
				mapaUltimoGPSJson.setPatente(v.getVehPatente());
				mapaUltimoGPSJson.setTipoVehiculo(v.getTipoVehiculo().getTipvDescripcion());
				mapaUltimoGPSJson.setIdTipoVehiculo(v.getTipoVehiculo().getTipvId());
				mapaUltimoGPSJson.setMarca(v.getVehMarca());
				mapaUltimoGPSJson.setNroInterno(v.getVehNumInterno());

				mapaUltimoGPSJson.setTipo(v.getTipoVehiculo().getTipo());
				mapaUltimoGPSJson.setIcon(v.getTipoVehiculo().getTivRutaIcono());

				//
				String classEvent = UtilServicio.getClassEventJS(dgu.getRutEvento(), "");

				EventosGp evento = new EventosGp();// sacarlo
				evento = emSM.find(EventosGp.class, dgu.getRutEvento());
				// evento = ReporteService.findEvento(dgu.getRutEvento());

				mapaUltimoGPSJson.setClassEvent(classEvent);
				try {
					mapaUltimoGPSJson.setEvento(evento.getEveNombre());
				} catch (Exception e) {
					mapaUltimoGPSJson.setEvento("N/A");
				}

				mlist.add(mapaUltimoGPSJson);

			}

		}

		emSM.clear();
		emSM.close();

		return mlist;
	}

	public static List<GeocercaUltimaGpsActive> findGeocercaUltimaGpsAjax(String clienterut) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		em3 = emf.createEntityManager();

		Query query = em3.createQuery("select distinct(d.rutGeocerca) from DatosGpsUltimo d "
				// + "where d.rutPatente in "
				// + "(select v.vehPatente from Vehiculo where v.cliente.cliRut
				// = :clienterut) "
				+ "order by d.rutGeocerca ");
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		// query.setParameter("clienterut", clienterut);

		List<Object[]> dlist = query.getResultList();

		List<GeocercaUltimaGpsActive> gist = new ArrayList();
		Integer idgeo;
		String nombregeo;
		for (Object c : dlist) {

			GeocercaUltimaGpsActive ga = new GeocercaUltimaGpsActive();

			nombregeo = (String) c;
			int na = 0;
			if (nombregeo != null) { // equal not null

				Query query2 = em3.createQuery("select z from Zona z " + "where z.zonNombre = :nombregeo "
						+ "and z.usuario.usuRut = :clienterut " + " ");
				query2.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
				query2.setParameter("nombregeo", nombregeo);
				query2.setParameter("clienterut", clienterut);

				Zona zona = new Zona();
				try {
					zona = (Zona) query2.getSingleResult();
					idgeo = zona.getZonId();

					if (idgeo == 107) {
						nombregeo = "En ruta";

					}

				} catch (Exception e) {
					zona = null;
					idgeo = 0;
					nombregeo = "Error";
					// System.out.println(e);
					// no agregar
					na = 1;

				}
			} else {

				idgeo = 0;
				nombregeo = "Fuera de Proyecto";

			}

			ga.setRutGeocerca(String.valueOf(idgeo));
			ga.setNombreGeocerca(nombregeo);
			if (na != 1) {
				gist.add(ga);
			}

		} // fin for

		em3.clear();
		em3.close();

		return gist;
	}

	public static List<MapaUltimoGPSJson> findUltimaPosicionGeoAjax(String cliente, String geo) {

		// PropertyConfigurator.configure(log4jConfPath);
		geo = "";

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		emSG = emf.createEntityManager();

		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Query query = emSG.createQuery("select d from DatosGpsUltimo d "
				// + "where d.rutGeocerca = :geo "
				+ "order by d.id.rutPatente asc  " + " ");

		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		// query.setParameter("geo", geo);

		ArrayList<MapaUltimoGPSJson> mlist = new ArrayList<MapaUltimoGPSJson>();

		List<DatosGpsUltimo> dlist = query.getResultList();
		String nrointerno = "";
		for (DatosGpsUltimo d : dlist) {

			MapaUltimoGPSJson mapaUltimoGPSJson = new MapaUltimoGPSJson();

			Vehiculo v = new Vehiculo();

			Query query2 = emSG.createQuery(
					"select v from Vehiculo v where v.vehPatente = :patente and v.cliente.cliRut = :cliente ");
			query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
			query2.setParameter("patente", d.getId().getRutPatente());
			query2.setParameter("cliente", cliente);
			String rutgeo = "";
			try {
				v = (Vehiculo) query2.getSingleResult();
				nrointerno = v.getVehNumInterno();

				mapaUltimoGPSJson.setFecha(d.getId().getRutFechaHora());
				mapaUltimoGPSJson.setNroInterno(nrointerno);
				mapaUltimoGPSJson.setPatente(d.getId().getRutPatente());
				mapaUltimoGPSJson.setFechaS(formatteri.format(d.getId().getRutFechaHora()));

				rutgeo = d.getRutGeocerca();

				if (rutgeo != null) {

					if (rutgeo.equals("Proyecto Emexco")) {

						mapaUltimoGPSJson.setRutGeocerca("En ruta");

					} else {

						mapaUltimoGPSJson.setRutGeocerca(d.getRutGeocerca());

					}

				} else {
					mapaUltimoGPSJson.setRutGeocerca("Fuera de Proyecto");

				}

				mlist.add(mapaUltimoGPSJson);

				mapaUltimoGPSJson = null;

			} catch (Exception e) {
				v = null;
				nrointerno = "";
				// no es cliente
			}

		}

		emSG.clear();
		emSG.close();

		return mlist;
	}

	public static List<ReporteHorometroActive> findHorometro(String usuLogincliente, String usuClavecliente,
			Integer faenas, String desde, String hasta, String opt_ver) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // dd/MM/yyyy
		em3 = emf.createEntityManager();

		Calendar calendarin = Calendar.getInstance();
		Calendar calendarfin = Calendar.getInstance();
		Date fecdesde = new Date();
		Date fechasta = new Date();
		Integer sw = 1;

		if (desde.equals("") && hasta.equals("")) {
			fecdesde = new Date();
			fechasta = new Date();
			sw = 1;
		} else {
			sw = 0;
			try {
				fecdesde = formatteri.parse(desde);
				fechasta = formatteri.parse(hasta);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		calendarin.setTime(fecdesde);
		calendarfin.setTime(fechasta);

		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
		Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

		StoredProcedureQuery query = em3.createStoredProcedureQuery("public.informeultimohorometro");

		query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN); // faena
		query.registerStoredProcedureParameter(2, Timestamp.class, ParameterMode.IN); // fecha
																						// desde
		query.registerStoredProcedureParameter(3, Timestamp.class, ParameterMode.IN); // fecha
																						// hasta
		query.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN); // switch
		query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN); // usuLogincliente
		query.registerStoredProcedureParameter(6, String.class, ParameterMode.IN); // usuClavecliente

		query.setParameter(1, faenas);

		query.setParameter(2, timein);
		query.setParameter(3, timefin);
		query.setParameter(4, sw);

		query.setParameter(5, usuLogincliente);
		query.setParameter(6, usuClavecliente);

		// System.out.println(query);

		query.execute();

		List<ReporteHorometroActive> ListArray = new ArrayList();

		List<Object[]> ListResult = query.getResultList();

		for (Object[] lr : ListResult) {

			ReporteHorometroActive ca = new ReporteHorometroActive();

			ca.setPatente((String) lr[0]);
			ca.setNrointerno((String) lr[3]);
			ca.setMarca((String) lr[4]);
			ca.setFecha((Timestamp) lr[1]);
			ca.setHorometro(Double.valueOf(String.format("%.2f", (Double) lr[6]).replace(",", ".")));
			ca.setEstado((Integer) lr[8]);
			ca.setModelo((String) lr[5]);
			ca.setTipo_equipo((Integer) lr[9]);
			ca.setTipo_equipo_str((String) lr[10]);
			ca.setTipo_horometro((String) lr[11]);

			DecimalFormat df = new DecimalFormat("0.00");

			if (sw == 1) {

				if (opt_ver.equals("3")) {
					ca.setHorometro_str(df.format((Double) ca.getHorometro() * (Double) 3600.0));
				} else if (opt_ver.equals("2")) {
					ca.setHorometro_str(df.format(Double.valueOf((Double) ca.getHorometro() * 60.0)));
				} else if (opt_ver.equals("1")) {
					ca.setHorometro_str(df.format((Double) ca.getHorometro()));
				} else if (opt_ver.equals("4")) {
					ca.setHorometro_str(UtilServicio.FormatoTimer((int) Math.round(ca.getHorometro() * 3600)));
				}

				ca.setHorometro_str(ca.getHorometro_str().replace(".", ",").trim());
			} else {
				ca.setFecha_fin((Timestamp) lr[2]);
				ca.setHorometro_fin(Double.valueOf(String.format("%.2f", (Double) lr[7]).replace(",", ".")));

				if (opt_ver.equals("3")) {
					ca.setHorometro_str(df.format((Double) ca.getHorometro() * (Double) 3600.0));
					ca.setHorometro_fin_str(df.format((Double) ca.getHorometro_fin() * (Double) 3600.0));
					ca.setHoras_str(df.format((Double) (ca.getHorometro_fin() - ca.getHorometro()) * (Double) 3600.0));
				} else if (opt_ver.equals("2")) {
					ca.setHorometro_str(df.format((Double) ca.getHorometro() * 60.0));
					ca.setHorometro_fin_str(df.format(Double.valueOf((Double) ca.getHorometro_fin() * 60.0)));
					ca.setHoras_str(df.format((Double) (ca.getHorometro_fin() - ca.getHorometro()) * (Double) 60.0));
				} else if (opt_ver.equals("1")) {
					ca.setHorometro_str(df.format((Double) ca.getHorometro()));
					ca.setHorometro_fin_str(df.format((Double) ca.getHorometro_fin()));
					ca.setHoras_str(df.format((Double) (ca.getHorometro_fin() - ca.getHorometro())));
				} else if (opt_ver.equals("4")) {
					ca.setHorometro_str(UtilServicio.FormatoTimer((int) Math.round(ca.getHorometro() * 3600)));
					ca.setHorometro_fin_str(UtilServicio.FormatoTimer((int) Math.round(ca.getHorometro_fin() * 3600)));
					ca.setHoras_str(UtilServicio
							.FormatoTimer((int) Math.round((ca.getHorometro_fin() - ca.getHorometro()) * 3600)));
				}

				ca.setHorometro_str(ca.getHorometro_str().replace(".", ",").trim());
				ca.setHorometro_fin_str(ca.getHorometro_fin_str().replace(".", ",").trim());
				ca.setHoras_str(ca.getHoras_str().replace(".", ",").trim());
			}

			ListArray.add(ca);

		}

		em3.clear();
		em3.close();

		return ListArray;

	}

	// no depracated
	public static List<ReporteIndicadorOptimoCargaActive> findDiagramaTiempo(String patente, String desde,
			String hasta) {

		String fechanulas = "";

		// dd-MM-yyyy
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy"); // dd/MM/yyyy
		em = emf.createEntityManager();

		Calendar calendarin = Calendar.getInstance();
		Calendar calendarfin = Calendar.getInstance();

		Date fecdesde = new Date();
		Date fechasta = new Date();

		// si las fechas vienen nulo se calcula el mes completo
		if (desde.equals("") && hasta.equals("")) {

			fechanulas = "S";

		}

		try {
			fecdesde = formatteri.parse(desde);
			fechasta = formatteri.parse(hasta);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		// configuro las hora inicio y fin
		calendarin.setTime(fecdesde);
		calendarin.add(Calendar.HOUR_OF_DAY, 00);
		calendarin.add(Calendar.MINUTE, 00);
		calendarin.add(Calendar.SECOND, 00);

		// si las fechas vienen nul se resta el mes a la fecha inicio y fecha
		// fin queda como dia hoy
		if (fechanulas.equals("S")) {

			calendarfin.add(Calendar.DAY_OF_MONTH, -2);

		}

		calendarfin.setTime(fechasta);
		calendarfin.add(Calendar.HOUR_OF_DAY, 23);
		calendarfin.add(Calendar.MINUTE, 59);
		calendarfin.add(Calendar.SECOND, 59);

		// las convierto timestamp para insertarlas en el query
		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
		Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

		/*
		 * Query query = em.createQuery("select m from Listadoot m " //+
		 * "where m.fechacreacion>=:fc and  m.fechacreacion<=:ff " +
		 * "where m.fechainicio>=:fc and  m.fechatermino<=:ff " +
		 * "and m.flotatransporte.patente = :patente " +
		 * "order by m.fechainicio desc ");
		 * query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		 * 
		 * query.setParameter("fechain", timein); query.setParameter("fechafin",
		 * timefin); query.setParameter("patente", patente);
		 */

		// List resultList = query.getResultList();
		ArrayList<ReporteIndicadorOptimoCargaActive> rlist = new ArrayList<ReporteIndicadorOptimoCargaActive>();

		// Se construye Luego
		/*
		 * if (resultList != null && !resultList.isEmpty()) {
		 * 
		 * for (Listadoot m : resultList) {
		 * 
		 * 
		 * ReporteIndicadorOptimoCargaActive r=new
		 * ReporteIndicadorOptimoCargaActive();
		 * 
		 * 
		 * l.setGlosa(m.getGlosa()); l.setTipoMantencion(m.getTipomantencion());
		 * l.setGlosa(m.getGlosa()); l.setNumeroOT(m.getNumeroot());
		 * l.setStatus(m.getStatus());
		 * 
		 * l.setFechaCreacion(formatter.format(m.getFechacreacion()));
		 * l.setFechaInicioExtrema(formatter.format(m.getFechainicioextrema()));
		 * l.setFechaFinExtrema(formatter.format(m.getFechafinextrema()));
		 * l.setKilometrajeProximaMantencion(m.getKilometrajeproximamantencion()
		 * .intValue());
		 * 
		 * 
		 * lot.add(l); }
		 * 
		 * 
		 * }
		 */

		// llenamos manual por ahora
		ReporteIndicadorOptimoCargaActive r01 = new ReporteIndicadorOptimoCargaActive();
		ReporteIndicadorOptimoCargaActive r02 = new ReporteIndicadorOptimoCargaActive();
		ReporteIndicadorOptimoCargaActive r03 = new ReporteIndicadorOptimoCargaActive();
		ReporteIndicadorOptimoCargaActive r04 = new ReporteIndicadorOptimoCargaActive();
		ReporteIndicadorOptimoCargaActive r05 = new ReporteIndicadorOptimoCargaActive();
		ReporteIndicadorOptimoCargaActive r06 = new ReporteIndicadorOptimoCargaActive();

		// { x: new Date(2018, 10, 24, 9, 33, 30, 0), y: 74.2},
		// { x: new Date(2018, 10, 24, 10, 33, 30, 0), y: 23.4},
		// { x: new Date(2018, 10, 24, 10, 34, 30, 0), y: 43.4},
		// { x: new Date(2018, 10, 24, 10, 39, 30, 0), y: 53.4},
		// { x: new Date(2018, 10, 24, 10, 53, 30, 0), y: 33.4},
		// { x: new Date(2018, 10, 24, 11, 43, 30, 0), y: 63.4},
		// { x: new Date(2018, 10, 24, 11, 45, 30, 0), y: 71.5},
		// { x: new Date(2018, 10, 24, 12, 43, 30, 0), y: 43.8},

		// Reporte
		r01.setNrovuelta(2);
		r01.setHorasdespacho("09:33");
		r01.setIdcamion("HHFD98");
		r01.setCargareal(36.4833);
		r01.setCarganominal(30);
		r01.setPorcentajedecarga(122);

		// Grafico
		// model { x: new Date(2018, 10, 24, 09, 33, 30, 0), y: 74.2}
		r01.setAno(2018);
		r01.setMes(10);
		r01.setDia(24);
		r01.setHora(9);// revisar si funciona sin cero
		r01.setMin(33);
		r01.setSeg(30);
		r01.setTonelada(36.4833);

		r02.setNrovuelta(2);
		r02.setHorasdespacho("10:33");
		r02.setIdcamion("HKPC22");
		r02.setCargareal(23.4833);
		r02.setCarganominal(30);
		r02.setPorcentajedecarga(122);

		r02.setAno(2018);
		r02.setMes(10);
		r02.setDia(24);
		r02.setHora(10);// revisar si funciona sin cero
		r02.setMin(33);
		r02.setSeg(30);
		r02.setTonelada(23.4833);

		rlist.add(r01);
		rlist.add(r02);

		// em.clear();
		// em.close();
		return rlist;
	}

	public static List<DiagramaTiempoActive> findDiagramaTiempo2(String patentes, String desde, String hasta,
			String cliente) {

		String fechanulas = "";

		// dd-MM-yyyy
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // dd/MM/yyyy
																			// HH:mm
		em3 = emf.createEntityManager();

		Calendar calendarin = Calendar.getInstance();
		Calendar calendarfin = Calendar.getInstance();

		Date fecdesde = new Date();
		Date fechasta = new Date();

		// si las fechas vienen nulo se calcula el mes completo
		if (desde.equals("") && hasta.equals("")) {

			fechanulas = "S";

		}

		try {
			fecdesde = formatteri.parse(desde);
			fechasta = formatteri.parse(hasta);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		// configuro las hora inicio y fin
		calendarin.setTime(fecdesde);
		calendarin.add(Calendar.HOUR_OF_DAY, 00);
		calendarin.add(Calendar.MINUTE, 00);
		calendarin.add(Calendar.SECOND, 00);

		// si las fechas vienen nul se resta el mes a la fecha inicio y fecha
		// fin queda como dia hoy
		if (fechanulas.equals("S")) {

			calendarfin.add(Calendar.DAY_OF_MONTH, -2);

		}

		calendarfin.setTime(fechasta);

		// las convierto timestamp para insertarlas en el query
		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
		Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

		// para jpa se para un array para el in, y no lleva parentesis
		String[] parts = patentes.split(",");
		List<String> plista = Arrays.asList(parts);

		String sql = "";

		if (!patentes.equals(null)) {

			sql = " and v.vehPatente in :patentes ";

		} else {

			sql = "";
		}

		Query query = em3.createQuery(
				"select v from Vehiculo v where 1 = 1 " + sql + "and v.cliente.cliRut = :cliente " + " " + " ");
		query.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
		query.setParameter("cliente", cliente);
		if (!patentes.equals(null)) {

			query.setParameter("patentes", plista);

		}

		List<Vehiculo> vlist = query.getResultList();

		ArrayList<DiagramaTiempoActive> rlist = new ArrayList<DiagramaTiempoActive>();
		ArrayList<VehiculoActive> vclist = new ArrayList<VehiculoActive>();

		for (Vehiculo v : vlist) {

			// aqui procedimiento que llena reportecarguio x patente

			em2 = emf.createEntityManager();

			Query query02 = em2.createNativeQuery(
					"SELECT rut_fecha_hora, rut_patente, zon_id_zona, rut_geocerca, rut_fecha_inserta "
							+ " FROM public.datos_gps " + " where rut_patente = '" + v.getVehPatente() + "' "
							+ " and rut_fecha_hora >= '" + timein + "' and rut_fecha_hora <= '" + timefin + "' "
							// + " and rf_patente_camion notnull "
							+ " ORDER BY rut_fecha_hora " + " " + " ");

			List<Object[]> dlist = query02.getResultList();
			Integer i = 0;

			// reglas
			// Proyecto Emexco, En ruta 107
			// Null, Fuera de Proyecto (cualquier zona que no sea proyecto y
			// taller)
			// Taller, Instalacion de Faenas y Taller (62)
			// Latencia
			int segenruta = 0;
			long segr = 0;
			int segfueraproyecto = 0;
			long segf = 0;
			int segtaller = 0;
			long segt = 0;
			int seglatencia = 0;
			long segl = 0;
			int seggeo = 0;

			int segenruta2 = 0;
			int segfueraproyecto2 = 0;
			int segtaller2 = 0;
			int seglatencia2 = 0;
			int seggeo2 = 0;

			String patentecamion = "";
			String numerointerno = "";

			Date fechaentrada = new Date();
			Date fechasalida = new Date();
			Timestamp fechai = new Timestamp(fechaentrada.getTime());
			Timestamp fechainicio = new Timestamp(fechaentrada.getTime());
			Timestamp fechainicio2 = new Timestamp(fechaentrada.getTime());
			Timestamp fechafin = new Timestamp(fechasalida.getTime());

			String tiempocarguio = "";
			String tiemporuta = "";
			String tiempofueraproyecto = "";
			String tiempotaller = "";
			String tiempogeo = "";
			String tiempolatencie = "";
			String ubicacion = "";
			int idubicacion = 0;

			// Total busqueda
			Date datebi = new Date(timein.getTime());
			Date datebf = new Date(timefin.getTime());
			long segtotalbusqueda = UtilServicio.getDiferenciaSeg(datebi, datebf);
			long segtotal = 0;

			DiagramaTiempoActive r = new DiagramaTiempoActive();

			for (Object[] d : dlist) {
				i++;

				Timestamp fecha2 = (Timestamp) d[0];
				String patentecamion2 = (String) d[1];
				int idubicacion2 = 0;
				String ubicacion2 = "";

				try {
					ubicacion2 = (String) d[3];
					idubicacion2 = (Integer) d[2]; // id_zona

				} catch (Exception e) {

					ubicacion2 = "Fuera de Proyecto";
					idubicacion2 = 0;
				}

				Timestamp fecha2db = (Timestamp) d[4];

				Date datei = new Date();
				Date datef = new Date(fecha2.getTime());
				if (i == 1) {

					datei = new Date(fecha2.getTime());
					// if(i2 > 1){
					// datei =new Date(fechai.getTime());
					// }
				} else {

					datei = new Date(fechai.getTime());
				}

				/*
				 * int segenruta = 0; long segr = 0; int segfueraproyecto = 0;
				 * long segf = 0; int segtaller = 0; long segt = 0; int
				 * seglatencia = 0; long segl = 0;
				 */

				if (i != 1) {
					// if(idubicacion2 == idubicacion){

					if (idubicacion2 == 107) {

						long seg = UtilServicio.getDiferenciaSeg(datei, datef);
						segenruta = Integer.valueOf(String.valueOf(Long.valueOf(seg)));
						segenruta2 = segenruta2 + segenruta;

						segtotal = segtotal + seg;

					} else if (idubicacion2 == 62) {

						long seg = UtilServicio.getDiferenciaSeg(datei, datef);
						segtaller = Integer.valueOf(String.valueOf(Long.valueOf(seg)));
						segtaller2 = segtaller2 + segtaller;

						segtotal = segtotal + seg;

					}

					else if (idubicacion2 == 0) {

						long seg = UtilServicio.getDiferenciaSeg(datei, datef);
						segfueraproyecto = Integer.valueOf(String.valueOf(Long.valueOf(seg)));
						segfueraproyecto2 = segfueraproyecto2 + segfueraproyecto;

						segtotal = segtotal + seg;

					} else {// dentro geocerca

						long seg = UtilServicio.getDiferenciaSeg(datei, datef);
						seggeo = Integer.valueOf(String.valueOf(Long.valueOf(seg)));
						seggeo2 = seggeo2 + seggeo;

						segtotal = segtotal + seg;

					}
					// } // if(idubicacion2 == idubicacion){
				} // if(i != 1) {

				// if(idubicacion2 != idubicacion){

				fechai = fecha2;

				// }

				if (i == 1) {
					fechainicio = fecha2;

				}
				idubicacion = idubicacion2;

			} // fin for

			long segtiempolatencia = segtotalbusqueda - segtotal;
			tiempolatencie = UtilServicio.FormatoTimer(segtiempolatencia);
			// segenruta2 = segenruta2 + segenruta;
			tiemporuta = UtilServicio.FormatoTimer(segenruta2);

			// segfueraproyecto2 = segfueraproyecto2 + segfueraproyecto;
			tiempofueraproyecto = UtilServicio.FormatoTimer(segfueraproyecto2);

			// segtaller2 = segtaller2 + segenruta;
			tiempotaller = UtilServicio.FormatoTimer(segtaller2);

			// seggeo2 = seggeo2 + seggeo;
			tiempogeo = UtilServicio.FormatoTimer(seggeo2);

			r.setPatente(v.getVehPatente());
			r.setNrointerno(v.getVehNumInterno());

			r.setTiempofueraproyecto(tiempofueraproyecto);
			r.setTiemporuta(tiemporuta);
			r.setTiempotaller(tiempotaller);
			r.setTiempogeo(tiempogeo);
			r.setTiempolatencia(tiempolatencie);

			// paso a horas

			float horalatencia = (float) segtiempolatencia / 3600;

			float horarutaD = (float) segenruta2 / 3600;
			float horafueraproyectoD = (float) segfueraproyecto2 / 3600;
			float horatallerD = (float) segtaller2 / 3600;
			float horageoD = (float) seggeo2 / 3600;

			r.setHorasruta(horarutaD);
			r.setHorasfueraproyecto(horafueraproyectoD);
			r.setHorastaller(horatallerD);
			r.setHorasgeo(horageoD);
			r.setHoraslatencia(horalatencia);

			r.setUbicacioncarguio(ubicacion);

			segenruta2 = 0;
			segfueraproyecto2 = 0;
			segtaller2 = 0;
			seggeo2 = 0;
			i = 0;

			rlist.add(r);

		} // fin for vehiculo

		em3.clear();
		em3.close();
		return rlist;
	}

	public static List<DiagramaTiempoGraficoActive> findGraficoTiempo(List<DiagramaTiempoActive> list) {

		ArrayList<DiagramaTiempoGraficoActive> listg = new ArrayList<DiagramaTiempoGraficoActive>();

		/*
		 * private int horasfueraproyecto; private int horastaller; private int
		 * horasgeo; private int horasruta;
		 */

		DecimalFormat df = new DecimalFormat("#######.##");

		DiagramaTiempoGraficoActive graFueraproyecto = new DiagramaTiempoGraficoActive();

		graFueraproyecto.setCategoria("Fuera de Proyecto");

		ArrayList<DiagramaTiempoGraficoDetalleActive> listfp = new ArrayList<DiagramaTiempoGraficoDetalleActive>();

		for (DiagramaTiempoActive dt : list) {

			DiagramaTiempoActive da = new DiagramaTiempoActive();

			DiagramaTiempoGraficoDetalleActive dd = new DiagramaTiempoGraficoDetalleActive();

			dd.setPatente(dt.getPatente());
			dd.setNrinterno(dt.getNrointerno());
			dd.setHoras(dt.getHorasfueraproyecto());

			String horas = df.format(dt.getHorasfueraproyecto());
			dd.setHorasS(horas.replace(",", ".").trim());

			listfp.add(dd);

		}
		graFueraproyecto.setDiagramaTiempoGraficoDetalleActives(listfp);

		DiagramaTiempoGraficoActive graTaller = new DiagramaTiempoGraficoActive();

		graTaller.setCategoria("Taller");

		ArrayList<DiagramaTiempoGraficoDetalleActive> listta = new ArrayList<DiagramaTiempoGraficoDetalleActive>();

		for (DiagramaTiempoActive dt : list) {

			DiagramaTiempoActive da = new DiagramaTiempoActive();

			DiagramaTiempoGraficoDetalleActive dd = new DiagramaTiempoGraficoDetalleActive();

			dd.setPatente(dt.getPatente());
			dd.setNrinterno(dt.getNrointerno());
			dd.setHoras(dt.getHorastaller());

			String horas = df.format(dt.getHorastaller());
			dd.setHorasS(horas.replace(",", ".").trim());

			listta.add(dd);

		}
		graTaller.setDiagramaTiempoGraficoDetalleActives(listta);

		DiagramaTiempoGraficoActive graGeo = new DiagramaTiempoGraficoActive();

		graGeo.setCategoria("En Geo");

		ArrayList<DiagramaTiempoGraficoDetalleActive> listgeo = new ArrayList<DiagramaTiempoGraficoDetalleActive>();

		for (DiagramaTiempoActive dt : list) {

			DiagramaTiempoActive da = new DiagramaTiempoActive();

			DiagramaTiempoGraficoDetalleActive dd = new DiagramaTiempoGraficoDetalleActive();

			dd.setPatente(dt.getPatente());
			dd.setNrinterno(dt.getNrointerno());
			dd.setHoras(dt.getHorasgeo());

			String horas = df.format(dt.getHorasgeo());
			dd.setHorasS(horas.replace(",", ".").trim());

			listgeo.add(dd);

		}
		graGeo.setDiagramaTiempoGraficoDetalleActives(listgeo);

		DiagramaTiempoGraficoActive graRuta = new DiagramaTiempoGraficoActive();

		graRuta.setCategoria("En Ruta");

		ArrayList<DiagramaTiempoGraficoDetalleActive> listruta = new ArrayList<DiagramaTiempoGraficoDetalleActive>();

		for (DiagramaTiempoActive dt : list) {

			DiagramaTiempoActive da = new DiagramaTiempoActive();

			DiagramaTiempoGraficoDetalleActive dd = new DiagramaTiempoGraficoDetalleActive();

			dd.setPatente(dt.getPatente());
			dd.setNrinterno(dt.getNrointerno());
			dd.setHoras(dt.getHorasruta());

			String horas = df.format(dt.getHorasruta());
			dd.setHorasS(horas.replace(",", ".").trim());

			listruta.add(dd);

		}
		graRuta.setDiagramaTiempoGraficoDetalleActives(listruta);

		DiagramaTiempoGraficoActive gralatencio = new DiagramaTiempoGraficoActive();

		gralatencio.setCategoria("Latencia");

		ArrayList<DiagramaTiempoGraficoDetalleActive> listl = new ArrayList<DiagramaTiempoGraficoDetalleActive>();

		for (DiagramaTiempoActive dt : list) {

			DiagramaTiempoActive da = new DiagramaTiempoActive();

			DiagramaTiempoGraficoDetalleActive dd = new DiagramaTiempoGraficoDetalleActive();

			dd.setPatente(dt.getPatente());
			dd.setNrinterno(dt.getNrointerno());
			dd.setHoras(dt.getHoraslatencia());

			String horas = df.format(dt.getHoraslatencia());
			dd.setHorasS(horas.replace(",", ".").trim());

			listl.add(dd);

		}
		gralatencio.setDiagramaTiempoGraficoDetalleActives(listl);

		listg.add(graFueraproyecto);
		listg.add(graTaller);
		listg.add(graGeo);
		listg.add(graRuta);
		listg.add(gralatencio);

		return listg;

	}

	public static DiagramaCicloGraficoActive findDiagramaCiclo(String desde, String hasta, String geo, String geo2,
			String patente2, String todo) {

		String fechanulas = "";

		// dd-MM-yyyy
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // dd/MM/yyyy
																			// HH:mm
		em3 = emf.createEntityManager();

		Calendar calendarin = Calendar.getInstance();
		Calendar calendarfin = Calendar.getInstance();

		Date fecdesde = new Date();
		Date fechasta = new Date();

		// si las fechas vienen nulo se calcula el mes completo
		if (desde.equals("") && hasta.equals("")) {

			fechanulas = "S";

		}

		try {
			fecdesde = formatteri.parse(desde);
			fechasta = formatteri.parse(hasta);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		// configuro las hora inicio y fin
		calendarin.setTime(fecdesde);
		calendarin.add(Calendar.HOUR_OF_DAY, 00);
		calendarin.add(Calendar.MINUTE, 00);
		calendarin.add(Calendar.SECOND, 00);

		// si las fechas vienen nul se resta el mes a la fecha inicio y fecha
		// fin queda como dia hoy
		if (fechanulas.equals("S")) {

			calendarfin.add(Calendar.DAY_OF_MONTH, -2);

		}

		calendarfin.setTime(fechasta);

		// las convierto timestamp para insertarlas en el query
		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
		Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

		// para jpa se para un array para el in, y no lleva parentesis
		/*
		 * String[] parts=patentes.split(","); List<String> plista =
		 * Arrays.asList(parts);
		 * 
		 * String sql = "";
		 * 
		 * if(!patentes.equals(null)){
		 * 
		 * sql= " and v.vehPatente in :patentes ";
		 * 
		 * }else{
		 * 
		 * sql = ""; }
		 * 
		 * 
		 * }
		 */ // por si usamos geo

		String sql = "";

		if (!todo.equals("S")) {

			if (!geo.equals("")) {

				sql = "and geo_ini = '" + geo + "' ";

			}
			if (!geo2.equals("")) {

				sql = "and geo_fin = '" + geo2 + "' ";

			}
			if (!patente2.equals("")) {

				sql = "and patente = '" + patente2 + "' ";

			}

		}

		em2 = emf.createEntityManager();

		Query query02 = em2.createNativeQuery(
				"select  geo_ini,  geo_nom_ini,  geo_fin, geo_nom_fin, patente, count(patente) as vuelta , "
						+ "sum(tmpo_ciclo) as segundociclo, " + "sum(carga_nominal) as carga " + "from "
						+ "(SELECT * FROM public.informevueltas_ver2('" + timein + "', '" + timefin
						+ "', 'CYYS82', 1, 1, 1,0)) " + "as vuelta  " + "where 1 = 1 " + sql
						// + "where geo_ini = '"+geo+"' "
						+ "group by geo_ini, geo_nom_ini, geo_fin, geo_nom_fin, patente  "
						+ "order by geo_nom_ini, geo_nom_fin, patente  " + " " + " ");

		List<Object[]> dlist = query02.getResultList();
		Integer i = 0;

		DiagramaCicloGraficoActive diagramaCicloGraficoActive = new DiagramaCicloGraficoActive();

		ArrayList<DiagramaCicloTablaActive> rlist = new ArrayList<DiagramaCicloTablaActive>();

		ArrayList<NodeDataArrayActive> nlist = new ArrayList<NodeDataArrayActive>();
		ArrayList<LinkDataArrayActive> llist = new ArrayList<LinkDataArrayActive>();

		int vueltapatente = 0;
		int vueltadestino = 0;
		int vueltaorigen = 0;

		String origenbefore = "";
		String destinobefore = "";
		Integer j = 0;
		Integer z = 0;
		// 233 "Emprestito 47" 65 "Piscina Recuperacion Este" "GSFW14" "9"
		// "7245" "162"
		for (Object[] d : dlist) {
			i++;
			LinkDataArrayActive link = new LinkDataArrayActive();
			NodeDataArrayActive nodeOrigen = new NodeDataArrayActive();
			NodeDataArrayActive nodeDestino = new NodeDataArrayActive();
			NodeDataArrayActive nodePatente = new NodeDataArrayActive();

			// Timestamp fecha2 = (Timestamp) d[0];
			int geoiniid = (Integer) d[0];
			String geoininombre = (String) d[1];
			int geofinid = (Integer) d[2];
			String geofinnombre = (String) d[3];
			String patente = (String) d[4];
			long vueltal = (Long) d[5];
			long segundos = (Long) d[6];
			double carga = (Double) d[7];

			int vuelta = (int) vueltal;

			if (segundos <= 0) {

				segundos = 0;

			}

			String tiempoS = UtilServicio.FormatoTimer(segundos);

			DiagramaCicloTablaActive diagramaCicloTablaActive = new DiagramaCicloTablaActive();
			diagramaCicloTablaActive.setGeoNombreIni(geoininombre);
			diagramaCicloTablaActive.setGeoNombreFin(geofinnombre);
			// buscar numero interno
			diagramaCicloTablaActive.setPatente(patente);
			diagramaCicloTablaActive.setCarga(carga);
			diagramaCicloTablaActive.setVuelta(Integer.parseInt(String.valueOf(vuelta)));
			diagramaCicloTablaActive.setSeg(String.valueOf(segundos));
			diagramaCicloTablaActive.setTiempoS(tiempoS);
			rlist.add(diagramaCicloTablaActive);

			String ss = "";
			if (i == 1 || !origenbefore.equals(geoininombre)) {
				// Creo el primer nodo origen
				// { key: 6, name: "PLANTA", icono: "icons8-marcadores-40",
				// title: "42", color: "blue", colorTitulo: "white",
				// colorContenido: "white" },
				nodeOrigen.setKey(String.valueOf(geoiniid + "_"));
				nodeOrigen.setName(geoininombre);
				nodeOrigen.setIcono("icons8-marcadores-40");
				nodeOrigen.setTitle("");
				nodeOrigen.setColor("orange");
				nodeOrigen.setColorTitulo("white");
				nodeOrigen.setColorContenido("white");

				// se agrega una sola vez
				nlist.add(nodeOrigen);
				if (!origenbefore.equals(geoininombre))
					ss = "S";

			}

			// if(origenbefore.equals(geoininombre)){

			if (!destinobefore.equals(geofinnombre)) {
				j++;
				// si destino es la primera vez que carga o distinto anterio
				// creamos nodo destino
				nodeDestino.setKey(String.valueOf(geofinid + "_" + j));
				nodeDestino.setName(geofinnombre);
				nodeDestino.setIcono("icons8-marcadores-40");
				// nodeDestino.setTitle(String.valueOf(vuelta));
				nodeDestino.setColor("blue");
				nodeDestino.setColorTitulo("white");
				nodeDestino.setColorContenido("white");

				// destinobefore = geofinnombre;

				// creamos el conecto origen y destino
				// { from: 0, to: 2, color: "grey", thick: 2 },
				link.setFrom(String.valueOf(geoiniid + "_"));
				link.setTo(String.valueOf(geofinid + "_" + j));
				link.setColor("grey");
				link.setThick("2");

				llist.add(link);

				// se crea el link de destino a patente el primero
				// { from: 0, to: 2, color: "grey", thick: 2 },
				LinkDataArrayActive link2 = new LinkDataArrayActive();
				link2.setFrom(String.valueOf(geofinid + "_" + j));
				link2.setTo(String.valueOf(patente + "_" + i));
				link2.setColor("grey");
				link2.setThick("2");

				llist.add(link2);

				// se agrega solo un destino por n repeticiones
				// nlist.add(nodeDestino);

			}
			// ahora agregamos todas las patentes en nodo y conexiones
			nodePatente.setKey(String.valueOf(patente + "_" + i));
			nodePatente.setName(patente);
			nodePatente.setIcono("icons8-cami?-de-construcci?-48");
			nodePatente.setTitle(String.valueOf(vuelta));
			nodePatente.setColor("green");
			nodePatente.setColorTitulo("white");
			nodePatente.setColorContenido("white");

			// se agrega por cada registro con identificador i;
			nlist.add(nodePatente);

			// si el destino es igual al anterios totalizamos las vuelta y
			// creamos los link
			if (destinobefore.equals(geofinnombre)) {
				if (!ss.equals("S")) {
					vueltadestino = vueltadestino + vuelta;
					// { from: 0, to: 2, color: "grey", thick: 2 },
					LinkDataArrayActive link3 = new LinkDataArrayActive();
					link3.setFrom(String.valueOf(geofinid + "_" + j));
					link3.setTo(String.valueOf(patente + "_" + i));
					link3.setColor("grey");
					link3.setThick("2");

					llist.add(link3);
				}
				// nodeDestino.setTitle(String.valueOf(vueltadestino));

			} else {

				// nodeDestino.setTitle(String.valueOf(vueltadestino));
				nlist.add(nodeDestino);
				// vueltadestino = 0;

			} // nuevo

			if (destinobefore.equals(geofinnombre) && !origenbefore.equals(geoininombre)) {

				j++;
				// si destino es la primera vez que carga o distinto anterio
				// creamos nodo destino
				nodeDestino.setKey(String.valueOf(geofinid + "_" + j));
				nodeDestino.setName(geofinnombre);
				nodeDestino.setIcono("icons8-marcadores-40");
				// nodeDestino.setTitle(String.valueOf(vuelta));
				nodeDestino.setColor("blue");
				nodeDestino.setColorTitulo("white");
				nodeDestino.setColorContenido("white");

				link.setFrom(String.valueOf(geoiniid + "_"));
				link.setTo(String.valueOf(geofinid + "_" + j));
				link.setColor("grey");
				link.setThick("2");

				llist.add(link);

				vueltadestino = vueltadestino + vuelta;
				// { from: 0, to: 2, color: "grey", thick: 2 },
				LinkDataArrayActive link3 = new LinkDataArrayActive();
				link3.setFrom(String.valueOf(geofinid + "_" + j));
				link3.setTo(String.valueOf(patente + "_" + i));
				link3.setColor("grey");
				link3.setThick("2");

				llist.add(link3);

				nlist.add(nodeDestino);

			}

			destinobefore = geofinnombre;

			// }//if(!origenbefore.equals(geoininombre)){

			origenbefore = geoininombre;

			// agregamos a lista
			diagramaCicloGraficoActive.setNodeDataArray(nlist);
			diagramaCicloGraficoActive.setLinkDataArray(llist);
			diagramaCicloGraficoActive.setDiagramaCicloTablaActive(rlist);

		} // fin for

		// } // fin for vehiculo

		em3.clear();
		em3.close();
		return diagramaCicloGraficoActive;
	}

	public static List<ProduccionTurnoActive> findProduccionTurno(String usuLogincliente, String clavecliente,
			String fecha_ini, String fecha_fin) {
		// TODO Auto-generated method stub

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // dd/MM/yyyy
		em3 = emf.createEntityManager();

		Date fecdesde = new Date();
		Date fechasta = new Date();
		Calendar calendarin = Calendar.getInstance();
		Calendar calendarfin = Calendar.getInstance();
		try {
			fecdesde = formatteri.parse(fecha_ini);
			fechasta = formatteri.parse(fecha_fin);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendarin.setTime(fecdesde);
		calendarfin.setTime(fechasta);
		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
		Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

		// System.out.println("select * from
		// ('"+timein+"','"+timefin+"','"+fecha_ini+"','"+fecha_fin+"')");

		Query query02 = em3.createNativeQuery(
				"select  geo_ini,  geo_nom_ini,  geo_fin, geo_nom_fin, patente, count(patente) as vuelta , "
						+ " sum(tmpo_ciclo) as segundociclo," + " sum(carga_nominal) as carga" + " from "
						+ " (SELECT * FROM public.informevueltas('" + timein + "', '" + timefin
						+ "', 'CYYS82', 1, 1, 1)) as vuelta , dispositivo.tipo_vehiculo  as t"
						+ " where tipo_vehiculo = tipv_id and t.tipo = 'c' "
						+ " group by geo_ini, geo_nom_ini, geo_fin, geo_nom_fin, patente"
						+ " order by geo_nom_ini,geo_fin,patente " + " ");

		List<ProduccionTurnoActive> mlist = new ArrayList();

		List<Object[]> mlistFor = query02.getResultList();

		for (Object[] pa : mlistFor) {

			ProduccionTurnoActive pa2 = new ProduccionTurnoActive();

			pa2.setGeo_ini((Integer) pa[0]);
			pa2.setGeo_nom_ini((String) pa[1]);
			pa2.setGeo_fin((Integer) pa[2]);
			pa2.setGeo_nom_fin((String) pa[3]);
			pa2.setPatente((String) pa[4]);
			pa2.setVuelta((Long) pa[5]);
			pa2.setSegundociclo((Long) pa[6]);
			pa2.setCarga((Double) pa[7]);

			mlist.add(pa2);

		}

		em3.clear();
		em3.close();

		return mlist;
	}

	public static List<TurnoActive> findTurnoXCliente(String usuLogincliente, String clavecliente, String rut) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		em2 = emf.createEntityManager();

		Query query02 = em2.createNativeQuery("select tur_id, tur_nombre from  cliente.turno where usu_rut_usuario='"
				+ rut + "' order by tur_nombre");

		List<TurnoActive> mlist = new ArrayList();
		List<Object[]> faenalist = query02.getResultList();

		for (Object[] fa : faenalist) {
			TurnoActive fa2 = new TurnoActive();
			fa2.setIdTurno((Integer) fa[0]);
			fa2.setNombre((String) fa[1]);
			mlist.add(fa2);
		}

		em2.clear();
		em2.close();

		return mlist;
	}

	public static List<ResumenConduccionDiarioActive> findConduccionDiario(String patente, String faena,
			String fecha_ini, String fecha_fin, Integer turno) {
		// TODO Auto-generated method stub

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy"); // dd/MM/yyyy
		DateFormat formatteri2 = new SimpleDateFormat("MM/dd/yyyy");
		emc = emf.createEntityManager();

		Date fecdesde = new Date();
		Date fechasta = new Date();
		Calendar calendarin = Calendar.getInstance();

		String fechasi = "";

		try {
			fecdesde = formatteri.parse(fecha_ini);
			//// System.out.println(fecdesde);
			fechasi = formatteri2.format(fecdesde);
			//// System.out.println(fechasi);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Integer sw;
		Integer sw_faena;
		if (patente == "") {
			sw = 1;
			sw_faena = Integer.parseInt(faena);
		} else {
			sw = 0;
			sw_faena = 0;
		}

		Query query02 = emc.createNativeQuery("select * from public.informerendimientodiarioxturno('" + fechasi + "','"
				+ fechasi + "'," + sw + "," + sw_faena + ",'" + patente + "','" + turno + "')");
		//// System.out.println(query02);

		List<Object[]> mlistFor = query02.getResultList();

		List<ResumenConduccionDiarioActive> mlist = new ArrayList();

		for (Object[] pa : mlistFor) {

			ResumenConduccionDiarioActive pa2 = new ResumenConduccionDiarioActive();

			pa2.setPatente((String) pa[0]);
			pa2.setNumero_int((String) pa[1]);
			pa2.setMarca((String) pa[2]);
			pa2.setModelo((String) pa[3]);
			// pa2.setFecha((Timestamp) pa[4]);
			pa2.setFecha_on((Timestamp) pa[5]);
			pa2.setFecha_off((Timestamp) pa[6]);
			pa2.setOdo_ini((Double) pa[7]);
			pa2.setOdo_fin((Double) pa[8]);
			pa2.setOdo((Double) pa[9]);
			pa2.setHoro_ini((Double) pa[10]);
			pa2.setHoro_fin((Double) pa[11]);
			pa2.setHoro((Double) pa[12]);
			pa2.setRalenti((Integer) pa[13]);
			pa2.setTot_ciclo((Integer) pa[14]);

			pa2.setTurno((String) pa[15]);
			pa2.setFecha_ini((Timestamp) pa[16]);
			pa2.setFecha_fin((Timestamp) pa[17]);

			mlist.add(pa2);

		}

		emc.clear();
		emc.close();

		return mlist;
	}

	public static List<EventActive> listaEventos() {
		//
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		emc = emf.createEntityManager();
		Query query02 = emc.createNativeQuery("select * from cliente.eventos_gps where eve_ver_historico = 1");
		//// System.out.println(query02);

		List<Object[]> mlistFor = query02.getResultList();

		List<EventActive> mlist = new ArrayList();

		for (Object[] pa : mlistFor) {

			EventActive pa2 = new EventActive();

			pa2.setId_evento(Integer.toString((Integer) pa[0]));
			pa2.setNombre((String) pa[1]);

			mlist.add(pa2);

		}

		emc.clear();
		emc.close();
		return mlist;
	}

	public static ColorGeo findColor(Integer id) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		em2 = emf.createEntityManager();
		ColorGeo color = em2.find(ColorGeo.class, id);

		em2.clear();
		em2.close();
		return color;
	}

	public static List<ReporteIgnicionActive> findIgnicion(String fecha_ini, String fecha_fin, String faena,
			String patente, String usuario) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // dd/MM/yyyy
		emc = emf.createEntityManager();

		Date fecdesde = new Date();
		Date fechasta = new Date();
		Calendar calendarin = Calendar.getInstance();
		Calendar calendarfin = Calendar.getInstance();
		try {
			fecdesde = formatteri.parse(fecha_ini);
			fechasta = formatteri.parse(fecha_fin);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendarin.setTime(fecdesde);
		calendarfin.setTime(fechasta);
		Timestamp timein = new Timestamp(calendarin.getTimeInMillis());
		Timestamp timefin = new Timestamp(calendarfin.getTimeInMillis());

		Integer sw;
		Integer sw_faena;
		if (patente == "") {
			sw = 1;
			sw_faena = Integer.parseInt(faena);
		} else {
			sw = 0;
			sw_faena = 0;
		}

		Query query02 = emc.createNativeQuery("select * from public.informeignicion('" + timein + "','" + timefin + "',"
				+ sw + "," + sw_faena + ",'" + patente + "',0,'" + usuario + "')");

		// System.out.println(query02);

		List<Object[]> mlistFor = query02.getResultList();

		DecimalFormat df = new DecimalFormat("0.00");

		List<ReporteIgnicionActive> mlist = new ArrayList();

		for (Object[] pa : mlistFor) {

			ReporteIgnicionActive pa2 = new ReporteIgnicionActive();

			pa2.setFecha_ini((Timestamp) pa[0]);
			pa2.setPatente((String) pa[1]);
			pa2.setConductor((String) pa[2]);
			pa2.setTiempo((Integer) pa[3]);
			pa2.setEvento((String) pa[4]);
			pa2.setUbicacion((String) pa[5]);
			pa2.setLat((String) pa[6]);
			pa2.setLon((String) pa[7]);
			pa2.setNuminterno((String) pa[8]);
			pa2.setDistancia((Double) pa[9]);
			pa2.setDistancia_str(df.format(pa2.getDistancia()));
			pa2.setDistancia_str(pa2.getDistancia_str().replace(",", ".").trim());
			pa2.setTiempo_str(df.format((double) pa2.getTiempo() / (double) 60));
			pa2.setTiempo_str(pa2.getTiempo_str().replace(",", ".").trim());

			mlist.add(pa2);

		}

		emc.clear();
		emc.close();
		return mlist;

	}

	public static List<IbuttomActive> findIbuttomXCliente(String cliRut) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sgomtDB");
		DateFormat formatteri = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // dd/MM/yyyy
		emc = emf.createEntityManager();

		Query query02 = emc.createNativeQuery(
				"select * from cliente.ibuttom where usu_rut_usuario ='" + cliRut + "' order by ibu_fecha_crea desc");

		// System.out.println(query02);

		List<Object[]> mlistFor = query02.getResultList();

		List<IbuttomActive> mlist = new ArrayList();

		for (Object[] pa : mlistFor) {

			IbuttomActive pa2 = new IbuttomActive();

			pa2.setFecha_crea((Timestamp) pa[3]);
			pa2.setCodigo((String) pa[1]);
			pa2.setEstado((Integer) pa[2]);
			pa2.setId((Integer) pa[0]);

			mlist.add(pa2);

		}

		emc.clear();
		emc.close();
		return mlist;
	}

}
