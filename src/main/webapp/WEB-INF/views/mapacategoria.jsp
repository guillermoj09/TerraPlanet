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
 
 <style>
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
        background: #fff;
        padding: 10px;
        margin: 10px;
        border: 3px solid #000;
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
                                 
                                 	
                                 
                                 </div> 
                        </div>
                    
                </div>
            </div>


       </div> <!-- fin row -->
       
      
		
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
    
    
    <!-- Counter Up  
	<script
		src="resources/inspinia_v2.9/FullVersion/js/plugins/waypoints/lib/jquery.waypoints.js"></script>
	<script
		src="resources/inspinia_v2.9/FullVersion/js/plugins/counterup/jquery.counterup.min.js"></script> 
    
   -->
    <script>
    
    var heatmap;  
    var mapTypeIds = [];
    
    var myWindow = null;
    var doc = null;
    
    var markers = [];
    var infoWindowContent = [];
    var URLactual =window.location.protocol +"//"+ window.location.host;

    
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
        
              
            //geoCercasJS
            <%@include file="utiljs/geoCercasJSPG.jsp"%>   
            
        	/* Listado de ultimo gps en el map */ 
        	 </script>	
        	<%@include file="utiljs/BotonTablaUltimoDatosGps.jsp"%>
        	 <script>	
			var le = document.getElementById('legend');         	
        	le.style.display = 'none';			
            //para equipos dentro de google maps , usando el servicio
           map.controls[google.maps.ControlPosition.RIGHT_BOTTOM].push(legend);            
           /* fin Listado de ultimo gps en el map */
    
//Agregar botones Categoria

		var activoControlDiv = document.createElement('div');
        var activoControl = new ActivoControl(activoControlDiv);

        
        //Activo
        activoControlDiv.index = 1;
        map.controls[google.maps.ControlPosition.LEFT_CENTER].push(activoControlDiv);     
  
         function ActivoControl(controlDiv) {
  
     	// Set CSS for the control border.
          var controlUI = document.createElement('div');
          controlUI.style.backgroundColor = '#fff';
          controlUI.style.border = '2px solid #fff';
          controlUI.style.borderRadius = '2px';
          controlUI.style.boxShadow = '0 2px 6px rgba(0,0,0,.3)';
          controlUI.style.cursor = 'pointer';
          controlUI.style.marginBottom = '22px';
          controlUI.style.marginLeft = '9px';
          controlUI.style.marginTop = '8px';
          controlUI.style.textAlign = 'center';
          controlUI.title = 'Click para mostrar o ocultar Activo';
          controlDiv.appendChild(controlUI);

          // Set CSS for the control interior.
          var controlText = document.createElement('div');
          //controlText.style.color = 'rgb(25,25,25)';
          //controlText.style.fontFamily = 'Roboto,Arial,sans-serif';
          controlText.style.fontFamily = 'Normal';
          controlText.style.fontSize = '16px';
          controlText.style.lineHeight = '38px';
          controlText.style.paddingLeft = '12px';
          controlText.style.paddingRight = '12px';
          controlText.innerHTML = '<i class="fa fa-check" title="Activo"></i>';
          controlUI.appendChild(controlText);
          
          }     
         
         
       //Retraso
         var retrasoControlDiv = document.createElement('div');
         var retresoControl = new RetrasoControl(retrasoControlDiv);
         
         retrasoControlDiv.index = 1;
        map.controls[google.maps.ControlPosition.LEFT_CENTER].push(retrasoControlDiv);     
  
         function RetrasoControl(controlDiv) {
  
     	// Set CSS for the control border.
          var controlUI = document.createElement('div');
          controlUI.style.backgroundColor = '#fff';
          controlUI.style.border = '2px solid #fff';
          controlUI.style.borderRadius = '2px';
          controlUI.style.boxShadow = '0 2px 6px rgba(0,0,0,.3)';
          controlUI.style.cursor = 'pointer';
          controlUI.style.marginBottom = '22px';
          controlUI.style.marginLeft = '9px';
          controlUI.style.marginTop = '8px';
          controlUI.style.textAlign = 'center';
          controlUI.title = 'Click para mostrar o ocultar Retraso';
          controlDiv.appendChild(controlUI);

          // Set CSS for the control interior.
          var controlText = document.createElement('div');
          //controlText.style.color = 'rgb(25,25,25)';
          //controlText.style.fontFamily = 'Roboto,Arial,sans-serif';
          controlText.style.fontFamily = 'Normal';
          controlText.style.fontSize = '16px';
          controlText.style.lineHeight = '38px';
          controlText.style.paddingLeft = '12px';
          controlText.style.paddingRight = '12px';
          controlText.innerHTML = '<i class="fa fa-warning" title="Retraso"></i>';
          controlUI.appendChild(controlText);
          
          }
         
         //Alerta
         
         var alertaControlDiv = document.createElement('div');
         var alertaControl = new AlertaControl(alertaControlDiv);
         
         
         alertaControlDiv.index = 1;
        map.controls[google.maps.ControlPosition.LEFT_CENTER].push(alertaControlDiv);     
  
         function AlertaControl(controlDiv) {
  
     	// Set CSS for the control border.
          var controlUI = document.createElement('div');
          controlUI.style.backgroundColor = '#fff';
          controlUI.style.border = '2px solid #fff';
          controlUI.style.borderRadius = '2px';
          controlUI.style.boxShadow = '0 2px 6px rgba(0,0,0,.3)';
          controlUI.style.cursor = 'pointer';
          controlUI.style.marginBottom = '22px';
          controlUI.style.marginLeft = '9px';
          controlUI.style.marginTop = '8px';
          controlUI.style.textAlign = 'center';
          controlUI.title = 'Click para mostrar o ocultar Alerta';
          controlDiv.appendChild(controlUI);

          // Set CSS for the control interior.
          var controlText = document.createElement('div');
          //controlText.style.color = 'rgb(25,25,25)';
          //controlText.style.fontFamily = 'Roboto,Arial,sans-serif';
          controlText.style.fontFamily = 'Normal';
          controlText.style.fontSize = '16px';
          controlText.style.lineHeight = '38px';
          controlText.style.paddingLeft = '12px';
          controlText.style.paddingRight = '12px';
          controlText.innerHTML = '<i class="fa fa-minus-circle" title="Activo"></i>';
          controlUI.appendChild(controlText);
          
          }
         
        // var markers = [];
         markers = [];
   		 	      
     	var icon = 'http://maps.google.com/mapfiles/ms/icons/red-dot.png';
     	      
                
 
        function getCoords() {
        	$.ajax({
        	url: "ultimaposicionajax.json",
        	type: "POST",
        	data: {
        	rutcliente : "${usuario.clienterut}",
        	rut : "${usuario.rut}"
        	},
        	//contentType:"application/json; charset=utf-8",
        	dataType: "json",
        	//contentType = 'application/json',
        	success: function(valor) {

        	//var infoWindowContent = [];
        	infoWindowContent = [];

        	//	DeleteMarkers();
        	//lleno contenido para usar en los maker
        	for (var i = 0; i < valor.length; i++) {
        	 
        	 			   infoWindowContent[i] 	= '<div id="content">'+
        	   	      								  '<div id="siteNotice">'+
        	   	      								  '</div>'+
        	   	      							      '<h1 id="firstHeading" class="firstHeading">'+valor[i].patente+'</h1>'+
        	   	      								  '<div id="bodyContent">'+
        	   	      								  '<p><b>Evento</b>:  <font size="+1.5"><i class=\''+valor[i].classEvent+'\'></i> </font> '+ valor[i].evento +'</p>' +
        	   	      								  '<p><b>Tipo Vehiculo</b>: '+valor[i].tipoVehiculo+'</p>'+
        	   	      								  '<p><b>Marca</b>:   '+valor[i].marca+'</p>'+   	      								  
        	   	      								  '<p><b>Fecha GPS</b>:  '+valor[i].fechaS+'</p>'+
        	   	      								  '<p><b>Fecha DB</b>:  '+valor[i].fechaSdb+'</p>'+
        	   	      								  '<p><b>Numero Interno</b>:  '+valor[i].nroInterno+'</p>'+
        	   	      								  '<p><b>Velocidad</b>:   '+valor[i].velocidad+' Kmh</p>'+   	      								
        	   	      								  '<p><b>Geocerca</b>:  '+valor[i].rutGeocerca+'</p>'+
        	   	      							 '<p><b>Estado</b>:  '+valor[i].descestado+'</p>'+
        	   	      							      '</div>'+
        	   	      								  '</div>';  
        	 
        	}//fin for
        	i = 0;

        	//(div legent) se carga el elemento legent, luego se crea el contenido
        	var legend = document.getElementById('legend');
        	    var div2 = document.createElement('div');
        	    div2.setAttribute("id", "contenido");
        	    contenido = document.getElementById('contenido');
        	    
        	    //creo la tabla
        	       var table = document.createElement('TABLE');
	  table.setAttribute("id",  "ultimos");
	  table.setAttribute("style",  "font-size:100%");
    table.setAttribute("class",  "table table-striped table-bordered table-hover geo");
	  
	  table.border = '0';
	  
	  var tableThead = document.createElement('thead');
	  table.appendChild(tableThead);
	  
	  var tr = document.createElement('TR');
	  
	  tableThead.appendChild(tr);
	  
	  var th = document.createElement('TH');
	 
	      		
	    th.appendChild(document.createTextNode("Patente"));
	      		    
	    tr.appendChild(th);
	      	
	    var th = document.createElement('TH');	 
		      		
	    th.appendChild(document.createTextNode("Nr Interno"));
		      			
	    tr.appendChild(th);
		      	
		var th = document.createElement('TH');		
			      		
		th.appendChild(document.createTextNode("Fecha GPS"));
			      			
		tr.appendChild(th);
		
		var th = document.createElement('TH');		
			      		
		th.appendChild(document.createTextNode("Ubicacion"));
			      			
		tr.appendChild(th);
		
		var th = document.createElement('TH');		
  		
		th.appendChild(document.createTextNode("Estado"));
			      			
		tr.appendChild(th);
		
		var th = document.createElement('TH');		
  		
		th.appendChild(document.createTextNode("Fecha DB"));
			      			
		tr.appendChild(th);
		
		 var tableBody = document.createElement('TBODY');
	  table.appendChild(tableBody);
	// fin crear tabla	primera parte

        	for (i in valor)
        	{
        	    //camion = 6;
        	    //camion dumper = 30;
        	    //maquina  = "11, 19, 22, 25, 26, 27";
        	   //console.log(i);
        	   
        	   icon = 'http://maps.google.com/mapfiles/ms/icons/'+valor[i].icon;   

        	  
        	   var myLatlng =  new google.maps.LatLng(valor[i].lat, valor[i].lon);
        	   
        	   window["patente" + "i"]  = valor[i].patente;
        	   window["evento" + "i"]  = valor[i].evento;
        	   window["marca" + "i"]  = valor[i].marca;
        	   window["fecha" + "i"]  = valor[i].fecha;
        	   window["nroInterno" + "i"]  = +valor[i].nroInterno; 
        	   
        	      //creamos maker  
        	      marker = new google.maps.Marker({
        		        		        	
        		        	//position: {lat: parseFloat(valor[i].lat), lng: parseFloat(valor[i].lon)},
        		        	position: myLatlng,
        		            map: map,
        		            icon: icon,
        		           // animation: google.maps.Animation.BOUNCE,
        			  		title: patentei
        			  		
        		        });
        		        	         
        		    
        		    //abrimos infoWindos desde los maker
        	        google.maps.event.addListener(marker, 'click', (function(marker, i) {
        	            return function() {
        	                infoWindow.setContent(infoWindowContent[i]);
        	                infoWindow.open(map, marker);
        	            }
        	        })(marker, i));
        	            
        		    markers.push( marker);
        		    	    
        		      	  //se crea los elementos que se van a agregar al div legent
        		      	  var patente = valor[i].patente;
        	              var fecha = valor[i].fechaS;
        	              var geo = valor[i].rutGeocerca;
        	              var nro = valor[i].nroInterno;
        	              var descestado = valor[i].descestado;
        	              var fechaSdb = valor[i].fechaSdb;
        	              var estado = valor[i].estado;
        	                           
        	              var div = document.createElement('div');
        	              div.setAttribute("id",  "equipos" + i);
        	                                                      
        	              div.innerHTML = ' <b>'+nro+'</b> '+patente + ' GPS ' + fecha + ' '+ geo+ ' '+  descestado + ' DB ' +fechaSdb;                       
        	              div2.appendChild(div); 
        	        
        	              idnumero = "equipos" + i;
        	              
        	           	var equipos = document.getElementById(idnumero);
        	           	
        	           	//creo contenido tabla //nuevo         	
        	           	  var tr = document.createElement('TR');
        	           	  tr.setAttribute("id",  "equipos" + i);
        	  	    	  tableBody.appendChild(tr);
        	           	
        	           	  var td = document.createElement('TD');	     
        	  	      	  td.appendChild(document.createTextNode(patente));
        	  	      	  
        	  	      	  var td2 = document.createElement('TD');	     
        	  	      	  td2.appendChild(document.createTextNode(nro));
        	  	      	  
        	  	      	  var td3 = document.createElement('TD');	     
        	  	      	  td3.appendChild(document.createTextNode(fecha));
        	  	      	  
        	  	      	  var td4 = document.createElement('TD');	     
        	  	      	  td4.appendChild(document.createTextNode(geo));
        	  	      	          	  	       
      	  	      var td5 = document.createElement('TD');	
      	  	      //console.log(descestado)
      	  	      var estadodesc = null
      	  	      if(estado == 2){
      	  	    	estadodesc = "\<small style=\'color: orange; font-size :12px;\'\>Retraso de Datos \<i class=\'fa fa-warning\'\>\</i\> \</small\>";
      	  	      }
      	  	  	  if(estado == 1){
      	  	  	estadodesc = "\<small style=\'color: green; font-size :12px;\'\>Activo \<i class=\'fa fa-check\'\>\</i\> \</small\>" ;
  	  	      	  }
      	  		  if(estado == 3){
      	  			estadodesc = "\<small style=\'color: red; font-size :12px;\'\>Sin Transmision \<i class=\'fa fa-circle\'\>\</i\> \</small\>" ;
	  	      	  }
	  	      	 
      	  		 //td5.appendChild(document.createTextNode(estadodesc));
	  	      	  
	  	      	td5.innerHTML = estadodesc
	  	      	
	  	        var td6 = document.createElement('TD');	     
	  	      	  td6.appendChild(document.createTextNode(fechaSdb));
        	  	      	
        	  	      	tr.appendChild(td);
        	  	      	tr.appendChild(td2);
        	  	      	tr.appendChild(td3);
        	  	      	tr.appendChild(td4);
        	  	      	tr.appendChild(td5);
        	  	      	tr.appendChild(td6);
        	            	
        	            	//fin tabla dialog
        		        	       
        	}// fin for


        		//se agrega a legen los elementos creados
        	    //legend.innerHTML = div2.innerHTML;
        	
        	 legend.innerHTML = ""; 
        	   	legend.appendChild(table)
        	    
        	  
        	    
        	    //para quitar maker por estados
        	     var marker = "";
        	     z = 0;
        	    
        	     for (z in valor) {
        	     
        	    var marker = markers[z];
        	       
        	         var patente = valor[z].patente;
        	         var estado = valor[z].estado;
        	         var descestado = valor[z].descestado;
        	         
        	          console.log(patente);
        	         console.log(estado);
        	           console.log(descestado);
        	            console.log(marker);
        	    
        	       if(estado == 1){
        	        
        	         activoControlDiv.addEventListener('click',  (function(marker, z)  {
        	        return function() {
        		         if( marker.getMap() == null){ console.log("null active")
        		           marker.setMap(map);	            
        		         }else{ console.log(marker)
        		          marker.setMap(null);
        		         }    
        		          }
        		     }) (marker, z));
        		     }// fin if  
        		     
        	       if(estado == 2){
           	        
          	         retrasoControlDiv.addEventListener('click',  (function(marker, z)  {
          	        return function() {
          		         if( marker.getMap() == null){ console.log("null retraso")
          		           marker.setMap(map);	            
          		         }else{ console.log(marker)
          		          marker.setMap(null);
          		         }    
          		          }
          		     }) (marker, z));
          		     }// fin if  
          		     
          		     
        	       if(estado == 3){
           	        
          	         alertaControlDiv.addEventListener('click',  (function(marker, z)  {
          	        return function() {
          		         if( marker.getMap() == null){ console.log("null alerta")
          		           marker.setMap(map);	            
          		         }else{ console.log(marker)
          		          marker.setMap(null);
          		         }    
          		          }
          		     }) (marker, z));
          		     }// fin if  
        		     
        		} //fin for z
   
        	} // fin sussecs
        	}); //fin ajax

        	} //fin funcion
        
        
       
        	getCoords();
        	
        </script>
        
        


    
</body>

</html>