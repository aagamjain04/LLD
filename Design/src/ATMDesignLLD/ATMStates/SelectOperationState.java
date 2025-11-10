package ATMDesignLLD.ATMStates;

import ATMDesignLLD.ATM;
import ATMDesignLLD.Card;
import ATMDesignLLD.TransactionType;

public class SelectOperationState extends ATMState{

    public SelectOperationState(){
        showOperations();
    }

    @Override
    public void selectOperation(ATM atm, Card card, TransactionType transactionType){
        switch (transactionType){

            case CASH_WITHDRAWAL :
                atm.setCurrentATMState(new CashWithdrawalState());
                break;
            case BALANCE_CHECK:
                atm.setCurrentATMState(new CheckBalanceState());
                break;
            default: {
                System.out.println("Invalid Option");
                exit(atm);
            }

        }
    }

    @Override
    public void exit(ATM atmObject){
        returnCard();
        atmObject.setCurrentATMState(new IdleState());
        System.out.println("Exiting...");
    }

    @Override
    public void returnCard(){
        System.out.println("Please collect your card");
    }


    private void showOperations(){
        System.out.println("Please select the Operation");
        TransactionType.showAllTransactionTypes();
    }

}
