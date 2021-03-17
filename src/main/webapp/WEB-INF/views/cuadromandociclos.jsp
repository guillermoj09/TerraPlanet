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

		<div id="page-wrapper" class="gray-bg_optional">


			<%@include file="menu.jsp"%>

			<div id="page-wrapper" class="gray-bg">

				<%@include file="barramenu.jsp"%>

				<div class="wrapper wrapper-content animated fadeInRight">
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
								<div class="ibox-content">
									<div class="row">
										<div class="col-lg-12">
											<div class="col-lg-8 col-md-9">
												<div class="form-inline" id="data_5">
													<label class="font-normal">Fecha de Busqueda :</label>
													<div class="input-group date">
														<span class="input-group-addon"><i
															class="fa fa-calendar"></i></span><input type="text"
															class="form-control" name="start" id="start"
															value="18/10/2019" />
													</div>


													<button
														class="ladda-button ladda-button-demo btn btn-primary"
														data-style="zoom-in" id="btn_buscar">Buscar</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

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
									<div class="pull-right" style="padding: 10px">
										<button
											class="ladda-button ladda-button-excel btn btn-primary"
											data-style="zoom-in">
											<i class="fa fa-file-excel-o"></i> Exportar Todo
										</button>
									</div>
									<div class="pull-right" style="padding: 10px">
										<button
											class="ladda-button ladda-button-update btn btn-primary"
											data-style="zoom-in">
											<i class="fa fa-refresh"></i> Actualizar
										</button>
									</div>
									<div id="div_table_general">
										<br> <br> <br>
									</div>
								</div>
							</div>
						</div>
					</div>


				</div>
				<div class="footer">
					<div class="pull-right">Tecnologia en Control y Gestion de
						Flotas.</div>
					<div>
						<strong><a href="https://www.samtech.cl">SAMTECH S.A.</a></strong>
					</div>
				</div>
			</div>
			<%@include file="footer.jsp"%>			
		</div>
	</div>
	<%@include file="footerjs.jsp"%>
</body>


</html>