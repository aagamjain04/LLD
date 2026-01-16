package FlipRideSharingLLD.service;

import FlipRideSharingLLD.model.Ride;
import FlipRideSharingLLD.model.User;
import FlipRideSharingLLD.model.Vehicle;
import FlipRideSharingLLD.selectionStrategy.RideSelectionStrategy;
import FoodKartLLD.selectionStrategy.SelectionStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RideSharingService {

    private Map<String, User> users = new HashMap<>();
    private Map<String,Vehicle> vehicleMap = new HashMap<>(); //regNo vs Vehicle
    private List<Vehicle> vehicleList = new ArrayList<>();
    private List<Ride> rides = new ArrayList<>();

    public void addUser(String name){
        User u = new User(name);
        users.putIfAbsent(name,u);
    }

    public void addVehicle(String name,String model,String regNo){
        Vehicle v = new Vehicle(name,model,regNo);
        vehicleList.add(v);
        vehicleMap.put(regNo,v);
    }
    // service.offerRide("Rohan", "Hyderabad", 1, "Swift", "KA-01-12345", "Bangalore");
    public void offerRide(String owner,String origin,int seat,String model,String regNo,String destination){

        Vehicle v = vehicleMap.getOrDefault(regNo,null);

        if(v==null || v.isHasActiveRide()){
            System.out.println("Offer Failed: Vehicle busy or not found for " + owner);
            return;
        }

        Ride r = new Ride(owner,origin,destination,seat,v);
        v.setHasActiveRide(true);
        rides.add(r);

    }

    //Ride r1 = service.selectRide("Nandini", "Bangalore", "Mysore", 1, new MostVacantSelectionStrategy());

    public synchronized Ride selectRide(String passengerName, String origin, String dest, int seats, RideSelectionStrategy strategy){

        // get rides which are available,has required seats, has same origin and destination

        List<Ride> matches = rides.stream().
                filter(r -> r.isActive() && r.getAvailableSeats()>=seats && r.getOrigin().equalsIgnoreCase(origin) && r.getDestination().equalsIgnoreCase(dest))
                .toList();

        if(matches.isEmpty()){
            System.out.println("No rides found for " + passengerName);
            return null;
        }

        Ride selected = strategy.selectRide(matches);

        if(selected==null){
            System.out.println("No rides found for " + passengerName);
            return null;
        }else{
            selected.availableSeats-=seats;
            users.get(passengerName).ridesTaken++;

            return selected;
        }



    }

    public void endRide(Ride r){
        if(r!=null){
            r.setActive(false);
            r.getVehicle().setHasActiveRide(false);
            users.get(r.getDriverName()).ridesOffered++;
        }
    }

    public void printRideStats() {
        users.forEach((name, user) ->
                System.out.println(name + ": " + user.ridesTaken + " Taken, " + user.ridesOffered + " Offered"));
    }


}
