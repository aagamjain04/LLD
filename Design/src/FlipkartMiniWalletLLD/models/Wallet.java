package FlipkartMiniWalletLLD.models;

import java.util.ArrayList;
import java.util.List;

public class Wallet {
    private double balance = 0;
    private List<Transaction> transactions = new ArrayList<>();

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void credit(double amount) {
        balance += amount;
    }

    public void debit(double amount) {
        balance -= amount;
    }

}
