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
		
		//alert("entro");

		var username = document.getElementById('usuLogin').value;
		var correo = document.getElementById('usuMail').value;
		var nombre = document.getElementById('usuNombre').value;
		var apellido = document.getElementById('usuApellido').value;
		
		
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
			if ( nombre.charAt(i) == " " ) {
			
				alert('El Apellido no puede contener espacios en blanco');
				return false
			
			}
			
		}
		
		
			
		  document.getElementById("crearUsuarioForm").submit();
		 //return true;
	}
	
	//funcion aparte validar email
	function validar_email( email ) 
	{
	    var regex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	    return regex.test(email) ? true : false;
	}
			
				
	</script>


	<div id="wrapper">

		<%@include file="menu.jsp"%>

		<div id="page-wrapper" class="gray-bg">
			<div class="row border-bottom">
							</div>

			  <!-- Titulo del Menu  -->
			  	<%@include file="barramenu.jsp"%>
          <!--   <div class="row wrapper border-bottom white-bg page-heading">
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
                            <strong>Modificar Usuario</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>  -->

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
							<h5> Modificar Usuario &nbsp;&nbsp; &nbsp;&nbsp; <small> Por favor rellenar todos los campos ... </small></h5>
                            	
                        	</div>                        	
                        	
                        	<div class="ibox-content">
                            
                               
                              <form:form action="editarusuario.html" commandName="crearUsuarioForm">
                              
                                   <div class="row" id="contenedorPass">
									    
									    
									     <div class="form-group col-lg-2">
											
												<label>Nombre de usuario</label> 
												<input type="text"  class="form-control"  name="usuLogin" id="usuLogin" maxlength="20"  tabindex="1" value="${userlogin.username}" disabled >
												<input type="hidden"   class="form-control" name="usuLogin02" id="usuLogin02" maxlength="20"  tabindex="1" value="${userlogin.username}">
											
										</div>	
											
										 <div class="form-group col-lg-2">	
											
												<label>Correo</label> 
												<input type="email" placeholder="Ingresar email" class="form-control" name="usuMail" id="usuMail" tabindex="4" value="${userlogin.correo}">
															
										 </div>																		 										
																					 
										
										 <div class="form-group col-lg-2">
											
												<label>Nombre </label> 
												<input type="text"  class="form-control" name="usuNombre" id="usuNombre"  tabindex="3" value="${userlogin.nombre}">
															
										</div>							
											
										 <div class="form-group col-lg-2">	
											
												<label>Apellido</label> 
												<input type="text"  class="form-control" name="usuApellido"  id="usuApellido" tabindex="6" maxlength="12"  value="${userlogin.apellido}">
											
										</div>	
											
										
										
											
										<div class="form-group col-lg-2">
											
												<label>Rut </label> 
												<input type="text"  class="form-control" name="usuRut" id="usuRut"  tabindex="3" value="${userlogin.rut}" disabled>
											
										</div>	
											 
										 <div class="form-group col-lg-2">	 	
											
												<label>Direccion </label> 
												<input type="text"  class="form-control" name="usuDireccion" id="usuDireccion"  tabindex="3" value="${userlogin.direccion}">
											
										</div>	
											 
											
										 <div class="form-group col-lg-2">	
											
												<label>Activo</label> 
												<select  name="usuEstado" id="usuEstado"  class="form-control" tabindex="7" requerid>
													<option value="1" 
													<c:if test="${userlogin.estado==1}">  Selected</c:if>>SI													
													</option>
													<option value="0" 
													<c:if test="${userlogin.estado==0}">  Selected</c:if>>No													
													</option>
										 			
												</select>
											   
										</div>	
										
										<c:if test="${usuario.perfilid == '6'}"> 
										<div class="form-group col-lg-2">	
										
												<label>Perfil</label> 
												<select name="perfil"  id="perfil"  class="form-control" tabindex="7">												
												<option  value="${userlogin.perfilid}" Selected>${userlogin.perfilnombre}</option>														
												 <c:forEach items="${perfiles}" var="p" varStatus="count" >	
												  <c:if test="${userlogin.perfilid!=p.perId}">   
													<option  value="${p.perId}">${p.perNombre}</option>
												  </c:if>
												 													
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
												
												<!-- <label>Perfil</label> 
												<input type="text"  class="form-control"  name="perfil" id="perfil" maxlength="20"  tabindex="1" value="${userlogin.perfilid}" disabled >
												<input type="hidden"   placeholder="jperez" class="form-control" name="perfil02" id="perfil02"  value="${userlogin.perfilid}">  -->
											
										</div>	
										</c:if>
										
										 <div class="form-group col-lg-2">
                                 
												<label>Cliente </label>
												
												<select   data-placeholder="Seleccione cliente" name="cliente" id="cliente"  style="width:100%;" tabindex="4" class="form-control text-uppercase" >
																					
														<option value="${userlogin.clienterut}" selected> ${userlogin.cliente} </option>	
														
														<c:if test="${not  empty clientes }">
																									
														<c:forEach items="${clientes}" var="c" varStatus="count" >	  
														
														   <c:if test="${not  empty c }">	
														   
														    <c:if test="${userlogin.clienterut ne c.cliRut}"> <!-- NE es Not equals -->
														 													
															<option value="${c.cliRut}">${c.cliRazonSocial}</option>
												
												 		  </c:if>
												 		  
												 		  </c:if>
												 		  	    
												      </c:forEach>	 
												      
												       </c:if>   																											
												</select>												
							 		 </div>  
							 		 							 		 
							 		    <%!//<c:if test="${usuario.perfilid == '6'}">  %>      
										 <div class="form-group col-lg-6">
												<label>Asociar Menu - Usuario </label>		
												<select data-placeholder="Seleccione menus..." name="menus" id="menus" multiple style="width:100%;" tabindex="4">
													<!-- 
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
														 -->
														 

														 <c:set var = "optgroupy" scope = "session" value = ""/>
      														 
														 <c:if test="${not  empty menus03 }">													  
													  		<c:forEach items="${menus03}" var="m" varStatus="count" >	
													  			
													  			<c:if test="${optgroupy ne m.mod_nombre}">	
													  				<optgroup label="${m.mod_nombre}">		
													  				<c:set var = "optgroupy" scope = "session" value = "${m.mod_nombre}"/>
													  			</c:if>			
													  																																			
																<option value="${m.men_id}" ${m.selected}> ${m.men_nombre} </option>																									
															</c:forEach>           
		            									</c:if>
		            									    		            										            								
												</select>												
										</div> 
										 <div class="form-group col-lg-2">
										 <label>Todos los Menus</label>
										 <input type="checkbox" class="js-switch" id = "all"> 
										 </div>
										 
										 
                                                   
										 
										<%!//</c:if>     %>    
										
										 <div class="form-group col-lg-12">
												<label>Asociar Equipos - Usuario</label>		
												<select data-placeholder="Seleccione vehiculos" name="vehiculos" id="vehiculos" multiple style="width:100%;" tabindex="4">
													
													  <c:if test="${not  empty vehiculos }">	  
													  
													  		<c:forEach items="${vehiculos}" var="v" varStatus="count" >	  	
																																					
																<option value="${v.vehPatente}" selected> ${v.vehPatente} - ${v.nrointerno} - ${v.tipoVehiculo.tipvDescripcion} </option>
																									
															</c:forEach>           
		            								</c:if>
		            								
		            								 <c:if test="${not  empty vehiculos02 }">	  
													  
													  		<c:forEach items="${vehiculos02}" var="v2" varStatus="count" >	  	
																																					
																	<option value="${v2.vehPatente}"> ${v2.vehPatente} - ${v2.nrointerno} - ${v2.tipoVehiculo.tipvDescripcion} </option>
																									
															</c:forEach>           
		            								</c:if>
														
														
												</select>
												
										</div> 
										 <div class="form-group col-lg-2">
										 <label>Toda la Flota</label>
										 <input type="checkbox" class="js-switch" id = "allFlota"> 
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
    
   <!-- Switchery -->
   <script src="resources/inspinia_v2.9/FullVersion/js/plugins/switchery/switchery.js"></script>
    
  <script>
      
    $( "#cliente" ).select2( {
		placeholder: "Seleccione Cliente",
		//allowClear: true
		
	} );
    
    $( "#menus" ).select2( {
		placeholder: "Seleccione Menus",
		allowClear: true
		
	} );
    
    $( "#vehiculos" ).select2( {
		placeholder: "Seleccione Equipos",
		allowClear: true
		
	} );
    
    $("#all").click(function(){
        if($("#all").is(':checked') ){
        	
        	$("#menus").find('option').prop("selected","selected");
        	$("#menus").find('option').trigger("change");
        	$("#menus").find('option').click();
            
        }else{
        	
        	//$("#menus").find('option').removeAttr("selected","selected");
        	$("#menus").find('option').prop("selected",false);
        	$("#menus").find('option').trigger("change");
         }
    });
    
    $("#allFlota").click(function(){
        if($("#allFlota").is(':checked') ){
        	
        	$("#vehiculos").find('option').prop("selected","selected");
        	$("#vehiculos").find('option').trigger("change");
        	$("#vehiculos").find('option').click();
            
        }else{
        	
        	//$("#menus").find('option').removeAttr("selected","selected");
        	$("#vehiculos").find('option').prop("selected",false);
        	$("#vehiculos").find('option').trigger("change");
         }
    });
    
   
    </script>
    
     <script>
    	
    	
		var limpiar = document.getElementById('limpiar');
    	
    	limpiar.addEventListener('click', limpiarFuncion);
    	
    	function limpiarFuncion () {
    		
    	 $("#cliente").select2("val", "");
    	 $("#menus").select2("val", "");
    		
   		 var options = document.getElementById('usuEstado');
   		 
   		// options.selectedIndex = "-1";
   		 
   		 //recorrer select    		 
   		 for (var i = 0, l = options.length; i < l; i++) {    			 
   		     options[i].selected = options[i].defaultSelected;    			
   		 }
    		
    		// document.getElementById("crearUsuarioForm").reset();
   		document.getElementById("crearUsuarioForm").usuApellido.value = "";
		document.getElementById("crearUsuarioForm").usuNombre.value = "";
		document.getElementById("crearUsuarioForm").usuMail.value = "";
		document.getElementById("crearUsuarioForm").usuDireccion.value = "";
				
    	}
    
  	  </script>
	

	

</body>

</html>
