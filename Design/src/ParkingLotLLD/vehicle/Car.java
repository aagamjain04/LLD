package ParkingLotLLD.vehicle;

import ParkingLotLLD.parkingspot.ParkingSpotType;

public class Car extends Vehicle{

    public Car(String licenseNumber) {
        super(licenseNumber,VehicleType.CAR);
    }

    @Override
    public ParkingSpotType getSpotType() {
        return ParkingSpotType.CAR;
    }
}
