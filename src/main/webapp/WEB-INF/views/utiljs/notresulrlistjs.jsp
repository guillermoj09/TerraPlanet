<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
 <script>
 <c:if test='${ empty rlist}'>	   
    
    document.getElementById("mensajejs").innerHTML =  "<div class='row'> <div class='col-lg-12'>  <div class='form-group'> <div class='alert alert-danger'> <button type='button' class='close' data-dismiss='alert'>&times;</button> <a href=''#' class='alert-link'>No hay resultado</a> </div></div></div></div>"
    
 </c:if>
 </script>    