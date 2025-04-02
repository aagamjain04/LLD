package ParkingLot.observers;

import ParkingLot.parking.ParkingSpot;
import ParkingLot.parking.ParkingSpotType;
import ParkingLot.vehicle.Vehicle;

import java.util.*;



import java.util.*;

public class ParkingFloor implements DisplayBoardObservable {
    private int floorNumber;
    private Map<ParkingSpotType, List<ParkingSpot>> spots;
    private List<DisplayBoard> observers;


    // Constructor with fixed slots for each spot type
    public ParkingFloor(int floorNumber, int compactSpots, int largeSpots, int motorcycleSpots, int handicappedSpots) {
        this.floorNumber = floorNumber;
        this.spots = new HashMap<>();
        this.observers = new ArrayList<>();

        initializeSpots(compactSpots, largeSpots, motorcycleSpots, handicappedSpots);

    }

    // Method to initialize slots with fixed counts
    private void initializeSpots(int compactSpots, int largeSpots, int motorcycleSpots, int handicappedSpots) {
        spots.put(ParkingSpotType.COMPACT, createSpots(ParkingSpotType.COMPACT, compactSpots));
        spots.put(ParkingSpotType.LARGE, createSpots(ParkingSpotType.LARGE, largeSpots));
        spots.put(ParkingSpotType.MOTORCYCLE, createSpots(ParkingSpotType.MOTORCYCLE, motorcycleSpots));
        spots.put(ParkingSpotType.HANDICAPPED, createSpots(ParkingSpotType.HANDICAPPED, handicappedSpots));
    }

    // Create a list of parking spots of a given type and count
    private List<ParkingSpot> createSpots(ParkingSpotType type, int count) {
        List<ParkingSpot> spotList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            spotList.add(new ParkingSpot(type));
        }
        return spotList;
    }

    // Park a vehicle in the appropriate spot
    public boolean parkVehicle(Vehicle vehicle) {
        List<ParkingSpot> availableSpots = spots.get(vehicle.getSpotType());
        if (availableSpots == null) {
            System.out.println("No available spot found for type: " + vehicle.getType());
            return false;
        }
        for (ParkingSpot spot : availableSpots) {
            if (spot.isFree()) {
                spot.assignVehicle(vehicle);
                notifyObservers(); // Notify observers when spot is occupied
                return true;
            }
        }
        return false; // No available spot found
    }

    public boolean removeVehicle(String licenseNumber) {
        for (List<ParkingSpot> spotList : spots.values()) {
            for (ParkingSpot spot : spotList) {
                if (!spot.isFree() && spot.getVehicle().getLicenseNumber().equals(licenseNumber)) {
                    spot.removeVehicle();
                    notifyObservers(); // Notify observers when vehicle is removed
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void addObserver(DisplayBoard observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(DisplayBoard observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        Map<ParkingSpotType, Integer> availableSpots = new HashMap<>();
        for (Map.Entry<ParkingSpotType, List<ParkingSpot>> entry : spots.entrySet()) {
            int freeSpots = (int) entry.getValue().stream().filter(ParkingSpot::isFree).count();
            availableSpots.put(entry.getKey(), freeSpots);
        }
        for (DisplayBoard observer : observers) {
            observer.update(availableSpots,this.floorNumber);
        }
    }

    public int getFloorNumber() {
        return floorNumber;
    }

}
