package ATMDesignLLD.AmountWithdrawal;

import ATMDesignLLD.ATM;

public class OneHundredWithdrawProcessor implements CashWithdrawProcessor{

    CashWithdrawProcessor nextCashWithdrawProcessor;

    public OneHundredWithdrawProcessor(CashWithdrawProcessor cashWithdrawProcessor){
        this.nextCashWithdrawProcessor = cashWithdrawProcessor;
    }

    @Override
    public void withdraw(ATM atm, int remainingAmount) {

        int required =  remainingAmount/100;
        int balance = remainingAmount%100;

        if(required <= atm.getNoOfOneHundredNotes()) {
            atm.deductOneHundredNotes(required);
        }
        else if(required > atm.getNoOfOneHundredNotes()) {
            atm.deductOneHundredNotes(atm.getNoOfOneHundredNotes());
            balance = balance + (required-atm.getNoOfOneHundredNotes()) * 100;
        }

        if(balance!=0){
            System.out.println("Denomination Error");
        }

    }
}
