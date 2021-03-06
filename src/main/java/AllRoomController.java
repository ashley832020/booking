import Utils.DBUtils;
import patterns.ValidationMessage;
import model.Room;
import patterns.factory.RoomCarType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Objects;

public class AllRoomController extends JFrame implements ValidationMessage, ActionListener {

    private final JPanel mainPanel = new JPanel();
    private final JPanel rightPanel = new JPanel();
    private final JTextField tfRoomName = new JTextField(10);
    private final JTextField tfRoomPrice = new JTextField(10);
    private final JTextField tfCustomerEmail = new JTextField(10);
    private JButton addANewRoom;
    private JTextArea lbWay;
    private ArrayList<Room> rooms = DBUtils.getRoomsBusyOrReady(true, "");
    JComboBox<String> cbPrice = null;
    JComboBox<String> cbCapacity = null;

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

        makeJTextFieldGoFrom(panelTop, "Room Number", tfRoomName);
//        makeJTextFieldGoFrom(panelTop, "Price", tfRoomPrice);

        String[] capacity = {"1", "2", "3", "4"};
        makeCapacityComboBox(panelTop, "Capacity", capacity);

        String[] prices = {"50", "100", "150", "200",
                "250", "300"};
        makePriceComboBox(panelTop, "Price ($) ", prices);

        makeSearchButton(panelTop);

        panel.setBorder(new EmptyBorder(0, 5, 0, 0));
        return panel;
    }

    private JPanel drawRightLayout() {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new TitledBorder(""));
        mainPanel.setBackground(new Color(64, 224, 208));

        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(mainPanel, BorderLayout.WEST);

        String col[] = {"Pos", "Team", "P", "W", "A"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        for (Room room : rooms) {
            Object[] data2 = {"P" + room.getRoomNumber(), room.getPrice() + " $", room.getCapacity() + " people", room.getRoomType(), room.getAvailable() + ""};
            tableModel.addRow(data2);
        }

        JTable table = new JTable(tableModel);

        rightPanel.add(table);
        return rightPanel;
    }

    private JMenuBar drawMenu() {
        JMenu menuFile = new JMenu("File");
//        menuFile.add(createMenuItem("Danh S??ch Ph??ng", KeyEvent.VK_X, Event.CTRL_MASK));
//        menuFile.add(createMenuItem("Ph??ng Tr???ng", KeyEvent.VK_X, Event.CTRL_MASK));
//        menuFile.add(createMenuItem("Ph??ng", KeyEvent.VK_X, Event.CTRL_MASK));
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

    private void makeCapacityComboBox(JPanel panelTop, String title, String[] choices) {

        JPanel panelSmall = new JPanel(new GridLayout(1, 2, 15, 5));
        panelSmall.setPreferredSize(new Dimension(200, 30));
        panelSmall.setBorder(new EmptyBorder(0, 15, 0, 5));
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder(title));
        panel.add(panelSmall);
        cbCapacity = new JComboBox<String>(choices);
        cbCapacity.setMaximumSize(cbCapacity.getPreferredSize()); // added code
        cbCapacity.setAlignmentX(Component.CENTER_ALIGNMENT);// added code
        //cb.setVisible(true); // Not needed
        panel.add(cbCapacity);
        panelTop.add(panel);
    }

    private void makePriceComboBox(JPanel panelTop, String title, String[] choices) {

        JPanel panelSmall = new JPanel(new GridLayout(1, 2, 15, 5));
        panelSmall.setPreferredSize(new Dimension(200, 30));
        panelSmall.setBorder(new EmptyBorder(0, 15, 0, 5));
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder(title));
        panel.add(panelSmall);
        cbPrice = new JComboBox<String>(choices);
        cbPrice.setMaximumSize(cbPrice.getPreferredSize()); // added code
        cbPrice.setAlignmentX(Component.CENTER_ALIGNMENT);// added code
        //cb.setVisible(true); // Not needed
        panel.add(cbPrice);
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

    private void makeSearchButton(JPanel panel) {
        JPanel panelRunTemp = new JPanel(new GridLayout(1, 2, 15, 5));
        panelRunTemp.setBorder(new EmptyBorder(0, 15, 0, 5));
        panelRunTemp.add(addANewRoom = CustomButton("OK"));
        JPanel panelRun = new JPanel(new BorderLayout());
        panelRun.setBorder(new TitledBorder("Add New Room"));
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

        String actionKey = e.getActionCommand();
        if (e.getSource() == addANewRoom) {

            if (tfRoomName.getText().trim().equals("")) {
                errorMessage("Input Room Name");
                return;
            }

            Room room = new Room();
            room.setBook(false);

            int price = Integer.parseInt(cbPrice.getSelectedItem().toString());
            if (price <= 50) {
                room.setRoomType(RoomCarType.CHEAP);
            } else if (price <= 150) {
                room.setRoomType(RoomCarType.NORMAL);
            } else {
                room.setRoomType(RoomCarType.EXPENSIVE);
            }

            room.setRoomNumber(tfRoomName.getText());
            room.setPrice(Objects.requireNonNull(cbPrice.getSelectedItem()).toString());
            room.setCapacity(Objects.requireNonNull(cbCapacity.getSelectedItem()).toString());
            room.setAvailable("Ready");
            if (DBUtils.isExistRoomOrNot(room.getRoomNumber())) {
                errorMessage("Room Exist!");
            } else {
                errorMessage("Successfully");
                DBUtils.insertRoomIntoDb(room);
                rooms.add(room);
//        remove(rightPanel);
                rightPanel.removeAll();
                drawRightLayout();
                rightPanel.revalidate();
                rightPanel.repaint();
            }
        }

        if (actionKey.equals("Exit")) {
            System.exit(0);
        }

        if (actionKey.equals("Danh S??ch Ph??ng")) {
            System.exit(0);
        }


//
//        BookingRoom bookingRoom = new BookingRoom(rooms.get(0));
//        bookingRoom.setErrorMessage(this);
//        bookingRoom.tryToBookRoom();

    }


}


