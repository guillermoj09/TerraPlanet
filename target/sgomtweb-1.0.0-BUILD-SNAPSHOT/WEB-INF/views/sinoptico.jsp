<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="cl.samtech.sgomt.object.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SAMTECH | SGOMT</title>

<%@include file="style.jsp"%>  

 <!-- Sweet Alert -->
    <link href="resources/inspinia_v2.9/FullVersion/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
 
 <style type="text/css">
 
.Scroll {
   overflow: auto;
    
  
   
  
}
 
.Timeline {
 overflow: auto;
  display: inline-flex;
  align-items: center;
  height: 400px;
  
  
  
}

.event1,
.event2 {
  position: relative;
  
}

.event1Bubble {
  position: absolute;
  background-color: rgba(158, 158, 158, 0.1);
  width: 70px;
  height: 70px;
  top: -120px;
  left: -15px;
  border-radius: 5px;
  box-shadow: inset 0 0 5px rgba(158, 158, 158, 0.64)
}

.event2Bubble {
  position: absolute;
  background-color: rgba(158, 158, 158, 0.1);
  width: 70px;
  height: 70px;
  top: 53px;
  left: -15px;  
  border-radius: 5px;
  box-shadow: inset 0 0 5px rgba(158, 158, 158, 0.64)
}

.eventTime {
  display: flex;
}

.Carga {
  font-size: 14px;
  fontStyle: "normal";
  margin-left: 10px;
  color: #676a6c;
}

.Day {
  font-size: 11px;
  margin-left: 5px;
  margin-top: 10px;
  color: #676a6c;
  fontStyle: "normal",
}



.eventTitle {
  fontStyle: "normal";
  color: #676a6c;
  font-size: 10px;
  text-transform: uppercase;
  display: flex;
  flex: 1;
  align-items: center;
  margin-left: 12px;
  margin-top: 54px;
}

.time {
  position: absolute;
  width: 50px;
  font-size: 8px;
  margin-top: -3px;
  margin-left: -5px;
  color: #676a6c;
}

 
 </style>
 
  
</head>
<body class="top-navigation">

	<div id="wrapper">
	
	
	<%@include file="menu.jsp"%>
	
		 <div id="page-wrapper" class="gray-bg">
		 
		 
       <div class="row border-bottom">
       </div>
        
            
               <!-- Titulo del Menu  -->
            <div class="row wrapper border-bottom white-bg page-heading">
                <div class="col-lg-10">
                    <h2></h2>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="">Productividad</a>
                        </li>
                        
                        
                        <li class="breadcrumb-item active">
                            <strong>Cuadro Sinoptico</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>
        
        
   <!-- Contenido principal  -->
        <div class="wrapper wrapper-content animated fadeInRight">
          <c:if test="${not empty mensaje}">        
                    <div class="row">
                            <div class="col-lg-12">
                            
                            	 
										 <div class="form-group">      
	       									<div class="alert alert-danger">
	       									<button type="button" class="close" data-dismiss="alert">&times;</button>
  												<a href="#" class="alert-link">${mensaje}</a>
											</div>	
										</div>	       
	       						
                            
                            </div>
                            </div>
         </c:if>
         
           <c:choose>
	    <c:when test="${rform.collapseshow == 'SI'}">
	    
      		<c:set var = "border" scope = "page" value = ""/>
      		<c:set var = "iboxContent" scope = "page" value = ""/>
      		<c:set var = "link" scope = "page" value = "fa-chevron-up"/>
      		<c:set var = "panelR" scope = "page" value = " "/>
      		<c:set var = "panelF" scope = "page" value = " in show "/>
	    </c:when>    
	    <c:otherwise>
      		<c:set var = "border" scope = "page" value = "border-bottom"/>
      		<c:set var = "iboxContent" scope = "page" value = "display: none;"/>
      		<c:set var = "link" scope = "page" value = "fa-chevron-down"/>
      		<c:set var = "panelR" scope = "page" value = " in show "/>
      		<c:set var = "panelF" scope = "page" value = " "/>
	    </c:otherwise>
		</c:choose>
         
         
         <div class="row"> <!-- Bloque Formulario de busqueda --> 
        
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                      <div class="ibox-title">
                          <h5>Filtros <b>Sinoptico</b> &nbsp;&nbsp; &nbsp;&nbsp; <small> Buscar por ... </small></h5>                           
                          <div class="ibox-tools">
                              <a class="collapse-link">
                                  <i class="fa fa-chevron-up" id="ocultar"></i>
                              </a>
                          </div>
                      </div>    
                      <form:form action="sinoptico.html" commandName="reportePatenteFechaForm"  id="data_1" autocomplete="off">                  
                      <div class="ibox-content" id="ocultardiv">
                        <div class="panel-group" id="accordion">
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h5 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">Busqueda Rapida</a>
                                            </h5>
                                        </div>
                                        
                                       <!--  <div id="collapseOne" class="panel-collapse collapse in show">  -->
                                       <div id="collapseOne" class="panel-collapse collapse <c:if test="${rform.collapseshow == 'NO'}"> in show</c:if>"> 
                                            <div class="panel-body">
                                                <div class="row"> 
                                               <%-- <div class="col-lg-2 ">   
                                                      <div class="form-group">                                      
                                                              <label>Faenas </label>
                                                              <div>
                                                              <select  name="faena" id="faena"  style="width:100%;" tabindex="4" data-placeholder="Seleccione una Faena" class="form-control text-uppercase">
														
														<option value=""></option>
														
														<!-- <option value="0"  <c:if test="${faenaTodas == 'SI'}"> selected</c:if> >Todas</option>  -->
														  														  														  
													<c:if test="${grupo.gruId != null }">
														  
														<option value="${grupo.gruId}" selected>${grupo.gruNombre}</option>
														  
													</c:if>
														
														<c:forEach items="${listfaena}" var="f" varStatus="count" >	  
														
														   <c:if test="${not  empty f }">
														   		<c:if test="${ f.idGru ne rform.faena }">
														   
														   <option value="${f.idGru}">${f.nombreGru}</option>
														   
														   		</c:if>	     
															
												
												 		  </c:if>	    
												      </c:forEach>	    																											
												</select>		
                                                              </div>                      
                                                      </div>
                                                    </div>  --%>
                                                    
                                                     <div class="col-lg-2  ">   
                                                      <div class="form-group">                                      
                                                              <label>Patentes </label>
                                                              <div>
                                                             
                                                               <select  name="patente" id="patente"  style="width:100%;" tabindex="4"  class="form-control text-uppercase" multiple required>
														 
														   <c:if test="${not  empty listpatentessi }">	  
													  
													  		<c:forEach items="${listpatentessi}" var="ps" varStatus="count" >	  	
																																																					
																 <option value="${ps.vehPatenteVehiculo}" Selected >${ps.vehPatenteVehiculo}<c:if test="${not  empty ps.nroInterno }"><p class="text-secondary"> - ${ps.nroInterno}</p> </c:if></option>
																									
															</c:forEach>           
		            								</c:if>  
																											
														<c:forEach items="${listpatentes}" var="p" varStatus="count" >	  
														
														   <c:if test="${not  empty p }">	
														      
															 <option value="${p.vehPatenteVehiculo}">${p.vehPatenteVehiculo}<c:if test="${not  empty p.nroInterno }"><p class="text-secondary"> - ${p.nroInterno}</p> </c:if></option>																	
																											
												 		  </c:if>	    
												      </c:forEach>	    																											
												</select>		
                                                              </div>                      
                                                      </div>
                                                    </div>  
                                                    
                                                     <div class="col-lg-8  b-r">   
                                                      
                                                    </div>    
                                                    
                                                      <div class="col-lg-2 text-center">                                                                                                              
                                                              <button class="ladda-button btn btn-primary" id="toggleSpinners" data-style="contract" type="submit" style="margin-top:20px;">Buscar</button>
                                                      </div>
                                                  </div>
                                            </div>
                                        </div>
                                    </div>
                                   
                                  <%--  <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">Filtros Avanzados</a>
                                            </h4>
                                        </div>
                                        <div id="collapseTwo" class="panel-collapse collapse <c:if test="${rform.collapseshow == 'SI'}"> in show</c:if>">
                                            <div class="panel-body">
                                                <div class="row">                               
                                                    <div class="col-lg-2">
                                                        <div class="form-group">
                                                            <label class="control-label">&nbsp; Fecha Desde</label>&nbsp;&nbsp; &nbsp;&nbsp; 
                                                            <div class="input-group date">
                                                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" class="form-control" name="fechaDesde" id="fechaDesde"  value="${rform.fechaDesde}" required>
                                                               
                                                               
                                                            </div>                                      
                                                        </div> 
                                                    </div>
                                                       <div class="col-lg-2">
                                                        <div class="form-group">
                                                            <label class="control-label">&nbsp; Hora Desde</label>&nbsp;&nbsp; &nbsp;&nbsp; 
                                                            <div class="input-group clockpicker" data-autoclose="true">
                                                                 <input type="text" class="form-control" name="horaDesde" id="horaDesde"  value="${rform.horaDesde}" >
                                                                <span class="input-group-addon"><span class="fa fa-clock-o"></span></span>
                                                            </div>                                     
                                                        </div> 
                                                    </div>
                                                    <div class="col-lg-2">
                                                        <div class="form-group">
                                                            <label class="control-label">&nbsp; Fecha Hasta</label>&nbsp;&nbsp; &nbsp;&nbsp; 
                                                            <div class="input-group date">
                                                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" class="form-control" name="fechaHasta" id="fechaHasta" value="${rform.fechaHasta}" required>
                                                            </div>                                      
                                                        </div> 
                                                    </div>
                                                      <div class="col-lg-2 ">
                                                        <div class="form-group">
                                                            <label class="control-label">&nbsp; Hora Hasta</label>&nbsp;&nbsp; &nbsp;&nbsp; 
                                                            <div class="input-group clockpicker" data-autoclose="true">
                                                                <input type="text" class="form-control" name="horaHasta" id="horaHasta" value="${rform.horaHasta}" >
                                                                <span class="input-group-addon"><span class="fa fa-clock-o"></span></span>
                                                            </div>                                       
                                                        </div> 
                                                    </div>
                                                   
                                               <div class="col-lg-2 b-r">
                                                      
                                                    </div>
                                                
                                                    
                                                     <div class="col-lg-2 text-center">                                                                                                              
                                                             <!--  <button class="btn btn-primary" type="submit"  style="margin-top:20px;">Filtraur</button>  -->
                                                              <button class="ladda-button btn btn-primary" id="toggleSpinners2" data-style="contract" type="submit" style="margin-top:20px;">Filtrar</button> 
                                                     </div>
                                                     
                                                     
                                                  </div>  
                                                                     

                                            </div>
                                        </div>
                                    </div> --%>
                                </div>
                         
                      </div>   
                      </form:form>                   
                    </div>
                </div>
            </div>  <!-- Fin bloque Fomr de Busqueda -->   
       
        
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins ${border}" id="ibox1">
                        <div class="ibox-title">
                            <h5>Cuadro Sinoptico</h5>
                       
                       </div>
                                                             
                        	<div class="ibox-content" style="${iboxContent}">
			
							<c:forEach items="${clist}" var="c" varStatus="count" >
							
							<div class="row Scroll">
               					 <div class="col-lg-12">
							
							<div class="Timeline">
                                
                                <div>
                                 <img src="resources/img/iconos/icons8-excavadora-filled-50.png" style="position:relative;left:10px;top: -20px;">
                                <br>${c.nrocarg} </br>
                                </div>

                                  <svg height="5" width="50">
                                  <line x1="0" y1="0" x2="100" y2="0" style="stroke:#24B893;stroke-width:5" />
                                 
                                </svg>

								<c:forEach items="${c.sinopticoactive}" var="s" varStatus="count2" >

                                  <div class="event${s.parimpar}">   
                                  
                                  <div class="event${s.parimpar}Bubble">
      								<div class="eventTime">
        							<div class="Carga">${s.cargaactual}/${s.cargapermitida}</div>
        								<i class='fa fa-truck fa-2x' style="position:absolute;left:15px;top: 30px;"></i>
        							 
      							</div>
      							 <div class="eventTitle">${s.nrocamion}</div>  
    							</div>
    									
    									<svg height="20" width="20">
       									<circle cx="10" cy="11" r="5" fill="#24B893" />
     								</svg>
    									<div class="time">${s.hora}</div>
    
  								</div>  <!-- fin event1 -->
                                                                   
                                                                   
                                  <svg height="5" width="50">
                                  <line x1="0" y1="0" x2="250" y2="0" style="stroke:#24B893;stroke-width:5" />
                                 
                                </svg>
                                
                                
								</c:forEach>
								
								 
								</div>
								
								<div class="Timeline">
								
								 <div>
                             <i class='fa fa-arrow-circle-down fa-3x' style="position:relative;margin-left:15px"></i>
                                
                                </div>
                                
                                </div>
								
								
								 </div>
                              </div>
								 
                              </c:forEach>     
                              
                             
                             
                              
                              <!-- #########################################3 -->
                              </br>
                              <%--
                              <div class="row Scroll">
               					 <div class="col-lg-6">
                               
                              <div class="Timeline">
                               
                                <div>
                                <img src="resources/img/iconos/icons8-excavadora-filled-50.png" style="position:relative;left:10px;top: -20px;">
                                <br>HCK22 </br>
                                </div>

                                  <svg height="5" width="50">
                                  <line x1="0" y1="0" x2="100" y2="0" style="stroke:#004165;stroke-width:5" />
                                  Sorry, your browser does not support inline SVG.
                                </svg>

							
                                  <div class="event1">   
                                  
                                  <div class="event1Bubble">
      								<div class="eventTime">
        							<div class="Carga">33/43</div>
        								<!-- <div class="Day">           Wednesday
          									<div class="MonthYear">february 2016</div>
        								</div>  -->
        								<i class='fa fa-truck fa-2x' style="position:absolute;left:15px;top: 30px;"></i>
        							<!--  <img src="resources/img/iconos/icons8-camion-contenedor-30.png" style="position:absolute;left:15px;top: 30px;"> -->
      							</div>
      							 <div class="eventTitle">HKF22</div>  
    							</div>
    									<!-- <div class="eventAuthor">by Youri Nelson</div>  -->
    									<svg height="20" width="20">
       									<circle cx="10" cy="11" r="5" fill="#004165" />
     								</svg>
    									<div class="time">9 : 27 AM</div>
    
  								</div>  <!-- fin event1 -->
                                                                   
                                                                   
                                  <svg height="5" width="50">
                                  <line x1="0" y1="0" x2="250" y2="0" style="stroke:#004165;stroke-width:5" />
                                  Sorry, your browser does not support inline SVG.
                                </svg>
                                
                                
                                
								 <div class="event2">   
                                  
                                  <div class="event2Bubble">
      								<div class="eventTime">
        							<div class="DayDigit">43/43</div>
        								<!-- <div class="Day">           Wednesday
          									<div class="MonthYear">february 2016</div>
        								</div>  -->
        							 <img src="resources/img/iconos/icons8-camion-contenedor-30.png" style="position:absolute;left:15px;top: 30px;">
      							</div>
      							 <div class="eventTitle">dffd</div>  
    							</div>
    									<!-- <div class="eventAuthor">by Youri Nelson</div>  -->
    									<svg height="20" width="20">
       									<circle cx="10" cy="11" r="5" fill="#004165" />
     								</svg>
    									<div class="time">9 : 27 AM</div>
    
  								</div>  <!-- fin event1 -->
                                                                   
                                                                   
                                  <svg height="5" width="50">
                                  <line x1="0" y1="0" x2="250" y2="0" style="stroke:#004165;stroke-width:5" />
                                  Sorry, your browser does not support inline SVG.
                                </svg>
                                
                                 <div class="event1">   
                                  
                                  <div class="event1Bubble">
      								<div class="eventTime">
        							<div class="DayDigit">33/43</div>
        								<!-- <div class="Day">           Wednesday
          									<div class="MonthYear">february 2016</div>
        								</div>  -->
        							 <img src="resources/img/iconos/icons8-camion-contenedor-30.png" style="position:absolute;left:15px;top: 30px;">
      							</div>
      							 <div class="eventTitle">dsdsds</div>  
    							</div>
    									<!-- <div class="eventAuthor">by Youri Nelson</div>  -->
    									<svg height="20" width="20">
       									<circle cx="10" cy="11" r="5" fill="#004165" />
     								</svg>
    									<div class="time">9 : 27 AM</div>
    
  								</div>  <!-- fin event1 -->
                                                                   
                                                                   
                                  <svg height="5" width="50">
                                  <line x1="0" y1="0" x2="250" y2="0" style="stroke:#004165;stroke-width:5" />
                                  Sorry, your browser does not support inline SVG.
                                </svg>
                                
                                 <div class="event2">   
                                  
                                  <div class="event2Bubble">
      								<div class="eventTime">
        							<div class="DayDigit">43/43</div>
        								<!-- <div class="Day">           Wednesday
          									<div class="MonthYear">february 2016</div>
        								</div>  -->
        							 <img src="resources/img/iconos/icons8-camion-contenedor-30.png" style="position:absolute;left:15px;top: 30px;">
      							</div>
      							 <div class="eventTitle">dffd</div>  
    							</div>
    									<!-- <div class="eventAuthor">by Youri Nelson</div>  -->
    									<svg height="20" width="20">
       									<circle cx="10" cy="11" r="5" fill="#004165" />
     								</svg>
    									<div class="time">9 : 27 AM</div>
    
  								</div>  <!-- fin event1 -->
                                                                   
                                                                   
                                  <svg height="5" width="50">
                                  <line x1="0" y1="0" x2="250" y2="0" style="stroke:#004165;stroke-width:5" />
                                  Sorry, your browser does not support inline SVG.
                                </svg>
                                
                                 <div class="event1">   
                                  
                                  <div class="event1Bubble">
      								<div class="eventTime">
        							<div class="DayDigit">33/43</div>
        								<!-- <div class="Day">           Wednesday
          									<div class="MonthYear">february 2016</div>
        								</div>  -->
        							 <img src="resources/img/iconos/icons8-camion-contenedor-30.png" style="position:absolute;left:15px;top: 30px;">
      							</div>
      							 <div class="eventTitle">dsdsds</div>  
    							</div>
    									<!-- <div class="eventAuthor">by Youri Nelson</div>  -->
    									<svg height="20" width="20">
       									<circle cx="10" cy="11" r="5" fill="#004165" />
     								</svg>
    									<div class="time">9 : 27 AM</div>
    
  								</div>  <!-- fin event1 -->
                                                                   
                                                                   
                                  <svg height="5" width="50">
                                  <line x1="0" y1="0" x2="250" y2="0" style="stroke:#004165;stroke-width:5" />
                                  Sorry, your browser does not support inline SVG.
                                </svg>
                                
                                 <div class="event2">   
                                  
                                  <div class="event2Bubble">
      								<div class="eventTime">
        							<div class="DayDigit">43/43</div>
        								<!-- <div class="Day">           Wednesday
          									<div class="MonthYear">february 2016</div>
        								</div>  -->
        							 <img src="resources/img/iconos/icons8-camion-contenedor-30.png" style="position:absolute;left:15px;top: 30px;">
      							</div>
      							 <div class="eventTitle">dffd</div>  
    							</div>
    									<!-- <div class="eventAuthor">by Youri Nelson</div>  -->
    									<svg height="20" width="20">
       									<circle cx="10" cy="11" r="5" fill="#004165" />
     								</svg>
    									<div class="time">9 : 27 AM</div>
    
  								</div>  <!-- fin event1 -->
                                                                   
                                                                   
                                  <svg height="5" width="50">
                                  <line x1="0" y1="0" x2="250" y2="0" style="stroke:#004165;stroke-width:5" />
                                  Sorry, your browser does not support inline SVG.
                                </svg>
                                
                                 <div class="event1">   
                                  
                                  <div class="event1Bubble">
      								<div class="eventTime">
        							<div class="DayDigit">33/43</div>
        								<!-- <div class="Day">           Wednesday
          									<div class="MonthYear">february 2016</div>
        								</div>  -->
        							 <img src="resources/img/iconos/icons8-camion-contenedor-30.png" style="position:absolute;left:15px;top: 30px;">
      							</div>
      							 <div class="eventTitle">dsdsds</div>  
    							</div>
    									<!-- <div class="eventAuthor">by Youri Nelson</div>  -->
    									<svg height="20" width="20">
       									<circle cx="10" cy="11" r="5" fill="#004165" />
     								</svg>
    									<div class="time">9 : 27 AM</div>
    
  								</div>  <!-- fin event1 -->
                                                                   
                                                                   
                                  <svg height="5" width="50">
                                  <line x1="0" y1="0" x2="250" y2="0" style="stroke:#004165;stroke-width:5" />
                                  Sorry, your browser does not support inline SVG.
                                </svg>
                                
                                
                                 <div class="event2">   
                                  
                                  <div class="event2Bubble">
      								<div class="eventTime">
        							<div class="DayDigit">43/43</div>
        								<!-- <div class="Day">           Wednesday
          									<div class="MonthYear">february 2016</div>
        								</div>  -->
        							 <img src="resources/img/iconos/icons8-camion-contenedor-30.png" style="position:absolute;left:15px;top: 30px;">
      							</div>
      							 <div class="eventTitle">dffd</div>  
    							</div>
    									<!-- <div class="eventAuthor">by Youri Nelson</div>  -->
    									<svg height="20" width="20">
       									<circle cx="10" cy="11" r="5" fill="#004165" />
     								</svg>
    									<div class="time">9 : 27 AM</div>
    
  								</div>  <!-- fin event1 -->
                                                                   
                                                                   
                                  <svg height="5" width="50">
                                  <line x1="0" y1="0" x2="250" y2="0" style="stroke:#004165;stroke-width:5" />
                                  Sorry, your browser does not support inline SVG.
                                </svg>
                                
                                 <div class="event1">   
                                  
                                  <div class="event1Bubble">
      								<div class="eventTime">
        							<div class="DayDigit">33/43</div>
        								<!-- <div class="Day">           Wednesday
          									<div class="MonthYear">february 2016</div>
        								</div>  -->
        							 <img src="resources/img/iconos/icons8-camion-contenedor-30.png" style="position:absolute;left:15px;top: 30px;">
      							</div>
      							 <div class="eventTitle">dsdsds</div>  
    							</div>
    									<!-- <div class="eventAuthor">by Youri Nelson</div>  -->
    									<svg height="20" width="20">
       									<circle cx="10" cy="11" r="5" fill="#004165" />
     								</svg>
    									<div class="time">9 : 27 AM</div>
    
  								</div>  <!-- fin event1 -->
                                                                   
                                                                   
                                  <svg height="5" width="50">
                                  <line x1="0" y1="0" x2="250" y2="0" style="stroke:#004165;stroke-width:5" />
                                  Sorry, your browser does not support inline SVG.
                                </svg>
                                
                                
                                 <div class="event2">   
                                  
                                  <div class="event2Bubble">
      								<div class="eventTime">
        							<div class="DayDigit">43/43</div>
        								<!-- <div class="Day">           Wednesday
          									<div class="MonthYear">february 2016</div>
        								</div>  -->
        							 <img src="resources/img/iconos/icons8-camion-contenedor-30.png" style="position:absolute;left:15px;top: 30px;">
      							</div>
      							 <div class="eventTitle">dffd</div>  
    							</div>
    									<!-- <div class="eventAuthor">by Youri Nelson</div>  -->
    									<svg height="20" width="20">
       									<circle cx="10" cy="11" r="5" fill="#004165" />
     								</svg>
    									<div class="time">9 : 27 AM</div>
    
  								</div>  <!-- fin event1 -->
                                                                   
                                                                   
                                  <svg height="5" width="50">
                                  <line x1="0" y1="0" x2="250" y2="0" style="stroke:#004165;stroke-width:5" />
                                  Sorry, your browser does not support inline SVG.
                                </svg>
                                
                                 <div class="event1">   
                                  
                                  <div class="event1Bubble">
      								<div class="eventTime">
        							<div class="DayDigit">33/43</div>
        								<!-- <div class="Day">           Wednesday
          									<div class="MonthYear">february 2016</div>
        								</div>  -->
        							 <img src="resources/img/iconos/icons8-camion-contenedor-30.png" style="position:absolute;left:15px;top: 30px;">
      							</div>
      							 <div class="eventTitle">dsdsds</div>  
    							</div>
    									<!-- <div class="eventAuthor">by Youri Nelson</div>  -->
    									<svg height="20" width="20">
       									<circle cx="10" cy="11" r="5" fill="#004165" />
     								</svg>
    									<div class="time">9 : 27 AM</div>
    
  								</div>  <!-- fin event1 -->
                                                                   
                                                                   
                                  <svg height="5" width="50">
                                  <line x1="0" y1="0" x2="250" y2="0" style="stroke:#004165;stroke-width:5" />
                                  Sorry, your browser does not support inline SVG.
                                </svg>
                                
                                 <div class="event2">   
                                  
                                  <div class="event2Bubble">
      								<div class="eventTime">
        							<div class="DayDigit">43/43</div>
        								<!-- <div class="Day">           Wednesday
          									<div class="MonthYear">february 2016</div>
        								</div>  -->
        							 <img src="resources/img/iconos/icons8-camion-contenedor-30.png" style="position:absolute;left:15px;top: 30px;">
      							</div>
      							 <div class="eventTitle">dffd</div>  
    							</div>
    									<!-- <div class="eventAuthor">by Youri Nelson</div>  -->
    									<svg height="20" width="20">
       									<circle cx="10" cy="11" r="5" fill="#004165" />
     								</svg>
    									<div class="time">9 : 27 AM</div>
    
  								</div>  <!-- fin event1 -->
                                                                   
                                                                   
                                  <svg height="5" width="50">
                                  <line x1="0" y1="0" x2="250" y2="0" style="stroke:#004165;stroke-width:5" />
                                  Sorry, your browser does not support inline SVG.
                                </svg>
                                
                                 <div class="event1">   
                                  
                                  <div class="event1Bubble">
      								<div class="eventTime">
        							<div class="DayDigit">33/43</div>
        								<!-- <div class="Day">           Wednesday
          									<div class="MonthYear">february 2016</div>
        								</div>  -->
        							 <img src="resources/img/iconos/icons8-camion-contenedor-30.png" style="position:absolute;left:15px;top: 30px;">
      							</div>
      							 <div class="eventTitle">dsdsds</div>  
    							</div>
    									<!-- <div class="eventAuthor">by Youri Nelson</div>  -->
    									<svg height="20" width="20">
       									<circle cx="10" cy="11" r="5" fill="#004165" />
     								</svg>
    									<div class="time">9 : 27 AM</div>
    
  								</div>  <!-- fin event1 -->
                                                                   
                                                                   
                                  <svg height="5" width="50">
                                  <line x1="0" y1="0" x2="250" y2="0" style="stroke:#004165;stroke-width:5" />
                                  Sorry, your browser does not support inline SVG.
                                </svg>
                                 <div class="event2">   
                                  
                                  <div class="event2Bubble">
      								<div class="eventTime">
        							<div class="DayDigit">43/43</div>
        								<!-- <div class="Day">           Wednesday
          									<div class="MonthYear">february 2016</div>
        								</div>  -->
        							 <img src="resources/img/iconos/icons8-camion-contenedor-30.png" style="position:absolute;left:15px;top: 30px;">
      							</div>
      							 <div class="eventTitle">dffd</div>  
    							</div>
    									<!-- <div class="eventAuthor">by Youri Nelson</div>  -->
    									<svg height="20" width="20">
       									<circle cx="10" cy="11" r="5" fill="#004165" />
     								</svg>
    									<div class="time">9 : 27 AM</div>
    
  								</div>  <!-- fin event1 -->
                                                                   
                                                                   
                                  <svg height="5" width="50">
                                  <line x1="0" y1="0" x2="250" y2="0" style="stroke:#004165;stroke-width:5" />
                                  Sorry, your browser does not support inline SVG.
                                </svg>
                                
                                 <div class="event1">   
                                  
                                  <div class="event1Bubble">
      								<div class="eventTime">
        							<div class="DayDigit">33/43</div>
        								<!-- <div class="Day">           Wednesday
          									<div class="MonthYear">february 2016</div>
        								</div>  -->
        							 <img src="resources/img/iconos/icons8-camion-contenedor-30.png" style="position:absolute;left:15px;top: 30px;">
      							</div>
      							 <div class="eventTitle">dsdsds</div>  
    							</div>
    									<!-- <div class="eventAuthor">by Youri Nelson</div>  -->
    									<svg height="20" width="20">
       									<circle cx="10" cy="11" r="5" fill="#004165" />
     								</svg>
    									<div class="time">9 : 27 AM</div>
    
  								</div>  <!-- fin event1 -->
                                                                   
                                                                   
                                  <svg height="5" width="50">
                                  <line x1="0" y1="0" x2="250" y2="0" style="stroke:#004165;stroke-width:5" />
                                  Sorry, your browser does not support inline SVG.
                                </svg>
                                 <div class="event2">   
                                  
                                  <div class="event2Bubble">
      								<div class="eventTime">
        							<div class="DayDigit">43/43</div>
        								<!-- <div class="Day">           Wednesday
          									<div class="MonthYear">february 2016</div>
        								</div>  -->
        							 <img src="resources/img/iconos/icons8-camion-contenedor-30.png" style="position:absolute;left:15px;top: 30px;">
      							</div>
      							 <div class="eventTitle">dffd</div>  
    							</div>
    									<!-- <div class="eventAuthor">by Youri Nelson</div>  -->
    									<svg height="20" width="20">
       									<circle cx="10" cy="11" r="5" fill="#004165" />
     								</svg>
    									<div class="time">9 : 27 AM</div>
    
  								</div>  <!-- fin event1 -->
                                                                   
                                                                   
                                  <svg height="5" width="50">
                                  <line x1="0" y1="0" x2="250" y2="0" style="stroke:#004165;stroke-width:5" />
                                  Sorry, your browser does not support inline SVG.
                                </svg>
                                
                                </div>
                                </div>
                                </div>
                                
                                
                                   <div class="Timeline" style="height:200px;margin-left:52px;">
                                   
                                    
                                
                                 
                                  <svg height="5" width="50">
                                  <line x1="0" y1="0" x2="100" y2="0" style="stroke:#004165;stroke-width:5" />
                                  Sorry, your browser does not support inline SVG.
                                </svg>
                                
                                
                                
                                 <div class="event1">   
                                  
                                  <div class="event1Bubble">
      								<div class="eventTime">
        							<div class="DayDigit">33/43</div>
        								<!-- <div class="Day">           Wednesday
          									<div class="MonthYear">february 2016</div>
        								</div>  -->
        							 <img src="resources/img/iconos/icons8-camion-contenedor-30.png" style="position:absolute;left:15px;top: 30px;">
      							</div>
      							 <div class="eventTitle">dsdsds</div>  
    							</div>
    									<!-- <div class="eventAuthor">by Youri Nelson</div>  -->
    									<svg height="20" width="20">
       									<circle cx="10" cy="11" r="5" fill="#004165" />
     								</svg>
    									<div class="time">9 : 27 AM</div>
    
  								</div>  <!-- fin event1 -->
                                                                   
                                                                   
                                  <svg height="5" width="50">
                                  <line x1="0" y1="0" x2="250" y2="0" style="stroke:#004165;stroke-width:5" />
                                  Sorry, your browser does not support inline SVG.
                                </svg>
                                
                                
                                </div>       <!-- fin time -->  --%>
                                                                         
		
							</div> <!-- fin ibox content -->
                               		                                   
              </div>
                                   
            </div>
                                                
        </div>
        
        
         <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins ${border}" id="ibox1">
                        <div class="ibox-title">
                            <h5>Reporte Sinoptico</h5>
                       
                       </div>
                                                             
                        	<div class="ibox-content" style="${iboxContent}">
								<div class="table-responsive">
									<table
										class="table table-striped table-bordered table-hover dataTables-example">
										<thead>
											<tr>
											<th>Peso Transportado</th>
                                            <th>TPO Medio</th>
                                            <th>Peso Total</th>
                                            <th>Distribucion Carga</th>                                            
                                            <th>TPO Operacion Carga</th>                                            
                                            <th>TPO Operacion Limpieza</th>                                            
                                            <th>TPO Muerto</th>
                                            <th>Frecuencia Despacho</th>
                                            <th>TPO de Carga</th>
                                                                                                                                                           
											</tr>
										</thead>
									 <tbody>
					
				    <c:if test="${not  empty rlist }">	    
					
						<c:forEach items="${rlist}" var="r" varStatus="count" >	  
							                                
		                                	
		                                	<tr>															                                    
		                                       
		                                         <td>${r.pesoTransp}</td>
		                                         <td>${r.tiempoMedio}</td>
		                                         <td>${r.pesoTotalDesp}</td>
		                                         <td>${r.distribucionCarga}</td>	
		                                         <td>${r.tiempoOperacionCar}</td>
		                                        <td>${r.tiempoOperacionLimp}</td>
		                                        <td>${r.tiempoMuerto}</td>
		                                        <td>${r.frecuenciaDesp}</td>
		                                        <td>${r.tiempoCargaMedia}</td>
		                                            
		                                      
		                                    </tr>
		           		</c:forEach>           
		            </c:if>		                                    	                         
		                                </tbody>
									</table>
								</div>
							</div>
                               		                                   
              </div>
                                   
            </div>
                                                
        </div>
        
        
        
       </div>
       
        <%@include file="footer.jsp"%>
        
      </div>

    
    </div>
    
     <%@include file="footerjs.jsp"%>
     
         
    <!-- Peity  pie del porcentaje-->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/peity/jquery.peity.min.js"></script>
    <script src="resources/inspinia_v2.9/FullVersion/js/demo/peity-demo.js"></script>
    <!-- Peity demo -->
     
    
    <!-- d3 and c3 charts -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/d3/d3.min.js"></script>
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/c3/c3.min.js"></script>
        <!-- Date range use moment.js same as full calendar plugin -->
	    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/fullcalendar/moment.min.js"></script>

	<!-- Date range picker -->
	<script src="resources/inspinia_v2.9/FullVersion/js/plugins/daterangepicker/daterangepicker.js"></script>
   <!-- Data picker -->
   <script src="resources/inspinia_v2.9/FullVersion/js/plugins/datapicker/bootstrap-datepicker.js"></script>
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/dataTables/datatables.min.js"></script>
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/dataTables/dataTables.bootstrap4.min.js"></script>

    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/dualListbox/jquery.bootstrap-duallistbox.js"></script>
    
     <!-- select 2 -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/select2/select2.full.min.js"></script>
   
    
    <!-- Sweet alert -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/sweetalert/sweetalert.min.js"></script>
    
    	 
    <!-- funciones de validacion de hora, efecto load para botones de busqueda y carga de pagina -->
       <%@include file="utiljs/loadpagebuttonjs.jsp"%>		
       <%@include file="utiljs/valclockpickerjs.jsp"%>
       <%@include file="utiljs/validarFechaHoraJs.jsp"%>			
    
    <script>
    
    //requiere validarFechaHoraJs y sweetalert
	var formdata = document.getElementById('data_1');  	
	formdata.addEventListener("submit", validarFechasHora, true);  
	
	
    var ocultar = document.getElementById("ocultar");   
    var ocultardiv= document.getElementById("ocultardiv");
     
    ocultar.addEventListener("click", funcionOcultar);   				
  	
     function funcionOcultar(){
     	
     	if (ocultardiv.style.display === "none") {
     		ocultardiv.style.display = "block";
     	  } else {
     		 ocultardiv.style.display = "none";
     		 
     	  }
     	
        }
     
     $( "#patente" ).select2( {
  		placeholder: "Seleccione Patente",
  		 allowClear: true
  		
  	} );
     
    
    
    var URLactual =window.location.protocol +"//"+ window.location.host;
    
    $(document).ready(function(){
        $('.dataTables-example').DataTable({
        	"language": {
                "url": URLactual + "/sgomtweb/resources/datetableespanil.json"
            },
        	pageLength: 10,
            //"order": [[ 0, "asc" ]],
            "order": false,
            responsive: true,
            dom: '<"html5buttons"xºB>lTfgitp',
            buttons: [
                {extend: 'copy'},
                {extend: 'csv'},
                {extend: 'excel', title: 'ReporteSinoptico'},
                {extend: 'pdf', title: 'ReporteSinoptico'},

                {extend: 'print',
                 customize: function (win){
                        $(win.document.body).addClass('white-bg');
                        $(win.document.body).css('font-size', '10px');

                        $(win.document.body).find('table')
                                .addClass('compact')
                                .css('font-size', 'inherit');
                }
                }
            ]

        });

    });
    
    $('#data_1 .input-group.date').datepicker({
    	language: 'es',
    	todayBtn: "linked",
        keyboardNavigation: false,
        forceParse: false,
        calendarWeeks: true,
        autoclose: true,
        format: "dd/mm/yyyy"
    });
    
   
    $('.clockpicker').clockpicker();
    		

    </script>

    
</body>

</html>

<%--
tamaño icon fa
fa-xs
fa-sm
fa-lg
fa-2x
fa-3x
fa-4x
fa-5x
fa-10x
 --%>