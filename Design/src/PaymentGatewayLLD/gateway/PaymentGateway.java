package PaymentGatewayLLD.gateway;

import PaymentGatewayLLD.model.PaymentRequest;
import PaymentGatewayLLD.model.PaymentResponse;

public interface PaymentGateway {

    PaymentResponse processPayment(PaymentRequest request);
}
