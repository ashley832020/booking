package patterns.filter;

import model.Room;
import java.util.ArrayList;
import java.util.List;

public class CapacityOne implements Capacity {

    @Override
    public List<Room> roomCapacity(List<Room> rooms) {
        List<Room> roomCapacityOne = new ArrayList<>();

        for (Room room : rooms) {
            if(room.getCapacity().equalsIgnoreCase("1")){
                roomCapacityOne.add(room);
            }
        }
        return roomCapacityOne;
    }
}
