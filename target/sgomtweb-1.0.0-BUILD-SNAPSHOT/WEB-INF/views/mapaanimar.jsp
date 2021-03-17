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
        height: 100% !important;
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

<body onLoad="initialize();" style="resize:none;margin:0px;border:0px;">	
	
  <div id="map" style="width:100%; height:100%;margin:0px;border:0px;"></div>
  <div style="background-color: white; position: absolute; bottom: 55px; left:0px; width:140px; height: 30px; padding-top: 7px; font-family: Verdana, Geneva, sans-serif; font-size: 10px;">
  	<a href="http://maps.google.com?q=-22.75011,-69.29762" target="_blank">
  		-&gt; Ver en Google Maps
  		</a>
  	</div>
</body>



	    
    <%@include file="footerjs.jsp"%>
        
    
    <!-- d3 and c3 charts -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/d3/d3.min.js"></script>
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/c3/c3.min.js"></script>
    	
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/dualListbox/jquery.bootstrap-duallistbox.js"></script>
    
      <script src="https://maps.googleapis.com/maps/api/js?client=gme-samtech&channel=heatmaps&libraries=visualization"></script> 
    
    <!-- <script src="https://maps.googleapis.com/maps/api/js?client=gme-samtech&channel=heatmap&libraries=places"></script>  -->
    
    <!-- <script src="https://maps.googleapis.com/maps/api/js?client=gme-samtech&channel=Telemetria&callback=initMap" async defer></script>  -->  
    
   
    <script>
    
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
        

            
	     	//animacion
            <%@include file="utiljs/mapaAnimarJs.jsp"%>
                      
            <%--@include file="utiljs/UltimaPosicionVehiculoAjax.jsp"--%>
            //geoCercasJS
            <%--<%@include file="utiljs/geoCercasJS.jsp"%>  --%>           
            <%@include file="utiljs/geoCercasJSPG.jsp"%>     
			
            
        </script>
        
        


    
</body>

</html>