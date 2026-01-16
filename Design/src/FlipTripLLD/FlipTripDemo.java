package FlipTripLLD;

import FlipTripLLD.model.Flight;
import FlipTripLLD.model.Route;
import FlipTripLLD.repository.FlightRepository;
import FlipTripLLD.service.SearchService;



public class FlipTripDemo {
    public static void main(String[] args) {
        FlightRepository repo = new FlightRepository();

        // Register flights (sample data)
        repo.addFlight(new Flight("JetAir","DEL","BLR",500,false));
        repo.addFlight(new Flight("JetAir","BLR","LON",1000,false));
        repo.addFlight(new Flight("Delta","DEL","LON",2000,false));
        repo.addFlight(new Flight("Delta","LON","NYC",2000,false));
        repo.addFlight(new Flight("IndiGo","LON","NYC",2500,true));
        repo.addFlight(new Flight("IndiGo","DEL","BLR",600,true));
        repo.addFlight(new Flight("IndiGo","BLR","PAR",800,true));
        repo.addFlight(new Flight("IndiGo","PAR","LON",300,true));

        SearchService service = new SearchService(repo);

        System.out.println("====== WITHOUT MEAL FILTER ======");



        Route minHops = service.findMinHops("DEL","NYC", false);
        System.out.println("* Route with Minimum Hops:");
        minHops.print();

        Route cheapest = service.findCheapest("DEL","NYC", false);
        System.out.println("* Cheapest Route:");
        cheapest.print();


        System.out.println("\n====== WITH MEAL FILTER ======");



        Route minHopsMeal = service.findMinHops("DEL","NYC", true);
        System.out.println("* Route with Minimum Hops:");
        minHopsMeal.print();

        Route cheapestMeal = service.findCheapest("DEL","NYC", true);
        System.out.println("* Cheapest Route:");
        cheapestMeal.print();
    }
}
