package CarRentalSystemLLD;

import CarRentalSystemLLD.Product.Vehicle;
import CarRentalSystemLLD.Product.VehicleType;

import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        VehicleRentalSystem vehicleRentalSystem = new VehicleRentalSystem();

        // add user
        vehicleRentalSystem.addUser(new User("Aagam", "436456456"));

        // add stores
        Location location1 = new Location("AECS","BLR","KA",560037);
        Location location2 = new Location("HSR","BLR","KA",560035);
        vehicleRentalSystem.addStore(new Store(location1,1));
        vehicleRentalSystem.addStore(new Store(location2,2));

        // adding some vehicles in location1
        Store store1 = vehicleRentalSystem.getStore(location1);
        VehicleInventoryManagement inventoryManagement = store1.getVehicleInventoryManagement();
        inventoryManagement.addVehicle(new Vehicle("1", VehicleType.CAR,"A","A",2019,1000.00));
        inventoryManagement.addVehicle(new Vehicle("2", VehicleType.CAR,"B","B",2018,950.00));


        //0. User comes
        User user = vehicleRentalSystem.getUsers().getFirst();

        //1. user search store based on location
        Store store = vehicleRentalSystem.getStores().getFirst();

        //2. get All vehicles you are interested in (based upon different filters)
        List<Vehicle> storeVehicle = store.getVehicleInventoryManagement().getVehicles(VehicleType.CAR);

        Vehicle selectedVehicle = storeVehicle.getFirst();

        //3. make reservation for a vehicle
        Reservation reservation = store.createReservation(user,selectedVehicle, LocalDateTime.of(2025, 6,16,16, 0),LocalDateTime.of(2025,6,16,18,0));


        //4. generate bill
        Bill bill = new Bill(reservation);

        //5. make payment
        Payment payment = new Payment();
        payment.payBill(PaymentMode.CASH,bill.getAmount(),bill);

        //6. trip completed, submit vehicle and close reservation
        store.completeReservation(selectedVehicle);


    }

}