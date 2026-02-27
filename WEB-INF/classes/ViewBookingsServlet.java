import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ViewBookingsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>My Bookings</title>");
        out.println("<link rel='stylesheet' href='" + req.getContextPath() + "/dashboard.css'>");
        out.println("</head>");
        out.println("<body class='dash-bg'>");

        out.println("<h2 class='title'>My Bookings</h2>");

        out.println("<div class='services-container'>");
        out.println("<div class='service-card' style='grid-column:1/-1;'>");

        out.println("<table class='booking-table'>");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>Service</th>");
        out.println("<th>From</th>");
        out.println("<th>To</th>");
        out.println("<th>Date</th>");
        out.println("<th>Time</th>");
        out.println("<th>Seat</th>");
        out.println("<th>Total</th>");
        out.println("<th>Action</th>");
        out.println("</tr>");

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "SELECT id, service, from_city, to_city, journey_date, journey_time, seat, total FROM bookings"
            );
            ResultSet rs = ps.executeQuery();

            boolean hasRows = false;
            while (rs.next()) {
                hasRows = true;
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("service") + "</td>");
                out.println("<td>" + rs.getString("from_city") + "</td>");
                out.println("<td>" + rs.getString("to_city") + "</td>");
                out.println("<td>" + rs.getDate("journey_date") + "</td>");
                out.println("<td>" + rs.getString("journey_time") + "</td>");
                out.println("<td>" + rs.getString("seat") + "</td>");
                out.println("<td>&#8377;" + rs.getDouble("total") + "</td>");
                out.println("<td><a class='cancel-btn' href='" + req.getContextPath() + "/cancel?id=" + rs.getInt("id") + "'>Cancel</a></td>");
                out.println("</tr>");
            }

            if (!hasRows) {
                out.println("<tr><td colspan='9' style='text-align:center; padding:12px;'>No bookings yet 😅</td></tr>");
            }

        } catch (Exception e) {
            out.println("<tr><td colspan='9'>Error: " + e.getMessage() + "</td></tr>");
        }

        out.println("</table>");
        out.println("</div>");
        out.println("</div>");

        out.println("<div style='text-align:center; margin-top:20px;'>");
        out.println("<a href='" + req.getContextPath() + "/dashboard.html' class='btn'>⬅ Back to Dashboard</a>");
        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }
}
