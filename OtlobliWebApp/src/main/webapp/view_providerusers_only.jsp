<%-- 
    Document   : view_city
    Created on : Feb 20, 2020, 3:30:28 PM
    Author     : HaithamGamal
--%>

<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="net.haitham.otlobli.common.bean.ProviderUserBean"%>
<%@page import="net.haitham.otloblidal.entity.annotation.ProviderUser"%>
<%@page import="java.util.List"%>
<%@page import="net.haitham.otlobli.common.bean.DeliveryAreaBean"%>
<%@page import="net.haitham.otlobli.common.bean.ProviderBean"%>
<%@page import="net.haitham.otlobli.common.bean.BranchBean"%>
<%@page import="net.haitham.otlobli.common.bean.AreaBean"%>
<%@page import="net.haitham.otlobli.common.bean.CityBean"%>
<%@page import="net.haitham.otloblibal.OtlobliBLGateway"%>
<%@page import="net.haitham.otloblibal.OtlobliBLGateway"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
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
    String rememberMessage = "";
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
        rememberMessage = "Welcome" + "     " + rememberUserName + "    " + "   to provider users  where you can edit provider users";
%>
<br><br><br>
<h1 style="color:gold;"><%="Welcome" + "  " + rememberUserName + "where you can manage edit provider user "%></h1>

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
<h1 style="color:gold;">Welcome <%=userName%> where you can edit provider user</h1>

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
    Integer providerId = Integer.parseInt(request.getParameter("providers"));
    ProviderBean providerBean = gateway.findProviderById(providerId);

    List<ProviderUserBean> providerUsersList = providerBean.getProviderUserList();

    request.setAttribute("providerBean", providerBean);
    request.setAttribute("message", message);
    request.setAttribute("providerId", providerId);
    request.setAttribute("providerUsersList", providerUsersList);


%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View provider users</title>
    </head>
    <body>

        <%           response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setHeader("Expires", "0"); //set proxies

        %>
        <jsp:include page="header_all.jsp" />
        <br> <br> <br> 

        <table class="table table-dark">
            <thead>

                <tr>
                    <th scope="col">ProviderID</th>
                    <th scope="col">Provider arabic name</th>
                    <th scope="col">Provider english name</th>
                    <th scope="col">Provider logo</th>




                </tr>
            </thead>
            <tbody> 
                <tr>
                    <td><c:out value="${providerBean.id}"/> </td>
                    <td><%=providerBean.getNameAr()%></td>
                    <td><c:out value="${providerBean.nameEn}"/></td>
                    <td><img src="images/${providerBean.logoPathEn}" width="100" height="100"/></td>
                </tr>
            </tbody>
        </table>
        <br> <br> <br> 
        <h1>Provider users</h1>
        <table  class="table table-hover" border='1' width='100%'>
            <tr>
                <th scope="col">ProviderUserID</th>
                <th scope="col">Provider username</th>
                <th scope="col">Provider user email</th>
                <th scope="col">Provider user password</th>
                <th scope="col">Provider user encrypted password</th>
                <th scope="col">Is active ?</th>

                <th scope="col">Edit provider username</th>
                <th scope="col">Delete provider username</th>
            </tr>

            <% for (ProviderUserBean providerUserBean : providerUsersList) {


            %>
            <% if (providerUserBean.getBranch() == null) {
            %>
            <tr>
                <td> <%=providerUserBean.getId()%> </td>
                <td> <%=providerUserBean.getUsername()%> </td>
                <td> <%=providerUserBean.getEmail()%> </td>
                <td> <%=providerUserBean.getPassword()%> </td>
                <td> <%=providerUserBean.getEncrypted_password()%> </td>
                <%
                    if (providerUserBean.getActive() == 1) {


                %>
                <td><%="Yes"%></td>
                <%
                    }
                %>
                <%
                    if (providerUserBean.getActive() != 1) {


                %>
                <td><%="No"%></td>
                <%
                    }

                %>
                <td> 
                    <a href="edit_provider_user_only.jsp?id=<%=providerUserBean.getId()%>&providerId=<%=providerId%>"class="btn btn-primary btn-lg active" role="button" aria-pressed="true" onclick="if (confirm('Are you sure you want to edit the record ?')) {
                                return true
                            } else {
                                return false}" > Edit provider user details"</a>
                </td>

                <td> 
                    <a href="DeleteProviderUserServletOnly?id=<%=providerUserBean.getId()%>&providerId=<%=providerId%>"class="btn btn-danger btn-lg active" role="button" aria-pressed="true" onclick="if (confirm('Are you sure you want to delete the record ?')) {
                                return true
                            } else {
                                return false }"> Delete provider user details</a>
                </td>

            </tr>
            <%                    }

                }
            %>




            <%--   <c:forEach items="${providerUsersList}" var="iProviderUserBean"> 
                    <c:choose> 
                        <c:when test="${(iProviderUserBean.branch == null)}"> 
                            <tr>
                                <c:out value="${(iProviderUserBean.branch == null)}"/>  ;


                            <td> <c:out value="${iProviderUserBean.id}"/> </td>
                            <td> <c:out value="${iProviderUserBean.username}"/> </td>
                            <td> <c:out value="${iProviderUserBean.email}"/> </td>
                            <td> <c:out value="${iProviderUserBean.password}"/> </td>
                            <td> <c:out value="${iProviderUserBean.encrypted_password}"/> </td>
                            <c:choose>  
                                <c:when test="${iProviderUserBean.active == 1}">  
                                    <td> <c:out value="${'yes'}"/> </td>  
                                </c:when>  

                                <c:otherwise>  
                                    <td> <c:out value="${'No'}"/> </td>  
                                </c:otherwise>  
                            </c:choose>


                            <td> 
                                <a href="edit_provider_user_only.jsp?id=${iProviderUserBean.id}&providerId=${providerId}"class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Edit provider user details</a>
                            </td>

                            <td> 
                                <a href="DeleteProviderUserServletOnly?id=${iProviderUserBean.id}&providerId=${providerId}" onclick="if (confirm('Are you sure you want to delete the record ?')) {
                                            return true
                                        } else {
                                            return false
                                        }" class="btn btn-danger btn-lg active" role="button" aria-pressed="true">Delete provider user</a>
                            </td> 

                        </tr>

                    </c:when>
                    <c:otherwise>


                    </c:otherwise>
                </c:choose> 
            </c:forEach>
            --%>
        </table>



        <p><c:out value="${message}"/></p>

        <a href='add_provider_user_only.jsp?providerId=${providerId}' class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Add new provider user</a>
    </body>
</html>
