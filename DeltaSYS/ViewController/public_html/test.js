
var Controller = "DSInfraccionesController";

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
    alert($.toJSON(obj));
}
