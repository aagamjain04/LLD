package ParkingLotLLD.vehicle;

import ParkingLotLLD.parkingspot.ParkingSpotType;

public abstract class Vehicle {
    String licenseNumber;

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    VehicleType vehicleType;

    public Vehicle(String licenseNumber, VehicleType vehicleType) {
        this.licenseNumber = licenseNumber;
        this.vehicleType = vehicleType;
    }

    public abstract ParkingSpotType getSpotType();


}
