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
	
    
         
<!--- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->       
      
       <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins" id="ibox1">                    
                        <div class="ibox-title">
                            <h5>Reporte Usuarios Detalles</h5>
                            <div class="ibox-tools">
                              <a class="collapse-link">
                                  <i class="fa ${link}"></i>
                              </a>
                          </div>
                        </div>                                                             
                        	<div class="ibox-content" style="">
                        	
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
											<th>Menu</th>
											<th>Modulo</th>
                                            <th>Fecha</th>
                                            <th>Hora</th>                                            
                                            <th>Url</th>                                                                                                                                  
											</tr>
										</thead>
									 <tbody>	
									 
											<c:forEach items="${rlist}" var="r" varStatus="count" >														
											   <c:if test="${not  empty r }">	
											   <tr>																	   															   
											  		
											  		   	<td>${r.menu.menNombre}</td>															                                    
						                                  <td>${r.menu.modulo.modNombre}</td>
						                                 
						                                  <td><fmt:formatDate value="${r.horaIngreso}" pattern="dd/MM/yyyy" /> </td>
						                                  <td><fmt:formatDate value="${r.horaIngreso}" pattern="HH:mm:ss" /> </td>							                                  					                                  						                                   
						                                  <td>${r.url}</td>
						                                  						                                    
						                                 		                                        	                                      
						                              </tr>																	   														
									 		   </c:if>	    
								     		</c:forEach>				 
	                                    	                         
		                                </tbody>
		                                
		                               
									</table>
									  <div class="row">
									  <div class="col-md-10 col-sm-10 col-lg-10 b-r">                                                                                                              
				                                 <button class="btn btn-default " type="button" id="volver"><strong><span class="fa fa-caret-left"></span><a href="javascript:history.back(1)"> Volver</a></strong></button>
			                          </div>
			                           </div>
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
   
	<script>
	
	
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
                "order": [[ 3, "desc" ]],
                //"order": false,
                responsive: true,
                dom: '<"html5buttons"xºB>lTfgitp',
                buttons: [
                    {extend: 'copy'},
                    {extend: 'csv'},
                    {extend: 'excel',    
                    	title: 'UsuariosDetalle'
                    	                     	
                    },
                    
                    {extend: 'pdf', 
                    	title: 'UsuarioDetalle'                    	
                                       	
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

