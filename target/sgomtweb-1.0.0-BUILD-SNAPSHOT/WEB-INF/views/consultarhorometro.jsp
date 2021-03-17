<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="cl.samtech.sgomt.object.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>


<!DOCTYPE html>
<html>
<head>
<!-- <meta http-equiv="refresh" content="60" />  -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SAMTECH | SGOMT</title>

<%@include file="style.jsp"%>

<link href="resources/inspinia_v2.9/FullVersion/css/plugins/jQueryUI/jquery-ui.css" rel="stylesheet">
 <style>
      /*para el dialog */
      .ui-dialog {
     background-color: rgba(255,255,255,0.9);
}
/* .no-close .ui-dialog-titlebar-close {display: none } */
 
 
 <style>
      
      #geo {
       height: 400px !important;  /* 500 */
        /*width: 200px !important; */
        font-family: Arial, sans-serif;
        background: grey;
        padding: 10px;
        margin: 10px;       
        opacity: 0.6;        
      }
      
      .panel-body{
 	 background-color: #F5F5F4;
	}
      
    </style>
    
   
 
 
  
</head>
<body class="top-navigation">

	<div id="wrapper">
	
	
	<%@include file="menu.jsp"%>
	
		 <div id="page-wrapper" class="gray-bg">
		 
		 
       <div class="row border-bottom">
       </div>
                          
              <!-- Titulo del Menu  -->
            	<%@include file="barramenu.jsp"%>
        
   <!-- Contenido principal  -->
        <div class="wrapper wrapper-content animated fadeInRight">
        
        <div id="mensajejs"></div><div id="mensajejs2"></div>
                            
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
           			
           			  <c:choose>
	    <c:when test="${mc.collapseshow == 'SI'}">
	    
      		<c:set var = "border" scope = "page" value = ""/>
      		<c:set var = "iboxContent" scope = "page" value = ""/>
      		<c:set var = "link" scope = "page" value = "fa-chevron-up"/>
      		<c:set var = "panelR" scope = "page" value = " "/>
      		<c:set var = "panelF" scope = "page" value = " in show "/>
	    </c:when>    
	    <c:otherwise>
      		<c:set var = "border" scope = "page" value = "border-bottom"/>
      		<c:set var = "iboxContent" scope = "page" value = "display: none;"/>
      		<c:set var = "link" scope = "page" value = "fa-chevron-down"/>
      		<c:set var = "panelR" scope = "page" value = " in show "/>
      		<c:set var = "panelF" scope = "page" value = " "/>
	    </c:otherwise>
		</c:choose>
        
      <div class="row">
      
       <div class="col-xs-12 col-md-12 col-lg-12 col-xl-12"> 
       <div class="ibox-content " style="">
                        	 <div class="sk-spinner sk-spinner-three-bounce">                                
                                 <div class="sk-bounce1"></div>
                                    <div class="sk-bounce2"></div>
                                    <div class="sk-bounce3"></div>                                                                    
                    			</div>
                    			
								<div class="table-responsive">
									<table
										class="table table-striped table-bordered table-hover tablahorometro" id="tablahorometro">
										<thead>
											<tr>
																						
                                            <th>Patente</th>
                                            <th>Horometro Actual</th>                                        
                                            <th>Tipo</th>
                                            <th>Fecha Ultima</th>                                           
                                            <th>Estado</th>
                                          
                                            <th>Usuario Cambia</th>
                                            <th>Fecha Cambia</th>
                                            <th>Horometro Anterior</th> <!-- actual -->
                                          <!--   <th>Tipo D</th> -->
                                            
                                            <th>Horo Nuevo</th>
                                            <th>Fecha Ultima T</th>
                                            <th>Cod Estado</th>
                                             <th>Tipo Id</th>
                                            <!-- <th>Fecha Cambia</th>
                                            <th>Hora Actual</th>
                                            <th>Hora Cambia</th>
                                            <th>Rut Usuario</th>
                                            <th>Estado</th>  -->
                                            
											</tr>
										</thead>
									 <tbody>
						                                
		                                	 <tr> 			                                    
		                                        <td></td>
		                                        <td></td>
		                                        <td></td>
		                                        <td></td>
		                                         <td></td>
		                                         <td></td>
		                                         <td></td>
		                                         <td></td>
		                                         <td></td>
		                                         <td></td>
		                                         <td></td>
		                                         <td></td>
		                                                                       
		                                    </tr>		           	          
		                                               	                         
		                                </tbody>
		                                
									</table>
								</div>
					</div>
	</div>
      

</div>

 <div id="legend">
 
 <div class="row">
				
				 
				
					<div class="col-lg-12">
						<div class="ibox">
							<div class="ibox-title">
                            	   <h5> <div id="titulo"></div> &nbsp; <small> Por favor rellenar todos los campos a actualizar ... </small></h5>          
                        	</div>                        	
                        	
                        	<div class="ibox-content">
                               
                              <form:form action="modificarhorometro" commandName="horometroForm">
                              
                               <div class="row" id="contenedorPass">
							   
							     <div class="form-group col-lg-12">
                                      
                                        <label>Horometro</label> 
                                        <input type="number" placeholder="" class="form-control" name="horometro"  id="horometro"  tabindex="3" value="" >
                                        <input type="hidden"  class="form-control" name="gps"  id="gps" value="">
                                        <input type="hidden"  class="form-control" name="tipo"  id="tipo" value="">
                                        <input type="hidden"  class="form-control" name="patente"  id="patente" value="">
                                       
                                </div>
                                
                            
                                </div> <!-- row -->
                                      <div class="hr-line-dashed"></div> 
                                
										
					   <div class="row">                            
                                <div class="col-lg-12 col-md-10 col-sm-10 b-r">
                                </div>
                                <div class="col-lg-6 col-md-2 col-sm-2 text-center">                                                                                                              
                                    	<button class="btn btn-primary " type="button" onClick="validar()" >Guardar</button>
                                </div>
                                
                							  <div class="col-lg-6 col-md-2 col-sm-2 text-center">                                                                                                              
                                     	<button class="btn btn-primary " type="button" onClick="cerrar()" >Cerrar</button>
                                </div>
                                                                       
                          </div>                
                                		
                                </form:form>                                      
                                 </div> 
                        	</div>
						</div> <!-- row -->
					</div>
                                                           
</div> <!-- fin legend -->
       		
             
     </div>
  <%@include file="footer.jsp"%>
        
    
    </div>
    
    </div>
    
    <%@include file="footerjs.jsp"%>
        
    
    <!-- d3 and c3 charts -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/d3/d3.min.js"></script>
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/c3/c3.min.js"></script>
    	
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/dualListbox/jquery.bootstrap-duallistbox.js"></script>
    
      
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/dataTables/datatables.min.js"></script>
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/dataTables/dataTables.bootstrap4.min.js"></script>
    
      <script src="resources/inspinia_v2.9/FullVersion/js/plugins/jquery-ui/jquery-ui.min.js"></script>    
       <script src="resources/inspinia_v2.9/FullVersion/js/popper.min.js"></script>
  
    <script>
    
              
/*	Tabla ultima posicion por json  */
 
	//oculto div para dialog 
 	var le = document.getElementById('legend');         	
    le.style.display = 'none';			
 
 $.fn.dataTable.ext.errMode = 'throw';
	
	var URLactual =window.location.protocol +"//"+ window.location.host;
 
	var tableHoro = $('#tablahorometro').DataTable({
		"language": {
            "url": URLactual + "/sgomtweb/resources/datetableespanil.json"
        },	
        "order": [[ 10, "asc" ], [ 9, "desc" ]],
      // "order": [[ 10, "desc" ]],
       //"order": false,
       //"ordering": false,
          "columnDefs": [
                 {
                     "targets": [ 9, 10 ,11],
                     "visible": false,
                     "searchable": false
                 },
                 ],
		"autoWidth": false,		
		"ajax": {
			url: "consultarhorometroajax.json",    	 
			type: "POST",
	    	data: {
	    	rutcliente : "${usuario.clienterut}",
	    	categoria : "t" //t todo, c camion, m maquinas
	    	},
	    	dataType: "json",
			success :  function(data){
									
					$.each(data, function(ind, obj){
						//console.log(obj)
						tableHoro.row.add([
							obj.patente,	//0				
							obj.horometro,  //1
							obj.tipoD,       //2							
							obj.fechaUltimaS, //3
							obj.estadoD,     //4						 
						    obj.usuarioCambia,//5
						    obj.fechaCambiaS,//6
						    obj.horoActual,//7
						   // obj.tipoD,//8						    						   
						    obj.horoCambia,//8
						    obj.fechaUltima,//9
						    obj.estado,  //10
						    obj.tipo,  //11
							//obj.horaActual,
							//obj.horaCambia,
							//obj.usuarioCambia,
							//obj.estado,
						]).draw();
						console.log(ind)
						//tableUltimos.row(ind).attr("id", "equipos"+ind);
						tableHoro.rows(ind).nodes().to$().attr("id", obj.gps);
											
				//contadores aqui
					
				}); //fin for
				
					
				
			} //success
		}, //fin ajax
	}); 	
	
	//refrescar las tabla
	setInterval( function () {
		tableHoro.clear();
		 //datatable.rows.add(newDataArray);  por si necesita agregar nueva columna		
		tableHoro.ajax.reload( null, false);
	}, 40000 );
	
	
	var table = $('#tablahorometro').DataTable();
	
	$('#tablahorometro').on( 'click', 'tbody tr', function () {
		/*tableHoro.row( this ).edit( {
	        buttons: [
	            { label: 'Cancel', fn: function () { this.close(); } },
	            'Edit'
	        ]
	    } );*/
	    
	    var id = $(this).attr('id'); //trae el id de la tabla
	    	  
	    //var id = table.row( this ).id();
	    var tr = $(this).closest('tr');
	    var row = table.row(tr);
        var rowData = row.data();//trae todo los datos 
        
	    //alert( 'Clicked row id '+id+' '+tr + row + rowData[1] );
        
	    var titulo = document.getElementById("titulo");
	    titulo.innerHTML  = rowData[0]
	    
	    var horometroimput = document.getElementById("horometro");	    
	    horometroimput.value = rowData[1]
	    
	    var tipoimput = document.getElementById("tipo");	    
	   // tipoimput.value = rowData[2]
	    tipoimput.value = rowData[11]
	    
	    var patenteimput = document.getElementById("patente");	    
	    patenteimput.value = rowData[0]
	    
	    var horometrogps = document.getElementById("gps");	    
	    horometrogps.value = id;
	    
	    var estado = rowData[5];
	    
	if(estado != 0){
		 $( "#legend" ).dialog({
 	  		open: function(event, ui) {
 	  		
 			//$(this).css({'max-height': 500, 'overflow-y': 'auto'});
 			$(this).parent().children().children('.ui-dialog-titlebar-close').hide();
 			$(this).parent().children().children('.ui-dialog-title').css({'color': '#676a6c', 'font-family': 'sans-serif'});
 			  
				},
				 title: 'Actualizar Horometro',
				//autoOpen:false,
				//modal: true,
				resizable: true,
				draggable: true,
				width: '490',
				height: '390',
				closeOnEscape: true,
				//position: 'top' 
				//position: { my: "buttom", at: "buttom", of:  $('#map') },          
		 	});
		 
	}else {
		
		alert("No se puede Realizar Operacion");
		
	}
	    
	} );
	
	
	function cerrar(){
		
		$('#legend').dialog('close');
		
	}
	
	function validar(){
		
	    var horometroimput = document.getElementById("horometro").value;	    	    
	    var horometrogps = document.getElementById("gps").value;
	    var horotipo = document.getElementById("tipo").value;
	    var horopatente = document.getElementById("patente").value;
	    console.log(horometroimput)
	    $.ajax({
			url: "modificarhorometro.json",    	 
			type: "POST",
	    	data: {
	    	horometro : horometroimput,
	    	gps : horometrogps,
	    	tipo : horotipo,
	    	patente : horopatente,
	    	},
	    	dataType: "json",
			success :  function(data){
						
				alert(data.response);
				$('#legend').dialog('close');
				tableHoro.clear();				 		
				tableHoro.ajax.reload();
					
				
			} //success
		}) //fin ajax
	    
	    
		
	}
	
/*  Tabla ultima posicion por json   */
           
        </script>
        
        

</body>

</html>