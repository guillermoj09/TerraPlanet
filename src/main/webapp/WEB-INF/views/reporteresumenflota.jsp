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
                      <form:form action="reporteresumenflota.html" commandName="reportePatenteFechaForm"  id="data_1" autocomplete="off">                  
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
                                                      <div class="form-group">                                      
                                                              <label>Faenas </label>
                                                              <div>
                                                                
                                                                <select name="faena" id="faena"  style="width:100%;" tabindex="4" data-placeholder="Seleccione una Faena" class="form-control text-uppercase" required>
														
														<option value=""></option>
														
														<!-- <option value="Todas">Todas</option>  -->
														
													<!--	<option value="0"  <c:if test="${faenaTodas == 'SI'}"> selected</c:if> >TODAS</option> -->
														  														  														  
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
                            <h5>${b.menu}</h5>
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
											<th>Patente </th>
                                            <th>N.Int</th>                                            
                                            <th>Marca</th>
                                            <th>Modelo</th>
                                            <th>Viajes</th>
                                            <th data-toggle="tooltip" data-placement="top" title="Rendimiento (Km/Lt)">RDM(Km/Lt)</th>
                                            <th data-toggle="tooltip" data-placement="top" title="Rendimiento (Lt/Hr)">RDM (Lt/Hr)</th>
                                            <th data-toggle="tooltip" data-placement="top" title="Distancia Total (KM)">DTS Total (KM)</th>
                                            <th data-toggle="tooltip" data-placement="top" title="Combustible Total (Lts)">CMB Total (Lts)</th>
                                            <th data-toggle="tooltip" data-placement="top" title="Tiempo Total (HR)">TPO Total (HR)</th>
                                            <th  data-toggle="tooltip" data-placement="top" title="Velocidad Maxima">Vel Max</th>
                                            <th>RPM Max.</th>                                            
                                            <th>ALA RPM </th>
                                            <th>ALA Ralenti</th>
                                            <th>ALA Veleo</th>                                            
                                            <th>COD. Fallas</th>
                                            <!-- <th data-toggle="tooltip" data-placement="top" title="Acelerada Brusca">AB</th>
                                            <th data-toggle="tooltip" data-placement="top" title="Frenada Brusca">FB</th>  -->
                                            <th data-toggle="tooltip" data-placement="top" title="Odómetro">ODO</th>
                                            <th data-toggle="tooltip" data-placement="top" title="Horómetro">HOR</th>
                                             <th id="thide" >DT</th>
                                             <th id="thide">tpomm</th> 
                                            
                                                                                        
											</tr>
										</thead>
									 <tbody>
					
				    <c:if test="${not  empty rlist }">	    
					
						<c:forEach items="${rlist}" var="r" varStatus="count" >	  
							                                
		                                	<!-- <tr onClick="location.href='editarusuario.html?id=${r.idVehicle}'">  -->
		                                	<tr>
		                                		 															                                    
		                                        <td>${r.patente}</td>
		                                        <td>${r.numinterno}</td>		                                       
		                                        <td>${r.marca}</td>
		                                        <td>${r.modelo}</td>		                                       
		                                        <td>${r.viajes}</td>
		                                       
		                                        <td>${r.rendimkmltString}</td> 
		                                   	    
		                                        <td>${r.rendimlthrString}</td>
		                                   	
		                                        <td>${r.dsttotString}</td>
		                                        <td>${r.combtotString}</td>
		                                        <td>${r.totaltiempo}</td>
		                                        <td><fmt:formatNumber type = "number" pattern="######"  maxFractionDigits = "0" value = "${r.velmax}" /></td>
		                                        <td> <fmt:formatNumber type = "number" pattern="######" maxFractionDigits = "0" value = "${r.rpmmax}" /></td>		                                        
		                                        <td>${r.alarpm}</td>
		                                        <td>${r.alaral}</td>
		                                         <td>${r.alavel}</td>
		                                         <td>${r.alacodf}</td>
		                                         <!-- <td>${r.alaacelb}</td>		                                         
		                                         <td>${r.alafrenb}</td> -->
		                                         <td>${r.odometro}</td>
		                                         <td>${r.horometro}</td>
		                                         <!--   <td  id="thide"> <fmt:formatNumber type = "number"  maxFractionDigits = "2" value = "${r.dt}" /></td>  -->    
		                                            <td  id="thide"> ${r.dtString}</td>
		                                           <td  id="thide">${r.tpommString}</td>                                  		                                        		                                        		                                    
		                                      
		                                    </tr>
		           		</c:forEach>           
		            </c:if>		                                    	                         
		                                </tbody>
		                                
		                              <!--    <tfoot align="right">
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
											<th  id="thide"></th>
											<th  id="thide"></th>
										</tr>
									</tfoot>  -->
		                                
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
    
    $("#calculos").hide();
    
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
     
    $( "#faena" ).select2( {
		placeholder: "Seleccione Faena",
		 allowClear: true
		
	} );
    
    var URLactual =window.location.protocol +"//"+ window.location.host;
    
    $(document).ready(function(){
        $('.dataTables-example').DataTable({
        	"language": {
                "url": URLactual + "/sgomtweb/resources/datetableespanil.json"
            },
            "columnDefs": [
                {
                    "targets": [ 18 ],
                    "visible": false,
                    "searchable": false
                },
                {
                    "targets": [ 19 ],
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
            var Total4 = api
            .column( 4 )
            .data()
            .reduce( function (a, b) {
                return intVal(a) + intVal(b);
            }, 0 );
            
            var Total4c = api
           // .column( 4,  { page: 'current'} )  // solo pagina
           .column( 4, { 'filter': 'applied'} ) // todo y filtrado
            .data()
            .reduce( function (a, b) {
                return intVal(a) + intVal(b);
            }, 0 );
     	    
     	   var Total5 = api
           .column( 5 )
           .data()
           .reduce( function (a, b) {
               return intVal(a) + intVal(b);
           }, 0 );
     	   
     	  var Total5c = api
          .column( 5, { page: 'current'} )
          .data()
          .reduce( function (a, b) {
              return intVal(a) + intVal(b);
          }, 0 );
     	   
     	  //falta revisar para no calcular columna que traen cero
     	 //var Total5f = api.column( 5 ).data();
     	  var promedio5 = Total5/ data.length;
     	  var lenghtdata =  api.column( 5, { page: 'current'} ).data().length;     	 //cantida pagina actual
     	  var promedio5c = Total5c/ lenghtdata;
     	   
     	   var Total6 = api
           .column( 6 )
           .data()
           .reduce( function (a, b) {
               return intVal(a) + intVal(b);
           }, 0 );
     	   
     	  var Total6c = api
          .column( 6,  { page: 'current'}  )
          .data()
          .reduce( function (a, b) {
              return intVal(a) + intVal(b);
          }, 0 );
     	   
     	  var promedio6 = Total6/ data.length;
     	 var lenghtdata2 =  api.column( 6, { page: 'current'} ).data().length;     	 //cantida pagina actual
     	  var promedio6c = Total6c/ lenghtdata2;
     	   
     	   var Total7 = api
           .column( 7 )
           .data()
           .reduce( function (a, b) {
               return intVal(a) + intVal(b);
           }, 0 );
     	  var Total8 = api
          .column( 8 )
          .data()
          .reduce( function (a, b) {
              return intVal(a) + intVal(b);
          }, 0 );
     	  var Total9 = api
          .column( 9 )
          .data()
          .reduce( function (a, b) {
              return intVal(a) + intVal(b);
          }, 0 );
     	  
     	 var Total7c = api
         .column( 7, { 'filter': 'applied'} )
         .data()
         .reduce( function (a, b) {
             return intVal(a) + intVal(b);
         }, 0 );
   	  var Total8c = api
        .column( 8, { 'filter': 'applied'} )
        .data()
        .reduce( function (a, b) {
            return intVal(a) + intVal(b);
        }, 0 );
   	  var Total9c = api
        .column( 9 , { 'filter': 'applied'} )
        .data()
        .reduce( function (a, b) {
            return intVal(a) + intVal(b);
        }, 0 );
     	  
     	  var Total10 = api
          .column( 10 )
          .data()
          .reduce( function (a, b) {
              return intVal(a) + intVal(b);
          }, 0 );
     	  
     	  var promedio10 = Total10/ data.length;
     	  
     	  var Total11 = api
          .column( 11 )
          .data()
          .reduce( function (a, b) {
              return intVal(a) + intVal(b);
          }, 0 );
     	  
     	  var promedio11 = Total11/ data.length;
     	  
     	 var Total12 = api
         .column( 12 )
         .data()
         .reduce( function (a, b) {
             return intVal(a) + intVal(b);
         }, 0 );
     	 var Total13 = api
         .column( 13 )
         .data()
         .reduce( function (a, b) {
             return intVal(a) + intVal(b);
         }, 0 );
     	 var Total14 = api
         .column( 14 )
         .data()
         .reduce( function (a, b) {
             return intVal(a) + intVal(b);
         }, 0 );
     	 var Total15 = api
         .column( 15 )
         .data()
         .reduce( function (a, b) {
             return intVal(a) + intVal(b);
         }, 0 );
     	 var Total16 = api
         .column( 16 )
         .data()
         .reduce( function (a, b) {
             return intVal(a) + intVal(b);
         }, 0 );
     	 var Total17 = api
         .column( 17 )
         .data()
         .reduce( function (a, b) {
             return intVal(a) + intVal(b);
         }, 0 );
     	 
     	var Total18 = api
        .column( 18 )
        .data()
        .reduce( function (a, b) {
            return intVal(a) + intVal(b);
        }, 0 );
     	
    	var Total19 = api
        .column( 19 )
        .data()
        .reduce( function (a, b) {
            return intVal(a) + intVal(b);
        }, 0 );
     	 
     	  var promediolthora = Total13/(Total18);
     	  var promediokmlitro = Total9/3600;
     	
     //current promedio
     	 var Total13c = api
         .column( 13, { 'filter': 'applied'} )
         .data()
         .reduce( function (a, b) {
             return intVal(a) + intVal(b);
         }, 0 ); 
     	  
     	 var Total18c = api
         .column( 18, { 'filter': 'applied'} )
         .data()
         .reduce( function (a, b) {
             return intVal(a) + intVal(b);
         }, 0 );
      	
     	var Total19c = api
         .column( 19 , { 'filter': 'applied'})
         .data()
         .reduce( function (a, b) {
        	 
        	 return intVal(a) + intVal(b); 
         }, 0 );	
     	
     	     	
     	var  promrendkmlitroc = Total19c/(3600);  //mapacaloractive.getTpomm()/3600; Total9c/3600;
     	
    	 var promrendlithorac =  Total8c/(Total18c/3600) ; //mapacaloractive.getCombtot()/((mapacaloractive.getDt()/3600));	 //Total13c/(Total18c);   	  
   //fin current promedio
   
   var  promrendkmlitro = Total19/(3600);
     	
  var promrendlithora =  Total8/(Total18/3600) ;   
   
   
   if(isNaN(promrendlithora)){     		 
	   promrendlithora = 0;
	 }
   
   if(isNaN(promrendkmlitro)){     		 
	   promrendkmlitro = 0;
	 }
   
   if(isNaN(promrendlithorac)){     		 
	   promrendlithorac = 0;
	 }
   
   if(isNaN(promrendkmlitroc)){     		 
	   promrendkmlitroc = 0;
	 }
     	 
     	if(isNaN(promedio5)){     		 
     		promedio5 = 0;
     	 }
     	if(isNaN(promedio6)){     		 
     		promedio6 = 0;
     	 }
     	if(isNaN(promedio10)){     		 
     		promedio10 = 0;
     	 }
     	if(isNaN(promedio11)){     		 
     		promedio11 = 0;
     	 }
     	
     	
     	/* $( api.column( 3 ).footer() ).html('Total: ');
     	 $( api.column( 4 ).footer() ).html(parseInt(Total4.toFixed(2)));
         $( api.column( 5 ).footer() ).html("<font color='#FF0040'>"+parseFloat(promedio5.toFixed(2))+"</fonr>");
         $( api.column( 6 ).footer() ).html("<font color='#FF0040'>"+parseFloat(promedio6.toFixed(2))+"</fonr>");
         $( api.column( 7 ).footer() ).html(Total7.toFixed(2));//s
         $( api.column( 8 ).footer() ).html(Total8.toFixed(2));//s
         $( api.column( 9 ).footer() ).html(Total9.toFixed(2));//s
         $( api.column( 10 ).footer() ).html("<font color='#FF0040'>"+parseInt(promedio10.toFixed(2))+"</fonr>");//p
         $( api.column( 11 ).footer() ).html("<font color='#FF0040'>"+parseInt(promedio11.toFixed(2))+"</fonr>");//p
         $( api.column( 12 ).footer() ).html(parseInt(Total12));//s
         $( api.column( 13 ).footer() ).html(parseInt(Total13));//s
         $( api.column( 14 ).footer() ).html(parseInt(Total14));//s
         $( api.column( 15 ).footer() ).html(parseInt(Total15));//s
         $( api.column( 16 ).footer() ).html(parseInt(Total16));//s
         $( api.column( 17 ).footer() ).html(parseInt(Total17));//s */
         
       /*  if(lenghtdata > 0){
        
        	 $("#calculos").show();
         
         }else {
        	 $("#calculos").hide();
         
         }*/
         
        //Dinamico x filtro
         $("#viajes").html(parseInt(Total4c.toFixed(2)));        
         $("#rendinkm").html(parseFloat(promrendkmlitroc.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp;Km/Lt</small>");
         $("#rendinlt").html(parseFloat(promrendlithorac.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp;Lt/Hr</small>");
         $("#distancia").html(parseFloat(Total7c.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp;Km</small>");
         $("#combustible").html(parseFloat(Total8c.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp;Lts</small>");
         $("#tiempo").html(parseFloat(Total9c.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp;Hr</small>");
         
        
       // if(lenghtdata2 > 1){
        
         //fijo         
         $("#viajesf").html("<small style='color: red; font-size :12px;'>&nbsp;("+parseInt(Total4.toFixed(2))+")</small>");
         $("#rendinkmf").html(parseFloat(promrendkmlitro.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp;Km/Lt</small>");
         $("#rendinltf").html(parseFloat(promrendlithora.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp;Lt/Hr</small>");
         $("#distanciaf").html(parseFloat(Total7.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp;Km</small>");
         $("#combustiblef").html(parseFloat(Total8.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp;Lts</small>");
         $("#tiempof").html(parseFloat(Total9.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp;Hr</small>");
         
        //}
     	 
        },
         
        	pageLength: 10,
            //"order": [[ 0, "asc" ]],
            //s"order": false,
            responsive: true,
            dom: '<"html5buttons"xºB>lTfgitp',
            buttons: [
                {extend: 'copy'},
                {extend: 'csv'},
                {extend: 'excel', title: 'ResumenFlota',
                	exportOptions: {
             			columns: [ 0, 1, 2, 3, 4 ,5, 6 , 7 , 8 ,9 ,10, 11, 12, 13, 14, 15 , 16, 17 ]
    	 			},                
                				 footer: true                	
                },
                
                {extend: 'pdf', title: 'ResumenFlota',
                	exportOptions: {
             			columns: [ 0, 1, 2, 3, 4 ,5, 6 , 7 , 8 ,9 ,10, 11, 12, 13, 14, 15 , 16, 17 ]
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