package template;

public abstract class CarTemplate {

    public abstract void chooseItems();

    public final void getVoucher() {
        System.out.println("Get voucher");
    }

    public abstract void doPayment(int paymentType);

    public abstract void doDelivery();

    public final void tryToOrder(boolean isVoucher, int paymentType) {
        chooseItems();
        if (isVoucher) {
            getVoucher();
        }
        doPayment(paymentType);
        doDelivery();
    }
}
