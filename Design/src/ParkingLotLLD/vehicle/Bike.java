package ParkingLotLLD.vehicle;

import ParkingLotLLD.parkingspot.ParkingSpotType;

public class Bike extends Vehicle{
    public Bike(String licenseNumber) {
        super(licenseNumber, VehicleType.MOTORCYCLE);
    }

    @Override
    public ParkingSpotType getSpotType() {
        return ParkingSpotType.MOTORCYCLE;
    }
}
