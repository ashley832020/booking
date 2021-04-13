import BookingRoom.BookingRoom;
import Utils.DBUtils;
import Utils.ValidationMessage;
import layout.ButtonColumn;
import layout.ColorRenderer;
import model.Room;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BookingRoomController extends JFrame implements ValidationMessage, ActionListener {

    private final JPanel mainPanel = new JPanel();
    private final JPanel rightPanel = new JPanel();
    private final JTextField tfCustomerName = new JTextField(10);
    private final JTextField tfCustomerPhone = new JTextField(10);
    private final JTextField tfCustomerEmail = new JTextField(10);
    private JButton btnRefresh;
    private JTextArea lbWay;
    private ArrayList<Room> rooms = DBUtils.getRooms(false);
    UtilDateModel modelFrom = new UtilDateModel();
    UtilDateModel modelTo = new UtilDateModel();

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
        JPanel panelTop = new JPanel(new GridLayout(8, 1, 5, 5));
        JPanel panelBottom = new JPanel(new BorderLayout());
        JScrollPane scroll = new JScrollPane(lbWay = new JTextArea());
        scroll.setPreferredSize(panelTop.getPreferredSize());
        panelBottom.add(scroll);

        panel.add(panelTop, BorderLayout.PAGE_START);
        panel.add(panelBottom, BorderLayout.CENTER);

        makeJTextFieldGoFrom(panelTop, "Name", tfCustomerName);
        makeJTextFieldGoFrom(panelTop, "Phone", tfCustomerPhone);
        makeJTextFieldGoFrom(panelTop, "Email", tfCustomerEmail);

//        String[] dayChoices = {"1 day", "2 days", "3 days", "4 days",
//                "5 days", "6 days"};
//        makeDaysNumber(panelTop, "How many days?", dayChoices);

        String[] numberChoices = {"All", "1", "2", "3", "4"};
        makeDaysNumber(panelTop, "How many beds?", numberChoices);

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

//        JLabel lblNewLabel = new JLabel("Rooms");
//        lblNewLabel.setForeground(Color.BLACK);
//        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
//        lblNewLabel.setBounds(423, 13, 273, 93);
//        rightPanel.add(lblNewLabel);


        String col[] = {"Pos", "Team", "P", "W", "K"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        for (Room room : rooms) {
            Object[] data2 = {"P" + room.getRoomNumber(), room.getPrice() + " $", room.getCapacity() + " people", room.getAvailable() + "", "Book"};
            tableModel.addRow(data2);
        }

        JTable table = new JTable(tableModel);
        table.setDefaultRenderer(Color.class, new ColorRenderer(true));
        Action delete = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());
                Room room = rooms.get(modelRow);
                roomDetailDialog(room);
//                ((DefaultTableModel)table.getModel()).removeRow(modelRow);
            }
        };

        ButtonColumn buttonColumn = new ButtonColumn(table, delete, 4);
        buttonColumn.setMnemonic(KeyEvent.VK_D);

        table.setDefaultRenderer(Double.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setForeground(((Double) value) > 0 ? Color.BLUE : Color.RED);
                return c;
            }
        });

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
        menuFile.add(createMenuItem("Danh Sách Phòng", KeyEvent.VK_X, Event.CTRL_MASK));
        menuFile.add(createMenuItem("Làm Mới", KeyEvent.VK_X, Event.CTRL_MASK));
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

    private void makeDaysNumber(JPanel panelTop, String title, String[] choices) {

        JPanel panelSmall = new JPanel(new GridLayout(1, 2, 15, 5));
        panelSmall.setPreferredSize(new Dimension(200, 30));
        panelSmall.setBorder(new EmptyBorder(0, 15, 0, 5));
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder(title));
        panel.add(panelSmall);
        final JComboBox<String> cb = new JComboBox<String>(choices);
        cb.setMaximumSize(cb.getPreferredSize()); // added code
        cb.setAlignmentX(Component.CENTER_ALIGNMENT);// added code
        //cb.setVisible(true); // Not needed
        cb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // search
                rooms = DBUtils.getRooms(false, cb.getSelectedItem().toString());
                refreshData();
            }
        });
        panel.add(cb);
        panelTop.add(panel);
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


        modelFrom.setDate(2020, 4, 1);
        JDatePanelImpl datePanel = new JDatePanelImpl(modelFrom, new Properties());
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

        modelTo.setDate(2020, 4, 30);
        JDatePanelImpl datePanel = new JDatePanelImpl(modelTo, new Properties());
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
        panelRunTemp.add(btnRefresh = CustomButton("Refesh"));
        JPanel panelRun = new JPanel(new BorderLayout());
        panelRun.setBorder(new TitledBorder("Get Newst List"));
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

    private void roomDetailDialog(Room room) {
        JFrame f = new JFrame();
        JDialog d = new JDialog(f, "Dialog Example", true);
        d.setLayout(new FlowLayout());
        JButton b = new JButton("OK");
//
        b.addActionListener(e -> {
            d.setVisible(false);

            if (modelFrom.getValue() == null) {
                errorMessage("Input From Date");
                return;
            }

            if (modelTo.getValue() == null) {
                errorMessage("Input To Date");
                return;
            }
            room.setBook(true);
            room.setAvailable("Busy");
            room.setCustomerName(tfCustomerName.getText());
            room.setCustomerPhone(tfCustomerPhone.getText());
            room.setCustomerEmail(tfCustomerEmail.getText());
            BookingRoom bookingRoom = new BookingRoom(room);
            bookingRoom.setErrorMessage(this);
            bookingRoom.tryToBookRoom();

            refreshData();
        });
        d.add(new JLabel(room.getRoomNumber()), BorderLayout.SOUTH);
        d.add(new JLabel(room.getCustomerName()), BorderLayout.SOUTH);
        d.add(new JLabel(room.getCustomerPhone()), BorderLayout.SOUTH);
        d.add(b);
        d.setSize(300, 300);
        d.setBounds(300, 200, 200, 200);
        d.setVisible(true);

    }

    private void refreshData() {

        //        remove(rightPanel);
        rightPanel.removeAll();
        drawRightLayout();
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String actionKey = e.getActionCommand();
        if (actionKey.equals("Danh Sách Phòng")) {
            RoomListController bookingRoomController = new RoomListController();
            bookingRoomController.MapLayout("Ahihi");
        }

        if (e.getSource() == btnRefresh) {
            rooms = DBUtils.getRooms(false);
            refreshData();
        }

        if (actionKey.equals("Làm Mới")) {
            rooms = DBUtils.getRooms(false);
            refreshData();
        }

        if (actionKey.equals("Làm Mới1")) {
            if (modelFrom.getValue() == null) {
                errorMessage("Input From Date");
                return;
            }

            if (modelTo.getValue() == null) {
                errorMessage("Input To Date");
                return;
            }

            SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
            try {
                Date date1 = myFormat.parse(modelFrom.getDay() + " " + modelFrom.getMonth() + " " + modelFrom.getYear());
                Date date2 = myFormat.parse(modelTo.getDay() + " " + modelTo.getMonth() + " " + modelTo.getYear());
                long diff = date2.getTime() - date1.getTime();
                System.out.println("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
            } catch (ParseException exx) {
                exx.printStackTrace();
            }
        }

        //        rooms.remove(0);
////        remove(rightPanel);
//        rightPanel.removeAll();
//        drawRightLayout();
//        rightPanel.revalidate();
//        rightPanel.repaint();
//
//        BookingRoom bookingRoom = new BookingRoom(rooms.get(0));
//        bookingRoom.setErrorMessage(this);
//        bookingRoom.tryToBookRoom();

    }


}


