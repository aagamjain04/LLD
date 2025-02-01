package Splitwise.service;

import Splitwise.UserRepository.UserRepository;
import Splitwise.dto.User;

public class UserService {
    public void createNewUser(String user){
        User newUser = new User(user);
        UserRepository.getUserList().add(newUser);
        UserRepository.getStringUserMap().put(user,newUser);
    }
}
