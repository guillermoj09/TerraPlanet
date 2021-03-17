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
  
   <link href="resources/inspinia_v2.9/FullVersion/css/plugins/switchery/switchery.css" rel="stylesheet">
  
  
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
                          <h5>Filtros <b>${b.menu} </b> &nbsp;&nbsp; &nbsp;&nbsp; <small> Buscar por ... </small></h5>                           
                          <div class="ibox-tools">
                              <a class="collapse-link">
                                  <i class="fa fa-chevron-up" id="ocultar"></i>
                              </a>
                          </div>
                      </div>    
                      <form:form action="reporteinformerendimiento.html" commandName="reportePatenteFechaForm"  id="data_1" autocomplete="off">                  
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
                                               <%-- <div class="col-lg-2 ">   
                                                      <div class="form-group">                                      
                                                              <label>Faenas </label>
                                                              <div>
                                                              <select  name="faena" id="faena"  style="width:100%;" tabindex="4" data-placeholder="Seleccione una Faena" class="form-control text-uppercase">
														
														<option value=""></option>
														
														<!-- <option value="0"  <c:if test="${faenaTodas == 'SI'}"> selected</c:if> >Todas</option>  -->
														  														  														  
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
                                                    </div> --%>
                                                    
                                                     <div class="col-lg-2  ">   
                                                      <div class="form-group">                                      
                                                              <label>Patentes </label>
                                                              <div>
                                                               <select  name="patente" id="patente"  style="width:100%;" tabindex="4" data-placeholder="Seleccione una Patente" class="form-control text-uppercase" required>
																					
														<option value=""></option>	
														
														<c:if test="${not  empty rform.patente }">
														<!--  <option value="${rform.patente}" selected>${rform.patente}</option> -->
														 </c:if>	  
																											
														<c:forEach items="${listpatentes}" var="p" varStatus="count" >	  
														
														   <c:if test="${not  empty p }">	
														        
															 <option value="${p.vehPatenteVehiculo}" <c:if test="${p.vehPatenteVehiculo == rform.patente}"> selected</c:if>>${p.vehPatenteVehiculo}<c:if test="${not  empty p.nroInterno }"><p class="text-secondary"> - ${p.nroInterno}</p> </c:if></option>													
															
												
												 		  </c:if>	    
												      </c:forEach>	    																											
												</select>		
                                                              </div>                      
                                                      </div>
                                                    </div>  
                                                    
                                                     <div class="col-lg-8  b-r">   
                                                      
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
                                                      <div class="col-lg-2 ">
                                                        <div class="form-group">
                                                            <label class="control-label">&nbsp; Hora Hasta</label>&nbsp;&nbsp; &nbsp;&nbsp; 
                                                            <div class="input-group clockpicker" data-autoclose="true">
                                                                <input type="text" class="form-control" name="horaHasta" id="horaHasta" value="${rform.horaHasta}" >
                                                                <span class="input-group-addon"><span class="fa fa-clock-o"></span></span>
                                                            </div>                                       
                                                        </div> 
                                                    </div>
                                                   
                                               <div class="col-lg-2 b-r">
                                                       <!--  <div class="form-group">                                      
                                                              <label>Consultar X Tiempo</label>
                                                              <div>
                                                                   <input type="checkbox" class="js-switch"  name = "sw" id = "sw"  <c:if test="${rform.sw == true}">checked</c:if> />
                                                              </div>                      
                                                      </div> --> 
                                                    </div>
                                                
                                                    
                                                     <div class="col-lg-2 text-center">                                                                                                              
                                                             <!--  <button class="btn btn-primary" type="submit"  style="margin-top:20px;">Filtraur</button>  -->
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
            
            <!--  bloque Destino patente  id="iniciotermino"  style="display:none;" -->   
            <div class="row">
				

		<div class="col-xs-12 col-md-12 col-lg-4 col-xl-4">
					
					 <div class="ibox ">
                    <div class="ibox-title">
                    	 <span class="label label-success  pull-right">${r.nroInt}</span> 
						<i class="icon-chart"></i>
						<h4 class="text-muted text-uppercase m-b-20"><b>Patente:</b> ${r.patente}</h4>
						  <input type="hidden" id="patented"  value="${r.patente}" >
						<div class="m-b-20">	
						<h3>
							<span data-plugin="counterup" id="marca"> <b>Marca:</b> ${r.marca} </span>
							</br>
							<span data-plugin="counterup" id="modelo"> <b>Modelo:</b> ${r.modelo} </span>
																				
						</h3>																								
						</div>
						
							
					</div>	
					</div>
				</div>

				
			<div class="col-xs-12 col-md-12 col-lg-4 col-xl-4">
					
					 <div class="ibox ">
                    <div class="ibox-title">
                    	 <i class="fa fa-thumb-tack  pull-right"></i> 
						<i class="icon-chart"></i>
						<h4 class="text-muted text-uppercase m-b-20">Inicio</h4>
						<div class="m-b-20">	
						<h3>
							<span data-plugin="counterup" id="inicio">${r.origen} </span>
							</br>
							<span data-plugin="counterup" > &nbsp;<fmt:formatDate value="${r.salidad}" pattern="dd/MM/yyyy HH:mm:ss" /></span>
																				
						</h3>																								
						</div>
						
						
					</div>	
					</div>
				</div>

				<div class="col-xs-12 col-md-12 col-lg-4 col-xl-4">
					
					 <div class="ibox ">
                    <div class="ibox-title">
                    	 <i class="fa fa-thumb-tack  pull-right"></i> 
						<i class="icon-chart"></i>
						<h4 class="text-muted text-uppercase m-b-20">Termino</h4>
						<div class="m-b-20">	
						<h3>
							<span data-plugin="counterup" id="termino"> ${r.destino} </span>
							</br>
							<span data-plugin="counterup" > &nbsp;<fmt:formatDate value="${r.llegada}" pattern="dd/MM/yyyy HH:mm:ss" /> </span>
																				
						</h3>																								
						</div>
						
						
					</div>	
					</div>
				</div>


			</div> <!-- end row Patente Inicio Termino -->
            
            <!--  bloque promedios totales id="totales"  style="display:none;"-->   
            <div class="row" >
				<div class="col-xs-12 col-md-6 col-lg-6 col-xl-2">
					 <div class="ibox ">
                    <div class="ibox-title">
                    	 <span class="label label-success pull-right">Total </span> 
						<i class="icon-chart"></i>
						<h5 class="text-muted text-uppercase m-b-20">TPO</h5>
						<div class="m-b-20">	
						<h2>
							<span data-plugin="counterup" id="tiemporecorrido"><fmt:formatNumber type = "number"   maxFractionDigits = "2" value = "${r.horometro}" /><small style="color: red; font-size :12px;">&nbsp;hr</small></span>							
							
						</h2>	
																							
						</div>
						
						<small>Tiempo (hr)</small>

						
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
							<span data-plugin="counterup"  id="kilometrosrecorridos"><fmt:formatNumber type = "number"   maxFractionDigits = "2" value = "${r.kmrecorrido}" /><small style="color: red; font-size :12px;">&nbsp;km</small></span>														
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
							<span data-plugin="counterup" id="litrosconsumidos">  <fmt:formatNumber type = "number"   maxFractionDigits = "2" value = "${r.lstconsumidos}" /><small style="color: red; font-size :12px;">&nbsp;Lts</small></span>														
						</h2>																								
						</div>
						
							<small>Combustible (Lts)</small>
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
							<span data-plugin="counterup" id="rendimiento">  <fmt:formatNumber type = "number"   maxFractionDigits = "2" value = "${r.rendimiento}" /><small style="color: red; font-size :12px;">&nbsp;Km/Lt</small></span>														
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
							<span data-plugin="counterup" id="rendimientolthr">  <fmt:formatNumber type = "number"   maxFractionDigits = "2" value = "${r.rendimientolthr}" /><small style="color: red; font-size :12px;">&nbsp;Lt/Hr</small></span>														
						</h2>																								
						</div>
						
							<small>Rendimiento (Lt/Hr)</small>
					</div>	
					</div>
				</div>

				<div class="col-xs-12 col-md-6 col-lg-6 col-xl-2">
					
					 <div class="ibox ">
                    <div class="ibox-title">
                    	 <span class="label label-primary  pull-right">Promedio </span> 
						<i class="icon-chart"></i>
						<h4 class="text-muted text-uppercase m-b-20">VPM</h4>
						<div class="m-b-20">	
						<h2>
							<span data-plugin="counterup" id="velpro"><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${r.velocidapromedio}" /><small style="color: red; font-size :12px;">&nbsp;kmh</small></span>														
						</h2>																								
						</div>
						
							<small>Velocidad Prom(kmh)</small>
					</div>	
					</div>
				</div>



			</div> <!-- end row bloque promedios y totales -->   
          

        <%--
          <!--   <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins ${border}" id="ibox1">
                   
                    
                        <div class="ibox-title">
                            <h5>Reporte Informe Rendimiento </h5>
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
                                            <th>Lugar Origen</th>
                                            <th>Fecha Salida</th>                                           
                                            <th>Lugar Destino</th>
                                            <th>Fecha Llegada</th>
                                            <th>Tpo. Recorrido</th>
                                            <th>Kms Recorrido</th>
                                            <th>Lts Consumidos</th>                                            
                                            <th>Rendimiento Km/Lt</th>                                           
                                                                                                                                
											</tr>
										</thead>
									 <tbody>
					
				    <c:if test="${not  empty rlist }">	    
					
						<c:forEach items="${rlist}" var="r" varStatus="count" >	  
							                                
		                                	
		                                	<tr>															                                    
		                                        <td>${r.patente}</td>
		                                        <td>${r.origen}</td>		                                       		                                       
		                                    	<td><fmt:formatDate value="${r.salidad}" pattern="dd/MM/yyyy HH:mm:ss" /> </td>						                                      				                                      
		                                        <td>${r.destino}</td>
		                                        <td><fmt:formatDate value="${r.llegada}" pattern="dd/MM/yyyy HH:mm:ss" /> </td>
		                                        <td>${r.tporecorrido}</td>
		                                        <td>${r.kmrecorrido}</td>
		                                         <td><fmt:formatNumber type = "number" pattern="######"  maxFractionDigits = "2" value = "${r.lstconsumidos}" /></td>		                                        
		                                        <td>${r.rendimiento}</td>		 
		                                                                           
		                                     
		                                      
		                                    </tr>
		           		</c:forEach>           
		            </c:if>		                                    	                         
		                                </tbody>
									</table>
								</div>
							</div>
                               		                                   
              </div>
                                   
            </div>
                                                
        </div> -->  --%>
        
        
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
    
    <!-- Switchery -->
   <script src="resources/inspinia_v2.9/FullVersion/js/plugins/switchery/switchery.js"></script>
   
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
     
     var iniciotermino = document.getElementById("iniciotermino");
     var totales = document.getElementById("totales");
     //var salida = document.getElementById("salida").innerHTML;
     var patente = document.getElementById("patented").value;
    // mostracalculos();   				
  	
      function mostracalculos(){
    	  
    	  if(patente.length > 0 ){
    		  //alert("mayor 0")
    		  iniciotermino.style.display = "block";
    		  totales.style.display = "block";
    		  
    	  }else{
    		  //alert("menor 0")
    		  iniciotermino.style.display = "none";
    		  totales.style.display = "none";
    		  
    	  }      	      
      	
         }
      
     
   //  var elem = document.querySelector('.js-switch');
   //  var switchery = new Switchery(elem, { color: '#1AB394' });

          
    
    $( "#patente" ).select2( {
		placeholder: "Seleccione Patente",
		 allowClear: true
		
	} );

   /* $( "#faena" ).select2( {
		placeholder: "Seleccione Faena",
		 allowClear: true
		
	} ); */
    
    
	  //migrar a js puro
/*	 $(document).off("change","#patente").on("change","#patente", function()
            {                   
                $('#faena').prop('selectedIndex', 0);  $('#faena').select2();
            });


    $(document).off("change","#faena").on("change","#faena", function()
            { 
                $('#patente').prop('selectedIndex', 0); $('#patente').select2();          
            });
*/
       
   </script>
   
    
    
    <script>
    
    var URLactual =window.location.protocol +"//"+ window.location.host;
    
    $(document).ready(function(){
        $('.dataTables-example').DataTable({
        	"language": {
                "url": URLactual + "/sgomtweb/resources/datetableespanil.json"
            },
        	pageLength: 10,
            //"order": [[ 0, "asc" ]],
            //"order": false,
            responsive: true,
            dom: '<"html5buttons"xºB>lTfgitp',
            buttons: [
                {extend: 'copy'},
                {extend: 'csv'},
                {extend: 'excel',    
                	title: 'ReporteRendimiento',
                	 exportOptions: {
                         columns: [ 0, 1, 2, 3, 4 ,5, 6 , 7, 8 ]
                	 }
                	
                },
                
                {extend: 'pdf', 
                	title: 'ReporteRendimiento',                    	
               	 exportOptions: {
                        columns: [ 0, 1, 2, 3, 4 ,5, 6, 7, 8  ]
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
    
    $(document).on("click",".link_mapa",function(){
    	window.open($(this).attr('link'),"MAPA","top=100,left=300,width=800,height=650,scrollbars=NO,resizable=YES,class=lbOn" );
	});

    		

    </script>

    
</body>

</html>