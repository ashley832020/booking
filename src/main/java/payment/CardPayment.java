package payment;

public class CardPayment implements AdvancedPayment {
    @Override
    public void cash() {

    }

    @Override
    public void card() {
        System.out.println("Payment with card");
    }
}
