package patterns.adapter;

import Utils.DBTitle;
public class EnglishAdaptee {
    public void receive(String language) {
        DBTitle.setDataLanguage(language);
    }
}
