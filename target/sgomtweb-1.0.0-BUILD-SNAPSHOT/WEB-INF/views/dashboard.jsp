<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link
	href="resources/inspinia_v2.9/FullVersion/css/plugins/jQueryUI/jquery-ui.css"
	rel="stylesheet">
<link href="resources/map-icons-master/dist/css/map-icons.css"
	rel="stylesheet">
<link
	href="resources/inspinia_v2.9/FullVersion/css/plugins/dataTables/datatables.min.css"
	rel="stylesheet">
<link
	href="resources/inspinia_v2.9/FullVersion/css/plugins/dataTables/datatables.min.css"
	rel="stylesheet">


<style>
/*para el dialog */
.ui-dialog {
	background-color: rgba(255, 255, 255, 0.9);
}

.no-close .ui-dialog-titlebar-close {
	display: none
}

/* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
#map {
	height: 700px !important; /* 500 */
	width: 100% !important;
}

/* Optional: Makes the sample page fill the window. */


html, body {
	
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
	font-family: 'Roboto', 'sans-serif';
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

/#legend {
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

.labels {
	/* border: 2px solid black;
    color: red;
    background-color: white;
    font-family: "Lucida Grande", "Arial", sans-serif;
    font-size: 10px;
    font-weight: bold;
    text-align: center;
    width: 40px;
    padding: 3px;
    white-space: nowrap;
    position: relative;*/
	padding: 5px;
	position: absolute;
	visibility: visible;
	z-index: 1030;
}

.labels.active {
	background: grey;
}

.labels.hover {
	background-color: yellow;
}

.labels .arrow {
	border-right-color: rgba(0, 0, 0, 0);
	border-bottom-color: rgba(0, 0, 0, 0);
	border-left-color: rgba(0, 0, 0, 0);
	border-width: 5px 5px 0;
	bottom: 0;
	left: 45%;
	margin-left: 0px;
	border-style: solid;
	height: 0;
	position: absolute;
	width: 0;
}

.labels .inner {
	border-radius: 4px;
	color: #FFFFFF;
	padding: 3px 4px;
	text-align: center;
	text-decoration: none;
	background-color: #333;
}

/* highlight active menu */



@media (min-width: @screen-sm-min) {
    /* overlay sub levels on small screens */
    #sidebar .list-group .collapse.in, #sidebar .list-group .collapsing {
        position: absolute;
        z-index: 1;
        width: 190px;
    }
    #results {
    	height:50px;
    }
}

/* change transition animation to width when entire sidebar is toggled */
#sidebar.collapse {
  -webkit-transition-timing-function: ease;
       -o-transition-timing-function: ease;
          transition-timing-function: ease;
  -webkit-transition-duration: .2s;
       -o-transition-duration: .2s;
          transition-duration: .2s;
}

#sidebar.collapsing {
  opacity: 0.8;
  
  -webkit-transition-timing-function: ease-in;
       -o-transition-timing-function: ease-in;
          transition-timing-function: ease-in;
  -webkit-transition-property: width;
       -o-transition-property: width;
          transition-property: width;

}


#results { 
    width: 100%;
  	height: 700px;
    border-radius: 3px;
    -moz-border-radius: 3px;
    -webkit-border-radius: 3px;
    overflow-y: scroll;
}



</style>

</head>
<body class="top-navigation">

	<div id="wrapper">


		<%@include file="menu.jsp"%>

		<div id="page-wrapper" class="gray-bg">


			<div class="row border-bottom"></div>

			<!-- Titulo del Menu  -->
			<%@include file="barramenu.jsp"%>


			<!-- Contenido principal  -->
			<div class="wrapper wrapper-content animated fadeInRight">

				<div id="mensajejs"></div>
				<div id="mensajejs2"></div>

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

						<c:set var="border" scope="page" value="" />
						<c:set var="iboxContent" scope="page" value="" />
						<c:set var="link" scope="page" value="fa-chevron-up" />
						<c:set var="panelR" scope="page" value=" " />
						<c:set var="panelF" scope="page" value=" in show " />
					</c:when>
					<c:otherwise>
						<c:set var="border" scope="page" value="border-bottom" />
						<c:set var="iboxContent" scope="page" value="display: none;" />
						<c:set var="link" scope="page" value="fa-chevron-down" />
						<c:set var="panelR" scope="page" value=" in show " />
						<c:set var="panelF" scope="page" value=" " />
					</c:otherwise>
				</c:choose>


				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins ${border}" id="ibox1">
							<div class="ibox-title">
								<h5>Dashboard</h5>
								<div class="ibox-tools">
									<a class="collapse-link"> <i class="fa ${link}"></i>
									</a>
								</div>

							</div>
							<div class="ibox-content" style="">
								<div class="sk-spinner sk-spinner-three-bounce">                                
                            		<div class="sk-bounce1"></div>
                                    <div class="sk-bounce2"></div>
                                    <div class="sk-bounce3"></div>                                                                    
                    			</div>
								<div class="row">
									<div class="collapse in col-sm-12" id="sidebar">
										<div class="list-group panel">
											 <div id="results" class="content">											
												<input type="text" class="form-control form-control-sm m-b-xs" id="filter" placeholder="Search in table">	
												<table class="table table-striped table-bordered table-hover geo table-responsive" id="myTable" data-filter=#filter>
				                                <thead>
					                                <tr>
					                                    <th scope='col'>Patente</th>
					                                    <th scope='col'>fecha</th>
					                                    <th scope='col'>ubicacion</th>
					                                    <th scope='col'>Nro Interno</th>
					                                  
					                                </tr>
				                                </thead>
													<tbody id="contenidot"></tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="col-md-12 col-xs-11" value="12" id="map_content">
									<!--  <a href="#sidebar" id="btn-sidebar" data-toggle="collapse"><i class="fa fa-navicon fa-lg"></i></a>-->
										<div id="map"></div>
									</div>
									
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
			<!-- fin row -->


			<!-- inicio estados por camiones maquinas y dumper, (solo para test ) Necesita en include utiljs de dashboardcategoriaJS -->
			<%-- include file="dashboardcategoria.jsp" --%>
			<!-- fin estados por camiones maquinas y dumper, (solo para test ) -->
			<div id="panel-oculto" style="display: none;">panel oculto</div>
			<div class="row"></div>

			<div class="row" id="dgeocerca"></div>

			<%@include file="footer.jsp"%>

		</div>


	</div>

	<%@include file="footerjs.jsp"%>


	<!-- d3 and c3 charts -->
	
	<script src="resources/inspinia_v2.9/FullVersion/js/plugins/d3/d3.min.js"></script>
	<script src="resources/inspinia_v2.9/FullVersion/js/plugins/c3/c3.min.js"></script>
	<script src="resources/inspinia_v2.9/FullVersion/js/popper.min.js"></script>
	<script src="resources/inspinia_v2.9/FullVersion/js/plugins/dualListbox/jquery.bootstrap-duallistbox.js"></script>

	<script src="https://maps.googleapis.com/maps/api/js?client=gme-samtech&channel=heatmaps&libraries=visualization"></script>

	<!-- <script src="https://maps.googleapis.com/maps/api/js?client=gme-samtech&channel=heatmap&libraries=places"></script>  -->

	<!-- <script src="https://maps.googleapis.com/maps/api/js?client=gme-samtech&channel=Telemetria&callback=initMap" async defer></script>  -->
	<script src="resources/inspinia_v2.9/FullVersion/js/plugins/footable/footable.all.min.js"></script>
	
	<script src="resources/inspinia_v2.9/FullVersion/js/plugins/dataTables/datatables.min.js"></script>
	<script src="resources/inspinia_v2.9/FullVersion/js/plugins/dataTables/dataTables.bootstrap4.min.js"></script>

	<script src="resources/map-icons-master/dist/js/map-icons.min.js"></script>
	





	<script src="resources/markerwithlabel/markerwithlabel.js"></script>
	<script src="resources/js-rich-marker/src/richmarker.js"></script>
	<script src="resources/js/acordeon.js"></script>
	<!-- Counter Up  
	<script
		src="resources/inspinia_v2.9/FullVersion/js/plugins/waypoints/lib/jquery.waypoints.js"></script>
	<script
		src="resources/inspinia_v2.9/FullVersion/js/plugins/counterup/jquery.counterup.min.js"></script> 
    
   -->
	<script>
    
    //WinMove();
	

    
$(document).ready(function() {
    //$('.footable').footable();
    //FooTable.get('#table_id').pageSize("5");
    //$('.collapse').collapse();
	//alert("a");
	$("#filter").on("keyup", function() {
		var value = $(this).val().toLowerCase();
	    $("#myTable tr").filter(function() {
	    	$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
 	});
	
	$("#btn-sidebar").click(function() {
		
		var aux = $("#map_content").attr('value');
		console.log(aux);
		
		if(aux == "12" ){
			$("#sidebar").attr("class","col-md-3 collapse in");
			$("#map_content").attr("class","col-md-9 col-xs-11");	
			$("#map_content").attr("value","9");
		}else{
			$("#sidebar").attr("class","col");
			$("#map_content").attr("class","col-md-12 col-xs-11");		
			$("#map_content").attr("value","12");
		}
	});


});


 var markers = [];
 var infoWindowContent = [];
 var URLactual =window.location.protocol +"//"+ window.location.host;
 var idnum;
 var equipo;
     
     
     
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
 			//var le = document.getElementById('legend');  
 			//console.log(le);
         	//le.style.display = 'none';			
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
           	<%--include file="utiljs/mapaAntenas.jsp"--%>
           
	
	//agregar nuevos datos y borrar los viejos
	//https://stackoverflow.com/questions/27778389/how-to-manually-update-datatables-table-with-new-json-data
	
  //intervalo de tiempo
  //https://datatables.net/reference/api/ajax.reload()
	
 
	//var dgeocerca = document.getElementById('dgeocerca');
        
        </script>

	<!-- inicio estados por camiones maquinas y dumper, (solo para test )  necesita el div dashboardcategoria en el html-->
	<%-- include file="utiljs/dashboardcategoriaJS.jsp" --%>
	<!-- fin  estados por camiones maquinas y dumper, (solo para test ) -->

	<script>



</script>


</body>

</html>