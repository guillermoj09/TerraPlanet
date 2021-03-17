<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="true"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="cl.samtech.sgomt.object.*"%>
<%@ page import="cl.samtech.sgomt.model.*"%>


<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>SAMTECH | SGOMT</title>
	
	<%@include file="style.jsp"%>

</head>

<body class="top-navigation">
	<script type="text/javascript">
		function limpiar(){
			document.getElementById("cambiarClaveForm").passwordviejo.value = "";
			document.getElementById("cambiarClaveForm").passwordnuevo.value = "";
			document.getElementById("cambiarClaveForm").passwordnuevo2.value = "";
			
		}			  	
	
	</script>



	<div id="wrapper">

		<%@include file="menu.jsp"%>

		<div id="page-wrapper" class="gray-bg">
			<div class="row border-bottom">
							</div>
			
            
            <!-- Titulo del Menu  -->
            	<%@include file="barramenu.jsp"%>
           <!--  <div class="row wrapper border-bottom white-bg page-heading">
                <div class="col-lg-10">
                    <h2></h2>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="">Administracion</a>
                        </li>
                        
                         <li class="breadcrumb-item">
                            <a href="">Usuario</a>
                        </li>
                        
                        <li class="breadcrumb-item active">
                            <strong>Cambiar Clave</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>  -->

<!-- Contenido principal  -->
            <div class="wrapper wrapper-content animated fadeInRight">
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox">
							<div class="ibox-title">
							<h5> Cambiar clave &nbsp;&nbsp; &nbsp;&nbsp; <small> Por favor rellenar todos los campos ... </small></h5>                            
                        	</div>                        	
                        	
                        	<div class="ibox-content">
                                                                                  
                              <form:form action="cambiarclave.html" modelAttribute="cambiarClaveForm" autocomplete="off">
                              
                                   <div class="row" id="contenedorPass">
                                   
																		 
									  <div class="form-group col-lg-2">
											
												<label>Clave actual </label> 
												<input type="hidden" name="username"  value="${userlogin.username}">
												<!-- <input type="password" placeholder="****" class="form-control" name="passwordviejo" maxlength="20"  tabindex="1" value="" >  -->
												<form:input path="passwordviejo" type="password" class="form-control"  maxlength="12" />
												
											
										</div>	
											
										 	
											
									      
											
										 <div class="form-group col-lg-2">	
											
												<label>Nueva Clave</label> 
												<form:input path="passwordnuevo" placeholder="****" type="password" class="form-control"  maxlength="12" /> 
												<!-- <input type="password" placeholder="****" class="form-control" name="passwordnuevo" tabindex="4" value="" >  -->
												
											
										</div>	
											
										
										<div class="form-group col-lg-2">		
											
												<label>Repetir Nueva Clave </label> 
												<form:input path="passwordnuevo2" placeholder="****" type="password" class="form-control"  maxlength="12" />
												<!-- <input type="password" placeholder="****" class="form-control" name="passwordnuevo2" tabindex="4" value="" >  -->
												
												  
											
										</div>	
											
										
	       																			
                                   		
                                   		
                                   		
                                    </div>
                                    
                                     
                                      <c:set var="claveactual">
										<form:errors path="passwordviejo"/>
									 </c:set>
									  <c:set var="nuevaclave">
										<form:errors path="passwordnuevo"/>
									 </c:set>
									  <c:set var="repetir">
										<form:errors path="passwordnuevo2"/>
									 </c:set>
                                    
                                   <c:if test="${not empty claveactual or nuevaclave or repetir}">    
                                    <div class="row">
                                     <div class="col-lg-12">
                                                        	  
										 <div class="form-group">      
	       									<div class="alert alert-danger">
	       									<button type="button" class="close" data-dismiss="alert">&times;</button>
  												<a href="#" class="alert-link"><form:errors path="passwordviejo"  /></a>  												
											</div>	
										</div>	       
	       						
                           		 </div>
                          			  </div>
                          		 </c:if>
                          		 
                          		 <c:if test="${not empty nuevaclave}">    
                                    <div class="row">
                                     <div class="col-lg-12">
                                                        	  
										 <div class="form-group">      
	       									<div class="alert alert-danger">  	
	       									<button type="button" class="close" data-dismiss="alert">&times;</button>											
  												<a href="#" class="alert-link"><form:errors path="passwordnuevo"  /></a>  												
											</div>	
										</div>	       
	       						
                           		 </div>
                          			  </div>
                          		 </c:if>
                          		 
                          		 <c:if test="${not empty repetir}">    
                                    <div class="row">
                                     <div class="col-lg-12">
                                                        	  
										 <div class="form-group">      
	       									<div class="alert alert-danger">
	       									<button type="button" class="close" data-dismiss="alert">&times;</button>
  												<a href="#" class="alert-link"><form:errors path="passwordnuevo2"  /></a>
											</div>	
										</div>	       
	       						
                           		 </div>
                          			  </div>
                          		 </c:if>
                            
                            
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
                            		
                            		
                            		
                            		
                                      <div class="hr-line-dashed"></div> 
                                     
											
											
                          
                          <div class="row">
				                                <div class="col-md-10 col-sm-10 col-lg-10 b-r">                                                                                                              
				                                <button class="btn btn-default " type="button" id="volver"><strong><span class="fa fa-caret-left"></span> Volver</strong></button>&nbsp;&nbsp;&nbsp;&nbsp;
				                                <button class="btn btn-default " type="button" id="limpiar" ><strong><span class="fa fa-eraser"></span> Limpiar Campos</strong></button>&nbsp;&nbsp;&nbsp;&nbsp;
				                                </div>
                							  <div class="col-md-2 col-sm-2 col-lg-2 text-center">                                                                                                              
                                        			<button class="btn btn-primary " type="submit" >Cambiar</button>
			                                </div>
			                                                                        
			                          </div>     
											
																															
																				
									                                   		                                   
                                </form:form>   
                               
                                
                                 
	       
	         
                        	</div>
						</div>
					</div>
				</div>   
            </div>

			
			<%@include file="footer.jsp"%>

		</div>
	</div>


		<%@include file="footerjs.jsp"%>
    
  <script>  
  
  var volver = document.getElementById('volver');
	
	volver.addEventListener("click", volverFuncion);
	
	function volverFuncion () {
		
		window.history.back()
		    		
	}  	 
  
var limpiar = document.getElementById('limpiar');
    	
    	limpiar.addEventListener('click', limpiarFuncion);
    	
    	function limpiarFuncion () {
    		
    		 document.getElementById("cambiarClaveForm").reset();
    	}
  	 
  	  </script>
	
	

</body>

</html>
