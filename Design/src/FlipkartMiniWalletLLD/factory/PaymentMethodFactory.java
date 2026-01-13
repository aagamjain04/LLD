package FlipkartMiniWalletLLD.factory;


import FlipkartMiniWalletLLD.enums.PaymentType;
import FlipkartMiniWalletLLD.utils.payment.CreditCardPayment;
import FlipkartMiniWalletLLD.utils.payment.DebitCardPayment;
import FlipkartMiniWalletLLD.utils.payment.PaymentMethod;
import FlipkartMiniWalletLLD.utils.payment.UPIPayment;

public class PaymentMethodFactory {

    public static PaymentMethod getPaymentMethod(PaymentType type){
        switch (type){
            case CREDIT_CARD:
                return new CreditCardPayment();
            case UPI:
                return new UPIPayment();
            case DEBIT_CARD:
                return new DebitCardPayment();
            default:
                throw new IllegalArgumentException("Unsupported payment method");
        }
    }
}
