package CarRentalSystemLLD;

import CarRentalSystemLLD.Product.Status;
import CarRentalSystemLLD.Product.Vehicle;
import CarRentalSystemLLD.Product.VehicleType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Store {
    int storeId;
    Location location;
    List<Reservation> reservationList;
    VehicleInventoryManagement vehicleInventoryManagement;

    public Store(Location location,int storeId){
        this.storeId = storeId;
        this.location = location;
        reservationList = new ArrayList<>();
        vehicleInventoryManagement = new VehicleInventoryManagement();
    }

    public VehicleInventoryManagement getVehicleInventoryManagement(){
        return vehicleInventoryManagement;
    }



    public Location getLocation(){
        return location;
    }

    public List<Vehicle> getVehicles(VehicleType vehicleType){
        return vehicleInventoryManagement.getVehicles(vehicleType);
    }

    public void addVehicle(Vehicle vehicle){
        vehicleInventoryManagement.addVehicle(vehicle);
    }

    public void removeVehicle(Vehicle vehicle){
        vehicleInventoryManagement.removeVehicle(vehicle);
    }

    public Reservation createReservation(User user, Vehicle vehicle, LocalDateTime bookedFrom,LocalDateTime bookedTo){
        if(vehicle.getStatus() == Status.INACTIVE){
            System.out.println("Vehicle is inactive and not ready for reservation");
            return null;
        }

        Reservation reservation = new Reservation(user,vehicle,bookedFrom,bookedTo);
        reservationList.add(reservation);
        vehicle.setStatus(Status.INACTIVE);
        return reservation;
    }

    public void completeReservation(Vehicle vehicle){

        Optional<Reservation> reservationOptional = reservationList.stream()
                .filter(reservation -> reservation.getVehicle().getVehicleId() == vehicle.getVehicleId())
                .findFirst();

        if(reservationOptional.isEmpty()){
            System.out.println("No reservation found for vehicle " + vehicle.getVehicleId());
            return;
        }

        reservationList.remove(reservationOptional.get());
        System.out.println("Reservation found for vehicle " + vehicle.getVehicleId() + " and is completed");
        vehicle.setStatus(Status.ACTIVE);

    }


}
