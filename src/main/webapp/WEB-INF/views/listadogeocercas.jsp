<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link
	href="resources/inspinia_v2.9/FullVersion/css/plugins/sweetalert/sweetalert.css"
	rel="stylesheet">
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
						<li class="breadcrumb-item"><a href="">Administracion</a></li>

						<li class="breadcrumb-item active"><strong>Cercos
								Virtuales</strong></li>



					</ol>
				</div>
				<div class="col-lg-2"></div>
			</div>

			<!-- Contenido principal  -->
			<div class="wrapper wrapper-content animated fadeInRight">
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-title">
								<h5>Listado de Geocercos</h5>

							</div>

							<div class="ibox-content">
								<div class="col-12 "
									style="padding-bottom: 20px; padding-top: 10px">
									<a class="btn btn-primary" href="creageocercos.html">Nueva
										Geocerca</a> <a href="downloadgeotodoKML.kml">
										<button class="btn btn-success btn_kml"
											title="Exportar Todas Geo" type="button" value="Exportar">
											<i class="fa fa-file-code-o"></i>&nbsp;&nbsp;Exportar
										</button> <!--  btn-outline -->
									</a>
								</div>
								<div class="table-responsive">
									<table
										class="table table-striped table-bordered table-hover dataTables-example">
										<thead>
											<tr>
												<th>Nombre</th>
												<th>Color</th>
												<!--  <th>Area (Km²)</th> -->
												<th>Opciones</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${not  empty geocercas }">
												<c:forEach items="${geocercas}" var="g" varStatus="count">
													<tr>
														<td>${g.nombre}</td>
														<td><span class="badge badge-danger"
															style="background-color:${g.codigo_color}">&nbsp;&nbsp;&nbsp;</span>
															&nbsp;&nbsp;${g.nombre_color}</td>
														<!--  <td>${g.area}</td> -->
														<td><font size="+1.5" color="green" title="Editar"
															style="cursor: pointer"
															onClick="location.href='editageocercos.html?id=${g.id_geo}'"><i
																class="fa fa-edit"></i></font> &nbsp;&nbsp;&nbsp;&nbsp;<font
															size="+1.5" color="red" title="Eliminar"
															style="cursor: pointer"><i id="borraGeo"
																name="borraGeo" nombre="${g.nombre}" geo="${g.id_geo}"
																class="fa fa-trash"></i></font></td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>

							</div>
							<!--  fin ibox-content -->

						</div>



					</div>



				</div>


			</div>

			<%@include file="footer.jsp"%>

		</div>

	</div>

	<%@include file="footerjs.jsp"%>


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
		src="resources/inspinia_v2.9/FullVersion/js/plugins/dualListbox/jquery.bootstrap-duallistbox.js"></script>

	<!-- Sweet alert -->
	<script
		src="resources/inspinia_v2.9/FullVersion/js/plugins/sweetalert/sweetalert.min.js"></script>

	<script>
		var URLactual = window.location.protocol + "//" + window.location.host;

		$(document)
				.ready(
						function() {
							$('.dataTables-example')
									.DataTable(
											{
												"language" : {
													"url" : URLactual
															+ "/sgomtweb/resources/datetableespanil.json"
												},
												pageLength : 10,
												//"order": [[ 0, "asc" ]],
												//"order": false,
												responsive : true,
												dom : '<"html5buttons"xºB>lTfgitp',
												buttons : [
														{
															extend : 'copy'
														},
														{
															extend : 'csv'
														},
														{
															extend : 'excel',
															title : 'Usuarios'
														},
														{
															extend : 'pdf',
															title : 'Usuarios'
														},

														{
															extend : 'print',
															customize : function(
																	win) {
																$(
																		win.document.body)
																		.addClass(
																				'white-bg');
																$(
																		win.document.body)
																		.css(
																				'font-size',
																				'10px');

																$(
																		win.document.body)
																		.find(
																				'table')
																		.addClass(
																				'compact')
																		.css(
																				'font-size',
																				'inherit');
															}
														} ]

											});

							$('td').mouseover(function() {
								$(this).addClass('rowselected');
								$(this).css('cursor', 'default');
							});
							$('td').mouseout(function() {
								$(this).removeClass('rowselected');
							});

							$(document)
									.on(
											'click',
											'#borraGeo',
											function(event) {

												nombre = $(this).attr("nombre");
												id_geo = $(this).attr("geo");

												swal(
														{
															title : "",
															text : "Esta seguro que desea eliminar la geocerca: "
																	+ nombre
																	+ " ?",
															type : "warning",
															showCancelButton : true,
															confirmButtonColor : "#DD6B55",
															confirmButtonText : "Si, Eliminar",
															cancelButtonText : "Cancelar",
															closeOnConfirm : false
														},
														function() {
															swal(
																	"",
																	"La geocerca ha sido eliminada.",
																	"success");
															location.href = "eliminageocerco.html?id_geo="
																	+ id_geo;
														});

											})

						});
	</script>


</body>

</html>