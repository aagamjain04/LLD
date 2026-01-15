package FoodKartLLD.repository;

import FoodKartLLD.model.Restaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantRepository {
    private Map<String, Restaurant> storage = new HashMap<>();
    public void addRestaurant(Restaurant r) {
        storage.put(r.getName(), r);
    }

    public Restaurant getRestaurant(String name) {
        return storage.get(name);
    }

    public List<Restaurant> getAll() {
        return new ArrayList<>(storage.values());
    }

}
