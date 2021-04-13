package payment;

public class Payment implements IPayment {

    PaymentAdapter paymentAdapter = null;
    @Override
    public void payment(String paymentType) {
        paymentAdapter = new PaymentAdapter(paymentType);
        paymentAdapter.payment(paymentType);
    }
}
