package FlipBnplLLD.repository;

import FlipBnplLLD.model.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InMemoryInventoryRepository implements InventoryRepository{
    Map<String, Product> store = new HashMap<>(); //ProductName To Product

    public void save(Product product) {
        store.put(product.getName(), product);
    }

    public Product find(String name) {
        return store.getOrDefault(name,null);
    }

    public Collection<Product> findAll() {
        return store.values();
    }

    public void remove(String name) {
        store.remove(name);
    }
}
