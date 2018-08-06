
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.*;

public class get_msg extends HttpServlet {

    Connection cn;
    Statement st;
    ResultSet rs;
    String url = "jdbc:mysql://localhost:3306/messenger";
    String user = "root";
    String password = "1234";
    String qry, msg, date, time;
    int to_id, from_id;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection(url, user, password);
            if (cn != null) {
                to_id = Integer.parseInt(req.getParameter("to_id"));
                from_id = Integer.parseInt(req.getParameter("from_id"));
                st = cn.createStatement();
                qry = "(Select * From tbl_messages_to_" + from_id + " where friend_id=" + to_id + " ) union all (Select * From tbl_messages_to_" + to_id + " where friend_id=" + from_id + " )  order by msg_date,msg_time";
                rs = st.executeQuery(qry);
                int last_id = 0;

                if (rs.next()) {
                    do {
                        int id = rs.getInt("friend_id");
                        if (last_id == 0) {
                            if (id == from_id) {
                                out.write("<div class=\"mymsg\" ><p>" + rs.getString("message") + "</p>");
                            } else {
                                out.write("<div class=\"tomsg\" ><p>" + rs.getString("message") + "</p>");
                            }
                        } else {
                            if (last_id == id) {
                                out.write("<p>" + rs.getString("message") + "</p>");
                            } else {
                                if (id == from_id) {
                                    out.write("</div><div class=\"mymsg\" ><p>" + rs.getString("message") + "</p>");
                                } else {
                                    out.write("</div><div class=\"tomsg\" ><p>" + rs.getString("message") + "</p>");
                                }

                            }
                        }
                        last_id = rs.getInt("friend_id");
                    } while (rs.next());
                    out.write("</div>");
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(get_msg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
