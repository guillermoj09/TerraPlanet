
<script>
	function tableControl(controlDiv) {

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
		controlUI.title = 'Click para mostrar o ocultar Tabla';

		controlDiv.appendChild(controlUI);

		// Set CSS for the control interior.
		var controlText = document.createElement('div');
		//controlText.style.color = 'rgb(25,25,25)';
		//controlText.style.fontFamily = 'Roboto,Arial,sans-serif';          
		controlText.style.fontSize = '16px';
		controlText.style.lineHeight = '38px';
		controlText.style.paddingLeft = '12px';
		controlText.style.paddingRight = '12px';
		controlText.innerHTML = '<i class="fa fa-list" title="tabla"></i>';
		controlUI.appendChild(controlText);

		//Setup the click event listeners: simply set the map.     
		
		controlText.addEventListener('click', function() {

			//var el = document.getElementById('legend');
			var aux = $("#map_content").attr('value');

			if (aux == "12") {
				$("#sidebar").attr("class", "col-sm-12  col-md-3 collapse in show ");
				$("#map_content").attr("class", "col-md-9 col-xs-11");
				$("#map_content").attr("value", "9");
			} else {
				$("#sidebar").attr("class", "col-md-0 collapse in hide ");
				$("#map_content").attr("class", "col-md-12 col-xs-11");
				$("#map_content").attr("value", "12");
			}
			
			for (var j = 0; j < markers.length; j++) {
				
				var marker = markers[j];
				var idnumero = "equipos" + j;
				var equipos = document.getElementById(idnumero);
				equipos.addEventListener('click', (function(marker, j) {
					return function() {
						infoWindow.setContent(infoWindowContent[j]);
						infoWindow.open(map, marker);
					}
				})(marker, j));
				

			}//fin for 

		});


	}

	var geoControlDiv = document.createElement('div');
	var geoControl = new tableControl(geoControlDiv);
	geoControlDiv.index = 1;
	map.controls[google.maps.ControlPosition.LEFT_CENTER].push(geoControlDiv);
	
	/*if (el.style.display == 'none') {
	console.log('entro')*/

	//el.style.display = 'block';
	//descomentar si se quiere seguir usando el dialog
	/*  $( "#legend" ).dialog({
	  		open: function(event, ui) {
			//$(this).css({'max-height': 500, 'overflow-y': 'auto'});
			$(this).parent().children().children('.ui-dialog-titlebar-close').hide();
			$(this).parent().children().children('.ui-dialog-title').css({'color': '#676a6c', 'font-family': 'sans-serif'});
			  
			},
			 title: 'Listado Patentes Mapa',
			//autoOpen:false,
			//modal: true,
			resizable: true,
			draggable: true,
			width: '690',
			height: '590',
			closeOnEscape: true,
			//position: 'top' 
			position: { my: "buttom", at: "buttom", of:  $('#map') },          
	 	});*/

	/*} else {
		console.log('salio')
		//el.style.display = 'none';

		//descomentar si se quiere seguir usando el dialog
		//$('#legend').dialog('close')

	}*/

	//aqui va codigo nuevo, ventana emergente que se sincronice el id equipo del mapa y funcione el addlistener
	/*myWindow = window.open("ultimaposicionvehiculopopup2","RUTA","width=800,height=650,scrollbars=NO,resizable=YES,class=lbOn,location=0,status=0, directories=0, toolbar=0, titlebar=0");
	doc = myWindow.document;
	doc.open("ultimaposicionvehiculopopup2","RUTA","width=800,height=650,scrollbars=NO,resizable=YES,class=lbOn,location=0,status=0, directories=0, toolbar=0, titlebar=0");		
	console.log(doc)*/

	//myWindow = window.open(" ","Listado Mapa ","top=100,left=300,width=800,height=600,scrollbars=NO,resizable=YES,class=lbOn,location=0,status=0, directories=0, toolbar=0, titlebar=0" );
	/*myWindow = window.open("Listado Mapa ","Listado Mapa ","scrollbars=Yes,resizable=YES" );    
	
	doc = myWindow.document;
	doc.open();       	
	//creamos la pagina popup
	doc.write("");
	doc.write("\<html\>\<head\>");
	doc.write("\<link href=\'resources/css/stylepopup.css\' rel=\'stylesheet\'\>");       
	
	doc.write("\<body class=\'top-navigation gray-bg\'\>");
	doc.write("\<div class=\'wrapper\'\>		\<div id='\page-wrapper\' class=\'gray-bg\'\>		   \<div class=\'wrapper wrapper-content animated fadeInRight\'\>");
	//doc.write("\<div class=\'row\'\>		\<div class=\'col-lg-12\'\>		 \<div class=\'ibox-content\'\>	 \<div class=\'table-responsive\'\> ");
	doc.write(legend.innerHTML);
	doc.write("\</div\>		\</div\>	\</div\>");
	doc.write("\</div\>		\</div\>	\</div\> \</div\>");
	
	var testlog = "\<script\> console.log(\'hola\') \</script\>";
	var urllibes =  "\<script\> var URLactual =window.location.protocol +'////'+ window.location.host \</script\>";		
	var jquejs = "\<script src='resources/inspinia_v2.9/FullVersion/js/jquery-3.1.1.min.js'\>\</script\>";
	var jquejs2 = "\<script src='resources/inspinia_v2.9/FullVersion/js/bootstrap.min.js'\>\</script\>";
	var libreriajs = "\<script src='resources/inspinia_v2.9/FullVersion/js/plugins/dataTables/datatables.min.js'\>\</script\>";
	var libreriajs2 = "\<script src='resources/inspinia_v2.9/FullVersion/js/plugins/dataTables/dataTables.bootstrap4.min.js'\>\</script\>"		
	//var datadablestring = "\<script\> $(\'#ultimos\').DataTable({ \'language\': { \'url\': URLactual + \'/sgomtweb/resources/datetableespanil.json\'	 }, \'order\': [[ 2, \'desc\' ]], \'autoWidth\': false,	}); \</script\>";
	
	doc.write( testlog );
	doc.write( urllibes );
	doc.write( jquejs );
	doc.write( jquejs2 );
	doc.write( libreriajs );
	doc.write( libreriajs2 );
	doc.write( datadablestring );
	 */
	//relacionar

	//test para ventana emergente  (cambiarlo a pagina aparte que se sincronice con el mismo id) 
	//este se llena dinamico no es viable ni escalable, ya que se debe pasar los js y css de inspinia para que funcione el popup
	//window.open("ultimaposicionvehiculopopup?patente=${rform.patente}&fechain=${rform.fechaDesde} ${rform.horaDesde}&fechafin=${rform.fechaHasta} ${rform.horaHasta}&velocidad=${rform.velocidad}","RUTA","width=800,height=650,scrollbars=NO,resizable=YES,class=lbOn,location=0,status=0, directories=0, toolbar=0, titlebar=0");
	// myWindow = window.open(" ","Listado Mapa ","top=100,left=300,width=800,height=600,scrollbars=NO,resizable=YES,class=lbOn,location=0,status=0, directories=0, toolbar=0, titlebar=0" );
	/*myWindow = window.open("Listado Mapa ","Listado Mapa ","width=800,height=750,scrollbars=Yes,resizable=YES" );        	 
	doc = myWindow.document;
	doc.open();
	//creamos la pagina popup
	doc.write("\<html\>\<head\>");
	doc.write("\<link href=\'resources/css/stylepopup.css\' rel=\'stylesheet\'\>");
	doc.write("\<html\>\<head\>");
	
	
	doc.write(legend.innerHTML);
	
	var testlog = "\<script\> console.log(\'hola\') \</script\>";
	var urllibes =  "\<script\> var URLactual =window.location.protocol +'////'+ window.location.host \</script\>";		
	var jquejs = "\<script src='resources/inspinia_v2.9/FullVersion/js/jquery-3.1.1.min.js'\>\</script\>";
	var jquejs2 = "\<script src='resources/inspinia_v2.9/FullVersion/js/bootstrap.min.js'\>\</script\>";
	var libreriajs = "\<script src='resources/inspinia_v2.9/FullVersion/js/plugins/dataTables/datatables.min.js'\>\</script\>";
	var libreriajs2 = "\<script src='resources/inspinia_v2.9/FullVersion/js/plugins/dataTables/dataTables.bootstrap4.min.js'\>\</script\>"		
	var datadablestring = "\<script\> $(\'#ultimos\').DataTable({ \'language\': { \'url\': URLactual + \'/sgomtweb/resources/datetableespanil.json\'	 }, \'order\': [[ 2, \'desc\' ]], \'autoWidth\': false,	}); \</script\>";
	
	doc.write( testlog );
	doc.write( urllibes );
	doc.write( jquejs );
	doc.write( jquejs2 );
	doc.write( libreriajs );
	doc.write( libreriajs2 );
	doc.write( datadablestring );

	//doc.close();    
	 */
</script>

