package ParkingLotLLD.payment;

public class CardPayment implements PaymentStrategy{
    @Override
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using Card\n");
    }
}
