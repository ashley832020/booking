package template;

import Utils.ErrorMessage;
import model.Room;

import javax.swing.*;

public abstract class RoomTemplate {

    public abstract Boolean validEmail(String email);

    public abstract Boolean validPhone(String phone);

    public abstract Boolean checkExistRoom(String roomName);

    public abstract void bookRoom(Room room);

    public ErrorMessage errorMessage = null;

    public final void tryToBookRoom(Room room) {

        if(!validEmail(room.getEmail())) {
            System.out.format("Invalid Email \n");
            errorMessage.errorMessage("Invalid Email");
            return;
        }

        if(!validPhone(room.getPhone())) {
            System.out.format("Invalid Phone \n");
            errorMessage.errorMessage("Invalid Phone");
            return;
        }

        if(checkExistRoom(room.getRoomName())) {
            errorMessage.errorMessage("Room Exist");
            System.out.format("Room Exist \n");
            return;
        }

        bookRoom(room);
        errorMessage.successMessage("");
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }
}
