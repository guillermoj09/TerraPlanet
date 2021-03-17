   <!-- inicio estados por camiones maquinas y dumper, (solo para test ) -->
      <div class ="row">
      
      <div class="col-xs-12 col-md-6 col-lg-6 col-xl-4">
					 <div class="ibox ">
                    <div class="ibox-title">
                    	 <span class="label label-success pull-right">Estado &nbsp;<i class='fa fa-rss'></i> </span> 
						<i class="icon-chart"></i>
						<h5 class="text-muted text-uppercase m-b-20">Camiones &nbsp; <i class='fa fa-truck'></i></h5>
						<h6 class="text-muted text-uppercase m-b-20" id="camionesfechaAjaxS"><small>&nbsp; </small></h6>
						<div class="m-b-20">	
						
						
						<h2>
							<span data-plugin="counterup" id="camionesD"></span>														
						
						
						
							<span data-plugin="counterup" id="camionesR"></span>														
						
						
						
							<span data-plugin="counterup" id="camionesN"></span>														
						</h2>	
																							
						</div>
						

					</div>	
					</div>
				</div>


				<div class="col-xs-12 col-md-6 col-lg-6 col-xl-4">
					
					 <div class="ibox ">
                    <div class="ibox-title">
                    	 <span class="label label-success  pull-right">Estado &nbsp;<i class='fa fa-rss'></i>  </span> 
						<i class="icon-chart"></i>
						<h5 class="text-muted text-uppercase m-b-20">Dumpers &nbsp; <i class='fa fa-truck'></i></h5>
						<h6 class="text-muted text-uppercase m-b-20" id="dumperfechaAjaxS"><small>&nbsp; </small></h6>
						<div class="m-b-20">	
						<h2>
							<span data-plugin="counterup" id="dumperD"></span>														
						
							<span data-plugin="counterup" id="dumperR"></span>														
							<span data-plugin="counterup" id="dumperN"></span>														
						</h2>																									
						</div>
						
							
					</div>	
					</div>
				</div>

				

			<div class="col-xs-12 col-md-6 col-lg-6 col-xl-4">
					
					 <div class="ibox ">
                    <div class="ibox-title">
                    	 <span class="label label-success  pull-right">Estado &nbsp;<i class='fa fa-rss'></i>  </span> 
						<i class="icon-chart"></i>
						<h5 class="text-muted text-uppercase m-b-20">Maquinas  &nbsp; <i class='fa fa-truck'></i></h5>
						<h6 class="text-muted text-uppercase m-b-20" id="maquinasfechaAjaxS"><small>&nbsp; </small></h6>
						<div class="m-b-20">	
						<h2>
							<span data-plugin="counterup" id="maquinasD"></span>														
							<span data-plugin="counterup" id="maquinasR"></span>														
							<span data-plugin="counterup" id="maquinasN"></span>														
						</h2>																								
						</div>
						
							
					</div>	
					</div>
				</div>
      
      
     </div> 
        
       <div class="row">
       <div class="col-xs-12 col-md-6 col-lg-6 col-xl-4"> 
       <div class="ibox-content " style="${iboxContent}">
                        	 <div class="sk-spinner sk-spinner-three-bounce">                                
                                 <div class="sk-bounce1"></div>
                                    <div class="sk-bounce2"></div>
                                    <div class="sk-bounce3"></div>                                                                    
                    			</div>
                    			
								<div class="table-responsive">
									<table
										class="table table-striped table-bordered table-hover camiones" id="camiones">
										<thead>
											<tr>
																						
                                            <th>Patente</th>
                                            <th>Nr.Int</th>
                                            <th>Estado</th>
                                            <th>Fecha GPS</th>
                                            <th>Fecha DB</th>
                                            <th>GEO</th>
                                            
											</tr>
										</thead>
									 <tbody>
						                                
		                                	 <tr> 			                                    
		                                        <td></td>
		                                        <td></td>
		                                        <td></td>
		                                        <td></td>
		                                         <td></td>
		                                        <td id="trC"></td>
		                                        		                                      
		                                    </tr>		           	          
		                                               	                         
		                                </tbody>
		                                
									</table>
								</div>
					</div>
	</div>
							
						 <div class="col-xs-12 col-md-6 col-lg-6 col-xl-4">	
							  <div class="ibox-content" style="${iboxContent}">
                        	 <div class="sk-spinner sk-spinner-three-bounce">                                
                                 <div class="sk-bounce1"></div>
                                    <div class="sk-bounce2"></div>
                                    <div class="sk-bounce3"></div>                                                                    
                    			</div>
                    			
								<div class="table-responsive">
									<table
										class="table table-striped table-bordered table-hover camiones" id="dumper">
										<thead>
											<tr>
																						
                                            <th>Patente</th>
                                            <th>Nr.Int</th>
                                            <th>Estado</th>
                                            <th>Fecha GPS</th>
                                            <th>Fecha DB</th>
                                             <th>GEO</th>
                                            
											</tr>
										</thead>
									 <tbody>
						                                
		                                	 <tr> 			                                    
		                                        <td></td>
		                                        <td></td>
		                                        <td></td>
		                                        <td></td>
		                                        <td></td>
		                                         <td id="trD"></td>
		                                        		                                      
		                                    </tr>		           	          
		                                               	                         
		                                </tbody>
		                                
									</table>
								</div>
							</div>
					</div>		
							
					<div class="col-xs-12 col-md-6 col-lg-6 col-xl-4">
							  <div class="ibox-content" style="${iboxContent}">
                        	 <div class="sk-spinner sk-spinner-three-bounce">                                
                                 <div class="sk-bounce1"></div>
                                    <div class="sk-bounce2"></div>
                                    <div class="sk-bounce3"></div>                                                                    
                    			</div>
                    			
								<div class="table-responsive">
									<table
										class="table table-striped table-bordered table-hover camiones" id="maquinas">
										<thead>
											<tr>
																						
                                            <th>Patente</th>
                                            <th>Nr.Int</th>
                                            <th>Estado</th>
                                            <th>Fecha GPS</th>
                                            <th>Fecha DB</th>
                                            <th>GEO</th>
                                            
											</tr>
										</thead>
									 <tbody>
						                                
		                                	 <tr> 			                                    
		                                        <td></td>
		                                        <td></td>
		                                        <td></td>
		                                        <td></td>
		                                        <td></td>
		                                        <td id="trM"></td>
		                                        		                                      
		                                    </tr>		           	          
		                                               	                         
		                                </tbody>
		                                
									</table>
								</div>
							</div>
						</div>	
							
			</div> <!-- -fin row --> 
			<!-- fin estados por camiones maquinas y dumper, (solo para test ) -->