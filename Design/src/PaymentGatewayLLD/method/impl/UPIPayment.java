package PaymentGatewayLLD.method.impl;

import PaymentGatewayLLD.method.PaymentMethod;

public class UPIPayment implements PaymentMethod {

    @Override
    public void validate() {
        System.out.println("Validating UPI details");
    }
}
