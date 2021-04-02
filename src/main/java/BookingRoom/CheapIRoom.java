package BookingRoom;

import Utils.DBUtils;
import model.Room;

public class CheapIRoom implements IRoom {
    @Override
    public void book(Room room) {
        System.out.format("book CheapIRoom \n");
        DBUtils.insertIntoDb(room);
    }
}
