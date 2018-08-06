
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class login extends HttpServlet {

    Connection cn;
    String url = "jdbc:mysql://localhost:3306/messenger";
    String user = "root";
    String password = "1234";
    String qry;

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        try {
            res.setContentType("text/html");
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection(url, user, password);
            if (cn != null) {
                String email = req.getParameter("txt_email");
                String pass = req.getParameter("txt_pass");
                qry = "select * from tbl_users where email='" + email + "' and pass='" + pass + "'";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(qry);
                if (rs.next()) {
                    if (rs.getString("email").equals(email) && rs.getString("pass").equals(pass)) {
                        HttpSession session = req.getSession();
                        session.setAttribute("id", rs.getString("id"));
                        session.setAttribute("email", email);
                        session.setAttribute("uname", rs.getString("uname"));
                        session.setAttribute("mobile", rs.getString("mobile"));
                        out.println("<script language='javascript'>alert('Welcome');</script>");
                        res.sendRedirect("user/index.jsp");
                    } else {
                        out.println("<script language='javascript'>alert('Invalid credentials');</script>");
                        out.println("<script language='javascript'>window.location='index.jsp';</script>");
                    }
                } else {
                    out.println("<script language='javascript'>alert('Invalid credentials');</script>");
                    out.println("<script language='javascript'>window.location='index.jsp';</script>");
                }
            } else {
                out.println("<script language='javascript'>alert('Connection not set');</script>");
                out.println("<script language='javascript'>window.location='index.jsp';</script>");
            }
        } catch (Exception ex) {
            out.println("<script language='javascript'>alert('" + ex.toString() + "');</script>");
            out.println("<script language='javascript'>window.location='index.jsp';</script>");
        }
    }
}
