package ParkingLotLLD.payment;

import ParkingLotLLD.factories.PaymentFactory;

public class ProcessPayment {
    PaymentStrategy paymentStrategy;

    public ProcessPayment(PaymentMode mode,double amount){
        this.paymentStrategy = PaymentFactory.getPaymentStrategy(mode);
        paymentStrategy.pay(amount);
    }


}
