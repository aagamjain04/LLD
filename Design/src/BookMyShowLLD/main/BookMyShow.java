package BookMyShowLLD.main;

import BookMyShowLLD.service.BookingService;

public class BookMyShow {
    public static void main(String[] args) {
        BookingService bookingService = BookingService.getInstance();
        bookingService.initialize();
        bookingService.startBookingSession();

    }
}
