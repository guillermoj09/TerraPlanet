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
                            	   <h5>Creacion de iButton &nbsp;&nbsp; &nbsp;&nbsp; </h5>          
                        	</div>                        	
                        	
                        	<div class="ibox-content">
                               
                              <form:form action="creaributton" commandName="basicForm" id = "ibuttonForm">
                              
                               <div class="row" id="contenedorPass">
							          <div class="form-group col-lg-2">
                                        <label>iButton</label> 
                                        <input type="text" placeholder="" class="form-control" 
                                        name="ibuttom"  id="ibuttom"  tabindex="3" maxlength="12" minlength="8" required>
                                      </div> 
                                
							  		                         
                            </div> <!-- row -->
                            
                                
                                      <div class="hr-line-dashed"></div> 
                                      
                          <div class="row">                            
                                <div class="col-lg-10 col-md-10 col-sm-10 b-r">   
                                <button class="btn btn-default " type="button" id="volver" ><strong><span class="fa fa-caret-left"></span><a href="consultaibutton.html"> Volver </a></strong></button>&nbsp;&nbsp;&nbsp;&nbsp;                                                                                                                                          
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
    
	
	<div class="lg-12"><div class="lg-3"></div><div class="lg-3"></div><div class="lg-3"></div></div>

</body>

</html>
