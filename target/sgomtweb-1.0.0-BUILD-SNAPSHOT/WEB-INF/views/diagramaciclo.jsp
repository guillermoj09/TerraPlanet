<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
                      <form:form action="diagramaciclo" commandName="reportePatenteFechaForm"  id="data_1" autocomplete="off">                  
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
                                                              <label>Carguio </label>
                                                              <div>
                                                               <select  name="geo" id="geo"  style="width:100%;" tabindex="4" data-placeholder="Seleccione Carguio" class="form-control text-uppercase" > <!-- required -->
																					
														<option value=""></option>	
																											
														<c:forEach items="${geocercas}" var="g" varStatus="count" >	  
														
														   <c:if test="${not  empty g }">	
														        
														   <c:if test="${ (g.id_geo != 107)  &&  (g.id_geo != 62) }">
															 <option value="${g.id_geo}" <c:if test="${g.id_geo == rform.geo}"> selected</c:if>>${g.nombre}</option>													
															<												
												 		  </c:if>	
												 		  </c:if>    
												      </c:forEach>	    																											
												</select>		
                                                              </div>                      
                                                      </div>
                                                    </div>  
                                                    
                                                     <div class="col-lg-2  ">   
                                                      <div class="form-group">                                      
                                                              <label>Descarga </label>
                                                              <div>
                                                               <select  name="geo2" id="geo2"  style="width:100%;" tabindex="4" data-placeholder="Seleccione Descarga" class="form-control text-uppercase" >
																					
														<option value=""></option>	
																											
														<c:forEach items="${geocercas}" var="g" varStatus="count" >	  
														
														   <c:if test="${not  empty g }">	
														        
														   <c:if test="${ (g.id_geo != 107)  &&  (g.id_geo != 62) }">
															 <option value="${g.id_geo}" <c:if test="${g.id_geo == rform.geo2}"> selected</c:if>>${g.nombre}</option>													
															<												
												 		  </c:if>	
												 		  </c:if>    
												      </c:forEach>	    																											
												</select>		
                                                              </div>                      
                                                      </div>
                                                    </div>  
                                                    
                                                     <div class="col-lg-2  ">   
                                                      <div class="form-group">                                      
                                                              <label>Patentes </label>
                                                              <div>
                                                               <select  name="patente" id="patente"  style="width:100%;" tabindex="4" data-placeholder="Seleccione una Patente" class="form-control text-uppercase">
																					
														<option value=""></option>	
														
														<c:if test="${not  empty rform.patente }">
														 <!-- <option value="${rform.patente}" selected>${rform.patente}</option>  -->  
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
                                                    
                                                     <div class="col-lg-4  b-r">   
                                                      
                                                    </div>    
                                                    
                                                      <div class="col-lg-2 text-center">                                                                                                              
                                                              <button class="ladda-button btn btn-primary btn_submit" id="toggleSpinners" data-style="contract" type="submit" style="margin-top:20px;">Buscar</button>
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
                                                              <button class="ladda-button btn btn-primary btn_submit" id="toggleSpinners2" data-style="contract" type="submit" style="margin-top:20px;">Filtrar</button> 
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
				   
				   <div class="ibox float-e-margins">
				   
					<!--	<h5>Diagrama Entrego por Talabre</h5> 	 -->
					<div class="ibox-title">
					 <h5>Diagrama Ciclo</h5>
					<div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>       
                            </div>
						  </div>			  
                       <div class="ibox-content" style="${iboxContent}">					  
						<div id="myDiagramDiv" style="width:auto; height:auto; background-color: #FFFFFF; min-width:100px; min-height:100px;">
					
						</div>
						
						 <div class="row">                        
                        	<div class="col-lg-12">
                        	<span class="float-right">
    
                        				<button class="btn btn-success btn_kml" title="Abri a IMG" type="button"  id="imgdiagrama" style="margin-top:20px;margin-right:13px;"value="Exportar"> <i class="fa fa-file-image-o"></i>&nbsp;&nbsp;Abrir Imagen</button> 
                             
                                 	                                                                                                                              		                                                                                              
                                     </span>     
                                </div>                  	
                    	</div>  
						
					</div>	
    											
						
				   </div>
				   
				   
				   </div>
				   
			
            </div>
            
             <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins ${border}" id="ibox1">
                   
                    
                        <div class="ibox-title">
                            <h5>Reporte ${b.menu}</h5>
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
											<th>Origen</th>                                                                                       
                                            <th>Destino</th>
                                            <th>Patente</th>                                           
                                            <th>Vueltas</th>
                                            <th>Tiempo</th>
                                            <th>Carga</th>                                                                   
											</tr>
										</thead>
									 <tbody>
					
				    <c:if test="${not  empty rlist }">	    					
						<c:forEach items="${rlist}" var="r" varStatus="count" >		                                	
                        	<tr>															                                    
                                <td>${r.geoNombreIni}</td>
                                <td>${r.geoNombreFin}</td>		                                       		                                       		                                    							                                      				                                     
                                <td>${r.patente}</td>		                                        
                                <td>${r.vuelta}</td>
                                <td>${r.tiempoS}</td>		                                        		                                       
                                <td>${r.carga}</td>                                		 
                            </tr>                            
	                            <c:set var = "for_total_count" scope = "session" value = "${count.count}"/>      						
		           		</c:forEach>           
		            </c:if>		               
		                              	                         
		                                </tbody>
									</table>
								</div>
							</div>
                               		<input type="hidden" value="${for_total_count}" name="tot_count" id="tot_count">                         
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
    
     <!-- select 2 -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/select2/select2.full.min.js"></script>
    
    <!-- hora -->
      <script src="resources/inspinia_v2.9/FullVersion/js/plugins/clockpicker/clockpicker.js"></script>
    
    <!-- Switchery -->
   <script src="resources/inspinia_v2.9/FullVersion/js/plugins/switchery/switchery.js"></script>
   
   <!-- Sweet alert -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/sweetalert/sweetalert.min.js"></script>
    
      <!--  Libreria para diagrama GO  la resources/js/go.js  es la t version, no se usa debug en pro-->
    <script src="resources/js/go2.js"></script>
     <!-- <script src="resources/js/go-debug2.js"></script> -->
    
      <!-- funciones de validacion de hora, efecto load para botones de busqueda y carga de pagina -->
       <%@include file="utiljs/loadpagebuttonjs.jsp"%>
       <%@include file="utiljs/valclockpickerjs.jsp"%>
        <%@include file="utiljs/validarFechaHoraJs.jsp"%>
   
   <script>  		
	  	$('#myDiagramDiv').css('height',($('#tot_count').val()*101)+'px')  		
  </script>
    
      <script>
      //var $j = jQuery.noConflict(); //en caso de conflicto entre variable con el mismo nombre en libreria
      
      $( "#geo" ).select2( {
  		
  		 allowClear: true
  		
  	} );
      
      $( "#geo2" ).select2( {
    		
    		 allowClear: true
    		
    	} );
      
      $( "#patente" ).select2( {
    	  allowClear: true
    	  
  		
  	} );
    
     
     $(document).off("change","#geo").on("change","#geo", function()
             { 
                 $('#patente').prop('selectedIndex', 0); $('#patente').select2({allowClear: true});     
                 $('#geo2').prop('selectedIndex', 0);  $('#geo2').select2({allowClear: true});
             });
     
     $(document).off("change","#geo2").on("change","#geo2", function()
             { 
     	  $('#geo').prop('selectedIndex', 0);  $('#geo').select2({allowClear: true});     
     	  $('#patente').prop('selectedIndex', 0); $('#patente').select2({allowClear: true});
             });
     
     $(document).off("change","#patente").on("change","#patente", function()
             {                   
                 $('#geo').prop('selectedIndex', 0);  $('#geo').select2({allowClear: true});
                 $('#geo2').prop('selectedIndex', 0);  $('#geo2').select2({allowClear: true});
             });

     
      
      var URLactual =window.location.protocol +"//"+ window.location.host;
        
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
                  {extend: 'excel',    
                  	title: 'TiempoCiclo',
                  	 exportOptions: {
                           columns: [ 0, 1, 2, 3, 4 ,5 ]
                  	 }
                  	
                  },
                  
                  {extend: 'pdf', 
                  	title: 'TiempoCiclo',                    	
                 	 exportOptions: {
                          columns: [ 0, 1, 2, 3, 4 ,5 ]
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
      
       
      //INICIO DE DIAGRAMA #######################################################################################
   
     // banderas ejemplo
   // function theNationFlagConverter(nation) {
     // return "https://www.nwoods.com/go/Flags/" + nation.toLowerCase().replace(/\s/g, "-") + "-flag.Png";
    //}
	
	//iconos de los paneles
	//Cambiar url a produccion
	//http://localhost:8080/sgomtweb/resources/img/iconos/
	//var URLactual =window.location.protocol +"//"+ window.location.host;
	//salert(theIconoConverter('ejemplo'));
    function theIconoConverter(icono) {
      return URLactual+ "/sgomtweb/resources/img/iconos/" + icono.toLowerCase().replace(/\s/g, "-") + ".png";
    }
   
     //Contenido del texto 
     function theInfoTextConverter(info) {
      var str = "";
      if (info.title) str += "Vueltas: " + info.title;
      if (info.headOf) str += "\n\n Duracion: " + info.headOf;
      /*if (typeof info.boss === "number") {
        var bossinfo = myDiagram.model.findNodeDataForKey(info.boss);
        if (bossinfo !== null) {
          str += "\n\nReporting to: " + bossinfo.name;
        }
      }*/
      return str;
    }
   
var $d = go.GraphObject.make;
var myDiagram =
  $d(go.Diagram, "myDiagramDiv",
    {
      "undoManager.isEnabled": true, // enable Ctrl-Z to undo and Ctrl-Y to redo
      layout: $d(go.TreeLayout, // specify a Diagram.layout that arranges trees
                { angle: 0, layerSpacing: 280 })  //45, 90 , 180 
    });
	
	
myDiagram.scrollMode = go.Diagram.DocumentScroll;

 myDiagram.nodeTemplate =
    $d(go.Node, "Auto",
      $d(go.Shape,
        { figure: "RoundedRectangle",
          fill: "white" },  // default Shape.fill value
        new go.Binding("fill", "color")),  // binding to get fill from nodedata.color
		
	/*
      $(go.TextBlock,
        { margin: 0, alignment: go.Spot.Left  },
        new go.Binding("text", "descripcion")),  // binding to get TextBlock.text from nodedata.key		// width: 60, height: 33,  column: 3 , new go.Margin(0, 40)
		// $(go.TextBlock, { text: "a Text Block", background: "lightblue" }),
      $(go.TextBlock,
        {  margin: 40, height: 80, width: 60, stroke: "white" ,  alignment: go.Spot.Center},
        new go.Binding("text", "descripcion2")),  
	  $(go.TextBlock,
        {  margin: 40, stroke: "white", alignment: go.Spot.Left},
        new go.Binding("text", "descripcion3")),  
		*/

		$d(go.Panel, "Table",
          { margin: 6, maxSize: new go.Size(150, NaN) },
          // the two TextBlocks in column 0 both stretch in width
          // but align on the left side
		  
          $d(go.RowColumnDefinition,
            {
              column: 0,
              stretch: go.GraphObject.Horizontal,
              alignment: go.Spot.Left
            }),
          // the name
          $d(go.TextBlock,
            {
              row: 0, column: 0,
              maxSize: new go.Size(160, NaN), margin: 2,
              font: "500 16px Roboto, sans-serif",
              alignment: go.Spot.Top
            },
            new go.Binding("text", "name"),
			new go.Binding("stroke", "colorTitulo")
			),
          // the country flag
          $d(go.Picture,
            {
              row: 0, column: 1, margin: 2,
              imageStretch: go.GraphObject.Uniform,
              alignment: go.Spot.TopRight
            },
            // 
            new go.Binding("desiredSize", "icono", function(){ return new go.Size(34, 26) }), //tamalo imagen
            new go.Binding("source", "icono", theIconoConverter)),
          // aqui agregar adicional informacion
          $d(go.TextBlock,
            {
              row: 1, column: 0, columnSpan: 2,
              font: "12px Roboto, sans-serif"
            },
            //new go.Binding("text", "", theInfoTextConverter),
            new go.Binding("text", "", theInfoTextConverter),
			new go.Binding("stroke", "colorContenido")
			)
        )  // end Table Panel
      );  // end Node		
      
     
		
      myDiagram.linkTemplate =
    	    $d(go.Link,
    	    	      { routing: go.Link.Orthogonal,  // Orthogonal routing
    	    	        corner: 10,
    	    	        //curve: go.Link.JumpGap
    	    	        },                 
    	    	      $d(go.Shape,
    	    	    		  new go.Binding("stroke", "color"),  // shape.stroke = data.color
    	    	    	      new go.Binding("strokeWidth", "thick")),  // shape.strokeWidth = data.thick),
    	    	        	    	      
    	    	      $d(go.Shape, { toArrow: "Standard" })
    	    	    );

    
   //modelo del diagrama personalizado 
  var nodeDataArray = [
	  
		<c:forEach items="${nlist}" var="n" varStatus="count" >	  		
		   <c:if test="${not  empty n }">	
		   
		   { key: "${n.key}", name: "${n.name}", icono: "${n.icono}", title: "${n.title}", color: "${n.color}" , colorTitulo: "${n.colorTitulo}", colorContenido: "${n.colorContenido}"},
		   
		   </c:if>	    
      </c:forEach>	    	
   
	//origen
   /*{ key: 0, name: "Cantera", icono: "icons8-bloquear-64", color: "orange" , colorTitulo: "white", colorContenido: "white"},
   { key: 1, name: "Cantera 2", icono: "icons8-bloquear-64", color: "orange", colorTitulo: "white", colorContenido: "white"  },
   
   
   */
	//{ key: 6, descripcion: "Utilizacion Efectiva ", color: "white" }
  ];
  
  //conexiones 
  var linkDataArray = [
	  
		<c:forEach items="${llist}" var="l" varStatus="count" >	  		
		   <c:if test="${not  empty l }">	
		   
		  
		   { from:  "${l.from}", to:  "${l.to}",  color: " ${l.color}", thick:  ${l.thick} },
		   
		   </c:if>	    
   </c:forEach>	    	
   
	//{ from: 0, to: 2,  color: "blue", thick: 2  },
	/*{ from: 0, to: 2,  color: "grey", thick: 2  },
	{ from: 0, to: 3, color: "grey", thick: 2  },
	{ from: 0, to: 4, color: "grey", thick: 2  },
		
	*/
	
  ];
  
  //AGregar nuevo diagrama aparte
  /*var violetbrush = $(go.Brush, "Linear", { 0.0: "Violet", 1.0: "Lavender" });

   myDiagram.add(
    $(go.Node, "Auto",
      $(go.Shape, "Ellipse",
        { fill: violetbrush }),
      $(go.TextBlock, "Goodbye!",
        { margin: 5 }), 		
    )); */
  
  myDiagram.model = new go.GraphLinksModel(nodeDataArray, linkDataArray);

  //Habilitar y Deshabilidar 
  myDiagram.isEnabled = false; 
  
    
    var button = document.getElementById('imgdiagrama'); button.addEventListener('click', function() 
    		{ var newWindow = window.open("","newWindow"); if (!newWindow) return; var newDocument = newWindow.document; 
    		var img = myDiagram.makeImage({ scale: 0 }); newDocument.body.appendChild(img); },
    		false);
  
  </script>
  
 
    
</body>

</html>
