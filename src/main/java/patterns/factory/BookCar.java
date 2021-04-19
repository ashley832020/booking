package patterns.factory;

import Utils.DBUtils;
import model.Car;
import patterns.strategy.carAction.ICar;
import patterns.strategy.validation.EmailValidate;
import patterns.strategy.validation.PhoneValidate;
import patterns.strategy.validation.ValidationStrategy;
import patterns.template.BookTemplate;

public class BookCar extends BookTemplate {

    private Car car = null;
    private ValidationStrategy validationStrategy;

    public BookCar(Car car) {
        this.car = car;
    }

    @Override
    public Boolean validEmail() {
        System.out.println("validEmail");
        this.validationStrategy = new EmailValidate();
//        ValidationStrategy validationStrategy = new EmailValidate();
        return validationStrategy.validate(car.getCustomerEmail());
    }

    @Override
    public Boolean validPhone() {
        System.out.println("validPhone");
        this.validationStrategy = new PhoneValidate();
//        ValidationStrategy validationStrategy = new PhoneValidate();
        return validationStrategy.validate(car.getCustomerPhone());
    }

    @Override
    public Boolean checkExist() {
        System.out.println("Check Exist Car");
        return DBUtils.checkCarAvailableOrNot(car.getCarNumber());
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
        ICar iCar = roomCarFactory.tryToBookCar(car.getCarType());
        if (car.getBook()) {
            errorMessage.successMessage("Successfully with " + car.getPaymentMethod() + " payment");
            iCar.book(car);
        } else {
            iCar.insertCar(car);
        }
    }
}
