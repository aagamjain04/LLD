package ParkingLot.factories;

import ParkingLot.vehicle.VehicleType;
import ParkingLot.vehicle.Car;
import ParkingLot.vehicle.Motorcycle;
import ParkingLot.vehicle.Truck;
import ParkingLot.vehicle.Vehicle;

public class VehicleFactory {
    public static Vehicle createVehicle(VehicleType type, String licenseNumber) {
        switch (type) {
            case CAR:
                return new Car(licenseNumber);
            case TRUCK:
                return new Truck(licenseNumber);
            case MOTORCYCLE:
                return new Motorcycle(licenseNumber);
            default:
                throw new IllegalArgumentException("Invalid Vehicle Type");
        }
    }
}