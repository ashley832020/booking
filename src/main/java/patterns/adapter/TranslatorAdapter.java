package patterns.adapter;

import Utils.DBTitle;

public class TranslatorAdapter implements LanguageTarget {

    private LanguageAdaptee adaptee;
    public TranslatorAdapter(LanguageAdaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void translate(String language) {
        DBTitle.setDataLanguage(language); // already translated
        adaptee.receiveLanguage(DBTitle.titleMultipleLanguage);
    }
}
