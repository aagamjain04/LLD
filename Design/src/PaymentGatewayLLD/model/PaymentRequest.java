package PaymentGatewayLLD.model;

import PaymentGatewayLLD.enums.PaymentGatewayType;
import PaymentGatewayLLD.enums.PaymentMethodType;

public class PaymentRequest {

    private final double amount;
    private final PaymentGatewayType gateway;
    private final PaymentMethodType paymentMethod;

    public PaymentRequest(double amount, PaymentGatewayType gateway, PaymentMethodType paymentMethod) {
        this.amount = amount;
        this.gateway = gateway;
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentGatewayType getGateway() {
        return gateway;
    }

    public PaymentMethodType getPaymentMethod() {
        return paymentMethod;
    }
}
