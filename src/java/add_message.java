
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.servlet.http.*;

public class add_message extends HttpServlet {

    Connection cn;
    Statement st;
    PreparedStatement pst;
    ResultSet rs;
    String url = "jdbc:mysql://localhost:3306/messenger";
    String user = "root";
    String password = "1234";
    String qry, to_id, from_id, msg, date, time;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection(url, user, password);
            if (cn != null) {
                to_id = req.getParameter("to_id");
                from_id = req.getParameter("from_id");
                msg = req.getParameter("msg");
                java.util.Date dt = new java.util.Date();
                date = new SimpleDateFormat("dd/MM/yyyy").format(dt);
                time = new SimpleDateFormat("kk:mm:ss").format(dt);
                //st = cn.
                qry = "insert into tbl_messages_to_" + to_id + " values(?,?,?,?)";
                pst = cn.prepareStatement(qry);
                pst.setInt(1, Integer.parseInt(from_id));
                pst.setString(2, msg);
                pst.setString(3, date);
                pst.setString(4, time);
                pst.executeUpdate();
                qry = "insert into tbl_messages_to_" + to_id + " values(?,?,?,?)";
                pst = cn.prepareStatement(qry);
                pst.setInt(1, Integer.parseInt(to_id));
                pst.setString(2, msg);
                pst.setString(3, date);
                pst.setString(4, time);
                pst.executeUpdate();
                out.write("{ \"result\" : 1 }");

            } else {
                out.println("<script language='javascript'>alert('Connection not set');</script>");
                out.println("<script language='javascript'>window.location='chatroom.jsp';</script>");
            }

        } catch (Exception ex) {
            out.write("{ \"result\" : 0 }");
            //out.println("<script language='javascript'>alert('" + ex.toString() + "');</script>");
            //out.println("<script language='javascript'>window.location='chatroom.jsp';</script>");

        }
    }
}
