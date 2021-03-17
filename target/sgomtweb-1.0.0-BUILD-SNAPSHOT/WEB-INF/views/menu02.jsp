<div class="row border-bottom white-bg">
	<nav class="navbar navbar-expand-lg navbar-static-top" role="navigation">		
           <%@include file="header.jsp"%>
           
            <div class="navbar-collapse collapse" id="navbar">
                <ul class="nav navbar-nav mr-auto">
                                                            
                    <li class="dropdown">
                        <a aria-expanded="false" role="button" href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-road"></i> Mapas</a>
                        <ul role="menu" class="dropdown-menu">
                       
                     
                       
                            <li><a href="pageframe.html?id=FaListadoFlota" target="_blank">Mapa General </a></li>
                            <li><a href="pageframe.html?id=kml_generico_new" target="_blank">Google Earth</a></li>                            
                           <!--  <li class="dropdown-submenu">
                             	 <a href="#" class="dropdown-toggle" data-toggle="dropdown">Mapa Faena</a>
                             	 	<ul class="dropdown-menu">                                                
                                                <li ><a href="pageframe.html?id=maps/index">Spence</a></li>                                                
                                   </ul>
                             </li>  -->
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a aria-expanded="false" role="button" href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-group"></i> Administración</a>
                        <ul role="menu" class="dropdown-menu">  
                          <c:if test="${usuario.perfilid == '1'}">                           
                            <li><a href="pageframe.html?id=asigna-fae">Asignación de Faenas</a></li>                            
                            <li><a href="pageframe.html?id=geoPoli2/index">Cercos Virtuales</a></li>
                            <li><a href="pageframe.html?id=acciones_ibutton">Llaves Ibutton</a></li>
                            <li><a href="pageframe.html?id=listadoconductores">Maestro de Conductores</a></li>
                            <li><a href="pageframe.html?id=listado_faenas">Maestro Faenas</a></li>
                            <li><a href="pageframe.html?id=marcas_de_terreno">Marcas de Terreno</a></li>
                          </c:if>
                            <li><a href="pageframe.html?id=geo_cercas_dm_rutas">Rutas</a></li>
                              <c:if test="${usuario.perfilid == '1'}">  
	                           <li class="dropdown-submenu">
	                            	 <a href="#" class="dropdown-toggle" data-toggle="dropdown">Usuarios</a>
	                            	 	<ul class="dropdown-menu">                                                
	                                       <!-- <li ><a href="crearusuario.html">Crear Usuario</a></li>  -->
	                                    
	               						<li><a href="consultarusuarios.html">Listado Usuario</a></li>
	                                       <li class="dropdown-divider"></li>	                                      
	                                       <li ><a href="cambiarclave.html?id=<%=usuario.getUsername()%>"> <i class="fa fa-key"></i> <span class="nav-label">Cambiar Clave</span></a></li>
	                                                                                   
	                                  </ul>
	                           </li>  
	                            </c:if>                             
                           <!--  <li><a href="pageframe.html?id=vehiculos_new">Vehículos</a></li>  -->
                            	<li class="dropdown-submenu">
	                            	 <a href="#" class="dropdown-toggle" data-toggle="dropdown">Vehiculos</a>
	                            	 	<ul class="dropdown-menu">
	                            	 	   <c:if test="${usuario.perfilid == '1'}">     
	                            	 	   <li><a href="pageframe.html?id=vehiculos_new">Listado Vehiculos</a></li>  
	                            	 	    <li class="dropdown-divider"></li>                                      
	                            	 	    </c:if>    	                                       
	               						   <li><a href="consultarvehiculo.html">Listado Vehiculos </a></li>
	               						     <c:if test="${usuario.perfilid == '1'}">
	               						    <!--  <li ><a class= "dropdown-item disabled not-allowed" href="creartag.html" title="DESHABILITADO" onclick="return false;">Crear Tag</a></li> -->
	               						     </c:if>
	               						    <li><a href="consultartag.html">Listado Tags</a></li>	                                                                                      
	                                  </ul>
	                           	</li>
                            
                            
                        </ul> 
                    </li>
                     <li class="dropdown">
                        <a aria-expanded="false" role="button" href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-truck"></i> Gestión de Flota</a>
                        <ul role="menu" class="dropdown-menu">
                            <li><a href="historico.html">Histórico</a></li>
                             <c:if test="${usuario.perfilid == '1'}">
                            <li><a href="pageframe.html?id=geo_cercas_todos_new">Cercos Virtuales</a></li>
                            </c:if>                            
                            <li class="dropdown-submenu">
                             	 <a href="#" class="dropdown-toggle" data-toggle="dropdown">Alarmas</a>
                             	 	<ul class="dropdown-menu">     
                     	 				<li ><a href=reporteinformealarmacond.html">Alarma de Condicion</a></li>
                						<li><a href="reportealarmaruta.html">Alarma de Ruta</a></li>                                             
                                        <li ><a href="reportedetencionessos.html">Detenciones Sostenidas</a></li>
                						<!-- <li><a href="pageframe.html?id=sobre_extendidas_new2">Velocidades Sostenidas</a></li>  -->
                        						                                                                                              
                                   </ul>
                             </li>  
                              <c:if test="${usuario.perfilid == '1'}">      
                             <li><a href="pageframe.html?id=informe_ibutton">Tiempo de Conduccion</a></li>
                             </c:if>                      
                        </ul>
                    </li>
                     <li class="dropdown">
                        <a aria-expanded="false" role="button" href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-line-chart"></i> Productividad</a>
                        <ul role="menu" class="dropdown-menu">

							 <c:if test="${usuario.perfilid == '1'}">
                            <li><a class= "dropdown-item disabled not-allowed" href="reportetiempocarguio.html" title="DESHABILITADO" onclick="return false;">Carguio</a></li>
                            </c:if>
                            <li><a href="mapacalor.html">Mapa de Calor</a></li>
                            <li><a href="reportedescarga.html">Informe Descarga</a></li>
                             <c:if test="${usuario.perfilid == '1'}">
                            <li><a class= "dropdown-item disabled not-allowed" href="sinoptico.html" title="DESHABILITADO" onclick="return false;">Cuadro Sinoptico</a></li>
                            <li><a class= "dropdown-item disabled not-allowed" href="diagramaentregado.html" title="DESHABILITADO" onclick="return false;">Diagrama de Produccion</a></li>
                            <li><a class= "dropdown-item disabled not-allowed" href="reporteindicadoroptimocarga.html" title="DESHABILITADO" onclick="return false;">Grafico Toneladas</a></li>
                            </c:if>
                            <li><a href="reportetiemfueraoperacion.html">Tiempo fuera de operacion</a></li>
                             <c:if test="${usuario.perfilid == '1'}">
                            <li><a class= "dropdown-item disabled not-allowed" href="pageframe.html?id=listado4" title="DESHABILITADO" onclick="return false;">Produccion por Turno</a></li>                             
                            <li><a class= "dropdown-item disabled not-allowed" href="reportetiempotransporte.html" title="DESHABILITADO" onclick="return false;">Reporte Tiempo de Transporte</a></li>
                            <li><a class= "dropdown-item disabled not-allowed" href="reporteciclotransporte.html" title="DESHABILITADO" onclick="return false;">Reporte Ciclo de Transporte</a></li>
                            </c:if>                             
                        </ul>
                    </li>
                    
                     <li class="dropdown">
                        <a aria-expanded="false" role="button" href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-plus-square"></i> Salud</a>
                        <ul role="menu" class="dropdown-menu">
                            <!-- <li><a href="pageframe.html?id=Ralenti_CAN">Informe Ralenti</a></li>  -->
                            <li><a href="reportetinformeralentican.html">Informe Ralenti</a></li>
                            <!-- <li><a href="pageframe.html?id=Rendimiento_CAN_new">Informe Rendimiento</a></li> -->
                            <li><a href="reporteinformerendimiento.html">Informe Rendimiento</a></li>
                            <!-- ><li><a href="pageframe.html?id=Informe_Turno_Itinerario_CAN">Informe Datos X Turnos</a></li>  -->
                            <li><a href="detallexviaje.html">Detalle Data Canbus</a></li>
                            <li><a href="reporteresumenflota.html">Resumen Flota Canbus</a></li> 
                        </ul>
                    </li>
                    

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
		
						