import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionTest {
    private static final String DATABASE_URL = "jdbc:sqlserver://localhost:1433;DatabaseName=swing_demo;instance=SQLSERVER;encrypt=true;TrustServerCertificate=true;";
    private static final String
            DATABASE_USER_NAME = "sa";
    private static final String DATABASE_PASSWORD = "12";
    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // đăng ký driver class với method forname(). Phương thức này được sử dụng để tải động driver class
            conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER_NAME, DATABASE_PASSWORD); // thành lập sự kết nối với cơ sở dữ liệu
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }

}
