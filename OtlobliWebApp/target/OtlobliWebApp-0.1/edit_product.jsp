<%-- 
    Document   : edit_country
    Created on : Feb 13, 2020, 3:34:26 AM
    Author     : HaithamGamal
--%>

<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>

<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="net.haitham.otlobli.common.bean.ProductBean"%>
<%@page import="java.util.List"%>
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
<h1 style="color:gold;"><%="Welcome" + "  " + rememberUserName + "where you can edit product "%></h1>

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
<h1 style="color:gold;">Welcome <%=userName%> where you can edit product</h1>

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
    Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));
    Integer providerId = Integer.parseInt(request.getParameter("providerId"));
    ProviderBean providerBean = gateway.findProviderById(providerId);
    Integer productId = Integer.parseInt(request.getParameter("id"));
    ProductBean productBean = gateway.findProductById(productId);
    List<CategoryBean> categoryLst = providerBean.getCategoryBeanList();


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
        <%            response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setHeader("Expires", "0"); //set proxies

        %>
        <jsp:include page="header_all.jsp" />
        <form action='EditProductServlet'method="post" enctype="multipart/form-data" accept-charset="ISO-8859-1">

            <input class="form-control" required hidden readonly type="text" name="providerId" value="<%=providerId%>"/>


            <div class="form-group">
                <label for="catid">CategoryID</label>
                <input class="form-control" required readonly type="text" name="catid" value="<%=categoryId%>"/>
            </div>
             <div class="form-group">
                <label for="id">ProductID</label>
                <input class="form-control" id="id" required readonly type="text" name="id" value="<%=productId%>"/>
            </div>





            <div class="form-group">
                <label for="name_ar_product">Name AR</label>
                <input class="form-control"id="name_ar_product"  required type="text" name="name_ar_product" value="<%=productBean.getNameAr()%>"/>
            </div>



            <div class="form-group">
                <label for="name_en_product">Name EN</label>
                <input class="form-control" id="name_en_product" required type="text" name="name_en_product" value="<%=productBean.getNameEn()%>"/>
            </div>


            <div class="form-group">
                <label for="name_ar_desc_product">Description AR</label>
                <textarea class="form-control" id="name_ar_desc_product" rows="5" required name="name_ar_desc_product"  ><%=productBean.getDescriptionAr()%></textarea>

            </div>




            <div class="form-group">
                <label for="name_en_desc_product">Description EN</label>
                <textarea class="form-control" id="name_en_desc_product" rows="5" required name="name_en_desc_product"  ><%=productBean.getDescriptionEn()%></textarea>
            </div>



            <div class="form-group">
                <label for="categories">Categories</label>
                <select name="categories" required class="form-control" id="categories">
                    <option disabled="disabled">--SELECT---</option>
                    <% for (CategoryBean categoryBeanE : categoryLst) {%>
                    <option 
                        <% if (categoryBeanE.getId().equals(categoryId)) {%>
                        selected = "selected"
                        <% }%>




                        value="<%=categoryBeanE.getId()%>"><%=categoryBeanE.getNameEn()%> 



                    </option>

                    <% }%>
                </select>
            </div>


            <div class="form-group">
                <label for="status">Available?</label>
                <select name="status" required class="form-control" id="status">
                    <option disabled="disabled">--SELECT---</option>
                    <option value="yes">Yes</option>
                    <option value="no">No</option>

                </select>

            </div>

            <div class="form-group">
                <label for="price">Price($)</label>

                <input type="number" step="any" id="price" value="<%=productBean.getPrice()%>" required class="form-control" id="fees" name="price" placeholder="type price">
            </div>


        

            <div class="form-group">

                <button class="btn btn-primary" type="submit" onclick="if (confirm('Are you sure you want to edit the record ?')) {
                            return true
                        } else {
                            return false
                        }">Update Product</button>
            </div>
        </form>
    </body>
</html>
