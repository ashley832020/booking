package patterns.factory;

import Utils.DBUtils;
import model.Room;
import patterns.strategy.roomAction.IRoom;
import patterns.strategy.validation.EmailValidate;
import patterns.strategy.validation.PhoneValidate;
import patterns.strategy.validation.ValidationStrategy;
import patterns.template.BookTemplate;

public class BookRoom extends BookTemplate {

    private Room room;
    private ValidationStrategy validationStrategy;

    public BookRoom(Room room) {
        this.room = room;
    }

    @Override
    public Boolean validEmail() {
        System.out.println("validEmail");
        this.validationStrategy = new EmailValidate();
//        ValidationStrategy validationStrategy = new EmailValidate();
        return validationStrategy.validate(room.getCustomerEmail());
    }

    @Override
    public Boolean validPhone() {
        System.out.println("validPhone");
        this.validationStrategy = new PhoneValidate();
//        ValidationStrategy validationStrategy = new PhoneValidate();
        return validationStrategy.validate(room.getCustomerPhone());
    }

    @Override
    public Boolean checkExist() {
        System.out.println("Check Exist Room");
        return DBUtils.checkRoomAvailableOrNot(room.getRoomNumber());
    }

    @Override
    public void book() {

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


        RoomCarFactory roomCarFactory = new RoomCarFactory();
        IRoom iRoom = roomCarFactory.tryToBookRoom(room.getRoomType());
        if (room.getBook()) {
            message.successMessage("Successfully with " + room.getPaymentMethod() + " payment");
            iRoom.book(room);
        } else {
            iRoom.insertRoom(room);
        }
    }
}
