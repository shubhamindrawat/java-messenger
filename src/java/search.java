
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class search extends HttpServlet {

    Connection cn;
    Statement st;
    String url = "jdbc:mysql://localhost:3306/messenger";
    String user = "root";
    String password = "1234";
    String qry;

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        req.setAttribute("user/index.jsp", 1);
        res.setContentType("text/html");
        try {
            req.getRequestDispatcher("user/index.jsp").include(req, res);
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection(url, user, password);
            if (cn != null) {
                String criteria = req.getParameter("ddl_criteria");
                String value = req.getParameter("txt_value");
                st = cn.createStatement();
                HttpSession session = req.getSession();
                qry = "select * from tbl_users where " + criteria + " like '%" + value + "%' and id!=" + session.getAttribute("id");
                ResultSet rs = st.executeQuery(qry);
                if (rs.next()) {
                    out.println("<form action='user/chatroom.jsp' method='post'><center>");
                    out.println("<table class='rwd-table'>");
                    out.println("<tr><th>Username</th><th>Email Id</th><th>Mobile No</th><th>Click to</th></tr>");
                    do {
                        out.println("<tr>");
                        out.println("<td  data-th=\"Username\">" + rs.getString("uname") + "</td><td data-th=\"Email ID: \">" + rs.getString("email") + "</td><td data-th=\"Mobile No: \">" + rs.getString("mobile") + "</td><td data-th=\"Cilck to\"><button name='user' value='" + rs.getString("id") + "'>Start Chatting</button></td>");
                        out.println("</tr>");
                    } while (rs.next());
                    out.println("</table>");
                    out.println("</center></form>");
                } else {
                    out.println("<script language='javascript'>alert('No such user exists.');</script>");
                    out.println("<script language='javascript'>window.location='user/index.jsp';</script>");
                }
            } else {
                out.println("<script language='javascript'>alert('Connection not set');</script>");
                out.println("<script language='javascript'>window.location='index.jsp';</script>");
            }
        } catch (Exception ex) {
            out.println("<script language='javascript'>alert('" + ex.toString() + "');</script>");
        }
    }
}
