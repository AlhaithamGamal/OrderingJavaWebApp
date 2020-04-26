
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>

<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.haitham.otlobli.common.bean.AreaBean"%>
<%@page import="net.haitham.otlobli.common.bean.ProviderBean"%>
<%@page import="net.haitham.otlobli.common.bean.BranchBean"%>
<%@page import="net.haitham.otlobli.common.bean.CountryBean"%>
<%@page import="net.haitham.otloblibal.OtlobliBLGateway"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
<%
       if(rememberUserName != "" || !rememberUserName.equals("")) {
        %>
        <br><br><br>
<h1 style="color:gold;"><%="Welcome" +"  " + rememberUserName+"where you can add provider user"%></h1>

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
<h1 style="color:gold;">Welcome <%=userName%>where you can add provider user</h1>

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
    //Integer providerIdForImage = Integer.parseInt(String.valueOf(request.getParameter("providerId")));
    ProviderBean providerBean = gateway.findProviderById(providerId);
    request.setAttribute("providerId", providerId);
    request.setAttribute("providerBean", providerBean);


%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

        <title>Add provider user</title>
    </head>
    <body>
        <br> <br> <br> 
        <jsp:include page="header_all.jsp" />
           <%
    
            response.setHeader("Cache-Control","no-cache,no-store,must-revalidate"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setHeader("Expires", "0"); //set proxies
    
    %>

        <h1>Add new provider user</h1>
        <form action="SaveProviderUserServlet" method="post"  accept-charset="ISO-8859-1">


            <div class="form-group">
                <label for="id">Provider id</label>
                <input type="text" class="form-control" id="id" readonly value="${providerId}" name="providerId" >
            </div>
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" required class="form-control" id="username" name="username" placeholder="type username ">
            </div>
             <div class="form-group">
                <label for="email">Email</label>
                <input type="email"  class="form-control" id="email" name="email" placeholder="type email ">
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password"   required class="form-control" id="password" name="password" placeholder="type password">
            </div>
             <div class="form-group">
                <label for="status">Active?</label>
                <select name="status" required class="form-control" id="status">
                    <option disabled="disabled">--SELECT---</option>
                    <option value="yes">Yes</option>
                     <option value="no">No</option>
                    
                </select>
                
            </div>


            <div class="form-group">
                <label for="branches">Branches</label>
                <select name="branches" required class="form-control" id="branches">
                    <option disabled="disabled">--SELECT---</option>
                    <c:forEach items="${providerBean.branchList}" var="iBranch">
                      
                        <option 

                            value="${iBranch.id}">${iBranch.nameEn}

                        </option>
                    </c:forEach>

                </select>
            </div>

            <button class="btn btn-primary" type="submit">Add provider user</button>

        </form>
    </body>
</html>