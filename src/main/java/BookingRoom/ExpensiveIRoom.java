package BookingRoom;

import Utils.DBUtils;
import model.Room;

public class ExpensiveIRoom implements IRoom {
    @Override
    public void book(Room room) {
        System.out.format("book ExpensiveIRoom \n");
        DBUtils.insertIntoDb(room);
    }
}
