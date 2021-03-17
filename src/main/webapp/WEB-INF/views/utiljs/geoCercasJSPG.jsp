
   	
   		function showArrays(event) {

						  var contentString = '<b>' + this.title +'</b>'

						  infoWindow.setContent(contentString);
						  infoWindow.setPosition(event.latLng);
						  infoWindow.open(map);

						}

  	
   	var markersArray = [];
	var infoWindowsArray = [];	
	var Poligonos = [];		
	Poligonos.length = 0;	
	var bermudaTriangle;
	
	var allGeo = [];
	
		
        var latlng = new google.maps.LatLng(parseFloat("${mc.lat}"), parseFloat("${mc.lon}"));        
        var myLatLng = latlng;
		
						var trafficLayer = new google.maps.TrafficLayer();
						 trafficLayer.setMap(map);
							
						var transitLayer = new google.maps.TransitLayer();
							 transitLayer.setMap(map);

						var bikeLayer = new google.maps.BicyclingLayer();
							 bikeLayer.setMap(map);

			<c:forEach items="${mlistGeo}" var="mg" varStatus="count" >													
											
							var triangleCoords = [
							<c:forEach items="${mg.coordenadas}" var="mgC" varStatus="count" >	
								new google.maps.LatLng(${mgC.lat},${mgC.lon}),									
							</c:forEach>
							];
							
							
							<c:forEach items="${mg.coordenadas}" var="mgC" varStatus="count" >									
								allGeo.push(new google.maps.LatLng(${mgC.lat},${mgC.lon}));								
							</c:forEach>
							
							
							var  bermudaTriangle = new google.maps.Polygon({
								paths: triangleCoords,
								strokeColor: '${mg.codigo_color}',
								strokeOpacity: 0.8,
								strokeWeight: 2,
								fillColor: '${mg.codigo_color}',
								fillOpacity: 0.35,
										title: '${mg.nombre}'
								});

							 bermudaTriangle.setMap(map);
				
							 var infowindow = new google.maps.InfoWindow({
									content: '${mg.nombre}'
								});

							bermudaTriangle.addListener('click', showArrays);
							infoWindow = new google.maps.InfoWindow;
							
							Poligonos.push(bermudaTriangle);
							
				    		
			</c:forEach>
			
			
			/* centrar_todo(allGeo); */
			
			function centrar_todo(arreglo){
             
              var boundsP = new google.maps.LatLngBounds();
                for (var i = 0; i < arreglo.length; i++) {
                    var c = 0;
                    var lt = 0;
                    var lg = 0;
                    for (var key in arreglo[i]) {
                        if (c == 0) {
                            lt = arreglo[i].lat();
                        }
                        if (c == 1) {
                            lg = arreglo[i].lng();
                        }
                        c = c + 1;
                    }
                    boundsP.extend(new google.maps.LatLng(parseFloat(lt), parseFloat(lg)));
                }

                map.fitBounds(boundsP);    
				map.panToBounds(boundsP);                              
             }
             
             
            
			
					
    
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	