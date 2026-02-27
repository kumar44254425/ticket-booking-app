import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CancelBookingServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(req.getParameter("id"));

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "UPDATE bookings SET status='CANCELLED' WHERE id=?"
            );
            ps.setInt(1, id);
            ps.executeUpdate();

            resp.sendRedirect("view");

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("Cancel Error: " + e);
        }
    }
}
