package OrderManagementSystemLLD.Controller;

import OrderManagementSystemLLD.User.User;

import java.util.List;

public class UserController {

    List<User> userList;

    public UserController(List<User> userList){
        this.userList = userList;
    }

    // add user
    public void addUser(User user){
        userList.add(user);
    }

    // get user by id
    public User getUserById(int userId){
        for(User user: userList){
            if(user.userId == userId){
                return user;
            }
        }
        return null;
    }

    // remove user
    public void removeUser(int userId){
        userList.removeIf(user -> user.userId == userId);
    }
}
