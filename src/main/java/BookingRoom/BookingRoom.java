package BookingRoom;

import Utils.DBUtils;
import model.Room;
import pattern.EmailValidate;
import pattern.PhoneValidate;
import pattern.RoomFactory;
import pattern.ValidationStrategy;
import template.RoomTemplate;

public class BookingRoom extends RoomTemplate {

    private Room room = null;

    public BookingRoom(Room room) {
        this.room = room;
    }

    @Override
    public Boolean validEmail() {
        ValidationStrategy validationStrategy = new EmailValidate();
        return validationStrategy.validate(room.getEmail());
    }

    @Override
    public Boolean validPhone() {
        ValidationStrategy validationStrategy = new PhoneValidate();
        return validationStrategy.validate(room.getPhone());
    }

    @Override
    public Boolean checkExistRoom() {
        return DBUtils.isExistRoomOrNot(room.getRoomName());
    }

    @Override
    public void bookRoom() {
        RoomFactory roomFactory = new RoomFactory();
        IRoom iRoom = roomFactory.tryToBookRoom(RoomType.EXPENSIVE);
        iRoom.insertRoom(room);
    }
}
