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
                          <h5>Filtros <b>Reporte Ignicion</b> &nbsp;&nbsp; &nbsp;&nbsp; <small> Buscar por ... </small></h5>
                          <div class="ibox-tools">
                              <a class="collapse-link">
                                  <i class="fa fa-chevron-up" id="ocultar"></i>
                              </a>
                          </div>
                      </div>
                      <form:form action="reporteignicion.html" commandName="basicForm"  id="data_1" autocomplete="off">
                      <div class="ibox-content" id="ocultardiv">
                        <div class="panel-group" id="accordion">
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h5 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">Busqueda Rapida</a>
                                            </h5>
                                        </div>

                                       <!--  <div id="collapseOne" class="panel-collapse collapse in show">  -->
                                       <div id="collapseOne" class="panel-collapse collapse ${panelR}">
                                            <div class="panel-body">
                                                <div class="row">
                                                <div class="col-lg-2 ">
                                                      <div class="form-group">
                                                              <label>Faenas </label>
                                                              <div>
                                                              <select  name="faenas" id="faenas"  style="width:100%;" tabindex="4" required data-placeholder="Seleccione una Faena"
                                                              class="form-control text-uppercase">

														<option value=""></option>
														<c:forEach items="${listfaena}" var="f" varStatus="count" >
														   <c:if test="${not  empty f }">
														  			<option value="${f.idGru}" <c:if test="${f.idGru == rform.faenas}"> selected</c:if>>${f.nombreGru}</option>
												 		  </c:if>
												      </c:forEach>
												</select> 
                                                              </div>
                                                      </div>
                                                    </div>
                                                 <div class="col-lg-2 ">
                                                      <div class="form-group">
                                                              <label>Patentes</label>
                                                              <div>
                                                               <select  name="patentes" id="patentes"  style="width:100%;" tabindex="4" data-placeholder="Seleccione una Patente"
                                                               		class="form-control text-uppercase" envola="${rform.patentes}">

														<option value=""></option>
														<c:forEach items="${listpatentes}" var="p" varStatus="count" >
														   <c:if test="${not  empty p }">
															 <option value="${p.vehPatenteVehiculo}" <c:if test="${p.vehPatenteVehiculo == rform.patentes}"> selected</c:if>>${p.vehPatenteVehiculo}<c:if test="${not  empty p.nroInterno }"><p class="text-secondary"> - ${p.nroInterno}</p> </c:if></option>

												 		  </c:if>
												      </c:forEach>
												</select>
                                                              </div>
                                                      </div>
                                                    </div>
                                                    <div class="col-lg-5  b-r">

                                                    </div>
                                                      <div class="col-lg-3 text-center">
                                                              <button  type="button" class="ladda-button btn btn-primary btn_submit" id="toggleSpinners" data-style="contract" style="margin-top:20px;">Buscar</button>
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
                                        <div id="collapseTwo" class="panel-collapse collapse  ${panelF}">
                                            <div class="panel-body">
                                                <div class="row">
                                                <div class="col-lg-10">
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
                                                    <div class="col-lg-2">
                                                        <div class="form-group">
                                                            <label class="control-label">&nbsp; Hora Hasta</label>&nbsp;&nbsp; &nbsp;&nbsp;
                                                            <div class="input-group clockpicker" data-autoclose="true">
                                                                <input type="text" class="form-control" name="horaHasta" id="horaHasta" value="${rform.horaHasta}" >
                                                                <span class="input-group-addon"><span class="fa fa-clock-o"></span></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-4 ">
	                                                    <div class="form-group">                                  
	                                                    </div>
                                                 	 </div>
													<!-- 
                                                    <div class="col-lg-2 b-r">
	                                                    <div class="form-group">	                                                 
                                                             <label>Turnos </label>
                                                             <div>
                                                              <select  name="turnos" id="turnos"  style="width:100%;" tabindex="4" data-placeholder="Seleccione un Turno"
                                                              		class="form-control text-uppercase">	
																<option value="0">Todos</option>
																<c:forEach items="${listturno}" var="p" varStatus="count" >				
																   <c:if test="${not  empty p }">				
																	 <option value="${p.idTurno}" <c:if test="${p.idTurno == rform.turnos}"> selected</c:if>>${p.nombre}</option>				
														 		  </c:if>
															      </c:forEach>
															</select>
                                                             </div>
	                                                      </div>
                                                 	 </div>
                                                 	 
                                                 	 <div class="col-lg-2  b-r">
                                                      <div class="form-group">
                                                              <label>Visualización Tiempos </label>
                                                              <div>
                                                               <select  name="opt_ver" id="opt_ver"  style="width:100%;" tabindex="4" data-placeholder="Seleccione una Opcion"
                                                               		class="form-control text-uppercase">
																	<option value="1" <c:if test="${rform.opt_ver=='1'}"> selected</c:if>>En Horas</option>
																	<option value="2" <c:if test="${rform.opt_ver=='2'}"> selected</c:if>>En Minutos</option>
																	<option value="3" <c:if test="${rform.opt_ver=='3'}"> selected</c:if>>En Segundos</option>
																	<option value="4" <c:if test="${rform.opt_ver=='4'}"> selected</c:if>>En HH:MM:SS</option>
														
															</select>
                                                              </div>
                                                      </div>
                                                    </div>
                                                     -->
                                                    </div>
												</div>
												<div class="col-lg-2">
                                                     <div class="col-lg-12 text-center">
                                                             <button type="button" class="ladda-button btn btn-primary btn_submit" id="toggleSpinners2" data-style="contract" style="margin-top:20px;">Filtrar</button>
                                                      </div>
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
                    <div class="ibox float-e-margins  ${border}" id="ibox1">
                        <div class="ibox-title">
                            <h5>Reporte Ignicion</h5>
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
											<th>Conductor</th>
											<!--  <th>Faena</th>  -->
											<th>Evento</th>
											<th>Fecha/Hora</th>
											<th title="Tiempo Encendido (minutos)">Tiempo Encendido(min)</th>
											<th>KM</th>
											<th>Ubicacion</th>
											<th>Mapa</th> 
											</tr>
										</thead>
									 <tbody>

									    <c:if test="${not  empty rlist }">
											<c:forEach items="${rlist}" var="r" varStatus="count" >
				                              	<tr >
			                                      <td>${r.patente}</td>
			                                      <td>${r.conductor}</td>
			                                      <!--<td>${r.ubicacion}</td>-->
			                                      <td>${r.evento}</td>
			                                      <td><fmt:formatDate value="${r.fecha_ini}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
			                                      <td>${r.tiempo_str}</td>
			                                      <td>${r.distancia_str}</td>
			                                      <td>${r.ubicacion}</td>
			                                      <td title="Ver en Mapa"><font size="+1.5"><a style="color:#337ab7" class="link_mapa" link="mapaxpatentereporte.html?patente=${r.patente}&lat=${r.lat}&lon=${r.lon}"><i class="fa fa-external-link"></i></a></font></td>  		                                      
				                                  </tr>
							           		</c:forEach>
							            </c:if>
		                                </tbody>
		                                <tfoot align="right">
										<tr>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
											<th></th>
											<th id="thide"></th>											
										</tr>
									</tfoot>
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


     var URLactual =window.location.protocol +"//"+ window.location.host;

    $(document).ready(function(){
    	
    	 $(document).on("click",".link_mapa",function(){
         	window.open($(this).attr('link'),"MAPA","top=100,left=300,width=800,height=650,scrollbars=NO,resizable=YES,class=lbOn,location=0,status=0, directories=0, toolbar=0, titlebar=0" );
	    	});

   	 $( "#patentes" ).select2( {
   	 		placeholder: "Seleccione Patente",
   	 		 allowClear: true

   	 	} );

   	     $( "#faenas" ).select2( {
   				placeholder: "Seleccione Faena",
   				 allowClear: true

   			} );

   	  $(document).off("change","#faenas").on("change","#faenas", function()
	             {
	     			$('#patentes').prop('selectedIndex', 0); $('#patentes').select2();
	             });

	     $(document).off("change","#patentes").on("change","#patentes", function()
	             {
	     			$('#faenas').prop('selectedIndex', 0); $('#faenas').select2();
	             });


	     $(document).on("click", ".btn_submit", function(e){
  		if($("#faenas").val()=="" && $("#patentes").val()==""){
  	    	swal("", "Debe seleccionar una Faena o una Patente ", "error"); $("#faenas").focus();
  	    	return  false;
  	    }
  	    else{
  	    	var istrue = validoFechasHora();
 	    	if(istrue){
 	    		$("#data_1").submit();
 	    	}
  	    }
  	});

	     var buttonCommon = {
	    	        exportOptions: {
	    	            format: {
	    	                body: function ( data, row, column, node ) {
	    	                	//console.log(node,row,column,data,data.replace( /\./gi, '' ),data.replace( /\./gi, '' ).replace( /\,/gi, '.' ) ,data.replace( /\,/gi, '.' ))
	   	    	    	      return column === 8 || column === 14 || column === 15 || column === 16 || column === 17 || column === 18 || column === 19 || column === 20 || column === 21  
	   	    	    	      		? data.replace( /\./gi, '' ).replace( /\,/gi, '.' ) 
	   	    	    	      				: data;	    	    	    	    	      
	    	                }
	    	            }
	    	        }
	    	    };


        $('.dataTables-example').DataTable({
        	"language": {
                "url": URLactual + "/sgomtweb/resources/datetableespanil.json"
            },
            pageLength : 5,            
            "fnFooterCallback": function ( row, data, start, end, display ) {
            	var api = this.api(), data;
            	
                var visible = 0;
				for (var i = start; i < end; i++) {
					visible = visible + data[display[i]][4]*1;                  
				}
				var iTotalhh = 0;
				for ( var k=0 ; k<data.length ; k++ )
				{
					iTotalhh += parseFloat(data[k][4]*1); 
				}
				
				$( api.column(3).footer() ).html("Minutos Totales:");
                $( api.column(4).footer() ).html(visible.toFixed(2)+" min ("+iTotalhh.toFixed(2)+" min)");
				
            },
            lengthMenu: [[5, 10, 50, 100, -1], [5, 10, 50, 100, 'TODOS']],
            //"order": [[ 0, "asc" ]],
            //"order": false,
            responsive: true,
            dom: '<"html5buttons"xï¿½B>lTfgitp',
            buttons: [
                {extend: 'copy'},
                $.extend( true, {}, buttonCommon, {
                    extend: 'csv', title: 'Reporte Ignicion',exportOptions: {
             			columns: [ 0, 1, 2, 3, 4 ,5, 6  ]
    	 			},
                } ),
                $.extend( true, {}, buttonCommon, {
                    extend: 'excelHtml5', title: 'Reporte Ignicion',exportOptions: {
             			columns: [ 0, 1, 2, 3, 4 ,5, 6  ]
    	 			},
                } ),
                {extend: 'pdf', title: 'Reporte Ciclo Transporte'},
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
        
        $('.dataTables-example tbody').on( 'click', 'tr', function () {
            $(this).toggleClass('selected');
        } );

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
