package CouponsLLD;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private final List<Item> items = new ArrayList<>();

    public void addItem(Item item){
        items.add(item);
    }

    public List<Item> getItems(){
        return items;
    }

    public double getTotalAmount(){
        return items.stream()
                .mapToDouble(Item::getPrice)
                .sum();
    }

}
