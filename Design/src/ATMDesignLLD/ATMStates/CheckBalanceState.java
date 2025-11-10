package ATMDesignLLD.ATMStates;

import ATMDesignLLD.ATM;
import ATMDesignLLD.Card;

public class CheckBalanceState extends ATMState{

    public CheckBalanceState(){

    }

    @Override
    public void displayBalance(ATM atm, Card card){
        System.out.println("Your Balance is: " + card.getBankBalance());
        exit(atm);
    }

    @Override
    public void exit(ATM atm){
        returnCard();
        atm.setCurrentATMState(new IdleState());
        System.out.println("Exiting...");
    }

    @Override
    public void returnCard(){
        System.out.println("Please collect your card");
    }



}
