package ParkingLot;

import ParkingLot.factories.VehicleFactory;
import ParkingLot.observers.DisplayBoard;
import ParkingLot.observers.ParkingFloor;
import ParkingLot.payment.CashPayment;
import ParkingLot.payment.PaymentStrategy;
import ParkingLot.payment.ProcessPayment;
import ParkingLot.vehicle.*;

public class Main {
    public static void main(String[] args) {
        // Initialize Parking Lot with fixed slots per floor
        ParkingLot parkingLot = ParkingLot.getInstance(
                3, // Number of floors
                10, // Compact Spots per floor
                5,  // Large Spots per floor
                5,  // Motorcycle Spots per floor
                2   // Handicapped Spots per floor
        );

        System.out.println("Parking Lot Initialized with 3 floors.");



        // Create and add display boards to each floor
        DisplayBoard displayBoard1 = parkingLot.getDisplayBoard(0);
        DisplayBoard displayBoard2 = parkingLot.getDisplayBoard(1);
        DisplayBoard displayBoard3 = parkingLot.getDisplayBoard(2);

        parkingLot.getFloor(0).addObserver(displayBoard1);
        parkingLot.getFloor(1).addObserver(displayBoard2);
        parkingLot.getFloor(2).addObserver(displayBoard3);

        System.out.println("\n--- Initial Available Spots ---");
        parkingLot.getFloor(0).notifyObservers();
        parkingLot.getFloor(1).notifyObservers();
        parkingLot.getFloor(2).notifyObservers();




        // Create vehicles
        Vehicle car1 = new Car("KA-01-1234");
        Vehicle truck1 = new Truck("KA-02-5678");
        Vehicle bike1 = new Motorcycle("KA-03-9876");

        // Park vehicles
        System.out.println("\n--- Parking Vehicles ---");
        parkVehicle(parkingLot, car1);
        parkVehicle(parkingLot, truck1);
        parkVehicle(parkingLot, bike1);

        // Display available spots after parking
        System.out.println("\n--- Displaying Available Spots After Parking ---");
        displayBoard1.display(0);
        displayBoard2.display(1);
        displayBoard3.display(2);

        // Remove a vehicle
        System.out.println("\n--- Removing Car KA-01-1234 ---");
        removeVehicle(parkingLot, "KA-01-1234");

        // Display available spots after removing vehicle
        System.out.println("\n--- Displaying Available Spots After Removal ---");
        displayBoard1.display(0);
        displayBoard2.display(1);
        displayBoard3.display(2);
    }

    // Helper method to park a vehicle
    private static void parkVehicle(ParkingLot parkingLot, Vehicle vehicle) {
        if (parkingLot.parkVehicle(vehicle)) {
            System.out.println("Vehicle " + vehicle.getLicenseNumber() + " parked successfully.");
        } else {
            System.out.println("No available spots for " + vehicle.getLicenseNumber());
        }
    }

    // Helper method to remove a vehicle
    private static void removeVehicle(ParkingLot parkingLot, String licenseNumber) {
        if (parkingLot.removeVehicle(licenseNumber)) {
            System.out.println("Vehicle " + licenseNumber + " removed successfully.");
            ProcessPayment processPayment = new ProcessPayment(new CashPayment(), 100);
        } else {
            System.out.println("Vehicle " + licenseNumber + " not found.");
        }
    }





    }



