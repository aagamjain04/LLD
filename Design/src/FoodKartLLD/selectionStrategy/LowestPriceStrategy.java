package FoodKartLLD.selectionStrategy;

import FoodKartLLD.model.Restaurant;

import java.util.List;

public class LowestPriceStrategy implements SelectionStrategy{

    @Override
    public Restaurant findRestaurant(String item, List<Restaurant> restaurantList) {

        Restaurant best = null;
        int minPrice = Integer.MAX_VALUE;

        for(Restaurant res : restaurantList){
            if(res.hasItem(item)){
                int price = res.getPrice(item);
                if(price < minPrice){
                    minPrice = price;
                    best = res;
                }
            }
        }
        return best;
    }
}
