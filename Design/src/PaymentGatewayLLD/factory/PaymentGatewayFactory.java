package PaymentGatewayLLD.factory;

import PaymentGatewayLLD.enums.PaymentGatewayType;
import PaymentGatewayLLD.gateway.PaymentGateway;
import PaymentGatewayLLD.gateway.impl.RazorpayGateway;
import PaymentGatewayLLD.gateway.impl.StripeGateway;

public class PaymentGatewayFactory {

    public static PaymentGateway getGateway(PaymentGatewayType type){
        switch (type) {
            case RAZORPAY:
                return new RazorpayGateway();
            case STRIPE:
                return new StripeGateway();
            default:
                throw new IllegalArgumentException("Unsupported gateway");
        }
    }

}
