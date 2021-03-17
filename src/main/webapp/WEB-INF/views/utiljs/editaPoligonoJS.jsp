
			var triangleCoords = [
			<c:forEach items="${mlistGeoId.coordenadas}" var="mgC" varStatus="count" >	
				new google.maps.LatLng(${mgC.lat},${mgC.lon}),									
			</c:forEach>
			];
	
                GeoPoligonal = new google.maps.Polygon({
                    paths: triangleCoords,
                    strokeColor: colorGeo,
                    strokeOpacity: 0.8,
                    strokeWeight: 2,
                    fillColor: colorGeo,
                    fillOpacity: 0.35,
                    editable: true,
                    draggable: true,
                    zIndex: 99999999
                });
                GeoPoligonal.setMap(map);
                
                addDeleteButton(GeoPoligonal, 'http://i.imgur.com/RUrKV.png');
                
                
                marker.setVisible(true);
                
                 centrar(GeoPoligonal.getPath().getArray());
                 
                  console.log("Geo")
                console.log(GeoPoligonal.getPath().getArray());
                $('#geomText').val(GeoPoligonal.getPath().getArray());  
                 
                // console.log(google.maps.geometry.spherical.computeArea(GeoPoligonal.getPath().getArray()))
                
                google.maps.event.addListener(GeoPoligonal, 'drag', function () {
                    centerPoligonoEP(GeoPoligonal.getPath().getArray());
                });
				
				google.maps.event.addListener(GeoPoligonal.getPath(), 'set_at', function (event) {
					centerPoligonoEP(GeoPoligonal.getPath().getArray());
				});				
                google.maps.event.addListener(GeoPoligonal.getPath(), 'insert_at', function () {
				//console.log('insert_at')
                    centerPoligonoEP(GeoPoligonal.getPath().getArray());
                });                
                google.maps.event.addListener(GeoPoligonal.getPath(), 'remove_at', function () {
                    centerPoligonoEP(GeoPoligonal.getPath().getArray());
                });
                
                
             function centrar(arreglo){
             
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
                
                //map.setCenter(new google.maps.LatLng(boundsP.getCenter().lat(), boundsP.getCenter().lng()));
                
                map.fitBounds(boundsP);    
				map.panToBounds(boundsP);  

                $('#txtLat0').val(boundsP.getCenter().lat());
                $('#txtLon0').val(boundsP.getCenter().lng());
                $("#txtArea").val(Math.round(google.maps.geometry.spherical.computeArea(arreglo)*10)/10);
                            
             }   
             
             
function addDeleteButton(poly, imageUrl) {
  var path = poly.getPath();
  path["btnDeleteClickHandler"] = {};
  path["btnDeleteImageUrl"] = imageUrl;
  
  google.maps.event.addListener(poly.getPath(),'set_at',pointUpdated);
  google.maps.event.addListener(poly.getPath(),'insert_at',pointUpdated);
}

function pointUpdated(index) {
  var path = this;
  var btnDelete = getDeleteButton(path.btnDeleteImageUrl);

  
  if(btnDelete.length === 0) 
  {
    var undoimg = $("img[src$='https://maps.gstatic.com/mapfiles/undo_poly.png']");
      
    undoimg.parent().css('height', '21px !important');
    undoimg.parent().parent().append('<div id ="btn_delete_por_aca" style="overflow-x: hidden; overflow-y: hidden; position: absolute; width: 30px; height: 27px;top:21px;"><img src="' + path.btnDeleteImageUrl + '" class="deletePoly" style="height:auto; width:auto; position: absolute; left:0;"/></div>');
       
    // now get that button back again!
    btnDelete = getDeleteButton(path.btnDeleteImageUrl);
    btnDelete.hover(function() { $(this).css('left', '-30px'); return false;}, 
                    function() { $(this).css('left', '0px'); return false;});
    btnDelete.mousedown(function() { $(this).css('left', '-60px'); return false;});
  }
  
  // if we've already attached a handler, remove it
  if(path.btnDeleteClickHandler) 
    btnDelete.unbind('click', path.btnDeleteClickHandler);
    
  // now add a handler for removing the passed in index
  path.btnDeleteClickHandler = function() {
    path.removeAt(index); 
    return false;
  };
  btnDelete.click(path.btnDeleteClickHandler);
}

function getDeleteButton(imageUrl) {
  return  $("img[src$='" + imageUrl + "']");
}
                
            function centerPoligonoEP(arreglo) {
            	$("#txtArea").val(Math.round(google.maps.geometry.spherical.computeArea(arreglo)*10)/10);
            	$('#geomText').val(arreglo);    
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
                
                $('#txtLat0').val(boundsP.getCenter().lat());
                $('#txtLon0').val(boundsP.getCenter().lng());
              	marker.setPosition(new google.maps.LatLng($('#txtLat0').val(), $('#txtLon0').val()));


            }

