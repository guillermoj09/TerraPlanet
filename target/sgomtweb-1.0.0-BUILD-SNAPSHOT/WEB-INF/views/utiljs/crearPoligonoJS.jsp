

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                var drawingManager = new google.maps.drawing.DrawingManager({
                    drawingMode: google.maps.drawing.OverlayType.POLYGON,
                    drawingControl: false,
                    drawingControlOptions: {
                        drawingModes: [google.maps.drawing.OverlayType.POLYGON]
                    },
                    polygonOptions: {
						
                        editable: true,
                        fillColor: colorGeo,
                        strokeColor: colorGeo,
                        strokeWeight: 2,
                        draggable: true,
                        zIndex: 99999999
                    }
                });
                
                //console.log("Geo")
                //console.log(GeoPoligonal.getPath().getArray());
                //$('#geomText').val(GeoPoligonal.getPath().getArray());   

                drawingManager.setMap(map);
                google.maps.event.addListener(drawingManager, 'overlaycomplete', function (event) {
                    GeoPoligonal = event.overlay;
                    GeoPoligonal.type = event.type;
                    if (event.type == google.maps.drawing.OverlayType.POLYGON) {
                        try {
                            overlayClickListener(event.overlay);                            
                            drawingManager.setOptions({
                                drawingMode: null,
                                drawingControl: false
                            });
                            centerPoligono(event.overlay.getPath().getArray());
                            GeoPoligonal.setEditable(true);
                        } catch (ess) {
                        }
                    }
                });

                google.maps.event.addListener(drawingManager, 'bounds_changed', function (event) {
                    GeoPoligonal = event.overlay;
                    GeoPoligonal.type = event.type;
					
                    if (event.type == google.maps.drawing.OverlayType.POLYGON) {
                        try {
                            overlayClickListener(event.overlay);
                            drawingManager.setOptions({
                                drawingMode: null,
                                drawingControl: false
                            });
                            centerPoligono(event.overlay.getPath().getArray());
                            GeoPoligonal.setEditable(true);

                            marker.setVisible(true);
                        } catch (ess) {
                            //console.log(ess)
                        }
                    }
                });
                
                
                
function overlayClickListener(overlay) {
    google.maps.event.addListener(overlay, "mouseup", function (event) {
        centerPoligono(overlay.getPath().getArray());
    });
    google.maps.event.addListener(overlay, "bounds_changed", function (event) {
        centerPoligono(overlay.getPath().getArray());
    });
}



function centerPoligono(arreglo) {
	$("#txtArea").val(Math.round(google.maps.geometry.spherical.computeArea(GeoPoligonal.getPath().getArray())*100)/100);
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
