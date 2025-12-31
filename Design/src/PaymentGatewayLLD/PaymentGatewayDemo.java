package PaymentGatewayLLD;

import PaymentGatewayLLD.enums.PaymentGatewayType;
import PaymentGatewayLLD.enums.PaymentMethodType;
import PaymentGatewayLLD.model.PaymentRequest;
import PaymentGatewayLLD.model.PaymentResponse;
import PaymentGatewayLLD.service.PaymentService;

public class PaymentGatewayDemo {
    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();

        PaymentRequest request = new PaymentRequest(
                        1000.0,
                PaymentGatewayType.RAZORPAY,
                PaymentMethodType.CARD
                );

        PaymentResponse response = paymentService.makePayment(request);

        System.out.println("Payment Status: " + response.getPaymentStatus());
        System.out.println("Transaction ID: " + response.getTransactionId());
    }
}
