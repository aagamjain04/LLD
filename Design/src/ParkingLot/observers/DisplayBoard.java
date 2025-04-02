package ParkingLot.observers;

import ParkingLot.parking.ParkingSpotType;

import java.util.Map;

public class DisplayBoard implements Observer {

    private Map<ParkingSpotType,Integer> availableSpot;

    public void update(Map<ParkingSpotType,Integer> availableSpot,int floorNo) {
        this.availableSpot = availableSpot;
        display(floorNo);
    }

    public void display(int floorNo) {
        System.out.println("Available Parking Spots for Floor: "+floorNo);
        for (Map.Entry<ParkingSpotType, Integer> entry : availableSpot.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}
