<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
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
  
</head>
<body class="top-navigation">

	<div id="wrapper">
	
	
	<%@include file="menu.jsp"%>
	
		 <div id="page-wrapper" class="gray-bg">
		 
		 
       <div class="row border-bottom">
       </div>
        
            
              <!-- Titulo del Menu  -->
          	<%@include file="barramenu.jsp"%>
        
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
        
        
            <div class="row">
			
			<div class="col-md-4">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <span class="label label-success pull-right">Dias</span>
                            <h5>Datos</h5>
                        </div>
                        <div class="ibox-content">
                            <h1 class="no-margins">10</h1>
                            <div class="stat-percent font-bold text-success">Producción del proceso al Viernes, 28 de septiembre de 2018 <i class="fa fa-bolt"></i></div>
                            <small>3.718 m3/día.</small>
                        </div>
                    </div>
                </div>
				
				   <div class="col-lg-12">
				   
				   <div class="ibox float-e-margins">
				   
					<!--	<h5>Diagrama Entrego por Talabre</h5> 	 -->
					<div class="ibox-title">
					 <h5>Diagrama Entrega</h5>
					<div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                    <i class="fa fa-wrench"></i>
                                </a>
                                <ul class="dropdown-menu dropdown-user">
                                    <li><a href="#">Config option 1</a>
                                    </li>
                                    <li><a href="#">Config option 2</a>
                                    </li>
                                </ul>
                                <a class="close-link">
                                    <i class="fa fa-times"></i>
                                </a>
                            </div>
						  </div>
                       <div class="ibox-content">						  
						<div id="myDiagramDiv"style="width:1100px; height:550px; background-color: #FFFFFF;">
					
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
    
    
    <!--  Libreria para diagrama GO  la resources/js/go.js  es la trial version, no se usa debug en pro-->
    <script src="resources/js/go2.js"></script>
    <script src="resources/js/go-debug2.js"></script>
    
      <script>
   
     // banderas ejemplo
   // function theNationFlagConverter(nation) {
     // return "https://www.nwoods.com/go/Flags/" + nation.toLowerCase().replace(/\s/g, "-") + "-flag.Png";
    //}
	
	//iconos de los paneles
	//Cambiar url a produccion
	//http://localhost:8080/sgomtweb/resources/img/iconos/
	var URLactual =window.location.protocol +"//"+ window.location.host;
	//salert(theIconoConverter('ejemplo'));
    function theIconoConverter(icono) {
      return URLactual+ "/sgomtweb/resources/img/iconos/" + icono.toLowerCase().replace(/\s/g, "-") + ".png";
    }
   
     //Contenido del texto 
     function theInfoTextConverter(info) {
      var str = "";
      if (info.title) str += "Movil 10 dias: " + info.title;
      if (info.headOf) str += "\n\nPeriodo Anterior: " + info.headOf;
      /*if (typeof info.boss === "number") {
        var bossinfo = myDiagram.model.findNodeDataForKey(info.boss);
        if (bossinfo !== null) {
          str += "\n\nReporting to: " + bossinfo.name;
        }
      }*/
      return str;
    }
   
var $ = go.GraphObject.make;
var myDiagram =
  $(go.Diagram, "myDiagramDiv",
    {
      "undoManager.isEnabled": true, // enable Ctrl-Z to undo and Ctrl-Y to redo
      layout: $(go.TreeLayout, // specify a Diagram.layout that arranges trees
                { angle: 0, layerSpacing: 45 })  //90 , 180 
    });
	
	
 myDiagram.nodeTemplate =
    $(go.Node, "Auto",
      $(go.Shape,
        { figure: "RoundedRectangle",
          fill: "white" },  // default Shape.fill value
        new go.Binding("fill", "color")),  // binding to get fill from nodedata.color
		
	/*
      $(go.TextBlock,
        { margin: 0, alignment: go.Spot.Left  },
        new go.Binding("text", "descripcion")),  // binding to get TextBlock.text from nodedata.key		// width: 60, height: 33,  column: 3 , new go.Margin(0, 40)
		// $(go.TextBlock, { text: "a Text Block", background: "lightblue" }),
      $(go.TextBlock,
        {  margin: 40, height: 80, width: 60, stroke: "white" ,  alignment: go.Spot.Center},
        new go.Binding("text", "descripcion2")),  
	  $(go.TextBlock,
        {  margin: 40, stroke: "white", alignment: go.Spot.Left},
        new go.Binding("text", "descripcion3")),  
		*/

		$(go.Panel, "Table",
          { margin: 6, maxSize: new go.Size(150, NaN) },
          // the two TextBlocks in column 0 both stretch in width
          // but align on the left side
		  
          $(go.RowColumnDefinition,
            {
              column: 0,
              stretch: go.GraphObject.Horizontal,
              alignment: go.Spot.Left
            }),
          // the name
          $(go.TextBlock,
            {
              row: 0, column: 0,
              maxSize: new go.Size(160, NaN), margin: 2,
              font: "500 16px Roboto, sans-serif",
              alignment: go.Spot.Top
            },
            new go.Binding("text", "name"),
			new go.Binding("stroke", "colorTitulo")
			),
          // the country flag
          $(go.Picture,
            {
              row: 0, column: 1, margin: 2,
              imageStretch: go.GraphObject.Uniform,
              alignment: go.Spot.TopRight
            },
            // 
            new go.Binding("desiredSize", "icono", function(){ return new go.Size(34, 26) }), //tamalo imagen
            new go.Binding("source", "icono", theIconoConverter)),
          // aqui agregar adicional informacion
          $(go.TextBlock,
            {
              row: 1, column: 0, columnSpan: 2,
              font: "12px Roboto, sans-serif"
            },
            //new go.Binding("text", "", theInfoTextConverter),
            new go.Binding("text", "", theInfoTextConverter),
			new go.Binding("stroke", "colorContenido")
			)
        )  // end Table Panel
      );  // end Node		
		
    
   //modelo del diagrama personalizado 
  var nodeDataArray = [
   { key: 0, name: "Produccion Lastre + Filtro", icono: "icons8-bloquear-64", title: "3.7", headOf: "2.2", color: "red" , colorTitulo: "white", colorContenido: "white"},
   { key: 1, name: "Caminones Totales", icono: "icons8-camión-contenedor-48", title: "21", headOf: "13", color: "grey", colorTitulo: "white", colorContenido: "white"  },
   { key: 2, name: "Eficiencia", icono: "icons8-gráfico-circular-40", title: "51%", headOf: "41%" , color: "grey", colorTitulo: "white", colorContenido: "white" },
   { key: 3, name: "Rendimiento", icono: "icons8-gráfico-circular-azul-40", title: "14.5", headOf: "12.9" , color: "grey", colorTitulo: "white", colorContenido: "white" },
   { key: 4, name: "Disponibilidad (% Horas Totales) ", icono: "icons8-gráfico-circular-40", title: "95%", headOf: "96%" },
   { key: 5, name: "Utilizacion Efectiva", icono: "icons8-gráfico-circular-40", title: "54%", headOf: "43%" },
   { key: 6, name: "Duracion ciclo (min)", icono: "icons8-tiempo-64", title: "90.3", headOf: "97.7" },
   { key: 7, name: "Carga (m3/camion)", icono: "icons8-camión-de-construcción-48", title: "20 m3", headOf: "20 m3" },
   { key: 8, name: "Carguio (min)", icono: "icons8-tiempo-30", title: "21.2", headOf: "19.2" },
   { key: 9, name: "Transporte (min)", icono: "icons8-tiempo-30", title: "60.1", headOf: "58.4" },
   { key: 10, name: "Descarga (min) ", icono: "icons8-tiempo-30", title: "9.10", headOf: "10.3" },
   { key: 11, name: "Distancia (km) ", icono: "icons8-marcadores-40", title: "40.5", headOf: "43.3" },
   { key: 12, name: "Velocidad (km/h) ", icono: "icons8-velocidad-40", title: "47.0", headOf: "42.6" }
	//{ key: 6, descripcion: "Utilizacion Efectiva ", color: "white" }
  ];
  
  //conexiones 
  var linkDataArray = [
    { from: 0, to: 1 },
	{ from: 0, to: 2 },
	{ from: 0, to: 3 },
	{ from: 2, to: 4 },
	{ from: 2, to: 5 },
	{ from: 3, to: 6 },
	{ from: 3, to: 7 },
	{ from: 6, to: 8 },
	{ from: 6, to: 9 },
	{ from: 6, to: 10 },
	{ from: 9, to: 11 },
	{ from: 9, to: 12 }
  ];
  
  //AGregar nuevo diagrama aparte
  /*var violetbrush = $(go.Brush, "Linear", { 0.0: "Violet", 1.0: "Lavender" });

   myDiagram.add(
    $(go.Node, "Auto",
      $(go.Shape, "Ellipse",
        { fill: violetbrush }),
      $(go.TextBlock, "Goodbye!",
        { margin: 5 }), 		
    )); */
  
  myDiagram.model = new go.GraphLinksModel(nodeDataArray, linkDataArray);

  //Habilitar y Deshabilidar 
  myDiagram.isEnabled = false; 
  
  </script>
    
    <script>
    
    //TABLA 
    /*
    $(document).ready(function(){
        $('.dataTables-example').DataTable({
            pageLength: 10,
            //"order": [[ 0, "asc" ]],
            "order": false,
            responsive: true,
            dom: '<"html5buttons"xºB>lTfgitp',
            buttons: [
                {extend: 'copy'},
                {extend: 'csv'},
                {extend: 'excel', title: 'Usuarios'},
                {extend: 'pdf', title: 'Usuarios'},

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
    		
  */
    </script>

    
</body>

</html>
