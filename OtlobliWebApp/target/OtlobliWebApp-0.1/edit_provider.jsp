<%-- 
    Document   : edit_country
    Created on : Feb 13, 2020, 3:34:26 AM
    Author     : HaithamGamal
--%>

<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>

<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="net.haitham.otlobli.common.bean.ProviderBean"%>
<%@page import="net.haitham.otlobli.common.bean.CountryBean"%>
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
<h1 style="color:gold;"><%="Welcome" +"  " + rememberUserName +"where you can edit provider "%></h1>

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
<h1 style="color:gold;">Welcome <%=userName%> where you can edit provider</h1>

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
    String sid = request.getParameter("id");
    int id = Integer.parseInt(sid);

    ProviderBean p = gateway.findProviderById(id);
%>
<!DOCTYPE html>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Provider Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    </head>
    <body>
        <br > <br> <br>
           <%
    
            response.setHeader("Cache-Control","no-cache,no-store,must-revalidate"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setHeader("Expires", "0"); //set proxies
    
    %>
   <jsp:include page="header_all.jsp" />
        <form action='EditProviderServlet2'method="post"  accept-charset="ISO-8859-1">

            <div class="form-group">
                
                <input  type="text" readonly  name='id' value="<%=p.getId()%>" required hidden readonly class="form-control" id="id" name='id' value="<%=p.getId()%>">
            </div>

            <div class="form-group">
                <label for="name_ar_provider">NameAR</label>
                <input type="text" required  class="form-control" id="name_ar_provider" name='name_ar_provider'value="<%=p.getNameAr()%>">
            </div>
            <div class="form-group">
                <label for="name_en_provider">NameEN</label>
                <input type="text" required  class="form-control" id="name_en_provider" name='name_en_provider' value="<%=p.getNameEn()%>">
            </div>


            <div class="form-group">
                <label for="hotline">Hotline</label>
                <input type="number"  class="form-control" id="hotline" name='hotline' value="<%=p.getHotline()%>">
            </div>
     

            <div class="form-group">
               
                <button class="btn btn-primary" type="submit" onclick="if (confirm('Are you sure you want to edit the record ?')) {
                            return true
                        } else {
                            return false
                        }">Update Provider</button>
            </div>
        </form>
    </body>
</html>
