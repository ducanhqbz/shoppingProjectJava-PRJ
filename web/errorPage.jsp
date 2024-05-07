<%-- 
    Document   : errorPage
    Created on : Apr 21, 2024, 10:07:45 PM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p>${requestScope.errorMessage}</p>
    </body>
</html>
