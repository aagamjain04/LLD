package FlipRideSharingLLD.model;

import java.util.UUID;

public class Ride {
    String id;
    String driverName;
    String origin;
    String destination;
    public int availableSeats;
    Vehicle vehicle;
    boolean isActive;

    public Ride(String driverName, String origin, String destination, int availableSeats, Vehicle vehicle) {
        this.id = UUID.randomUUID().toString();
        this.driverName = driverName;
        this.origin = origin;
        this.destination = destination;
        this.availableSeats = availableSeats;
        this.vehicle = vehicle;
        this.isActive = true;
    }

    public String getId() {
        return id;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean status){
        this.isActive = status;
    }
}
