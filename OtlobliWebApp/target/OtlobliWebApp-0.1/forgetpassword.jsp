<%-- 
    Document   : forgetpassword
    Created on : Feb 29, 2020, 8:49:17 PM
    Author     : HaithamGamal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title> Forget Password ! </title>
    </head>
    <body>
        <jsp:include page="header_logout.jsp" />  
        <br>
        <br>
        <br>
        <br>
        <br>
       <form class="form-control" action="forgetpassword_result.jsp" method="post" >
           
            <div class="form-group">
                    
                    <div class="col-sm-10">
                        <input type="email" class="form-control" id="email" required placeholder="Enter email" name="email">
                    </div>
                </div>
             <div class="form-group">        
                    <div class="col-sm-offset-2 col-sm-10">
                   <input type="submit" class="btn btn-info"  value="Send Email"/>
                    </div>
                </div>
      
             
    </body>
</html>
