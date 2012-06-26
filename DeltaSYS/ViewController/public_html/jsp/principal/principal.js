
var Controller = "DsUsuariosController";

$(document).ready(function(){

    $("#btnSubmit").click(function (e)
    {       
      //ajax
      e.preventDefault();
      var objParam = {};
      
      form = $("#login form")[0];      
      objParam.oid = form.oid.value;
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
    })

});


function autenticarRes(obj,result)
{

    if(obj!="")
    {
        var loggedon = obj[0].loggedon;
    
        if(loggedon=="yes")
        {
        
            alert("Bienvenid@ "+obj[0].user);
            
            /*document.frmData.nombre.value = obj[0].oid;
            document.frmData.id_perfil.value = obj[0].id_perfil;
            document.frmData.perfil.value = obj[0].perfil;
            document.frmData.loggedon.value = obj[0].loggedon;*/
            
            var rol = obj[0].id_perfil;
            var urlDeRol="supervisor.jsp";
            
            
            if(rol == "1")
                urlDeRol="Subdirector.jsp";
                
            urlDeRol = "jsp/"+ urlDeRol;
            
            $.ajax({
                  type : "GET",
                  url : urlDeRol, // Llamamos al servlet UsuarioController                 
                  success : cargaPantalla,
                  error : function (XMLHttpRequest, textStatus, errorThrown) {
                      alert(errorThrown)
                  }
              });
            
            return;
        }
    }
  
    //document.frmData.loggedon.value = "no";
    alert("Datos de usuario incorrectos");
}

function cargaPantalla(htmlPantalla)
{

    $("#content").html(htmlPantalla);
    
    $("#menuGeneral li").click(function(){             
        idMenuElegido = this.id;
        
        
        urlDeRol =  "jsp/"+idMenuElegido+"/"+ idMenuElegido+".jsp";
            
        $.ajax({
              type : "GET",
              url : urlDeRol, // Llamamos al servlet UsuarioController                 
              success : cargaSubPantalla,
              error : function (XMLHttpRequest, textStatus, errorThrown) {
                  alert(errorThrown)
              }
        });
        loadDynamicJS(urlDeRol.replace(".jsp",".js"))            
        
     });
}


function cargaSubPantalla(htmlPantalla)
{

    $("#contenedorOpciones").html(htmlPantalla);
}
