<script>

	var gpsD = 0;
	var gpsR = 0;
 	var gpsN = 0;
 	
 	var gpsDF = 0;
	var gpsRF = 0;
 	var gpsNF = 0;
 	
 	var gUp; var g2Up; var g3Up;
 	
 	var gpsfechaAjaxS;
	
	var tableGPS = $('#tablagps').DataTable({
		"language": {
            "url": URLactual + "/sgomtweb/resources/datetableespanil.json"
        },	
        "order": [[ 2, "desc" ]],
		"autoWidth": false,		
		 dom: '<"html5buttons"xºB>lTfgitp',
         buttons: [
             {extend: 'copy'},
             {extend: 'csv'},
             {extend: 'excel', title: 'ReporteDatosGPS'},
             {extend: 'pdf', title: 'ReporteDatosGPS'},

             {extend: 'print',
              customize: function (win){
                     $(win.document.body).addClass('white-bg');
                     $(win.document.body).css('font-size', '10px');

                     $(win.document.body).find('table')
                             .addClass('compact')
                             .css('font-size', 'inherit');
             }
             }
         ],
		"ajax": {
			url: "ultimaposicionajaxd.json",    	 
			type: "POST",
	    	data: {
	    	rutcliente : "${usuario.clienterut}",
	    	rut : "${usuario.rut}",
	    	tipo : "gps"
	    	},
	    	dataType: "json",
			success :  function(data){
									
					$.each(data, function(ind, obj){
						
						tableGPS.row.add([
							obj.patente,				
							obj.nroInterno,
							obj.descestado,
							obj.fechaS,
							obj.fechaSdb,
							obj.rutGeocerca,
						]).draw();
						
					
				if(obj.estado == 1){
						
						gpsD++;
						
					}
					if(obj.estado == 2){
						
						gpsR++;
						
					}
					if(obj.estado == 3){

					gpsN++;

					}
					
					gpsfechaAjaxS = obj.fechaAjaxS;
					
				}); 	//ACUALIZACION CON LAS FLECHAS
					if(gpsD > gpsDF){
						if(gpsDF == 0){
							$("#gpsD").html(parseInt(gpsD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
						}else {
						$("#gpsD").html(parseInt(gpsD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");
						gUp = "U";
						}
					}
					if(gpsD == gpsDF){
						if(gUp == "U"){
							$("#gpsD").html(parseInt(gpsD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");	
						}else if(gUp == "D"){
							$("#gpsD").html(parseInt(gpsD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");
						} else {
							$("#gpsD").html(parseInt(gpsD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
						}							
					}
					if(gpsD < gpsDF){						
						$("#gpsD").html(parseInt(gpsD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");
						gUp = "D";
					}
					
					//##################################
					
					if(gpsR > gpsRF){	
						if(gpsRF == 0){
							$("#gpsR").html(parseInt(gpsR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
						}else {
							$("#gpsR").html(parseInt(gpsR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");
							g2Up = "U";
						}
					}
					if(gpsR == gpsRF){	
						if(g2Up == "U"){
							$("#gpsR").html(parseInt(gpsR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");	
						}else if(m2Up == "D"){
							$("#gpsR").html(parseInt(gpsR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");
						} else {
							$("#gpsR").html(parseInt(gpsR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
						}			
										
					}
					if(gpsR < gpsRF){						
						$("#gpsR").html(parseInt(gpsR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");
						g2Up = "D";
					}
					
					//##################################
					
					if(gpsN > gpsNF){
						if(gpsNF == 0){
							$("#gpsN").html(parseInt(gpsN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
						}else {
							$("#gpsN").html(parseInt(gpsN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");
							g3Up = "U";
						}
													
					}
					if(gpsN == gpsNF){
						if(g3Up == "U"){
							$("#gpsN").html(parseInt(gpsN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");	
						}else if(m3Up == "D"){
							$("#gpsN").html(parseInt(gpsN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");
						} else {
							$("#gpsN").html(parseInt(gpsN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
						}		
												
					}
					if(gpsN < gpsNF){						
						$("#gpsN").html(parseInt(gpsN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");
						g3Up = "D";
						
					}
										
					gpsDF = gpsD;
					gpsRF = gpsR;
					gpsNF = gpsN;
					
					
					$("#gpsfechaAjaxS").html(gpsfechaAjaxS)+"<small style='color: grey; font-size :12px;'>&nbsp; <i class='fa fa-clock-o'></i> </small>";
					//$("#maquinasR").html(parseInt(maquinasR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-warning'></i> </small>");
					//$("#maquinasN").html(parseInt(maquinasN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-minus-circle'></i> </small>");
					
				
			} // fin ajax
		},
	});
	
	
	//DB
	
	var dbD = 0;
	var dbR = 0;
 	var dbN = 0;
 	
 	var dbDF = 0;
	var dbRF = 0;
 	var dbNF = 0;
 	
 	var dUp; var d2Up; var d3Up;
 	
 	var dbfechaAjaxS;
	
	var tableDB = $('#tabledb').DataTable({
		"language": {
            "url": URLactual + "/sgomtweb/resources/datetableespanil.json"
        },	
        "order": [[ 2, "desc" ]],
		"autoWidth": false,		
		 dom: '<"html5buttons"xºB>lTfgitp',
         buttons: [
             {extend: 'copy'},
             {extend: 'csv'},
             {extend: 'excel', title: 'ReporteDatosDB'},
             {extend: 'pdf', title: 'ReporteDatosDB'},

             {extend: 'print',
              customize: function (win){
                     $(win.document.body).addClass('white-bg');
                     $(win.document.body).css('font-size', '10px');

                     $(win.document.body).find('table')
                             .addClass('compact')
                             .css('font-size', 'inherit');
             }
             }
         ],
		"ajax": {
			url: "ultimaposicionajaxd.json",    	 
			type: "POST",
	    	data: {
	    	rutcliente : "${usuario.clienterut}",
	    	rut : "${usuario.rut}",
	    	tipo : "db"
	    	},
	    	dataType: "json",
			success :  function(data){
									
					$.each(data, function(ind, obj){
						
						tableDB.row.add([
							obj.patente,				
							obj.nroInterno,
							obj.descestadodb,
							obj.fechaS,
							obj.fechaSdb,
							obj.rutGeocerca,
						]).draw();
						
					
				if(obj.estadodb == 1){
						
						dbD++;
						
					}
					if(obj.estadodb == 2){
						
						dbR++;
						
					}
					if(obj.estadodb == 3){

						dbN++;

					}
					
					dbfechaAjaxS = obj.fechaAjaxS;
					
				}); 	//ACUALIZACION CON LAS FLECHAS
					if(dbD > dbDF){
						if(dbDF == 0){
							$("#dbD").html(parseInt(dbD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
						}else {
						$("#dbD").html(parseInt(dbD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");
						dUp = "U";
						}
					}
					if(dbD == dbDF){
						if(dUp == "U"){
							$("#dbD").html(parseInt(dbD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");	
						}else if(gUp == "D"){
							$("#dbD").html(parseInt(dbD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");
						} else {
							$("#dbD").html(parseInt(dbD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
						}							
					}
					if(dbD < dbDF){						
						$("#dbD").html(parseInt(gpsD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");
						dUp = "D";
					}
					
					//##################################
					
					if(dbR > dbRF){	
						if(dbRF == 0){
							$("#dbR").html(parseInt(dbR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
						}else {
							$("#dbR").html(parseInt(dbR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");
							d2Up = "U";
						}
					}
					if(dbR == dbRF){	
						if(d2Up == "U"){
							$("#dbR").html(parseInt(dbR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");	
						}else if(m2Up == "D"){
							$("#dbR").html(parseInt(dbR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");
						} else {
							$("#dbR").html(parseInt(dbR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
						}			
										
					}
					if(dbR < dbRF){						
						$("#dbR").html(parseInt(dbR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");
						d2Up = "D";
					}
					
					//##################################
					
					if(dbN > dbNF){
						if(dbNF == 0){
							$("#dbN").html(parseInt(dbN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
						}else {
							$("#dbN").html(parseInt(dbN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");
							d3Up = "U";
						}
													
					}
					if(dbN == dbNF){
						if(d3Up == "U"){
							$("#dbN").html(parseInt(dbN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");	
						}else if(m3Up == "D"){
							$("#dbN").html(parseInt(dbN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");
						} else {
							$("#dbN").html(parseInt(dbN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
						}		
												
					}
					if(dbN < dbNF){						
						$("#dbN").html(parseInt(dbN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");
						d3Up = "D";
						
					}
										
					dbDF = dbD;
					dbRF = dbR;
					dbNF = dbN;
					
					
					$("#dbfechaAjaxS").html(dbfechaAjaxS)+"<small style='color: grey; font-size :12px;'>&nbsp; <i class='fa fa-clock-o'></i> </small>";
					//$("#maquinasR").html(parseInt(maquinasR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-warning'></i> </small>");
					//$("#maquinasN").html(parseInt(maquinasN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-minus-circle'></i> </small>");
					
				
			} // fin ajax
		},
	});
	
	
		//refrescar las tabla
	setInterval( function () {
		tableGPS.clear();
		 //datatable.rows.add(newDataArray);  por si necesita agregar nueva columna
		gpsD = 0;
		gpsR = 0;
	 	gpsN = 0;
	 	tableGPS.ajax.reload();
	}, 55000 );
		
	//refrescar las tabla
	setInterval( function () {
		tableDB.clear();
		 //datatable.rows.add(newDataArray);  por si necesita agregar nueva columna
		dbD = 0;
		dbR = 0;
	 	dbN = 0;
	 	tableDB.ajax.reload();
	}, 60000 );
	
	
</script>