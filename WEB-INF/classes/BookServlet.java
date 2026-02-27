import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class BookServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            Connection con = DBConnection.getConnection();

            String service = req.getParameter("service");
            String from = req.getParameter("from");
            String to = req.getParameter("to");
            String journeyDate = req.getParameter("journey_date");
            String journeyTime = req.getParameter("journey_time");
            String seat = req.getParameter("seat");
            String totalStr = req.getParameter("total");

            if (service == null || from == null || to == null ||
                journeyDate == null || journeyTime == null ||
                seat == null || totalStr == null) {
                resp.getWriter().println("❌ Missing form data");
                return;
            }

            double total = Double.parseDouble(totalStr);

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO bookings(service, from_city, to_city, journey_date, journey_time, seat, total) VALUES (?,?,?,?,?,?,?)"
            );

            // ✅ SET ALL PARAMETERS
            ps.setString(1, service);
            ps.setString(2, from);
            ps.setString(3, to);
            ps.setDate(4, java.sql.Date.valueOf(journeyDate));
            ps.setString(5, journeyTime);
            ps.setString(6, seat);
            ps.setDouble(7, total);

            ps.executeUpdate();

            resp.sendRedirect("payment.html");

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("❌ Booking Error: " + e.getMessage());
        }
    }
}
