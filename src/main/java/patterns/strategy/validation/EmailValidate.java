package patterns.strategy.validation;

import Utils.Utils;

public class EmailValidate implements ValidationStrategy {
    @Override
    public Boolean validate(String email) {
        return Utils.isValidEmail(email);
    }
}
