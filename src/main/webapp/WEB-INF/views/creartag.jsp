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
                            	   <h5>Creacion de Tags &nbsp;&nbsp; &nbsp;&nbsp; <small> Por favor rellenar todos los campos ... </small></h5>          
                        	</div>                        	
                        	
                        	<div class="ibox-content">
                               
                              <form:form action="creartag.html" commandName="TagForm">
                              
                               <div class="row" id="contenedorPass">
							          <div class="form-group col-lg-2">
                                        <label>ID</label> 
                                        <input type="text" placeholder="T-00000" class="form-control" name="tagId"  id="tagId"  tabindex="3" maxlength="10" required>
                                      </div> 
                                
                                      <div class="form-group col-lg-2">
                                        <label>Serie</label> 
                                        <input type="text" placeholder="181017F3" class="form-control" name="tagSerie"  id="tagSerie"  tabindex="3" required>
                                      </div> 
                                                                        
                                      <div class="form-group col-lg-2">
                                        <label>Marca</label> 
                                        <input type="text" placeholder=" " class="form-control" name="tagMarca" id="tagMarca" tabindex="6" >
                                      </div>  
                                                                    
                                      <div class="form-group col-lg-2">
                                        <label>Modelo</label> 
                                        <input type="text" placeholder=" " class="form-control" name="tagModelo" id="tagModelo" tabindex="6" >
                                      </div>   
                                       <div class="form-group col-lg-2">
                                 
												<label>Cliente</label>
												
												<select  name="tagCliente" id="tagCliente" data-placeholder="Seleccione Cliente" style="width:100%;" tabindex="7" class="form-control text-uppercase" required>
																					
														<!-- <option value=""></option>  -->	
														
														<c:if test="${not  empty clientes }">
																									
														<c:forEach items="${clientes}" var="c" varStatus="count" >	  
														
														   <c:if test="${not  empty c }">	
														 													
															<option value="${c.cliRut}">${c.cliRazonSocial}</option>
												
												 		  </c:if>	    
												      </c:forEach>	 
												      
												       </c:if>   																											
												</select>												
							  		</div>                            
                            </div> <!-- row -->
                            
                                
                                      <div class="hr-line-dashed"></div> 
                                      
                          <div class="row">                            
                                <div class="col-lg-10 col-md-10 col-sm-10 b-r">   
                                <button class="btn btn-default " type="button" id="volver" ><strong><span class="fa fa-caret-left"></span><a href="consultartag.html"> Volver </a></strong></button>&nbsp;&nbsp;&nbsp;&nbsp;                                                                                                                                          
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
	
	    
    <!-- Chosen -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/chosen/chosen.jquery.js"></script>
    
      <!-- select 2 -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/select2/select2.full.min.js"></script>
    
	
	<script>
               
	//$('.chosen-select').chosen({width: "100%"});
    
    $( "#tagCliente" ).select2( {
		placeholder: "Seleccione Cliente",
		// allowClear: true
		
	} ); 
    
    </script>
	

	

</body>

</html>
