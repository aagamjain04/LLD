package Splitwise.service.Split;

import Splitwise.UserRepository.UserRepository;
import Splitwise.service.UserService;

import java.util.List;

public class SplitPercentage implements Split{
    UserService userService = new UserService();

    @Override
    public void process(String user, Double amount, List<String> members, List<String> shares) {

        int n = members.size();

        for(String member:members){
            if(!UserRepository.getStringUserMap().containsKey(member)) {
                userService.createNewUser(member);
            }
        }

        for(int i=0;i<n;i++)
        {
            double borrowedAmount=Double.parseDouble(shares.get(i))*amount/100.0;
            shares.set(i,String.valueOf(borrowedAmount));
        }

        Settlement settlement = new Settlement();
        settlement.settle(user,amount,members,shares);

    }
}
