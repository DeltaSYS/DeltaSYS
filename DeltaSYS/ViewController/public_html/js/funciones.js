// JavaScript Document

// 

 // validaciones

function validacion(e,t)
{
  if(isDefinedAndNotNull(t))
  {
    switch(t)
    {
      case 'letras':
        e.onkeypress= function(){ return soloLetras(event);}
        break;
      case 'numeros':
        e.onkeypress= function(){return numeros(event);}
        break;
      case 'dinero':
        e.onkeypress= function(){return dinero(event);}
        e.onblur= function(){if(e.value=='')e.value=0;}
        e.onfocus= function(){if(e.value==0)e.value='';}
        break;
      case 'alfanumerico':
        e.onkeypress= function(){return alfanumerico(event);}
        break;
      case 'email':
        e.onkeypress= function(){return email(event);}
        break;
      case 'date':
        e.onkeypress= function(){return date(event);}
        break;
      default:
        break;
    } 
  }
}

// Solo numeros y decimales (con punto decimal)
function decimal(e, field) {
  key = e.keyCode ? e.keyCode : e.which
  // backspace
  if (key == 8) return true
  // 0-9
  if (key > 47 && key < 58) 
  {
    if (field.value == "") return true
    regexp = /.[0-9]{2}$/
    return !(regexp.test(field.value))
  }
// .
    if (key == 46) {
    if (field.value == "") return false
    regexp = /^[0-9]+$/
    return regexp.test(field.value)
    }
// other key
return false
}

// Solo numeros
function numeros(evt)
{
var charCode = (evt.which) ? evt.which : event.keyCode
if (charCode > 31 && (charCode < 48 || charCode > 57))
	return false;
 
return true;
}


// Solo numeros . decimal y , de miles
function dinero(evt)
{
var charCode = (evt.which) ? evt.which : event.keyCode

if (charCode > 31 && (charCode < 48 || charCode > 57)&&(charCode != 46))
	return false;
 
return true;
}


//Solo letras
function soloLetras(e) {
tecla = (document.all) ? e.keyCode : e.which;
if (tecla==8) return true;
patron =/[A-Za-z\s]/;
te = String.fromCharCode(tecla);
return patron.test(te);
} 

//Solo alfanumerico
function alfanumerico(e) {
tecla = (document.all) ? e.keyCode : e.which;
if (tecla==8) return true;
patron = /[A-Za-z0-9_]/;
te = String.fromCharCode(tecla);
return patron.test(te);
} 

// email
function email(e) {
tecla = (document.all) ? e.keyCode : e.which;
if (tecla==8) return true;
patron = /[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/;
te = String.fromCharCode(tecla);
return patron.test(te);
} 

// date
function date(e) {
tecla = (document.all) ? e.keyCode : e.which;
if (tecla==8) return true;
patron = /^\d{1,2}\/\d{1,2}\/\d{2,4}$/;
te = String.fromCharCode(tecla);
return patron.test(te);
} 


// ocultar - mostrar 
function mostrar_ocultar(hide) {
  if (document.layers)
  document.automatico.visibility = hide ? 'hide' : 'show';
  else {
  var g = document.all ? document.all.contenido :
  document.getElementById('automatico');
  g.style.visibility = hide ? 'visible' : 'hidden';
  
  var h = document.all ? document.all.contenido :
  document.getElementById('manual');
  h.style.visibility = hide ? 'hidden' : 'visible';
  }
}