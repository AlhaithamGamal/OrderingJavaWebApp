<%-- 
    Document   : edit_country
    Created on : Feb 13, 2020, 3:34:26 AM
    Author     : HaithamGamal
--%>


<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>

<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="net.haitham.otlobli.common.bean.CategoryBean"%>
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
<%    if (rememberUserName != "" || !rememberUserName.equals("")) {
%>
<br><br><br>
<h1 style="color:gold;"><%="Welcome" + "  " + rememberUserName + "where you can edit category "%></h1>

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
<h1 style="color:gold;">Welcome <%=userName%> where you can edit category</h1>

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
    OtlobliBLGateway gateway = (OtlobliBLGateway) appCon.getBean("otlobliBLGateway");


%>
<%
    String sid = request.getParameter("id");
    int id = Integer.parseInt(sid);

    CategoryBean p = gateway.findCategoryById(id);
    Integer providerId = Integer.parseInt(request.getParameter("providerId"));
    Integer categoryId = Integer.parseInt(request.getParameter("id"));
    ProviderBean providerBean = gateway.findProviderById(providerId);
    CategoryBean categoryBean = gateway.findCategoryById(categoryId);


%>
<!DOCTYPE html>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Category</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    </head>
    <body>
        <br > <br> <br>
        <%            response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setHeader("Expires", "0"); //set proxies

        %>
        <jsp:include page="header_all.jsp" />
        <form action='EditCategoryImage'method="post" enctype="multipart/form-data" accept-charset="ISO-8859-1">


            <input class="form-control" id="providerId" required hidden readonly type="text" name="providerId" value="<%=providerId%>"/>



            <input class="form-control" required hidden readonly id="id" hidden readonly type="text" name="id" value="<%=categoryId%>"/>




            <input class="form-control" id="name_ar_cat" hidden readonly required hidden type="text" name="name_ar_cat" value="<%=categoryBean.getNameAr()%>"/>



            <input class="form-control" id="name_en_cat" hidden readonly required hidden type="text" name="name_en_cat" value="<%=categoryBean.getNameEn()%>"/>


            <textarea class="form-control" id="name_ar_desc_cat" hidden readonly rows="5" required name="name_ar_desc_cat"  ><%=categoryBean.getDescriptionAr()%></textarea>



            <textarea class="form-control" id="name_en_desc_cat" hidden readonly rows="5" required name="name_en_desc_cat"  ><%=categoryBean.getDescriptionEn()%></textarea>


            <img src="images/<%=categoryBean.getImagePath()%>"  id="logo" width="100" height="100"/>


            <div class="form-group">
                <label for="logo">Logo</label>

                <input class="form-control" id="logo" required type="file" name="logo" />
            </div>

            <div class="form-group">

                <button class="btn btn-primary" type="submit" onclick="if (confirm('Are you sure you want to update the image ?')) {
                            return true
                        } else {
                            return false
                        }">Update Category</button>
            </div>
        </form>
    </body>
</html>
