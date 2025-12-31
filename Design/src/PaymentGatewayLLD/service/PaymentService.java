package PaymentGatewayLLD.service;

import PaymentGatewayLLD.enums.PaymentGatewayType;
import PaymentGatewayLLD.factory.PaymentGatewayFactory;
import PaymentGatewayLLD.factory.PaymentMethodFactory;
import PaymentGatewayLLD.gateway.PaymentGateway;
import PaymentGatewayLLD.method.PaymentMethod;
import PaymentGatewayLLD.model.PaymentRequest;
import PaymentGatewayLLD.model.PaymentResponse;

public class PaymentService {

    public PaymentResponse makePayment(PaymentRequest request){

        // Step 1 : Validate payment method
        // Step 2 : Get Gateway
        // Step 3 : Process payment

        PaymentMethod paymentMethod = PaymentMethodFactory.getPaymentMethod(request.getPaymentMethod());
        paymentMethod.validate();

        PaymentGateway gateway = PaymentGatewayFactory.getGateway(request.getGateway());

        return gateway.processPayment(request);


    }
}
