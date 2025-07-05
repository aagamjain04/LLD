package CarRentalSystemLLD;

import CarRentalSystemLLD.Product.Vehicle;

import java.time.LocalDateTime;
import java.util.Date;

public class Reservation {
    ReservationStatus reservationStatus;
    User user;
    Vehicle vehicle;
    int reservationID;
    Date bookingDate;
    LocalDateTime bookedFrom;
    LocalDateTime bookedTo;

    public Reservation(User user, Vehicle vehicle, LocalDateTime bookedFrom, LocalDateTime bookedTo) {
        this.reservationStatus = ReservationStatus.SCHEDULED;
        this.user = user;
        this.vehicle = vehicle;
        this.reservationID+=1;
        this.bookingDate = new Date();
        this.bookedFrom = bookedFrom;
        this.bookedTo = bookedTo;
    }


    //getter and setter


    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalDateTime getBookedFrom() {
        return bookedFrom;
    }

    public void setBookedFrom(LocalDateTime bookedFrom) {
        this.bookedFrom = bookedFrom;
    }

    public LocalDateTime getBookedTo() {
        return bookedTo;
    }

    public void setBookedTo(LocalDateTime bookedTo) {
        this.bookedTo = bookedTo;
    }
}
