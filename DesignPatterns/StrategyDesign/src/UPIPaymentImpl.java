public class UPIPaymentImpl implements PaymentStrategy{

    @Override
    public void pay(int amount) {
        System.out.println("Paid $"+amount + " using UPI");
    }
}
