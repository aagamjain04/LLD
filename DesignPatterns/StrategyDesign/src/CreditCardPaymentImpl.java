public class CreditCardPaymentImpl implements PaymentStrategy{
    @Override
    public void pay(int amount) {
        System.out.println("Paid $"+amount + " using Credit Card");
    }
}
