package payment;

public class CashPayment implements AdvancedPayment {
    @Override
    public void cash() {
        System.out.println("Payment with Cash");
    }

    @Override
    public void card() {

    }
}
