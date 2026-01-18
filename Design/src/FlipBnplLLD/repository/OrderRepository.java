package FlipBnplLLD.repository;

import FlipBnplLLD.model.Order;

public interface OrderRepository {
    void save(Order order);
    Order find(String orderId);
}
