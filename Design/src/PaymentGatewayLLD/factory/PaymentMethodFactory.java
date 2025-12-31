package PaymentGatewayLLD.factory;

import PaymentGatewayLLD.enums.PaymentMethodType;
import PaymentGatewayLLD.method.PaymentMethod;
import PaymentGatewayLLD.method.impl.CardPayment;
import PaymentGatewayLLD.method.impl.UPIPayment;

public class PaymentMethodFactory {

    public static PaymentMethod getPaymentMethod(PaymentMethodType type){
        switch (type){
            case CARD:
                return new CardPayment();
            case UPI:
                return new UPIPayment();
            default:
                throw new IllegalArgumentException("Unsupported payment method");
        }
    }
}
