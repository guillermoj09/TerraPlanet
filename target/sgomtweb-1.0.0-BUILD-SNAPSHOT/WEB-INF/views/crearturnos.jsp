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
  <link href="resources/inspinia_v2.9/FullVersion/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

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
                            	   <h5>Creacion de Turnos &nbsp;&nbsp; &nbsp;&nbsp; <small> Por favor rellenar todos los campos ... </small></h5>          
                        	</div>                        	
                        	
                        	<div class="ibox-content">
                               
                              <form:form action="crearturnos" commandName="crearTurnosForm" id = "crearTurnosForm">
                              
                               <div class="row" id="contenedorPass">
                               
                                					<div class="col-lg-2">
                                                        <div class="form-group">
                                                            <label class="control-label">&nbsp; Fecha Desde</label>&nbsp;&nbsp; &nbsp;&nbsp; 
                                                            <div class="input-group date">
                                                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" class="form-control" name="fechaDesde" id="fechaDesde"  value="${rform.fechaDesde}" required>                            
                                                            </div>                                                                                                   
                                                        </div> 
                                                    </div>
                                                    <div class="col-lg-2">
                                                        <div class="form-group">
                                                            <label class="control-label">&nbsp; Hora Desde</label>&nbsp;&nbsp; &nbsp;&nbsp; 
                                                            <div class="input-group clockpicker" data-autoclose="true">
                                                                 <input type="text" class="form-control" name="horaDesde" id="horaDesde"  value="${rform.horaDesde}" >
                                                                <span class="input-group-addon"><span class="fa fa-clock-o"></span></span>
                                                            </div>                                     
                                                        </div> 
                                                    </div>                                                    
                                                    <div class="col-lg-2">
                                                        <div class="form-group">
                                                            <label class="control-label">&nbsp; Fecha Hasta</label>&nbsp;&nbsp; &nbsp;&nbsp; 
                                                            <div class="input-group date">
                                                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" class="form-control" name="fechaHasta" id="fechaHasta" value="${rform.fechaHasta}" required>
                                                                
                                                            </div>                                      
                                                        </div> 
                                                    </div>                                                     
                                                    <div class="col-lg-2 ">
                                                        <div class="form-group">
                                                            <label class="control-label">&nbsp; Hora Hasta</label>&nbsp;&nbsp; &nbsp;&nbsp; 
                                                            <div class="input-group clockpicker" data-autoclose="true">
                                                                <input type="text" class="form-control" name="horaHasta" id="horaHasta" value="${rform.horaHasta}" >
                                                                <span class="input-group-addon"><span class="fa fa-clock-o"></span></span>
                                                            </div>                                       
                                                        </div> 
                                                    </div>
                                                    
                                                 <div class="col-lg-2 ">
	                                                    <div class="form-group">
                                                             <label>Turnos </label>
                                                             <div>
                                                             
                                                              <!-- <select  name="turnos" id="turnos"  style="width:100%;" tabindex="4" data-placeholder="Seleccione un Turno" class="form-control text-uppercase">  -->
                                                              <select  name="turnoname" id="turnoname"  style="width:100%;" tabindex="4" data-placeholder="Seleccione un Turno" class="form-control text-uppercase">	
																<option value="0">Todos</option>
																<c:forEach items="${listturno}" var="p" varStatus="count" >				
																   <c:if test="${not  empty p }">	
																   <option value="${p.nombre}" <c:if test="${p.nombre == rform.turnoname}"> selected</c:if>>${p.nombre}</option>			
																					
														 		  </c:if>
															      </c:forEach>
															</select>
                                                             </div>
	                                                      </div>
                                                 	 </div>
                                                
                                                
                                                <div class="form-group col-lg-2">
                                 
												<label>Tipo Turno</label>
												
												<select  name="turTipo" id="turTipo" data-placeholder="Seleccione Tipo Turnos" style="width:100%;" tabindex="7" class="form-control text-uppercase" required>
														 													
															<option value="0">1 dia</option>
															<option value="1">2 dias</option>
																																							
												</select>												
							  					</div>   
							  					
							  					
							  		 <div class="col-lg-2 ">
                                                        <div class="form-group">
                                                            <label class="control-label">&nbsp; Hora Inicial Dia 1</label>&nbsp;&nbsp; &nbsp;&nbsp; 
                                                            <div class="input-group clockpickersec" data-autoclose="true">
                                                                <input type="text" class="form-control" name="horaini1" id="horaini1" value="${rform.horaini1}" >
                                                                <span class="input-group-addon"><span class="fa fa-clock-o"></span></span>
                                                            </div>                                       
                                                        </div> 
                                                    </div>
                                                    
                                                    
                                       <div class="col-lg-2 ">
                                                        <div class="form-group">
                                                            <label class="control-label">&nbsp; Hora Final Dia 1</label>&nbsp;&nbsp; &nbsp;&nbsp; 
                                                            <div class="input-group clockpickersec" data-autoclose="true">
                                                                <input type="text" class="form-control" name="horafin1" id="horafin1" value="${rform.horafin1}" >
                                                                <span class="input-group-addon"><span class="fa fa-clock-o"></span></span>
                                                            </div>                                       
                                                        </div> 
                                                    </div>
                                                    
                                                    
                                       <div class="col-lg-2 b-r">
                                                        <div class="form-group">
                                                            <label class="control-label">&nbsp; Hora Inicial Dia 2</label>&nbsp;&nbsp; &nbsp;&nbsp; 
                                                            <div class="input-group clockpickersec" data-autoclose="true">
                                                                <input type="text" class="form-control" name="horaini2" id="horaini2" value="${rform.horaini2}" >
                                                                <span class="input-group-addon"><span class="fa fa-clock-o"></span></span>
                                                            </div>                                       
                                                        </div> 
                                                    </div>
                                                    
                                                    
                                       <div class="col-lg-2 b-r">
                                                        <div class="form-group">
                                                            <label class="control-label">&nbsp; Hora Final Dia 2</label>&nbsp;&nbsp; &nbsp;&nbsp; 
                                                            <div class="input-group clockpickersec" data-autoclose="true">
                                                                <input type="text" class="form-control" name="horafin2" id="horafin2" value="${rform.horafin2}" >
                                                                <span class="input-group-addon"><span class="fa fa-clock-o"></span></span>
                                                            </div>                                       
                                                        </div> 
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
							  		
							  									  		                        
                            </div> <!-- row -->
                            
                                
                                      <div class="hr-line-dashed"></div> 
                                      
                          <div class="row">                            
                                <div class="col-lg-10 col-md-10 col-sm-10 b-r">   
                                <button class="btn btn-default " type="button" id="volver" ><strong><span class="fa fa-caret-left"></span><a href="consultarturnos"> Volver </a></strong></button>&nbsp;&nbsp;&nbsp;&nbsp;                                                                                                                                          
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
	    
       <!-- Date range use moment.js same as full calendar plugin -->
	    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/fullcalendar/moment.min.js"></script>
	         
    <!-- Date range picker -->
	<script src="resources/inspinia_v2.9/FullVersion/js/plugins/daterangepicker/daterangepicker.js"></script>
   <!-- Data picker -->
   <script src="resources/inspinia_v2.9/FullVersion/js/plugins/datapicker/bootstrap-datepicker.js"></script>
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/dataTables/datatables.min.js"></script>
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/dataTables/dataTables.bootstrap4.min.js"></script>
         <!-- hora -->
      <script src="resources/inspinia_v2.9/FullVersion/js/plugins/clockpicker/clockpicker.js"></script>
      
    
    <!-- select 2 -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/select2/select2.full.min.js"></script>
    
      <!-- Sweet alert -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/sweetalert/sweetalert.min.js"></script>
    
    <%@include file="utiljs/valclockpickerjs.jsp"%>
   
	
	<script>
	
	
	
	  $('.input-group.date').datepicker({
    	  language: 'es',
    	  todayBtn: "linked",
          keyboardNavigation: false,
          forceParse: false,
          calendarWeeks: true,
          autoclose: true,
          format: "dd/mm/yyyy"
      });
              

        $('.clockpicker').clockpicker();
        
        $('.clockpickersec').clockpicker(
        		
        );
        
        // For adding seconds (00)
	    /*$('.clockpickersec').on('change', function() {
	        let receivedVal = $(this).val();
	        $(this).val(receivedVal + ":00");
	    });*/

    
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
    
   
    
    </script>
	
	<div class="lg-12"><div class="lg-3"></div><div class="lg-3"></div><div class="lg-3"></div></div>

</body>

</html>
