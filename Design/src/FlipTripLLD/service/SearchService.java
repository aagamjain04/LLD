package FlipTripLLD.service;

import FlipTripLLD.filter.FlightFilter;
import FlipTripLLD.model.Flight;
import FlipTripLLD.model.Route;
import FlipTripLLD.repository.FlightRepository;

import java.util.*;

public class SearchService {

    private final FlightRepository repository;

    public SearchService(FlightRepository repository) {
        this.repository = repository;
    }

    public Route findMinHops(String src, String dest, boolean mealRequired){

        // do bfs

        Route best = null;
        int bestCost = Integer.MAX_VALUE;
        Queue<Route> q = new LinkedList<>();
        q.offer(new Route());

        Map<Route,String> lastCity = new HashMap<>();
        lastCity.put(q.peek(),src);

        while(!q.isEmpty()){

            Route r = q.poll();
            String city = lastCity.get(r);

            if(city.equals(dest)){
                if(best == null || r.getTotalCost()<bestCost){
                    best = r;
                    bestCost = r.getTotalCost();
                }
            }


            for(Flight f : repository.getFlights(city)){
                if(f.isHasMeal() != mealRequired){
                    continue;
                }

                Route nr = new Route(r);
                nr.addFlight(f);
                lastCity.put(nr,f.getDest());
                q.offer(nr);
            }
        }
        return best;
    }

    public Route findCheapest(String src,String dest, boolean mealRequired){
        class Node {
            String city;
            Route route;
            Node(String c, Route r){ city=c; route=r; }
        }

        PriorityQueue<Node> pq =
                new PriorityQueue<>(Comparator.comparingInt(a -> a.route.getTotalCost()));

        pq.add(new Node(src, new Route()));
        Map<String, Integer> bestCost = new HashMap<>();

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.city.equals(dest)) return cur.route;

            if (bestCost.containsKey(cur.city) &&
                    bestCost.get(cur.city) <= cur.route.getTotalCost()) continue;

            bestCost.put(cur.city, cur.route.getTotalCost());

            for (Flight f : repository.getFlights(cur.city)) {
                if (f.isHasMeal() != mealRequired) continue;

                Route nr = new Route(cur.route);
                nr.addFlight(f);
                pq.add(new Node(f.getDest(), nr));
            }
        }
        return null;
    }
}
