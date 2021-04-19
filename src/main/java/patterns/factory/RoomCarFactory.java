package patterns.factory;

import patterns.strategy.carAction.CheapICar;
import patterns.strategy.carAction.ExpensiveICar;
import patterns.strategy.carAction.ICar;
import patterns.strategy.carAction.NormalICar;
import patterns.strategy.roomAction.CheapIRoom;
import patterns.strategy.roomAction.ExpensiveIRoom;
import patterns.strategy.roomAction.IRoom;
import patterns.strategy.roomAction.NormalIRoom;

public class RoomCarFactory {

    public IRoom tryToBookRoom(RoomCarType type) {
        if (type == null) return null;
        switch (type) {
            case CHEAP: {
                return new CheapIRoom();
            }
            case NORMAL: {
                return new NormalIRoom();
            }
            case EXPENSIVE: {
                return new ExpensiveIRoom();
            }
            default:
                return new CheapIRoom();
        }
    }

    public ICar tryToBookCar(RoomCarType type) {
        if (type == null) return null;
        switch (type) {
            case CHEAP: {
                return new CheapICar();
            }
            case NORMAL: {
                return new NormalICar();
            }
            case EXPENSIVE: {
                return new ExpensiveICar();
            }
            default:
                return new CheapICar();
        }
    }
}
