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
       <div class="panel-group">
  <div class="panel panel-default">
    <div class="panel-body">
    
    <div class="row">
			
			
			
			<div class="row" id="dgeocercat">
			 
			
			
			</div>
       
        </div> <!-- fin row -->
       
    
    </div>
  </div>
  
  
  <div class="panel panel-default">
  
  <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" href="#collapse1">Ver Detalles</a>
        </h4>
      </div>
      <div id="collapse1" class="panel-collapse collapse">
        <div class="panel-body">
    
    	<div class="row">
			
			
			
			<div class="row" id="dgeocerca">
			 
			
			
			</div>
       
        </div> <!-- fin row -->
    
    
    </div>
        
        
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
    
    
    <!-- Counter Up  
	<script
		src="resources/inspinia_v2.9/FullVersion/js/plugins/waypoints/lib/jquery.waypoints.js"></script>
	<script
		src="resources/inspinia_v2.9/FullVersion/js/plugins/counterup/jquery.counterup.min.js"></script> 
    
   -->
    <script>
    
              
 /*2.	Dashboard con ubicación de vehículos en faena.  */
 
 	var URLactual =window.location.protocol +"//"+ window.location.host;
 
 var dgeocerca = document.getElementById('dgeocerca');
 var dgeocercat = document.getElementById('dgeocercat');
 
 var valores = [];
 
 function getGeos() {
 
 $.ajax({
url: "geocercaultimagps.json",
async : false, 
type: "POST",
data: {
rutcliente : "${usuario.clienterut}"
},
//contentType:"application/json; charset=utf-8",
dataType: "json",
//contentType = 'application/json',
success: function(valor) {
		
		//se crea los elemento ddiv	
	   for (i in valor) {
		   
		   var geo = valor[i].rutGeocerca;
		   var nombregeo = valor[i].nombreGeocerca;
		   nombretable = "geotable" + geo
		   
		  //RESUMEN
		  //nuevos elementos totale
           
           var div = document.createElement('div');
		   
		   div.setAttribute("class",  "col-xs-12 col-md-6 col-lg-6 col-xl-4 mb-4"); 
		   
		   var divb = document.createElement('div');
		   divb.setAttribute("class",  "ibox");
		 
		   var divti = document.createElement('div');
		   divti.setAttribute("class",  "ibox-title");		  		 
		   divti.innerHTML= "<h4>"+nombregeo+"</h4>";
		   		   
		   var divc = document.createElement('div');
		   divc.setAttribute("class",  "ibox-content");		   
		   divc.setAttribute("id",  "geoTotal" + i);
		
		   divb.appendChild(divti);
		   divb.appendChild(divc); 
		   
		   div.appendChild(divb); 
           
		   dgeocercat.appendChild(div);		  
		  
		  //Detalles
		   
		   var table = crearTable(geo);		   
		   		   
		   var div = document.createElement('div');
		   //col-xs-12 col-md-6 col-lg-6 col-xl-4
		   div.setAttribute("class",  "col-xs-12 col-md-6 col-lg-6 col-xl-4 mb-4");
		   //div.setAttribute("id",  "geo");
		 		   
		   var divc = document.createElement('div');
		   divc.setAttribute("class",  "ibox-content");
		   //divc.setAttribute("id",  "geo");
		   //divc.setAttribute("id",  "geoTotal" + i);
		   divc.innerHTML= "<h2>"+nombregeo+"</h2>";
		   		   
		   var divt = document.createElement('div');
		   divt.setAttribute("class",  "table-responsive");
		   divt.setAttribute("id",  "geo" + i);
		   divt.setAttribute("style",  "height : 350px;");
		   
		   
 		   divt.appendChild(table);
		   
 		  divc.appendChild(divt);
 		   
 		   div.appendChild(divc); 
           
           dgeocerca.appendChild(div);
                        
           valores.push(valor);   
                                
		   
	   }//fin for
	   
	
	       
		   var j = 0
		
	   for (i in valor) {
		   
		   var geo = valor[i].rutGeocerca;
		   var nombregeo = valor[i].nombreGeocerca;
		   
		   nombretable = "geotable" + geo
		   //console.log(nombregeo +" "+ geo + " i: " + i)
		   //console.log(nombretable)
		  		  
		  window["geoT" + geo]  = $("#"+nombretable).DataTable({
				"language": {
		            "url": URLactual + "/sgomtweb/resources/datetableespanil.json"
		        },	
		        //async : true, 
		        "order": [[ 2, "desc" ]],
				"autoWidth": false,		
			
			}); // fin datatable */
		  // 
	   }//fin for 
	   
		  /* for (i in valor) {
			   
			   var ramdon = i+100;
			   
	   
		   setInterval( function () {
			   window["geo" + i] .clear();
				 //datatable.rows.add(newDataArray);  por si necesita agregar nueva columna
			
				  window["geo" + i] .ajax.reload();
			}, 40000 );
		   
		   }*/
			
	
	
}// fin success

	
});// fin ajax

 }

function crearTable(i) {
	  //var myTableDiv = document.getElementById("dgeocerca");

	  var table = document.createElement('TABLE');
	  table.setAttribute("id",  "geotable" + i);
    table.setAttribute("class",  "table table-striped table-bordered table-hover geo" + i);
	  
	  table.border = '0';
	  
	  var tableThead = document.createElement('thead');
	  table.appendChild(tableThead);
	  
	  var tr = document.createElement('TR');
	  
	  tableThead.appendChild(tr);
	  
	  var th = document.createElement('TH');
	    //th.width = '10';
	      		
	    th.appendChild(document.createTextNode("Patente"));
	      		    
	    tr.appendChild(th);
	      	
	    var th = document.createElement('TH');
	    //th.width = '50';
		      		
	    th.appendChild(document.createTextNode("Nr Interno"));
		      			
	    tr.appendChild(th);
		      	
		var th = document.createElement('TH');
		//th.width = '50';
			      		
		th.appendChild(document.createTextNode("Fecha"));
			      			
		tr.appendChild(th);

	  var tableBody = document.createElement('TBODY');
	  table.appendChild(tableBody);

	  for (var i = 2; i < 2; i++) {
	    var tr = document.createElement('TR');
	    tableBody.appendChild(tr);

	    for (var j = 0; j < 3; j++) {
	      var td = document.createElement('TD');
	      //td.width = '50';
	      		
	      		td.appendChild(document.createTextNode(""));
	      	
	      	//td.appendChild(document.createTextNode(""));
	      	tr.appendChild(td);
	      
	    }
	    
	  }
	  //myTableDiv.appendChild(table);
	  return table;
	}//fin funcion
	
	  
	//carga lista de patentes
	 var patentes = [];

	 function getPatentes() {
	
	 $.ajax({
		 url: "geocercaultimagpslistadoajax.json",    	 
			type: "POST",
			async : false,
	 	data: {
	 	rutcliente : "${usuario.clienterut}",
	 	geo : 107,
	 	nombregeo : nombregeo,
	 	},
	 	dataType: "json",
			success :  function(data){
				
				$.each(data, function(ind, obj){
					
					var patente =	obj.patente;				
					var nrointerno = obj.nroInterno;
					var fecha = obj.fechaS;
					var geocerca = obj.rutGeocerca;
					
					//console.log("dentro ajax patente "+ patente)
					
					patentes.push(data)
					
				});
									
				} // fin succes
			}); // ajas

	//llenar los datatable	
	
	console.log("valores "+valores.length)
	
	 for (var i = 0; i < valores.length; i++) {
	              
		 console.log("entro funcion");
	                
	                var geo = valores[i][i].rutGeocerca;
	     		   var nombregeo = valores[i][i].nombreGeocerca;
	     		   nombretable = "geotable" + geo
	     		   
	     		  window["total" + geo] = 0;
	     		   	     		   
	                console.log(nombretable)
	                console.log(patentes.length)
	                	              
	                 for (var j = 0; j < patentes.length; j++) {
	                	 
	                	 var patente  = patentes[j][j].patente;
	                	 var nroInterno  = patentes[j][j].nroInterno;
	                	 var rutGeocerca  = patentes[j][j].rutGeocerca;
	                	 var fechaS  = patentes[j][j].fechaS;
	                	 
	                	 //console.log(nombregeo)
	                	 //console.log(rutGeocerca)
	                	 	                	 
	                	 if(rutGeocerca === nombregeo){
	                		 
	                		 console.log(nombregeo)
	                		 
	                		  //window["geoT" + gep] .clear();
	                 	                
									 window["geoT" + geo].row.add([
										 patente,				
										 nroInterno,
										 fechaS,
										//rutGeocerca,
										
									]).draw();
									 
									 window["total" + geo]	  ++;
									 
									//var geoTotal =  window["geoTotal" + i]
									
									var geoTotal = "geoTotal" + i
									 
									
									
									 //	$("#"+geoTotal).append(parseInt(window["total" + geo].toFixed(2)));
									 	
									//Aqui se agrega los iconos vehiculos (hab/desh)
									//$("#"+geoTotal).append("<small style='color: green; font-size :15px;'>&nbsp; <i class='fa fa-truck'></i> </small>");
									
									
								
									 
									 
	                	 }// fin if			 
																
	                 }// fin for patentes	
	                 
	                 
	                 
	                 if( window["total" + geo] != 0){
	                 
	              	$("#"+geoTotal).append("<h2> Total: "+parseInt(window["total" + geo].toFixed(2))+ "</h2>");
	              	
	                 }
	                
	  }// fin for valores
	  
	 }//fin funcion
	 
	 getGeos()
	 getPatentes()
	 
	 setInterval( function () {
		 
		 dgeocerca.innerHTML = "";
		 dgeocercat.innerHTML = "";
		 
		 //var patentes = [];
		 valores = [];
		 getGeos()
		 patentes = [];
		 console.log("intervalo "+patentes.length)
		 getPatentes()
		 
		 
		 
	/* for (var i = 0; i < valores.length; i++) {
	              
		console.log("entro");
	                
	                var geo = valores[i][i].rutGeocerca;
	     		   var nombregeo = valores[i][i].nombreGeocerca;
	     		   nombretable = "geotable" + geo
	     		   
	     		  window["total" + geo] = 0;
	     		   	     		   
	                console.log(nombretable)
	                console.log(patentes.length)
	                	              
	                 for (var j = 0; j < patentes.length; j++) {
	                	 
	                	 var patente  = patentes[j][j].patente;
	                	 var nroInterno  = patentes[j][j].nroInterno;
	                	 var rutGeocerca  = patentes[j][j].rutGeocerca;
	                	 var fechaS  = patentes[j][j].fechaS;
	                	 
	                	 //console.log(nombregeo)
	                	 //console.log(rutGeocerca)
	                	 	                	 
	                	 if(rutGeocerca === nombregeo){
	                		 
	                		 console.log(nombregeo)
	                		 
	                		  window["geoT" + gep] .clear();
	                 	                
									 window["geoT" + geo].row.add([
										 patente,				
										 nroInterno,
										 fechaS,
										//rutGeocerca,
										
									]).draw();
									 
									 window["total" + geo]	  ++;
									 
									//var geoTotal =  window["geoTotal" + i]
									
									var geoTotal = "geoTotal" + i
									 
									
									
									 //	$("#"+geoTotal).append(parseInt(window["total" + geo].toFixed(2)));
									 	
									
									 $("#"+geoTotal).append("<small style='color: green; font-size :15px;'>&nbsp; <i class='fa fa-truck'></i> </small>");
									
									
								
									 
									 
	                	 }// fin if			 
																
	                 }// fin for patentes	
	                 
	                 
	                 
	                 if( window["total" + geo] != 0){
	                 
	              	$("#"+geoTotal).append("Total: "+parseInt(window["total" + geo].toFixed(2)));
	              	
	                 }
	                
	  }// fin for valores **/
		
		//tableMaquina.clear();
		//tableMaquina.ajax.reload();
	}, 50000 );   
	 
	  
	  //reaload patentes
	  
	  //reload datatables

	
 /*2.  finDashboard con ubicación de vehículos en faena.  */
           
        </script>
        
        


    
</body>

</html>