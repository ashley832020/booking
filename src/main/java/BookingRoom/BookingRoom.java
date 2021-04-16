package BookingRoom;

import Utils.DBUtils;
import model.Room;
import validation.EmailValidate;
import validation.PhoneValidate;
import validation.RoomFactory;
import validation.ValidationStrategy;
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

//        if(room.getRoomType() == RoomType.CHEAP) {
//            IRoom iRoom = new CheapIRoom();
//            iRoom.book(room);
//        } else if(room.getRoomType() == RoomType.NORMAL) {
//            IRoom iRoom = new NormalIRoom();
//            iRoom.book(room);
//        } else  {
//            IRoom iRoom = new CheapIRoom();
//            iRoom.book(room);
//        }


        RoomFactory roomFactory = new RoomFactory();
        IRoom iRoom = roomFactory.tryToBookRoom(room.getRoomType());
        if (room.getBook()) {
            errorMessage.successMessage("Successfully with " + room.getPaymentMethod() + " payment");
            iRoom.book(room);
        } else {
            iRoom.insertRoom(room);
        }
    }
}
