<%-- 
    Document   : login
    Created on : Feb 25, 2020, 11:16:30 PM
    Author     : HaithamGamal
--%>

<%@page import="net.haitham.otlobli.common.constant.AccountCookieRemember"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<!DOCTYPE html>



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
   <!--Made with love by Mutiullah Samim -->
   
	<!--Bootsrap 4 CDN-->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    
    <!--Fontawesome CDN-->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

	<!--Custom styles-->
	<link rel="stylesheet" type="text/css" href="styles.css">
        <style>
      
        </style>
    </head>
    <body>
       
         <br> <br> <br>
         <jsp:include page="header_logout.jsp" />
         <br><br><br>
          <%!   String id;
  private AccountCookieRemember checkCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        AccountCookieRemember acc = null;
        if (cookies == null) {
            return null;
        } else {

            String userName = "";
            String password = "";
             id = "";
            for (Cookie ck : cookies) {
                if (ck.getName().equalsIgnoreCase("username_remember")) {
                    userName = ck.getValue();

                }
                if (ck.getName().equalsIgnoreCase("password_remember")) {
                    password = ck.getValue();
                }
                 if (ck.getName().equalsIgnoreCase("id_remember")) {
                    id = ck.getValue();
                }

            }
            if (!userName.isEmpty() && !password.isEmpty()) {
                acc = new AccountCookieRemember(userName, password);
            }
        }
        
        return acc;
    }
  
  
 %>
 <%   AccountCookieRemember acc = checkCookie(request);
        if(acc != null){
//              session.setAttribute("session_username_remember", acc.getUserName());
//               session.setAttribute("session_password_remember", acc.getPassword());
//               session.setAttribute("session_id_remember", String.valueOf(id));
            //   request.getRequestDispatcher("profile.jsp").forward(request,response);
               String username = acc.getUserName();
               response.sendRedirect("admin_profile.jsp?msg="+username+"&userId="+Integer.parseInt(id));
        
        }
     
     %>
      
     <%String logOutExpired = request.getParameter("msg2");
     if(logOutExpired != null || logOutExpired != ""){
      
      
     %>
     <h1 style="color:green"><%=logOutExpired%></h1>
     <% 
     }
     %>
     <%
     String activationMsg = request.getParameter("activationMsg");
        %>
         <% if (activationMsg != null){
         
     
    %>
     <h1 style="color:green"><%=activationMsg+"now you can login easily thank you for verification"%></h1>
     <%
     } %>
         <h1 style="color:gold;">Welcome to login page </h1>
              

           
       <div class="container">
	<div class="d-flex justify-content-center h-100">
		<div class="card">
			<div class="card-header">
				<h3>Sign In</h3>
				<div class="d-flex justify-content-end social_icon">
					<span><i class="fab fa-facebook-square"></i></span>
					<span><i class="fab fa-google-plus-square"></i></span>
					<span><i class="fab fa-twitter-square"></i></span>
				</div>
			</div>
			<div class="card-body">
				<form  action="LoginServlet" method="post"  accept-charset="ISO-8859-1">
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-user"></i></span>
						</div>
						<input type="text" class="form-control" name="username" placeholder="name">
						
					</div>
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-key"></i></span>
						</div>
						<input type="password" class="form-control" name="password" placeholder="password">
					</div>
					<div class="row align-items-center remember">
						<input type="checkbox" name="remember">Remember Me
					</div>
					<div class="form-group">
						<input type="submit" value="Login" class="btn float-right login_btn">
					</div>
				</form>
			</div>
			<div class="card-footer">
				<div class="d-flex justify-content-center links">
					Don't have an account?<a href="signup_admin.jsp">Sign Up</a>
				</div>
				<div class="d-flex justify-content-center">
					<a href="forgetpassword.jsp">Forgot your password?</a>
				</div>
			</div>
		</div>
	</div>
</div>
    </body>
</html>
