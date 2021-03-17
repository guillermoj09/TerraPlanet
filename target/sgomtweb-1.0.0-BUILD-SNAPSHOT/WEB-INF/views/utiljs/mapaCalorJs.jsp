 		 		
 		heatmap = new google.maps.visualization.HeatmapLayer({
              data: getPoints(),
              map: map,
              radius : 20,
              //opacity : 0.8
              opacity : 1
            });

          function getPoints() {

            return [ 
                                   
            <c:forEach items="${mlist}" var="m" varStatus="count" >
            	new google.maps.LatLng(parseFloat(${m.lat}),parseFloat(${m.lon})),
            </c:forEach>
                        
            	//new google.maps.LatLng(parseFloat(-22.74228),parseFloat(-070.61926)),
                       	
            ];

          }