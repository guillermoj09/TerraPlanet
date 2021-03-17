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
	<script type="text/javascript">
		
	
function validarFormulario(){
		
		
		var username = document.getElementById('usuLogin').value;
		var correo = document.getElementById('usuMail').value;
		var nombre = document.getElementById('usuNombre').value;
		var apellido = document.getElementById('usuApellido').value;
		var rut = document.getElementById('usuRut').value;
		
		
		//USUARIO
		if(username == null || username.length == 0 || /^\s+$/.test(username)){
			alert('El campo Usuario no debe ir vacío o lleno de solamente espacios en blanco');
			return false;
		}
		
		for ( i = 0; i < username.length; i++ ) {
			if ( username.charAt(i) == " " ) {
			
				alert('El usuario no puede contener espacios en blanco');
				return false
			
			}
			
		}
		
		if(username.length < 5 ){
			alert('Valor campo usuario debe tener un minimo 5 caracteres');
			return false;
		}
		
		//CORREO
		if(correo == null || correo.length == 0 || /^\s+$/.test(correo)){
			alert('El campo Correo no debe ir vacío o lleno de solamente espacios en blanco');
			return false;
		}
		
		if(! validar_email( correo ) )
		{
			alert('Formato de correo incorrecto');
			  return false;
		}
		
		//Nombre
		if(nombre == null || nombre.length == 0 || /^\s+$/.test(nombre)){
			alert('El campo Nombre no debe ir vacío o lleno de solamente espacios en blanco');
			return false;
		}
		
		for ( i = 0; i < nombre.length; i++ ) {
			if ( nombre.charAt(i) == " " ) {
			
				alert('El Nombre no puede contener espacios en blanco');
				return false
			
			}
			
		}
		
		//Apellido
		if(apellido == null || apellido.length == 0 || /^\s+$/.test(apellido)){
			alert('El campo Apellido no debe ir vacío o lleno de solamente espacios en blanco');
			return false;
		}
		
		for ( i = 0; i < apellido.length; i++ ) {
			if ( apellido.charAt(i) == " " ) {
			
				alert('El Apellido no puede contener espacios en blanco');
				return false
			
			}
			
		}
		
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
		
		repuesta = [true,""];
		
		mensaje = repuesta [1];
		retorno = repuesta [0];
		
		if(!retorno){
			alert(mensaje);
			return false
		}
		
					
		 document.getElementById("miform").submit();
		 //return true;
	}
	
	//funcion aparte validar email
	function validar_email( email ) 
	{
	    var regex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	    return regex.test(email) ? true : false;
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
							 <h5> Crear Usuario &nbsp;&nbsp; &nbsp;&nbsp; <small> Por favor rellenar todos los campos ... </small></h5>
                            
                        	</div>   
                        	                        
                        	
                        	<div class="ibox-content">
                               
                              <form:form action="crearusuario.html" commandName="crearUsuarioForm" id="miform">
                                   <div class="row" id="contenedorPass">
									
									 
									  <div class="form-group col-lg-2">											
												<label>Nombre de usuario</label> 
												<input type="text"  class="form-control" name="usuLogin" id="usuLogin" maxlength="20"  tabindex="1">											
									  </div>		
									  <div class="form-group col-lg-2">											
												<label>Correo</label> 
												<input type="email"  class="form-control" name="usuMail" id="usuMail" tabindex="4">
									  </div>
																						
										
										<div class="form-group col-lg-2">
											
												<label>Nombre Completo</label> 
												<input type="text" class="form-control" name="usuNombre"  id="usuNombre"  tabindex="3">
													
										</div>									
											
										<div class="form-group col-lg-2">	
											
												<label>Apellido</label> 
												<input type="text" class="form-control" name="usuApellido" id="usuApellido" tabindex="6" maxlength="12">
											
										</div>	
											
										
										
										
										<div class="form-group col-lg-2">	
											
												<label>Rut</label> 
												<input type="text"class="form-control" name="usuRut" id="usuRut" tabindex="6" maxlength="12">
											
										</div>	
											
										<div class="form-group col-lg-2">	
											
												<label>Direccion</label> 
												<input type="text"  class="form-control" name="usuDireccion" id="usuDireccion" tabindex="6" maxlength="12">
											
										</div>	
											
										<div class="form-group col-lg-2">	
											
												<label>Activo</label> 
												<select name="usuEstado"  id="usuEstado"  class="form-control" tabindex="7">
													<option value="1" selected>SI</option>
													<option value="0">NO</option>
												</select>
											
										</div>	
										
										<c:if test="${usuario.perfilid == '6'}"> 
										<div class="form-group col-lg-2">	
											
												<label>Perfil</label> 
												<select name="perfil"  id="perfil"  class="form-control" tabindex="7">
												 <c:forEach items="${perfiles}" var="p" varStatus="count" >	  
													<option  value="${p.perId}">${p.perNombre}</option>													
												 </c:forEach>	
												</select>
											
										</div>	
										</c:if>
										
										<c:if test="${usuario.perfilid == '7' || usuario.perfilid == '8'}"> 
										<div class="form-group col-lg-2">	
										
												<label>Perfil</label> 
												<select name="perfil"  id="perfil"  class="form-control" tabindex="7">												
												<option  value="${userlogin.perfilid}" Selected>${userlogin.perfilnombre}</option>																										
												
												 <c:forEach items="${perfiles02}" var="p" varStatus="count" >	
												  <c:if test="${userlogin.perfilid!=p.perId}">   
													<option  value="${p.perId}">${p.perNombre}</option>
												  </c:if>
												  
																						
												 </c:forEach>
												 
												   </select>
																								
										</div>	
										</c:if>
										
										 <div class="form-group col-lg-2">
                                 
												<label>Cliente </label>
												
												<select   name="cliente" id="cliente"  style="width:100%;" tabindex="4" class="form-control text-uppercase" requerid>
																																																		
														<c:if test="${not  empty clientes }">
																									
														<c:forEach items="${clientes}" var="c" varStatus="count" >	  
														
														   <c:if test="${not  empty c }">	
														   														  														 													
															<option value="${c.cliRut}">${c.cliRazonSocial}</option>
																								 		 												 		  
												 		  </c:if>
												 		  	    
												      </c:forEach>	 
												      
												       </c:if>   																											
												</select>												
							 		 </div>    
											
											
																		
                                   		</div>
                                   		
                                   		
											   <div class="hr-line-dashed"></div> 
																						                          
                          
                          					<div class="row">
				                                <div class="col-md-10 col-sm-10 col-lg-10 b-r">                                                                                                              
				                                <button class="btn btn-default " type="button" id="volver"><strong><span class="fa fa-caret-left"></span><a href="consultarusuarios.html"> Volver</a></strong></button>&nbsp;&nbsp;&nbsp;&nbsp;
				                                <button class="btn btn-default " type="button" id="limpiar"><strong><span class="fa fa-eraser"></span> Limpiar Campos</strong></button>&nbsp;&nbsp;&nbsp;&nbsp;
				                                </div>
                							  <div class="col-md-2 col-sm-2 col-lg-2 text-center">                                                                                                              
                                        			<button class="btn btn-primary " type="button" onClick="validarFormulario()" >Guardar</button>
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
    
    <script>
               
    
    $( "#cliente" ).select2( {
		placeholder: "Seleccione Cliente",		 
		//allowClear: true 
		
	} );
    
    </script>
    
      <script>
            
    	var limpiar = document.getElementById('limpiar');
    	
    	limpiar.addEventListener('click', limpiarFuncion);
    	
    	function limpiarFuncion () {
    		
    		//$("#cliente").select2("val", "");
    		
    		 var options = document.getElementById('usuEstado');
    		 
    		// options.selectedIndex = "-1";
    		 
    		 //recorrer select    		 
    		 for (var i = 0, l = options.length; i < l; i++) {    			 
    		     options[i].selected = options[i].defaultSelected;    			
    		 }
    		
    		 document.getElementById("miform").reset();
    		     		    		 
    	}
  	 
  	  </script>
	

</body>

</html>
