package FoodKartLLD.model;

import java.util.Map;

public class Order {
    String orderId;
    String customerId;
    Map<Restaurant,Integer> fulfillment;
    Map<String, Integer> items; // item name -> quantity
    int cost;
    boolean isDelivered;

    public Order(String orderId, String customerId, Map<Restaurant,Integer> fulfillment, Map<String, Integer> items){
        this.orderId = orderId;
        this.customerId = customerId;
        this.fulfillment = fulfillment;
        this.items = items;
        this.isDelivered = false;
        this.cost = 0;
    }

    public String getId() { return orderId; }
    public String getCustomerId() { return customerId; }
    public Map<Restaurant, Integer> getFulfillment() { return fulfillment; }
    public Map<String, Integer> getItems() { return items; }
    public boolean isDelivered() { return isDelivered; }
    public void setDelivered(boolean delivered) { isDelivered = delivered; }
    public int getCost() { return cost; }
    public void setCost(int cost) { this.cost = cost; }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", items=" + items +
                ", cost=" + cost +
                ", isDelivered=" + isDelivered +
                '}';
    }
}
