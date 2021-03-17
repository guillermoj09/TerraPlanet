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
                      <form:form action="reportetiempotransporte.html" commandName="reportePatenteFechaForm"  id="data_1" autocomplete="off">                  
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
                                                              <select  name="faena" id="faena"  style="width:100%;" tabindex="4" required data-placeholder="Seleccione una Faena" 
                                                              class="form-control text-uppercase">
														
														<option value=""></option>
														<c:forEach items="${listfaena}" var="f" varStatus="count" >	  														
														   <c:if test="${not  empty f }">													   
														  			<option value="${f.idGru}" <c:if test="${f.idGru == rform.faena}"> selected</c:if>>${f.nombreGru}</option>														  											   									
												 		  </c:if>	    
												      </c:forEach>	    																											
												</select>		
                                                              </div>                      
                                                      </div>
                                                    </div>   
                                                 <div class="col-lg-2 ">   
                                                      <div class="form-group">                                      
                                                              <label>Patentes </label>
                                                              <div>
                                                               <select  name="patente" id="patente"  style="width:100%;" tabindex="4" data-placeholder="Seleccione una Patente" 
                                                               		class="form-control text-uppercase">
																					
														<option value=""></option>	
														
														<c:if test="${not  empty rform.patente }">
														<option value="${rform.patente}" selected>${rform.patente}</option>
														 </c:if>	  
																											
														<c:forEach items="${listpatentes}" var="p" varStatus="count" >	  
														
														   <c:if test="${not  empty p }">	
														   
														   <c:if test="${p.vehPatenteVehiculo ne rform.patente}">											
																<option value="${p.vehPatenteVehiculo}">${p.vehPatenteVehiculo}</option>															
															</c:if>
															
												
												 		  </c:if>	    
												      </c:forEach>	    																											
												</select>		
                                                              </div>                      
                                                      </div>
                                                    </div>
                                                    <div class="col-lg-5  b-r">   
                                                      
                                                    </div>
                                                      <div class="col-lg-3 text-center">                                                                                                              
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
                                                    <div class="col-lg-3">
                                                        <div class="form-group">
                                                            <label class="control-label">&nbsp; Fecha Desde</label>&nbsp;&nbsp; &nbsp;&nbsp; 
                                                            <div class="input-group date">
                                                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" class="form-control" name="fechaDesde"  value="${rform.fechaDesde}" required>
                                                            </div>                                      
                                                        </div> 
                                                    </div>
                                                    <!-- 
                                                    <div class="col-lg-3">
                                                        <div class="form-group">
                                                            <label class="control-label">&nbsp; Hora Desde</label>&nbsp;&nbsp; &nbsp;&nbsp; 
                                                            <div class="input-group clockpicker" data-autoclose="true">
                                                                <input type="text" class="form-control" value="09:30">
                                                                <span class="input-group-addon"><span class="fa fa-clock-o"></span></span>
                                                            </div>                                     
                                                        </div> 
                                                    </div>
                                                    -->
                                                    <div class="col-lg-3">
                                                        <div class="form-group">
                                                            <label class="control-label">&nbsp; Fecha Hasta</label>&nbsp;&nbsp; &nbsp;&nbsp; 
                                                            <div class="input-group date">
                                                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" class="form-control" name="fechaHasta" value="${rform.fechaHasta}" required>
                                                            </div>                                      
                                                        </div> 
                                                    </div>
                                                    
                                                    <div class="col-lg-3 b-r">                                                                                                              
                                                  
                                                  </div>
                                                
                                                    
                                                     <div class="col-lg-3 text-center">                                                                                                              
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
											<th>Camion</th>
                                            <th>Ubicacion Carguio</th>
                                            <!-- <th>Fecha Entrada a Cargio</th> // ciclo-->
                                            <th>Fecha Salida Carguio</th>                                            
                                            <th>Ubicacion Descarga</th>
                                            <th>Fecha Entrada a Descarga</th> 
                                            <th>Fecha Descarga</th> 
                                            <th>Fecha Salida Descarga</th> 
                                            <th>Carga Real</th>                                            
                                            <th>Tiempo Espera</th>
                                            <th>Tiempo Descarga</th> 
                                            <th>Tiempo Transporte</th>      
                                            <!-- <th>Tiempo Ciclo</th>  -->	                                                                                 
											</tr>
										</thead>
									 <tbody>
					
				    <c:if test="${not  empty rlist }">    					
						<c:forEach items="${rlist}" var="r" varStatus="count" >
		                                	<tr>															                                    
		                                        <td>${r.idcamion}</td>
		                                        <td>${r.ubicacioncarguio}</td>
		                                        <!--<td>${r.fechaentradacargio}</td> -->
		                                        <td>${r.fechasalidacargio}</td>		                                        
		                                        <td>${r.ubicaciondescarga}</td>
		                                        <td>${r.fechaentradadescarga}</td>
	                                            <td>${r.fechadescarga}</td>
	                                            <td>${r.fechasalidadescarga}</td>
		                                        <td>${r.cargareal}</td>		                                        
		                                        <td>${r.tiempoespera}</td>
		                                        <td>${r.tiempodescarga}</td>
		                                        <td>${r.tiempotransporte}</td>
		                                        <!--<td>${r.tiempociclo}</td>  -->		                                      
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
    
      <!-- Sweet alert -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/sweetalert/sweetalert.min.js"></script>
   
    <!-- funciones de validacion de hora, efecto load para botones de busqueda y carga de pagina -->
       <%@include file="utiljs/loadpagebuttonjs.jsp"%>		             
       <%@include file="utiljs/validarFechaHoraJs.jsp"%>						 
    
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

     
     var URLactual =window.location.protocol +"//"+ window.location.host;
    
    $(document).ready(function(){
    	
    	
   	 $( "#patente" ).select2( {
   	 		placeholder: "Seleccione Patente",
   	 		 allowClear: true
   	 		
   	 	} );
   	     
   	     $( "#faena" ).select2( {
   				placeholder: "Seleccione Faena",
   				 allowClear: true
   				
   			} );
   	     
   	  $(document).off("change","#faena").on("change","#faena", function()
	             { 
	     			$('#patente').prop('selectedIndex', 0); $('#patente').select2();  
	             });
	     
	     $(document).off("change","#patente").on("change","#patente", function()
	             { 
	     			$('#faena').prop('selectedIndex', 0); $('#faena').select2();
	             });
	     
	     
	     $(document).on("click", ".btn_submit", function(e){ 
  		if($("#faena").val()=="" && $("#patente").val()==""){            	    
  	    	swal("", "Debe seleccionar una Faena o una Patente ", "error"); $("#faena").focus();
  	    	return  false;
  	    }
  	    else{
  	    	var istrue = validoFechasHora();    	
 	    	if(istrue){
 	    		$("#data_1").submit(); 
 	    	}
  	    }
  	});
   	     
   	     
   	     
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
                {extend: 'excel', title: 'Reporte Tiempo Transporte'},
                {extend: 'pdf', title: 'Reporte Tiempo Transporte'},

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
    		

    </script>

    
</body>

</html>