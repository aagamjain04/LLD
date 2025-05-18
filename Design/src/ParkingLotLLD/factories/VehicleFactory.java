package ParkingLotLLD.factories;


import ParkingLotLLD.vehicle.*;


public class VehicleFactory {
    public static Vehicle createVehicle(VehicleType type, String licenseNumber){
        return switch (type) {
            case CAR -> new Car(licenseNumber);
            case TRUCK -> new Truck(licenseNumber);
            case MOTORCYCLE -> new Bike(licenseNumber);
            default -> throw new IllegalArgumentException("Invalid Vehicle Type");
        };
    }
}
