<%-- 
    Document   : forgetpassword_result
    Created on : Feb 29, 2020, 8:49:44 PM
    Author     : HaithamGamal
--%>

<%@page import="net.haitham.otlobli.common.constant.SendEmail"%>
<%@page import="java.util.List"%>
<%@page import="net.haitham.otlobli.common.bean.AdminUserBean"%>
<%@page import="net.haitham.otloblibal.OtlobliBLGateway"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
ApplicationContext appCon = new ClassPathXmlApplicationContext("AContext.xml");
OtlobliBLGateway gateway =(OtlobliBLGateway)appCon.getBean("otlobliBLGateway");


%>
<% String email = request.getParameter("email").toString();
    boolean emailFound = false;
    AdminUserBean adminUserBeanFound = null;
    List<AdminUserBean> adminUserBean = gateway.findAdminAccounts();
    for (AdminUserBean adminUserBeanElement : adminUserBean) {
        if (email.equals(adminUserBeanElement.getEmail())) {

            emailFound = true;
            adminUserBeanFound = adminUserBeanElement;
            break;
        }

    }

    if (emailFound) {

        SendEmail send = new SendEmail(email, adminUserBeanFound, 0);
        send.sendPassword();


%>
<br><br><br>
<h1 style="color:greenyellow;" > Check Your Email please </h1>
<%  }
%>
<%if (!emailFound) {


%>
<h1 style="color:red;" > Email not found ! </h1>
<%     }
%>


<!DOCTYPE html>

<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title> Forget Password ! </title>
    </head>
    <body>
        <jsp:include page="header_logout.jsp" />  
        <br>
        <br>
        <br>
        <br>
        <br>

    </body>
</html>
