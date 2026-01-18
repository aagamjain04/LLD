package FlipBnplLLD.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    String id;
    String name;
    int creditLimit;
    public int availableCredit;
    public List<Order> orders = new ArrayList<>();
    public boolean isBlacklisted;
    public int defaulted;

    public User(String id, String name, int creditLimit) {
        this.id = id;
        this.name = name;
        this.creditLimit = creditLimit;
        this.availableCredit = creditLimit;
        this.isBlacklisted = false;
        this.defaulted = 0;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCreditLimit() {
        return creditLimit;
    }

    public int getAvailableCredit() {
        return availableCredit;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
