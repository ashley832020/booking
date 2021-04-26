package patterns.filter;

import model.Room;
import java.util.List;

public interface Capacity {
    List<Room> roomCapacity(List<Room> rooms);
}
