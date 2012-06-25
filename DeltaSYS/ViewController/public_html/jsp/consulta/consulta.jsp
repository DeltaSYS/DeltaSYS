    <div id="consulta" class="opcionmenu">
      <div id="top">
            <input type="button" id="btnRegresar" value="Regresar" ondblclick="" />
        </div><!--end top-->  
      <div id="left" class="left">       
            <img class="imagen" src="resources/images/consulta.jpg"  alt="Consulta Ciudadana" />
          <h3>Consulta Ciudadana</h3>
        </div> <!--end consulta-->   
      <div id="right">
            <div id="searchMatricula">
              <form name="frmMatricula" method="post">
                <table>
                  <tr>
                    <th>Matricula/Folio</th>
                    <td><input type="text" id="placa_infraccion" name="placa_infraccion" onkeypress=""/></td>
                    <td align="center" ><input type="button" value="Consultar Infracci&oacute;n" onclick="consultar(frmMatricula)"/></td>
                  </tr>
                </table>
              </form>
            </div><!--end searchMatricula-->
           <div id="folios">
            
            <table id="tableFolios">
                <thead>
                <tr>
                    <th>Folio(s)</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
            
            <form name="frmDetalle" id="frmDetalle" action="">
              <fieldset>
                <legend>Detalle de Infracciones</legend>
                <div><label>Folio:</label><input  readonly="readonly" type="text" id="folio" name="folio" /></div>
                <div><label>Fecha:</label><input  readonly="readonly" type="text" id="fecha" name="fecha" /></div>
                <div><label>Matricula:</label><input readonly="readonly" type="text" id="placa" name="placa" /></div>
                <div><label>Motivo:</label><textarea readonly="readonly" id="motivo" rows="4" cols="40"></textarea></div>
                <div><label>Fundamento:</label><textarea readonly="readonly" id="fundamento" rows="1" cols="40"></textarea></div>
                <div><label>Sancion:</label><input readonly="readonly" type="text" id="sancion" name="sancion" /></div>
            </fieldset>
            </form> 
            </div><!--end folios-->
          </div> <!--end right -->     
    </div> <!--end consulta-->
    