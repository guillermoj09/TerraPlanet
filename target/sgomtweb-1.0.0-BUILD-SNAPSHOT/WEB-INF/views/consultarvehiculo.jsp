<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
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
           	<%@include file="barramenu.jsp"%>
        
   <!-- Contenido principal  -->
        <div class="wrapper wrapper-content animated fadeInRight">
        
          <c:if test="${not empty mensaje}">
              
               <div class="row">
                            <div class="col-lg-12">
	       
			    <div class="${estilo}">
    				<button type="button" class="close" data-dismiss="alert">&times;</button>
    				<a href="#" class="alert-link"> ${mensaje}</a>    				
 				</div>
 				
 				</div>
                            </div>
	       
	      	     </c:if>
        
            <div class="row">
            
            
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Equipos</h5>
                       
                       </div>
                                                             
                        	<div class="ibox-content">
                        	<div class="col-12 " style="padding-bottom:20px;padding-top:10px">                                                                                                              
                                       <a class="btn btn-primary" href="crearvehiculo.html">Nuevo <i class="fa fa-plus"></i></a>
			                     </div> 
								<div class="table-responsive">
									<table
										class="table table-striped table-bordered table-hover dataTables-example">
										<thead>
											<tr>
												<th>Patente</th>		                                        
		                                        <th>N.Int</th>	                                        
		                                        <th>Modelo</th>
		                                        <th>Marca</th>
		                                        <th>Año</th>		                                        
		                                        <th>Tipo Vehiculo</th>
		                                        <th>Tags</th>

											</tr>
										</thead>
									 <tbody>
					
				    <c:if test="${not  empty vehiculos }">	    
					
						<c:forEach items="${vehiculos}" var="v" varStatus="count" >	  
							                                
		                                	<tr  onClick="location.href='editarvehiculo.html?id=${v.vehPatente}'">															                                    
		                                        <td>${v.vehPatente}</td>
		                                        <td>${v.nrointerno}</td>
		                                        <td>${v.vehModelo}</td>
		                                        <td>${v.vehMarca}</td>
		                                        <td>${v.vehAnio}</td>
		                                        <td>${v.tipoVehiculo.tipvDescripcion}</td>	
		                                        <td>
		                                        	<c:forEach items="${v.tags}" var="t" varStatus="count" >		                                        
		                                        		<b>${t.tagSerie}</b>	-	                                        
		                                        	</c:forEach>		                                        
		                                        </td>		                                        
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
                {extend: 'excel', title: 'Equipos'},
                {extend: 'pdf', title: 'Equipos'},

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
        
        $('td').mouseover(function() {
            $(this).addClass('rowselected');
        });
        $('td').mouseout(function() {
            $(this).removeClass('rowselected');
        });

    });
    		

    </script>

    
</body>

</html>