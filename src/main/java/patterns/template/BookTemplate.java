package patterns.template;

import patterns.ValidationMessage;

public abstract class BookTemplate {

    public abstract Boolean validEmail();

    public abstract Boolean validPhone();

    public abstract Boolean checkExist();

    public abstract void book();

    public ValidationMessage message = null;

    public final void tryToBook() {
        System.out.format("tryToBook \n");

        if(!validPhone()) {
            System.out.format("Invalid Phone \n");
            message.errorMessage("Invalid Phone");
            return;
        }

        if(!validEmail()) {
            System.out.format("Invalid Email \n");
            message.errorMessage("Invalid Email");
            return;
        }

        if(!checkExist()) {
            message.errorMessage("Not Available");
            return;
        }

        book();
    }

    public void setMessage(ValidationMessage message) {
        this.message = message;
    }
}
