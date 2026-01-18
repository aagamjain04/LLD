package FlipBnplLLD.repository;

import FlipBnplLLD.model.User;

public interface UserRepository {
    void save(User user);
    User find(String id);
}
