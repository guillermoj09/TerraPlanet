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
            <div class="row wrapper border-bottom white-bg page-heading">
                <div class="col-lg-10">
                    <h2></h2>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="">Administracion</a>
                        </li>
                        
                         <li class="breadcrumb-item active">
                            <a href="">Usuario</a>
                        </li>
                        
                        
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>
        
   <!-- Contenido principal  -->
        <div class="wrapper wrapper-content animated fadeInRight">
 
            			<div class="row">
					<div class="col-lg-12">
						<div class="ibox">
							<div class="ibox-title">
								<h5>Usuario</h5>
							</div>

							<div class="ibox-content">
								<div class="row">
									<div class="col-sm-12 b-r">
										<h3 class="m-t-none m-b">${mensaje}</h3>
										<p>${mensaje02}</p></h3>										
										<br>
										<br>
										<div>
											<button class="btn btn-sm btn-primary pull-left m-t-n-xs"
												type="button" onClick="history.back()">
												<strong>Volver</strong>
											</button>

										</div>


									</div>

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
     
             
    
        
</body>

</html>
