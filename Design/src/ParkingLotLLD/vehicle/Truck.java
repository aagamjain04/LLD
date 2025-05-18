package ParkingLotLLD.vehicle;

import ParkingLotLLD.parkingspot.ParkingSpotType;

public class Truck extends Vehicle{
    public Truck(String licenseNumber) {
        super(licenseNumber, VehicleType.TRUCK);
    }

    @Override
    public ParkingSpotType getSpotType() {
        return ParkingSpotType.TRUCK;
    }
}
