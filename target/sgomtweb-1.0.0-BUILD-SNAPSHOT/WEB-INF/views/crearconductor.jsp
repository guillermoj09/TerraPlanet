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
                            	   <h5>Creacion de Conductores &nbsp;&nbsp; &nbsp;&nbsp; <small> Por favor rellenar todos los campos ... </small></h5>          
                        	</div>                        	
                        	
                        	<div class="ibox-content">
                               
                              <form:form action="crearconductor" commandName="conductorForm" id = "conductorForm">
                              
                               <div class="row" id="contenedorPass">
							          <div class="form-group col-lg-2">
                                        <label>Rut</label> 
                                        <input type="text" placeholder="" class="form-control" name="rut"  id="rut"  tabindex="3" maxlength="10" required>
                                      </div> 
                                
                                      <div class="form-group col-lg-2">
                                        <label>Nombre</label> 
                                        <input type="text" placeholder="" class="form-control" name="nombre"  id="nombre"  tabindex="3" required>
                                      </div> 
                                                                        
                                      <div class="form-group col-lg-2">
                                        <label>Apellido</label> 
                                        <input type="text" placeholder=" " class="form-control" name="apellido" id="apellido" tabindex="6" >
                                      </div>
                                      
                                      <div class="form-group col-lg-2">
                                        <label>Telefono</label> 
                                        <input type="text" placeholder=" " class="form-control" name="telefono" id="telefono" tabindex="6" >
                                      </div>
                                      
                                      <div class="form-group col-lg-2">
                                        <label>Direccion</label> 
                                        <input type="text" placeholder=" " class="form-control" name="direccion" id="direccion" tabindex="6" >
                                      </div>  
                                                                    
                                     
                                       <div class="form-group col-lg-2">
                                 
												<label>Cliente</label>
												
												<select  name="cliRazonSocial" id="cliRazonSocial" data-placeholder="Seleccione Cliente" style="width:100%;" tabindex="7" class="form-control text-uppercase" required>
																					
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
							  		
							  		 <div class="form-group col-lg-2">
												<label>Asociar Llaves Ibutton - Operador</label>		
												<select data-placeholder="Seleccione Llaves Ibutton" name="ibuttoms" id="ibuttoms" multiple style="width:100%;" tabindex="4">
													
													  <c:if test="${not  empty ibuttoms }">	  
													  
													  		<c:forEach items="${ibuttoms}" var="i" varStatus="count" >	  	
																																					
																<option value="${i.ibuCodigo}" > ${i.ibuCodigo} </option>
																									
															</c:forEach>           
		            								</c:if>
		            									
												</select>
												
										</div> 
							  		                         
                            </div> <!-- row -->
                            
                                
                                      <div class="hr-line-dashed"></div> 
                                      
                          <div class="row">                            
                                <div class="col-lg-10 col-md-10 col-sm-10 b-r">   
                                <button class="btn btn-default " type="button" id="volver" ><strong><span class="fa fa-caret-left"></span><a href="consultarconductor"> Volver </a></strong></button>&nbsp;&nbsp;&nbsp;&nbsp;                                                                                                                                          
                                <!-- <button class="btn btn-default " type="button" ><strong><span class="fa fa-eraser"></span> Limpiar Campos</strong></button>&nbsp;&nbsp;&nbsp;&nbsp;  -->
                                </div>
                							  <div class="col-lg-2 col-md-2 col-sm-2 text-center">  
                					    <button class="btn btn-primary " type="button" onClick="validarFormulario()" >Guardar</button>                                                                                                            
                                        <!-- <button class="btn btn-primary " type="submit" onClick="validarFormulario()" >Guardar</button>  -->
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
    
    $( "#cliRazonSocial" ).select2( {
		placeholder: "Seleccione Cliente",
		// allowClear: true
		
	} ); 
    
    $( "#ibuttoms" ).select2( {
		placeholder: "Seleccione Llaves Ibutton",
		 //allowClear: true
		maximumSelectionLength: 1,		
		
	} ); 
    
    function validarFormulario(){
    	
    	var rut = document.getElementById('rut').value;    	
    	var nombre = document.getElementById('nombre').value;
    	var apellido = document.getElementById('apellido').value;
    	
    	
    	
    	//Rut
		if(rut == null || rut.length == 0 || /^\s+$/.test(rut)){
			alert('El campo Rut no debe ir vacío o lleno de solamente espacios en blanco');
			return false;
		}
		
		for ( i = 0; i < rut.length; i++ ) {
			if ( rut.charAt(i) == " " ) {
			
				alert('El Rut no puede contener espacios en blanco');
				return false
			
			}
			
		}
		
		repuesta = valida_rut( rut );
		
		mensaje = repuesta [1];
		retorno = repuesta [0];
		
		if(!retorno){
			alert(mensaje);
			return false
		}
		
		//Nombre
		if(nombre == null || nombre.length == 0 || /^\s+$/.test(nombre)){
			alert('El campo Nombre no debe ir vacío o lleno de solamente espacios en blanco');
			return false;
		}
    	
		//Apellido
		if(apellido == null || apellido.length == 0 || /^\s+$/.test(apellido)){
			alert('El campo Apellido no debe ir vacío o lleno de solamente espacios en blanco');
			return false;
		}
		
		//return true;
		
    	 document.getElementById("conductorForm").submit();
    	
    	
    }
    
    function valida_rut(T)
    {  	  
    	   var error="";
    	   var resp="";
    	   
    	   if(T.length < 8 || T.length > 9 ) {
    		   	error="El RUT debe contener 8 digitos como minimo";
    			resp=false;
    			return [resp, error];
           } else {
                
    		  var cadena=T;
    		  var dgv_ingresado = cadena.charAt(T.length-1); //RUT sin digito verificador
    		  var rut_sin_dgv = cadena.substr(0, T.length-1); //RUT sin digito verificador
    		  
    		  if(isNaN(rut_sin_dgv)==true){
    			error="Verifique que el RUT ingresado sea valido";
    			resp=false;
    			return [resp, error];
    		  }		  
    		  
    		  var dgv_calculado="";
    		  //alert("RUT: "+cadena+" - RUT sin DGV:"+rut_sin_dgv+" - DGV:"+dgv_ingresado);
    		  //var T=rut_sin_dv;
    		  
    		  var M=0,S=1;
    		  for(;rut_sin_dgv;rut_sin_dgv=Math.floor(rut_sin_dgv/10))
    		  S=(S+rut_sin_dgv%10*(9-M++%6))%11;
    		  
    		  dgv_calculado = S?S-1:'K';
    		  
    		  if(dgv_ingresado==dgv_calculado){
    				
    				//error="EL RUT es correcto";
    				resp=true;
    				return [resp, error];
    		  }else{
    			  	error="Verifique el RUT o el Digito Verificador ya que los mismos no coinciden";
    				resp=false;
    				return [resp, error];
    		  }
    		  //return S?S-1:'K';
    		  //alert(S?S-1:'K');
    	   }
     }
    
    </script>
	
	<div class="lg-12"><div class="lg-3"></div><div class="lg-3"></div><div class="lg-3"></div></div>

</body>

</html>
