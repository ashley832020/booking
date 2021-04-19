package patterns.strategy.roomAction;

import Utils.DBUtils;
import model.Room;

public class CheapIRoom implements IRoom {
    @Override
    public void book(Room room) {
        System.out.format("book CheapIRoom \n");
        DBUtils.updateRoomInformation(room);
    }

    @Override
    public void insertRoom(Room room) {
        DBUtils.insertRoomIntoDb(room);
    }
}
