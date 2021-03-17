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
   <link href="resources/inspinia_v2.9/FullVersion/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
  
</head>
<body class="top-navigation">

	<div id="wrapper">
	
	
	<%@include file="menu.jsp"%>
	
		 <div id="page-wrapper" class="gray-bg">
		 
		 
       <div class="row border-bottom">
       </div>
        
            
               <!-- Titulo del Menu  -->
            <div class="row wrapper border-bottom white-bg page-heading">
                <div class="col-lg-10">
                    <h2></h2>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="">Gestión Flota</a>
                        </li>
                        
                        
                        <li class="breadcrumb-item active">
                            <strong>Cercos Virtuales</strong>
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
         
         <c:choose>
	    <c:when test="${rform.collapseshow == 'SI'}">
	    
      		<c:set var = "border" scope = "page" value = ""/>
      		<c:set var = "iboxContent" scope = "page" value = ""/>
      		<c:set var = "link" scope = "page" value = "fa-chevron-up"/>
      		<c:set var = "panelR" scope = "page" value = " "/>
      		<c:set var = "panelF" scope = "page" value = " in show "/>
      		<c:set var = "disa" scope = "page" value = ""/>
	    </c:when>    
	    <c:otherwise>
      		<c:set var = "border" scope = "page" value = "border-bottom"/>
      		<c:set var = "iboxContent" scope = "page" value = "display: none;"/>
      		<c:set var = "link" scope = "page" value = "fa-chevron-down"/>
      		<c:set var = "panelR" scope = "page" value = " in show "/>
      		<c:set var = "panelF" scope = "page" value = " "/>
      		<c:set var = "disa" scope = "page" value = "disabled"/>
	    </c:otherwise>
		</c:choose>
         
            <div class="row"> <!-- Bloque Formulario de busqueda --> 
        
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                      <div class="ibox-title">
                          <h5>Filtros <b>Informe de Geocercas</b> &nbsp;&nbsp; &nbsp;&nbsp; <small> Buscar por ... </small></h5>                           
                          <div class="ibox-tools">
                              <a class="collapse-link">
                                  <i class="fa fa-chevron-up" id="ocultar"></i>
                              </a>
                          </div>
                      </div>    
                      <form:form action="reportegeocercas.html" commandName="reporteGeocercasForm"  id="form_reporte"   name="form_reporte" autocomplete="off">                  
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
                                                <div class="col-lg-2  b-r">   
                                                      <div class="form-group">                                      
                                                              <label>Geocercas </label>
                                                              <div>
                                                               <select  name="geocercas" id="geocercas"  style="width:100%;" tabindex="4" data-placeholder="Seleccione una Geocerca" 
                                                               			class="form-control text-uppercase" required >
																					
														<option value=""></option>											
														<c:forEach items="${geocercas}" var="g" varStatus="count" >	  														
														   <c:if test="${not  empty g }">	
														      
															 <option value="${g.id_geo}" <c:if test="${g.id_geo == rform.geocercas}"> selected</c:if>>${g.nombre}</option>																	
																											
												 		  </c:if>	    
												      </c:forEach>	    																											
												</select>		
                                                              </div>                      
                                                      </div>
                                                    </div> 
                                                <div class="col-lg-2 ">   
                                                      <div class="form-group">                                      
                                                              <label>Faenas </label>
                                                              <div>
                                                              <select  name="faenas" id="faenas"  style="width:100%;" tabindex="4" data-placeholder="Seleccione una Faena" 
                                                              class="form-control text-uppercase">
														
															<option value=""></option>
															<c:forEach items="${listfaena}" var="f" varStatus="count" >	  														
																   <c:if test="${not  empty f }">													   
																  			<option value="${f.idGru}" <c:if test="${f.idGru == rform.faenas}"> selected</c:if>>${f.nombreGru}</option>														  											   									
														 		  </c:if>	    
														      </c:forEach>	    																											
															</select>		
                                                              </div>                      
                                                      </div>
                                                    </div>                                                       
                                                    <div class="col-lg-2  ">   
                                                      <div class="form-group">                                      
                                                              <label>Patentes </label>
                                                              <div>
                                                               <select  name="patentes" id="patentes"  style="width:100%;" tabindex="4" data-placeholder="Seleccione una Patente" 
                                                               		class="form-control text-uppercase">
                                                               		<option value=""></option>																	
																	<c:forEach items="${listpatentes}" var="p" varStatus="count" >																																	
																	   <c:if test="${not  empty p }">																	   															   
																	  		 <option value="${p.vehPatenteVehiculo}" <c:if test="${p.vehPatenteVehiculo == rform.patentes}"> selected</c:if>>${p.vehPatenteVehiculo}<c:if test="${not  empty p.nroInterno }"><p class="text-secondary"> - ${p.nroInterno}</p> </c:if></option>																	   														
															 		   </c:if>	    
														     		</c:forEach>	    																											
															</select>		
                                                              </div>                      
                                                      </div>
                                                    </div>
                                                    
                                                     <div class="col-lg-4  b-r">   
                                                      
                                                    </div>    
                                                    
                                                      <div class="col-lg-2 text-center">                                                                                                              
                                                              <button  type="button" class="ladda-button btn btn-primary btn_submit" id="toggleSpinners" data-style="contract" style="margin-top:20px;">Buscar</button>
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
                                                            <label class="control-label">&nbsp; Fecha Desde</label>&nbsp;&nbsp; &nbsp;&nbsp; 
                                                            <div class="input-group date">
                                                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" class="form-control" name="fechaDesde"  value="${rform.fechaDesde}" required>
                                                               
                                                               
                                                            </div>                                      
                                                        </div> 
                                                    </div>
                                                       <div class="col-lg-1">
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
                                                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" class="form-control" name="fechaHasta" value="${rform.fechaHasta}" required>
                                                            </div>                                      
                                                        </div> 
                                                    </div>
                                                      <div class="col-lg-1 ">
                                                        <div class="form-group">
                                                            <label class="control-label">&nbsp; Hora Hasta</label>&nbsp;&nbsp; &nbsp;&nbsp; 
                                                            <div class="input-group clockpicker" data-autoclose="true">
                                                                <input type="text" class="form-control" name="horaHasta" id="horaHasta" value="${rform.horaHasta}" >
                                                                <span class="input-group-addon"><span class="fa fa-clock-o"></span></span>
                                                            </div>                                       
                                                        </div> 
                                                    </div>
                                                   
                                               <div class="col-lg-2 ">
                                                        <div class="form-group">                                      
                                                              <label>Velocidad >= a </label>
                                                              <div>
                                                                  <select  style="width:100%;" tabindex="4" name="velocidad" id="velocidad" data-placeholder="Seleccione una Velocidad"  class="form-control text-uppercase">                                                                      
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
                                                    
                                                    <div class="col-lg-2 b-r">
                                                        <div class="form-group"><label>Ver Puntos</label>
                                                              <div>
                                                                  <select  style="width:100%;" tabindex="4" name="puntos" id="puntos" data-placeholder="Seleccione una Velocidad"  class="form-control text-uppercase">                                                   
                                                                      <option value="0"  <c:if test="${rform.puntos == '0'}"> selected</c:if> >TODOS</option>
                                                                      <option value="2"  <c:if test="${rform.puntos == '2'}"> selected</c:if> >SOLO ENTRADA</option>
                                                                      <option value="3"  <c:if test="${rform.puntos == '3'}"> selected</c:if> >SOLO SALIDA</option>
                                                                      <option value="1"  <c:if test="${rform.puntos == '1'}"> selected</c:if> >SOLO ENTRADA Y SALIDA</option>															                                                                       
                                                                  </select>
                                                              </div>  
						                                </div> 
                                                    </div>
                                                
                                                    
                                                     <div class="col-lg-2 text-center">                                                                                                              
                                                             <!--  <button class="btn btn-primary" type="submit"  style="margin-top:20px;">Filtraur</button>  -->
                                                              <button type="button" class="ladda-button btn btn-primary btn_submit" id="toggleSpinners2" data-style="contract" style="margin-top:20px;">Filtrar</button>                                                              
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
                            <h5>Reporte Informe de Geocercas</h5>
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
                    			
								<div class="table-responsive"> <!-- class="table table-striped table-bordered table-hover dataTables-example"> -->
									<table
										class="table table-striped table-bordered table-hover dataTables-example">
										<thead>
											<tr>
											<th>Patente</th>                                                                                       
                                            <th>N° Interno</th>
                                            <th>Conductor</th>
                                            <th>Faena</th>                                           
                                            <th>Fecha</th>
                                            <th>Velocidad Km/H</th>                                                   
                                            <th>Evento</th>
                                            <th>Orientacion</th>
                                            <th>Mapa</th>                                                                                        
											</tr>
										</thead>
									 <tbody>
					
				    <c:if test="${not  empty listReporGeo }">	    
					
						<c:forEach items="${listReporGeo}" var="r" varStatus="count" >							                                
		                                	<!-- <tr onClick="location.href='editarusuario.html?id=${r.patente}'">  -->
		                                	<tr>		                                        		                                    							                                      				                                      
		                                        <td>${r.patente}</td>
		                                        <td>${r.num_interno}</td>
		                                        <td>${r.chofer}</td>
		                                        <td>${r.faena} </td>
		                                        <td><fmt:formatDate value="${r.fecha_ini}" pattern="dd/MM/yyyy HH:mm:ss" /> </td>
		                                        <td>${r.velocidad} </td>
		                                        <td><font size="+1.5">${r.nombre_evento}</font></td>
		                                        <td><img src="resources/img/historico/${r.ruta_flecha}" width="16" height="16" title="${r.nom_flecha}"><span style="display:none">${r.nom_flecha}</span></td>		                                       						                        
		                                       <td title="Ver en Mapa"><font size="+1.5"><a style="color:#337ab7" class="link_mapa" link="mapaxpatentereporte.html?patente=${r.patente}&lat=${r.lat}&lon=${r.lon}"><i class="fa fa-external-link"></i></a></font></td>                                      		
		                                    </tr>
		           		</c:forEach>           
		            </c:if>		                                    	                         
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
    
    <!-- select 2 -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/select2/select2.full.min.js"></script>
            
    <!-- hora -->
      <script src="resources/inspinia_v2.9/FullVersion/js/plugins/clockpicker/clockpicker.js"></script>
      
        <!-- Chosen 
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/chosen/chosen.jquery.js"></script> -->
    
    
      <!-- funciones de validacion de hora, efecto load para botones de busqueda y carga de pagina -->
       <%@include file="utiljs/loadpagebuttonjs.jsp"%>
       <%@include file="utiljs/valclockpickerjs.jsp"%>
       
  <!-- Sweet alert -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/sweetalert/sweetalert.min.js"></script>
  <!-- iCheck -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/iCheck/icheck.min.js"></script>
        <script>
            $(document).ready(function () {
                $('.i-checks').iCheck({
                    checkboxClass: 'icheckbox_square-green',
                    radioClass: 'iradio_square-green',
                });
            });
        </script>
             
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
 

    $(document).off("change","#faenas").on("change","#faenas", function()
            { 
    			$('#patentes').prop('selectedIndex', 0); $('#patentes').select2();  
            });
    
    $(document).off("change","#patentes").on("change","#patentes", function()
            { 
    			$('#faenas').prop('selectedIndex', 0); $('#faenas').select2();
            });
       
    $(document).off("change","#geocercas").on("change","#geocercas", function()
            { 
    			 $('#faenas').prop('selectedIndex', 0); $('#faenas').select2();
    			 $('#patentes').prop('selectedIndex', 0); $('#patentes').select2(); 
               
            });

    var URLactual =window.location.protocol +"//"+ window.location.host;
    
    $(document).ready(function(){
    	
    	
	    	$(document).on("click", ".btn_submit", function(e){ 
	    		if($("#geocercas").val()==""){            	    
	    	    	swal("", "Debe seleccionar una Geocerca", "error"); $("#geocercas").focus();
	    	    	return  false;
	    	    }else if($("#faenas").val()=="" && $("#patentes").val()==""){            	    
	    	    	swal("", "Debe seleccionar una Faena o una Patente ", "error"); $("#faenas").focus();
	    	    	return  false;
	    	    }
	    	    else{
	    	    	$("#form_reporte").submit();
	    	    }
	    	});
    	
    	
    	   $( "#patentes" ).select2( {
    			placeholder: "Seleccione Patente",
    			 allowClear: true
    			
    		} );

    	    $( "#faenas" ).select2( {
    			placeholder: "Seleccione Faena",
    			 allowClear: true
    			
    		} );
    	    
    	    $( "#geocercas" ).select2( {
    			placeholder: "Seleccione Geocerca",
    			 allowClear: true
    			
    		} );
    	    
    	    
        $('.dataTables-example').DataTable({
        	"language": {
                "url": URLactual + "/sgomtweb/resources/datetableespanil.json"
            },
        	pageLength: 10,
            //"order": [[ 2, "asc" ]],
            //"order": true,
            responsive: true,
            dom: '<"html5buttons"xºB>lTfgitp',
            buttons: [
                {extend: 'copy'},
                {extend: 'csv'},
                {extend: 'excel',    
                	title: 'InformeDescarga',
                	 exportOptions: {
                         columns: [ 0, 1, 2, 3, 4 ,5 ]
                	 }
                	
                },
                
                {extend: 'pdf', 
                	title: 'InformeDescarga',                    	
               	 exportOptions: {
                        columns: [ 0, 1, 2, 3, 4 ,5  ]
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

    });
    
            
    $('form .input-group.date').datepicker({    	
        
    	language: 'es',
    	todayBtn: "linked",
        keyboardNavigation: false,
        forceParse: false,
        calendarWeeks: true,
        autoclose: true,
        format: "dd/mm/yyyy"
    });
    
    $('.clockpicker').clockpicker();
    
    $(document).on("click",".link_mapa",function(){
    	window.open($(this).attr('link'),"MAPA","top=100,left=300,width=800,height=650,scrollbars=NO,resizable=YES,class=lbOn" );
	});

    		

    </script>

 <!--  <script src="resources/inspinia_v2.9/FullVersion/js/plugins/datapicker/bootstrap-datepicker.ES.js" charset="UTF-8"></script>  -->  
    
</body>

</html>