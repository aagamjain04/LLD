package Splitwise.enums;

public enum ExpenseType {
    EQUAL("EQUAL"),
    EXACT("EXACT"),
    PERCENT("PERCENT");
    String s;
    ExpenseType(String s) {
        this.s = s;
    }
}
