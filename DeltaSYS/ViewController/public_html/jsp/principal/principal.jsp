<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>

      <div id="content">
        <div id="query" >   
            
            <form name="frmConsulta" method="POST">
                <input type="hidden" name="consulta" value="si"/>
            </form>
          <img src="resources/images/consulta.jpg"  alt="Consulta Ciudadana" onclick="document.frmConsulta.submit()"/>
          <h3>Consulta Ciudadana</h3>
        </div><!--end query-->
        <div id="login">
          <form name="frmData" method="post">
            <table>
              <tr>
              <th  colspan="2">Ingresar al sistema<th>
              </tr>
              <tr>
                <td>Usuario<td>
                <td><input type="text" id="user" name="oid" onkeypress="pressKey()"/><td>
              </tr>
              <tr>
                <td>Contrase&ntilde;a<td>
                <td><input type="password" id="password" name="password" onkeypress="pressKey()"/><td>
              </tr>
              <tr>
                <td align="center" ><input type="button" value="Enviar" onclick="autenticar(frmData)"/>
                <td>
              </tr>
            </table>
          </form>
          </div> <!--end login-->
        </div> <!--end content-->