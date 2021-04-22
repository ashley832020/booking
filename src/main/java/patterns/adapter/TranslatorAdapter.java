package patterns.adapter;

public class TranslatorAdapter implements VietnameseTarget  {

    private EnglishAdaptee adaptee;
    public TranslatorAdapter(EnglishAdaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void send(String words) {
        String language = this.translate(words);
        adaptee.receive(language);
    }

    private String translate(String language) {
        return language;
    }
}
