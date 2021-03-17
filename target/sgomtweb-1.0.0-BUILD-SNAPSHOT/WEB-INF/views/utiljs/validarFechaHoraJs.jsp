 <script>
    
 var validarFechasHora = function (event){
				
	    var fechaDesde = document.getElementById("fechaDesde").value;  
	    var fechaHasta= document.getElementById("fechaHasta").value;		   
	    var horaDesde = document.getElementById("horaDesde").value;  
	    var horaHasta= document.getElementById("horaHasta").value;
	    
	    var nowi = moment(fechaDesde+" "+horaDesde, "DD/MM/YYYY HH:mm:ss");
	    var nowf = moment(fechaHasta+" "+horaHasta, "DD/MM/YYYY HH:mm:ss");
		
		var vali = moment(nowi).isBefore(nowf);
	
	if(!vali){
				
		swal("", "La fecha de inicio debe de ser inferior a la fecha de termino.", "error");
		event.preventDefault();
		
		return false;
					
	}else{
		
		//swal("", "Fecha ok", "error");		
		return true;
		
	}
				
	}
 
 
 function validoFechasHora(fec_desde = "fechaDesde", hor_desde = "horaDesde", fec_hasta = "fechaHasta",  hor_hasta= "horaHasta"){
	 
	 var fechaDesde = $('#'+fec_desde).val() || $("[name='"+fec_desde+"']").val();  
	    var fechaHasta= $('#'+fec_hasta).val() || $("[name='"+fec_hasta+"']").val(); 		   
	    var horaDesde = $('#'+hor_desde).val() || $("[name='"+hor_desde+"']").val();   
	    var horaHasta= $('#'+hor_hasta).val() || $("[name='"+hor_hasta+"']").val(); 
	 
	    var nowi = moment(fechaDesde+" "+horaDesde, "DD/MM/YYYY HH:mm:ss");
	    var nowf = moment(fechaHasta+" "+horaHasta, "DD/MM/YYYY HH:mm:ss");		
		var vali = moment(nowi).isBefore(nowf);
		
	if(!vali){		
		swal("", "La fecha de inicio debe de ser inferior a la fecha de termino.", "error");
		event.preventDefault();		
		return false;					
	}else{				
		return true;		
	}
	 
 }
	
 </script>   	