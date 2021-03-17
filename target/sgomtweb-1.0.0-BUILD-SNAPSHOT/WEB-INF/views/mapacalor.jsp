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
 
 <style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #map {
        height: 500px !important;
        width: 100% !important;
      }
      /* Optional: Makes the sample page fill the window. */
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
      #floating-panel {
        position: absolute;
        top: 10px;
        left: 25%;
        z-index: 5;
        background-color: #fff;
        padding: 5px;
        border: 1px solid #999;
        text-align: center;
        font-family: 'Roboto','sans-serif';
        line-height: 30px;
        padding-left: 10px;
      }
      #floating-panel {
        background-color: #fff;
        border: 1px solid #999;
        left: 25%;
        padding: 5px;
        position: absolute;
        top: 10px;
        z-index: 5;
      }
    </style>
    
   
 
 
  
</head>
<body class="top-navigation">

	<div id="wrapper">
	
	
	<%@include file="menu.jsp"%>
	
		 <div id="page-wrapper" class="gray-bg">
		 
		 
       <div class="row border-bottom">
       </div>
                          
              <!-- Titulo del Menu  -->
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
	    <c:when test="${rform.collapseshow == 'SI'}">
	    
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
                          <h5>Filtros <b>Mapa calor</b> &nbsp;&nbsp; &nbsp;&nbsp; <small> Buscar por ... </small></h5>                           
                          <div class="ibox-tools">
                              <a class="collapse-link">
                                  <i class="fa fa-chevron-up" id="ocultar"></i>
                              </a>
                          </div>
                      </div>    
                      <form:form action="mapacalor.html" commandName="mapaCalorForm"  id="data_1" autocomplete="off">                  
                      <div class="ibox-content" id="ocultardiv">
                        <div class="panel-group" id="accordion">
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h5 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">Busqueda Rapida</a>
                                            </h5>
                                        </div>
                                        
                                       <!--  <div id="collapseOne" class="panel-collapse collapse in show">  -->
                                       <div id="collapseOne" class="panel-collapse collapse <c:if test="${rform.collapseshow == 'NO'}"> in show</c:if>"> 
                                            <div class="panel-body">
                                                <div class="row"> 
                                                <div class="col-lg-2">   
                                                      <div class="form-group ">                                      
                                                              <label>Faenas </label>
                                                              <div>
                                                                 
                                                                 <select  name="faena" id="faena"  style="width:100%;" tabindex="4"  class="form-control text-uppercase" required>
														
																<option value=""></option>
														
																<option value="0"  <c:if test="${faenaTodas == 'SI'}"> selected</c:if> >TODAS</option>
														  														  														  
															<c:if test="${grupo.gruId != null }">
														  
																<option value="${grupo.gruId}" selected>${grupo.gruNombre}</option>
														  
															</c:if>
														
														<c:forEach items="${listfaena}" var="f" varStatus="count" >	  
														
														   <c:if test="${not  empty f }">
														   		<c:if test="${ f.idGru ne rform.faena }">
														   
														  		 <option value="${f.idGru}">${f.nombreGru}</option>
														   
														   		</c:if>	     															
												
												 		  </c:if>	    
												      </c:forEach>	    																											
												</select>	
                                                              </div>                      
                                                      </div>
                                                    </div>  
                                                    
                                                     
                                                    
                                                    
                                                     
                                                    
                                                      <div class="col-lg-8 b-r">  
                                                      </div>
                                                    
                                                      <div class="col-lg-2 text-center">                                                                                                              
                                                              <button class="ladda-button btn btn-primary " id="toggleSpinners" type="submit" data-style="contract" style="margin-top:20px;">Buscar</button>
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
                                        <div id="collapseTwo" class="panel-collapse collapse <c:if test="${rform.collapseshow == 'SI'}"> in show</c:if>">
                                            <div class="panel-body">
                                                <div class="row">                               
                                                    <div class="col-lg-2">
                                                        <div class="form-group">
                                                            <label class="control-label">&nbsp; Fecha</label>&nbsp;&nbsp; &nbsp;&nbsp; 
                                                            <div class="input-group date">
                                                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" class="form-control" name="fechaDesde"  value="${rform.fechaDesde}" required>                            
                                                            </div>                                                                                                   
                                                        </div> 
                                                    </div>
                                                                                                       
                                                <!--     <div class="col-lg-2">
                                                        <div class="form-group">
                                                            <label class="control-label">&nbsp; Hora </label>&nbsp;&nbsp; &nbsp;&nbsp; 
                                                            <div class="input-group clockpicker" data-autoclose="true">
                                                                 <input type="text" class="form-control" name="horaDesde" id="horaDesde"  value="${rform.horaDesde}" >
                                                                <span class="input-group-addon"><span class="fa fa-clock-o"></span></span>
                                                            </div>                                     
                                                        </div> 
                                                    </div>  -->
                                                    
                                                    <!--  habilitar cuando vuelva a pedir el filtro 
                                                    <div class="col-lg-2">
                                                        <div class="form-group">
                                                            <label class="control-label">&nbsp; Fecha Hasta</label>&nbsp;&nbsp; &nbsp;&nbsp; 
                                                            <div class="input-group date">
                                                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" class="form-control" name="fechaHasta" value="${rform.fechaHasta}" required>
                                                                
                                                            </div>                                      
                                                        </div> 
                                                    </div>
                                                     
                                                    <div class="col-lg-2">
                                                        <div class="form-group">
                                                            <label class="control-label">&nbsp; Hora Hasta</label>&nbsp;&nbsp; &nbsp;&nbsp; 
                                                            <div class="input-group clockpicker" data-autoclose="true">
                                                                <input type="text" class="form-control" name="horaHasta" id="horaHasta" value="${rform.horaHasta}" >
                                                                <span class="input-group-addon"><span class="fa fa-clock-o"></span></span>
                                                            </div>                                       
                                                        </div> 

                                                    </div>
                                                     
                                                   --> 
                                                   <!-- quitar cuando vuelva a pedir el filtro -->
                                                   <input type="hidden"  name="horaHasta" value="${rform.fechaHasta}" >
                                                   <input type="hidden"  name="horaHasta" value="${rform.horaHasta}" >
                                                    
                                                
                                                 <div class="col-lg-2">   

                                                      <div class="form-group">                                      
                                                              <label>Reporte </label>
                                                              <div>
                                                                 <select class="chosen-select" data-placeholder="Seleccione tipo..." name="sw1" id="sw1"  style="width:150px;" tabindex="3" class="form-control text-uppercase">
																																									
														<option value="0" <c:if test="${rform.sw1 == '0'}"> selected</c:if> >TODO</option>
														<option value="1" <c:if test="${rform.sw1 == '1'}"> selected</c:if> >DESCARGA</option>
														<!-- <option value="2" <c:if test="${rform.sw1 == '2'}"> selected</c:if> >DESCARGA</option>  -->
																																						
												</select>
                                                              </div>                      
                                                      </div>
                                                    </div>
                                                    
                                                     <!-- quitar cuando vuelva a pedir el filtro -->
                                                    <div class="col-lg-2">
                                                    </div>
                                                     <div class="col-lg-4 b-r">
                                                     </div>
                                                    
                                                     <div class="col-lg-2 text-center">                                                                                                              
                                                              <button class="ladda-button btn btn-primary " id="toggleSpinners2" type="submit" data-style="contract" style="margin-top:20px;">Filtrar</button>
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
          
  
        
			<div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins ${border}" id="ibox1">
                         <div class="ibox-title">
                            <h5>Mapa Calor</h5>
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
                                <div id="map"></div>
                        </div>
                    
                </div>
            </div>


        
        
                
       
        
       </div>
       
        </div>
       
         <%@include file="footer.jsp"%>
        
     </div>

    
    </div>
    
    <%@include file="footerjs.jsp"%>
        
    <!-- Peity  pie del porcentaje-->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/peity/jquery.peity.min.js"></script>
    <script src="resources/inspinia_v2.9/FullVersion/js/demo/peity-demo.js"></script>
    <!-- Peity demo -->
     
    
    <!-- d3 and c3 charts -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/d3/d3.min.js"></script>
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/c3/c3.min.js"></script>
        <!-- Date range use moment.js same as full calendar plugin -->
	    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/fullcalendar/moment.min.js"></script>

	<!-- Date range picker -->
	<script src="resources/inspinia_v2.9/FullVersion/js/plugins/daterangepicker/daterangepicker.js"></script>
   <!-- Data picker -->
   <script src="resources/inspinia_v2.9/FullVersion/js/plugins/datapicker/bootstrap-datepicker.js"></script>
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/dataTables/datatables.min.js"></script>
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/dataTables/dataTables.bootstrap4.min.js"></script>

    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/dualListbox/jquery.bootstrap-duallistbox.js"></script>
    
      <script src="https://maps.googleapis.com/maps/api/js?client=gme-samtech&channel=heatmaps&libraries=visualization"></script> 
    
    <!-- <script src="https://maps.googleapis.com/maps/api/js?client=gme-samtech&channel=heatmap&libraries=places"></script>  -->
    
    <!-- <script src="https://maps.googleapis.com/maps/api/js?client=gme-samtech&channel=Telemetria&callback=initMap" async defer></script>  -->  
    
     <!-- select 2 -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/select2/select2.full.min.js"></script>
    
    <!-- Chosen -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/chosen/chosen.jquery.js"></script>
    
     <!-- hora -->
      <script src="resources/inspinia_v2.9/FullVersion/js/plugins/clockpicker/clockpicker.js"></script>
      
      <!-- funciones de validacion de hora, efecto load para botones de busqueda y carga de pagina -->
       <%@include file="utiljs/loadpagebuttonjs.jsp"%>
      
   
    <script>
                 
   var ocultar = document.getElementById("ocultar");   
   var ocultardiv= document.getElementById("ocultardiv");
    
   ocultar.addEventListener("click", funcionOcultar);   				
	
    function funcionOcultar(){
    	
    	if (ocultardiv.style.display === "none") {
    		ocultardiv.style.display = "block";
    	  } else {
    		 ocultardiv.style.display = "none";
    		 
    	  }
    	
       }
    
   //console.log(ocultar);

  // $('.chosen-select').chosen({width: "100%"});
    
    $( "#faena" ).select2( {
		placeholder: "Seleccione Faena",
		 allowClear: true
		
	} );
    
    $( "#sw1" ).select2( {
		placeholder: "Seleccione ",
		
		
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
      
   

    
    var heatmap;  
    var mapTypeIds = [];

    
    for(var type in google.maps.MapTypeId) {mapTypeIds.push(google.maps.MapTypeId[type]);}
	mapTypeIds.push("Mapa","SATELLITE");

        var map = new google.maps.Map(document.getElementById('map'), {
              zoom: 12,
              streetViewControl: false,
              center: {lat: parseFloat("${mc.lat}"), lng: parseFloat("${mc.lon}")},
              mapTypeId: 'satellite',                            
              mapTypeControl: true,
			    mapTypeControlOptions: {
			        style: google.maps.MapTypeControlStyle.HORIZONTAL_BAR,
			        position: google.maps.ControlPosition.LEFT_TOP,
					mapTypeIds: mapTypeIds
			    },
			    zoomControl: true,
			    zoomControlOptions: {
			        position: google.maps.ControlPosition.LEFT_CENTER
			    },
			    /*scaleControl: true, //streew view
			    streetViewControl: true,
			    streetViewControlOptions: {
			        position: google.maps.ControlPosition.LEFT_CENTER
			    },*/
			    fullscreenControl: true,
				fullscreenControlOptions: {
			        position: google.maps.ControlPosition.LEFT_CENTER
			    },
				rotateControl : true,
				rotateControlOptions: {
			        position: google.maps.ControlPosition.LEFT_CENTER
			    },
			  });
        
      //CENTRAR ZOOM AUTO
        <c:if test="${not  empty mlistGeoId2 }">	  
       var boundsP = new google.maps.LatLngBounds();
               
      	<c:forEach items="${mlistGeoId2.coordenadas}" var="r" varStatus="count" >	
       				
       				boundsP.extend(new google.maps.LatLng(parseFloat(${r.lat}), parseFloat(${r.lon})));
       		        
       </c:forEach>
      
       map.fitBounds(boundsP);    
		map.panToBounds(boundsP);
		</c:if>
		//FIN CENTRAR 
            
        
			//geocercas                    
            <%@include file="utiljs/geoCercasJSPG.jsp"%>		
          		//boton Geo  (Recibe Arreglo Poligonos de geoCercasJSPG )          
            	<%@include file="utiljs/BotonGeoJs.jsp"%>
            
            //mapacalor            
            <%@include file="utiljs/mapaCalorJs.jsp"%>
            
          
        </script>
        
        


    
</body>

</html>