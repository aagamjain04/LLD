package FlipAuctionLLD.model;

import java.util.HashMap;
import java.util.Map;

public class Auction {
    String id;
    public int low;
    public int high;
    public int participationCost;
    Seller seller;
    public int participants;

    public boolean closed;

    public Map<String,Bid> bids;

    public Auction(String id, int low, int high, int participationCost, Seller seller) {
        this.id = id;
        this.low = low;
        this.high = high;
        this.participationCost = participationCost;
        this.seller = seller;
        this.closed = false;
        bids = new HashMap<>();
        participants = 0;
    }

    public void addOrUpdateBid(Buyer buyer, int amount) {
        if (closed) throw new RuntimeException("Auction closed");

        if (amount < low || amount > high)
            throw new RuntimeException("Bid outside limits");

        if(!bids.containsKey(buyer.getName()))
            participants++;

        bids.put(buyer.name, new Bid(buyer, amount));
    }

    public void withdrawBid(String buyerName) {
        if (closed) throw new RuntimeException("Auction closed");
        bids.remove(buyerName);
    }


}
