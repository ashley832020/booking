import Utils.DBUtils;
import Utils.ValidationMessage;
import model.Room;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Properties;

public class RoomListController extends JFrame implements ValidationMessage, ActionListener {

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
        JPanel panelTop = new JPanel(new GridLayout(6, 1, 5, 5));
        JPanel panelBottom = new JPanel(new BorderLayout());
        JScrollPane scroll = new JScrollPane(lbWay = new JTextArea());
        scroll.setPreferredSize(panelTop.getPreferredSize());
        panelBottom.add(scroll);

        panel.add(panelTop, BorderLayout.PAGE_START);
        panel.add(panelBottom, BorderLayout.CENTER);

        makeJTextFieldGoFrom(panelTop, "Điểm đi", tfBegin);
        makeJTextFieldGoFrom(panelTop, "Điểm đến", tfEnd);

        makeCalendarForm(panelTop);
        makeCalendarFormToDate(panelTop);

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

        JLabel lblNewLabel = new JLabel("Rooms");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        lblNewLabel.setBounds(423, 13, 273, 93);
        rightPanel.add(lblNewLabel);

        ArrayList<Room> rooms = DBUtils.getRooms();
        String col[] = {"Pos", "Team", "P", "W", "L"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        for (Room room : rooms) {
            Object[] data2 = {room.getCustomerName(), room.getRoomType(), room.getCustomerEmail(), room.getCustomerEmail(), room.getCustomerPhone()};
            tableModel.addRow(data2);

        }

        JTable table = new JTable(tableModel);
        rightPanel.add(table);
        return rightPanel;
    }

    private JMenuBar drawMenu() {
        JMenu menuFile = new JMenu("File");
        menuFile.add(createMenuItem("Danh Sách Phòng", KeyEvent.VK_X, Event.CTRL_MASK));
        menuFile.add(createMenuItem("Phòng Trống", KeyEvent.VK_X, Event.CTRL_MASK));
        menuFile.add(createMenuItem("Phòng", KeyEvent.VK_X, Event.CTRL_MASK));
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

    private void makeCalendarForm(JPanel panelTop) {

        UtilDateModel model = new UtilDateModel();
        model.setDate(1990, 8, 24);
        JDatePanelImpl datePanel = new JDatePanelImpl(model, new Properties());
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());

        JPanel panelSmall = new JPanel(new GridLayout(1, 2, 15, 5));
        panelSmall.setPreferredSize(new Dimension(200, 30));
        panelSmall.setBorder(new EmptyBorder(0, 15, 0, 5));
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder("From date"));
        panel.add(panelSmall);
        panel.add(datePicker);
        panelTop.add(panel);
    }

    private void makeCalendarFormToDate(JPanel panelTop) {

        UtilDateModel model = new UtilDateModel();
        model.setDate(2020, 8, 24);
        JDatePanelImpl datePanel = new JDatePanelImpl(model, new Properties());
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());

        JPanel panelSmall = new JPanel(new GridLayout(1, 2, 15, 5));
        panelSmall.setPreferredSize(new Dimension(200, 30));
        panelSmall.setBorder(new EmptyBorder(0, 15, 0, 5));
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder("To date"));
        panel.add(panelSmall);
        panel.add(datePicker);
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


    }
}
