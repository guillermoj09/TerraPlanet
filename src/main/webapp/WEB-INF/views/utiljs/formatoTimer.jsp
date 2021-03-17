	 <script>
 <%-- se necesita incluir la libreria moment en la pagina
 Para la version java esta en Utilservice --%>
 function segundosToTimer2(segundos){ //deprecated
	 
	 var now = moment("01 00:00:00", "DD HH:mm:ss");
     var now2 = moment(now).add(segundos, 'seconds');
     if(segundos >= 86400){
    // var now3 = moment(now2).subtract(1, 'day');
     var now3 = moment(now2);
     
     var dia = moment(now3).get('date');
     
     nowfinal = dia+"d "+now3.format("HH:mm:ss");
     }else{
     nowfinal = now2.format("HH:mm:ss");
     }
     //moment({ day:0, minute:10 });
     return nowfinal;
     //alert(nowfinal);
	 
 }
 
 function segundosToTimer(seconds)
 {
     var numdays = Math.floor(seconds / 86400);
     var numhours = Math.floor((seconds % 86400) / 3600);
     var numminutes = Math.floor(((seconds % 86400) % 3600) / 60);
     var numseconds = ((seconds % 86400) % 3600) % 60;
     
     //if(numdays<10) { numdays = "0"+numdays;}
     if(numhours<10) {numhours = "0"+numhours;}
     if(numminutes<10) {numminutes = "0"+numminutes;}
     if(numseconds<10) {numseconds = "0"+numseconds;}
  
     if(seconds >= 86400){
     return numdays + "d " + numhours + ":" + numminutes + ":" + numseconds + ":";
     }else{
    	 
     return numhours + ":" + numminutes + ":" + numseconds + ":"; 
     }
 }
 <%-- javascrip puro, no work
 /* var date = new Date(null);                
 date.setMonth(0); 
 date.setDate(0);
 date.setHours(0);
 date.setMinutes(0);
 date.setMilliseconds(0);
 date.setSeconds(TotalTaller); //TotalTaller                  
 var TotalTallermin = date.toISOString().substr(11, 8);
 
if(date.getDate()>1){
  TotalTallermin = date.getDate() + " "+ date.getHours()+ ":" + date.getMinutes()+ ":" + date.getSeconds();
}*/

/*  var segundo = 76400;

var now = moment("01 00:00:00", "DD HH:mm:ss");
var now2 = moment(now).add(segundo, 'seconds');
if(segundo >= 86400){
var now3 = moment(now2).subtract(1, 'day');
nowfinal = now3.format("DD HH:mm:ss");
}else{
nowfinal = now2.format("HH:mm:ss");
}
//moment({ day:0, minute:10 });
alert(nowfinal);*/

/* var date2 = new Date(null);
date2.setSeconds(pageTotal); 
var pageTotalm = date2.toISOString().substr(11, 8); */

/*var date3 = new Date(null);
date3.setSeconds(Total); 
var Totalm = date3.toISOString().substr(11, 8);*/

 --%>
 </script>