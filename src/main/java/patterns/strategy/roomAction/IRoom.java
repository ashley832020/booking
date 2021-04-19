package patterns.strategy.roomAction;

import model.Room;

public interface IRoom {
    void book(Room room);
    void insertRoom(Room room);
}
