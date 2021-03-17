<div class="row border-bottom white-bg">
	<nav class="navbar navbar-expand-lg navbar-static-top" role="navigation">		
           <%@include file="header.jsp"%>
           
            <div class="navbar-collapse collapse" id="navbar">
                <ul class="nav navbar-nav mr-auto">
                
                <c:forEach items="${usuario.modulos}" var="m" varStatus="count" >	 
                <c:if test="${not empty m.menus}"> 
                	   <li class="dropdown">
                	   
                	     <a aria-expanded="false" role="button" href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="${m.classdiv}"></i>${m.modNombre}</a>
                	       <ul role="menu" class="dropdown-menu">
                	        <c:forEach items="${m.menus}" var="menu" varStatus="count" >
                	        	<c:if test="${menu.valsubme == 'NO'}">  	
                	        	 <li><a href="${menu.menLink}"  ${menu.target}  class= "dropdown-item" >${menu.menNombre} </a></li>
                	        	 </c:if>
                	        	 	<c:if test="${menu.valsubme == 'SI'}">   
                	        	 	  <li class="dropdown-submenu">   
                	        	 	  	<a href="#" class="dropdown-toggle" data-toggle="dropdown">${menu.menNombre}</a>
                	        	 	  		<ul class="dropdown-menu">
                	        	 	 			<c:forEach items="${menu.submenus}" var="submenu" varStatus="count" >
                	        	 	 					<li ><a href="${submenu.menLink}">${submenu.menNombre}</a></li>                	        	 	 	
                	        	 	 			</c:forEach>
                	        	 	 		</ul>	
                	        	 	  </li>
                	        	 	</c:if>
                	        </c:forEach>
                	       
                	       </ul>                	   
                	   </li>	
                	
                	</c:if>   
																																																																												
				</c:forEach>   
                                                            
                                        

                </ul>
                <ul class="nav navbar-top-links navbar-right">
                <li><span class="m-r-sm text-muted welcome-message">Bienvenido <%=usuario.getNombre() %>.</span></li>
                    <li>
                        <a href="logout.html">
                            <i class="fa fa-sign-out"></i> SALIR
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
	</div>
		
						