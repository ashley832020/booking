package payment;

import Utils.ConstantsKey;

public class PaymentAdapter implements IPayment {

    AdvancedPayment advancedPayment = null;
    public PaymentAdapter(String paymentType) {
        if (paymentType.equalsIgnoreCase(ConstantsKey.PAYMENT_CASH)) {
            advancedPayment = new CashPayment();

        } else if (paymentType.equalsIgnoreCase(ConstantsKey.PAYMENT_CARD)) {
            advancedPayment = new CardPayment();
        }
    }

    @Override
    public void payment(String paymentType) {
        if(advancedPayment == null) return;
        if (paymentType.equalsIgnoreCase(ConstantsKey.PAYMENT_CASH)) {
            advancedPayment.cash();

        } else if (paymentType.equalsIgnoreCase(ConstantsKey.PAYMENT_CARD)) {
            advancedPayment.card();
        }
    }
}
