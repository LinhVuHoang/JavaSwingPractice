import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionSQLSERVER {
    private static String DB_URL = "jdbc:sqlserver://localhost:1433;DatabaseName=swing_demo;instance=SQLSERVER;encrypt=true;TrustServerCertificate=true;"; // khai báo đường link connect jdbc
    private static String USER_NAME = "sa"; //username
    private static String PASSWORD = "12"; // password

    /**
     * main
     *
     * @author viettuts.vn
     * @param args
     */
    public static void main(String args[]) { // hàm main chạy chương trình
        try {
            // connnect to database 'testdb'
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD); // Connection và getConnection để kết nối db
            // crate statement
            Statement stmt = conn.createStatement(); // tạo statement để thực hiện query
            // get data from table 'student'
            ResultSet rs = stmt.executeQuery("select * from student"); // thực hiện query
            //  1.public ResultSet executeQuery(String sql): được sử dụng để thực thi truy vấn SELECT. Nó trả về đối tượng của ResultSet
            //  2.public int executeUpdate(String sql): được sử dụng để thực thi truy vấn cụ thể, nó có thể là create, drop, insert, update,delete,…
            //  3.public boolean execute(String sql): được sử dụng để thực thi các truy vấn có thể trả về nhiều kết quả
            //  4.public int[] executeBatch(): được sử dụng để thực hiện hàng loạt lệnh

            // show data
            while (rs.next()) { // result next sẽ lấy theo row nếu có thì in ra
                System.out.println(rs.getInt(1) + "  " + rs.getString(2)
                        + "  " + rs.getString(3));
            }
            // close connection
            conn.close(); // phải đóng kết nối sau khi xong để giải phóng tài nguyên
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // connection db trong try catch để bắt lỗi nếu có lỗi xảy ra
    }


    /**
     * create connection
     *
     * @author viettuts.vn
     * @param dbURL: database's url
     * @param userName: username is used to login
     * @param password: password is used to login
     * @return connection
     */
    public static Connection getConnection(String dbURL, String userName,
                                           String password) { // method getconnection tĩnh trả về đối tượng Connection
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // đăng ký driver class với method forname(). Phương thức này được sử dụng để tải động driver class
            conn = DriverManager.getConnection(dbURL, userName, password); // thành lập sự kết nối với cơ sở dữ liệu
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }
}
