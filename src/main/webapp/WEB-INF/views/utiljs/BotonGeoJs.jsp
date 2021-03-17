   function GeoControl(controlDiv, Poligonos) {

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
          controlUI.title = 'Click para mostrar o ocultar GEO';
          controlDiv.appendChild(controlUI);

          // Set CSS for the control interior.
          var controlText = document.createElement('div');
          //controlText.style.color = 'rgb(25,25,25)';
          //controlText.style.fontFamily = 'Roboto,Arial,sans-serif';
          controlText.style.fontSize = '16px';
          controlText.style.lineHeight = '38px';
          controlText.style.paddingLeft = '5px';
          controlText.style.paddingRight = '5px';
          controlText.innerHTML = 'Geo';
          controlUI.appendChild(controlText);
          
         
          //Setup the click event listeners: simply set the map.
         controlUI.addEventListener('click', function() {

        	 if(Poligonos.length > 0){
                 if(Poligonos[0].getMap() == null){ 
                  for (var i = 0; i <= Poligonos.length-1; i++) {
                   Poligonos[i].setMap(map);
                }
                cambia = cambia + 1;
              } else {
                  for (var i = 0; i <= Poligonos.length-1; i++) {
                   Poligonos[i].setMap(null);
                }
              }      
                }

        	 
          });
          
      }

 var geoControlDiv = document.createElement('div');
        var geoControl = new GeoControl(geoControlDiv, Poligonos);

        geoControlDiv.index = 1;
        map.controls[google.maps.ControlPosition.LEFT_CENTER].push(geoControlDiv);     
                 