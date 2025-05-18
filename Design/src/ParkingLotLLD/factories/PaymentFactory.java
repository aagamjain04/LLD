package ParkingLotLLD.factories;

import ParkingLotLLD.payment.CardPayment;
import ParkingLotLLD.payment.CashPayment;
import ParkingLotLLD.payment.PaymentMode;
import ParkingLotLLD.payment.PaymentStrategy;

public class PaymentFactory {

    public static PaymentStrategy getPaymentStrategy(PaymentMode mode){
        switch (mode){
            case CARD -> {
                return new CardPayment();
            }
            case CASH -> {
                return new CashPayment();
            }
        }
        return null;
    }
}
