<%-- 
    Document   : edit_city
    Created on : Feb 20, 2020, 3:30:16 PM
    Author     : HaithamGamal
--%>

<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>

<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="net.haitham.otlobli.common.bean.ProviderUserBean"%>
<%@page import="net.haitham.otlobli.common.bean.DeliveryAreaBean"%>
<%@page import="net.haitham.otlobli.common.bean.AreaBean"%>
<%@page import="net.haitham.otlobli.common.bean.ProviderBean"%>
<%@page import="net.haitham.otlobli.common.bean.BranchBean"%>
<%@page import="net.haitham.otlobli.common.bean.CityBean"%>
<%@page import="java.util.List"%>
<%@page import="net.haitham.otlobli.common.bean.CountryBean"%>
<%@page import="net.haitham.otloblibal.OtlobliBLGateway"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>  
<%@ page errorPage="error.jsp" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

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
<%    if (rememberUserName != "" || !rememberUserName.equals("")) {
%>
<br><br><br>
<h1 style="color:gold;"><%="Welcome" + "  " + rememberUserName + "where you can edit provider user "%></h1>

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
<h1 style="color:gold;">Welcome <%=userName%> where you can edit  provider user</h1>

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
<%!
      ApplicationContext appCon = new ClassPathXmlApplicationContext("AContext.xml");
OtlobliBLGateway gateway =(OtlobliBLGateway)appCon.getBean("otlobliBLGateway");


%>

<%
    Integer providerUserId = Integer.parseInt(request.getParameter("id"));
    Integer providerId = Integer.parseInt(request.getParameter("providerId"));
    ProviderUserBean providerUserBean = gateway.findProviderUserById(providerUserId);
    //ProviderBean providerBean = gateway.findProviderById(providerId);
    List<ProviderBean> providerBeanList = gateway.findProviders();
    request.setAttribute("providerId", providerId);
    request.setAttribute("providerBeanList", providerBeanList); //for viewing branches
    request.setAttribute("providerUserBean", providerUserBean);


%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

        <title>Update provider user information</title>
    </head>
    <body>
        <br> <br> <br>
        <%              response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setHeader("Expires", "0"); //set proxies

        %>
        <jsp:include page="header_all.jsp" />
        <h1>Update provider user information</h1>
        <form action="EditProviderUserServletOnly" method="post"  accept-charset="ISO-8859-1">


            <div class="form-group">
                <label for="id">Provider id</label>
                <input type="text" class="form-control" id="id" readonly value="${providerId}" name="providerId" >
            </div>
            <div class="form-group">
                <label for="id">Provider user id</label>
                <input type="text" class="form-control" id="id" readonly value="${providerUserBean.id}" name="providerUserId" >
            </div>
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" required class="form-control" id="username" name="username" value="${providerUserBean.username}">
            </div>
             <div class="form-group">
                <label for="email">Email</label>
                <input type="email"  class="form-control" id="email" name="email" value="${providerUserBean.email}">
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="text"   required class="form-control" id="password" name="password" value="${providerUserBean.password}">
            </div>
            <div class="form-group">
                <label for="status">Active? </label>
                <select name="status" required class="form-control" id="status">
                    <option disabled="disabled">--SELECT---</option>
                    <option value="yes">Yes</option>
                    <option value="no">No</option>

                </select>

            </div>


            <div class="form-group">
                <label for="providers">Providers</label>
                <select name="providers" required class="form-control" id="providers">
                    <option disabled="disabled">--SELECT---</option>
                    <c:forEach items="${providerBeanList}" var="iProvider">

                        <option 

                            value="${iProvider.id}">${iProvider.nameEn}


                        </option>

                    </c:forEach>

                </select>
            </div>
            

            <button class="btn btn-primary" type="submit" onclick="if (confirm('Are you sure you want to edit the record ?')) {
                            return true
                        } else {
                            return false
                        }">Update provider user</button>

        </form>
    </body>
</html>
