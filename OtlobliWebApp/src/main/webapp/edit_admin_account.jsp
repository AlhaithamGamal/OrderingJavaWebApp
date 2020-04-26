<%-- 
    Document   : edit_admin_account
    Created on : Feb 29, 2020, 7:01:58 PM
    Author     : HaithamGamal
--%>

<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>

<%@page import="org.springframework.context.ApplicationContext"%>

<%@page import="java.util.List"%>
<%@page import="net.haitham.otlobli.common.bean.AdminUserBean"%>
<%@page import="net.haitham.otlobli.common.bean.AdminUserBean"%>
<%@page import="net.haitham.otloblibal.OtlobliBLGateway"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
<h1 style="color:gold;"><%="Welcome" + "  " +rememberUserName +"where you can edit admins "%></h1>

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

%>
<br><br><br>
<h1 style="color:gold;">Welcome <%=userName%> where you can edit admin</h1>

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
<%
    String message = "";
    String status = request.getParameter("s");
    String status2 = request.getParameter("s2");
    String status3 = request.getParameter("s3");
    if (status != null) { //check if the status not null in case of k=just refreshing the page without adding 

        if ("true".equals(status)) { //true first to avoid null pointer exception

            message = "Added Successfully";
        } else {
            message = "Error in ADDING";
        }
    }
    if (status2 != null) {
        if ("true".equals(status2)) {
            message = "Updated Successfully";
        } else {

            message = "error in UPDATING ";
        }

    }
    if (status3 != null) {
        if ("true".equals(status2)) {
            message = "Deleted Successfully";
        } else {

            message = "error in DELETING";
        }

    }
   
%>
<%!
   ApplicationContext appCon = new ClassPathXmlApplicationContext("AContext.xml");
OtlobliBLGateway gateway =(OtlobliBLGateway)appCon.getBean("otlobliBLGateway");


%>

<%
    Integer userId = Integer.parseInt(request.getParameter("userId"));

   AdminUserBean foundAdminUserBean = gateway.findAdminAccountBy(userId);
 
           
           
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <title>Edit Admin info</title>
    </head>
<body>
    <br> <br> <br>
      <%
    
            response.setHeader("Cache-Control","no-cache,no-store,must-revalidate"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setHeader("Expires", "0"); //set proxies
    
    %>
   <jsp:include page="header_all.jsp" />
         
         <h1 style="color:greenyellow;"><%=message%></h1>
    <form action="EditAdminAccountServlet" method="post"  accept-charset="ISO-8859-1">
        <div class="form-group">
            <label for="userId">Admin Id</label>
            <input type="text" required  readonly class="form-control" id="userId" name='userId'  value="<%=userId%>">
        </div>

        <div class="form-group">
            <label for="username">Admin username</label>
            <input type="text" required class="form-control" id="username" name="username" value="<%=foundAdminUserBean.getUsername()%>" >
        </div>
        <div class="form-group">
            <label for="email">Admin email</label>
            <input type="email" required class="form-control" id="email" name="email" value="<%=foundAdminUserBean.getEmail()%>" >
        </div>

        <div class="form-group">
           
            <input type="text" required readonly hidden class="form-control" id="password_enc" name="password_enc" value="<%=foundAdminUserBean.getPassword()%>" >
        </div>
        <div class="form-group">
            <label for="password">Admin  password</label>
            <input type="text" required read only class="form-control" id="password" name="password" value="<%=foundAdminUserBean.getOriginalPassword()%>" >
        </div>
        <div class="form-group">
            
            <input type="text" required readnly hidden class="form-control" id="status" name="status" value="<%=foundAdminUserBean.getActive()%>" >
        </div>
         <div class="form-group">
           
            <input type="text" required readonly hidden class="form-control" id="hash" name="hash" value="<%=foundAdminUserBean.getHash()%>" >
        </div>

        <button class="btn btn-primary" type="submit" onclick="if (confirm('Are you sure you want to edit the account ?')) {
                    return true
                } else {
                    return false
                }">Update Account</button>

    </form> 
        
        
</body>
</html>