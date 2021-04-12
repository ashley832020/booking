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
        System.out.println("validEmail");
        ValidationStrategy validationStrategy = new EmailValidate();
        return validationStrategy.validate(room.getCustomerEmail());
    }

    @Override
    public Boolean validPhone() {
        System.out.println("validPhone");
        ValidationStrategy validationStrategy = new PhoneValidate();
        return validationStrategy.validate(room.getCustomerPhone());
    }

    @Override
    public Boolean checkExistRoom() {
        System.out.println("checkExistRoom");
        return DBUtils.checkRoomAvailableOrNot(room.getRoomNumber());
    }

    @Override
    public void bookRoom() {
        RoomFactory roomFactory = new RoomFactory();
        IRoom iRoom = roomFactory.tryToBookRoom(RoomType.EXPENSIVE);
        iRoom.book(room);
    }
}
