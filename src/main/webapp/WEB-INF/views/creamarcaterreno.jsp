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
<!-- <meta http-equiv="refresh" content="30" />  -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SAMTECH | SGOMT</title>

<%@include file="style.jsp"%> 
 <!-- Sweet Alert -->
    <link href="resources/inspinia_v2.9/FullVersion/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
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
		 
	  <c:choose>
			    <c:when test="${origen == 'crear'}">
			     		<c:set var = "titulo" scope = "page" value = "Crear "/>
			    </c:when>    
			    <c:otherwise>
			     		<c:set var = "titulo" scope = "page" value = "Editar "/>
			    </c:otherwise>
		 	</c:choose>	   
		 	
		 		 
       <div class="row border-bottom">
       </div>
                          
              <!-- Titulo del Menu  -->
            <div class="row wrapper border-bottom white-bg page-heading">
                <div class="col-lg-10">
                    <h2></h2>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="">Administracion</a>
                        </li>
                        
                         <li class="breadcrumb-item ">
                            <a href="">Marcas de Terreno</a>
                        </li>
                        
                        <li class="breadcrumb-item active">
                            <strong>${titulo} Marcas de Terreno</strong>
                        </li>

                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>
        
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
	    
	      		<c:set var = "border" scope = "page" value = ""/>
	      		<c:set var = "iboxContent" scope = "page" value = ""/>
	      		<c:set var = "link" scope = "page" value = "fa-chevron-up"/>
	      		<c:set var = "panelR" scope = "page" value = " "/>
	      		<c:set var = "panelF" scope = "page" value = " in show "/>      		
      		
		     
                 
			<div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins ${border}" id="ibox1">
                         <div class="ibox-title">
                            <h5>${titulo} Marcas de Terreno</h5>
                            <div class="ibox-tools">
                              <a class="collapse-link">
                                  <i class="fa ${link}"></i>
                              </a>
                          </div>                       
                       </div>    
               <form:form action="creamarcaterreno.html" commandName="marcaterrenoForm"  id="form_marcas" autocomplete="off">                   
                        <div class="ibox-content" style="${iboxContent}">
	                         <div class="sk-spinner sk-spinner-three-bounce">                                
	                                 <div class="sk-bounce1"></div>
	                                    <div class="sk-bounce2"></div>
	                                    <div class="sk-bounce3"></div>                                                                    
	                   		 </div>                    	 
							<div class="panel-group" id="accordion">
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h5 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">Creacion Rapida</a>
                                            </h5>
                                        </div>
<!--  <div id="collapseOne" class="panel-collapse collapse in show">  -->
                <div id="collapseOne" class="panel-collapse collapse in show <c:if test="${rform.collapseshow == 'NO'}"> in show</c:if>"> 
                     <div class="panel-body">
                    		<div class="row">                               
                             	<div class="col-lg-2">
                                 <div class="form-group">
                                     <label class="control-label">&nbsp; Nombre de Marca</label>&nbsp;&nbsp; &nbsp;&nbsp; 
                                     <input type="text" class="form-control" name="nombre"  id="nombre"  value="${marcasxid.nombre}" required>                                                                                                                                                          
                                 </div> 
	                             </div>                                                                                             
	                             <div class="col-lg-2">
	                                   <div class="form-group">
	                                       <label class="control-label">&nbsp; Latitud</label>&nbsp;&nbsp; &nbsp;&nbsp; 
	                                       <input type="text" class="form-control" name="txtLat0" id="txtLat0"  value="${marcasxid.lat}" readonly>                                   
	                                   </div> 
	                               </div> 
	                               <div class="col-lg-2">
	                                   <div class="form-group">
	                                       <label class="control-label">&nbsp; Longitud</label>&nbsp;&nbsp; &nbsp;&nbsp; 
	                                       <input type="text" class="form-control" name="txtLon0" id="txtLon0"  value="${marcasxid.lon}" readonly>                                      
	                                   </div> 
	                               </div>  
	                               <div class="col-lg-4  b-r">   
                                                      
                                   </div>
                                     <div class="col-lg-2 text-center">                                             
                                             <button class="ladda-button btn btn-primary btn_guardar" id="toggleSpinners2" data-style="contract" style="margin-top:20px;">Guardar</button>
                                     </div>
	                                                                                   
	                               <input type="hidden" id="origen" name="origen" value="${origen}" />
	                               <input type="hidden" id="id_marca" name="id_marca" value="${marcasxid.id_marca}" />
	                                                                                 
		                             </div> 
		                         </div>
		                      </div>
                       	</div>
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">Creacion Avanzada</a>
                                            </h4>
                                        </div>
                                        <div id="collapseTwo" class="panel-collapse collapse <c:if test="${rform.collapseshow == 'SI'}"> in show</c:if>">
                                            <div class="panel-body">
                                            <div class="row">                                            	
	                                            	<div class="col-lg-2">
					                                 <div class="form-group">
					                                     <label class="control-label">&nbsp; Descripcion</label>&nbsp;&nbsp; &nbsp;&nbsp; 
					                                     <input type="text" class="form-control" name="descripcion"  id="descripcion"  value="${marcasxid.descripcion}" required>                                                                                                                                                          
					                                 </div> 
						                             </div>
						                             <div class="col-lg-2">
						                                   <div class="form-group">
						                                       <label class="control-label">&nbsp; Icono</label>&nbsp;&nbsp; &nbsp;&nbsp; 
						                                       <select  name="icono" id="icono"  style="width:100%;" tabindex="4"  class="form-control text-uppercase" required>														
																	<c:forEach items="${lIconos}" var="ico" varStatus="count" >
																	<option value="${ico.id_icono}" <c:if test="${ico.id_icono == marcasxid.id_icono}"> selected</c:if>> ${ico.nombre}</option>									
																	</c:forEach>																											
																</select>                                    
						                                   </div> 
						                               </div>
						                               <div class="col-lg-2">
						                                   <div class="form-group">
						                                       <label class="control-label">&nbsp; Vigencia</label>&nbsp;&nbsp; &nbsp;&nbsp; 
						                                       <select  name="vigencia" id="vigencia"  style="width:100%;" tabindex="4"  class="form-control text-uppercase" required>														
																<option value="1" <c:if test="${'1' == marcasxid.vigencia}"> selected</c:if>>SI</option>
																<option value="0" <c:if test="${'0' == marcasxid.vigencia}"> selected</c:if>>NO</option>		    																											
																</select>                                      
						                                   </div> 
						                               </div> 						                                
						                               <div class="col-lg-2">
						                                   <div class="form-group">
						                                       <label class="control-label">&nbsp; Muestra en Mapa</label>&nbsp;&nbsp; &nbsp;&nbsp; 
						                                       <select  name="muestra" id="muestra"  style="width:100%;" tabindex="4"  class="form-control text-uppercase" required>														
																<option value="1" <c:if test="${'1' == marcasxid.muestra}"> selected</c:if>>SI</option>
																<option value="0" <c:if test="${'0' == marcasxid.muestra}"> selected</c:if>>NO</option>		    																											
																</select>                                   
						                                   </div> 
						                               </div> 
						                               <div class="col-lg-2 b-r">
						                                   <div class="form-group">
						                                       <label class="control-label">&nbsp; Valido para Calculos</label>&nbsp;&nbsp; &nbsp;&nbsp; 
						                                       <select  name="valido" id=valido  style="width:100%;" tabindex="4"  class="form-control text-uppercase" required>																
																<option value="1" <c:if test="${'1' == marcasxid.valido}"> selected</c:if>>SI</option>
																<option value="0" <c:if test="${'0' == marcasxid.valido}"> selected</c:if>>NO</option>		    																											
																</select>                                      
						                                   </div> 
						                               </div>		                                           	 
					                                   <div class="col-lg-2 text-center">                                             
					                                        <button class="ladda-button btn btn-primary btn_guardar" id="toggleSpinners2" data-style="contract" style="margin-top:20px;">Guardar</button>
					                                   </div>                                          	
                                            	 </div>
                                            </div>
                                        </div>
                                    </div>   
                                  </div>
                               
                              </form:form>                   
                        <div class="row">                        
                        	<div class="col-lg-12">
                        	<span class="float-right">
                        	<!-- 
                        		  <a href="downloadgeoKML.kml?id=${marcasxid.id_marca}"> 
                                 		<button class="btn btn-success btn_kml" title="Exportar a KML la Geo" type="button" style="margin-top:20px;margin-right:13px;"value="Exportar"> <i class="fa fa-file-code-o"></i>&nbsp;&nbsp;Exportar</button>
                                  </a>      
                                  -->	
                                                                    
                                 <a href="listadomarcaterreno.html">
                                 		<input type="button"  class="ladda-button btn btn-default btn_cancel" id="toggleSpinners2" data-style="contract" style="margin-top:20px;" value="Cancelar">
                                 	</a>                                 		                                                                                               
                                     </span>     
                                </div>                  	
                    	</div>
                    	
                    	
						<div class="hr-line-dashed"></div>  
                    	<div class="row">
                                <div id="map"></div>
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
        
    
    <!-- d3 and c3 charts -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/d3/d3.min.js"></script>
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/c3/c3.min.js"></script>
    	
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/dualListbox/jquery.bootstrap-duallistbox.js"></script>

    <script src="https://maps.googleapis.com/maps/api/js?client=gme-samtech&channel=heatmaps&libraries=visualization,drawing,geometry"></script> 
            
     <!-- Sweet alert -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/sweetalert/sweetalert.min.js"></script>
    
   
    <script>
 
    var heatmap;  
    var mapTypeIds = [];
    var marker = null;
    var markers = [];
    var markers_center = [];
    var posicion = new google.maps.LatLng(${mc.lat}, ${mc.lon});
    
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
			    fullscreenControl: true,
				fullscreenControlOptions: {
			        position: google.maps.ControlPosition.LEFT_CENTER
			    },
				rotateControl : true,
				rotateControlOptions: {
			        position: google.maps.ControlPosition.LEFT_CENTER
			    },
			  });
        

        var colorGeo = $("option:selected", "#color").attr("color");
            if (!colorGeo) {
                 colorGeo = "#66FF00";

            }	

            marker = new google.maps.Marker({
                map: map,
                position: posicion,
                title: 'Centro de la GeoCerca',
                draggable: true,
                icon: '//gps.samtech.cl/img/blank.png',
                zIndex: 20
            });
            var latlngbounds = new google.maps.LatLngBounds();
            latlngbounds.extend(posicion);
            map.setCenter(latlngbounds.getCenter());
            
            

            $(document).ready(function () {
            	
            	$(document).on('change', '#color',function(event) {                  
		            		GeoPoligonal.setOptions({
		                        strokeColor: $("option:selected", this).attr("color"),
		                        fillColor:$("option:selected", this).attr("color")
		                    });		            		
            		});
            	
            	$(document).on("click", ".btn_guardar", function(e){ 
            		if($("#nombre").val()==""){            	    
            	    	swal("", "Debe ingresar un nombre de marca.", "error"); $("#nombre").focus();
            	    	return  false;
            	    }else if($("#txtLat0").val()==""){            	    
            	    	swal("", "Debe mover el marcador de mapa.", "error"); 
            	    	return  false;
            	    }
            	    else{
            	    	$("#form_marcas").submit();
            	    }
            	});
            	
            });
            
        
        //geoCercasJS
          <%@include file="utiljs/geoCercasJSPG.jsp"%>  
        
        //MarcasdeTerreno
          <%@include file="utiljs/marcaTerrenoJSPG.jsp"%>
          
          if('${marcasxid.nombre}'!=''){    
          //editaMarcaJS
           <%@include file="utiljs/editaMarcaJS.jsp"%>     
          }else{
        	//crearMarcaJS
              <%@include file="utiljs/crearMarcaJS.jsp"%>        	  
          }
            
          centrar_todo(allGeo); 
            
        </script>
        
        


    
</body>

</html>