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

<style type="text/css">
html,body{
    height:100%;
}

</style>


 <link href="resources/inspinia_v2.9/FullVersion/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">


<link href="resources/inspinia_v2.9/FullVersion/css/plugins/switchery/switchery.css" rel="stylesheet">

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
                          <h5>Filtros <b>${b.menu}</b> &nbsp;&nbsp; &nbsp;&nbsp; <small> Buscar por ... </small></h5>                           
                          <div class="ibox-tools">
                              <a class="collapse-link">
                                  <i class="fa fa-chevron-up" id="ocultar"></i>
                              </a>
                          </div>
                      </div>    
                      <form:form action="diagramatiempo" commandName="reportePatenteFechaForm"  id="data_1" autocomplete="off">                  
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
                                                    
                                                     <div class="col-lg-8  ">   
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
                                                    
                                                     <div class="form-group col-lg-2 b-r" >
										 <label>Toda la flota</label>
										 <input type="checkbox" class=" " id = "allFlota"> 
										 </div>
										 
                                                    
                                                     <!-- <div class="col-lg-6  b-r">   
                                                      
                                                    </div>  -->    
                                                    
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
                    <div class="ibox float-e-margins ${border}" id="ibox1">
                        <div class="ibox-title">
                            <h5>Gráfico Tiempo Horas </h5>
                            
                        </div>
                        <div class="ibox-content" style="${iboxContent}">
                           <div id="chartContainer"  style="height: 500px; width: 100%;">
                           
                           <div style="right: 0px; bottom: 0px; position: fixed; z-index: 1000;" id="__ig_wm__" class="ui-igtrialwatermark"></div>
                           
                           <a class="canvasjs-chart-credit" title="JavaScript Charts" style="outline:none;margin:0px;position:absolute;right:2px;top:286px;color:dimgrey;text-decoration:none;font-size:20px;font-family: Calibri, Lucida Grande, Lucida Sans Unicode, Arial, sans-serif" tabindex="-1" target="_blank" href="https://canvasjs.com/">CanvasJS.com</a>
                           
                           </div>

                        </div>
                    </div>
                </div>
			
			  
			</div> 
        
        
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins ${border}" id="ibox1">
                        <div class="ibox-title">
                            <h5>Diagrama Tiempo Tabla</h5>
                       
                       </div>
                                                             
                        	<div class="ibox-content" style="${iboxContent}">
								<div class="table-responsive">
									<table
										class="table table-striped table-bordered table-hover dataTables-example">
										<thead>
											<tr>
												<th>Patente</th>
                                            	<th>NrInterno</th>
                                            	<th>En Ruta</th>
                                            	<th>Fuera Proyecto</th>
                                            	<th>Taller</th>
                                            	<th>En Geo</th>
                                            	<th>Mapa</th>
                                            	

											</tr>
										</thead>
									 <tbody>
					
				    <c:if test="${not  empty rlist }">	    
					
						<c:forEach items="${rlist}" var="r" varStatus="count" >	  
							                
		                                	<tr>															                                    
		                                        <td>${r.patente}</td>
		                                        <td>${r.nrointerno}</td>
		                                        <td>${r.tiemporuta}</td>
		                                        <td>${r.tiempofueraproyecto}</td>
		                                        <td>${r.tiempotaller}</td>
		                                        <td>${r.tiempogeo}</td>
		                                        <td title="Ver en Mapa"><font size="+1.5"><a style="color:#337ab7" class="link_mapa" link="maparuta.html?patente=${r.patente}&fechain=${rform.fechaDesde} ${rform.horaDesde}&fechafin=${rform.fechaHasta} ${rform.horaHasta}"><i class="fa fa-external-link"></i></a></font></td>
		                                        
		                                        
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
    
     <!--  Libreria para Grafico SCATTER CAMVAS la jquery.canvasjs.min2.js v2.2 es trial version la min2 es version 1.8 no funciona algunas librerias -->   
    <script src="resources/js/jquery.canvasjs.min.js"></script>
    
     <!-- select 2 -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/select2/select2.full.min.js"></script>
          
    <!-- Sweet alert -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/sweetalert/sweetalert.min.js"></script>
    
     <!-- hora -->
      <script src="resources/inspinia_v2.9/FullVersion/js/plugins/clockpicker/clockpicker.js"></script>
      
      <!-- Switchery -->
   <script src="resources/inspinia_v2.9/FullVersion/js/plugins/switchery/switchery.js"></script>
   
    <!-- iCheck -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/iCheck/icheck.min.js"></script>
        <script>
            $(document).ready(function () {
                $('.i-checks').iCheck({
                    checkboxClass: 'icheckbox_square-green',
                    radioClass: 'iradio_square-green',
                });
            });
        </script>
   
      
	
	  <!-- funciones de validacion de hora, efecto load para botones de busqueda y carga de pagina -->
       <%@include file="utiljs/loadpagebuttonjs.jsp"%>		
       <%@include file="utiljs/valclockpickerjs.jsp"%>
       <%@include file="utiljs/validarFechaHoraJs.jsp"%>				 
  
  
  <script type="text/javascript">
  
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
   
   
   $("#allFlota").click(function(){
       if($("#allFlota").is(':checked') ){
       	
       	$("#patente").find('option').prop("selected","selected");
       	$("#patente").find('option').trigger("change");
       	$("#patente").find('option').click();
           
       }else{
       	
       	//$("#menus").find('option').removeAttr("selected","selected");
       	$("#patente").find('option').prop("selected",false);
       	$("#patente").find('option').trigger("change");
        }
   });
   
  
window.onload = function() {
	var y = 1;
  //year, month, day, hours, minutes, seconds, milliseconds
  //11 diciembre 10 noviembre 00 enero 
  
	
	$("#chartContainer").CanvasJSChart({
		//height:300,
		title: {
			text: "Diagrama Tiempo Horas ",
			fontSize: 18,
			fontColor: '#676a6c',
			fontStyle: "normal",
		},
		animationEnabled: true,
		title:{
			text: "Horas",
			fontSize: 16,
			//labelFontSize: 12,
			fontColor: '#676a6c',
			fontStyle: "normal",
		},
		axisX: {
			//valueFormatString: "DDD"
			 fontColor: '#676a6c',
	    		//fontSize: 4,
	    		labelFontSize: 12,
	    		fontStyle: "normal",
	    		interval: 1,
		},
		axisY: {
			//valueFormatString: "DDD"
			 fontColor: '#676a6c',
	    		//fontSize: 12,
	    		fontStyle: "normal",
	    		labelFontSize: 10,
	    		interval: 1,
		},
		
		toolTip: {
			shared: true,
			fontColor: '#676a6c',
			fontStyle: "normal",
			labelFontSize: 12,			
			//fontSize: 14,
		},
		legend:{
			cursor: "pointer",
			fontColor: '#676a6c',
			fontStyle: "normal",
			//labelFontSize: 14,
			fontSize: 16,
			//itemclick: toggleDataSeries
		},
	data: [
		
		 <c:if test="${not  empty gralist }">
		
		  <c:forEach items="${gralist}" var="g" varStatus="count" >	  
		  
		  {
			    //indexLabelFontSize: 12,
			    type: "stackedBar",
				name: "${g.categoria}",
				showInLegend: "true",
				dataPoints: [
					
					  <c:forEach items="${g.diagramaTiempoGraficoDetalleActives}" var="d" varStatus="count" >	
					 
					  
					  { label: "${d.patente}", y: ${d.horasS} },//parseFloat(${d.horas}).toFixed(2) //Number.parseFloat(${d.horas}).toPrecision(2)
					  
					  </c:forEach>    
		  
		  ]
		  },
		  
		  </c:forEach>
		  
		  </c:if>
		
		  /*{
		type: "stackedBar",
		name: "En Ruta",
		showInLegend: "true",
		dataPoints: [
			{ label: "HDFF2", y: 4 },
			{ label: "HDFF3", y: 4 },
			
		]
	},
	{
		type: "stackedBar",
		name: "Fuera de Proycto",
		showInLegend: "true",
		dataPoints: [
			{ label: "HDFF2", y: 6 },
			{ label: "HDFF3", y: 2 },
		]
	},*/
	
	 ]
	});
    
}
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
                {extend: 'excel', title: 'DiagramaTiempo'},
                {extend: 'pdf', title: 'DiagramaTiempo'},

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
    
    $(document).on("click",".link_mapa",function(){
    	window.open($(this).attr('link'),"MAPA","top=100,left=300,width=800,height=650,scrollbars=NO,resizable=YES,class=lbOn" );
	});
    
    var elem = document.querySelector('.js-switch');
    var switchery = new Switchery(elem, { color: '#1AB394' });

    		

    </script>

    
</body>

</html>