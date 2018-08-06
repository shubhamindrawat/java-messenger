<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js" ></script>
        <link href="../fontawesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <title>Chatroom</title>
        <style>
            #msg_cont
            {
                border: 1px solid #f5f5f7;
                background-color: white;
                z-index: 1;
                box-shadow: 0 2px 10px rgba(0,0,0,0.1);
                border-radius: 0px;
                border-bottom: 1px solid rgba(0,0,0,0.09);
            }
            #msg_cont div{
                position: relative;
                width: inherit;
                margin: 0px;
                font-family: 'Microsoft JhengHei UI Light';
                padding: 3px 30px 3px 30px;
            }

            #msg_header
            {
                color: white;   
                background-color: #B0DD46;
            }

            #msg_body{
                height: 500px;
                overflow-y: auto;
                overflow-x: hidden;
            }
            #msg_footer{
                background-color: #f5f1ee;

            }
            #msg_footer input[type=text]{
                font-size: 15px;   
                height: 30px;
                border-radius: 2px;
                border: 2px solid #b0dd46;
                padding-left:  10px;
                width: 80%;
            }

            #msg_footer button{
                height: 30px;
                width: 100px;
                background-color: #b0dd46;
                border: 2px solid #b0dd46;
                color: white;
                box-shadow: 0 2px 10px rgba(0,0,0,0.1);
                border-radius: 0px;
            }

            body{
                background-color: #f5f5f7;
                margin-left: 20%;
                margin-right: 20%;
                outline: 0;

            }
            div.tomsg p
            {
                color: white;
                background: #b0dd46;
                border-radius: 5px 25px 25px 5px;
            }
            div.mymsg p
            {
                float: right;
                color: black;
                background: #eee;
                border-radius: 25px 5px 5px 25px;
            }
            #msg_body div{
                clear: both;
                display: block;
            }
            div p {
                clear: both;
                font-weight: 300;
                font-weight: 100;
                margin: 0 0 1px 0;
                padding: 10px 15px;
                width: auto;
                display: table;
            }

            div.tomsg p:first-child {
                border-radius: 25px 25px 25px 5px;
            }
            div.tomsg p:last-child {
                border-radius: 5px 25px 25px 25px;
            }

            div.mymsg p:first-child {
                border-radius: 25px 25px 5px 25px;
            }
            div.mymsg p:last-child {
                border-radius: 25px 5px 25px 25px;
            }

        </style>
    </head>
    <body>
        <jsp:include page="../header.jsp"/>
        <div id="msg_cont"> 
            <div id="msg_header">
                <%
                    if (session.getAttribute("id") == null || session.getAttribute("id").equals("")) {
                        response.sendRedirect("../");
                    }
                    String to_id = request.getParameter("user");
                    Connection cn;
                    Statement st;
                    String url = "jdbc:mysql://localhost:3306/messenger";
                    String user = "root";
                    String password = "1234";
                    String qry;

                    response.setContentType("text/html");
                    Class.forName("com.mysql.jdbc.Driver");
                    try {
                        cn = DriverManager.getConnection(url, user, password);
                        if (cn != null) {
                            st = cn.createStatement();
                            qry = "select * from tbl_users where id=" + to_id;
                            ResultSet rs = st.executeQuery(qry);
                            if (rs.next()) {
                                out.print("<h1>" + rs.getString("uname") + "</h1>");
                            }
                        }
                    } catch (Exception ex) {

                    }
                    out.print("<input type='hidden' value='" + session.getAttribute("id").toString() + "' id='from_id'>");
                    out.print("<input type='hidden' value='" + to_id + "' id='to_id'>");
                %>
            </div>
            <div id="msg_body">

            </div>
            <script>
                var task = true;
                setInterval(function () {
                    if (task)
                    {
                        task = false;
                        $.post("/Messenger/get_msg?to_id=<%=to_id%>&from_id=<%=session.getAttribute("id").toString()%>", function (data) {
                            if (data.indexOf("<div") !== -1)
                            {
                                $("#msg_body").html(data.trim());
                                var elem = document.getElementById("msg_body");
                                elem.scrollTop = elem.scrollHeight;
                            }

                        });
                        task = true;
                    }
                }, 2000);
            </script>
            <script>
                function send(e)
                {
                    if (e.keyCode == 13)
                        $("#msgbtn").trigger("click");
                    return true;
                }
            </script>
            <div id="msg_footer"> 
                <input type="text" autofocus="" onkeydown="send(event)" id="txt_msg" placeholder="Message" name="txt_msg">
                <button id="msgbtn" >Send</button>
                <script>
                    $("#msgbtn").click(function () {
                        var msg = $("#txt_msg").val().trim();
                        if (msg != "")
                        {
                            if ($("#msg_body div:last-child").length) {
                                if ($("#msg_body div:last-child").attr("class") == 'mymsg')
                                {
                                    $("#msg_body div:last-child").append("<p>" + msg + "</p>");

                                } else
                                {
                                    $("#msg_body").append("<div class='mymsg'><p>" + msg + "</p></div>");
                                }
                            } else
                            {
                                $("#msg_body").append("<div class='mymsg'><p>" + msg + "</p></div>");
                            }
                            $.post("${pageContext.request.contextPath}/add_message?to_id=" + $("#to_id").val() + "&from_id=" + $("#from_id").val() + "&msg=" + msg, function (data) {
                            });
                            $("#txt_msg").val("");

                            var elem = document.getElementById("msg_body");
                            elem.scrollTop = elem.scrollHeight;
                        }
                    });
                </script>
            </div>
        </div>
    </body>
</html>
