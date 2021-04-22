/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author macbookpro
 */

import Utils.ConstantsKey;
import Utils.DBTitle;
import Utils.DBUtils;
import layout.ButtonColumn;
import layout.ColorRenderer;
import model.*;
import patterns.ValidationMessage;
import patterns.adapter.EnglishAdaptee;
import patterns.adapter.TranslatorAdapter;
import patterns.adapter.VietnameseTarget;
import patterns.factory.*;
import payment.Payment;

import java.time.temporal.ChronoUnit;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Objects;

public class BookController extends JFrame implements ValidationMessage, ActionListener {
    private ArrayList<Room> availableRooms = DBUtils.getRoomsBusyOrReady(false, ConstantsKey.ROOM_STATUS_READY);
    private ArrayList<Room> bookedRooms = DBUtils.getRoomsBusyOrReady(false, ConstantsKey.ROOM_STATUS_BUSY);

    private ArrayList<Car> availableCars = DBUtils.getCarsBusyOrReady(false, ConstantsKey.CAR_STATUS_READY);
    private ArrayList<Car> bookedCars = DBUtils.getCarsBusyOrReady(false, ConstantsKey.CAR_STATUS_BUSY);

    private ArrayList<String> titleData = DBTitle.titleMultipleLanguage;

    public BookController() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        bigBackGround = new JPanel();
        cusomerInfomationPanel = new JPanel();
        tfPhone = new java.awt.TextField();
        tfName = new java.awt.TextField();
        tfEmail = new java.awt.TextField();
        labelName = new JLabel();
        labelPhone = new JLabel();
        labelEmail = new JLabel();
        labelBed = new JLabel();
        labelFromDay = new JLabel();
        btnBook = new JButton();
        labelCustomer = new JLabel();
        jComboBox1 = new JComboBox<>();
        datePickerFromDay = new com.github.lgooddatepicker.components.DatePicker();
        labelToDay = new JLabel();
        datePickerToDay = new com.github.lgooddatepicker.components.DatePicker();
        labelTitle = new JLabel();
        tabTable = new JTabbedPane();
        tabRoom = new JPanel();
        labelAvailableRooms = new JLabel();
        jScrollPane2 = new JScrollPane();
        tableAvailableRooms = new JTable();
        labelBookedRooms = new JLabel();
        jScrollPane1 = new JScrollPane();
        tableBookedRooms = new JTable();
        tabCar = new JPanel();
        labelAvailableCars = new JLabel();
        jScrollPane5 = new JScrollPane();
        tableAvailableCars = new JTable();
        labelBookedCars = new JLabel();
        jScrollPane6 = new JScrollPane();
        tableBookedCars = new JTable();
        cbxLanguage = new JComboBox<>();


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(new Color(255, 255, 255));

        bigBackGround.setBackground(new Color(140, 153, 220));

        cusomerInfomationPanel.setBackground(new Color(50, 59, 100));

        tfPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPhoneActionPerformed(evt);
            }
        });

        tfName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNameActionPerformed(evt);
            }
        });

        tfEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfEmailActionPerformed(evt);
            }
        });

        cbxLanguage.addActionListener(e -> {
            VietnameseTarget client = new TranslatorAdapter(new EnglishAdaptee());
            client.send(Objects.requireNonNull(cbxLanguage.getSelectedItem()).toString());
            setTitleMultipleLang();
        });

        labelName.setBackground(new Color(255, 255, 255));
        labelName.setFont(new Font("UTM Ericsson Capital", 0, 14)); // NOI18N
        labelName.setForeground(new Color(255, 255, 255));
        labelName.setText("name");

        labelPhone.setBackground(new Color(255, 255, 255));
        labelPhone.setFont(new Font("UTM Ericsson Capital", 0, 14)); // NOI18N
        labelPhone.setForeground(new Color(255, 255, 255));
        labelPhone.setText("phone");

        labelEmail.setBackground(new Color(255, 255, 255));
        labelEmail.setFont(new Font("UTM Ericsson Capital", 0, 14)); // NOI18N
        labelEmail.setForeground(new Color(255, 255, 255));
        labelEmail.setText("email");

        labelBed.setBackground(new Color(255, 255, 255));
        labelBed.setFont(new Font("UTM Ericsson Capital", 0, 14)); // NOI18N
        labelBed.setForeground(new Color(255, 255, 255));
        labelBed.setText("BED");

        labelFromDay.setBackground(new Color(255, 255, 255));
        labelFromDay.setFont(new Font("UTM Ericsson Capital", 0, 14)); // NOI18N
        labelFromDay.setForeground(new Color(255, 255, 255));
        labelFromDay.setText("from");

        btnBook.setBackground(new Color(255, 255, 255));
        btnBook.setFont(new Font("UTM Ericsson Capital", 0, 14)); // NOI18N
        btnBook.setForeground(new Color(102, 102, 255));
        btnBook.setText("book");
        btnBook.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
        btnBook.setBorderPainted(false);
        btnBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookActionPerformed(evt);
            }
        });

        labelCustomer.setBackground(new Color(255, 255, 255));
        labelCustomer.setFont(new Font("UTM Ericsson Capital", 0, 24)); // NOI18N
        labelCustomer.setForeground(new Color(153, 153, 255));
        labelCustomer.setText("customer");

        jComboBox1.setFont(new Font("UTM Ericsson Capital", 0, 18)); // NOI18N
        jComboBox1.setModel(new DefaultComboBoxModel<>(new String[]{"All", "1", "2", "3"}));

        labelToDay.setBackground(new Color(255, 255, 255));
        labelToDay.setFont(new Font("UTM Ericsson Capital", 0, 18)); // NOI18N
        labelToDay.setForeground(new Color(255, 255, 255));
        labelToDay.setText("to");

        GroupLayout cusomerInfomationPanelLayout = new GroupLayout(cusomerInfomationPanel);
        cusomerInfomationPanel.setLayout(cusomerInfomationPanelLayout);
        cusomerInfomationPanelLayout.setHorizontalGroup(
                cusomerInfomationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(cusomerInfomationPanelLayout.createSequentialGroup()
                                .addGap(117, 117, 117)
                                .addComponent(btnBook, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, cusomerInfomationPanelLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelCustomer)
                                .addGap(108, 108, 108))
                        .addGroup(cusomerInfomationPanelLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(cusomerInfomationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(labelPhone, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(cusomerInfomationPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                .addComponent(labelToDay, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(labelFromDay, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(cusomerInfomationPanelLayout.createSequentialGroup()
                                                .addGroup(cusomerInfomationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(labelName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(labelEmail, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(labelBed, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
                                                .addGap(23, 23, 23)
                                                .addGroup(cusomerInfomationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(tfPhone, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(tfName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(tfEmail, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(datePickerFromDay, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(datePickerToDay, GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                                        .addComponent(jComboBox1, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(25, 25, 25))
        );
        cusomerInfomationPanelLayout.setVerticalGroup(
                cusomerInfomationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, cusomerInfomationPanelLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(labelCustomer)
                                .addGap(39, 39, 39)
                                .addGroup(cusomerInfomationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(labelName, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tfName, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(cusomerInfomationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(labelPhone, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tfPhone, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(cusomerInfomationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(tfEmail, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelEmail, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(cusomerInfomationPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(datePickerFromDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelFromDay, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(cusomerInfomationPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelToDay, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(datePickerToDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                .addGroup(cusomerInfomationPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelBed, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBook, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
        );

        labelTitle.setBackground(new Color(255, 255, 255));
        labelTitle.setFont(new Font("UTM Ericsson Capital", 0, 48)); // NOI18N
        labelTitle.setForeground(new Color(255, 255, 255));
        labelTitle.setText("demo hotel");

        tabTable.setBackground(new Color(255, 255, 255));
        tabTable.setForeground(new Color(102, 102, 255));
        tabTable.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabTable.setFont(new Font("UTM Ericsson Capital", 0, 18)); // NOI18N

        tabRoom.setBackground(new Color(255, 255, 255));

        labelAvailableRooms.setBackground(new Color(255, 255, 255));
        labelAvailableRooms.setFont(new Font("UTM Ericsson Capital", 0, 24)); // NOI18N
        labelAvailableRooms.setForeground(new Color(153, 153, 255));
        labelAvailableRooms.setText("available rooms");

        jScrollPane2.setViewportView(tableAvailableRooms);

        labelBookedRooms.setBackground(new Color(255, 255, 255));
        labelBookedRooms.setFont(new Font("UTM Ericsson Capital", 0, 24)); // NOI18N
        labelBookedRooms.setForeground(new Color(153, 153, 255));
        labelBookedRooms.setText("Booked rooms");

        jScrollPane1.setViewportView(tableBookedRooms);

        GroupLayout tabRoomLayout = new GroupLayout(tabRoom);
        tabRoom.setLayout(tabRoomLayout);
        tabRoomLayout.setHorizontalGroup(
                tabRoomLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(tabRoomLayout.createSequentialGroup()
                                .addGroup(tabRoomLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 486, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(tabRoomLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(tabRoomLayout.createSequentialGroup()
                                                        .addGap(63, 63, 63)
                                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 486, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(tabRoomLayout.createSequentialGroup()
                                                        .addGap(30, 30, 30)
                                                        .addGroup(tabRoomLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addComponent(labelBookedRooms, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(labelAvailableRooms, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap(37, Short.MAX_VALUE))
        );
        tabRoomLayout.setVerticalGroup(
                tabRoomLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(tabRoomLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(labelAvailableRooms)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelBookedRooms)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32))
        );

        tabTable.addTab("Room", tabRoom);

        tabCar.setBackground(new Color(255, 255, 255));

        labelAvailableCars.setBackground(new Color(255, 255, 255));
        labelAvailableCars.setFont(new Font("UTM Ericsson Capital", 0, 24)); // NOI18N
        labelAvailableCars.setForeground(new Color(153, 153, 255));
        labelAvailableCars.setText("available cars");

        jScrollPane5.setViewportView(tableAvailableCars);

        labelBookedCars.setBackground(new Color(255, 255, 255));
        labelBookedCars.setFont(new Font("UTM Ericsson Capital", 0, 24)); // NOI18N
        labelBookedCars.setForeground(new Color(153, 153, 255));
        labelBookedCars.setText("Booked cars");

        jScrollPane6.setViewportView(tableBookedCars);

        GroupLayout tabCarLayout = new GroupLayout(tabCar);
        tabCar.setLayout(tabCarLayout);
        tabCarLayout.setHorizontalGroup(
                tabCarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(tabCarLayout.createSequentialGroup()
                                .addGroup(tabCarLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane5, GroupLayout.PREFERRED_SIZE, 486, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(tabCarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(tabCarLayout.createSequentialGroup()
                                                        .addGap(63, 63, 63)
                                                        .addComponent(jScrollPane6, GroupLayout.PREFERRED_SIZE, 486, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(tabCarLayout.createSequentialGroup()
                                                        .addGap(30, 30, 30)
                                                        .addGroup(tabCarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addComponent(labelBookedCars, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(labelAvailableCars, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap(37, Short.MAX_VALUE))
        );
        tabCarLayout.setVerticalGroup(
                tabCarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(tabCarLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(labelAvailableCars)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane5, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelBookedCars)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane6, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32))
        );

        tabTable.addTab("Car", tabCar);

        cbxLanguage.setFont(new Font("UTM Ericsson Capital", 0, 12)); // NOI18N
        cbxLanguage.setForeground(new Color(102, 102, 255));
        cbxLanguage.setModel(new DefaultComboBoxModel<>(new String[]{"English", "Vietnamese"}));

        GroupLayout bigBackGroundLayout = new GroupLayout(bigBackGround);
        bigBackGround.setLayout(bigBackGroundLayout);
        bigBackGroundLayout.setHorizontalGroup(
                bigBackGroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(bigBackGroundLayout.createSequentialGroup()
                                .addContainerGap(53, Short.MAX_VALUE)
                                .addComponent(cusomerInfomationPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(tabTable, GroupLayout.PREFERRED_SIZE, 607, GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29))
                        .addGroup(GroupLayout.Alignment.TRAILING, bigBackGroundLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cbxLanguage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(207, 207, 207)
                                .addComponent(labelTitle, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bigBackGroundLayout.setVerticalGroup(
                bigBackGroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(bigBackGroundLayout.createSequentialGroup()
                                .addGroup(bigBackGroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(bigBackGroundLayout.createSequentialGroup()
                                                .addGap(19, 19, 19)
                                                .addComponent(labelTitle))
                                        .addGroup(bigBackGroundLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(cbxLanguage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(bigBackGroundLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(tabTable, GroupLayout.PREFERRED_SIZE, 510, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cusomerInfomationPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(35, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(bigBackGround, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(bigBackGround, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        drawRoomsTables();
        drawCarsTables();
        pack();


    }// </editor-fold>

    private void roomDetailDialog(Room room) {
        JFrame frame = new JFrame();
        JDialog d = new JDialog(frame, "Payment", true);
        d.setLayout(new FlowLayout());
        JButton card = new JButton("CARD");
        JButton cash = new JButton("CASH");

        cash.addActionListener(e -> {
            d.setVisible(false);
            room.setPaymentMethod(ConstantsKey.PAYMENT_CASH);
            Payment payment = new Payment();
            payment.payment(ConstantsKey.PAYMENT_CASH);
            doBookRoom(room);
        });

        card.addActionListener(e -> {
            d.setVisible(false);
            room.setPaymentMethod(ConstantsKey.PAYMENT_CARD);
            Payment payment = new Payment();
            payment.payment(ConstantsKey.PAYMENT_CARD);
            doBookRoom(room);
        });
        d.add(new JLabel("Need To Pay"), BorderLayout.SOUTH);
        String price = payMoney(Integer.parseInt(room.getPrice()));

        if (price == null) {
            errorMessage("Input date time!");
            return;
        }

        if (price.equals("0")) {
            errorMessage("Length of days living must be greater than 1 day!");
        }

        if (price.contains("-")) {
            errorMessage("TO DATE is smaller than FROM DATE!");
            return;
        }

        d.add(new JLabel(price + " $"), BorderLayout.SOUTH);
//        d.add(new JLabel(room.getCustomerPhone()), BorderLayout.SOUTH);
        d.add(card);
        d.add(cash);
        d.setSize(300, 100);
        d.setBounds(300, 200, 200, 200);
        d.setVisible(true);
    }

    private void carDetailDialog(Car car) {

//        if (modelFrom.getValue() == null) {
//            message("Input from Date");
//            return;
//        }
//
//        if (modelTo.getValue() == null) {
//            message("Input to Date");
//            return;
//        }

        JFrame frame = new JFrame();
        JDialog d = new JDialog(frame, "Payment", true);
        d.setLayout(new FlowLayout());
        JButton card = new JButton("CARD");
        JButton cash = new JButton("CASH");

        cash.addActionListener(e -> {
            d.setVisible(false);
            car.setPaymentMethod(ConstantsKey.PAYMENT_CASH);
            Payment payment = new Payment();
            payment.payment(ConstantsKey.PAYMENT_CASH);
            doBookCar(car);
        });

        card.addActionListener(e -> {
            d.setVisible(false);
            car.setPaymentMethod(ConstantsKey.PAYMENT_CARD);
            Payment payment = new Payment();
            payment.payment(ConstantsKey.PAYMENT_CARD);
            doBookCar(car);
        });
        d.add(new JLabel("Need To Pay"), BorderLayout.SOUTH);
        String price = payMoney(Integer.parseInt(car.getPrice()));

        if (price == null) {
            errorMessage("Input date time!");
            return;
        }

        if (price.equals("0")) {
            errorMessage("Length of days living must be greater than 1 day!");
        }

        if (price.contains("-")) {
            errorMessage("TO DATE is smaller than FROM DATE!");
            return;
        }
        d.add(new JLabel(price + " $"), BorderLayout.SOUTH);
//        d.add(new JLabel(room.getCustomerPhone()), BorderLayout.SOUTH);
        d.add(card);
        d.add(cash);
        d.setSize(300, 100);
        d.setBounds(300, 200, 200, 200);
        d.setVisible(true);
    }

    private void roomCheckoutDialog(Room room) {
        JFrame f = new JFrame();
        JDialog d = new JDialog(f, "Checkout", true);
        d.setLayout(new FlowLayout());
        JButton checkout = new JButton("Confirm");

        checkout.addActionListener(e -> {
            d.setVisible(false);
            DBUtils.checkoutRoom(room);
            availableRooms = DBUtils.getRoomsBusyOrReady(false, ConstantsKey.ROOM_STATUS_BUSY);
            bookRoomRefreshData();
        });
        d.add(new JLabel("Do you wanna checkout?"), BorderLayout.SOUTH);
//        d.add(new JLabel(room.getCustomerPhone()), BorderLayout.SOUTH);
        d.add(checkout);
        d.setSize(300, 100);
        d.setBounds(300, 200, 200, 200);
        d.setVisible(true);
    }

    private void carCheckoutDialog(Car car) {
        JFrame f = new JFrame();
        JDialog d = new JDialog(f, "Checkout", true);
        d.setLayout(new FlowLayout());
        JButton checkout = new JButton("Confirm");

        checkout.addActionListener(e -> {
            d.setVisible(false);
            DBUtils.checkoutCar(car);
            availableCars = DBUtils.getCarsBusyOrReady(false, ConstantsKey.CAR_STATUS_BUSY);
            bookCarRefreshData();
        });
        d.add(new JLabel("Do you wanna checkout?"), BorderLayout.SOUTH);
//        d.add(new JLabel(room.getCustomerPhone()), BorderLayout.SOUTH);
        d.add(checkout);
        d.setSize(300, 100);
        d.setBounds(300, 200, 200, 200);
        d.setVisible(true);
    }

    private void drawRoomsTables() {
        // ---------------------------------------------------------------------------------------------------
        String columns_available[] = {"Room", "Bed", "Type", "Price", "Book"};
        DefaultTableModel availableRoom = new DefaultTableModel(columns_available, 0);
        tableAvailableRooms.setModel(availableRoom);

        for (Room room : availableRooms) {
            if (room.getAvailable().equals("Ready")) {
                Object[] data = {"R" + room.getRoomNumber(), room.getCapacity() + " bed", room.getRoomType(), room.getPrice() + "$", "Book"};
                availableRoom.addRow(data);
            }
        }

        tableAvailableRooms.setDefaultRenderer(Color.class, new ColorRenderer(true));
        Action deleteAvailable = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                int modelRow = Integer.valueOf(e.getActionCommand());
                Room room = availableRooms.get(modelRow);
                roomDetailDialog(room);
            }
        };

        ButtonColumn buttonColumnAvailable = new ButtonColumn(tableAvailableRooms, deleteAvailable, 4);
        buttonColumnAvailable.setMnemonic(KeyEvent.VK_D);

        // ---------------------------------------------------------------------------------------------------

        String columns_booked[] = {"Room", "Bed", "Type", "Price", "Checkout"};
        DefaultTableModel bookedRoom = new DefaultTableModel(columns_booked, 0);
        tableBookedRooms.setModel(bookedRoom);
        for (Room room : bookedRooms) {
            if (room.getAvailable().equals("Busy")) {
                Object[] data = {"R" + room.getRoomNumber(), room.getCapacity() + " bed", room.getRoomType(), room.getPrice() + "$", "Checkout"};
                bookedRoom.addRow(data);
            }
        }

        tableBookedRooms.setDefaultRenderer(Color.class, new ColorRenderer(true));
        Action deleteCheckout = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                int modelRow = Integer.valueOf(e.getActionCommand());
                Room room = bookedRooms.get(modelRow);
                roomCheckoutDialog(room);
            }
        };

        ButtonColumn buttonColumnCheckout = new ButtonColumn(tableBookedRooms, deleteCheckout, 4);
        buttonColumnCheckout.setMnemonic(KeyEvent.VK_D);

    }

    private void drawCarsTables() {
        // ---------------------------------------------------------------------------------------------------
        String columns_available[] = {"Car", "Seat", "Type", "Price", "Book"};
        DefaultTableModel availableCar = new DefaultTableModel(columns_available, 0);
        tableAvailableCars.setModel(availableCar);

        for (Car car : availableCars) {
            if (car.getAvailable().equals("Ready")) {
                Object[] data = {"C" + car.getCarNumber(), car.getCapacity() + " seats", car.getCarType(), car.getPrice() + "$", "Book"};
                availableCar.addRow(data);
            }
        }

        tableAvailableCars.setDefaultRenderer(Color.class, new ColorRenderer(true));
        Action deleteAvailable = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                int modelRow = Integer.valueOf(e.getActionCommand());
                Car car = availableCars.get(modelRow);
                carDetailDialog(car);
            }
        };

        ButtonColumn buttonColumnAvailable = new ButtonColumn(tableAvailableCars, deleteAvailable, 4);
        buttonColumnAvailable.setMnemonic(KeyEvent.VK_D);

        // ---------------------------------------------------------------------------------------------------

        String columns_booked[] = {"Car", "Seat", "Type", "Price", "Checkout"};
        DefaultTableModel bookedCar = new DefaultTableModel(columns_booked, 0);
        tableBookedCars.setModel(bookedCar);
        for (Car car : bookedCars) {
            if (car.getAvailable().equals("Busy")) {
                Object[] data = {"R" + car.getCarNumber(), car.getCapacity() + " seats", car.getCarType(), car.getPrice() + "$", "Checkout"};
                bookedCar.addRow(data);
            }
        }

        tableBookedCars.setDefaultRenderer(Color.class, new ColorRenderer(true));
        Action deleteCheckout = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                int modelRow = Integer.valueOf(e.getActionCommand());
                Car car = bookedCars.get(modelRow);
                carCheckoutDialog(car);
            }
        };

        ButtonColumn buttonColumnCheckout = new ButtonColumn(tableBookedCars, deleteCheckout, 4);
        buttonColumnCheckout.setMnemonic(KeyEvent.VK_D);
    }

    private void doBookRoom(Room room) {
        room.setBook(true);
        room.setAvailable(ConstantsKey.ROOM_STATUS_BUSY);
        room.setCustomerName(tfName.getText());
        room.setCustomerPhone(tfPhone.getText());
        room.setCustomerEmail(tfEmail.getText());
        String fromDate = datePickerFromDay.getDate().toString();
        String[] from = fromDate.split("-");
        String toDate = datePickerToDay.getDate().toString();
        String[] to = toDate.split("-");

        room.setFromDate(from[2] + "/" + from[1] + "/" + from[0]);
        room.setToDate(to[2] + "/" + to[1] + "/" + to[0]);
        BookRoom bookRoom = new BookRoom(room);
        bookRoom.setMessage(this);
        bookRoom.tryToBook();

        bookRoomRefreshData();
    }

    private void doBookCar(Car car) {
        car.setBook(true);
        car.setAvailable(ConstantsKey.ROOM_STATUS_BUSY);
        car.setCustomerName(tfName.getText());
        car.setCustomerPhone(tfPhone.getText());
        car.setCustomerEmail(tfEmail.getText());
        String fromDate = datePickerFromDay.getDate().toString();
        String[] from = fromDate.split("-");
        String toDate = datePickerToDay.getDate().toString();
        String[] to = toDate.split("-");

        car.setFromDate(from[2] + "/" + from[1] + "/" + from[0]);
        car.setToDate(to[2] + "/" + to[1] + "/" + to[0]);
        BookCar bookCar = new BookCar(car);
        bookCar.setMessage(this);
        bookCar.tryToBook();

        bookCarRefreshData();
    }

    private void bookRoomRefreshData() {
        availableRooms = DBUtils.getRoomsBusyOrReady(false, ConstantsKey.ROOM_STATUS_READY);
        bookedRooms = DBUtils.getRoomsBusyOrReady(false, ConstantsKey.ROOM_STATUS_BUSY);

        tableBookedRooms.removeAll();
        tableAvailableRooms.removeAll();
        drawRoomsTables();
        tableBookedRooms.revalidate();
        tableBookedRooms.repaint();
        tableAvailableRooms.revalidate();
        tableAvailableRooms.repaint();
    }

    private void bookCarRefreshData() {

        availableCars = DBUtils.getCarsBusyOrReady(false, ConstantsKey.CAR_STATUS_READY);
        bookedCars = DBUtils.getCarsBusyOrReady(false, ConstantsKey.CAR_STATUS_BUSY);

        tableBookedCars.removeAll();
        tableAvailableCars.removeAll();
        drawCarsTables();
        tableBookedCars.revalidate();
        tableBookedCars.repaint();
        tableAvailableCars.revalidate();
        tableAvailableCars.repaint();
    }

    private String payMoney(Integer moneyPerDay) {
        java.time.LocalDate fromDate = datePickerFromDay.getDate();
        java.time.LocalDate toDate = datePickerToDay.getDate();

        if (fromDate == null || toDate == null) {

            return null;
        }

        long diffInDays = ChronoUnit.DAYS.between(fromDate, toDate);
        return (diffInDays * moneyPerDay) + "";
    }

    public void setTitleMultipleLang() {
        titleData = DBTitle.titleMultipleLanguage;
        labelName.setText(titleData.get(0));
        labelPhone.setText(titleData.get(1));
        labelEmail.setText(titleData.get(2));
        labelFromDay.setText(titleData.get(3));
        labelToDay.setText(titleData.get(4));
        labelCustomer.setText(titleData.get(5));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VietnameseTarget client = new TranslatorAdapter(new EnglishAdaptee());
        client.send("vn");
        setTitleMultipleLang();
    }

    @Override
    public void errorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

    @Override
    public void successMessage(String successMessage) {
        JOptionPane.showMessageDialog(this, successMessage);
    }
    // End of variables declaration

    private void tfEmailActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void tfNameActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void tfPhoneActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btnBookActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BookController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookController().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private JPanel bigBackGround;
    private JButton btnBook;
    private JPanel cusomerInfomationPanel;
    private com.github.lgooddatepicker.components.DatePicker datePickerFromDay;
    private com.github.lgooddatepicker.components.DatePicker datePickerToDay;
    private JComboBox<String> jComboBox1;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane5;
    private JScrollPane jScrollPane6;
    private JLabel labelAvailableCars;
    private JLabel labelAvailableRooms;
    private JLabel labelBed;
    private JLabel labelBookedCars;
    private JLabel labelBookedRooms;
    private JLabel labelCustomer;
    private JLabel labelEmail;
    private JLabel labelFromDay;
    private JLabel labelName;
    private JLabel labelPhone;
    private JLabel labelTitle;
    private JLabel labelToDay;
    private JPanel tabCar;
    private JPanel tabRoom;
    private JTabbedPane tabTable;
    private JTable tableAvailableCars;
    private JTable tableAvailableRooms;
    private JTable tableBookedCars;
    private JTable tableBookedRooms;
    private java.awt.TextField tfEmail;
    private java.awt.TextField tfName;
    private java.awt.TextField tfPhone;
    private JComboBox<String> cbxLanguage;
}
