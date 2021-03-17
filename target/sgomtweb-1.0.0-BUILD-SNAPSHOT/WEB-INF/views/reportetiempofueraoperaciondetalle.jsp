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
	    <c:when test="${collapseshow == 'SI'}">
	    
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
         
           
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins ${border}" id="ibox1">
                   
                    
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
											<th>Patente</th>
											<th>Nro internp</th>
                                            <th>Chofer</th>
                                            <th>Fecha</th>                                            
                                            <th>Ubicacion</th> 
                                            <th>Vel (Km/H)</th> 
                                            <th>Evento</th> 
                                            <th title="Orientacion">ORIENT.</th>
                                            <th>MAPA</th>
                                                                                        
											</tr>
										</thead>
									 <tbody>
					
				    <c:if test="${not  empty rlist }">	    
					
						<c:forEach items="${rlist}" var="r" varStatus="count" >	  
							                                
		                                	 <tr>   															                                    
		                                        <td>${r.patente}</td>
		                                        <td>${r.nrointerno}</td>
						                        <td>${r.chofer}</td>
						                        <td><fmt:formatDate value="${r.fechain}" pattern="dd/MM/yyyy HH:mm:ss" /> </td>						                                  						                        						                                
						                        <td>${r.ubicacion}</td>
						                        <td>${r.vel}</td>
						                         <c:if test="${r.idEvent == 64}">
						                          <td><font color="#31B404">${r.nombreve}</font></td>
						                         </c:if>
						                          <c:if test="${r.idEvent == 65}">
						                           <td><font color="#FF0040">${r.nombreve}</font></td>
						                          </c:if>
						                           <c:if test="${r.idEvent == 61}">
						                           <td><font color="#FF0040">${r.nombreve}</font></td>
						                          </c:if>
						                       
						                        <td title="${r.nomflecha}"><img src="resources/img/historico/${r.rutaflecha}" width="16" height="16"><span style="display:none">${r.nomflecha}</span></td>						                                  						                         
						                        <td title="Ver en Mapa"><font size="+1.5"><a style="color:#337ab7" class="link_mapa" link="mapaxpatentereporte.html?patente=${r.patente}&lat=${r.lat}&lon=${r.lon}"><i class="fa fa-external-link"></i></a></font></td>
		                                      
		                                    </tr>
		           		</c:forEach>           
		            </c:if>		                                    	                         
		                                </tbody>
		                               
									</table>
									
										<div class="col-md-12 col-sm-12 col-lg-12 b-r">                                                                                                              
				                        
				                                  <button class="ladda-button btn btn-primary" id="volver" data-style="contract" style="margin-top:20px;margin-right:20px;">Volver</button>
				                         
				                                
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
    
      <!-- funciones de validacion de hora, efecto load para botones de busqueda y carga de pagina -->
       <%@include file="utiljs/loadpagebuttonjs.jsp"%>
       <%@include file="utiljs/valclockpickerjs.jsp"%>
    
    <script>
    
var volver = document.getElementById('volver');
  	
	volver.addEventListener("click", volverFuncion);
	
	function volverFuncion () {
		
		window.history.back()
		    		
	}  	 
    
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
                {extend: 'excel', title: 'ReporteTiempoFueraOperacionDetalle'},
                {extend: 'pdf', title: 'ReporteTiempoFueraOperacionDetalle'},

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
    
    $(document).on("click",".link_mapa",function(){
    	window.open($(this).attr('link'),"MAPA","top=100,left=300,width=800,height=650,scrollbars=NO,resizable=YES,class=lbOn,location=0,status=0, directories=0, toolbar=0, titlebar=0" );
	});
    

    </script>

    
</body>

</html>