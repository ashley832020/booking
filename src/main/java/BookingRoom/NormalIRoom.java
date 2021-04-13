package BookingRoom;

import Utils.DBUtils;
import model.Room;

public class NormalIRoom implements IRoom {
    @Override
    public void book(Room room) {
        System.out.format("book NormalIRoom \n");
        DBUtils.updateRoomInformation(room);
    }

    @Override
    public void insertRoom(Room room) {
        DBUtils.insertIntoDb(room);
    }
}
