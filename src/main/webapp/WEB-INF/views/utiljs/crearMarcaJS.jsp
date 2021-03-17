marker=	new google.maps.Marker({
					position: map.getCenter()
					, map: map
					, title: 'Pulsa aquí'
					, cursor: 'pointer'
					, draggable: true
				});					


	google.maps.event.addListener(marker,'drag',function(event) {
						document.getElementById('txtLat0').value = this.position.lat();
						document.getElementById('txtLon0').value = this.position.lng();
						 
					});	