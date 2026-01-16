package FlipAuctionLLD.service;

import FlipAuctionLLD.model.Auction;
import FlipAuctionLLD.model.Bid;
import FlipAuctionLLD.model.Buyer;
import FlipAuctionLLD.model.Seller;
import FlipAuctionLLD.repository.InMemoryUserDb;

import java.util.HashMap;
import java.util.Map;

public class AuctionService {
    private final InMemoryUserDb db;
    private final Map<String,Auction> auctions = new HashMap<>();

    public AuctionService(InMemoryUserDb db) {
        this.db = db;
    }

    public void createAuction(String id,int low,int high,int fee,String sellerName){
        Seller seller = db.getSeller(sellerName);
        if (seller == null) throw new RuntimeException("Seller not found");
        Auction a = new Auction(id,low,high,fee,seller);
        auctions.put(id,a);
    }

    public void createOrUpdateBid(String buyer,String sellerId,int amount){

        Buyer b = db.getBuyer(buyer);
        Auction a = auctions.get(sellerId);

        if (buyer == null || a == null)
            throw new RuntimeException("Invalid buyer or auction");

        a.addOrUpdateBid(b,amount);

    }

    public Buyer closeAuction(String id){

        Auction a = auctions.get(id);
        if (a == null) throw new RuntimeException("Auction not found");

        a.closed = true;
        return findHighestUniqueBidder(a);

    }

    public void withdrawBid(String buyerName, String auctionId) {
        Auction auction = auctions.get(auctionId);
        if (auction == null) throw new RuntimeException("Auction not found");
        auction.withdrawBid(buyerName);
    }

    private Buyer findHighestUniqueBidder(Auction auction) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (Bid b : auction.bids.values())
            freq.put(b.getAmount(), freq.getOrDefault(b.getAmount(), 0) + 1);

        int best = -1;
        for (Bid b : auction.bids.values()) {
            if (freq.get(b.getAmount()) == 1 && b.getAmount() > best)
                best = b.getAmount();
        }

        if (best == -1) return null;

        for (Bid b : auction.bids.values())
            if (b.getAmount() == best) return b.getBuyer();

        return null;
    }

    public  double getProfit(String sellerName, String auctionId) {
        Auction auction = auctions.get(auctionId);
        if (auction == null || !auction.closed)
            throw new RuntimeException("Auction invalid or not closed");

        int bidders = auction.participants;
        double participationShare = bidders * 0.2 * auction.participationCost;

        Buyer winner = findHighestUniqueBidder(auction);

        if (winner == null)
            return participationShare;

        int winningPrice = auction.bids.get(winner.getName()).getAmount();
        double avgLimit = (auction.low + auction.high) / 2.0;

        return winningPrice + participationShare - avgLimit;
    }
}
