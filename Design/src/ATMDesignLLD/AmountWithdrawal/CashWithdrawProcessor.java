package ATMDesignLLD.AmountWithdrawal;

import ATMDesignLLD.ATM;

public interface CashWithdrawProcessor {

    public void withdraw(ATM atm, int remainingAmount);

}
