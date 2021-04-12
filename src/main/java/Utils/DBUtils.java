package Utils;

import BookingRoom.RoomType;
import model.Room;
import model.User;

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

    public static Boolean checkRoomAvailableOrNot(String roomNumber) {
        try {
            Connection connection = DBUtils.getConnection();
            Statement st = connection.createStatement();
            String query = "SELECT available FROM room WHERE roomNumber = " + "'" + roomNumber + "'";
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                int available = rs.getInt("available");
                return available == 1;
            }
        } catch (Exception e) {
            System.out.format("Exception \n");
        }

        return false;
    }

    public static void insertIntoDb(Room room) {
        String roomName = room.getRoomName();
        String roomNumber = room.getRoomName();
        String roomType = room.getCustomerEmail();
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

    public static void updateRoomInformation(Room room) {
        String customerName = room.getRoomName();
        String customerEmail = room.getCustomerName();
        String customerPhone = room.getCustomerPhone();
        String roomNumber = room.getRoomNumber();

        try {

            String sql = "UPDATE room SET customerName=?, customerEmail=?, customerPhone=? WHERE roomNumber=?";
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setString(1, customerName);
            statement.setString(2, customerEmail);
            statement.setString(3, customerPhone);
            statement.setString(4, roomNumber);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }

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

    public static void updateUserLogin() {
        try {
            connection = DBUtils.getConnection();
            PreparedStatement st = (PreparedStatement) connection
                    .prepareStatement("UPDATE Account " +
                            "SET isLogin = 1 WHERE id = 1");
            st.executeUpdate();

        } catch (Exception e) {

        }
    }

    public static User getUerById(int id) {
        try {
            connection = DBUtils.getConnection();
            Statement st = connection.createStatement();
            String query = "SELECT * FROM Account where id = " + id;
            ResultSet rs = st.executeQuery(query);
            User user = new User();
            if (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                int isLogin = rs.getInt("isLogin");
                user.setUsername(username);
                user.setPassword(password);
                user.setIsLogin(isLogin);
            }
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    public static ArrayList<Room> getRooms() {

        ArrayList rooms = new ArrayList();
        try {
            Connection connection = DBUtils.getConnection();
            Statement st = connection.createStatement();
            String query = "SELECT * FROM room where available = 1";
//            String query = "SELECT * FROM room";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
//                id, roomNumber, customerName, customerEmail, customerPhone
                int id = rs.getInt("id");
                String roomNumber = rs.getString("roomNumber");
                String customerName = rs.getString("customerName");
                String customerEmail = rs.getString("customerEmail");
                String customerPhone = rs.getString("customerPhone");
                int capacity = rs.getInt("capacity");
                int available = rs.getInt("available");
                String price = rs.getString("price");
                Room room = new Room();
                room.setRoomType(RoomType.EXPENSIVE);
                room.setCustomerName(customerName);
                room.setCustomerEmail(customerEmail);
                room.setCustomerPhone(customerPhone);
                room.setRoomNumber(roomNumber);
                room.setPrice(price);
                room.setCapacity(capacity);
                if (available == 1) {
                    room.setAvailable("Busy");
                } else {
                    room.setAvailable("Ready");
                }

                rooms.add(room);
            }

        } catch (Exception e) {
            System.out.format("Exception \n");
        }
        return rooms;
    }
}
