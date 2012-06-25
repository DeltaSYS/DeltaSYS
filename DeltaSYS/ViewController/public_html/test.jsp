<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    
    <script src="js/jquery/jquery.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/validation/lib/jquery.metadata.js" type="text/javascript"></script>
    <script src="js/uniform/jquery.uniform.js" type="text/javascript"></script>
    <script src="js/jquery/jquery.json-2.2.js" type="text/javascript"></script>
  <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>
    <script language="javascript" src="js/gmaps.js"></script>
  <script type="text/javascript" src="js/prettify/prettify.js"></script>
    <script language="javascript" src="js/funciones.js"></script>
    <script language="javascript" src="js/deltasys.js"></script>
    <script language="javascript" src="test.js"></script>
    
    <title>DetaSYS - Test Center</title>
  </head>
  <body onload="initForms()">
    
    <table cellspacing="0" cellpadding="0" width="100%">
      <tr>
        <td>
          <form name="frmData" method="post">
          <table align="center">
            <tr>
              <td>
                &nbsp;
              </td>
            </tr>
            <tr>
              <td class="subtitulo" align="left">
                Sectores
              </td>
            </tr>
            <tr>
              <td class="subtitulo" align="left">
                <div id="dvSectores"></div>
              </td>
            </tr>
            <tr>
              <td class="subtitulo" align="left">
                Oficiales de Transito
              </td>
            </tr>
            <tr>
              <td class="subtitulo" align="left">
                <div id="dvOficiales"></div>
              </td>
            </tr>
            <tr>
              <td class="subtitulo" align="left">
                Ubicación
              </td>
            </tr>
            <tr>
              <td align="right">
                <div id="dvUbicaciones"></div>
              </td>
            </tr>
            
            
          </table>
          </form>
        
        </td>
      </tr>
      
    <tr>
      <td align="center">
          <div id="map" style="width:600px;height:350px;"></div>
      </td>
    </tr>
    </table>
    
    
  </body>
</html>
