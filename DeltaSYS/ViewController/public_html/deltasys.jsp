<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=windows-1252"%>

<%
    String consulta = request.getParameter("consulta")!=null?request.getParameter("consulta").toString():"no";
%>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8"></meta>
    <link rel="stylesheet" type="text/css" href="resources/css/style1.css">
    
    <script src="js/jquery/jquery.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/validation/lib/jquery.metadata.js" type="text/javascript"></script>
    <script src="js/uniform/jquery.uniform.js" type="text/javascript"></script>
    <script src="js/jquery/jquery.json-2.2.js" type="text/javascript"></script>
    <script language="javascript" src="js/funciones.js"></script>
    <script language="javascript" src="js/deltasys.js"></script>
    
      <%
      if(consulta.equals("si")){
      %>
        <script language="javascript" src="jsp/consulta/consulta.js"></script>
      <%
      }else if(consulta.equals("no")){
      %>
        <script language="javascript" src="jsp/principal/principal.js"></script>
      <%}%>
    
    <title>SUI</title>
  </head>
  <body>
    <div id="full" >
      <div id="head">
        <h1>Sistema &Uacute;nico de Infracciones</h1>
      </div> <!--end head-->
      <div id="content">
      <%
      if(consulta.equals("si")){
      %>
        <jsp:include page="/jsp/consulta/consulta.jsp" />
      <%
      }else if(consulta.equals("no")){
      %>
        <jsp:include page="/jsp/principal/principal.jsp" />
      <%}%>
      </div>
      <div id="footer" >
        <h3>DeltaSys</h3>
      </div> <!--end footer-->
    </div> <!--end full-->
  </body>
</html>