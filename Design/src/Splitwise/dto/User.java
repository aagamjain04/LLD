package Splitwise.dto;

import java.util.HashMap;
import java.util.Map;

public class User {


    String name;
    Map<String,Expense> lendToMap;

    public User(String name) {
        this.name = name;
        this.lendToMap = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Expense> getLendToMap() {
        return lendToMap;
    }

    public Expense getExpense(String user,Double amount){
        if(lendToMap.containsKey(user)){
            return lendToMap.get(user);
        }else return new Expense(user,amount);
    }

    public void setLendToMap(String user,Expense expense) {
        lendToMap.put(user,expense);
    }


}
