package FlipkartMiniWalletLLD.service;

import FlipkartMiniWalletLLD.enums.PaymentType;
import FlipkartMiniWalletLLD.enums.TransactionType;
import FlipkartMiniWalletLLD.factory.FilterFactory;
import FlipkartMiniWalletLLD.factory.PaymentMethodFactory;
import FlipkartMiniWalletLLD.factory.SortFactory;
import FlipkartMiniWalletLLD.models.Transaction;
import FlipkartMiniWalletLLD.models.User;
import FlipkartMiniWalletLLD.repository.UserRepository;
import FlipkartMiniWalletLLD.utils.filter.FilterStrategy;
import FlipkartMiniWalletLLD.utils.payment.PaymentMethod;
import FlipkartMiniWalletLLD.utils.sort.SortStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class WalletServiceImpl implements WalletService{

    private final UserRepository userRepository;

    public WalletServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(String name) {
        if(userRepository.exists(name)){
            System.out.println("User already exists");
            return;
        }
        userRepository.add(new User(name));
        System.out.println("User " + name + " is registered");
    }

    @Override
    public void topUp(String name, String source, double amount) {

        if(!userRepository.exists(name)){
            System.out.println("User not registered");
            return;
        }
        if(amount <=0 ){
            System.out.println("Invalid amount");
            return;

        }

        PaymentMethod paymentMethod = PaymentMethodFactory.getPaymentMethod(PaymentType.valueOf(source));

        boolean paymentSuccess = paymentMethod.pay();

        if (!paymentSuccess) {
            System.out.println("Payment failed");
            return;
        }


        User user = userRepository.get(name);
        user.wallet.credit(amount);
        user.wallet.getTransactions().add(new Transaction("SYSTEM",name,amount, TransactionType.TOPUP,PaymentType.valueOf(source),LocalDateTime.now()));

    }

    @Override
    public void sendMoney(String fromUser, String toUser, double amount) {

        if(!userRepository.exists(fromUser) || !userRepository.exists(toUser)){
            System.out.println("Invalid Users");
            return;
        }

        User sender = userRepository.get(fromUser);
        User receiver = userRepository.get(toUser);

        if(sender.wallet.getBalance()<amount){
            System.out.println("Insufficient balance");
            return;
        }

        sender.wallet.debit(amount);
        receiver.wallet.credit(amount);

        sender.wallet.getTransactions().add(new Transaction(fromUser,toUser,amount,TransactionType.SEND,PaymentType.UPI,LocalDateTime.now()));
        receiver.wallet.getTransactions().add(new Transaction(fromUser,toUser,amount,TransactionType.RECEIVE,PaymentType.UPI,LocalDateTime.now()));

        System.out.println(fromUser + " sent " + amount + " to " + toUser);

    }

    @Override
    public void fetchBalance(String name) {
        if (!userRepository.exists(name)) return;
        System.out.println(name + " Balance = " +
                userRepository.get(name).wallet.getBalance());
    }

    @Override
    public void getTransactions(String name, String filter, String sort) {

        List<Transaction> tx = new ArrayList<>(userRepository.get(name).wallet.getTransactions());

        FilterStrategy filterBy = FilterFactory.getFilterStrategy(filter);

        tx = filterBy.filter(tx);

        SortStrategy sortBy = SortFactory.getSortStrategy(sort);

        sortBy.sort(tx);

        tx.forEach(t ->
                System.out.println(t.fromUser + " -> " + t.toUser + " : " + t.getAmount())
        );


    }
}
