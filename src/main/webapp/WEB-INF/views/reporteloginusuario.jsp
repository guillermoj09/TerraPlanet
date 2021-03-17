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
	
<body class="top-navigation gray-bg">

  	<div id="wrapper">

		<%@include file="menu.jsp"%>

		<div id="page-wrapper" class="gray-bg">

			<%@include file="barramenu.jsp"%>
         <!--    <div class="row wrapper border-bottom white-bg page-heading">
                <div class="col-lg-10">
                    <h2></h2>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="">Gestion de Flota</a>
                        </li>
                        <li class="breadcrumb-item active">
                            <strong>Historico</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>  -->
        
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
	    <c:when test="${rform.collapseshow == 'off'}">
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
                          <h5>Filtros <b>Accesos Usuario</b> &nbsp;&nbsp; &nbsp;&nbsp; <small> Buscar por ... </small></h5>                           
                          <div class="ibox-tools">
                              <a class="collapse-link">
                                  <i class="fa fa-chevron-up"></i>
                              </a>
                          </div>
                      </div>         
                       <form:form action="reporteloginusuario" commandName="reportePatenteFechaForm"  id="data_1" autocomplete="off">                
                      <div class="ibox-content">
                       
                                  
                                       
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
                                                    <div class="col-lg-2 b-r">
                                                        <div class="form-group">
                                                            <label class="control-label">&nbsp; Hora Hasta</label>&nbsp;&nbsp; &nbsp;&nbsp; 
                                                            <div class="input-group clockpicker" data-autoclose="true">
                                                                <input type="text" class="form-control" name="horaHasta" id="horaHasta" value="${rform.horaHasta}" >
                                                                <span class="input-group-addon"><span class="fa fa-clock-o"></span></span>
                                                            </div>                                       
                                                        </div> 
                                                    </div>
                                                   
                                                    
                                                   
                                                </div>  
                                                <div class="row">                               
                                                    <div class="col-lg-10 ">
                                                        <div class="form-group">                                                                                                   
                                                        </div> 
                                                    </div>
                                                    <div class="col-lg-2 text-center">                                                        
                                                        <button class="ladda-button btn btn-primary" id="toggleSpinners2" data-style="contract" type="submit" style="margin-top:20px;">Filtrar</button>                                                        
                                                    </div>
                                                 </div>                                                                              
                                            
                                    
                                                        

                                              
                      </div>  
                      
                           </form:form>                       
                    </div>
                </div>
            </div>  <!-- Fin bloque Fomr de Busqueda -->   
         
<!--- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->       
      
       <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins ${border}" id="ibox1">                    
                        <div class="ibox-title">
                            <h5>Reporte Acceso Usuarios</h5>
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
											<th>usuario</th>
                                            <th>Rut</th>
                                            <th>Ingreso</th>                                            
                                            <th>Salida</th> 
                                            <th>IP</th> 
                                            <th>Sistema Operatipo</th> 
                                                                                                                                   
											</tr>
										</thead>
									 <tbody>	
									 
											<c:forEach items="${rlist}" var="r" varStatus="count" >														
											   <c:if test="${not  empty r }">	
											   <tr onClick="location.href='reporteloginusuariodetalle?id=${r.id}'">																	   															   
											  		
											  		   	<td>${r.usuLogin}</td>															                                    
						                                  <td>${r.usuRut}</td>
						                                 
						                                  <td><fmt:formatDate value="${r.ingreso}" pattern="dd/MM/yyyy HH:mm:ss" /> </td>
						                                  <td><fmt:formatDate value="${r.salida}" pattern="dd/MM/yyyy HH:mm:ss" /> </td>							                                  					                                  						                                   
						                                  <td>${r.ip}</td>
						                                  <td>${r.sistemaOperativo}</td>						                                    
						                                 		                                        	                                      
						                              </tr>																	   														
									 		   </c:if>	    
								     		</c:forEach>				 
	                                    	                         
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
	 
	   <!-- Date range use moment.js same as full calendar plugin -->
	    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/fullcalendar/moment.min.js"></script>
	         
    <!-- Date range picker -->
	<script src="resources/inspinia_v2.9/FullVersion/js/plugins/daterangepicker/daterangepicker.js"></script>
   <!-- Data picker -->
   <script src="resources/inspinia_v2.9/FullVersion/js/plugins/datapicker/bootstrap-datepicker.js"></script>
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/dataTables/datatables.min.js"></script>
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/dataTables/dataTables.bootstrap4.min.js"></script>
         <!-- hora -->
      <script src="resources/inspinia_v2.9/FullVersion/js/plugins/clockpicker/clockpicker.js"></script>
      
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
	
    var URLactual =window.location.protocol +"//"+ window.location.host;
    
	 
	
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
        
        $(document).ready(function(){
            $('.dataTables-example').DataTable({
            	"language": {
                    "url": URLactual + "/sgomtweb/resources/datetableespanil.json"
                },
            	pageLength: 10,
                "order": [[ 2, "desc" ]],
                //"order": false,
                responsive: true,
                dom: '<"html5buttons"xºB>lTfgitp',
                buttons: [
                    {extend: 'copy'},
                    {extend: 'csv'},
                    {extend: 'excel',    
                    	title: 'AccesoUsuarios'
                    	                     	
                    },
                    
                    {extend: 'pdf', 
                    	title: 'AccesoUsuarios'                    	
                                       	
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
	    
    
    </script>
	

	

</body>

</html>

