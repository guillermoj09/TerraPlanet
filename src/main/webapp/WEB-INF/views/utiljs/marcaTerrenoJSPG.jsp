	<c:forEach items="${marcas}" var="mf" varStatus="count" >
	
	var image = {
          url: 'resources/img/${mf.nombre_icono}',
          size: new google.maps.Size(20, 32),
          scaledSize: new google.maps.Size(20, 32),
          origin: new google.maps.Point(0, 0),          
          anchor: new google.maps.Point(10, 30)
        };	

	
	  var marker = new google.maps.Marker({
	    position: {lat: ${mf.lat}, lng: ${mf.lon}},
	    map: map,
	    title: '${mf.nombre}',
	    icon: image	    
	  });
	  
	</c:forEach>
			