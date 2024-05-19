package Splitwise.service;

import Splitwise.enums.ExpenseType;
import Splitwise.service.Split.Split;
import Splitwise.service.Split.SplitEqually;
import Splitwise.service.Split.SplitExact;
import Splitwise.service.Split.SplitPercentage;

public class SplitFactory {

    public static Split getSplitObj(ExpenseType expenseType){

        return switch (expenseType) {
            case ExpenseType.EQUAL -> new SplitEqually();
            case ExpenseType.EXACT -> new SplitExact();
            case ExpenseType.PERCENT -> new SplitPercentage();
            default -> null;
        };
    }
}
