<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%
//    session = request.getSession();
//    String rememberUserName = String.valueOf(session.getAttribute("session_username_remember"));
//    String rememberPassword = String.valueOf(session.getAttribute("session_password_remember"));
//    Integer rememberId=Integer.parseInt(String.valueOf(session.getAttribute("session_id_remember")));
//    //String name="";
//    if (rememberUserName != null && rememberPassword != null && rememberId != null) {
 String rememberUserName = "";
    String rememberPassword = "";
    Integer rememberId = null;
    Cookie ck[] = request.getCookies();
    if (ck != null) {

        for (int i = 0; i < ck.length; i++) {
            if (ck[i].getName().equals("username_remember")) {
                rememberUserName = ck[i].getValue();

            }
            if (ck[i].getName().equals("password_remember")) {
                rememberPassword = ck[i].getValue();

            }
            if (ck[i].getName().equals("id_remember")) {
                rememberId = Integer.parseInt(ck[i].getValue());

            }
        }


%>
<%
       if(rememberUserName != "" || !rememberUserName.equals("")) {
        %>
        <br><br><br>
<h1 style="color:gold;"><%="Welcome" + "  " + rememberUserName+"where you can add provider"%></h1>

<% 
       }
%>
<% 
    }

%>
<% String msg = "";
    String userName = "";
    String password = "";
    String cookieUserId = "";
    if (rememberUserName.equals("") || rememberPassword.equals("") || rememberId == null) {

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

            if (!userName.equals("") && !password.equals("")) {

%><br><br><br>
<h1 style="color:gold;">Welcome <%=userName%>  where you can add provider</h1>

<% } else {
                String msg2 = "please login first expired !";
                response.sendRedirect("login.jsp?msg2=" + msg2);
            }
        } else {

            String msg2 = "please login first expired !";
            response.sendRedirect("login.jsp?msg2=" + msg2);
        }
    }
%>
<html>
    <head>
        <title>Add Provider</title>
           <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
          <br> <br> <br> 
          <jsp:include page="header_all.jsp" />
             <%
    
            response.setHeader("Cache-Control","no-cache,no-store,must-revalidate"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setHeader("Expires", "0"); //set proxies
    
    %>

        <h1>Add New Provider</h1>  
<form action="SaveProviderServlet"  method="post" enctype="multipart/form-data"  accept-charset="ISO-8859-1">  
<table>  
    <tr>
        <td>NameAR:</td>
        <td>
            <div class="form-group">
            <input class="form-control" required type="text" name="name_ar_provider"/>
            </div>
        </td>
    </tr>  
<tr>
    <td>NameEn:</td>
    <td>
        <div class="form-group">
        <input class="form-control" required type="text" name="name_en_provider"/>
        </div>
    </td>
</tr>
<tr><td>Hotline:</td>
    <td>
        <div class="form-group">
        <input class="form-control" type="number"  name="hotline"/>
        </div>
    </td>
</tr>
 <tr><td>Select Logo:</td>
     <td>
       <div class="form-group">
         <input class="form-control" type="file" required name="logo"/>
       </div>
     </td></tr>
    <tr>
        <td colspan="2">
            
          <button class="btn btn-primary" type="submit">Add Provider</button>

        </td>
    </tr>  
    
</table>  
</form>  
  
        
    </body>
</html>
