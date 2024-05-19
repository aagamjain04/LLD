package Splitwise.service.Split;

import Splitwise.UserRepository.UserRepository;
import Splitwise.dto.Expense;
import Splitwise.dto.User;
import Splitwise.service.UserService;

import java.util.List;
import java.util.Map;

public class SplitEqually implements Split{

    UserService userService = new UserService();

    @Override
    public void process(String user, Double amount, List<String> members, List<String> shares) {

        int n = members.size();
        double eachShare = amount/n;

        for(String member:members){
            if(!UserRepository.getStringUserMap().containsKey(member)) {
                userService.createNewUser(member);
            }
        }

        for(String s:members)shares.add(String.valueOf(eachShare));

        Settlement settlement = new Settlement();
        settlement.settle(user,amount,members,shares);

    }
}
