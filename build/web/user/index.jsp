<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/home.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/user.css"/>
        <link href="../fontawesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/table.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="../header.jsp"/>
        <%
            if (session.getAttribute("id") == null || session.getAttribute("id").equals("")) {
                response.sendRedirect("../");
            }
        %>
        <form action="${pageContext.request.contextPath}/search" method="post">
            <center>
                <table>
                    <tr>
                        <th>Search Criteria</th>
                        <th>
                            <select id="soflow" name="ddl_criteria">
                                <option value="email">Email ID</option>
                                <option value="uname">Username</option>
                                <option value="mobile">Mobile Number</option>
                            </select>
                        </th>
                    </tr>
                    <tr>
                        <th>
                            Enter value
                        </th>
                        <th>
                            <input type="search" placeholder="Search" name="txt_value">
                        </th>
                    </tr>
                    <tr>
                        <th colspan="2">
                    <center><input type="submit" id="submit"></center>
                    </th>
                    </tr>
                </table>
            </center>
        </form>
    </body>
</html>