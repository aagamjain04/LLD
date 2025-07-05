package CarRentalSystemLLD;

public class Payment {

    public void payBill(PaymentMode paymentMode,double amount,Bill bill){
        if(amount == bill.getAmount()){
            bill.setPaid(true);
            System.out.println("Bill is paid for amount :- " + amount + " with payment mode as " + paymentMode);
        }else{
            System.out.println("Incorrect amount");
        }
    }
}
