package ParkingLot.observers;

import ParkingLot.parking.ParkingSpotType;

import java.util.Map;

public interface Observer {
    void update(Map<ParkingSpotType,Integer> availableSpot,int floor);
}
