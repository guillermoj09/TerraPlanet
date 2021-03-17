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
<script>

var mensaje = "";


if(document.getElementById("smsForm").predefinido.selectedIndex >= 1){
	
	document.getElementById("smsForm").parametro.disabled = false;	
	
	var predefinido = document.getElementById("smsForm").predefinido.value;		
		
	document.getElementById("smsForm").comando.value = ">"+predefinido+"<"; 
			
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
	       									<div class="${estilo}">
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
							<div class="ibox-title"><!-- Edicion de Vehiculos -->
                            	   <h5>Comando GPS&nbsp;&nbsp; &nbsp;&nbsp; <small> Por favor rellenar todos los campos a actualizar... </small></h5>          
                        	</div>                        	
                        	
                        	<div class="ibox-content">
                               
                              <form:form action="comandogps" commandName="ComandoGpsForm">
                              
                               <div class="row" id="contenedorPass">
							    
                                        <div class="form-group col-lg-2">
                                                              <label class="font-normal">GPS ID</label>
											                <select  tabindex="2" style="width:100%;" name="idpatente" id="idpatente" data-placeholder="Seleccione una ID Patente" class="form-control text-uppercase"  required>
											                <option value=""></option>
													                <c:forEach items="${listPatentes}" var="f" varStatus="count" >														
																	   <c:if test="${not  empty f }">																	   															   
																	  		 <option value="${f.usuvehId}">${f.usuvehId} - ${f.vehPatenteVehiculo}<c:if test="${not  empty f.nroInterno }"><p class="text-secondary"> - ${f.nroInterno}</p> </c:if></option>																	   														
															 		   </c:if>	    
														     		</c:forEach>	
											                </select> 
                                                      	</div>
                                            
                                       <div class="form-group col-lg-2"> 
                                                   	
                                       <label class="font-normal">PREDEFINIDO</label>
											                <select  tabindex="2" style="width:100%;" name="predefinido" id="predefinido" data-placeholder="Seleccione una Patente" class="form-control text-uppercase" onchange="selectt1()">
											                	
											                	<option value="">Otro</option>
											                	<option value="">QTD00</option>
											                	<option value="">QED46</option>
											                	<option value="">QVR</option>
											                	<option value="">SSR55AA*</option>
											                	<option value="">SED47_IP1</option>
											                	<option value="">IP1_3</option>
											                	<option value="">TD00_30s</option>
											                	<option value="">TD00_60</option>
											                	<option value="">SRS1 9600</option>
											                	<option value="">SEDU55AA</option>
											                	<option value="">QGRC</option>
											                	<option value="">QPT</option>
											                	<option value="">QPI</option>
											                	<option value="">SEDA47_LOG</option>
											                	<option value="">IP1_0</option>
											                	<option value="">TDO01_30s</option>
											                	<option value="">TD01_1H</option>
											                	<option value="">SRS1 11200</option>
											                														            	   
														     	
											                </select> 
                                       </div>               	
                                                                       
                                      <div class="form-group col-lg-2">
                                        <label>Comandos</label> 
                                        <input type="text" placeholder="" class="form-control" name="comando" id="comando" tabindex="6" value="" >
                                      </div>  
                                      
                                	</div> <!-- row -->
                                
                                      <div class="hr-line-dashed"></div> 
                                
										
										<div class="row">
				                               
                							  <div class="col-md-2 col-sm-2 col-lg-2 text-center">                                                                                                              
                                        			<button class="btn btn-primary " type="submit" >Enviar</button>
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
    <script src="resources/js/select2espanol.js"></script>
  
    
    <script>
    	
    
		$(document).ready(function(){
			var options = {};
			options.ui = {
				container: "#contenedorPass",
				showVerdictsInsideProgressBar: true,
				viewports: {
                    progress: ".pwstrength_viewport_progress"
                }
			};
			options.common = {
                debug: false,
            };
            $('.example1').pwstrength(options);
						
		})
	</script>
	
	
	<script>
	
	$.fn.select2.defaults.set('language', 'es');
 
    
    $( "#predefinido" ).select2( {
		placeholder: "Seleccione Comando Predefinido",
		 allowClear: true,
		 
		
	} ); 
    
    
    $( "#idpatente" ).select2( {
		placeholder: "Seleccione ID GPS",
		 //allowClear: true
		
	} ); 
                 
    
    </script>
    
     <script>
     
     
     /*
     
      //javascript puro, no funciona para elementos dentro de select2 jquery
    /* var a = document.getElementById('tipoVehiculo');
     a.addEventListener('change', function() {
       alert(this.value);
     }, false);
     
     //validar camion solo vea los tags
     var selecttipo = document.getElementById('tipoVehiculo');
     var selecttags = document.getElementById('tags');
     //alert(selecttipo);
     selecttipo.addEventListener("change", bloquearrdiv);
     
 	function bloquearrdiv () {
     alert("hola");
     for (var i = 0, l = selecttipo.length; i < l; i++) {
    	 	if (options[i].value  == 6){
    	 		
    	 		selecttags.disabled = true;
    	 		
    	 		
    	 	}else{
    	 		
    	 		selecttags.disabled = false;
    	 		
    	 		
    	 	}
    	 		
				    	 
		    // options[i].selected = options[i].defaultSelected;    			
		 }
     
 	} */
     
     
     
     </script>
    
</body>

</html>
