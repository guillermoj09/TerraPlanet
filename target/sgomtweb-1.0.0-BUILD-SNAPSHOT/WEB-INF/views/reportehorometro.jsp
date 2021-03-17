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
                            <strong>Reporte Horometro</strong>
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
	    <c:when test="${rform.collapseshow == 'off'}">
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
                          <h5>Filtros <b>Reporte Horometro </b> &nbsp;&nbsp; &nbsp;&nbsp; <small> Buscar por ... </small></h5>
                          <div class="ibox-tools">
                              <a class="collapse-link">
                                  <i class="fa fa-chevron-up" id="ocultar"></i>
                              </a>
                          </div>
                      </div>
                      <form:form action="reportehorometro.html" commandName="reporteHorometroForm"  id="data_1" autocomplete="off">
                      <div class="ibox-content" id="ocultardiv">
                        <div class="panel-group" id="accordion">
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h5 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">Busqueda Rapida</a>
                                            </h5>
                                        </div>

                                       <!--  <div id="collapseOne" class="panel-collapse collapse in show">  -->
                                       <div id="collapseOne" class="panel-collapse collapse ${panelR} in show">
                                            <div class="panel-body">
                                                <div class="row">
                                                <div class="col-lg-2 ">
                                                      <div class="form-group">
                                                              <label>Faenas </label>
                                                              <div>
                                                              <select  name="faenas" id="faenas"  style="width:100%;" tabindex="4" required data-placeholder="Seleccione una Faena"
                                                              class="form-control text-uppercase">

																<option value=""></option>
																<c:forEach items="${listfaena}" var="f" varStatus="count" >
																   <c:if test="${not  empty f }">
																  			<option value="${f.idGru}" <c:if test="${f.idGru == rform.faenas}"> selected</c:if>>${f.nombreGru}</option>
														 		  </c:if>
														      </c:forEach>
															</select>
                                                              </div>
                                                      </div>
                                                    </div>
                                                    <div class="col-lg-5  b-r">

                                                    </div>
                                                    <div class="col-lg-2  b-r">
                                                      <div class="form-group">
                                                              <label>Visualización Tiempos </label>
                                                              <div>
                                                               <select  name="opt_ver" id="opt_ver"  style="width:100%;" tabindex="4" data-placeholder="Seleccione una Opcion"
                                                               		class="form-control text-uppercase">
																	<option value="1" <c:if test="${rform.opt_ver=='1'}"> selected</c:if>>En Horas</option>
																	<option value="2" <c:if test="${rform.opt_ver=='2'}"> selected</c:if>>En Minutos</option>
																	<option value="3" <c:if test="${rform.opt_ver=='3'}"> selected</c:if>>En Segundos</option>
																	<option value="4" <c:if test="${rform.opt_ver=='4'}"> selected</c:if>>En HH:MM:SS</option>
														
															</select>
                                                              </div>
                                                      </div>
                                                    </div>
                                                      <div class="col-lg-3 text-center">
                                                              <button  type="button" class="ladda-button btn btn-primary btn_submit" id="toggleSpinners" data-style="contract" style="margin-top:20px;">Buscar</button>
                                                      </div>
                                                  </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                      </div>
                      </form:form>
                    </div>
                </div>
            </div>  <!-- Fin bloque Fomr de Busqueda -->

            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins  ${border}" id="ibox1">
                        <div class="ibox-title">
                            <h5>Reporte Horometro</h5>
                       		<div class="ibox-tools">
                              <a class="collapse-link">
                                  <i class="fa ${link}"></i>
                              </a>
                          </div>
                       </div>

                        	<div class="ibox-content" style="${iboxContent}">
                        	 <div class="sk-spinner sk-spinner-three-bounce">
                                 <div class="sk-bounce1"></div>
                                    <div class="sk-bounce2"></div>
                                    <div class="sk-bounce3"></div>
                    			</div>
								<div class="table-responsive">
									<table
										class="table table-striped table-bordered table-hover dataTables-example">
										<thead>
											<tr>
												<th>Patente</th>
												<th>N.Int</th>
	                                            <th>Marca</th>
	                                            <th>Modelo</th>
	                                            <th>Tipo Equipo</th>
	                                            <th>Fecha Ultimo Registro</th>
	                                            <th>Horometro (${rform.opt_ver_text})</th>	
	                                            <th title="Tipo Horometro">Tipo Horometro</th>                                              
	                                            <th>Estado</th>                                            
											</tr>
										</thead>
									 <tbody>
				   					 <c:if test="${not  empty rlist }">
										<c:forEach items="${rlist}" var="r" varStatus="count" >											
			                               	<tr>
		                                       <td>${r.patente}</td>
		                                       <td>${r.nrointerno}</td>
		                                       <td>${r.marca}</td>
		                                       <td>${r.modelo}</td>
		                                       <td>${r.tipo_equipo_str}</td>	
		                                       <td><fmt:formatDate value="${r.fecha}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
		                                       <td>${r.horometro_str}</td>
		                                       <td>${r.tipo_horometro}</td>			                                           
		                                       <td>
		                                       		<c:if test="${r.estado == 1 }">
		                                       			<span class="badge badge-primary"> &nbsp;&nbsp;  </span>
		                                       		</c:if>
		                                       		<c:if test="${r.estado == 0 }">
		                                       			<span class="badge badge-danger">  &nbsp;&nbsp; </span>
		                                       		</c:if>					                                       
		                                       </td>                                         
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

     <!-- hora -->
      <script src="resources/inspinia_v2.9/FullVersion/js/plugins/clockpicker/clockpicker.js"></script>

    <!-- select 2 -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/select2/select2.full.min.js"></script>

      <!-- Sweet alert -->
    <script src="resources/inspinia_v2.9/FullVersion/js/plugins/sweetalert/sweetalert.min.js"></script>

    <!-- funciones de validacion de hora, efecto load para botones de busqueda y carga de pagina -->
       <%@include file="utiljs/loadpagebuttonjs.jsp"%>
       <%@include file="utiljs/valclockpickerjs.jsp"%>
       <%@include file="utiljs/validarFechaHoraJs.jsp"%>

    <script>

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


     var URLactual =window.location.protocol +"//"+ window.location.host;

    $(document).ready(function(){

   	     $( "#faenas" ).select2( {
   				placeholder: "Seleccione Faena",
   				 allowClear: true
   			} );

	     $(document).on("click", ".btn_submit", function(e){
  		if($("#faenas").val()==""){
  	    	swal("", "Debe seleccionar una Faena ", "error"); $("#faena").focus();
  	    	return  false;
  	    }
  	    else{
     		$("#data_1").submit();
   	    }
  	});

	     var buttonCommon = {
	    	        exportOptions: {
	    	            format: {
	    	                body: function ( data, row, column, node ) {
	    	               //console.log(node,row,column,data,data.replace( /\./gi, '' ),data.replace( /\./gi, '' ).replace( /\,/gi, '.' ) ,data.replace( /\,/gi, '.' ))
	    	    			//return column === 6 ? data.replace( /\./gi, '' ).replace( /\,/gi, '.' ) : data;
	    	    			if (column === 6){	    	    				
	    	    				data = data.replace( /\./gi, '' ).replace( /\,/gi, '.' );
	    	    				}	    	    			
							if (column === 8){	    	    				
	    	    				if (data.search('primary')>0){
	    	    					data = "Activo";
	    	    				}
	    	    				else{
	    	    					data = "Inactivo";
	    	    				}
							}	    	    			
	    	    			return data;	    	    		
	    		              }
	    		            }
	    		        }
	    		    };

        $('.dataTables-example').DataTable({
        	"language": {
                "url": URLactual + "/sgomtweb/resources/datetableespanil.json"
            },
        	pageLength: 10,
            //"order": [[ 0, "asc" ]],
            //"order": false,
            responsive: true,
            dom: '<"html5buttons"xï¿½B>lTfgitp',
            buttons: [
                {extend: 'copy'},
                $.extend( true, {}, buttonCommon, {
                    extend: 'csv', title: 'Reporte Horometro'
                } ),
                $.extend( true, {}, buttonCommon, {
                    extend: 'excelHtml5', title: 'Reporte Horometro'
                } ),
                {extend: 'pdf', title: 'Reporte Horometro'},

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
        
        $('.dataTables-example tbody').on( 'click', 'tr', function () {
            $(this).toggleClass('selected');
        } );

    });


    </script>


</body>

</html>
