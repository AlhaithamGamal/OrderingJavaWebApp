
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
<h1 style="color:gold;"><%="Welcome" +"  " + rememberUserName+"where you can add delivery area"%></h1>

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
<h1 style="color:gold;">Welcome <%=userName%>where you can add delivery area</h1>

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
    Integer branchId = Integer.parseInt(request.getParameter("branchId"));
    //Integer providerIdForImage = Integer.parseInt(String.valueOf(request.getParameter("providerId")));
     List<AreaBean> foundAreas = gateway.findAreas();
    List<BranchBean> foundBranches = gateway.findbranches();
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

        <title>Add delivery area</title>
    </head>
    <body>
          <br> <br> <br> 
            <jsp:include page="header_all.jsp" />
               <%
    
            response.setHeader("Cache-Control","no-cache,no-store,must-revalidate"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setHeader("Expires", "0"); //set proxies
    
    %>

        <h1>Add new Delivery area</h1>
        <form action="SaveDeliveryAreaServlet" method="post"  accept-charset="ISO-8859-1">


            <div class="form-group">
                <label for="id">Branch id</label>
                <input type="text" class="form-control" id="id" readonly value="<%=branchId%>" name="id" >
            </div>
            <div class="form-group">
                <label for="minutes">Delivery minutes</label>
                <input type="number" required class="form-control" id="minutes" name="minutes" placeholder="type minutes ">
            </div>

            <div class="form-group">
                <label for="fees">Delivery fees ($)</label>
                <input type="number" step="any"  required class="form-control" id="fees" name="fees" placeholder="type fees">
            </div>


            <div class="form-group">
                <label for="branches">Branches</label>
                <select name="branches" required class="form-control" id="branches">
                    <option disabled="disabled">--SELECT---</option>
                    <% for (BranchBean branchBean : foundBranches) { %>
                    <option 
                        <% if (branchBean.getId().equals(branchId)) {%>
                        selected = "selected"

                        <% }%>

                        value="<%=branchBean.getId()%>"><%=branchBean.getNameEn()%> 



                    </option>

                    <% }%>
                </select>
            </div>


            <div class="form-group">
                <label  for="areas">Areas</label>
                <select name="areas" required class="form-control" id="areas">
                    <option disabled="disabled">--SELECT---</option>
                    <% for (AreaBean areaBean : foundAreas) {%>
                    <option 

                        selected = "selected"


                        value="<%=areaBean.getId()%>"><%=areaBean.getNameEn()%> 
                      

                    </option>

                    <% }%>
                </select>
            </div>
            <button class="btn btn-primary" type="submit">Add delivery area</button>

        </form>
    </body>
</html>