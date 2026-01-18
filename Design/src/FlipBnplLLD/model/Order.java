package FlipBnplLLD.model;

import java.time.LocalDate;
import java.util.List;

public class Order {
    String orderId;
    public LocalDate purchaseDate;
    List<OrderItem> items;
    int totalAmount;
    boolean isPaid;

    public Order(String orderId, LocalDate purchaseDate, List<OrderItem> items, int totalAmount) {
        this.orderId = orderId;
        this.purchaseDate = purchaseDate;
        this.items = items;
        this.totalAmount = totalAmount;
        this.isPaid = false;
    }

    public String getOrderId() {
        return orderId;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public boolean isPaid() {
        return isPaid;
    }
    public void setIsPaid(boolean status){
        this.isPaid = status;
    }
}
