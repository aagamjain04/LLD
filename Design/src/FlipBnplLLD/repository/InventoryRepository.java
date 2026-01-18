package FlipBnplLLD.repository;

import FlipBnplLLD.model.Product;

import java.util.Collection;

public interface InventoryRepository {
    void save(Product product);
    Product find(String name);
    Collection<Product> findAll();
    void remove(String name);
}
