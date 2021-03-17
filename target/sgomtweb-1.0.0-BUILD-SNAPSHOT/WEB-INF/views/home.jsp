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
<!-- Ladda style boton load-->
    <link href="resources/css/plugins/ladda/ladda-themeless.min.css" rel="stylesheet">

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
			<p>Ingresar Usuario y Contraseña.</p>
			<form class="m-t" role="form" action="home.html" method="post">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="username"
						required="" id="username" name="username">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" placeholder="Password"
						required="" id="password" name="password">
				</div>
				<!--  <button class="ladda-button btn btn-primary" id="toggleSpinners" data-style="contract" type="submit" style="margin-top:20px;">login</button>  -->
				<button id="toggleSpinners" data-style="zoom-in" type="submit" class="ladda-button btn btn-primary block full-width m-b">Login</button>
				
		           
			</form>
			
			 						  		
			  <c:if test="${not empty mensaje}">
	       
	       	<div class="alert alert-danger">
  				<a href="#" class="alert-link">${mensaje}</a>
				</div>
	       
	       </c:if>
	       
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

 <!-- Ladda boton load-->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/ladda/spin.min.js"></script>
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/ladda/ladda.min.js"></script>
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/ladda/ladda.jquery.min.js"></script>

 <script>

    $(document).ready(function (){

        // Bind normal buttons
        Ladda.bind( '.ladda-button',{ timeout: 2000 });

        // Bind progress buttons and simulate loading progress
        Ladda.bind( '.progress-demo .ladda-button',{
            callback: function( instance ){
                var progress = 0;
                var interval = setInterval( function(){
                    progress = Math.min( progress + Math.random() * 0.1, 1 );
                    instance.setProgress( progress );

                    if( progress === 1 ){
                        instance.stop();
                        clearInterval( interval );
                    }
                }, 200 );
            }
        });


        var l = $( '.ladda-button-demo' ).ladda();

        l.click(function(){
            // Start loading
            l.ladda( 'start' );

            // Timeout example
            // Do something in backend and then stop ladda
            setTimeout(function(){
                l.ladda('stop');
            },22000)


        });

    });

</script>

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