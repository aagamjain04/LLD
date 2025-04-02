package ParkingLot.vehicle;

import ParkingLot.parking.ParkingSpotType;

public abstract class Vehicle {
    private String licenseNumber;
    private VehicleType type;

    public Vehicle(String licenseNumber, VehicleType type) {
        this.licenseNumber = licenseNumber;
        this.type = type;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public VehicleType getType() {
        return type;
    }
    // Abstract method to be implemented by subclasses
    public abstract ParkingSpotType getSpotType();
}