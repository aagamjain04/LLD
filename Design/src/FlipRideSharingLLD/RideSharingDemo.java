package FlipRideSharingLLD;

import FlipRideSharingLLD.model.Ride;
import FlipRideSharingLLD.selectionStrategy.MostVacantSelectionStrategy;
import FlipRideSharingLLD.selectionStrategy.PreferredSelectionStrategy;
import FlipRideSharingLLD.service.RideSharingService;

public class RideSharingDemo {
    public static void main(String[] args) {

        RideSharingService service = new RideSharingService();

        //Onboarding

        service.addUser("Rohan"); service.addVehicle("Rohan", "Swift", "KA-01-12345");
        service.addUser("Shipra"); service.addVehicle("Shipra", "Polo", "KA-05-41491");
        service.addVehicle("Shipra", "Activa", "KA-12-12332");
        service.addUser("Nandini"); service.addUser("Gaurav"); service.addUser("Shashank");
        service.addVehicle("Shashank", "Baleno", "TS-05-62395");
        service.addUser("Rahul"); service.addVehicle("Rahul", "XUV", "KA-05-1234");

        // Offering Rides
        service.offerRide("Rohan", "Hyderabad", 1, "Swift", "KA-01-12345", "Bangalore");
        service.offerRide("Shipra", "Bangalore", 1, "Activa", "KA-12-12332", "Mysore");
        service.offerRide("Shipra", "Bangalore", 2, "Polo", "KA-05-41491", "Mysore");
        service.offerRide("Shashank", "Hyderabad", 2, "Baleno", "TS-05-62395", "Bangalore");
        service.offerRide("Rahul", "Hyderabad", 5, "XUV", "KA-05-1234", "Bangalore");

        // Duplicate offer fail check
        service.offerRide("Rohan", "Bangalore", 1, "Swift", "KA-01-12345", "Pune");

        // Selecting Rides
        Ride r1 = service.selectRide("Nandini", "Bangalore", "Mysore", 1, new MostVacantSelectionStrategy());
        Ride r2 = service.selectRide("Gaurav", "Bangalore", "Mysore", 1, new PreferredSelectionStrategy("Activa"));
        Ride r3 = service.selectRide("Shashank", "Mumbai", "Bangalore", 1, new MostVacantSelectionStrategy());
        Ride r4 = service.selectRide("Rohan", "Hyderabad", "Bangalore", 1, new PreferredSelectionStrategy("Baleno"));

        // End Rides
        service.endRide(r1); service.endRide(r2); service.endRide(r4);
        // Note: r3 was null, and we didn't end Rohan's initial offered ride if not selected, 
        // but stats count offered rides that ended.

        service.printRideStats();
    }
}
