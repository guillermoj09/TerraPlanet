var myLatLng = {lat: ${marcasxid.lat}0, lng: ${marcasxid.lon}0};

marker=	new google.maps.Marker({
					position: myLatLng
					, map: map
					, title: '${marcasxid.nombre}'
					, cursor: 'pointer'
					, draggable: true
				});					

	map.setCenter(myLatLng);

	google.maps.event.addListener(marker,'drag',function(event) {
						document.getElementById('txtLat0').value = this.position.lat();
						document.getElementById('txtLon0').value = this.position.lng();
						 
					});	