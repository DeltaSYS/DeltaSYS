// funciones básicas 

 $(document).ready(function () {
    $("select, input:text, input:button, input:checkbox, input:radio, input:password, input:submit").uniform();

});



function isArray(a) { return isObject(a) && a.constructor == Array }                              // Valida si el objeto es una instancia de Array
function isBoolean(a) { return typeof a == 'boolean' }                                            // Valida si el objeto es de tipo boolean
function isNull(a) { return typeof a == 'object' && !a }                                          // Valida si el objeto es nulo
function isFunction(a) { return typeof a == 'function' }                                          // Valida si el objeto es una funcion
function isNumber(a) { return (typeof a == 'number') || (a === 0) }                               // Valida si el objeto es de tipo number
function isObject(a) { return (typeof a == 'object' && !!a) || isFunction(a) }                    // Valida si el parametro es un objeto
function isString(a) { return a && (typeof a == 'string' || a.constructor == String) }            // Valida si el objeto es de tipo string
function isUndefined(a) { return (typeof a == 'undefined') }                                      // Valida si el objeto es no valido
function isDefined(a) { return (typeof a != 'undefined') }                                        // Valida si el objeto es no valido

$.metadata.setType("attr", "validate");
function isError(a)                                                                               // Valida si el objeto tiene parametro error en el
{
    var berror = (typeof a.error != 'undefined');
    if(berror){ alert("Error: "+a.error);}
    return  berror;
} 

function isErrorNonAlert(a)                                                                               // Valida si el objeto tiene parametro error en el
{
    var berror = (typeof a.error != 'undefined');
    return  berror;
} 



// json reports---
var ObjJson;

function reporte(jspfile)
{
  var link = document.link;
  var url = "jsp/Expediente/Reportes/"+jspfile; 
  SA(link,"action",url);
  
  link.fjson.value = ObjJson;
  
  link.submit();
}


//--- Menú -----

var sivetMenu;
var pantalla ;

function reloadPantalla()
{ 
   $.ajax({
            url: pantalla+".jsp",
            context: document.body,
            dataType: "HTML",
            success: successfulAjax
          });
}
//--------

function isDefinedAndNotNull(obj)
{
	return (isDefined(obj) && !isNull(obj));	
}
function GI(id)
{
	return document.getElementById(id);
}

function GA(el, atrrName)
{
	try { return el.getAttribute(atrrName); } catch(e) { return null; }
}

function SA(el, atrrName, attrValue) {
	try { el.setAttribute(atrrName, attrValue); return true; } catch(e) { return false; }
}

function RA(el, atrrName) {
  try { el.removeAttribute(atrrName); return true; } catch(e) { return false; }
}

function loadDynamicJS(jsFile)
{
  var script   = document.createElement('script');
  script.defer = true;
  script.src   = jsFile;
  document.getElementsByTagName('head')[0].appendChild(script); 
}



function initForms() {
  for(var i = 0; i < document.forms.length; i++) {
    var f = document.forms[i];
    if(f.name == "") continue;
    for(var j = 0; j < f.elements.length; j++) {
      if(loadElement(f.elements[j])) break;
    }
  }
}


function lockForm(idForm)
{
  for(var i = 0; i < document.forms.length; i++) 
  {
    var f = document.forms[i];
    if(f.id == idForm)
    {
      for(var j = 0; j < f.elements.length; j++) 
      {
        if(lockElement(f.elements[j])) break;
      }
    }
  }
}

function unlockForm(idForm)
{
  for(var i = 0; i < document.forms.length; i++) 
  {
    var f = document.forms[i];
    if(f.id == idForm)
    {
      for(var j = 0; j < f.elements.length; j++) 
      {
        if(!isDefinedAndNotNull(GA(f.elements[j],"pk")))// no es parte de la llave
          if(unlockElement(f.elements[j])) break;
      }
    }
  }
}


function lockForms()
{
  for(var i = 0; i < document.forms.length; i++) 
  {
    var f = document.forms[i];
    if(f.name == "") continue;
    for(var j = 0; j < f.elements.length; j++) 
    {
      if(lockElement(f.elements[j])) break;
    }
  }
}


function lockElements(strelements)
{
  for(w=0;w<strelements.split(",").length;w++)
    lockElement(GI(strelements.split(",")[w]));
}
function unlockElements(strelements)
{
  for(w=0;w<strelements.split(",").length;w++)
    unlockElement(GI(strelements.split(",")[w]));
}

function lockElement(e)
{
  if(!e.type.indexOf("text"))
  {
    SA(e,"readonly","readonly");
  }
  else
  {
    e.disabled=true;
  }
}
function unlockElement(e)
{
  if(!e.type.indexOf("text"))
  {
    RA(e,"readonly");
  }
  else
  {
    e.disabled=false;
  }
}

 
function loadElement(e) 
{

  var q = GA(e, "ref"); // Referencia del Query a ejecutar
  var m = GA(e, "fun"); // Nombre de la funcion a ejecutar
  var p = GA(e, "param"); // Variable con los parametros adicionales para el, los combos
  var t =  GA(e, "tipo"); // Variable con el tipo de campo (validación)
  var s = GA(e, "skip");
  
 if(isDefinedAndNotNull(q) && !isDefinedAndNotNull(s)) { 
  var name =q.split(".")[1];
  var controller =q.split(".")[0]+"Controller";
 // Variable con los parametros adicionales para el, los combos

    var fun = eval(m);
    
    // combos dinamicos --------
    var parents = GA(e, "parents"); // Variable con los parametros adicionales para los combos (hijos) que dependen de otros (padres)
    var loaded = GA(e, "loaded"); // cargado inicialmente
    if(isDefinedAndNotNull(parents))
    {
      // la cadena es parecida a la majedada por JSON
      var strJSONParents = (parents.replace('{','').replace('}','')); 
      var objJSONParents = strJSONParents.split(",");
      var objParents = new Array();
      var strJSON = parents;
      
      for(j=0;j<objJSONParents.length;j++)
      {
        var auxObjJSONParents = objJSONParents[j].split(":"); // [0] identificador en consulta , [1] id del combo padre
        objParents[j] = new parent(auxObjJSONParents[0],auxObjJSONParents[1]);
        
        if(isNaN(objParents[j].value)||objParents[j].value=="")
        strJSON = strJSON.replace(objParents[j].id,"\""+(objParents[j].value==""?-1:objParents[j].value)+"\""); // reemplaza el id del combo padre por el valor del combo padre
        else
        strJSON = strJSON.replace(objParents[j].id,objParents[j].value); // reemplaza el id del combo padre por el valor del combo padre
      }
      
      if(!isDefinedAndNotNull(loaded))// solo lo hace en la carga inicial del combo hijo
      {
        for(j=0;j<objJSONParents.length;j++)// a los combos padre se les agregara una cadena de funciones a ejecutar en el onchange
        {
          var auxObjJSONParents = objJSONParents[j].split(":");
          SA(GI(auxObjJSONParents[1]),"children",strchildren(GI(auxObjJSONParents[1]),e)); // setAttribute 
          
          // combo padre
            GI(auxObjJSONParents[1]).onchange = function(){ asignaparams(strchildren(GI(auxObjJSONParents[1]),e));}
        }
      }
      cmbhijo = $.evalJSON(strJSON);
      SA(e,"param","cmbhijo");
      SA(e,"loaded","true");
    }
    // combos dinamicos --------
    
    
    
      var objParam;
      
        if(eval(p)!=null){
            objParam = eval(p);
        }else{
            objParam = new Object();
        }
        objParam.name=name;
        
        
        objParam.action="select";
        
        if(m=='loadInputElement'){
          objParam.action="count";
        }
                  
        $.ajax({
            type : "POST",
            url : controller, // Llamamos al servlet Controller                 
            data: $.toJSON(objParam),// PARSEAMOS EL OBJETO jsoN A STRING Y LO MANDAMOS
            success : function(data, textStatus, jqXHR){
                fun(e,data);
            },
            error : function (XMLHttpRequest, textStatus, errorThrown) {
                alert(errorThrown)
            }
        });
 }
 
  // validaciones
  validacion(e,t);
 
}


function loadInputElement(input, arreglo) {
  var value = GA(input, "theValue");
  try {
      input.value = eval("arreglo." + value);
  } catch(ex) { 
    alert("loadInputElement(): " + ex.message); 
  }
  
}
 
function loadComboElement(obj,result) {

    
  var jsonAux= $.toJSON(result);   
  var arrValues = $.evalJSON(jsonAux);
  
  var key = GA(obj, "keyValue");
  
  var value = GA(obj, "theValue");
  loadCombo(obj, arrValues, key, value);
  var nextElement = GA(obj, "next");
  if(isDefinedAndNotNull(nextElement)) {
    var nextObj = GI(nextElement); 
    if(isDefinedAndNotNull(nextObj)) {
      loadElement(nextObj);
    } else {
      try { eval(nextElement+"()"); } catch(ex) {}
    }
  }
  $.uniform.update("select");
}




function loadCombo(combo, arreglo, llave, valor) {
  var defaultValue = GA(combo, "defaultValue");
  if(!isDefinedAndNotNull(defaultValue)) defaultValue = "-1";
   
  
  combo.options.length = arreglo.length+1;
  combo.options[0].value = defaultValue;
  combo.options[0].text = "-- Seleccione --";
  try {
    
    for(var i = 0; i < arreglo.length; i++) {
      var itemArreglo = arreglo[i];
      
      combo.options[i+1].value = eval("itemArreglo." + llave);
      combo.options[i+1].text = eval("itemArreglo." + valor);
      
      if(isDefinedAndNotNull(GA(combo, "extraValue"))) {
           
       SA(combo.options[i+1],"extraValue",eval("itemArreglo." + GA(combo, "extraValue")));  
       
     }
      
    }
  } catch(ex) { 
    alert("loadCombo(): " + ex.message); 
  }
  
}

 
$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};
 

 // combos dinamicos --------

function parent(cve,id) // objeto combo padre (clave en consulta,id,valor)
{
  this.cve = cve;
  this.id = id;
  this.value = GI(id).value;
  return this;
} 

function strchildren(objPadre,objHijo) // cadena de funciones a ejecutar por el padre 
{
  var ch = GA(objPadre, "children");
  var strchild = "";
  var archild = new Array(); // arreglo de funciones auxiliar para armar cadena de funciones
  
  if(ch!=null)// tiene cadena de hijos
  {
    archild = ch.split("|");
    var cexist = false; 
    for(k=0;k<archild.length;k++)
    {
      if(archild[k]==objHijo.id)// ya esta declarado en la sucesion de combos hijos
      {
        cexist = true;
        k=archild.length;
      }
    }
    if(!cexist)
      archild[archild.length] = objHijo.id // agrega nuevo combo hijo
  }
  else // es la primer hijo que se agrega
  {
    archild[0] = objHijo.id;
  }
  
  strchild = buildstrchildren(archild);// genera cadena desde objeto
  
  return strchild;
} 

function buildstrchildren(archild)// desde objeto forma cadena estilo "combohijo1&paramJSON|combohijo2&paramJSON"
{
    var strchild = "";
    for(w=0;w<archild.length;w++)
    {
      if(w==0)
        strchild = archild[w];
      else
        strchild += '|'+archild[w];
    }
    return strchild;
}

function asignaparams(strchildren)
{
  var archildren = strchildren.split("|");// separador de funciones
  for(l=0;l<archildren.length;l++)
  {
    loadElement(GI(archildren[l]));
  }
}

// combos dinamicos --------

var sCat =0;

var sIdForm;
var sData;

function setCatalogo(idForm,data) {
 
    sIdForm = idForm;
    sData = data;
    
    if(sCat<9) // comienza ciclo
    {
        $.each(data, function(key, value) 
          {        
            $('#'+idForm+' #'+key).val(value)
            
            if(isDefinedAndNotNull(GI(key))) 
              if(isDefinedAndNotNull(GI(key).onchange))
                GI(key).onchange();
          });
          
        sCat++;
        
        setTimeout("setCatalogo(sIdForm,sData)",200);
     }
     else// fin del ciclo
     {
        $.uniform.update("select");
        sCat = 0;
     }
}

function consultar(btn, frm) {

    var m = GA(btn, "fun"); // Nombre de la funcion a ejecutar
    var fun = eval(m);// se asigna la funcion a la variable var
    var n=GA(btn, "ref").split('.')[1];//nombre del named query
    var c=GA(btn, "ref").split('.')[0]+"Controller";//nombre del controller
    var p = GA(btn, "param");
    var pc = GA(btn, "paramcat");
      
        if(p!=null){
            var objParam = $.evalJSON(p);
            
        }
        else if(pc!=null)
        {
            var objParam={
                name:n,
                action:"select",
                parameters: $.evalJSON(replaceParams(pc,frm))
            };
            
        }else{
             var objParam={
                name:n,
                action:"select"
            };
        }
        
    $.ajax({
        type : "POST",
        url : c, // Llamamos al servlet CotizadorController                 
        data: $.toJSON(objParam),// PARSEAMOS EL OBJETO JSON A STRING Y LO MANDAMOS
        success : function(data, textStatus, jqXHR){
            fun(GI(GA(btn, "table")),data);
        },
        error : function (XMLHttpRequest, textStatus, errorThrown) {
            alert(errorThrown)
        }
    });
}


function loadTable(table, dataArray)
{
  
    var nombreFuncion=GA(table,"fun");
    var header=table.getElementsByTagName("thead")[0].getElementsByTagName("tr")[0];//accedemos al tr del thead
        
    var tbody = table.getElementsByTagName("tbody")[0];
    
    while(tbody!=null && tbody.hasChildNodes() ){//Eliminamos todos los hijos de la tabla
        tbody.removeChild(tbody.lastChild);
    }
    
    
     if(!(dataArray.length>0)){
        alert("No se encontraron resultados!")
        return;
    }
    
    var tablebody=document.createElement("tbody");//Creamos un elemento tbody (es MUY importante para conservar la compatibilidad en distintos browsers)
    if(isDefinedAndNotNull(tbody)){
        tablebody=tbody;
    }
    
    tablebody.id="b"+table.id
    var arWidths= new Array(dataArray.length);
    
    
    for(i = 0; i < dataArray.length; i++) {
    
        var fila = document.createElement("tr");
        fila.id=table.id+'_tr'+i;
        
        var item = dataArray[i];
        
        var valorItem = "";      
        
                
        var colRadio = document.createElement("td");
        var radio = document.createElement("input");
        
        SA(radio,"type","radio");      
        radio.name=table.id+ "Rdo";      
        colRadio.appendChild(radio);      
        radio.className = "radio";
        SA(colRadio,"width","20px")
        fila.appendChild(colRadio);
          
        for(var j = 1; j < header.children.length; j++) {//Se recorren las columnas de la tabla Header
            var visualcolumn = header.children[j].id;
            var columnWidth = header.children[j].width;
            var tp = GA(header.children[j],"takeParameter");
            
           
            if(!isDefinedAndNotNull(visualcolumn) || visualcolumn==""){
                var col = document.createElement("td");
                col.width=columnWidth;            
            }else{
                var col = document.createElement("td"); 
                
                col.innerHTML = ""+item[visualcolumn];  //asignamos el valor del td mediante item [column]   donde item es un objeto y column el nombre del atributo      
                if(i==0){
                    arWidths[j]=0;
                }
                 
                var currentColLength=(""+item[visualcolumn]+"").length;
                 
                arWidths[j]=(currentColLength>arWidths[j])?currentColLength:arWidths[j];
                
                if(i==dataArray.length-1){
                   
                    //SA(col,"width",arWidths[j])
                    if(!isDefinedAndNotNull(GA(col,"width")))
                      SA(col,"width",columnWidth)
                    
                }
                
                if(isDefinedAndNotNull(tp)){        
                    if(typeof(item[visualcolumn]) == "number"){
                        valorItem += "\"" + visualcolumn + "\":" + item[visualcolumn] + ",";
                    }else{
                        valorItem += "\"" + visualcolumn + "\":\"" + item[visualcolumn] + "\",";
                    }
                }
               
            }
            fila.appendChild(col);
                
          
        }
        
        if(i%2==0){
            fila.className = "fila0";
        }else{
            fila.className = "fila1";
        }
        
        fila.onmouseover = function() { cambiaColorFila(this); }
        fila.onmouseout = function() { cambiaColorFila(this); }
        
        SA(fila, "colorOriginal", fila.className);
        
        valorItem = valorItem.substring(0, valorItem.length - 1); 
        
        SA(fila, "valorItem", valorItem);   
        
        
        fila.onclick = function(){ 
          try { this.firstChild.firstChild.checked = true; } catch(ex) {}
          var fun = eval(nombreFuncion);
          if(isDefinedAndNotNull(fun))
          {
            fun($.evalJSON("{"+GA(this,"valorItem")+"}"));
          }
        }
        
        tablebody.appendChild(fila)    
        
        
        
    }
        
    table.appendChild(tablebody);
    
}


function loadTableCart(table, dataArray)
{
    var nombreFuncion=GA(table,"fun");
    var header=table.getElementsByTagName("thead")[0].getElementsByTagName("tr")[0];//accedemos al tr del thead
        
    var tbody = table.getElementsByTagName("tbody")[0];
    
    while(tbody!=null && tbody.hasChildNodes() ){//Eliminamos todos los hijos de la tabla
        tbody.removeChild(tbody.lastChild);
    }
    
     if(!(dataArray.length>0)){
        alert("No se encontraron resultados!")
        return;
    }
    
    var tablebody=document.createElement("tbody");//Creamos un elemento tbody (es MUY importante para conservar la compatibilidad en distintos browsers)
    if(isDefinedAndNotNull(tbody)){
        tablebody=tbody;
    }
    
    tablebody.id="b"+table.id
    var arWidths= new Array(dataArray.length);
    
    
    for(i = 0; i < dataArray.length; i++) {
    
        var fila = document.createElement("tr");
        fila.id=table.id+'_tr'+i;
        
        var item = dataArray[i];
        
        var valorItem = "";      
        
                
        var colRadio = document.createElement("td");
        var radio = document.createElement("input");
        
        SA(radio,"type","radio");      
        radio.name=table.id+ "Rdo";  
        radio.style.position = "absolute";
        radio.style.visibility = "hidden";
        
        var dropCart = document.createElement("img");
        
        SA(dropCart,"src",GA(table,"img"));
        SA(dropCart,"height","25px")
        SA(dropCart,"width","25px")
        
        colRadio.appendChild(radio);    
        colRadio.appendChild(dropCart);      
        radio.className = "radio";
        
        SA(colRadio,"width",30)
        SA(colRadio,"height",25)
        fila.appendChild(colRadio);
          
        for(var j = 1; j < header.children.length; j++) {//Se recorren las columnas de la tabla Header
            var visualcolumn = header.children[j].id;
            var columnWidth = header.children[j].width;
            var tp = GA(header.children[j],"takeParameter");
            
           
            if(!isDefinedAndNotNull(visualcolumn) || visualcolumn==""){
                var col = document.createElement("td");
                col.width=columnWidth;            
            }else{
                var col = document.createElement("td"); 
                
                col.innerHTML = ""+item[visualcolumn];  //asignamos el valor del td mediante item [column]   donde item es un objeto y column el nombre del atributo      
                if(i==0){
                    arWidths[j]=0;
                }
                 
                var currentColLength=(""+item[visualcolumn]+"").length;
                 
                arWidths[j]=(currentColLength>arWidths[j])?currentColLength:arWidths[j];
                
                if(i==dataArray.length-1){
                   
                    //SA(col,"width",arWidths[j])
                    SA(col,"width",columnWidth)
                    
                }
                
                if(isDefinedAndNotNull(tp)){        
                    if(typeof(item[visualcolumn]) == "number"){
                        valorItem += "\"" + visualcolumn + "\":" + item[visualcolumn] + ",";
                    }else{
                        valorItem += "\"" + visualcolumn + "\":\"" + item[visualcolumn] + "\",";
                    }
                }
               
            }
            fila.appendChild(col);
          
        }
        
        if(i%2==0){
            fila.className = "fila0";
        }else{
            fila.className = "fila1";
        }
        
        fila.onmouseover = function() { cambiaColorFila(this); }
        fila.onmouseout = function() { cambiaColorFila(this); }
        
        SA(fila, "colorOriginal", fila.className);
        
        
        
        fila.onclick = function(){ 
          try { this.firstChild.firstChild.checked = true; } catch(ex) {}
          var fun = eval(nombreFuncion);
          if(isDefinedAndNotNull(fun))
            fun(this);
        }
        valorItem = valorItem.substring(0, valorItem.length - 1); 
        
        SA(fila, "valorItem", valorItem);   
        
        
        
        
        
        tablebody.appendChild(fila)    
    }
        
    table.appendChild(tablebody);
    
}
  
function removeChildNodes(elem)
{ 
  while(elem.hasChildNodes()) 
    elem.removeChild(elem.lastChild);
} 
        
         
function changeCombo(combo,value)
{
  var tam = combo.options.length;
  for(i=0;i<tam;i++)
  {
    if(combo.options[i].value==value)
    {
      combo.selectedIndex = i;
    }
  }
}         


function cambiaColorFila(fila) {
  var colorOriginal = GA(fila, "colorOriginal");
  if(fila.className != colorOriginal) {
    fila.className = colorOriginal;
  } else {
    fila.className = GA(fila, "colorOriginal")+"Sobre";
  }
}     
          
          
function RF(form) {
  form.reset();
}

// ---- parametros consulta


function replaceParams(jsonparam,form)
{
  jsonString = jsonparam;
  var f = form;
  
  for(var j = 0; j < f.elements.length; j++)
  {
    
    var valElement = f.elements[j].value;  
    if(valElement==null||valElement=='')
      valElement = -1;
      
    if(isDefinedAndNotNull(GA(f.elements[j],"checkbox")))// Booleano (CheckBox)
    {
      if(f.elements[j].checked)//True
        jsonString = jsonString.replace(f.elements[j].id,1);
      else//False
        jsonString = jsonString.replace(f.elements[j].id,-1);
    }
    else if(isNaN(valElement))// (Cadena Texto)
      jsonString = jsonString.replace(f.elements[j].id,"\"%"+valElement+"%\"");
    else// Número
      jsonString = jsonString.replace(f.elements[j].id,valElement); 
  } 
  return jsonString;

}
