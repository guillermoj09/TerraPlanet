<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="true"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="cl.samtech.sgomt.object.*"%>


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

			
              <!-- Titulo del Menu  -->
           	<%@include file="barramenu.jsp"%>

            <div class="wrapper wrapper-content animated fadeInRight">
            
            <c:if test="${not empty mensaje}">
              
               <div class="row">
                            <div class="col-lg-12">
	       
			    <div class="${estilo}">
    				<button type="button" class="close" data-dismiss="alert">&times;</button>
    				<a href="#" class="alert-link"> ${mensaje}</a>    				
 				</div>
 				
 				</div>
                            </div>
	       
	      	     </c:if>
            
            
				<div class="row">
				
				
					<div class="col-lg-12">
					
				
						<div class="ibox">
							<div class="ibox-title">
                            	   <h5>Editar menu &nbsp;&nbsp; &nbsp;&nbsp; <small> Por favor rellenar todos los campos ... </small></h5>          
                        	</div>                        	
                        	
                        	<div class="ibox-content">
                               
                              <form:form action="editarmenu" commandName="MenuForm">
                              
                               <div class="row" id="contenedorPass">
							          <div class="form-group col-lg-2">
                                        <label>Nombre</label> 
                                        <input type="text"  class="form-control" name="menNombre"  id="menNombre" value="${menu.menNombre}" tabindex="3"  required>
                                         <input type="hidden"  name="menId"  id="menId" value="${menu.menId}" >
                                      </div> 
                                
                                      <div class="form-group col-lg-2">
                                        <label>Link</label> 
                                        <input type="text" class="form-control" name="menLink"  id="menLink"  tabindex="3"   value="${menu.menLink}" required>
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
                                                                                                                 
                                       <div class="form-group col-lg-2">
                                 
												<label>Modulo</label>
												
												<select  name="modulo" id="modulo" data-placeholder="Seleccione Modulo" style="width:100%;" tabindex="7" class="form-control text-uppercase" required>
														
														<option value="${menu.modulo.modId}" selected> ${menu.modulo.modNombre} </option>	
														
														<c:if test="${not  empty modulos }">																									
														<c:forEach items="${modulos}" var="m" varStatus="count" >	  														
														   <c:if test="${not  empty m }">	
														 		<c:if test="${m.modId ne menu.modulo.modId }">								
															<option value="${m.modId}">${m.modNombre}</option>
																</c:if>													
												 		  </c:if>	    
												      </c:forEach>	 
												      
												       </c:if>   																											
												</select>												
							  		</div>                            
                            </div> <!-- row -->
                            
                                
                                      <div class="hr-line-dashed"></div> 
                                      
                          <div class="row">                            
                                <div class="col-lg-10 col-md-10 col-sm-10 b-r">   
                                <button class="btn btn-default " type="button" id="volver" ><strong><span class="fa fa-caret-left"></span><a href="consultarmenuxmodulo?id=${menu.modulo.modId}"> Volver </a></strong></button>&nbsp;&nbsp;&nbsp;&nbsp;                                                                                                                                          
                                <!-- <button class="btn btn-default " type="button" ><strong><span class="fa fa-eraser"></span> Limpiar Campos</strong></button>&nbsp;&nbsp;&nbsp;&nbsp;  -->
                                </div>
                							  <div class="col-lg-2 col-md-2 col-sm-2 text-center">                                                                                                              
                                        <button class="btn btn-primary " type="submit" >Guardar</button>
                                </div>
                                                                       
                          </div>            
                                										
                                   		                              
                                </form:form>                                      
                                 </div> 
                        	</div>
						</div> <!-- row -->
					</div>
				</div>   
        
        	<%@include file="footer.jsp"%>
        
            </div>

		</div>
	
	<%@include file="footerjs.jsp"%>
	
	  <!-- select 2 -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/select2/select2.full.min.js"></script>    
	
	<script>
               	
    $( "#modulo" ).select2( {
		placeholder: "Seleccione Modulo",
		
		
	} ); 
    
    </script>
	

</body>

</html>
