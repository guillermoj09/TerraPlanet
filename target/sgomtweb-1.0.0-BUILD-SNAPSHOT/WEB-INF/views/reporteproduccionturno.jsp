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
                            <a href="">Productividad</a>
                        </li>
                        
                        
                        <li class="breadcrumb-item active">
                            <strong>REPORTE DE PRODUCCIÓN POR TURNOS, FRENTE, OPERADOR, Y VEHÍCULO</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>
        
   <!-- Contenido principal  -->
        <div class="wrapper wrapper-content animated fadeInRight">
        
        
        <div class="row"> <!-- Bloque Formulario de busqueda -->
                    <div class="col-sm-12">
                        <div class="ibox-content">

                            <h4 class="header-title m-t-0 m-b-30">REPORTE DE PRODUCCIÓN POR TURNOS, FRENTE, OPERADOR, Y VEHÍCULO</h4>
								<form:form action="reportetiempotransporte.html" commandName="reportePatenteFechaForm"  id="data_1" autocomplete="off"> 
                             <div class="row">  
                             
                                <div class="col-lg-4"> 
                             
                             <div class="form-group">
	                                
	                                <div class="input-group date"><label class="control-label">&nbsp; Fecha Desde</label>&nbsp;&nbsp; &nbsp;&nbsp; 
	                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" class="form-control" name="fechaDesde" value="${rform.fechaDesde}" required="required">
	                                </div>
                            </div>
                            
                            </div>
                            
                               <div class="col-lg-4"> 
                            
                            <div class="form-group">
                                   
	                                <div class="input-group date"> <label class="control-label">&nbsp; Fecha Hasta</label>&nbsp;&nbsp; &nbsp;&nbsp; 
	                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" class="form-control" name="fechaHasta" value="${rform.fechaHasta}"  required="required">
	                                </div>
                            </div>
                            
                            </div>
                            
                              
                              

                          </div>
                             <div class="row">
                                <div class="col-lg-12 col-sm-12 col-xs-12 col-md-12 col-xl-6"> 
                              <div class="form-group">
                              
                                <div >
	                                     <input type="text" id="patente" name="patente" placeholder="patente" class="form-control text-uppercase">
	                             </div>
                              
                              
                              </div> 
                              </div>
                              
                              </div>    
                           
                            <!--  <div class="row"> 
                                
                              <div class="form-group">
                                <div class="col-lg-12 col-sm-12 col-xs-12 col-md-12 col-xl-6"> 
												<label>Clasificacion </label>
												<select data-placeholder="Seleccione clasificacion..." name="clasificacion"   style="width:200px;" tabindex="4" class="form-control text-uppercase">
														
														<option value=""></option>
														
														<c:forEach items="${clasificaciones}" var="c" varStatus="count" >	  
														
														   <c:if test="${not  empty c }">	     
																																		
															<option value="${c}">${c}</option>
												
												 		  </c:if>	    
												      </c:forEach>	    																											
												</select>												
							  </div>
							  </div>
							  
							  </div> -->
							  
							<!--    <div class="row"> 
									
							  <div class="form-group">
							   <div class="col-lg-12 col-sm-12 col-xs-12 col-md-12 col-xl-6">
												<label>Tipo </label>	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
												<select data-placeholder="Seleccione tipo..." name="tipo"   style="width:150px;" tabindex="3" class="form-control text-uppercase">
														
														<option value=""></option>													
														<option value="Camion">Camion</option>
														<option value="Rampla">Rampla</option>
																																						
												</select>
												
								</div> 
								</div>
								
							</div>  -->
							<div class="row">
							   <div class="col-lg-12 col-sm-12 col-xs-12 col-md-12 col-xl-6">
                                                                                                
                                <button class="btn btn-sm btn-primary" type="submit" >Buscar</button>
                                
                                </div>
                                
                             </div>
                                                                                    
                            </form:form>
                            
                            </div>
                                                                                    
                          
                        </div>
                    </div>  <!-- Fin bloque Fomr de Busqueda -->
         <c:if test="${not  empty rlist }">	  
        <div class="row">
			
			<div class="col-md-4">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <span class="label label-success pull-right">Fecha</span>
                            <h5></h5>
                        </div>
                        <div class="ibox-content">
                           
                            <div class="stat-percent font-bold text-success"> ${rform.fechaDesde} - ${rform.fechaHasta} <i class="fa fa-bolt"></i></div>
                           
                        </div>
                    </div>
                </div>
								
								
				</div>
		 </c:if>			
								   				   
			        
        
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>REPORTE DE PRODUCCIÓN POR TURNOS, FRENTE, OPERADOR, Y VEHÍCULO</h5>
                       
                       </div>
                                                             
                        	<div class="ibox-content">
								<div class="table-responsive">
									<table
										class="table table-striped table-bordered table-hover dataTables-example">
										<thead>
											<tr>
											<th>ID Camion</th>
                                            <th>Ubicacion Carguio</th>
                                            <th>Hora Carguio</th>
                                            <th>Hora Descarga</th>
                                            <th>Carga Real</th>
                                            <th>Ubicacion Descarga</th>
                                            <th>Tiempo Transporte</th>                                            
											</tr>
										</thead>
									 <tbody>
					
				    <c:if test="${not  empty rlist }">	    
					
						<c:forEach items="${rlist}" var="r" varStatus="count" >	  
							                                
		                                	<!-- <tr onClick="location.href='editarusuario.html?id=${r.idcamion}'">  -->
		                                	<tr>															                                    
		                                        <td>${r.idcamion}</td>
		                                        <td>${r.ubicacioncarguio}</td>
		                                        <td>${r.horainiciotransporte}</td>
		                                        <td>${r.horaterminotransporte}</td>
		                                        <td>${r.cargareal}</td>
		                                        <td>${r.ubicaciondescarga}</td>
		                                        <td>${r.tiempototaldescarga}</td>
		                                        
		                                      
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
    
    
    <script>
    
    $(document).ready(function(){
        $('.dataTables-example').DataTable({
            pageLength: 10,
            //"order": [[ 0, "asc" ]],
            "order": false,
            responsive: true,
            dom: '<"html5buttons"xºB>lTfgitp',
            buttons: [
                {extend: 'copy'},
                {extend: 'csv'},
                {extend: 'excel', title: 'REPORTE DE PRODUCCIÓN POR TURNOS'},
                {extend: 'pdf', title: 'REPORTE DE PRODUCCIÓN POR TURNOS'},

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