package ParkingLotLLD.parking;

import ParkingLotLLD.displayboard.DisplayBoard;
import ParkingLotLLD.parkingspot.ParkingSpot;
import ParkingLotLLD.parkingspot.ParkingSpotType;
import ParkingLotLLD.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ParkingLotLLD.displayboard.Operation.FREE;
import static ParkingLotLLD.displayboard.Operation.RESERVE;

public class ParkingFloor {

    static final int MOTORCYCLE_SPOTS = 200;
    static final int CAR_SPOTS = 100;
    static final int TRUCK_SPOTS = 50;


    DisplayBoard displayboard;
    int floorNumber;
    Map<ParkingSpotType, List<ParkingSpot>> spots;

    ParkingFloor(int floorNumber){
        this.floorNumber = floorNumber;
        this.displayboard = new DisplayBoard(floorNumber);
        this.spots = new HashMap<>();
        initSpots();
    }

    private void initSpots(){
        // 200 MOTORCYCLE, 100 CAR , 50 TRUCK
        spots.put(ParkingSpotType.MOTORCYCLE,createSpots(ParkingSpotType.MOTORCYCLE,MOTORCYCLE_SPOTS));
        spots.put(ParkingSpotType.CAR,createSpots(ParkingSpotType.CAR,CAR_SPOTS));
        spots.put(ParkingSpotType.TRUCK,createSpots(ParkingSpotType.TRUCK,TRUCK_SPOTS));

        Map<ParkingSpotType,Integer> board = displayboard.getBoard();
        board.put(ParkingSpotType.MOTORCYCLE,MOTORCYCLE_SPOTS);
        board.put(ParkingSpotType.CAR,CAR_SPOTS);
        board.put(ParkingSpotType.TRUCK,TRUCK_SPOTS);

    }

    private List<ParkingSpot> createSpots(ParkingSpotType parkingSpotType, int spots){
        List<ParkingSpot> parkingSpots = new ArrayList<>();
        for(int i=0;i<spots;i++){
            parkingSpots.add(new ParkingSpot(parkingSpotType));
        }
        return parkingSpots;
    }

    public Boolean canPark(Vehicle vehicle){
        List<ParkingSpot> availableSpots = spots.get(vehicle.getSpotType());
        for(ParkingSpot spot : availableSpots){
            if(spot.isFree()){
                spot.assign(vehicle);
                displayboard.updateDisplayBoard(vehicle.getSpotType(), String.valueOf(RESERVE));
                return true;
            }
        }
        return false;
    }

    public Boolean removePark(Vehicle vehicle){
        List<ParkingSpot> availableSpots = spots.get(vehicle.getSpotType());
        String licenseNumber = vehicle.getLicenseNumber();
        for(ParkingSpot spot : availableSpots){
            if(!spot.isFree() && spot.getVehicle().getLicenseNumber().equals(licenseNumber)){
                spot.remove(vehicle);
                displayboard.updateDisplayBoard(vehicle.getSpotType(), String.valueOf(FREE));
                return true;
            }
        }
        return false;
    }




}
