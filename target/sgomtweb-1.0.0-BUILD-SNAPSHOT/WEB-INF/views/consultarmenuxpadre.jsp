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

<link href="resources/inspinia_v2.9/FullVersion/css/plugins/switchery/switchery.css" rel="stylesheet">

</head>

<body class="top-navigation">
	<script type="text/javascript">
	
function validarFormulario(){
					
		  document.getElementById("crearUsuarioForm").submit();
		 //return true;
	}
				
	</script>


	<div id="wrapper">

		<%@include file="menu.jsp"%>

		<div id="page-wrapper" class="gray-bg">
			<div class="row border-bottom">
							</div>

			  <!-- Titulo del Menu  -->
			  	<%@include file="barramenu.jsp"%>
           

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
						<div class="ibox">
							<div class="ibox-title">
							<h5> Modificar Menu Padre &nbsp;&nbsp; &nbsp;&nbsp; <small> Por favor rellenar todos los campos ... </small></h5>
                            	
                        	</div>                        	
                        	
                        	<div class="ibox-content">
                            
                               
                              <form:form action="consultarmenuxpadre" commandName="menuForm">
                              
                                   <div class="row" id="contenedorPass">
									    
									    
									     <div class="form-group col-lg-2">
                                        <label>Nombre</label> 
                                        <input type="text"  class="form-control" name="menNombre"  id="menNombre" value="${menu.menNombre}" tabindex="3"  required>
                                         <input type="hidden"  name="menId"  id="menId" value="${menu.menId}" >
                                         <input type="hidden"  name="modulo"  id="modulo" value="${menu.modulo.modId}" >
                                            		
                                      </div> 
											
										   <div class="form-group col-lg-2">	
											
												<label>Activo</label> 
												<select  name="estado" id="estado"  class="form-control" tabindex="7" requerid>
													<option value="1" 
													<c:if test="${menu.menEstado==1}">  Selected</c:if>>SI													
													</option>
													<option value="0" 
													<c:if test="${menu.menEstado==0}">  Selected</c:if>>No													
													</option>
										 			
												</select>
											   
										</div>																			 										
																 		 							 		       
										 <div class="form-group col-lg-6">
												<label>Asociar Menu - Padre</label>		
												<select data-placeholder="Seleccione menus..." name="menus" id="menus" multiple style="width:100%;" tabindex="4">
													
													  <c:if test="${not  empty menus }">	  
													  
													  		<c:forEach items="${menus}" var="m" varStatus="count" >	  	
																																					
																<option value="${m.menId}" selected> ${m.menNombre} </option>
																									
															</c:forEach>           
		            								</c:if>
		            								
		            								 <c:if test="${not  empty menus02 }">	  
													  
													  		<c:forEach items="${menus02}" var="m2" varStatus="count" >	  	
																																					
																<option value="${m2.menId}" > ${m2.menNombre} </option>
																									
															</c:forEach>           
		            								</c:if>
														
														
												</select>
												
										</div> 
										 
																					
                                    </div>
                                    
                                       <div class="hr-line-dashed"></div> 
											
							
                          <div class="row">
				                                <div class="col-md-10 col-sm-10 col-lg-10 b-r">                                                                                                              
				                                 <button class="btn btn-default " type="button" id="volver"><strong><span class="fa fa-caret-left"></span><a href="consultarmenuxmodulo?id=${menu.modulo.modId}"> Volver</a></strong></button>&nbsp;&nbsp;&nbsp;&nbsp;
				                                <button class="btn btn-default " type="button" id="limpiar"><strong><span class="fa fa-eraser"></span> Limpiar Campos</strong></button>&nbsp;&nbsp;&nbsp;&nbsp;
				                                </div>
                							  <div class="col-md-2 col-sm-2 col-lg-2 text-center">                                                                                                              
                                        			<button class="btn btn-primary " type="submit">Guardar</button>
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

	<!-- select 2 -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/select2/select2.full.min.js"></script>
    
   <!-- Switchery -->
   <script src="resources/inspinia_v2.9/FullVersion/js/plugins/switchery/switchery.js"></script>
    
  <script>
    
    $( "#menus" ).select2( {
		placeholder: "Seleccione Menus",
		allowClear: true
		
	} );
      
    </script>
    
     <script>
    	    	
		var limpiar = document.getElementById('limpiar');
    	
    	limpiar.addEventListener('click', limpiarFuncion);
    	
    	function limpiarFuncion () {
    		    	
    	 $("#menus").select2("val", "");
    		   		
   		document.getElementById("menuForm").menNombre.value = "";
						
    	}
    
  	  </script>
	

</body>

</html>
