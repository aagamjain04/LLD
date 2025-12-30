package CouponsLLD;

public class Item {

    private final String name;
    private final String category;
    private final Double price;

    public Item(String name, String category, Double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }
}
