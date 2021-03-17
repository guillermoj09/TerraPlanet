  <script>
//validar hora desde formato correcto  
  	var horadesde = document.getElementById('horaDesde');
  	
  	 horadesde.addEventListener("change", validarHora);  
  	 
  	 function validarHora(){
  		 
  		var patt = new RegExp("^$|^(([01][0-9])|(2[0-3])):[0-5][0-9]$");
  	    var res = patt.test(horadesde.value);
  	    
  	    if(res){
  	    	
  	    	
  	    	
  	    }else{
  	    	
  	    	document.getElementById("mensajejs").innerHTML =  "<div class='row'> <div class='col-lg-12'>  <div class='form-group'> <div class='alert alert-danger'> <button type='button' class='close' data-dismiss='alert'>&times;</button> <a href=''#' class='alert-link'>Formato de la hora desde es incorrecto</a> </div></div></div></div>"           
  	    	
  	    	document.getElementById("horaDesde").value = "00:00"
  	    	
  	    }
  	    
  	 }
  	 
  	//validar hora desde formato correcto  
   	var horahasta = document.getElementById('horaHasta');
   	
   	horahasta.addEventListener("change", validarHora2);  
   	 
   	 function validarHora2(){
   		 
   		var patt = new RegExp("^$|^(([01][0-9])|(2[0-3])):[0-5][0-9]$");
   	    var res = patt.test(horahasta.value);
   	    
   	    if(res){
   	    	
   	    	
   	    	
   	    }else{
   	    	
   	    	document.getElementById("mensajejs2").innerHTML =  "<div class='row'> <div class='col-lg-12'>  <div class='form-group'> <div class='alert alert-danger'> <button type='button' class='close' data-dismiss='alert'>&times;</button> <a href=''#' class='alert-link'>Formato de la hora hasta es incorrecto</a> </div></div></div></div>"           
   	    	
   	    	document.getElementById("horaHasta").value = "23:59"
   	    	
   	    }
   	    
   	 }
  		
     	
 </script>