
   	
   		function showArrays(event) {

						  var contentString = '<b>' + this.title +'</b>'

						  infoWindow.setContent(contentString);
						  infoWindow.setPosition(event.latLng);
						  infoWindow.open(map);

						}

  	
   	var markersArray = [];
	var infoWindowsArray = [];
	
	
	var Circulos = [];
	var Cuadrados = [];
	var Poligonos = [];
	var Cars = [];
	var Puntos = [];
	var Marks = []; 
	
	
	Circulos.length = 0;
	Cuadrados.length = 0;
	Poligonos.length = 0;
	Cars.length = 0;
	Puntos.length = 0;
	Marks.length = 0;
	markersArray.length = 0;
	
	var cityCircle;
	var rectangle;
	var bermudaTriangle;
	var markerCluster;
	
		

        var latlng = new google.maps.LatLng(${mc.lat}, ${mc.lon});
        console.log(${mc.lat}, ${mc.lon})
        var myLatLng = latlng;
		

						var osmMapType = new google.maps.ImageMapType({
								getTileUrl: function (coord, zoom) {
										return "https://tile.openstreetmap.org/" + zoom + "/" + coord.x + "/" + coord.y + ".png";
								},
								tileSize: new google.maps.Size(256, 256),
								isPng: true,
								maxZoom: 19,
								minZoom: 0,
								name: "OSM",
						alt: "Open Street Map"
						});


						var bingSatType = new google.maps.ImageMapType({
								name: "BingSat",
								getTileUrl: function (coord, zoom) {
										return "https://ecn.t1.tiles.virtualearth.net/tiles/h" + TileToQuadKey(coord.x, coord.y, zoom) + ".jpeg?g=1173&n=z";
								},
								tileSize: new google.maps.Size(256, 256),
								maxZoom: 21,
						alt: "Mapa Bing Satelite"
						});

						var bingMapType = new google.maps.ImageMapType({
								name: "Bing",
								getTileUrl: function (coord, zoom) {
										return "https://ecn.t1.tiles.virtualearth.net/tiles/r" + TileToQuadKey(coord.x, coord.y, zoom) + ".jpeg?g=1173&n=z";
								},
								tileSize: new google.maps.Size(256, 256),
								maxZoom: 21,
						alt: "Mapa Bing Callejero"
						});

					  	map.setTilt(45);

						map.mapTypes.set('OSM', osmMapType);
					  	map.mapTypes.set('Bing', bingMapType);
						map.mapTypes.set('BingSat', bingSatType);


						var trafficLayer = new google.maps.TrafficLayer();
						 trafficLayer.setMap(map);
							
						var transitLayer = new google.maps.TransitLayer();
							 transitLayer.setMap(map);

						var bikeLayer = new google.maps.BicyclingLayer();
							 bikeLayer.setMap(map);


			<c:forEach items="${mlistGeo}" var="mg" varStatus="count" >	
					<c:if test="${mg.tipo == '2'}">	
											var populationOptions = {
												strokeColor: '${mg.color}',
												strokeOpacity: 0.8,
												strokeWeight: 2,
												fillColor: '${mg.color}',
												fillOpacity: 0.35,
												map: map,
												title: '${mg.nombre}',
												center: new google.maps.LatLng(${mg.lat0},${mg.lon0}),
												radius: (${mg.area}) * 1000
											};
									cityCircle = new google.maps.Circle(populationOptions);

									cityCircle.addListener('click', showArrays);
									infoWindow = new google.maps.InfoWindow;

									Circulos.push(cityCircle);
									
									</c:if>
					<c:if test="${mg.tipo == '1'}">	
									
										var rectangle = new google.maps.Rectangle({
										strokeColor: '${mg.color}',
										strokeOpacity: 0.8,
										strokeWeight: 2,
										fillColor: '${mg.color}',
										fillOpacity: 0.35,
										map: map,
												title: '${mg.nombre}',
										bounds: new google.maps.LatLngBounds(
											new google.maps.LatLng(${mg.lat2},${mg.lon4}),
											new google.maps.LatLng(${mg.lat3},${mg.lon1}))
										});

										var infowindow = new google.maps.InfoWindow({
											content: '${mg.nombre}'
										});

									rectangle.addListener('click', showArrays);
									infoWindow = new google.maps.InfoWindow;

									Cuadrados.push(rectangle);
									</c:if>									
					<c:if test="${mg.tipo == '3'}">
									
									var triangleCoords = [
									<c:forEach items="${mg.coordenadas}" var="mgC" varStatus="count" >	
										new google.maps.LatLng(${mgC.lat},${mgC.lon}),									
									</c:forEach>
									];
									
									var  bermudaTriangle = new google.maps.Polygon({
										paths: triangleCoords,
										strokeColor: '${mg.color}',
										strokeOpacity: 0.8,
										strokeWeight: 2,
										fillColor: '${mg.color}',
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

									</c:if>

	    
</c:forEach>
    
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	