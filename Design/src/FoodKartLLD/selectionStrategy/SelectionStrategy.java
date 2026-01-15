package FoodKartLLD.selectionStrategy;

import FoodKartLLD.model.Restaurant;

import java.util.List;

public interface SelectionStrategy {
    Restaurant findRestaurant(String item, List<Restaurant> restaurantList);
}
