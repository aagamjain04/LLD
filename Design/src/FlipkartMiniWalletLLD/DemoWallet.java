package FlipkartMiniWalletLLD;

import FlipkartMiniWalletLLD.repository.InMemoryUserRepository;
import FlipkartMiniWalletLLD.repository.UserRepository;
import FlipkartMiniWalletLLD.service.WalletService;
import FlipkartMiniWalletLLD.service.WalletServiceImpl;

public class DemoWallet {
    public static void main(String[] args) {

        UserRepository db = new InMemoryUserRepository();
        WalletService service = new WalletServiceImpl(db);

        service.fetchBalance("Bob");
        service.registerUser("Bob");
        service.topUp("Bob","CREDIT_CARD",1000);
        service.topUp("Bob","UPI",100);
        service.fetchBalance("Bob");

        service.registerUser("Alice");
        service.topUp("Alice","CREDIT_CARD",100);
        service.fetchBalance("Alice");

        service.sendMoney("Bob","Alice",1250);
        service.sendMoney("Bob","Alice",250);
        service.sendMoney("Alice","Bob",50);
        service.sendMoney("Bob","Alice",100);

        service.fetchBalance("Bob");
        service.fetchBalance("Alice");

        service.getTransactions("Bob","send","amount");
        service.getTransactions("Bob","receive","time");






    }
}
