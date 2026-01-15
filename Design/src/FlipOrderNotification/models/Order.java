package FlipOrderNotification.models;


public class Order {
    public String orderId;
    public String customerId;
    public String sellerId;
    public String deliveryId;

    public Order(String orderId, String customerId, String sellerId, String deliveryId) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.sellerId = sellerId;
        this.deliveryId = deliveryId;

    }



}
