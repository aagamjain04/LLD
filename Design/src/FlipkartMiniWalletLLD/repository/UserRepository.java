package FlipkartMiniWalletLLD.repository;

import FlipkartMiniWalletLLD.models.User;

public interface UserRepository {
    boolean exists(String name);
    void add(User user);
    User get(String name);

}
