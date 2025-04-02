package ParkingLot.vehicle;

import ParkingLot.parking.ParkingSpotType;

public class Motorcycle extends Vehicle {
    public Motorcycle(String licenseNumber) {
        super(licenseNumber, VehicleType.MOTORCYCLE);
    }

    @Override
    public ParkingSpotType getSpotType() {
        return ParkingSpotType.MOTORCYCLE;
    }
}
