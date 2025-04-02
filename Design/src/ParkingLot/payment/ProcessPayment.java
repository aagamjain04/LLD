package ParkingLot.payment;

public class ProcessPayment {
    PaymentStrategy paymentStrategy;
    public ProcessPayment(PaymentStrategy paymentStrategy, double amount){
        this.paymentStrategy = paymentStrategy;
        paymentStrategy.pay(amount);
    }
}
