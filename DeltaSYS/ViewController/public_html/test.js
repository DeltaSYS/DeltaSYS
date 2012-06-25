
var Controller = "DsSectoresController";

function consultaSectores()
{ 
  //ajax
  var objParam = new Object();
  objParam.action="select";
          
  $.ajax({
      type : "POST",
      url : Controller, // Llamamos al servlet UsuarioController                 
      data: $.toJSON(objParam),// PARSEAMOS EL OBJETO jsoN A STRING Y LO MANDAMOS
      success : consultaSectoresRes,
      error : function (XMLHttpRequest, textStatus, errorThrown) {
          alert(errorThrown)
      }
  });
} 

function consultaSectoresRes(obj,result)
{    
    jsonInfracciones = $.evalJSON($.toJSON(obj));
    
    var str = "<table>";
        
    for(var i = 0; i < jsonInfracciones.length;i++)
    {
        var jsonInfraccion = jsonInfracciones[i];
    
        str += "        <tr>";
        str += "            <td index='"+jsonInfraccion.id_sector+"' onclick='consultaOficiales(this);'>"+jsonInfraccion.sector+"</td>";
        str += "        </tr>";
    }
    
    str += "</table>";   
    
    $("#dvSectores").html(str);
}

consultaSectores();

function consultaOficiales(objSector)
{ 
  //ajax
  var objParam = new Object();
  objParam.action="select";
  
  var id_sector = $(objSector).attr("index");
  
  objParam.id_sector=id_sector;
          
    Controller = "DsSectorOficialController";
  $.ajax({
      type : "POST",
      url : Controller, // Llamamos al servlet UsuarioController                 
      data: $.toJSON(objParam),// PARSEAMOS EL OBJETO jsoN A STRING Y LO MANDAMOS
      success : consultaOficialesRes,
      error : function (XMLHttpRequest, textStatus, errorThrown) {
          alert(errorThrown)
      }
  });
} 

function consultaOficialesRes(obj,result)
{    
    jsonInfracciones = $.evalJSON($.toJSON(obj));
    
    var str = "<table>";
        
    for(var i = 0; i < jsonInfracciones.length;i++)
    {
        var jsonInfraccion = jsonInfracciones[i];
    
        str += "        <tr>";
        str += "            <td index='"+jsonInfraccion.oid+"' onclick='consultaUbicaciones(this);'>"+jsonInfraccion.user+"</td>";
        str += "        </tr>";
    }
    
    str += "</table>";   
    
    $("#dvOficiales").html(str);
}


function consultaUbicaciones(objOficial)
{ 
  //ajax
  var objParam = new Object();
  objParam.action="select";
  
  var oid = $(objOficial).attr("index");
  
  objParam.oid=oid;
  objParam.fecha_inicio="2012-06-20";
  objParam.fecha_fin="2012-06-26";
          
  Controller = "DsUbicacionesController";
    
  $.ajax({
      type : "POST",
      url : Controller, // Llamamos al servlet UsuarioController                 
      data: $.toJSON(objParam),// PARSEAMOS EL OBJETO jsoN A STRING Y LO MANDAMOS
      success : consultaUbicacionesRes,
      error : function (XMLHttpRequest, textStatus, errorThrown) {
          alert(errorThrown)
      }
  });
} 

function consultaUbicacionesRes(obj,result)
{    
    jsonInfracciones = $.evalJSON($.toJSON(obj));
    
    var str = "<table>";
        
    for(var i = 0; i < jsonInfracciones.length;i++)
    {
        var jsonInfraccion = jsonInfracciones[i];
    
        str += "        <tr>";
        str += "            <td>"+jsonInfraccion.latitud+","+jsonInfraccion.longitud+"</td>";
        str += "        </tr>";
    }
    
    str += "</table>";   
    
    $("#dvUbicaciones").html(str);
}