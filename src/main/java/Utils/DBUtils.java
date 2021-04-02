package Utils;

import BookingRoom.RoomType;
import model.Room;

import java.sql.*;
import java.util.ArrayList;

public class DBUtils {
    private static Connection connection = null;
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/swing_demo";
    static final String DB_USER = "root";
    static final String DB_PASS = "root";

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        } else {
            try {
                Class.forName(JDBC_DRIVER);
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            } catch (ClassNotFoundException e) {
                // TODO: handle exception
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            return connection;
        }
    }

    public static Boolean isExistRoomOrNot(String roomName) {
        try {
            Connection connection = DBUtils.getConnection();
            Statement st = connection.createStatement();
            String query = "SELECT * FROM room WHERE roomNumber = " + "'" + roomName + "'";
            ResultSet rs = st.executeQuery(query);
            return rs.next();
        } catch (Exception e) {
            System.out.format("Exception \n");
        }

        return false;
    }

    public static void insertIntoDb(Room room) {
        String roomName = room.getRoomName();
        String roomNumber = room.getRoomName();
        String roomType = room.getEmail();
        String customerName = room.getCustomerName();
        String subQuery = "VALUES" + "(" + "'" + roomName + "'" + "," + "'" + roomNumber +
                "'" + "," + "'" + roomType + "'" + "," + "'" + customerName + "'" + ")";
        try {

            Statement st = connection.createStatement();
            String query = "INSERT INTO room " + "(roomNumber,customerName,customerEmail,customerPhone)" +
                    subQuery;
            Integer rs = st.executeUpdate(query);
            st.close();
            System.out.format("bookRoom Okkkkk \n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Boolean login(String userName, String password) {
        try {
            connection = DBUtils.getConnection();
            PreparedStatement st = (PreparedStatement) connection
                    .prepareStatement("Select username, password from account where username=? and password=?");
            st.setString(1, userName);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static ArrayList<Room> getRooms() {

        ArrayList rooms = new ArrayList();
        try {
            Connection connection = DBUtils.getConnection();
            Statement st = connection.createStatement();
            String query = "SELECT * FROM room";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
//                id, roomNumber, customerName, customerEmail, customerPhone
                int id = rs.getInt("id");
                String roomNumber = rs.getString("roomNumber");
                String customerName = rs.getString("customerName");
                String customerEmail = rs.getString("customerEmail");
                String customerPhone = rs.getString("customerPhone");
                Room room = new Room();
                room.setRoomType(RoomType.EXPENSIVE);
                room.setCustomerName(customerName);
                room.setEmail(customerEmail);
                room.setPhone(customerPhone);
                rooms.add(room);
            }

        } catch (Exception e) {
            System.out.format("Exception \n");
        }
        return rooms;
    }
}
