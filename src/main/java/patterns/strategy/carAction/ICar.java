package patterns.strategy.carAction;
import model.Car;

public interface ICar {
    void book(Car car);
    void insertCar(Car car);
}
