import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

public class register extends HttpServlet {

    Connection cn;
    PreparedStatement pst;
    Statement st;
    String url = "jdbc:mysql://localhost:3306/messenger";
    String user = "root";
    String password = "1234";
    String qry, email, msg_to_tab_name, msg_from_tab_name;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        try {
            res.setContentType("text/html");
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection(url, user, password);
            cn.setAutoCommit(false);
            st = cn.createStatement();
            if (cn != null) {
                String pass = req.getParameter("txt_pass");
                String cnf_pass = req.getParameter("txt_cnf_pass");
                if (pass.equals(cnf_pass)) {
                    String email = req.getParameter("txt_email");
                    String mobile = req.getParameter("txt_mobile");
                    String uname = req.getParameter("txt_uname");
                    String select_qry = "select * from tbl_users where email='" + email + "'";
                    ResultSet rs = st.executeQuery(select_qry);
                    if (rs.next()) {
                        out.println("<script language='javascript'>alert('A User with this email already exists');</script>");
                        cn.close();
                        out.println("<script language='javascript'>window.location='register.jsp';</script>");
                    } else {
                        select_qry = "select * from tbl_users where uname='" + uname + "'";
                        rs = st.executeQuery(select_qry);
                        if (rs.next()) {
                            out.println("<script language='javascript'>alert('A User with this username already exists');</script>");
                            cn.close();
                            out.println("<script language='javascript'>window.location='register.jsp';</script>");
                        } else {
                            select_qry = "select * from tbl_users where mobile='" + mobile + "'";
                            rs = st.executeQuery(select_qry);
                            if (rs.next()) {
                                out.println("<script language='javascript'>alert('A User with this mobile number already exists');</script>");
                                cn.close();
                                out.println("<script language='javascript'>window.location='register.jsp';</script>");
                            } else {
                                qry = "insert into tbl_users(email,mobile,uname,pass) values(?,?,?,?)";
                                pst = cn.prepareStatement(qry);
                                pst.setString(1, email);
                                pst.setString(2, mobile);
                                pst.setString(3, uname);
                                pst.setString(4, pass);
                                pst.executeUpdate();
                                cn.commit();
                                String id_qry = "select id from tbl_users where email='" + email + "'";
                                ResultSet rs1 = st.executeQuery(id_qry);
                                rs1.next();
                                int id = Integer.parseInt(rs1.getString("id"));
                                msg_to_tab_name = "tbl_messages_to_" + id;
                                msg_from_tab_name = "tbl_messages_from_" + id;
                                String qry1 = "create table " + msg_to_tab_name + "(" + "friend_id int references tbl_users(id)," + "message varchar(1000)," + "msg_date varchar(30)," + "msg_time varchar(30)" + ");";
                                String qry2 = "create table " + msg_from_tab_name + "(" + "friend_id int references tbl_users(id)," + "message varchar(1000)," + "msg_date varchar(30)," + "msg_time varchar(30)" + ");";
                                st.execute(qry1);
                                st.execute(qry2);
                                out.println("<script language='javascript'>alert('User successfully registered. You will now be redirected to the login page within 10 Seconds.');</script>");
                                cn.commit();
                                cn.close();
                                out.println("<script language='javascript'>window.location='index.jsp';</script>");
                            }
                        }
                    }
                } else {
                    out.println("<script language='javascript'>alert('Both the passwords should be same');</script>");
                    cn.close();
                    out.println("<script language='javascript'>window.location='register.jsp';</script>");
                }
            } else {
                out.println("<script language='javascript'>alert('Connection not set');</script>");
                out.println("<script language='javascript'>window.location='register.jsp';</script>");
            }
        } catch (SQLException ex) {
            try {
                cn.rollback();
                String qry_del = "delete from tbl_users where email='" + req.getParameter("txt_email") + "';";
                st.executeUpdate(qry_del);
                st.execute("commit;");
                cn.commit();
                ResultSet rs1 = st.executeQuery("select count(*) from information_schema.tables where table_schema='messenger' and table_name='" + msg_to_tab_name + "';");
                if (rs1.next()) {
                    st.execute("drop table " + msg_to_tab_name + ";");
                    cn.commit();
                }
                ResultSet rs2 = st.executeQuery("select * from information_schema.tables where table_schema='messenger' and table_name='" + msg_from_tab_name + "';");
                if (rs2.next()) {
                    st.execute("drop table " + msg_from_tab_name + ";");
                    cn.commit();
                }
                out.println("<script language='javascript'>alert('" + ex.toString() + "');</script>");
                out.println("<script language='javascript'>window.location='register.jsp';</script>");
            } catch (SQLException ex1) {
                out.println("<script language='javascript'>alert('Due to some internal errors the user cannot be registered. Please try again.');</script>");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
            out.println("<script language='javascript'>alert('" + ex.toString() + "');</script>");
            out.println("<script language='javascript'>window.location='register.jsp';</script>");
        }
    }
}