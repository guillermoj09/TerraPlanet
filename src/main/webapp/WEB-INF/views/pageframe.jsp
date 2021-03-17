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
 
  
 <style>
 
 html{
   height: 100% !important;
}

body{
   height: 100% !important;
} 
#wrapper{
   height: 100% !important;
} 

#page-wrapper{
   height: 100% !important;
} 

.ibox-content{
   height: 90% !important;
   margin-top: 10px;
}

#frame {
    position: relative;
    width: 100% !important;
    height: 100% !important;
    margin: 0 auto 0;
}

 </style>
 
  
</head>
<body class="top-navigation">

	<div id="wrapper">
	
	
	<%@include file="menu.jsp"%>
	
		 <div id="page-wrapper" class="gray-bg">
		 
		 
       <div class="row border-bottom">
       </div>
        
   <!-- Contenido principal  -->

		<div class="ibox-content">
		
		
			<div id="frame">
			
			<c:if test="${imgdescarga == 'SI'}">  
			
			<!--  <img src="resources/img/descargar.jpg" alt="descarga"  style="width: 100%; height: 100%; border: none">  -->
			<iframe src="https://gps.samtech.cl/${url}.asp?user=<%=usuario.getUsername() %>&pass=<%=usuario.getPassword() %>${parametros}"  style="width: 1%; height: 1%; border: none"></iframe>
			<script>
				if("${id}"=="kml_generico_new"){
					//window.location.href="mapageneral.html";					
					setTimeout(cerrar, 500);
				}
				
				function cerrar(){
					window.open('','_self').close();
				}
			</script>
			</c:if>
			<c:if test="${imgdescarga == 'NO'}">  
			
            <iframe src="https://gps.samtech.cl/${url}.asp?user=<%=usuario.getUsername() %>&pass=<%=usuario.getPassword() %>${parametros}"  style="width: 100%; height: 100%; border: none"></iframe>
                  
            </c:if>
             </div>
				
	    
       </div>
        
        <%@include file="footer.jsp"%>
        
      </div> <!-- fin wrapper -->
      
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
    
    <script>
    
    </script>

    
</body>

</html>