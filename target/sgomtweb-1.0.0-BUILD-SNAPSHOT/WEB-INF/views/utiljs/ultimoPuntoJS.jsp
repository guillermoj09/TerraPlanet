
			function load_vehiculos(refresco)
			{

			try	{
				var ran=Math.floor(Math.random()*110)
				if(refresco!="on"){
				bounds = new google.maps.LatLngBounds();
				var infowindow = new google.maps.InfoWindow();
				}
				$.ajax({
							url: "https://gps.samtech.cl/maps/ajax_listadoflotanew.asp?faena=Spence&sw=0&us=<%=usuario.getUsername()%>&random="+ran,
							async:true,
							beforeSend: function(objeto)
							{$('#loading').fadeIn("slow"); },//antes de enviar
							complete: function(objeto, exito){
								if(exito=="success"){}//finalizo Proceso
							},
							contentType: "application/x-www-form-urlencoded",
							dataType: "html",
							error: function(objeto, quepaso, otroobj){
							load_vehiculos('on');
							//alert(quepaso)
							},
							global: true,
							ifModified: false,
							processData:true,
							success: function(datos){
							var ve=datos.split('@@@')
							var cantidad=ve.length-2
							var insertar=''
							var insertar3=''
							clearOverlays();
							$('#panel-tab-vehiculos').html('');
							$('#div_hora').html('<img src="https://gps.samtech.cl/maps/images/icon.jpg">&nbsp;&nbsp;<strong>Listado Vehiculos ('+formatDate(new Date())+')</strong>');

							for (i=0;i<=cantidad;i++)
							{var veh=ve[i].split(';')
							createMarker(veh[0],veh[1],veh[2],veh[4],veh[3],veh[5],veh[6],veh[7],i,veh[8],veh[9],veh[10],veh[11],refresco,veh[14],veh[15],veh[16],veh[17]);
							if (veh[3]==0){
							insertar=insertar+'<font size=1 color=blue><a href=# style="color:gray" onclick=ver_Marcador("'+veh[0]+'","'+veh[1]+'","'+i+'")>'+veh[2]+' ('+veh[9]+') - '+veh[3]+' Kmh - '+veh[6]+'</a></font><br>'
								}
								else{
									if (veh[3]<90){
							insertar=insertar+'<font size=2 color=blue><a href=# style="color:blue" onclick=ver_Marcador("'+veh[0]+'","'+veh[1]+'","'+i+'")>'+veh[2]+' ('+veh[9]+') - '+veh[3]+' Kmh - '+veh[6]+'</a></font><br>'
								}
								else{

							insertar=insertar+'<font size=2 color=red><a href=# style="color:red" onclick=ver_Marcador("'+veh[0]+'","'+veh[1]+'","'+i+'")>'+veh[2]+' ('+veh[9]+') - '+veh[3]+' Kmh - '+veh[6]+'</a></font><br>'
							}


							}

							}

							if(refresco!="on"){
								map.fitBounds(bounds);
							}

							$('#panel-tab-vehiculos').html(insertar);

							$('#loading').fadeOut("fast");

							if($.trim($('#txt_busca_patente').val())!=""){ 
	 							$("#panel-tab-vehiculos a:contains('"+$('#txt_busca_patente').val()+"')").css('background-color','yellow')							    
							}
							
							},
							timeout: 7200,
							type: "GET"
							});
					}
					catch(err){
					alert(err.message)
					//load_vehiculos();
					}


			}

            function createMarker(lat, lon, patente,imagen,spd,fecha,chofer,ubicacion,i,fono,num_interno,geo_cerca,tercero,refresco,geocoding,alarma,est_mov,hdg){

            	
				lat=parseFloat(lat);lon=parseFloat(lon);
				var icono = new google.maps.MarkerImage(imagen,null,null,  new google.maps.Point(8,29))
				//var icono = new google.maps.MarkerImage(imagen,null,null,  new google.maps.Point(8,30))
                var myLatlng = new google.maps.LatLng(lat, lon);
                marker = new google.maps.Marker({
                    position: myLatlng,
                    map: map,
					draggable:false,
                    title:patente+" - "+spd+" Kmh : "+chofer
					,icon: icono
                	})
				if (alarma=='') {	
				var contentString = '<div style="font-size:10px ;font-family:Verdana, Geneva, sans-serif; ">'+
				'<b>Conductor: </b>'+chofer+'<br/>'+
				'<b>Telefono: </b>'+fono+'<br/>'+
				'<b>Patente: </b>'+patente+'<br/>'+
				'<b>Nº Int.: </b>'+num_interno+'<br/>'+
				'<b>Tercero: </b>'+tercero+'<br/>'+
				'<b>Faena: </b>Spence<br/>'+
				'<b>Estado: </b>'+est_mov+'<br/>'+
				'<b>Velocidad: </b>'+spd+' Km/H<br/>'+
				'<b>Fecha: </b>'+fecha+'<br/>'+
				'<b>Ubicaci&oacute;n Cercana: </b>'+ubicacion+'<br/>'+
				'<b>Geocerca: </b><iframe src=geo4.asp?pat='+patente+' width="80%" height="12" scrolling="no" frameborder="0" marginheight="0" marginwidth="0" ></iframe><br/>'+
				'<b>Co</b>:&nbsp;&nbsp;'+ lat +', '+lon+', '+hdg+'º<br/>'+
				'<b>Geocoding: </b>:&nbsp;'+geocoding+'<br/>'+
				'<iframe src=streetv.asp?str='+ lat +','+lon +','+hdg+' width="100%" height="200" scrolling="no" frameborder="0" marginheight="0" marginwidth="0" ></iframe></div>';
				}else{
				var contentString = '<div style="font-size:10px ;font-family:Verdana, Geneva, sans-serif; ">'+
				'<b>Conductor: </b>'+chofer+'<br/>'+
				'<b>Telefono: </b>'+fono+'<br/>'+
				'<b>Patente: </b>'+patente+'<br/>'+
				'<b>Nº Int.: </b>'+num_interno+'<br/>'+
				'<b>Tercero: </b>'+tercero+'<br/>'+
				'<b>Faena: </b>Spence<br/>'+
				'<b>Estado: </b>'+est_mov+'<br/>'+
				'<b>Velocidad: </b>'+spd+' Km/H<br/>'+
				'<b>Fecha: </b>'+fecha+'<br/>'+
				'<b>Ubicaci&oacute;n Cercana: </b>'+ubicacion+'<br/>'+
				'<b>Geocerca: </b><iframe src=geo4.asp?pat='+patente+' width="80%" height="12" scrolling="no" frameborder="0" marginheight="0" marginwidth="0" ></iframe><br/>'+
				'<b>Co</b>:&nbsp;&nbsp;'+ lat +', '+lon+', '+hdg+'º<br/>'+
				'<b>Geocoding: </b>:&nbsp;'+geocoding+'<br/>'+
				'<b>Alarma: </b>:&nbsp;'+alarma+'<br/>'+
				'<iframe src=streetv.asp?str='+ lat +','+lon +','+hdg+' width="100%" height="200" scrolling="no" frameborder="0" marginheight="0" marginwidth="0" ></iframe></div>';
				}
                
                if(refresco!="on"){
					bounds.extend(marker.position);
					}
					
					markersArray.push(marker);
					infoWindowsArray[i] = new google.maps.InfoWindow({content: contentString,position: myLatlng,maxWidth:350,pixelOffset : new google.maps.Size(-10,20)});
					google.maps.event.addListener(marker, 'click', function() {ver_Marcador(lat,lon,i)});

				


            }
            
            
            
            


            function TileToQuadKey(x, y, zoom) {
                var quad = "";
                for (var i = zoom; i > 0; i--) {
                    var mask = 1 << (i - 1);
                    var cell = 0;
                    if ((x & mask) != 0) cell++;
                    if ((y & mask) != 0) cell += 2;
                    quad += cell;
                }
                return quad;
            }

            function clearOverlays() {
              if (markersArray) {
                for (i in markersArray) {
                  markersArray[i].setMap(null);
                }
              }
              markersArray = [];
              infoWindowsArray=[];
            }

            function ver_Marcador(lat,lon,i)
            {	var myLatlng = new google.maps.LatLng(lat, lon);


            		try	{map.setCenter( myLatlng );}
            		catch(err){}



            	var largo=infoWindowsArray.length;
            	largo=largo-1
            	for (a=0;a<=largo;a++)
            	{
            	try	{infoWindowsArray[a].close();}
            	catch(err)
            	{}
            	}


            infoWindowsArray[i].open(map,markersArray[i])
            }

          
          