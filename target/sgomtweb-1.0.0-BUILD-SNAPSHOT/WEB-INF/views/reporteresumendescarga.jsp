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
                          <h5>Filtros <b>Resumen Descarga</b> &nbsp;&nbsp; &nbsp;&nbsp; <small> Buscar por ... </small></h5>                           
                          <div class="ibox-tools">
                              <a class="collapse-link">
                                  <i class="fa fa-chevron-up" id="ocultar"></i>
                              </a>
                          </div>
                      </div>    
                      <form:form action="reporteresumendescarga.html" commandName="reportePatenteFechaForm"  id="data_1" autocomplete="off">                  
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
                                                    
                                                     <div class="col-lg-2  ">   
                                                      <div class="form-group">                                      
                                                              <label>Patentes </label>
                                                              <div>
                                                               <select  name="patente" id="patente"  style="width:100%;" tabindex="4" data-placeholder="Seleccione una Patente" class="form-control text-uppercase">
																					
															
														
														<c:if test="${not  empty rform.patente }">
														 <c:if test="${rform.patente eq 'todos'}">
														 	<option value="todos" selected>TODOS</option>
														  </c:if>
														   <c:if test="${rform.patente ne 'todos'}">														
														<option value="todos">TODOS</option>
														</c:if>	  
														 </c:if>	  
																											
														<c:forEach items="${listpatentes}" var="p" varStatus="count" >	  
														
														   <c:if test="${not  empty p }">	
														        
															 <option value="${p.vehPatenteVehiculo}" <c:if test="${p.vehPatenteVehiculo == rform.patente}"> selected</c:if>>${p.vehPatenteVehiculo}<c:if test="${not  empty p.nroInterno }"><p class="text-secondary"> - ${p.nroInterno}</p> </c:if></option>												
												
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
                                                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" class="form-control" name="fechaDesde"  value="${rform.fechaDesde}" required>
                                                               
                                                               
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
                                                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" class="form-control" name="fechaHasta" value="${rform.fechaHasta}" required>
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
               
               <div class="ibox-content" style="${iboxContent}">
               
                <div id="chartContainer" style="height: 370px; width: 100%;"></div>
        
   
        </div>
        </div>
        
        </div>
        
        
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins ${border}" id="ibox1">
                   
                    
                        <div class="ibox-title">
                            <h5>Resumen Descarga</h5>
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
                    			
								<div class="table-responsive"> <!-- class="table table-striped table-bordered table-hover dataTables-example"> -->
									<table
										class="table table-striped table-bordered table-hover dataTables-example">
										<thead>
											<tr>
											<th>Patente</th>
											<th>Fecha </th>                                                                                       
                                            <th>Descargas</th>
                                            <th>Produccion</th>                                                                                                                                   
											</tr>
										</thead>
									 <tbody>
					
				    <c:if test="${not  empty rlist }">	    
					
						<c:forEach items="${rlist}" var="v" varStatus="count" >	  
						
						  <c:if test="${not  empty v.listreporteResumenDescargaActive }">
			  
			  				<c:forEach items="${v.listreporteResumenDescargaActive}" var="r" varStatus="count" >
							           <c:if test="${r.vueltas > 0 }">	                           
		                                	<!-- <tr onClick="location.href='editarusuario.html?id=${r.patente}'">  -->
		                                	<tr>															                                    
		                                        <td>${r.patente}</td>
		                                        <td><fmt:formatDate value="${r.fecha}" pattern="dd/MM/yyyy" /> </td>		                                         
		                                        <td>${r.vueltas}</td>
		                                        <td>${r.produccion} Ton</td>		                                      
		                                    </tr>
		                                  </c:if>  
		                                    
		                                    	</c:forEach>           
		            </c:if>	
		                                    
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
            
    <!-- hora -->
      <script src="resources/inspinia_v2.9/FullVersion/js/plugins/clockpicker/clockpicker.js"></script>
      
        <!-- Chosen 
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/chosen/chosen.jquery.js"></script> -->
    
    <!-- grafico -->
    <script src="resources/js/jquery.canvasjs.min.js"></script>
    
    
      <!-- funciones de validacion de hora, efecto load para botones de busqueda y carga de pagina -->
       <%@include file="utiljs/loadpagebuttonjs.jsp"%>
       <%@include file="utiljs/valclockpickerjs.jsp"%>
       
       
  
             
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
          
    
    $( "#patente" ).select2( {
		placeholder: "Seleccione Patente",
		 allowClear: true
		
	} );

    $( "#faena" ).select2( {
		placeholder: "Seleccione Faena",
		 allowClear: true
		
	} );
        
	  //migrar a js puro
	 $(document).off("change","#patente").on("change","#patente", function()
            {                   
                $('#faena').prop('selectedIndex', 0);  $('#faena').select2();
            });


    $(document).off("change","#faena").on("change","#faena", function()
            { 
                $('#patente').prop('selectedIndex', 0); $('#patente').select2();          
            });

       
   </script>
   
    
    
    <script>
    
    var URLactual =window.location.protocol +"//"+ window.location.host;
    
    $(document).ready(function(){
        $('.dataTables-example').DataTable({
        	"language": {
                "url": URLactual + "/sgomtweb/resources/datetableespanil.json"
            },
        	pageLength: 10,
            //"order": [[ 2, "asc" ]],
            //"order": true,
            responsive: true,
            dom: '<"html5buttons"x�B>lTfgitp',
            buttons: [
                {extend: 'copy'},
                {extend: 'csv'},
                {extend: 'excel',    
                	title: 'InformeDescarga',
                	 exportOptions: {
                         columns: [ 0, 1, 2, 3, 4 ,5 ]
                	 }
                	
                },
                
                {extend: 'pdf', 
                	title: 'InformeDescarga',                    	
               	 exportOptions: {
                        columns: [ 0, 1, 2, 3, 4 ,5  ]
               	 }
                	
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

    		

    </script>
    
      <script>
    /*
    var isCanvasSupported = !!document.createElement("canvas").getContext; // You might want to remove this line if you are not planning on using it.

var theme = {
	Chart: {
		colorSet: "colorSet1"
	},
	Title: {
		//fontFamily: isCanvasSupported ? "Calibri, Optima, Candara, Verdana, Geneva, sans-serif" : "calibri",
		fontSize: 33,
		//fontColor: "#3A3A3A",
		fontWeight: "bold",
		verticalAlign: "top",
		margin: 10
	},
	Axis: {
		titleFontSize: 28,
		titleFontColor: "#666666",
		//titleFontFamily: isCanvasSupported ? "Calibri, Optima, Candara, Verdana, Geneva, sans-serif" : "calibri",

		//labelFontFamily: isCanvasSupported ? "Calibri, Optima, Candara, Verdana, Geneva, sans-serif" : "calibri",
		labelFontSize: 28,
		//labelFontColor: "grey",
		//tickColor: "#BBBBBB",
		tickThickness: 2,
		gridThickness: 2,
		//gridColor: "#BBBBBB",
		lineThickness: 2,
		//lineColor: "#BBBBBB"
	},
	Legend: {
		verticalAlign: "bottom",
		horizontalAlign: "center",
		fontFamily: isCanvasSupported ? "monospace, sans-serif,arial black" : "calibri"
	},
	DataSeries: {
		//bevelEnabled: true,
		//indexLabelFontColor: "grey",
		//indexLabelFontFamily: "Trebuchet MS, monospace, Courier New, Courier",
		//indexLabelFontFamily: isCanvasSupported ? "Calibri, Optima, Candara, Verdana, Geneva, sans-serif" : "calibri",
		//indexLabelFontWeight: "bold",
		indexLabelFontSize: 18,
		//indexLabelLineColor: "lightgrey",
		indexLabelLineThickness: 1
	}
}
CanvasJS.addTheme('inspinia',theme); // You can use any name */

</script>
 
 <script>
window.onload = function () {

var chart = new CanvasJS.Chart("chartContainer", {
	animationEnabled: true,
	//theme: 'inspinia',
	title:{
		text: "Grafico Resumen Descarga",
		//fontStyle: "italic",
		fontSize: 18,
		fontColor: '#676a6c',
		fontStyle: "normal",
	},
	axisX: {
		//valueFormatString: "DD MMM,YY",
		valueFormatString: "DD/MM/YYYY",
		fontColor: '#676a6c',
		fontSize: 15,
		fontStyle: "normal",
		//margin: 10,
	},
	axisY: {
		title: "Descargas",
		includeZero: false,
		fontStyle: "normal",
		//suffix: " �C"
	},
	legend:{
		cursor: "pointer",
		fontSize: 15,
		itemclick: toggleDataSeries,
		fontColor: '#676a6c',
		legendMarkerType: "circule",
		fontStyle: "normal",
	},
	toolTip:{
		shared: true,
		fontColor: '#676a6c',
		fontStyle: "normal",
		//fontFamily: "calibri",
		fontSize: 14,
	},
	data: [
		
		  <c:if test="${not  empty rlist }">
		  
		  <c:forEach items="${rlist}" var="v2" varStatus="count" >	
		  <c:if test="${not  empty v2.listreporteResumenDescargaActive }">
		
		{
		name: "${v2.vehPatente}",
		type: "splineArea",
		yValueFormatString: "#0.## ",
		showInLegend: true,
		dataPoints: [
			
			 			  
			  <c:forEach items="${v2.listreporteResumenDescargaActive}" var="r2" varStatus="count" >	
			  
			  <c:if test="${r2.vueltas > 0 }">	
			
			{ x: new Date(${r2.ano},${r2.mes},${r2.dia}), y: ${r2.vueltas}  },
			
			  </c:if>
			
			  <c:if test="${r2.vueltas == 0 }">	
			  
				{ x: new Date(${r2.ano},${r2.mes},${r2.dia}), y:  ${r2.vueltas} ,  color: "red"},
			  
			  </c:if>   
			
			</c:forEach>           
			
						
		]
	},
	 </c:if>
	 </c:forEach>           
    </c:if>	
	
   /* {  // ejemplo de la estructura
	name: "KFHW83",
	type: "spline",
	yValueFormatString: "#0.## ",
	showInLegend: true,
	//axisYType: "secondary", // para mostrar y comparar otro valor en el otro lado del grafico
	dataPoints: [
		{ x: new Date(2019,1,24), y: 6 ,  indexLabel: "Inactivo", indexLabelFontColor: "#C24642", color: "#C24642",  markerType: "square"},
		{ x: new Date(2019,1,25), y: 7, indexLabel: "Inactivo", indexLabelFontColor: "#C24642", color: "#C24642" },
		{ x: new Date(2019,1,26), y: 55 ,indexLabel: "Inactivo", indexLabelFontColor: "#C24642", color: "#C24642"},
		{ x: new Date(2019,1,27), y: 2 },
		{ x: new Date(2019,1,28), y: 44 },
		
	]
},*/
    
	]
});
chart.render();
chart.render();

function toggleDataSeries(e){
	if (typeof(e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
		e.dataSeries.visible = false;
	}
	else{
		e.dataSeries.visible = true;
	}
	chart.render();
}

}
//https://canvasjs.com/javascript-charts/multi-series-chart/
</script>
   
    
</body>

</html>