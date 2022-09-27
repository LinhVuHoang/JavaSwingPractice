import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class giaodien extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField textName;
    private JTextField textBirth;
    private JPanel contentPane;
    private JTable jTable;
    private JRadioButton JMale;
    private JRadioButton JFemale;
    private  JTextArea Jcountry;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    giaodien frame = new giaodien();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public giaodien() throws Exception {
        setBounds(200, 150, 1400, 1200);
        setResizable(true);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);
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
        Jcountry.setFont(new Font("Times New Roman",Font.PLAIN,20));
        Jcountry.setBounds(140,185,900,170);
        contentPane.add(Jcountry,BorderLayout.CENTER);

        JButton addbtn = new JButton("Thêm");
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
        contentPane.add(luubtn);

        JButton boquabtn = new JButton("Bỏ qua");
        boquabtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
        boquabtn.setBounds(660, 360, 120, 73);
        contentPane.add(boquabtn);

        JButton thoatbtn = new JButton("Thoát");
        thoatbtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
        thoatbtn.setBounds(790, 360, 120, 73);
        contentPane.add(thoatbtn);


        String data[][] = { { "101", "Tran Van Minh", "6000" },
                { "102", "Phan Van Tai", "8000" },
                { "101", "Do Cao Hoc", "7000" } };
        String column[] = { "ID", "NAME", "SALARY" };
        jTable = new JTable(data,column);
        jTable.setBounds(40, 450, 1200, 800);
        jTable.setSize(1200, 800);
        jTable.setVisible(true);
        jTable.setFont(new Font("Tahoma", Font.PLAIN, 24));
        jTable.setRowHeight(50);
        JScrollPane scrollPane = new JScrollPane(jTable);
        scrollPane.setBounds(40, 450, 1200, 400);
        contentPane.add(scrollPane);
    }
}
