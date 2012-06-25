<div>
        <div id="top">
            <input type="button" value="Regresar" ondblclick="" />
        </div><!--end top-->
        
        <div id="left" class="left">
            <img class="imagen" src="resources/images/cancelar.jpg"  alt="Cancelar Infraccion por inpugnacion" />
          <h3>Cancelar Infracci&oacute;n</h3>
        </div> <!--end left-->
        
        <div id="right">
            <div id="cancelar">
              <form name="frmCancelar" method="post">
                <table>
                  <tr>
                    <td>Folio infracci&oacute;n:</td>
                    <td><input type="text" id="folio" name="folio" onkeypress=""/></td>
                  </tr>
                  <tr>
                    <td>Folio de Oficio de cancelaci&oacute;n:</td>
                    <td><input type="text" id="ofCancelacion"  /></td>
                  </tr>
                  <tr>
                    <td colspan="2" align="center" ><input type="button" value="Confirmar Cancelaci&oacute;n" onclick=""/></td>
                  </tr>
                </table>
              </form>
            </div><!--end cancelar-->
        </div> <!--end right -->
</div>