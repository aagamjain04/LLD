package ParkingLot.parking;

import ParkingLot.vehicle.Vehicle;

public class ParkingSpot {

    private ParkingSpotType type;
    private Vehicle vehicle;
    private boolean isFree;

    public ParkingSpot(ParkingSpotType type) {
        this.type = type;
        this.isFree = true;
    }

    public boolean isFree() {
        return isFree;
    }

    public ParkingSpotType getType() {
        return type;
    }

    public void assignVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isFree = false;
    }

    public void removeVehicle() {
        this.vehicle = null;
        this.isFree = true;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

}
