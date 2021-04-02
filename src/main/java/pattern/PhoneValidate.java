package pattern;

import Utils.Utils;

public class PhoneValidate implements ValidationStrategy {
    @Override
    public Boolean validate(String phone) {
        return Utils.isValidPhone(phone);
    }
}
