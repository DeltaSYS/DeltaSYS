
var Controller = "DSInfraccionesController";
var jsonInfracciones = null;

function consultar(form)
{ 
  //ajax
  var objParam = new Object();
  
  var num_placa = form.placa_infraccion.value;
  
  objParam.id_folio = isNaN(num_placa)?0:num_placa;
  objParam.num_placa = form.placa_infraccion.value;
  
  objParam.action="select";
          
  $.ajax({
      type : "POST",
      url : Controller, // Llamamos al servlet UsuarioController                 
      data: $.toJSON(objParam),// PARSEAMOS EL OBJETO jsoN A STRING Y LO MANDAMOS
      success : consultarRes,
      error : function (XMLHttpRequest, textStatus, errorThrown) {
          alert(errorThrown)
      }
  });
} 

function consultarRes(obj,result)
{      
    jsonInfracciones = $.evalJSON($.toJSON(obj));
    
    var strInfracciones = "<table>";
    strInfracciones += "        <tr>";
    strInfracciones += "            <th>Folio(s)</th>";
    strInfracciones += "        </tr>";
                             
    for(var i = 0; i < jsonInfracciones.length;i++)
    {
        var jsonInfraccion = jsonInfracciones[i];
    
        strInfracciones += "        <tr>";
        strInfracciones += "            <td index='"+i+"' onclick='detalle(this);'>"+jsonInfraccion.id_folio+"</td>";
        strInfracciones += "        </tr>";
    }
    
    strInfracciones += "</table>";   
    
    $("#dvFolios").html(strInfracciones);
}


function detalle(obj)
{
    var index = obj.getAttribute("index");
    
    var jsonInfraccion = jsonInfracciones[index];
    
    $("#folio").val(jsonInfraccion.id_folio);
    
    var fecha = jsonInfraccion.fecha.date+"/";
    fecha += jsonInfraccion.fecha.month+"/";
    fecha += Number(jsonInfraccion.fecha.year)+1900;
    
    $("#fecha").val(fecha);
    $("#placa").val(jsonInfraccion.num_placa);
    $("#motivo").val(jsonInfraccion.motivo);
    
    var fundamento = "Art. "+jsonInfraccion.fundamento_articulo;
    fundamento += " Frac. "+jsonInfraccion.fundamento_fraccion;
    fundamento += " Inciso "+jsonInfraccion.fundamento_inciso;
    
    $("#fundamento").val(fundamento);
    $("#sancion").val(jsonInfraccion.fundamento_sancion);

}