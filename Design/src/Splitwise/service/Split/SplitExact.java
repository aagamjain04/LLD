package Splitwise.service.Split;

import Splitwise.UserRepository.UserRepository;
import Splitwise.service.UserService;

import java.util.List;

public class SplitExact implements Split{
    UserService userService = new UserService();

    @Override
    public void process(String user, Double amount, List<String> members, List<String> shares) {

        int n = members.size();

        for(String member:members){
            if(!UserRepository.getStringUserMap().containsKey(member)) {
                userService.createNewUser(member);
            }
        }

        for(String s:members)shares.add(String.valueOf(shares.get(s.indexOf(s))));

        Settlement settlement = new Settlement();
        settlement.settle(user,amount,members,shares);

    }
}
