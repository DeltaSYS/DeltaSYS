<!DOCTYPE html">
<%@ page contentType="text/html;charset=windows-1252"%>

      <div id="content">
        <div id="top">
            <input type="button" value="Regresar" ondblclick="" />
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
                <table cellspacing="0" cellpadding="10">
                    <tr><td colspan="2"> &nbsp;</td></tr>
                    <tr><td colspan="2">Detalle de Infracciones</td></tr>
                    <tr>
                        <td valign="top" align="left" width="100" id="dvFolios">
                            &nbsp;
                        </td>
                        <td>
                            <table>
                                <tr>
                                    <td>Folio:</td>
                                    <td><input  type="text" id="folio" name="folio" /></td>
                                </tr>
                                <tr>
                                    <td>Situaci&oacute;n:</td>
                                    <td><input  type="text" id="situacion" name="situacion" /></td>
                                </tr>
                                <tr>
                                    <td>Fecha:</td>
                                    <td><input  type="text" id="fecha" name="fecha" /></td>
                                </tr>
                                <tr>
                                    <td>Matricula:</td>
                                    <td><input  type="text" id="placa" name="placa" /></td>
                                </tr>
                                <tr>
                                    <td>Motivo:</td>
                                    <td><textarea id="motivo" rows="4" cols="40">
                                    </textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Fundamento:</td>
                                    <td>
                                        <textarea id="fundamento" rows="1" cols="40">
                                        </textarea>
                                    </td>
                                </tr>
                                 <tr>
                                    <td>Sancion:</td>
                                    <td><input  type="text" id="sancion" name="sancion" /></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </div><!--end folios-->
          </div> <!--end search -->
      </div> <!--end content-->