package FlipkartMiniWalletLLD.models;

public class User {
    String name;
    public Wallet wallet = new Wallet();

    public User(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
