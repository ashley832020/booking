package patterns.adapter;

import Utils.DBTitle;

import java.util.ArrayList;

public class LanguageAdaptee {
    public void receiveLanguage(ArrayList<String> languages) {
        DBTitle.titleMultipleLanguageReceive = languages;
    }
}
