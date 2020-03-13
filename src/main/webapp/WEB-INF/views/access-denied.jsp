<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

    <head>
        <title>Access Denied</title>
        <meta charset="utf-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Online Bank</title>
        
    </head>

    <body>

        <h2>Access Denied - You are not authorized to access this resource.</h2>

        <hr>
        <form:form action="${pageContext.request.contextPath}/logout" 
                   method="POST">

            <input type="submit" value="Logout" />

        </form:form>
        
    </body>

</html>