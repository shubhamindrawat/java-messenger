<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            session.removeAttribute("id");
            session.removeAttribute("email");
            session.removeAttribute("name");
            session.removeAttribute("mobile");
            response.sendRedirect("index.jsp");
        %>
    </body>
</html>
