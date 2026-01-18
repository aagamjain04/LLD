package FlipBnplLLD.service;

import FlipBnplLLD.model.User;
import FlipBnplLLD.repository.UserRepository;

public class UserService {

    private final UserRepository userDb;

    public UserService(UserRepository userDb) {
        this.userDb = userDb;
    }


    int userId = 1;

    public User registerUser(String name, int creditLimit) {
        String id = String.format("%04d", userId++);
        User user = new User(id, name, creditLimit);
        userDb.save(user);
        return user;
    }


}
