package ParkingLotLLD.displayboard;

import ParkingLotLLD.parkingspot.ParkingSpotType;

import java.util.HashMap;
import java.util.Map;

import static ParkingLotLLD.displayboard.Operation.FREE;
import static ParkingLotLLD.displayboard.Operation.RESERVE;

public class DisplayBoard {

    int floorNo;
    Map<ParkingSpotType,Integer> board;
    public DisplayBoard(int floorNo){
        this.floorNo = floorNo;
        this.board = new HashMap<>();
    }

    public Map<ParkingSpotType,Integer> getBoard(){
        return board;
    }

    public void updateDisplayBoard(ParkingSpotType spotType,String operation){
        if (!board.containsKey(spotType)) {
            System.out.println("Parking spot type not found on display board.");
            return;
        }
        Integer currentSpot = board.get(spotType);
        if(operation.equals(String.valueOf(RESERVE))){
            board.put(spotType,currentSpot-1);
        }else if(operation.equals(String.valueOf(FREE))){
            board.put(spotType,currentSpot+1);
        }
    }


}