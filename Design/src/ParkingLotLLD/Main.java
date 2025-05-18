package ParkingLotLLD;

import ParkingLotLLD.factories.VehicleFactory;
import ParkingLotLLD.parking.ParkingLot;
import ParkingLotLLD.payment.PaymentMode;
import ParkingLotLLD.vehicle.Vehicle;
import ParkingLotLLD.vehicle.VehicleType;

public class Main {
    public static void main(String[] args) {


        ParkingLot parkingLot = ParkingLot.getInstance();

        parkingLot.display();


        Vehicle v1 = VehicleFactory.createVehicle(VehicleType.CAR,"1234");
        parkingLot.parkVehicle(v1);

        parkingLot.display();

        Vehicle v2 = VehicleFactory.createVehicle(VehicleType.CAR,"45343");
        parkingLot.parkVehicle(v2);

        Vehicle v3 = VehicleFactory.createVehicle(VehicleType.CAR,"46344");
        parkingLot.parkVehicle(v3);

        Vehicle v4 = VehicleFactory.createVehicle(VehicleType.MOTORCYCLE,"7655");
        parkingLot.parkVehicle(v4);

        parkingLot.display();

        parkingLot.removeVehicle(v1, PaymentMode.CARD);
        parkingLot.removeVehicle(v4,PaymentMode.CASH);
        parkingLot.display();







    }
}