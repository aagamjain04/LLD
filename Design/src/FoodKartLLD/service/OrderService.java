package FoodKartLLD.service;

import CouponsLLD.Item;
import FoodKartLLD.model.Order;
import FoodKartLLD.model.Restaurant;
import FoodKartLLD.repository.RestaurantRepository;
import FoodKartLLD.selectionStrategy.SelectionStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OrderService {
    RestaurantRepository repository;
    SelectionStrategy selectionStrategy;
    Map<String, Order> orderStorage = new HashMap<>();

    public OrderService(RestaurantRepository repository, SelectionStrategy selectionStrategy) {
        this.repository = repository;
        this.selectionStrategy = selectionStrategy;
    }

    public void addRestaurant(String name,int capacity){
        Restaurant r1 = new Restaurant(name,capacity);
        repository.addRestaurant(r1);
    }

    public void updateMenu(String name,Map<String, Integer> menu){

        Restaurant r = repository.getRestaurant(name);
        r.updateMenu(menu);

    }

    public void printAllRestaurant(){
        for(Restaurant r : repository.getAll()){
            System.out.println(r.toString());
        }
    }

    public String book(String customerId, Map<String, Integer> items) {
        // Use selection strategy to find the best restaurant that can fulfill all items
        Restaurant selectedRestaurant = null;

        // Try to find a single restaurant that has all items (using the first item to start)
        if (!items.isEmpty()) {
            String firstItem = items.keySet().iterator().next();
            selectedRestaurant = selectionStrategy.findRestaurant(firstItem, repository.getAll());

            // Verify this restaurant has all requested items
            if (selectedRestaurant != null) {
                for (String item : items.keySet()) {
                    if (!selectedRestaurant.hasItem(item)) {
                        selectedRestaurant = null;
                        break;
                    }
                }
            }
        }

        if (selectedRestaurant == null) {
            System.out.println("No restaurant available for this order");
            return null;
        }

        // Check if restaurant has capacity
        int totalItems = items.values().stream().mapToInt(Integer::intValue).sum();
        if (!selectedRestaurant.canAccept(totalItems)) {
            System.out.println("Restaurant does not have capacity");
            return null;
        }

        // Calculate cost
        int totalCost = 0;
        for (Map.Entry<String, Integer> item : items.entrySet()) {
            totalCost += selectedRestaurant.getPrice(item.getKey()) * item.getValue();
        }

        // Create order
        String orderId = "order_" + UUID.randomUUID().toString().substring(0, 8);
        Map<Restaurant, Integer> fulfillment = new HashMap<>();
        fulfillment.put(selectedRestaurant, totalItems);

        Order order = new Order(orderId, customerId, fulfillment, items);
        order.setCost(totalCost);

        // Reserve capacity at restaurant
        selectedRestaurant.reserve(totalItems);

        // Store order
        orderStorage.put(orderId, order);

        System.out.println("Order placed: " + orderId + " at restaurant: " + selectedRestaurant.getName());
        return orderId;
    }

    public void printAllOrders() {
        System.out.println("\n--- All Orders ---");
        for (Order order : orderStorage.values()) {
            System.out.println(order.toString());
        }
    }

    public void markAsDelivered(String orderId) {
        Order order = orderStorage.get(orderId);
        if (order != null) {
            order.setDelivered(true);
            // Release capacity at all restaurants
            for (Map.Entry<Restaurant, Integer> entry : order.getFulfillment().entrySet()) {
                entry.getKey().release(entry.getValue());
            }
            System.out.println("Order marked as delivered: " + orderId);
        } else {
            System.out.println("Order not found: " + orderId);
        }
    }
}
