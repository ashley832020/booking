package Utils;

import patterns.factory.RoomCarType;
import model.*;

import java.sql.*;
import java.util.ArrayList;

public class DBUtils {
    private static Connection connection = null;
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/booking";
    static final String DB_USER = "root";
    static final String DB_PASS = "123456";

    public static Connection getConnection() {
        if (connection == null) {
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
        }
        return connection;
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
                String available = rs.getString("available");
                return available.equals("Ready");
            }
        } catch (Exception e) {
            System.out.format("Exception \n");
        }

        return false;
    }

    public static Boolean checkCarAvailableOrNot(String carNumber) {
        try {
            Connection connection = DBUtils.getConnection();
            Statement st = connection.createStatement();
            String query = "SELECT available FROM car WHERE carNumber = " + "'" + carNumber + "'";
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                String available = rs.getString("available");
                return available.equals("Ready");
            }
        } catch (Exception e) {
            System.out.format("Exception \n");
        }

        return false;
    }

    public static void insertRoomIntoDb(Room room) {

        String roomNumber = room.getRoomNumber();
        String price = room.getPrice();
        String capacity = room.getCapacity();
        String type = room.getRoomType().name();
        String fromDate = room.getFromDate();
        String toDate = room.getToDate();
        String subQuery = "VALUES" + "(" + "'" + roomNumber + "'" + "," + "'" + price +
                "'" + "," + "'" + capacity + "'" + "," + "'" + type + "'" + "," + "'" + fromDate + "'" + "," + "'" + toDate + "'" + ")";
        try {

            Statement st = connection.createStatement();
            String query = "INSERT INTO room " + "(roomNumber,price,capacity,type,fromDate,toDate)" +
                    subQuery;
            Integer rs = st.executeUpdate(query);
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertCarIntoDb(Car car) {

        String carNumber = car.getCarNumber();
        String price = car.getPrice();
        String capacity = car.getCapacity();
        String type = car.getCarType().name();
        String fromDate = car.getFromDate();
        String toDate = car.getToDate();
        String subQuery = "VALUES" + "(" + "'" + carNumber + "'" + "," + "'" + price +
                "'" + "," + "'" + capacity + "'" + "," + "'" + type + "'" + "," + "'" + fromDate + "'" + "," + "'" + toDate + "'" + ")";
        try {

            Statement st = connection.createStatement();
            String query = "INSERT INTO room " + "(roomNumber,price,capacity,type,fromDate,toDate)" +
                    subQuery;
            Integer rs = st.executeUpdate(query);
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkoutRoom(Room room) {
        String roomNumber = room.getRoomNumber();
        try {

            String sql = "UPDATE room SET customerName=?, customerEmail=?, customerPhone=?, available=?, fromDate=?, toDate=? WHERE roomNumber=?";
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setString(1, "");
            statement.setString(2, "");
            statement.setString(3, "");
            statement.setString(4, "Ready");
            statement.setString(5, "");
            statement.setString(6, "");
            statement.setString(7, roomNumber);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkoutCar(Car car) {
        String carNumber = car.getCarNumber();
        try {

            String sql = "UPDATE car SET customerName=?, customerEmail=?, customerPhone=?, available=?, fromDate=?, toDate=? WHERE carNumber=?";
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setString(1, "");
            statement.setString(2, "");
            statement.setString(3, "");
            statement.setString(4, "Ready");
            statement.setString(5, "");
            statement.setString(6, "");
            statement.setString(7, carNumber);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateRoomInformation(Room room) {
        String customerName = room.getCustomerName();
        String customerEmail = room.getCustomerEmail();
        String customerPhone = room.getCustomerPhone();
        String roomNumber = room.getRoomNumber();
        String roomStatus = room.getAvailable();
        String fromDate = room.getFromDate();
        String toDate = room.getToDate();

        try {

            String sql = "UPDATE room SET customerName=?, customerEmail=?, customerPhone=?, available=?, fromDate=?, toDate=? WHERE roomNumber=?";
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setString(1, customerName);
            statement.setString(2, customerEmail);
            statement.setString(3, customerPhone);
            statement.setString(4, roomStatus);
            statement.setString(5, fromDate);
            statement.setString(6, toDate);
            statement.setString(7, roomNumber);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateCarInformation(Car car) {
        String customerName = car.getCustomerName();
        String customerEmail = car.getCustomerEmail();
        String customerPhone = car.getCustomerPhone();
        String carNumber = car.getCarNumber();
        String carStatus = car.getAvailable();
        String fromDate = car.getFromDate();
        String toDate = car.getToDate();

        try {

            String sql = "UPDATE car SET customerName=?, customerEmail=?, customerPhone=?, available=?, fromDate=?, toDate=? WHERE carNumber=?";
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setString(1, customerName);
            statement.setString(2, customerEmail);
            statement.setString(3, customerPhone);
            statement.setString(4, carStatus);
            statement.setString(5, fromDate);
            statement.setString(6, toDate);
            statement.setString(7, carNumber);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("successfully!");
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

    public static void logout() {
        try {
            connection = DBUtils.getConnection();
            PreparedStatement st = (PreparedStatement) connection
                    .prepareStatement("UPDATE Account " +
                            "SET isLogin = 0 WHERE id = 1");
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

    public static ArrayList<Room> getRoomsBusyOrReady(Boolean isShowAll, String readyOrBusy) {

        ArrayList rooms = new ArrayList();
        try {
            Connection connection = DBUtils.getConnection();
            Statement st = connection.createStatement();
            String query = "SELECT * FROM room";
            if (isShowAll) {
                query = "SELECT * FROM room";
            } else {
                query = "SELECT * FROM room where available =" + "'" + readyOrBusy + "'";
            }

            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
//                id, roomNumber, customerName, customerEmail, customerPhone
                int id = rs.getInt("id");
                String roomNumber = rs.getString("roomNumber");
                String customerName = rs.getString("customerName");
                String customerEmail = rs.getString("customerEmail");
                String customerPhone = rs.getString("customerPhone");
                String capacity = rs.getString("capacity");
                String available = rs.getString("available");
                String price = rs.getString("price");
                String type = rs.getString("type");
                String fromDate = rs.getString("fromDate");
                String toDate = rs.getString("toDate");
                RoomCarType roomType = RoomCarType.CHEAP;
                switch (type) {
                    case  "CHEAP" : {
                        roomType = RoomCarType.CHEAP;
                        break;
                    }
                    case  "NORMAL" : {
                        roomType = RoomCarType.NORMAL;
                        break;
                    }
                    case  "EXPENSIVE" : {
                        roomType = RoomCarType.EXPENSIVE;
                        break;
                    }
                    default: break;
                }
                Room room = new Room();
                room.setRoomType(roomType);
                room.setCustomerName(customerName);
                room.setCustomerEmail(customerEmail);
                room.setCustomerPhone(customerPhone);
                room.setRoomNumber(roomNumber);
                room.setPrice(price);
                room.setCapacity(capacity);
                room.setAvailable(available);
                room.setFromDate(fromDate);
                room.setToDate(toDate);

                rooms.add(room);
            }

        } catch (Exception e) {
            System.out.format("Exception \n");
        }
        return rooms;
    }

    public static ArrayList<Car> getCarsBusyOrReady(Boolean isShowAll, String readyOrBusy) {

        ArrayList cars = new ArrayList();
        try {
            Connection connection = DBUtils.getConnection();
            Statement st = connection.createStatement();
            String query;
            if (isShowAll) {
                query = "SELECT * FROM car";
            } else {
                query = "SELECT * FROM car where available =" + "'" + readyOrBusy + "'";
            }

            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
//                id, roomNumber, customerName, customerEmail, customerPhone
                int id = rs.getInt("id");
                String carNumber = rs.getString("carNumber");
                String customerName = rs.getString("customerName");
                String customerEmail = rs.getString("customerEmail");
                String customerPhone = rs.getString("customerPhone");
                String capacity = rs.getString("capacity");
                String available = rs.getString("available");
                String price = rs.getString("price");
                String type = rs.getString("type");
                String fromDate = rs.getString("fromDate");
                String toDate = rs.getString("toDate");
                RoomCarType carType = RoomCarType.CHEAP;
                switch (type) {
                    case  "CHEAP" : {
                        carType = RoomCarType.CHEAP;
                        break;
                    }
                    case  "NORMAL" : {
                        carType = RoomCarType.NORMAL;
                        break;
                    }
                    case  "EXPENSIVE" : {
                        carType = RoomCarType.EXPENSIVE;
                        break;
                    }
                    default: break;
                }
                Car car = new Car();
                car.setCarType(carType);
                car.setCustomerName(customerName);
                car.setCustomerEmail(customerEmail);
                car.setCustomerPhone(customerPhone);
                car.setCarNumber(carNumber);
                car.setPrice(price);
                car.setCapacity(capacity);
                car.setAvailable(available);
                car.setFromDate(fromDate);
                car.setToDate(toDate);

                cars.add(car);
            }

        } catch (Exception e) {
            System.out.format("Exception " + e.getMessage());
        }
        return cars;
    }

    public static ArrayList<Room> getRoomsWithCapacity(Boolean isShowAll, String key) {

        ArrayList rooms = new ArrayList();
        try {
            Connection connection = DBUtils.getConnection();
            Statement st = connection.createStatement();
            String query = "SELECT * FROM room";
            if (isShowAll) {
                query = "SELECT * FROM room";
            } else {
                if (key.equals("All")) {
                    query = "SELECT * FROM room where available =" + "'" + "Ready" + "'";
                } else {
                    query = "SELECT * FROM room where available =" + "'" + "Ready" + "'" + " AND capacity =" + "'" + key + "'";
                }
            }

            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
//                id, roomNumber, customerName, customerEmail, customerPhone
                int id = rs.getInt("id");
                String roomNumber = rs.getString("roomNumber");
                String customerName = rs.getString("customerName");
                String customerEmail = rs.getString("customerEmail");
                String customerPhone = rs.getString("customerPhone");
                String capacity = rs.getString("capacity");
                String type = rs.getString("type");
                String available = rs.getString("available");
                String price = rs.getString("price");
                RoomCarType roomType = RoomCarType.CHEAP;
                switch (type) {
                    case  "CHEAP" : {
                        roomType = RoomCarType.CHEAP;
                        break;
                    }
                    case  "NORMAL" : {
                        roomType = RoomCarType.NORMAL;
                        break;
                    }
                    case  "EXPENSIVE" : {
                        roomType = RoomCarType.EXPENSIVE;
                        break;
                    }
                    default: break;
                }
                Room room = new Room();
                room.setRoomType(roomType);
                room.setCustomerName(customerName);
                room.setCustomerEmail(customerEmail);
                room.setCustomerPhone(customerPhone);
                room.setRoomNumber(roomNumber);
                room.setPrice(price);
                room.setCapacity(capacity);
                room.setAvailable(available);

                rooms.add(room);
            }

        } catch (Exception e) {
            System.out.format("Exception \n");
        }
        return rooms;
    }
}
