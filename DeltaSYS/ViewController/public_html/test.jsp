<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    
    <script src="js/jquery/jquery.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/validation/lib/jquery.metadata.js" type="text/javascript"></script>
    <script src="js/uniform/jquery.uniform.js" type="text/javascript"></script>
    <script src="js/jquery/jquery.json-2.2.js" type="text/javascript"></script>
    <script language="javascript" src="js/funciones.js"></script>
    <script language="javascript" src="js/deltasys.js"></script>
    
    <title>DetaSYS - Test Center</title>
  </head>
  <body onkeypress="pressKey()" onload="initForms()">
    
    <table cellspacing="0" cellpadding="0" class="fullTable" width="100%">
      <tr>
        <td height="50px"><jsp:include page="/header.jsp" /></td>
      </tr>
      <tr>
        <td>
          <form name="frmData" method="post" action="principal.jsp">
          <table align="center">
            <tr>
              <td>
                &nbsp;
              </td>
              <td align="center" class="titulo">
                    Inicio de Sesión
              </td>
            </tr>
            <tr>
              <td class="subtitulo" align="left">
                Usuario
              </td>
              <td align="right">
                <input name="user" type="text" size="20" maxlength="10" value="" tipo="alfanumerico"/>
              </td>
            </tr>
            <tr>
              <td class="subtitulo" align="left">
                Contraseña
              </td>
              <td align="right">
                <input name="password" type="password" size="20" maxlength="10"  value="" tipo="alfanumerico"/>
              </td>
            </tr>
            <tr>
              <td>
                &nbsp;
              </td>
              <td align="right">
                <input type="button" size="20px;" value="Entrar" maxlength="20" onclick="autenticar(frmData)"/>
              </td>
            </tr>
          </table>
                <input name="tipo" type="hidden" size="20" maxlength="10"/>
                <input name="nombre" type="hidden" size="20" maxlength="10"/>
          </form>
        
        </td>
      </tr>
      <tr>
        <td height="20px"><jsp:include page="/footer.jsp" /></td>
      </tr>
    </table>
    
    
  </body>
</html>
