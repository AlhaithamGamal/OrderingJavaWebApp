
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>

<%@page import="org.springframework.context.ApplicationContext"%>
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
<h1 style="color:gold;"><%="Welcome" +"  " + rememberUserName+"where you can add branch"%></h1>

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
<h1 style="color:gold;">Welcome <%=userName%>where you can add branch</h1>

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
//    Integer providerId = Integer.parseInt(request.getParameter("providerId"));
    List<ProviderBean> foundProviders = gateway.findProviders();
     List<AreaBean> foundAreas = gateway.findAreas();
     Integer flag = Integer.parseInt(request.getParameter("flag"));
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

        <title>Add Branch</title>
    </head>
    <body>
          <br> <br> <br> 
            <jsp:include page="header_all.jsp" />
               <%
    
            response.setHeader("Cache-Control","no-cache,no-store,must-revalidate"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setHeader("Expires", "0"); //set proxies
    
    %>

        <h1>Add New Branch</h1>
        <form action="SaveBranchServlet" method="post"  accept-charset="ISO-8859-1">
            <input type="text" class="form-control" hidden readonly  id="flag" readonly value="<%=flag%>" name="flag" >
           
            <div class="form-group">
                <label for="name_ar">Branch Arabic name</label>
                <input type="text" required class="form-control" id="name_ar" name="name_ar" placeholder="Arabic name">
            </div>

            <div class="form-group">
                <label for="name_en">Branch English name</label>
                <input type="text" required class="form-control" id="name_en" name="name_en" placeholder="English name">
            </div>
            
             <div class="form-group">
                <label for="opened">Opened:</label>
                <input type="time" required class="form-control" id="opened" name="opened" placeholder="English name">
            </div>
            
             <div class="form-group">
                <label for="closed">Closed:</label>
                <input type="time" required class="form-control" id="closed" name="closed" placeholder="English name">
            </div>
             <div class="form-group">
                <label for="phone">Phone</label>
                <input type="number"  class="form-control" id="phone" name="phone" placeholder="Phone number">
            </div>
             <div class="form-group">
                <label for="lat">Lat:</label>
                <input type="number" step="any"  class="form-control" id="lat" name="lat" placeholder="Latitude">
            </div>
             <div class="form-group">
                <label for="lng">Lng:</label>
                <input type="number" step="any" required class="form-control" id="lng" name="lng" placeholder="Langitude">
            </div>
            
            <div class="form-group">
                <label for="providers">Providers</label>
                <select name="providers" required class="form-control" id="providers">
                    <option disabled="disabled">--SELECT---</option>
                    <% for (ProviderBean providerBean : foundProviders) { %>
                    <option 
                       
                        selected = "selected"

                        

                        value="<%=providerBean.getId()%>"><%=providerBean.getNameEn()%> 



                    </option>

                    <% }%>
                </select>
            </div>
                
                
                   <div class="form-group">
                <label for="areas">Areas</label>
                <select name="areas" required class="form-control" id="providers">
                    <option disabled="disabled">--SELECT---</option>
                    <% for (AreaBean areaBean : foundAreas) { %>
                    <option 
                       
                        selected = "selected"

                        

                        value="<%=areaBean.getId()%>"><%=areaBean.getNameEn()%> 



                    </option>

                    <% }%>
                </select>
            </div>
            <button class="btn btn-primary" type="submit">Add Branch</button>

        </form>
    </body>
</html>
