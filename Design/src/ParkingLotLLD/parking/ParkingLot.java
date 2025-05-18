package ParkingLotLLD.parking;

import ParkingLotLLD.displayboard.DisplayBoard;
import ParkingLotLLD.parkingspot.ParkingSpotType;
import ParkingLotLLD.payment.PaymentMode;
import ParkingLotLLD.payment.ProcessPayment;
import ParkingLotLLD.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParkingLot {

    private static ParkingLot instance;
    private static int FLOORS = 2;
    List<ParkingFloor> floors;




    private ParkingLot() {
        this.floors = new ArrayList<>();

        for(int i=0;i<FLOORS;i++){
            floors.add(new ParkingFloor(i+1));
        }

        System.out.println("\nParking Lot with " + FLOORS + " floors is initialised");

    }

    //singleton instance getter
    public static synchronized ParkingLot getInstance(){
        if(instance==null){
            return new ParkingLot();
        }
        return instance;
    }

    //display board
    public void display(){
        for(int i=0;i<floors.size();i++){
            System.out.println("Available Parking Space for floor: "+ (i+1));
            DisplayBoard board = floors.get(i).displayboard;

            for(Map.Entry<ParkingSpotType,Integer> entry : board.getBoard().entrySet()){
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
        System.out.println(" ");
    }

    // park vehicle
    public void parkVehicle(Vehicle vehicle){
        for(int i=0;i<floors.size();i++){
            ParkingFloor f = floors.get(i);
            if(f.canPark(vehicle)){
                System.out.println("Vehicle of type " + vehicle.getVehicleType() + " and license number " + vehicle.getLicenseNumber() + " Parked on floor " + (i+1) +"\n");
                return;
            }
        }
        System.out.println("No parking space available!!\n");
    }

    // remove vehicle
    public void removeVehicle(Vehicle vehicle, PaymentMode mode){
        for(int i=0;i<floors.size();i++){
            ParkingFloor f = floors.get(i);
            if(f.removePark(vehicle)){
                System.out.println("Vehicle of type " + vehicle.getVehicleType() + " and license number " + vehicle.getLicenseNumber() + " Removed from floor " + (i+1));
                ProcessPayment processPayment = new ProcessPayment(mode,200);
                return;
            }
        }
        System.out.println("Not able to remove from parking\n");
    }

}
