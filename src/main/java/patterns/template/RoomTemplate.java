package patterns.template;

import patterns.ValidationMessage;

public abstract class RoomTemplate {

    public abstract Boolean validEmail();

    public abstract Boolean validPhone();

    public abstract Boolean checkExistRoom();

    public abstract void bookRoom();

    public ValidationMessage errorMessage = null;

    public final void tryToBookRoom() {
        System.out.format("tryToBookRoom \n");

        if(!validPhone()) {
            System.out.format("Invalid Phone \n");
            errorMessage.errorMessage("Invalid Phone");
            return;
        }

        if(!validEmail()) {
            System.out.format("Invalid Email \n");
            errorMessage.errorMessage("Invalid Email");
            return;
        }

        if(!checkExistRoom()) {
            errorMessage.errorMessage("Not Available");
            System.out.format("Room Exist \n");
            return;
        }

        bookRoom();
    }

    public void setErrorMessage(ValidationMessage errorMessage) {
        this.errorMessage = errorMessage;
    }
}
