package Splitwise.service;

import Splitwise.UserRepository.UserRepository;
import Splitwise.constants.Error;
import Splitwise.dto.Expense;
import Splitwise.dto.User;

import java.util.Map;

public class ShowService {
    public void showById(String id) {
        if(!UserRepository.getStringUserMap().containsKey(id)){
            System.out.println(Error.NOBALANCE);
            return;
        }
        for(User user : UserRepository.getUserList()){
            Map<String, Expense> lendMap = user.getLendToMap();
            for(Expense x: lendMap.values()){
                if(user.getName().equals(id) || x.getUserId().equals(id) || x.getAmount()>0)
                 System.out.println(user.getName() + " owes " + x.getUserId() + " : " + x.getAmount());
            }
        }

    }

    public void showAll() {
        Boolean noBalance = true;
        for(User user : UserRepository.getUserList()){
            Map<String, Expense> lendMap = user.getLendToMap();
            for(Expense x: lendMap.values()){
                if(x.getAmount()>0){
                    System.out.println(user.getName() + " owes " + x.getUserId() + " : " + x.getAmount());
                    noBalance = false;
                }

            }
        }
        if(noBalance.equals(true)){
            System.out.println(Error.NOBALANCE);
            return;
        }

    }
}
