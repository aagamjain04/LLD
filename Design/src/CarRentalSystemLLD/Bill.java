package CarRentalSystemLLD;

import CarRentalSystemLLD.Product.Vehicle;

public class Bill {
    Reservation reservation;
    double amount;
    Boolean isPaid;

    public Bill(Reservation reservation) {
        this.reservation = reservation;
        this.amount = computeBillAmount(reservation.getVehicle());
        this.isPaid = false;
    }

    private double computeBillAmount(Vehicle vehicle){
        return vehicle.getRentalPricePerDay() + (reservation.getBookedTo().getHour() - reservation.getBookedFrom().getHour())*50;
    }

    public void setPaid(Boolean paidStatus){
        this.isPaid = paidStatus;
    }

    public double getAmount() {
        return amount;
    }
}
