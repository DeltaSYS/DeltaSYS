
var Controller = "DSFoliosController";

function consultar(form)
{ 
  //ajax
  var objParam = new Object();
  
  var num_folios = form.num_folios.value;
  objParam.num_folios = isNaN(num_folios)?0:num_folios;
  
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
