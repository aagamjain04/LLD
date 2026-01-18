package FlipBnplLLD.service;

import FlipBnplLLD.model.Product;
import FlipBnplLLD.repository.InventoryRepository;

public class InventoryService {

    private final InventoryRepository db;


    public InventoryService(InventoryRepository db) {
        this.db = db;
    }


    public void seedInventory(String name, int stock, int price) {
        db.save(new Product(name, stock, price));
    }

    public void removeInventory(String name){
        if(db.find(name)!=null){
            db.remove(name);
        }
    }


    Product getProduct(String name) {
        Product p = db.find(name);
        if(p==null){
            System.out.println("No product found!");
            return null;
        }
        return db.find(name);
    }

    public void viewInventory() {
        System.out.println("\nInventory:");
        for (Product p : db.findAll()) {
            System.out.println(p.getName() + " " + p.getQuantity() + " " + p.getPrice());
        }
    }
}
