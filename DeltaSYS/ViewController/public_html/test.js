
var Controller = "DsUsuariosController";

$("select, input:text, input:button").uniform();

initForms();
var pkCat = null;

function cancelar()
{
  reloadPantalla();
  $.uniform.update("select");
}

function clickTabla(pk)
{
  pkCat = pk;
  
  GI("cidespecie").value = pkCat.Idespecie;
  GI("cnombre").value = pkCat.Nombre;
  
  SA(GI("btnAceptar"),"onclick","");
  
  lockForm("frmMantenimiento");
  $.uniform.update("input:button");
}
// Acciones Catalogo--------------------

function nuevo()
{
    $("#frmMantenimiento").validate({
        submitHandler: function(form) {
            nuevoValidado();
        }
    }); 
  $("#frmMantenimiento").submit();
}

function nuevoValidado() 
{      
    var objParam={
        action:"insert",
        parameters:
        {
            Form:$('#frmMantenimiento').serializeObject()
        }
    }
     $.ajax({
            type : "POST",
            url : Controller, // Llamamos al servlet CotizadorController                 
            data: $.toJSON(objParam),// PARSEAMOS EL OBJETO jsoN A STRING Y LO MANDAMOS
            success :nuevoRes,
            error : function (XMLHttpRequest, textStatus, errorThrown) {
                alert(errorThrown)
            }
        }); 
}

function nuevoRes(obj,result)
{
  if(isError(obj)){ return;}
  
  alert("¡Se ha dado de alta un nuevo registro!");
  reloadPantalla();
}

function modificar()
{
    $("#frmMantenimiento").validate({
        submitHandler: function(form) {
            modificarValidado();
        }
    }); 
  $("#frmMantenimiento").submit();
}

function modificarValidado() 
{      
    var objParam={
        action:"update",
        parameters:
        {
            Form:$('#frmMantenimiento').serializeObject()
        }
    }
     $.ajax({
            type : "POST",
            url : Controller, // Llamamos al servlet CotizadorController                 
            data: $.toJSON(objParam),// PARSEAMOS EL OBJETO jsoN A STRING Y LO MANDAMOS
            success :modificarRes,
            error : function (XMLHttpRequest, textStatus, errorThrown) {
                alert(errorThrown)
            }
        }); 
}

function modificarRes(obj,result)
{
  if(isError(obj)){ return;}
  
  alert("¡Se ha modificado el registro!");
  reloadPantalla();
}

function borrar()
{
   var objParam={
        action:"delete",
        parameters:
        {
            Form:$('#frmMantenimiento').serializeObject()
        }
    }
     $.ajax({
            type : "POST",
            url : Controller, // Llamamos al servlet CotizadorController                 
            data: $.toJSON(objParam),// PARSEAMOS EL OBJETO jsoN A STRING Y LO MANDAMOS
            success :borrarRes,
            error : function (XMLHttpRequest, textStatus, errorThrown) {
                alert("¡No se puede eliminar registro por integridad de datos!")
            }
        }); 
}

function borrarRes(obj,result)
{
  if(isError(obj)){ return;}
  
  alert("¡Se ha eliminado el registro!");
  reloadPantalla();; // limpia formulario
}

// Acciones Catalogo--------------------

// Botones------------------------------

function borrarbtn()
{
  if(pkCat==null)
  {
    alert("¡Seleccione registro!");
    return;
  }  
  
   if(!confirm("¿Está seguro que desea borrar este registro?"))
    return;
    
    borrar();
}

function modificarbtn()
{
  if(pkCat==null)
  {
    alert("¡Seleccione registro!");
    return;
  }  

  unlockElements("btnAceptar,btnCancelar");
  unlockForm("frmMantenimiento");
  SA(GI("btnAceptar"),"onclick","modificar()");
  
  $.uniform.update("input:button");
}

function nuevobtn()
{
  RF(GI("frmMantenimiento"));
  unlockElements("btnAceptar,btnCancelar");
  
  SA(GI("btnAceptar"),"onclick","nuevo()");
  unlockForm("frmMantenimiento");
  
  $.uniform.update("input:button");
  
  RA(GI("cidespecie"),"skip");
  loadElement(GI("cidespecie"));
}
