
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
    jsonInfracciones = $.toJSON(obj);
    
    var strInfracciones = "<table>";
    strInfracciones += "        <tr>";
    strInfracciones += "            <th>Folio(s)</th>";
    strInfracciones += "        </tr>";
                             
    for(var i =0; i<jsonInfracciones.size();i++)
    {
        var jsonInfraccion = jsonInfracciones.get(i);
    
        strInfracciones += "        <tr>";
        strInfracciones += "            <td valor='"+jsonInfraccion.id_folio+"' onclick='detalle(this);'>"+jsonInfraccion.id_folio+"</td>";
        strInfracciones += "        </tr>";
    }
    
    var strInfracciones = "</table>";   
}


function detalle(obj)
{
    alert(obj.getAttribute("valor"));
}