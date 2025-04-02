package ParkingLot.vehicle;

import ParkingLot.parking.ParkingSpotType;

public class Truck extends Vehicle{
    public Truck(String licenseNumber) {
        super(licenseNumber, VehicleType.TRUCK);
    }

    @Override
    public ParkingSpotType getSpotType() {
        return ParkingSpotType.LARGE;
    }
}
