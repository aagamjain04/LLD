package FlipTripLLD.repository;

import FlipTripLLD.model.Flight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightRepository {

    private Map<String, List<Flight>> graph = new HashMap<>(); // Source vs Flight

    public void addFlight(Flight f){
        if(!graph.containsKey(f.getSrc())){
            graph.put(f.getSrc(), new ArrayList<>());
        }
        graph.get(f.getSrc()).add(f);
    }

    public List<Flight> getFlights(String source){
        return graph.getOrDefault(source,List.of());
    }
}
