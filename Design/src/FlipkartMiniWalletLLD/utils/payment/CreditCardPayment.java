package FlipkartMiniWalletLLD.utils.payment;

public class CreditCardPayment implements PaymentMethod{
    @Override
    public boolean pay() {
        System.out.println("Payment done using Credit Card");
        return true;
    }
}
