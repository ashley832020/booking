package template;

import Utils.ValidationMessage;
import model.Room;

public abstract class RoomTemplate {

    public abstract Boolean validEmail();

    public abstract Boolean validPhone();

    public abstract Boolean checkExistRoom();

    public abstract void bookRoom();

    public ValidationMessage errorMessage = null;

    public final void tryToBookRoom() {

        if(!validEmail()) {
            System.out.format("Invalid Email \n");
            errorMessage.errorMessage("Invalid Email");
            return;
        }

        if(!validPhone()) {
            System.out.format("Invalid Phone \n");
            errorMessage.errorMessage("Invalid Phone");
            return;
        }

        if(checkExistRoom()) {
            errorMessage.errorMessage("Room Exist");
            System.out.format("Room Exist \n");
            return;
        }

        bookRoom();
        errorMessage.successMessage("");
    }

    public void setErrorMessage(ValidationMessage errorMessage) {
        this.errorMessage = errorMessage;
    }
}
