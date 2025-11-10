package ATMDesignLLD;

public class Card {

    private int cardNumber;
    private int cvv;
    private String holderName;
    private int expiryDate;
    private int pin;
    private UserBankAccount bankAccount;

    public boolean isCorrectPINEntered(int pin){
        if(this.pin == pin){
            return true;
        }
        return false;
    }

    public void setBankAccount(UserBankAccount userBankAccount){
        this.bankAccount = userBankAccount;
    }

    public int getBankBalance(){
        return bankAccount.balance;
    }

    public void deductBankBalance(int amount){
        bankAccount.withdrawalBalance(amount);
    }

    public void setPin(int pin){
        this.pin = pin;
    }




}
