package ParkingLot.factories;

import ParkingLot.parking.HandicappedSpot;
import ParkingLot.parking.*;
import ParkingLot.parking.ParkingSpotType;


public class ParkingSpotFactory {
    public static ParkingSpot createSpot(ParkingSpotType type, int number){
        switch (type){
            case HANDICAPPED:
                return new HandicappedSpot();
            case COMPACT:
                return new CompactSpot();
            case LARGE:
                return new LargeSpot();
            case MOTORCYCLE:
                return new MotorcycleSpot();
            default:
                throw new IllegalArgumentException("Invalid Spot Type");
        }
    }
}
