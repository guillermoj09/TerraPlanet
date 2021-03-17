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
		
		
			
		  document.getElementById("crearUsuarioForm").submit();
		 //return true;
	}
	
	//funcion aparte validar email
	function validar_email( email ) 
	{
	    var regex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	    return regex.test(email) ? true : false;
	}
	
	function limpiar(){
		
		document.getElementById("crearUsuarioForm").username.value = "";
		document.getElementById("crearUsuarioForm").nombre.value = "";
		document.getElementById("crearUsuarioForm").correo.value = "";
		document.getElementById("crearUsuarioForm").telefono.value = "";
		
	}
	
		
	</script>



	<div id="wrapper">

		<%@include file="menu.jsp"%>

		<div id="page-wrapper" class="gray-bg">
			<div class="row border-bottom">
							</div>

			<%@include file="barramenu.jsp"%>

            <div class="wrapper wrapper-content animated fadeInRight">
				<div class="row">
				
				  <c:if test="${not empty mensaje}">
	       
			    <div class="${estilo}">
    				<button type="button" class="close" data-dismiss="alert">&times;</button>
    				<strong>!!!</strong> ${mensaje}.
 				</div>
	       
	      	     </c:if>
				
					<div class="col-lg-12">
						<div class="ibox">
							<div class="ibox-title">
                            	   <h5>Creacion de Vehiculos &nbsp;&nbsp; &nbsp;&nbsp; <small> Por favor rellenar todos los campos ... </small></h5>          
                        	</div>                        	
                        	
                        	<div class="ibox-content">
                               
                              <form:form action="crearvehiculo.html" commandName="VehiculoForm">
                              
                               <div class="row" id="contenedorPass">
							    <div class="row col-sm-12">
                                <div class="col-sm-2">
                                      <div class="form-group">
                                        <label>Patente</label> 
                                        <input type="text" placeholder="km3044" class="form-control" name="vehPatente"  id="vehPatente"  tabindex="3">
                                      </div> 
                                </div>
                                <div class="col-sm-2">                                        
                                      <div class="form-group">
                                        <label>Año</label> 
                                        <input type="text" placeholder="2014" class="form-control" name="vehAnio" id="vehAnio" tabindex="6" >
                                      </div>  
                                </div>
                                <div class="col-sm-2">                                   
                                      <div class="form-group">
                                        <label>Carga Soportada</label> 
                                        <input type="text" placeholder="33" class="form-control" name="vehCargaSoportada" id="vehCargaSoportada" tabindex="6" >
                                      </div>   
                                </div>
                                <div class="col-sm-2">                               
                                      <div class="form-group">
                                        <label>Volumen</label> 
                                        <input type="text" placeholder="Calle 9" class="form-control" name="vehCargaVolumen" id="vehCargaVolumen" tabindex="6" >
                                      </div>
                                </div>
                                <div class="col-sm-2">                               
                                      <div class="form-group">
                                        <label>Marca</label> 
                                        <input type="text" placeholder="Land" class="form-control" name="vehMarca" id="vehMarca" tabindex="6" >
                                      </div>
                                </div>
                                <div class="col-sm-2">      
                                      <div class="form-group">
                                        <label>Modelo</label> 
                                        <input type="text" placeholder="Toyota" class="form-control" name="vehModelo" id="vehModelo" tabindex="6" >
                                      </div>
                                </div>
                            </div>
                            <div class="row col-sm-12">
                                <div class="col-sm-2">
                                      <div class="form-group">
                                        <label>Motor</label> 
                                        <input type="text" placeholder="4.0" class="form-control" name="vehMotor"  id="vehMotor"  tabindex="3">
                                      </div> 
                                </div>
                                <div class="col-sm-2">                                        
                                      <div class="form-group">
                                        <label>Nro. Interno</label> 
                                        <input type="text" placeholder="101010101" class="form-control" name="vehVin" id="vehVin" tabindex="6" >
                                      </div>  
                                </div>                                
                                <div class="col-sm-2">                               
                                      <div class="form-group">
												<label>Usuarios </label>		
												<select data-placeholder="Seleccione Usuarios..." name="listareas" class="chosen-select" multiple style="width:350px;" tabindex="4">
													
													  <c:if test="${not  empty usuarios }">	  
													  
													  		<c:forEach items="${usuarios}" var="u" varStatus="count" >	  	
																																					
																<option value="${u.usuRut}">${u.usuLogin}</option>
																									
															</c:forEach>           
		            								</c:if>
																												
												</select>
												
											</div> 
                                </div>
                                <div class="col-sm-2">                               
                                       <div class="form-group">
                                 
												<label>Tipo Vehiculo </label>
												
												<select   name="tipoVehiculo" id="tipoVehiculo"  style="width:200px;" tabindex="4" class="form-control text-uppercase">
																					
														<option value=""></option>	
														
														<c:if test="${not  empty tipovehiculos }">
																									
														<c:forEach items="${tipovehiculos}" var="t" varStatus="count" >	  
														
														   <c:if test="${not  empty t }">	
														 													
															<option value="${t.tipvId}">${t.tipvDescripcion}</option>
												
												 		  </c:if>	    
												      </c:forEach>	 
												      
												       </c:if>   																											
												</select>												
							      </div>
                                </div>
                                <div class="col-sm-2">                                   
                                      <div class="form-group">
												<label>Tags </label>		
												<select data-placeholder="Seleccione Tags..." name="tags" class="chosen-select" multiple style="width:350px;" tabindex="4">
													
													  <c:if test="${not  empty tags }">	  
													  
													  		<c:forEach items="${tags}" var="t" varStatus="count" >	  	
																																					
																<option value="${t.tagId}"> ${t.tagSerie} </option>
																									
															</c:forEach>           
		            								</c:if>
														
														
												</select>
												
										</div>    
                                </div>
                                <!-- 
                                <div class="col-sm-2">      
                                      <div class="form-group">
                                        <label>Activo</label> 
                                        <select name="usuEstado"  id="usuEstado"   class="form-control col-sm-2" tabindex="7">
                                          <option value="1" selected>SI</option>
                                          <option value="0">NO</option>
                                        </select>
                                      </div>
                                </div>  -->
                                
                                </div>
                                
                                      <div class="hr-line-dashed"></div> 
                                
										
										<div class="col-lg-6">
										
										  <div>
												<button class="btn btn-sm btn-default m-t-n-xs" type="button" onClick="window.history.go(-1)" tabindex="9"><strong><span class="fa fa-caret-left"></span> Volver</strong></button>
												<button class="btn btn-sm btn-default m-t-n-xs" type="button" onClick="limpiar()" tabindex="10"><strong><span class="fa fa-eraser"></span> Limpiar Campos</strong></button>
												 <button class="btn btn-sm btn-primary m-t-n-xs" type="submit" onClick="validarFormulario()" id="guardar_usu" tabindex="11"><strong><span class="fa fa-check"></span> Guardar</strong></button>
												<!-- <button class="btn btn-sm btn-primary m-t-n-xs" type="button" onClick="validarFormulario()" id="guardar_usu" tabindex="11"><strong><span class="fa fa-check"></span> Guardar</strong></button>  -->												   
										  </div>
											
										</div>	
											
                                  													
                                   		</div> <!-- row -->
                                   		
                                   		                              
                                </form:form>                                      
                                 </div> 
                        	</div>
						</div> <!-- row -->
					</div>
				</div>   
        
        	<%@include file="footer.jsp"%>
        
            </div>

			
		

		</div>
	


	<!-- Mainly scripts -->
	<script src="resources/js/jquery-3.1.1.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="resources/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

	<script src="resources/js/plugins/dataTables/datatables.min.js"></script>

	<!-- Custom and plugin javascript -->
	<script src="resources/js/inspinia.js"></script>
	<script src="resources/js/plugins/pace/pace.min.js"></script>

 <!-- Password meter -->
    <script src="resources/js/plugins/pwstrength/pwstrength-bootstrap.min.js"></script>
    <script src="resources/js/plugins/pwstrength/zxcvbn.js"></script>
    
    <!-- Chosen -->
    <script src="resources/js/plugins/chosen/chosen.jquery.js"></script>
    
      <!-- select 2 -->
    <script src="resources/js/plugins/select2/select2.full.min.js"></script>
    
    <script>
    	$('.chosen-select').chosen({width: "100%"});
    
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
               
    
    $( "#tipoVehiculo" ).select2( {
		placeholder: "Seleccione Tipo Vehiculo",
		 allowClear: true
		
	} );
    
    </script>
	

	

</body>

</html>
