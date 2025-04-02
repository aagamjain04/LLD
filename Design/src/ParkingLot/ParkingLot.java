package ParkingLot;

import ParkingLot.observers.DisplayBoard;
import ParkingLot.observers.ParkingFloor;
import ParkingLot.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private static ParkingLot instance;
    private List<ParkingFloor> floors;
    private List<DisplayBoard> displayBoards;

    // Private constructor to enforce Singleton
    private ParkingLot(int numberOfFloors, int compactSpots, int largeSpots, int motorcycleSpots, int handicappedSpots) {
        this.floors = new ArrayList<>();
        this.displayBoards = new ArrayList<>();
        for (int i = 0; i < numberOfFloors; i++) {
            floors.add(new ParkingFloor(i + 1, compactSpots, largeSpots, motorcycleSpots, handicappedSpots));
            displayBoards.add(new DisplayBoard());
        }
    }

    // Singleton instance getter
    public static synchronized ParkingLot getInstance(int numberOfFloors, int compactSpots, int largeSpots, int motorcycleSpots, int handicappedSpots) {
        if (instance == null) {
            instance = new ParkingLot(numberOfFloors, compactSpots, largeSpots, motorcycleSpots, handicappedSpots);
        }
        return instance;
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (ParkingFloor floor : floors) {
            if (floor.parkVehicle(vehicle)) {
                System.out.println("Vehicle parked on Floor: " + floor.getFloorNumber());
                return true;
            }
        }
        System.out.println("No available spot for vehicle: " + vehicle.getLicenseNumber());
        return false;
    }

    public boolean removeVehicle(String licenseNumber) {
        for (ParkingFloor floor : floors) {
            if (floor.removeVehicle(licenseNumber)) {
                System.out.println("Vehicle removed from Floor: " + floor.getFloorNumber());
                return true;
            }
        }
        System.out.println("Vehicle with license number " + licenseNumber + " not found.");
        return false;
    }

    public ParkingFloor getFloor(int index) {
        if (index >= 0 && index < floors.size()) {
            return floors.get(index);
        }
        return null;
    }
    public DisplayBoard getDisplayBoard (int index) {
        if (index >= 0 && index < floors.size()) {
            return displayBoards.get(index);
        }
        return null;
    }
}
