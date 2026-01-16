package FlipAuctionLLD;

import FlipAuctionLLD.model.Buyer;
import FlipAuctionLLD.repository.InMemoryUserDb;
import FlipAuctionLLD.service.AuctionService;

public class AuctionDemo {
    public static void main(String[] args) {

        InMemoryUserDb db = new InMemoryUserDb();
        AuctionService service = new AuctionService(db);

        db.addBuyer("buyer1");
        db.addBuyer("buyer2");
        db.addBuyer("buyer3");
        db.addSeller("seller1");

        service.createAuction("A1",10,50,1,"seller1");

        service.createOrUpdateBid("buyer1","A1",17);
        service.createOrUpdateBid("buyer2","A1",15);
        service.createOrUpdateBid("buyer2","A1",19);
        service.createOrUpdateBid("buyer3","A1",19);

        Buyer winner1 = service.closeAuction("A1");
        System.out.println("Winner A1: " + (winner1==null?"None":winner1.getName()));

        double profit1 = service.getProfit("seller1","A1");
        System.out.println("Profit A1: " + profit1);


        // Test Case 2
        db.addSeller("seller2");

        service.createAuction("A2",5,20,2,"seller2");

        try {
            service.createOrUpdateBid("buyer3","A2",25);
        } catch (Exception e) {
            System.out.println("Expected failure: " + e.getMessage());
        }

        service.createOrUpdateBid("buyer2","A2",5);
        service.withdrawBid("buyer2","A2");

        Buyer winner2 = service.closeAuction("A2");
        System.out.println("Winner A2: " + (winner2==null?"None":winner2.getName()));

        double profit2 = service.getProfit("seller2","A2");
        System.out.println("Profit A2: " + profit2);

    }
}
