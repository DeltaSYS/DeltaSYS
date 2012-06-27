<div> 
    <div id="query" >           
            <form name="frmConsulta" method="POST">
                <input type="hidden" name="consulta" value="si"/>
            </form>
          <img class="imagen" src="resources/images/consulta.jpg"  alt="Consulta Ciudadana" onclick="document.frmConsulta.submit()"/>
          <h3>Consulta Ciudadana</h3>
    </div><!--end query-->
    
    <div id="login">
          <form name="frmData" method="post" action="">
            <table>
              <tr>
              <th  colspan="2">Ingresar al sistema<th>
              </tr>
              <tr>
                <td>Usuario<td>
                <td><input type="text" id="user" name="oid" value="" required/><td>
              </tr>
              <tr>
                <td>Contrase&ntilde;a<td>
                <td><input type="password" id="password" required name="password" value=""/><td>
              </tr>
              <tr>
                <td align="center" ><input type="submit" id="btnSubmit" value="Enviar"/>
                <td>
              </tr>
            </table>
          </form>
    </div> <!--end login-->
</div>