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
<!-- <meta http-equiv="refresh" content="60" />  -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SAMTECH | SGOMT</title>

<%@include file="style.jsp"%> 
 <link href="resources/inspinia_v2.9/FullVersion/css/plugins/jQueryUI/jquery-ui.css" rel="stylesheet">
 <style>
      /*para el dialog */
      .ui-dialog {
     background-color: rgba(255,255,255,0.9);
}
.no-close .ui-dialog-titlebar-close {display: none }

      
     
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #map {
        height: 700px !important;  /* 500 */
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
      
      
        #legend {
        font-family: Arial, sans-serif;
        /*background: #fff;*/
        padding: 10px;
        margin: 10px;
        /*border: 3px solid #000;*/
        opacity: 0.8;
      }
      #legend h3 {
        margin-top: 0;
      }
      #legend img {
        vertical-align: middle;
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
	    <c:when test="${mc.collapseshow == 'SI'}">
	    
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
         
                 
			<div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins ${border}" id="ibox1">
                         <div class="ibox-title">
                            <h5>Dashboard</h5>
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
                                  <div id="legend">
                                                           
                                 </div> <!-- fin legend -->
                        </div>
                    
                </div>
            </div>


       </div> <!-- fin row -->
       
       <!-- inicio estados por camiones maquinas y dumper, (solo para test ) Necesita en include utiljs de dashboardcategoriaJS -->   
    <%@ include file="dashboardcategoria.jsp" %>
       <!-- fin estados por camiones maquinas y dumper, (solo para test ) -->
	
	<%@ include file="dashboardcategoriaall.jsp" %>
			
			<div class="row">
			
			</div>
			
			<div class="row" id="dgeocerca">
			 
			
			
			
			</div>
       
        </div>
       
         <%@include file="footer.jsp"%>
        
     </div>

    
    </div>
    
    <%@include file="footerjs.jsp"%>
        
    
    <!-- d3 and c3 charts -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/d3/d3.min.js"></script>
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/c3/c3.min.js"></script>
    	
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/dualListbox/jquery.bootstrap-duallistbox.js"></script>
    
      <script src="https://maps.googleapis.com/maps/api/js?client=gme-samtech&channel=heatmaps&libraries=visualization"></script> 
    
    <!-- <script src="https://maps.googleapis.com/maps/api/js?client=gme-samtech&channel=heatmap&libraries=places"></script>  -->
    
    <!-- <script src="https://maps.googleapis.com/maps/api/js?client=gme-samtech&channel=Telemetria&callback=initMap" async defer></script>  --> 
    
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/dataTables/datatables.min.js"></script>
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/dataTables/dataTables.bootstrap4.min.js"></script>
    
      <script src="resources/inspinia_v2.9/FullVersion/js/plugins/jquery-ui/jquery-ui.min.js"></script>    
       <script src="resources/inspinia_v2.9/FullVersion/js/popper.min.js"></script>
    
    
    <!-- Counter Up  
	<script
		src="resources/inspinia_v2.9/FullVersion/js/plugins/waypoints/lib/jquery.waypoints.js"></script>
	<script
		src="resources/inspinia_v2.9/FullVersion/js/plugins/counterup/jquery.counterup.min.js"></script> 
    
   -->
    <script>
    
    //WinMove();
 var markers = [];
 var infoWindowContent = [];
     var URLactual =window.location.protocol +"//"+ window.location.host;
    
    var heatmap;  
    var mapTypeIds = [];

    
    for(var type in google.maps.MapTypeId) {mapTypeIds.push(google.maps.MapTypeId[type]);}
	mapTypeIds.push("Mapa","SATELLITE");

        var map = new google.maps.Map(document.getElementById('map'), {
              zoom: 14,
              streetViewControl: false,
              center: {lat: ${mc.lat}, lng: ${mc.lon}},
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
        

             var myWindow = null;
             var doc = null;
             
             /* Listado de ultimo gps en el map */
         	</script>	
         	<%@include file="utiljs/BotonTablaUltimoDatosGps.jsp"%>
         	<script>	
 			var le = document.getElementById('legend');         	
         	le.style.display = 'none';			
             //para equipos dentro de google maps , usando el servicio
            //map.controls[google.maps.ControlPosition.RIGHT_BOTTOM].push(legend);            
            /* fin Listado de ultimo gps en el map */
             
	     	//utimaPosicion
            <%--@include file="utiljs/UltimaPosicionVehiculos.jsp"--%>
		
            </script>	
            <%@include file="utiljs/UltimaPosicionVehiculoAjax.jsp"%>
            <script>
                      
            //geoCercasJS
            <%@include file="utiljs/geoCercasJSPG.jsp"%>   
            
        	
           
           //marca terrno (Antenas Solo TEST)
           	<%@include file="utiljs/mapaAntenas.jsp"%>
           
	
	//agregar nuevos datos y borrar los viejos
	//https://stackoverflow.com/questions/27778389/how-to-manually-update-datatables-table-with-new-json-data
	
  //intervalo de tiempo
  //https://datatables.net/reference/api/ajax.reload()
	
 
	//var dgeocerca = document.getElementById('dgeocerca');
        
        </script>
     
       <!-- inicio estados por camiones maquinas y dumper, (solo para test )  necesita el div dashboardcategoria en el html-->    
   	<%@ include file="utiljs/dashboardcategoriaJS.jsp" %>     
   	   <!-- fin  estados por camiones maquinas y dumper, (solo para test ) -->
   	   
   	    	<%@ include file="utiljs/dashboardcategoriaAllJS.jsp" %>  

<script>
 

</script>

    
</body>

</html>