import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class View extends JFrame{
    private static final long serialVersionUID = 1L;
    private JTextField textName;
    private JTextField textBirth;
    private JPanel contentPane;
    private JTable jTable;
    private JRadioButton JMale;
    private JRadioButton JFemale;
    private  JTextArea Jcountry;
    private JScrollPane scrollPane;
    private  JButton addbtn;
    private DefaultTableModel model;
    private boolean check =false;
    EmployeeController employeeController = new EmployeeController();
//Xin vui lòng vào file porm.xml để chạy dependency trước khi chạy chương trình
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    View frame = new View();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public View() throws Exception {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // xét khi đóng ứng dụng cũng ừng chương trình
        setBounds(200, 150, 1400, 1200); // xét vị trí của frame
        setResizable(true); // cho phép kéo thả cửa sổ hay không
        contentPane = new JPanel(); // tạo component ( bộ chứa các thành phần như một container)
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane); // xét nội dung trong cư ổ là contentpane
        contentPane.setLayout(null); // xét layout cho container

        JLabel lblname =  new JLabel("Họ và tên:");
        lblname.setForeground(Color.red);
        lblname.setFont(new Font("Times New Roman",Font.PLAIN,20));
        lblname.setBounds(40, 13, 273, 50);
        contentPane.add(lblname);

        textName = new JTextField();
        textName.setFont(new Font("Tahoma", Font.PLAIN, 20));
        textName.setBounds(130, 13, 400, 60);
        contentPane.add(textName);
        textName.setColumns(10);

        JLabel lblbirth = new JLabel("Ngày Sinh:");
        lblbirth.setForeground(Color.red);
        lblbirth.setFont(new Font("Times New Roman",Font.PLAIN,20));
        lblbirth.setBounds(560, 13, 273, 50);
        contentPane.add(lblbirth);

        textBirth = new JTextField();
        textBirth.setFont(new Font("Tahoma", Font.PLAIN, 20));
        textBirth.setBounds(660, 13, 400, 60);
        contentPane.add(textBirth);
        textBirth.setColumns(10);

        JLabel lblsex =  new JLabel("Giới tính:");
        lblsex.setForeground(Color.BLACK);
        lblsex.setFont(new Font("Times New Roman",Font.PLAIN,20));
        lblsex.setBounds(40, 100, 273, 50);
        contentPane.add(lblsex);
        JMale = new JRadioButton("Nam");
        JMale.setFont(new Font("Tahoma", Font.PLAIN, 20));
        JMale.setBounds(120,100,70,50);
        contentPane.add(JMale);

        JFemale = new JRadioButton("Nữ");
        JFemale.setFont(new Font("Tahoma", Font.PLAIN, 20));
        JFemale.setBounds(200,100,100,50);
        contentPane.add(JFemale);

        JLabel lblcountry =  new JLabel("Quê quán:");
        lblcountry.setForeground(Color.BLACK);
        lblcountry.setFont(new Font("Times New Roman",Font.PLAIN,20));
        lblcountry.setBounds(40, 185, 273, 50);
        contentPane.add(lblcountry);

        Jcountry = new JTextArea();
     //   Jcountry.setBounds(140,185,1000,900);
        Jcountry.setFont(new Font("Times New Roman",Font.PLAIN,20));
       // Jcountry.setPreferredSize(new Dimension(100,100)); //setPreferredSize( new Dimension( 100 , 100 )); Mặc định flowLayout sẽ thiết lập kích thước cho các component con vừa đủ để bao bọc nội dung bên trong component đó,setPreferredSize( new Dimension( 100 , 100 )); Mặc định flowLayout sẽ thiết lập kích thước cho các component con vừa đủ để bao bọc nội dung bên trong component đó,
        contentPane.add(Jcountry,BorderLayout.CENTER);
        scrollPane = new JScrollPane(Jcountry,scrollPane.VERTICAL_SCROLLBAR_ALWAYS,scrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(140,185,900,170);
        contentPane.add(scrollPane);

        addbtn = new JButton("Thêm");
        addbtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
        addbtn.setBounds(140, 360, 120, 73);
        contentPane.add(addbtn);

        JButton suabtn = new JButton("Sửa");
        suabtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
        suabtn.setBounds(270, 360, 120, 73);
        contentPane.add(suabtn);


        JButton xoabtn = new JButton("Xoá");
        xoabtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
        xoabtn.setBounds(400, 360, 120, 73);
        contentPane.add(xoabtn);

        JButton luubtn = new JButton("Lưu lại");
        luubtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
        luubtn.setBounds(530, 360, 120, 73);
        luubtn.setEnabled(false);
        contentPane.add(luubtn);

        JButton boquabtn = new JButton("Bỏ qua");
        boquabtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
        boquabtn.setBounds(660, 360, 120, 73);
        boquabtn.setEnabled(false);
        contentPane.add(boquabtn);
        boquabtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                luubtn.setEnabled(false);
                check =false;
                textName.setText("");
                textBirth.setText("");
                Jcountry.setText("");
                JMale.setSelected(false);
                JFemale.setSelected(false);
                boquabtn.setEnabled(false);
            }
        });
        JButton thoatbtn = new JButton("Thoát");
        thoatbtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
        thoatbtn.setBounds(790, 360, 120, 73);
        contentPane.add(thoatbtn);
        thoatbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(thoatbtn,"Bạn đã Thoát chương trình");
                dispose();
            }
        });
        refreshTable();

        addbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                luubtn.setEnabled(true);
                boquabtn.setEnabled(true);
                check =true;
                textName.setText("");
                textBirth.setText("");
                Jcountry.setText("");
                JMale.setSelected(false);
                JFemale.setSelected(false);
                textName.requestFocus();
            }
        });
        luubtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textName.getText();
                String birthday = textBirth.getText();
                String country = Jcountry.getText();
                String sex="";
                if(JMale.isSelected()){
                    sex = "nam";
                }else if(JFemale.isSelected()) {
                    sex="nữ";
                }
                Employee employee = new Employee(name,birthday,sex,country);
                try {
                    employeeController.addEmployee(employee);
                    model.fireTableDataChanged();
                    JOptionPane.showConfirmDialog(luubtn,"Thêm nhân viên thành công");
                    luubtn.setEnabled(false);
                    check =false;
                    textName.setText("");
                    textBirth.setText("");
                    Jcountry.setText("");
                    JMale.setSelected(false);
                    JFemale.setSelected(false);
                    boquabtn.setEnabled(false);
                    refreshTable();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }
    public void refreshTable() throws Exception {
        String[] columnNames = { "STT", "Họ và Tên","Ngày sinh", "Giới Tính","Quê Quán" };
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        int id;String name,birthday,sex,country;
        ArrayList<Employee> employees = new ArrayList<>();
        employees = employeeController.getAllEmployees();
        for(int i=0;i<employees.size();i++){
            Employee employee = employees.get(i);
            System.out.println(employee.toString());
            id = employee.getId();
            name = employee.getName();
            birthday = employee.getBirthday();
            sex = employee.getSex();
            country = employee.getCountry();
            model.addRow(new Object[]{i+1,name,birthday,sex,country});
        }
        jTable = new JTable();
        jTable.setModel(model);
        jTable.setFont(new Font("Tahoma", Font.PLAIN, 24));
        // jTable.setBounds(40, 450, 1200, 400);
        jTable.setAutoCreateRowSorter(false);
        jTable.setRowHeight(50);
        jTable.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 24));
        jTable.getColumnModel().getColumn(0).setMinWidth(50);
        jTable.getColumnModel().getColumn(1).setMinWidth(300);
        jTable.getColumnModel().getColumn(2).setMinWidth(300);
        jTable.getColumnModel().getColumn(3).setMinWidth(100);
        jTable.getColumnModel().getColumn(4).setMinWidth(300);
        JScrollPane scrollPane1 = new JScrollPane(jTable);
        scrollPane1.setBounds(40, 450, 1200, 400);
        contentPane.add(scrollPane1);
            jTable.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (check == false) {
                        int index = jTable.getSelectedRow();
                        textName.setText((String) model.getValueAt(index, 1));
                        textBirth.setText((String) model.getValueAt(index, 2));
                        Jcountry.setText((String) model.getValueAt(index, 4));
                        String checksex = (String) model.getValueAt(index, 3);
                        System.out.println(checksex);
                        if (checksex.length() == 3) {
                            JMale.setSelected(true);
                            JFemale.setSelected(false);
                        } else {
                            JFemale.setSelected(true);
                            JMale.setSelected(false);
                        }
                    }
                }
            });
        }
}
