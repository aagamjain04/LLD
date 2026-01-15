package FoodKartLLD;

import FoodKartLLD.model.Restaurant;
import FoodKartLLD.repository.RestaurantRepository;
import FoodKartLLD.selectionStrategy.LowestPriceStrategy;
import FoodKartLLD.selectionStrategy.SelectionStrategy;
import FoodKartLLD.service.OrderService;

import java.util.Map;

public class FoodKartDemo {
    public static void main(String[] args) {
        RestaurantRepository repository = new RestaurantRepository();
        SelectionStrategy selectionStrategy = new LowestPriceStrategy();
        OrderService orderService = new OrderService(repository,selectionStrategy);



        orderService.addRestaurant("resta1",15);
        orderService.updateMenu("resta1",Map.of("king_burger", 10, "samosa_pizza", 10, "alu_pasta", 19));



        orderService.addRestaurant("resta2",12);
        orderService.updateMenu("resta2",Map.of("king_burger", 10, "samosa_pizza", 20, "alu_pasta", 19));



        orderService.updateMenu("resta1",Map.of("bendi_macaroni", 8, "king_burger", 15));

        orderService.printAllRestaurant();

        // TODO implementation
        // Book order with customer 'cust1' and items
        String order1 = orderService.book("cust1", Map.of("bendi_macaroni", 3, "samosa_pizza", 2));

        // Print all restaurant details
        System.out.println("\n--- Restaurant Details After Booking ---");
        orderService.printAllRestaurant();

        // Print all orders placed
        orderService.printAllOrders();

        // Mark order as delivered
        orderService.markAsDelivered(order1);

        // Print all restaurant details after delivery (capacity should be released)
        System.out.println("\n--- Restaurant Details After Delivery ---");
        orderService.printAllRestaurant();



    }
}
