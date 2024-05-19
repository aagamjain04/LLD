package Splitwise.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Splitwise.dto.User;

public class UserRepository {



    static List<User> userList;
    static Map<String,User> stringUserMap;

    public UserRepository() {
        userList = new ArrayList<>();
        stringUserMap = new HashMap<>();
    }

    public static List<User> getUserList() {
        return userList;
    }

    public static Map<String, User> getStringUserMap() {
        return stringUserMap;
    }



}
