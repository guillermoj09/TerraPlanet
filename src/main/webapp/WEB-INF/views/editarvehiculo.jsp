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

 <!-- Sweet Alert -->
    <link href="resources/inspinia_v2.9/FullVersion/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    
</head>

<body class="top-navigation">
	

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
                            	   <h5>Editar Equipos &nbsp;&nbsp; &nbsp;&nbsp; <small> Por favor rellenar todos los campos a actualizar... </small></h5>          
                        	</div>                        	
                        	
                        	<div class="ibox-content">
                               
                              <form:form action="editarvehiculo.html" commandName="VehiculoForm" id="FormVehiculo" name="FormVehiculo" method="POST">
                              
                               <div class="row" id="contenedorPass">
							    
                                      <div class="form-group col-lg-2">
                                        <label>*Patente</label> 
                                        <input type="hidden" name="origen" id="origen" value="">
                                        <input type="text" placeholder="" class="form-control" name="vehPatente"  id="vehPatente" maxlength="6"  value="${vehiculo.vehPatente}" tabindex="3" >
                                      </div> 
                                                                       
                                      <div class="form-group col-lg-2">
                                        <label>Año</label> 
                                        <input type="text" placeholder="" class="form-control" name="vehAnio" id="vehAnio" tabindex="6" maxlength="4" value="${vehiculo.vehAnio}" >
                                      </div>  
                                                              
                                      <div class="form-group col-lg-2">
                                        <label>Marca</label> 
                                        <input type="text" placeholder="" class="form-control" name="vehMarca" id="vehMarca" tabindex="6" value="${vehiculo.vehMarca}" >
                                      </div>
                                     
                                      <div class="form-group col-lg-2">
                                        <label>Modelo</label> 
                                        <input type="text" placeholder="" class="form-control" name="vehModelo" id="vehModelo" tabindex="6" value="${vehiculo.vehModelo}" >
                                      </div>
                                      
                                      <div class="form-group col-lg-2">
                                        <label>Motor</label> 
                                        <input type="text" placeholder="" class="form-control" name="vehMotor"  id="vehMotor"  tabindex="3" value="${vehiculo.vehMotor}" >
                                      </div> 
                                                                       
                                      <!-- <div class="form-group col-lg-2">
                                        <label>vehVin</label> 
                                        <input type="text" placeholder="101010101" class="form-control" name="vehVin" id="vehVin" tabindex="6" value="${vehiculo.vehVin}" disabled>
                                      </div>  -->   
                                      
                                       <div class="form-group col-lg-2">
                                        <label>Nro. Interno</label> 
                                        <input type="text" placeholder="" class="form-control" name="vehNumInterno" id="vehNumInterno" tabindex="6"  maxlength="30" value="${vehiculo.vehNumInterno}" >
                                      </div>  
                                      
                                       <div class="form-group col-lg-2">
                                        <label>Color</label> 
                                        <input type="text" placeholder="" class="form-control" name="vehColor" id="vehColor" tabindex="6" value="${vehiculo.vehColor}" >
                                      </div>  
                                      
                                      <c:if test="${usuario.perfilid == '6'}">                                                           
                                      <div class="form-group col-lg-2">
												<label>Usuarios </label>		
												<select  name="usuarios" id="usuarios"  multiple style="width:100%;" tabindex="4" >
													
													  <c:if test="${not  empty usuarios }">	  
													  
													  		<c:forEach items="${usuarios}" var="u" varStatus="count" >	  	
																																					
																<option value="${u.usuRut}" Selected>${u.usuLogin}</option>
																									
															</c:forEach>           
		            								</c:if>
		            								
		            								 <c:if test="${not  empty usuarios02 }">	  
													  
													  		<c:forEach items="${usuarios02}" var="u2" varStatus="count" >	  	
																																					
																<option value="${u2.usuRut}">${u2.usuLogin}</option>
																									
															</c:forEach>           
		            								</c:if>
																												
												</select>
												
											</div>
										</c:if>	                                                              
                                       <div class="form-group col-lg-2">
                                 
												<label>*Tipo Vehiculo </label>
												
												<select   data-placeholder="Seleccione Tipo Vehiculo"  name="tipoVehiculo" id="tipoVehiculo"  style="width:100%;" tabindex="4" class="form-control text-uppercase" >
																					
														<option value="${vehiculo.tipoVehiculo.tipvId}" selected> ${vehiculo.tipoVehiculo.tipvDescripcion} </option>	
														
														<c:if test="${not  empty tipovehiculos }">
																									
														<c:forEach items="${tipovehiculos}" var="t" varStatus="count" >	  
														
														   <c:if test="${not  empty t }">	
														   
														    <c:if test="${vehiculo.tipoVehiculo.tipvId ne t.tipvId}">
														 													
															<option value="${t.tipvId}">${t.tipvDescripcion}</option>
												
												 		  </c:if>
												 		  
												 		  </c:if>
												 		  	    
												      </c:forEach>	 
												      
												       </c:if>   																											
												</select>												
							 		 </div>   
							 		     
							 		     <c:if test="${usuario.perfilid == '6' || usuario.perfilid == '7'}">                                                                          
                                      <div class="form-group col-lg-2">
												<label>Asociar Tag RFid</label>		
												<select data-placeholder="Seleccione Tags..." name="tags" id="tags"  multiple style="width:100%;" tabindex="4" >
													
													  <c:if test="${not  empty tags }">	  
													  
													  		<c:forEach items="${tags}" var="t" varStatus="count" >	  	
																																					
																<option value="${t.tagId}" selected> ${t.tagId} </option>
																									
															</c:forEach>           
		            								</c:if>
		            								
		            								 <c:if test="${not  empty tags02 }">	  
													  
													  		<c:forEach items="${tags02}" var="t2" varStatus="count" >	  	
																																					
																<option value="${t2.tagId}" > ${t2.tagId} </option>
																									
															</c:forEach>           
		            								</c:if>
														
														
												</select>
												
										</div>  
										</c:if>
										   <c:if test="${usuario.perfilid == '8'}">      
										 <div class="form-group col-lg-2">
												<label>Asociar Tag RFid</label>		
												<select data-placeholder="Seleccione Tags..." name="tags2" id="tags2"  multiple style="width:100%;height: 100%;" tabindex="4" disabled="disabled">
													
													  <c:if test="${not  empty tags }">	  
													  
													  		<c:forEach items="${tags}" var="t" varStatus="count" >	  	
																																					
																<option value="${t.tagId}" selected> ${t.tagId} </option>
																									
															</c:forEach>           
		            								</c:if>
		            								
		            								 <c:if test="${not  empty tags02 }">	  
													  
													  		<c:forEach items="${tags02}" var="t2" varStatus="count" >	  	
																																					
																<option value="${t2.tagId}" > ${t2.tagId} </option>
																									
															</c:forEach>           
		            								</c:if>
														
														
												</select>
												
										</div>  
											</c:if>
										  
                                
                              		 <div class="form-group col-lg-2" id = "vehNivelEstanqueDiv">
                                        <label>Capacidad Estanque (Lts)</label> 
                                        <input type="number" placeholder="" min="0" step=".01" class="form-control" name="vehNivelEstanque" id="vehNivelEstanque" tabindex="6" value="${vehiculo.vehNivelEstanque}" >
                                      </div>
                                
                                	</div> <!-- row -->
                                	
                                	
                                	
                                 <div class="row" class="form-group col-lg-6" id="tipocamion">   
                                                                   	                    
                                      <div class="form-group col-lg-2">
                                        <label>Capacidad de Carga (ton)</label>
                                        <input type="number" placeholder="" min="0" step=".01" class="form-control" name="vehCargaSoportada" id="vehCargaSoportada" tabindex="6" value="${vehiculo.vehCargaSoportada}" >
                                      </div>   
                                                              
                                      <div class="form-group col-lg-2">
                                        <label>Volumen tolva (m3)</label> 
                                        <input type="number" placeholder="" min="0" step=".01" class="form-control" name="vehCargaVolumen" id="vehCargaVolumen" tabindex="6" value="${vehiculo.vehCargaVolumen}" >
                                      </div>
                                      
                                       <div class="form-group col-lg-2">
                                        <label>Peso Bruto (ton)</label> 
                                        <input type="number" placeholder="" min="0" step=".01" class="form-control" name="vehPesoBruto" id="vehPesoBruto" tabindex="6" value="${vehiculo.vehPesoBruto}" >
                                      </div>
                                                                                                                   
                                      
                                 </div>
                                 
                                   <div class="row" class="form-group col-lg-6" id="tipomaquina">      
                                                       
                                      <div class="form-group col-lg-2">
                                        <label>Volumen del Balde (m3)</label>
                                        <input type="number" placeholder="" min="0" step=".01" class="form-control" name="vehCapacidadBalde" id="vehCapacidadBalde" tabindex="6" value="${vehiculo.vehCapacidadBalde}" >
                                      </div>   
                                                              
                                      <div class="form-group col-lg-2">
                                        <label>Peso Operacional (ton)</label> 
                                        <input type="number" placeholder="" min="0" step=".01" class="form-control" name="vehPesoOperacional" id="vehPesoOperacional" tabindex="6" value="${vehiculo.vehPesoOperacional}" >
                                      </div>  
                                      
                                      
                                      <div class="form-group col-lg-2">
                                        <label>Capacidad Balde (ton)</label> 
                                        <input type="number" placeholder="" min="0" step=".01" class="form-control" name="vehCapacidadBaldeTon" id="vehCapacidadBaldeTon" tabindex="6" value="${vehiculo.vehCapacidadBaldeTon}" >
                                      </div>                                    
                                 </div>
                                
                                      <div class="hr-line-dashed"></div> 
                                
										
										<div class="row">
				                                <div class="col-md-10 col-sm-10 col-lg-10 b-r">                                                                                                              
				                                <button class="btn btn-default " type="button" id="volver" ><strong><span class="fa fa-caret-left"></span><a href="consultarvehiculo.html"> Volver </a></strong></button>&nbsp;&nbsp;&nbsp;&nbsp;
				                                <!-- <button class="btn btn-default " type="button" id="limpiar" ><strong><span class="fa fa-eraser"></span> Reset</strong></button>&nbsp;&nbsp;&nbsp;&nbsp;  -->
				                                </div>
                							  <div class="col-md-2 col-sm-2 col-lg-2 text-center">                                                                                                              
                                        			<button class="btn btn-primary " type="button" id="btn_guardar" name="btn_guardar" >Guardar</button>
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
  
     <!-- Sweet alert -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/sweetalert/sweetalert.min.js"></script>
    
    <script>
    	
    
		$(document).ready(function(){			
			
			validaTipoVehiculo();
			
			
			$(document).off('click','#btn_guardar').on('click','#btn_guardar', function(event){			

					if($("#vehPatente").val() == "")
						{swal("", "Debe ingresar una Patente.", "warning"); $("#vehPatente").focus();
		    	    	return  false;						
					}if($("#tipoVehiculo").val() == "")
						{swal("", "Debe seleccionar un tipo de Vehiculo.", "warning"); $("#tipoVehiculo").focus();
		    	    	return  false;						
					}
					else{
						$("#FormVehiculo").submit();
						}				
                   
	           });
			
			
			if($("#vehPatente").val()=="")
				{
					//$("#vehPatente").prop('disabled', false);
					$("#vehPatente").attr('readonly', false);
					$("#usuarios").prop('disabled', true);
					$("#vehPatente").focus();
					$("#origen").val("crear");
				}
			else
				{
					//$("#vehPatente").prop('disabled', true);
					$("#vehPatente").attr('readonly', true);
					$("#usuarios").prop('disabled', false);
					$("#origen").val("editar");
				}
			
			
			
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
 
    
    $( "#tipoVehiculo" ).select2( {
		placeholder: "Seleccione Tipo Vehiculo",
		 allowClear: true,
		 
		
	} ); 
    
    var maximumSelection = 5;
    
    $( "#usuarios" ).select2( {
		placeholder: "Seleccione Usuarios",
		 //allowClear: true
		
	} ); 
    
    $( "#tags" ).select2( {
		placeholder: "Seleccione Tags",
		 //allowClear: true
		maximumSelectionLength: maximumSelection,		
		
	} ); 
    
    $( "#tags2" ).select2( {
		placeholder: "Seleccione Tags",
		 //allowClear: true
		maximumSelectionLength: maximumSelection,		
		
	} ); 
    
    </script>
    
     <script>
     
     function validaTipoVehiculo(){
          
     //verificamos por defecto que solo sea camion vea los tag
     	//camion  6 y 30  // se modifica tambien en ultima posicion ajax los colores de los camiones
    	 if($("#tipoVehiculo").val()== 6 || $("#tipoVehiculo").val()== 30){
    				 
             $('#tags').prop('disabled', false); $('#tags').select2( {placeholder: "Seleccione Tags",maximumSelectionLength: maximumSelection,} );
             //mostrar div tipo camion
             $('#tipocamion').show(); 
             $('#tipomaquina').hide();
             $('#vehNivelEstanqueDiv').show();
             
    	 }
         //maquina 11, 19, 22, 25 , 26 27     
         else if($("#tipoVehiculo").val()== 11 || $("#tipoVehiculo").val()== 19 || $("#tipoVehiculo").val()== 22 || $("#tipoVehiculo").val()== 25 || $("#tipoVehiculo").val()== 26 || $("#tipoVehiculo").val()== 27){
        	$('#tags').prop('disabled', true); $('#tags').select2();
        	$('#tipomaquina').show(); 
        	$('#tipocamion').hide();
        	  $('#vehNivelEstanqueDiv').show();
        	
        	          
    	 }else{
    	//cualquier otro vehiculo			 
    		$('#tags').prop('disabled', true); $('#tags').select2();
    		//ocultat div tipo camion
    		$('#tipocamion').hide();
    		$('#tipomaquina').hide();
    		$('#vehNivelEstanqueDiv').hide();
    				 
    	  }
     
     }
             
     //se deshabilita los tag, si se cambia de tipo vehiculo y tiene tags, se van a borrar
     var i = 0;
     $(document).off("change","#tipoVehiculo").on("change","#tipoVehiculo", function()
             { 
    	 			validaTipoVehiculo();
             }
     
     
     );

     
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
 	
 	// para el que escribio lo de arriba... si funciona ;)
 	
     
     
     </script>
    
</body>

</html>
