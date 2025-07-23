package BookMyShowLLD.main;

import BookMyShowLLD.Service.BookingService;

public class BookMyShow {
    public static void main(String[] args) {
        BookingService bookingService = BookingService.getInstance();
        bookingService.initialize();
        bookingService.startBookingSession();

    }
}
