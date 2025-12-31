package PaymentGatewayLLD.gateway.impl;

import PaymentGatewayLLD.enums.PaymentStatus;
import PaymentGatewayLLD.gateway.PaymentGateway;
import PaymentGatewayLLD.model.PaymentRequest;
import PaymentGatewayLLD.model.PaymentResponse;

public class RazorpayGateway implements PaymentGateway {
    @Override
    public PaymentResponse processPayment(PaymentRequest request) {
        System.out.println("Processing payment via Razorpay");
        return new PaymentResponse("RAZOR123", PaymentStatus.SUCCESS);
    }
}
