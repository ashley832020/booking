package patterns.filter;

import model.Room;
import java.util.ArrayList;
import java.util.List;

public class CapacityTwo implements Capacity {

    @Override
    public List<Room> roomCapacity(List<Room> rooms) {
        List<Room> roomCapacityTwo = new ArrayList<Room>();

        for (Room room : rooms) {
            if(room.getCapacity().equalsIgnoreCase("2")){
                roomCapacityTwo.add(room);
            }
        }
        return roomCapacityTwo;
    }
}
