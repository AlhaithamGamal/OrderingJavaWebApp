<%-- 
    Document   : logout
    Created on : Feb 29, 2020, 12:41:19 AM
    Author     : HaithamGamal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%String msgLogout = "";
     msgLogout = request.getParameter("msgLogOut");
    if (msgLogout != null) {
%>
<br><br><br>
<h1 style="color:green;"><%=msgLogout%></h1>
<% }%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <script type="text/javascript" src ="backNoWork.js" type="text/javascript"></script>
<!--        <script  type="text/javascript">
            window.history.forward();
            function noBack() {
                window.history.forward();
            }
        </script>-->
        <title>Logout</title>
    </head>
<!--    <body> onload="noBack();"
          onpageshow="if (event.persisted) noBack();" >-->
<body>
     <br> <br> <br>
        <jsp:include page="header_logout.jsp" />
      


    </body>
</html>
