package patterns.strategy.carAction;

import Utils.DBUtils;
import model.Car;

public class ExpensiveICar implements ICar {
    @Override
    public void book(Car car) {
        System.out.format("book ExpensiveICar \n");
        DBUtils.updateCarInformation(car);
    }

    @Override
    public void insertCar(Car car) {
        DBUtils.insertCarIntoDb(car);
    }
}
