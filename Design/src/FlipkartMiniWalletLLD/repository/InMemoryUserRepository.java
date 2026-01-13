package FlipkartMiniWalletLLD.repository;

import FlipkartMiniWalletLLD.models.User;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository{

    private Map<String,User> store = new HashMap<>();
    @Override
    public boolean exists(String name) {
        return store.containsKey(name);
    }

    @Override
    public void add(User user) {
        store.put(user.getName(),user);
    }

    @Override
    public User get(String name) {
        return store.getOrDefault(name,null);
    }
}
