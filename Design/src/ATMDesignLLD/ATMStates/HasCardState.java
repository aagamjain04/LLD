package ATMDesignLLD.ATMStates;

import ATMDesignLLD.ATM;
import ATMDesignLLD.Card;

public class HasCardState extends ATMState{

    public HasCardState(Card card){
        System.out.println("enter your card pin number");
        card.setPin(1234);
    }

    @Override
    public void authenticatePin(ATM atm, Card card, int pin){
        boolean isCorrectPinEntered = card.isCorrectPINEntered(pin);
        if(isCorrectPinEntered) {
            atm.setCurrentATMState(new SelectOperationState());
        } else {
            System.out.println("Invalid PIN Number");
            exit(atm);
        }
    }

    @Override
    public void exit(ATM atm){
        returnCard();
        atm.setCurrentATMState(new IdleState());
        System.out.println("Exiting..");
    }

    @Override
    public void returnCard(){
        System.out.println("Please collect your card");
    }
}
