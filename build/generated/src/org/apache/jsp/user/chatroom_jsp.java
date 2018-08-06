package org.apache.jsp.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class chatroom_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        ");

            if (session.getAttribute("id") == null || session.getAttribute("id").equals("")) {
                response.sendRedirect("../");
            }
        
      out.write("\n");
      out.write("        <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/js/jquery-3.2.1.min.js\" ></script>\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        <style>\n");
      out.write("            #msg_cont\n");
      out.write("            {\n");
      out.write("                border: 1px solid #f5f5f7;\n");
      out.write("                background-color: white;\n");
      out.write("                z-index: 1;\n");
      out.write("                box-shadow: 0 2px 10px rgba(0,0,0,0.1);\n");
      out.write("                border-radius: 0px;\n");
      out.write("                border-bottom: 1px solid rgba(0,0,0,0.09);\n");
      out.write("            }\n");
      out.write("            #msg_cont div{\n");
      out.write("                position: relative;\n");
      out.write("                width: inherit;\n");
      out.write("                margin: 0px;\n");
      out.write("                font-family: 'Microsoft JhengHei UI Light';\n");
      out.write("                padding: 3px 30px 3px 30px;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            #msg_header\n");
      out.write("            {\n");
      out.write("                color: white;   \n");
      out.write("                background-color: #B0DD46;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            #msg_body{\n");
      out.write("                height: 500px;\n");
      out.write("                overflow-y: auto;\n");
      out.write("                overflow-x: hidden;\n");
      out.write("            }\n");
      out.write("            #msg_footer{\n");
      out.write("                background-color: #f5f1ee;\n");
      out.write("\n");
      out.write("            }\n");
      out.write("            #msg_footer input[type=text]{\n");
      out.write("                font-size: 15px;   \n");
      out.write("                height: 30px;\n");
      out.write("                border-radius: 2px;\n");
      out.write("                border: 2px solid #b0dd46;\n");
      out.write("                padding-left:  10px;\n");
      out.write("                width: 80%;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            #msg_footer button{\n");
      out.write("                height: 30px;\n");
      out.write("                width: 100px;\n");
      out.write("                background-color: #b0dd46;\n");
      out.write("                border: 2px solid #b0dd46;\n");
      out.write("                color: white;\n");
      out.write("                box-shadow: 0 2px 10px rgba(0,0,0,0.1);\n");
      out.write("                border-radius: 0px;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            body{\n");
      out.write("                background-color: #f5f5f7;\n");
      out.write("                margin-left: 20%;\n");
      out.write("                margin-right: 20%;\n");
      out.write("                outline: 0;\n");
      out.write("\n");
      out.write("            }\n");
      out.write("            div.tomsg p\n");
      out.write("            {\n");
      out.write("                color: white;\n");
      out.write("                background: #b0dd46;\n");
      out.write("                border-radius: 5px 25px 25px 5px;\n");
      out.write("            }\n");
      out.write("            div.mymsg p\n");
      out.write("            {\n");
      out.write("                float: right;\n");
      out.write("                color: black;\n");
      out.write("                background: #eee;\n");
      out.write("                border-radius: 25px 5px 5px 25px;\n");
      out.write("            }\n");
      out.write("            #msg_body div{\n");
      out.write("                clear: both;\n");
      out.write("                display: block;\n");
      out.write("            }\n");
      out.write("            div p {\n");
      out.write("                clear: both;\n");
      out.write("                font-weight: 300;\n");
      out.write("                font-weight: 100;\n");
      out.write("                margin: 0 0 1px 0;\n");
      out.write("                padding: 10px 15px;\n");
      out.write("                width: auto;\n");
      out.write("                display: table;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            div.tomsg p:first-child {\n");
      out.write("                border-radius: 25px 25px 25px 5px;\n");
      out.write("            }\n");
      out.write("            div.tomsg p:last-child {\n");
      out.write("                border-radius: 5px 25px 25px 25px;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            div.mymsg p:first-child {\n");
      out.write("                border-radius: 25px 25px 5px 25px;\n");
      out.write("            }\n");
      out.write("            div.mymsg p:last-child {\n");
      out.write("                border-radius: 25px 5px 25px 25px;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../header.jsp", out, false);
      out.write("\n");
      out.write("        <div id=\"msg_cont\"> \n");
      out.write("            <div id=\"msg_header\">\n");
      out.write("                ");

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
                
      out.write("\n");
      out.write("            </div>\n");
      out.write("            <div id=\"msg_body\">\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("            <script>\n");
      out.write("                var task = true;\n");
      out.write("                setInterval(function () {\n");
      out.write("                    if (task)\n");
      out.write("                    {\n");
      out.write("                        task = false;\n");
      out.write("                        $.post(\"/Messenger/get_msg?to_id=");
      out.print(to_id);
      out.write("&from_id=");
      out.print(session.getAttribute("id").toString());
      out.write("\", function (data) {\n");
      out.write("                            if (data.indexOf(\"<div\") !== -1)\n");
      out.write("                            {\n");
      out.write("                                $(\"#msg_body\").html(data.trim());\n");
      out.write("                                var elem = document.getElementById(\"msg_body\");\n");
      out.write("                                elem.scrollTop = elem.scrollHeight;\n");
      out.write("                            }\n");
      out.write("\n");
      out.write("                        });\n");
      out.write("                        task = true;\n");
      out.write("                    }\n");
      out.write("                }, 2000);\n");
      out.write("            </script>\n");
      out.write("            <script>\n");
      out.write("                function send(e)\n");
      out.write("                {\n");
      out.write("                    if (e.keyCode == 13)\n");
      out.write("                        $(\"#msgbtn\").trigger(\"click\");\n");
      out.write("                    return true;\n");
      out.write("                }\n");
      out.write("            </script>\n");
      out.write("            <div id=\"msg_footer\"> \n");
      out.write("                <input type=\"text\" autofocus=\"\" onkeydown=\"send(event)\" id=\"txt_msg\" placeholder=\"Message\" name=\"txt_msg\">\n");
      out.write("                <button id=\"msgbtn\" >Send</button>\n");
      out.write("                <script>\n");
      out.write("                    $(\"#msgbtn\").click(function () {\n");
      out.write("                        var msg = $(\"#txt_msg\").val().trim();\n");
      out.write("                        if (msg != \"\")\n");
      out.write("                        {\n");
      out.write("                            if ($(\"#msg_body div:last-child\").length) {\n");
      out.write("                                if ($(\"#msg_body div:last-child\").attr(\"class\") == 'mymsg')\n");
      out.write("                                {\n");
      out.write("                                    $(\"#msg_body div:last-child\").append(\"<p>\" + msg + \"</p>\");\n");
      out.write("\n");
      out.write("                                } else\n");
      out.write("                                {\n");
      out.write("                                    $(\"#msg_body\").append(\"<div class='mymsg'><p>\" + msg + \"</p></div>\");\n");
      out.write("                                }\n");
      out.write("                            } else\n");
      out.write("                            {\n");
      out.write("                                $(\"#msg_body\").append(\"<div class='mymsg'><p>\" + msg + \"</p></div>\");\n");
      out.write("                            }\n");
      out.write("                            $.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/add_message?to_id=\" + $(\"#to_id\").val() + \"&from_id=\" + $(\"#from_id\").val() + \"&msg=\" + msg, function (data) {\n");
      out.write("                            });\n");
      out.write("                            $(\"#txt_msg\").val(\"\");\n");
      out.write("\n");
      out.write("                            var elem = document.getElementById(\"msg_body\");\n");
      out.write("                            elem.scrollTop = elem.scrollHeight;\n");
      out.write("                        }\n");
      out.write("                    });\n");
      out.write("                </script>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
