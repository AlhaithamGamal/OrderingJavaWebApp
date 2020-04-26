<%-- 
    Document   : countries
    Created on : Feb 13, 2020, 3:19:46 AM
    Author     : HaithamGamal
--%>

<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>

<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="net.haitham.otlobli.common.bean.CategoryBean"%>
<%@page import="net.haitham.otlobli.common.bean.ProviderBean"%>
<%@page import="java.util.List"%>
<%@page import="net.haitham.otlobli.common.bean.CountryBean"%>
<%@page import="net.haitham.otloblibal.OtlobliBLGateway"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>  

<!DOCTYPE html>
<%
//    session = request.getSession();
//    String rememberUserName = String.valueOf(session.getAttribute("session_username_remember"));
//    String rememberPassword = String.valueOf(session.getAttribute("session_password_remember"));
//    Integer rememberId=Integer.parseInt(String.valueOf(session.getAttribute("session_id_remember")));
//    //String name="";
//    if (rememberUserName != null && rememberPassword != null && rememberId != null) {
 String rememberUserName = "";
    String rememberPassword = "";
    String rememberMessage="";
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
            rememberMessage = "Welcome"+"     "+rememberUserName+"    "+"   to categories where you can manage categories";
        %>
        <br><br><br>
<h1 style="color:gold;"><%="Welcome" +"  "+  rememberUserName +"  where you can manage and view categories "%></h1>

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
    String ordinaryMessage = "";
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
<h1 style="color:gold;">Welcome <%=userName%> where you can manage categories</h1>


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
<% String message = "";
    List<ProviderBean> providers = gateway.findProviders();
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
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <title>All Categories</title>
    </head>
    <body>
  <%
    
            response.setHeader("Cache-Control","no-cache,no-store,must-revalidate"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setHeader("Expires", "0"); //set proxies
    
    %>
    <%
         ApplicationContext appCon = new ClassPathXmlApplicationContext("AnnotationContext.xml");
OtlobliBLGateway gateway =(OtlobliBLGateway)appCon.getBean("otlobliBLGateway");
    Integer providerId = Integer.parseInt(request.getParameter("providerId"));
    ProviderBean providerBean = gateway.findProviderById(providerId);
    List<CategoryBean> lstCategories = providerBean.getCategoryBeanList();
   
   
%>
   
   <jsp:include page="header_all.jsp" />


        <p><%=message%></p>


        <a href='add_category.jsp?providerId=<%=providerId%>' class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Add New Category</a>
        <h1>Category List By JSP</h1>  

        <table class="table table-hover" border='1' width='100%'> 
              <tr> <th>Provider Name:</th></tr>
            <tr> <td> <%= providerBean.getNameEn()%></td></tr>
            <tr><th>Id</th>
                <th>Category Arabic Name</th>
                <th>Category English Name</th>
                <th>Category Description AR</th>
                <th>Category Description EN</th>
                 <th>Image</th>
                <th>Edit</th>
                  <th>Edit category image</th>
                <th>View Products </th>
                <th>Delete</th>
            </tr>
            <%
              for (CategoryBean categoryBean : lstCategories) {%>

            <tr><td> <%=categoryBean.getId()%></td>
                <td><%=categoryBean.getNameAr()%></td>
                  <td><%=categoryBean.getNameEn()%></td>
                <td><%=categoryBean.getDescriptionAr()%></td>
                <td><%=categoryBean.getDescriptionEn()%></td>
                <td><img src="images/<%=categoryBean.getImagePath()%>" width="100" height="100"/></td>

                <td>
                    <a href="edit_category.jsp?id=<%=categoryBean.getId()%>&providerId=<%=providerId%>"class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Edit Category</a>

                </td>
                
                <td>
                    <a href="edit_category_image.jsp?id=<%=categoryBean.getId()%>&providerId=<%=providerId%>"class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Edit Category Image</a>

                </td>
                <td>
                    <a href="view_products.jsp?categoryId=<%=categoryBean.getId()%>&providerId=<%=providerId%>"class="btn btn-primary btn-lg active" role="button" aria-pressed="true">View Products</a>

                </td> 
    
                <td>

                    <a href="DeleteCategoryServlet?id=<%=categoryBean.getId()%>&providerId=<%=providerId%>" onclick="if (confirm('Are you sure you want to delete the record ?')) {
                                return true
                            } else {
                                return false
                            }" class="btn btn-danger btn-lg active" role="button" aria-pressed="true">Delete Category</a>


                </td>
                <% }
                %>  
        </table> 
    </body>
</html>

