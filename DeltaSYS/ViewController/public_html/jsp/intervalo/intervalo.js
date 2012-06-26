
function jsLoaded()
{
    $("#fecha").val(stoday);
    $("#responsable").val(global_oid);
    
    recuperaIntervalo();
}

function recuperaIntervalo()
{

    var objParam = {};
    
    objParam.action="select";
    
    Controller = "DsIntervaloController";
    
      $.ajax({
          type : "POST",
          url : Controller, // Llamamos al servlet UsuarioController                 
          data: $.toJSON(objParam),// PARSEAMOS EL OBJETO jsoN A STRING Y LO MANDAMOS
          success : recuperaIntervaloRes,
          error : function (XMLHttpRequest, textStatus, errorThrown) {
              alert(errorThrown)
          }
      });
}

function recuperaIntervaloRes(obj,result)
{
    $("#tiempo").val(obj.intervalo);  
}

function actualizaIntervalo()
{

    var objParam = {};
    var tiempo = $("#tiempo").val();

    if(tiempo=="")
        tiempo = 15; // default
    
    
    $("#tiempo").val(tiempo);
    
    objParam.action="update";
    objParam.intervalo= $("#tiempo").val();;
    objParam.oid= $("#responsable").val();
    objParam.fecha= $("#fecha").val();
    
    
    Controller = "DsIntervaloController";
    
      $.ajax({
          type : "POST",
          url : Controller, // Llamamos al servlet UsuarioController                 
          data: $.toJSON(objParam),// PARSEAMOS EL OBJETO jsoN A STRING Y LO MANDAMOS
          success : actualizaIntervaloRes,
          error : function (XMLHttpRequest, textStatus, errorThrown) {
              alert(errorThrown)
          }
      });
}

function actualizaIntervaloRes(obj,result)
{
    alert("Intervalo establecido");  
}