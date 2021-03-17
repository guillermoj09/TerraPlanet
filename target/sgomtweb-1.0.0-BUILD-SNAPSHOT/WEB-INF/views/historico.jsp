<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="cl.samtech.sgomt.object.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SAMTECH | SGOMT</title>

<%@include file="style.jsp"%>
 <!-- Sweet Alert -->
    <link href="resources/inspinia_v2.9/FullVersion/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">


</head>
	
<body class="top-navigation gray-bg">

  	<div id="wrapper">

		<%@include file="menu.jsp"%>

		<div id="page-wrapper" class="gray-bg">

			<%@include file="barramenu.jsp"%>
                 
   <!-- Contenido principal  -->
        <div class="wrapper wrapper-content animated fadeInRight">
        
          <div id="mensajejs"></div><div id="mensajejs2"></div>
		       <c:if test="${not empty mensaje}">        
	           		<div class="row">
	                    <div class="col-lg-12">                            	 
							 <div class="form-group">      
								<div class="alert alert-danger">
									<button type="button" class="close" data-dismiss="alert">&times;</button>
									<a href="#" class="alert-link">${mensaje}</a>
								</div>	
							</div>	                             
	                    </div>
	             	</div>
		         </c:if>
        
        
		 	<c:choose>
			    <c:when test="${rform.collapseshow == 'off'}">
		      		<c:set var = "border" scope = "page" value = ""/>
		      		<c:set var = "iboxContent" scope = "page" value = ""/>
		      		<c:set var = "link" scope = "page" value = "fa-chevron-up"/>
		      		<c:set var = "panelR" scope = "page" value = " "/>
		      		<c:set var = "panelF" scope = "page" value = " in show "/>
			    </c:when>    
			    <c:otherwise>
		      		<c:set var = "border" scope = "page" value = "border-bottom"/>
		      		<c:set var = "iboxContent" scope = "page" value = "display: none;"/>
		      		<c:set var = "link" scope = "page" value = "fa-chevron-down"/>
		      		<c:set var = "panelR" scope = "page" value = " in show "/>
		      		<c:set var = "panelF" scope = "page" value = " "/>
			    </c:otherwise>
			</c:choose>
	
        
    
        <div class="row"> <!-- Bloque Formulario de busqueda --> 
        
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                      <div class="ibox-title">
                          <h5>Filtros <b>Historico</b> &nbsp;&nbsp; &nbsp;&nbsp; <small> Buscar por ... </small></h5>                           
                          <div class="ibox-tools">
                              <a class="collapse-link">
                                  <i class="fa fa-chevron-up"></i>
                              </a>
                          </div>
                      </div>         
                       <form:form action="historico.html" commandName="historicoForm"  id="data_1" autocomplete="off">                
                      <div class="ibox-content">
                        <div class="panel-group" id="accordion">
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h5 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">Busqueda Rapida</a>
                                            </h5>
                                        </div>
                                        <div id="collapseOne" class="panel-collapse collapse ${panelR}">
                                            <div class="panel-body">
                                                <div class="row">    
                                                      <div class="col-lg-10 b-r">                                                                                                        
                                                          <div class="form-group col-lg-3">
                                                              <label class="font-normal">Patente</label>
											                <select  tabindex="2" style="width:100%;" name="patente" id="patente" data-placeholder="Seleccione una Patente" class="form-control text-uppercase"  required>
											                <option value=""></option>
													                <c:forEach items="${listPatentes}" var="f" varStatus="count" >														
																	   <c:if test="${not  empty f }">																	   															   
																	  		 <option value="${f.vehPatenteVehiculo}" <c:if test="${f.vehPatenteVehiculo == rform.patente}"> selected</c:if>>${f.vehPatenteVehiculo}<c:if test="${not  empty f.nroInterno }"><p class="text-secondary"> - ${f.nroInterno}</p> </c:if></option>																	   														
															 		   </c:if>	    
														     		</c:forEach>	
											                </select> 
                                                      	</div>
                                                      </div>
                                                      <div class="col-lg-2 text-center">   
                                                      <button class="ladda-button btn btn-primary" id="toggleSpinners" data-style="contract" type="submit" style="margin-top:20px;">Buscar</button>                                                                                                           
                                                              
                                                      </div>
                                                  </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">Filtros Avanzados</a>
                                            </h4>
                                        </div>
                                        <div id="collapseTwo" class="panel-collapse collapse ${panelF}">
                                            <div class="panel-body">
                                                <div class="row">                               
                                                    <div class="col-lg-2">
                                                        <div class="form-group">
                                                            <label class="control-label">&nbsp; Fecha Desde</label>&nbsp;&nbsp; &nbsp;&nbsp; 
                                                            <div class="input-group date">
                                                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" class="form-control" name="fechaDesde" id="fechaDesde"  value="${rform.fechaDesde}" required>                            
                                                            </div>                                                                                                   
                                                        </div> 
                                                    </div>
                                                    <div class="col-lg-2">
                                                        <div class="form-group">
                                                            <label class="control-label">&nbsp; Hora Desde</label>&nbsp;&nbsp; &nbsp;&nbsp; 
                                                            <div class="input-group clockpicker" data-autoclose="true">
                                                                 <input type="text" class="form-control" name="horaDesde" id="horaDesde"  value="${rform.horaDesde}" >
                                                                <span class="input-group-addon"><span class="fa fa-clock-o"></span></span>
                                                            </div>                                     
                                                        </div> 
                                                    </div>                                                    
                                                    <div class="col-lg-2">
                                                        <div class="form-group">
                                                            <label class="control-label">&nbsp; Fecha Hasta</label>&nbsp;&nbsp; &nbsp;&nbsp; 
                                                            <div class="input-group date">
                                                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" class="form-control" name="fechaHasta" id="fechaHasta" value="${rform.fechaHasta}" required>
                                                                
                                                            </div>                                      
                                                        </div> 
                                                    </div>                                                     
                                                    <div class="col-lg-2 b-r">
                                                        <div class="form-group">
                                                            <label class="control-label">&nbsp; Hora Hasta</label>&nbsp;&nbsp; &nbsp;&nbsp; 
                                                            <div class="input-group clockpicker" data-autoclose="true">
                                                                <input type="text" class="form-control" name="horaHasta" id="horaHasta" value="${rform.horaHasta}" >
                                                                <span class="input-group-addon"><span class="fa fa-clock-o"></span></span>
                                                            </div>                                       
                                                        </div> 
                                                    </div>
                                                    <div class="col-lg-2 b-r">
                                                        <div class="form-group">                                      
                                                              <label>Velocidad >= a </label>
                                                              <div>
                                                                  <select  style="width:100%;" tabindex="4" name="velocidad" id="velocidad" data-placeholder="Seleccione una Velocidad"  class="form-control text-uppercase">                                                   
                                                                      <option value=""></option>
                                                                      <option value="0" <c:if test="${rform.velocidad == '0'}"> selected</c:if> >0</option>
															          <option value="5" <c:if test="${rform.velocidad == '5'}"> selected</c:if> >5</option>
															          <option value="10" <c:if test="${rform.velocidad == '10'}"> selected</c:if> >10</option>
															          <option value="20" <c:if test="${rform.velocidad == '20'}"> selected</c:if> >20</option>
															          <option value="25" <c:if test="${rform.velocidad == '25'}"> selected</c:if> >25</option>
															          <option value="30" <c:if test="${rform.velocidad == '30'}"> selected</c:if> >30</option>
															          <option value="35" <c:if test="${rform.velocidad == '35'}"> selected</c:if> >35</option>
															          <option value="40" <c:if test="${rform.velocidad == '40'}"> selected</c:if> >40</option>		  		  
															          <option value="45" <c:if test="${rform.velocidad == '45'}"> selected</c:if> >45</option>		  		  		  
															          <option value="50" <c:if test="${rform.velocidad == '50'}"> selected</c:if> >50</option>		  		  		  
															          <option value="55" <c:if test="${rform.velocidad == '55'}"> selected</c:if> >55</option>		  		  		  		  
															          <option value="60" <c:if test="${rform.velocidad == '60'}"> selected</c:if> >60</option>
															          <option value="65" <c:if test="${rform.velocidad == '65'}"> selected</c:if> >65</option>
															          <option value="70" <c:if test="${rform.velocidad == '70'}"> selected</c:if> >70</option>
															          <option value="75" <c:if test="${rform.velocidad == '75'}"> selected</c:if> >75</option>		  
															          <option value="80" <c:if test="${rform.velocidad == '80'}"> selected</c:if> >80</option>
															          <option value="90" <c:if test="${rform.velocidad == '90'}"> selected</c:if> >90</option>
																	  <option value="95" <c:if test="${rform.velocidad == '95'}"> selected</c:if> >95</option>
															          <option value="100" <c:if test="${rform.velocidad == '100'}"> selected</c:if> >100</option>
															          <option value="105" <c:if test="${rform.velocidad == '105'}"> selected</c:if> >105</option>
															          <option value="110" <c:if test="${rform.velocidad == '110'}"> selected</c:if> >110</option>
															          <option value="115" <c:if test="${rform.velocidad == '115'}"> selected</c:if> >115</option>
															          <option value="120" <c:if test="${rform.velocidad == '120'}"> selected</c:if> >120</option>
															          <option value="125" <c:if test="${rform.velocidad == '125'}"> selected</c:if> >125</option>
															          <option value="130" <c:if test="${rform.velocidad == '130'}"> selected</c:if> >130</option>
															          <option value="999" <c:if test="${rform.velocidad == '999'}"> selected</c:if> >Detenido</option>                                                              
                                                                  </select>
                                                              </div>                      
                                                      </div> 
                                                    </div>
                                                    <div class="col-lg-2">
                                                     <label class="font-normal">Eventos</label>
										                <select  tabindex="2" style="width:100%;" name="evento" id="evento" data-placeholder="Seleccione un Evento" class="form-control text-uppercase"  required>
										                <option value="0">TODOS</option>
												                <c:forEach items="${listEventos}" var="f" varStatus="count" >														
																   <c:if test="${not  empty f }">																	   															   
																  		 <option value="${f.id_evento}" <c:if test="${f.id_evento == rform.evento}"> selected</c:if>>${f.nombre}</option>																	   														
														 		   </c:if>	    
													     		</c:forEach>	
										                </select>             
                                                    </div>
                                                </div>  
                                                <div class="row">                               
                                                    <div class="col-lg-10 ">
                                                        <div class="form-group">                                                                                                   
                                                        </div> 
                                                    </div>
                                                    <div class="col-lg-2 text-center">                                                        
                                                        <button class="ladda-button btn btn-primary" id="toggleSpinners2" data-style="contract" type="submit" style="margin-top:20px;">Filtrar</button>                                                        
                                                    </div>
                                                 </div>                                                                              
                                            </div>
                                        </div>
                                    </div>
                                </div>                          

                                              
                      </div>  
                      
                           </form:form>                       
                    </div>
                </div>
            </div>  <!-- Fin bloque Fomr de Busqueda -->   
         
<!--- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->       
      
       <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins ${border}" id="ibox1">                    
                        <div class="ibox-title">
                            <h5>Reporte Historico</h5>
                            <div class="ibox-tools">
                              <a class="collapse-link">
                                  <i class="fa ${link}"></i>
                              </a>
                          </div>
                        </div>                                                             
                        	<div class="ibox-content" style="${iboxContent}">
                        	
                        	 <div class="sk-spinner sk-spinner-three-bounce">                                
                                 <div class="sk-bounce1"></div>
                                    <div class="sk-bounce2"></div>
                                    <div class="sk-bounce3"></div>                                                                    
                    			</div>
                        	
                        	<div class="row">
                                    <div class="col-lg-12" style="margin-bottom:10px;">                                                              
                                       <button class="btn btn-success btn-outline btn_ruta"  title="Ver en mapa la Ruta de la busqueda" type="button"><i class="fa fa-road"></i>&nbsp;&nbsp;<span class="bold">Ruta</span></button>&nbsp;&nbsp;&nbsp;&nbsp;
                                       <button class="btn btn-success btn-outline btn_animar"  title="Animar la ruta de la busqueda" type="button"><i class="fa fa-bus"></i>&nbsp;&nbsp;<span class="bold">Animar</span></button>&nbsp;&nbsp;&nbsp;&nbsp;
                                       <button class="btn btn-success btn-outline btn_kml" title="Exportar a KML la busqueda" type="button"><i class="fa fa-file-code-o"></i>&nbsp;&nbsp;<span class="bold">KML</span></button>&nbsp;&nbsp;&nbsp;&nbsp;                                                                
                                    </div>                                                                                                
                              </div>
                        	 <div class="sk-spinner sk-spinner-three-bounce">                                
                                 <div class="sk-bounce1"></div>
                                    <div class="sk-bounce2"></div>
                                    <div class="sk-bounce3"></div>                                                                    
                    			</div>                    			
								<div class="table-responsive">
									<table
										class="table table-striped table-bordered table-hover dataTables-example">
										<thead>
											<tr>
											<th>Patente</th>
                                            <th>Conductor</th>
                                            <th>Fecha</th>                                            
                                            <th>Ubicación</th> 
                                            <th>Velocidad (Km/H)</th> 
                                            <th>Evento</th> 
                                            <th title="Orientacion">Orient.</th>
                                            <th>Mapa</th>                                                                                       
											</tr>
										</thead>
									 <tbody>	
									 
											<c:forEach items="${listHistorico}" var="r" varStatus="count" >														
											   <c:if test="${not  empty r }">																	   															   
											  		 <tr class=" ${r.data_date} ">															                                    
						                                  <td>${r.patente}</td>
						                                  <td>${r.chofer}</td>
						                                  <td><fmt:formatDate value="${r.data_date}" pattern="dd/MM/yyyy HH:mm:ss" /> </td>	
						                                  					                                  						                                    
						                                  <td>${r.ubicacion}</td>
						                                  <td>${r.spd}</td>
						                                  <td title="${r.nombreve}" idevent="${r.idEvent}"><font size="+1.5">${r.classEvent}</font><span style="display:none">${r.nombreve}</span></td>
						                                  <td title="${r.nomflecha}"><img src="resources/img/historico/${r.rutaflecha}" width="16" height="16"><span style="display:none">${r.nomflecha}</span></td>						                                  						                                  
						                                 <td title="Ver en Mapa"><font size="+1.5"><a style="color:#337ab7" class="link_mapa" link="mapaxpatentereporte.html?patente=${r.patente}&lat=${r.lat}&lon=${r.lon}"><i class="fa fa-external-link"></i></a></font></td>  
						                                 		                                        	                                      
						                              </tr>																	   														
									 		   </c:if>	    
								     		</c:forEach>				 
	                                    	                         
		                                </tbody>
									</table>
								</div>
							</div>
                               		                                   
              </div>
                                   
            </div>
                                                
        </div>

       
       </div>
        
					<%@include file="footer.jsp"%>
        
            </div>		

		</div>
	


	 <%@include file="footerjs.jsp"%>
	 
	   <!-- Date range use moment.js same as full calendar plugin -->
	    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/fullcalendar/moment.min.js"></script>
	         
    <!-- Date range picker -->
	<script src="resources/inspinia_v2.9/FullVersion/js/plugins/daterangepicker/daterangepicker.js"></script>
   <!-- Data picker -->
   <script src="resources/inspinia_v2.9/FullVersion/js/plugins/datapicker/bootstrap-datepicker.js"></script>
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/dataTables/datatables.min.js"></script>
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/dataTables/dataTables.bootstrap4.min.js"></script>
         <!-- hora -->
      <script src="resources/inspinia_v2.9/FullVersion/js/plugins/clockpicker/clockpicker.js"></script>
      
         <!-- select 2 -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/select2/select2.full.min.js"></script>
    
      <!-- Sweet alert -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/sweetalert/sweetalert.min.js"></script>
      
      <!-- funciones de validacion de hora, efecto load para botones de busqueda y carga de pagina -->
       <%@include file="utiljs/loadpagebuttonjs.jsp"%>
       <%@include file="utiljs/valclockpickerjs.jsp"%>
       <%@include file="utiljs/validarFechaHoraJs.jsp"%>
   
	<script>
	
	//requiere validarFechaHoraJs y sweetalert
	var formdata = document.getElementById('data_1');  	
	formdata.addEventListener("submit", validarFechasHora, true);  
	
    var URLactual =window.location.protocol +"//"+ window.location.host;
    
	  $( "#patente" ).select2( {
			placeholder: "Seleccione Patente",
			 allowClear: true
			
		} );
	  
	  $( "#evento" ).select2( {
			placeholder: "Seleccione Evento",
			 allowClear: true
			
		} );

	    $( "#velocidad" ).select2( {
			placeholder: "Seleccione una Opcion",
			 allowClear: true
			
		} );
	
    $('#data_1 .input-group.date').datepicker({
    	  language: 'es',
    	  todayBtn: "linked",
          keyboardNavigation: false,
          forceParse: false,
          calendarWeeks: true,
          autoclose: true,
          format: "dd/mm/yyyy"
      });
              

        $('.clockpicker').clockpicker();
        
        $(document).ready(function(){
            $('.dataTables-example').DataTable({
            	"language": {
                    "url": URLactual + "/sgomtweb/resources/datetableespanil.json"
                },
            	pageLength: 10,
                //"order": [[ 2, "asc" ]],
                //"order": false,
                responsive: true,
                dom: '<"html5buttons"xºB>lTfgitp',
                buttons: [
                    {extend: 'copy'},
                    {extend: 'csv'},
                    {extend: 'excel',    
                    	title: 'Historico',
                    	 exportOptions: {
                             columns: [ 0, 1, 2, 3, 4 ,5 , 6 ]
                    	 }
                    	
                    },
                    
                    {extend: 'pdf', 
                    	title: 'Historico',                    	
                   	 exportOptions: {
                            columns: [ 0, 1, 2, 3, 4 ,5 , 6 ]
                   	 }
                    	
                    },
                    

                    {extend: 'print',
                     customize: function (win){
                            $(win.document.body).addClass('white-bg');
                            $(win.document.body).css('font-size', '10px');

                            $(win.document.body).find('table')
                                    .addClass('compact')
                                    .css('font-size', 'inherit');
                    }
                    }
                ]

            });
            
           
            $(document).on("click",".btn_ruta",function(){
            	
            	window.open("maparuta.html?patente=${rform.patente}&fechain=${rform.fechaDesde} ${rform.horaDesde}&fechafin=${rform.fechaHasta} ${rform.horaHasta}&velocidad=${rform.velocidad}","RUTA","width=800,height=650,scrollbars=NO,resizable=YES,class=lbOn,location=0,status=0, directories=0, toolbar=0, titlebar=0");
	    	});
            
            $(document).on("click",".btn_animar",function(){
            	
            	window.open("mapaanimar.html?patente=${rform.patente}&fechain=${rform.fechaDesde} ${rform.horaDesde}&fechafin=${rform.fechaHasta} ${rform.horaHasta}&velocidad=${rform.velocidad}","RUTA","width=800,height=650,scrollbars=NO,resizable=YES,class=lbOn,location=0,status=0, directories=0, toolbar=0, titlebar=0");
	    	});
            
           
	    	 $(document).on("click",".btn_kml",function(){	            	
	       
	            	location.href="downloadKML.kml?patente=${rform.patente}&fechain=${rform.fechaDesde} ${rform.horaDesde}&fechafin=${rform.fechaHasta} ${rform.horaHasta}&velocidad=${rform.velocidad}","RUTA","width=800,height=650,scrollbars=NO,resizable=YES,class=lbOn,location=0,status=0, directories=0, toolbar=0, titlebar=0"
		    	});
            
            $(document).on("click",".link_mapa",function(){
            	window.open($(this).attr('link'),"MAPA","top=100,left=300,width=800,height=650,scrollbars=NO,resizable=YES,class=lbOn,location=0,status=0, directories=0, toolbar=0, titlebar=0" );
	    	});

        });
	    
    
    </script>
	

	

</body>

</html>

