
var Controller = "DsUsuariosController";

function autenticar(form)
{ 
  //ajax
  var objParam = new Object();
  
  objParam.user = form.user.value;
  objParam.password = form.password.value.replace("'","''");
  objParam.action="autentify";
          
  $.ajax({
      type : "POST",
      url : Controller, // Llamamos al servlet UsuarioController                 
      data: $.toJSON(objParam),// PARSEAMOS EL OBJETO jsoN A STRING Y LO MANDAMOS
      success : autenticarRes,
      error : function (XMLHttpRequest, textStatus, errorThrown) {
          alert(errorThrown)
      }
  });
} 

function autenticarRes(obj,result)
{
  if(obj!="")
  {
    alert("Bienvenid@ "+obj[0].user);
    
    document.frmData.nombre.value = obj[0].oid;
    document.frmData.perfil.value = obj[0].id_perfil;
  }
  else
  {
    alert("Datos de usuario incorrectos")
  }
}

function pressKey()
{
  if(event.keyCode==13)// press ENTER   
    autenticar(document.frmData);
}
