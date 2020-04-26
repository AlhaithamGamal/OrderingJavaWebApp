<%-- 
    Document   : index
    Created on : Jan 1, 2020, 7:46:18 PM
    Author     : HaithamGamal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
    response.addCookie(ckUserNameRemember);
    response.addCookie(ckPasswordRememeber);
    response.addCookie(ckIdRememeber);
    response.addCookie(ckUserName);
    response.addCookie(ckPassword);
    response.addCookie(ckId);
    session.invalidate();
%>
<html lang="en">
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Sign up </title>
    </head>
    <body>
        <jsp:include page="header_logout.jsp" /> 
        <br>
        <br>
        <br>
        <br>
        <div class="container">
            <h2>New Account</h2>
            <form action="SaveAdminAccount" class="form-horizontal"  method="post">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="username">Username:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="username" placeholder="Enter user name"  required name="username">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="password">Password:</label>
                    <div class="col-sm-10">          
                        <input type="password" class="form-control" id="password" placeholder="Enter password" required name="password">
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="email">Email:</label>
                        <div class="col-sm-10">          
                            <input type="email" class="form-control" id="email" placeholder="Enter email" required name="email">
                        </div>
                    </div>

                    <div class="form-group">        
                        <div class="col-sm-offset-2 col-sm-10">
                            <input type="submit" value="submit" class="btn btn-info">
                        </div>
                    </div>
            </form>
        </div>

    </body>
</html>

