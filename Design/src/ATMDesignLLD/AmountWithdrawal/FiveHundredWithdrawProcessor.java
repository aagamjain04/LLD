package ATMDesignLLD.AmountWithdrawal;

import ATMDesignLLD.ATM;

public class FiveHundredWithdrawProcessor implements CashWithdrawProcessor{

    CashWithdrawProcessor nextCashWithdrawProcessor;

    public FiveHundredWithdrawProcessor(CashWithdrawProcessor cashWithdrawProcessor){
        this.nextCashWithdrawProcessor = cashWithdrawProcessor;
    }

    @Override
    public void withdraw(ATM atm, int remainingAmount) {

        int required =  remainingAmount/500;
        int balance = remainingAmount%500;

        if(required <= atm.getNoOfFiveHundredNotes()) {
            atm.deductFiveHundredNotes(required);
        }
        else if(required > atm.getNoOfFiveHundredNotes()) {
            atm.deductFiveHundredNotes(atm.getNoOfFiveHundredNotes());
            balance = balance + (required-atm.getNoOfFiveHundredNotes()) * 500;
        }

        if(balance!=0){
            nextCashWithdrawProcessor.withdraw(atm,balance);
        }

    }
}
