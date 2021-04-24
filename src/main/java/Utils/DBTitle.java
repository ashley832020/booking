package Utils;

import java.util.ArrayList;

public class DBTitle {

    public static ArrayList<String> titleMultipleLanguage = null;
    public static ArrayList<String> titleMultipleLanguageReceive = new ArrayList<>();

    public static void setDataLanguage(String language){
        if (language.equals("Vietnamese")) {
            setDataVietnamese();
        } else {
            setDataEnglish();
        }
    }

    private static void setDataEnglish() {
        titleMultipleLanguage = new ArrayList<>();
        titleMultipleLanguage.add("Name");
        titleMultipleLanguage.add("Phone");
        titleMultipleLanguage.add("Email");
        titleMultipleLanguage.add("From");
        titleMultipleLanguage.add("To");
        titleMultipleLanguage.add("Customer");
    }

    private static void setDataVietnamese() {
        titleMultipleLanguage = new ArrayList<>();
        titleMultipleLanguage.add("Tên");
        titleMultipleLanguage.add("SĐT");
        titleMultipleLanguage.add("Hòm thư");
        titleMultipleLanguage.add("Từ ngày");
        titleMultipleLanguage.add("Đến ngày");
        titleMultipleLanguage.add("Khách hàng");
    }
}

