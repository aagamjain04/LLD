package FlipAuctionLLD.repository;

import FlipAuctionLLD.model.Buyer;
import FlipAuctionLLD.model.Seller;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDb {

    private Map<String, Seller> sellers = new HashMap<>();
    private Map<String, Buyer> buyers = new HashMap<>();

    public void addBuyer(String name){
        Buyer b = new Buyer(name);
        buyers.put(name,b);
    }

    public void addSeller(String name) {
        Seller s = new Seller(name);
        sellers.put(name, s);

    }

    public Buyer getBuyer(String name) { return buyers.get(name); }
    public Seller getSeller(String name) { return sellers.get(name); }



}
