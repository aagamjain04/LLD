package FlipBnplLLD.model;

public class OrderItem {
    String productName;
    int quantity;
    int price;

    public OrderItem(String productName, int quantity, int price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }


}
