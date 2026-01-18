package FlipBnplLLD.repository;

import FlipBnplLLD.model.Order;

import java.util.HashMap;
import java.util.Map;

public class InMemoryOrderRepository implements OrderRepository{
    Map<String, Order> store = new HashMap<>(); //OrderId to Order

    public void save(Order order) {
        store.put(order.getOrderId(), order);
    }

    public Order find(String orderId) {
        return store.getOrDefault(orderId,null);
    }
}
