package BookingRoom;

import Utils.DBUtils;
import model.Room;
import pattern.EmailValidate;
import pattern.PhoneValidate;
import pattern.RoomFactory;
import pattern.ValidationStrategy;
import template.RoomTemplate;

public class BookingRoom extends RoomTemplate {

    @Override
    public Boolean validEmail(String email) {
        ValidationStrategy validationStrategy = new EmailValidate();
        return validationStrategy.validate(email);
    }

    @Override
    public Boolean validPhone(String phone) {
        ValidationStrategy validationStrategy = new PhoneValidate();
        return validationStrategy.validate(phone);
    }

    @Override
    public Boolean checkExistRoom(String roomName) {
        return DBUtils.isExistRoomOrNot(roomName);
    }

    @Override
    public void bookRoom(Room room) {
        RoomFactory roomFactory = new RoomFactory();
        roomFactory.tryToBookRoom(room.getRoomType());
    }
}
