import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

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
    public View(){
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

        JButton thoatbtn = new JButton("Thoát");
        thoatbtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
        thoatbtn.setBounds(790, 360, 120, 73);
        contentPane.add(thoatbtn);

        String[][] data = {
                { "1","Kundan Kumar Jha","23/09/2000", "Nam", "USA" },
                { "2","Anand Jha", "09/10/1997","Nữ", "Japan" }
        };
        String[] columnNames = { "STT", "Họ và Tên","Ngày sinh", "Giới Tính","Quê Quán" };
        jTable = new JTable(data,columnNames);
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

    }
}
