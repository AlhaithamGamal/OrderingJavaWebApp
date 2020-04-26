
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Cookie ckUserName = new Cookie("username", "");
    Cookie ckPassword = new Cookie("password", "");
    Cookie ckId = new Cookie("id", "");
    Cookie ckUserNameRemember = new Cookie("username_remember", "");
    Cookie ckPasswordRememeber = new Cookie("password_remember", "");
    Cookie ckIdRememeber = new Cookie("id_remember", "");
    ckUserNameRemember.setMaxAge(0);
    ckPasswordRememeber.setMaxAge(0);
    ckIdRememeber.setMaxAge(0);
    ckUserName.setMaxAge(0);
    ckPassword.setMaxAge(0);
    ckId.setMaxAge(0);
    session.invalidate();
    response.addCookie(ckUserNameRemember);
    response.addCookie(ckPasswordRememeber);
    response.addCookie(ckIdRememeber);
    response.addCookie(ckUserName);
    response.addCookie(ckPassword);
    response.addCookie(ckId);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>

    </head>

    <body>

        <jsp:include page="header_logout.jsp" />


        <!-- Main jumbotron for a primary marketing message or call to action -->
        <div class="jumbotron">
            <div class="container">
                <h1 style="color:blue;">Hello!</h1>
                <h1 style="color:blue;"> Welcome To Ordering Food Admin Panel</h1>
                <p><a class="btn btn-primary btn-lg" href="#" role="button">Learn more &raquo;</a></p>
            </div>
        </div>
     
    </body>
</html>

