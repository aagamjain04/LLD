package PaymentGatewayLLD.model;

import PaymentGatewayLLD.enums.PaymentStatus;

public class PaymentResponse {
    private final String transactionId;
    private final PaymentStatus paymentStatus;

    public PaymentResponse(String transactionId, PaymentStatus paymentStatus) {
        this.transactionId = transactionId;
        this.paymentStatus = paymentStatus;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
}
