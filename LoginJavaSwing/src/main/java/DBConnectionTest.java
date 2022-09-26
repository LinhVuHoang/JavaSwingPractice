import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionTest {
    private static final String DATABASE_URL = "jdbc:sqlserver://localhost:1433;DatabaseName=swing_practice;instance=SQLSERVER;encrypt=true;TrustServerCertificate=true;";
    private static final String
            DATABASE_USER_NAME = "sa";
    private static final String DATABASE_PASSWORD = "Docker@123";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER_NAME, DATABASE_PASSWORD);
    }

}
