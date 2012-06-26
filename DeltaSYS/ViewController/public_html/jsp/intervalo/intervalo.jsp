<div>
        <div id="top">
            <input type="button" value="Regresar" ondblclick="" />
        </div><!--end top-->
        
        <div id="left" class="left">
            <img class="imagen" src="resources/images/clock.jpg"  alt="Establecer intrvalo" />
          <h3>Establecer intervalo</h3>
        </div> <!--end left-->
        
        <div id="right">
            <div id="establecerIntervalo">
              <form name="frmIntervalo" method="post">
                <table>
                  <tr>
                    <th>Establecer Intervalo(minutos)</th>
                    <td><input type="text" id="tiempo" name="tiempo" onkeypress=""/></td>
                    <td align="center" ><input type="button" value="Actualiazar tiempo" onclick=""/></td>
                  </tr>
                  <tr>
                    <td>Responsable de modificaci&oacute;n </td>
                    <td colspan="2"><input type="text" id="responsable" disabled="disabled" /></td>
                  </tr>
                  <tr>
                    <td>Fecha de la &uacute;ltima modificaci&oacute;n</td>
                    <td colspan="2"><input type="text" id="fecha" disabled="disabled" /></td>
                  </tr>
                </table>
              </form>
            </div><!--end establecerIntervalo-->
        </div> <!--end right -->
</div>