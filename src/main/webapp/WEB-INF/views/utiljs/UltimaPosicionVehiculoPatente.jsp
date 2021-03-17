        //ultima x patente
        
     
	     	      
        	var icon = 'http://maps.google.com/mapfiles/ms/icons/red-dot.png';
        	     
   		   //var tipoVehiculo = ${mc.vehiculo.tipoVehiculo.tipvDescripcion}.toFixed(1); //dale formato a decimale y imprimirlo string
   		   var tipoVehiculo = '${mc.vehiculo.tipoVehiculo.tipvDescripcion}';
   		   var contenido = '<div id="content">'+
   	      								  '<div id="siteNotice">'+
   	      								  '</div>'+
   	      							      '<h1 id="firstHeading" class="firstHeading">${mc.vehiculo.vehPatente}</h1>'+
   	      								  '<div id="bodyContent">'+
   	      								  //'<p><b>Evento</b>:  <font size="+1.5"><i class=\'${mc.classEvent}\'></i></font></p>' +
   	      								  '<p><b>Tipo Vehiculo</b>: '+tipoVehiculo+'</p>'+
   	      								  '<p><b>Marca</b>:  ${mc.vehiculo.vehMarca}</p>'+
   	      								  //'<p><b>Fecha</b>: <fmt:formatDate value="${mc.fecha}" pattern="dd/MM/yyyy HH:mm" /></p>'+
   	      								  '<p><b>Numero Interno</b>: ${mc.vehiculo.vehNumInterno}</p>'+
   	      								  //'<p><b>Velocidad</b>: ${mc.velocidad}</p>'+
   	      								  //'</p>'+   	      
   	      							      '</div>'+
   	      								  '</div>';
	      	     	        	       	       
	        var marker = new google.maps.Marker({
	        		        	
	        	position: {lat: ${mc.lat}, lng: ${mc.lon}},
	            map: map,
	            icon: icon,
	            animation: google.maps.Animation.DROP,
		  		title: '${mc.vehiculo.vehPatente}'   //nombreEstanque
		  			  			
		  		
	        });
	     //Llamo la ventana
	        google.maps.event.addListener(marker, 'click', function() { //mouseover
	            infowindow = new google.maps.InfoWindow({
	                content: contenido
	            });
	            infowindow.open(map, marker);
	        });
	        
	     
	     // Evento cierra la ventana al hacer click en el mapa
	        google.maps.event.addListener(map, 'click', function() {
	          infowindow.close();
	        });	   
	      
	        	        
	     
	     //fin ultima