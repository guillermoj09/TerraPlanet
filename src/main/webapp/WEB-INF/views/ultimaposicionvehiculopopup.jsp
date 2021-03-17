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
 
 <style>
     
      
      #geo {
       height: 400px !important;  /* 500 */
        /*width: 200px !important; */
        font-family: Arial, sans-serif;
        background: grey;
        padding: 10px;
        margin: 10px;       
        opacity: 0.8;        
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
            <div class="row wrapper border-bottom white-bg page-heading">
                <div class="col-lg-10">
                    <h2></h2>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="">Inicio</a>
                        </li>
                        <li class="breadcrumb-item active">
                            <strong>Tabla Mapa</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>
        
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
										class="table table-striped table-bordered table-hover ultimosp" id="ultimosp">
										<thead>
											<tr>
																						
                                            <th>Patente</th>
                                            <th>Nr.Int</th>
                                            <th>Estado</th>
                                            <th>Fecha GPS</th>
                                            <th>Fecha DB</th>
                                            <th>GEO</th>
                                            
											</tr>
										</thead>
									 <tbody>
						                                
		                                	 <tr> 			                                    
		                                        <td></td>
		                                        <td></td>
		                                        <td></td>
		                                        <td></td>
		                                         <td></td>
		                                        <td id="trC"></td>
		                                        		                                      
		                                    </tr>		           	          
		                                               	                         
		                                </tbody>
		                                
									</table>
								</div>
					</div>
	</div>
      

</div>
       		
             
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
  
    <script>
    
              
/*	Tabla ultima posicion por json  */
 
 $.fn.dataTable.ext.errMode = 'throw';
	
	var URLactual =window.location.protocol +"//"+ window.location.host;
 
	var tableUltimos = $('#ultimosp').DataTable({
		"language": {
            "url": URLactual + "/sgomtweb/resources/datetableespanil.json"
        },	
        "order": [[ 2, "desc" ]],
		"autoWidth": false,		
		"ajax": {
			url: "ultimaposicionajax.json",    	 
			type: "POST",
	    	data: {
	    	rutcliente : "${usuario.clienterut}",
	    	categoria : "t" //t todo, c camion, m maquinas
	    	},
	    	dataType: "json",
			success :  function(data){
									
					$.each(data, function(ind, obj){
						
						tableUltimos.row.add([
							obj.patente,				
							obj.nroInterno,
							obj.descestado,
							obj.fechaS,
							obj.fechaSdb,
							obj.rutGeocerca,
						]).draw();
						console.log(ind)
						//tableUltimos.row(ind).attr("id", "equipos"+ind);
						tableUltimos.rows(ind).nodes().to$().attr("id", "equipos"+ind);
											
				//contadores aqui
					
				}); //fin for
				
				//dibujar en div aqui	
				
			} // fin ajax
		},
	}); 	
	
	//refrescar las tabla
	setInterval( function () {
		tableUltimos.clear();
		 //datatable.rows.add(newDataArray);  por si necesita agregar nueva columna		
		tableUltimos.ajax.reload();
	}, 40000 );
	
	//tableUltimos
	$("#ultimosp").find("tr").each(function(index){
		console.log("dentro tabla")
	    $(this).attr("id", "equipos"+(index));
	});
	
	
	
	
/*  Tabla ultima posicion por json   */
           
        </script>
        
        

</body>

</html>