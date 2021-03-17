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
                            <a href="">Cercos Virtuales</a>
                        </li>
                        
                        <li class="breadcrumb-item active">
                            <strong>${titulo} GeoCercos</strong>
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
       									<div class="alert alert-success">
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
                            <h5>${titulo} GeoCercos</h5>
                            <div class="ibox-tools">
                              <a class="collapse-link">
                                  <i class="fa ${link}"></i>
                              </a>
                          </div>                       
                       </div>    
               <form:form action="creageocercos.html" commandName="geocercasForm"  id="form_geo" enctype="multipart/form-data" autocomplete="off">                   
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
                                     <label class="control-label">&nbsp; Nombre de Geocerca</label>&nbsp;&nbsp; &nbsp;&nbsp; 
                                     <input type="text" class="form-control" name="nombre"  id="nombre"  value="${mlistGeoId.nombre}" required>                                                                                                                                                          
                                 </div> 
	                             </div>                                                                                             
	                             <div class="col-lg-2">
	                                 <div class="form-group">
	                                     <label class="control-label">&nbsp; Color </label>&nbsp;&nbsp; &nbsp;&nbsp; 
	                                     <div>                                                                
	                                         <select  name="color" id="color"  style="width:100%;" tabindex="4"  class="form-control text-uppercase" required>														
									<c:forEach items="${color}" var="cor" varStatus="count" >
									<option value="${cor.id_color}" color="${cor.codigo}"  <c:if test="${cor.codigo == mlistGeoId.codigo_color}"> selected</c:if>> ${cor.nombre} </option>									
									</c:forEach>		    																											
									</select>
	                                      	</div>                                     
	                                   </div> 
	                               </div> 
	                               <div class="col-lg-6  b-r">   
                                                      
                                   </div>
                                     <div class="col-lg-2 text-center">                                             
                                             <button class="ladda-button btn btn-primary btn_guardar" id="toggleSpinners2" data-style="contract" style="margin-top:20px;">Guardar</button>
                                     </div>
	                                                                                   
	                               <input type="hidden" id="geomText" name="geomText" value="${mlistGeoId.geomText}" />
	                               <input type="hidden" id="origen" name="origen" value="${origen}" />
	                               <input type="hidden" id="id_geo" name="id_geo" value="${mlistGeoId.id_geo}" />
	                                                                                 
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
						                                       <label class="control-label">&nbsp; Tipo Uso</label>&nbsp;&nbsp; &nbsp;&nbsp; 
						                                        <select  name="uso" id="uso"  style="width:100%;" tabindex="4"  class="form-control text-uppercase" required>
						                                        <option value="i" <c:if test="${'i' == mlistGeoId.uso}"> selected</c:if>>No Aplica</option>														
																<option value="c" <c:if test="${'c' == mlistGeoId.uso}"> selected</c:if>>Carga</option>
																<option value="d" <c:if test="${'d' == mlistGeoId.uso}"> selected</c:if>>Descarga</option>		    																											
																</select>                                   
						                                   </div> 
						                               </div> 
						                               <div class="col-lg-2">
						                                   <div class="form-group">
						                                       <label class="control-label">&nbsp; Vigencia</label>&nbsp;&nbsp; &nbsp;&nbsp; 
						                                       <select  name="vigencia" id="vigencia"  style="width:100%;" tabindex="4"  class="form-control text-uppercase" required>														
																<option value="1" <c:if test="${'1' == mlistGeoId.vigencia}"> selected</c:if>>SI</option>
																<option value="0" <c:if test="${'0' == mlistGeoId.vigencia}"> selected</c:if>>NO</option>		    																											
																</select>                                      
						                                   </div> 
						                               </div> 
						                               <div class="col-lg-2">
						                                   <div class="form-group">
						                                       <label class="control-label">&nbsp; Visible</label>&nbsp;&nbsp; &nbsp;&nbsp; 
						                                       <select  name="visible" id="visible"  style="width:100%;" tabindex="4"  class="form-control text-uppercase" required>														
																<option value="1" <c:if test="${'1' == mlistGeoId.visible}"> selected</c:if>>SI</option>
																<option value="0" <c:if test="${'0' == mlistGeoId.visible}"> selected</c:if>>NO</option>		    																											
																</select>                                    
						                                   </div> 
						                               </div> 
						                               <div class="col-lg-2">
						                                   <div class="form-group">
						                                       <label class="control-label">&nbsp; Georeferencia</label>&nbsp;&nbsp; &nbsp;&nbsp; 
						                                       <select  name="georeferencia" id="georeferencia"  style="width:100%;" tabindex="4"  class="form-control text-uppercase" required>														
																<option value="1" <c:if test="${'1' == mlistGeoId.georeferencia}"> selected</c:if>>SI</option>
																<option value="0" <c:if test="${'0' == mlistGeoId.georeferencia}"> selected</c:if>>NO</option>		    																											
																</select>                                   
						                                   </div> 
						                               </div> 
						                               <div class="col-lg-2">
						                                   <div class="form-group">
						                                       <label class="control-label">&nbsp; Aplicacion</label>&nbsp;&nbsp; &nbsp;&nbsp; 
						                                       <select  name="aplicacion" id="aplicacion"  style="width:100%;" tabindex="4"  class="form-control text-uppercase" required>																
																<option value="0" <c:if test="${'0' == mlistGeoId.aplicacion}"> selected</c:if>>NO</option>
																<option value="1" <c:if test="${'1' == mlistGeoId.aplicacion}"> selected</c:if>>SI</option>		    																											
																</select>                                      
						                                   </div> 
						                               </div> 
						                               <div class="col-lg-2">
						                                   <div class="form-group">
						                                       <label class="control-label">&nbsp; Fuera Operacion</label>&nbsp;&nbsp; &nbsp;&nbsp; 
						                                       <select  name="fuera" id="fuera"  style="width:100%;" tabindex="4"  class="form-control text-uppercase" required>														
																<option value="0" <c:if test="${'0' == mlistGeoId.fuera}"> selected</c:if>>Dentro Geocerca</option>														
																<option value="2" <c:if test="${'2' == mlistGeoId.fuera}"> selected</c:if>>Fuera Geocerca</option>
																<option value="1" <c:if test="${'1' == mlistGeoId.fuera}"> selected</c:if>>Taller</option>		    																											
																</select>                                    
						                                   </div> 
						                               </div>                                          	
                                            	 </div>
                                            	<div class="row">                                            	
	                                            	<div class="col-lg-2">
						                                   <div class="form-group">
						                                       <label class="control-label">&nbsp; Latitud</label>&nbsp;&nbsp; &nbsp;&nbsp; 
						                                       <input type="text" class="form-control" name="txtLat0" id="txtLat0"  value="" readonly>                                   
						                                   </div> 
						                               </div> 
						                               <div class="col-lg-2">
						                                   <div class="form-group">
						                                       <label class="control-label">&nbsp; Longitud</label>&nbsp;&nbsp; &nbsp;&nbsp; 
						                                       <input type="text" class="form-control" name="txtLon0" id="txtLon0"  value="" readonly>                                      
						                                   </div> 
						                               </div> 
						                               <div class="col-lg-2">
						                                   <div class="form-group">
						                                       <label class="control-label">&nbsp; Area Mts²</label>&nbsp;&nbsp; &nbsp;&nbsp; 
						                                       <input type="text" class="form-control" name="txtArea" id="txtArea" value="" readonly>                                    
						                                   </div> 
						                               </div> 
						                             
					        <%//<c:if test="${origen eq 'crear'}"> %>        
											<div class="col-lg-4 b-r  "> 
											
											                      <label class="control-label">KML</label>
    														<input type="file" class="form-control-file" id="filed" name="file">                                                                                                                                           
	
                                   								</div>
                                   
                                     <!-- <div class="col-lg-2  b-r">                                    
                                                                                                                        
                                     </div>  -->
                                  
                             <%//</c:if>   %> 
                               	
					                                   <div class="col-lg-2 text-center">                                             
					                                        <button class="ladda-button btn btn-primary btn_guardar" id="toggleSpinners2" data-style="contract" name ="guardar2" style="margin-top:20px;">Guardar</button>
					                                   </div> 
					                                   <div class="col-lg-2">
						                                   <div class="form-group">
						                                       <label class="control-label">&nbsp;Velocidad de alarma</label>&nbsp;&nbsp; &nbsp;&nbsp; 
						                                       <input type="number" class="form-control" name="velocidadAlarma"  disabled="disabled" id="velocidadAlarma" value="${mlistGeoId.velocidadAlarma}" >                                    
						                                   </div> 
						                               </div> 
						                               <div class="col-lg-2">
						                                   <div class="form-group">
						                                       <label class="control-label">&nbsp; Estado de alarma</label>&nbsp;&nbsp; &nbsp;&nbsp; 
						                                       <select  name="estadoAlarma" id="estadoAlarma"  style="width:100%;" tabindex="4"  class="form-control text-uppercase" required>														
																<option value="0" <c:if test="${mlistGeoId.estadoAlarma==0}"> selected </c:if> >Inactivo</option>
																<option value="1" <c:if test="${mlistGeoId.estadoAlarma==1}"> selected </c:if>>Activo</option>		    																											
																</select>                                      
						                                   </div> 
						                               </div> 
						                               <div class="col-lg-2">
						                                   <div class="form-group">
						                                       <label class="control-label">&nbsp;Correo</label>&nbsp;&nbsp; &nbsp;&nbsp; 
						                                       <input type="email" class="form-control" name="correo"   id="correo" value="${mlistGeoId.correo}" >                                    
						                                   </div> 
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
                        	
                        	<c:if test="${origen ne 'crear'}">        
	
                        		  <a href="downloadgeoKML.kml?id=${mlistGeoId.id_geo}"> 
                                 		
                                 		<button class="btn btn-success btn_kml" title="Exportar a KML la Geo" type="button"  style="margin-top:20px;margin-right:13px;"value="Exportar"> <i class="fa fa-file-code-o"></i>&nbsp;&nbsp;Exportar</button> <!--  btn-outline -->
                                  </a>    
                                  
                             </c:if>   
                                 	                                                                    
                                 <a href="listadogeocercas.html">
                                 		<input type="button"  class="ladda-button btn btn-default btn_cancel" id="toggleSpinners2" data-style="contract" style="margin-top:20px;" value="Cancelar">
                                 	</a>                                 		                                                                                               
                                     </span>     
                                </div>                  	
                    	</div>
                    	
                    	
						<div class="hr-line-dashed"></div>  
                    		<div class="row">
                                <div id="map" readonly></div>
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
        		
            	if(${mlistGeoId.estadoAlarma == 1} ){
            		$("#velocidadAlarma").prop("disabled", false);	
            	}else{
   		    		$("#velocidadAlarma").prop("disabled", true); 
            	}
            	
            	$(document).on('change', '#filed',function(event) {                  
            		console.log("filed change")
            		 $('.btn_guardar').html("SUBIR KML"); 
            	    document.getElementById('map').style.pointerEvents = 'none';

            		
    			});
            	
            	/*$(document).on('blur', '#velocidadAlarma',function(event) {                  
            		if($("#velocidadAlarma").val() <= 10){            	    
            	    	swal("", "Velocidad debe ser mayor a 10", "error");
            	    	$("#velocidadAlarma").val("");
            	    }	
    			});
            	*/
            	$(document).on('change', '#estadoAlarma',function(event) {                  
            		$( "#estadoAlarma option:selected" ).each(function() {
            		     var option = $( this ).text();
            		     if(option == 'Activo'){
            		    	 $("#velocidadAlarma").prop("disabled", false);  
            		     }else{
            		    	$("#velocidadAlarma").prop("disabled", true);
                 	    	$("#velocidadAlarma").val("");
            		     }
            		});  	            		
            	});
            	
            	$(document).on('change', '#color',function(event) {                  
            		GeoPoligonal.setOptions({
                        strokeColor: $("option:selected", this).attr("color"),
                        fillColor:$("option:selected", this).attr("color")
                    });		            		
    		});
            	
            	$(document).on("click", ".btn_guardar", function(e){ 
					
            		if($("#estadoAlarma").val() == 1){
            			if($("#velocidadAlarma").val() != ""){
            				if($("#velocidadAlarma").val() < 10	){  
            					swal("", "Velocidad debe ser mayor a 10", "error");
                    	    	return false;	
            				}	
            			}else{
            				swal("", "Debe ingresar un valor.", "error");	
            				return false;
            			}
            		}
            		
            		if($("#nombre").val()==""){            	    
            	    	swal("", "Debe ingresar un nombre de geocerca.", "error"); $("#nombre").focus();
            	    	return  false;
            	    }else if($("#geomText").val()==""){    
            	    	
            	    	if($("#filed").val()==""){   
            	    	
            	    	swal("", "Debe crear una geocerca.", "error");
            	    	return  false;
            	    	
            	    	}else {
            	    		//$("#form_geo").submit();
            	    		
            	    	}
            	    }else if($("#filed").val()==""){ 
            	    	
            	    	if($("#geomText").val()==""){   
                	    	
            	    		swal("", "Debe Cargar KML.", "error");
                	    	return  false;
                	    	
                	    	}else {
                	    		//$("#form_geo").submit();
                	    		
                	    	}
            	    }
            	    else{
            	    	//$("#form_geo").submit();
            	    }
            	});
            	
            });
            
            
        
        //geoCercasJS
          <%@include file="utiljs/geoCercasJSPG.jsp"%>  	
        
          //Marcas de Terreno
          <c:forEach items="${marcas_terreno}" var="mf" varStatus="count" >	
			var image = {
		          url: 'resources/img/${mf.nombre_icono}',
		          size: new google.maps.Size(20, 32),
		          scaledSize: new google.maps.Size(20, 32),
		          origin: new google.maps.Point(0, 0),          
		          anchor: new google.maps.Point(10, 30)
		        };	
		
			
			  var marker = new google.maps.Marker({
			    position: {lat: ${mf.lat}, lng: ${mf.lon}},
			    map: map,
			    title: '${mf.nombre}',
			    icon: image	    
			  });
			  
			</c:forEach>
          
          if('${mlistGeoId.nombre}'!=''){    
          //editarPoligonoJS
            <%@include file="utiljs/editaPoligonoJS.jsp"%>     
          }else{
        	//creaPoligonoJS
              <%@include file="utiljs/crearPoligonoJS.jsp"%>        	  
          }
            
          
          centrar_todo(allGeo); 

            
        </script>
        
        


    
</body>

</html>