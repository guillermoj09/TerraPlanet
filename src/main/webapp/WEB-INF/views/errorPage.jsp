<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>

<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>SAMTECH | SGOMT</title>
<link rel="shortcut icon" href="resources/img/favicon.ico" />
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/font-awesome/css/font-awesome.css"
	rel="stylesheet">

<link href="resources/css/animate.css" rel="stylesheet">
<link href="resources/css/style.css" rel="stylesheet">

</head>

<body class="gray-bg">

	<div class="middle-box text-center loginscreen animated fadeInDown">
		<div>
			<br>
			<br>
			<br>
			<br>
			<img src="resources/img/logohome.png"><br>
			<br>
			<h3>Bienvenido a Samtech</h3>
			<p>SGOMT</p>
			<p>Ha sucedido un Error tipo ${httpErrorCode} </p>
			
			 <h2>${errorMsg}</h2>
			<br>
			
			
			<button class="btn btn-sm btn-primary pull-center m-t-n-xs" type="button" onClick="history.back()">
				<strong>Volver</strong>
			</button>
											

		    <br>
						 						  					 
	       <jsp:useBean id="date" class="java.util.Date" />
					
			<p class="m-t">
				<small>Copyright</strong> SAMTECH &copy; <fmt:formatDate value="${date}" pattern="yyyy" /> 
				</small>
			</p>
		</div>
	</div>

	<!-- Mainly scripts -->
	<script src="resources/inspinia_v2.9/FullVersion/js/jquery-3.1.1.min.js"></script>
	<script src="resources/inspinia_v2.9/FullVersion/js/bootstrap.min.js"></script>

</body>

<!-- 

Alertas 

<div class="alert alert-success">
  <a href="#" class="alert-link">...</a>
</div>
 
<div class="alert alert-info">
  <a href="#" class="alert-link">...</a>
</div>
 
<div class="alert alert-warning">
  <a href="#" class="alert-link">...</a>
</div>
 
<div class="alert alert-danger">
  <a href="#" class="alert-link">...</a>
</div>


 -->

</html>