
var pathCoords = [
<c:forEach items="${listHistorico}" var="r" varStatus="count" >	
			{
			"lat": ${r.lat},
			"lng": ${r.lon}
			},
</c:forEach>			
			];
			
		

	function moveMarker(map, marker, latlng) {
			marker.setPosition(latlng);
			map.panTo(latlng);
		}

		function autoRefresh(map) {
			var i, route, marker;
			
			route = new google.maps.Polyline({
				path: [],
				geodesic : true,
				strokeColor: '#FF0000',
				strokeOpacity: 1.0,
				strokeWeight: 2,
				
				  icons: [{

        icon: {

          path: google.maps.SymbolPath.FORWARD_CLOSED_ARROW,

          strokeColor: 'black',

          strokeOpacity: 2.0,

          strokeWeight: 1.0,

          fillColor: 'yellow',

          fillOpacity: 1.0,

          scale: 3

        },

        repeat: '10%',//3

        offset: '5%'

      }],
				editable: false,
				map:map
			});
			
			marker=new google.maps.Marker({map:map,icon:"http://maps.google.com/mapfiles/ms/micons/blue.png"});

			for (i = 0; i < pathCoords.length; i++) {
				setTimeout(function (coords)
				{
					var latlng = new google.maps.LatLng(coords.lat, coords.lng);
					route.getPath().push(latlng);
					moveMarker(map, marker, latlng);
				}, 200 * i, pathCoords[i]);
			}
		}
		
		autoRefresh(map);

		//google.maps.event.addDomListener(window, 'load', initialize);
	