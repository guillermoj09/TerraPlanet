   <!-- inicio estados por camiones maquinas y dumper, (solo para test ) -->
      <div class ="row">
      
      <div class="col-xs-12 col-md-6 col-lg-6 col-xl-6">
					 <div class="ibox ">
                    <div class="ibox-title">
                    	 <span class="label label-success pull-right">Estado &nbsp;<i class='fa fa-rss'></i> </span> 
						<i class="icon-chart"></i>
						<h5 class="text-muted text-uppercase m-b-20">GPS &nbsp; <i class='fa fa-compass'></i></h5>
						<h6 class="text-muted text-uppercase m-b-20" id="gpsfechaAjaxS"><small>&nbsp; </small></h6>
						<div class="m-b-20">	
						
						
						<h2>
							<span data-plugin="counterup" id="gpsD"></span>														
						
						
						
							<span data-plugin="counterup" id="gpsR"></span>														
						
						
						
							<span data-plugin="counterup" id="gpsN"></span>														
						</h2>	
																							
						</div>
						

					</div>	
					</div>
				</div>


				<div class="col-xs-12 col-md-6 col-lg-6 col-xl-6">
					
					 <div class="ibox ">
                    <div class="ibox-title">
                    	 <span class="label label-success  pull-right">Estado &nbsp;<i class='fa fa-rss'></i>  </span> 
						<i class="icon-chart"></i>
						<h5 class="text-muted text-uppercase m-b-20">DB &nbsp; <i class='fa fa-database'></i></h5>
						<h6 class="text-muted text-uppercase m-b-20" id="dbfechaAjaxS"><small>&nbsp; </small></h6>
						<div class="m-b-20">	
						<h2>
							<span data-plugin="counterup" id="dbD"></span>														
						
							<span data-plugin="counterup" id="dbR"></span>														
							<span data-plugin="counterup" id="dbN"></span>														
						</h2>																									
						</div>
						
							
					</div>	
					</div>
				</div>

				

			
      
     </div> 
        
       <div class="row">
       
       <div class="col-xs-12 col-md-6 col-lg-6 col-xl-6"> 
       <div class="ibox-content " style="${iboxContent}">
                        	 <div class="sk-spinner sk-spinner-three-bounce">                                
                                 <div class="sk-bounce1"></div>
                                    <div class="sk-bounce2"></div>
                                    <div class="sk-bounce3"></div>                                                                    
                    			</div>
                    			
								<div class="table-responsive">
									<table
										class="table table-striped table-bordered table-hover gps" id="tablagps">
										<thead>
											<tr>
																						
                                            <th>Patente</th>
                                            <th>Nr.Int</th>
                                            <th>Estado GPS</th>
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
							
							
							
					<div class="col-xs-12 col-md-6 col-lg-6 col-xl-6">
							  <div class="ibox-content" style="${iboxContent}">
                        	 <div class="sk-spinner sk-spinner-three-bounce">                                
                                 <div class="sk-bounce1"></div>
                                    <div class="sk-bounce2"></div>
                                    <div class="sk-bounce3"></div>                                                                    
                    			</div>
                    			
								<div class="table-responsive">
									<table
										class="table table-striped table-bordered table-hover camiones" id="tabledb">
										<thead>
											<tr>
																						
                                            <th>Patente</th>
                                            <th>Nr.Int</th>
                                            <th>Estado DB</th>
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
			<!-- fin gps, DB, (solo para test ) -->