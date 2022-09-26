import java.sql.*;
import java.util.ArrayList;
//Statement  –  Sử dụng để thực hiện các câu truy vấn SQL tĩnh
//PreparedStatement – Sử dụng để thực hiện các  câu truy vấn SQL động hoặc có tham số
//CallableStatement – Sử dụng để thực thi các stored procedures (Hiểu nôm na là các lệnh định nghĩa sẵn trên database)
public class EmployeeController {

    public ArrayList<Employee> getAllEmployees() throws Exception{
        ArrayList<Employee> arrEm = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection cnn = null;
        try {
            //tạo kết nối
            cnn = DBConnectionTest.getConnection();
            //tạo ra đối tượng lớp Statement
            stmt = cnn.createStatement();
            String SQL = "SELECT * from employee";
            rs = stmt.executeQuery(SQL);
            while (rs.next()){
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    String birthday = rs.getString(3);
                    String sex = rs.getString(4);
                    String country = rs.getString(5);
                    Employee emp = new Employee(id,name,birthday,sex,country);
                    arrEm.add(emp);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }finally {
            try {
                if(rs !=null){
                    rs.close();
                }
                if(stmt !=null){
                    stmt.close();
                }
                if(cnn !=null){
                    cnn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
                throw e;
            }
        }
        return arrEm;
    }
    public void addEmployee(Employee employee) throws SQLException{ //nem ra ngoai le de ben client bat duoc
        PreparedStatement stmt = null; // dùng để truy vấn các tham số ? trong query sql
        Connection conn = null;
        try {
            String SQL = "insert into employee(name,birthday,sex,country) values(?,?,?,?)";
            conn = DBConnectionTest.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1,employee.getName());
            stmt.setString(2,employee.getBirthday());
            stmt.setString(3,employee.getSex());
            stmt.setString(4,employee.getCountry());
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }finally {
            try {
                stmt.close();
                conn.close();
            }catch (Exception e){
                throw new SQLException(e.getMessage());
            }
        }
    }
}
