<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="cl.samtech.sgomt.object.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.DecimalFormat"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SAMTECH | SGOMT</title>

<%@include file="style.jsp"%>
<!-- Sweet Alert -->
<link
	href="resources/inspinia_v2.9/FullVersion/css/plugins/sweetalert/sweetalert.css"
	rel="stylesheet">

<style>
.tr1 {
	display: none;
}


</style>
</head>

<body class="top-navigation">
	<div id="wrapper">
		<%@include file="menu.jsp"%>
		<div id="page-wrapper" class="gray-bg">
			<div class="row border-bottom"></div>

			<!-- Titulo del Menu  -->
			<div class="row wrapper border-bottom white-bg page-heading">
				<div class="col-lg-10">
					<h2></h2>
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="">Productividad</a></li>
						<li class="breadcrumb-item active"><strong>Cuadro de
								mando</strong></li>
					</ol>
				</div>
				<div class="col-lg-2"></div>
			</div>

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
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-title">
								<h5>BUSQUEDA POR FECHA</h5>
								<div class="ibox-tools">
									<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
									</a>
								</div>
							</div>
							<form:form action="cuadromando.html"
								commandName="cuadroMandoForm" id="form_cdm"
								enctype="multipart/form-data" autocomplete="off">
								<div class="ibox-content">
									<div class="row">
										<div class="col-lg-2">
											<div class="form-group">
												<label class="control-label">&nbsp; Fecha </label>&nbsp;&nbsp;
												&nbsp;&nbsp;
												<div class="input-group date">
													<span class="input-group-addon"><i
														class="fa fa-calendar"></i></span><input type="text"
														class="form-control" name="fechaBusqueda"
														id="fechaBusqueda" value="${cform.fechaBusqueda}" required>
												</div>
											</div>
										</div>
										<div class="col-lg-2">
											<div class="form-group">
												<label class="control-label">&nbsp; Origen </label>&nbsp;&nbsp;
												<select name="origen" id="origen" style="width: 100%;"
													tabindex="4" data-placeholder="Seleccione una origen"
													class="form-control text-uppercase">
													<option value="0">TODAS</option>


													<c:forEach items="${listadogeo}" var="l" varStatus="count">
														<c:if test="${not  empty l }">
															<option value="${l.id_geo}"
																<c:if test="${l.id_geo == cform.origen}"> selected</c:if>>${l.nombre}</option>
														</c:if>
													</c:forEach>

												</select>
											</div>
										</div>
										<div class="col-lg-6  b-r"></div>
										<div class="col-lg-2 text-center">
											<button class="ladda-button btn btn-primary btn_guardar"
												id="toggleSpinners2" data-style="contract"
												style="margin-top: 20px;">Buscar</button>
										</div>
									</div>
								</div>
							</form:form>
						</div>
					</div>
				</div>
				<c:if test="${not  empty lista1 }">
					<div class="row">
						<div class="col-lg-12">
							<div class="ibox float-e-margins">
								<div class="ibox-title">
									<h5>DASHBOARD TURNOS</h5>

									<div class="ibox-tools">
										<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
										</a>
									</div>
								</div>
								<div class="ibox-content">
									<input type="hidden" value="${cform.fechaBusqueda}"
										id="fechaHidden"> <input type="hidden"
										value="${cform.origen }" id="origenHidden">
									<table
										class="table table-bordered table-hover dataTables-example" id="tabla">
										<thead>
											<tr>
												<th data-toggle="true">Turnos</th>
												<th>Origen</th>
												<th>Vueltas totales</th>
												<th>Ciclos promedio</th>
												<th>Produccion media x hora</th>
												<th>Tiempo de ciclo promedio</th>
												<th>Velocidad promedio</th>
												<th>Distancia promedio</th>
												<th>Vueltas x camion</th>
												<th>Ralenti x camion</th>
												<th>Horas x camion</th>
												<th>Camiones totales</th>
											</tr>
										</thead>
										<tbody>
											<%
												DecimalFormat df = new DecimalFormat("#0.00");

												List<CuadroMandoActive> lista = (List) request.getAttribute("lista1");
												for (CuadroMandoActive c : lista) {
											%>
											<tr class="fila">
												<td class="turno"><%=c.getTurno() %></td>
												<td><%=c.getOrigen()%></td>
												<td><%=c.getTotal_vueltas()%></td>
												<td><%=df.format(c.getCiclo_promedio())%></td>
												<td><%=df.format(c.getProduccion_mxh())%></td>
												<td><%=df.format(c.getTiempo_ciclo_promedio()) %></td>
												<td><%=df.format(c.getVel_prom())%></td>
												<td><%=df.format(c.getDistancia_promedio())%></td>
												<td><%=df.format(c.getVueltaxcamion())%></td>
												<td><%=df.format(c.getRalentixcamion())%></td>
												<td><%=df.format(c.getHorasxcamion())%></td>
												<td><%=c.getNum_camiones().intValue() %></td>
												<input type="hidden" class="id_turno" value="<%=c.getId_turno()%>"></input>
											</tr>
											
											<%
												}
											%>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</c:if>
			</div>



			<div class="footer">
				<div class="pull-right">Tecnologia en Control y Gestion de
					Flotas.</div>
				<div>
					<strong><a href="https://www.samtech.cl">SAMTECH S.A.</a></strong>
				</div>
			</div>
			<%@include file="footer.jsp"%>
		</div>
	</div>
	<%@include file="footerjs.jsp"%>

	<div class="modal inmodal in" id="myModal" role="dialog"  tabindex="-1" aria-hidden="true">
		<div class="modal-dialog modal-lg" ">
			<div class="modal-content animated bounceInRight">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<i class="fa fa-list-alt modal-icon"></i>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
					<table class='table table-bordered table-hover dataTables-example2 ' id="example" data-filter=#filter>
						<thead>
							<tr>
								<th>Patente</th>
								<th>Vueltas totales</th>
								<th>Produccion media x hora</th>
								<th>Tiempo de ciclo promedio</th>
								<th>Velocidad promedio</th>
								<th>Distancia promedio</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
						
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
					
				</div>
			</div>
		</div>
	</div>
</body>


<!-- Peity  pie del porcentaje-->
<script
	src="resources/inspinia_v2.9/FullVersion/js/plugins/peity/jquery.peity.min.js"></script>
<script src="resources/inspinia_v2.9/FullVersion/js/demo/peity-demo.js"></script>
<!-- Peity demo -->
<!-- d3 and c3 charts -->
<script
	src="resources/inspinia_v2.9/FullVersion/js/plugins/d3/d3.min.js"></script>
<script
	src="resources/inspinia_v2.9/FullVersion/js/plugins/c3/c3.min.js"></script>
<!-- Date range use moment.js same as full calendar plugin -->
<script
	src="resources/inspinia_v2.9/FullVersion/js/plugins/fullcalendar/moment.min.js"></script>

<!-- Date range picker -->
<script
	src="resources/inspinia_v2.9/FullVersion/js/plugins/daterangepicker/daterangepicker.js"></script>
<!-- Data picker -->
<script
	src="resources/inspinia_v2.9/FullVersion/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<script
	src="resources/inspinia_v2.9/FullVersion/js/plugins/dataTables/datatables.min.js"></script>
<script
	src="resources/inspinia_v2.9/FullVersion/js/plugins/dataTables/dataTables.bootstrap4.min.js"></script>

<script
	src="resources/inspinia_v2.9/FullVersion/js/plugins/footable/footable.all.min.js"></script>

<script
	src="resources/inspinia_v2.9/FullVersion/js/plugins/dualListbox/jquery.bootstrap-duallistbox.js"></script>

<!-- hora -->
<script
	src="resources/inspinia_v2.9/FullVersion/js/plugins/clockpicker/clockpicker.js"></script>

<!-- select 2 -->
<script
	src="resources/inspinia_v2.9/FullVersion/js/plugins/select2/select2.full.min.js"></script>



<script type="text/javascript">
	function datos_table() {
		return "<h1>Hola mundo</h1>";

	}

	function ajaxlista3(vturno, idDestino) {
		var vorigen = document.getElementById("origenHidden").value;
		var vfecha = document.getElementById("fechaHidden").value;
		var vdestino = document.getElementById("destino"+idDestino).value;
		$('#example').DataTable().destroy();
		console.log(vorigen);
		console.log(vfecha);
		console.log(vturno);
		console.log(idDestino);
		console.log(vdestino);

		$.ajax({
			type : 'POST',
			url : 'ajaxlistar3.html',
			data : {
				turno : vturno,
				fecha : vfecha,
				origen : vorigen,
				destino : idDestino,

			},
			success : function(respuesta) {
				$("#myModal .modal-body table tbody").html(respuesta);
				$("#myModal .modal-title").html(vdestino);
				$("#myModal").modal("show");
				
			}
		}).done(function() {
					
			$('#example').DataTable( {
				retrieve: true,
			  
			} );
			
		})
		
	}
	var URLactual = window.location.protocol + "//" + window.location.host;

	$(document).ready(function() {

	
		var table = $('.dataTables-example').DataTable({	
			"language" : {
				"url" : URLactual
						+ "/sgomtweb/resources/datetableespanil.json"
			},
             pageLength: 25,
             responsive: true,
             dom: '<"html5buttons"xºB>lTfgitp',
             buttons: [
                 { extend: 'copy'},
                 {extend: 'csv'},
                 {extend: 'excel', title: 'CuadroMandoFile'},
                 {extend: 'pdf', title: 'CuadroMandoFile'},

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
		
		$('#tabla tbody').on('click', 'tr.fila', function() {

			var tr = $(this).closest('tr');
			var row = table.row(tr);
			if (row.child.isShown()) {
				// This row is already open - close it
				row.child.hide();
				tr.removeClass('shown');
			} else {
				// Open this row
				//var vturno = $(this).find("td.turno").text();
				var vturno = $(this).find("input.id_turno").val();
				console.log(vturno);
				var vfecha = $("#fechaHidden").val();
				var vorigen = $("#origenHidden").val();
				$.ajax({
					type : 'POST',
					url : 'ajaxlistar2.html',
					data : {
						turno : vturno,
						fecha : vfecha,
						origen : vorigen,
					},
					success : function(respuesta) {
						row.child(respuesta).show();
						tr.addClass('shown');
						
					}
				}).done(function(){
					$('#tabla_2').footable();
				})
			}
		});

		$('#form_cdm .input-group.date').datepicker({
			language : 'es',
			todayBtn : "linked",
			keyboardNavigation : false,
			calendarWeeks : true,
			autoclose : true,
			format : "dd/mm/yyyy"
		});
	});
</script>
</html>