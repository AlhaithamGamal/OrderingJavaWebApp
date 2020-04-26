<%-- 
    Document   : edit_city
    Created on : Feb 20, 2020, 3:30:16 PM
    Author     : HaithamGamal
--%>

<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>

<%@page import="org.springframework.context.ApplicationContext"%>
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
<h1 style="color:gold;"><%="Welcome" +"  " + rememberUserName +"where you can edit branch "%></h1>

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
<h1 style="color:gold;">Welcome <%=userName%> where you can edit branch</h1>

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
    Integer providerId = Integer.parseInt(request.getParameter("providerId"));
    Integer id = Integer.parseInt(request.getParameter("id"));
    BranchBean branchBean = gateway.findBranchById(id);
   
    List<ProviderBean> foundProviders = gateway.findProviders();
     List<AreaBean> foundAreas = gateway.findAreas();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

        <title>Update Branch</title>
    </head>
    <body>
        <br> <br> <br>
           <%
    
            response.setHeader("Cache-Control","no-cache,no-store,must-revalidate"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setHeader("Expires", "0"); //set proxies
    
    %>
   <jsp:include page="header_all.jsp" />
        <h1>Update Branch</h1>
        <form action="EditBranchServlet" method="post"  accept-charset="ISO-8859-1">
           <input type="text" class="form-control" hidden readonly id="id"  value="true" name="flag" >
            <div class="form-group">
                <label for="id">Provider id</label>
                <input type="text" class="form-control" id="id" readonly value="<%=providerId%>" name="providerId" >
            </div>
            <div class="form-group">
                <label for="id">Branch id</label>
                <input type="text" class="form-control" id="id" readonly  value="<%= branchBean.getId() %>" name="id" >
            </div>
            <div class="form-group">
                <label for="name_ar">Branch Arabic name</label>
                <input type="text" required class="form-control" id="name_ar" name="name_ar" value="<%= branchBean.getNameAr() %>">
            </div>

            <div class="form-group">
                <label for="name_en">Branch English name</label>
                <input type="text" required class="form-control" id="name_en" name="name_en" value="<%= branchBean.getNameEn() %>">
            </div>
            
             <div class="form-group">
                <label for="opened">Opened:</label>
                <input type="time" required class="form-control" id="opened" name="opened" value="<%= branchBean.getOpenAt() %>">
            </div>
            
             <div class="form-group">
                <label for="closed">Closed:</label>
                <input type="time" required class="form-control" id="closed" name="closed" value="<%= branchBean.getCloseAt() %>">
            </div>
             <div class="form-group">
                <label for="phone">Phone</label>
                <input type="number"  class="form-control" id="phone" name="phone" value="<%= branchBean.getPhone() %>">
            </div>
             <div class="form-group">
                <label for="lat">Lat:</label>
                <input type="number" step="any"  class="form-control" id="lat" name="lat" value="<%= branchBean.getLat() %>">
            </div>
             <div class="form-group">
                <label for="lng">Lng:</label>
                <input type="number" step="any" required class="form-control" id="lng" name="lng" value="<%= branchBean.getLng() %>">
            </div>
            
            <div class="form-group">
                <label for="providers">Providers</label>
                <select name="providers" required class="form-control" id="providers">
                    <option disabled="disabled">--SELECT---</option>
                    <% for (ProviderBean providerBean : foundProviders) { %>
                    <option 
                        <% if (providerBean.getId().equals(providerId)) {%>
                        selected = "selected"

                        <% }%>

                        value="<%=providerBean.getId()%>"><%=providerBean.getNameEn()%> 



                    </option>

                    <% } %>
                </select>
            </div>
                
                 <div class="form-group">
                <label for="areas">Areas</label>
                <select name="areas" required class="form-control" id="areas">
                    <option disabled="disabled">--SELECT---</option>
                    <% for (AreaBean areaBean : foundAreas) { %>
                    <option 
                     

                        value="<%=areaBean.getId()%>"><%=areaBean.getNameEn()%> 



                    </option>

                    <% }%>
                </select>
            </div>
                
                
                 
            <button class="btn btn-primary" type="submit">Update Branch</button>

        </form>
    </body>
</html>
