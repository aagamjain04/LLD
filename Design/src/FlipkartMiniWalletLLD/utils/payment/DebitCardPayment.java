package FlipkartMiniWalletLLD.utils.payment;

public class DebitCardPayment implements PaymentMethod{
    @Override
    public boolean pay() {
        System.out.println("Payment done using Debit Card");
        return true;
    }
}
