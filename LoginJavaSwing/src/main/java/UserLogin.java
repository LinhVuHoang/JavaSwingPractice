import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UserLogin extends JFrame {
    private static final long serialVersionUID = 1L;


    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JLabel label;
    private JPanel contentPane;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() { //sẽ chạy ứng dụng của chúng ta trong một luồng do EventQueue quản lý
            public void run() {
                try {
                    UserLogin frame = new UserLogin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public UserLogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // cho phép khi close chương trình thì cũng thoát chương trình code đang chạy
        setBounds(450, 190, 1014, 597); // set vị trí chiều dài chiều cao cùng tọa độ hiển thị
        setResizable(false); // cài đặt cho phép kéo thả thay đổi kích thước cửa sổ
        contentPane = new JPanel();// container cung cấp nơi chứa các component
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); //set border panel //thiết lập đường bao quanh của một jcomponent
        setContentPane(contentPane); //tạo một thành phần cho bảng nội dung
        contentPane.setLayout(null); // chỉ định bố cục trong container

        JLabel lblNewLabel = new JLabel("Login"); //set label
        lblNewLabel.setForeground(Color.red); //setcolor  font
        lblNewLabel.setFont(new Font("Lucida Bright", Font.PLAIN, 46)); // set font chữ theo các kiểu đơn giản , in nghiêng ...
        lblNewLabel.setBounds(423, 13, 273, 93); //set vị trí chiều dài chiều cao cùng tọa độ hiển thị
        contentPane.add(lblNewLabel); //add vào component

        textField = new JTextField(); //tạo textField (input)
        textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        textField.setBounds(481, 170, 281, 68);
        contentPane.add(textField);
        textField.setColumns(10); //Thiết lập số cột trong TextField này, và sau đó làm mất hiệu lựa layout đó

        passwordField = new JPasswordField(); //password field
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(481, 286, 281, 68);
        contentPane.add(passwordField);

        JLabel lblUsername = new JLabel("Username"); //tương tự label login
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblUsername.setBounds(250, 166, 193, 52);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password"); // tương tự label login
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblPassword.setBounds(250, 286, 193, 52);
        contentPane.add(lblPassword);

        btnNewButton = new JButton("Login");  //jbutton
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton.setBounds(545, 392, 162, 73);
        btnNewButton.addActionListener(new ActionListener() { //tạo event với button
            //Lớp xử lý ActionEvent nên triển khai interface ActionListener. Đối tượng của lớp đó phải được đăng ký với một thành phần. Đối tượng
            // có thể được đăng ký bởi sử dụng phương thức addActionListener(). Khi action event
            // xảy ra, phương thức actionPerformed() của đối tượng đó được gọi.
//void actionPerformed(ActionEvent e): Được triệu hồi khi một action xuất hiện.
            public void actionPerformed(ActionEvent e) {
                String userName = textField.getText(); //get value trong input
                String password = passwordField.getText();
                try {
                    Connection connection = DBConnectionFactory.getConnection(); //kêt nối db
///Giao diện PreparedStatement trong java là một sub-interface của Statement. Nó được sử dụng để thực hiện truy vấn tham số.
                    PreparedStatement st = connection
                            .prepareStatement("Select name, password from student where name=? and password=?"); //query với preparedStatement

                    st.setString(1, userName);
                    st.setString(2, password);
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        dispose(); // khiến jrame cửa sổ bị phá hủy và làm sạc
                        UserHome ah = new UserHome(userName); // mở userHome client
                        ah.setTitle("Chao mung"); // set title
                        ah.setVisible(true); //set hiện cửa sổ
                        JOptionPane.showMessageDialog(btnNewButton, "Ban da dang nhap thanh cong"); // message dialog
                    } else {
                        JOptionPane.showMessageDialog(btnNewButton, "User hoac password sai!");
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        });

        contentPane.add(btnNewButton); // add button vào component

//        label = new JLabel("");
//        label.setBounds(0, 0, 1008, 562);
//        contentPane.add(label);
    }
}