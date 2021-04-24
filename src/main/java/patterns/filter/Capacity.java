package patterns.filter;

import model.Room;
import java.util.List;

public interface Capacity {
    public List<Room> roomCapacity(List<Room> persons);
}
