package OrderManagementSystemLLD.Order;

public class UPIPayment implements PaymentMode{
    @Override
    public boolean doPayment() {
        System.out.println("Payment done using UPI");
        return true;
    }
}
