
function menu(titulo,status)
{
  this.titulo = titulo;
  this.submenu = new Array();
  this.status = status;
  return this;
}

function submenu(titulo,url,status)
{
  this.titulo = titulo;
  this.url = url;
  this.operacion = 0;// Alta - 1, Baja - 2, Consulta -3, Normal - 0
  this.status = status;
  return this;
}

function submenu(titulo,url,operacion,status)
{
  this.titulo = titulo;
  this.url = url;
  this.operacion = operacion;// Alta - 1, Baja - 2, Consulta -3, Normal - 0
  this.status = status;
  return this;
}

var currentMenu;
function cargaMenu()// carga arreglos menu
{
  sivetMenu = new Array();
  
  sivetMenu[0] = new menu('Expedientes','ACTIVO');
    sivetMenu[0].submenu[0] = new submenu('Nuevo','jsp/Expediente/Expediente/Expediente',1,'ACTIVO');
    sivetMenu[0].submenu[1] = new submenu('Modificar','jsp/Expediente/Expediente/Expediente',2,'ACTIVO');
    sivetMenu[0].submenu[2] = new submenu('Consultar','jsp/Expediente/Expediente/Expediente',3,'ACTIVO');
    sivetMenu[0].submenu[3] = new submenu('Prestamo','jsp/Expediente/Prestamo/Prestamo',0,'ACTIVO');
    sivetMenu[0].submenu[4] = new submenu('Devolución','jsp/Expediente/Devolucion/Devolucion',0,'ACTIVO');
    sivetMenu[0].submenu[5] = new submenu('Remitir','jsp/Expediente/Remitir/Remitir',0,'ACTIVO');
  
  sivetMenu[1] = new menu('Catálogos','ACTIVO');
    sivetMenu[1].submenu[0] = new submenu('Secciones','jsp/Catalogos/Secciones/Secciones',0,'ACTIVO');
    sivetMenu[1].submenu[1] = new submenu('Especies','jsp/Catalogos/Especies/Especies',0,'ACTIVO');
    sivetMenu[1].submenu[2] = new submenu('Razas','jsp/Catalogos/Razas/Razas',0,'ACTIVO');
    sivetMenu[1].submenu[3] = new submenu('Generos','jsp/Catalogos/Generos/Generos',0,'ACTIVO');
    sivetMenu[1].submenu[4] = new submenu('Descuentos','jsp/Catalogos/Descuentos/Descuentos',0,'ACTIVO');
    sivetMenu[1].submenu[5] = new submenu('Estados','jsp/Catalogos/Estados/Estados',0,'ACTIVO');
    sivetMenu[1].submenu[6] = new submenu('Delegación/Municipio','jsp/Catalogos/DelegacionMunicipio/DelegacionMunicipio',0,'ACTIVO');
    //sivetMenu[1].submenu[7] = new submenu('Enfermedades','',0,'ACTIVO'); // Desarrollo Posterior
    sivetMenu[1].submenu[7] = new submenu('Tipos Medico','jsp/Catalogos/TiposMedico/TiposMedico',0,'ACTIVO');
    sivetMenu[1].submenu[8] = new submenu('Tipos Usuario','jsp/Catalogos/TiposUsuario/TiposUsuario',0,'ACTIVO');
    sivetMenu[1].submenu[9] = new submenu('Status','jsp/Catalogos/Status/Status',0,'ACTIVO');
    
    sivetMenu[2] = new menu('Administración','ACTIVO');
    sivetMenu[2].submenu[0] = new submenu('Usuarios','jsp/Administracion/Usuarios/Usuarios',0,'ACTIVO');
    sivetMenu[2].submenu[1] = new submenu('Personal','jsp/Administracion/Personal/Personal',0,'ACTIVO');
    sivetMenu[2].submenu[2] = new submenu('Medico/Sección','jsp/Administracion/MedicoSeccion/MedicoSeccion',0,'ACTIVO');
    sivetMenu[2].submenu[3] = new submenu('Consulta','jsp/Administracion/Consulta/Consulta',0,'ACTIVO');
    //sivetMenu[2].submenu[2] = new submenu('Personal Académico','',0,'ACTIVO');
    //sivetMenu[2].submenu[3] = new submenu('Personal Administrativo','',0,'ACTIVO');
    
    switch(eval(tipo))
    {
      case 1: // ADMINISTRADOR
        //sivetMenu[1].status = "BLOQUEADO";
        //sivetMenu[2].status = "BLOQUEADO";
        break;
      case 2: // ARCHIVO
        sivetMenu[0].submenu[1].status = "BLOQUEADO";
        sivetMenu[0].submenu[2].status = "BLOQUEADO";
        
        sivetMenu[1].status = "BLOQUEADO";
        sivetMenu[2].status = "BLOQUEADO";
        break;
      case 3: // CAJA
        
        sivetMenu[0].submenu[1].status = "BLOQUEADO";
        sivetMenu[0].submenu[2].status = "BLOQUEADO";
        sivetMenu[0].submenu[3].status = "BLOQUEADO";
        sivetMenu[0].submenu[4].status = "BLOQUEADO";
        
        sivetMenu[1].status = "BLOQUEADO";
        sivetMenu[2].status = "BLOQUEADO";
        break;
      case 8: // RECEPCION
        
        sivetMenu[0].submenu[1].status = "BLOQUEADO";
        sivetMenu[0].submenu[3].status = "BLOQUEADO";
        sivetMenu[0].submenu[4].status = "BLOQUEADO";
        
        sivetMenu[1].status = "BLOQUEADO";
        sivetMenu[2].status = "BLOQUEADO";
        break;
      default: // SIN CLASIFICAR
        sivetMenu[0].status = "BLOQUEADO";
        sivetMenu[1].status = "BLOQUEADO";
        sivetMenu[2].status = "BLOQUEADO";
        break;
    }
    
  grabaMenu(sivetMenu);
}

function grabaMenu(menu) // graba menus del arreglo
{
  var sMenu = "<table cellspacing='0' cellpadding='0'><tr>";
  for(i=0;i<menu.length;i++)
  {
    sMenu += "<td id='menu_"+i+"' width='"+(menu[i].titulo.length*14)+"' status='"+sivetMenu[i].status+"' class='menu"+sivetMenu[i].status+"' classOriginal='menu"+sivetMenu[i].status+"' seleccionado='NO' onmouseover='menuOverOut(this)' onmouseout='menuOverOut(this)' onclick='grabaSubMenu(sivetMenu,"+i+")'>"+menu[i].titulo+"</td>"; 
  }
  sMenu += "</tr></table>";
  GI('dvMenu').innerHTML = sMenu;
}

function grabaSubMenu(menu,index)// graba los submenus del arreglo
{
  
  if(GA(GI('menu_'+index),'seleccionado')!='SI'&&GA(GI('menu_'+index),'status')=='ACTIVO')
  {
    seleccionaMenu('menu',menu,index,index);
    
    var submenu = menu[index].submenu;
    var sSubMenu = "<table cellspacing='0' cellpadding='0'><tr>";
    for(i=0;i<submenu.length;i++)
    {
      sSubMenu += "<td  id='submenu_"+i+"' width='"+(submenu[i].titulo.length*15)+"' status='"+submenu[i].status+"' class='submenu"+submenu[i].status+"' classOriginal='submenu"+submenu[i].status+"' seleccionado='NO' onmouseover='menuOverOut(this)' onmouseout='menuOverOut(this)' onclick='cargaPantallaMenu(sivetMenu,"+index+","+i+")' >"+submenu[i].titulo+"</td>"; 
    }
    sSubMenu += "</tr></table>";
    GI('dvSubMenu').innerHTML = sSubMenu;
  }
}

function cargaPantallaMenu(menu,indexMenu,indexSubMenu)// graba los submenus del arreglo
{
    
  if(GA(GI('submenu_'+indexSubMenu),'seleccionado')!='SI'&&GA(GI('submenu_'+indexSubMenu),'status')=='ACTIVO')
  {
    
    $("#dvPantalla").css("visibility","hidden");
    
    seleccionaMenu('submenu',menu,indexMenu,indexSubMenu);
    
    currentMenu = menu[indexMenu].submenu[indexSubMenu];
    pantalla = currentMenu.url;
    $.ajax({
            url: pantalla+".jsp",
            context: document.body,
            dataType: "HTML",
            success: successfulAjax
          });
    
  }
}

function successfulAjax(result)
{
    
  $("#dvPantalla").html(result);
  loadDynamicJS(pantalla+".js");
  
  
  
}

function menuOverOut(menuElement)// cambia estilo a accion del mouse over y out
{
   if(GA(menuElement,'seleccionado')=='SI')
    menuElement.className = GA(menuElement,'classOriginal')+'Over';
  else
  {
    if(menuElement.className==GA(menuElement,'classOriginal')+'Over')
      menuElement.className=GA(menuElement,'classOriginal');
    else
      menuElement.className=GA(menuElement,'classOriginal')+'Over';
  }
  
}

function seleccionaMenu(nivel,menu,index,index2)// selecciona texto del menu
{
  var arMenu;
  
  if(nivel=='menu')
    arMenu = menu;
  else if(nivel=='submenu')
    arMenu = menu[index].submenu;
  
  for(s=0;s<arMenu.length;s++)
  {
    if(s!=index2)
    {
      SA(GI(nivel+'_'+s),'seleccionado','NO');
      GI(nivel+'_'+s).className = nivel+GA(GI(nivel+'_'+s),"status");
    }
    
  }
  SA(GI(nivel+'_'+index2),'seleccionado','SI'); 
}

cargaMenu();