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
	       									<div class="${estilo}">
	       									<button type="button" class="close" data-dismiss="alert">&times;</button>
  												<a href="#" class="alert-link">${mensaje}</a>
											</div>	
										</div>	       
	       						
                            
                            </div>
                            </div>
           			</c:if>
            
            <div class="row">
                             
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Conductores</h5>
                       
                       </div>                                                             
                        	<div class="ibox-content">                        	
                        	<div class="col-12 " style="padding-bottom:20px;padding-top:10px">                                                                                                              
                                       <a class="btn btn-primary" href="crearconductor">Nuevo <i class="fa fa-plus"></i></a>
			                     </div> 
                        	
                        	  <!--   <div class="col-lg-12" style="margin-bottom:10px;">                                                              
                                       <button class="btn btn-success btn-outline btn_ruta"  title="Nuevo tag" type="button"><i class="fa fa-plus"></i>&nbsp;&nbsp;<span class="bold"><a href="creartag.html">Nuevo</a></span></button>&nbsp;&nbsp;&nbsp;&nbsp;
                                                                                                       
                                    </div>  -->            
                        	
								<div class="table-responsive">
									<table
										class="table table-striped table-bordered table-hover dataTables-example">
										<thead>
											<tr>
												<th>Rut</th>		                                        
		                                        <th>Nombres</th>	                                        
		                                        <th>Apellidos</th>
		                                        <th>Cliente</th>
		                                         <th>Ibuttoms</th>
		                                        <!-- <th>Opciones</th>  -->		                                                                               	                                        
											</tr>
										</thead>
									 <tbody>
					
				    <c:if test="${not  empty conductores }">	    
					
						<c:forEach items="${conductores}" var="c" varStatus="count" >	  
							                                
		                                	 <tr onClick="location.href='editarconductor?id=${c.rut}'"> 
		                                																                                    
		                                        <td>${c.rut}</td>
		                                        <td>${c.nombre}</td>
		                                        <td>${c.apellido}</td>
		                                        <td>${c.cliRazonSocial}</td>
		                                        
		                                        <td>
		                                        		<c:forEach items="${c.ibuttoms}" var="b" varStatus="count" >	
		                                        		${b.ibuCodigo} - 
		                                        		</c:forEach>  
		                                        
		                                        
		                                        
		                                        </td>
		                                        
		                                        
		                                        <!-- <td><font size="+1.5" color="green" title="Editar" style="cursor:pointer" onClick="location.href='editarconductor?id=${c.rut}'"><i class="fa fa-edit"></i></font>
				                                     		&nbsp;&nbsp;&nbsp;&nbsp;<font size="+1.5" color="red" title="Eliminar" style="cursor:pointer" ><i id ="borraConductor" name ="borraConductor" rut="${c.rut}" class="fa fa-trash"></i></font> </td>  -->		                                                                                		                                      
		                                    </tr>
		           		</c:forEach>           
		            </c:if>		                                    	                         
		                                </tbody>
									</table>
								</div>															
								
							</div> <!--  fin ibox-content  -->
                               		                                   
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
    
      <!-- Sweet alert -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/sweetalert/sweetalert.min.js"></script>
    
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
                {extend: 'excel', title: 'Operadores'},
                {extend: 'pdf', title: 'Operadores'},

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
        
        
 $(document).on('click','#borraConductor', function(event){
        	
        	rut = $(this).attr("rut");
        	        	
        	swal({
        		title: "",
                text: "Esta seguro que desea eliminar el Rut: "+ rut +" ?",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Si, Eliminar",
                cancelButtonText: "Cancelar",
                closeOnConfirm: false
            }, function () {
                swal("", "El conductor ha sido eliminada.", "success");
                location.href="eliminamarconductor?rut="+ rut;
            });

        	
        })

    });
    		

    </script>

    
</body>

</html>