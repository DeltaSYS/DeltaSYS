
function jsLoaded()
{
    $("#fecha").val(stoday);
}

function consultaCancelado()
{
    var objParam = {};
    
    var id_folio = $("#folio").val();
    
    if(id_folio=="")
    {
        $("#folio").focus();
        return;
    }
    
    objParam.action="select";
    objParam.id_folio= id_folio;
    
    Controller = "DsCancelacionesController";
    
      $.ajax({
          type : "POST",
          url : Controller, // Llamamos al servlet UsuarioController                 
          data: $.toJSON(objParam),// PARSEAMOS EL OBJETO jsoN A STRING Y LO MANDAMOS
          success : consultaCanceladoRes,
          error : function (XMLHttpRequest, textStatus, errorThrown) {
              alert(errorThrown)
          }
      });
}

function consultaCanceladoRes(obj,result)
{
    if(obj.cancelado=="si")
    {
        alert("El folio de infracción no puede ser cancelado");
    }  
    else
    {
        cancelarInfraccion();
    }
}


function cancelarInfraccion()
{
    var objParam = {};
    
    var id_folio = $("#folio").val();
    var folio_oficio = $("#folio_oficio").val();
    folio_oficio = folio_oficio==""?0:folio_oficio;
    
    objParam.action="insert";
    objParam.id_folio= id_folio;
    objParam.folio_oficio= folio_oficio;
    objParam.fecha= $("#fecha").val();
    
    Controller = "DsCancelacionesController";
    
      $.ajax({
          type : "POST",
          url : Controller, // Llamamos al servlet UsuarioController                 
          data: $.toJSON(objParam),// PARSEAMOS EL OBJETO jsoN A STRING Y LO MANDAMOS
          success : cancelarInfraccionRes,
          error : function (XMLHttpRequest, textStatus, errorThrown) {
              alert(errorThrown)
          }
      });
}

function cancelarInfraccionRes(obj,result)
{
    alert("Infracción cancelada");
}