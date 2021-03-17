  <c:if test="${empty listHistorico2 }">	    
  
  alert("No presenta resultado");
  
  </c:if>
  
        var arribaControlDiv = document.createElement('div');
        var geoControl = new ArribaControl(arribaControlDiv, Poligonos);

        arribaControlDiv.index = 1;
        map.controls[google.maps.ControlPosition.LEFT_CENTER].push(arribaControlDiv);     
  
 
         function ArribaControl(controlDiv, Poligonos) {
  
     	// Set CSS for the control border.
          var controlUI = document.createElement('div');
          controlUI.style.backgroundColor = '#fff';
          controlUI.style.border = '2px solid #fff';
          controlUI.style.borderRadius = '2px';
          controlUI.style.boxShadow = '0 2px 6px rgba(0,0,0,.3)';
          controlUI.style.cursor = 'pointer';
          controlUI.style.marginBottom = '22px';
          controlUI.style.marginLeft = '9px';
          controlUI.style.marginTop = '8px';
          controlUI.style.textAlign = 'center';
          controlUI.title = 'Click para mostrar o ocultar GEO';
          controlDiv.appendChild(controlUI);

          // Set CSS for the control interior.
          var controlText = document.createElement('div');
          //controlText.style.color = 'rgb(25,25,25)';
          //controlText.style.fontFamily = 'Roboto,Arial,sans-serif';
          controlText.style.fontFamily = 'Normal';
          controlText.style.fontSize = '16px';
          controlText.style.lineHeight = '38px';
          controlText.style.paddingLeft = '14px';
          controlText.style.paddingRight = '14px';
          controlText.innerHTML = '<i class="fa fa-level-up" title="arriba"></i>';
          controlUI.appendChild(controlText);
          
          }
          
        var abajoControlDiv = document.createElement('div');
        var geoControl = new AbajoControl(abajoControlDiv);

        abajoControlDiv.index = 1;
        map.controls[google.maps.ControlPosition.LEFT_CENTER].push(abajoControlDiv);     
  
 
         function AbajoControl(controlDiv) {
  
     	// Set CSS for the control border.
          var controlUI = document.createElement('div');
          controlUI.style.backgroundColor = '#fff';
          controlUI.style.border = '2px solid #fff';
          controlUI.style.borderRadius = '2px';
          controlUI.style.boxShadow = '0 2px 6px rgba(0,0,0,.3)';
          controlUI.style.cursor = 'pointer';
          controlUI.style.marginBottom = '10px';
          controlUI.style.marginLeft = '9px';
          controlUI.style.marginTop = '1px';
          controlUI.style.textAlign = 'center';
          controlUI.title = 'Abajo';
          controlDiv.appendChild(controlUI);

          // Set CSS for the control interior.
          var controlText = document.createElement('div');
          //controlText.style.color = 'rgb(25,25,25)';
          controlText.style.fontFamily = 'Normal';
          controlText.style.fontSize = '16px';
          controlText.style.lineHeight = '38px';
          controlText.style.paddingLeft = '14px';
          controlText.style.paddingRight = '14px';
          controlText.innerHTML = '<i class="fa fa-level-down" title="abajo"></i>';
          controlUI.appendChild(controlText);
          
          }
          
         var eventoControlDiv = document.createElement('div');
        var geoControl = new EventoControl(eventoControlDiv);

        eventoControlDiv.index = 1;
        map.controls[google.maps.ControlPosition.LEFT_CENTER].push(eventoControlDiv);   
  
 
         function EventoControl(controlDiv) {
  
     	// Set CSS for the control border.
          var controlUI = document.createElement('div');
          controlUI.style.backgroundColor = '#fff';
          controlUI.style.border = '2px solid #fff';
          controlUI.style.borderRadius = '2px';
          controlUI.style.boxShadow = '0 2px 6px rgba(0,0,0,.3)';
          controlUI.style.cursor = 'pointer';
          controlUI.style.marginBottom = '22px';
          controlUI.style.marginLeft = '9px';
          controlUI.style.marginTop = '10px';
          controlUI.style.textAlign = 'center';
          controlUI.title = 'Evento';
          controlDiv.appendChild(controlUI);

          // Set CSS for the control interior.
          var controlText = document.createElement('div');
          //controlText.style.color = 'rgb(25,25,25)';
          controlText.style.fontFamily = 'Normal';
          controlText.style.fontSize = '16px';
          controlText.style.lineHeight = '38px';
          controlText.style.paddingLeft = '14px';
          controlText.style.paddingRight = '14px';
          controlText.innerHTML = '<i class="fa fa-map-marker" title="evento"></i>';
          controlUI.appendChild(controlText);
          
          }
          
		var printError = function(error, explicit) {
  
    	}
  <c:forEach items="${listHistorico2}" var="r" varStatus="count" >	  
	     	
		
		try {
			var image_icon = '${r.rutaflecha}';
			console.log(image_icon);
			var arrayicon = image_icon.split(".");
			console.log(arrayicon[0]);
		    var icon = "resources/img/historico/"+arrayicon[0]+".png";
		    console.log("El icono es:", icon);
		} catch (e) {
		    if (e instanceof SyntaxError) {
		        printError(e, true);
		    } else {
		        printError(e, false);
		    }
		}
	       	//https://sites.google.com/site/gmapsdevelopment/
	       	//var icon = 'http://maps.google.com/mapfiles/ms/icons/blue-dot.png';
	       	//var icon = 'http://maps.gstatic.com/mapfiles/ridefinder-images/mm_20_blue.png'
	       	
	       	//idEvent 7 arriba
	       	//idEvent 8 abajo
	       	var event = 1;
	       	
	       	var ancho = 25;
	       	<c:if test="${r.idEvent == 7}">
	       	 icon = 'http://maps.google.com/mapfiles/ms/icons/green-dot.png';
	       	 ancho = 33;
	       	 event = 7;
	       	</c:if>
	       	<c:if test="${r.idEvent == 8}">
	       	 icon = 'http://maps.google.com/mapfiles/ms/icons/red-dot.png';
	       	 ancho = 33;
	       	 event = 8;
	       	</c:if>
	       	     	   		   		    
   		   var contenido_${count.index} = '<div id="content">'+
   	      								  '<div id="siteNotice">'+
   	      								  '</div>'+
   	      							      '<h1 id="firstHeading" class="firstHeading">${r.numInterno}</h1>'+
   	      								  '<div id="bodyContent">'+
   	      								  '<p><b>${r.patente}</b> </p>' +   
   	      								  '<p><b>Evento</b>:  <font size="+1.5"><i class=\'${r.classEventJs}\'></i> </font> ${r.nombreve}</p>' +	      								
   	      								  '<p><b>Velocidad </b>:  ${r.spd}</p>'+
   	      							      '<p><b>Hora  </b>: <fmt:formatDate value="${r.data_date}" pattern="HH:mm" /></p>'+
   	      								  
   	      								  //'</p>'+   	      
   	      							      '</div>'+
   	      								  '</div>';
   	       							  	      	     	        	       	       
	        var marker_${count.index} = new google.maps.Marker({
	        			     	        	
	        	position: {lat: parseFloat("${r.lat}"), lng: parseFloat("${r.lon}") },
	            map: map,
	            icon: icon,
	            <c:if test="${r.idEvent == 7 || r.idEvent == 8 }">
	            animation: google.maps.Animation.DROP,
	            </c:if>
		  		title: '<fmt:formatDate value="${r.data_date}" pattern="dd/MM/yyyy HH:mm" />'		  			  			
		  		
	        });
	     //Llamo la ventana
	        google.maps.event.addListener(marker_${count.index}, 'click', function() { //mouseover
	            infowindow = new google.maps.InfoWindow({
	                content: contenido_${count.index}
	            });
	            infowindow.open(map, marker_${count.index});
	        });
	        
	     
	     // Evento cierra la ventana al hacer click en el mapa
	        google.maps.event.addListener(map, 'click', function() {
	          infowindow.close();
	        });
	        
	        //ARRIBA
	        <c:if test="${r.idEvent == 7}">
	         arribaControlDiv.addEventListener('click', function() {
	         if( marker_${count.index}.getMap() == null){
	           marker_${count.index}.setMap(map);
	             // markers[i].setMap(null);
	         }else{
	          marker_${count.index}.setMap(null);
	         }    
	
	        });// fin coltrolUI
	        </c:if>
	        
	        //ABAJO
	        <c:if test="${r.idEvent == 8}">
	         abajoControlDiv.addEventListener('click', function() {
	         if( marker_${count.index}.getMap() == null){
	           marker_${count.index}.setMap(map);
	             // markers[i].setMap(null);
	         }else{
	          marker_${count.index}.setMap(null);
	         }    
	
	        });// fin coltrolUI
	        </c:if>
	        
	        //EVENTOS
	         <c:if test="${r.idEvent != 7 && r.idEvent != 8 }">
	         eventoControlDiv.addEventListener('click', function() {
	         if( marker_${count.index}.getMap() == null){
	           marker_${count.index}.setMap(map);
	             // markers[i].setMap(null);
	         }else{
	          marker_${count.index}.setMap(null);
	         }    
	
	        });// fin coltrolUI
	        </c:if>
	     
	         
	      
	   </c:forEach>
	   
	 
        
	   
	 //Trazo ruta 
       var flightPlanCoordinates = [
    	   <c:forEach items="${listHistorico}" var="r2" varStatus="count" > 
    	   
    	   {lat: parseFloat("${r2.lat}"), lng: parseFloat("${r2.lon}") },
    	   
    	   </c:forEach>
    	                
     ] ;
   	  		 
	 var iconstarr = 'http://maps.google.com/mapfiles/ms/icons/arrow.png';
	 var iconend = 'http://maps.google.com/mapfiles/ms/icons/flag.png';	 
	  	 
	  	 var flightPath = new google.maps.Polyline({
	  	    path: flightPlanCoordinates,
	  	    geodesic: true,
	  	    strokeColor: '#FF0000',
	  	    strokeOpacity: 1.0,
	  	    strokeWeight: 2
	  	    });

	  	  flightPath.setMap(map);
	  	  
	  	  
	  	  
	  	var marker1 = new google.maps.Marker({
		  	   position: flightPlanCoordinates[0],
		  	   map: map,
		  	   icon: iconstarr,
		  	    animation: google.maps.Animation.DROP,
		  	   title: "Inicio"
		  	});

		var marker2 = new google.maps.Marker({
		  	   position: flightPlanCoordinates[ flightPlanCoordinates.length - 1 ],
		  	   map: map,
		  	   icon: iconend,
		  	   animation: google.maps.Animation.DROP,
		  	   title: "Fin"
		  	});
		  	
		  	var contenido2 = "Inicio"
		  	var contenido3 = "Fin"
		  	
		    google.maps.event.addListener(marker1, 'click', function() { //mouseover
	            infowindow = new google.maps.InfoWindow({
	                content: contenido2
	            });
	            infowindow.open(map, marker1);
	        });
		  	
		    google.maps.event.addListener(marker2, 'click', function() { //mouseover
	            infowindow = new google.maps.InfoWindow({
	                content: contenido3
	            });
	            infowindow.open(map, marker2);
	        });
	  	  		   	  