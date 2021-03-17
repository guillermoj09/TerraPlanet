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
                          <h5>Filtros <b>Carguio</b> &nbsp;&nbsp; &nbsp;&nbsp; <small> Buscar por ... </small></h5>                           
                          <div class="ibox-tools">
                              <a class="collapse-link">
                                  <i class="fa fa-chevron-up" id="ocultar"></i>
                              </a>
                          </div>
                      </div>    
                      <form:form action="reportetiempocarguio.html" commandName="reportePatenteFechaForm"  id="data_1" autocomplete="off">                  
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
                                                    </div>  --%>
                                                    
                                                     <div class="col-lg-2  ">   
                                                      <div class="form-group">                                      
                                                              <label>Patentes </label>
                                                              <div>
                                                             
                                                               <select  name="patente" id="patente"  style="width:100%;" tabindex="4"  class="form-control text-uppercase" multiple required>
														 
														   <c:if test="${not  empty listpatentessi }">	  
													  
													  		<c:forEach items="${listpatentessi}" var="ps" varStatus="count" >	  	
																																																					
																 <option value="${ps.vehPatenteVehiculo}" Selected >${ps.vehPatenteVehiculo}<c:if test="${not  empty ps.nroInterno }"><p class="text-secondary"> - ${ps.nroInterno}</p> </c:if></option>
																									
															</c:forEach>           
		            								</c:if>  
																											
														<c:forEach items="${listpatentes}" var="p" varStatus="count" >	  
														
														   <c:if test="${not  empty p }">	
														      
															 <option value="${p.vehPatenteVehiculo}">${p.vehPatenteVehiculo}<c:if test="${not  empty p.nroInterno }"><p class="text-secondary"> - ${p.nroInterno}</p> </c:if></option>																	
																											
												 		  </c:if>	    
												      </c:forEach>	    																											
												</select>		
                                                              </div>                      
                                                      </div>
                                                    </div>  
                                                    
                                                     <div class="col-lg-8  b-r">   
                                                      
                                                    </div>    
                                                    
                                                      <div class="col-lg-2 text-center">                                                                                                              
                                                              <button class="ladda-button btn btn-primary" id="toggleSpinners" data-style="contract" type="submit" style="margin-top:20px;">Buscar</button>
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
                                                      
                                                    </div>
                                                
                                                    
                                                     <div class="col-lg-2 text-center">                                                                                                              
                                                             <!--  <button class="btn btn-primary" type="submit"  style="margin-top:20px;">Filtraur</button>  -->
                                                              <button class="ladda-button btn btn-primary" id="toggleSpinners2" data-style="contract" type="submit" style="margin-top:20px;">Filtrar</button> 
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
                    <div class="ibox float-e-margins" id="ibox1">
                        <div class="ibox-title">
                            <h5>${b.menu}</h5>
                       
                       </div>
                                                             
                        	<div class="ibox-content"  style="${iboxContent}">
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
											<th>Maquina</th>											
											<th>Inicio</th>
											<th>Fin</th>
                                            <th>Tiempo de Carguio</th>
                                            <th>Pases</th>
                                            <th>Ubicacion</th>
                                            <th>Carga Real o Nominal</th>
                                            <th>Camion</th>                                                                                        
											</tr>
										</thead>
									 <tbody>
					
				    <c:if test="${not  empty rlist }">	    
					
						<c:forEach items="${rlist}" var="r" varStatus="count" >	  
							                                
		                                	
		                                	<tr>															                                    
		                                        <td>${r.patente}<c:if test="${not  empty r.nrointerno }"> - ${r.nrointerno} </c:if></td>		                                        
		                                        <td><fmt:formatDate value="${r.fechain}" pattern="dd/MM/yyyy HH:mm:ss" /> </td>
		                                        <td><fmt:formatDate value="${r.fechafin}" pattern="dd/MM/yyyy HH:mm:ss" /> </td>
		                                        <td>${r.tiempocarguio}</td>
		                                        <td>${r.pases}</td>
		                                        <td>${r.ubicacioncarguio}</td>
		                                        <td>${r.cargareal}</td>
		                                        <td>${r.patentecamion}<c:if test="${not  empty r.nrointernocamion }"> - ${r.nrointernocamion} </c:if></td>		                                        
		                                        		                                      
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
        
         <c:if test="${not  empty olist }">
        
        <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins  ${border}">
                        <div class="ibox-title">
                            <h5>Radar Carguio</h5>
                        </div>
                        <div class="ibox-content" style="${iboxContent}">
                            <div>
                                <canvas id="radarChart"></canvas>
                            </div>
                        </div>
                    </div>
                </div>
                
             </div>
        
        
        </c:if>
                
         
        
       </div>
        
        <%@include file="footer.jsp"%>
        
      </div> 

    </div>
    
     <%@include file="footerjs.jsp"%>
            
    <!-- Peity  pie del porcentaje-->
    <script src="resources/js/plugins/peity/jquery.peity.min.js"></script>
    <script src="resources/js/demo/peity-demo.js"></script>
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

    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/dualListbox/jquery.bootstrap-duallistbox.js"></script>ç
     
     <!-- hora -->
      <script src="resources/inspinia_v2.9/FullVersion/js/plugins/clockpicker/clockpicker.js"></script>
    
     <!-- ChartJS--> <!-- Para el grafico Radar -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/chartJs/Chart.min.js"></script>
    
      <!-- select 2 -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/select2/select2.full.min.js"></script>
    
      <!-- Sweet alert -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/sweetalert/sweetalert.min.js"></script>
		 
    <!-- funciones de validacion de hora, efecto load para botones de busqueda y carga de pagina -->
       <%@include file="utiljs/loadpagebuttonjs.jsp"%>		
       <%@include file="utiljs/valclockpickerjs.jsp"%>
       <%@include file="utiljs/validarFechaHoraJs.jsp"%>						
        
    
    <script>
    
   //requiere validarFechaHoraJs y sweetalert
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
     
     $( "#patente" ).select2( {
  		placeholder: "Seleccione Patente",
  		 allowClear: true
  		
  	} );
     
     $( "#faena" ).select2( {
 		placeholder: "Seleccione Faena",
 		 allowClear: true
 		
 	} );
     
     //migrar a js puro
	 $(document).off("change","#patente").on("change","#patente", function()
            {                   
                $('#faena').prop('selectedIndex', 0);  $('#faena').select2();
            });


    $(document).off("change","#faena").on("change","#faena", function()
            { 
                $('#patente').prop('selectedIndex', 0); $('#patente').select2();          
            });
    
    function randomColor()
    {
         color='rgb('+Math.round(Math.random()*255)+','+Math.round(Math.random()*255)+','+Math.round(Math.random()*255)+')';

         return color;
    }
    
        $(document).ready(function() {


                var radarData = {
                    labels: [
                    	<c:forEach items="${olist}" var="o" varStatus="count" >
                    	
                    	"${o.nombreoperacion}",
                    	
                    	//"Tiempo de Carga", "Carga Real", "Pases", "Espera", "Ralentí", "Otra operacion"
                    	</c:forEach>
                    	],
                    datasets: [
                    	
                    	<c:forEach items="${clist}" var="c" varStatus="count" >
                    	
                        {
                            label: "${c.patente}",
                            backgroundColor: "rgba(255, 28, 28, 0)",  //"${c.backgroundColor}"
                            borderColor: randomColor(),		 //"${c.borderColor}"
                            data: [
                            	<c:forEach items="${c.operacionesactive}" var="o2" varStatus="count2" >
                            	${o2.data},
                            	//95, 89, 90, 81, 60, 55
                            	</c:forEach>
                            	]
                        },
                        
                        </c:forEach>
                        
                      /*  {
                            label: "Maquina 01",
                            backgroundColor: "rgba(255, 28, 28, 0)",
                            borderColor: randomColor(),
                            data: [25, 45, 15, 75, 65]
                        },
                        
                        {
                            label: "Maquina 02",
                            backgroundColor: "rgba(255, 28, 28, 0)",
                            borderColor: randomColor(),
                            data: [55, 45, 35, 25, 15]
                        }, */
                                              
                    ]
                };

                var radarOptions = {
                    responsive: true
                };

                var ctx5 = document.getElementById("radarChart").getContext("2d");
                new Chart(ctx5, {type: 'radar', data: radarData, options:radarOptions});

        });
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
                {extend: 'excel', title: 'ReporteCarguio'},
                {extend: 'pdf', title: 'ReporteCarguio'},

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