package validation;

import BookingRoom.*;

public class RoomFactory {

    public IRoom tryToBookRoom(RoomType type) {
        if(type == null) return null;
        switch (type) {
            case CHEAP: {
                return new CheapIRoom();
            }
            case NORMAL: {
                return new NormalIRoom();
            }
            case EXPENSIVE: {
                return new ExpensiveIRoom();
            }
            default:
                return new CheapIRoom();
        }
    }
}
