package FlipkartMiniWalletLLD.service;

import FlipkartMiniWalletLLD.enums.PaymentType;

public interface WalletService {

    void registerUser(String name);
    void topUp(String name, String source,double amount);
    void sendMoney(String fromUser,String toUser,double amount);
    void fetchBalance(String name);
    void getTransactions(String name,String filter,String sortBy);

}
