package patterns.template;

import patterns.ValidationMessage;

public abstract class BookTemplate {

    public abstract Boolean validEmail();

    public abstract Boolean validPhone();

    public abstract Boolean checkExist();

    public abstract void book();

    public ValidationMessage errorMessage = null;

    public final void tryToBook() {
        System.out.format("tryToBook \n");

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

        if(!checkExist()) {
            errorMessage.errorMessage("Not Available");
            return;
        }

        book();
    }

    public void setErrorMessage(ValidationMessage errorMessage) {
        this.errorMessage = errorMessage;
    }
}
