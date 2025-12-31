package PaymentGatewayLLD.gateway.impl;

import PaymentGatewayLLD.enums.PaymentStatus;
import PaymentGatewayLLD.gateway.PaymentGateway;
import PaymentGatewayLLD.model.PaymentRequest;
import PaymentGatewayLLD.model.PaymentResponse;

public class StripeGateway implements PaymentGateway {
    @Override
    public PaymentResponse processPayment(PaymentRequest request) {
        System.out.println("Processing payment via Stripe");
        return new PaymentResponse("STRIPE456", PaymentStatus.SUCCESS);
    }
}
