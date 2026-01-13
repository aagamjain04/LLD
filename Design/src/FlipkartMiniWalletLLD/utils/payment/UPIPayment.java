package FlipkartMiniWalletLLD.utils.payment;

public class UPIPayment implements PaymentMethod{

    @Override
    public boolean pay() {
        System.out.println("Payment done using UPI");
        return true;
    }
}
