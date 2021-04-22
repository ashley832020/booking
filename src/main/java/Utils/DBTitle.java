package Utils;

import java.util.ArrayList;

public class DBTitle {

    public static ArrayList<String> titleMultipleLanguage = null;

    public static void setDataLanguage(String language){
        if (language.equals("Vietnamese")) {
            setDataVietnamese();
        } else {
            setDataEnglish();
        }
    }

    public static void setDataEnglish() {
        titleMultipleLanguage = new ArrayList<>();
        titleMultipleLanguage.add("Name");
        titleMultipleLanguage.add("Phone");
        titleMultipleLanguage.add("Email");
        titleMultipleLanguage.add("From");
        titleMultipleLanguage.add("To");
        titleMultipleLanguage.add("Customer");
    }

    public static void setDataVietnamese() {
        titleMultipleLanguage = new ArrayList<>();
        titleMultipleLanguage.add("Tên");
        titleMultipleLanguage.add("Số điện thoai");
        titleMultipleLanguage.add("Hòm thư");
        titleMultipleLanguage.add("Từ ngày");
        titleMultipleLanguage.add("Đến ngày");
        titleMultipleLanguage.add("Khách hàng");
    }
}

