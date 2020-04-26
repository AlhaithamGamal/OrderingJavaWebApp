<%-- 
    Document   : view_city
    Created on : Feb 20, 2020, 3:30:28 PM
    Author     : HaithamGamal
--%>

<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>

<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="net.haitham.otlobli.common.bean.AreaBean"%>
<%@page import="net.haitham.otlobli.common.bean.CityBean"%>
<%@page import="net.haitham.otloblibal.OtlobliBLGateway"%>
<%@page import="net.haitham.otloblibal.OtlobliBLGateway"%>
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
    String rememberMessage = " ";
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
           rememberMessage=  "Welcome"+"     "+rememberUserName+"    "+"   to cities where you can edit city";

        %>
        <br><br><br>
<h1 style="color:gold;"><%="Welcome" + "  " +rememberUserName +"where you can edit cities "%></h1>

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
<h1 style="color:gold;">Welcome <%=userName%> where you can edit cities</h1>

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
    String sid = request.getParameter("cityId");
    int id = Integer.parseInt(sid);

    CityBean citBean = gateway.findCityById(id);

%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View City Data</title>
    </head>
    <body>
           <%
    
            response.setHeader("Cache-Control","no-cache,no-store,must-revalidate"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setHeader("Expires", "0"); //set proxies
    
    %>
   <jsp:include page="header_all.jsp" />
        <br> <br> <br> 
        <table class="table table-dark">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">English(EN) Country Name</th>
                    <th scope="col">Arabic(AR)City Name</th>
                    <th scope="col">English(EN) City Name</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><%=id%></td>
                    <td><%=citBean.getCountryBean().getNameEn()%></td>
                    <td><%=citBean.getNameAr()%></td>
                    <td><%=citBean.getNameEn()%></td>
                </tr>
            </tbody>
        </table>
        <br> <br> <br> 
        <h1>City Areas</h1>
        <table  class="table table-hover" border='1' width='100%'>
            <tr>
                <th>ID</th>
                <th>Arabic Name(AR)</th>
                <th> English Name(EN)</th>
                <th> Edit City</th>
                <th> View City</th>
                <th> Delete City </th>
            </tr>
            <% for (AreaBean areaBean : citBean.getAreaList()) {%>
            <tr>
                <td> <%=areaBean.getId()%></td>
                <td> <%=areaBean.getNameAr()%></td>
                <td><%=areaBean.getNameEn()%></td>
                <td> 
                    <a href="edit_area.jsp?id=<%=areaBean.getId()%>&cityId=<%=id%>" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Edit area</a>
                </td>
                <td> 
                    <a href="view_area_branches.jsp?areaId=<%=areaBean.getId()%>" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">View area branches</a>
                </td> 
                <td> 
                    <a href="DeleteAreaServlet?id=<%=areaBean.getId()%>&cityId=<%=id%>" onclick="if (confirm('Are you sure you want to delete the record ?')) {
                                return true
                            } else {
                                return false
                            }" class="btn btn-danger btn-lg active" role="button" aria-pressed="true">Delete area</a>
                </td> 

            </tr>
            <% }%>
        </table>



        <p><%=message%></p>

        <a href='add_area.jsp?cityId=<%=id%>' class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Add New area</a>
    </body>
</html>
