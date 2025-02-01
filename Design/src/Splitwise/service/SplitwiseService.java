package Splitwise.service;

import Splitwise.constants.Commands;
import Splitwise.dto.Expense;
import Splitwise.dto.User;
import Splitwise.enums.ExpenseType;
import Splitwise.service.Split.Split;

import java.util.ArrayList;
import java.util.List;

public class SplitwiseService {
    public void show(String[] inp) {
        ShowService showService = new ShowService();
        if(inp.length > 1){
            showService.showById(inp[1]);
        }else {
            showService.showAll();
        }
    }

    public void splitExpense(String[] inp) {

        String user = inp[1];
        Double amount = Double.parseDouble(inp[2]);
        int memberCount = Integer.parseInt(inp[3]);
        List<String> members = new ArrayList<>();
        for(int i=0;i<memberCount;i++)
            members.add(inp[4+i]);

        String expenseType =  inp[4+memberCount];
        int k = 4+memberCount+1;
        List<String> shares = new ArrayList<>();
        for(int i=k;i<inp.length ;i++)
            shares.add(inp[i]);

        Split splitObject = SplitFactory.getSplitObj(ExpenseType.valueOf(expenseType));
        splitObject.process(user,amount,members,shares);




    }
}
