package Utils;

import java.util.regex.Pattern;

public class Utils {
    public static Boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }

    public static Boolean isValidPhone(String phone) {
        String regex = "^[0-9]{10}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(phone).matches();
    }

    public static Boolean isValidGirl(String name) {
        return name.equals("Ashley");
    }

}
