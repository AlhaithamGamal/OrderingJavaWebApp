<%-- 
    Document   : edit_area
    Created on : Feb 20, 2020, 7:52:24 PM
    Author     : HaithamGamal
--%>

<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>

<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="net.haitham.otloblibal.OtlobliBLGateway"%>
<%@page import="net.haitham.otlobli.common.bean.CityBean"%>
<%@page import="java.util.List"%>
<%@page import="net.haitham.otlobli.common.bean.CountryBean"%>
<%@page import="net.haitham.otlobli.common.bean.AreaBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>  
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
<h1 style="color:gold;"><%="Welcome" +"  " + rememberUserName +"where you can edit area "%></h1>

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
<h1 style="color:gold;">Welcome <%=userName%> where you can edit area</h1>

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
    Integer cityId = Integer.parseInt(request.getParameter("cityId"));
    Integer id = Integer.parseInt(request.getParameter("id"));
    AreaBean areaBean = gateway.findAreaById(id);

    List<CityBean> foundCities = gateway.findCities();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

        <title>Edit Area</title>
    </head>
    <body>
        <br> <br> <br>
           <%
    
            response.setHeader("Cache-Control","no-cache,no-store,must-revalidate"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setHeader("Expires", "0"); //set proxies
    
    %>
   <jsp:include page="header_all.jsp" />
        <h1>Edit Area Record</h1>
        <form action="EditAreaServlet" method="post"  accept-charset="ISO-8859-1">
            <div class="form-group">
                <label for="id">City id</label>
                <input type="text" class="form-control" id="id" readonly value="<%=cityId%>" name="cityId" >
            </div>
            <div class="form-group">
                <label for="id">Area id</label>
                <input type="text" class="form-control" id="id" readonly value="<%=id%>" name="id" >
            </div>
            <div class="form-group">
                <label for="name_ar">Area Arabic name</label>
                <input type="text" required class="form-control" id="name_ar" value="<%=areaBean.getNameAr()%>" name="name_ar" >
            </div>

            <div class="form-group">
                <label for="name_en">Area English name</label>
                <input type="text" required class="form-control" id="name_en" value="<%=areaBean.getNameEn()%>" name="name_en" >
            </div>
            <div class="form-group">
                <label for="cities">Cities</label>
                <select name="cities" required class="form-control" id="cities">
                    <option disabled="disabled">--SELECT---</option>
                    <% for (CityBean ciyBean : foundCities) { %>
                    <option 
                        <% if (ciyBean.getId().equals(cityId)) {%>
                        selected = "selected"
                            <% }%>


                        value="<%=ciyBean.getId()%>"><%=ciyBean.getNameEn()%> 


                    </option>

                    <% }%>
                </select>
            </div>
            <button class="btn btn-primary" type="submit" onclick="if (confirm('Are you sure you want to edit the record ?')) {
                        return true
                    } else {
                        return false
                    }">Update Area</button>

        </form>
    </body>
</html>
