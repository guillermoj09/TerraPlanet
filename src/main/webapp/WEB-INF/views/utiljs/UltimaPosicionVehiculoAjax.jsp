
<script>
	//esta variables tienen que ir donde se incluya el jsp, desde el principio ya que las usa el jsp BotonTablaUltimoGPS
	//var myWindow = null;
	//var doc = null;
	//ultima x patente

	//var markers = [];

	var InforObj = [];

	//var icon = 'http://maps.google.com/mapfiles/ms/icons/red-dot.png';

	function getCoords() {
		
		$.ajax({
			url : "ultimaposicionajax.json",
			type : "POST",
			data : {
				rutcliente : "${usuario.clienterut}",
				rut : "${usuario.rut}"
			},
			//contentType:"application/json; charset=utf-8",
			dataType : "json",
			//contentType = 'application/json',
			success : function(valor) {

				//var infoWindowContent = [];
				infoWindowContent = [];
				//console.log(valor[1])
				DeleteMarkers();
				//lleno contenido para usar en los maker
				for (var i = 0; i < valor.length; i++) {

					infoWindowContent[i] = '<div id="content">'
							+ '<div id="siteNotice">'
							+ '</div>'
							+ '<h1 id="firstHeading" class="firstHeading">'
							+ valor[i].patente
							+ '</h1>'
							+ '<div id="bodyContent">'
							+ '<p><b>Evento</b>:  <font size="+1.5"><i class=\''+valor[i].classEvent+'\'></i> </font> '
							+ valor[i].evento
							+ '</p>'
							+ '<p><b>Tipo Vehiculo</b>: '
							+ valor[i].tipoVehiculo
							+ '</p>'
							+ '<p><b>Marca</b>:   '
							+ valor[i].marca
							+ '</p>'
							+ '<p><b>Fecha</b>:  '
							+ valor[i].fechaS
							+ '</p>'
							+ '<p><b>Numero Interno</b>:  '
							+ valor[i].nroInterno
							+ '</p>'
							+ '<p><b>Velocidad</b>:   '
							+ valor[i].velocidad
							+ ' Kmh</p>'
							+ '<p><b>Geocerca</b>:  '
							+ valor[i].rutGeocerca
							+ '</p>'
							+ '</div>'
							+ '</div>';

				}//fin for
				i = 0;

				//(div legent) se carga el elemento legent, luego se crea el contenido
				//var legend = document.getElementById('legend');
				var contenidot = document.getElementById('contenidot');
				//console.log(legend);
				var div2 = document.createElement('table');
				div2.setAttribute("id", "contenido");
				contenido = document.getElementById('contenido');

				
				// fin crear tabla	primera parte
				var contenidotabla="";
				for (i in valor) {
					
					contenidotabla += '<tr id="equipos'+i+'" >';
					contenidotabla += ' <td>' +valor[i].patente +'</td>';
					contenidotabla += ' <td>' +valor[i].fechaS +'</td>';
					//contenidotabla += ' <td>' +valor[i].rutGeocerca +'</td>';
					contenidotabla += ' <td>' +valor[i].nroInterno +'</td>';
					contenidotabla += ' </tr>';
					//console.log(contenidotabla);
					icon = 'http://maps.google.com/mapfiles/ms/icons/'+ valor[i].icon;

					var myLatlng = new google.maps.LatLng(valor[i].lat,
							valor[i].lon);

					window["patente" + "i"] = valor[i].patente;
					window["evento" + "i"] = valor[i].evento;
					window["marca" + "i"] = valor[i].marca;
					window["fecha" + "i"] = valor[i].fecha;
					window["nroInterno" + "i"] = +valor[i].nroInterno;
					
					
					var colorI = "";
					//console.log(colorI);

					var sb = new StringBuilder();

					if (valor[i].velocidad == 0) {
						colorI = "gray";
						sb.append(patentei);
						var myString = sb.toString();
						var aux = 40;

					} else {
						colorI = "green";
						sb.append(patentei);
						sb.append(" a ");
						sb.append(valor[i].velocidad);
						sb.append(" kmh");
						var myString = sb.toString();
						var aux = 58;

					}
					if(valor[i].tipoVehiculo == "Dumper"){
						valor[i].tipoVehiculo = "Camion";
						
					}
					
					if(valor[i].tipoVehiculo != "Camion" && valor[i].tipoVehiculo != "Cargador Frontal" && valor[i].tipoVehiculo != "Excavadora" ){
						valor[i].tipoVehiculo = "Camion";						
					}
					
					
				      marker = new MarkerWithLabel({
				     	 position: myLatlng,
				         map: map,
				         labelContent: "<div class='arrow' style='border-top-color:"+colorI+"'></div><div class='inner' style='background-color:"+colorI+"'><img src='resources/img/iconos/"+valor[i].tipoVehiculo+".png' with='17px' height='14px'> "+myString+ "</div>",
				         labelAnchor: new google.maps.Point(aux, 33),
				         labelClass: "labels", // the CSS class for the label
				         icon :{
								url:''
							},
				         labelStyle: {opacity: 0.75}
				       });
					//abrimos infoWindos desde los maker
					google.maps.event.addListener(marker,'click',(function(marker, i) {
						return function() {
							infoWindow.setContent(infoWindowContent[i]);
							infoWindow.open(map, marker);
							
						}
					})
					(marker, i));

					markers.push(marker);
					
					
					
					
					
					
					//se crea los elementos que se van a agregar al div legent (dialog)
					var patente = valor[i].patente;
					var fecha = valor[i].fechaS;
					var geo = valor[i].rutGeocerca;
					var nro = valor[i].nroInterno;
					//viejo              
					var div = document.createElement('div');
					div.setAttribute("id", "equipos" + i);

					div.innerHTML = ' <b>' + nro + '</b> ' + patente
							+ ' ' + fecha + ' ' + geo;
					div2.appendChild(div);
					//viejo

					idnumero = "equipos" + i;

					var equipos = document.getElementById(idnumero);

					//creo contenido tabla //nuevo         	
					var trt = document.createElement('TR');
					trt.setAttribute("id", "equipos" + i);
					

					var td = document.createElement('TD');
					td.appendChild(document.createTextNode(patente));

					var td2 = document.createElement('TD');
					td2.appendChild(document.createTextNode(nro));

					var td3 = document.createElement('TD');
					td3.appendChild(document.createTextNode(fecha));

					var td4 = document.createElement('TD');
					td4.appendChild(document.createTextNode(geo));

					trt.appendChild(td);
					trt.appendChild(td2);
					trt.appendChild(td3);
					trt.appendChild(td4);
					
					//fin tabla dialog 
					//console.log(trt);
					
				}// fin for
				
				var sb2 = new StringBuilder();
				
				
				//sb2.append("<table class='footable table table-stripped' data-filter=#filter>");
				//sb2.append("<thead><tr> <th scope='col'>Patente</th><th scope='col'>Fecha</th><th scope='col'>Ubicacion</th><th scope='col'>Nro interno</th></tr></thead>");
				//sb2.append("<tbody>");
				sb2.append(contenidotabla);
				contenidot.innerHTML = sb2.toString();
				
				for (var j = 0; j < markers.length; j++) {
				
				var marker = markers[j];
				var idnumero = "equipos" + j;
				//console.log(idnumero);
				//version popup
				var equipos = document.getElementById(idnumero);
				//console.log(idnumero);
				//console.log(equipos);

				equipos.addEventListener('click', (function(marker, j) {
					return function() {
						console.log("dentro funcion")
						infoWindow.setContent(infoWindowContent[j]);
						infoWindow.open(map, marker);
					}
				})(marker, j));
				

				}
				
				try {
					if($('#primera_carga').val()=="1"){
						centrar_todo_dos(markers); 
						$('#primera_carga').val('2');
					}
				}
				catch(err) {
				  console.log(err.message);
				}	
				
				
				//legend.innerHTML = "";
				//legend.appendChild(table);
				//legend.innerHTML = table;
				//nuevo    
				//creo datatable
				/* $("#ultimosd").DataTable({
							"language": {
					            "url": URLactual + "/sgomtweb/resources/datetableespanil.json"
					        },	
					        //async : true, 
					        "order": [[ 2, "desc" ]],
							"autoWidth": false,		
						
				 }); // fin datatable (para dialog)*/

				//recorro de nuevo los valores para abrir los infoWindow desde los div creados en legent
				/*console.log(doc)
				
				//var equipos = null;
				doc.getElementsByTagName('body')[0].innerHTML = '';
				//doc.getElementById('ultimos_wrapper').innerHTML = '';	
							  
				doc.write("\<html\>\<head\>");
				doc.write("\<link href=\'resources/css/stylepopup.css\' rel=\'stylesheet\'\>");			
				
				doc.write("\<body class=\'top-navigation gray-bg\'\>");
				doc.write("\<div class=\'wrapper\'\>		\<div id='\page-wrapper\' class=\'gray-bg\'\>		   \<div class=\'wrapper wrapper-content animated fadeInRight\'\>");
				doc.write("\<div class=\'row\'\>		\<div class=\'col-lg-12\'\>		 \<div class=\'ibox-content\'\>	 \<div class=\'table-responsive\'\> ");
				doc.write(legend.innerHTML);
				doc.write("\</div\>		\</div\>	\</div\>");
				doc.write("\</div\>		\</div\>	\</div\> \</div\>");
				
				var testlog = "\<script\> console.log(\'hola\') \</script\>";
				var urllibes =  "\<script\> var URLactual =window.location.protocol +'////'+ window.location.host \</script\>";		
				var jquejs = "\<script src='resources/inspinia_v2.9/FullVersion/js/jquery-3.1.1.min.js'\>\</script\>";
				var jquejs2 = "\<script src='resources/inspinia_v2.9/FullVersion/js/bootstrap.min.js'\>\</script\>";
				var libreriajs = "\<script src='resources/inspinia_v2.9/FullVersion/js/plugins/dataTables/datatables.min.js'\>\</script\>";
				var libreriajs2 = "\<script src='resources/inspinia_v2.9/FullVersion/js/plugins/dataTables/dataTables.bootstrap4.min.js'\>\</script\>"		
				//var datadablestring = "\<script\> $(\'#ultimos\').DataTable({ \'language\': { \'url\': URLactual + \'/sgomtweb/resources/datetableespanil.json\'	 }, \'order\': [[ 2, \'desc\' ]], \'autoWidth\': false,	}); \</script\>"; 
				
				doc.write( testlog );
				doc.write( urllibes );
				doc.write( jquejs );
				doc.write( jquejs2 );
				doc.write( libreriajs );
				doc.write( libreriajs2 );
				//doc.write( datadablestring );
				
				/*
				for (j in valor) {
				
				var marker = markers[j];
				
				idnumero = "equipos" + j;
				//version dialog
				//var equipos = document.getElementById(idnumero);      
				
				//version popup
				
				var  equipos = doc.getElementById(idnumero);
				console.log(idnumero);
				console.log(equipos);
				
				equipos.addEventListener('click', (function(marker, j) {        
				return function() {
					console.log("dentro funcion")
				    infoWindow.setContent(infoWindowContent[j]);
				    infoWindow.open(map, marker);
				}
				})(marker, j));
				    
				
				}//fin for 
				
				//va al final popup cierre
				doc.close();*/

				//para quitar maker por estados
				var marker = "";
				z = 0;
				document.getElementById("filter").value="";
			} // fin sussecs
		}); //fin ajax

	} //fin funcion

	function DeleteMarkers() {

		//elimina todas las marca
		for (var i = 0; i < markers.length; i++) {
			markers[i].setMap(null);
		}
		markers = [];
		var marker = "";
	};

	getCoords();

	function intervalo() {
		//DeleteMarkers(); se coloco al inicio de getcoord
		getCoords();

	}

	window.setInterval(intervalo, 30000); //5000	       

	//fin ultima

	/*
	
	deprecaded
	
	doc.getElementsByTagName('body')[0].innerHTML = '';
	//doc.getElementById('ultimos_wrapper').innerHTML = '';	
	
	  
	doc.write("\<html\>\<head\>");
	doc.write("\<link href=\'resources/css/stylepopup.css\' rel=\'stylesheet\'\>");
	doc.write("\<html\>\<head\>");
	 
	doc.write(legend.innerHTML);
	var testlog = "\<script\> console.log(\'hola\') \</script\>";
	var urllibes =  "\<script\> var URLactual =window.location.protocol +'////'+ window.location.host \</script\>";		
	var jquejs = "\<script src='resources/inspinia_v2.9/FullVersion/js/jquery-3.1.1.min.js'\>\</script\>";
	var jquejs2 = "\<script src='resources/inspinia_v2.9/FullVersion/js/bootstrap.min.js'\>\</script\>";
	var libreriajs = "\<script src='resources/inspinia_v2.9/FullVersion/js/plugins/dataTables/datatables.min.js'\>\</script\>";
	var libreriajs2 = "\<script src='resources/inspinia_v2.9/FullVersion/js/plugins/dataTables/dataTables.bootstrap4.min.js'\>\</script\>"		
	var datadablestring = "\<script\> $(\'#ultimos\').DataTable({ \'language\': { \'url\': URLactual + \'/sgomtweb/resources/datetableespanil.json\'	 }, \'order\': [[ 2, \'desc\' ]], \'autoWidth\': false,	}); \</script\>"; 
	
	doc.write( testlog );
	doc.write( urllibes );
	doc.write( jquejs );
	doc.write( jquejs2 );
	doc.write( libreriajs );
	doc.write( libreriajs2 );
	doc.write( datadablestring );
	
	 */

	//no va
	/*  for (z in valor) {
	 
	var marker = markers[z];
	   
	     var patente = valor[z].patente;
	     var estado = valor[z].estado;
	     var descestado = valor[z].descestado;
	     
	      console.log(patente);
	     console.log(estado);
	       console.log(descestado);
	        console.log(marker);
	
	   if(estado == 1){
	    
	     arribaControlDiv.addEventListener('click',  (function(marker, z)  {
	    return function() {
	         if( marker.getMap() == null){ console.log("es null map")
	           marker.setMap(map);	            
	         }else{ console.log(marker)
	          marker.setMap(null);
	         }    
	          }
	     }) (marker, z));
	     }// fin if  
	     
	} */

	//EN DESARROLLO , TABLA DE EQUIPOS EN GOOGLE MAPS USANDO EL SERVICIO  Por si se necesita separado de las marca
	/*   var legend = document.getElementById('legend');
	   var div2 = document.createElement('div');
	   div2.setAttribute("id", "contenido");
	   contenido = document.getElementById('contenido');
	     
	           for (i in valor) {
	             var patente = valor[i].patente;
	             var fecha = valor[i].fechaS;
	             var geo = valor[i].rutGeocerca;
	             var nro = valor[i].nroInterno;
	                          
	             var div = document.createElement('div');
	             div.setAttribute("id", "equipos");
	                                                     
	             div.innerHTML = ' <b>'+nro+'</b> '+patente + ' ' + fecha + ' '+ geo;                       
	             div2.appendChild(div);  
	             
	             div.addEventListener("click", function(){
				 alert('entro');
				 
	               infoWindow.setContent(infoWindowContent[i]);
	               infoWindow.open(map, marker);
	           
				 
			});
	                       
	           }//fin for
	           
	            legend.innerHTML = div2.innerHTML;
	            
	           
	 */

	//FIN EN DESARROLLO , TABLA DE EQUIPOS EN GOOGLE MAPS USANDO EL SERVICIO                   
	/*$.each( returnedData.points, function(i, value) {
	          // var myLatlng =  new google.maps.LatLng(value.lat, value.lon);
	           alert(value.lat);
	           alert(value.lon);
	           //var marker = new google.maps.Marker({
	           //position: myLatlng,
	           //map: map,
	           //title:"Hello World!"
	     //  });

	           }); */

	//moveMarkerMap(returnedData);
	//no se usa mas , se usa icon de tipo vehiculos
	//camion = 6;
	//camion dumper = 30;
	//maquina  = "11, 19, 22, 25, 26, 27";
	/*if(valor[i].idTipoVehiculo == 6){
	
	icon = 'http://maps.google.com/mapfiles/ms/icons/red-dot.png';
	
	//}else  if(valor[i].idTipoVehiculo == 11 || valor[i].idTipoVehiculo == 19 || valor[i].idTipoVehiculo == 22 || valor[i].idTipoVehiculo == 25 || valor[i].idTipoVehiculo == 26 || valor[i].idTipoVehiculo == 27){
	}else  if(valor[i].idTipoVehiculo == 11 || valor[i].idTipoVehiculo == 22 || valor[i].idTipoVehiculo == 25 || valor[i].idTipoVehiculo == 26 || valor[i].idTipoVehiculo == 27){
	
	icon = 'http://maps.google.com/mapfiles/ms/icons/'+valor[i].icon;
	//icon = 'http://maps.google.com/mapfiles/ms/icons/yellow-dot.png';
	
	} else if(valor[i].idTipoVehiculo == 30){
	
	  icon = 'http://maps.google.com/mapfiles/ms/icons/blue-dot.png';
	
	}
	else if(valor[i].idTipoVehiculo == 19){
	
	  icon = 'http://maps.google.com/mapfiles/ms/icons/green-dot.png';
	
	}
	
	else {
	
	icon = 'http://maps.google.com/mapfiles/ms/icons/red-dot.png';
	
	}
	
	/*function moveMarkerMap(newCoords) {
	var newLatLang = new google.maps.LatLng(newCoords);
	map.panTo(newLatLang);
	marker.setPosition(newLatLang);

	}*/
	function StringBuilder(value) {
		this.strings = new Array();
		this.append(value);
	}

	StringBuilder.prototype.append = function(value) {
		if (value) {
			this.strings.push(value);
		}
	}

	StringBuilder.prototype.clear = function() {
		this.strings.length = 0;
	}

	StringBuilder.prototype.toString = function() {
		return this.strings.join("");
	}
</script>
