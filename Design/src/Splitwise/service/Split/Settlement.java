package Splitwise.service.Split;

import Splitwise.UserRepository.UserRepository;
import Splitwise.dto.Expense;
import Splitwise.dto.User;

import java.util.List;

public class Settlement {
    public void settle(String user, Double amount, List<String> members, List<String> shares) {
        for(String member:members) {
            if(member.equals(user))continue;
            Expense ex1 = UserRepository.getStringUserMap().get(user).getLendToMap().get(member);
            Expense ex2 = UserRepository.getStringUserMap().get(member).getLendToMap().get(user);
            int index = members.indexOf(member);
            Double share = Double.valueOf(shares.get(index));
            Double amount1 = 0.0,amount2 = 0.0;
            if(ex1 != null)
                amount1 = ex1.getAmount();

            if(ex2 != null)
                amount2 = ex2.getAmount();

            double result = amount2 - amount1 + share;

            if(result>0){
                Expense expense = new Expense(user,result);
                User u = UserRepository.getStringUserMap().get(member);
                u.setLendToMap(user,expense);
            }else{
                Expense expense = new Expense(member,-result);
                User u = UserRepository.getStringUserMap().get(user);
                u.setLendToMap(member,expense);
            }


        }
    }
}
