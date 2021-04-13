import BookingRoom.BookingRoom;
import Utils.ConstantsKey;
import Utils.DBUtils;
import payment.Payment;
import validation.ValidationMessage;
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

    private ArrayList<Room> rooms = DBUtils.getRoomsBusyOrReady(false, ConstantsKey.ROOM_STATUS_READY);
    private final UtilDateModel modelFrom = new UtilDateModel();
    private final UtilDateModel modelTo = new UtilDateModel();

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

        String[] numberChoices = {"All", "1", "2", "3", "4"};
        makeDaysNumber(panelTop, "How many beds?", numberChoices);

        makeCalendarForm(panelTop);
        makeCalendarFormToDate(panelTop);
        panel.setBorder(new EmptyBorder(0, 5, 0, 0));
        return panel;
    }

    private JPanel drawRightLayout() {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new TitledBorder(""));
        mainPanel.setBackground(null);

        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(mainPanel, BorderLayout.WEST);

        String col[] = {"A", "B", "C", "D", "E"};
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

    private JMenuBar drawMenu() {
        JMenu menuFile = new JMenu("File");
        menuFile.add(createMenuItem("Danh Sách Phòng", KeyEvent.VK_X, Event.ENTER));
        menuFile.add(createMenuItem("Phòng Đã Đặt", KeyEvent.VK_X, Event.ENTER));
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
        cb.setMaximumSize(cb.getPreferredSize());
        cb.setAlignmentX(Component.CENTER_ALIGNMENT);
        cb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rooms = DBUtils.getRoomsWithCapacity(false, cb.getSelectedItem().toString());
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

    @Override
    public void errorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(mainPanel, errorMessage);
    }

    @Override
    public void successMessage(String successMessage) {
        JOptionPane.showMessageDialog(mainPanel, successMessage);
    }

    private void roomDetailDialog(Room room) {

        if (modelFrom.getValue() == null) {
            errorMessage("Input from Date");
            return;
        }

        if (modelTo.getValue() == null) {
            errorMessage("Input to Date");
            return;
        }

        JFrame f = new JFrame();
        JDialog d = new JDialog(f, "Thanh Toán", true);
        d.setLayout(new FlowLayout());
        JButton card = new JButton("CARD");
        JButton cash = new JButton("CASH");

        cash.addActionListener(e -> {
            d.setVisible(false);
            room.setPaymentMethod(ConstantsKey.PAYMENT_CASH);
            doBookRoom(room);
            Payment payment = new Payment();
            payment.payment(ConstantsKey.PAYMENT_CASH);

        });

        card.addActionListener(e -> {
            d.setVisible(false);
            room.setPaymentMethod(ConstantsKey.PAYMENT_CARD);
            doBookRoom(room);
            Payment payment = new Payment();
            payment.payment(ConstantsKey.PAYMENT_CARD);
        });
        d.add(new JLabel("Need To Pay"), BorderLayout.SOUTH);
        d.add(new JLabel(payMoney(Integer.parseInt(room.getPrice())) + " $"), BorderLayout.SOUTH);
//        d.add(new JLabel(room.getCustomerPhone()), BorderLayout.SOUTH);
        d.add(card);
        d.add(cash);
        d.setSize(300, 100);
        d.setBounds(300, 200, 200, 200);
        d.setVisible(true);
    }

    private void doBookRoom(Room room) {
        room.setBook(true);
        room.setAvailable(ConstantsKey.ROOM_STATUS_BUSY);
        room.setCustomerName(tfCustomerName.getText());
        room.setCustomerPhone(tfCustomerPhone.getText());
        room.setCustomerEmail(tfCustomerEmail.getText());
        room.setFromDate(modelFrom.getDay() + "/" + modelFrom.getMonth() + "/" + modelFrom.getYear());
        room.setToDate(modelTo.getDay() + "/" + modelTo.getMonth() + "/" + modelTo.getYear());
        BookingRoom bookingRoom = new BookingRoom(room);
        bookingRoom.setErrorMessage(this);
        bookingRoom.tryToBookRoom();

        rooms = DBUtils.getRoomsBusyOrReady(false, ConstantsKey.ROOM_STATUS_READY);
        refreshData();
    }

    private void refreshData() {
        rightPanel.removeAll();
        drawRightLayout();
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private String payMoney(Integer moneyPerDay) {
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        try {
            Date date1 = myFormat.parse(modelFrom.getDay() + " " + modelFrom.getMonth() + " " + modelFrom.getYear());
            Date date2 = myFormat.parse(modelTo.getDay() + " " + modelTo.getMonth() + " " + modelTo.getYear());
            long diff = date2.getTime() - date1.getTime();
            long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            return (days * moneyPerDay) + "";
//            System.out.println("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
        } catch (ParseException exx) {
            exx.printStackTrace();
        }
        return "";
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String actionKey = e.getActionCommand();
        if (actionKey.equals("Danh Sách Phòng")) {
            AllRoomController bookingRoomController = new AllRoomController();
            bookingRoomController.MapLayout("Ahihi");
        }

        if (actionKey.equals("Phòng Đã Đặt")) {
            BookedRoomController bookingRoomController = new BookedRoomController();
            bookingRoomController.MapLayout("Phòng Đã Đặt");
        }

        if (actionKey.equals("Làm Mới")) {
            rooms = DBUtils.getRoomsBusyOrReady(false, ConstantsKey.ROOM_STATUS_READY);
            refreshData();
        }
    }
}


