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
                          <h5>Filtros <b>Graficos Toneladas</b> &nbsp;&nbsp; &nbsp;&nbsp; <small> Buscar por ... </small></h5>                           
                          <div class="ibox-tools">
                              <a class="collapse-link">
                                  <i class="fa fa-chevron-up" id="ocultar"></i>
                              </a>
                          </div>
                      </div>    
                      <form:form action="reporteindicadoroptimocarga.html" commandName="reportePatenteFechaForm"  id="data_1" autocomplete="off">                  
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
                    <div class="ibox float-e-margins ${border}" id="ibox1">
                        <div class="ibox-title">
                            <h5>Gráfico de toneladas transportadas por horario </h5>
                            
                        </div>
                        <div class="ibox-content" style="${iboxContent}">
                           <div id="chartContainer" style="width: 100%; height: 300px">
                           
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
                            <h5>Grafico Tonelada Transportada</h5>
                       
                       </div>
                                                             
                        	<div class="ibox-content" style="${iboxContent}">
								<div class="table-responsive">
									<table
										class="table table-striped table-bordered table-hover dataTables-example">
										<thead>
											<tr>
												<th>Nro. de Vuelta</th>
                                            	<th>Hora Despacho </th>
                                            	<th>ID Camion </th>
                                            	<th>Carga Real  </th>
                                            	<th>Carga Nominal </th>
                                            	<th>Porcentaje de Carga  </th>

											</tr>
										</thead>
									 <tbody>
					
				    <c:if test="${not  empty rlist }">	    
					
						<c:forEach items="${rlist}" var="r" varStatus="count" >	  
							                                
		                                	<!-- <tr onClick="location.href='editarusuario.html?id=${r.nrovuelta}'">  -->
		                                	<tr>															                                    
		                                        <td>${r.nrovuelta}</td>
		                                        <td>${r.horasdespacho}</td>
		                                        <td>${r.idcamion}</td>
		                                        <td>${r.cargareal}</td>
		                                        <td>${r.carganominal}</td>
		                                        <td>${r.porcentajedecarga}<span class="pie">${r.porcentajedecarga}/100</span></td>
		                                        
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
  
window.onload = function() {
	var y = 1;
  //year, month, day, hours, minutes, seconds, milliseconds
  //11 diciembre 10 noviembre 00 enero 
  
  var dataPoints = [
	  
	  <c:if test="${not  empty rlist }">
	  
	  <c:forEach items="${rlist}" var="rr" varStatus="count" >	  
	  
	  { x:  new Date(${rr.ano}, ${rr.mes}, ${rr.dia}, ${rr.hora}, ${rr.min}, ${rr.seg}, 0), y: ${rr.tonelada}},
	  
	  </c:forEach>           
      </c:if>	
	  
	  
				//	{ x:  new Date(2018, 10, 24, 10, 33, 30, 0), y: 23.4},
				//	{ x:  new Date(2018, 10, 24, 10, 34, 30, 0), y: 43.4},
				//	{ x:  new Date(2018, 10, 24, 10, 39, 30, 0), y: 53.4},
				//	{ x:  new Date(2018, 10, 24, 10, 53, 30, 0), y: 33.4},
				//	{ x:  new Date(2018, 10, 24, 11, 43, 30, 0), y: 63.4},
				//	{ x:  new Date(2018, 10, 24, 11, 45, 30, 0), y: 71.5},
				//	{ x:  new Date(2018, 10, 24, 12, 43, 30, 0), y: 43.8},
               //     { x:  new Date(2018, 10, 24, 15, 53, 30, 0), y: 21.2}
                    
                    //{ x: 2, y: 5}, { x: 1, y: 1},
                    //{ x: 1, y: 2}, { x: 1, y: 5}
                   
                   ]; 
  //for(var i = 1 ; i <= 30 ; i++) {
  //	dataPoints.push({ x: i, y: Math.round(Math.random(i) * //70)});   
  //	y += 1;
  //    }
  
	
	$("#chartContainer").CanvasJSChart({
		title: {
			text: "Diagrama Horas ",
			fontSize: 18,
			fontColor: '#676a6c',
			fontStyle: "normal",
		},
		axisX: {
			interval: 1,
            valueFormatString: "hh:mm TT",
            gridThickness: 1,
            tickLength: 0,
            fontColor: '#676a6c',
    		fontSize: 15,
    		fontStyle: "normal",
		},
       axisY: [
       {
         title: "",
         //lineColor: "#4F81BC",
         //tickColor: "#4F81BC",
         //labelFontColor: "#4F81BC",
         //titleFontColor: "#4F81BC",
          //lineThickness: 2,
         //interlacedColor: "#F8F1E4",
         //tickLength: 0,
         fontColor: '#676a6c',
 		fontSize: 15,
 		fontStyle: "normal",
         
         stripLines:[
			{
				value:47,
				label: "120%",
                color:"red",
              thickness:3,
				labelPlacement:"inside",//"outside"
			},
           {
				value:38,
				label: "110%",
                 color:"yellow",
             thickness:3,
             labelPlacement:"inside",//"outside"
			},
           {
				value:33,
				label: "100%",
                color:"green",
             thickness:3,
				labelPlacement:"inside",//"outside"
			}
			],
           
      },
      
      {
         title: "TONELADAS",
         lineColor: "#C0504E",
         tickColor: "#C0504E",
         labelFontColor: "#C0504E",
         titleFontColor: "#C0504E",
        lineThickness: 2
      }
    ], 
    toolTip:{
		shared: true,
		fontColor: '#676a6c',
		fontStyle: "normal",
		//fontFamily: "calibri",
		fontSize: 14,
	},
		data: [
		{
			type: "scatter", //canvasjs-chart-tooltip  estilo
			fontColor: '#676a6c',
	 		fontSize: 15,
	 		fontStyle: "normal",
			xValueFormatString:"HH:mm",
			//yValueFormatString:"HH:mm",			
            toolTipContent: "{label} hora: {x}  toneladas: {y} ",
			dataPoints: dataPoints,
			
		}
          /*,
          {
       type: "stepLine",
       markerSize: 1, 
            //axisYIndex: 1,
       dataPoints: [      
          { x:  new Date(2018, 11, 24, 09, 10, 30, 0), y: 50 },
         // { x:  new Date(2018, 11, 24, 09, 33, 30, 0), y: 70 },
         { x:  new Date(2018, 11, 24, 10, 45, 30, 0), y: 50 }
          
        
       ]
       }*/
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
            "order": false,
            responsive: true,
            dom: '<"html5buttons"xºB>lTfgitp',
            buttons: [
                {extend: 'copy'},
                {extend: 'csv'},
                {extend: 'excel', title: 'ReporteIndicadorOptimoCarga'},
                {extend: 'pdf', title: 'ReporteIndicadorOptimoCarga'},

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