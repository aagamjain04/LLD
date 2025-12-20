package OrderManagementSystemLLD.Order;

public class Payment {

    PaymentMode paymentMode;

    public Payment(PaymentMode paymentMode){
        this.paymentMode = paymentMode;
    }

    public Boolean makePayment(){
        return paymentMode.doPayment();
    }
}
