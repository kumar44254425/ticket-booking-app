import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ticketapp?useSSL=false&serverTimezone=UTC",
                    "root",
                    "srija@9970");

            System.out.println("✅ DB Connected Successfully");

        } catch (Exception e) {
            System.out.println("❌ DB Connection Failed");
            e.printStackTrace();
        }
        return con;
    }
}
