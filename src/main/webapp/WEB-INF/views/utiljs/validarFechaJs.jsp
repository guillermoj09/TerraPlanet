 <script>
          
    var validarFechas = function (event){
		
	    var fechaDesde = document.getElementById("fechaDesde").value;  
	    var fechaHasta= document.getElementById("fechaHasta").value;		   
	    
	    var nowi = moment(fechaDesde, "DD/MM/YYYY");
	    var nowf = moment(fechaHasta, "DD/MM/YYYY");
		
		var vali = moment(nowi).isBefore(nowf);
	
	if(!vali){
				
		swal("", "Fecha Desde mayor que Fecha Hasta ", "error");
		event.preventDefault();
		
		return false;
					
	}else{
					
		return true;
		
	}
				
	} 
    
 </script>   