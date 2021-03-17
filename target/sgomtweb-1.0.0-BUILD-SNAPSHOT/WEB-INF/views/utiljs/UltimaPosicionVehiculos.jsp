        //ultima
        
        <c:forEach items="${mulist}" var="mu" varStatus="count" >
	     	      
        	//var icon = 'http://maps.google.com/mapfiles/ms/icons/red-dot.png';
        	var icon = 'resources/img/iconos/${mu.vehiculo.tipoVehiculo.tivRutaIcono}';
        	     
   		   //var tipoVehiculo_${count.index} = ${mu.vehiculo.tipoVehiculo.tipvDescripcion}.toFixed(1); //dale formato a decimale y imprimirlo string
   		   var tipoVehiculo_${count.index} = '${mu.vehiculo.tipoVehiculo.tipvDescripcion}';
   		   var contenido_${count.index} = '<div id="content">'+
   	      								  '<div id="siteNotice">'+
   	      								  '</div>'+
   	      							      '<h1 id="firstHeading" class="firstHeading">${mu.vehiculo.vehPatente}</h1>'+
   	      								  '<div id="bodyContent">'+
   	      								  '<p><b>Evento</b>:  <font size="+1.5"><i class=\'${mu.classEvent}\'></i> </font> ${mu.evento}</p>' +
   	      								  '<p><b>Tipo Vehiculo</b>: '+tipoVehiculo_${count.index}+'</p>'+
   	      								  '<p><b>Marca</b>:  ${mu.vehiculo.vehMarca}</p>'+   	      								  
   	      								  '<p><b>Fecha</b>: <fmt:formatDate value="${mu.fecha}" pattern="dd/MM/yyyy HH:mm" /></p>'+
   	      								  '<p><b>Numero Interno</b>: ${mu.vehiculo.vehNumInterno}</p>'+
   	      								  '<p><b>Velocidad</b>: ${mu.velocidad} Kmh</p>'+
   	      								  <c:if test="${mu.rutGeocerca != null }">
   	      								  '<p><b>Geocerca</b>: ${mu.rutGeocerca}</p>'+
   	      								  </c:if>
   	      								  //'</p>'+   	      
   	      							      '</div>'+
   	      								  '</div>';
	      	     	        	       	       
	        var marker_${count.index} = new google.maps.Marker({
	        		        	
	        	position: {lat: ${mu.lat}, lng: ${mu.lon}},
	            map: map,
	            icon: icon,
	            animation: google.maps.Animation.DROP,
		  		title: '${mu.vehiculo.vehPatente}'   //
		  			  			
		  		
	        });
	     //Llamo la ventana
	        google.maps.event.addListener(marker_${count.index}, 'click', function() { //mouseover
	            infowindow = new google.maps.InfoWindow({
	                content: contenido_${count.index}//porcentaje_${count.index}+'%'
	            });
	            infowindow.open(map, marker_${count.index});
	        });
	        
	     
	     // Evento cierra la ventana al hacer click en el mapa
	        google.maps.event.addListener(map, 'click', function() {
	          infowindow.close();
	        });		     	       
		      
	 	   </c:forEach>
	 	   
	 	  
	 	        
	 	      
	     
	     //fin ultima