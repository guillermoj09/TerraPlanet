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
           <!--  <div class="row wrapper border-bottom white-bg page-heading">
                <div class="col-lg-10">
                    <h2></h2>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="">Administracion</a>
                        </li>
                                               
                        
                        <li class="breadcrumb-item active">
                            <strong>Maestro de Operadores</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div> -->
        
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
                            <h5>iButton</h5>
                       
                       </div>                                                             
                        	<div class="ibox-content">                        	
                        	<div class="col-12 " style="padding-bottom:20px;padding-top:10px">                                                                                                              
                                       <a class="btn btn-primary" href="creaributton">Nuevo <i class="fa fa-plus"></i></a>
			                     </div> 
                        	
                        	  <!--   <div class="col-lg-12" style="margin-bottom:10px;">                                                              
                                       <button class="btn btn-success btn-outline btn_ruta"  title="Nuevo tag" type="button"><i class="fa fa-plus"></i>&nbsp;&nbsp;<span class="bold"><a href="creartag.html">Nuevo</a></span></button>&nbsp;&nbsp;&nbsp;&nbsp;
                                                                                                       
                                    </div>  -->            
                        	
								<div class="table-responsive">
									<table
										class="table table-striped table-bordered table-hover dataTables-example">
										<thead>
											<tr>
												<th>iButton</th>	                                        
		                                        <th>Fecha Creacion</th>
		                                        <th>Estado</th>
		                                        <th>Opciones</th>		                                                                               	                                        
											</tr>
										</thead>
									 <tbody>
					
				    <c:if test="${not  empty ibutton }">	    
					
						<c:forEach items="${ibutton}" var="c" varStatus="count" >	  
							                                
		                                	 <tr> 		                                																                                    
		                                        <td>${c.codigo}</td>
		                                        <td><fmt:formatDate value="${c.fecha_crea}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
		                                        <td>
		                                        <c:choose>
												    <c:when test="${c.estado == 1}">
												        <span class="label label-primary"  style="font-size:12px">Activo</span>
												    </c:when>
												    <c:when test="${c.estado == 0}">
												        <span class="label label-danger"  style="font-size:12px">Inactivo</span>
												    </c:when>
												    <c:otherwise>
												        <span class="label label-plain"  style="font-size:12px">Otro</span>
												    </c:otherwise>
												</c:choose>
		                                        
		                                        </td>
		                                        <td>
		                                        <c:choose>
												    <c:when test="${c.estado == 1}">
												        <i class="fa fa-trash eliminaibutton" id_ibu="${c.id}" ibutton="${c.codigo}" style="font-size:20px" ></i>
												    </c:when>
												</c:choose>
												</td>	                                        		                                                                                		                                      
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
        
        
 $(document).on('click','.eliminaibutton', function(event){
        	
				 ibutton = $(this).attr("ibutton");
				 id_ibu= $(this).attr("id_ibu");
        	        	
        	swal({
        		title: "",
                text: "Esta seguro que desea eliminar el iButton: "+ ibutton +" ?",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Si, Eliminar",
                cancelButtonText: "Cancelar",
                closeOnConfirm: false
            }, function () {
                swal("", "El iButton ha sido eliminado.", "success");
                location.href="eliminaibutton?id_ibu="+ id_ibu;
            });

        	
        })

    });
    		

    </script>

    
</body>

</html>