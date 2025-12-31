package PaymentGatewayLLD.method.impl;

import PaymentGatewayLLD.method.PaymentMethod;

public class CardPayment implements PaymentMethod {
    @Override
    public void validate() {
        System.out.println("Validating card details");
    }
}
