import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    private JScrollPane scrollPane1;
    private  JButton addbtn;
    private DefaultTableModel model;
    private boolean check =false;
    private  int idupdate;
    private  int idcheck;
    EmployeeController employeeController = new EmployeeController();
    Validate validate = new Validate();
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
        //giao diện
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
        JMale.setBounds(120,100,100,50);
        contentPane.add(JMale);

        JFemale = new JRadioButton("Nữ");
        JFemale.setFont(new Font("Tahoma", Font.PLAIN, 20));
        JFemale.setBounds(230,100,100,50);
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

        JButton thoatbtn = new JButton("Thoát");
        thoatbtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
        thoatbtn.setBounds(790, 360, 120, 73);
        contentPane.add(thoatbtn);

//function button

        //radiobutton

        JMale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JMale.isSelected()){
                    JFemale.setSelected(false);
                }
            }
        });
        JFemale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JFemale.isSelected()){
                    JMale.setSelected(false);
                }
            }
        });

        //add button

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


        //update button

        suabtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textName.getText();
                String birthday = textBirth.getText();
                String country = Jcountry.getText();
                String sex = "";
                if (JMale.isSelected()) {
                    sex = "Nam";
                } else if (JFemale.isSelected()) {
                    sex = "Nữ";
                }
                ArrayList<Employee> employees = null;
                if (!validate.isName(name)) {
                    JOptionPane.showMessageDialog(null, "Tên chỉ được từ 7 đến 25 ký tự bao gồm dấu cách và không chứa ký tự số hoặc ký tự đặc biệt","Thông Báo",JOptionPane.OK_OPTION);
                    textName.requestFocus();
                } else if (!validate.isDate(birthday)) {
                    JOptionPane.showMessageDialog(null, "Ngày được định dạng theo kiểu DD/MM/YYYY","Thông Báo",JOptionPane.OK_OPTION);
                    textBirth.requestFocus();
                } else if (!JMale.isSelected() && !JFemale.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Xin mời chọn giới tính","Thông Báo",JOptionPane.OK_OPTION);
                } else if (!validate.isCountry(country)) {
                    JOptionPane.showMessageDialog(null, "Xin mời nhập quê quán","Thông Báo",JOptionPane.OK_OPTION);
                    Jcountry.requestFocus();
                } else {
                    try {
                        employees = employeeController.getAllEmployees();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    for (int i = 0; i < employees.size(); i++) {
                        if (i == idcheck) {
                            Employee employee = employees.get(i);
                            idupdate = employee.getId();
                        }
                    }
                    Employee employee = new Employee(idupdate, name, birthday, sex, country);
                    try {
                        employeeController.updateEmployee(employee);
                        JOptionPane.showMessageDialog(null, "Cập nhật nhân viên thành công","Thông Báo",JOptionPane.INFORMATION_MESSAGE);
                        textName.setText(null);
                        textBirth.setText(null);
                        JMale.setSelected(false);
                        JFemale.setSelected(false);
                        Jcountry.setText(null);
                        refreshTable();
                        loadData();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        //delete button

        xoabtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Employee> employees = null;
                try {
                    employees = employeeController.getAllEmployees();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                for (int i=0;i<employees.size();i++){
                    if(i==idcheck){
                        Employee employee = employees.get(i);
                        idupdate =employee.getId();
                    }
                }
                try {
                    int checkdel=JOptionPane.showConfirmDialog(null,"Bạn muốn xóa nhân viên này","Thông Báo",JOptionPane.YES_NO_OPTION);
                    if(checkdel == JOptionPane.OK_OPTION) {
                        employeeController.deleteEmployee(idupdate);
                        JOptionPane.showMessageDialog(null, "Xóa nhân viên thành công", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
                        textName.setText(null);
                        textBirth.setText(null);
                        JMale.setSelected(false);
                        JFemale.setSelected(false);
                        Jcountry.setText(null);
                        refreshTable();
                        loadData();
                    }else {
                        refreshTable();
                        loadData();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    throw new RuntimeException(ex);
                }
            }
        });

        //boqua button

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

        //save button

        luubtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textName.getText();
                String birthday = textBirth.getText();
                String country = Jcountry.getText();
                String sex="";
                if(JMale.isSelected()){
                    sex = "Nam";
                }else if(JFemale.isSelected()) {
                    sex="Nữ";
                }
                Employee employee = new Employee(name,birthday,sex,country);
                if(!validate.isName(name)){
                    JOptionPane.showMessageDialog(null,"Tên chỉ được từ 7 đến 25 ký tự bao gồm dấu cách và không chứa ký tự số hoặc ký tự đặc biệt","Thông Báo",JOptionPane.OK_OPTION);
                    textName.requestFocus();
                }else if(!validate.isDate(birthday)){
                    JOptionPane.showMessageDialog(null,"Ngày được định dạng theo kiểu DD/MM/YYYY","Thông Báo",JOptionPane.OK_OPTION);
                    textBirth.requestFocus();
                }else if(!JMale.isSelected() && !JFemale.isSelected()){
                    JOptionPane.showMessageDialog(null,"Xin mời chọn giới tính","Thông Báo",JOptionPane.OK_OPTION);
                }else if(!validate.isCountry(country)){
                    JOptionPane.showMessageDialog(null,"Xin mời nhập quê quán","Thông Báo",JOptionPane.OK_OPTION);
                    Jcountry.requestFocus();
                }
                else {
                    try {
                        employeeController.addEmployee(employee);
                        JOptionPane.showMessageDialog(null,"Thêm nhân viên thành công","Thông Báo",JOptionPane.INFORMATION_MESSAGE);
                        luubtn.setEnabled(false);
                        check =false;
                        textName.setText("");
                        textBirth.setText("");
                        Jcountry.setText("");
                        JMale.setSelected(false);
                        JFemale.setSelected(false);
                        boquabtn.setEnabled(false);
                        refreshTable();
                        loadData();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }


            }
        });

        //exit button

        thoatbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              int checkout=  JOptionPane.showConfirmDialog(null,"Bạn muốn thoát chương trình?","Thông Báo",JOptionPane.YES_NO_OPTION);
              if(checkout==JOptionPane.OK_OPTION) {
                  JOptionPane.showMessageDialog(null, "Bạn đã Thoát chương trình", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
                  dispose();
              }
            }
        });

        //loadData table
        loadData();
    }
    //refresh table
    public void refreshTable() throws Exception {
        contentPane.remove(jTable); // phải remove để add lại tránh tình trạng bị ghi đè
        contentPane.remove(scrollPane1);
        }

    //table ( lưu ý phải có JscrollPane thì mới có thể hiện tên cột ( column name);

    public void loadData() throws Exception {
            String[] columnNames = {"STT", "Họ và Tên", "Ngày sinh", "Giới Tính", "Quê Quán"};
            model = new DefaultTableModel();
            model.setColumnIdentifiers(columnNames);
            String name, birthday, sex, country;
            ArrayList<Employee> employees = employeeController.getAllEmployees();
            for (int i = 0; i < employees.size(); i++) {
                Employee employee = employees.get(i);
                System.out.println(employee.toString());
                name = employee.getName();
                birthday = employee.getBirthday();
                sex = employee.getSex();
                country = employee.getCountry();
                model.addRow(new Object[]{i + 1, name, birthday, sex, country});
            }
            jTable = new JTable();
            jTable.setFont(new Font("Tahoma", Font.PLAIN, 24));
            JTableHeader header = jTable.getTableHeader(); // lấy table header
            DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer(); // tạo render default cho table
            cellRenderer.setHorizontalAlignment(JLabel.CENTER); //set render center cho JLabel
            header.setDefaultRenderer(cellRenderer); //set center cho header
            jTable.setModel(model);
            jTable.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
            jTable.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
            jTable.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
            jTable.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
            jTable.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
            // jTable.setBounds(40, 450, 1200, 400);
            jTable.setAutoCreateRowSorter(false);
            jTable.setRowHeight(50);
            jTable.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 24));
            jTable.getColumnModel().getColumn(0).setMinWidth(50);
            jTable.getColumnModel().getColumn(1).setMinWidth(300);
            jTable.getColumnModel().getColumn(2).setMinWidth(300);
            jTable.getColumnModel().getColumn(3).setMinWidth(100);
            jTable.getColumnModel().getColumn(4).setMinWidth(300);
            scrollPane1 = new JScrollPane(jTable);
            scrollPane1.setBounds(40, 450, 1200, 400);
            contentPane.add(scrollPane1);
            jTable.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (check == false) {
                        final int index = jTable.rowAtPoint(evt.getPoint());
                        idcheck = index;
                        textName.setText((String) model.getValueAt(index, 1));
                        textBirth.setText((String) model.getValueAt(index, 2));
                        Jcountry.setText((String) model.getValueAt(index, 4));
                        String checksex = (String) model.getValueAt(index, 3);
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
