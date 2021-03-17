<script>

//Solo test ->          
/*1. Dashboard con listado de flota, fecha de ultima transmisión y registro de lagunas de información */
           
  /*$(document).ready(function() {
	    $('#camiones').DataTable( {
	        "processing": true,
	        "serverSide": true,
	        "ajax": {
	        	url: "ultimaposicionajax.json",
	        	type: "POST",
	        	data: {
	        	rutcliente : "${usuario.clienterut}"
	        	},
	            "dataType": "json",
	            dataSrc:"",
	        }
	    } );
	} );  */
	
	$.fn.dataTable.ext.errMode = 'throw';
	
	var URLactual =window.location.protocol +"//"+ window.location.host;
	
	 
	 	var i2 = 0;
	 	
	 	var camionesD = 0;
	 	var camionesR = 0;
	 	var camionesN = 0;
	 	
	 	var camionesDF = 0;
	 	var camionesRF = 0;
	 	var camionesNF = 0;
	 	
	 	var cUp; var c2Up; var c3Up;
	 	
		var camionesfechaAjaxS;
	
	var tableCamiones = $('#camiones').DataTable({
		"language": {
            "url": URLactual + "/sgomtweb/resources/datetableespanil.json"
        },
        "order": [[ 2, "desc" ]],
        "footerCallback": function ( row, data, start, end, display ) {
            var api = this.api(), data;
            
           //para desarrollar totales y calculos
    	i2++;
         
       },
		"autoWidth": false,	
		 dom: '<"html5buttons"xºB>lTfgitp',
         buttons: [
             {extend: 'copy'},
             {extend: 'csv'},
             {extend: 'excel', title: 'ReporteCamiones'},
             {extend: 'pdf', title: 'ReporteCamiones'},

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
			url: "ultimaposicioncategoriaajax.json",    	 
			type: "POST",
	    	data: {
	    	rutcliente : "${usuario.clienterut}",
	    	rut : "${usuario.rut}",
	    	categoria : "c"
	    	},
	    	dataType: "json",
			success :  function(data){
									
					$.each(data, function(ind, obj){
						
						tableCamiones.row.add([
							obj.patente,				
							obj.nroInterno,
							obj.descestado,
							obj.fechaS,
							obj.fechaSdb,
							obj.rutGeocerca,
							,
						]).draw(); //node();
					
						if(obj.estado == 1){
							
							camionesD++;
						
														
						}
						if(obj.estado == 2){
							
							camionesR++;
							
							
						}
						if(obj.estado == 3){
	
						camionesN++;
						
	
						}
						
						camionesfechaAjaxS = obj.fechaAjaxS;
						
					});// fin for
					
					if(camionesD > camionesDF){
						if(camionesDF == 0){
							$("#camionesD").html(parseInt(camionesD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
						}else {
						$("#camionesD").html(parseInt(camionesD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");
						cUp = "U";
						}
					}
					if(camionesD == camionesDF){
						if(cUp == "U"){
							$("#camionesD").html(parseInt(camionesD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");	
						}else if(cUp == "D"){
							$("#camionesD").html(parseInt(camionesD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");
						} else {
							$("#camionesD").html(parseInt(camionesD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
						}							
					}
					if(camionesD < camionesDF){						
						$("#camionesD").html(parseInt(camionesD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");
						cUp = "D";
					}
					
					//##################################
					
					if(camionesR > camionesRF){	
						if(camionesRF == 0){
							$("#camionesR").html(parseInt(camionesR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
						}else {
							$("#camionesR").html(parseInt(camionesR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");
							c2Up = "U";
						}
					}
					if(camionesR == camionesRF){	
						if(c2Up == "U"){
							$("#camionesR").html(parseInt(camionesR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");	
						}else if(c2Up == "D"){
							$("#camionesR").html(parseInt(camionesR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");
						} else {
							$("#camionesR").html(parseInt(camionesR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
						}			
										
					}
					if(camionesR < camionesRF){						
						$("#camionesR").html(parseInt(camionesR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");
						c2Up = "D";
					}
					
					//##################################
					
					if(camionesN > camionesNF){
						if(camionesNF == 0){
							$("#camionesN").html(parseInt(camionesN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
						}else {
							$("#camionesN").html(parseInt(camionesN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");
							c3Up = "U";
						}
													
					}
					if(camionesN == camionesNF){
						if(c3Up == "U"){
							$("#camionesN").html(parseInt(camionesN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");	
						}else if(c3Up == "D"){
							$("#camionesN").html(parseInt(camionesN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");
						} else {
							$("#camionesN").html(parseInt(camionesN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
						}		
												
					}
					if(camionesN < camionesNF){						
						$("#camionesN").html(parseInt(camionesN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");
						c3Up = "D";
						
					}
										
					camionesDF = camionesD;
					camionesRF = camionesR;
					camionesNF = camionesN;
										
					$("#camionesfechaAjaxS").html(camionesfechaAjaxS)+"<small style='color: grey; font-size :12px;'>&nbsp; <i class='fa fa-clock-o'></i> </small>";
					//$("#camionesD").html(parseInt(camionesD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
					//$("#camionesR").html(parseInt(camionesR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-warning'></i> </small>");
					//$("#camionesN").html(parseInt(camionesN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-minus-circle'></i> </small>");
										
					
			},
		},
	});
	
	var dumperD = 0;
	var dumperR = 0;
 	var dumperN = 0;
 	
 	var dumperDF = 0;
	var dumperRF = 0;
 	var dumperNF = 0;
 	
 	var dUp; var d2Up; var d3Up;
 	
 	var dumperfechaAjaxS;
	
	var tableDumper = $('#dumper').DataTable({
		"language": {
            "url": URLactual + "/sgomtweb/resources/datetableespanil.json"
        },
        "order": [[ 2, "desc" ]],
		"autoWidth": false,	
		 dom: '<"html5buttons"xºB>lTfgitp',
         buttons: [
             {extend: 'copy'},
             {extend: 'csv'},
             {extend: 'excel', title: 'ReporteDumper'},
             {extend: 'pdf', title: 'ReporteDumper'},

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
			url: "ultimaposicioncategoriaajax.json",    	 
			type: "POST",
	    	data: {
	    	rutcliente : "${usuario.clienterut}",
	    	rut : "${usuario.rut}",
	    	categoria : "d"
	    	},
	    	dataType: "json",
			success :  function(data){
									
					$.each(data, function(ind, obj){
						
						tableDumper.row.add([
							obj.patente,				
							obj.nroInterno,
							obj.descestado,
							obj.fechaS,
							obj.fechaSdb,
							obj.rutGeocerca,
						]).draw();
					
					
					if(obj.estado == 1){
						
						dumperD++;
						
					}
					if(obj.estado == 2){
						
						dumperR++;
						
					}
					if(obj.estado == 3){

					dumperN++;

					}
					
					dumperfechaAjaxS = obj.fechaAjaxS;
					
				});
					
					/*if(dumperD > dumperDF){						
						$("#dumperD").html(parseInt(dumperD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");							
					}
					if(dumperD == dumperDF){						
						$("#dumperD").html(parseInt(dumperD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");						
					}
					if(dumperD < dumperDF){						
						$("#dumperD").html(parseInt(dumperD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");						
					}
					
					if(dumperR > dumperRF){						
						$("#dumperR").html(parseInt(dumperR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");							
					}
					if(dumperR == dumperRF){						
						$("#dumperR").html(parseInt(dumperR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");						
					}
					if(dumperR < dumperRF){						
						$("#dumperR").html(parseInt(dumperR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");						
					}
					
					if(dumperN > dumperNF){						
						$("#dumperN").html(parseInt(dumperN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");							
					}
					if(dumperN == dumperNF){						
						$("#dumperN").html(parseInt(dumperN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");						
					}
					if(dumperN < dumperNF){						
						$("#dumperN").html(parseInt(dumperN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");						
					}*/
					//ACUALIZACION CON LAS FLECHAS
					if(dumperD > dumperDF){
						if(dumperDF == 0){
							$("#dumperD").html(parseInt(dumperD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
						}else {
						$("#dumperD").html(parseInt(dumperD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");
						dUp = "U";
						}
					}
					if(dumperD == dumperDF){
						if(dUp == "U"){
							$("#dumperD").html(parseInt(dumperD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");	
						}else if(dUp == "D"){
							$("#dumperD").html(parseInt(dumperD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");
						} else {
							$("#dumperD").html(parseInt(dumperD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
						}							
					}
					if(dumperD < dumperDF){						
						$("#dumperD").html(parseInt(dumperD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");
						dUp = "D";
					}
					
					//##################################
					
					if(dumperR > dumperRF){	
						if(dumperRF == 0){
							$("#dumperR").html(parseInt(dumperR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
						}else {
							$("#dumperR").html(parseInt(dumperR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");
							d2Up = "U";
						}
					}
					if(dumperR == dumperRF){	
						if(d2Up == "U"){
							$("#dumperR").html(parseInt(dumperR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");	
						}else if(d2Up == "D"){
							$("#dumperR").html(parseInt(dumperR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");
						} else {
							$("#dumperR").html(parseInt(dumperR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
						}			
										
					}
					if(dumperR < dumperRF){						
						$("#dumperR").html(parseInt(dumperR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");
						d2Up = "D";
					}
					
					//##################################
					
					if(dumperN > dumperNF){
						if(dumperNF == 0){
							$("#dumperN").html(parseInt(dumperN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
						}else {
							$("#dumperN").html(parseInt(dumperN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");
							d3Up = "U";
						}
													
					}
					if(dumperN == dumperNF){
						if(d3Up == "U"){
							$("#dumperN").html(parseInt(dumperN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");	
						}else if(d3Up == "D"){
							$("#dumperN").html(parseInt(dumperN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");
						} else {
							$("#dumperN").html(parseInt(dumperN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
						}		
												
					}
					if(dumperN < dumperNF){						
						$("#dumperN").html(parseInt(dumperN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");
						d3Up = "D";
						
					}
										
					dumperDF = dumperD;
					dumperRF = dumperR;
					dumperNF = dumperN;
						
					$("#dumperfechaAjaxS").html(dumperfechaAjaxS)+"<small style='color: grey; font-size :12px;'>&nbsp; <i class='fa fa-clock-o'></i> </small>";					
				//$("#dumperD").html(parseInt(dumperD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
				//$("#dumperR").html(parseInt(dumperR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-warning'></i> </small>");
				//$("#dumperN").html(parseInt(dumperN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-minus-circle'></i> </small>");
				
			}
		},
	});
	
	var maquinasD = 0;
	var maquinasR = 0;
 	var maquinasN = 0;
 	
 	var maquinasDF = 0;
	var maquinasRF = 0;
 	var maquinasNF = 0;
 	
 	var mUp; var m2Up; var m3Up;
 	
 	var maquinasfechaAjaxS;
	
	var tableMaquina = $('#maquinas').DataTable({
		"language": {
            "url": URLactual + "/sgomtweb/resources/datetableespanil.json"
        },	
        "order": [[ 2, "desc" ]],
		"autoWidth": false,	
		 dom: '<"html5buttons"xºB>lTfgitp',
         buttons: [
             {extend: 'copy'},
             {extend: 'csv'},
             {extend: 'excel', title: 'ReporteMaquinas'},
             {extend: 'pdf', title: 'ReporteMaquinas'},

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
			url: "ultimaposicioncategoriaajax.json",    	 
			type: "POST",
	    	data: {
	    	rutcliente : "${usuario.clienterut}",
	    	rut : "${usuario.rut}",
	    	categoria : "m"
	    	},
	    	dataType: "json",
			success :  function(data){
									
					$.each(data, function(ind, obj){
						
						tableMaquina.row.add([
							obj.patente,				
							obj.nroInterno,
							obj.descestado,
							obj.fechaS,
							obj.fechaSdb,
							obj.rutGeocerca,
						]).draw();
						
					
				if(obj.estado == 1){
						
						maquinasD++;
						
					}
					if(obj.estado == 2){
						
						maquinasR++;
						
					}
					if(obj.estado == 3){

					maquinasN++;

					}
					
					maquinasfechaAjaxS = obj.fechaAjaxS;
					
					
				}); //fin for
				
					/*if(maquinasD > maquinasDF){						
						$("#maquinasD").html(parseInt(maquinasD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");							
					}
					if(maquinasD == maquinasDF){						
						$("#maquinasD").html(parseInt(maquinasD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");						
					}
					if(maquinasD < maquinasDF){						
						$("#maquinasD").html(parseInt(maquinasD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");						
					}
					
					if(maquinasR > maquinasRF){						
						$("#maquinasR").html(parseInt(maquinasR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");							
					}
					if(maquinasR == maquinasRF){						
						$("#maquinasR").html(parseInt(maquinasR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");						
					}
					if(maquinasR < maquinasRF){						
						$("#maquinasR").html(parseInt(maquinasR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");						
					}
					
					if(maquinasN > maquinasNF){						
						$("#maquinasN").html(parseInt(maquinasN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");							
					}
					if(maquinasN == maquinasNF){						
						$("#maquinasN").html(parseInt(maquinasN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");						
					}
					if(maquinasN < maquinasNF){						
						$("#maquinasN").html(parseInt(maquinasN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");						
					}*/
					//ACUALIZACION CON LAS FLECHAS
					if(maquinasD > maquinasDF){
						if(maquinasDF == 0){
							$("#maquinasD").html(parseInt(maquinasD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
						}else {
						$("#maquinasD").html(parseInt(maquinasD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");
						mUp = "U";
						}
					}
					if(maquinasD == maquinasDF){
						if(mUp == "U"){
							$("#maquinasD").html(parseInt(maquinasD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");	
						}else if(mUp == "D"){
							$("#maquinasD").html(parseInt(maquinasD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");
						} else {
							$("#maquinasD").html(parseInt(maquinasD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
						}							
					}
					if(maquinasD < maquinasDF){						
						$("#maquinasD").html(parseInt(maquinasD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");
						mUp = "D";
					}
					
					//##################################
					
					if(maquinasR > maquinasRF){	
						if(maquinasRF == 0){
							$("#maquinasR").html(parseInt(maquinasR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
						}else {
							$("#maquinasR").html(parseInt(maquinasR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");
							m2Up = "U";
						}
					}
					if(maquinasR == maquinasRF){	
						if(m2Up == "U"){
							$("#maquinasR").html(parseInt(maquinasR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");	
						}else if(m2Up == "D"){
							$("#maquinasR").html(parseInt(maquinasR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");
						} else {
							$("#maquinasR").html(parseInt(maquinasR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
						}			
										
					}
					if(maquinasR < maquinasRF){						
						$("#maquinasR").html(parseInt(maquinasR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");
						m2Up = "D";
					}
					
					//##################################
					
					if(maquinasN > maquinasNF){
						if(maquinasNF == 0){
							$("#maquinasN").html(parseInt(maquinasN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
						}else {
							$("#maquinasN").html(parseInt(maquinasN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");
							m3Up = "U";
						}
													
					}
					if(maquinasN == maquinasNF){
						if(m3Up == "U"){
							$("#maquinasN").html(parseInt(maquinasN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-arrow-up'></i> </small>");	
						}else if(m3Up == "D"){
							$("#maquinasN").html(parseInt(maquinasN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");
						} else {
							$("#maquinasN").html(parseInt(maquinasN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
						}		
												
					}
					if(maquinasN < maquinasNF){						
						$("#maquinasN").html(parseInt(maquinasN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-arrow-down'></i> </small>");
						m3Up = "D";
						
					}
										
					maquinasDF = maquinasD;
					maquinasRF = maquinasR;
					maquinasNF = maquinasN;
									
					$("#maquinasfechaAjaxS").html(maquinasfechaAjaxS)+"<small style='color: grey; font-size :12px;'>&nbsp; <i class='fa fa-clock-o'></i> </small>";
					//$("#maquinasD").html(parseInt(maquinasD.toFixed(2))+"<small style='color: green; font-size :12px;'>&nbsp; <i class='fa fa-check'></i> </small>");
					//$("#maquinasR").html(parseInt(maquinasR.toFixed(2))+"<small style='color: orange; font-size :12px;'>&nbsp; <i class='fa fa-warning'></i> </small>");
					//$("#maquinasN").html(parseInt(maquinasN.toFixed(2))+"<small style='color: red; font-size :12px;'>&nbsp; <i class='fa fa-minus-circle'></i> </small>");
					
				
			} // fin ajax
		},
	});
	
	//refrescar las tabla
	setInterval( function () {
		tableCamiones.clear();
		 //datatable.rows.add(newDataArray);  por si necesita agregar nueva columna
		camionesD = 0;
		camionesR = 0;
	 	camionesN = 0;
		tableCamiones.ajax.reload();
	}, 40000 );
	
	setInterval( function () {
		
		tableDumper.clear();
		dumperD = 0;
		dumperR = 0;
	 	dumperN = 0;
		tableDumper.ajax.reload();
	}, 45000 );
	
	setInterval( function () {
		maquinasD = 0;
		maquinasR = 0;
	 	maquinasN = 0;
		tableMaquina.clear();
		tableMaquina.ajax.reload();
	}, 50000 );   
	
  
</script>
  
  