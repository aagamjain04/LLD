package FlipkartMiniWalletLLD.models;

import FlipkartMiniWalletLLD.enums.PaymentType;
import FlipkartMiniWalletLLD.enums.TransactionType;

import java.time.LocalDateTime;

public class Transaction {
    public String fromUser;
    public String toUser;
    double amount;
    TransactionType transactionType;
    PaymentType paymentType;
    LocalDateTime time;

    public Transaction(String fromUser, String toUser, double amount, TransactionType transactionType,PaymentType paymentType, LocalDateTime time) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.amount = amount;
        this.transactionType = transactionType;
        this.paymentType = paymentType;
        this.time = time;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
