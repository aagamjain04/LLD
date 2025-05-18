package ParkingLotLLD.parkingspot;

import ParkingLotLLD.vehicle.Vehicle;

public class ParkingSpot {

    Boolean isFree;
    ParkingSpotType parkingSpotType;
    Vehicle vehicle;

    public ParkingSpot(ParkingSpotType parkingSpotType) {
        this.parkingSpotType = parkingSpotType;
        this.isFree = true;
        this.vehicle = null;
    }

    public Boolean isFree() {
        return isFree;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpotType getParkingSpotType() {
        return parkingSpotType;
    }

    public void assign(Vehicle vehicle){
        this.isFree = false;
        this.vehicle = vehicle;
    }

    public void remove(Vehicle vehicle){
        this.isFree = true;
        this.vehicle = null;
    }




}
