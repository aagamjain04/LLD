public class BankTransferPaymentImpl implements PaymentStrategy{
    @Override
    public void pay(int amount) {
        System.out.println("Paid $"+amount + " using Bank Transfer");
    }
}
