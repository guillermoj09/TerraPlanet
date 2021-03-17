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
  
  <style type="text/css">
  
  #thide  {
  width:0%;
  position: absolute;
  
  </style>
  
   <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/css/bootstrap-select.min.css" />
  
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
                          <h5>Filtros <b>${b.menu}</b> &nbsp;&nbsp; &nbsp;&nbsp; <small> Buscar por ... </small></h5>                           
                          <div class="ibox-tools">
                              <a class="collapse-link">
                                  <i class="fa fa-chevron-up" id="ocultar"></i>
                              </a>
                          </div>
                      </div>    
                      <form:form action="detallexviaje.html" commandName="reportePatenteFechaForm"  id="data_1" autocomplete="off">                  
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
                                                <div class="col-lg-2 ">   
                                                      <div class="form-group ">                                      
                                                              <label>Patentes </label>
                                                              <div>
                                                                
                                                                <select class="chosen-select"  name="patente" id="patente"  style="width:100%;" tabindex="4" data-placeholder="Seleccione una Patente" class="form-control text-uppercase" required>
																					
														<option value=""></option>	
														
														<c:if test="${not  empty vehiculoDevice }">
														 <!-- <option value="${vehiculoDevice.devIdDevice}" selected>${vehiculoDevice.vehPatenteVehiculo}</option>  -->  
														 </c:if>	  
																											
														<c:forEach items="${listpatentes}" var="p" varStatus="count" >	  
														
														   <c:if test="${not  empty p }">	
														   
														   <%--<c:if test="${p.fnGetdevice ne rform.patente}">  --%>
														        
																	<!-- fnGetdevice alguno procedimiento recibe el id y no patente -->													
															<!-- <option value="${p.fnGetdevice}">${p.vehPatenteVehiculo}</option>  -->
															<option value="${p.fnGetdevice}" <c:if test="${p.fnGetdevice == rform.patente}"> selected</c:if>>${p.vehPatenteVehiculo}<c:if test="${not  empty p.nroInterno }"><p class="text-secondary"> - ${p.nroInterno}</p> </c:if></option>
															
															<%--</c:if>--%>
															
												
												 		  </c:if>	    
												      </c:forEach>	    																											
															</select>	
                                                              </div>                      
                                                      </div>
                                                    </div> 
                                                    
                                                    
                                                   
                                                   <div class="col-lg-8 b-r">
                                                   </div> 
                                                    
                                                      <div class="col-lg-2 text-center">                                                                                                              
                                                              <button class="ladda-button btn btn-primary btn_submit" id="toggleSpinners" data-style="contract" type="submit" style="margin-top:20px;">Buscar</button>
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
                                                   
                                                <div class="col-lg-2">
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
                                                      
                                                       <label>Seleccione </label>
                                                              <div>
                                                                  <div class="radio radio-info radio-inline">
                                                                      <input type="radio" id="inlineRadio1" value="1" name="sw1" id="sw1" <c:if test="${rform.sw1 == '1'}"> checked </c:if> required>
                                                                      <label for="inlineRadio1"> Todos </label>
                                                                  </div>
                                                                  <div class="radio radio-info  radio-inline">
                                                                      <input type="radio" id="inlineRadio2" value="0"  name="sw1" id="sw1" <c:if test="${rform.sw1 == '0'}"> checked </c:if> required>
                                                                      <label for="inlineRadio2">Combustible > 0 </label>
                                                                  </div>
                                                              </div>                                     
                                                                                                                                                                                             
                                                                                                                                                                                              
                                                    </div>
                                                   </div>  
                                                                                                                                                              
                                                     <div class="col-lg-2 text-center">                                                                                                              
                                                             <button class="ladda-button btn btn-primary btn_submit" id="toggleSpinners2" data-style="contract" type="submit" style="margin-top:20px;">Filtrar</button>
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
            
            <!--  bloque promedios totales -->   
            <div class="row">
				<div class="col-xs-12 col-md-6 col-lg-6 col-xl-2">
					 <div class="ibox ">
                    <div class="ibox-title">
                    	 <span class="label label-success pull-right">Total </span> 
						<i class="icon-chart"></i>
						<h5 class="text-muted text-uppercase m-b-20">Viajes</h5>
						<div class="m-b-20">	
						<h2>
							<span data-plugin="counterup" id="viajes"></span>							
							
						</h2>	
																							
						</div>
						
						<small>&nbsp;</small>

						
					</div>	
					</div>
				</div>


				<div class="col-xs-12 col-md-6 col-lg-6 col-xl-2">
					
					 <div class="ibox ">
                    <div class="ibox-title">
                    	 <span class="label label-primary  pull-right">Promedio </span> 
						<i class="icon-chart"></i>
						<h4 class="text-muted text-uppercase m-b-20">RND</h4>
						<div class="m-b-20">	
						<h2>
							<span data-plugin="counterup" id="rendinkm"></span>														
						</h2>																								
						</div>
						
							<small>Rendimiento (Km/Lt)</small>
					</div>	
					</div>
				</div>

				

			<div class="col-xs-12 col-md-6 col-lg-6 col-xl-2">
					
					 <div class="ibox ">
                    <div class="ibox-title">
                    	 <span class="label label-primary  pull-right">Promedio </span> 
						<i class="icon-chart"></i>
						<h4 class="text-muted text-uppercase m-b-20">RND</h4>
						<div class="m-b-20">	
						<h2>
							<span data-plugin="counterup" id="rendinlt"></span>														
						</h2>																								
						</div>
						
							<small>Rendimiento (Lt/Hr)</small>
					</div>	
					</div>
				</div>

			<div class="col-xs-12 col-md-6 col-lg-6 col-xl-2">
					
					 <div class="ibox ">
                    <div class="ibox-title">
                    	 <span class="label label-success  pull-right">Total </span> 
						<i class="icon-chart"></i>
						<h4 class="text-muted text-uppercase m-b-20">DST</h4>
						<div class="m-b-20">	
						<h2>
							<span data-plugin="counterup" id="distancia"></span>														
						</h2>																								
						</div>
						
							<small>Distancia (Km)</small>
					</div>	
					</div>
				</div>
				
			<div class="col-xs-12 col-md-6 col-lg-6 col-xl-2">
					
					 <div class="ibox ">
                    <div class="ibox-title">
                    	 <span class="label label-success  pull-right">Total </span> 
						<i class="icon-chart"></i>
						<h4 class="text-muted text-uppercase m-b-20">CMB</h4>
						<div class="m-b-20">	
						<h2>
							<span data-plugin="counterup" id="combustible"></span>														
						</h2>																								
						</div>
						
							<small>Combustible (Lts)</small>
					</div>	
					</div>
				</div>

				<div class="col-xs-12 col-md-6 col-lg-6 col-xl-2">
					
					 <div class="ibox ">
                    <div class="ibox-title">
                    	 <span class="label label-success  pull-right">Total </span> 
						<i class="icon-chart"></i>
						<h4 class="text-muted text-uppercase m-b-20">TPO</h4>
						<div class="m-b-20">	
						<h2>
							<span data-plugin="counterup" id="tiempo"></span>														
						</h2>																								
						</div>
						
							<small>Tiempo (Hr)</small>
					</div>	
					</div>
				</div>



			</div> <!-- end row bloque promedios y totales -->
          
         
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins  ${border}" id="ibox1">
                        <div class="ibox-title">
                            <h5>Reporte ${b.menu}</h5>
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
								<div class="table-responsive">
									<table
										class="table table-striped table-bordered table-hover dataTables-example">
										<thead>
											<tr>
											<th>Patente</th>
											<th>N.Int</th>
											<th>Conductor</th>
											<th data-toggle="tooltip" data-placement="top" title="Fecha Inicial Viaje">Inicio</th>
                                            <th data-toggle="tooltip" data-placement="top" title="Fecha Fin Viaje">Fin</th>
                                            <th data-toggle="tooltip" data-placement="top" title="Rendimiento (Km/Lt)">RDM (Km/Lt)</th>
                                            <th data-toggle="tooltip" data-placement="top" title="Rendimiento (Lt/Hr)">RDM (Lt/Hr)</th>   
                                            <th data-toggle="tooltip" data-placement="top" title="Combustible Total">CMB Total</th>                                            
                                            <th data-toggle="tooltip" data-placement="top" title="Combustible Marcha">CMB Marcha</th>
                                            <th data-toggle="tooltip" data-placement="top" title="Combustible Ralenti">CMB Ralenti</th>
                                            <th data-toggle="tooltip" data-placement="top" title="Combustible Crucero">CMB Crucero</th>
                                            <th data-toggle="tooltip" data-placement="top" title="Combustible PTO">CMB PTO</th>
                                             
                                             <th data-toggle="tooltip" data-placement="top" title="Combustible PTO">Distancia Total</th>    
                                             <th data-toggle="tooltip" data-placement="top" title="Tiempo Total">TPO Total</th>                                            
                                            <th data-toggle="tooltip" data-placement="top" title="Tiempo Marcha">TPO Marcha</th>   
                                             <th data-toggle="tooltip" data-placement="top" title="Tiempo Ralenti">TPO Ralenti</th>                                                                                                                                                                  
                                             <th data-toggle="tooltip" data-placement="top" title="Tiempo Veleo">TPO Veleo</th>
                                             <th data-toggle="tooltip" data-placement="top" title="Tiempo Crucero">TPO Crucero</th>
                                             <th data-toggle="tooltip" data-placement="top" title="Tiempo PTO">TPO PTO</th>
                                            
                                            <th data-toggle="tooltip" data-placement="top" title="Velocidad MAXIMA">Vel MAX</th>
                                            <th data-toggle="tooltip" data-placement="top" title="RPM MAXIMA">RPM MAX</th>
                                            <th data-toggle="tooltip" data-placement="top" title="Nivel Estanque">NE</th>
                                            <th data-toggle="tooltip" data-placement="top" title="Carga Motor">CM</th>
                                                                                                                                             
                                           <!--  <th>NE NP</th>  -->
                                             <th id="thide">DT</th>
                                                                                                                                   
											</tr>
										</thead>
									 <tbody>
					
				    <c:if test="${not  empty rlist }">	    
					
						<c:forEach items="${rlist}" var="r" varStatus="count" >	  
							                                
		                                	<!-- <tr onClick="location.href='editarusuario.html?id=${r.idGps2}'">  -->
		                                	<tr>
		                                	     <td>${r.patente}</td>
		                                	      <td>${r.numInterno}</td>
		                                	       <td>${r.conductor}</td>															                                    
		                                        <td><fmt:formatDate value="${r.fechaIni}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
		                                        <td><fmt:formatDate value="${r.fechaFin}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
		                                       
		                                          <td>${r.rendkmlitroString}</td>
		                                       
		                                          <td>${r.rendlithoraString}</td>
		                                          
		                                          
		                                         <td>${r.combtot2}</td>		                                          		                                        
		                                        <td>${r.combmarchar2}</td>
		                                        <td>${r.combral2}</td>
		                                         <td>${r.combcc2}</td>
		                                        <td>${r.combpto2}</td>
		                                        
		                                          <td>${r.dsttot2}</td>
		                                           <td>${r.horotot2}</td>
		                                          <td>${r.tiempomarchaString}</td>
		                                         <td>${r.tiemporelamtiString}</td>
		                                          <td>${r.tpovel2}</td>
		                                           <td>${r.tpocc2}</td>
		                                           <td>${r.tpopto2}</td>
		                                           <td><fmt:formatNumber type = "number" pattern="######"  maxFractionDigits = "0" value = "${r.velmax2}" /></td>
		                                           <td> <fmt:formatNumber type = "number" pattern="######" maxFractionDigits = "0" value = "${r.rpmmax2}" /></td>
		                                            <td> <fmt:formatNumber type = "number" pattern="######"  maxFractionDigits = "0" value = "${r.nivelestamque2}" /></td>		         
		                                             <td> <fmt:formatNumber type = "number" pattern="######"  maxFractionDigits = "0" value = "${r.cargamoto2}" /></td>
		                                            
		                                        <!-- <td>${r.numenergizacion2}-${r.numpartida2}</td>  -->
		                                         <td id="thide"> <fmt:formatNumber type = "number"  maxFractionDigits = "2" value = "${r.dt2/3600}" /></td>
		                                        
		                                      
		                                    </tr>
		           		</c:forEach>           
		            </c:if>		                                    	                         
		                                </tbody>
		                               <tfoot align="right">
										<tr>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
											<!-- <th></th>  -->
											<th id="thide"></th>
											
										</tr>
									</tfoot>
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
    
     <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/js/bootstrap-select.min.js"></script>
     
     <!-- select 2 -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/select2/select2.full.min.js"></script>
    
     <!-- Chosen -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/chosen/chosen.jquery.js"></script>
    
    <!-- hora -->
      <script src="resources/inspinia_v2.9/FullVersion/js/plugins/clockpicker/clockpicker.js"></script>
      
   <!-- Sweet alert -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/sweetalert/sweetalert.min.js"></script>
    
     <!-- funciones de validacion de hora, efecto load para botones de busqueda y carga de pagina -->
       <%@include file="utiljs/loadpagebuttonjs.jsp"%>
       <%@include file="utiljs/valclockpickerjs.jsp"%>
       <%@include file="utiljs/validarFechaHoraJs.jsp"%>
       								
        
    <script>
    
    var formdata = document.getElementById('data_1');  	
	formdata.addEventListener("submit", validarFechasHora, true);  
    
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
     
    // $('.chosen-select').chosen({width: "100%"});
    
    $( "#patente" ).select2( {
		placeholder: "Seleccione Patente",
		 allowClear: true
		
	} ); 
    
    jQuery.fn.dataTable.Api.register( 'average()', function () {
        var data = this.flatten();
        var sum = data.reduce( function ( a, b ) {
            return (a*1) + (b*1); // cast values in-case they are strings
        }, 0 );
     
        return sum / data.length;
    } );
    
    var URLactual =window.location.protocol +"//"+ window.location.host;
    
    $(document).ready(function(){
        $('.dataTables-example').DataTable({
        	 "language": {
                 "url": URLactual + "/sgomtweb/resources/datetableespanil.json",
                 "decimal": ".",
                 //"thousands": "."
             },
             "columnDefs": [
                 {
                     "targets": [ 23 ],
                     "visible": false,
                     "searchable": false
                 },
                 ],
            
             "footerCallback": function ( row, data, start, end, display ) {
                 var api = this.api(), data;
      
                 // converting to interger to find total
                 var intVal = function ( i ) {
                     return typeof i === 'string' ?
                         i.replace(/[\,,]/g, '.')*1 :
                         typeof i === 'number' ?
                             i : 0;
                 };
                 
                 
      
                 // Total over all pages
                /* total = api
                     .column( 2 )
                     .data()
                     .reduce( function (a, b) {
                         return intVal(a) + intVal(b);
                     }, 0 ); */
      
                 // Total over this page
                 pageTotal = api
                     .column( 2, { page: 'current'} )
                     .data()
                     .reduce( function (a, b) {
                         return intVal(a) + intVal(b);
                     }, 0 ); 
                     
     /* fechaInicial =  api.column( 3, { page: 'current'} ).data()[0];
      totalcurrent = api.column( 3, { page: 'current'} ).data().length
      fechaFinal =  api.column( 3, { page: 'current'} ).data()[totalcurrent-1];
      
     // fechaFinal =  api.column( 3, { page: 'current'} ).data()[data.length];
      alert(totalcurrent + fechaInicial + fechaFinal); */
      
      //datas = api.rows().data()[0]; alert(datas);
      
     
                 // Update footer
                 /*$( api.column( 2 ).footer() ).html(
                     '$'+pageTotal +' ( $'+ total +' total)'
                 );*/
                 
              // computing column Total of the complete result 
              var monTotal = api
                     .column( 5 )
                     .data()
                     .reduce( function (a, b) {
                         return intVal(a) + intVal(b);
                     }, 0 );
                 
                 var promedio2 = monTotal/ data.length;    
     				
     	    var tueTotal = api
                     .column( 6 )
                     .data()
                     .reduce( function (a, b) {
                         return intVal(a) + intVal(b);
                     }, 0 );
     	    
     	    var promedio3 = tueTotal/ data.length;
     	    
     	         				
                 var wedTotal = api
                     .column( 7 )
                     .data()
                     .reduce( function (a, b) {
                         return intVal(a) + intVal(b);
                     }, 0 );
     				
     	     var thuTotal = api
                     .column( 8 )
                     .data()
                     .reduce( function (a, b) {
                         return intVal(a) + intVal(b);
                     }, 0 );
     				
     	     var friTotal = api
                     .column( 9 )
                     .data()
                     .reduce( function (a, b) {
                         return intVal(a) + intVal(b);
                     }, 0 );
     	     
     	    var Total7 = api
            .column( 10 )
            .data()
            .reduce( function (a, b) {
                return intVal(a) + intVal(b);
            }, 0 );
     	    
     	   var Total8 = api
           .column( 11 )
           .data()
           .reduce( function (a, b) {
               return intVal(a) + intVal(b);
           }, 0 );
     	   
     	   var Total9 = api
           .column( 12 )
           .data()
           .reduce( function (a, b) {
               return intVal(a) + intVal(b);
           }, 0 );
     	   
     	   var Total10 = api
           .column( 13 )
           .data()
           .reduce( function (a, b) {
               return intVal(a) + intVal(b);
           }, 0 );
     	  var Total11 = api
          .column( 14 )
          .data()
          .reduce( function (a, b) {
              return intVal(a) + intVal(b);
          }, 0 );
     	  var Total12 = api
          .column( 15 )
          .data()
          .reduce( function (a, b) {
              return intVal(a) + intVal(b);
          }, 0 );
     	  var Total13 = api
          .column( 16 )
          .data()
          .reduce( function (a, b) {
              return intVal(a) + intVal(b);
          }, 0 );
     	  var Total14 = api
          .column( 17 )
          .data()
          .reduce( function (a, b) {
              return intVal(a) + intVal(b);
          }, 0 );
     	  var Total15 = api
          .column( 18 )
          .data()
          .reduce( function (a, b) {
              return intVal(a) + intVal(b);
          }, 0 );
     	  var Total16 = api
          .column( 19 )
          .data()
          .reduce( function (a, b) {
              return intVal(a) + intVal(b);
          }, 0 );
     	  
     	    var promedio16 = Total16/ data.length;
     	  
     	  var Total17 = api
          .column( 20 )
          .data()
          .reduce( function (a, b) {
              return intVal(a) + intVal(b);
          }, 0 );
     	  
     	    var promedio17 = Total17/ data.length;
     	    
     	   var Total18 = api
           .column( 21 )
           .data()
           .reduce( function (a, b) {
               return intVal(a) + intVal(b);
           }, 0 );
      	  
      	    var promedio18 = Total18/ data.length;
     	  
     	/*  var Total19 = api
          .column( 22 )
          .data()
          .reduce( function (a, b) {
              return intVal(a) + intVal(b);
          }, 0 ); */
     	  
     	  var Total21 = api
          .column( 23 )
          .data()
          .reduce( function (a, b) {
              return intVal(a) + intVal(b);
          }, 0 );
     	  
     	 var lenghtdata =  api.column( 5 ).data().length;  
     	  
     	  var promediolthora = wedTotal/(Total21);
     	  var promediokmlitro = Total9/wedTotal;
     	  
     	 if(isNaN(promedio2)){     		 
      		promedio2 = 0;
      	 }
     	if(isNaN(promedio3)){     		 
     		promedio3 = 0;
     	 }
     	 
     	 if(isNaN(promediolthora)){     		 
     		promediolthora = 0;
     	 }
     	if(isNaN(promediokmlitro)){     		 
     		promediokmlitro = 0;
     	 }
     	if(isNaN(promedio2)){     		 
     		promedio3 = 0;
     	 }
     	if(isNaN(promedio3)){     		 
     		promedio3 = 0;
     	 }
     	if(isNaN(promedio17)){     		 
     		promedio17 = 0;
     	 }
     	if(isNaN(promedio18)){     		 
     		promedio18 = 0;
     	 }
     	if(isNaN(promedio16)){     		 
     		promedio16 = 0;
     	 }
     	  
                 //alert(data.length) //#31B404
                 $( api.column( 0 ).footer() ).html('');
                 //$( api.column( 4 ).footer() ).html('Total viajes: '+data.length);
                 //$( api.column( 5 ).footer() ).html("<font color='#FF0040'>"+parseFloat(promediokmlitro.toFixed(2))+"</fonr>");
                //$( api.column( 6 ).footer() ).html("<font color='#FF0040'>"+parseFloat(promediolthora.toFixed(2))+"</fonr>");
                 $( api.column( 7 ).footer() ).html(wedTotal.toFixed(2));
                 $( api.column( 8 ).footer() ).html(thuTotal.toFixed(2));
                 $( api.column( 9 ).footer() ).html(friTotal.toFixed(2));
                 $( api.column( 10 ).footer() ).html(Total7.toFixed(2));
                 $( api.column( 11 ).footer() ).html(Total8.toFixed(2));
                 $( api.column( 12 ).footer() ).html(Total9.toFixed(2));
                 $( api.column( 13 ).footer() ).html(Total10.toFixed(2));
                 $( api.column( 14 ).footer() ).html(Total11.toFixed(2));
                 $( api.column( 15 ).footer() ).html(Total12.toFixed(2));
                 $( api.column( 16 ).footer() ).html(Total13.toFixed(2));
                 $( api.column( 17 ).footer() ).html(Total14.toFixed(2));
                 $( api.column( 18 ).footer() ).html(Total15.toFixed(2));
                // $( api.column( 19 ).footer() ).html("<font color='#FF0040'>"+parseInt(promedio16.toFixed(2))+"</fonr>");
                // $( api.column( 20 ).footer() ).html("<font color='#FF0040'>"+parseInt(promedio17.toFixed(2))+"</fonr>");
                // $( api.column( 21 ).footer() ).html("<font color='#FF0040'>"+parseInt(promedio18.toFixed(2))+"</fonr>");
                 
                // $( api.column( 22 ).footer() ).html();
                 
                 $( api.column( 23 ).footer() ).html();
                 
                /* if(lenghtdata > 0){
                     
                	 $("#calculos").show();
                 
                 }else {
                	 $("#calculos").hide();
                 
                 }*/
                 
                //viajes rendinkm rendinlt distancia combustible tiempo
                 $("#viajes").html(parseInt(lenghtdata.toFixed(2)));
                 $("#rendinkm").html(parseFloat(promediokmlitro.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp;Km/Lt</small>");
                 $("#rendinlt").html(parseFloat(promediolthora.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp;Lt/Hr</small>");
                 $("#distancia").html(parseFloat(Total9.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp;Km</small>");
                 $("#combustible").html(parseFloat(wedTotal.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp;Lts</small>");
                 $("#tiempo").html(parseFloat(Total10.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp;hr</small>");
             
             },
        	pageLength: 10,
            //"order": [[ 0, "asc" ]],
            //"order": false,
            responsive: true,
            dom: '<"html5buttons"xºB>lTfgitp',
            buttons: [
                {extend: 'copy'},
                {extend: 'csv'},
                {extend: 'excel', title: 'DetalleXViaje',
                				exportOptions: {
                         			columns: [ 0, 1, 2, 3, 4 ,5, 6 , 7 , 8 ,9 ,10, 11, 12, 13, 14, 15 , 16 , 17 ,18 , 19 , 20 , 21 ]
                	 			},
                				footer: true
                		},
                
                {extend: 'pdf', title: 'DetalleXViaje', 
                	 			exportOptions: {
                         			columns: [ 0, 1, 2, 3, 4 ,5, 6 , 7 , 8 ,9 , 19 , 20 , 21 ]
                	 			},
                				footer: true, 
                				orientation: 'landscape',                
                    			pageSize: 'LEGAL'
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
    
    //var table = $('.dataTables-example').DataTable();
    //table.column( 3 ).data().average();
    
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
    		

    </script>

    
</body>

</html>
