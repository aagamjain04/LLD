package FlipBnplLLD.repository;

import FlipBnplLLD.model.User;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository{
    Map<String, User> store = new HashMap<>(); // UserId to User

    public void save(User user) {
        store.put(user.getId(), user);
    }

    public User find(String id) {
        return store.getOrDefault(id,null);
    }

}
