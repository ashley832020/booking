import Utils.DBUtils;
import Utils.ErrorMessage;
import model.Room;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class BookingRoomController extends JFrame implements ErrorMessage, ActionListener {

    private JPanel mainPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    JTextField tfBegin = new JTextField(10);
    JTextField tfEnd = new JTextField(10);
    JTextField tfEnd1 = new JTextField(10);
    JTextField tfEnd2 = new JTextField(10);
    private JButton btnSearch;
    private JTextArea lbWay;

    public void MapLayout(String title) {
        setTitle(title);
        setLayout(new BorderLayout(5, 5));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(drawMenu(), BorderLayout.PAGE_START);
        add(drawLeftLayout(), BorderLayout.WEST);
        add(drawRightLayout(), BorderLayout.CENTER);
        setPreferredSize(new Dimension(1014, 597));
        pack();
        setVisible(true);
    }

    private JPanel drawLeftLayout() {

        JPanel panel = new JPanel(new BorderLayout());
        JPanel panelTop = new JPanel(new GridLayout(4, 1, 5, 5));
        JPanel panelBottom = new JPanel(new BorderLayout());
        JScrollPane scroll = new JScrollPane(lbWay = new JTextArea());
        scroll.setPreferredSize(panelTop.getPreferredSize());
        panelBottom.add(scroll);

        panel.add(panelTop, BorderLayout.PAGE_START);
        panel.add(panelBottom, BorderLayout.CENTER);

        makeJTextFieldGoFrom(panelTop, "Điểm đi", tfBegin);
        makeJTextFieldGoFrom(panelTop, "Điểm đến", tfEnd);
        makeSearchButton(panelTop);

        panel.setBorder(new EmptyBorder(0, 5, 0, 0));
        return panel;
    }

    private JPanel drawRightLayout() {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new TitledBorder(""));
        mainPanel.setBackground(null);

        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(mainPanel, BorderLayout.WEST);

        ArrayList<Room> rooms = DBUtils.getRooms();
        String col[] = {"Pos", "Team", "P", "W", "L"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        for (Room room : rooms) {
            Object[] data2 = {room.getCustomerName(), room.getRoomType(), room.getEmail(), room.getEmail(), room.getPhone()};
            tableModel.addRow(data2);
        }

        JTable table = new JTable(tableModel);


        rightPanel.add(table);


        return rightPanel;
    }

    public JScrollPane Login() {
        JFrame f;
        f = new JFrame();
        String data[][] = {{"101", "Amit", "670000"},
                {"102", "Jai", "780000"},
                {"101", "Sachin", "700000"}};
        String column[] = {"EMAIL", "NAME", "SALARY", "SALARY", "SALARY"};
        JTable jt = new JTable(data, column);
        jt.setBounds(30, 40, 200, 300);
        JScrollPane sp = new JScrollPane(jt);
        f.add(sp);
        f.setSize(300, 400);
        return sp;
    }

    private JMenuBar drawMenu() {
        JMenu menuFile = new JMenu("File");
        menuFile.add(createMenuItem("Exit", KeyEvent.VK_X, Event.CTRL_MASK));
        JMenu menuHelp = new JMenu("Help");
        menuHelp.setMnemonic(KeyEvent.VK_H);
        menuHelp.add(createMenuItem("About", KeyEvent.VK_A, Event.CTRL_MASK));
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuFile);
        menuBar.add(menuHelp);
        return menuBar;
    }

    private JMenuItem createMenuItem(String title, int keyEvent, int event) {
        JMenuItem mi = new JMenuItem(title);
        mi.setMnemonic(keyEvent);
        mi.setAccelerator(KeyStroke.getKeyStroke(keyEvent, event));
        mi.addActionListener(this);
        return mi;
    }

    private void makeJTextFieldGoFrom(JPanel panelTop, String title, JTextField tf) {
        JPanel panelSmall = new JPanel(new GridLayout(1, 2, 15, 5));
        panelSmall.setPreferredSize(new Dimension(200, 30));
        panelSmall.setBorder(new EmptyBorder(0, 15, 0, 5));
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder(title));
        panel.add(panelSmall);
        panel.add(tf);
        panelTop.add(panel);
    }

    private void makeSearchButton(JPanel panel) {
        JPanel panelRunTemp = new JPanel(new GridLayout(1, 2, 15, 5));
        panelRunTemp.setBorder(new EmptyBorder(0, 15, 0, 5));
        panelRunTemp.add(btnSearch = CustomButton("Tìm"));
        JPanel panelRun = new JPanel(new BorderLayout());
        panelRun.setBorder(new TitledBorder("Tìm đường"));
        panelRun.add(panelRunTemp);
        panel.add(panelRun);
    }

    private JButton CustomButton(String lable) {
        JButton btn = new JButton(lable);
        btn.addActionListener(this);
        return btn;
    }

    @Override
    public void errorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(mainPanel, errorMessage);
    }

    @Override
    public void successMessage(String successMessage) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        ArrayList<Room> rooms = DBUtils.getRooms();
//        String label = "";
//        String col[] = {"Pos","Team","P", "W", "L"};
//
//        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
//        // The 0 argument is number rows.
//
//
//        for (Room room : rooms) {
//
//            label = label + "\n" + room.getCustomerName() +  "\n";
//
//            Object[] data = {room.getRoomName(), room.getRoomType(), room.getEmail(), room.getCustomerName(), room.getPhone()};
//            tableModel.addRow(data);
//        }
//
//        JTable table = new JTable(tableModel);
//
//        System.out.println(" == " + label);
//
//        JLabel lblPassword = new JLabel(label);
//        lblPassword.setForeground(Color.BLACK);
//        lblPassword.setBackground(Color.CYAN);
//        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 31));
//        lblPassword.setBounds(0, 0, 1935, 552);


        String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};

        Object[][] data = {
                {"Kathy", "Smith",
                        "Snowboarding", new Integer(5), new Boolean(false)},
                {"John", "Doe",
                        "Rowing", new Integer(3), new Boolean(true)},
                {"Sue", "Black",
                        "Knitting", new Integer(2), new Boolean(false)},
                {"Jane", "White",
                        "Speed reading", new Integer(20), new Boolean(true)},
                {"Joe", "Brown",
                        "Pool", new Integer(10), new Boolean(false)}
        };

        JTable table2 = new JTable(data, columnNames);


        rightPanel.add(table2);
        repaint();
    }

    public void bookRoom() {
        dispose();
        Main main = new Main();
        main.Login();


//        Room bookRoom = new Room();
//        bookRoom.setEmail("");
//        bookRoom.setPhone("0988888888");
//        bookRoom.setRoomName("");
//        bookRoom.setCustomerName("Le Thanh Hai");
//        bookRoom.setRoomType(RoomType.EXPENSIVE);
//
//        BookingRoom bookingRoom = new BookingRoom();
//        bookingRoom.setErrorMessage(BookingRoomController.this);
//        bookingRoom.tryToBookRoom(bookRoom);
//
//        JLabel lblNewLabel = new JLabel("Login");
//        lblNewLabel.setForeground(Color.BLACK);
//        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
//        lblNewLabel.setBounds(423, 13, 273, 93);
//        f.add(lblNewLabel);
    }
}
