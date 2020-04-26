<%-- 
    Document   : header_all
    Created on : Mar 1, 2020, 3:13:45 AM
    Author     : HaithamGamal
--%>
<%
    Cookie ck[] = request.getCookies();
    String userName = "";
    String password = "";
    String cookieUserId = "";
    if (ck != null) {

        for (int i = 0; i < ck.length; i++) {
            if (ck[i].getName().equals("username")) {
                userName = ck[i].getValue();

            }
            if (ck[i].getName().equals("password")) {
                password = ck[i].getValue();

            }
            if (ck[i].getName().equals("id")) {
                cookieUserId = ck[i].getValue();

            }
        }
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header</title>
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <link rel="canonical" href="https://getbootstrap.com/docs/4.4/examples/jumbotron/">

        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">


        <style>
            .bd-placeholder-img {
                font-size: 1.125rem;
                text-anchor: middle;
                -webkit-user-select: none;
                -moz-user-select: none;
                -ms-user-select: none;
                user-select: none;
            }

            @media (min-width: 768px) {
                .bd-placeholder-img-lg {
                    font-size: 3.5rem;
                }
            }
        </style>
        <!-- Custom styles for this template -->
        <link href="jumbotron.css" rel="stylesheet">
    </head>
    <body>
        <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
            <a class="navbar-brand" href="admin_profile.jsp?userId=<%=cookieUserId%>">Otlobli</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarsExampleDefault">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="admin_profile.jsp?userId=<%=cookieUserId%>">Home <span class="sr-only">(current)</span></a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link "  

                           onclick="if (confirm('Are you sure you want to logout ?')) {
                                       return true
                                   } else {
                                       return false
                                   }" href="LogoutServlet" tabindex="-1" aria-disabled="true">Logout</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Records</a>
                        <div class="dropdown-menu" aria-labelledby="dropdown01">
                            <a class="dropdown-item" href="providers.jsp?msg='Welcome to providers'">Providers</a>
                            <a class="dropdown-item" href="countries.jsp?msg='welcome to countries'">Countries</a>
                            <a class="dropdown-item" href="edit_admin_account.jsp?userId=<%=cookieUserId%>">Edit Account info</a>

                            <a class="dropdown-item" href="DeleteAdminAccountServlet?userId=<%=cookieUserId%>"  onclick="if (confirm('Are you sure you want to delete the account ?')) {
                                        return true
                                    } else {
                                        return false
                                    }" class="btn btn-danger btn-lg active" role="button" aria-pressed="true">Delete Account and logout</a>




                        </div>
                    </li>
                </ul>
                <form class="form-inline my-2 my-lg-0" action="SearchServlet" method="get"  accept-charset="ISO-8859-1">
                    <input class="form-control mr-sm-2" type="text" name="query_name" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </form>
            </div>
        </nav>

    </body>
</html>