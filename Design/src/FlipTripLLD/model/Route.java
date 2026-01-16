package FlipTripLLD.model;

import java.util.ArrayList;
import java.util.List;

public class Route {
    List<Flight> flights;
    int totalCost;

    public Route(){
        flights = new ArrayList<>();
        totalCost = 0;
    }

    public Route(Route other) {
        this.flights = new ArrayList<>(other.flights);
        this.totalCost = other.totalCost;
    }

    public void addFlight(Flight f){
        flights.add(f);
        totalCost+=f.getPrice();
    }

    public int hops(){
        return flights.size();
    }

    public void print() {
        for (Flight f : flights) {
            System.out.println(f.src + " to " + f.dest + " via " + f.airline + " for " + f.price);
        }
        System.out.println("Total Flights = " + hops());
        System.out.println("Total Cost = " + totalCost);

    }

    public int getTotalCost() {
        return totalCost;
    }
}
