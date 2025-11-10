package ATMDesignLLD.ATMStates;

import ATMDesignLLD.ATM;
import ATMDesignLLD.ATMRoom;
import ATMDesignLLD.Card;
import ATMDesignLLD.TransactionType;

public abstract class ATMState {

    public void insertCard(ATM atm, Card card){
        System.out.println("OOPS!! Something went wrong");
    }
    public void authenticatePin(ATM atm, Card card,int pin){
        System.out.println("OOPS!! Something went wrong");
    }
    public void selectOperation(ATM atm, Card card, TransactionType transactionType){
        System.out.println("OOPS!! Something went wrong");
    }
    public void cashWithdrawal(ATM atm, Card card, int withdrawAmount){
        System.out.println("OOPS!! Something went wrong");
    }
    public void displayBalance(ATM atm, Card card){
        System.out.println("OOPS!! Something went wrong");
    }
    public void returnCard(){
        System.out.println("OOPS!! Something went wrong");
    }
    public void exit(ATM atm){
        System.out.println("OOPS!! Something went wrong");
    }

}
